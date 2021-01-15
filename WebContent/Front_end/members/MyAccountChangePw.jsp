<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%
	String m_id = session.getAttribute("loginId").toString();
    MemberService memSvc = new MemberService();
	MemberVO memberVO = memSvc.findOneMem(m_id);
    session.setAttribute("memberVO", memberVO);
%>
<jsp:useBean id="bankSvc" scope="page" class="com.bankcode.model.BankcodeService" />

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

.content {
	margin-top: 100px;
	margin-bottom: 200px;
}

#pills-home-tab, #pills-profile-tab {
    font-size: 24px;
    padding: 15px 150px;
}

#myfiles,#changepw{
    text-align: left;
    margin: auto;
    width: 800px;
    height: 900px;
    font-size: 24px;
    background-color: lightblue;
    color: #8d8a8a;
    border-radius: 30px;
    font-weight: bold;
}
#pills-tab{
    margin:auto;
    width:800px;
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
#signup {
	background-color: white;
	color: black;
	width: 120px;
	border: 1px solid #707070;
	margin-left: 50px;
}



</style>

</head>

<body class="mybody">

	<div class="header">
		<jsp:include page="../header.jsp"></jsp:include>
	</div>

	<div class="content">
	<div>
	     <jsp:include page="SelectBar.jsp"></jsp:include>
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

			<ul class="nav nav-pills mb-3" id="pills-tab" role="tablist" id="myfilebar">
			
				<li class="nav-item" role="presentation">
				<a class="nav-link" id="pills-home-tab" data-toggle="pill" href="#pills-home" role="tab" aria-controls="pills-home" aria-selected="false">我的檔案</a></li>
					
				<li class="nav-item" role="presentation">
				<a class="nav-link active" id="pills-profile-tab" data-toggle="pill" href="#pills-profile" role="tab" aria-controls="pills-profile" aria-selected="true">更改密碼</a>
				</li>

			</ul>
			
			
			<div class="tab-content" id="pills-tabContent" id="myfile">


<div class="tab-pane fade" id="pills-home" role="tabpanel" aria-labelledby="pills-home-tab">

					<table id="myfiles">
						<tr>
							<td><b>我的檔案</b>
							<hr></td>
							<td></td>
						</tr>
<!-- 							<tr> -->
<%-- 								<td style="display: none;" name="m_id" value="<%=memberVO.getM_id()%>"></td> --%>
<!-- 							</tr> -->
						<tr>
							<td>Email</td>
							<td>${memberVO.m_email}</td>

						</tr>
						<tr>
							<td>姓名</td>
							<td>${memberVO.m_name}</td>
						</tr>
						<tr>
							<td>電話</td>
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
							<td><b>銀行帳戶</b>
							<hr></td>
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
							<td>${bankSvc.getOneBankcode(memberVO.b_code).b_name}</td>
						</tr>
						<tr>
							<td>帳戶</td>
							<td>${memberVO.m_account}</td>
						</tr>

					</table>

				</div>

<div class="tab-pane fade active show" id="pills-profile" role="tabpanel" aria-labelledby="pills-profile-tab">
                     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/controller/MemberServlet" style="margin-bottom: 0px;">
						<table id="changepw">
						
							<tr>
								<td><input type="hidden" name="m_oldpassword" size="40" value="<%=memberVO.getM_password()%>"/></td>
							</tr>
							<tr>
								<td>原始密碼</td>
								<td><input type="password" id="m_password" name="m_password"/></td>
							</tr>
							<tr>
								<td>新密碼</td>
								<td><input type="password" id="m_newpassword" name="m_newpassword"/></td>
							</tr>
							<tr>
								<td>新密碼確認</td>
								<td><input type="password" id="m_newpasswordconfirm" name="m_newpasswordconfirm"/></td>
							</tr>

						</table>
					<input type="button" value="放棄修改" onclick="location.href='<%=request.getContextPath()%>/Front_end/members/MyAccount.jsp'" class="btn btn-primary" id="signup">
					
					<input type="hidden" name="action" value="MyfilePasswordconfirm">
					<input type="hidden" name="m_id" value="<%=memberVO.getM_id()%>">
                    <input type="submit" value="送出" id="revise" class="btn btn-primary">
                    </FORM>

                 </div>

			</div>

		</div>
	
	
<!-- --------------------------------------------------------------------------- -->
</div>


	<div class="footer">
		<jsp:include page="../footer.jsp"></jsp:include>
	</div>


</body>
<!-- <script type="text/javascript"> -->
//  $(document).ready(function(){
//     $("#revise").click(function JSalert(){
//         if($("#m_password").val()==""){
//             swal("舊密碼尚未填寫");       
//         }
//     })
//  })
<!-- </script> -->
<!-- <!-- <script type="text/javascript"> --> -->
<!-- // function JSalert(){ -->
<!-- // 	swal("A Basic JS alert by a plug-in"); -->
<!-- // } -->
<!-- <!-- </script> --> -->



</html>