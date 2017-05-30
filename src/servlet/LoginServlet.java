package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jms.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Buyer;
import dao.BuyerDao;

public class LoginServlet extends HttpServlet {

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

		request.setCharacterEncoding("utf-8");   // ����resquest��ȡ���ݵı���
		response.setContentType("text/html;charset=utf-8");   //���������ҳ��ı��뷽ʽ
		PrintWriter out = response.getWriter();
		String buyerName = request.getParameter("user");
		String password = request.getParameter("password");
		HttpSession session = (HttpSession)request.getSession();
//		buyerName = java.net.URLDecoder.decode(buyerName,"UTF-8");   //JS ͨ��urlת���ֵ�������ô���Ҫ����
		
		BuyerDao buyerDao = new BuyerDao();
		Buyer buyer = null;
		try {
			buyer = buyerDao.loginCheck(buyerName); //��ѯ�û���ΪbuyerName���û�
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Exception LoginServlet");
			e.printStackTrace();
		}
		if(buyer != null && buyer.password != null && buyer.password.equals(password)){   //��֤buyerName�Ƿ���ڣ������Ƿ���ȷ
			session.setAttribute("user", buyer.getUserName());
			out.print("true");     // login.jsp���жϸ�ҳ������ݣ�Ȼ��������Ӧ��Ӧ
//			response.sendRedirect("/" + project + "/" + "loginSuccess.jsp");
		}
		else{
			out.print("false");
//			response.sendRedirect("/" + project + "/" + "loginFail.jsp");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}
}
