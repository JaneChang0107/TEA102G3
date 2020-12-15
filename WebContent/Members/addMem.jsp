<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>


<%
	MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>新增會員</title>

<style>
table {
	width: 400px;
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
b{
     color: red;
}
</style>
</head>
<body>
	<h3>資料新增:</h3><b>*</b>會員必填欄位
     
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/MemberServlet" name="form1">
		<table>
			<tr>
				<td>電子郵件:<b>*</b></td>
				<td><input type="text" name="m_email" size="40"
					value="<%=(memberVO == null) ? "a123@yahoo.com" : memberVO.getM_email()%>" /></td>

			</tr>
			<tr>
				<td>密碼:<b>*</b></td>
				<td><input type="password" name="m_password" size="40"
					value="<%=(memberVO == null) ? "" : memberVO.getM_password()%>" /></td>
			</tr>
			<tr>
				<td>姓名:<b>*</b></td>
				<td><input type="text" name="m_name" size="40"
					value="<%=(memberVO == null) ? "MrJava" : memberVO.getM_name()%>" /></td>
			</tr>
			<tr>
				<td>性別:<b>*</b></td>
				<td>
				<input type="radio" name="m_gender" checked
				value="男">男 
				<input type="radio" name="m_gender" 
				value="女">女
				</td>
			</tr>
			<tr>
				<td>電話:<b>*</b></td>
				<td><input type="text" name="m_phone" size="40"
					value="<%=(memberVO == null) ? "0999999999" : memberVO.getM_phone()%>" /></td>
			</tr>
			<tr>
				<td>地址:<b>*</b></td>
				<td><input type="text" name="m_address" size="40"
					value="<%=(memberVO == null) ? "請填入地址" : memberVO.getM_address()%>" /></td>
			</tr>
			<tr>
				<td>生日:<b>*</b></td>
				<td><input name="m_birth" id="f_date1" type="text"></td>
			</tr>

		</table>
		<br> 
		<input type="hidden" name="action" value="insert"> 
		<input type="submit" value="送出新增"></FORM>
</body>


<!-- =====Date time picker設定======== -->
<%
	java.sql.Date m_birth = null;
	try {
		m_birth = memberVO.getM_birth();
	} catch (Exception e) {
		m_birth = new java.sql.Date(System.currentTimeMillis());
	}
%>

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
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value:   new Date(),
           maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
</script>
</html>
