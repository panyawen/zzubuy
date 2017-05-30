<%@ page language="java" import="java.util.*,bean.Book,dao.SellerDao" pageEncoding="utf-8"%>
<%
	System.out.println(request.getSession().getServletContext().getRealPath("/"));
%>
<html>
  <script type="text/javascript">
  /*  var xmlHttp = false;
 	function InitAjax(){
		if(window.XMLHttpRequest){
			xmlHttp = new XMLHttpRequest();
		}
		else if(window.ActiveXObject){
			try{
				xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
			}catch(e){
				try{
					xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
				}catch(e){
					alert("该浏览器不支持AJAX");
				}
			}
		}
 	}
  
  	function update(index){
  		InitAjax();
  		var url = "updateMyBook.jsp?index=" + index;
		url = encodeURI(url);   // 包含中文的URL进行转码
		xmlHttp.open("get", url, true);//?user=" + user + "&password=" + password, true);   //  servlet/LoginServlet
		xmlHttp.onreadystatechange = function(){
			if(xmlHttp.readyState == 4){
				updateDiv.innerHTML = xmlHttp.responseText;
			}
		}
		xmlHttp.send();
  	}*/
  </script>
  <head>
  	<title>商品详情!</title>
  </head>
  <body>
    <center>
	<%
		String index = null;
		String user = (String)session.getAttribute("user");
		if(user == null){   //若用户未登录， 则无法查看商品的详细信息
			RequestDispatcher rd = application.getRequestDispatcher("/loginFirst.jsp"); 
			rd.forward(request, response);
		}else{
			index = request.getParameter("index");
			if(index == null){
			 	RequestDispatcher rd = application.getRequestDispatcher("/bookShow.jsp"); 
				rd.forward(request, response);
			}else{		    
				ArrayList<Book> myBook = (ArrayList<Book>)session.getAttribute("myBook");
		 		if(myBook == null)
		 			response.sendRedirect("show.jsp");
		 		else{
		 		//	out.println(al.size() + "<br>");  
		 		//	out.println(Integer.valueOf(num));
		 			Book book = myBook.get(Integer.valueOf(index));
		 			String image = book.getImage();
		 			String sellerName = book.getSellerName();
		 			SellerDao sellerDao = new SellerDao();
		 			HashMap<String, String> contactMap = sellerDao.getContact(sellerName);
		 			String tel = contactMap.get("tel");
		 			String qq = contactMap.get("qq");
		 			String weixin = contactMap.get("weixin");
		 			
		 			out.println("<table>");
		 				out.println("<td>");
				 			out.println("<img src=servlet/ShowBookImageServlet?image=" + image 
												+ " width=200px height=200px>" +"</a>");   
				 		out.println("</td>");
				 		out.println("<td bgcolor=pink>");
				 			out.println("<div>");
				 			out.println("书名: " + book.getBookName() + "<br>");
				 			out.println("作者: " + book.getAuthor() + "<br>");
				 			out.println("出版社: " + book.getPublishing() + "<br>");
				 			out.println("价格: " + book.getPrice() + "<br>");
				 		/*	out.println("<hr>卖家信息:<br>");
				 			out.println("卖家昵称: "  + sellerName + "<br>");
				 			if(!"".equals(tel))
				 			    out.println("tel:" + tel + "<br>");
				 			if(!"".equals(qq))
				 			    out.println("qq:" + qq + "<br>");
				 			if(!"".equals(weixin))
				 			    out.println("weixin:" + weixin + "<br>");*/
				 			out.println("</div>");
				 		out.println("</td>");
			 		out.println("</table>");
			 		out.println("<br><table width=300px>");
			 			out.println("<tr width=300px>");
			 				out.println("<td bgcolor=pink width=300px>");
			 					out.println("商品描述如下:<br>");
								out.println("<br>&nbsp;&nbsp;" + book.getInfo());
							out.println("</td>");
						out.println("</tr>");
					out.println("</table>");
		 		}
		 	}
		 }
		// out.println("<br><input type='button' onClick=update(" + index + ") value='修改图书信息'");
	 %>
	 <br><div id="updateDiv"></div>
	 </center>
  </body>
</html>
