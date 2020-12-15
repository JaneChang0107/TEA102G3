<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.rentpicture.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
	RentPictureVO rpv = new RentPictureVO();
	RentPictureService rpSvc = new RentPictureService();
	List<RentPictureVO> list = rpSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<html>
<head>
<title>�Ҧ����u��� - xxx.jsp</title>

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
	width: 800px;
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

	<h4>�����m�߱ĥ� EL ���g�k����:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>�Ҧ����u��� - listAllEmp.jsp</h3>
				<h4>
					<a
						href="<%=request.getContextPath()%>/Back_end/RentPicture/index_rentpicture.jsp">
						<img src="images/back1.png" width="100" height="32" border="0">�^����
					</a>
				</h4>
			</td>
		</tr>
	</table>

	<!--  ���~��C  -->
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table>
		<tr>
			<th>�X���~�Ϥ��s��</th>
			<th>�X���~�Ϥ�</th>
			<th>�X���~�s��</th>

		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="RentPictureVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${RentPictureVO.rp_id}</td>
				<td><img
					src="data:image/jpg;base64,${RentPictureVO.rp_picture2}"
					width="200" height="150" /></td>
				<td>${RentPictureVO.r_id}</td>

				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath() %>/RentPictureServlet"
						style="margin-bottom: 0px;">
						<input type="submit" value="�ק�"> <input type="hidden"
							name="rp_id" value="${RentPictureVO.rp_id}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath() %>/RentPictureServlet"
						style="margin-bottom: 0px;">
						<input type="submit" value="�R��"> <input type="hidden"
							name="rp_id" value="${RentPictureVO.rp_id}"> <input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>

</body>
</html>