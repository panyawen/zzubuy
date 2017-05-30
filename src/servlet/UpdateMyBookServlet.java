package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;

import dao.BookDao;
import dao.SellerDao;
import bean.Book;
import bean.Seller;

public class UpdateMyBookServlet extends HttpServlet {

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

		response.setContentType("text/html; charSet=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String sellerName = (String)session.getAttribute("user");
		Seller seller = (Seller)session.getAttribute("seller");
		ArrayList<Book> myBook = (ArrayList<Book>)session.getAttribute("myBook");
		ServletContext application = request.getServletContext();
		String project = application.getInitParameter("project");
		if(sellerName == null || seller == null){
			response.sendRedirect("/" + project + "/login.jsp");
		}else if(myBook == null){
			response.sendRedirect("/" + project + "/show.jsp");
		}
		else{
			SmartUpload smartUpload = new SmartUpload();
			Request req = smartUpload.getRequest();
			ServletConfig config = this.getServletConfig();
			smartUpload.initialize(config, request, response);
			try{
				smartUpload.upload();
			}catch(Exception e){
				System.out.println("BookUpoad: Error! 上传错误");
				e.printStackTrace();
			}
			String bookName = req.getParameter("bookName");
			String author = req.getParameter("author");
			String publishing = req.getParameter("publishing");
			String price = req.getParameter("price");
			String info = req.getParameter("info");
			String image = req.getParameter("imageURL");
			String index = req.getParameter("index");
			
			/*out.println("bookName:" + bookName);
			out.println("author:" + author);
			out.println("publishing:" + publishing);
			out.println("price:" + price);
			out.println("info:" + info);
			out.println("image:" + image);*/
			
			if(bookName == null || price == null || image == null){
				System.out.println("UpdateMyBookServlet: 含空的项");
//				response.sendRedirect("/" + project + "/myBook.jsp");
			}else{
				File smartFile = smartUpload.getFiles().getFile(0);
				try {
					String url = "G:\\web_workspaces\\zzubuy\\WebRoot\\book\\";
					smartFile.saveAs(url + image + ".jpg", smartUpload.SAVE_PHYSICAL);
					out.println(smartFile);
					Book book = new Book();
					book.setSellerName(sellerName);
					book.setImage(image);
					book.setBookName(bookName);
					book.setPrice(price);
					book.setPublishing(publishing);
					book.setInfo(info);
					book.setAuthor(author);
					
					
					
					// 往book中插入数据
					BookDao bookDao = new BookDao();	
					int cnt = bookDao.update(book);
					if(cnt == 0){
						session.setAttribute("ErrorInfo", "插入失败...");
						RequestDispatcher rd = application.getRequestDispatcher("/Error.jsp");
					    rd.forward(request, response);
					}
					else{
						myBook.set(Integer.valueOf(index), book);
						response.sendRedirect("/" + project + "/myBook.jsp");
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
	
	public String getDate(){
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
//		System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
		return df.format(new Date());
	}
}
