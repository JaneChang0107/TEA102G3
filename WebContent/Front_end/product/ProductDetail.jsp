<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.viewseller.model.*"%>
<%@ page import="com.member.model.*"%>

<%
	// ProductVO pVO = new ProductVO();
	ProductVO pVO = (ProductVO) request.getAttribute("pVO");

	String m_sellid = pVO.getM_id();
	ViewsellerService vsc = new ViewsellerService();
	List<ViewsellerVO> list = vsc.findBysellid(m_sellid);
	request.setAttribute("list", list);
	request.setAttribute("m_sellid", m_sellid);

// 	MemberService memSvc = new MemberService();
// 	MemberVO memberVO = memSvc.findOneMem(m_sellid);
// 	session.setAttribute("memberVO", memberVO);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ProductDetail</title>

<style>
* {
	border: solid 0px gray;
}


img.productImg {
	width: 250px;
	background-color:white;
}

#seller{
  margin: 0px auto;/*div對齊效果*/
  text-align: center;/*display: inline對齊效果*/
  width: 1200px;
  height: 200px;
  paddin:20px auto;
 
}

#right {
  margin: 0px auto;
  text-align: center;/*display: inline對齊效果*/
  display: inline-block;/*讓div並排*/ 
  vertical-align: top;/*就算個個div行數不同，也一律向上對齊*/ 
  width: 900px;
  height:500px;
  border-radius:3px;
  
  
 
  }
#left {
   margin: 0px auto;
  text-align: center;/*display: inline對齊效果*/
  display: inline-block;/*讓div並排*/ 
  vertical-align: top;/*就算個個div行數不同，也一律向上對齊*/ 
  width: 300px;
  height: 600px;
  border-radius:3px;

  }
  #info{
  margin: 0px auto;
   width: 1200px;
  height: 600px;
  }
  #productinfo{
  margin: 0px auto;
   background-color:white;
    text-align:center;
  }
  span{
  font-size:24px;
  }
   #content{
   width:100%;
  margin: 0px auto;
   background-color:white;
    text-align:center;
  }
  #headpic{
  border-radius:50%;
  width:200px;
  height:200px;
  
  }
  .all{
  height:1800px;
  }
  #viewseller{
  height:auto;
  
  }
  
</style>
</head>
<body>
	<div class="header">

		<jsp:include page="/Front_end/header.jsp"></jsp:include>
	</div>
<div class="all">
	<jsp:useBean id="memSvc" scope="page"
			class="com.member.model.MemberService"></jsp:useBean>

<div style="height:50px">
  </div>
  <div id="seller"><img src="${memSvc.findOneMem(m_sellid).m_cover2}" width=1200px height=200px>
  </div>
  <br>
  <div id="info">
  <div id="left">
  <div >
 <img src="${memSvc.findOneMem(m_sellid).m_headpic2}" id="headpic">
  <br>
 <h4>${memSvc.findOneMem(m_sellid).m_name}</h4>
  </div>
  		<form id="myForm"
				action="<%=request.getContextPath()%>/chat/chatsell.jsp"
				method="POST"  target="_blank">
				<input type="submit" value="賣場聊聊" class="btn btn-success"> <input type="hidden"
					name="m_id" value="${pVO.m_id}"> <input type="hidden"
					name="action" value="">
			</form>
		
  </div><div id="right"><div>
		<jsp:useBean id="ppService" scope="page"
			class="com.productPicture.model.ProductPictureService"></jsp:useBean>
  
  		<form action="<%=request.getContextPath()%>/BuyServlet" method="POST">
  		<div id="productinfo">
		<c:forEach var="ppVO" items="${ppService.findProductPicture(pVO.p_id)}">
		<img src="<%= request.getContextPath() %>/ShowPicture?type=ppid&id=${ppVO.pp_id}" class="productImg" name="p_img">
		</c:forEach> 		
  		</div>
			<table class="table3">
				<tr>
						<div id="productinfo">
						<Table id="content">
							  <tr>
							    <td><span>商品ID:</span></td>
							     <td><span id="productId" name="p_id">${pVO.p_id}</span> </td>
							  </tr>
							   <td><span>商品名稱:</span></td>
							     <td><span id="productName" name="p_name">${pVO.p_name}</span></td>
							  <tr>
							    <td><span>平台:</span></td>
							     <td><span id="productCount">${ptVO.pt_platform}</span></td>
							    </tr>
							  <tr>
							    <td><span>商品數量:</span></td>
							     <td><span id="productCount" name="p_count">${pVO.p_count}</span></td>
							    </tr>
							  <tr>
							    <td><span>商品價格:</span></td>
							     <td><span id="productPrice" name="p_price">${pVO.p_price}</span></td>
							    </tr>
							     <tr>
							    <td><span>種類:</span></td>
							     <td><span id="productKindS" name="p_kind">${ptVO.pt_kind}</span></td>
							    </tr>
							     <tr>
							    <td name="p_detail"><span>商品簡介:</span></td>
							     <td ><span> ${pVO.p_detail}</span></td>
							    </tr>
							    <tr>
							    <td colspan=2> <input type="submit" value="放入購物車" class="btn btn-info"></td> 
							    </tr>
							</Table>
						</div>
						</tr>
						

			</table>
				 <input type="hidden" name="action" value="addCart">
				 <input type="hidden" name="p_id" value="${pVO.p_id}">
				 <input type="hidden" name="p_name" value="${pVO.p_name}">
				 <input type="hidden" name="p_price" value="${pVO.p_price}">
				 <input type="hidden" name="p_kind" value="${ptVO.pt_id}">
				 <input type="hidden" name="p_count" value="${pVO.p_count}">
				 <input type="hidden" name="p_detail" value="${pVO.p_detail}">
				 <input type="hidden" name="m_id" value="${pVO.m_id}">
				
		</form>
  
  </div>
 
  <div id="viewseller">
				<c:forEach var="viewsellerVO" items="${list}">

					<div class="card">
						<h5 class="card-header">會員名稱:
							${memSvc.findOneMem(viewsellerVO.m_buyid).m_name}</h5>
						<div class="card-body">
							<h5 class="card-title">評價: ${viewsellerVO.v_gb}</h5>
							<h5 class="card-title">評論內容: ${viewsellerVO.v_comment}</h5>

						</div>
					</div>

				</c:forEach>
  </div>
  
  </div>
</div>
 </div> 


	<div class="footer">
		<jsp:include page="/Front_end/footer.jsp"></jsp:include>
	</div>

</body>
</html>