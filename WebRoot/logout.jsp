<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
  <script type="text/javascript">
  	function click(){
  	    alert("login");
  		window.location="login.jsp";
  	}
  </script>
  <head>
  	<title>ZZUBUY</title>
  </head>
  <body>
	<%
		session.removeAttribute("user");
		session.removeAttribute("seller");
		session.removeAttribute("myBook");
	 %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 <center>
	 	<br><br><br><br>
	 	<img src="logo/ok.jpg">
	 	<font size="5">您已成功退出ZZUBUY</font>
	 	<a href="login.jsp"><input id="loginAgain" type="button" value=重新登录></input></a>
	 </center>
	 <jsp:include page="copyright.jsp"></jsp:include>
  </body>
</html>
