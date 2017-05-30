<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
	<script type="text/javascript">
		function release(){
			window.open("servlet/ReleaseServlet");
		}		
		
		function myUpload(){
			window.open("servlet/MyUploadServlet");
		}		
		
		function book(){
			window.open("bookShow.jsp","_blank");
		}
		
		function elec(){
			window.open("elecShow.jsp","_blank");
		}
		
		function life(){
			window.open("lifeShow.jsp","_blank");
		}
		
		function other(){
			window.open("otherShow.jsp","_blank");
		}
		
		var cnt = 0;        
        function time1(){
       		cnt ++;
            cnt %= 2;         //         超过2则取余数，保证循环在0、1、2之间
       		document.getElementById("body").background="background/image" + cnt +".jpg";
        }setInterval("time1()",5000);
	</script>
  <head>
  	<link rel="stylesheet" rev="stylesheet" href="css/background.css" type="text/css"/>
  	<title>ZZUBUY</title>
  </head>
  
  <body id="body" background="background/image1.jpg">
  <font color="red">
	<%
		String user = (String)session.getAttribute("user");
		if(user != null)
		   out.println(user);
	 %>
	 </font>
	 <%= "欢迎您！" %>
	 <%
	 	if(user != null){
	 		out.println("<a id='release' onClick='release()'>我要发布商品</a>&nbsp;&nbsp;");
	 		out.println("<a id='myUpload' onClick='myUpload()'>我发布的商品</a>");
	 %>
	 		
	 <%
	 		out.println("<br><br><a href='logout.jsp'>退出</a>");
	 	}
	 	else
	 	    out.println("<a href='login.jsp'>请登录</a>");
	  %>
	 
	 <div id="div1">
	    <font size="5" color="black">
	 	<a onClick="book()" >书本</a>&nbsp;&nbsp;
	 	<a onClick="elec()">电子产品</a>&nbsp;&nbsp;
	 	<a onClick="life()">生活用品</a>&nbsp;&nbsp;
	 	<a onClick="other()">其他商品</a>&nbsp;&nbsp;
	 	</font>
	 </div>
	 
	 
  </body>
</html>
