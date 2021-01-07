<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>allProduct</title>
<style>
	form.check{
		display: inline;
	}
	input.checkBtn{
		height: 40px;
	}
</style>
</head>
<body>
	<div class="header">
		<jsp:include page="/Front_end/header.jsp"></jsp:include>
	</div>
	
	<div>
		<div>
			<select>
				<option id="all">全部</option>
				<option id="check">待審核</option>
				<option id="onSell">上架中</option>
				<option id="notSell">下架中</option>
				<option id="selled">已出售</option>
			</select>
		</div>
		
		<div id="allProduct">
			
		</div>
	</div>


	<div class="footer">
		<jsp:include page="/Front_end/footer.jsp"></jsp:include>
	</div>
	
    <script src="<%= request.getContextPath() %>/Back_end/product/js/allProduct.js"></script>
</body>
</body>
</html>