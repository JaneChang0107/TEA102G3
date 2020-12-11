<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<%
	MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>�s�W�|��</title>

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
	<h3>��Ʒs�W:</h3>
     
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="memberServlet.do" name="form1">
		<table>
			<tr>
				<td>�q�l�l��:<b>*</b></td>
				<td><input type="text" name="m_email" size="40"
					value="<%=(memberVO == null) ? "�ж�J�l�c" : memberVO.getM_address()%>" /></td>

			</tr>
			<tr>
				<td>�K�X:<b>*</b></td>
				<td><input type="password" name="m_password" size="40"
					value="<%=(memberVO == null) ? "" : memberVO.getM_password()%>" /></td>
			</tr>
			<tr>
				<td>�m�W:<b>*</b></td>
				<td><input type="text" name="m_name" size="40"
					value="<%=(memberVO == null) ? "�ж�J�m�W" : memberVO.getM_name()%>" /></td>
			</tr>
			<tr>
				<td>�ʧO:<b>*</b></td>
				<td><input type="radio" name="m_gender" value="�k">�k <input
					type="radio" name="m_gender" value="�k">�k</td>
			</tr>
			<tr>
				<td>�q��:<b>*</b></td>
				<td><input type="text" name="m_phone" size="40"
					value="<%=(memberVO == null) ? "�ж�J�q��" : memberVO.getM_phone()%>" /></td>
			</tr>
			<tr>
				<td>�a�}:<b>*</b></td>
				<td><input type="text" name="m_address" size="40"
					value="<%=(memberVO == null) ? "�ж�J�a�}" : memberVO.getM_address()%>" /></td>
			</tr>
			<tr>
				<td>�ͤ�:<b>*</b></td>
				<td><input name="m_birth" id="f_date1" type="text"></td>
			</tr>
			<tr>
				<td>�j�Y��:</td>
				<td><input type="file" name="m_headpic"
					value="<%=(memberVO == null) ? "�ФW�Ǥj�Y��" : memberVO.getM_headpic()%>" /></td>
			</tr>
			<tr>
				<td>�|�����A:</td>
				<td><input type="text" name="m_status" value="1"></td>
			</tr>
			<tr>
				<td>�����Ҧr��:</td>
				<td><input type="text" name="m_identity"
					value="<%=(memberVO == null) ? "�ж�J�����Ҧr��" : memberVO.getM_identity()%>"></td>
			</tr>
			<tr>
				<td>�����ҷӤ�:</td>
				<td><input type="file" name="m_id_pic"
					value="<%=(memberVO == null) ? "�ФW�Ǩ����ҷ�" : memberVO.getM_id_pic()%>" /></td>
			</tr>
			<tr>
				<td>�Ȧ�b��:</td>
				<td><input type="text" name="m_account"
					value="<%=(memberVO == null) ? "�п�J�Ȧ�b��" : memberVO.getM_account()%>"></td>
			</tr>
			<tr>
				<td>�Ȧ��W:</td>
				<td><input type="text" name="m_accountname"
					value="<%=(memberVO == null) ? "�п�J�Ȧ��W" : memberVO.getM_accountName()%>"></td>
			</tr>
			<tr>
				<td>�Ȧ�N��:</td>
				<td><input type="text" name="b_code"
					value="<%=(memberVO == null) ? "�п�J�Ȧ�N��" : memberVO.getB_code()%>"></td>
			</tr>
			<tr>
				<td>�s�P�Ӥ�:</td>
				<td><input type="file" name="m_id_pic"
					value="<%=(memberVO == null) ? "�W�Ǧs�P��" : memberVO.getM_id_pic()%>">
			</tr>
			<tr>
				<td>����W��:</td>
				<td><input type="text" name="m_storename"
					value="<%=(memberVO == null) ? "�п�J�ӳ��W�r" : memberVO.getM_storename()%>"></td>
			</tr>
			<tr>
				<td>���²��:</td>
				<td><input type="text" name="m_info"
					value="<%=(memberVO == null) ? "�п�J�ӳ�����" : memberVO.getM_info()%>"></td>
			</tr>
			<tr>
				<td>����ʭ�:</td>
				<td><input type="file" name="m_cover"
					value="<%=(memberVO == null) ? "�ФW�ǽ���Ӥ�" : memberVO.getM_cover()%>"></td>
			</tr>
			<tr>
				<td>��ѰݭԻy:</td>
				<td><input type="text" name="m_hi"
					value="<%=(memberVO == null) ? "�п�J�ݭԻy" : memberVO.getM_hi()%>"></td>
			</tr>
			<tr>
				<td>���u�ݭԻy:</td>
				<td><input type="text" name="m_offlinehi"
					value="<%=(memberVO == null) ? "�п�J���u�ݭԻy" : memberVO.getM_offlineHi()%>"></td>
			</tr>
			<tr>
				<td>�N��:</td>
				<td><input type="text" name="m_coin"
					value="<%=(memberVO == null) ? 0 : memberVO.getM_coin()%>"></td>
			</tr>
		</table>
		<br> 
		<input type="hidden" name="action" value="insert"> 
		<input type="submit" value="�e�X�s�W"></FORM>
</body>


<!-- =====Date time picker�]�w======== -->
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
	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
<%-- 		   // value: '<%=m_birth%>',    --%>
		   value:   new Date(),
           maxDate:               '+1970-01-01'  // �h������(���t)����
        });
</script>
</html>
