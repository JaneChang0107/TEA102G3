<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>

<%
   MemberService memSvc =new MemberService();
   List<MemberVO> list =memSvc.getAll();
   pageContext.setAttribute("list",list);
%>

<html>
<head>
<meta charset="BIG5">
	<title>Insert title here</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet type" href="<%=request.getContextPath()%>/Back_end/employee/css/index_backstage.css">
<title>會員列表</title>

<style>

  h4 {
    color: blue;
    display: inline;
  }
  #membercol{
     height:70px;
  }
  #memberrow{
     height:110px;  
  }

</style>



</head>

<body bgcolor='E3F8F6'>
	<div class="container-fluid management_container"  id="mem_page">
<!-- 		<div class="row"> -->
<!-- 			<div class="col" > -->
<!-- 			尋找 -->
<!-- 				<input type="text" name=""> -->
<!-- 				<button type="button" id="button_search" class="btn btn-primary">查詢</button> -->
<!-- 			</div> -->

<!-- 		</div> -->
<!-- 		<div class="row"> -->
<!-- 			<div class="col"> -->
<!-- 				<div class="row listAll_row" id="membercol"> -->
<!-- 					<div class="col">會員id</div> -->
<!-- 					<div class="col">電子郵件</div> -->
<!-- 					<div class="col">姓名</div> -->
<!-- 					<div class="col">性別</div> -->
<!-- 					<div class="col">電話</div> -->
<!-- 					<div class="col">地址</div> -->
<!-- 					<div class="col">生日</div> -->
<!-- 					<div class="col">會員狀態</div> -->
<!-- 					<div class="col">修改</div> -->
<!-- 				</div> -->

<%-- 				<c:forEach var="memberVO" items="${list}" > --%>
<!-- 					<div class="row"  id="memberrow"> -->
<%-- 						<div class="col">${memberVO.m_id}</div> --%>
<%-- 						<div class="col">${memberVO.m_email}</div> --%>
<%-- 						<div class="col">${memberVO.m_name}</div> --%>
<%-- 						<div class="col">${memberVO.m_gender}</div> --%>
<%-- 						<div class="col">${memberVO.m_phone}</div> --%>
<%-- 						<div class="col">${memberVO.m_address}</div> --%>
<%-- 						<div class="col">${memberVO.m_birth}</div> --%>
<%-- 						<div class="col">${memberVO.m_statusByString}</div> --%>
<!-- 						<div class="col"> -->
<%-- 							<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/member/controller/MemberServlet" style="margin-bottom: 0px;"> --%>
<!-- 							<div class="row"> -->
<!-- 								<div class="col button_mem"><button type="button">未開通</div> -->
<!-- 								<div class="col button_mem"><button type="button">買家</div> -->
<!-- 							</div> -->
<!-- 							<div class="row">							 -->
<!-- 								<div class="col button_mem"><button type="button">未驗證</div> -->
<!-- 								<div class="col button_mem"><button type="button">賣家</div> -->
<!-- 							</div> -->
<%-- 							<input type="hidden" name="m_id" value="${memberVO.m_id}">  --%>
<!-- 							<input type="hidden" name="action" value="getOne_For_Update"> -->
<!-- 							</FORM> -->
<!-- 						</div>	 -->
<!-- 					</div> -->
<%-- 			</c:forEach> --%>
<!-- 			</div> -->
<!-- 		</div> -->
	</div>
	
<%-- 	<script src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script> --%>
<%--     <script src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script> --%>
<%--     <script src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script> --%>
<%--     <script src="<%=request.getContextPath()%>/Back_end/employee/js/index_backstage.js"></script> --%>
    <script>
// 	    var btn_add = document.getElementById("btn_add");
// 	    btn_add.addEventListener('click',function(){
<%-- 	    	location.href='<%=request.getContextPath()%>/Back_end/employee/newEmployee.jsp' --%>
// 	    });
    </script>



</body>
</html>