
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="com.orderlist.model.OrderlistVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
  OrderlistVO orderlistVO = (OrderlistVO) request.getAttribute("orderlistVO");

// Timestamp dNow = new Timestamp( );
// SimpleDateFormat ft = 
// new SimpleDateFormat ("yyyy-MM-dd  hh:mm:ss");



%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>新增</title>

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
<body>

<table id="table-1">
	<tr><td>
		 <h3>新增 - add.jsp</h3></td><td>
		 <h4><a href="<%= request.getContextPath() %>/Back_end/orderlist/select_page.jsp"><img src="<%= request.getContextPath() %>/Back_end/images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
	
	</td></tr>
</table>
<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/orderlist" name="form1">
<table>
	<tr>
		<td>訂單編號:</td>
		<td><input type="hidden" name="o_id" size="45" value="11111">
<%-- 			 value="<%= (orderlistVO==null)? "會自動生成訂單編號" : orderlistVO.getO_id()%>" /></td> --%>
			 <p>會自動生成訂單編號</p>
		</td>
	</tr>
	<tr>
		<td>訂單日期:</td>
		<td><input name="o_date" id="o_date" type="text"></td>
	</tr>
	<tr>
		<td>訂單狀態:</td>
		<td><input type="TEXT" name="o_status" size="45"
			 value="<%= (orderlistVO==null)? "已送出" : orderlistVO.getO_status()%>" /></td>
	</tr>
	<tr>
		<td>出貨日期:</td>
		<td><input name="o_shipdate" id="o_shipdate" type="text"></td>
	</tr>
	<tr>
		<td>收貨日期:</td>
		<td><input name="o_deceiptdate" id="o_deceiptdate" type="text"></td>
	</tr>
	<tr>
		<td>完成日期:</td>
		<td><input name="o_finishdate" id="o_finishdate" type="text"></td>
		
	</tr>
	<tr>
		<td>運送方式:</td>
		<td><input type="TEXT" name="o_transport" size="45"
			 value="<%= (orderlistVO==null)? "7-11" : orderlistVO.getO_transport()%>" /></td>
	</tr>
	<tr>
		<td>寄送地址:</td>
		<td><input type="TEXT" name="o_address" size="45"
			 value="<%= (orderlistVO==null)? "基隆市" : orderlistVO.getO_address()%>" /></td>
	</tr>
	<tr>
		<td>總金額:</td>
		<td><input type="TEXT" name="o_total" size="45"
			 value="<%= (orderlistVO==null)? "1000" : orderlistVO.getO_total()%>" /></td>
	</tr>
	<tr>
		<td>堃幣:</td>
		<td><input type="TEXT" name="o_pm" size="45"
			 value="<%= (orderlistVO==null)? "1" : orderlistVO.getO_pm()%>" /></td>
	</tr>
	<tr>
		<td>買家:</td>
		<td><input type="TEXT" name="m_id" size="45"
			 value="<%= (orderlistVO==null)? "M00001" : orderlistVO.getM_id()%>" /></td>
	</tr>
	
	
	
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>

</body>
<!-- <!-- =========================================以下為 datetimepicker 之相關設定========================================== --> -->

<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" /> --%>
<%-- <script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script> --%>
<%-- <script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script> --%>

<!-- <style> -->
/*   .xdsoft_datetimepicker .xdsoft_datepicker { */
/*            width:  300px;   /* width:  300px; */ */
/*   } */
/*   .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box { */
/*            height: 151px;   /* height:  151px; */ */
/*   } */
<!-- </style> -->

<!-- <script> -->
//         $.datetimepicker.setLocale('zh');
//         $('#o_date').datetimepicker({
//            theme: '',              //theme: 'dark',
//  	       timepicker:true,       //timepicker:true,
//  	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
//  	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
<%--  		   value: '<%= orderlistVO.getO_date()%>', // value:   new Date(), --%>
//            //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
//            //startDate:	            '2017/07/10',  // 起始日
//            //minDate:               '-1970-01-01', // 去除今日(不含)之前
//            //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
//         });
        
        
   
//         // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

//         //      1.以下為某一天之前的日期無法選擇
//         //      var somedate1 = new Date('2017-06-15');
//         //      $('#f_date1').datetimepicker({
//         //          beforeShowDay: function(date) {
//         //        	  if (  date.getYear() <  somedate1.getYear() || 
//         //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
//         //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
//         //              ) {
//         //                   return [false, ""]
//         //              }
//         //              return [true, ""];
//         //      }});

        
//         //      2.以下為某一天之後的日期無法選擇
//         //      var somedate2 = new Date('2017-06-15');
//         //      $('#f_date1').datetimepicker({
//         //          beforeShowDay: function(date) {
//         //        	  if (  date.getYear() >  somedate2.getYear() || 
//         //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
//         //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
//         //              ) {
//         //                   return [false, ""]
//         //              }
//         //              return [true, ""];
//         //      }});


//         //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
//         //      var somedate1 = new Date('2017-06-15');
//         //      var somedate2 = new Date('2017-06-25');
//         //      $('#f_date1').datetimepicker({
//         //          beforeShowDay: function(date) {
//         //        	  if (  date.getYear() <  somedate1.getYear() || 
//         //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
//         //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
//         //		             ||
//         //		            date.getYear() >  somedate2.getYear() || 
//         //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
//         //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
//         //              ) {
//         //                   return [false, ""]
//         //              }
//         //              return [true, ""];
//         //      }});
        
<!-- </script> -->






</html>