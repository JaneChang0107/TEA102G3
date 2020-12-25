<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>我的帳戶</title>
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

.content {
	text-align: center;
	margin-top: 50px;
	margin-bottom: 200px;
}

#icons {
	left: 0px;
}

#headimg {
	width: 200px;
	height: 200px;
	border-radius: 50%;
	margin-right: 20px;
}

#page1, #page2 {
	position: relative;
	left: 200%;
	font-size: 24px;
	padding: 15px 80px;
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
		<div>

			<img src="../../images/M00001head.jpg" id="headimg">
			<button type="button" class="btn btn-secondary">
				<i class="fas fa-user-circle" id="icons"></i>我的帳戶
			</button>
			<button type="button" class="btn btn-secondary">
				<i class="fas fa-clipboard-list" id="icons"></i>購買清單
			</button>
			<button type="button" class="btn btn-secondary">
				<i class="fas fa-gamepad" id="icons"></i>租用清單
			</button>
			<button type="button" class="btn btn-secondary">
				<i class="fas fa-bell" id="icons"></i>通知總覽
			</button>
			<button type="button" class="btn btn-secondary">
				<i class="fas fa-coins" id="icons"></i>我的堃幣
			</button>
		</div>

<div>
		<ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
			<li class="nav-item" role="presentation">
			<a
				class="nav-link active" id="page1" data-toggle="pill"
				href="#pills-home" role="tab" aria-controls="pills-home"
				aria-selected="true">Home</a></li>
			<li class="nav-item" role="presentation">
			
			<a class="nav-link"
				id="page2" data-toggle="pill" href="#pills-profile"
				role="tab" aria-controls="pills-profile" aria-selected="false">Profile</a>
			</li>

		</ul>
		
		
		<div class="tab-content" id="pills-tabContent">
			<div class="tab-pane fade show active" id="pills-home"
				role="tabpanel" aria-labelledby="pills-home-tab">...</div>
			<div class="tab-pane fade" id="pills-profile" role="tabpanel"
				aria-labelledby="pills-profile-tab">...</div>

		</div>

</div>



	</div>


	<div class="footer">
		<jsp:include page="../footer.jsp"></jsp:include>
	</div>


</body>



</html>