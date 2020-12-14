<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="pService" scope="page" class="com.product.model.ProductService" />
<jsp:useBean id="ptService" scope="page" class="com.productType.model.ProductTypeService" />
	<form action="" method="post">
	<h1>��@�j�M</h1>
	<select name="pid">
		<option>�п��</option>
		<c:forEach var="pVO" items="${pService.all}">
			<option value="${pVO.p_id}">${pVO.p_name} ${ptService.getOneProductType(pVO.pt_id).getPt_platform()}</option>
		</c:forEach>
	</select>
	</form>
	// xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
	<c:if test="${ppVO != null}">
		<table>
			<tr>
				<th>�s��</th>
				<th>�W��</th>
				<th>�Ϥ�</th>
				
			</tr>
			<tr>
				<td>${ppVO.pp_id}</td>
				<td>${pService.}</td>
				<td>${ppVO.pt_kind}</td>
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