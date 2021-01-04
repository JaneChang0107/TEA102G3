<%@page import="com.sun.xml.internal.bind.ValidationEventLocatorEx"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.viewseller.model.*"%>

<%
	ViewsellerVO viewsellerVO = (ViewsellerVO) request.getAttribute("viewsellerVO");
ViewsellerService Srv = new ViewsellerService();
List<ViewsellerVO> list = Srv.get();
pageContext.setAttribute("list", list);
%>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>新增評價</title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/setcomment.css">


</head>
<body>

	<div class="header">
		<jsp:include page="/Front_end/header.jsp"></jsp:include>
	</div>


  <FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/ViewSellerServlet" name="form1">
		<table>

			<tr>
				<!-- 	綁訂單編號 動態顯示 -->
				<td>訂單編號:</td>
				<td>
					<div id="member_o_id">
						<span id="o_id" name="o_id">${viewsellerVO.o_id}</span>
					</div>
				</td>
<!-- 				<td><input type="TEXT" name="o_id" size="45" -->
<%-- 					value="<%=(viewsellerVO == null) ? "O00001" : viewsellerVO.getO_id()%>" /></td> --%>
			</tr>

			<tr>
				<!-- 	綁訂單編號 動態顯示 -->
				<td>買家名稱:</td>
				<td>
					<div id="member_buyid">
						<span id="m_buyid" name="m_buyid">${viewsellerVO.m_buyid}</span>
					</div>
				</td>

<!-- 				<td><input type="TEXT" name="m_buyid" size="45" -->
<%-- 					value="<%=(viewsellerVO == null) ? "M00001" : viewsellerVO.getM_buyid()%>" /></td> --%>
			</tr>


			<tr>
				<!-- 	綁訂單編號 動態顯示 -->
				<td>賣家名稱:</td>
				<td>
					<div id="member_sellid">
						<span id="m_sellid" name="m_sellid">${viewsellerVO.m_sellid}</span>
					</div>
				</td>
<!-- 				<td><input type="TEXT" name="m_sellid" size="45" -->
<%-- 					value="<%=(viewsellerVO == null) ? "M00002" : viewsellerVO.getM_sellid()%>" /></td> --%>
			</tr>

			<tr>
				<td>好壞評價:</td>
				<td><input type="radio" name="v_gb" value="好評">好評 <input
					type="radio" name="v_gb" value="負評">負評</td>
			</tr>

			<tr>
				<td>評論內容:</td>
				<td><input type="TEXT" name="v_comment" size="45"
					value="<%=(viewsellerVO == null) ? "" : viewsellerVO.getV_comment()%>" />
				</td>
			</tr>

			<tr>
				<!-- 	綁訂單編號 動態顯示 -->
				<td>評價日期:</td>
				<td><input type="text" name="v_date" id="v_date" size="40"
					placeholder="請選擇日期" /></td>
			</tr>


		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="確認送出">

	</FORM>



	

	<article class="task_container">
		<button type="button" class="btn_empty">清空</button>
		<h1 class="title1">評價</h1>

		<div class="task_add_block">
			<input type="text" class="task_name" placeholder="留下您的評價">
			<button type="button" class="task_add">送出</button>
		</div>

		<div class="task_list_parent">
			<ul class="task_list">
			</ul>
		</div>
	</article>





	<div class="footer">
		<jsp:include page="/Front_end/footer.jsp"></jsp:include>
	</div>
	<script
		src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/setcomment.js"></script>

</body>

<%
	java.sql.Timestamp v_date = null;

	try {
		v_date = viewsellerVO.getV_date();
	} catch (Exception e) {
		v_date = new java.sql.Timestamp(System.currentTimeMillis());
	}
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
<script>
	$.datetimepicker.setLocale('zh'); // kr ko ja en
	$(function() {
		$('#v_date').datetimepicker({
			theme : '', //theme: 'dark',
			timepicker : true, //timepicker: false,
			step : 1, //step: 60 (這是timepicker的預設間隔60分鐘)
			format : 'Y-m-d H:i:s',
			value : new Date(),
			//disabledDates:    ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
			//startDate:	        '2017/07/10',  // 起始日
			minDate : '-1970-01-01', // 去除今日(不含)之前
		//maxDate:           '+1970-01-01'  // 去除今日(不含)之後
		});
	});
</script>


</html>