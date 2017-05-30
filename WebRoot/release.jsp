<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
  <script type="text/javascript">
  	/*var xmlHttp = false;
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
  	function book(){
  		InitAjax();
  		xmlHttp.open("get", "bookUpload.jsp", true);//?user=" + user + "&password=" + password, true);   //  servlet/LoginServlet
		xmlHttp.onreadystatechange = function(){
			if(xmlHttp.readyState == 4){
			    uploadDiv.innerHTML = xmlHttp.responseText;
			}
		}
		xmlHttp.send();
  	}
  	*/
  	
  	
  </script>
  <head>
    <link rel="stylesheet" rev="stylesheet" href="css/background.css" type="text/css"/>
  	<title>商品发布</title>
  </head>
  <body>
	<center>
	  <font size="6">
		请选择发布的商品类型<br><br>
	  </font>
	  <font size="5">
		<a href="bookUpload.jsp">书本</a>
		<a href="elecShow.jsp">电子类</a>
	  </font>
	</center>
	
	
	<div id="uploadDiv"></div>
  </body>
</html>

