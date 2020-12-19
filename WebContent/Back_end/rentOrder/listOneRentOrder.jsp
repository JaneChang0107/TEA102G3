<%@page import="com.rentOrder.model.RentOrderVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.rentOrder.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
RentOrderVO rentOrderVO = (RentOrderVO) request.getAttribute("rentOrderVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>員工資料 - listOneRentOrder.jsp</title>

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
		 <h3>員工資料 - ListOneRentOrder.jsp</h3>
		  <h4><a href="<%=request.getContextPath()%>/Back_end/rentOrder/select_page.jsp"><img src="<%=request.getContextPath()%>/Back_end/employee/images/1.png" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>出租單編號</th>
		<th>訂單日期</th>
		<th>訂單狀態</th>
		<th>門市id</th>
		<th>出租時間</th>
		<th>歸還時間</th>
		<th>押金</th>
		<th>總金額</th>
		<th>合約簽名</th>
		<th>坤幣</th>
		<th>會員</th>
	</tr>
	<tr>
		<td><%=rentOrderVO.getRo_id()%></td>
		<td><%=rentOrderVO.getRo_date()%></td>
		<td><%=rentOrderVO.getRo_status()%></td>
		<td><%=rentOrderVO.getSt_id()%></td>
		<td><%=rentOrderVO.getRo_outdate()%></td>
		<td><%=rentOrderVO.getRo_backdate()%></td>
		<td><%=rentOrderVO.getRo_deposit()%></td>
		<td><%=rentOrderVO.getRo_total()%></td>
		<td><%=rentOrderVO.getRo_sign()%></td>
		<td><%=rentOrderVO.getRo_pm()%></td>
		<td><%=rentOrderVO.getM_id()%></td>
	</tr>
</table>

</body>
</html>