<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="BIG5">
	<title>Insert title here</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet type" href="<%=request.getContextPath()%>/Back_end/employee/css/index_backstage.css">
</head>
<body>
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
                        <p>請輸入註冊時電子郵件:</p>
                        <input type="text"></input>
                    </div>
                </div>
                <div class="row forget-row">
                    <div class="col-10">
                        <button type="button" id="btn_index"  class="btn btn-light forget-btn">回首頁</button>
                        <button type="button" class="btn btn-warning forget-btn">送出</button>
                    </div>
<%--                     onclick="location.href='<%=request.getContextPath()%>/Back_end/employee/login.jsp'" --%>
                </div>
            </div>
        </div>
    </div>


    <script src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/Back_end/employee/js/index_backstage.js"></script>
    <script>
	    var btn_index = document.getElementById("btn_index");
	    btn_index.addEventListener('click',function(){
	    	location.href='<%=request.getContextPath()%>/Back_end/employee/login.jsp'
	    });   
    </script>
</body>
</html>