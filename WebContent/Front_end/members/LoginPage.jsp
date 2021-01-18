<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login</title>
<style>
.mybody {
	background-color: #E3F8F6;
}

.myform {
	border: 1px solid gray;
	background-color: rgb(243, 241, 241);
	width: 400px;
	height: 300px;
	text-align: center;
	margin: 20px auto;
}

.topcol {
	width: auto;
	height: 80px;
	background-color: #6CCFF3;
	margin: 0px 0px 20px 0px;
	font-size: 24px;
}

#inputEmail3 {
	width: 300px;
}

#inputPassword3 {
	width: 300px;
	margin-bottom: 5px;
}

#signup {
	background-color: white;
	color: black;
	width: 120px;
	border: 1px solid #707070;
	margin-left: 50px;
}

#signin {
	background-color: #FFA000;
	width: 100px;
	border: 1px solid #707070;
	margin-left: 20px;
}

#forget {
	position: relative;
	left: 100px;
}

.content {
	margin-top: 100px;
	margin-bottom: 200px;
}
</style>

</head>
<body class="mybody">

	<div class="header">
		<jsp:include page="../header.jsp"></jsp:include>
	</div>

	<div class="content">
		<form class="myform"
			ACTION="<%=request.getContextPath()%>/member/controller/MemberLogin"
			method="post">
			<div class="topcol">
				<br> <u>遊戲堃會員登入</u>
			</div>

			<div class="form-group row">
				<label for="inputEmail3" class="col-sm-2 col-form-label">Email</label>
				<div class="col-sm-10">
					<input type="email" class="form-control" id="inputEmail3"
						name="account" placeholder="請輸入註冊時的Email">
				</div>
			</div>

			<div class="form-group row">
				<label for="inputPassword3" class="col-sm-2 col-form-label">Password</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" id="inputPassword3"
						name="password" placeholder="請輸入密碼">
				</div>
			</div>

			<div class="col-sm-10">
				<input type="button" class="btn btn-primary" id="signup"
					value="註冊新帳號" onclick="location.href='addMem.jsp'" />
				<button type="submit" class="btn btn-primary" id="signin" value="登入">登入</button>
				<br>
			</div>

<!-- 			<div id="forget">忘記密碼?</div> -->

		</form>
	</div>

	<div class="footer">
		<jsp:include page="../footer.jsp"></jsp:include>
	</div>
</body>



</html>