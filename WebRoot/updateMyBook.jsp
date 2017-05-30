<%@ page language="java" import="java.util.*,bean.*" pageEncoding="gb2312"%>
<html>
<script>   
		function setImagePreview() {   
		    var docObj=document.getElementById("image");   
		    var imgObjPreview=document.getElementById("preview");   
		    if(docObj.files && docObj.files[0]){   
		   		//火狐下，直接设img属性   
			    imgObjPreview.style.display = 'block';   
			    imgObjPreview.style.width = "300px";   
			    imgObjPreview.style.height = "300px";   
			    //imgObjPreview.src = docObj.files[0].getAsDataURL();   
			    //火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式   
			    imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);   
		    }else{   
		        //IE下，使用滤镜   
			    docObj.select();   
			    var imgSrc = document.selection.createRange().text;   
			    var localImagId = document.getElementById("localImag");   
			    //必须设置初始大小   
			    localImagId.style.width = "200px";   
			    localImagId.style.height = "200px";   
			    //图片异常的捕捉，防止用户修改后缀来伪造图片   
			    try{   
			      localImagId.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";  
			      localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;   
			    }catch(e){   
		      alert("您上传的图片格式不正确，请重新选择!");   
		      return false;   
		    }   
		    imgObjPreview.style.display = 'none';   
		    document.selection.empty();   
		  }   
		  return true;   
		}  
		
		function check(){
		   // alert("AA");
			var bookName = uploadForm.bookName.value;
			var price = uploadForm.price.value;
			var image = uploadForm.image.value;
			
			if(bookName == ""){
				alert("请输入书名");
				return ;
			}
			if(price == ""){
				alert("请填入合适的价格")
				return ;
			}
			if(image ==""){
				alert("请选择图片");
				return ;
			}
			var patten = /^-?\d+\.?\d*$/;  
 			if(!patten.test(price)){
 				alert("请输入正确的价格");
 				return ;
 			}
 			uploadForm.submit();
		}
  </script>   
  <body>
    
    	<%
    		Seller seller = (Seller)session.getAttribute("seller");
			ArrayList<Book> myBook = (ArrayList<Book>)session.getAttribute("myBook");
			String index = request.getParameter("index");
			if(seller == null || myBook == null || index == null) 
				response.sendRedirect("show.jsp");
			else{
				Book book = myBook.get(Integer.valueOf(index));
    	 %>
    
	<center>
	  <font size="6">修改图书信息</font><br><br>
	  <form name="uploadForm" action="servlet/UpdateMyBookServlet" method="post" 
	  		enctype="multipart/form-data">
	  			  <input type="hidden" name="imageURL" value=<%=book.getImage() %>>
	  			  <input type="hidden" name="index" value=<%=index %>>
		书&nbsp;名:<input type="text" name="bookName" value=<%=book.getBookName() %>><br><br>
		作&nbsp;者:<input type="text" name="author" value=<%=book.getAuthor() %>><br><br>
		出版社:<input type="text" name="publishing" value=<%=book.getPublishing() %>><br><br>
		价&nbsp;格:<input type="text" name="price" value=<%=book.getPrice() %>><br><br>
		描&nbsp;述:<textarea name="info" rows="5" cols="20"><%=book.getInfo() %></textarea><br><br>
		请选择商品图片:<br>
		<input type="file" name="image" id="image" onchange="setImagePreview()"> 
		<p><div id="localImag"><img id="preview" width=-1 height=-1 style="diplay:none" /></div></p>  
	  	<input type="button" onClick="check()" value="发布">
	  </form>
	</center>
	<%} %>
  </body>
</html>
