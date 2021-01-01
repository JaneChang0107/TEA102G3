<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.productType.model.*"%>
<%!int i=0;%>
<%
	Vector<ProductVO> buylist = (Vector<ProductVO>) session.getAttribute("shoppingcart");
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>您的購物車-我要買</title>
<style>
* {
	border: 1px solid gray;
}

.mybody {
	background-color: #E3F8F6;
}

.content {
	margin: 20px auto;
	background-color: white;
	width: 1000px;
	height: 250px;
	/* 	border: solid 1px */
}

.null {
	margin: 20px auto;
	width: 1000px;
	height: 250px;
	/* 	border: solid 1px */
}

.total {
	margin: 10px auto;
	text-align: right;
	width: 1000px;
	height: 150px;
	/* 	border: solid 1px */
}

.buttonarea {
	margin: 10px auto;
	background-color:;
	width: 1000px;
	height: 100px;
	/* 	border: solid 1px */
}

.table1 {
	margin: 20px auto;
	background-color: white;
	width: 800px;
	height: 50px;
}

.cart {
	width: 100px;
	height: 50px;
	text-align: center;
}

.title {
	margin: 40px;
}


.data {
	margin: 0px auto;
	background-color: white;
	width: 800px;
	height: 100px;
}

.pic {
	margin: 0px auto;
	background-color: white;
	width: 100px;
	height: 100px;
}


.checkbox1 {
	height: 20px;
	width: 20px;
}

.button1 {
	background-color: white;
	height: 50px;
	width: 400px;
	border-radius: 10px;
}

.button2 {
	background-color: white;
	height: 50px;
	width: 400px;
	border-radius: 10px;
	float: right;
}

.button1:hover {
	background-color: #008CBA;
	color: white;
}

.button2:hover {
	background-color: #008CBA;
	color: white;
}

table {
	border-collapse: collapse;
	table-layout: fixed;
}

.counter {
	border: 1px solid gray;
}

.footer{
    position: absoloute;
    bottom: 0px;
}
</style>

</head>
<body class="mybody">
	<link rel="stylesheet"
		href="../../vendors/bootstrap/css/bootstrap.min.css">

	<div class="header">
		<jsp:include page="../header.jsp"></jsp:include>
	</div>

	<div>
		<h2 class="title">您的購物車-我要買</h2>
		<hr>
		
<%
 	if (buylist != null && (buylist.size() > 0)){
%>
<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/BuyServlet">

		<c:forEach var="productVO" items="${shoppingcart}" varStatus="loop">
		
			<%
			ProductVO order = null;
			ProductTypeVO ptv = null;
			int index = 0;
			int counter = buylist.size();
			int a;
			for (index = 0; index < buylist.size(); index++) {
				order = buylist.get(index);
				
			}
		%>
		
	</div>
	<div class="content">
		<table class="table1">
			<tr>
				<td colspan=8>
					<h4 align="left">阿堃的賣場</h4>

				</td>
			</tr>
			
			
			<tr class="cart">

				<td name="id">${loop.count}</td>
				<td>商品圖片</td>
				<td>品名</td>
				<td>規格</td>
				<td>單價</td>
				<td>數量</td>
				<td>金額</td>
				<td>
				<a href="<%=request.getContextPath()%>/BuyServlet?action=DELETE&del=${loop.index}">刪除</a>
				</td>
			</tr>
			<tr class="cart">

				<td></td>
				<td><img class="pic">
					</div></td>

				<td><c:out value="${productVO.p_name}" /></td>
				<td
					style="max-width: 100px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap"><c:out
						value="${productVO.p_detail}" /></td>
						
				<td  class="value1" name="price"><c:out value="${productVO.p_price}" />
				</td>

<%-- 				 --%>
				<td align="center">
					<div align="center">
						<input type="number" min="1" max="${productVO.p_count}" step="1" class="numberbox" name="xx${loop.count}" value="selected"><br>
							<a href="<%=request.getContextPath()%>/BuyServlet?action=CALCULATE
							&calid=${loop.count}
							&price=${productVO.p_price}
							&xx${loop.index}="selected">計算</a>
<!-- 						<input type="button" id="Test" onclick="numbertest()" /> -->
						<br>
						庫存:${productVO.p_count}
				</td>
			
				<td class="sum"><p><span class="parsed"></span></p></td>
				<td></td>

			</tr>
		</table>
	</div>
	<hr>
	</c:forEach>


	<div class="total">
		<h4>合計共<%=buylist.size()%>項商品</h4>
		<br>
		<h4>總計元</h4>
	</div>
	<div class="buttonarea">
		<button type="button" class="button1">繼續逛逛</button>
		<button type="submit" class="button2">進入結帳</button>
		<input type="hidden" name="action" value="xxxx">	
	</div>
</form>	       
<%
 	}else{
%>

	<div class="null"><tr><td><h3 align=center>您的購物車現在沒有商品</h3></td></tr></div>
	<div class="buttonarea"> 
	<button type="button" class="button1">繼續逛逛</button>
<%-- 	<input type ="button" class="button2" onclick="javascript:location.href='<%=request.getContextPath()%>/Front_end/index.jsp'" value="回首頁"></input> --%>
	</div>
<%} %>	
	<div class="footer">
		<jsp:include page="../footer.jsp"></jsp:include>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.js"
		integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
		crossorigin="anonymous"></script>
	<script>
// 	$(function(){
//         $('.value1, #xx${loop.count}').keyup(function(){
//            var value1 = parseFloat($('.value1').val()) || 0;
//            var value2 = $('xx${loop.count}');
//           var value3 = $('#sum').val(value1 * value2);
//            console.log(value3);
//         });
//      });
	
	
// 	var value1 =document.getElementById("value1").textContent
// 	var value2 = document.getElementById("parsed").val;
// 	console.log(value1);
// 	console.log(parsed);
	

// 		function numbertest() {
// 			// 		$(".test").off('click').on('click', function (e) {

// // 					  var result = document.getElementById('numberbox').value;
// // 					  var parsed = document.getElementById('parsed');
// // 					  var value1 =document.getElementById("value1").textContent
// // 					  parsed.innerHTML = result*value1;
					  
// 			var numberbox = document.getElementsByClassName('numberbox');
			
// 			for (var i = 0; i < numberbox.length; i++) {
// 				console.log(numberbox.length);
// 				var result = document.getElementsByClassName('numberbox')[i].value;
// 				var parsed = document.getElementsByClassName('parsed')[i];
// 				var value1 = document.getElementsByClassName("value1")[i].textContent;

// 				console.log(result);
// 				console.log(parsed);
// 				console.log(value1);

// 				// 		});
// 			}

// 		}
	</script>

</body>

</html>