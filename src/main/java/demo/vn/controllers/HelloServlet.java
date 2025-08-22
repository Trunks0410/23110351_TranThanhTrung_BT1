package demo.vn.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie; // <-- Thêm import cho Cookie
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter; // <-- Thêm import cho PrintWriter

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/hello") // <-- Sửa URL để khớp với logic chuyển hướng
public class HelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = resp.getWriter();
        String name = "";

        // Nhận mảng cookie từ request
        Cookie[] cookies = req.getCookies();

        // Kiểm tra xem mảng cookie có tồn tại không
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("username")) {
                    name = c.getValue();
                    break; // Thoát vòng lặp khi đã tìm thấy
                }
            }
        }

        if (name.equals("")) {
            // Nếu không tìm thấy cookie "username", chuyển về trang login
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            // Hiển thị lời chào nếu đã đăng nhập
            printWriter.println("<h1>Xin chào " + name + "!</h1>");
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}