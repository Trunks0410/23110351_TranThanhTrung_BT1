package demo.vn.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession; // Thêm import cho HttpSession
import java.io.IOException;
import java.io.PrintWriter; // Thêm import cho PrintWriter

@WebServlet("/Login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Login() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Chuyển hướng đến trang login nếu người dùng truy cập bằng GET
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    /**
     * Phương thức này xử lý dữ liệu khi người dùng nhấn nút submit trên form đăng nhập.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html;charset=UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if ("trung".equals(username) && "123".equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("name", username);

            response.sendRedirect("Profile");

        } else {
            PrintWriter out = response.getWriter();
            out.print("<p style='color:red;'>Tài khoản hoặc mật khẩu không chính xác</p>");
            request.getRequestDispatcher("login.jsp").include(request, response);
        }
    }
}