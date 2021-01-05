<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.productType.model.*"%>
<%@ page import="com.member.model.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>您的購物車-我要買</title>
<style>
* {
	border: 0px solid gray;
}

.mybody {
	background-color: #E3F8F6;
}

.content {
	margin: 20px auto;
	background-color: white;
	width: 1000px;
	height: 250px;
	/* 	border: solid 1px */
}

.null {
	margin: 20px auto;
	width: 1000px;
	height: 250px;
	/* 	border: solid 1px */
}

.buttonarea {
	margin: 10px auto;
	background-color:;
	width: 1000px;
	height: 100px;
	/* 	border: solid 1px */
}


.title {
	margin: 40px;
}

.button1 {
	background-color: white;
	height: 50px;
	width: 400px;
	border-radius: 10px;
}

.button2 {
	background-color: white;
	height: 50px;
	width: 400px;
	border-radius: 10px;
	float: right;
}

.button1:hover {
	background-color: #008CBA;
	color: white;
}

.button2:hover {
	background-color: #008CBA;
	color: white;
}

.footer{
    position: absoloute;
    bottom: 0px;
}

</style>

</head>
<body class="mybody">
	<link rel="stylesheet"
		href="../../vendors/bootstrap/css/bootstrap.min.css">

	<div class="header">
		<jsp:include page="../header.jsp"></jsp:include>
	</div>

	<div>
		<h2 class="title">您的購物車-我要買</h2>
		<hr>
		

	<div class="null"><tr><td><h3 align=center>您的購物車現在沒有商品</h3></td></tr></div>
	<div class="buttonarea"> 
	<button type="button" class="button1" onclick="history.back()">回上一頁</button>
	<input type ="button" class="button2" onclick="javascript:location.href='<%=request.getContextPath()%>/Front_end/index.jsp'" value="回首頁"></input>
	</div>

	<div class="footer">
		<jsp:include page="../footer.jsp"></jsp:include>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.js"
		integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
		crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha.6/js/bootstrap.min.js">
	</script>
	


</body>

</html>