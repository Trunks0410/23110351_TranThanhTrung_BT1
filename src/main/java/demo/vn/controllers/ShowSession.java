package demo.vn.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession; // <-- Thêm import cho HttpSession
import java.io.IOException;
import java.io.PrintWriter; // <-- Thêm import cho PrintWriter

@WebServlet("/ShowSession")
public class ShowSession extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ShowSession() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();

        // Lấy session hiện tại, không tạo mới nếu chưa có (truyền tham số false)
        HttpSession s = req.getSession(false);

        // --- PHIÊN BẢN AN TOÀN HƠN ---
        if (s == null) {
            // Nếu không có session nào, chuyển về trang tạo session
            out.println("Session không tồn tại hoặc đã hết hạn. Đang chuyển hướng...");
            resp.setHeader("Refresh", "3; URL=" + req.getContextPath() + "/CreateSession");
        } else {
            // Lấy dữ liệu từ session
            Object tenObj = s.getAttribute("ten");
            Object tuoiObj = s.getAttribute("tuoi");

            // Kiểm tra xem dữ liệu có tồn tại trong session không
            if (tenObj != null && tuoiObj != null) {
                String ten = tenObj.toString();
                int tuoi = (Integer) tuoiObj;

                // Hiển thị session lên web
                out.println("<h1>Thông tin từ Session</h1>");
                out.println("Xin chào bạn: " + ten + "<br>");
                out.println("Tuổi: " + tuoi);
            } else {
                // Nếu có session nhưng thiếu dữ liệu, cũng chuyển về trang tạo
                out.println("Dữ liệu trong session không đầy đủ. Đang chuyển hướng...");
                resp.setHeader("Refresh", "3; URL=" + req.getContextPath() + "/CreateSession");
            }
        }
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}