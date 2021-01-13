<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.member.model.*"%>
<%@page import="java.util.*"%>
<%@page import="com.orderlist.model.*"%>
<%@page import="com.viewseller.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
    String m_sellid =(String)session.getAttribute("loginId");
    ViewsellerService vSvc = new ViewsellerService();
    List<ViewsellerVO> list = vSvc.findBysellid(m_sellid);
    pageContext.setAttribute("list", list);

    int goodNum = 0 ,badNum = 0;
    int sum = 0;
    

    pageContext.setAttribute("goodNum", goodNum);
    pageContext.setAttribute("badNum", badNum);
    pageContext.setAttribute("sum", sum);
    
%>

<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService"></jsp:useBean>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>我的評價</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/vendors/bootstrap-4.5.3-dist/css/bootstrap.min.css">


<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style>
#showAll{
 	margin:0px auto;    
    width:1000px;
}

.tab-content{
	
 	background:white;
  	width:1000px;
  	height:600px;
  	
}

#showViewseller{
	margin:20px auto; 
 	background:white;
 	width:1000px;
  	height:150px;
  	

}
#showViewsellerGB{
	float:left;
	width:333px;
  	height:150px;
  	text-align:center;
}

#tablecontent{
	height:50px;
	width:1000px;
	text-align:center;
	margin:0px auto;
	
}

table{
margin-left:auto; 
margin-right:auto;
}

div#addProductPlace{
		position: relative;
		width: 50%;
		left: 50%;
		top: 10%;
		transform: translateX(-50%);
	}

img#productImg { 
 	height: 200px; 
	overflow: hidden; 
 	text-align: center; 
	line-height: 200px; 
}

    div.col-md-3 { 
 	margin: 70px 30px auto 30px; 
    } 
</style>
</head>
<body style="background: #F5D2CD; height: 100%;">
	<!-- header----------->
<div class="header">
	
	<div class="header">
		<jsp:include page="/Front_end/header.jsp"></jsp:include>
	</div>
	<div>
		<jsp:include page="/Front_end/index_Seller_Buttongroup.jsp"></jsp:include>
	</div>
	<!-- header----------->

	<table>
	<tr>
	<td><h2>我的評價</h2></td>
	</tr>
	<tr>
	<td><div id="showViewseller">
	<br>
	<div id="showViewsellerGB">
	<table>
	<tr>
	<td colspan=2><h3 >全部</h3></td>
	</tr>
	<tr>
  <!-- ---------計算評價數量 start------------- -->
 
		<c:forEach var="viewsellerVO"  items="${list}">
		     <% ViewsellerVO viewsellerVO =(ViewsellerVO)pageContext.getAttribute("viewsellerVO");%>
			<c:choose >
				<c:when test="<%=viewsellerVO.getV_gb().equals(\"good\")%>">
				  <%  pageContext.setAttribute("goodNum",++goodNum);
				  %>	
				</c:when>
		          	<c:otherwise >
					  <% pageContext.setAttribute("badNum",++badNum);
					 
					  %>
		            </c:otherwise>
			</c:choose>
		</c:forEach>	  
	
  <!-- ---------計算評價數量 end----------------- -->
 <% sum=badNum+goodNum;
 pageContext.setAttribute("sum",sum);%>
	<td><h3></h3></td><td><h1 style="color:red"> ${sum} </h1></td>
	</tr>
	</table>
	</div>
	
	<div id="showViewsellerGB">
	<table>
	<tr>
	<td colspan=2><h3>好評</h3></td>
	</tr>
	<tr>
	<td>
<span class="likebtn-wrapper" data-theme="large" data-white_label="true" data-identifier="GOOD" data-show_like_label="false" data-dislike_enabled="false" data-icon_dislike_show="false" data-voting_enabled="false" data-voting_cancelable="false" data-counter_show="false" data-popup_disabled="true" data-tooltip_enabled="false"></span>
	</td><td><h1 style="color:red">  ${goodNum}</h1></td>
	</tr>
	</table>
	</div>
	
	<div id="showViewsellerGB">
	<table>
	<tr>
	<td colspan=2><h3 >負評</h3></td>
	</tr>
	<tr>
	<td>
<img id="img" src="<%=request.getContextPath()%>/images/dislike.PNG"
						width="67px" height="67px" border="0">
	</td><td><h1 style="color:red"> ${badNum}</h1></td>
	</tr>
	</table>
	</div>
	</div></td>
	</tr>
	</table>

	<!-- --------------------------------------------------------------------------- -->
	   
	<table>
	<tr>
	<td><h2>評價列表</h2></td>
	</tr>
	<tr>
	<td>
	<div id="showAll">
	
<!--Nav bar區域 start-->
	<nav>
	  <div class="nav nav-tabs" id="nav-tab" role="tablist">
	    <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab" aria-controls="nav-home" aria-selected="true">全部</a>
	    <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" role="tab" aria-controls="nav-profile" aria-selected="false">好評</a>
	    <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" href="#nav-contact" role="tab" aria-controls="nav-contact" aria-selected="false">負評</a>
	  </div>
	</nav>
<!--Nav bar區域 end-->
	<!-- 全部start -->

	<div class="tab-content" id="nav-tabContent">
	  <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
	  	<table id="tablecontent"  class="table table-hover">
	  	<tr>
		  <td><h4>訂單</h4></td>
		  <td><h4>買家</h4></td>
		  <td><h4>評價</h4></td>
		  <td><h4>評論內容</h4></td>
		 </tr>
		 <c:forEach varStatus="status" var="viewsellerVO" items="${list}"> 
		  <% ViewsellerVO viewsellerVO =(ViewsellerVO)pageContext.getAttribute("viewsellerVO");%>
		      <c:if test="${status.count%3==0}">
		   	    <tr bgcolor="lightyellow">
			  </c:if>
		      <c:if test="${status.count%3==1}">
		   		<tr bgcolor="lightblue">
			  </c:if>				  
		      <c:if test="${status.count%3==2}">
		   		<tr bgcolor="lightgreen">
			  </c:if>
					 <td><h4>${viewsellerVO.o_id} </h4></td>
		             <td><h4>${memSvc.findOneMem(viewsellerVO.m_buyid).m_name}</h4></td>
					 <td><h4>${viewsellerVO.v_gb}</h4></td>
					 <td><h4>${viewsellerVO.v_comment}</h4></td>
		        </tr>
		 </c:forEach>
		</table>  
	</div>
	<!-- 全部  end-->

    <!-- 好評  start-->
	<div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
	 	<table id="tablecontent"  class="table table-hover">
	 	<tr>
		  <td><h4>訂單</h4></td>
		  <td><h4>買家</h4></td>
		  <td><h4>評價</h4></td>
		  <td><h4>評論內容</h4></td>
		 </tr>
		  <c:forEach varStatus="status" var="viewsellerVO" items="${list}"> 
		  <% ViewsellerVO viewsellerVO =(ViewsellerVO)pageContext.getAttribute("viewsellerVO");%>
		        <c:choose>
				  <c:when test="<%=viewsellerVO.getV_gb().equals(\"good\")%>">
		      <c:if test="${status.count%3==0}">
		   	    <tr bgcolor="lightyellow">
			  </c:if>
		
		      <c:if test="${status.count%3==1}">
		   		<tr bgcolor="lightblue">
			  </c:if>
					  
		      <c:if test="${status.count%3==2}">
		   		<tr bgcolor="lightpink">
			  </c:if>
					 <td><h4>${viewsellerVO.o_id} </h4></td>
		             <td><h4>${memSvc.findOneMem(viewsellerVO.m_buyid).m_name}</h4></td>
					 <td><h4>${viewsellerVO.v_gb}</h4></td>
					 <td><h4>${viewsellerVO.v_comment}</h4></td>
		        </tr>
		           </c:when>
				</c:choose> 
		 </c:forEach>
		</table>  
	</div>
    <!-- 好評  end-->
	
    <!-- 負評  start-->
		<div class="tab-pane fade" id="nav-contact" role="tabpanel" aria-labelledby="nav-contact-tab">
	 	<table id="tablecontent"  class="table table-hover">
	 	<tr>
		  <td><h4>訂單</h4></td>
		  <td><h4>買家</h4></td>
		  <td><h4>評價</h4></td>
		  <td><h4>評論內容</h4></td>
		 </tr>
		  <c:forEach varStatus="status" var="viewsellerVO" items="${list}"> 
		  <% ViewsellerVO viewsellerVO =(ViewsellerVO)pageContext.getAttribute("viewsellerVO");%>
		        <c:choose>
				  <c:when test="<%=viewsellerVO.getV_gb().equals(\"bad\")%>">
		      <c:if test="${status.count%3==0}">
		   	    <tr bgcolor="lightyellow">
			  </c:if>
		
		      <c:if test="${status.count%3==1}">
		   		<tr bgcolor="lightblue">
			  </c:if>
					  
		      <c:if test="${status.count%3==2}">
		   		<tr bgcolor="lightpink">
			  </c:if>
					 <td><h4>${viewsellerVO.o_id} </h4></td>
		             <td><h4>${memSvc.findOneMem(viewsellerVO.m_buyid).m_name}</h4></td>
					 <td><h4>${viewsellerVO.v_gb}</h4></td>
					 <td><h4>${viewsellerVO.v_comment}</h4></td>
		        </tr>
		           </c:when>
				</c:choose> 
		 </c:forEach>
		</table>  
	</div>
		</div>
		
		</div></td>
	</tr>
	</table>
	
	
	<div class="footer">
		<jsp:include page="/Front_end/footer.jsp"></jsp:include>
	</div>
</div>
	<!-- ----footer---- -->
	
	<script>(function(d,e,s){if(d.getElementById("likebtn_wjs"))return;a=d.createElement(e);m=d.getElementsByTagName(e)[0];a.async=1;a.id="likebtn_wjs";a.src=s;m.parentNode.insertBefore(a, m)})(document,"script","//w.likebtn.com/js/w/widget.js");</script>
<!-- LikeBtn.com END -->
	
</body>
</html>