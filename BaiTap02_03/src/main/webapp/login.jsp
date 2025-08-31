<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<h1>Login</h1>
	<form action="${pageContext.request.contextPath}/login" method="post">
		<label for="username">Tài khoản:</label><br> <input type="text"
			id="username" name="username"><br> <label for="password">Mật
			khẩu:</label><br> <input type="password" id="password" name="password"><br>
		<div>
			<a href="${pageContext.request.contextPath}/forgot-password">Quên
				mật khẩu</a>
		</div>
		<br>
		<input type="submit" value="Đăng nhập">
	</form>
	<p style="color: red;">${alert}</p>
	<div class="links">
		Nếu chưa có tài khoản thì chọn <a
			href="${pageContext.request.contextPath}/register">Đăng ký</a>
	</div>
</body>
</html>