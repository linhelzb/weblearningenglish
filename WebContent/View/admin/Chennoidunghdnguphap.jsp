<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý phần hướng dẫn ngữ pháp</title>
	
    <link rel="stylesheet" href="Template/Backend/assets/css/listgrammarguidelinemanage.css"> 
    <link rel="stylesheet" href="Template/Backend/assets/css/menu.css">
    <link rel="stylesheet" href="Template/Backend/assets/css/texteditor.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"/>  
</head>
<body>
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
                            <li><a href="Hienthidsquanlydethi?pageid=1">Đề thi</a></li>
                         </ul>
                    </li>
                </ul>
             </nav>
        </div>
        <div class="noidung">
            <div class="tieude">
                <div><a href="TrangchuFw"><img src="Template/Frontend/img/logo.jpg" width="245" height="51"/></a></div>             
                <ul class="nav-s">       
                   <li><a style="font-weight: 500;">Xin chào, <%=session.getAttribute("sessionadmin") %></a></li>
                   <li><a href="DangxuatCtl" style="color: #C80000;text-decoration: none;padding-left: 25px;">Đăng xuất</a></li>  
                </ul>
            </div>
            <div class="nd">
	            
                <div >
                    <ul class="logo">
                        <li>
                            <i class="fa fa-home"></i>
                            <a href="QuantriFw">Trang chủ > </a>
                        </li>

                        <li>
							<a href="#">Quản lý phần hướng dẫn</a>
						</li>
						<li>Ngữ pháp</li>
                   </ul> 
                </div>
             	<h4 style="padding-top: 28px;">Soạn thảo nội dung bài ngữ pháp</h4>
				<h4 >
					<a><%=request.getAttribute("tbnoidunghdnguphap")!=null?request.getAttribute("tbnoidunghdnguphap"):" " %> </a>
				</h4>
             	<form action="NoidunghdnguphapCtl?ma_ngu_phap=<%=request.getAttribute("ma_ngu_phap") %>" method="POST">
										
					<div class="noidung-chinh" style="padding-top: 28px;">
				        <div class="suavanban-tieude">
				            <button type="button" class="butn" data-cmd="bold">
				                <i class="fa fa-bold"></i>
				            </button>
				            <button type="button" class="butn" data-cmd="italic">
				                <i class="fa fa-italic"></i>
				            </button>
				            <button type="button" class="butn" data-cmd="underline">
				                <i class="fa fa-underline"></i>
				            </button>
				            <button type="button" class="butn" data-cmd="insertUnorderedList">
				                <i class="fa fa-list-ul"></i>
				            </button>
				            <button type="button" class="butn" data-cmd="insertOrderedList">
				                <i class="fa fa-list-ol"></i>
				            </button>
				            <button type="button" class="butn" data-cmd="createLink">
				                <i class="fa fa-link"></i>
				            </button>
				            <button type="button" class="butn" data-cmd="justifyLeft">
				                <i class="fa fa-align-left"></i>
				            </button>
				            <button type="button" class="butn" data-cmd="justifyCenter">
				                <i class="fa fa-align-center"></i>
				            </button>
				            <button type="button" class="butn" data-cmd="justifyRight">
				                <i class="fa fa-align-right"></i>
				            </button>
				            <button type="button" class="butn" data-cmd="justifyFull">
				                <i class="fa fa-align-justify"></i>
				            </button>
				            <button type="button" class="butn" data-cmd="insertImage">
				                <i class="fa fa-image"></i>
				            </button>
				        </div>
				    	
				        <div style="padding-top: 50px;">
				           <textarea name="noi_dung_ngu_phap" rows="25" cols="120" >
													

							</textarea>
						</div>
				       
				        
				        <button type="submit" style="padding: 8px 16px;">
							Lưu
						</button>
				    </div> 
				</form>
			
         </div>  					
            <jsp:include page="Cuoitrangquantri.jsp"/>
        </div>
    </div>
	<script >
		const buttons = document.querySelectorAll('.butn');
		for(let i = 0; i < buttons.length; i++){
		    buttons[i].addEventListener('click', ()=>{
		        let cmd = buttons[i].getAttribute('data-cmd');
		        
		        if(cmd === "insertImage" || cmd === "createLink"){
		            let url = prompt("Enter link:", "");
		            document.execCommand(cmd, false, url);
		        }else{
		        	document.execCommand(cmd, false, null);
		        }
		    })
		}
		const apply = () => {
			const x = document.querySelectorAll("#texteditor-content");
			document.getElementById("getm").value = x[0].innerHTML;
		}
		
	</script>
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

