<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Trang chủ quản trị</title>
	<meta charset="UTF-8">
	
    <link rel="stylesheet" href="Template/Backend/assets/css/stylehome.css"> 
    <link rel="stylesheet" href="Template/Backend/assets/css/menu.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/> 
	
</head>
<body >

	<div class="chua-dung">
        <div class="menu">
            
            <nav class="cot-ben">
                <div class="vanban">
                    <i class="fas fa-user-lock" style="color: aliceblue;"></i>
                    Trang quản trị
                </div>
                <ul>
                   <li>
                      <a href="#" >
                        <i class="fab fa-buffer"> </i>
                      Quản lí phần hướng dẫn
                      <span class="fas fa-caret-down"></span>
                      </a>
                      <ul class="mot-show">
                         <li><a href="Danhsachhdnguphap?pageid=1">Ngữ pháp</a></li>
                         <li><a href="Hienthidstuvung?pageid=1">Từ vựng</a></li>
                      </ul>
                   </li>
                   <li>
                      <a href="#">
                        <i class="fab fa-buffer"> </i>
                        Quản lí bài tập
                      <span class="fas fa-caret-down"></span>
                      </a>
                      <ul class="hai-show">
                         <li><a href="Hienthidsbtdoc?pageid=1">Bài tập đọc</a></li>
                         <li><a href="Hienthidsquanlybtnghe?pageid=1">Bài tập nghe</a></li>
                      </ul>
                   </li>
                   <li>
                        <a href="#">
                            <i class="fab fa-buffer"> </i>
                            Quản lí đề thi
                            <span class="fas fa-caret-down"></span>
                        </a>
                        <ul class="ba-show">
                            <li><a href="#">Đề thi</a></li>
                         </ul>
                    </li>

                </ul>
             </nav>
            
        </div>
        <div class="noidung">
            <div class="tieude">
                <div><a href="QuantriFw"><img src="Template/Frontend/img/logo.jpg" width="245" height="51"/></a></div> 
               
               
                    <ul class="nav-s">       
                        <li><a style="font-weight: 500;">Xin chào, <%=session.getAttribute("sessionquantrivien") %></a></li>
                        <li><a href="DangxuatCtl" style="color: #C80000;text-decoration: none;padding-left: 25px;">Đăng xuất</a></li>  
                    </ul>
               
            </div>
            <div class="nd">
            	<div >
						<ul class="logo">
							<li>
								<i class="fas fa-home"></i>
								<a href="QuantriFw">Trang chủ</a>
							</li>	
						</ul>					
					</div>
                <p class="noiDung">Trang quản trị<br>Đào tạo TOEIC số 1 Việt Nam</p>
                
            </div>
            <jsp:include page="Cuoitrangquantri.jsp"/>
        </div>
    </div>
    
	<script>
	  let arrow = document.querySelectorAll(".fa-caret-down");
	  for (var i = 0; i < arrow.length; i++) {
	    arrow[i].addEventListener("click", (e)=>{
	   e.target.parentElement.nextElementSibling.classList.toggle("showMenu");
	   e.target.classList.toggle("rotate");
	   e.target.parentElement.parentElement.classList.toggle("active");
	    });
	  }
	</script>
		
</body>
</html>