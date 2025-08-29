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
 * Servlet implementation class ReadCookie
 */
@WebServlet("/ReadCookie")
public class ReadCookie extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadCookie() {
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

        // Thiết lập kiểu nội dung trả về là HTML
        response.setContentType("text/html;charset=UTF-T-8");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Thông tin Cookies đã lưu:</h2>");

        if (cookies != null) {
            out.println("Đã tìm thấy Cookies!<br>");
            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i];
                out.print("<b>Tên:</b> " + cookie.getName() + " | ");
                out.print("<b>Giá trị:</b> " + cookie.getValue() + "<br>");
            }
        } else {
            out.println("Không tìm thấy cookie nào!");
        }
        
        out.println("</body></html>");
        out.close();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Thông thường, ta sẽ gọi doGet để xử lý cho cả POST
        doGet(request, response);
    }
}