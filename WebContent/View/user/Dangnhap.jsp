<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng nhập</title>

	<link rel="stylesheet" href="Template/Frontend/css/stylelogin.css"> 
	
</head>
<body>
	<div id="tieude">
			<a class="thuonghieu" href="TrangchuFw"><img src="Template/Frontend/img/logo.jpg" width="245" height="51" ></a>
	</div>

       <div class = "bg">
           <div class="form">
                <div class="tieude" > <p>Đăng nhập <strong>Anh ngữ MsHoa</strong></p></div>
                <div class="khoi-form" >
                    <form action="DangnhapCtl" method="POST">
                        <div class="dangnhapsai" style="color:red">
                            <%=request.getAttribute("thongbaodangnhap")!=null? request.getAttribute("thongbaodangnhap"):" "%>
                        </div>
                        <div class="">
                            <label for="username">Email</label><br>
                            <input type="text" class="dieukhien-form" placeholder="Tên đăng nhập" name="ten_thanh_vien">
                            
                        </div>
                        <br>
                        <div class="">
                            <label for="password">Mật khẩu</label><br>
                            <input type="password" class="dieukhien-form" placeholder="Mật khẩu" name="mat_khau"><br>
                            
                        </div>
                        <br>
                        <div class="">
                            <label class=""><span>Nhớ mật khẩu</span>
                                <input type="checkbox" checked="checked"/>
                               
                            </label>
                        </div>
                        <br>
                       
                        <input type="submit" value="Đăng nhập" class="dangnhap">
                        <div class="dangky">
                            <p>Bạn chưa có tài khoản?<a href="DangkyCtl">Đăng ký</a></p>
                        </div>
                        <div class="trove">
                            <a href="TrangchuFw">Trở về trang chủ</a>
                        </div>
                  </form>
                </div>
            </div>
       </div>
	</body>

</html>
