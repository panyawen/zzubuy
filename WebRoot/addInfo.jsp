<%@ page language="java" import="java.util.*,bean.Seller" pageEncoding="utf-8"%>
<html>
  <script type="text/javascript">
  	  function check(){
  	  	  var tel = infoForm.tel.value;
  	  	  var qq = infoForm.qq.value;
  	  	  var weixin = infoForm.weixin.value;
  	  	  if(tel == "" && qq == "" && weixin == ""){
  	  	  	return ;
  	  	  }
  	  	  infoForm.submit();
  	  }
  </script>

  <head>
  	<title>添加个人信息</title>
  </head>
  <body>
	<br>
	<center>请完善联系方式:<br><br>
		<form name="infoForm" action="servlet/AddInfoServlet" method="post"> 
			号码:<input type="tel" name="tel"><br><br>
			Q&nbsp;Q:<input type="text" name="qq"><br><br>
			微信:<input type="text" name="weixin"><br><br>
			<input type="button" value="提交" onClick="check()"><br><br>
			<font color="red">请至少填入一项 ！</font>
		<!--	进入此页面时需检测seller表中是否存在 当前user -->
			
		</form>
		<%@include file="copyright.jsp" %>
	</center>
  </body>
</html>
