<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang chủ</title>
    <link rel="stylesheet" href="Template/Frontend/css/stylehome.css"> 
    <link rel="stylesheet" href="Template/Frontend/css/styles.css">
   
        
</head>

 <body>
	<jsp:include page="Dautrang.jsp"/>
	
	<div class="chua-dung">
		<div class="khoangtrang" style="position: relative;height: 60px;"></div>
		<div class="khung-slide">
            <div class="anh-slide">
                <div class="chua-dung-anh">
                    <img src="Template/Frontend/img/slide/slide1.jpg" width="900px" height="100%" />
                </div>
                <div class="chua-dung-anh">
                    <img src="Template/Frontend/img/slide/slide2.jpg" width="900px" height="100%" />
                </div>
                <div class="chua-dung-anh">
                    <img src="Template/Frontend/img/slide/slide3.png" width="900px" height="100%" />
                </div>
            </div>
        </div>
         <img class="ngancach" style="width: 100%;  border-bottom: 1px solid #D3D3D3;"/>

       <div class=noiDung>
            <div class="slogan">
                <h2> Anh ngữ Ms Hoa phấn đấu trở thành đơn vị đi đầu trong lĩnh vực đào tạo TOEIC.</h2>
                <h3> TOEIC ONLINE MIỄN PHÍ</h3>
            </div>
            <div class="noidung">
                <div class="nd">
                    <img src="Template/Frontend/img/grammar.jpg">
                    <h2><a href="DsbaihdnguphapFw?pageid=1">Hướng dẫn phần ngữ pháp</a></h2>
                    <p>
                        Cung cấp bài hướng dẫn sát đề thi.
                    </p>
                </div>
                <div class="nd">
                    <img src="Template/Frontend/img/reading.jpg" width="365px" height="180px">
                   <a href="Hienthidsbtnghe?pageid=1"><h2>Bài tập phần nghe</h2></a>
                    <p>
                        Cung cấp bài tập sát đề thi.
                    </p>  
                </div>
                
                <div class="nd">
                    <img src="Template/Frontend/img/exam.jpg">
                    <h2><a href="#">Đề thi thử</a></h2>
                    <p>
                        Sát với đề thi thật. 
                    </p>
                    
                </div>
            </div>
            <div class="noidung">
                <div class="nd">
                    <img src="Template/Frontend/img/grammar.jpg">
                    <h2><a href="Hienthidshdtuvung?pageid=1">Hướng dẫn phần từ vựng</a></h2>
                    
                    <p>
                        Cung cấp bài hướng dẫn sát đề thi.
                    </p>
                </div>
                <div class="nd">
                    <img src="Template/Frontend/img/reading.jpg" width="365px" height="180px">
                    <a href="Hienthidsbtphandoc?pageid=1"><h2>Bài tập phần đọc</h2></a>
                    <p>
                        Cung cấp bài tập sát đề thi.
                    </p>   
                </div>
                <div class="nd">
                    
                </div>
             </div>

            <img class="ngancach" src="Template/Frontend/img/slide-bg.png" width="100%" height="10%"/>
            <div class="btn-nd" >
                <h3> TÀI LIỆU LUYỆN THI TOEIC</h3><br>
            </div>
            <div class="noidung">
                <div class="cap-do">
                    <div class="tieude">
                        <h3> Level 250-500</h3>
                    </div>
                    <ul class="tl">
                        <li><a href="https://www.anhngumshoa.com/tin-tuc/20-chu-de-giao-tiep-trong-cong-viec-38170.html">
                            [FULL PDF] 20 CHỦ ĐỀ GIAO TIẾP TRONG CÔNG VIỆC</a></li>
                        <li><a href="https://www.anhngumshoa.com/tin-tuc/download-ngay-tron-bo-english-vocabulary-in-use-tu-vung-cho-moi-cap-do-37719.html">
                            DOWNLOAD NGAY TRỌN BỘ ENGLISH VOCABULARY IN USE - TỪ VỰNG CHO MỌI CẤP ĐỘ</a></li>
                        <li><a href="https://www.anhngumshoa.com/tin-tuc/qua-tang-hoc-vien-tai-lieu-doc-quyen-do-ms-hoa-bien-soan-37521.html">
                            QUÀ TẶNG HỌC VIÊN: TÀI LIỆU ĐỘC QUYỀN DO MS HOA BIÊN SOẠN</a></li>
                        <li><a href="https://www.anhngumshoa.com/tin-tuc/download-sach-toeic-preparation-pdf-audio-37816.html">
                            TRỌN BỘ TOEIC PREPARATION LC + RC VOLUME 1, 2 {PDF + AUDIO}</a></li>
                        <li><a href="https://www.anhngumshoa.com/tin-tuc/download-sach-bootcamp-for-the-toeic-37812.html">
                            BOOTCAMP FOR THE TOEIC - TÀI LIỆU ÔN THI TOEIC THEO KỸ NĂNG [PDF + AUDIO]</a></li>
                        <li><a href="https://www.anhngumshoa.com/tin-tuc/tai-lieu-luyen-thi-toeic-sach-very-easy-toeic-34868.html">
                            TÀI LIỆU LUYỆN THI TOEIC: SÁCH VERY EASY TOEIC</a></li>
                    </ul>
                </div>
                <div class="cap-do">
                    <div class="tieude">
                        <h3> Level 500-750</h3>
		            </div>
		                    <ul class="tl">
		                        <li><a href="/tin-tuc/20-chu-de-giao-tiep-trong-cong-viec-38170.html">[FULL PDF] 20 CHỦ ĐỀ GIAO TIẾP TRONG CÔNG VIỆC</a></li>
								<li><a href="/tin-tuc/300-cum-tu-chac-chan-xuat-hien-trong-bai-thi-toeic-37959.html">300 cụm từ chắc chắn xuất hiện trong bài thi TOEIC</a></li>
								<li><a href="/tin-tuc/download-ngay-tron-bo-english-vocabulary-in-use-tu-vung-cho-moi-cap-do-37719.html">Download ngay trọn bộ ENGLISH VOCABULARY IN USE - Từ vựng cho mọi cấp độ</a></li>
								<li><a href="/tin-tuc/download-taking-the-toeic-37810.html">(Review + Download) Bộ sách Taking The TOEIC 1, 2 mới nhất </a></li>
								<li><a href="/tin-tuc/tron-bo-tai-lieu-am-tron-diem-toeic-listening-full-pdf-audio-34893.html">TRỌN BỘ TÀI LIỆU ẴM TRỌN ĐIỂM TOEIC LISTENING {Full PDF + Audio}</a></li>
								<li><a href="/tin-tuc/tron-bo-sach-toeic-sat-voi-de-thi-that-toeic-nhat-35260.html">Trọn bộ Sách TOEIC sát với đề thi thật TOEIC nhất</a></li>
		                    </ul>
		                
                    </div>
               
                <div class="cap-do">
                    <div class="tieude">
                        <h3> Level 750-990</h3>
                     </div>
                        <ul class="tl">
			                <li><a href="/tin-tuc/20-chu-de-giao-tiep-trong-cong-viec-38170.html">[FULL PDF] 20 CHỦ ĐỀ GIAO TIẾP TRONG CÔNG VIỆC</a></li>
							<li><a href="/tin-tuc/download-ngay-tron-bo-english-vocabulary-in-use-tu-vung-cho-moi-cap-do-37719.html">Download ngay trọn bộ ENGLISH VOCABULARY IN USE - Từ vựng cho mọi cấp độ</a></li>
							<li><a href="/tin-tuc/american-accent-training-ebook-cd-ndash-tai-lieu-phat-am-chuan-giong-my-37728.html">[Download] American Accent Training {Ebook + CD} – tài liệu phát âm chuẩn giọng Mỹ</a></li>
							<li><a href="/tin-tuc/22-chu-de-giao-tiep-phai-biet-neu-muon-ban-tieng-anh-nhu-gio-37096.html">[FULL PDF+AUDIO] 22 chủ đề GIAO TIẾP phải biết nếu muốn bắn tiếng Anh như gió</a></li>
							<li><a href="/tin-tuc/tron-bo-big-step-toeic-ban-dep-37097.html">Trọn bộ Big Step TOEIC - Bản đẹp [FULL PDF + AUDIO]</a></li>
							<li><a href="/tin-tuc/download-ngay-tron-bo-tactics-for-listening-key-bo-sach-luyen-nghe-cho-moi-cap-do-37717.html">Download ngay trọn bộ Tactics for Listening + KEY - Bộ sách luyện nghe cho mọi cấp độ</a></li>
		                </ul>
		                </div>
                    </div>
                </div>
            </div>
       </div>
   </div>

   <jsp:include page="Cuoitrang.jsp"/>

  </body>
</html>