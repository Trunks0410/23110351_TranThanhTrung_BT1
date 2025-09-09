<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Category List</title>
<link rel="stylesheet"
	href="<c:url value='/views/category-list.css' />">
</head>
<body>
	<h2>Danh sách Category</h2>

	<c:set var="currentUser" value="${sessionScope.currentUser}" />
	<c:set var="basePath" value="/admin/category" />
	<c:if test="${currentUser.roleid == 2}">
		<c:set var="basePath" value="/manager/category" />
	</c:if>
	<c:if test="${currentUser.roleid == 1}">
		<c:set var="basePath" value="/user/category" />
	</c:if>

	<div class="header-container">
		<div class="add-category-link">
			<a href="<c:url value='${basePath}/create' />">Thêm mới Category</a>
		</div>
		<div class="logout-link">
			<a href="<c:url value='/logout' />">Đăng xuất
				${currentUser.username}</a>
		</div>
	</div>

	<table border="1">
		<thead>
			<tr>
				<th>ID</th>
				<th>Tên Category</th>
				<th>Icon</th>
				<th>Người đăng</th>
				<th>Thao tác</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="category" items="${listCategory}">
				<tr>
					<td>${category.cateId}</td>
					<td>${category.catename}</td>
					<td><img
						src="${pageContext.request.contextPath}/${category.icon}"
						alt="Category Icon" class="category-icon" /></td>
					<td>${category.user.username}</td>
					<td class="action-links">
						<!-- Kiểm tra quyền của người dùng hiện tại trước khi hiển thị các nút thao tác -->
						<c:if
							test="${currentUser.roleid == 3 || category.user.id == currentUser.id}">
							<a
								href="<c:url value='${basePath}/edit?id=${category.cateId}' />"
								class="edit-link">Sửa</a>
							<a
								href="<c:url value='${basePath}/delete?id=${category.cateId}' />"
								class="delete-link"
								onclick="return confirm('Bạn có chắc chắn muốn xóa danh mục này không?');">Xóa</a>
						</c:if> <c:if
							test="${currentUser.roleid != 3 && category.user.id != currentUser.id}">
							<span class="access-denied-text">Không có quyền</span>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
