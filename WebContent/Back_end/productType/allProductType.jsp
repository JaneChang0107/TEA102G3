<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.productType.model.*" %>
<%@ page import="java.util.*" %>

<%
	ProductTypeService ptService = new ProductTypeService();
	List<ProductTypeVO> list = ptService.getAll();	
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<style>
	table, tr, td, th{
		border: 1px solid black;
	}

</style>
</head>
<body>

	<table>
		<tr>
			<th>編號</th>
			<th>平台</th>
			<th>種類</th>
			<th>修改</th>
			<th>刪除</th>
		</tr>
		<c:forEach var="ptVO" items="${list}">
		<tr>
			<td>${ptVO.pt_id}</td>
			<td>${ptVO.pt_platform}</td>
			<td>${ptVO.pt_kind}</td>
			<td>
				<form action="<%= request.getContextPath() %>/ProductTypeServlet" method="post">
					<input type="hidden" name="action" value="updateOne">
					<input type="hidden" name="pt_id" value="${ptVO.pt_id}">
					<input type="submit" value="修改">
				</form>
			</td>
			<td>
				<form action="<%= request.getContextPath() %>/ProductTypeServlet" method="post">
					<input type="hidden" name="action" value="delete">
					<input type="hidden" name="pt_id" value="${ptVO.pt_id}">
					<input type="submit" value="刪除">
				</form>
			</td>
		</tr>
		</c:forEach>
	</table>
	<a href="<%= request.getContextPath() %>/Back_end/productType/oneProductType.jsp">滾回單一查詢</a>

</body>
</html>