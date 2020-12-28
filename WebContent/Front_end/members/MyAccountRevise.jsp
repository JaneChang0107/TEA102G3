<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    text-align: center;
    margin: auto;
    position: relative;
    left: 140%;
    font-size: 24px;
    padding: 15px 150px;
}

.content {
	margin-top: 100px;
	margin-bottom: 200px;
}

#myfiles,#changepw{
    text-align:left;
    margin:auto;
    background-color: #e9e9e9;
    width:800px;
    height:900px;
    font-size:24px;
}
#changepw{
    height:300px;
}

#revise{
    background-color: #FFA000;
    width: 100px;
    border: 1px solid #707070;
    margin-left: 20px;
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

		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		
		<div>
		<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/member/controller/MemberServlet"
		name="form1" enctype="multipart/form-data">


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
							<td><b>我的檔案</b>
							<hr></td>
							<td></td>
						</tr>
							<tr>
								<td style="display: none;"><%=memberVO.getM_id()%></td>
							</tr>
							
							<tr>
							<td>Email</td>
							<td><input type="text" name="m_email" size="40"
					value="<%=memberVO.getM_email()%>" /></td>

						</tr>
						<tr>
							<td>姓名</td>
							<td><input type="text" name="m_name" size="40"
					value="<%=memberVO.getM_name()%>" /></td>
						</tr>
						<tr>
							<td>電話</td>
							<td><input type="text" name="m_phone" size="40"
					value="<%=memberVO.getM_phone()%>" /></td>
						</tr>
						<tr>
							<td>性別</td>
							<td><input type="radio" name="m_gender" value="男" <%=memberVO.getM_gender().equals("男") ? "checked" : "" %> >男
					<input type="radio" name="m_gender" value="女" <%=memberVO.getM_gender().equals("女") ? "checked" : "" %>>女</td>
						</tr>
						<tr>
							<td>生日</td>
							<td><input name="m_birth" id="f_date1" type="text"></td>
						</tr>
									<tr>
				<td>大頭照:</td>
				<td><input type="file" name="m_headpic"
					value="<%=memberVO.getM_headpic()%>" /></td>
			</tr>
						
						<tr>
							<td>地址</td>
							<td><input type="text" name="m_address" size="40"
					value="<%=memberVO.getM_address()%>" /></td>
						</tr>

						<!-- -------------------------------------------------------------------------- -->
						<tr>
							<td><b>銀行帳戶</b>
							<hr></td>
							<td></td>

						</tr>
						<tr>
							<td>全名</td>
							<td><input type="text" name="m_accountName"
					value="<%=memberVO.getM_accountName()%>"></td>
						</tr>
						<tr>
							<td>銀行代碼</td>
							<td><input type="text" name="b_code"
					value="<%=memberVO.getB_code()%>"></td>

						</tr>
						<tr>
							<td>帳戶</td>
							<td><input type="text" name="m_account"
					value="<%=memberVO.getM_account()%>"></td>
						</tr>

					</table>
		<input type="hidden" name="action" value="Myfileupdateconfirm">
		<input type="hidden" name="m_id" value="<%=memberVO.getM_id()%>">
		<input type="submit" value="送出修改" id="revise" class="btn btn-primary">


				</div>

					<div class="tab-pane fade" id="pills-profile" role="tabpanel"
						aria-labelledby="pills-profile-tab">
						<table id="changepw">
						
							<tr>
								<td><input type="hidden" name="m_oldpassword" size="40" value="<%=memberVO.getM_password()%>"/></td>
							</tr>
							<tr>
								<td>原始密碼</td>
								<td><input type="password" name="m_password" size="40"/></td>
							</tr>
							<tr>
								<td>新密碼</td>
								<td><input type="password" name="m_newpassword"></td>
							</tr>
							<tr>
								<td>新密碼確認</td>
								<td><input type="password" name="m_newpasswordconfirm"></td>
							</tr>



						</table>
						<input type="hidden" name="action" value="Myfileupdateconfirm">
						<input type="hidden" name="m_id" value="<%=memberVO.getM_id()%>">
						<input type="submit" value="送出修改" id="revise"
							class="btn btn-primary"></FORM>

                 </div>

			</div>



		</div>
	
	
<!-- --------------------------------------------------------------------------- -->
</div>


	<div class="footer">
		<jsp:include page="../footer.jsp"></jsp:include>
	</div>


</body>

<!-- =====Date time picker設定======== -->
<%
	java.sql.Date m_birth = null;
	try {
		m_birth = memberVO.getM_birth();
	} catch (Exception e) {
		m_birth = new java.sql.Date(System.currentTimeMillis());
	}
%>

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>
<script>
	$.datetimepicker.setLocale('zh');
	$('#f_date1').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : false, //timepicker:true,
		step : 1, //step: 60 (這是timepicker的預設間隔60分鐘)
		format : 'Y-m-d', //format:'Y-m-d H:i:s',
		value : '<%=memberVO.getM_birth()%>',
		maxDate : '+1970-01-01' // 去除今日(不含)之後
	});
</script>

</html>