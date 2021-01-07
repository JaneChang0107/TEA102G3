<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="redis.clients.jedis.Jedis"%>
<%@page import="java.util.List"%>
<%@page import="com.orderdetail.model.OrderdetailVO"%>
<%@page import="com.orderdetail.model.OrderdetailService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%

%>


<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<title>YuXiKun</title>


<style>
marquee {
	width: 1000px;
}
body {
	background:#F5D2CD;
	height: 100%;
}

#sellerbar1,#sellerbar2,#sellerbar3,#sellerbar4{
    padding: 50px 110px;
    font-size: 50px;
    margin: 30px;
    margin-left: 60px;
    border-radius: 5%;
    
}

</style>

</head>

<body style="background:#F5D2CD;
	height: 100%;">

<h2><b><a href="http://localhost:8081/TEA102G3/Front_end/index_Seller.jsp" style="padding-left: 20px;">遊戲堃|賣家中心</a></b></h2>

<button type="button" class="btn btn-primary" id="sellerbar1">訂單管理</button>
<button type="button" class="btn btn-success" id="sellerbar2">商品管理</button><br>
<button type="button" class="btn btn-danger" id="sellerbar3">財務管理</button>
<button type="button" class="btn btn-info" id="sellerbar4">賣場管理</button>


</body>

</html>