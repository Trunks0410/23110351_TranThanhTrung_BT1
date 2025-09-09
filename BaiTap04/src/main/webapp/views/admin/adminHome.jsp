<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang Quản Trị</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f4;
	text-align: center;
	padding-top: 50px;
}

.container {
	width: 80%;
	margin: 0 auto;
	background-color: #fff;
	padding: 20px;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h2 {
	color: #333;
}

p {
	color: #555;
}

a {
	display: inline-block;
	margin-top: 20px;
	padding: 10px 20px;
	background-color: #007bff;
	color: white;
	text-decoration: none;
	border-radius: 5px;
}

a:hover {
	background-color: #0056b3;
}
</style>
</head>
<body>
	<div class="container">
		<h2>Chào mừng, Admin!</h2>
		<c:if test="${not empty currentUser}">
			<p>Bạn đã đăng nhập thành công với tên:
				**${currentUser.username}**</p>
		</c:if>
		<p>Đây là trang quản trị. Bạn có toàn quyền quản lý hệ thống.</p>
		<a href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
	</div>
</body>
</html>