<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

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
    	<div class="khoi-nd">
	        <div class="chitiet">
	        <c:forEach items="${dshuongdannguphap}" var="huongdannguphap">
	            <div class="bai">
			        <img src="Imageupload/${huongdannguphap.hinh_anh_ngu_phap}">
					<a href="ChitietbaihdnguphapFw?ma_ngu_phap=${huongdannguphap.ma_ngu_phap}" type="Xem"><p>${huongdannguphap.ten_ngu_phap}</p></a><br>
			    </div><br>
	        </c:forEach>	
	        </div>
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
	    
    	<div class="phantrang">
			<div class="nav">  
				<ul class="pt">
					<c:if test="${ numberpage==1}">
						<li class="disabled" style="padding: 8px;"><a href="#">&laquo;</a></li>

						<li style="padding: 8px;"><a href="DsbaihdnguphapFw?pageid=1">1</a></li>

						<li style="padding: 8px;"><a href="DsbaihdnguphapFw?pageid=2">2</a></li>

						<li style="padding: 8px;"><a href="DsbaihdnguphapFw?pageid=${numberpage+1}">&raquo;</a></li>

					</c:if>
					<c:if test="${ numberpage==max}">
						<li style="padding: 8px;"><a href="DsbaihdnguphapFw?pageid=${numberpage-1}">&laquo;</a></li>

						<li style="padding: 8px;"><a href="DsbaihdnguphapFw?pageid=1">1</a></li>

						<li style="padding: 8px;"><a href="DsbaihdnguphapFw?pageid=2">2</a></li>

						<li class="disabled" style="padding: 8px;"><a href="#">&raquo;</a></li>

					</c:if>
					<c:if test="${ numberpage > 1 && numberpage < max}">
						<li style="padding: 8px;"><a href="DsbaihdnguphapFw?pageid=${numberpage-1}">&laquo;</a></li>

						<li style="padding: 8px;"><a href="DsbaihdnguphapFw?pageid=1">1</a></li>

						<li style="padding: 8px;"><a href="DsbaihdnguphapFw?pageid=2">2</a></li>

						<li style="padding: 8px;"><a href="DsbaihdnguphapFw?pageid=${numberpage+1}">&raquo;</a></li>

					</c:if>
					
				</ul>
			</div>	
		</div>
	</div>
	
		<jsp:include page="Cuoitrang.jsp"/>

</body>
</html>