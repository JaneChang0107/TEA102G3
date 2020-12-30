<%@page import="java.util.Set"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="redis.clients.jedis.Jedis"%>
<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%
Jedis jedis = new Jedis("localhost", 6379);
jedis.auth("123456");



    jedis.close();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>


<td></td>


</body>
</html>