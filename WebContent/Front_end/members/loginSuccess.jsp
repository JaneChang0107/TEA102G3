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

			<div class="form-group row"></div>
			<h3><b>歡迎回來!</b></h3>
			<h3><%=session.getAttribute("loginName")%></h3>

			<div class="form-group row"></div>
	</div>

	<div class="col-sm-10">

		<br>
	</div>



	</form>
	</div>

	<div class="footer">
		<jsp:include page="../footer.jsp"></jsp:include>
	</div>
</body>



</html>