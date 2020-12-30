<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
<title>viewseller</title>
<style>
table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

</head>
<body>
	<table id="table-1">
		<tr>
			<td><h3>viewseller</h3>
				<h4>home</h4></td>
		</tr>

	</table>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<ul>

		<li><a
			href='<%=request.getContextPath()%>/Back_end/ViewSeller/listAllviewseller.jsp'>List</a>
			all orderlist. <br> <br></li>
		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/ViewSellerServlet">
				<b>輸入:</b> <input type="text" name="v_id"> <input
					type="hidden" name="action" value="getOne_For_Display"> <input
					type="submit" value="送出">
			</FORM>
		</li>

		<jsp:useBean id="viewsellerSvc" scope="page"
			class="com.viewseller.model.ViewsellerService" />

		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/ViewSellerServlet">
				<b>選擇編號:</b> <select size="1" name="v_id">
					<c:forEach var="viewsellerVO" items="${viewsellerSvc.all}">
						<option value="${viewsellerVO.v_id}">${viewsellerVO.v_id}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>
	</ul>

	<h3>管理</h3>

	<ul>
		<li><a
			href='<%=request.getContextPath()%>/Back_end/ViewSeller/addviewseller.jsp'>Add</a>
			a new orderdetail.</li>
	</ul>






</body>
</html>