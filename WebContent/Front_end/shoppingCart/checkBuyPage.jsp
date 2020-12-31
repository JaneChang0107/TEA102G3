<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.productType.model.*"%>
<%
	Vector<ProductVO> buylist = (Vector<ProductVO>) session.getAttribute("shoppingcart");
%>
<%-- <% --%>
<!--  	if (buylist != null && (buylist.size() > 0)){ -->
<%-- %> --%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>您的購物車-我要買</title>
<style>
* {
	border: 0px solid gray;
}

.mybody {
	background-color: #E3F8F6;
}

.content {
	margin: 0px auto;
	background-color: white;
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

.seller {
	
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

.minus {
	width: 15px;
	height: 15px;
	padding: 8px 5px 8px 5px;
	background: #f2f2f2;
	border-radius: 4px 0 0 4px;
	font-size: 15px;
	border: 1px solid #ddd;
	text-align: center;
	cursor: pointer;
}

.plus {
	width: 15px;
	height: 15px;
	padding: 8px 5px 8px 5px;
	background: #f2f2f2;
	border-radius: 0 4px 4px 0;
	font-size: 15px;
	border: 1px solid #ddd;
	cursor: pointer;
}

.qty {
	height: 30px;
	width: 30px;
	text-align: center;
	font-size: 18px;
	border: 1px solid #ddd;
	border-radius: 0;
}

#trashcan {
	margin: 0px auto;
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

		<c:forEach var="productVO" items="${shoppingcart}" varStatus="loop">
			<%
			ProductVO order = null;
			ProductTypeVO ptv = null;
			int index = 0;
			int counter = buylist.size();
			int a;
			for (index = 0; index < buylist.size(); index++) {
				order = buylist.get(index);
				System.out.println(index);
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

				<td>${loop.count}</td>
				<td>商品圖片</td>
				<td>品名</td>
				<td>規格</td>
				<td>單價</td>
				<td>數量</td>
				<td>金額</td>
				<td><FORM name="deleteForm" METHOD="post"
						ACTION="<%=request.getContextPath()%>/BuyServlet"
						style="margin-bottom: 0px;">
						<input type="hidden" name="action" value="DELETE"> <input
						
							type="hidden" name="del" value="${loop.index}"> <input
							type="submit" value="刪除">
						</div>
					</form>
			</tr>
			<tr class="cart">

				<td><input type="checkbox" class="checkbox1"></td>
				<td><img class="pic">
					</div></td>

				<td><c:out value="${productVO.p_name}" /></td>
				<td
					style="max-width: 100px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap"><c:out
						value="${productVO.p_detail}" /></td>
				<td><c:out value="${productVO.p_price}" /></td>


				<td align="center">
					<div align="center">
						<!-- 						<span class="minus">-</span> <span><input class="qty" -->
						<!-- 							type="text" value="1" /></span> <span class="plus">+</span> -->
						<input type="number" min="1" max="${productVO.p_count}" step="1"
							pattern="[0-9]*" class="counter"> <br>
						庫存:${productVO.p_count}
						<!-- 							<select> -->
						<%-- 							<option>${productVO.p_count}</option> --%>
						<!-- 							</select> -->
						<!-- 					</div> -->
				</td>
				<td>${productVO.p_count*productVO.p_price}</td>
				<td></td>

			</tr>
		</table>
	</div>
	<hr>
	</c:forEach>
<%-- 	<%} %> --%>




	<div class="total">
		<h4>合計共<%=buylist.size()%>項商品</h4>
		<br>
		<h4>總計9000元</h4>
	</div>
	<div class="buttonarea">
		<button type="button" class="button1">繼續逛逛</button>
		<button type="button" class="button2">進入結帳</button>
	</div>
	<div class="footer">
		<jsp:include page="../footer.jsp"></jsp:include>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.js"
		integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
		crossorigin="anonymous"></script>
	<script>
		// 		$(document).ready(function() {
		// 			$('.minus').click(function() {
		// 				var $input = $(this).parent().find('input');
		// 				var count = parseInt($input.val()) - 1;
		// 				count = count <= 1 ? 1 : count;
		// 				$input.val(count);
		// 				$input.change();
		// 				return false;
		// 			});
		// 			$('.plus').click(function() {
		// 				var $input = $(this).parent().find('input');
		// 				$input.val(parseInt($input.val()) + 1);
		// 				$input.change();
		// 				return false;
		// 			});
		// 		});
	</script>

</body>



</html>