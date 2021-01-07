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
	<div class="container-fluid management_container">
		<div class="row">
			<div class="col">
<!-- 				錯誤表列 -->
<%-- 				<c:if test="${not empty errorMsgs}"> --%>
<!-- 					<font style="color: red">請修正以下錯誤:</font> -->
<!-- 					<ul> -->
<%-- 						<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 							<li style="color: red">${message}</li> --%>
<%-- 						</c:forEach> --%>
<!-- 					</ul> -->
<%-- 				</c:if> --%>
				<c:remove var="errorMsgs_status" />
			</div>
		</div>
		<div class="row">
<!-- 			<div class="col searcj-col"> -->
				
<!-- 			</div> -->
			<div class="col" >
			尋找
				<input type="text" name="">
				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
					fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
	     <path	d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z" />
				</svg>
			</div>

		</div>
		<div class="row">
			<div class="col">
				<div class="row listAll_row" id="membercol">
					<div class="col">會員id</div>
					<div class="col">電子郵件</div>
<!-- 					<div class="col">密碼</div> -->
					<div class="col">姓名</div>
					<div class="col">性別</div>
					<div class="col">電話</div>
					<div class="col">地址</div>
					<div class="col">生日</div>
<!-- 					<div class="col">大頭照</div> -->
					<div class="col">會員狀態</div>
					<div class="col">修改</div>
<!-- 					<div class="col">刪除</div> -->
				</div>

				<c:forEach var="memberVO" items="${list}" >
					<div class="row"  id="memberrow">
						<div class="col">${memberVO.m_id}</div>
						<div class="col">${memberVO.m_email}</div>
<%-- 						<div class="col">${memberVO.m_password}</div> --%>
						<div class="col">${memberVO.m_name}</div>
						<div class="col">${memberVO.m_gender}</div>
						<div class="col">${memberVO.m_phone}</div>
						<div class="col">${memberVO.m_address}</div>
						<div class="col">${memberVO.m_birth}</div>
<%-- 						<div class="col"><img src="${memberVO.m_headpic2}" width="100" height="100"></div> --%>
						<div class="col">${memberVO.m_statusByString}</div>
						<div class="col">
							<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/member/controller/MemberServlet" style="margin-bottom: 0px;">
							<input type="submit" value="修改"> 
							<input type="hidden" name="m_id" value="${memberVO.m_id}"> 
							<input type="hidden" name="action" value="getOne_For_Update">
							</FORM>
						</div>	
<!-- 							<div class="col"> -->
							
<!-- 						    </div> -->
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