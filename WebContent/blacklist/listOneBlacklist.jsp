<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.blacklist.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
BlacklistVO blacklistVO = (BlacklistVO) request.getAttribute("blacklistVO");
%>

<html>
<head>
<title>黑名單資料 - listOneBlacklist.jsp</title>

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
		 <h3>員工資料 - ListOneBlacklist.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/main.png" width="100" height="90" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>黑名單編號</th>
		<th>封鎖方會員編號</th>
		<th>被封鎖方會員編號</th>
	</tr>
	<tr>
		<td><%=blacklistVO.getBl_id()%></td>
		<td><%=blacklistVO.getM_id()%></td>
		<td><%=blacklistVO.getM_blackId()%></td>
	</tr>
</table>

</body>
</html>