package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UserService;

import java.io.IOException;

@WebServlet("/forgot-password")
public class ForgotPasswordController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService service;

    public ForgotPasswordController() {
        super();
        this.service = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/forgot-password.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String alertMsg = "";
        
        // Gọi phương thức trong UserService để xử lý logic
        boolean isSuccess = service.forgotPassword(email);
        
        if (isSuccess) {
            alertMsg = "Mật khẩu mới đã được gửi đến email của bạn. Vui lòng kiểm tra hộp thư.";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/forgot-password.jsp").forward(req, resp);
        } else {
            alertMsg = "Email không tồn tại trong hệ thống!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/forgot-password.jsp").forward(req, resp);
        }
    }
}