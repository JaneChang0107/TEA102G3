<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	// 	pageContext.setAttribute("memberID", "M00001");
	//     HttpSession session = request.getSession();
	//     loginId=session.getAttribute(loginId);
%>

<html>

<head>
<meta charset="utf-8">
<title>YuXiKun</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap 的 CSS -->

<style>
#icon {
	position: fixed;
	right: 20px;
	bottom: 80px;
	height: 75px;
	z-index: 2;
}
</style>

</head>


<footer id="id_footer">
	<img
		src="<%=request.getContextPath()%>/images/white_LOGO字在外版(revised).png"
		class="logo" id="footerlogo">
	<div class="footerwords" id="footerwords">
		<span class="footerwords">關於我們</span><span class="footerwords">隱私權政策</span><br>
		<span class="footerwords">網站地圖</span><span class="footerwords">常用問答</span><br>
		<span class="footerwords">服務條款</span>
	</div>

	<div>
		<c:if test="${loginId != null}">
			<form id="myForm"
				action="<%=request.getContextPath()%>/websocketchat/NameServlet"
				method="POST" style="position:fixed" >
				<input type="image" id="icon" value="賣場聊聊"
					src="<%=request.getContextPath()%>/images/chaticon.png"
					alt="Submit"> <input type="hidden" name="m_id"
					value="${memberID}"> <input type="hidden" name="action"
					value="">
			</form>
		</c:if>

	</div>
	<div class="copyright">Copyright © 2020 YuXiKun Co. ,Ltd. All
		rights reserved</div>

</footer>

<script
	src='//production-assets.codepen.io/assets/common/stopExecutionOnTimeout-b2a7b3fe212eaa732349046d8416e00a9dec26eb7fd347590fbced3ab38af52e.js'></script>
<!-- <script src='https://code.jquery.com/jquery-2.2.4.min.js'></script> -->

<!-- body 結束標籤之前，載入Bootstrap 的 JS 及其相依性安裝(jQuery、Popper) -->

</body>

</html>