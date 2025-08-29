package demo.vn.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession; // <-- Thêm import cho HttpSession
import java.io.IOException;
import java.io.PrintWriter; // <-- Thêm import cho PrintWriter

/**
 * Servlet implementation class CreateSession
 */
@WebServlet("/CreateSession")
public class CreateSession extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CreateSession() {
        super();
    }

    /**
     * Phương thức này được gọi khi người dùng truy cập URL /CreateSession.
     * Nó sẽ tạo ra một session mới.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Khởi tạo session. Lệnh này sẽ lấy session hiện tại nếu có,
        // hoặc tạo một session mới nếu chưa có.
        HttpSession s = req.getSession();

        // Gán dữ liệu vào session
        s.setAttribute("ten", "Trần Thành Trung");
        s.setAttribute("tuoi", 20); // Không cần new Integer(40)

        // Thiết lập thời gian session tồn tại (tính bằng giây)
        // Session sẽ tự hủy nếu không có hoạt động nào trong 30 giây.
        s.setMaxInactiveInterval(30);

        // Hiển thị thông báo lên web
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("Xin chào, session đã được tạo!");
        out.close();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}