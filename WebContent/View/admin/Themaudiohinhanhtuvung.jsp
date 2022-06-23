<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Thêm audio hinh ảnh hướng dẫn từ vựng</title>
		<link rel="stylesheet" href="Template/Backend/assets/css/insertgrammarguidelineimage.css"> 
   		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/> 
		
</head>
<body>
	<form action="Themaudiohinhanhtuvung" method="POST" enctype="multipart/form-data">
		  <fieldset>
		      <div class="chonfile">                                
		         <p>Thêm audio hình ảnh hướng dẫn từ vựng</p>                                   
		         <div class="" style="color:red; padding-bottom: 10px;">
		            <%=request.getAttribute("tbthemaudiohinhanhtuvung")!=null?request.getAttribute("tbthemaudiohinhanhtuvung"):" "%>
		         </div>
		        
		         <table id="bang-file">
						<tr>
							 <td><input name="files[0]" type="file" /></td>
						</tr>
						<tr>
							 <td><input name="files[1]" type="file" /></td>
						</tr>		  	
				</table>
		       
		       <div class="khoangtrang"></div>
		
		       <div>
		       		<input type="submit" value="Thêm audio và hình ảnh" >
		         
		      </div>    
	        </div>                                    
   		 </fieldset> 

		</form>
		
</body>
</html>

