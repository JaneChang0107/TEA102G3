<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.rentpicture.model.*"%>
<%@ page import="com.rent.model.*"%>

<%
	RentPictureVO rentpictureVO = (RentPictureVO) request.getAttribute("rentpictureVO"); // EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
	RentVO rentVO = (RentVO) request.getAttribute("rentVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>員工資料修改 - update_emp_input.jsp</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}

/* Selects any <input> element that is read-only */
/* Supported in Firefox with a prefix */
input:-moz-read-only {
	background-color: #ccc;
	color: #636363;
}

/* Supported in Blink/WebKit/Edge without a prefix */
input:read-only {
	background-color: #ccc;
	color: #636363;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>出租品圖片資料修改 - update_rentpicture_input.jsp</h3>
				<h4>
					<a
						href="<%=request.getContextPath()%>/Back_end/RentPicture/index_rentpicture.jsp"><img
						src="images/back1.png" width="40" height="40" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料修改:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/RentPictureServlet" name="form1"
		enctype="multipart/form-data">
		<table>

			<tr>
				<td>出租品圖片編號:</td>
				<td><%=rentpictureVO.getRp_id()%></td>
			</tr>
			<tr>
				<td>上傳圖片預覽:</td>
				<td><img id="blah" height="200" width="200" /></td>
			</tr>
			<tr>
				<td>出租品圖片:</td>
				<td><input type="File" name="rp_picture" id="imgInp" /></td>
			</tr>

			<tr>
				<td>出租品編號:</td>
				<td><input type="TEXT" name="r_id" size="45"
					value="<%=rentpictureVO.getR_id()%>" /></td>
			</tr>
		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="rp_id" value="<%=rentpictureVO.getRp_id()%>">
		<input type="hidden" name="r_id" value="<%=rentpictureVO.getR_id()%>">
		<input type="submit" value="送出修改">
	</FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
	$(function() {
		$("#imgInp").change(function() {
			if (this.files && this.files[0]) {
				var reader = new FileReader();

				reader.onload = function(e) {
					$('#blah').attr('src', e.target.result);
				}

				reader.readAsDataURL(this.files[0]);
			}
		});
	});
</script>
</html>