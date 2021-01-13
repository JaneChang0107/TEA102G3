<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="redis.clients.jedis.Jedis"%>
<%@page import="java.util.List"%>
<%@page import="com.orderdetail.model.OrderdetailVO"%>
<%@page import="com.orderdetail.model.OrderdetailService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%

%>


<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<title>YuXiKun</title>


<style>
marquee {
	width: 1000px;
}
body {
	background:#F5D2CD;
	height: 100%;
}

#sellerbar1,#sellerbar2,#sellerbar3,#sellerbar4{
    padding: 50px 110px;
    font-size: 50px;
    margin: 30px;
    margin-left: 60px;
    border-radius: 30px;
    box-shadow: 3px 3px 5px 6px darkgrey;
}
.btn-warning{
    width: 140px;
    margin: 5px;
    margin-left: 8px;
    
}

#dropdown-menu1,#dropdown-menu2,#dropdown-menu3,#dropdown-menu4{
    width:80px;
    height:120px;
    background-color:darkgrey;
    border-radius: 8%;
    opacity: 0.88;
}


</style>

</head>

<body style="background:#F5D2CD;
	height: 100%;">

<h2><b><a href="<%=request.getContextPath()%>/member/controller/MemberServlet?action=goSellerIndex" style="padding-left: 20px;">遊戲堃|賣家中心</a></b></h2>
<div style="text-align:center;">

<div class="btn-group dropright">
<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" id="sellerbar1">訂單管理</button>
  <div class="dropdown-menu" id="dropdown-menu1">
  <a href="<%=request.getContextPath()%>/orderlist?action=getSellerAll"><button type="button" class="btn btn-warning">我的銷售</button></a>
<!--   <button type="button" class="btn btn-warning">物流設定</button> -->
  </div>
</div>

<div class="btn-group dropright">
<button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" id="sellerbar2">商品管理</button><br>
  <div class="dropdown-menu" id="dropdown-menu2">
  <a href="<%=request.getContextPath()%>/Front_end/product/addProduct.jsp"><button type="button" class="btn btn-warning">新增商品</button></a>
  <a href="<%=request.getContextPath()%>/Front_end/product/sellerProduct.jsp"><button type="button" class="btn btn-warning">我的商品</button></a>
  </div>
</div><br>

<div class="btn-group dropright">
<button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" id="sellerbar3">財務管理</button>
  <div class="dropdown-menu" id="dropdown-menu3">
  <a href="<%=request.getContextPath()%>/Front_end/seller/MyPayIn.jsp"><button type="button" class="btn btn-warning">我的進帳</button></a>
  </div>
</div>

<div class="btn-group dropright">
<button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" id="sellerbar4">賣場管理</button>
  <div class="dropdown-menu" id="dropdown-menu4">
  <a href="<%=request.getContextPath()%>/Front_end/seller/udsellstore.jsp"><button type="button" class="btn btn-warning">賣場介紹</button></a>
  <a href="<%=request.getContextPath()%>/Front_end/seller/viewseller.jsp"><button type="button" class="btn btn-warning">賣場評價</button></a>
  </div>
</div>



</div>
</body>

</html>