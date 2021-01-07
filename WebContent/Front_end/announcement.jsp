<%@page import="java.util.List"%>
<%@page import="redis.clients.jedis.Jedis"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
 <%
 String m_id= session.getAttribute("loginId").toString();
 

	
 %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>announcement</title>
<div class="header">
	<jsp:include page="header.jsp"></jsp:include>
</div>
</head>
<body>



<div class="footer">
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>