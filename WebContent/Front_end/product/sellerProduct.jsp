<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	session.setAttribute("mid", "M00001");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
	div#sellerProduct{
		position: relative;
		width: 50%;
		left: 50%;
		transform: translateX(-50%);
	}
	h1{
		left: 50%;
		position: relative;
		transform: translate(-50%);
		width: 50%;
	}
	table{
		border: 1px solid black;
	}
	
</style>
</head>
<body>
	<div class="header">
		<jsp:include page="/Front_end/header.jsp"></jsp:include>
	</div>
	
	<h1>我的商品</h1>
	<% session.setAttribute("mid", "M00001"); %>
	<div id="sellerProduct">
		<table id="products">

		</table>
	</div>



	<div class="footer">
		<jsp:include page="/Front_end/footer.jsp"></jsp:include>
	</div>
	<input type="hidden" id="contextPath" value="<%= request.getContextPath() %>">
	<script src="<%= request.getContextPath() %>/vendors/jquery/jquery-3.5.1.min.js"></script>
	<script src="<%= request.getContextPath() %>/vendors/popper/popper.min.js"></script>
    <script src="<%= request.getContextPath() %>/vendors/bootstrap-4.5.3-dist/js/bootstrap.min.js"></script>
    <script src="<%= request.getContextPath() %>/Front_end/product/js/sellerProduct.js"></script>
</body>
</html>