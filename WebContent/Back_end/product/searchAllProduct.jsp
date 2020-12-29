<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.product.model.ProductVO" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>oneProduct</title>
</head>
<body>
<c:if test="${!errors.isEmpty()}">
	<c:forEach var="error" items="${errors}">
		<p style="color:red">${error}</p>
	</c:forEach>
</c:if>
<jsp:useBean id="ptService" scope="page" class="com.productType.model.ProductTypeService"></jsp:useBean>
	<h1>搜尋商品</h1>
	<form action="<%= request.getContextPath() %>/ProductServlet" method="post">
		<div>
			<label>商品種類</label>
			<select name="type">
				<option value="no"></option>
			<c:forEach var="ptVO" items="${ptService.all}">				
				<option value="${ptVO.pt_id}">${ptVO.pt_platform} ${ptVO.pt_kind}</option>
			</c:forEach>
			</select>
		</div>
		<div>
			<label>商品名稱</label>
			<input type="text" name="name">
		</div>
		<input type="hidden" name="action" value="showAll">
		<input type="submit">
	</form>

</body>
</html>