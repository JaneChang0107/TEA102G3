<%@page import="java.util.List"%>
<%@page import="com.member.model.MemberVO"%>
<%@page import="com.member.model.MemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
String m_id = session.getAttribute("loginId").toString();
MemberService memberSvc =new MemberService();
List<MemberVO> list=memberSvc.getNotice(m_id);
pageContext.setAttribute("list",list);

%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>通知總覽</title>
<div class="header">
	<jsp:include page="../header.jsp"></jsp:include>
</div>


<style>
  
  .div1 {
    border: 3px solid #CCCCFF;
    padding: 5px;
    text-align: center;
    width: 1000px;
    height: 70px;
    margin: auto;
    margin-top: 10px;
    background-color: white;
  }
</style>
</head>
<body>

<%@ include file="page1.file" %>
<c:forEach var="memberVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	<h3>
	<div class="div1"><p>${memberVO.m_name}的${memberVO.p_name}數量${memberVO.od_count}${memberVO.o_status}</p></div>
	</h3>
</c:forEach>	
	
	
	

<%@ include file="page2.file" %>



</body>
</html>