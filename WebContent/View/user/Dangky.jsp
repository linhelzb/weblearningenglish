<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
		<meta charset="UTF-8">
		<title>Đăng ký</title>
		<link rel="stylesheet" href="Template/Frontend/css/stylelogin.css"> 
		
		<script type = "text/javascript">
	        function Validate(){
	
	            var ten_day_du = document.myform.name.value;
	            var ten_thanh_vien = document.myform.membername.value;
	            var mat_khau = document.myform.memberpass.value;
	
	            if(ten_day_du=="" || ten_thanh_vien=="" || mat_khau==""){
	            	alert("yêu cầu không bỏ trống");     
	            	return false;
	            }
	            else{
	            	if(mat_khau.length<6){
                        document.getElementById("mat-khau-sai").innerHTML = "Mật khẩu ít nhất 6 kí tự!";
                        return false;
                    }
	            }
	            
	        }
      </script>
</head>
<body>
		<div id="tieude">
			<a class="thuonghieu" href="TrangchuFw"><img src="Template/Frontend/img/logo.jpg"></a>
	   </div>
	
       <div class = "nen">
           <div class="form">
                <div class="tieude" > <p><strong>Đăng ký tài khoản mới</strong></p></div>
                <div class="" >
                    <form name = "myform" action="DangkyCtl" method="POST" onsubmit = "return Validate()">
                        <div class="dangnhapsai" style="color:red">
                            <%=request.getAttribute("thongbaodangki")!=null? request.getAttribute("thongbaodangki"):" "%>
                        </div>
                        <div>
                            <input type="text" class="dieukhien-form" placeholder="Tên người dùng" name="ten_day_du">
                            <span></span>
                        </div>
                        <br>
                        <div>
                            <input type="text" class="dieukhien-form" placeholder="Tên đăng nhập" name="ten_thanh_vien"><br>
                            <span></span>
                        </div>
                        <br>
						<div>
                            <input type="password" class="dieukhien-form" placeholder="Mật khẩu" name="mat_khau"><br>
                            
                        </div>
						<label height="10px" id="mat-khau-sai" style="color:red">
														
						</label>
                        <div>
                            <label><span>Nhớ mật khẩu</span>
                                <input type="checkbox" checked="checked"/>
                                
                            </label>
                        </div>
                        <br>
                        
                        <input type="submit" value="Đăng ký" class="dangnhap">
						<div class="dangnhap" style="padding-top:10px; text-align: center;margin-top: 15px;height: 35px;"> 
							<a href="DangnhapCtl"style="color: white;text-decoration: none">Đăng nhập</a>
						</div>
                        <div class="trove" style="margin-top: 30px;">
                            <a href="TrangchuFw">Trở về trang chủ</a>
                        </div>
                  </form>
                </div>
            </div>
       </div>
	</body>
</html>