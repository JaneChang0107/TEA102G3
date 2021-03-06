<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.employee.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    EmployeeService employeeSvc = new EmployeeService();
    List<EmployeeVO> list = employeeSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有員工資料 - listAllEmp.jsp</title>

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
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有員工資料 - listAllEmployee.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/Back_end/employee/select_page.jsp"><img src="<%=request.getContextPath()%>/Back_end/employee/images/1.png" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>員工id</th>
		<th>員工密碼</th>
		<th>身分證字號</th>
		<th>員工姓名</th>
		<th>性別</th>
		<th>生日</th>
		<th>員工信箱</th>
		<th>員工電話</th>
		<th>員工地址</th>
		<th>職稱</th>
		<th>員工狀態</th>
		<th>門市id</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="employeeVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${employeeVO.e_id}</td>
			<td>${employeeVO.e_password}</td>
			<td>${employeeVO.e_identity}</td>
			<td>${employeeVO.e_name}</td>
			<td>${employeeVO.e_gender}</td>
			<td>${employeeVO.e_birth}</td> 
			<td>${employeeVO.e_email}</td>
			<td>${employeeVO.e_phone}</td>
			<td>${employeeVO.e_address}</td>
			<td>${employeeVO.e_title}</td>
			<td>${employeeVO.e_status}</td>
			<td>${employeeVO.st_id}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/employee/controller/employeeServlet.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="e_id"  value="${employeeVO.e_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/employee/controller/employeeServlet.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="e_id"  value="${employeeVO.e_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>