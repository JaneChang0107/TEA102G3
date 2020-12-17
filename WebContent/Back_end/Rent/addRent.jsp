<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.rent.model.*"%>
<%@ page import="com.store.model.*"%>
<%@ page import="com.productType.model.*"%>
<%@ page import="com.employee.model.*"%>

<%
	RentVO rentVO = (RentVO) request.getAttribute("rentVO");
	ProductTypeVO ptVO = (ProductTypeVO) request.getAttribute("ptVO");
	StoreVO storeVO = (StoreVO) request.getAttribute("storeVO");
	EmployeeVO employeeVO = (EmployeeVO) request.getAttribute("employeeVO");
%>

<%
	java.sql.Timestamp ts = null;
	try {
		ts = rentVO.getR_adddate();
	} catch (Exception e) {
		ts = new java.sql.Timestamp(System.currentTimeMillis());
	}

	java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String nowTime = df.format(ts);
	java.sql.Timestamp r_adddate = java.sql.Timestamp.valueOf(nowTime);
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>員工資料新增 - addRent.jsp</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
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

<style>
table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}

/* Selects any <input> element that is read-only */
/* Supported in Firefox with a prefix */
input:-moz-read-only {
	background-color: #ccc;
	color: #636363;
}

/* Supported in Blink/WebKit/Edge without a prefix */
input:read-only {
	background-color: #ccc;
	color: #636363;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>出租品資料新增 - addRent.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="<%=request.getContextPath()%>/Back_end/Rent/index.jsp"><img
						src="<%=request.getContextPath()%>/images/back1.png" width="40"
						height="40" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料新增:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/RentServlet"
		name="form1">
		<table>


			<tr>
				<td>出租品種類:</td>
				<td><select name="r_type">
						<option value="遊戲主機">遊戲主機</option>
						<option value="遊戲片">遊戲片</option>
						<option value="遊戲周邊">遊戲周邊</option>
				</select>
			</tr>
			<tr>
				<td>出租品名稱:</td>
				<td><input type="TEXT" name="r_name" size="45"
					value="<%=(rentVO == null) ? "動物森友會" : rentVO.getR_name()%>" /></td>
			</tr>
			<tr>
				<td>商品描述:</td>
				<td><input type="TEXT" name="r_describe" size="45"
					value="<%=(rentVO == null) ? "外殼破損" : rentVO.getR_describe()%>" /></td>
			</tr>

			<tr>
				<td>貨況:</td>
				<td><select name="r_situation">
						<option value="全新品">全新品</option>
						<option value="二手品">二手品</option>

				</select>
			</tr>

			<tr>
				<td>貨物狀態:</td>
				<td><select name="r_status">
						<option value="未上架">未上架</option>
						<option value="上架中">上架中</option>
						<option value="出租中">出租中</option>

				</select>
			</tr>

			<tr>
				<td>出租品價格:</td>
				<td><input type="TEXT" name="r_price" size="45"
					value="<%=(rentVO == null) ? "10" : rentVO.getR_price()%>" /></td>
			</tr>

			<tr>
				<td>新增日期:</td>
				<td><%=(rentVO == null) ? r_adddate : rentVO.getR_adddate()%></td>
			</tr>
			<tr>
				<jsp:useBean id="ptSvc" scope="page"
					class="com.productType.model.ProductTypeService" />
				<td>種類ID:</td>
				<td><select size="1" name="pt_id">
						<c:forEach var="ptVO" items="${ptSvc.all}">
							<option value="${ptVO.pt_id}"
								${(rentVO.pt_id==ptVO.pt_id)? 'selected':'' }>${ptVO.pt_id}
						</c:forEach>
				</select></td>
			</tr>

			<jsp:useBean id="empSvc" scope="page"
				class="com.employee.model.EmployeeService" />
			<tr>
				<td>新增者ID:</td>
				<td><select size="1" name="e_addid">
						<c:forEach var="employeeVO" items="${empSvc.all}">
							<option value="${employeeVO.e_id}"
								${(rentVO.e_addid==employeeVO.e_id)? 'selected':'' }>${employeeVO.e_id}
						</c:forEach>
				</select></td>
			</tr>

			<jsp:useBean id="storeSvc" scope="page"
				class="com.store.model.StoreService" />
			<tr>
				<td>門市ID:</td>
				<td><select size="1" name="st_id">
						<c:forEach var="storeVO" items="${storeSvc.all}">
							<option value="${storeVO.st_id}"
								${(rentVO.st_id==storeVO.st_id)? 'selected':'' }>${storeVO.st_id}
						</c:forEach>
				</select></td>
			</tr>

		</table>
		<br> <input type="hidden" name="r_adddate" value="<%=r_adddate%>">
		<input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
	</FORM>
</body>

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%
	// 	java.sql.Timestamp r_adddate = null;
	//   try {
	// 	  r_adddate = rentVO.getR_adddate();
	//    } catch (Exception e) {
	// 	   r_adddate = new java.sql.Timestamp(System.currentTimeMillis());
	//    }
%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>



</html>