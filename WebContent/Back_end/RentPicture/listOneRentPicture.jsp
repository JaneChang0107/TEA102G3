<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.rentpicture.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
	RentPictureVO rentpictureVO = (RentPictureVO) request.getAttribute("rentpictureVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
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
				<h3>�X���~��� - ListOneEmp.jsp</h3>
				<h4>
					<a
						href="<%=request.getContextPath()%>/Back_end/RentPicture/index_rentpicture.jsp">
						<img src="<%=request.getContextPath() %>/images/back1.png" width="40" weight="40" border="0">�^����
					</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
		<tr>
			<th>�X���~�Ϥ�ID</th>
			<th>�X���~�Ϥ�</th>
			<th>�X���~ID</th>
		</tr>

		<tr>
		<tr>
			<td><%=rentpictureVO.getRp_id()%></td>
			<td><img
				src="data:image/jpg;base64,<%=rentpictureVO.getRp_picture2()%>"
				width="200" height="150" /></td>
			<td><%=rentpictureVO.getR_id()%></td>


		</tr>
	</table>

</body>
</html>