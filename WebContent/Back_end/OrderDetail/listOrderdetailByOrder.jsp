<%@page import="com.viewseller.model.ViewsellerVO"%>
<%@page import="com.viewseller.model.ViewsellerService"%>
<%@page import="java.util.*"%>
<%@page import="com.orderdetail.model.*"%>
<%@page import="com.orderlist.model.*"%>
<%@page import="com.product.model.*"%>
<%@page import="com.member.model.*"%>
<%@page import="com.productPicture.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService"></jsp:useBean>
<jsp:useBean id="productPicSvc" scope="page" class="com.productPicture.model.ProductPictureService"></jsp:useBean>
<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService"></jsp:useBean>

<%
	OrderdetailService orderdetailSvc =new OrderdetailService();
	String o_id =request.getParameter("o_id");
    String od_id =request.getParameter("od_id");
    
	OrderlistService orderlistSvc =new OrderlistService();
	OrderlistVO orderlistVO =orderlistSvc.getOneOrderlist(o_id);
	pageContext.setAttribute("orderlistVO", orderlistVO);

	OrderdetailService odsv =new OrderdetailService();
	String comparem_id = productSvc.oneProduct(odsv.getFirstP_id(o_id)).getM_id();
	String m_id = session.getAttribute("loginId").toString();
	Boolean orderbelong;
	if(comparem_id.equals(m_id) && orderlistVO.getO_status().equals("訂單成立")){
		orderbelong=true;
	}else{
		orderbelong=false;
	}
	pageContext.setAttribute("comparem_id", comparem_id);
	request.setAttribute("orderbelong", orderbelong);

	//=================================
		ViewsellerService vser = new ViewsellerService();
		ViewsellerVO viewsellerVO = vser.getOneViewbyoid(o_id);
		
		List<OrderdetailVO> list = orderdetailSvc.getDetailByOrder(o_id);
		request.setAttribute("list", list);
		
		Boolean commentunfinish;
		if(viewsellerVO==null && orderlistVO.getO_status().equals("已到貨")){
			commentunfinish = true ;
		}else {
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
body{
    background-color: #E3F8F6;
}
td{
    padding:0px 30px 0px 30px;
}
#signin {
    background-color: #FFA000;
    width: 100px;
    height: 50px;
    font-size: 20px;
    color: floralwhite;
    border: 1px solid #707070;
    margin-left: 20px;
}

</style>


</head>
<body>



<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


<table id="listallorder">
<div style="width=100px"><p>訂單編號: <%=orderlistVO.getO_id()%></p></div>
<p>訂單成立: <%=orderlistVO.getO_dateForm()%></p>
<p>狀態: <%=orderlistVO.getO_status()%></p>
<hr>
<h2>收件資訊</h2>
<p>收件人: <%=memSvc.findOneMem(orderlistVO.getM_id()).getM_name()%></p>
<p>收件方式: <%=orderlistVO.getO_transport()%></p>
<p>收件地址: <%=orderlistVO.getO_address()%></p>

<hr>
	<tr>
		<th><h2>示意圖</h2></th>
		<th><h2>品名</h2></th>
		<th><h2>數量</h2></th>
		<th><h2>單價</h2></th>
		<th><h2>小計</h2></th>
     
	</tr>
	<c:forEach var="orderdetailVO" items="${list}">
		<tr>
           <td><img src="<%=request.getContextPath()%>/ShowPicture?type=ppid&id=${productPicSvc.findProductRandomPicture(orderdetailVO.p_id)}" width="100px" height="100px";></td> 
			<td><h2>${productSvc.oneProduct(orderdetailVO.p_id).p_name}</h2></td>
			<td><h2> * ${orderdetailVO.od_count}</h2></td>
			<td><h2>${productSvc.oneProduct(orderdetailVO.p_id).p_price}</h2></td>
			<td><h2>${orderdetailVO.od_count*productSvc.oneProduct(orderdetailVO.p_id).p_price}</h2></td>
		</tr>
	</c:forEach>
</table>
<hr>
<h2>總金額: <%=orderlistVO.getO_total()%></h2>


<c:if test="${orderbelong}">
<form METHOD="post" ACTION="<%=request.getContextPath()%>/orderlist">
     <input type="hidden" name="action" value="change_O_status">
     <input type="hidden" name="o_status" value="已出貨">
     <input type="hidden" name="o_id" value=<%=o_id%>>
     <input type="submit" id="signin" value="出貨">
</form>
</c:if>


	<!-- ----新增評價 按鈕-->


<c:if test="${commentunfinish}">
<button type="button" id="${orderlistVO.o_id}"/>

	<h1>留下評論：</h1>

	<c:if test="${!errors.isEmpty()}">
		<c:forEach var="error" items="${errors}">
			<p style="color: red">${error}</p>
		</c:forEach>
	</c:if>

	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/ViewSellerServlet" name="form1">

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
				<h2>
					給予評價： <input type="radio" name="v_gb" value="值得鼓勵"
						<%=viewsellerVO != null && viewsellerVO.getV_gb().equals("值得鼓勵") ? "checked" : ""%>><i class="fas fa-thumbs-up"></i>

					<input type="radio" name="v_gb" value="不甚滿意"
						<%=viewsellerVO != null && viewsellerVO.getV_gb().equals("不甚滿意") ? "checked" : ""%>><i class="fas fa-thumbs-down"></i>
				
			</div>

			<!--  		  </div> -->
			<!-- 			<input type="TEXT" name="v_gb" size="45" -->
			<%-- 				value="<%=(viewsellerVO == null) ? "好" : viewsellerVO.getV_gb()%>" /></h2> --%>
			<!-- 		</div> -->

			<div id="v_comment">
				<h2>
					評論內容：
					<textarea name="v_comment" cols="40" rows="1"><%=viewsellerVO == null ? "" : viewsellerVO.getV_comment()%></textarea>
				</h2>
			</div>

			<div style="display: none">
				<h2>
					評價日期： <input type="text" name="v_date" id="v_date" size="40"
						placeholder="請選擇日期" />
					<%--value="<%=(couponVO == null) ? "" : couponVO.getCo_expire()%>" /></td> --%>
				</h2>
			</div>

			<div>
				<input type="hidden" name="action" value="insert"> <input
					type="submit" class="btn btn-primary" value="送出">
			</div>
	</form>
	</div>
	</c:if>
	
	
	
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
	});s
});
</script>
</html>