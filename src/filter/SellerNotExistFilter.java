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
		if(sellerName == null)         // ���û�δ��¼ʱ����ת����¼����
			response.sendRedirect("/" + project + "/login.jsp");
		else{
			String fromRelease = (String)session.getAttribute("fromRelease");
			if(fromRelease != null){    // ��ʱ��һ��������ReleaseServletʱ����ֱ����ת����Ӧ���棬��ΪReleaseServlet���Ѿ��ж���seller������
				session.removeAttribute("fromRelease");
				chain.doFilter(arg0, arg1);
			}else{    // ���û������Ǵ�ReleaseServlet����תʱ�����ж�seller�Ƿ���ڡ�
				SellerDao sellerDao = new SellerDao();
				Seller seller = null;
				try {
					seller = sellerDao.getSeller(sellerName);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("SellerNotExist: getSeller Error!");
					e.printStackTrace();
				}
				if(seller != null){     // seller���д��ڸ��û�����Ϣ��������ת��addInfo.jsp�������Ϣ
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
