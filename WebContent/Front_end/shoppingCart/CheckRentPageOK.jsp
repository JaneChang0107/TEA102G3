<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>結帳完成</title>
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
	height: 800px;
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

.title {
	margin: 40px;
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

.col {
	text-align:center;
	font-size:25px;
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
		<h2 class="title">結帳完成</h2>
	</div>
	<div class="content">

		<table class="table1">
			<tr>
				<td class="col">以下是您的 訂單編號:00000000000</td>
			<tr>
			<tr>
				<td class="col">訂單日期:2020/XX/XX</td>
			</tr>
			<tr>
				<td class="col">..........</td>

			</tr>
			<tr>
				<td class="col">備用欄位</td>
			</tr>
			<tr>
				<td class="col">備用欄位</td>
			</tr>
			<tr>
				<td class="col">備用欄位</td>
			</tr>
			<tr>
				<td class="col">備用欄位</td>
			</tr>
			<tr>
				<td class="col">感謝您的購買</td>
			</tr>
		</table>
	</div>
	<div class="buttonarea">
		<button type="button" class="button1">會員專區</button>
		<button type="button" class="button2">回首頁</button>
	</div>
	<div class="footer">
		<jsp:include page="../footer.jsp"></jsp:include>
	</div>

	<script>
		
	</script>

</body>



</html>