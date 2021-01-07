<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.productType.model.*"%>
<%@ page import="com.member.model.*"%>

<%
	Vector<ProductVO> buylist = (Vector<ProductVO>) session.getAttribute("shoppingcart");

%>

<jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" />
<jsp:useBean id="productpicSvc" scope="page" class="com.productPicture.model.ProductPictureService" />
<jsp:useBean id="producttypeSvc" scope="page" class="com.productType.model.ProductTypeService" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>您的購物車-我要買</title>
<style>
* {
	border: 0px solid gray;
}

.mybody {
	background-color: #E3F8F6;
}

.content {
	margin: 20px auto;
	background-color: white;
	width: 1000px;
	height: 250px;
	/* 	border: solid 1px */
}

.null {
	margin: 20px auto;
	width: 1000px;
	height: 250px;
	/* 	border: solid 1px */
}

.total {
	margin: 10px auto;
	text-align: right;
	width: 1000px;
	height: 150px;
	/* 	border: solid 1px */
}

.buttonarea {
	margin: 10px auto;
	background-color:;
	width: 1000px;
	height: 100px;
	/* 	border: solid 1px */
}

.table1 {
	margin: 20px auto;
	background-color: white;
	width: 800px;
	height: 50px;
}

.cart {
	width: 100px;
	height: 50px;
	text-align: center;
}

.title {
	margin: 40px;
}


.data {
	margin: 0px auto;
	background-color: white;
	width: 800px;
	height: 100px;
}

.pic {
	margin: 0px auto;
	background-color: white;
	width: 100px;
	height: 100px;
}


.checkbox1 {
	height: 20px;
	width: 20px;
}

.button1 {
	background-color: white;
	height: 50px;
	width: 400px;
	border-radius: 10px;
}

.button2 {
	background-color: white;
	height: 50px;
	width: 400px;
	border-radius: 10px;
	float: right;
}

.button1:hover {
	background-color: #008CBA;
	color: white;
}

.button2:hover {
	background-color: #008CBA;
	color: white;
}

table {
	border-collapse: collapse;
	table-layout: fixed;
}

.counter {
	border: 1px solid gray;
}

.footer{
    position: absoloute;
    bottom: 0px;
}

#numberbox{
	border:1px solid gray;
}
</style>

</head>
<body class="mybody">
	<link rel="stylesheet"
		href="../../vendors/bootstrap/css/bootstrap.min.css">

	<div class="header">
		<jsp:include page="../header.jsp"></jsp:include>
	</div>

	<div>
		<h2 class="title">您的購物車-我要買</h2>
		<hr>
		
<%
 	if (buylist != null && (buylist.size() > 0)){
 	
%>
<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/BuyServlet">

		<c:forEach var="productVO" items="${shoppingcart}" varStatus="loop">
		
			<%
			ProductVO order = null;
			ProductTypeVO ptv = null;
			int index = 0;
			int counter = buylist.size();
			
			for (index = 0; index < buylist.size(); index++) {
				order = buylist.get(index);
				
			}
		%>
		
	</div>


					
	
					
	<div class="content">

					
		<table class="table1">
			<tr>
				<td colspan=8>
					<h4 align="left"><c:out value="${productVO.m_id}" />的賣場</h4>

				</td>
			</tr>
			
			
			<tr class="cart">

				<td name="id">${loop.count}</td>
				<td>商品圖片</td>
				<td>品名</td>
				<td>規格</td>
				<td>單價</td>
				<td>數量</td>
				<td>金額</td>
				<td>
				<a href="<%=request.getContextPath()%>/BuyServlet?action=DELETE&del=${loop.index}">刪除</a>
				</td>
			</tr>
			<tr class="cart">

				<td></td>
				<td><img class="pic" src="data:image/jpg;base64,${productpicSvc.findFirstOneProductPicture(productVO.p_id).pp_picture64} ">
					</td>

				<td><c:out value="${productVO.p_name}" /></td>
				<td>
						<c:out value="${productVO.pt_idName}" />
						</td>
				<td  name="price"><c:out value="${productVO.p_price}" />
				<input type="number"  class="price${loop.count}"  name="sum" class="price${loop.count}" class="form-control" value="${productVO.p_price}" hidden></input> 
				
				</td>

				<td align="center">
					
				<input type="number" min="1" max="${productVO.p_count}" step="1" class="numberbox${loop.count}" name="xx${loop.count}" id="numberbox"><br>


<!-- 						<button id="test" class="${loop.count}" type="button">test</button> -->
							<%-- 錯誤表列 --%>
					    <c:if test="${not empty errorMsgs}">
							
								<c:forEach var="message" items="${errorMsgs}">
									<font style="color: red">${message}</font>
								</c:forEach>
							
						</c:if>
<!-- 						<button type="button" id="test" >calculate</button> -->
<!-- 						onclick="numbertest()" -->
						<br>
						庫存:${productVO.p_count}
				</td>
			
				<td >
					<p class="sum${loop.count}">0</p>
				</td>
				<td></td>

			</tr>
		</table>
	</div>
	<hr>
	
		<script>
$(function(){
            $("input[class^='numberbox${loop.count}']").change(function(){
               var value1 = parseFloat($("input[class^='price${loop.count}']").val()) || 0;
               var value2 = parseFloat($("input[class^='numberbox${loop.count}']").val()) || 0;
               $("p[class^='sum${loop.count}']").html(value1 * value2);
               return false;
            });
         });

	</script>
	</c:forEach>

	<div class="buttonarea">
		<button type="button" class="button1" onclick="history.back()">回上一頁</button>
		<button type="submit" class="button2">進入結帳</button>
		<input type="hidden" name="action" value="CALCULATE">	
	</div>

				</form>
			</div>
		</div>
	</div>
	</form>
	<%
 	} else {
	%>

	<div class="null"><tr><td><h3 align=center>您的購物車現在沒有商品</h3></td></tr></div>
	<div class="buttonarea"> 
	<button type="button" class="button1" onclick="history.back()">回上一頁</button>
	<input type ="button" class="button2" onclick="javascript:location.href='<%=request.getContextPath()%>/Front_end/index.jsp'" value="回首頁"></input>
	</div>
<%} %>	
	<div class="footer">
		<jsp:include page="../footer.jsp"></jsp:include>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.js"
		integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
		crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha.6/js/bootstrap.min.js">
	</script>
	


</body>

</html>