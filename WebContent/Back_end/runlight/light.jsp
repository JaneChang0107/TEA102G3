<%@page import="java.util.Set"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="redis.clients.jedis.Jedis"%>
<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%


String test=(String)request.getAttribute("rl");
//     System.out.println(test);

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>

<h1>
<marquee  onMouseOver="this.stop()" onMouseOut="this.start()">${rl}</marquee>
</h1>


</body>
</html>