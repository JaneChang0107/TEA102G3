<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.employee.model.*"%>

<%
  EmployeeVO employeeVO = (EmployeeVO) request.getAttribute("employeeVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="BIG5">
	<title>Insert title here</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/vendors/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet type" href="${pageContext.request.contextPath}/Back_end/employee/css/index_backstage.css">
</head>
<body>
<%-- <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/login" name="form1"> --%>
	 <div id="update_without" class="container update_without">
<!-- 	 	<div class="row"> -->
<!-- 	 		<div class="col"> -->
<!-- 				錯誤表列 -->
<%-- 				<c:if test="${not empty errorMsgs_without}"> --%>
<!-- 					<font style="color:red">請修正以下錯誤:</font> -->
<!-- 					<ul> -->
<%-- 						<c:forEach var="message" items="${errorMsgs_without}"> --%>
<%-- 							<li style="color:red">${message}</li> --%>
<%-- 						</c:forEach> --%>
<!-- 					</ul> -->
<%-- 				</c:if> --%>
<%-- 				<c:remove var="errorMsgs_without"/> --%>
<!-- 	 		</div> -->
<!-- 	 	</div> -->
<!--         <div class="row"> -->
<!--             <div class="col"> -->
<!--             	<p>員工ID:</p> -->
<!--             </div> -->
<!--             <div class="col">                -->
<%--                 ${employeeVO.e_id} --%>
<!--             </div> -->
<!--             <div class="col"> -->
<!--                 <p>門市:</p> -->
<!--             </div> -->
<!--             <div class="col"> -->
<%--             	<jsp:useBean id="storeSvc" scope="page" class="com.store.model.StoreService" /> --%>
<!--                 <select size="1" id="st_id" name="st_id"> -->
<%-- 					<c:forEach var="storeVO" items="${storeSvc.all}"> --%>
<%-- 					<option id="select_store" value="${storeVO.st_id}" ${(employeeVO.st_id==storeVO.st_id)? 'selected':'' }>${storeVO.st_id} --%>
<%-- 					</c:forEach> --%>
<!-- 				</select> -->
<!--             </div> -->
<!--         </div> -->
<!--         <div class="row"> -->
<!--             <div class="col"> -->
<!--                 <p>職稱:</p> -->
<!--             </div> -->
<!--             <div class="col"> -->
<%--              <c:if test="${employeeVO.e_title.equals('BOSS')}">											 --%>
<%--                 <input id="status_emp" type="radio" name="e_title" size="45" value="EMPLOYEE" ${employeeVO.e_title eq 'EMPLOYEE' ? 'checked' : ''} >EMPLOYEE --%>
<%-- 				<input id="status_boss" type="radio" name="e_title" size="45" value="BOSS" ${employeeVO.e_title eq 'BOSS' ? 'checked' : ''} >BOSS --%>
<%-- 			 </c:if> --%>
<%--              <c:if test="${employeeVO.e_title.equals('EMPLOYEE')}">											 --%>
<!--                 <p>EMPLOYEE -->
<%-- 			 </c:if> --%>
<!--             </div> -->
<!--             <div class="col"> -->
<!--                 <p>電話:</p> -->
<!--             </div> -->
<!--             <div class="col"> -->
<%--                 <input id="e_phone" type="TEXT" name="e_phone" value="${employeeVO.e_phone}"> --%>
<!--             </div> -->
<!--         </div> -->
<!--         <div class="row"> -->
<!--             <div class="col"> -->
<!--                 <p>員工姓名:</p> -->
<!--             </div> -->
<!--             <div class="col"> -->
<%--                 <input id="e_name" type="TEXT" name="e_name" value="${employeeVO.e_name}"> --%>
<!--             </div> -->
<!--             <div class="col"> -->
<!--                 <p>性別:</p> -->
<!--             </div> -->
<!--             <div class="col"> -->
<%--                 <input id="gender_men" type="radio" name="e_gender" value="MEN" ${employeeVO.e_gender eq 'MEN' ? 'checked' : '' }>MEN --%>
<%-- 				<input id="gender_women" type="radio" name="e_gender" value="WOMEN" ${employeeVO.e_gender eq 'WOMEN' ? 'checked' : '' }>WOMEN --%>
<!--             </div> -->
<!--         </div> -->
<!--         <div class="row"> -->
<!--             <div class="col"> -->
<!--                 <p>身分證字號:</p> -->
<!--             </div> -->
<!--             <div class="col"> -->
<%--                 <input id="e_identity" type="TEXT" name="e_identity" value="${employeeVO.e_identity}"> --%>
<!--             </div> -->
<!--             <div class="col"> -->
<!--                 <p>生日:</p> -->
<!--             </div> -->
<!--             <div class="col"> -->
<!--                 <input name="e_birth" id="f_date1" type="text"> -->
<!--             </div> -->
<!--         </div> -->
<!--         <div class="row"> -->
<!--             <div class="col-3"> -->
<!--                 <p>地址:</p> -->
<!--             </div> -->
<!--             <div class="col-9"> -->
<%--                 <input type="TEXT" id="e_address"  name="e_address" value="${employeeVO.e_address}"> --%>
<!--             </div> -->
<!--         </div> -->
<!--         <div class="row"> -->
<!--             <div class="col-3"> -->
<!--                 <p>信箱:</p> -->
<!--             </div> -->
<!--             <div class="col-9"> -->
<%--                 <input type="TEXT" id="e_email" name="e_email" value="${employeeVO.e_email}"> --%>
<!--             </div> -->
<!--         </div> -->
<!--         <div class="row forget-row"> -->
<!--             <div class="col btn_col"> -->
<!--                 <button type="button" id="btn_enter" class="btn btn-primary forget-btn">確認</button> -->
<!--                 <input type="hidden" name="action" value="update_without"> -->
<%-- 				<input type="hidden" name="e_id" id="e_id" value="${employeeVO.e_id}"> --%>
<!--             </div> -->
<!--         </div> -->
    </div>
<!-- 	</FORM> -->



<%-- 	<script src="${pageContext.request.contextPath}/vendors/jquery/jquery-3.5.1.min.js"></script> --%>
<%--     <script src="${pageContext.request.contextPath}/vendors/popper/popper.min.js"></script> --%>
<%--     <script src="${pageContext.request.contextPath}/vendors/bootstrap/js/bootstrap.min.js"></script> --%>
<%--     <script src="${pageContext.request.contextPath}/Back_end/employee/js/index_backstage.js"></script> --%>
    <script>    	    
// 	    if(${errorMsgs_without != null}){
// 			alert("${errorMsgs_without}");			
// 		}
    </script>
    
    <!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/datetimepicker/jquery.datetimepicker.css" />
<script src="${pageContext.request.contextPath}/datetimepicker/jquery.js"></script>
<script src="${pageContext.request.contextPath}/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '${employeeVO.e_birth}', // value:   new Date(),
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
</body>

</html>