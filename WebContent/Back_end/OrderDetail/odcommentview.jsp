<%@page import="com.viewseller.model.ViewsellerService"%>
<%@page import="java.util.*"%>
<%@page import="com.orderdetail.model.*"%>
<%@page import="com.orderlist.model.*"%>
<%@page import="com.product.model.*"%>
<%@page import="com.productPicture.model.*"%>
<%@ page import="com.viewseller.model.ViewsellerVO"%>
<%@page import ="javax.servlet.http.HttpSession"%>
<%@page import ="javax.servlet.http.HttpServletRequest"%>
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
    
    //OrderdetailVO orderdetailVO =orderdetailSvc.getOneOrderdetail(od_id);
	OrderlistService orderlistSvc =new OrderlistService();
	OrderlistVO orderlistVO =orderlistSvc.getOneOrderlist(o_id);
	
	System.out.println(request.getParameter("o_id")); 
	
    HttpSession Session = request.getSession();
    String m_id = (String) Session.getAttribute("loginId");
	List<OrderdetailVO> list = (List<OrderdetailVO>)request.getAttribute("list");
    pageContext.setAttribute("list", list);
 
    ViewsellerVO viewsellerVO=(ViewsellerVO)request.getAttribute("viewsellerVO");
    List<ViewsellerVO> viewlist = new ArrayList<ViewsellerVO>(); 
    viewlist.add(viewsellerVO);
	pageContext.setAttribute("viewlist", viewlist);
      
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>orderdetail</title>

<style>
body {
	background-color: #E3F8F6;
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
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

</head>
<body>

	

		<div class="row">
			<div class="column1">
				<!-- 錯誤表列 -->
				<c:if test="${not empty errorMsgs}">
					<li style="color: red; font-color:red">請完成評價後再送出！</li>
				</c:if>
				<font style="color:blue"><h1>訂單資訊</h1></font>
				<td><p>訂單編號:<%=orderlistVO.getO_id()%></p></td>
			   <td><p> 訂單成立:<%=orderlistVO.getO_dateForm()%></p></td>
			   <td><p> 狀態:<%=orderlistVO.getO_status()%></p></td>


			
					<br>	
			<font style="color:blue"><h1>收件資訊</h1></font>
			<td><p>收件人:<%=memSvc.findOneMem(orderlistVO.getM_id()).getM_name()%></p></td>
			<td><p>收件方式:<%=orderlistVO.getO_transport()%></p></td>
			<td><p>收件地址:<%=orderlistVO.getO_address()%></p></td>
		  </div>

			<div class="column2">
				<font style="color:blue"><h1>商品明細</h1></font>
			<table class=detail style="width:800px; margin-left:-54px";>
			<tr style="text-align:center";>
			<td>示意圖</td>
			<td>品名</td>
			<td>數量</td>
			<td>單價</td>
			<td>小計</td>
			</tr>
			
			<c:forEach var="orderdetailVO" items="${list}">
			<tr style="text-align:center";>
			<td><img src="<%=request.getContextPath()%>/ShowPicture?type=ppid&id=${productPicSvc.findProductRandomPicture(orderdetailVO.p_id)}"
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
			<h2 style="text-align:right;">堃幣折抵:<%=orderlistVO.getO_pm()-Math.round(orderlistVO.getO_total()*0.01)%></h2>
			<hr>
			<h2 style="size:50px;text-align:right;">總金額:<%=orderlistVO.getO_total()%></h2>


	 <!-- --------------------顯示評價內容------------------- -->
	
<button type="button" id="${orderlistVO.o_id}" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">查看評價</button>
<div id="myModal" class="modal fade" role="dialog">
		<div class="modal-dialog" style="width:420px"; >
			<div class="modal-content">
				<div class="modal-header" style="background-color:#b0deb3";>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h1 class="modal-title">我的留言</h1>
				</div>
				<div class="modal-body">
					<!--       <h1>留下評論：</h1> -->
<table style="inline-block:auto";>
	<tr>
		<div><h2>選擇評價：${viewsellerVO.v_gb}</h2></div>
		<div><h2>評論內容：${viewsellerVO.v_comment}</h2></div>
		<div>${viewsellerVO.v_date}</div>
	</tr>
</table>
				</div>
				<div class="modal-footer" style="padding:0;">
					<button type="button" class="btn btn-default" data-dismiss="modal" style="display:none";></button>
	</div>
	</div>
</body>
<script src="https://kit.fontawesome.com/a72ac34f47.js"
	crossorigin="anonymous"></script>
</html>