<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.employee.model.*"%>

<%
	EmployeeVO employeeVO = (EmployeeVO) request.getAttribute("employeeVO");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>員工資料新增 - addEmployee.jsp</title>

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
			<h3>員工資料新增 - addEmployee.jsp</h3></td><td>
			<h4><a href="<%=request.getContextPath()%>/Back_end/employee/select_page.jsp"><img src="<%=request.getContextPath()%>/Back_end/employee/images/1.png" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color: red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color: red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/employee/controller/employeeServlet.do" name="form1">
<table>
	<tr>
		<td>密碼:</td>
		<td><input type="PASSWORD" name="e_password" size="45" placeholder="請輸入密碼"
			 value="<%= (employeeVO == null) ? "" : employeeVO.getE_password()%>" /></td>
	</tr>
	<tr>
		<td>身分證字號:</td>
		<td><input type="TEXT" name="e_identity" size="45" placeholder="請輸入身分證字號"
			 value="<%= (employeeVO == null) ? "A123456789" : employeeVO.getE_identity()%>" /></td>
	</tr>
	<tr>
		<td>姓名:</td>
		<td><input type="TEXT" name="e_name" size="45" placeholder="請輸入姓名"
			 value="<%=(employeeVO == null) ? "吳永志" : employeeVO.getE_name()%>" /></td>
	</tr>
	<tr>
		<td>性別:</td>
		<td><input type="radio" name="e_gender" size="45" value="MEN" <%=employeeVO!=null && "MEN".equals(employeeVO.getE_gender()) ? "checked" : ""%> />MEN
			<input type="radio" name="e_gender" size="45" value="WOMEN" <%=employeeVO!=null && "WOMEN".equals(employeeVO.getE_gender()) ? "checked" : ""%> />WOMEN
		</td>
	</tr>
	<tr>
		<td>生日:</td>
		<td><input name="e_birth" id="f_date1" type="text" placeholder="請選擇日期"></td>
	</tr>

	<tr>
		<td>電子信箱:</td>
		<td><input type="TEXT" name="e_email" size="45" placeholder="請輸入電子信箱"
		 	 value="<%=(employeeVO == null) ? "123@yahoo.com.tw" : employeeVO.getE_email()%>" /></td>
	</tr>
	<tr>
		<td>電話:</td>
		<td><input type="TEXT" name="e_phone" size="45" placeholder="請輸入電話"
		   	 value="<%=(employeeVO == null) ? "0987654321" : employeeVO.getE_phone()%>" /></td>
	</tr>
	<tr>
		<td>住址:</td>
		<td><input type="TEXT" name="e_address" size="45" placeholder="請輸入住址"
			 value="<%=(employeeVO == null) ? "新北市北投區大安路49號" : employeeVO.getE_address()%>" /></td>
	</tr>
	<tr>
		<td>職稱:</td>
		<td><input type="radio" name="e_title" size="45" value="0" <%=employeeVO!=null && "0".equals(employeeVO.getE_title()) ? "checked" : ""%> />EMPLOYEE
			<input type="radio" name="e_title" size="45" value="1" <%=employeeVO!=null && "1".equals(employeeVO.getE_title()) ? "checked" : ""%> />BOSS</td>
	</tr>
<!-- 	<tr> -->
<!-- 		<td>狀態:</td> -->
<!-- 		<td><input type="radio" name="e_status" size="45" value="0" checked />在職 -->
<!-- 			<input type="radio" name="e_status" size="45" value="1" />離職 -->
<!-- 			<input type="radio" name="e_status" size="45" value="2" />留職停薪</td> -->
<!-- 	</tr> -->

	<td>職稱:<font color=red><b>*</b></font></td>
		<td><select size="1" name="e_status">			
				<option value="0">在職
				<option value="1">離職
				<option value="2">留職停薪
		</select></td>

	<jsp:useBean id="storeSvc" scope="page"	class="com.store.model.StoreService" />
	<tr>
		<td>分店:<font color=red><b>*</b></font></td>
		<td><select size="1" name="st_id">
			<c:forEach var="storeVO" items="${storeSvc.all}">
				<option value="${storeVO.st_id}" ${(employeeVO.st_id==storeVO.st_id)? 'selected':'' }>${storeVO.st_id}
		</c:forEach>
		</select></td>
	</tr>

</table>
<br> 
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

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
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
		   value: '<%=e_birth%>', // value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});

	// ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

	//      1.以下為某一天之前的日期無法選擇
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

	//      2.以下為某一天之後的日期無法選擇
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

	//      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
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