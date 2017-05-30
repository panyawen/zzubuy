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

import com.sun.codemodel.fmt.JStaticJavaFile.ChainFilter;

import bean.Seller;
import dao.SellerDao;

public final class SellerNotExistFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;
		HttpSession session = (HttpSession)request.getSession();
		String sellerName = (String)session.getAttribute("user");
		ServletContext application = request.getServletContext();
		String project = application.getInitParameter("project");
		if(sellerName == null)         // 当用户未登录时，跳转到登录界面
			response.sendRedirect("/" + project + "/login.jsp");
		else{
			String fromRelease = (String)session.getAttribute("fromRelease");
			if(fromRelease != null){    // 当时上一个界面是ReleaseServlet时，可直接跳转到相应界面，因为ReleaseServlet中已经判断了seller不存在
				session.removeAttribute("fromRelease");
				chain.doFilter(arg0, arg1);
			}else{    // 当用户并不是从ReleaseServlet中跳转时，需判断seller是否存在。
				SellerDao sellerDao = new SellerDao();
				Seller seller = null;
				try {
					seller = sellerDao.getSeller(sellerName);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("SellerNotExist: getSeller Error!");
					e.printStackTrace();
				}
				if(seller != null){     // seller表中存在该用户的信息，不用跳转到addInfo.jsp中添加信息
					session.setAttribute("seller", seller);
					response.sendRedirect("/" + project + "/show.jsp");
				}else{
					chain.doFilter(arg0, arg1);
				}
			}
		}
			
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
