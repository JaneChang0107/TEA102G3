<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.product.model.ProductVO" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="<%= request.getContextPath() %>/vendors/bootstrap-4.5.3-dist/css/bootstrap.min.css">
<style>
	div#editProduct{
		display: block;
		position: absolute;
		width: 50%;
		left: 50%;
		transform: translateX(-50%);
	}
	div.dbImg > img{
		width:200px;
	}
	div#showImg > img.viewImg{
		width:200px;
	}
	div#editProduct{
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

	<div id="editProduct">
		<h1>修改</h1>
	    
	    <c:if test="${!errors.isEmpty()}">
	    	<c:forEach var="error" items="${errors}">
	    		<p style="color:red">${error}</p>
	    	</c:forEach>
	    </c:if>
	<jsp:useBean id="ptService" scope="page" class="com.productType.model.ProductTypeService" />
	    <form action="<%= request.getContextPath() %>/ProductServlet" method="POST" enctype="multipart/form-data">
	        <div>
	            <label>商品名稱</label>            
	            <input type="text" name="pname" value='${pVO == null ? "" : pVO.getP_name()}'>
	        </div>
	        <div>
	            <label>商品種類</label>            
	            <select name="ptid">
	            	<option value="">請選擇</option>
	            <c:forEach var="ptVO" items="${ptService.all}">
	                <option value="${ptVO.pt_id}"${ptVO.pt_id == pVO.pt_id ? "selected" : ""}>${ptVO.pt_platform} ${ptVO.pt_kind}</option>
	             </c:forEach>
	            </select>
	        </div>
	        <div>
	            <label>商品價格</label>            
	            <input type="text" min="0" name="pprice" value='${pVO == null ? "" : pVO.getP_price()}'>
	        </div>
	        <div>
	            <label>商品數量</label>            
	            <input type="text" min="0" name="pcount" value='${pVO == null ? "" : pVO.getP_count()}'>
	        </div>
	        <div>
	            <label>商品圖片</label>  
	            <%! Integer i = 0; %>
	            <% i = 0; %>
	        <c:forEach var="ppVO" items="${ppVOs}">
	        	<div class="dbImg">
		        	<img src="<%= request.getContextPath() %>/ShowPicture?type=ppid&id=${ppVO.pp_id}" class="showImg">
		        	<input type="hidden" name="<%= "ppid" + ++i %>" value="${ppVO.pp_id}">
		            <input type="file" name="<%= "img" + i %>" class="uploadImg" accept="image/*">
	            </div>
	        </c:forEach>
	        	<input type="file" name="img" id="uploadImg" accept="image/*" multiple>
	        	<div id="showImg">
	            </div>
	        </div>
	        <div>
	            <label>商品上下架 (需先經過審核)</label>            
	            <input type="radio" id="0" name="pstatus" value="2" checked><label for="2">下架</label>
	            <input type="radio" id="1" name="pstatus" value="1"><label for="1">上架</label>
	        </div>
	        <div>
	            <label>商品介紹</label>            
	            <textarea name="pdetail" id="editor" cols="30" rows="10">${pVO == null ? "" : pVO.getP_detail()}</textarea>
	        </div>
	        
	        <input type="hidden" name="pid" value="${pVO.p_id}">
	        <input type="hidden" name="action" value="update">
	        <input type="submit" class="btn btn-primary" value="送出">
	    </form>
	</div>
	
	<div class="footer">
			<jsp:include page="/Front_end/footer.jsp"></jsp:include>
	</div>
   
    <script src="<%= request.getContextPath() %>/vendors/ckeditor/ckeditor.js"></script>
    <script src="<%= request.getContextPath() %>/Front_end/product/js/previewImg.js"></script>
	<script>
		CKEDITOR.replace('editor');
	
	</script>
</body>
</html>