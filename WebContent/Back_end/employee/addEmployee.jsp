<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.employee.model.*"%>

<%
	EmployeeVO employeeVO = (EmployeeVO) request.getAttribute("employeeVO");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>���u��Ʒs�W - addEmployee.jsp</title>

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
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
			<h3>���u��Ʒs�W - addEmployee.jsp</h3></td><td>
			<h4><a href="<%=request.getContextPath()%>/Back_end/employee/select_page.jsp"><img src="<%=request.getContextPath()%>/Back_end/employee/images/1.png" width="100" height="100" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��Ʒs�W:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color: red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color: red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/employee/controller/employeeServlet.do" name="form1">
<table>
	<tr>
		<td>�K�X:</td>
		<td><input type="PASSWORD" name="e_password" size="45" placeholder="�п�J�K�X"
			 value="<%= (employeeVO == null) ? "" : employeeVO.getE_password()%>" /></td>
	</tr>
	<tr>
		<td>�����Ҧr��:</td>
		<td><input type="TEXT" name="e_identity" size="45" placeholder="�п�J�����Ҧr��"
			 value="<%= (employeeVO == null) ? "A123456789" : employeeVO.getE_identity()%>" /></td>
	</tr>
	<tr>
		<td>�m�W:</td>
		<td><input type="TEXT" name="e_name" size="45" placeholder="�п�J�m�W"
			 value="<%=(employeeVO == null) ? "�d�ç�" : employeeVO.getE_name()%>" /></td>
	</tr>
	<tr>
		<td>�ʧO:</td>
		<td><input type="radio" name="e_gender" size="45" value="MEN" <%=employeeVO!=null && "MEN".equals(employeeVO.getE_gender()) ? "checked" : ""%> />MEN
			<input type="radio" name="e_gender" size="45" value="WOMEN" <%=employeeVO!=null && "WOMEN".equals(employeeVO.getE_gender()) ? "checked" : ""%> />WOMEN
		</td>
	</tr>
	<tr>
		<td>�ͤ�:</td>
		<td><input name="e_birth" id="f_date1" type="text" placeholder="�п�ܤ��"></td>
	</tr>

	<tr>
		<td>�q�l�H�c:</td>
		<td><input type="TEXT" name="e_email" size="45" placeholder="�п�J�q�l�H�c"
		 	 value="<%=(employeeVO == null) ? "123@yahoo.com.tw" : employeeVO.getE_email()%>" /></td>
	</tr>
	<tr>
		<td>�q��:</td>
		<td><input type="TEXT" name="e_phone" size="45" placeholder="�п�J�q��"
		   	 value="<%=(employeeVO == null) ? "0987654321" : employeeVO.getE_phone()%>" /></td>
	</tr>
	<tr>
		<td>��}:</td>
		<td><input type="TEXT" name="e_address" size="45" placeholder="�п�J��}"
			 value="<%=(employeeVO == null) ? "�s�_���_��Ϥj�w��49��" : employeeVO.getE_address()%>" /></td>
	</tr>
	<tr>
		<td>¾��:</td>
		<td><input type="radio" name="e_title" size="45" value="0" <%=employeeVO!=null && "0".equals(employeeVO.getE_title()) ? "checked" : ""%> />EMPLOYEE
			<input type="radio" name="e_title" size="45" value="1" <%=employeeVO!=null && "1".equals(employeeVO.getE_title()) ? "checked" : ""%> />BOSS</td>
	</tr>
<!-- 	<tr> -->
<!-- 		<td>���A:</td> -->
<!-- 		<td><input type="radio" name="e_status" size="45" value="0" checked />�b¾ -->
<!-- 			<input type="radio" name="e_status" size="45" value="1" />��¾ -->
<!-- 			<input type="radio" name="e_status" size="45" value="2" />�d¾���~</td> -->
<!-- 	</tr> -->

	<td>¾��:<font color=red><b>*</b></font></td>
		<td><select size="1" name="e_status">			
				<option value="0">�b¾
				<option value="1">��¾
				<option value="2">�d¾���~
		</select></td>

	<jsp:useBean id="storeSvc" scope="page"	class="com.store.model.StoreService" />
	<tr>
		<td>����:<font color=red><b>*</b></font></td>
		<td><select size="1" name="st_id">
			<c:forEach var="storeVO" items="${storeSvc.all}">
				<option value="${storeVO.st_id}" ${(employeeVO.st_id==storeVO.st_id)? 'selected':'' }>${storeVO.st_id}
		</c:forEach>
		</select></td>
	</tr>

</table>
<br> 
<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W"></FORM>
</body>



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

<%
	java.sql.Date e_birth = null;
	try {
		e_birth = employeeVO.getE_birth();
	} catch (Exception e) {
		e_birth = new java.sql.Date(System.currentTimeMillis());
	}
%>
<link rel="stylesheet" type="text/css"	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

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
	       timepicker:true,       //timepicker:true,
	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
		   value: '<%=e_birth%>', // value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
	//startDate:	            '2017/07/10',  // �_�l��
	//minDate:               '-1970-01-01', // �h������(���t)���e
	//maxDate:               '+1970-01-01'  // �h������(���t)����
	});

	// ----------------------------------------------------------�H�U�ΨӱƩw�L�k��ܪ����-----------------------------------------------------------

	//      1.�H�U���Y�@�Ѥ��e������L�k���
	//      var somedate1 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      2.�H�U���Y�@�Ѥ��᪺����L�k���
	//      var somedate2 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      3.�H�U����Ӥ�����~������L�k��� (�]�i���ݭn������L���)
	//      var somedate1 = new Date('2017-06-15');
	//      var somedate2 = new Date('2017-06-25');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//		             ||
	//		            date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});
</script>
</html>