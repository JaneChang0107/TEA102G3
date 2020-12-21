<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
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
			<th>${pVO.p_id}</th>
			<th>${pVO.p_name}</th>
			<th>${pVO.p_price}</th>
			<th>${pVO.p_detail}</th>
			<th>${pVO.pt_id}</th>
			<th>${pVO.p_count}</th>
			<th>${pVO.p_addDateSec}</th>
			<th>${pVO.p_reviseDateSec}</th>
			<th>${pVO.p_status}</th>
			<th>${pVO.m_id}</th>
			<th>
		<c:forEach var="ppVO" items="${ppService.findProductPicture(pVO.p_id)}">
			<img src="<%= request.getContextPath() %>/ShowPicture?type=pp&id=${ppVO.pp_id}" class="showImg">
		</c:forEach>
			</th>
		</tr>
	</c:forEach>
	</table>
</body>
</html>