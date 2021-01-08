<%@page import="java.util.*"%>
<%@page import="com.orderdetail.model.*"%>
<%@page import="com.orderlist.model.*"%>
<%@page import="com.product.model.*"%>
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
    
    //OrderdetailVO orderdetailVO =orderdetailSvc.getOneOrderdetail(od_id);
	OrderlistService orderlistSvc =new OrderlistService();
	OrderlistVO orderlistVO =orderlistSvc.getOneOrderlist(o_id);
	
	System.out.println(request.getParameter("o_id")); 

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
           <td><img src="data:image/jpg;base64,${productPicSvc.findProductRandomPicture(orderdetailVO.p_id)}" width="100px" height="100px";></td> 
<%--        <td><img src="data:image/jpg;base64,<%=((ProductPictureVO)(productPicSvc.findFirstOneProductPicture(orderdetailVO.getP_id()))).getPp_picture64()%>" width="100px" height="100px";></td> --%>
			<td><h2>${productSvc.oneProduct(orderdetailVO.p_id).p_name}</h2></td>
			<td><h2> * ${orderdetailVO.od_count}</h2></td>
			<td><h2> * ${orderdetailVO.od_count}</h2></td>
			<td><h2>${productSvc.oneProduct(orderdetailVO.p_id).p_price}</h2></td>
			<td><h2>${orderdetailVO.od_count*productSvc.oneProduct(orderdetailVO.p_id).p_price}</h2></td>
		</tr>
	</c:forEach>
</table>
<hr>
<h2>總金額: <%=orderlistVO.getO_total()%></h2>

</body>
</html>