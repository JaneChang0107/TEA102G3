<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.member.model.*"%>
<%@page import="java.util.*"%>
<%@page import="com.orderlist.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
       MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
%>

<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="bankSvc" scope="page" class="com.bankcode.model.BankcodeService" />

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>我的堃幣</title>
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

#pills-home-tab, #pills-profile-tab, #pills-contact-tab, #pills-4-tab,
	#pills-5-tab {
	text-align: center;
	margin: auto;
	position: relative;
	left: 50%;
	font-size: 20px;
	padding: 15px 50px;
}

.content {
	margin-top: 100px;
	margin-bottom: 200px;
}

#pills-tab {
	margin: auto;
	width: 950px;
}

#changepw {
	height: 300px;
}

#revise {
	background-color: #FFA000;
	width: 100px;
	border: 1px solid #707070;
	margin-left: 20px;
}

#order {
	margin: auto;
}

.card {
	margin: auto;
	width: 800px;
	text-align: left;
}

h5.card-header {
	background-color: #cce5ff;
}

.btn btn-primary {
	text-align: right;
}

#coinBar {
    margin:auto;
	height: 80px;
	width: 800px;
	text-align: left;
	background-color: white;
	margin-bottom:50px;
}
#coinpic{
    color:#FFA000;
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
<div id="coinBar">
   <i class="fas fa-coins" id="coinpic"></i><span style="color:#FFA000;font-size:40px;padding: 30px;top: 10px;
    position: relative;">${memberVO.m_coin}</span><span>擁有的堃幣</span>
    <a href="#" data-toggle="tooltip" title="堃幣是遊戲堃內的專屬代幣，能1:1折抵消費
每消費滿100即獲得1塊堃幣，消費越多折扣越多!"><i class="far fa-question-circle" style="color:gray;font-size:30px;"></i></a>

</div>

  <table id="order">

	<c:forEach var="orderlistVO" items="${list}">

				<div class="card">
					<h5 class="card-header">訂單編號: ${orderlistVO.o_id}</h5>
					<div class="card-body">
					    <h5 class="card-title">時間: ${orderlistVO.o_dateForm}</h5>
						<h5 class="card-title">總金額: ${orderlistVO.o_total}</h5>
						<h5 class="card-title">獲得堃幣: <span style="color:#FFA000;">${orderlistVO.o_pm}</span></h5>

					</div>
				</div>

			</c:forEach>
			
     </table>   






	
<!-- --------------------------------------------------------------------------- -->
</div>
<script type="text/javascript">
$(function(){
	$('[data-toggle="tooltip"]').tooltip();
});
</script>


	<div class="footer">
		<jsp:include page="../footer.jsp"></jsp:include>
	</div>


</body>



</html>