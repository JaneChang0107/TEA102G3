<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.store.model.*"%>

<%
	StoreVO storeVO = (StoreVO) request.getAttribute("storeVO");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>新增租賃門市</title>

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
				<h3>租賃門市新增 - addStore.jsp</h3>
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
	<h3>新增租賃門市:</h3>

	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/store/StoreServlet" name="form1">
		<table>
			<tr>
				<td>門市名稱:<font color=red><b>*</b></font></td>
				<td><input type="text" name="st_name" size="40"
					placeholder="門市名稱"
					value="<%=(storeVO == null) ? "" : storeVO.getSt_name()%>" /></td>
			</tr>
			<tr>
				<td>門市電話:<font color=red><b>*</b></font></td>
				<td><input type="text" name="st_tel" size="40"
					placeholder="門市電話"
					value="<%=(storeVO == null) ? "" : storeVO.getSt_tel()%>" /></td>
			</tr>
			<tr>
				<td>門市地址:<font color=red><b>*</b></font></td>
				<td><input type="text" name="st_address" size="40"
					placeholder="門市地址"
					value="<%=(storeVO == null) ? "" : storeVO.getSt_address()%>" /></td>
			</tr>
		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
	</FORM>
</body>

</html>
