<!-- hi -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Quản lý danh sách từ vựng</title>
	<link rel="stylesheet" href="Template/Backend/assets/css/listgrammarguidelinemanage.css">
		<link rel="stylesheet" href="Template/Frontend/css/styles.css">  
		<link rel="stylesheet" href="Template/Backend/assets/css/menu.css"> 
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
                            <li><a href="Hienthidsquanlydethi?pageid=1">Đề thi</a></li>
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
	            <c:if test="${tbdstuvung!=null}">
					<h4>								
						<a class="green" data-toggle="modal">${tbdstuvung}</a>
					</h4>
					
				</c:if>
                <div >
                    <ul class="logo">
                        <li>
                            <i class="fas fa-home"></i>
                            <a href="QuantriFw">Trang chủ > </a>
                        </li>

                        <li>Quản lý bài hướng dẫn từ vựng</li>
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
						<c:forEach items="${danhsachtuvung}" var ="dstuvung">
							<tr>
								<td>
										${dstuvung.ma_loai_tu_vung}
								</td>
								<td>
										${dstuvung.ten_nhom_tu_vung}
								</td>
								<td>
										${dstuvung.hinh_anh}
								</td>
															
								<td>
														
									<i class="fas fa-trash"></i>						
																
								</td>
															
								<td>
										<a href="Themndchudetuvung?ma_loai_tu_vung=${dstuvung.ma_loai_tu_vung}">
											<i class="fas fa-pen"></i>
										</a>
								</td>
								<td>
									<ul>
										<c:if test="${dstuvung.kiem_tra_noi_dung==1}">
											<li>
												<i class="fas fa-check-square"></i>													
											</li>	
										</c:if>	
										<c:if test="${dstuvung.kiem_tra_noi_dung==0}">
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
								  
								   <li class = "sau"><a href = "Hienthidstuvung?pageid=${numberpage+1}"><i class="fas fa-angle-double-right"></i></a></li>
							   </c:if>
							   <c:if test="${numberpage == maxp}">
								   <li class = "truoc"><a href = "Hienthidstuvung?pageid=${numberpage-1}"><i class="fas fa-angle-double-left"></i></a></li>
								  
								   <li class = "sau"><a href ="#"><i class="fas fa-angle-double-right"></i></a></li>
							   </c:if>
							   
							   <c:if test="${numberpage > 1 && numberpage < max}">
								   <li class = "truoc"><a href = "Hienthidstuvung?pageid=${numberpage-1}"><i class="fas fa-angle-double-left"></i></a></li>
								   
								   <li class = "sau"><a href = "Hienthidstuvung?pageid=${numberpage+1}"><i class="fas fa-angle-double-right"></i></a></li>
							   </c:if>			   
					</ul>
				</div>	
			</div>
             
			<form action="Themchudetuvung" method="POST">
                   <div class="chua-dung-tb">
                       <label for="tieude" >Chủ đề từ vựng mới:</label>
                       <input type="text" name = "ten_nhom_tu_vung" id="tieude">
                       <button type="submit" id="btn" >Xong</button>
                   </div>
                   
              </form>
              <button> <a href="Themaudiohinhanhtuvung" role="button" class="btn">Thêm audio và hình ảnh cho chủ đề từ vựng</a></button>
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