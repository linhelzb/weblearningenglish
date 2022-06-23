<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Quản lý danh sách đề bài tập nghe</title>
		
		<link rel="stylesheet" href="Template/Backend/assets/css/listgrammarguidelinemanage.css">
		<link rel="stylesheet" href="Template/Backend/assets/css/menu.css">
		<link rel="stylesheet" href="Template/Frontend/css/styles.css">  
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
                      <a href="#">
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
	            <c:if test="${tbquanlydsbtnghe!=null }">
					<h4>
						<a data-toggle="modal"> ${tbquanlydsbtnghe} </a>
					</h4>
				</c:if>
                <div >
                    <ul class="logo">
                        <li>
                            <i class="fa fa-home"></i>
                            <a href="QuantriFw">Trang chủ > </a>
                        </li>

                        <li >Quản lý danh sách bài tập nghe</li>
                   </ul>    
                </div>
                <div class="bang">
                    <table id="show">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Tiêu đề</th>
                            <th>Hình ảnh</th>
                            <th>Xóa</th>
                            <th>Thêm nội dung</th>
                            <th>Nội dung</th>
                        </tr>
                    </thead>   
					<tbody>
						<c:forEach items="${danhsachqlbtnghe}" var ="qlbtnghe">
							<tr>
								<td>
									${qlbtnghe.ma_bai_tap_nghe}
								</td>
								<td>
									${qlbtnghe.ten_bai_tap_nghe}
								</td>
								<td>
									${qlbtnghe.anh_bai_tap_nghe}
								</td>
															
								<td>
									<a href="XoaCauHoiPhanNghe?ma_bai_tap_nghe=${qlbtnghe.ma_bai_tap_nghe }"><i class="fas fa-trash" > </i></a>
								</td>
															
								<td>
									<a href="Themcauhoibtnghe?ma_bai_tap_nghe=${qlbtnghe.ma_bai_tap_nghe}">
										<i class="fas fa-pen"></i>	
									</a>
								</td>
								
								<td>
									<ul>
										<c:if test="${qlbtnghe.kiem_tra_cau_hoi==1}">
											<li>
												<i class="fas fa-check-square"></i>														
											</li>	
										</c:if>	
										<c:if test="${qlbtnghe.kiem_tra_cau_hoi==0}">
											<li>
												Trống								
											</li>	
										</c:if>											
									</ul>
																
								</td>
															
							</tr>
						</c:forEach>
					</tbody>
                 </table>
             </div>
            <div class="phantrang">
			<div class="nav">  
				<ul class="pt"> 
					<c:if test="${numberpage == 1}">
						<li class = "truoc"><a href = "#"><i class="fas fa-angle-double-left"></i></a></li>
							  
						<li class = "sau"><a href = "Hienthidsquanlybtnghe?pageid=${numberpage+1}"><i class="fas fa-angle-double-right"></i></a></li>
					</c:if>
					
				   <c:if test="${numberpage == maxpageid}">
					   <li class = "truoc"><a href = "Hienthidsquanlybtnghe?pageid=${numberpage-1}"><i class="fas fa-angle-double-left"></i></a></li>

					   <li class = "sau"><a href = "#"><i class="fas fa-angle-double-right"></i></a></li>
				   </c:if>
				   
				   <c:if test="${numberpage > 1 && numberpage < maxpageid}">
					   <li class = "truoc"><a href = "Hienthidsquanlybtnghe?pageid=${numberpage-1}"><i class="fas fa-angle-double-left"></i></a></li>

					   <li class = "sau"><a href = "Hienthidsquanlybtnghe?pageid=${numberpage+1}"><i class="fas fa-angle-double-right"></i></a></li>
				   </c:if>
				</ul>
			</div>	
		</div>
			<form action="Themtenbtnghe" method="POST">
                   <div class="chua-dung-tb">
                       <label for="tieude" >Thêm đề bài mới:</label>
                       <input type="text" name = "ten_bai_tap_nghe" id="tieude">
                       <button type="submit" id="btn" >Xong</button>
                   </div>
                   
              </form>
              <button> <a href="Themaudiohinhanhbtnghe" >Thêm audio và hình ảnh bài tập nghe</a></button>
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

									
	