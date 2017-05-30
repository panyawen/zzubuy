package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginJspFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpServletRequest req = (HttpServletRequest) request;  
	    HttpServletResponse resp = (HttpServletResponse) response; 
	    HttpSession session = req.getSession();
	    String user = (String)session.getAttribute("user");
	    if(user == null)
	    	chain.doFilter(req, resp);
	    else{
	    	ServletContext application = req.getServletContext();
	    	String project = application.getInitParameter("project");
	    	resp.sendRedirect("/" + project + "/" + "show.jsp");
	    }
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
