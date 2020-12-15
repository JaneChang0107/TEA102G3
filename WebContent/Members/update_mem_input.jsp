<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>


<%
	MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>修改會員</title>

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

b {
	color: red;
}

a {
	color: orange;
}
</style>
</head>
<body>
	<h3>會員修改:</h3>


	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/member/MemberServlet"
		name="form1">
		<table>
		    <tr>
		       <td>會員id:<font color=red><b>*</b></font></td>
		       <td><%=memberVO.getM_id() %></td>
		    </tr>
		
			<tr>
				<td>電子郵件:<b>*</b></td>
				<td><input type="text" name="m_email" size="40"
					value="<%=memberVO.getM_email()%>" /></td>

			</tr>
			<tr>
				<td>密碼:<b>*</b></td>
				<td><input type="password" name="m_password" size="40"
					value="<%=memberVO.getM_password()%>" /></td>
			</tr>
			<tr>
				<td>姓名:<b>*</b></td>
				<td><input type="text" name="m_name" size="40"
					value="<%=memberVO.getM_name()%>" /></td>
			</tr>
			<tr>
				<td>性別:<b>*</b></td>
				<td><input type="radio" name="m_gender" value="男" <%=memberVO.getM_gender().equals("男") ? "checked" : "" %> >男
					<input type="radio" name="m_gender" value="女" <%=memberVO.getM_gender().equals("女") ? "checked" : "" %>>女</td>
			</tr>
			<tr>
				<td>電話:<b>*</b></td>
				<td><input type="text" name="m_phone" size="40"
					value="<%=memberVO.getM_phone()%>" /></td>
			</tr>
			<tr>
				<td>地址:<b>*</b></td>
				<td><input type="text" name="m_address" size="40"
					value="<%=memberVO.getM_address()%>" /></td>
			</tr>
			<tr>
				<td>生日:<b>*</b></td>
				<td><input name="m_birth" id="f_date1" type="text"></td>
			</tr>
			<tr>
				<td>大頭照:</td>
				<td><input type="file" name="m_headpic"
					value="<%=memberVO.getM_headpic()%>" /></td>
			</tr>
			<tr>
				<td>身分證字號:<a>*</a></td>
				<td><input type="text" name="m_identity"
					value="<%=memberVO.getM_identity()%>"></td>
			</tr>
			<tr>
				<td>身分證照片:<a>*</a></td>
				<td><input type="file" name="m_id_pic"
					value="<%=memberVO.getM_id_pic()%>" /></td>
			</tr>
			<tr>
				<td>銀行帳戶:<a>*</a></td>
				<td><input type="text" name="m_account"
					value="<%=memberVO.getM_account()%>"></td>
			</tr>
			<tr>
				<td>銀行戶名:<a>*</a></td>
				<td><input type="text" name="m_accountName"
					value="<%=memberVO.getM_accountName()%>"></td>
			</tr>
			<tr>
				<td>銀行代號:<a>*</a></td>
				<td><input type="text" name="b_code"
					value="<%=memberVO.getB_code()%>"></td>
			</tr>
			<tr>
				<td>存摺照片:<a>*</a></td>
				<td><input type="file" name="m_bank_pic"
					value="<%=memberVO.getM_id_pic()%>">
			</tr>
			<tr>
				<td>賣場名稱:<a>*</a></td>
				<td><input type="text" name="m_storename"
					value="<%=memberVO.getM_storename()%>"></td>
			</tr>
			<tr>
				<td>賣場簡介:</td>
				<td><input type="text" name="m_info"
					value="<%=memberVO.getM_info()%>"></td>
			</tr>
			<tr>
				<td>賣場封面:</td>
				<td><input type="file" name="m_cover"
					value="<%=memberVO.getM_cover()%>"></td>
			</tr>
			<tr>
				<td>聊天問候語:</td>
				<td><input type="text" name="m_hi"
					value="<%=memberVO.getM_hi()%>"></td>
			</tr>
			<tr>
				<td>離線問候語:</td>
				<td><input type="text" name="m_offlineHi"
					value="<%=memberVO.getM_offlineHi()%>"></td>
			</tr>
		</table>
		<input type="hidden" name="action" value="update">
		<input type="hidden" name="m_id" value="<%=memberVO.getM_id()%>">
		<input type="submit" value="送出修改"></FORM>
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
		theme : '', //theme: 'dark',
		timepicker : false, //timepicker:true,
		step : 1, //step: 60 (這是timepicker的預設間隔60分鐘)
		format : 'Y-m-d', //format:'Y-m-d H:i:s',
		value : new Date(),
		maxDate : '+1970-01-01' // 去除今日(不含)之後
	});
</script>
</html>
