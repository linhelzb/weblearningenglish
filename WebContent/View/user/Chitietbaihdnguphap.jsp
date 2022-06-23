<!--HI-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hướng dẫn học ngữ pháp</title>
   
    <link rel="stylesheet" href="Template/Frontend/css/chitietbaihdnguphap.css">
    <link rel="stylesheet" href="Template/Frontend/css/dsbaihdnguphap.css">  
    <link rel="stylesheet" href="Template/Frontend/css/styles.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/> 
    
    <script type="text/javascript">
    
    		function Binhluan()
    		{
    			var xhttp;
				var noi_dung_binh_luan = document.formbinhluan.noi_dung_binh_luan.value;
				var ma_thanh_vien = "<%=session.getAttribute("sessionuser")%>";
				var ma_ngu_phap = "<%=request.getAttribute("ma_ngu_phap")%>"
				
				var url = "BinhluannguphapCtl?noi_dung_binh_luan="+noi_dung_binh_luan+"&ma_thanh_vien="+ma_thanh_vien+"&ma_ngu_phap="+ma_ngu_phap;
				
				if (window.XMLHttpRequest) 
				{          
				         xhttp = new XMLHttpRequest();
				} 
				else
				{        
					xhttp = new ActiveXObject("Microsoft.XMLHTTP");
				}
				
				xhttp.onreadystatechange = function()
				{
					if(xhttp.readyState == 4)
					{
						var data = xhttp.responseText;
						document.getElementById("list-binhluan").innerHTML = data;
					}			
					
				}
				
				xhttp.open("POST",url,true);
				xhttp.send();
    		}
    
    </script>
    
</head>
<body>
	  	<jsp:include page="Dautrang.jsp"></jsp:include>
	 
		<div class="chua-dung">
			<div class="khoangtrang" style="position: relative;height: 30px;"></div>
	        <div class="trangchu">
		    	<a href="TrangchuFw"><i class="fas fa-home"></i></a>
		    	<a href="TrangchuFw">Trang chủ</a>
	    	</div>
		  	<div class="khoi-nd">
		  		
	            <div class="chitiet">
	            <c:forEach items="${dshdnpct }" var = "hdnguphapchitiet">			
					<h3>${dsnoidungtuvung.ten_ngu_phap }</h3>				
					<br>
				</c:forEach>
	                <c:forEach items="${dshdnpct }" var = "hdnguphapchitiet">
					<p>
						<c:set var = "kq1" value="${fn:replace(hdnguphapchitiet.noi_dung_ngu_phap,kitutrongdatabase1,kitutronghtml1) }"></c:set>
						
						<c:out value="${kq1 }" escapeXml="false"></c:out>					
					</p>
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
           <%
				if (session.getAttribute("sessionuser")==null)
				{
			%>
           <div class="binhluan">
	            <div class="tieude-binhluan" >${countrow}  bình luận</div>
	            <div class="dauvao" >
	                <img src="Template/Frontend/img/avatar.jpg" width="50" height="50" style="margin-right: 10px">
	                <input type="text"  class="vanban" placeholder="Đăng nhập để bình luận..." rows="4" cols="70" name="noi_dung_binh_luan" disabled style="color:red">
	                <button type="button" class="button" disabled>Đăng</button>
	            </div>
            	<div style="overflow: auto; max-height: 450px; max-width:650px;" >
					<div id="list-binhluan">				
						<c:forEach items="${dsbinhluannguphap}" var="binhluannguphap"> 
							<div class="dauvao" style="justify-content: left">
								<img src="Template/Frontend/img/avatar.jpg" style="padding-right:10px" width="50" height="50">
								<div>
									<h4 style="color:#3A5FCD" class="">${binhluannguphap.ten_day_du}</h4>
												
									<p style="max-width:450px ; min-height:15px" class="">
											${binhluannguphap.noi_dung_binh_luan}
									<p>
								</div>
							</div>
						</c:forEach>
										
					</div>
				</div>		
        	</div>
        	<%
				}
				else
				{		
			%>			
				<form name="formbinhluan">
					<div class="binhluan">
						<div class="tieude-binhluan" >${countrow}  bình luận</div>
			            <div class="dauvao" >	
							<img src="Template/Frontend/img/avatar.jpg" width="50" height="50" style="margin-right: 10px">
			                <input type="text"  class="vanban" placeholder="Thêm bình luận..." rows="4" cols="70" name="noi_dung_binh_luan">
			                <button type="button" class="button" onclick="Binhluan()">Đăng</button>	 
			            </div>	
			            <div style="overflow: auto; max-height: 450px; max-width:650px;" >
							<div id="list-binhluan">				
									<c:forEach items="${dsbinhluannguphap}" var="binhluannguphap"> 
									<div class="dauvao" style="justify-content: left">
											<img src="Template/Frontend/img/avatar.jpg" style="padding-right:10px" width="50" height="50">
											<div>
												<h4 style="color:#3A5FCD" class="">${binhluannguphap.ten_day_du}</h4>
												
												<p style="max-width:500px ; min-height:15px" class="">
													${binhluannguphap.noi_dung_binh_luan}
												</p>
											</div>
										</div>
									</c:forEach>
										
							</div>
						</div>		
			        </div>						
				</form>
			<%
				 }
			%>
						
		  	</div>  		
	 <div>
	 	<jsp:include page="Cuoitrang.jsp"></jsp:include>
	 </div>
	 
</body>

</html>