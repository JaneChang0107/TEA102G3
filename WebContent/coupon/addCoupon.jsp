<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.coupon.model.*"%>

<%
	CouponVO couponVO = (CouponVO) request.getAttribute("couponVO");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>新增優惠券</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>
</head>
<body bgcolor='white'>
	<table id="table-1">
		<tr>
			<td>
				<h3>優惠券新增 - addCoupon.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="select_page.jsp"><img src="images/main.png"
						width="100" height="90" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>
<body>
	<h3>資料新增:</h3>

	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/coupon/CouponServlet"
		name="form1">
		<table>
			<tr>
				<td>優惠券代碼:<font color=red><b>*</b></font></td>
				<td><input type="text" name="co_code" size="40"
					placeholder="請填入代碼"
					value="<%=(couponVO == null) ? "" : couponVO.getCo_code()%>" /></td>
			</tr>
			<tr>
				<td>金額:<font color=red><b>*</b></font></td>
				<td><input type="text" name="co_amount" size="40"
					placeholder="請填入金額"
					value="<%=(couponVO == null) ? "" : couponVO.getCo_amount()%>" /></td>
			</tr>
			<tr>
				<td>起始日期:<font color=red><b>*</b></font></td>
				<td><input type="text" name="co_start" id="start_date"
					size="40" placeholder="請選擇日期" /></td>
				<%--value="<%=(couponVO == null) ? "" : couponVO.getCo_start()%>" /></td> --%>
			</tr>

			<tr>
				<td>截止日期:<font color=red><b>*</b></font></td>
				<td><input type="text" name="co_expire" id="end_date" size="40"
					placeholder="請選擇日期" /></td>
				<%--value="<%=(couponVO == null) ? "" : couponVO.getCo_expire()%>" /></td> --%>
			</tr>
		
			<tr>
				<td>票券狀態:<font color=red><b>*</b></font></td>
				<td><input type="radio" name="co_status" value="未使用">未使用 
				<input type="radio" name="co_status" value="已使用">已使用
				<input type="radio" name="co_status" value="已逾期">已逾期</td>	
				
<!-- 				<td><input type="text" name="co_status" size="40" -->
<!-- 				placeholder="請選擇狀態" -->
<%-- 				value="<%=(couponVO == null) ? "" : couponVO.getCo_status()%>" /></td> --%>
			</tr>
			<tr>
				<td>會員ID:<font color=red><b>*</b></font></td>
				<td><input type="text" name="m_id" size="40"
					placeholder="請填入會員id"
					value="<%=(couponVO == null) ? "" : couponVO.getM_id()%>" /></td>
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
	</FORM>
</body>
<!-- =====Date time picker設定======== -->
<%
	java.sql.Timestamp co_start = null;
	java.sql.Timestamp co_expire = null;

	try {
		co_start = couponVO.getCo_start();
	} catch (Exception e) {
		co_start = new java.sql.Timestamp(System.currentTimeMillis());
	}
	try {
		co_expire = couponVO.getCo_expire();
	} catch (Exception e) {
		co_expire = new java.sql.Timestamp(System.currentTimeMillis());
	}
%>

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>
<script>
	$.datetimepicker.setLocale('zh'); // kr ko ja en
	$(function() {
		$('#start_date').datetimepicker({
			theme : '', //theme: 'dark',
			timepicker : true, //timepicker: false,
			step : 1, //step: 60 (這是timepicker的預設間隔60分鐘)
			format : 'Y-m-d H:i:s',
			value : new Date(),
			//disabledDates:    ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
			//startDate:	        '2017/07/10',  // 起始日
			minDate : '-1970-01-01', // 去除今日(不含)之前
		//maxDate:           '+1970-01-01'  // 去除今日(不含)之後
		});

		$('#end_date').datetimepicker({
			theme : '', //theme: 'dark',
			timepicker : true, //timepicker: false,
			step : 1, //step: 60 (這是timepicker的預設間隔60分鐘)
			format : 'Y-m-d H:i:s',
			value : new Date(),
			//disabledDates:    ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
			//startDate:	        '2017/07/10',  // 起始日
			minDate : '+1970-01-02', // 去除今日(不含)之前
		//maxDate:           '+1970-01-01'  // 去除今日(不含)之後
		});
	});
</script>
</html>
