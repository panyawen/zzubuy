package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bean.Seller;
import dao.SellerDao;

public class AddInfoServlet extends HttpServlet {

	/**
	 * Destruction of the servlet. <br>
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

		response.setContentType("text/html;charset=utf-8");   //设置输出到页面的编码方式
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");   // 设置resquest获取内容的编码
		String tel = request.getParameter("tel");
		String qq = request.getParameter("qq");
		String weixin = request.getParameter("weixin");
		//out.println("tel:" + tel + "<br>qq:" + qq + "<br>weixin:" + weixin);
		HttpSession session = request.getSession();
		String sellerName = (String)session.getAttribute("user");
		ServletContext application = getServletContext();
		String project = application.getInitParameter("project");
		if(sellerName == null){
			response.sendRedirect("/" + project + "/login.jsp");
		}
		Seller seller = new Seller();
		seller.setSellerName(sellerName);
		seller.setTel(tel);
		seller.setWeixin(weixin);
		seller.setQq(qq);
//		seller.setUploadCnt(0);
		seller.setNowCnt(0);
		SellerDao sellerDao = new SellerDao();
		
//		out.println("sellerName:" + sellerName + "<br>");
//		out.println("tel:" + tel + "<br>");
//		out.println("qq:" + qq + "<br>");
//		out.println("weixin:" + weixin + "<br>");
//		out.println("uploadCnt:" + seller.getUploadCnt() + "<br>");
//		out.println("nowCnt:" + seller.getNowCnt() + "<br>");
		
		int cnt = 0;
		try {
			cnt = sellerDao.addSeller(seller);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("AddInfoServlet: 插入seller错误！");
			e.printStackTrace();
		}
		if(cnt != 1){
			out.println("添加信息失败");
		}
		else{
			session.setAttribute("seller", seller);
			response.sendRedirect("/" + project + "/release.jsp");
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
