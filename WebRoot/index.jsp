<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>书本</title>
  </head>
  <a href="servlet/ShowImageServlet">Show</a>
  <body>
   <img src="servlet/ShowImageServlet" width="100px" height="100px" onClick="alert('aa')">
  </body>
</html>