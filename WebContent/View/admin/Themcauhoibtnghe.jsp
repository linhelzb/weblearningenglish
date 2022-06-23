  <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

		<title>Thêm câu hỏi bài tập nghe</title>

		 <link rel="stylesheet" href="Template/Backend/assets/css/insertgrammarguidelineimage.css"> 
   		 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/> 
</head> 
<body >
	<form action="Themcauhoibtnghe?ma_bai_tap_nghe=<%=request.getAttribute("ma_bai_tap_nghe")%>" method="POST" enctype="multipart/form-data">
		  <fieldset>
		    <div class="chonfile">
		                                          
		         <p>Thêm câu hỏi bài tập nghe</p>                                   
		        <div class="" style="color:red; padding-bottom: 10px;">
		            <%=request.getAttribute("tbthemcauhoibtnghe")!=null?request.getAttribute("tbthemcauhoibtnghe"):" "%>
		        </div>
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
