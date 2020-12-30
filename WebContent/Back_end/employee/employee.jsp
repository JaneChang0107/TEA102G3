<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/vendors/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet type" href="${pageContext.request.contextPath}/Back_end/employee/css/index_backstage.css">
</head>
<body>
	 <div class="container-fluid">
        <div class="row">
            <div class="col">
                <ul class="nav nav-tabs" id="myTab" role="tablist">
                    <li class="nav-item" role="presentation">   <!--員工資料修改 = emp_modify--->
                      <a class="nav-link active" id="emp_modify-tab" data-toggle="tab" href="#emp_modify" role="tab" aria-controls="emp_modify" aria-selected="true">員工資料修改</a>
                    </li>
                    <li class="nav-item" role="presentation">   <!--更改密碼 = pwd_modify-->
                      <a class="nav-link" id="pwd_modify-tab" data-toggle="tab" href="#pwd_modify" role="tab" aria-controls="pwd_modify" aria-selected="false">更改密碼</a>
                    </li>
                    <li class="nav-item" role="presentation">   <!--員工管理 = emp_mana-->
                      <a class="nav-link" id="emp_mana-tab" data-toggle="tab" href="#emp_mana" role="tab" aria-controls="emp_mana" aria-selected="false">員工管理</a>
                    </li>
                  </ul> 
                  <div class="tab-content" id="myTabContent">
                    <div class="tab-pane fade show active" id="emp_modify" role="tabpanel" aria-labelledby="emp_modify-tab">
                      	<%@include file="update_without.jsp" %>
                    </div>
                    <div class="tab-pane fade" id="pwd_modify" role="tabpanel" aria-labelledby="pwd_modify-tab">
                     	<%@include file="update_pwd.jsp" %>
                    </div>
                    <div class="tab-pane fade" id="emp_mana" role="tabpanel" aria-labelledby="emp_mana-tab">
                     	<%@include file="management.jsp" %>
                    </div>
                  </div>
            </div>
        </div>
    </div>
    
    
    
    
    <script src="${pageContext.request.contextPath}/vendors/jquery/jquery-3.5.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/vendors/popper/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/vendors/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/Back_end/employee/js/index_backstage.js"></script>
</body>
</html>