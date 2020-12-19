<%@page import="com.rentDetail.model.RentDetailVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.rentDetail.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  RentDetailVO rentDetailVO = (RentDetailVO) request.getAttribute("rentDetailVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
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
	<tr><td>
		 <h3>員工資料 - ListOneEmp.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/Back_end/rentDetail/select_page.jsp"><img src="<%=request.getContextPath()%>/Back_end/employee/images/1.png" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>流水號</th>
		<th>出租訂單編號</th>
		<th>出租單編號</th>
		<th>產品數量</th>

	</tr>
	<tr>
		<td><%=rentDetailVO.getRd_id()%></td>
		<td><%=rentDetailVO.getRo_id()%></td>
		<td><%=rentDetailVO.getR_id()%></td>
		<td><%=rentDetailVO.getRd_count()%></td>
		
	</tr>
</table>

</body>
</html>