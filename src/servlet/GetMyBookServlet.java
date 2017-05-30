package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Book;
import bean.Seller;
import dao.BookDao;

public class GetMyBookServlet extends HttpServlet {
	
	/**
	 * Destruction of the servlet. <br>
	 * ���û��鿴�ҷ�����ͼ��ʱ��ͨ����servlet��ȡ�ϴ���ͼ���image����myUpload��ת����
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
		
		ServletContext application = request.getServletContext();
		String project = application.getInitParameter("project");
		HttpSession session = request.getSession();
		Seller seller = (Seller)session.getAttribute("seller");
		if(seller == null){    // ����δ��¼
			response.sendRedirect("/" + project + "/login.jsp");
		}else{
			ArrayList<Book> myBook = (ArrayList<Book>)session.getAttribute("myBook");
			if(myBook != null){    // session �д���myBook��Ϣʱ������Ҫ�����ݿ��ж�ȡ
				response.sendRedirect("/" + project + "/myBook.jsp");
			}else{		// ��ȡmyBook��Ϣ
				BookDao bookDao = new BookDao();
				try {
					myBook = bookDao.getMyBook(seller.getSellerName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("GetMyBookServlet Error!");
					e.printStackTrace();
				}
				session.setAttribute("myBook", myBook);
				response.sendRedirect("/" + project + "/myBook.jsp");
			}
		}
		out.flush();
		out.close();
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
