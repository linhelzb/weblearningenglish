<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="Template/Frontend/css/header.css"> 
</head>
<body>
	<%
		if(session.getAttribute("sessionnguoidung")==null){
	%>
	<div id="hang-tieude">
		<div><a class="thuonghieu" href="TrangchuFw"><img src="Template/Frontend/img/logo.jpg" width="245" height="51" /></a></div> 
        <div class="nav">  
            <ul class="nav-s">       
                <li><a href="DangnhapCtl">Đăng nhập</a></li>
		        <li><a href="DangkyCtl" style="padding-left: 25px;">Đăng ký</a></li>
            </ul>
        </div>
       </div>
   <%
   	}
	else{
	%>
	<div id="hang-tieude">
		<div><a class="thuonghieu" href="TrangchuFw"><img src="Template/Frontend/img/logo.jpg" width="245" height="51" /></a></div> 
        <div class="nav">  
            <ul class="nav-s">       
                <li>Xin chào: ${sessionnguoidung}</li>
                <li><a href="DangxuatCtl" style="color: rgb(185, 64, 64);text-decoration: none;padding-left: 25px;">Đăng xuất</a></li>  
            </ul>
        </div>
       </div>
	<%
   	}
	%>	
</body>
</html>