<%@ page import="com.orderlist.model.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>


<%
	OrderlistVO orderlistVO=(OrderlistVO) request.getAttribute("orderlistVO");
%>
<!-- <!DOCTYPE html> -->
<html>
<head>
<meta charset="Big5">
<title>orderlist </title>
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
		 <h3>��� - ListOneOrderlist.jsp</h3>
		 <h4><a href="<%= request.getContextPath() %>/Back_end/orderlist/select_page.jsp"><img src="<%= request.getContextPath() %>/Back_end/images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>
<table>
	<tr>
		<th>�q��s��</th>
		<th>�q����</th>
		<th>�q�檬�A</th>
		<th>�X�f���</th>
		<th>���f���</th>
		<th>�������</th>
		<th>�B�e�覡</th>
		<th>�H�e�a�}</th>
		<th>�`���B</th>
		<th>��</th>
		<th>�R�a</th>
	</tr>
	<tr>
		<td><%=orderlistVO.getO_id()%></td>
		<td><%=orderlistVO.getO_date()%></td>
		
		<td><%=orderlistVO.getO_status()%></td>
		<td><%=orderlistVO.getO_shipdate()%></td>
		<td><%=orderlistVO.getO_deceiptdate()%></td>
		<td><%=orderlistVO.getO_finishdate()%></td>
		<td><%=orderlistVO.getO_transport()%></td>
		
		<td><%=orderlistVO.getO_address()%></td>
		<td><%=orderlistVO.getO_total()%></td>
		<td><%=orderlistVO.getO_pm()%></td>
		<td><%=orderlistVO.getM_id()%></td>
	</tr>
</table>

</body>
</html>