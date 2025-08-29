<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang Đăng Nhập</title>
</head>
<body>

    <h2>Đăng Nhập</h2>

    <form action="login" method="post">
        <label for="username_id">Tên đăng nhập:</label><br>
        <input type="text" id="username_id" name="username"> <br><br>

        <label for="password_id">Mật khẩu:</label><br>
        <input type="password" id="password_id" name="password"> <br><br>

        <input type="submit" value="Đăng nhập">
    </form>

</body>
</html>