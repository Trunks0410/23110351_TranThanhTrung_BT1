<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><c:if test="${category != null}">Chỉnh sửa</c:if> <c:if
		test="${category == null}">Thêm mới</c:if> Category</title>
<link rel="stylesheet" href="<c:url value='/views/category-form.css' />">
</head>
<body>
	<h2>
		<c:if test="${category != null}">Chỉnh sửa</c:if>
		<c:if test="${category == null}">Thêm mới</c:if>
		Category
	</h2>

	<c:set var="currentUser" value="${sessionScope.currentUser}" />
	<c:set var="basePath" value="/admin/category" />
	<c:if test="${currentUser.roleid == 2}">
		<c:set var="basePath" value="/manager/category" />
	</c:if>
	<c:if test="${currentUser.roleid == 1}">
		<c:set var="basePath" value="/user/category" />
	</c:if>

	<form action="<c:url value='${basePath}/create' />" method="post"
		enctype="multipart/form-data">
		<c:if test="${category != null}">
			<input type="hidden" name="id" value="${category.cateId}" />
		</c:if>
		<label for="cateName">Tên Category:</label><br> <input
			type="text" id="cateName" name="cateName"
			value="${category.catename}" required><br> <br> <label
			for="icon">Icon:</label><br>
		<c:if test="${category != null && category.icon != null}">
			<img id="existing-icon"
				src="${pageContext.request.contextPath}/${category.icon}"
				alt="Current Icon" style="width: 50px; height: 50px;">
			<br>
			<input type="hidden" name="existingIcon" value="${category.icon}" />
		</c:if>
		<input type="file" id="icon" name="icon" accept="image/*"><br>
		<img id="icon-preview" class="icon-preview" src="#" alt="Icon Preview"><br>
		<br> <input type="submit" value="Lưu"> <a
			href="<c:url value='${basePath}/home' />">Hủy</a>
	</form>

	<script>
		const iconInput = document.getElementById('icon');
		const iconPreview = document.getElementById('icon-preview');
		const existingIcon = document.getElementById('existing-icon');

		iconInput.addEventListener('change', function() {
			const file = this.files[0];
			if (file) {
				// Ẩn hình ảnh hiện tại (nếu có)
				if (existingIcon) {
					existingIcon.style.display = 'none';
				}
				iconPreview.style.display = 'block';
				iconPreview.src = URL.createObjectURL(file);
			} else {
				iconPreview.style.display = 'none';
				iconPreview.src = '';
			}
		});
	</script>
</body>
</html>
