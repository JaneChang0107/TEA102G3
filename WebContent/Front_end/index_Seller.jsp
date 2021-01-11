<%@page import="com.product.model.ProductVO"%>
<%@page import="com.product.model.ProductService"%>
<%@page import="com.orderlist.model.OrderlistVO"%>
<%@page import="com.orderlist.model.OrderlistService"%>
<%@page import="com.member.model.MemberService"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="redis.clients.jedis.Jedis"%>
<%@page import="java.util.List"%>
<%@page import="com.orderdetail.model.*"%>
<%@page import="com.member.model.MemberVO"%>
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
   #todolist{
   height:500px;
   width:1000px;
   background-color:white;
   margin:auto;
   left: 15px;
   top: 50px;
   position: relative;
   }
   .todocolumn{
    width: 250px;
    height: 130px;
    font-size: 30px;
    text-align: center;
    display: inline-block;
    margin-top: 40px;
    border-right: 5px solid green;
   }


</style>


</head>


<body style="background:#F5D2CD;
	height: 100%;">
	
	
<div class="header">
	<jsp:include page="header.jsp"></jsp:include>
</div>

	<center>

		<h3>
			<marquee onMouseOver="this.stop()" onMouseOut="this.start()"
				id="announcement"></marquee>
		</h3>
	</center>

	<div class="content">
	
	<div>
		<jsp:include page="index_Seller_Buttongroup.jsp"></jsp:include>
	</div>
	
	
    <div id="todolist">
    <pre style="font-size:50px;"> <u>待辦事項清單</u></pre>
    
    <div class="todocolumn">
    ${countorder}<br>
        總訂單數
    </div>
    
    <div class="todocolumn">
    ${countunfinish}<br>
        待完成訂單數
    </div>
    
    <div class="todocolumn">
    ${countshipping}<br>
        已出貨訂單數
    </div>
    
    <div class="todocolumn">
    ${countarrived}<br>
        已到貨訂單數
    </div>
    
    <div class="todocolumn">
    ${countfinish}<br>
        已完成訂單數
    </div>
    




	
	</div>
	

</div>
	<div class="footer">
		<jsp:include page="footer.jsp"></jsp:include>
	</div>



</body>

</html>