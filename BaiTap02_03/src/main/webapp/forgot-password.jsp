<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quên mật khẩu</title>
</head>
<body>
    <h1>Quên mật khẩu</h1>
    <form action="${pageContext.request.contextPath}/forgot-password" method="post">
        <label for="email">Email:</label><br>
        <input type="email" id="email" name="email" required><br><br>
        <input type="submit" value="Gửi">
    </form>
    <p style="color:red;">${alert}</p>
    <div class="links">
        <a href="${pageContext.request.contextPath}/login">Quay lại trang đăng nhập</a>
    </div>
</body>
</html>