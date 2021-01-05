<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
				action="<%=request.getContextPath()%>/websocketchat/NameServlet"
				method="POST" style="position: fixed">
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
					<td><input type="submit" name="Submit" value="放入購物車" ></td>>
				</tr>
				<tr>
					<td colspan=2><div id="pDetail" name="p_detail">
							<span>商品簡介:</span> ${pVO.p_detail}
						</div></td>
				</tr>

			</table>
			<input type="hidden" name="action" value="addCart"> 
			<input type="hidden" name="p_id" value="${pVO.p_id}"> 
			<input type="hidden" name="p_name" value="${pVO.p_name}">
			<input type="hidden" name="p_price" value="${pVO.p_price}">
			<input type="hidden" name="p_kind" value="${ptVO.pt_id}">
			<input type="hidden" name="p_count" value="${pVO.p_count}">
			<input type="hidden" name="p_detail" value="${pVO.p_detail}">
			<input type="hidden" name="m_id" value="${pVO.m_id}">
 
		</form>

	</div>

	<div class="footer">
		<jsp:include page="/Front_end/footer.jsp"></jsp:include>
	</div>
</body>
</html>