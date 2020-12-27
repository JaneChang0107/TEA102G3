<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.product.model.ProductVO" %>

<% 
	ProductVO pVO = (ProductVO) request.getAttribute("pVO"); 
	session.setAttribute("mid", "M00001");

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addProduct</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/vendors/bootstrap-4.5.3-dist/css/bootstrap.min.css">
<style>
	
	div#showImg > img.viewImg{
		width:200px;
	}
	div#addProductPlace{
		position: relative;
		width: 50%;
		left: 50%;
		top: 10%;
		transform: translateX(-50%);
	}

	
</style>
</head>
<body>
	<div class="header">
		<jsp:include page="/Front_end/header.jsp"></jsp:include>
	</div>

	<div id="addProductPlace">
	<jsp:useBean id="ptService" scope="page" class="com.productType.model.ProductTypeService"></jsp:useBean>
	    
	    <h1>新增</h1>
	    
	    <c:if test="${!errors.isEmpty()}">
	    	<c:forEach var="error" items="${errors}">
	    		<p style="color:red">${error}</p>
	    	</c:forEach>
	    </c:if>
	    
	    <form action="<%= request.getContextPath() %>/ProductServlet" method="POST" enctype="multipart/form-data">
	        <div id="productName">
	            <label>商品名稱</label>            
	            <input type="text" name="pname" value="<%= pVO == null ? "" : pVO.getP_name() %>">
	        </div>
	        <div id="productType">
	            <label>商品種類</label>            
	            <select name="ptid">
	            	<option value="">請選擇</option>
	            <c:forEach var="ptVO" items="${ptService.all}">
	                <option value="${ptVO.pt_id}"${ptVO.pt_id == pVO.pt_id ? "selected" : ""}>${ptVO.pt_platform} ${ptVO.pt_kind}</option>
	             </c:forEach>
	            </select>
	        </div>
	        <div id="productPrice">
	            <label>商品價格</label>            
	            <input type="text" min="0" name="pprice" value="<%= pVO == null ? "" : pVO.getP_price() %>">
	        </div>
	        <div id="productPrice">
	            <label>商品數量</label>            
	            <input type="text" min="0" name="pcount" value="<%= pVO == null ? "" : pVO.getP_count() %>">
	        </div>
	        <div id="productPicture">
	            <label>商品圖片</label>            
	            <input type="file" name="img" id="uploadImg" accept="image/*" multiple>
	            <div id="showImg">
	            </div>
	        </div>
	        <div id="productStatus">
	            <label>商品上下架</label>            
	            <input type="radio" id="0" name="pstatus" value="0" checked><label for="0">下架</label>
	            <input type="radio" id="1" name="pstatus" value="1"><label for="1">上架</label>
	        </div>
	        <div id="productDetail">
	            <label>商品介紹</label>            
	            <textarea name="pdetail" id="editor" cols="30" rows="10"><%= pVO == null ? "" : pVO.getP_detail() %></textarea>
	        </div>
	        <div>
		        <input type="hidden" name="action" value="insert">
		        <input type="submit" class="btn btn-primary" value="送出">
	        </div>
	    </form>
	</div>    
    
    <div class="footer">
		<jsp:include page="/Front_end/footer.jsp"></jsp:include>
	</div>
    
    <script src="<%= request.getContextPath() %>/vendors/jquery/jquery-3.5.1.min.js"></script>
	<script src="<%= request.getContextPath() %>/vendors/popper/popper.min.js"></script>
    <script src="<%= request.getContextPath() %>/vendors/bootstrap-4.5.3-dist/js/bootstrap.min.js"></script>
    <script src="<%= request.getContextPath() %>/vendors/ckeditor/ckeditor.js"></script>
	<script src="<%= request.getContextPath() %>/Front_end/product/js/previewImg.js"></script>
	<script>
		CKEDITOR.replace('editor');
		
	</script>
</body>
</html>