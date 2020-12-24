<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ProductDetail</title>
	
		<style>
			p#productName{
				font-size: 60px;
			}		
		</style>
	</head>
	<body>
		<div id="pImg">
	<jsp:useBean id="ppService" scope="page" class="com.productPicture.model.ProductPictureService"></jsp:useBean>
		<c:forEach var="ppVO" items="${ppService.findByProduct(pVO.p_id)}">
			<img src="<%= request.getContextPath() %>/ShowPicture?type=pp&id=${ppVO.pp_id}" class="productImg">
		</c:forEach>
		</div>
		<div id="pInfo">
			<span>商品名稱:</span>
			<p id="productName">${pVO.p_name}</p>
			<span>商品價格:</span>
			<p id="productPrice">${pVO.p_price}</p>
			<span>商品數量:</span>
			<p id="productCount">${pVO.p_count}</p>
		</div>
		<div id="pDetail">
			<span>商品簡介:</span>
			${pVO.p_detail}
		</div>
	</body>
</html>