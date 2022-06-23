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
						
						var url = "Lambtnghe?kq="+kq+"&so_thu_tu="+<%=request.getAttribute("numberpage")%>+"&ma_bai_tap_nghe="+<%=request.getAttribute("ma_bai_tap_nghe")%>;
						
						if (window.XMLHttpRequest) 
						{        
						    xhttp = new XMLHttpRequest(); 
						} 
						
						xhttp.onreadystatechange= function()
						{
							if (xhttp.readyState == 4)
							{
								var data = xhttp.responseText;
								document.getElementById("ketqualambtnghe").innerHTML=data;
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
			<p>
				In each question, you will look at a photograph and then listen to 4 sentences. Choose the sentence that best describes the photograph. There are 17 questions in this test.
Listen to four statements about each photo on the screen. Click on the letter of the best description of the photo.
			</p>

		  	<br/>
				<form name="myform" id="ketqualambtnghe">
					<c:forEach items="${danhsachcauhoibtnghe}" var ="cauhoibtnghe">
						<p>
							Câu ${cauhoibtnghe.so_thu_tu}. ${cauhoibtnghe.cau_hoi}
						</p>
						<p>
							<img src= "Filebtphannghe/${cauhoibtnghe.hinh_anh}" alt="" style="width:250px;height:150px;"/>
						</p>
						<p>
							<audio controls>
								<source src="Filebtphannghe/${cauhoibtnghe.file_nghe_mp3}" type="audio/ogg">
								<source src="Filebtphannghe/${cauhoibtnghe.file_nghe_ogg}" type="audio/mpeg">
							</audio>
									
						</p>
						<p>
						  	<input type="radio" name="radio" value="A"/>
						  			${cauhoibtnghe.lua_chon_1}
						</p>
						<p>
						  	<input type="radio" name="radio" value="B"/>
						  			${cauhoibtnghe.lua_chon_2}
						</p>
						<p>
						  	<input type="radio" name="radio" value="C"/>
						  			${cauhoibtnghe.lua_chon_3}
						</p>
						<p>
						  	<input type="radio" name="radio" value="D"/>
						  			${cauhoibtnghe.lua_chon_4}
						</p>
					  	</c:forEach>
				</form>
				
				<div>
						
					<c:if test="${numberpage == 1}">
						<a href = "#" class="btn">Truoc</a>
						<input type="button" value="Đáp án" class="btn" onclick="Xuatketqua()"/>
						<a href = "Lambtnghe?pageid=${numberpage}&ma_bai_tap_nghe=<%=request.getAttribute("ma_bai_tap_nghe")%>" class="btn">Làm lại</a>
						<a href = "Lambtnghe?pageid=${numberpage+1}&ma_bai_tap_nghe=<%=request.getAttribute("ma_bai_tap_nghe")%>" class="btn">Sau</a>
							   
					</c:if>
					<c:if test="${numberpage == maxpageid}">
						<a href = "Lambtnghe?pageid=${numberpage-1}&ma_bai_tap_nghe=<%=request.getAttribute("ma_bai_tap_nghe")%>" class="btn">Trước</a>
						<input type="button" value="Đáp án" class="btn" onclick="Xuatketqua()"/>
						<a href = "Lambtnghe?pageid=${numberpage}&ma_bai_tap_nghe=<%=request.getAttribute("ma_bai_tap_nghe")%>" class="btn">Làm lại</a>
							   
						<a href ="#" class="btn btn-info disabled">Sau</a>
					</c:if>
						   
					<c:if test="${numberpage > 1 && numberpage < maxpageid}">
						<a href = "Lambtnghe?pageid=${numberpage-1}&ma_bai_tap_nghe=<%=request.getAttribute("ma_bai_tap_nghe")%>" class="btn">Trước</a>
						<input type="button" value="Đáp án" class="btn" onclick="Xuatketqua()"/>
						<a href = "Lambtnghe?pageid=${numberpage}&ma_bai_tap_nghe=<%=request.getAttribute("ma_bai_tap_nghe")%>" class="btn">Làm lại</a>
							   
						<a href = "Lambtnghe?pageid=${numberpage+1}&ma_bai_tap_nghe=<%=request.getAttribute("ma_bai_tap_nghe")%>" class="btn">Sau</a>
					</c:if>
					
				</div>	
		</div>
	 <jsp:include page="Cuoitrang.jsp"></jsp:include>
</body>
</html>