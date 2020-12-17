<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>

<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
	crossorigin="anonymous">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>IBM Rent: Home</title>

<style>
tr,td {
/* 	border:solid 1px; */
}

table#table-1 {
	width: 100%;
	background-color: #6CCFF3;
	margin-top: 5px;
	margin-bottom: 10px;
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

.xxx {
	/* 	border: solid 1px; */
	margin:0px auto;
	background-color: white;
	width:1000px;
	height:220px;
	
}
.ooo {
	/* 	border: solid 1px; */
	margin:0px auto;
	
	
}
</style>

</head>
<body style="background-color: #E3F8F6">

	<table id="table-1">
		<tr>
		<td class="ooo"><img src='<%=request.getContextPath() %>/images/logo.png' width=199 height=77><h3>出租品管理系統</h3></td>
		
		</tr>
	</table>

	<p align=center>This is the Home page for IBM Rent: Home</p>
	<div class="xxx">

		<table >
			<tr>
				<td>
					<h3>出租品管理:</h3>	
				</td>
				<td><a
					href='<%=request.getContextPath()%>/Back_end/Rent/addRent.jsp'  class="btn btn-outline-primary btn-sm">新增出租品</a></td>
			
				
			</tr>

			
				<tr>

					<td>
						<h3>資料查詢:</h3> <%-- 錯誤表列 --%> <c:if test="${not empty errorMsgs}">
							<font style="color: red"　>※請修正以下錯誤:</font>
							<br>
							<tr>
								<c:forEach var="message" items="${errorMsgs}">
									<td><font style="color: red">${message}</font></td>
								</c:forEach>
							</tr>
						</c:if>
					</td>

					<td></td>

				</tr>
				<tr>

					<td><b>輸入出租品編號<br> (如R00001):
					</b></td>

					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/RentServlet">
							<input type="text" name="r_id"> <input type="hidden"
								name="action" value="getOne_For_Display"> <input
								type="submit" value="送出">

						</FORM>
					</td>

				</tr>
				<tr>

					<td><b>選擇出租品編號:</b></td>

					<td><jsp:useBean id="rentSvc" scope="page"
							class="com.rent.model.RentService" />
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/RentServlet">
							<select size="1" name="r_id">
								<c:forEach var="rentVO" items="${rentSvc.all}">
									<option value="${rentVO.r_id}">${rentVO.r_id}
								</c:forEach>
							</select> <input type="hidden" name="action" value="getOne_For_Display">
							<input type="submit" value="送出">
						</FORM></td>

				</tr>
				<tr>

					<td><b>查詢所有出租品:</b></td>
					<td><a
						href='<%=request.getContextPath()%>/Back_end/Rent/listAllRent.jsp' class="btn btn-outline-primary btn-sm">查詢全部</a></td>
				</tr>
			</table>

	</div>

	<!-- Option 1: Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
		crossorigin="anonymous"></script>
</body>
</html>