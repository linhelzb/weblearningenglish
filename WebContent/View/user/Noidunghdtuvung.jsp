<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>hướng dẫn từ vựng</title>
	
	<link rel="stylesheet" href="Template/Frontend/css/ndhdtv.css">  
	<link rel="stylesheet" href="Template/Frontend/css/styles.css">  
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"/>  
</head>
<body>
	
	<jsp:include page="Dautrang.jsp"></jsp:include>
	<div class="trangchu" style="padding:20px 130px; ">
		    <a href="TrangchuFw"><i class="fas fa-home"></i></a>
		    <a href="TrangchuFw">Trang chủ</a>
	    </div>
	 <div class="chua-dung">
		<div class="khoi-nd">
		  	
		  	<%
		  		if (request.getAttribute("msgndchudetuvung")!=null)
		  		{	  		
		  	%>
		  		<div>			
					<div>
						<p style="color:red">${msgndchudetuvung}</p>
					</div>										
				</div>
		  	<%
		  		}
		  		else
		  		{
		  	%>
		  	
			<div class="chitiet">
				<c:forEach items="${noidungtuvung}" var="noidung">
						
					<div class="bai">
						 <a href="#" class="pull-left"><img src="Imageaudiohdtuvung/${noidung.hinh_anh}" alt='' / width="150px" height="150px"></a>
						<div class="nd">
										 
							<p>
											Từ: ${noidung.ten_tu_vung}
							</p>
							<p>
											<br>
											
							</p>
							<p>
											Nghĩa và từ loại: ${noidung.nghia}
							</p>
										
							<p>
								<audio controls>
										<source src="Imageaudiohdtuvung/${noidung.audiogg}" type="audio/ogg">
										<source src="Imageaudiohdtuvung/${noidung.audiomp3}" type="audio/mpeg">
								</audio>
							</p>
										
						</div>
					</div>						
						
				</c:forEach>				
			</div>
			<%
		  		}
			%>	
		</div>
	 </div>
	 <div>
	 	<jsp:include page="Cuoitrang.jsp"></jsp:include>
	 </div>
	 
</body>
</html>