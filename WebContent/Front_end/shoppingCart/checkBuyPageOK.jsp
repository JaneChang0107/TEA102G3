<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.member.model.*"%>
<%@page import="java.util.*"%>
<%@page import="com.orderlist.model.*"%>
<%@page import="com.orderdetail.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%!int size;%>
<%
	String m_id = session.getAttribute("loginId").toString();
	OrderlistService orderlistSvc = new OrderlistService();
	OrderdetailService orderdetainSvc = new OrderdetailService();
	
	List<OrderlistVO> list = orderlistSvc.findByMember(m_id);
	size = list.size();
	pageContext.setAttribute("list", list);
	OrderlistVO showlist = list.get(size - 1);
	String o_id = showlist.getO_id();
	List<OrderdetailVO> orderDetailList = orderdetainSvc.getDetailByOrder(o_id);
	String o_transport = showlist.getO_transport();
	switch (o_transport) {
		case "0" :
			o_transport = "黑貓宅急便";
			break;
		case "1" :
			o_transport = "7-11";
			break;
		case "2" :
			o_transport = "全家";
			break;
		case "3" :
			o_transport = "OK mart";
			break;
		case "4" :
			o_transport = "萊爾富";
			break;
		case "5" :
			o_transport = "郵局";
			break;
	}

	Timestamp o_date = showlist.getO_date();
	Integer o_total = showlist.getO_total();
%>



<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>結帳完成</title>
<style>
* {
	border: 0px solid gray;
}

.mybody {
	background-color: #E3F8F6;
}

.content {
	margin: 10px auto;
	background-color: white;
	width: 1000px;
	height: 300px;
	/* 	border: solid 1px */
}

.buttonarea {
	margin: 10px auto;
	background-color:;
	width: 1000px;
	height: 100px;
	/* 	border: solid 1px */
}

.table1 {
	margin: 0px auto;
	background-color: white;
	width: 800px;
	height: 50px;
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

.col {
	text-align: center;
	font-size: 25px;
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
		<h2 class="title">結帳完成</h2>
	</div>
	<div class="content">

		<table class="table1">
			<tr>
				<td class="col">您的訂單已成立</td>
			</tr>
			<tr>
				<td class="col">訂單編號:<%=o_id%></td>
			</tr>
			<tr>
				<td class="col">訂單時間:<%=o_date%></td>
			</tr>
			<tr>
				<td class="col">感謝您的購買</td>
			</tr>
		</table>

	</div>
	<div class="buttonarea">
		<button type="button" class="button1">會員專區</button>
		<button type="button" class="button2"  onclick="javascript:location.href='<%=request.getContextPath()%>/Front_end/index.jsp'">回首頁</button>
	</div>
	<div class="footer">
		<jsp:include page="../footer.jsp"></jsp:include>
	</div>

	<script>
		
	</script>

</body>



</html>