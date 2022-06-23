<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm danh sách ngữ pháp</title>
	
    <link rel="stylesheet" href="Template/Backend/assets/css/listgrammarguidelinemanage.css"> 
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
	            <c:if test="${tbdsqlhdnguphap!=null }">
					<h4>
						<a data-toggle="modal"> ${tbdsqlhdnguphap} </a>
					</h4>
					
				</c:if>
                <div >
                    <ul class="logo">
                        <li>
                            <i class=" fa fa-home"></i>
                            <a href="QuantriFw">Trang chủ > </a>
                        </li>

                        <li>
                            <a href="#">Quản lý phần hướng dẫn > </a>
                        </li>
                        <li>Thêm danh sách bài hướng dẫn ngữ pháp</li>
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
						<c:forEach items="${dsqlhuongdannguphap}" var="qlhuongdannguphap">
							<tr>
								<td>
									${qlhuongdannguphap.ma_ngu_phap}
								</td>
								<td>
									${qlhuongdannguphap.ten_ngu_phap}
								</td>
								<td>
									${qlhuongdannguphap.hinh_anh_ngu_phap}
								</td>
														
								<td>
									<a class="red" href="XoaBaiHuongDanNguPhap?ma_ngu_phap=${qlhuongdannguphap.ma_ngu_phap}">
										<i class="fas fa-trash" ></i>
									</a>
								</td>
								<td>
									<a class="green" href="NoidunghdnguphapFw?ma_ngu_phap=${qlhuongdannguphap.ma_ngu_phap}">
										<i class="fas fa-pen"></i>
									</a>
								</td>
								<td>
									<ul>
										<c:if test="${qlhuongdannguphap.noi_dung_ngu_phap!=null}">
											<li>
												<i class="fas fa-check-square"></i>
																			
											</li>
										</c:if>
										<c:if test="${qlhuongdannguphap.noi_dung_ngu_phap==null}">
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
								  
								   <li class = "sau"><a href = "Danhsachhdnguphap?pageid=${numberpage+1}"><i class="fas fa-angle-double-right"></i></a></li>
							   </c:if>
							   <c:if test="${numberpage == max}">
								   <li class = "truoc"><a href = "Danhsachhdnguphap?pageid=${numberpage-1}"><i class="fas fa-angle-double-left"></i></a></li>
								  
								   <li class = "sau"><a href ="#"><i class="fas fa-angle-double-right"></i></a></li>
							   </c:if>
							   
							   <c:if test="${numberpage > 1 && numberpage < max}">
								   <li class = "truoc"><a href = "Danhsachhdnguphap?pageid=${numberpage-1}"><i class="fas fa-angle-double-left"></i></a></li>
								   
								   <li class = "sau"><a href = "Danhsachhdnguphap?pageid=${numberpage+1}"><i class="fas fa-angle-double-right"></i></a></li>
							   </c:if>
						
							   
					</ul>
				</div>	
			</div>
             
			<form action="Chentenhdnguphap" method="POST">
                   <div class="chua-dung-tb">
                       <label for="tieude" >Thêm tiêu đề mới:</label>
                       <input type="text" name = "ten_ngu_phap" id="tieude">
                       <button type="submit" id="btn" >Xong</button>
                   </div>
              </form>
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