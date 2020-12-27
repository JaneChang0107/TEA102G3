<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>SearchSellProduct</title>
        
        <link rel="stylesheet" href="<%= request.getContextPath() %>/vendors/bootstrap-4.5.3-dist/css/bootstrap.min.css">
        <style>
        	img{
        		width: 100px;
        		height: 200px;
        		overflow: hidden;
        		text-align: center;
				line-height: 200px;
        	}
        	div.col-md-4{
        		margin: 70px 0 auto 0;
        	}
        </style>
    </head>
    <body>
    
        <div id="search">
            <span>商品種類:</span>
            <select name="ptype" id="ptype">
                <option value="no">請選擇</option>
            </select>
            <span>商品名稱:</span>
            <input type="text" name="pname" id="pname">
            <input type="button" id="searchBtn" value="搜尋">
        </div>
<jsp:useBean id="ppService" scope="page" class="com.productPicture.model.ProductPictureService"></jsp:useBean>
        <div class="container">
	      	<div class="row align-items-center">
	      	
		<c:forEach var="value" items="${pVOs}">
			<div class="col-md-4">
			        <div class="card" style="width: 18rem;">
			        <img src="<%= request.getContextPath() %>/ShowPicture?type=pp&id=${ppService.findProductRandomPicture(value.p_id)}" class="card-img-top" alt="沒...沒圖片">
			        <div class="card-body">
			            <h5 class="card-title">${value.p_name}</h5>
			            <p class="card-text">平台:${value.pt_idName}</p>
			            <p class="card-text">價格:${value.p_price}</p>
			            <small>上架時間:${value.p_addDateSec}</small>
			            <div>
			                <form action="${contextPath}/ProductServlet" method="POST">
			                    <input type="hidden" name="pid" value="${value.p_id}">
			                    <input type="hidden" name="action" value="findthis">
			                    <input type="submit" class="btn btn-primary" value="詳細">
			                </form>
			            </div>
			        </div>
			    </div>
			</div>
			</c:forEach>
			</div>
        </div>
        
        
        
		<input type="hidden" id="contextPath" value="<%= request.getContextPath() %>">
        <script src="<%= request.getContextPath() %>/vendors/jquery/jquery-3.5.1.min.js"></script>
		<script src="<%= request.getContextPath() %>/vendors/popper/popper.min.js"></script>
	    <script src="<%= request.getContextPath() %>/vendors/bootstrap-4.5.3-dist/js/bootstrap.min.js"></script>
	    <script src="<%= request.getContextPath() %>/Front_end/product/js/searchSellProduct.js"></script>
    </body>
</html>