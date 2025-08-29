package demo.vn.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie; // <-- Import Cookie class
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter; // <-- Import PrintWriter class

/**
 * Servlet implementation class CreateCookie
 */
@WebServlet("/CreateCookie")
public class CreateCookie extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateCookie() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // --- YOUR CODE GOES HERE ---

        // Nhận dữ liệu từ FORM (Get data from the FORM)
        String ten = request.getParameter("ten");
        String holot = request.getParameter("holot");

        // Create cookies for first name (ten) and last name (holot).
        Cookie firstName = new Cookie("ten", ten);
        Cookie lastName = new Cookie("holot", holot);

        // Set expiry date after 24 Hrs for both the cookies.
        firstName.setMaxAge(60 * 60 * 24); // 24 hours in seconds
        lastName.setMaxAge(60 * 60 * 24);

        // Add both the cookies in the response header.
        response.addCookie(firstName);
        response.addCookie(lastName);

        // Send a response back to the browser
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h3>Cookies have been created successfully!</h3>");
        out.println("First Name (ten): " + firstName.getValue() + "<br>");
        out.println("Last Name (holot): " + lastName.getValue());
    }
}