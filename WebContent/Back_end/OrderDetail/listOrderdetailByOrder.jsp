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

#listallorder{
/*     margin: auto; */
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
<div style="width=100px"><label>訂單編號: <%=orderlistVO.getO_id()%></label></div>
<label>狀態: <%=orderlistVO.getO_status()%></label>
<hr>
<label>地址: <%=orderlistVO.getO_address()%></label>
<label></label>
	<tr>
<!-- 		<th>流水號</th> -->
<!-- 		<th>訂單編號</th> -->
<!-- 		<th>商品名稱</th> -->
<!-- 		<th>產品數量</th> -->
<!-- 		<th>修改</th> -->
<!-- 		<th>刪除</th> -->
		
	</tr>
	<c:forEach var="orderdetailVO" items="${list}">
		<tr>
		    
           <td><img src="data:image/jpg;base64,${productPicSvc.findFirstOneProductPicture(orderdetailVO.p_id).pp_picture64}" width="150px" height="150px";></td> 
<%--        <td><img src="data:image/jpg;base64,<%=((ProductPictureVO)(productPicSvc.findFirstOneProductPicture(orderdetailVO.getP_id()))).getPp_picture64()%>" width="100px" height="100px";></td> --%>
			<td><h2>${productSvc.oneProduct(orderdetailVO.p_id).p_name}</h2></td>
			<td><h2> * ${orderdetailVO.od_count}</h2></td>
			<td><h2>${productSvc.oneProduct(orderdetailVO.p_id).p_price}</h2></td>
			<td><h2>${orderdetailVO.od_count*productSvc.oneProduct(orderdetailVO.p_id).p_price}</h2></td>
		</tr>	
	</c:forEach>
</table>
<hr>

</body>
</html>