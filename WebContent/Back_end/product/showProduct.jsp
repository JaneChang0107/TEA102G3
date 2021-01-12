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
			<select id="selectStatus">
				<option id="all" value="all">全部</option>
				<option id="check" value="check">待審核</option>
				<option id="onSell" value="onSell">上架中</option>
				<option id="notSell" value="notSell">下架中</option>
				<option id="selled" value="selled">已出售</option>
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