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
	<div class="header">
		<jsp:include page="/Front_end/header.jsp"></jsp:include>
	</div>
	
	
		<div class="">
			<jsp:useBean id="ppService" scope="page" class="com.productPicture.model.ProductPictureService"></jsp:useBean>
			<div id="pImg">
			<c:forEach var="ppVO" items="${ppService.findProductPicture(pVO.p_id)}">
				<img src="<%= request.getContextPath() %>/ShowPicture?type=ppid&id=${ppVO.pp_id}" class="productImg">
			</c:forEach>
			</div>
			<div id="pInfo">
				<span>商品名稱:</span><p id="productName">${pVO.p_name}</p>
				<span>商品價格:</span><p id="productPrice">${pVO.p_price}</p>
				<span>商品數量:</span><p id="productCount">${pVO.p_count}</p>
			</div>
			<div id="pDetail">
				<span>商品簡介:</span>
				${pVO.p_detail}
			</div>
		</div>
		
		
		<div class="footer">
			<jsp:include page="/Front_end/footer.jsp"></jsp:include>
		</div>
	</body>
</html>