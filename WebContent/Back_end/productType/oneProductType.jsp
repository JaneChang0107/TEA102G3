<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.productType.model.*" %>

<% ProductTypeVO ptVO = (ProductTypeVO) request.getAttribute("ptVO"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>one product type</title>
<style>
	table, tr, th, td{
		border: 1px solid black;
	}

</style>
</head>
<body>
	<h1>��@�d��</h1>
<jsp:useBean id="ptService" scope="page" class="com.productType.model.ProductTypeService" />
	<form action="<%= request.getContextPath() %>/ProductTypeServlet" method="post">
		<div>
			<label>�����s��</label>
			<select name="ptid">
				<option>�п��</option>
					<c:forEach var="ptid" items="${ptService.all}">
						<option value="${ptid.pt_id}">${ptid.pt_id}</option>
					</c:forEach>
			</select>
		</div>
		<div>
			<input type="hidden" name="action" value="getOne">
			<input type="submit" value="�j�M">
		</div>
	</form>
	<a href="<%= request.getContextPath() %>/Back_end/productType/allProductType.jsp">��������</a>
	<a href="<%= request.getContextPath() %>/Back_end/productType/addProductType.jsp">�s�W����</a>
	<c:if test="${ptVO != null}">
		<table>
			<tr>
				<th>�s��</th>
				<th>���x</th>
				<th>����</th>
				<th>�ק�</th>
				<th>�R��</th>
			</tr>
			<tr>
				<td>${ptVO.pt_id}</td>
				<td>${ptVO.pt_platform}</td>
				<td>${ptVO.pt_kind}</td>
				<td>
					<form action="<%= request.getContextPath() %>/ProductTypeServlet" method="post">
						<input type="hidden" name="action" value="updateOne">
						<input type="hidden" name="pt_id" value="${ptVO.pt_id}">
						<input type="submit" value="�ק�">
					</form>
				</td>
				<td>
					<form action="<%= request.getContextPath() %>/ProductTypeServlet" method="post">
						<input type="hidden" name="action" value="delete">
						<input type="hidden" name="pt_id" value="${ptVO.pt_id}">
						<input type="submit" value="�R��">
					</form>
				</td>
			</tr>
		</table>
	</c:if>
</body>
</html>