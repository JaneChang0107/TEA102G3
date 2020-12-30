<%@page import="com.orderdetail.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>

<%
	OrderdetailVO orderdetailVO=(OrderdetailVO) request.getAttribute("orderdetailVO");
%>

<html>
<head>
<meta charset="BIG5">
<title>list one</title>
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
<body>

<table id="table-1">
	<tr><td>
		 <h3>資料 - ListOneOrderlist.jsp</h3>
		 <h4><a href="<%= request.getContextPath() %>/Back_end/OrderDetail/select_page.jsp"><img src="<%= request.getContextPath() %>/Back_end/images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	
	</td></tr>
</table>
<table>
	<tr>
		<th>流水號</th>
		<th>訂單編號</th>
		<th>商品編號</th>
		<th>產品數量</th>
		
	</tr>
	<tr>   
		<td><%=orderdetailVO.getOd_id()%></td>
		<td><%=orderdetailVO.getO_id()%></td>
		
		<td><%=orderdetailVO.getP_id()%></td>
		<td><%=orderdetailVO.getOd_count()%></td>
	</tr>
</table>




</body>
</html>