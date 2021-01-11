<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.member.model.*"%>
<%@page import="java.util.*"%>
<%@page import="com.orderlist.model.*"%>
<%@page import="com.viewseller.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	//拿到登入ID
    HttpSession Session = request.getSession();
	String m_id = Session.getAttribute("loginId").toString();

	//拿到memberVO
// 	MemberService memSvc = new MemberService();
// 	MemberVO memberVO = memSvc.findOneMem(m_id);
// 	session.setAttribute("memberVO", memberVO);

	//用訂單service拿到屬於會員的訂單
	OrderlistService orderlistSvc = new OrderlistService();
	List<OrderlistVO> list = orderlistSvc.findByMember(m_id);
	pageContext.setAttribute("list", list);

// 	ProductVO pVO =(ProductVO)request.getAttribute("pVO");

// 	String m_sellid= pVO.getM_id();
// 	ViewsellerService vsc = new ViewsellerService();
// 	List<ViewsellerVO> list = vsc.findBysellid(m_sellid);
// 	request.setAttribute("list", list);
%>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>我的訂單</title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/vendors/bootstrap-4.5.3-dist/css/bootstrap.min.css">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/viewseller.css">
<style>
img#productImg {
	height: 200px;
	overflow: hidden;
	text-align: center;
	line-height: 200px;
}

div.col-md-3 {
	margin: 70px 30px auto 30px;
}
</style>
</head>

<body class="mybody">


	<!-- header----------->
	<div class="header">
		<jsp:include page="/Front_end/header.jsp"></jsp:include>
	</div>
	<!-- header----------->

	<!-- --------------------------------------------------------------------------- -->

	<!--Nav bar區域-->

	<jsp:useBean id="ppService" scope="page"
		class="com.productPicture.model.ProductPictureService"></jsp:useBean>


	<!-- 	<div class="row align-items-center"> -->
	<ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
		<li class="nav-item" role="presentation"><a
			class="nav-link active" id="pills-home-tab" data-toggle="pill"
			href="#pills-home" role="tab" aria-controls="pills-home"
			aria-selected="true">全部</a></li>

		<li class="nav-item" role="presentation"><a class="nav-link"
			id="pills-profile-tab" data-toggle="pill" href="#pills-profile"
			role="tab" aria-controls="pills-profile" aria-selected="false">好評</a>
		</li>

		<li class="nav-item" role="presentation"><a class="nav-link"
			id="pills-contact-tab" data-toggle="pill" href="#pills-contact"
			role="tab" aria-controls="pills-contact" aria-selected="false">負評</a>
		</li>

	</ul>
    <jsp:useBean id="memSvc" scope="page"
	    class="com.member.model.MemberService"></jsp:useBean>


	<!-- 全部 -->
	<div class="tab-content" id="pills-tabContent">
		<div class="tab-pane fade show active" id="pills-home" role="tabpanel"
			aria-labelledby="pills-home-tab">

			<table id="order">

				<c:forEach var="orderlistVO" items="${list}">
					<div class="card">
						<h5 class="card-header">買家名稱: ${orderlistVO.m_name}</h5>
						<div class="card-body">
							<h5 class="card-title">訂單編號: ${orderlistVO.o_id}</h5>
							<%-- 						<h5 class="card-title">產品名稱: ${orderlistVO.o_status}</h5> --%>
							<%-- 						<h5 class="card-title">購買數量: ${orderlistVO.o_total}</h5> --%>
							<h5 class="card-title">購買金額: ${orderlistVO.o_total}</h5>

<%-- 							<jsp:useBean id="memSvc" scope="page" --%>
<%-- 								class="com.member.model.MemberService"></jsp:useBean> --%>
<!-- 							<div> -->
<!-- 								<table id="viewseller"> -->
<%-- 									<c:forEach var="viewsellerVO" items="${list}"> --%>

<!-- 										<div class="card"> -->
<!-- 											<h5 class="card-header">會員名稱: -->
<%-- 												${memSvc.findOneMem(viewsellerVO.m_buyid).m_name}</h5> --%>
<!-- 											<div class="card-body"> -->
<%-- 												<h5 class="card-title">評價: ${viewsellerVO.v_gb}</h5> --%>
<%-- 												<h5 class="card-title">評論內容: ${viewsellerVO.v_comment}</h5> --%>

<!-- 											</div> -->
<!-- 										</div> -->

<%-- 									</c:forEach> --%>
<!-- 								</table> -->

<!-- 							</div> -->


							<%-- 								<c:forEach var="viewsellerVO" items="${list}"> --%>
							<%-- 									<h5 class="card-title">是否滿意: ${vslist.v_gb}</h5> --%>
							<%-- 									<h5 class="card-title">評價內容: ${vslist.v_comment}</h5> --%>
							<%-- 								</c:forEach> --%>

							<%-- 						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ViewsellerServlet"  target="_blank" class="detail"> --%>
							<!-- 			            <input type="submit" value="詳情" class="btn btn-primary"> -->
							<%-- 			            <input type="hidden" name="o_id"  value="${orderlistVO.o_id}"> --%>
							<!-- 			            <input type="hidden" name="action"	value="getOrderDetailByOrder"></FORM> -->
						</div>
					</div>

				</c:forEach>

			</table>
		</div>

		<!--新訂單區域  -->
		<div class="tab-pane fade" id="pills-profile" role="tabpanel"
			aria-labelledby="pills-profile-tab">

			<c:forEach var="orderlistVO" items="${neworder}">

				<div class="card">
					<h5 class="card-header">訂單編號: ${orderlistVO.o_id}</h5>
					<div class="card-body">
						<h5 class="card-title">訂單成立: ${orderlistVO.o_dateForm}</h5>
						<h5 class="card-title">訂單狀態: ${orderlistVO.o_status}</h5>
						<h5 class="card-title">總金額: ${orderlistVO.o_total}</h5>

						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/OrderdetailServlet"
							target="_blank" class="detail">
							<input type="submit" value="詳情" class="btn btn-primary">
							<input type="hidden" name="o_id" value="${orderlistVO.o_id}">
							<input type="hidden" name="action" value="getOrderDetailByOrder">
						</FORM>


					</div>
				</div>

			</c:forEach>

		</div>

		<!-- 寄出訂單區域 -->
		<div class="tab-pane fade" id="pills-contact" role="tabpanel"
			aria-labelledby="pills-contact-tab">
			<c:forEach var="orderlistVO" items="${sentorder}">

				<div class="card">
					<h5 class="card-header">訂單編號: ${orderlistVO.o_id}</h5>
					<div class="card-body">
						<h5 class="card-title">訂單成立: ${orderlistVO.o_dateForm}</h5>
						<h5 class="card-title">訂單狀態: ${orderlistVO.o_status}</h5>
						<h5 class="card-title">總金額: ${orderlistVO.o_total}</h5>

						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/OrderdetailServlet"
							target="_blank" class="detail">
							<input type="submit" value="詳情" class="btn btn-primary">
							<input type="hidden" name="o_id" value="${orderlistVO.o_id}">
							<input type="hidden" name="action" value="getOrderDetailByOrder">
						</FORM>

					</div>
				</div>

			</c:forEach>

		</div>


		<!-- --------------------------------------------------------------------------- -->
	</div>

	<!-- ----footers---- -->
	<div class="footer">
		<jsp:include page="/Front_end/footer.jsp"></jsp:include>
	</div>
	<!-- ----footer---- -->
</body>



</html>