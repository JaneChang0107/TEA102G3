<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.employee.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
  EmployeeVO employeeVO = (EmployeeVO) request.getAttribute("employeeVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>

<html>
<head>
<title>���u��� - listOneEmployee.jsp</title>

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

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>���u��� - ListOneEmployee.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/Back_end/employee/select_page.jsp"><img src="<%=request.getContextPath()%>/Back_end/employee/images/1.png" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>���uid</th>
		<th>���u�K�X</th>
		<th>�����Ҧr��</th>
		<th>���u�m�W</th>
		<th>�ʧO</th>
		<th>�ͤ�</th>
		<th>���u�H�c</th>
		<th>���u�q��</th>
		<th>���u�a�}</th>
		<th>¾��</th>
		<th>���u���A</th>
		<th>����id</th>
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