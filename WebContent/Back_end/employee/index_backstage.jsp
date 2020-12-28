<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.employee.model.*"%>

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
</head>
<body>
	<div class="container-fluid index_container">
        <div class="row header">
            <div class="col-2 align-self-center img-div">
                <img src="./images/white_LOGO.png">
            </div>
            <div class="col head">
                <div class="row align-items-center">
                    <div class="col"><p>HELLO!${e_id}</p></div>
                </div>

                <div class="row align-items-center">
                    <div class="col head">
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
                            <li class="nav-item" role="presentation">   <!--客服系統 = cs_sys -->
                              <a class="nav-link" id="cs_sys-tab" data-toggle="tab" href="#cs_sys" role="tab" aria-controls="cs_sys" aria-selected="false">
                              	客服系統
                              </a>
                            </li>
                            <li class="nav-item" role="presentation">   <!--出租管理 = rent_mgt -->
                              <a class="nav-link" id="rent_mgt-tab" data-toggle="tab" href="#rent_mgt" role="tab" aria-controls="rent_mgt" aria-selected="false">
                                                                出租管理
                              </a>
                            </li>
                            <li class="nav-item" role="presentation">   <!--交易管理 = tran_sys -->
                              <a class="nav-link" id="tran_sys-tab" data-toggle="tab" href="#tran_sys" role="tab" aria-controls="tran_sys" aria-selected="false">
                                                               交易系統
                              </a>
                            </li>
                            <li>
                            	<form action="<%=request.getContextPath()%>/logout">
                                <button type="submit" class="btn btn-danger">登出</button>
                                </form>
                            </li>
                          </ul>                         
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
<%--                     	<%@include file="addEmployee.jsp"%> --%>
                    </div>
                    <div class="tab-pane fade" id="cs_sys" role="tabpanel" aria-labelledby="cs_sys-tab">這裡放你要的頁面</div>
                    <div class="tab-pane fade" id="rent_mgt" role="tabpanel" aria-labelledby="rent_mgt-tab">這裡放你要的頁面</div>
                    <div class="tab-pane fade" id="tran_sys" role="tabpanel" aria-labelledby="tran_sys-tab">這裡放你要的頁面</div>
                </div>
            </div>
        </div>
    </div>


    <script src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/Back_end/employee/js/index_backstage.js"></script>
</body>
</html>