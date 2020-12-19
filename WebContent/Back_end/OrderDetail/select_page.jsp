<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>orderdetail</title>

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
<marquee >
	<table id="table-1">
		<tr>
	
			<td><h3><marquee  onMouseOver="this.stop()" onMouseOut="this.start()">orderdetail</marquee></h3>
				<h4>home</h4></td>
		</tr>
	</table>
</marquee>
	

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
			href='<%=request.getContextPath()%>/back_end/OrderDetail/listAllOrderdetail.jsp'>List</a>
			all orderlist. <br>
		<br></li>
		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/OrderdetailServlet">
				<b>輸入:</b> <input type="text" name="od_id"> <input
					type="hidden" name="action" value="getOne_For_Display"> <input
					type="submit" value="送出">
			</FORM>
		</li>

		<jsp:useBean id="orderdetailSvc" scope="page"	class="com.orderdetail.model.OrderdetailService" />

		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/OrderdetailServlet">
				<b>選擇編號:</b> <select size="1" name="od_id">
					<c:forEach var="orderdetailVO" items="${orderdetailSvc.all}">
						<option value="${orderdetailVO.od_id}">${orderdetailVO.od_id}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>





<!-- 		<li> -->
<!-- 			<FORM METHOD="post" -->
<%-- 				ACTION="<%=request.getContextPath()%>/OrderdetailServlet"> --%>
<!-- 				<b>選擇編號:</b> <select size="1" name="o_id"> -->
<%-- 					<c:forEach var="orderdetailVO" items="${orderdetailSvc.all}"> --%>
<%-- 						<option value="${orderdetailVO.o_id}">${orderdetailVO.o_id} --%>
<%-- 					</c:forEach> --%>
<!-- 				</select> <input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 				<input type="submit" value="送出"> -->
<!-- 			</FORM> -->
<!-- 		</li> -->


<!-- 		<li> -->
<!-- 			<FORM METHOD="post" -->
<%-- 				ACTION="<%=request.getContextPath()%>/OrderdetailServlet"> --%>
<!-- 				<b>選擇編號:</b> <select size="1" name="p_id"> -->
<%-- 					<c:forEach var="orderdetailVO" items="${orderdetailSvc.all}"> --%>
<%-- 						<option value="${orderdetailVO.p_id}">${orderdetailVO.p_id} --%>
<%-- 					</c:forEach> --%>
<!-- 				</select> <input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 				<input type="submit" value="送出"> -->
<!-- 			</FORM> -->
<!-- 		</li> -->

	</ul>

	<h3>管理</h3>

	<ul>
		<li><a
			href='<%=request.getContextPath()%>/back_end/OrderDetail/addorderdetail.jsp'>Add</a>
			a new orderdetail.</li>
	</ul>


</body>
</html>