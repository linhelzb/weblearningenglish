  <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

		<title>Thêm hình ảnh </title>

		 <link rel="stylesheet" href="Template/Backend/assets/css/insertgrammarguidelineimage.css"> 
   		 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/> 
</head> 
<body >
	
	
	<form action="Capnhatanhhdnguphap?ma_ngu_phap=<%=request.getAttribute("ma_ngu_phap")%>" enctype="multipart/form-data" method="post">
		  <fieldset>
		    <div class="chonfile">
		                                          
		         <p>Thêm hình đại diện cho bài hướng dẫn ngữ pháp</p>                                   
		        <label class="" style="color:red; padding-bottom: 10px;">
		            <%=request.getAttribute("tbhinhanhhdnguphap")!=null? request.getAttribute("tbhinhanhhdnguphap"):" "%>
		        </label>
		        <label>
		            <input type="file" name="file">
		        </label>
		
		        <div class="khoangtrang"></div>
		
		        <div>
		        
		            <button type="submit" >
		                <span>Xong</span>
		            </button>
		        </div>    
	        </div>                                    
   		 </fieldset> 
   		 </form>                                        
	</body>
</html>