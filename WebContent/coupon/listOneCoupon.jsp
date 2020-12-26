<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.coupon.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
CouponVO couponVO = (CouponVO) request.getAttribute("couponVO");
%>

<html>
<head>
<title>優惠券資料 - listOneCoupon.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>優惠券資料 - ListOneCoupon.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/main.png" width="100" height="90" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>優惠券編號</th>
		<th>代碼</th>
		<th>金額</th>
		<th>起始日期</th>
		<th>截止日期</th>
		<th>票券狀態</th>
		<th>會員ID</th>
	</tr>
	<tr>
			<td>${couponVO.co_id}</td>
			<td>${couponVO.co_code}</td>
			<td>${couponVO.co_amount}</td>
			<td><fmt:formatDate value="${couponVO.co_start}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td><fmt:formatDate value="${couponVO.co_expire}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td>${couponVO.co_status}</td>
			<td>${couponVO.m_id}</td>
	</tr>
</table>

</body>
</html>