<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.employee.model.*"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="BIG5">
	<title>Insert title here</title>
<%-- 	<link rel="stylesheet" href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css"> --%>
<%--     <link rel="stylesheet type" href="<%=request.getContextPath()%>/Back_end/employee/css/index_backstage.css"> --%>
</head>
<body>
<%-- 	<form action="<%=request.getContextPath()%>/login" method="post"> --%>
	<div class="container update_without">
		<div class="row">
	 		<div class="col">
<!-- 	 			錯誤表列 -->
				<c:if test="${not empty errorMsgs_pwd}">
					<font style="color:red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs_pwd}">
							<li style="color:red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				<c:remove var="errorMsgs_pwd"/>
	 		</div>
	 	</div>
        <div class="row">
            <div class="col">
                <p>原有密碼:</p>
            </div>
            <div class="col">
                <input type="password" name="e_password">
            </div>
        </div>
        <div class="row">
            <div class="col">
                <p>新密碼:</p>
            </div>
            <div class="col">
                <input type="password" name="new_password">
            </div>
        </div>
        <div class="row">
            <div class="col">
                <p>密碼確認:</p>
            </div>
            <div class="col">
                <input type="password" name="check_password">
            </div>
        </div>
        <div class="row forget-row">
            <div class="col btn_col">
                <button type="button" id="enter_forget" class="btn btn-primary forget-btn">確認</button>
                <input type="hidden" name="action" value="update_pwd">
				<input type="hidden" name="e_id" value="${employeeVO.e_id}">
            </div>
        </div>
    </div>
<!--     </form> -->
    
    
<%--     <script src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script> --%>
<%--     <script src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script> --%>
<%--     <script src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script> --%>
<%--     <script src="<%=request.getContextPath()%>/Back_end/employee/js/index_backstage.js"></script> --%>
    <script>
// 	    if(${errorMsgs_pwd != null}){
// 			alert("${errorMsgs_pwd}");
// 		};
    </script>
</body>
</html>