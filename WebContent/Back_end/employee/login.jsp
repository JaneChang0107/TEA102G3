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
                        <img src="./images/white_LOGO.png">
                    </div>
                    <div class="col-8 img-div">
                        <p>後臺管理系統</p>
                    </div>
                </div>
                <div class="row account-row">
                    <div class="col">
                    
<!--                     	錯誤表列 -->
						<c:if test="${not empty errorMsgs_login}">
							<font style="color:red">請修正以下錯誤:</font>
							<ul>
							    <c:forEach var="message" items="${errorMsgs_login}">
									<li style="color:red">${message}</li>
								</c:forEach>
							</ul>
						</c:if> 
						<c:remove var="errorMsgs_login"/>
						                 	
	                     <p>帳號:</p><input type="text" name="e_id">
	                     <p>密碼:</p><input type="password" name="e_password">
	                     <input type="hidden" name="action" value="login">
                    </div>
                </div>
                <div class="row forget-row">
                    <div class="col-10">
                        <button type="submit" id="btn_login" class="btn btn-warning forget-btn">登入</button>
                        <button type="button"  id="btn_forget" class="btn btn-warning forget-btn">忘記密碼</button>
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
	    var btn_forget = document.getElementById("btn_forget");
	    btn_forget.addEventListener('click',function(){
	    	location.href='<%=request.getContextPath()%>/Back_end/employee/password.jsp'
	    });
	    
	   /* var btn_login = document.getElementById("btn_login");
	    btn_login.addEventListener('click',function(){*/
// 	    	if(${errorMsgs_login != null}){
// 	    		alert("${errorMsgs_login}");
// 	    	};
	 /*   })*/
    </script>
</body>
</html>