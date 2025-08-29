package demo.vn.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    /**
     * Phương thức này được gọi khi người dùng truy cập vào URL /login
     * hoặc được chuyển hướng đến đây. Nhiệm vụ của nó là hiển thị form đăng nhập.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Chuyển tiếp yêu cầu đến file login.jsp để hiển thị
        request.getRequestDispatcher("loginvd1.jsp").forward(request, response);
    }

    /**
     * Phương thức này được gọi khi người dùng nhấn nút submit trên form đăng nhập
     * (vì form có method="post"). Nhiệm vụ của nó là xử lý logic đăng nhập.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        // Lấy dữ liệu từ tham số của form
        String user = req.getParameter("username");
        String pass = req.getParameter("password");

        // Kiểm tra đăng nhập (ví dụ đơn giản)
        if ("trung".equals(user) && "123".equals(pass)) {
            Cookie cookie = new Cookie("username", user); // Khởi tạo cookie
            
            // Thiết lập thời gian tồn tại 30 giây của cookie
            cookie.setMaxAge(30);
            
            // Thêm cookie vào response
            resp.addCookie(cookie);
            
            // Chuyển sang trang HelloServlet
            resp.sendRedirect(req.getContextPath() + "/hello"); 
        } else {
            // Chuyển về lại trang Login nếu sai
             resp.sendRedirect(req.getContextPath() + "/loginvd1");
        }
    }
}