<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
</head>
<body>
    <h1>Registration</h1>
    <form action="${pageContext.request.contextPath}/register" method="post">
        <label for="username">Tài khoản:</label><br>
        <input type="text" id="username" name="username"><br>
        <label for="password">Mật khẩu:</label><br>
        <input type="password" id="password" name="password"><br>
        <label for="email">Email:</label><br>
        <input type="email" id="email" name="email"><br>
        <label for="fullname">Họ và tên:</label><br>
        <input type="text" id="fullname" name="fullname"><br>
        <label for="phone">Số điện thoại:</label><br>
        <input type="text" id="phone" name="phone"><br><br>
        <input type="submit" value="Đăng ký">
    </form>
    <p style="color:red;">${alert}</p>
    <div class="links">
        Nêu có tài khoản thì chọn <a href="${pageContext.request.contextPath}/login">Đăng nhập</a>
    </div>
</body>
</html>