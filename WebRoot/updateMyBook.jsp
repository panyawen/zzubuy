<%@ page language="java" import="java.util.*,bean.*" pageEncoding="gb2312"%>
<html>
<script>   
		function setImagePreview() {   
		    var docObj=document.getElementById("image");   
		    var imgObjPreview=document.getElementById("preview");   
		    if(docObj.files && docObj.files[0]){   
		   		//����£�ֱ����img����   
			    imgObjPreview.style.display = 'block';   
			    imgObjPreview.style.width = "300px";   
			    imgObjPreview.style.height = "300px";   
			    //imgObjPreview.src = docObj.files[0].getAsDataURL();   
			    //���7���ϰ汾�����������getAsDataURL()��ʽ��ȡ����Ҫһ�·�ʽ   
			    imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);   
		    }else{   
		        //IE�£�ʹ���˾�   
			    docObj.select();   
			    var imgSrc = document.selection.createRange().text;   
			    var localImagId = document.getElementById("localImag");   
			    //�������ó�ʼ��С   
			    localImagId.style.width = "200px";   
			    localImagId.style.height = "200px";   
			    //ͼƬ�쳣�Ĳ�׽����ֹ�û��޸ĺ�׺��α��ͼƬ   
			    try{   
			      localImagId.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";  
			      localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;   
			    }catch(e){   
		      alert("���ϴ���ͼƬ��ʽ����ȷ��������ѡ��!");   
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
				alert("����������");
				return ;
			}
			if(price == ""){
				alert("��������ʵļ۸�")
				return ;
			}
			if(image ==""){
				alert("��ѡ��ͼƬ");
				return ;
			}
			var patten = /^-?\d+\.?\d*$/;  
 			if(!patten.test(price)){
 				alert("��������ȷ�ļ۸�");
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
	  <font size="6">�޸�ͼ����Ϣ</font><br><br>
	  <form name="uploadForm" action="servlet/UpdateMyBookServlet" method="post" 
	  		enctype="multipart/form-data">
	  			  <input type="hidden" name="imageURL" value=<%=book.getImage() %>>
	  			  <input type="hidden" name="index" value=<%=index %>>
		��&nbsp;��:<input type="text" name="bookName" value=<%=book.getBookName() %>><br><br>
		��&nbsp;��:<input type="text" name="author" value=<%=book.getAuthor() %>><br><br>
		������:<input type="text" name="publishing" value=<%=book.getPublishing() %>><br><br>
		��&nbsp;��:<input type="text" name="price" value=<%=book.getPrice() %>><br><br>
		��&nbsp;��:<textarea name="info" rows="5" cols="20"><%=book.getInfo() %></textarea><br><br>
		��ѡ����ƷͼƬ:<br>
		<input type="file" name="image" id="image" onchange="setImagePreview()"> 
		<p><div id="localImag"><img id="preview" width=-1 height=-1 style="diplay:none" /></div></p>  
	  	<input type="button" onClick="check()" value="����">
	  </form>
	</center>
	<%} %>
  </body>
</html>
