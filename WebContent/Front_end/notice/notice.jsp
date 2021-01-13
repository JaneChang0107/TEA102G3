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
  
  .mybody {
	background-color: #E3F8F6;
}

.myform {
	border: 1px solid gray;
	background-color: rgb(243, 241, 241);
	width: 400px;
	height: 300px;
	text-align: center;
	margin: 20px auto;
}

.topcol {
	width: auto;
	height: 80px;
	background-color: #6CCFF3;
	margin: 0px 0px 20px 0px;
	font-size: 24px;
}

#inputEmail3 {
	width: 300px;
}

#inputPassword3 {
	width: 300px;
	margin-bottom: 5px;
}

.content {
	text-align: center;
	margin-top: 50px;
	margin-bottom: 200px;
}

#icons {
	left: 0px;
}
  
  #headimg {
	width: 200px;
	height: 200px;
	border-radius: 50%;
	margin-right: 20px;
}
  
#pills-home-tab, #pills-profile-tab {
    text-align: center;
    margin: auto;
    position: relative;
    left: 140%;
    font-size: 24px;
    padding: 15px 150px;
}

.content {
	margin-top: 100px;
	margin-bottom: 200px;
}

#myfiles,#changepw{
    text-align:left;
    margin:auto;
    background-color: #e9e9e9;
    width:800px;
    height:900px;
    font-size:24px;
}
#changepw{
    height:300px;
}

#revise{
    background-color: #FFA000;
    width: 100px;
    border: 1px solid #707070;
    margin-left: 20px;
}
#order{
    margin: auto;
}
.card{
    margin: auto;
    width: 800px;
    text-align:left;
}
h5.card-header {
    background-color: #cce5ff;
}

  
  
</style>
</head>
<body>
<body class="mybody">


	<div class="content">
	<div>
	     <jsp:include page="../members/SelectBar.jsp"></jsp:include>
	</div>



<%@ include file="page1.file" %>
<c:forEach var="memberVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	<h3>
	<div class="div1">
	<p>您購買的${memberVO.p_name}數量<b>${memberVO.od_count}</b>${memberVO.o_status}</p>
	</div>
	</h3>
</c:forEach>	
	
	
	

<%@ include file="page2.file" %>

</div>

</body>
</html>