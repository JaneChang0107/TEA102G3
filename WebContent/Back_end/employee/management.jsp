<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.employee.model.*"%>

<%
    EmployeeService employeeSvc = new EmployeeService();
    List<EmployeeVO> list = employeeSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="BIG5">
	<title>Insert title here</title>
<%-- 	<link rel="stylesheet" href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css"> --%>
<%--     <link rel="stylesheet type" href="<%=request.getContextPath()%>/Back_end/employee/css/index_backstage.css"> --%>
</head>
<body>
	<div class="container-fluid management_container">
		<div class="row">
			<div class="col">
				<!--錯誤表列 -->
				<c:if test="${not empty errorMsgs_status}">
					<font style="color:red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs_status}">
							<li style="color:red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				<c:remove var="errorMsgs_status"/>
			</div>
		</div>
		<div class="row">
			<div class="col search-col">
				<p>尋找</p>
			</div>
			<div class="col">
				<input type="text" name=""> 
				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
				  <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
				</svg>
			</div>
			<div class="col">
				<button type="button" id="btn_add" class="btn btn-outline-primary">新增</button>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<div class="row listAll_row">
					<div class="col">員工id</div>
					<div class="col">員工密碼</div>
					<div class="col">身分證字號</div>
					<div class="col">員工姓名</div>
					<div class="col">性別</div>
					<div class="col">生日</div>
					<div class="col">員工信箱</div>
					<div class="col">員工電話</div>
					<div class="col">員工地址</div>
					<div class="col">職稱</div>
					<div class="col">員工狀態</div>
					<div class="col">門市id</div>
					<div class="col">修改</div>
					<div class="col">刪除</div>
				</div>
				<c:forEach var="employeeVO" items="${list}">
				<div class="row">
					<div class="col">${employeeVO.e_id}</div>
					<div class="col">${employeeVO.e_password}</div>
					<div class="col">${employeeVO.e_identity}</div>
					<div class="col">${employeeVO.e_name}</div>
					<div class="col">${employeeVO.e_gender}</div>
					<div class="col">${employeeVO.e_birth}</div>
					<div class="col">${employeeVO.e_email}</div>
					<div class="col">${employeeVO.e_phone}</div>
					<div class="col">${employeeVO.e_address}</div>
					<div class="col">${employeeVO.e_title}</div>
					<div class="col">${employeeVO.e_status}</div>
					<div class="col">${employeeVO.st_id}</div>
					<div class="col">
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/login" style="margin-bottom: 0px;">
			     		<input type="submit" value="修改">
			     		<input type="hidden" name="e_id"  value="${employeeVO.e_id}">
			     		<input type="hidden" name="action"	value="getOne_For_Update"></FORM>
					</div>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/login" style="margin-bottom: 0px;">
					     <input type="submit" value="更新狀態">
					     <input type="hidden" name="e_id"  value="${employeeVO.e_id}">
					     <input type="hidden" name="e_status"  value="${employeeVO.e_status}">
					     <input type="hidden" name="action" value="update_status"></FORM>
					<div class="col">
					
					</div>
				</div>
				</c:forEach>	
			</div>
		</div>
	</div>



<%-- 	<script src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script> --%>
<%--     <script src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script> --%>
<%--     <script src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script> --%>
<%--     <script src="<%=request.getContextPath()%>/Back_end/employee/js/index_backstage.js"></script> --%>
    <script>
	    var btn_add = document.getElementById("btn_add");
	    btn_add.addEventListener('click',function(){
	    	location.href='<%=request.getContextPath()%>/Back_end/employee/newEmployee.jsp'
	    });
    </script>
</body>
</html>