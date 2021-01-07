<%@page import="com.productPicture.model.ProductPictureVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.productPicture.model.*"%>
<%@ page import="com.member.model.*"%>
<%
	Vector<ProductVO> buylist = (Vector<ProductVO>) session.getAttribute("shoppingcart");
	String m_id = session.getAttribute("loginId").toString();
	MemberService memSvc = new MemberService();
	MemberVO memberVO = memSvc.findOneMem(m_id);
	session.setAttribute("memberVO", memberVO);

%>
<%!Integer amount = 0;%>
<%
	amount = (Integer) request.getAttribute("amount");
%>
<jsp:useBean id="productSvc" scope="page"
	class="com.product.model.ProductService" />
<jsp:useBean id="productpicSvc" scope="page"
	class="com.productPicture.model.ProductPictureService" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>結帳頁面-我要買</title>
<style>
* {
	border: 0px solid gray;
}

.mybody {
	background-color: #E3F8F6;
}

.content {
	margin: 10px auto;
	background-color: white;
	width: 1000px;
	height: 1000px;
	/* 	border: solid 1px */
}

.info {
	margin: 10px auto;
	background-color: white;
	width: 1000px;
	height: 300px;
	/* 	border: solid 1px */
}

.total {
	margin: 10px auto;
	text-align: right;
	width: 1000px;
	height: 280px;
	/*border: solid 1px */
}

.buttonarea {
	margin: 10px auto;
	width: 1000px;
	height: 100px;
	/* 	border: solid 1px */
}

.table1 {
	margin: 0px auto;
	/* 	background-color: white; */
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

.seller {
	
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

.downarrow {
	height: 20px;
	width: 40px;
	/*  	margin: auto;  */
	/*  	float: right;  */
	display: inline-block;
}

.downarrow2 {
	display: block;
	margin: auto;
	float: right;
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

.card-body {
	background-color: #FCF8DC;
}

.table2 {
	text-align: left;
}

.table3 {
	margin: 50px auto;
	background-color: #FCF8DC;
	width: 800px;
	height: 200px;
}

input:read-only {
	background: #D7D7D7;
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
		<h2 class="title">結帳頁面-我要買</h2>
	</div>
<form>	
	<div class="content">
			<%!int i = 0;%>
			<%!String member_id;%>
			<%
				for (i = 0; i < buylist.size(); i++) {
					ProductVO order = buylist.get(i);
					String name = order.getP_name();
					String detail = order.getPt_idName();
					Integer price = order.getP_price();
					Integer count = order.getP_count();
					member_id = order.getM_id();
					String id = order.getP_id();
					ProductPictureVO ppvo = productpicSvc.findFirstOneProductPicture(id);
					String picture = ppvo.getPp_picture64();
					Integer k = Integer.parseInt(request.getParameter("xx" + (i + 1)));
					Integer total = k * count;
			%>
		<hr>
		<table class="table1">
			<th>
				<h4 align="center"><%=member_id%>的賣場</h4>

			</th>

			<tr class="cart">

				<td>商品圖片</td>
				<td>品名</td>
				<td>規格</td>
				<td>單價</td>
				<td>數量</td>
				<td>金額</td>
				<td></td>
				<td></td>

			</tr>

			<tr class="cart">

<input type="text" hidden name="od_count" value="<%=k%>"></input>

				<td><img class="pic" src="data:image/jpg;base64,<%=picture%>">
				</td>
				<td><%=name%></td>
				<td
					style="max-width: 100px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap"><%=detail%>
				</td>
				<td><%=price%></td>
				<td><%=k%></td>
				<td class="kprice<%=i%>"><%=k * price%></td>
				<td></td>
				<td></td>

			</tr>
			<%
				}
			%>
			<tr>
				<th colspan=8><a>確認運送與付款方式</a><img
					src="
					<%=request.getContextPath()%>
					/images/downarrow.png"
					class="downarrow" data-toggle="collapse" href="#collapseExample"
					type="button" aria-expanded="false" aria-controls="collapseExample"></img>
					<br>
					<div class="collapse" id="collapseExample">

						<div class="card-body">
							<table class="table2" width="100%">
								<tr>
									<td><h4>取貨方式</h4></td>
									<td></td>
									<td><h4>付款方式</h4></td>
								</tr>
								<tr>
									<td><label><input type="radio" name="o_transport"
											id="delivery" value=0 required />黑貓宅急便</label></td>
									<td>130</td>
									<td><label><input type="radio" name="pay" value=6 / required>貨到付款</label></td>
								</tr>
								<tr>
									<td><label><input type="radio" name="o_transport"
											id="delivery" value="1" />7-11</label></td>
									<td>60</td>
									<td><label><input type="radio" name="pay" value=7 />信用卡</label></td>
								</tr>

								<tr>
									<td><label><input type="radio" name="o_transport"
											id="delivery" value="2" />全家</label></td>
									<td>60</td>
									<td>信用卡號碼:<input type="text"></input></td>
								</tr>


								<tr>
									<td><label><input type="radio" name="o_transport"
											id="delivery" value="3" />OK mart</label></td>
									<td>60</td>
									<td>持有者姓名:<input type="text"></input> <br>安全碼:<input
										type="text"></input>

									</td>
								</tr>
								<th></th>
								<tr>
									<td><label><input type="radio" name="o_transport"
											id="delivery" value="4" />萊爾富</label></td>
									<td>60</td>
									<td><div class="ooo" id="expiration-date">
											<label>到期年月份</label> <select>
												<option value="01">January</option>
												<option value="02">February</option>
												<option value="03">March</option>
												<option value="04">April</option>
												<option value="05">May</option>
												<option value="06">June</option>
												<option value="07">July</option>
												<option value="08">August</option>
												<option value="09">September</option>
												<option value="10">October</option>
												<option value="11">November</option>
												<option value="12">December</option>
											</select> <select>
												<option value="16">2016</option>
												<option value="17">2017</option>
												<option value="18">2018</option>
												<option value="19">2019</option>
												<option value="20">2020</option>
												<option value="21">2021</option>
											</select>
										</div></td>
								</tr>

								<tr>
									<td><label><input type="radio" name="o_transport"
											id="delivery" value="5" />郵局</label></td>
									<td>70</td>
									<td>使用堃幣折抵<input type="number" min="0" id="kun"
										max="${memberVO.m_coin}" required>/現有${memberVO.m_coin}堃幣
									</td>
									<td></td>
								</tr>
								<tr>
									<td><br>
										<h4>訂購人資料</h4></td>
									<td></td>
									<td><br>
										<h4>收件人資料</h4></td>
								</tr>
								<tr>
									<td>姓名:<input type="text" value="${memberVO.m_name}"
										readonly id="txt1"></input></td>
									<td></td>
									<td>姓名:<input type="text" id="txt2" required></input></td>
								</tr>
								<tr>
									<td>住址:<input type="text" value="${memberVO.m_address}"
										readonly id="txt3"></input></td>
									<td></td>
									<td>住址:<input type="text" id="txt4"  name="o_address" required></input></td>
								</tr>
								<tr>
									<td>電話:<input type="text" value="${memberVO.m_phone}"
										readonly id="txt5"></input></td>
									<td></td>
									<td>電話:<input type="text" id="txt6" required></input></td>
								</tr>
								<tr>
									<td></td>
									<td></td>
									<td><input type="checkbox" id="checkbox"></input>同訂購人資料
									<td>
								</tr>
							</table>
						</div>
					</div></th>
			</tr>
		</table>
	</div>

	<div class="total">
		<h4 id="total">金額<%=amount%>元
		</h4>
		<br>
		<h4>運費<span id="deliverymoney"></span>元</h4>
		<br>
		<h4>總金額<span id="o_total2"></span>元</h4>
		<textarea class="o_total" id="o_total" hidden></textarea>
		折抵後金額:<input type=text class="o_total" id="o_total3" readonly name="o_total" ></input>
		<br>
		<h4>本次共可得到<span id="o_kun2"></span>堃幣</h4>
		<textarea class="o_kun" id="o_kun" hidden name="o_kun" ></textarea> 

	</div>
	<div class="buttonarea">
		<button type="button" class="button1" onclick="history.back()">回上一頁</button>
		<button type="submit" class="button2">確認送出</button>
		<input type="hidden" name="action" value="SUCCESS">	
</form>

	</div>


	<div class="footer">
		<jsp:include page="../footer.jsp"></jsp:include>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.js"
		integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
		crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>

	<script>
    $("#checkbox").change(function() {
        if(this.checked) {
            $('#txt2').val($('#txt1').val()).prop('disabled', false);
            $('#txt4').val($('#txt3').val()).prop('disabled', false);
            $('#txt6').val($('#txt5').val()).prop('disabled', false);
        }
        else {
            $('#txt2').val('').prop('disabled', false);
            $('#txt4').val('').prop('disabled', false);
            $('#txt6').val('').prop('disabled', false);
        }   
      });
    console.log(<%=amount%>);
    
    $('input[name=o_transport]').change(function(){
    	//alert($(this).val())   	
    	 var radioBtn = document.getElementsByName("o_transport");
    		var c;
    	 	 for(i=0; i<radioBtn.length; i++){
    	   	if(radioBtn[i].checked){
    	    	 c = radioBtn[i].value;
    	    	 
    	    	 switch (c) {
    	    	    case '0':
    	    	        c=130;
    	    	        break;
    	    	    case '1':
    	    	    	c=60;
    	    	        break;
    	    	    case '2':
    	    	    	c=60;
    	    	        break;
    	    	    case '3':
    	    	    	c=60;
    	    	        break;
    	    	    case '4':
    	    	    	c=60;
    	    	        break;
    	    	    case '5':
    	    	    	c=70;
    	    	        break;
    	    	    case '6':
    	    	    	
    	    	        break;
    	    	    case '7':
    	    	    	
    	    	        break;
    	    	  
    	    	}
    	   	} 
    	}
    	   document.getElementById("deliverymoney").innerHTML = c;	
    	   document.getElementById("o_total2").innerHTML=c+<%=amount%>;
    	   document.getElementById("o_total").innerHTML=c+<%=amount%>;
    	
    	  
    	  
    	   $(function(){
    	        $('#kun').blur(function(){
    	           var value1 = parseFloat($('#o_total').val()) || 0;
    	           var value2 = parseFloat($('#kun').val()) || 0;
    	           var value3= value1-value2
    	       		$('#o_total3').val(value3);
    	           document.getElementById("deliverymoney").innerHTML = c;	
    	    	   document.getElementById("o_total2").innerHTML=c+<%=amount%>;
    	    	   document.getElementById("o_total").innerHTML=c+<%=amount%>;
    	           document.getElementById("o_kun2").innerHTML=Math.floor(value3*0.01);
    	    	   document.getElementById("o_kun").innerHTML=Math.floor(value3*0.01);
    	        });
    	     });  
    });
    
    $(document).ready(function() {
    	  $(window).keydown(function(event){
    	    if(event.keyCode == 13) {
    	      event.preventDefault();
    	      return false;
    	    }
    	  });
    	});
    
	 </script>

</body>



</html>