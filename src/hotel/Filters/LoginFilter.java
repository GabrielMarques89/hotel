package hotel.Filters;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by grupoeuropa on 09/06/16.
 */
@WebFilter(filterName = "FiltroLogin" , urlPatterns = "/*")
public class LoginFilter {

	public void init(){

	}

	public void destroy(){

	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(false);
		String loginURI = request.getContextPath() + "/login";

		boolean loggedIn = session != null && session.getAttribute("user") != null;
		boolean loginRequest = request.getRequestURI().equals(loginURI);

		if (loggedIn || loginRequest) {
			chain.doFilter(request, response);
		} else {
			response.sendRedirect(loginURI);
		}
	}
}