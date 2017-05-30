package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import javax.lang.model.element.TypeElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookDao;
import bean.Book;
import bean.Seller;

public class DeleteMyBookServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DeleteMyBookServlet() {
		super();
	}

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

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		Seller seller = (Seller)session.getAttribute("seller");
		String index = request.getParameter("index");
		ServletContext application = request.getServletContext();
		String project = application.getInitParameter("project");
		ArrayList<Book> myBook = (ArrayList<Book>)session.getAttribute("myBook");
		if(seller == null || myBook == null)
			response.sendRedirect("/" + project + "/show.jsp");
		else if(index == null)
			response.sendRedirect("/" + project + "/myBook.jsp");
		
		BookDao bookDao = new BookDao();
		Book book = myBook.get(Integer.valueOf(index));
		String image = book.getImage();
		out.println(image);
		int cnt = 0;
		try {
			cnt = bookDao.DeleteBook(image);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("DeleteMyBookServlet: 数据库记录删除错误!");
			e.printStackTrace();
		}
		if(cnt == 0){
			out.println("数据库记录删除错误！");
		}else{
			out.println("数据库记录删除成功！<br>");
			boolean res = myBook.remove(myBook.get(Integer.valueOf(index)));   // 删除index对应的图书
																			   // 问题：remove(int index)无法删除，还没解决
			
//			if(res)
//				out.println("delete成功");
//			else
//				out.println("delete失败");
//			out.println(res);
//			out.println(myBook.size());
			
			File file = new File("G:\\web_workspaces\\zzubuy\\WebRoot\\book\\",image+".jpg");
            boolean del = file.delete();
            if(del){
            	response.sendRedirect("/" + project + "/myBook.jsp");
            }
            else{
            	session.setAttribute("ErrorInfo", "图片删除错误");
            	response.sendRedirect("/" + project + "/myBook.jsp");
            }
		}
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
