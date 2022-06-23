<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Câu hỏi bài tập theo mã</title>
	<link rel="stylesheet" href="Template/Frontend/css/lambaitapnghe.css"> 
    <link rel="stylesheet" href="Template/Frontend/css/styles.css">    
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/> 
    
    	<script type="text/javascript">
			
					function Xuatketqua()
					{
						var xhttp;
						var kq = document.myform.radio.value;
						
						var url = "Lambtdoc?kq="+kq+"&so_thu_tu="+<%=request.getAttribute("numberpage")%>+"&ma_bai_tap_doc="+<%=request.getAttribute("ma_bai_tap_doc")%>;
						
						if (window.XMLHttpRequest) 
						{        
						    xhttp = new XMLHttpRequest(); 
						} 
						
						xhttp.onreadystatechange= function()
						{
							if (xhttp.readyState == 4)
							{
								var data = xhttp.responseText;
								document.getElementById("ketqualambtdoc").innerHTML=data;
							}
							
						}
						
						xhttp.open("POST",url,true);
						xhttp.send();

					}
			</script>
      	
</head>
<body>
	<jsp:include page="Dautrang.jsp"></jsp:include>
	 <div class="trangchu" style="padding:20px 130px; ">
		    <a href="TrangchuFw"><i class="fas fa-home"></i></a>
		    <a href="TrangchuFw">Trang chủ</a>
	    </div>
		<div class="chua-dung">
			<h4>
						Lựa chọn câu trả lời đúng nhất
			</h4>
		  	<br/>
			<form name="myform" id="ketqualambtdoc">
				<c:forEach items="${danhsachcauhoibtdoc}" var ="cauhoibtdoc">
							<p>
									Câu ${cauhoibtdoc.so_thu_tu}. ${cauhoibtdoc.cau_hoi}
									
							</p>
							<p>
						  		<input type="radio" name="radio" value="A"/>
						  			${cauhoibtdoc.lua_chon_1}
						  	</p>
						  	<p>
						  			<input type="radio" name="radio" value="B"/>
						  			${cauhoibtdoc.lua_chon_2}
						  	</p>
						  	<p>
						  			<input type="radio" name="radio" value="C"/>
						  			${cauhoibtdoc.lua_chon_3}
						  	</p>
						  	<p>
						  			<input type="radio" name="radio" value="D"/>
						  			${cauhoibtdoc.lua_chon_4}
						  	</p>
				</c:forEach>
			</form>
			<div>
						
				<c:if test="${numberpage == 1}">
					<a href = "#" class="btn">Trước</a>
					<input type="button" value="Đáp án" class="btn" onclick="Xuatketqua()"/>
					<a href = "Lambtdoc?pageid=${numberpage}&ma_bai_tap_doc=<%=request.getAttribute("ma_bai_tap_doc")%>" class="btn">Làm lại</a>
					<a href = "Lambtdoc?pageid=${numberpage+1}&ma_bai_tap_doc=<%=request.getAttribute("ma_bai_tap_doc")%>" class="btn">Sau</a>
							   
				</c:if>
				<c:if test="${numberpage == maxpageid}">
					<a href = "Lambtdoc?pageid=${numberpage-1}&ma_bai_tap_doc=<%=request.getAttribute("ma_bai_tap_doc")%>" class="btn">Truoc</a>
					<input type="button" value="Đáp án" class="btn" onclick="Xuatketqua()"/>
					<a href = "Lambtdoc?pageid=${numberpage}&ma_bai_tap_doc=<%=request.getAttribute("ma_bai_tap_doc")%>" class="btn">Làm lại</a>
							   
					<a href ="#" class="btn">Sau</a>
				</c:if>
						   
				<c:if test="${numberpage > 1 && numberpage < maxpageid}">
					<a href = "Lambtdoc?pageid=${numberpage-1}&ma_bai_tap_doc=<%=request.getAttribute("ma_bai_tap_doc")%>" class="btn">Truoc</a>
					<input type="button" value="Đáp án" class="btn" onclick="Xuatketqua()"/>
					<a href = "Lambtdoc?pageid=${numberpage}&ma_bai_tap_doc=<%=request.getAttribute("ma_bai_tap_doc")%>" class="btn">Làm lại</a>
							   
					<a href = "Lambtdoc?pageid=${numberpage+1}&ma_bai_tap_doc=<%=request.getAttribute("ma_bai_tap_doc")%>" class="btn">Sau</a>
				</c:if>
						
			</div>	
		</div>
		<br/>
		<br/>
	 <jsp:include page="Cuoitrang.jsp"></jsp:include>
</body>
</html>