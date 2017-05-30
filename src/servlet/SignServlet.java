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
		request.setCharacterEncoding("utf-8");   // 设置resquest获取内容的编码
		response.setContentType("text/html;charset=utf-8");   //设置输出到页面的编码方式
		PrintWriter out = response.getWriter();
		String buyerName = request.getParameter("user"); //new String(request.getParameter("signUser").getBytes("ISO-8859-1"),"gb2312");
		String password = request.getParameter("password");
	//	buyerName = java.net.URLDecoder.decode(buyerName,"UTF-8");  //JS 通过url转码后传值过来，该处需要解码
	//	password = java.net.URLDecoder.decode(password,"UTF-8");
		
//		out.println("name:  " + buyerName);
//		out.println(password);
		BuyerDao buyerDao = new BuyerDao();  
		HttpSession session = request.getSession();
		ServletContext application = getServletContext();
		String project = application.getInitParameter("project");
		try {
			if(!buyerDao.validate(buyerName)){      // 验证该账号有没有被注册!
				//System.out.println("该用户名已注册！" + buyerName);
//				session.setAttribute("signInfo","该账号已注册!");
				response.sendRedirect("/" + project + "/signFail.jsp");
				return ;
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.out.println("\n SignServlet:查询异常 \n");
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
			System.out.println("\n SignServlet:插入异常 \n");
			e.printStackTrace();
		}
		if(addRes != 0){
//			System.out.println("signservlet:成功注册 " + addRes + "行");
//			session.setAttribute("signInfo","注册成功，您现在可以登录啦!");
			response.sendRedirect("/" + project + "/signSuccess.jsp");
		}
		else{
//		    System.out.println("signServlet:注册失败");
//			session.setAttribute("signInfo","注册失败!");
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
