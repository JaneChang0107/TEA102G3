<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>showProduct</title>
<style>
	table, tr, th, td{
		border: 1px solid black;
	}
	img.showImg{
		width: 200px;
	}
</style>
</head>
<body>
<jsp:useBean id="ppService" scope="page" class="com.productPicture.model.ProductPictureService"></jsp:useBean>
	<table>
		<tr>
			<th>�ӫ~�s��</th>
			<th>�ӫ~�W��</th>
			<th>�ӫ~����</th>
			<th>�ӫ~�Բ�</th>
			<th>�ӫ~����</th>
			<th>�ӫ~�ƶq</th>
			<th>�ӫ~�s�W�ɶ�</th>
			<th>�ӫ~�ק�ɶ�</th>
			<th>�ӫ~���A</th>
			<th>��a</th>
			<th>�Ϥ�</th>
			
			<th>�R��</th>
		</tr>
	<c:forEach var="pVO" items="${pVOs}">		
		<tr>
			<td>${pVO.p_id}</td>
			<td>${pVO.p_name}</td>
			<td>${pVO.p_price}</td>
			<td>${pVO.p_detail}</td>
			<td>${pVO.pt_id}</td>
			<td>${pVO.p_count}</td>
			<td>${pVO.p_addDateSec}</td>
			<td>${pVO.p_reviseDateSec}</td>
			<td>${pVO.p_status}</td>
			<td>${pVO.m_id}</td>
			<td>
		<c:forEach var="ppVO" items="${ppService.findProductPicture(pVO.p_id)}">
			<img src="<%= request.getContextPath() %>/ShowPicture?type=pp&id=${ppVO.pp_id}" class="showImg">
		</c:forEach>
			</td>
			
			<td>
				<form action="<%= request.getContextPath() %>/ProductServlet" method="post">
					<input type="hidden" name="pid" value="${pVO.p_id}">
					<input type="hidden" name="action" value="delete">
					<input type="submit" value="�R��">
				</form>
			</td>
		</tr>
	</c:forEach>
	</table>
</body>
</html>