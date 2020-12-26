<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.store.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
StoreVO storeVO = (StoreVO) request.getAttribute("storeVO");
%>

<html>
<head>
<title>門市資料 - listOneStore.jsp</title>

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
		 <h3>員工資料 - ListOneStore.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/main.png" width="100" height="90" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>門市編號</th>
		<th>門市名稱</th>
		<th>門市電話</th>
		<th>門市地址</th>
	</tr>
	<tr>
		<td><%=storeVO.getSt_id()%></td>
		<td><%=storeVO.getSt_name()%></td>
		<td><%=storeVO.getSt_tel()%></td>
		<td><%=storeVO.getSt_address()%></td>
	</tr>
</table>

</body>
</html>