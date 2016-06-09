package hotel.Filters;

import hotel.controller.SessionBean;

import javax.inject.Inject;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by grupoeuropa on 09/06/16.
 */
@WebFilter(filterName = "FiltroLogin" , urlPatterns = "/*")
public class LoginFilter {
	@Inject
	private SessionBean sessionBean;

	public void init(){

	}

	public void destroy(){

	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;

		if(sessionBean.getUsuarioLogado()==null){
			response.sendRedirect("/");
		}
	}
}