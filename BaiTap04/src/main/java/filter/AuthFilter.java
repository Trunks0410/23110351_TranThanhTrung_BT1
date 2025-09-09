package filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import entity.User;
import java.io.IOException;

@WebFilter({ "/admin/*", "/manager/*", "/user/*" })
public class AuthFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String path = httpRequest.getServletPath();

		HttpSession session = httpRequest.getSession(false);
		User currentUser = (session != null) ? (User) session.getAttribute("currentUser") : null;

		if (currentUser == null) {
			// User is not logged in, redirect to login page
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
			return;
		}

		int userRole = currentUser.getRoleid();

		// Check access for Admin URLs
		if (path.startsWith("/admin/")) {
			if (userRole != 3) {
				httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied: Admin role required.");
				return;
			}
		}

		// Check access for Manager URLs
		if (path.startsWith("/manager/")) {
			if (userRole < 2) {
				httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN,
						"Access Denied: Manager or Admin role required.");
				return;
			}
		}

		// Check access for User URLs
		if (path.startsWith("/user/")) {
			if (userRole < 1) { // User role is 1, so this check ensures the user is logged in.
				httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied: User role required.");
				return;
			}
		}

		// Pass the request along the filter chain
		chain.doFilter(request, response);
	}
}
