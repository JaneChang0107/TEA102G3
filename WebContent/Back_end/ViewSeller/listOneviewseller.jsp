<%@page import="com.viewseller.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>

<%
ViewsellerVO viewsellerVO=(ViewsellerVO) request.getAttribute("viewsellerVO");
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
		 <h4><a href="<%= request.getContextPath() %>/back_end/ViewSeller/select_page.jsp"><img src="<%= request.getContextPath() %>/back_end/images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>
<table>
	<tr>
		<th>評價編號</th>
		<th>訂單編號</th>
		<th>評價的人</th>
		<th>會員</th>
		<th>評價好壞</th>
		<th>評論</th>
		<th>時間</th>
	</tr>
	<tr>
		<td><%=viewsellerVO.getV_id()%></td>
		<td><%=viewsellerVO.getO_id()%></td>
		<td><%=viewsellerVO.getM_buyid()%></td>
		<td><%=viewsellerVO.getM_sellid()%></td>
		<td><%=viewsellerVO.getV_gb()%></td>
		<td><%=viewsellerVO.getV_comment()%></td>
		<td><%=viewsellerVO.getV_date()%></td>
		
	</tr>
</table>

</body>
</html>