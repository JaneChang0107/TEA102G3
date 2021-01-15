<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="com.runlight.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
RunlightVO runlightVO = new RunlightVO();
String value=runlightVO.getValue();
pageContext.setAttribute("value",value);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>輸入公告</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>


<body>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


   
<div id="div_input">


<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/light.do" name="form1">
<table class="table table-dark table-hover">

	<tr>
		<td>輸入:</td>
		<td>
		<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />   
<select class="form-select" aria-label="Default select example">
  <option selected >all為系統公告或是輸入會員編號</option>
  <option id="key" value="all">all</option>
  <c:forEach var="mVO" items="${memSvc.all}">
  <option id="key" value="${mVO.m_id}">${mVO.m_id}</option>
  </c:forEach>
  
</select>
		</td>
	</tr>
	
	<tr>
		<td>內容:</td>
		<td><input name="value" id="value" type="text"></td>
	</tr>
</table>
<input type="hidden" name="action" value="show">
<!-- <input type="submit" value="送出新增"> -->
<button type="button" id="btn_send">送出新增</button>
</FORM>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js" integrity="sha384-q2kxQ16AaE6UbzuKqyBE9/u/KzioAlnx2maXQHiDX9d4/zp8Ok3f+M7DPm+Ib6IU" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-pQQkAEnwaBkjpqZ8RU1fF1AKtTcHJwFl3pblpTlHXybJjHpMYo79HY3hIi4NKxyj" crossorigin="anonymous"></script>
</body>
</html>


