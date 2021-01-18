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

    int goodNum = 0 ,badNum = 0;

    pageContext.setAttribute("goodNum", goodNum);
    pageContext.setAttribute("badNum", badNum);
	
	
	
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
  margin: auto auto;/*div對齊效果*/
  text-align: center;/*display: inline對齊效果*/
  width: 1200px;
  height: 200px;
  paddin:20px auto;
  margin-left:266px;
}

#right {
  margin: 0px auto;
  text-align: center;/*display: inline對齊效果*/
  display: inline-block;/*讓div並排*/ 
  vertical-align: top;/*就算個個div行數不同，也一律向上對齊*/ 
  width: 890px;
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
  height:1500px;
  }
  #viewseller{
  height:auto;
  text-aligh:left";
  }
  .card {
    position: relative;
    display: -ms-flexbox;
    display: flex;
    -ms-flex-direction: column;
    flex-direction: column;
    min-width: 0;
    word-wrap: break-word;
    background-color: #dee2e6;
    background-clip: border-box;
    border: 1px solid rgba(0,0,0,.125);
    border-radius: .25rem;
}
  i {
    font-size: 4ex;
    position: relative;
    left: 16px;
    top: 2px;
    padding: 1px;
}

.row{
	font-size: 20px;
    flex-wrap: wrap;
    margin-right: auto;
    margin-left: auto;
    
}

.column1 {
    float: left;
    width: 31%;
padding: 33px;
font-color:white;
    background-color:#8dabc9;
   border-color:#adb5bd40;
   border-width:2px;
   border-style:solid;
 border-radius:10%;
 
}
.column2{
    float: left;
    width: 69%;
 padding: 10px 38px;
  	background-color:#adb5bd40;
  	border-color:#adb5bd40;
  	border-width:2px;
  	border-style:solid;


}
.row:after {
  content: "";
  display: table;
  clear: both;
}

</style>
</head>
<body>

		<jsp:include page="/Front_end/header.jsp"></jsp:include>
	
<div class="all">
	<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService"></jsp:useBean>

  <div style="height:50px">
  </div>
  
  <div id="seller"><img src="${memSvc.findOneMem(m_sellid).m_cover2}" style="width:1150px;height:240px"/>
  </div>
  <br>
  <br>
 <div id="info">
  
   <div id="left">
   <!-- ---------計算評價數量 start------------- -->
		<c:forEach var="viewsellerVO"  items="${list}">
		     <% ViewsellerVO viewsellerVO =(ViewsellerVO)pageContext.getAttribute("viewsellerVO");%>
			<c:choose >
				<c:when test="<%=viewsellerVO.getV_gb().equals(\"good\")%>">
				  <%  pageContext.setAttribute("goodNum",++goodNum);%>	
				</c:when>
		          	<c:otherwise >
					  <% pageContext.setAttribute("badNum",++badNum);%>
		            </c:otherwise>
			</c:choose>
		</c:forEach>	  
  <!-- ---------計算評價數量 end----------------- -->
  
  <div class="sellerinfo" style="width:250px;color:blue; font-size:30px; margin:auto";>
        ${memSvc.findOneMem(m_sellid).m_storename}
        <div style="font-style: italic; margin:auto;font-size:20px";>
		 ${memSvc.findOneMem(m_sellid).m_info}
	    </div>
  </div>
  
  <div><img src="${memSvc.findOneMem(m_sellid).m_headpic2}" id="headpic"></div>
  <br>
 <h1>${memSvc.findOneMem(m_sellid).m_name}</h1>
   <i class="far fa-thumbs-up" style="font-size: 3.5ex;position: relative;left: 3px; top: -7px">${goodNum}</i>
   <i class="far fa-thumbs-down"style="font-size: 3.5ex;position: relative;left: 3px; top: -7px">${badNum}</i>
  <br>
  		<form id="myForm" action="<%=request.getContextPath()%>/chat/chatsell.jsp" method="POST" target="_blank">
			<input type="submit" value="賣場聊聊" class="btn btn-success"> 
			<input type="hidden"name="m_id" value="${pVO.m_id}"> 
			<input type="hidden"name="action" value="">
		</form>
</div>
	
  <div id="right">
		<jsp:useBean id="ppService" scope="page" class="com.productPicture.model.ProductPictureService"></jsp:useBean>
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
				     <td><span id="productId" name="p_id">${pVO.p_id}</span></td>
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
				
						
				 <input type="hidden" name="action" value="addCart">
				 <input type="hidden" name="p_id" value="${pVO.p_id}">
				 <input type="hidden" name="p_name" value="${pVO.p_name}">
				 <input type="hidden" name="p_price" value="${pVO.p_price}">
				 <input type="hidden" name="p_kind" value="${ptVO.pt_id}">
				 <input type="hidden" name="p_count" value="${pVO.p_count}">
				 <input type="hidden" name="p_detail" value="${pVO.p_detail}">
				 <input type="hidden" name="m_id" value="${pVO.m_id}">
		</form>

	</tr>
		</table>
		 <br>
		 <br>


<c:forEach var="viewsellerVO" items="${list}">
<div id="viewseller">

  <% ViewsellerVO viewsellerVO =(ViewsellerVO)pageContext.getAttribute("viewsellerVO");%>
	<div class="row" style="background-color:#dee2e6;text-align:left;margin:auto">

	<div class="column1"style="font-color:white;font-size:35px";>
		<img src="${memSvc.findOneMem(viewsellerVO.m_buyid).m_headpic2}" style="width:90px ;height:90px; border-radius:50%";>
		${memSvc.findOneMem(viewsellerVO.m_buyid).m_name}
	</div>
		
	<div class="column2">
		<div class="card-title" style="font-size:22px">評價：					
			<c:choose >
				<c:when test="<%=viewsellerVO.getV_gb().equals(\"good\")%>">
				  	<i class="far fa-thumbs-up" style="font-size: 3.5ex;position: relative;left: 10px; top: 1px;padding: 2px";></i>
				</c:when>
		          	<c:otherwise >
					  <i class="far fa-thumbs-down" style="font-size: 3.5ex;position: relative;left: 10px; top: 1px;padding: 2px";></i>
		            </c:otherwise>
			</c:choose>
		</div>
					
		<div class="card-title" style="font-size:22px">評論內容: ${viewsellerVO.v_comment}</div>
		<div class="card-title" style="font-size:10px"> ${viewsellerVO.v_date}</div>
  
   </div>
 </div>
 </div>
</c:forEach>


	</div>




 </div>
</div>
	</div>
	
	</div>
</div>
</div>
</div>


</div>



		<jsp:include page="/Front_end/footer.jsp"></jsp:include>


</body>
</html>