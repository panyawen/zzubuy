package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




import bean.Seller;
import dao.SellerDao;

public class MyUploadServlet extends HttpServlet {

	/**
	 * Destruction of the servlet. <br>
	 * ����Ҫ�鿴�ҷ�������Ʒʱ����Ҫ��ͨ����servlet�жϵ�ǰ�û���seller���Ƿ��м�¼����show.jsp��ת������
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String user = (String)session.getAttribute("user");
		ServletContext application = request.getServletContext();
		String project = application.getInitParameter("project");
		if(user == null){                //  �û�δ��¼
			response.sendRedirect("/" + project + "/login.jsp");
		}else{
			SellerDao sellerDao = new SellerDao();
			Seller seller = null;
			try {
				seller = sellerDao.getSeller(user);     // ��seller���ж�ȡuser����Ϣ
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("ReleaseServlet  getSeller Error!");
				e.printStackTrace();
				RequestDispatcher rd = application.getRequestDispatcher("/Error.jsp");
				rd.forward(request, response);
				return ;
			}
			if(seller == null){         
				//out.println(seller.getSellerName());
				session.setAttribute("fromRelease","yes" );
				response.sendRedirect("/" + project + "/uploadEmpty.jsp");
			}else{             // ������Ʒ
				session.setAttribute("seller", seller);
				response.sendRedirect("/" + project + "/myUpload.jsp");
			}
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
