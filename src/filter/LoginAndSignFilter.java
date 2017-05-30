package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAndSignFilter implements Filter{
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;  
	    HttpServletResponse resp = (HttpServletResponse) response;  
		req.setCharacterEncoding("utf-8");
		String buyerName = req.getParameter("user");
		String password = req.getParameter("password");
		if(buyerName == null && password == null){        //当直接打开LoginServlet 和  SignServlet时url的传值为空，获取的buyerName为空，跳转到登录界面
			System.out.println("LoginAndSignFilter:用户名为空");
			System.out.println(buyerName + " " + password);
			ServletContext application = req.getServletContext();
			String project = application.getInitParameter("project");
			resp.sendRedirect("/" + project + "/login.jsp");
		}
		else{
			chain.doFilter(req, resp);
		}
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
