<%@ page language="java" import="java.util.*,bean.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <script type="text/javascript">
    function show(index){
		window.open("myBookDetail.jsp?index=" + index);
    }
    function update(index){
    	window.open("updateMyBook.jsp?index=" + index);
    }
    function del(index){
		location.href = "servlet/DeleteMyBookServlet?index=" + index;
    }
  </script>
  <head>
  	<link rel="stylesheet" rev="stylesheet" href="css/background.css" type="text/css"/>
    <title>我发布的图书</title>
  </head>
  <body>
    <center>
 	<table align="center"> <%//cellpadding="0px" border="5px" bordercolor="gray" %>
 	<%
 		ArrayList<Book> myBook = (ArrayList<Book>)session.getAttribute("myBook");
 		if(myBook == null)
			response.sendRedirect("show.jsp");
		else{
			int cnt = myBook.size();
			if(cnt != 0){
				int flag = 0;
				for(int i=0; i<cnt; i++){
					Book book = myBook.get(i);
					String image = book.getImage();
					String sellerName = book.getSellerName();
					String info = book.getInfo();
					session.setAttribute("image", image);
					out.println("<tr>");
					
					out.println("<td>");   
						out.println("<img src=servlet/ShowBookImageServlet?image=" + image 
										+ " width=200px height=200px onClick=show("+ i + ")>");   
					out.println("</td>");
					out.println("<td bgcolor=yellow>");
					    out.println("<div>");
							out.println("书名: " + book.getBookName()  + "<br>");
							out.println("作者: " + book.getAuthor() + "<br>");
							out.println("出版社: " + book.getPublishing() + "<br>");
							out.println("价格: " + book.getPrice() + "<br>");
						out.println("</div>");
					out.println("</td>");
					out.println("<td bgcolor=pink width=400px>");
					    int len = info.length();
					    for(int j=0; j<len; j++)
					        out.println(info.charAt(j));
					out.println("</td>");
					out.println("<td>");
						out.println("<img height=50px width=50px src=image/update.jpg onClick=update(" + i + ")><br><br><br>");
						out.println("<img height=50px width=50px src=image/delete.jpg onClick=del(" + i + ")>");
					out.println("</td>");
					
					out.println("</tr>");
				}
			}else{
				RequestDispatcher rd = application.getRequestDispatcher("/uploadEmpty.jsp");
				rd.forward(request, response);
			}
		}
 	 %>
 	 </table>
 	 </center>
  </body>
</html>