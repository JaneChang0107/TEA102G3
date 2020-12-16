<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.productPicture.model.ProductPictureVO" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>
	<h1>��s���</h1>
	<c:if test="${!errors.isEmpty()}">
			<c:forEach var="error" items="${errors}">
				<p style="color:red">${error}</p>
			</c:forEach>
	</c:if>
	<form action="<%= request.getContextPath() %>/ProductPictureServlet" method="post" enctype="multipart/form-data">
		<div>
			<label>�ӫ~�Ϥ�</label>
			<input type="text" name="ppid" value="${ppVO.pp_id}" readonly="readonly">
		</div>
		<div>
			<label>�ӫ~</label>
			<input type="text" name="pid" value="${ppVO.p_id}" readonly="readonly">
		</div>
		<div>
			<input type="file" name="picture" accept="image/*">
		</div>
		<div>
			<input type="hidden" name="action" value="update">
			<input type="submit" value="��s">	
		</div>
	</form>

</body>
</html>