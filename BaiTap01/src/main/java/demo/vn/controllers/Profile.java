package demo.vn.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession; // Thêm import cho HttpSession
import java.io.IOException;
import java.io.PrintWriter; // Thêm import cho PrintWriter

@WebServlet("/Profile")
public class Profile extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Profile() {
        super();
    }

    /**
     * Phương thức này được gọi khi người dùng truy cập vào trang Profile.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Lấy session hiện tại, không tạo session mới nếu chưa có
        HttpSession session = request.getSession(false);

        if(session!=null){
        	String name=(String)session.getAttribute("name");
        	out.print("Chào bạn, "+name+" đến với trang quản lý tài khoản");
        	out.println("<a href='Logout'>Đăng xuất</a>");
        }
        else {
        	out.print("Xin vui lòng đăng nhập");
        	response.sendRedirect("login.jsp");
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}