package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jms.Session;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BuyerDao;
import bean.Buyer;

public class SignServlet extends HttpServlet {
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
		String buyerName = request.getParameter("user"); //new String(request.getParameter("signUser").getBytes("ISO-8859-1"),"gb2312");
		String password = request.getParameter("password");
	//	buyerName = java.net.URLDecoder.decode(buyerName,"UTF-8");  //JS ͨ��urlת���ֵ�������ô���Ҫ����
	//	password = java.net.URLDecoder.decode(password,"UTF-8");
		
//		out.println("name:  " + buyerName);
//		out.println(password);
		BuyerDao buyerDao = new BuyerDao();  
		HttpSession session = request.getSession();
		ServletContext application = getServletContext();
		String project = application.getInitParameter("project");
		try {
			if(!buyerDao.validate(buyerName)){      // ��֤���˺���û�б�ע��!
				//System.out.println("���û�����ע�ᣡ" + buyerName);
//				session.setAttribute("signInfo","���˺���ע��!");
				response.sendRedirect("/" + project + "/signFail.jsp");
				return ;
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.out.println("\n SignServlet:��ѯ�쳣 \n");
			e1.printStackTrace();
		}
		
		Buyer buyer = new Buyer();
		buyer.setUserName(buyerName);
		buyer.setPassword(password);
		int addRes = 0 ;
		try {
			addRes = buyerDao.addBuyer(buyer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("\n SignServlet:�����쳣 \n");
			e.printStackTrace();
		}
		if(addRes != 0){
//			System.out.println("signservlet:�ɹ�ע�� " + addRes + "��");
//			session.setAttribute("signInfo","ע��ɹ��������ڿ��Ե�¼��!");
			response.sendRedirect("/" + project + "/signSuccess.jsp");
		}
		else{
//		    System.out.println("signServlet:ע��ʧ��");
//			session.setAttribute("signInfo","ע��ʧ��!");
			response.sendRedirect("/" + project + "/signError.jsp");
		}
		//out.println("userName:" + buyerName + "<br>password:" + password + "<br>");
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

}
