<!-- 設定JSP編碼 -->
<%-- <%@page import="com.video_record.model.Video_recordVO"%> --%>
<%-- <%@page import="com.video_record.model.Video_recordService"%> --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 設定標籤庫 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.class_info.model.*"%>

<%
	Class_infoService Srv = new Class_infoService();
	List<Class_infoVO> list = Srv.get_ROWNUM_8();
	pageContext.setAttribute("list", list);
%>

<!doctype html>
<html lang="en">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=yes">
<!-- video.js CSS -->
<link
	href="<%=request.getContextPath()%>/vendors/video-js/css/video-js.css"
	rel="stylesheet" />
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css">
<!-- css -->
<link href="<%=request.getContextPath()%>/css/index.css"
	rel="stylesheet" />
<link href="<%=request.getContextPath()%>/css/header.css"
	rel="stylesheet" />

<!-- <link href="https//cdnjs.cloudflare.com/ajax/libs/animate.css/3.7.0/animate.min.css" rel="stylesheet"> -->
<title>TOMATO - 讓你分分鐘鐘都在學習的平台</title>
</head>

<body>
	<%@ include file="header.jsp"%>
	<!-- 首頁圖 -->
	<div class="container-fluid sidebar" style="position: relative;">
		<div id="search">
			<form class="form-inline my-2 my-lg-0" style="position: left"
				action="<%=request.getContextPath()%>/Class_info/Class_Introduction">
				<input name="action" value="search" type="hidden"> <input
					name="class_status" value="4" type="hidden"> <input
					name="Category" value="class_list_search" type="hidden"> <input
					class="form-control mr-sm-2" name="class_name" type="search"
					placeholder="輸入你想要的課程" aria-label="Search">
				<button class="btn btn-outline-danger my-2 my-sm-0" type="submit">Search</button>
			</form>
		</div>
	</div>
	<!-- container -->
	<div class="container" style="margin-bottom: 50px;">
		<!--********************************************************************* 課程卡片 熱門課程-->
		<div class="class-card" style="height: 50px; margin-top: 50px;">
			<h2>熱門課程</h2>
		</div>
		<div class="row">
			<div>
				<div class="col-sm-12" style="margin-top: 20px;">
					<div class="card-deck">
						<c:forEach var="class_infoVO" items="${list}" varStatus="status" begin="0" end="3">
							<div class="card">
								<a
									href="<%=request.getContextPath()%>/Class_info/Class_learnServlet?action=class_Introduction&class_id=${class_infoVO.class_id}">

									<img class="card-img-top"
									src="<%=request.getContextPath()%>/Class_info/Class_Introduction?action=class_pic_sm&class_id=${class_infoVO.class_id}"
									alt="Card image cap">
									<div class="card-body">
										<h6 class="card-title" style="height: 30px">${class_infoVO.class_name}</h6>
								</a>
								<p class="card-text" style="line-height: 1">
									<small class="text-muted"> 授課老師：<a
										href="<%=request.getContextPath()%>/Class_info/Class_Introduction?action=class_Introduction&class_id=${class_infoVO.class_id}">
											${class_infoVO.member_id}</a><br>
										課程評價：${class_infoVO.member_id}<br>
										課程時數：${class_infoVO.member_id}<br>
										購買人數：${class_infoVO.member_id}<br>
									</small>
								</p>
							</div>
					</div>
					</c:forEach>
				</div>
			</div>
			<div class="col-sm-12" style="margin-top: 20px;">
				<div class="card-deck">
					<c:forEach var="class_infoVO" items="${list}" varStatus="status"
						begin="4" end="7">
						<div class="card">
							<a
								href="<%=request.getContextPath()%>/Class_info/Class_learnServlet?action=class_Introduction&class_id=${class_infoVO.class_id}">

								<img class="card-img-top"
								src="<%=request.getContextPath()%>/Class_info/Class_Introduction?action=class_pic_sm&class_id=${class_infoVO.class_id}"
								alt="Card image cap">
								<div class="card-body">
									<h6 class="card-title" style="height: 30px">${class_infoVO.class_name}</h6>
							</a>
							<p class="card-text" style="line-height: 1">
								<small class="text-muted"> 授課老師：<a
									href="<%=request.getContextPath()%>/Class_info/Class_Introduction?action=class_Introduction&class_id=${class_infoVO.class_id}">
										${class_infoVO.member_id}</a><br>
									課程評價：${class_infoVO.member_id}<br>
									課程時數：${class_infoVO.member_id}<br>
									購買人數：${class_infoVO.member_id}<br>
								</small>
							</p>
						</div>
				</div>
				</c:forEach>
			</div>
		</div>
	</div>
	</div>

	<!--********************************************************************* 課程卡片 猜你想學-->
	<div class="class-card" style="height: 50px; margin-top: 50px;">
		<h2>猜你想學</h2>
	</div>
	<div class="row">
		<div>
			<div class="col-sm-12" style="margin-top: 20px;">
				<div class="card-deck">
					<c:forEach var="class_infoVO" items="${ROWNUM_8_Bysub_class}" varStatus="status"
						begin="0" end="3">
						<div class="card">
							<a
								href="<%=request.getContextPath()%>/Class_info/Class_learnServlet?action=class_Introduction&class_id=${class_infoVO.class_id}">

								<img class="card-img-top"
								src="<%=request.getContextPath()%>/Class_info/Class_Introduction?action=class_pic_sm&class_id=${class_infoVO.class_id}"
								alt="Card image cap">
								<div class="card-body">
									<h6 class="card-title" style="height: 30px">${class_infoVO.class_name}</h6>
							</a>
							<p class="card-text" style="line-height: 1">
								<small class="text-muted"> 授課老師：<a
									href="<%=request.getContextPath()%>/Class_info/Class_Introduction?action=class_Introduction&class_id=${class_infoVO.class_id}">
										${class_infoVO.member_id}</a><br>
									課程評價：${class_infoVO.member_id}<br>
									課程時數：${class_infoVO.member_id}<br>
									購買人數：${class_infoVO.member_id}<br>
								</small>
							</p>
						</div>
				</div>
				</c:forEach>
			</div>
		</div>
		<div class="col-sm-12" style="margin-top: 20px;">
			<div class="card-deck">
				<c:forEach var="class_infoVO" items="${ROWNUM_8_Bysub_class}" varStatus="status"
					begin="4" end="7">
					<div class="card">
						<a
							href="<%=request.getContextPath()%>/Class_info/Class_learnServlet?action=class_Introduction&class_id=${class_infoVO.class_id}">

							<img class="card-img-top"
							src="<%=request.getContextPath()%>/Class_info/Class_Introduction?action=class_pic_sm&class_id=${class_infoVO.class_id}"
							alt="Card image cap">
							<div class="card-body">
								<h6 class="card-title" style="height: 30px">${class_infoVO.class_name}</h6>
						</a>
						<p class="card-text" style="line-height: 1">
							<small class="text-muted"> 授課老師：<a
								href="<%=request.getContextPath()%>/Class_info/Class_Introduction?action=class_Introduction&class_id=${class_infoVO.class_id}">
									${class_infoVO.member_id}</a><br>
								課程評價：${class_infoVO.member_id}<br>
								課程時數：${class_infoVO.member_id}<br>
								購買人數：${class_infoVO.member_id}<br>
							</small>
						</p>
					</div>
			</div>
			</c:forEach>
		</div>
	</div>
	</div>
	</div>

	<!--********************************************************************* 課程卡片 募資課程-->
	<div class="class-card" style="height: 50px; margin-top: 50px;">
		<h2>募資課程</h2>
	</div>
	<div class="row">
		<div>
			<div class="col-sm-12" style="margin-top: 20px;">
				<div class="card-deck">
					<c:forEach var="class_infoVO" items="${list}" varStatus="status"
						begin="0" end="3">
						<div class="card">
							<a
								href="<%=request.getContextPath()%>/Class_info/Class_learnServlet?action=class_Introduction&class_id=${class_infoVO.class_id}">

								<img class="card-img-top"
								src="<%=request.getContextPath()%>/Class_info/Class_Introduction?action=class_pic_sm&class_id=${class_infoVO.class_id}"
								alt="Card image cap">
								<div class="card-body">
									<h6 class="card-title" style="height: 30px">${class_infoVO.class_name}</h6>
							</a>
							<p class="card-text" style="line-height: 1">
								<small class="text-muted"> 授課老師：<a
									href="<%=request.getContextPath()%>/Class_info/Class_Introduction?action=class_Introduction&class_id=${class_infoVO.class_id}">
										${class_infoVO.member_id}</a><br>
									課程評價：${class_infoVO.member_id}<br>
									課程時數：${class_infoVO.member_id}<br>
									購買人數：${class_infoVO.member_id}<br>
								</small>
							</p>
						</div>
				</div>
				</c:forEach>
			</div>
		</div>
		<div class="col-sm-12" style="margin-top: 20px;">
			<div class="card-deck">
				<c:forEach var="class_infoVO" items="${list}" varStatus="status"
					begin="4" end="7">
					<div class="card">
						<a
							href="<%=request.getContextPath()%>/Class_info/Class_learnServlet?action=class_Introduction&class_id=${class_infoVO.class_id}">

							<img class="card-img-top"
							src="<%=request.getContextPath()%>/Class_info/Class_Introduction?action=class_pic_sm&class_id=${class_infoVO.class_id}"
							alt="Card image cap">
							<div class="card-body">
								<h6 class="card-title" style="height: 30px">${class_infoVO.class_name}</h6>
						</a>
						<p class="card-text" style="line-height: 1">
							<small class="text-muted"> 授課老師：<a
								href="<%=request.getContextPath()%>/Class_info/Class_Introduction?action=class_Introduction&class_id=${class_infoVO.class_id}">
									${class_infoVO.member_id}</a><br>
								課程評價：${class_infoVO.member_id}<br>
								課程時數：${class_infoVO.member_id}<br>
								購買人數：${class_infoVO.member_id}<br>
							</small>
						</p>
					</div>
			</div>
			</c:forEach>
		</div>
	</div>
	</div>
	</div>
	<!-- container end -->
	</div>
	<!--  聊天室-->
	<!-- 	<img id="talk" -->
	<%-- 				src="<%=request.getContextPath()%>/img/icon/chat.png"> --%>

	<%-- 	<%@ include file="footer.jsp"%> --%>

	<form action="<%=request.getContextPath()%>/chat/NameServlet" method="POST">
		<br> <input type="image" id="talk" src="<%=request.getContextPath()%>/img/icon/chat.png" alt="Submit">
	</form>
	
	<%@ include file="footer.jsp"%>
	
<!-- 	以下為測試用按鈕 -->
	<form action="<%=request.getContextPath()%>/class_info/class_findinterestServlet" method="GET">
	<input type="hidden" name="action" value="SearchInterest">
		<input type="submit" value="測試findinterest">
	</form>
<!-- 	以上為測試用按鈕 -->
	<!-- JavaScript -->
	<script src="./vendors/jquery/jquery-3.5.1.min.js"></script>
	<script src="./vendors/popper/popper.min.js"></script>
	<script src="./vendors/bootstrap/js/bootstrap.min.js"></script>
	<!-- <script src=""></script> -->


</body>

</html>