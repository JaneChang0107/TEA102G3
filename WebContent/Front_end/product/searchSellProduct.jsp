<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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

        <div class="container">
	      	 <div class="row align-items-center">
	      	 
	      	 </div>
        </div>
		<input type="hidden" id="contextPath" value="<%= request.getContextPath() %>">
        <script src="<%= request.getContextPath() %>/vendors/jquery/jquery-3.5.1.min.js"></script>
		<script src="<%= request.getContextPath() %>/vendors/popper/popper.min.js"></script>
	    <script src="<%= request.getContextPath() %>/vendors/bootstrap-4.5.3-dist/js/bootstrap.min.js"></script>
	    <script src="<%= request.getContextPath() %>/Front_end/product/js/searchSellProduct.js"></script>
    </body>
</html>