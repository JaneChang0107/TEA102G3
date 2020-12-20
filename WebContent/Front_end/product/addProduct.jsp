<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.product.model.ProductVO" %>

<% ProductVO pVO = (ProductVO) request.getAttribute("pVO"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addProduct</title>
<style>
	
</style>
</head>
<body>
<jsp:useBean id="ptService" scope="page" class="com.productType.model.ProductTypeService"></jsp:useBean>
    <h1>新增</h1>
    
    <c:if test="${!errors.isEmpty()}">
    	<c:forEach var="error" items="${errors}">
    		<p style="color:red">${error}</p>
    	</c:forEach>
    </c:if>
    
    <form action="<%= request.getContextPath() %>/ProductServlet" method="POST" enctype="multipart/form-data">
        <div>
            <label>商品名稱</label>            
            <input type="text" name="pname" value="<%= pVO == null ? "" : pVO.getP_name() %>">
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
            <input type="text" min="0" name="pprice" value="<%= pVO == null ? "" : pVO.getP_price() %>">
        </div>
        <div>
            <label>商品數量</label>            
            <input type="text" min="0" name="pcount" value="<%= pVO == null ? "" : pVO.getP_count() %>">
        </div>
        <div>
            <label>商品圖片</label>            
            <input type="file" name="img" id="uploadImg" accept="image/*" multiple>
        </div>
        <div>
            <label>商品狀態</label>            
            <input type="radio" id="0" name="pstatus" value="0" checked><label for="0">下架</label>
            <input type="radio" id="1" name="pstatus" value="1"><label for="1">上架</label>
        </div>
        <div>
            <label>商品介紹</label>            
            <textarea name="pdetail" cols="30" rows="10"><%= pVO == null ? "" : pVO.getP_detail() %></textarea>
        </div>
        
        <input type="hidden" name="mid" value="M00001">
        <input type="hidden" name="action" value="insert">
        <input type="submit" value="送出">
    </form>
</body>
</html>