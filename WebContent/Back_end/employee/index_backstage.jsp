<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.employee.model.*"%>
<%@ page import="com.member.model.*"%>

<%
    Object e_id = session.getAttribute("e_id");                  // 從 session內取出 (key) account的值
    if (e_id == null) {                                             // 如為 null, 代表此user未登入過 , 才做以下工作
      session.setAttribute("location", request.getRequestURI());       //*工作1 : 同時記下目前位置 , 以便於login.html登入成功後 , 能夠直接導至此網頁(須配合LoginHandler.java)
      response.sendRedirect(request.getContextPath()+"/Back_end/employee/login.jsp");   //*工作2 : 請該user去登入網頁(login.html) , 進行登入
      return;
    }
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet type" href="<%=request.getContextPath()%>/Back_end/employee/css/index_backstage.css">
<style>
.nav-link
{
    color: #9a9a9d;
    text-decoration: none;
    background-color: transparent;
}
</style>
</head>
<body>
	<div class="container-fluid index_container">
        <div class="row header">
            <div class="col-2 align-self-center img-div">
                <img src="./images/white_LOGO.png">
            </div>
            <div class="col head">
                 <div class="row align-items-end">
                    <div class="col-8 head">
                        <ul class="nav nav-tabs" id="myTab" role="tablist">
                            <li class="nav-item" role="presentation">   <!--員工系統 = emp_sys-->
                              <a class="nav-link active" id="emp_sys-tab" data-toggle="tab" href="#emp_sys" role="tab" aria-controls="emp_sys" aria-selected="true">
                              	員工系統
                              </a>
                            </li>
                            <li class="nav-item" role="presentation">   <!--帳號管理 = acc_mgt-->
                              <a class="nav-link" id="acc_mgt-tab" data-toggle="tab" href="#acc_mgt" role="tab" aria-controls="acc_mgt" aria-selected="false">
                              	帳號管理
                              </a>
                            </li>
                            <li class="nav-item" role="presentation">   <!--交易管理 = tran_sys -->
                              <a class="nav-link" id="tran_sys-tab" data-toggle="tab" href="#tran_sys" role="tab" aria-controls="tran_sys" aria-selected="false">
                                                               交易系統
                              </a>
                            </li>
                            <li class="nav-item" role="presentation">   <!--公告系統 = notice_sys -->
                              <a class="nav-link" id="notice_sys-tab" data-toggle="tab" href="#notice_sys" role="tab" aria-controls="notice_sys" aria-selected="false">
                                                               公告系統
                              </a>
                            </li>
                          </ul>                         
                    </div>
                    <div class="col">
                    	<p>${employeeVO.e_name}</p>
                    </div>
                    <div class="col">
                    	<form action="<%=request.getContextPath()%>/logout">
                            <button type="submit" class="btn btn-danger">登出</button>
                        </form>
                    </div>             
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <div class="tab-content" id="myTabContent">
                    <div class="tab-pane fade show active" id="emp_sys" role="tabpanel" aria-labelledby="emp_sys-tab">
                    	<%@include file="employee.jsp"%>
                    </div>
                    <div class="tab-pane fade" id="acc_mgt" role="tabpanel" aria-labelledby="acc_mgt-tab">
                        <jsp:include page="listAllMem.jsp"></jsp:include>
                    </div>  
                    <div class="tab-pane fade" id="tran_sys" role="tabpanel" aria-labelledby="tran_sys-tab">
						<%@include file="/Back_end/product/showProduct.jsp"%>
					</div>       
                    <div class="tab-pane fade" id="notice_sys" role="tabpanel" aria-labelledby="notice_sys-tab">
                    	<jsp:include page="/Back_end/runlight/input.jsp"></jsp:include>					
					</div>       
                </div>
            </div>
        </div>
    </div>


    <script src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/Back_end/employee/js/index_backstage.js"></script>
    <script src="<%=request.getContextPath()%>/Back_end/employee/js/notic.js"></script>
    
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