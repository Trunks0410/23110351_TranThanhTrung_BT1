/*
 * Servlet implementation class CategoryServlet
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import DAO.CategoryDAO;
import entity.Category;
import entity.User;

@WebServlet(urlPatterns = { "/admin/category/home", "/manager/category/home", "/user/category/home",
		"/admin/category/create", "/manager/category/create", "/user/category/create", "/admin/category/edit",
		"/manager/category/edit", "/user/category/edit", "/admin/category/delete", "/manager/category/delete",
		"/user/category/delete" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class CategoryServlet extends HttpServlet {

	private CategoryDAO categoryDAO;
	private static final String UPLOAD_DIRECTORY = "views/admin/images";

	public CategoryServlet() {
		this.categoryDAO = new CategoryDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		User currentUser = (User) request.getSession().getAttribute("currentUser");

		if (currentUser == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		if (path.endsWith("/category/home")) {
			listCategoryByRole(request, response, currentUser);
		} else if (path.endsWith("/create")) {
			showCreateForm(request, response);
		} else if (path.endsWith("/edit")) {
			showEditForm(request, response, currentUser);
		} else if (path.endsWith("/delete")) {
			deleteCategory(request, response, currentUser);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		User currentUser = (User) request.getSession().getAttribute("currentUser");

		if (currentUser == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		// Check if it's an update or create based on whether an ID is present in the
		// request
		String cateIdParam = request.getParameter("id");
		if (cateIdParam != null && !cateIdParam.isEmpty()) {
			updateCategory(request, response, currentUser);
		} else {
			insertCategory(request, response, currentUser);
		}
	}

	private void listCategoryByRole(HttpServletRequest request, HttpServletResponse response, User currentUser)
			throws ServletException, IOException {
		List<Category> listCategory;
		if (currentUser.getRoleid() == 3 || currentUser.getRoleid() == 1) { // Admin and User can see all categories
			listCategory = categoryDAO.findAll();
		} else { // Manager can only see their own categories
			listCategory = categoryDAO.findByUserId(currentUser.getId());
		}
		request.setAttribute("listCategory", listCategory);
		request.getRequestDispatcher("/views/admin/category-list.jsp").forward(request, response);
	}

	private void showCreateForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/views/admin/category-form.jsp").forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response, User currentUser)
			throws ServletException, IOException {
		try {
			int cateId = Integer.parseInt(request.getParameter("id"));
			Category existingCategory = categoryDAO.findById(cateId);

			if (currentUser.getRoleid() != 3 && existingCategory.getUser().getId() != currentUser.getId()) {
				request.setAttribute("errorMessage",
						"Truy cập bị từ chối: Bạn chỉ có thể chỉnh sửa danh mục của mình.");
				try {
					listCategoryByRole(request, response, currentUser);
				} catch (ServletException | IOException e1) {
					e1.printStackTrace();
				}
				return;
			}

			request.setAttribute("category", existingCategory);
			request.getRequestDispatcher("/views/admin/category-form.jsp").forward(request, response);
		} catch (NumberFormatException e) {
			request.setAttribute("errorMessage", "ID danh mục không hợp lệ.");
			try {
				listCategoryByRole(request, response, currentUser);
			} catch (ServletException | IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	private void insertCategory(HttpServletRequest request, HttpServletResponse response, User currentUser)
			throws ServletException, IOException {
		try {
			String cateName = request.getParameter("cateName");

			if (cateName == null || cateName.trim().isEmpty()) {
				request.setAttribute("errorMessage", "Tên danh mục không được để trống.");
				request.getRequestDispatcher("/views/admin/category-form.jsp").forward(request, response);
				return;
			}

			Part filePart = request.getPart("icon");
			String iconPath = saveUploadedFile(filePart, request);

			Category newCategory = new Category();
			newCategory.setCatename(cateName);
			newCategory.setIcon(iconPath);
			newCategory.setUser(currentUser);

			categoryDAO.create(newCategory);

			response.sendRedirect(request.getContextPath() + getRedirectPath(currentUser));
		} catch (Exception e) {
			System.err.println("Lỗi khi thêm danh mục: " + e.getMessage());
			e.printStackTrace();
			request.setAttribute("errorMessage", "Đã xảy ra lỗi khi thêm danh mục.");
			request.getRequestDispatcher("/views/admin/category-form.jsp").forward(request, response);
		}
	}

	private void updateCategory(HttpServletRequest request, HttpServletResponse response, User currentUser)
			throws ServletException, IOException {
		try {
			int cateId = Integer.parseInt(request.getParameter("id"));
			String cateName = request.getParameter("cateName");

			String existingIconPath = request.getParameter("existingIcon");

			Category categoryToUpdate = categoryDAO.findById(cateId);
			if (currentUser.getRoleid() != 3 && categoryToUpdate.getUser().getId() != currentUser.getId()) {
				request.setAttribute("errorMessage", "Truy cập bị từ chối: Bạn chỉ có thể cập nhật danh mục của mình.");
				try {
					listCategoryByRole(request, response, currentUser);
				} catch (ServletException | IOException e1) {
					e1.printStackTrace();
				}
				return;
			}

			String iconPath = existingIconPath;
			Part filePart = request.getPart("icon");
			if (filePart != null && filePart.getSize() > 0) {
				iconPath = saveUploadedFile(filePart, request);
			}

			categoryToUpdate.setCatename(cateName);
			categoryToUpdate.setIcon(iconPath);
			categoryDAO.update(categoryToUpdate);
			response.sendRedirect(request.getContextPath() + getRedirectPath(currentUser));
		} catch (NumberFormatException e) {
			request.setAttribute("errorMessage", "ID danh mục không hợp lệ.");
			try {
				listCategoryByRole(request, response, currentUser);
				return;
			} catch (ServletException | IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	private void deleteCategory(HttpServletRequest request, HttpServletResponse response, User currentUser)
			throws IOException {
		try {
			int cateId = Integer.parseInt(request.getParameter("id"));
			Category categoryToDelete = categoryDAO.findById(cateId);

			if (currentUser.getRoleid() != 3 && categoryToDelete.getUser().getId() != currentUser.getId()) {
				request.setAttribute("errorMessage", "Truy cập bị từ chối: Bạn chỉ có thể xóa danh mục của mình.");
				try {
					listCategoryByRole(request, response, currentUser);
				} catch (ServletException | IOException e1) {
					e1.printStackTrace();
				}
				return;
			}

			categoryDAO.remove(cateId);
			response.sendRedirect(request.getContextPath() + getRedirectPath(currentUser));
		} catch (NumberFormatException e) {
			request.setAttribute("errorMessage", "ID danh mục không hợp lệ.");
			try {
				listCategoryByRole(request, response, currentUser);
			} catch (ServletException | IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	// Helper method to save uploaded file
	private String saveUploadedFile(Part filePart, HttpServletRequest request) throws IOException {
		if (filePart == null || filePart.getSize() == 0)
			return null;
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
		String uploadPath = request.getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists())
			uploadDir.mkdirs();
		String relativePath = UPLOAD_DIRECTORY + File.separator + fileName;
		filePart.write(uploadPath + File.separator + fileName);
		return relativePath.replace("\\", "/");
	}

	private String getRedirectPath(User user) {
		switch (user.getRoleid()) {
		case 1:
			return "/user/category/home";
		case 2:
			return "/manager/category/home";
		case 3:
			return "/admin/category/home";
		default:
			return "/";
		}
	}
}
