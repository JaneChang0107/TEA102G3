<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>登入</h1>
<jsp:useBean id="mService" scope="page" class="com.member.model.MemberService"></jsp:useBean>
	<form action="<%= request.getContextPath() %>/ProductServlet" method="post">
		<div>
			<select name="mid">
			<c:forEach var="mVO" items="${mService.all}">
				<option value="${mVO.m_id}">${mVO.m_id}</option>
			</c:forEach>
			</select>
		</div>
		<div>
			<input type="hidden" name="action" value="showSell">
			<input type="submit" value="送出">
		</div>
	</form>
</body>
</html>