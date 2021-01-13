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
body{
    background-color: #E3F8F6;
}
td{
    padding:0px 30px 0px 30px;
}
h1{
/*     text-align: center; */
}
div#showImg>img.viewImg {
	width: 200px;
}
div#addComment {
	position: relative;
	width: 50%;
	left: 50%;
	top: 10%;
	transform: translateX(-50%);
}
btn btn-primary {
	text-align: right;
}
 
</style>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

</head>
<body>
<div style="border-width: 19px;border-style: dashed;border-color: #FFAC55;padding: 48px";">
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
		<tr>222</tr>
           <td><img src="<%=request.getContextPath()%>/ShowPicture?type=ppid&id=${productPicSvc.findProductRandomPicture(orderdetailVO.p_id)}" width="100px" height="100px";></td> 
<%--        <td><img src="data:image/jpg;base64,<%=((ProductPictureVO)(productPicSvc.findFirstOneProductPicture(orderdetailVO.getP_id()))).getPp_picture64()%>" width="100px" height="100px";></td> --%>
			<td><h2>${productSvc.oneProduct(orderdetailVO.p_id).p_name}</h2></td>
			<td><h2> * ${orderdetailVO.od_count}</h2></td>
			<td><h2>${productSvc.oneProduct(orderdetailVO.p_id).p_price}</h2></td>
			<td><h2>${orderdetailVO.od_count*productSvc.oneProduct(orderdetailVO.p_id).p_price}</h2></td>
		</tr>
	</c:forEach>
</table>
<hr>
<h2>總金額: <%=orderlistVO.getO_total()%></h2>

	 
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