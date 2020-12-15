<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.employee.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  EmployeeVO employeeVO = (EmployeeVO) request.getAttribute("employeeVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>員工資料 - listOneEmployee.jsp</title>

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
		 <h3>員工資料 - ListOneEmployee.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/Back_end/employee/select_page.jsp"><img src="<%=request.getContextPath()%>/Back_end/employee/images/1.png" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>員工id</th>
		<th>員工密碼</th>
		<th>身分證字號</th>
		<th>員工姓名</th>
		<th>性別</th>
		<th>生日</th>
		<th>員工信箱</th>
		<th>員工電話</th>
		<th>員工地址</th>
		<th>職稱</th>
		<th>員工狀態</th>
		<th>門市id</th>
	</tr>
	<tr>
		<td><%=employeeVO.getE_id()%></td>
		<td><%=employeeVO.getE_password()%></td>
		<td><%=employeeVO.getE_identity()%></td>
		<td><%=employeeVO.getE_name()%></td>
		<td><%=employeeVO.getE_gender()%></td>
		<td><%=employeeVO.getE_birth()%></td>
		<td><%=employeeVO.getE_email()%></td>
		<td><%=employeeVO.getE_phone()%></td>
		<td><%=employeeVO.getE_address()%></td>
		<td><%=employeeVO.getE_title()%></td>
		<td><%=employeeVO.getE_status()%></td>
		<td><%=employeeVO.getSt_id()%></td>
	</tr>
</table>

</body>
</html>