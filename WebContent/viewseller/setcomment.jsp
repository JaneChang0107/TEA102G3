<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.viewseller.model.ViewsellerVO"%>

<%
	ViewsellerVO viewVO = (ViewsellerVO) request.getAttribute("viewVO");
    pageContext.setAttribute("viewVO", viewVO);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addProduct</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/vendors/bootstrap-4.5.3-dist/css/bootstrap.min.css">

<style>
div#showImg>img.viewImg {
	width: 200px;
}

div#addComment {
	position: relative;
	width: 50%;
	left: 50%;
	top: 10%;
	transform: translateX(-50%);
}

btn btn-primary {
	text-align: right;
}
</style>

</head>

<body>
<!-- header----------->
	<div class="header">
		<jsp:include page="/Front_end/header.jsp"></jsp:include>
	</div>
<!-- header----------->


	<div id="addComment">
		<jsp:useBean id="viewsellerService" scope="page"
			class="com.viewseller.model.ViewsellerService"></jsp:useBean>
		<%-- 	<jsp:useBean id="ptService" scope="page" class="com.productType.model.ProductTypeService"></jsp:useBean> --%>

		<h1>留下評論：</h1>

		<c:if test="${!errors.isEmpty()}">
			<c:forEach var="error" items="${errors}">
				<p style="color: red">${error}</p>
			</c:forEach>
		</c:if>

		<FORM METHOD="post"
			ACTION="<%=request.getContextPath()%>/ViewSellerServlet" name="form1">

			<div id="o_id" style="display: none">
				<label>訂單編號</label> <input type="text"
					value="<%=viewVO == null ? "" : viewVO.getO_id()%>">
			</div>

			<div id="m_buyid" style="display: none">
				<label>買家名稱</label> <input type="text"
					value="<%=viewVO == null ? "" : viewVO.getM_buyid()%>">
			</div>

			<div id="m_sellid" style="display: none">
				<label>賣家名稱</label> <input type="text"
					value="<%=viewVO == null ? "" : viewVO.getM_sellid()%>">
			</div>

			<div id="choose_gb">
				<label>選擇好壞：</label> <input type="radio" id="2" name="v_gb"
					value="2" checked><label for="2">值得鼓勵</label> <input
					type="radio" id="1" name="v_gb" value="1"><label for="1">不甚滿意</label>
			</div>

			<div id="v_comment">
				<label>評論內容：</label>
				<textarea name="comment" cols="40" rows="1"><%=viewVO == null ? "" : viewVO.getV_comment()%></textarea>
			</div>
			<div>
				<input type="hidden" name="action" value="insert"> <input
					type="submit" class="btn btn-primary" value="送出">
			</div>
		</form>
	</div>



<!-- ----footers---- -->
		<div class="footer">
			<jsp:include page="/Front_end/footer.jsp"></jsp:include>
		</div>
<!-- ----footer---- -->


	<script
		src="<%=request.getContextPath()%>/vendors/ckeditor/ckeditor.js"></script>
	<script
		src="<%=request.getContextPath()%>/Front_end/product/js/previewImg.js"></script>
	<script>
		CKEDITOR.replace('editor');
	</script>

</body>
</html>