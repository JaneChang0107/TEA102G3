<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>
	<div class="container-fluid">
        <div class="row">
            <div class="col">
              <ul class="nav nav-tabs" id="myTab" role="tablist">
			    <li class="nav-item" role="presentation">	<!-- 商品管理 = product_mam -->
			      <a class="nav-link active" id="product_mam-tab" data-toggle="tab" href="#product_mam" role="tab" aria-controls="product_mam" aria-selected="true">商品管理</a>
			    </li>
			    <li class="nav-item" role="presentation">	<!-- 訂單管理 = order_mam -->
			      <a class="nav-link" id="order_mam-tab" data-toggle="tab" href="#order_mam" role="tab" aria-controls="order_mam" aria-selected="false">訂單管理</a>
			    </li>
			  </ul>
			  <div class="tab-content" id="myTabContent">	
			    <div class="tab-pane fade show active" id="product_mam" role="tabpanel" aria-labelledby="product_mam-tab">
					<%@include file="/Back_end/product/showProduct.jsp"%>
				</div>
			    <div class="tab-pane fade" id="order_mam" role="tabpanel" aria-labelledby="order_mam-tab">
					<%@include file="/Back_end/OrderDetail/listAllOrderdetail.jsp"%>
				</div>
			  </div>
        	</div>
        </div>
    </div>
</body>
</html>