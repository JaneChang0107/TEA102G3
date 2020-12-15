<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.rent.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	RentVO rentVO = (RentVO) request.getAttribute("rentVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>員工資料 - listOneEmp.jsp</title>

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
		<tr>
			<td>
				<h3>員工資料 - ListOneEmp.jsp</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/Back_end/Rent/index.jsp"><img src="images/back1.png" width="100"
						height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
		<tr>
			<th>出租品編號</th>
			<th>出租品種類</th>
			<th>出租品名稱</th>
			<th>類別編號</th>
			<th>商品描述</th>
			<th>貨況</th>
			<th>貨物狀態</th>
			<th>出租品價格</th>
			<th>新增日期</th>
			<th>上次修改</th>
			<th>新增者</th>
			<th>修改者</th>
			<th>所在門市</th>
		</tr>
		
		<tr>
		<tr>
			<td><%=rentVO.getR_id()%></td>
			<td><%=rentVO.getR_type()%></td>
			<td><%=rentVO.getR_name()%></td>
			<td><%=rentVO.getPt_id()%></td>
			<td><%=rentVO.getR_describe()%></td>
			<td><%=rentVO.getR_situation()%></td>
			<td><%=rentVO.getR_status()%></td>
			<td><%=rentVO.getR_price()%></td>
			<td><%=rentVO.getR_adddate()%></td>
			<td><%=rentVO.getR_revisedate()%></td>
			<td><%=rentVO.getE_addid()%></td>
			<td><%=rentVO.getE_editorid()%></td>
			<td><%=rentVO.getSt_id()%></td>
			
		</tr>
	</table>

</body>
</html>