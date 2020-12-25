<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	margin: 10px auto;
	background-color: white;
	width: 1000px;
	height: 800px;
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
	width:1000px;
	height: 100px;
/* 	border: solid 1px */
}

.table1 {
	margin: 0px auto;
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
.button1{
	background-color:white;
	height: 50px;
	width: 400px;
	border-radius:10px;
	
	
}
.button2{
	background-color:white;
	height: 50px;
	width: 400px;
	border-radius:10px;
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
	</div>
	<div class="content">

		<hr>
		<table class="table1">
			<th>
				<h4 align="center">阿堃的賣場</h4>

			</th>
			<tr class="cart">

				<td></td>
				<td>商品圖片</td>
				<td>品名</td>
				<td>規格</td>
				<td>單價</td>
				<td>數量</td>
				<td>金額</td>
				<td id="trashcan"><img
					src="https://img.icons8.com/plumpy/24/000000/trash.png" type="button"/></td>

			</tr>
			<tr class="cart">

				<td><input type="checkbox" class="checkbox1"></td>
				<td><img class="pic">
					</div></td>
				<td>二手Ps4 Slim</td>
				<td>500G<br> 附雙手把
				</td>
				<td>3000</td>
				<td><div align="center">
						<span class="minus">-</span> <span><input class="qty"
							type="text" value="1" /></span> <span class="plus">+</span>
					</div></td>
				<td>3000</td>
				<td></td>


			</tr>
		</table>
		<hr>
		<table class="table1">
			<th>
				<h4 align="center">阿堃的賣場</h4>

			</th>
			<tr class="cart">

				<td></td>
				<td>商品圖片</td>
				<td>品名</td>
				<td>規格</td>
				<td>單價</td>
				<td>數量</td>
				<td>金額</td>
				<td id="trashcan"><img
					src="https://img.icons8.com/plumpy/24/000000/trash.png" type="button"/></td>

			</tr>
			<tr class="cart">

				<td><input type="checkbox" class="checkbox1"></td>
				<td><img class="pic">
					</div></td>
				<td>二手Ps4 Slim</td>
				<td>500G<br> 附雙手把
				</td>
				<td>3000</td>
				<td><div align="center">
						<span class="minus">-</span> <span><input class="qty"
							type="text" value="1" /></span> <span class="plus">+</span>
					</div></td>
				<td>3000</td>
				<td></td>


			</tr>
		</table>

		<hr>
		<table class="table1">
			<th>
				<h4 align="center">阿堃的賣場</h4>

			</th>
			<tr class="cart">

				<td></td>
				<td>商品圖片</td>
				<td>品名</td>
				<td>規格</td>
				<td>單價</td>
				<td>數量</td>
				<td>金額</td>
				<td id="trashcan"><img
					src="https://img.icons8.com/plumpy/24/000000/trash.png" type="button"/></td>

			</tr>
			<tr class="cart">

				<td><input type="checkbox" class="checkbox1"></td>
				<td><img class="pic">
					</div></td>
				<td>二手Ps4 Slim</td>
				<td>500G<br> 附雙手把
				</td>
				<td>3000</td>
				<td><div align="center">
						<span class="minus">-</span> <span><input class="qty"
							type="text" value="1" /></span> <span class="plus">+</span>
					</div></td>
				<td>3000</td>
				<td></td>


			</tr>
		</table>

	</div>
	<div class="total">
		<h4>合計共3項商品</h4>
		<br>
		<h4>總計9000元</h4>
	</div>
	<div class="buttonarea">
	<button type="button" class="button1" >繼續逛逛</button>
	<button type="button" class="button2" >進入結帳</button>
	</div>
	<div class="footer">
		<jsp:include page="../footer.jsp"></jsp:include>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.js"
		integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
		crossorigin="anonymous"></script>
	<script>
		$(document).ready(function() {
			$('.minus').click(function() {
				var $input = $(this).parent().find('input');
				var count = parseInt($input.val()) - 1;
				count = count < 1 ? 1 : count;
				$input.val(count);
				$input.change();
				return false;
			});
			$('.plus').click(function() {
				var $input = $(this).parent().find('input');
				$input.val(parseInt($input.val()) + 1);
				$input.change();
				return false;
			});
		});
	</script>

</body>



</html>