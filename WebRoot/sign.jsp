<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
	<head>
		<center>
			<font size="10" color="red">欢迎注册</font>
		</center>
	</head>
	<body>
		<% request.setCharacterEncoding("utf-8"); %>
		<%=request.getParameter("signUser") %>
	</body>
</html>