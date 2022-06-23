<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
		<c:forEach items="${dapandungbtdoc}" var ="dapandung">
						<c:if test="${dapandung.dap_an == 'A'}">
							<c:if test="${kq == 'A'}">
								<p>
									${dapandung.so_thu_tu}. ${dapandung.cau_hoi}
								</p>
								<p>
								  <img alt="" src="Image/correct.png"> ${dapandung.lua_chon_1}
								
								</p>
								<p>
								  ${dapandung.lua_chon_2}
								</p>
								<p>
								  ${dapandung.lua_chon_3}
								</p>
								<p>
								  ${dapandung.lua_chon_4}
								</p>
							</c:if>
								
							<c:if test="${kq == 'B'}">
								<p>${dapandung.so_thu_tu}. ${dapandung.cau_hoi}
								</p>
								<p>
								  <img alt="" src="Image/correct.png">${dapandung.lua_chon_1}
								</p>
								<p>
								  <img alt="" src="Image/incorrect.png">${dapandung.lua_chon_2}
								</p>
								<p>
								  ${dapandung.lua_chon_3}
								</p>
								<p>
								  ${dapandung.lua_chon_4}
								</p>
							</c:if>
							
							<c:if test="${kq == 'C'}">
								<p>${dapandung.so_thu_tu}. ${dapandung.cau_hoi}</p>
								<p>
								  <img alt="" src="Image/correct.png">${dapandung.lua_chon_1}
								</p>
								  ${dapandung.lua_chon_2}
								<p>
								  <img alt="" src="Image/incorrect.png">${dapandung.lua_chon_3}
								</p>
								<p>
								  ${dapandung.lua_chon_4}
								</p>
							</c:if>
							
							<c:if test="${kq == 'D'}">
								<p>${dapandung.so_thu_tu}. ${dapandung.cau_hoi}</p>
								<p>  
								  <img alt="" src="Image/correct.png">${dapandung.lua_chon_1}
								</p>
								<p>
								  ${dapandung.lua_chon_2}
								</p>
								<p>
								  ${dapandung.lua_chon_3}
								</p>
								<p>
								  <img alt="" src="Image/incorrect.png">${dapandung.lua_chon_4}
								</p>>
							</c:if>
					
					</c:if>
					
					<c:if test="${dapandung.dap_an == 'B'}">
							<c:if test="${kq == 'A'}">
								<p>${dapandung.so_thu_tu}. ${dapandung.cau_hoi}</p>
								<p>
								  <img alt="" src="Image/incorrect.png">${dapandung.lua_chon_1}
								</p>
								<p>
								  <img alt="" src="Image/correct.png">${dapandung.lua_chon_2}
								</p>
								<p>
								  ${dapandung.lua_chon_3}
								</p>
								<p>
								  ${dapandung.lua_chon_4}
								</p>
							</c:if>
								
							<c:if test="${kq == 'B'}">
								<p>${dapandung.so_thu_tu}. ${dapandung.cau_hoi}</p>
								<p>  
								  ${dapandung.lua_chon_1}
								</p>
								<p>
								  <img alt="" src="Image/correct.png">${dapandung.lua_chon_2}
								</p>
								<p>
								  ${dapandung.lua_chon_3}
								</p>
								<p>
								  ${dapandung.lua_chon_4}
								</p>
							</c:if>
							
							<c:if test="${kq == 'C'}">
								<p>${dapandung.so_thu_tu}. ${dapandung.cau_hoi}</p>
								<p>  
								  ${dapandung.lua_chon_1}
								</p>
								<p>
								  <img alt="" src="Image/correct.png">${dapandung.lua_chon_2}
								</p>
								<p>
								  <img alt="" src="Image/incorrect.png">${dapandung.lua_chon_3}
								</p>
								<p>
								  ${dapandung.lua_chon_4}
								</p>
							</c:if>
							
							<c:if test="${kq == 'D'}">
								<p>${dapandung.so_thu_tu}. ${dapandung.cau_hoi}</p>
								 <p> 
								  ${dapandung.lua_chon_1}
								</p>
								<p>
								  <img alt="" src="Image/correct.png">${dapandung.lua_chon_2}
								</p>
								<p>
								  ${dapandung.lua_chon_3}
								</p>
								<p>
								  <img alt="" src="Image/incorrect.png">${dapandung.lua_chon_4}
								</p>
							</c:if>	
					</c:if>
					
					
					<c:if test="${dapandung.dap_an == 'C'}">
							<c:if test="${kq == 'A'}">
								<p>${dapandung.so_thu_tu}. ${dapandung.cau_hoi}</p>
								<p>  
								<img alt="" src="Image/incorrect.png">${dapandung.lua_chon_1}
								</p>
								<p>
								  ${dapandung.lua_chon_2}
								</p>
								<p>
								  <img alt="" src="Image/correct.png">${dapandung.lua_chon_3}
								</p>
								<p>
								  ${dapandung.lua_chon_4}
								</p>
							</c:if>
								
							<c:if test="${kq == 'B'}">
								<p>${dapandung.so_thu_tu}. ${dapandung.cau_hoi}</p>
								<p>  
								  ${dapandung.lua_chon_1}
								</p>
								<p>
								  <img alt="" src="Image/incorrect.png">${dapandung.lua_chon_2}
								</p>
								<p>
								  <img alt="" src="Image/correct.png">${dapandung.lua_chon_3}
								</p>
								<p>
								  ${dapandung.lua_chon_4}
								</p>
							</c:if>
							
							<c:if test="${kq == 'C'}">
								<p>${dapandung.so_thu_tu}. ${dapandung.cau_hoi}</p>
								<p> 
								  ${dapandung.lua_chon_1}
								</p>
								<p>
								  ${dapandung.lua_chon_2}
								</p>
								<p>
								  <img alt="" src="Image/correct.png">${dapandung.lua_chon_3}
								</p>
								<p>
								  ${dapandung.lua_chon_4}
								</p>
							</c:if>
							
							<c:if test="${kq == 'D'}">
								<p>${dapandung.so_thu_tu}. ${dapandung.cau_hoi}</p>
								<p>  
								  ${dapandung.lua_chon_1}
								</p>
								<p>
								  ${dapandung.lua_chon_2}
								</p>
								<p>
								  <img alt="" src="Image/correct.png">${dapandung.lua_chon_3}
								</p>
								<p>
								  <img alt="" src="Image/incorrect.png">${dapandung.lua_chon_4}
								</p>
							</c:if>	
					</c:if>
					
					<c:if test="${dapandung.dap_an == 'D'}">
							<c:if test="${kq == 'A'}">
								<p>${dapandung.so_thu_tu}. ${dapandung.cau_hoi}</p>
								<p>  
								  <img alt="" src="Image/incorrect.png">${dapandung.lua_chon_1}
								</p>
								<p>
								  ${dapandung.lua_chon_2}
								</p>
								<p>
								  ${dapandung.lua_chon_3}
								</p>
								<p>
								  <img alt="" src="Image/correct.png">${dapandung.lua_chon_4}
								</p>
							</c:if>
								
							<c:if test="${kq == 'B'}">
								<p>${dapandung.so_thu_tu}. ${dapandung.cau_hoi}</p>
								<p>
								  ${dapandung.lua_chon_1}
								</p>
								<p>
								  <img alt="" src="Image/incorrect.png">${dapandung.lua_chon_2}
								</p>
								<p>
								  ${dapandung.lua_chon_3}
								</p>
								<p>
								  <img alt="" src="Image/correct.png">${dapandung.lua_chon_4}
								</p>
							</c:if>
							
							<c:if test="${kq == 'C'}">
								<p>${dapandung.so_thu_tu}. ${dapandung.cau_hoi}</p>
								<p> 
								  ${dapandung.lua_chon_1}
								</p>
								<p>
								  ${dapandung.lua_chon_2}
								</p>
								<p>
								  <img alt="" src="Image/incorrect.png">${dapandung.lua_chon_3}
								</p>
								<p>
								  <img alt="" src="Image/correct.png">${dapandung.lua_chon_4}
								</p>
							</c:if>
							
							<c:if test="${kq == 'D'}">
								<p>${dapandung.so_thu_tu}. ${dapandung.cau_hoi}</p>
								<p>  
								 ${dapandung.lua_chon_1}
								</p>
								<p>
								  ${dapandung.lua_chon_2}
								</p>
								<p>
								  ${dapandung.lua_chon_3}
								</p>
								<p>
								  <img alt="" src="Image/correct.png">${dapandung.lua_chon_4}
								</p>
							</c:if>	
					</c:if>
		</c:forEach>
</body>
</html>