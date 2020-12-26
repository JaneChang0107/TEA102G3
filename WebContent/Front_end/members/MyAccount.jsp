<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.member.model.*"%>
<%
	String m_id = session.getAttribute("loginId").toString();
    MemberService memSvc = new MemberService();
	MemberVO memberVO = memSvc.findOneMem(m_id);
    session.setAttribute("memberVO", memberVO);
%>


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

#pills-home-tab, #pills-profile-tab {
    text-align:center;
    margin:auto;
	position: relative;
	left: 220%;
	font-size: 24px;
	padding: 15px 80px;
}

.content {
	margin-top: 100px;
	margin-bottom: 200px;
}

#myfiles,#changepw{
    text-align:center;
    margin:auto;
    background-color: #e9e9e9;
    width:800px;
    height:900px;
    font-size:24px;
}


</style>

</head>

<body class="mybody">

	<div class="header">
		<jsp:include page="../header.jsp"></jsp:include>
	</div>

	<div class="content">
		<div>

			<img src="${memberVO.m_headpic2}" id="headimg">
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

<!-- --------------------------------------------------------------------------- -->
		
		<div>

			<ul class="nav nav-pills mb-3" id="pills-tab" role="tablist" id="myfilebar">
			
				<li class="nav-item" role="presentation"><a
					class="nav-link active" id="pills-home-tab" data-toggle="pill"
					href="#pills-home" role="tab" aria-controls="pills-home"
					aria-selected="true">我的檔案</a></li>
					
				<li class="nav-item" role="presentation"><a class="nav-link"
					id="pills-profile-tab" data-toggle="pill" href="#pills-profile"
					role="tab" aria-controls="pills-profile" aria-selected="false">更改密碼</a>
				</li>

			</ul>
			
			
			<div class="tab-content" id="pills-tabContent" id="myfile">
			
			
				<div class="tab-pane fade show active" id="pills-home"
					role="tabpanel" aria-labelledby="pills-home-tab">
					
											<table id="myfiles">
							<tr>
								<td><b>我的檔案</b><hr></td>
								<td></td>
							</tr>	
							<tr>
								<td>Email</td>
								<td>${memberVO.m_email}</td>

							</tr>
							<tr>
								<td>姓名</td>
								<td>${memberVO.m_name}</td>
							</tr>
							<tr>
								<td>手機號碼</td>
								<td>${memberVO.m_phone}</td>
							</tr>
							<tr>
								<td>性別</td>
								<td>${memberVO.m_gender}</td>
							</tr>
							<tr>
								<td>生日</td>
								<td>${memberVO.m_birth}</td>
							</tr>
							<tr>
								<td>地址</td>
								<td>${memberVO.m_address}</td>
							</tr>
							
	<!-- -------------------------------------------------------------------------- -->						
							<tr>
								<td><b>銀行帳戶</b><hr></td>
								<td></td>

							</tr>
							<tr>
								<td>全名</td>
								<td>${memberVO.m_accountName}</td>
							</tr>
							<tr>
								<td>銀行代碼</td>
								<td>${memberVO.b_code}</td>
								<td>銀行</td>
								<td>XX銀行</td>
							</tr>
							<tr>
								<td>帳戶</td>
								<td>${memberVO.m_account}</td>
							</tr>

						</table>
						

                </div>
					
				<div class="tab-pane fade" id="pills-profile" role="tabpanel"
					aria-labelledby="pills-profile-tab">

                        bbbbbbbbbbbbbbbbb

                 </div>

			</div>



		</div>
	
	
<!-- --------------------------------------------------------------------------- -->
</div>


	<div class="footer">
		<jsp:include page="../footer.jsp"></jsp:include>
	</div>


</body>



</html>