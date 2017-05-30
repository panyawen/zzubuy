<%@ page language="java" import="java.util.*,bean.Book" pageEncoding="utf-8"%>
<html>
  <head>
  	<title>我发布的商品</title>
  </head>
  <body>
  <%
	String user = (String)session.getAttribute("user");
  %>
	<center>
	  <font size="6">
		我发布的商品<br><br>
	  </font>
	   <font size="5">
		<a href="servlet/GetMyBookServlet">书本</a>
		<a href="myElec.jsp">电子类</a>
	  </font>
	</center>
  </body>
</html>
