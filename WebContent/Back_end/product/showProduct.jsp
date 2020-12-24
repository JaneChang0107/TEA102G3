<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
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
			<th>商品編號</th>
			<th>商品名稱</th>
			<th>商品價格</th>
			<th>商品詳細</th>
			<th>商品種類</th>
			<th>商品數量</th>
			<th>商品新增時間</th>
			<th>商品修改時間</th>
			<th>商品狀態</th>
			<th>賣家</th>
			<th>圖片</th>
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
			<td>
				<form action="<%= request.getContextPath() %>/ProductServlet" method="post">
					<input type="hidden" name="pid" value="${pVO.p_id}">
					<input type="hidden" name="action" value="updateOne">
					<input type="submit" value="修改">
				</form>
			</td>
			<td>
				<form action="<%= request.getContextPath() %>/ProductServlet" method="post">
					<input type="hidden" name="pid" value="${pVO.p_id}">
					<input type="hidden" name="action" value="delete">
					<input type="submit" value="刪除">
				</form>
			</td>
		</tr>
	</c:forEach>
	</table>
</body>
</html>