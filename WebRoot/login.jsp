<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
	<head>
		<center><font size="10" color="red">交易系统!</font></center>
		<% String project = application.getInitParameter("project"); %>
	</head>
  <body>
  <script type="text/javascript" language="JavaScript">
  		var xmlHttp = false;
  		function InitAjax(){
			if(window.XMLHttpRequest){
			//	alert("其他1");
				xmlHttp = new XMLHttpRequest();
			//	alert("其他2");
			}
			else if(window.ActiveXObject){
			//	alert("IE");
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
  	
		function Check(){
			if(Form.user.value == ""){
				document.getElementById("info").innerHTML = "<center><font color='red'> 用户名不能为空！</font></center>";
				return false;
			}
			else if(Form.password.value == ""){
				document.getElementById("info").innerHTML = "<center><font color='red'> 密码不能为空！</font></center>";
				return false;
			}
			return true;
		}  
		
		function LoginCheck(){
			document.getElementById("info").innerHTML = "<center><font color='red'>登录中...</font></center>"
			if(!Check())
			    return ;
			InitAjax();
			var user = Form.user.value;
			var password = Form.password.value;
			var url = "servlet/LoginServlet?user=" + user + "&password=" + password;
			url = encodeURI(url);   // 包含中文的URL进行转码
			xmlHttp.open("get", url, true);//?user=" + user + "&password=" + password, true);   //  servlet/LoginServlet
			xmlHttp.onreadystatechange = function(){
				if(xmlHttp.readyState == 4){
					//info.innerHTML = xmlHttp.responseText;
					if(xmlHttp.responseText == "true")      //验证成功，跳转
					    window.location.href="show.jsp";   
					else      								//验证失败，返回错误信息
					    info.innerHTML = "<center><font color='red'>用户名或密码错误！</font></center>";
				}
			}
			xmlHttp.send();
		}
		
		function SignCheck(){
			document.getElementById("info").innerHTML = "<center><font color='red'>正在注册请稍后...</font></center>"
			if(!Check())
			    return ;
			InitAjax();
			var user = Form.user.value;
			var password = Form.password.value;
			var url = "servlet/SignServlet?user=" + user + "&password=" + password;
			url = encodeURI(url);   // 包含中文的URL进行转码
			xmlHttp.open("get", url, true);//?user=" + user + "&password=" + password, true);   //  servlet/LoginServlet
			xmlHttp.onreadystatechange = function(){
				if(xmlHttp.readyState == 4){
					info.innerHTML = xmlHttp.responseText;
				}
			}
			xmlHttp.send();
		}
  	</script>
  	<center>
	  	<form name="Form">
			用户:<input name="user" type="text"><br>
			密码:<input name="password" type="password"><br>
			<input type="button" value="登录" onClick="LoginCheck()">
			<input type="button" value="注册" onClick="SignCheck()">
		</form>
		<!-- <form name="LoginForm" action="servlet/LoginServlet" method="post">
			<input name="loginUser" type="hidden">
			<input name="loginPassword" type="hidden">
			<input type="button" value="登录" onClick="LoginCheck()">
		</form>
		<form name="SignForm" action="servlet/SignServlet" method="post">
			<input name="signUser" type="hidden">
			<input name="signPassword" type="hidden">
			<input type="button" value="注册" onClick="SignCheck()">
		</form>-->
	</center>
	<div id="info"></div>
	<jsp:include page="copyright.jsp"></jsp:include>
  </body>
</html>
