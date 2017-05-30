package filter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import bean.Book;
import dao.BookDao;

/** 
 * 访问bookShow.jsp界面时，确保session中已存在所有书籍的image(book表的主码，本地图片的名)    
 *  */

public class GetBookFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		ServletContext application = req.getServletContext();
		String project = application.getInitParameter("project");
		BookDao bookDao = new BookDao();
		ArrayList<Book> al = null;
		try {
			al = bookDao.getBook();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(al == null){
			RequestDispatcher rd = application.getRequestDispatcher("/" + project + "/Error.jsp");
			rd.forward(request, response);
		}else{
			session.setAttribute("bookArrayList", al);
//			System.out.println("GetBookNameFilter: ");
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
}
