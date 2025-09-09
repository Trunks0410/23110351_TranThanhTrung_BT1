package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import DAO.UserDAO;
import entity.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;

	public LoginServlet() {
		this.userDAO = new UserDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/views/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		User user = userDAO.findByUsernameAndPassword(username, password);

		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("currentUser", user);
			response.sendRedirect(request.getContextPath() + getHomePage(user.getRoleid()));
		} else {
			request.setAttribute("errorMessage", "Tên người dùng hoặc mật khẩu không đúng.");
			request.getRequestDispatcher("/views/login.jsp").forward(request, response);
		}
	}

	private String getHomePage(int roleId) {
		switch (roleId) {
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
