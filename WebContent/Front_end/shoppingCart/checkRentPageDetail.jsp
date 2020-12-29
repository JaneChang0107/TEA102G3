<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>您的購物車-我要租</title>
<style>
* {
	border: 0px solid gray;
}

.mybody {
	background-color: #E3F8F6;
}

.content {
	margin: 10px auto;
	background-color: white;
	width: 1000px;
	height: 1200px;
	/* 	border: solid 1px */
}

.info {
	margin: 10px auto;
	background-color: white;
	width: 1000px;
	height: 300px;
	/* 	border: solid 1px */
}

.total {
	margin: 10px auto;
	text-align: right;
	width: 1000px;
	height: 280px;
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

.downarrow {
	height: 20px;
	width: 40px;
	/*  	margin: auto;  */
	/*  	float: right;  */
	display: inline-block;
}

.downarrow2 {
	display: block;
	margin: auto;
	float: right;
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

.card-body {
	background-color: #FCF8DC;
}

.table2 {
	text-align: left;
}

.table3 {
	margin: 50px auto;
	background-color: #FCF8DC;
	width: 800px;
	height: 200px;
}

input:read-only {
	background: #D7D7D7;
}

.captureSignature {
	width: 800px;
	height: 200px;
}

.col {
	text-align: center;
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
		<h2 class="title">結帳頁面-我要租</h2>
	</div>
	<div class="content">

		<hr>
		<table class="table1">
			<th>
				<h4 align="center">遊戲堃</h4>

			</th>
			<tr class="cart">

				<td>商品圖片</td>
				<td>品名</td>
				<td>規格</td>
				<td>租用價/日</td>
				<td>數量</td>
				<td>租用天數</td>
				<td>金額</td>
				<td></td>

			</tr>
			<tr class="cart">

				<td><img class="pic"></td>
				<td>二手Ps4 Slim</td>
				<td>500G<br> 附雙手把
				</td>
				<td>30</td>
				<td>1</td>
				<td>7</td>
				<td>3000</td>
				<td></td>


			</tr>
			<tr class="cart">

				<td></td>
				<td>押金</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td>2000</td>
				<td></td>


			</tr>
			<tr>
				<th colspan=8><a>請於契約書簽名</a><img
					src="<%=request.getContextPath()%>/images/downarrow.png"
					class="downarrow" data-toggle="collapse" href="#collapseExample"
					type="button" aria-expanded="false" aria-controls="collapseExample"></img>
					<br>
					<div class="collapse" id="collapseExample">

						<div class="card-body">
							<div>
								<table class="table">
									<tr>
										<td class="col"><h3>契約書</h4></td>
									<tr>
									<tr>
										<td class="col">西元 20XX年X月X日</td>
									</tr>
									<tr>
										<td class="col">租賃方帳號: XXX5566</td>

									</tr>
									<tr>
										<td class="col">請於此契約書簽名 <br> 代表您同意本網站所立之租賃服務<br>並會愛惜物品與妥善使用物品
											<br>經查若為人為損壞<br>本網站將予以求償
										</td>
									</tr>
								</table>
							</div>

							<div align=center>
								<canvas id="signature" style="background: white"
									style="border: 1px solid #000000"></canvas>
									
								<br>
								<button id="clear-signature">Clear</button>
								<button id="saveButton" >Save</button>
								<textarea class="article-input" id="article-input" type="input" hidden >{{article}}</textarea> 
							</div>
							<div align=center>
								<div id="copy">
								</div>
							</div>

						</div>
					</div></th>
			</tr>

		</table>
		<hr>
		<table class="table3">
			<tr>
				<td><h4>訂購人資料</h4></td>
				<td><h4>取貨資料</h4></td>
			</tr>
			<tr>
				<td>姓名:<input type="text" readonly></input></td>
				<td>分店:<input type="text"></input></td>
			</tr>
			<tr>
				<td>住址:<input type="text" readonly></input></td>
				<td>住址:<input type="text"></input></td>
			</tr>
			<tr>
				<td>電話:<input type="text" readonly></input></td>
				<td></input></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
			</tr>

		</table>

	</div>

	<div class="total">
		<h4>合計共1項商品</h4>
		<br>
		<h4>總計210元</h4>
	</div>
	<div class="buttonarea">
		<button type="button" class="button1">回上一頁</button>
		<button type="button" class="button2">確認送出</button>
	</div>
	<div class="footer">
		<jsp:include page="../footer.jsp"></jsp:include>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.js"
		integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/signature_pad/1.5.3/signature_pad.min.js">
		
	</script>

	<script>
	
		jQuery(document).ready(function($) {

			var canvas = document.getElementById("signature");
			var signaturePad = new SignaturePad(canvas);

			$('#clear-signature').on('click', function() {
				signaturePad.clear();
			});
			
			saveButton.addEventListener("click", function post (event) {

			    if (signaturePad.isEmpty()) {
			        alert("Please provide signature first.");
			    } else {
			    	var canvas = document.getElementById("signature");
			    	
			    	var dataURL = canvas.toDataURL().replace("image/png", "image/octet-stream");  // here is the most important part because if you dont replace you will get a DOM 18 exception.
			    	console.log(dataURL);
					var str = dataURL;
					
					
			    	window.location.href=dataURL;
			    	document.getElementById("article-input").innerHTML=str;
			    	
// 			    	$.ajax({
//                         type: 'POST',
//                         url: 'checkRentPageDetail.jsp',
//                         data: '{ "imageData" : "' + image + '" }',
//                         contentType: 'application/json; charset=utf-8',
//                         dataType: 'json',
//                         success: function (msg) {
//                             alert('Image saved successfully !');
//                         }
//                     });

			    }
			  });

		});
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	</script>


</body>



</html>