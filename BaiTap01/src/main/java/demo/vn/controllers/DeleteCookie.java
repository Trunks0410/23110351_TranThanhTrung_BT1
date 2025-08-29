package demo.vn.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie; // <-- Import lớp Cookie
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter; // <-- Import lớp PrintWriter

/**
 * Servlet implementation class DeleteCookie
 */
@WebServlet("/DeleteCookie")
public class DeleteCookie extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCookie() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // --- ĐOẠN CODE CỦA BẠN ĐẶT Ở ĐÂY ---

        Cookie cookie = null;
        Cookie[] cookies = null;

        // Lấy danh sách cookie từ request
        cookies = request.getCookies();

        // Thiết lập kiểu nội dung trả về là HTML và hỗ trợ tiếng Việt
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Xử lý xóa Cookie</h2>");

        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i];
                // Tìm cookie có tên là "ten"
                if (cookie.getName().equals("ten")) {
                    // Đặt thời gian sống của cookie về 0 để xóa
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    out.print("<b>Đã xóa cookie có tên:</b> " + cookie.getName() + "<br>");
                }
            }
        } else {
            out.println("Không tìm thấy cookie nào để xóa.<br>");
        }
        
        out.println("</body></html>");
        out.close();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Gọi doGet để xử lý cho cả phương thức POST
        doGet(request, response);
    }
}