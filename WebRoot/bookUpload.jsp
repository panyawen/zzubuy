<%@ page language="java" import="java.util.*,bean.Seller" pageEncoding="gb2312"%>
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
  		session.setAttribute("fromBookUploadJsp", "true");
  	 %>
	<center>
	  <form name="uploadForm" action="servlet/BookUpload" method="post" 
	  		enctype="multipart/form-data">
		�����鱾��������������Ϣ��<br><br>
		��&nbsp;��:<input type="text" name="bookName"><br><br>
		��&nbsp;��:<input type="text" name="author"><br><br>
		������:<input type="text" name="publishing"><br><br>
		��&nbsp;��:<input type="text" name="price"><br><br>
		��&nbsp;��:<textarea name="info" rows="5" cols="20"></textarea><br><br>
		��ѡ����ƷͼƬ:<br>
		<input type="file" name="image" id="image" onchange="setImagePreview()">   <% //onchange="setImagePreview()" %>
		<p><div id="localImag"><img id="preview" width=-1 height=-1 style="diplay:none" /></div></p>  
	  	<input type="button" onClick="check()" value="����">
	  </form>
	</center>
  </body>
</html>
