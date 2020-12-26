<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.blacklist.model.*"%>

<%
	BlacklistVO blacklistVO = (BlacklistVO) request.getAttribute("blacklistVO"); //BlacklistServlet.java (Concroller) 存入req的blacklistVO物件 (包括幫忙取出的blacklistVO, 也包括輸入資料錯誤時的blacklistVO物件)
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>黑名單修改</title>

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
				<h3>黑名單修改 - update_Blacklist.jsp</h3>
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
	<h3>資料修改:</h3>

	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/blacklist/BlacklistServlet"
		name="form1">
		<table>
			<tr>
				<td>黑名單編號:</td>
				<td><%=blacklistVO.getBl_id()%></td>
			</tr>
			<tr>
				<td>封鎖方會員帳號:<font color=red><b>*</b></font></td>
				<td><input type="text" name="m_id" size="40"
					value="<%=blacklistVO.getM_id()%>" /></td>
			</tr>
			<tr>
				<td>被封鎖會員帳號:<font color=red><b>*</b></font></td>
				<td><input type="text" name="m_blackId" size="40"
					value="<%=blacklistVO.getM_blackId()%>" /></td>
			</tr>

		</table>
		<br> 
		<input type="hidden" name="action" value="update"> 
		<input type="hidden" name="bl_id" value="<%=blacklistVO.getBl_id()%>">
		<input type="submit" value="送出修改">
	</FORM>
</body>

</html>