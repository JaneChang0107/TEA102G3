<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="BIG5">
	<title>Insert title here</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet type" href="<%=request.getContextPath()%>/Back_end/employee/css/index_backstage.css">
</head>
<body>
<%-- -------------${errorMsgs_login}---------- --%>
	<form action="<%=request.getContextPath()%>/login" method="post">
	
	<div class="container-fluid login-container">
        <div class="row">
            <div class="col login_col">
                <div class="row header log_header">
                    <div class="col-4 img-div">
                        <img src="<%=request.getContextPath()%>/Back_end/employee/images/white_LOGO.png">
                    </div>
                    <div class="col-8 img-div">
                        <p class="p_backend">後臺管理系統</p>
                    </div>
                </div>
                <div class="row account-row">
                	<div class="col-6  d-none d-lg-block bg-login-image" id="serser">
                	</div>
                    <div class="col-6 col_enter">
                    
<!--                     	錯誤表列 -->
						<c:if test="${not empty errorMsgs_login}">
<!-- 							<font style="color:red">請修正以下錯誤:</font> -->
							<ul>
							    <c:forEach var="message" items="${errorMsgs_login}">
									<li style="color:red">${message}</li>
								</c:forEach>
							</ul>
						</c:if> 
						<c:remove var="errorMsgs_login"/>						                 	
	                     <div class="row form-group">
	                     	<input class="form-control form-control-user" type="text" name="e_id" placeholder="請輸入員工ID">
	                     </div>
	                     <div class="row form-group">
	                     	<input class="form-control form-control-user" type="password" name="e_password" placeholder="請輸入密碼">
	                     </div>
	                     <input type="hidden" name="action" value="login">
						<button type="submit" id="btn_login" class="btn btn-warning forget-btn btn-lg">登入</button>
						<hr>
                        <a id="btn_forget" class="small" href='<%=request.getContextPath()%>/Back_end/employee/password.jsp'>忘記密碼?</a>
                    </div>
                </div>              
<%--                       onclick="location.href='<%=request.getContextPath()%>/Back_end/employee/password.jsp'" --%>
                </div>
            </div>
        </div>
    </div>
    
	</form>


    <script src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/Back_end/employee/js/index_backstage.js"></script>
    <script>
// 		let pic = [
// 			"url(https://source.unsplash.com/K4mSJ7kc0As/600x800) center center / cover",
// 			"url(http://i.imgur.com/OZ60HUB.gif) center center / cover",
// 			"url(https://i.imgur.com/DJkTI8L.gif) center center / cover",
// 			"url(https://i.imgur.com/oK6VoFb.gif) center center / cover",
// 			"url(https://i.imgur.com/RYGJ7gN.gif) center center / cover"
// 		]
// 		let i = Math.floor(Math.random()*5)
// 		$("#serser").css("background",pic[i])
    </script>
</body>
</html>