<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.member.model.*"%>
<%@page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
//拿到登入ID
		String m_id = session.getAttribute("loginId").toString();

//拿到memberVO
	    MemberService memSvc = new MemberService();
		MemberVO memberVO = memSvc.findOneMem(m_id);
	    session.setAttribute("memberVO", memberVO);
%>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>賣場介紹修改</title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/vendors/bootstrap-4.5.3-dist/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style>

</style>
</head>
<body style="background: #F5D2CD; height: 100%;">
	
<!-- header----------->
<div class="header">
	
	<div class="header">
		<jsp:include page="/Front_end/header.jsp"></jsp:include>
	</div>
	<div>
		<jsp:include page="/Front_end/index_Seller_Buttongroup.jsp"></jsp:include>
	</div>
<!-- header----------->

	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/member/controller/MemberServlet"
		name="form1" enctype="multipart/form-data">
		<table>
			<tr>
				<td>賣場名稱:</td>
				<td><input type="text" name="m_storename"
					value="${memberVO == null ? "" : memberVO.getM_storename()}"></td>
			</tr>
			<tr>
				<td>賣場簡介:</td>
				<td><input type="text" name="m_info"
					value="${memberVO == null ? "" : memberVO.getM_info()}"></td>
			</tr>
			<tr>
				<td>賣場封面:</td>
				<td><input type="file" name="m_cover" id="uploadImg"
					value=""></td>
					<img src="${memberVO.m_headpic2}">
					
			</tr>
		</table>
		<input type="hidden" name="action" value="updateSellstore">
		<input type="hidden" name="m_id" value="${memberVO.m_id}">
		<input type="submit" value="送出修改"></FORM>
	
	<!-- ----footer---- -->
	<div class="footer">
		<jsp:include page="/Front_end/footer.jsp"></jsp:include>
	</div>
</div>
	

</body>
</html>