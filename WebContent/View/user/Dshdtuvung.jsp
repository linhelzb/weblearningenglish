<!-- Hi -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Danh sách hướng dẫn từ vựng</title>

 	<link rel="stylesheet" href="Template/Frontend/css/dsbaihdnguphap.css"> 
    <link rel="stylesheet" href="Template/Frontend/css/styles.css"> 
    
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/> 
   
</head>
<body>
	
	<jsp:include page="Dautrang.jsp"/>
	
	<div class="chua-dung">
    	<div class="khoangtrang" style="position: relative;height: 30px;"></div>
    	<div class="trangchu">
		    <a href="TrangchuFw"><i class="fas fa-home"></i></a>
		    <a href="TrangchuFw">Trang chủ</a>
	    </div>
	    <p>Danh sách chủ đề từ vựng</p>
	 	<div class="khoi-nd">
    		
    		<%
		  		if (request.getAttribute("tbdschudetuvung")!=null)
		  		{	  		
		  	%>
		  		<div class="hang">	
					
						<div>			
								<div>
									 <p style="color:red">${tbdschudetuvung}</p>
								</div>						
						</div>	
							
				</div>
		  	<%
		  		}
		  		else
		  		{
		  	%>
	        <div class="chitiet">
	        	
		        <c:forEach items="${danhsachhdtuvung}" var="huongdantuvung">
		            <div class="bai">
				        
				        <img src="Imageaudiohdtuvung/${huongdantuvung.hinh_anh}"/>
				        <a href="Xemnoidungtuvung?ma_loai_tu_vung=${huongdantuvung.ma_loai_tu_vung}" type="Xem"><p>${huongdantuvung.ten_nhom_tu_vung}</p></a><br>
						
				    </div><br>
		        </c:forEach>	
	        </div>
	        <%
		  		}
			%>
	        <div class="dslk">
                	<p>Liên kết nhanh</p>
                	<ul class="nav">
	                    <li><i class="fas fa-chevron-circle-right"></i><a href="#">Hướng dẫn học ngữ pháp</a></li>
	                    <li><i class="fas fa-chevron-circle-right"></i><a href="#">Hướng dẫn học từ vựng</a></li>
	                    <li><i class="fas fa-chevron-circle-right"></i><a href="#">Làm bài tập phần đọc</a></li>
	                    <li><i class="fas fa-chevron-circle-right"></i><a href="#">Làm bài tập phần nghe</a></li>
	                    <li><i class="fas fa-chevron-circle-right"></i><a href="#">Thi thử toeic</a></li>
                	</ul>
           </div>
           </div>
	    </div>
    	<div class="phantrang">
			<div class="nav">  
				<ul class="pt">
							<c:if test="${numberpage == 1}">
							   <li class = "truoc"><a href = "#"><i class="fas fa-angle-double-left"></i></a></li>
							  
							   <li class = "sau"><a href = "Hienthidshdtuvung?pageid=${numberpage+1}"><i class="fas fa-angle-double-right"></i></a></li>
						   </c:if>
						   <c:if test="${numberpage == max}">
							   <li class = "truoc"><a href = "Hienthidshdtuvung?pageid=${numberpage+1}"><i class="fas fa-angle-double-left"></i></a></li>
							  
							   <li class = "sau"><a href ="#"><i class="fas fa-angle-double-right"></i></a></li>
						   </c:if>
						   
						   <c:if test="${numberpage > 1 && numberpage < max}">
							   <li class = "truoc"><a href = "Hienthidshdtuvung?pageid=${numberpage-1}"><i class="fas fa-angle-double-left"></i></a></li>
							   
							   <li class = "sau"><a href = "Hienthidshdtuvung?pageid=${numberpage+1}"><i class="fas fa-angle-double-right"></i></a></li>
						   </c:if>
					
						   
				</ul>
			</div>	
		</div>
	
	<jsp:include page="Cuoitrang.jsp"/>

</body>
</html>
