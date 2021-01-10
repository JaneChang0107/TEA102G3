<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Seller Product</title>

<style>
	div#sellerProduct{
		position: relative;
		width: 50%;
		height: 100%;
		left: 50%;
		transform: translateX(-50%);
	}
	h1{
		left: 50%;
		position: relative;
		transform: translate(-50%);
		width: 50%;
		text-align: center;
	}
</style>
</head>
<body>
	<div class="header">
		<jsp:include page="/Front_end/header.jsp"></jsp:include>
	</div>
	<div>
		<jsp:include page="../index_Seller_Buttongroup.jsp"></jsp:include>
	</div>
	
	
	<div id="sellerProduct">
		
	</div>



	<div class="footer">
		<jsp:include page="/Front_end/footer.jsp"></jsp:include>
	</div>
	
	
	
    <script src="<%= request.getContextPath() %>/Front_end/product/js/sellerProduct.js"></script>
</body>
</html>