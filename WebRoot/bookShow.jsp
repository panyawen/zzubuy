<%@ page language="java" import="java.util.*,bean.Book" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <script type="text/javascript">
    function show(index){
		window.open("bookDetail.jsp?index=" + index);
    }
  </script>
  <head>
  	<link rel="stylesheet" rev="stylesheet" href="css/background.css" type="text/css"/>
    <title>书本</title>
  </head>
  <body>
    <center>
 	<table align="center" cellpadding="0px"  bordercolor="gray" >
 	<%
 		ArrayList<Book> al = (ArrayList<Book>)session.getAttribute("bookArrayList");
 		if(al == null)
			response.sendRedirect("show.jsp");
		else{
			session.setAttribute("bookArrayList", al);
			int cnt = al.size();
			int flag = 0;
			for(int i=0; i<cnt; i++){
				Book book = al.get(i);
				String image = book.getImage();
				String sellerName = book.getSellerName();
				String info = book.getInfo();
				session.setAttribute("image", image);
				if(i % 2 == 0){
					flag = 0;
				    out.println("<tr>");
				}
				out.println("<td>");   
				/*	out.println("<a href=bookDetail.jsp?sellerName=" + sellerName + "&&info=" 
								+ info + "&&image=" + image + ">" + "<img src=servlet/ShowImageServlet?image=" + image 
								+ " width=200px height=200px>" +"</a>");*/
				out.println("<img src=servlet/ShowBookImageServlet?image=" + image 
								+ " width=200px height=200px onClick=show("+ i + ")>" +"</a>");   
				//out.println("<img src=servlet/ShowImageServlet?image=" + image 
				//				+ " width=200px height=200px onClick=show("+image+","+sellerName+","+info+")>" +"</a>");     
				out.println("</td>");
				out.println("<td bgcolor=yellow>");
				    out.println("<div>");
						out.println("书名: " + book.getBookName()  + "<br>");
						out.println("作者: " + book.getAuthor() + "<br>");
						out.println("出版社: " + book.getPublishing() + "<br>");
						out.println("价格: " + book.getPrice() + "<br>");
					out.println("</div>");
				out.println("</td>");
				if((flag+1) % 2 == 0)
					out.println("</tr>");
				flag ++;
			}
		}
 	 %>
 	 </table>
 	 </center>
  </body>
</html>