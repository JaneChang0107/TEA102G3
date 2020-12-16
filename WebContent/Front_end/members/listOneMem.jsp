<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.member.model.*"%>

<%
	MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>

<html>
<head>
<meta charset="UTF-8">
<title>單會員查詢</title>
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
<body bgcolor='E3F8F6'>
<table id="table-1">
	<tr><td>
		 <h3>會員資料</h3>
		 <h4><a href="<%=request.getContextPath()%>/Front_end/members/select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

	<table>
		<tr>
			<th>會員id</th>
			<th>電子郵件</th>
			<th>密碼</th>
			<th>姓名</th>
			<th>性別</th>
			<th>電話</th>
			<th>地址</th>
			<th>生日</th>
			<th>大頭照</th>
			<th>會員狀態</th>
			<th>身分證字號</th>
			<th>身分證照片</th>
			<th>銀行帳戶</th>
			<th>銀行戶名</th>
			<th>銀行代號</th>
			<th>存摺照片</th>
			<th>匯款最後日期</th>
			<th>賣場名稱</th>
			<th>賣場簡介</th>
			<th>賣場封面</th>
			<th>聊天問候語</th>
			<th>離線問候語</th>
			<th>堃幣</th>
		</tr>

		<tr>
			<td>${memberVO.m_id}</td>
            <td>${memberVO.m_email}</td>
            <td>${memberVO.m_password}</td>
            <td>${memberVO.m_name}</td>
            <td>${memberVO.m_gender}</td>
            <td>${memberVO.m_phone}</td>
            <td>${memberVO.m_address}</td>
            <td>${memberVO.m_birth}</td>
            <td><img src="data:image/jpg;base64,${memberVO.m_headpic2}" width="100" height="100"></td>
            <td>${memberVO.m_status}</td>
            <td>${memberVO.m_identity}</td>
            <td><img src="data:image/jpg;base64,${memberVO.m_id_pic2}" width="100" height="100"></td>
            <td>${memberVO.m_account}</td>
            <td>${memberVO.m_accountName}</td>
            <td>${memberVO.b_code}</td>
            <td>${memberVO.m_bank_pic}</td>
            <td>${memberVO.m_moneyTranDate}</td>
            <td>${memberVO.m_storename}</td>
            <td>${memberVO.m_info}</td>
            <td>${memberVO.m_cover}</td>
            <td>${memberVO.m_hi}</td>
            <td>${memberVO.m_offlineHi}</td>
            <td>${memberVO.m_coin}</td>
		</tr>
	</table>
</body>
</html>