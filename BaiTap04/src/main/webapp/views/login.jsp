<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f4f7f9;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	margin: 0;
}

.login-container {
	background-color: #ffffff;
	padding: 40px;
	border-radius: 8px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	width: 350px;
	text-align: center;
}

h2 {
	margin-bottom: 25px;
	color: #333;
}

.form-group {
	margin-bottom: 20px;
	text-align: left;
}

label {
	display: block;
	margin-bottom: 8px;
	font-weight: bold;
	color: #555;
}

input[type="text"], input[type="password"] {
	width: 100%;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 5px;
	box-sizing: border-box;
}

input[type="submit"] {
	width: 100%;
	padding: 12px;
	background-color: #3498db;
	border: none;
	color: white;
	font-size: 16px;
	font-weight: bold;
	border-radius: 5px;
	cursor: pointer;
	transition: background-color 0.3s ease;
}

input[type="submit"]:hover {
	background-color: #2980b9;
}

.error-message {
	color: #e74c3c;
	margin-top: 15px;
}
</style>
</head>
<body>
	<div class="login-container">
		<h2>Đăng nhập</h2>
		<form action="${pageContext.request.contextPath}/login" method="post">
			<div class="form-group">
				<label for="username">Tên đăng nhập:</label> <input type="text"
					id="username" name="username" required>
			</div>
			<div class="form-group">
				<label for="password">Mật khẩu:</label> <input type="password"
					id="password" name="password" required>
			</div>
			<input type="submit" value="Đăng nhập">
		</form>
		<c:if test="${not empty param.error}">
			<p class="error-message">Tên đăng nhập hoặc mật khẩu không đúng.</p>
		</c:if>
	</div>
</body>
</html>