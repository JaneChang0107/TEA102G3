<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.rent.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
	RentVO rentVO = (RentVO) request.getAttribute("rentVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>

<html>
<head>
<title>���u��� - listOneEmp.jsp</title>

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
		<tr>
			<td>
				<h3>���u��� - ListOneEmp.jsp</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/Back_end/Rent/index.jsp"><img src="images/back1.png" width="100"
						height="32" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
		<tr>
			<th>�X���~�s��</th>
			<th>�X���~����</th>
			<th>�X���~�W��</th>
			<th>���O�s��</th>
			<th>�ӫ~�y�z</th>
			<th>�f�p</th>
			<th>�f�����A</th>
			<th>�X���~����</th>
			<th>�s�W���</th>
			<th>�W���ק�</th>
			<th>�s�W��</th>
			<th>�ק��</th>
			<th>�Ҧb����</th>
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