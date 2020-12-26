<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>SearchSellProduct</title>
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

        <div class="product">
            <table id="allProduct">
            
            </table>
        </div>
		<input type="hidden" id="contextPath" value="<%= request.getContextPath() %>">
        <script src="<%= request.getContextPath() %>/vendors/jquery/jquery-3.5.1.min.js"></script>
		<script src="<%= request.getContextPath() %>/vendors/popper/popper.min.js"></script>
	    <script src="<%= request.getContextPath() %>/vendors/bootstrap-4.5.3-dist/js/bootstrap.min.js"></script>
	    <script src="<%= request.getContextPath() %>/Front_end/product/js/searchSellProduct.js"></script>
    </body>
</html>