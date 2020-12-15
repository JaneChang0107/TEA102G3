<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.rent.model.*"%>

<%
	RentVO rentVO = (RentVO) request.getAttribute("rentVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<%
	java.sql.Timestamp r_revisedate = null;
	try {
		r_revisedate = new java.sql.Timestamp(System.currentTimeMillis());
	} catch (Exception e) {
		r_revisedate = new java.sql.Timestamp(System.currentTimeMillis());
	}
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
				<h3>員工資料修改 - update_emp_input.jsp</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/Back_end/Rent/index.jsp"><img
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

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/RentServlet"
		name="form1">
		<table>
			<tr>
				<td>出租品種類:</td>
				<td><%=rentVO.getR_id()%></td>
			</tr>
			<tr>
				<td>出租品種類:</td>
				<td><select name="r_type">
						<option value="遊戲主機">遊戲主機</option>
						<option value="遊戲片">遊戲片</option>
						<option value="遊戲周邊">遊戲周邊</option>
				</select>
			<tr>
				<td>出租品名稱:</td>
				<td><input type="TEXT" name="r_name" size="45"
					value="<%=rentVO.getR_name()%>" /></td>
			</tr>

			<tr>
				<td>出租品種類id:</td>
				<td><input type="TEXT" name="pt_id" size="45"
					value="<%=rentVO.getPt_id()%>" /></td>
			</tr>

			<tr>
				<td>描述:</td>
				<td><input type="TEXT" name="r_describe" size="45"
					value="<%=rentVO.getR_describe()%>" /></td>
			</tr>
			<tr>
				<td>貨況:</td>
				<td><input type="TEXT" name="r_situation" size="45"
					value="<%=rentVO.getR_situation()%>" /></td>
			</tr>
			<tr>
				<td>貨物狀態:</td>
				<td><select name="r_status">
						<option value="未上架">未上架</option>
						<option value="上架中">上架中</option>
						<option value="出租中">出租中</option>
						<option value="已歸還">已歸還</option>
						
			</select> 
			</tr>
			<tr>
				<td>出租品價格:</td>
				<td><input type="TEXT" name="r_price" size="45"
					value="<%=rentVO.getR_price()%>" /></td>
			</tr>
			<tr>
				<td>新增日期:</td>
				<td><%=rentVO.getR_adddate()%></td>
			</tr>
			<tr>
				<td>修改日期:</td>
				<td><%=r_revisedate%></td>
			</tr>
			<tr>
				<td>新增者ID:</td>
				<td><input type="TEXT" name="e_addid" size="45"
					value="<%=rentVO.getE_addid()%>" /></td>
			</tr>
			<tr>
				<td>修改者ID:</td>
				<td><input type="TEXT" name="e_editorid" size="45"
					value="<%=rentVO.getE_editorid()%>" /></td>
			</tr>
			<tr>
				<td>門市ID:</td>
				<td><input type="TEXT" name="st_id" size="45"
					value="<%=rentVO.getSt_id()%>" /></td>
			</tr>



		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="r_id" value="<%=rentVO.getR_id()%>"> <input
			type="hidden" name="r_adddate" value="<%=rentVO.getR_adddate()%>">
		<input type="hidden" name="r_revisedate"
			value="<%=r_revisedate %>"> <input type="submit"
			value="送出修改">
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
//         $.datetimepicker.setLocale('zh');
//         $('#f_date1').datetimepicker({
//            theme: '',              //theme: 'dark',
//  	       timepicker:false,       //timepicker:true,
//  	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
//  	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
<%--  		   value: '<%=rentVO.getR_revisedate()%>', // value:   new Date(), --%>
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
//	         });
        
        
   
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