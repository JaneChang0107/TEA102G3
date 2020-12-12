<%@page import="jdk.internal.misc.FileSystemOption"%>
<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.productType.model.*" %>

<% ProductTypeVO ptVO = (ProductTypeVO) request.getAttribute("ptVO"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>updateProductType</title>
</head>
<body>

	<c:if test="${!error.isEmpty()}">
		<div style="color:red">
			<c:forEach var="message" items="${error}">
				<p>${message}</p>
			</c:forEach>
		</div>
	</c:if>
	<% System.out.println(ptVO); %>
	<form action="<%= request.getContextPath() %>/ProductTypeServlet" method="post">
		<div>
			<label>平台名稱:</label>
			<input type="text" name="platform" value="<%= (ptVO != null) ? ptVO.getPt_platform() : "" %>">
		</div>
		
		<div>
			<label>商品類型:</label>
			<input type="text" name="kind" value="<%= (ptVO != null) ? ptVO.getPt_kind() : "" %>">
		</div>
		<div>
			<input type="hidden" name="action" value="update">
			<input type="submit" value="送出">
		</div>
	</form>


</body>
</html>