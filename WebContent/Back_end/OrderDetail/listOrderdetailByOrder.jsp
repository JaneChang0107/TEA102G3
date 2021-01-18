<%@page import="com.viewseller.model.ViewsellerVO"%>
<%@page import="com.viewseller.model.ViewsellerService"%>
<%@page import="java.util.*"%>
<%@page import="com.orderdetail.model.*"%>
<%@page import="com.orderlist.model.*"%>
<%@page import="com.product.model.*"%>
<%@page import="com.member.model.*"%>
<%@page import="com.productPicture.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:useBean id="productSvc" scope="page"
	class="com.product.model.ProductService"></jsp:useBean>
<jsp:useBean id="productPicSvc" scope="page"
	class="com.productPicture.model.ProductPictureService"></jsp:useBean>
<jsp:useBean id="memSvc" scope="page"
	class="com.member.model.MemberService"></jsp:useBean>

<%
	OrderdetailService orderdetailSvc = new OrderdetailService();
	String o_id = request.getParameter("o_id");
	String od_id = request.getParameter("od_id");

	OrderlistService orderlistSvc = new OrderlistService();
	OrderlistVO orderlistVO = orderlistSvc.getOneOrderlist(o_id);
	pageContext.setAttribute("orderlistVO", orderlistVO);
	OrderdetailService odsv = new OrderdetailService();
	String comparem_id = productSvc.oneProduct(odsv.getFirstP_id(o_id)).getM_id();
	String m_id = session.getAttribute("loginId").toString();
	Boolean orderbelong;
	if (comparem_id.equals(m_id) && orderlistVO.getO_status().equals("訂單成立")) {
		orderbelong = true;
	} else {
		orderbelong = false;
	}
	pageContext.setAttribute("comparem_id", comparem_id);
	request.setAttribute("orderbelong", orderbelong);
	//=================================
	ViewsellerService vser = new ViewsellerService();
	ViewsellerVO viewsellerVO = vser.getOneViewbyoid(o_id);

	List<OrderdetailVO> list = orderdetailSvc.getDetailByOrder(o_id);
	request.setAttribute("list", list);

	Boolean commentunfinish;
	if (viewsellerVO == null && orderlistVO.getO_status().equals("已到貨")) {
		commentunfinish = true;
	} else {
		commentunfinish = false;
	}

	// 		System.out.println("評價是否還沒完成?:"+commentunfinish);
	request.setAttribute("commentunfinish", commentunfinish);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<style>
body {
	
	font-size: 25px;
}
* {
  box-sizing: border-box;
}
.column1 {
    float: left;
    width: 30%;
    padding: 76px;
    height: 800px;
    background-color:lightblue
}
.column2{
    float: left;
    width: 70%;
    padding: 76px;
    height: 800px; 
  	background-color: #e4e1a2;
}
.row:after {
  content: "";
  display: table;
  clear: both;
}
.row {
	font-size: 25px; 
	width: 1500px;

}

/*  #signin {  */
/* 	background-color: #FFA000; */
/* 	width: 100px; */
/* 	height: 50px; */
/*  	font-size: 20px;  */
/* 	color: floralwhite; */
/* 	border: 1px solid #707070; */
/* 	margin-left: 20px; */
/*  }  */
.modal-content {
	position: relative;
	background-color: #e1fbae;
}
</style>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"/>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

</head>
<body style="background-color: #E3F8F6;">

	<div class="row" style="margin: auto; margin-top: 100px;">
		<div class="column1">
			<!-- 錯誤表列 -->
			<c:if test="${not empty errorMsgs}">
				<li style="color: red; font-color: red">請完成評價後再送出！</li>
			</c:if>
			<font style="color: blue"><h1>訂單資訊</h1></font>
			<td><p>
					訂單編號:<%=orderlistVO.getO_id()%></p></td>
			<td><p>
					訂單成立:<%=orderlistVO.getO_dateForm()%></p></td>
			<td><p>
					狀態:<%=orderlistVO.getO_status()%></p></td>

			<c:if test="${orderbelong}">
				<form METHOD="post" ACTION="<%=request.getContextPath()%>/orderlist">
					<input type="hidden" name="action" value="change_O_status">
					<input type="hidden" name="o_status" value="已出貨"> <input
						type="hidden" name="o_id" value=<%=o_id%>> <input
						type="submit" id="signin" value="出貨">
				</form>
			</c:if>

			<br> <font style="color: blue"><h1>收件資訊</h1></font>
			<td><p>
					收件人:<%=memSvc.findOneMem(orderlistVO.getM_id()).getM_name()%></p></td>
			<td><p>
					收件方式:<%=orderlistVO.getO_transport()%></p></td>
			<td><p>
					收件地址:<%=orderlistVO.getO_address()%></p></td>
					
			<c:if test="${commentunfinish}">

				<button type="button" id="${orderlistVO.o_id}"
					class="btn btn-info btn-lg" data-toggle="modal"
					data-target="#myModal">我要評價</button>
				<div id="myModal" class="modal fade" role="dialog">
					<div class="modal-dialog" style="width: 420px"; >
						<div class="modal-content">
							<div class="modal-header"
								style="background-color: lightsteelblue";>
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h1 class="modal-title">留下評論</h1>
							</div>
							<div class="modal-body">
								<!--       <h1>留下評論：</h1> -->

								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/ViewSellerServlet"
									name="form1">

									<div id="o_id" style="display: none">
										<label>訂單編號</label> <input type="text" name="o_id"
											value="${orderlistVO.o_id}">
									</div>

									<div id="m_buyid" style="display: none">
										<label>買家名稱</label> <input type="text" name="m_buyid"
											value="${orderlistVO.m_id}">
									</div>

									<div id="m_sellid" style="display: none">
										<label>賣家名稱</label> <input type="text" name="m_sellid"
											value="${comparem_id}">
									</div>

									<div id="choose_gb">
										<div>
											<tr>
												<h2>是否推薦：</h2>
											</tr>
											<tr>
												<input type="radio" name="v_gb" value="good"
													<%=viewsellerVO != null && viewsellerVO.getV_gb().equals("good") ? "checked" : ""%>>
												<i class="fas fa-thumbs-up"></i>
											
												<input type="radio" name="v_gb" value="bad"
													<%=viewsellerVO != null && viewsellerVO.getV_gb().equals("bad") ? "checked" : ""%>>
												<i class="fas fa-thumbs-down"></i>
											</tr>
										</div>
										<div id="v_comment">
											<h2>評論內容：</h2>
											<textarea name="v_comment" placeholder="請填寫評論內容"
												style="width: 385px" cols="50" rows="4"><%=viewsellerVO == null ? "" : viewsellerVO.getV_comment()%></textarea>
											<%-- 					<textarea name="v_comment" /*cols="40" rows="1"*/><%=viewsellerVO == null ? "" : viewsellerVO.getV_comment()%></textarea> --%>
										</div>

										<div style="display: none">
											<h2>
												評價日期： <input type="text" name="v_date" id="v_date" size="40"
													placeholder="請選擇日期" />
											</h2>
										</div>

										<div>
											<input type="hidden" name="action" value="insert"> <input
												type="submit" class="btn btn-primary" value="送出">
										</div>
								</form>

							</div>
							<div class="modal-footer" style="padding: 0";>
								<button type="button" class="btn btn-default"
									data-dismiss="modal" style="display: none";></button>
							</div>
			</c:if>	
					</div>
			</div>		
				</div>	
				</div>
				</div>


		<div class="column2">
			<font style="color: blue"><h1>商品明細</h1></font>
			<table class=detail style="width: 800px; margin-left: -54px";>
				<tr style="text-align: center";>
					<td>示意圖</td>
					<td>品名</td>
					<td>數量</td>
					<td>單價</td>
					<td>小計</td>
				</tr>

				<c:forEach var="orderdetailVO" items="${list}">
					<tr style="text-align: center";>
						<td><img
							src="<%=request.getContextPath()%>/ShowPicture?type=ppid&id=${productPicSvc.findProductRandomPicture(orderdetailVO.p_id)}"
							width="100px" height="100px";></td>
						<td><p>${productSvc.oneProduct(orderdetailVO.p_id).p_name}</p></td>
						<td><p>* ${orderdetailVO.od_count}</p></td>
						<td><p>${productSvc.oneProduct(orderdetailVO.p_id).p_price}</p></td>
						<td><p>${orderdetailVO.od_count*productSvc.oneProduct(orderdetailVO.p_id).p_price}</p></td>
					</tr>
				</c:forEach>

			</table>
			<hr>
			<h2 style="text-align:right;">運費:<%=orderlistVO.getO_shippingfee()%></h2>
<%-- 			<h2>堃幣回饋:<%=Math.round(orderlistVO.getO_total()*0.01) %></h2> --%>
			<h2 style="text-align:right;">堃幣折抵:<%=orderlistVO.getO_pm()-Math.round(orderlistVO.getO_total()*0.01)%></h2>
			<hr>
			<h2 style="size:50px;text-align:right;">總金額:<%=orderlistVO.getO_total()%></h2>



		</div>
	</div>


	<script src="https://code.jquery.com/jquery-3.5.1.js"
		integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
		crossorigin="anonymous"></script>

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
<script src="https://kit.fontawesome.com/a72ac34f47.js"
	crossorigin="anonymous"></script>
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