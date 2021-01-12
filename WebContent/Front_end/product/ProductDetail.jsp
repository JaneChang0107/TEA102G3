<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.viewseller.model.*"%>
<%@ page import="com.member.model.*"%>

<%
	// ProductVO pVO = new ProductVO();
	ProductVO pVO = (ProductVO) request.getAttribute("pVO");

	String m_sellid = pVO.getM_id();
	ViewsellerService vsc = new ViewsellerService();
	List<ViewsellerVO> list = vsc.findBysellid(m_sellid);
	request.setAttribute("list", list);

// 	MemberService memSvc = new MemberService();
// 	MemberVO memberVO = memSvc.findOneMem(m_sellid);
// 	session.setAttribute("memberVO", memberVO);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ProductDetail</title>

<style>
* {
	border: solid 0px gray;
}

p#productName {
	font-size: 60px;
}

div#productDetail {
	position: relative;
	width: 50%;
	left: 50%;
	top: 10%;
	transform: translateX(-50%);
	background-color: white;
}

img.productImg {
	width: 200px;
}
</style>
</head>
<body>
	<div class="header">

		<jsp:include page="/Front_end/header.jsp"></jsp:include>
	</div>

	<div id="productDetail">
		<jsp:useBean id="ppService" scope="page"
			class="com.productPicture.model.ProductPictureService"></jsp:useBean>


		<div class="article">
			<td>${pVO.m_id}</td>
			<form id="myForm"
				action="<%=request.getContextPath()%>/chat/chatsell.jsp"
				method="POST" style="position: fixed;" target="_blank">
				<input type="submit" value="賣場聊聊"> <input type="hidden"
					name="m_id" value="${pVO.m_id}"> <input type="hidden"
					name="action" value="">
			</form>
		</diV>
		
		


		<form action="<%=request.getContextPath()%>/BuyServlet" method="POST">
			<table class="table3">
				<tr>
					<td rowspan=8 align=center><h4>
							<div id="pImg">
								<c:forEach var="ppVO"
									items="${ppService.findProductPicture(pVO.p_id)}">
									<img
										src="<%= request.getContextPath() %>/ShowPicture?type=ppid&id=${ppVO.pp_id}"
										class="productImg" name="p_img">
								</c:forEach>
							</div>
						</h4></td>
					<td><h4>
							<div id="pInfo">
								<div id="pInfoName">
									<span id="productId" name="p_id">${pVO.p_id}</span> <span>商品名稱:</span>
									<span id="productName" name="p_name">${pVO.p_name}</span>
								</div>
						</h4></td>

				</tr>
				<tr>
					<td><button>聯絡賣家</button></td>
				</tr>
				<tr>
					<td>讚/倒讚</td>

				</tr>
				<tr>
					<td><div id="xxx">
							<span>平台:</span><span id="productCount">${ptVO.pt_platform}</span>
						</div></td>

				</tr>
				<tr>
					<td><div id="pInfoCount">
							<span>商品數量:</span><span id="productCount" name="p_count">${pVO.p_count}</span>
						</div></td>

				</tr>
				<tr>
					<td><div id="pInfoPrice">
							<span>商品價格:</span><span id="productPrice" name="p_price">${pVO.p_price}</span>
						</div></td>

				</tr>
				<tr>
					<td><span>種類:</span><span id="productKindS" name="p_kind">${ptVO.pt_kind}</span></td>
				</tr>
				<tr>
					<td><input type="submit" name="Submit" value="放入購物車"></td>>
				</tr>
				<tr>
					<td colspan=2><div id="pDetail" name="p_detail">
							<span>商品簡介:</span> ${pVO.p_detail}
						</div></td>
				</tr>

			</table>
			<input type="hidden" name="action" value="addCart"> <input
				type="hidden" name="p_id" value="${pVO.p_id}"> <input
				type="hidden" name="p_name" value="${pVO.p_name}"> <input
				type="hidden" name="p_price" value="${pVO.p_price}"> <input
				type="hidden" name="p_kind" value="${ptVO.pt_id}"> <input
				type="hidden" name="p_count" value="${pVO.p_count}"> <input
				type="hidden" name="p_detail" value="${pVO.p_detail}"> <input
				type="hidden" name="m_id" value="${pVO.m_id}">

		</form>
		
		<jsp:useBean id="memSvc" scope="page"
			class="com.member.model.MemberService"></jsp:useBean>
		<div>
			<table id="viewseller">
				<c:forEach var="viewsellerVO" items="${list}">

					<div class="card">
						<h5 class="card-header">會員名稱:
							${memSvc.findOneMem(viewsellerVO.m_buyid).m_name}</h5>
						<div class="card-body">
							<h5 class="card-title">評價: ${viewsellerVO.v_gb}</h5>
							<h5 class="card-title">評論內容: ${viewsellerVO.v_comment}</h5>

						</div>
					</div>

				</c:forEach>
			</table>

		</div>
	</div>

	<div class="footer">
		<jsp:include page="/Front_end/footer.jsp"></jsp:include>
	</div>
</body>
</html>