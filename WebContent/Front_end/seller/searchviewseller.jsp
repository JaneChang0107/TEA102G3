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
    //List<ViewsellerVO> list = vSvc.findBysellid(m_sellid);
    //pageContext.setAttribute("list", list);

    List<ViewsellerVO> viewList = (List<ViewsellerVO>)request.getAttribute("viewList");
    pageContext.setAttribute("list", viewList);
    
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
.showAll{ 
  	margin:45px auto;     
     width:1000px; 
 } 

.tab-content{
	
 	background:white;
  	width:1000px;
  	height:600px;
  	    text-align: center;
}

#showViewseller{
	margin:20px auto; 
 	background:#ffec076b;
 	width:1000px;
  	height:150px;
  	    text-align: center;

}
#showViewsellerGB{
	float:left;
	width:333px;
  	height:150px;
  	text-align:center;
}
.progress{
width:700px;
margin:20px 0px; 
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
    
b, strong {
    font-weight: bolder;
    font-size: 20px;
    margin: auto;
}
.date{
font-size:20px;
}
.nav-item{
font-size:25px;
}


 
</style>
</head>
<body style="background: #F5D2CD; height: 100%;">
	<!-- header----------->
<div class="header">
	
	<div class="header">
		<jsp:include page="/Front_end/header.jsp"></jsp:include>
	</div>
<!-- 	<div> -->
<%-- 		<jsp:include page="/Front_end/index_Seller_Buttongroup.jsp"></jsp:include> --%>
<!-- 	</div> -->
	<!-- header----------->
  <!-- ---------計算評價數量 start------------- -->
 
<%-- 		<c:forEach var="viewsellerVO"  items="${list}"> --%>
<%-- 		     <% ViewsellerVO viewsellerVO =(ViewsellerVO)pageContext.getAttribute("viewsellerVO");%> --%>
<%-- 			<c:choose > --%>
<%-- 				<c:when test="<%=viewsellerVO.getV_gb().equals(\"good\")%>"> --%>
<%-- 				  <%  pageContext.setAttribute("goodNum",++goodNum); --%>
<%-- 				  %>	 --%>
<%-- 				</c:when> --%>
<%-- 		          	<c:otherwise > --%>
<%-- 					  <% pageContext.setAttribute("badNum",++badNum); --%>
					 
<%-- 					  %> --%>
<%-- 		            </c:otherwise> --%>
<%-- 			</c:choose> --%>
<%-- 		</c:forEach>	   --%>
  <!-- ---------計算評價數量 end----------------- -->
  <% sum=badNum+goodNum;
 pageContext.setAttribute("sum",sum);%>
 <div class="showAll">
 
 <div><h2>賣場評價 &nbsp;&nbsp;
  ${goodNum}<i class="far fa-thumbs-up" style="font-size: 2ex;position: relative;left: 10px; top: 1px;padding: 2px"></i>
 &nbsp; &nbsp;${badNum}<i class="far fa-thumbs-down" style="font-size: 2ex;position: relative;left: 10px; top: 1px;padding: 2px"></i>
</h2></div> 
<!-- ------------------------- -->
<div class="progress">
  <div class="progress-bar bg-success" role="progressbar" style="width:${goodNum/sum*100}%" aria-valuenow="30" aria-valuemin="0" aria-valuemax="100"></div>
 <div class="progress-bar bg-danger" role="progressbar" style="width:${badNum/sum*100}%" aria-valuenow="15" aria-valuemin="0" aria-valuemax="100"></div>
</div>
	<!-- --------------------------------------------------------------------------- -->
   
   <br>
    <div><h2>查詢評價 &nbsp;&nbsp;<br>
   <br>
 
    <div class="date">
<!--         <tr> -->
<!-- 				<li><td>起始日期：</td> -->
<!-- 				<td><input type="text" name="co_start" id="start_date" -->
<!-- 					size="20" placeholder="請選擇日期" /></td></li> -->
<!-- 			</tr> -->
<!-- <br> -->
<!-- 			<tr> -->
<!-- 				<li><td>結束日期：</td> -->
<!-- 				<td><input type="text" name="co_expire" id="end_date" size="20" -->
<!-- 					placeholder="請選擇日期" /></td></li> -->
<!-- 			</tr> -->

    </div>
  </div> 

	<table>
	<tr>

	</tr>
	<tr>
	<td>
	<div class="showAll">
	
<!--Nav bar區域 start-->
<!-- 	<nav> -->
<!-- 	  <div class="nav nav-tabs" id="nav-tab" role="tablist" > -->
<!-- 	    <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab" aria-controls="nav-home" aria-selected="true">全部</a> -->
<!-- 	    <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" role="tab" aria-controls="nav-profile" aria-selected="false">好評</a> -->
<!-- 	    <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" href="#nav-contact" role="tab" aria-controls="nav-contact" aria-selected="false">負評</a> -->
<!-- 	  </div> -->
<!-- 	</nav> -->
<!--Nav bar區域 end-->
	<!-- 全部start -->
	<div class="tab-content" id="nav-tabContent">
	  <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
 <%@ include file="page1.file" %> 
	  	<table id="tablecontent"  class="table table-hover">
	  	<tr>
		  <td><h4>訂單</h4></td>
		  <td><h4>買家</h4></td>
		  <td><h4>評價</h4></td>
		  <td><h4>評論內容</h4></td>
		  <td><h4>時間</h4></td>
		 </tr>
		<c:forEach varStatus="status" var="viewsellerVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">
		  <% ViewsellerVO viewsellerVO =(ViewsellerVO)pageContext.getAttribute("viewsellerVO");%>
		      <c:if test="${status.count%2==0}">
		   	    <tr bgcolor="white">
			  </c:if>
		      <c:if test="${status.count%2==1}">
		   		<tr bgcolor="#dee2e6">
			  </c:if>				  
					 <td><h4>${viewsellerVO.o_id} </h4></td>
		             <td><h4>${memSvc.findOneMem(viewsellerVO.m_buyid).m_name}</h4></td>
					 <td>
					 <c:choose >
				<c:when test="<%=viewsellerVO.getV_gb().equals(\"good\")%>">
				  	<i class="far fa-thumbs-up" style="font-size: 3ex;position: relative;left: 10px; top: 1px;padding: 2px"></i>
				</c:when>
		          	<c:otherwise >
					  <i class="far fa-thumbs-down" style="font-size: 3ex;position: relative;left: 10px; top: 1px;padding: 2px"></i>
		            </c:otherwise>
			</c:choose>
					 </td>
					 <td><h4>${viewsellerVO.v_comment}</h4></td>
					 <td><h4>
					 <fmt:formatDate value="${viewsellerVO.v_date}" pattern="yyyy-MM-dd HH:mm:ss"/>
					 </h4></td>
		        </tr>
		 </c:forEach>
		</table>  
	<%@ include file="page2.file" %> 
	</div>
	<!-- 全部  end-->

	
   
		</div>
		
		</div></td>
	</tr>
	</table>

	</div>
	<div class="footer">
		<jsp:include page="/Front_end/footer.jsp"></jsp:include>
	</div>
	<!-- ----footer---- -->
	
	<!-- =====Date time picker設定======== -->
<%-- <% --%>
// 	java.sql.Timestamp co_start = null;
// 	java.sql.Timestamp co_expire = null;

// 	try {
// 		co_start = couponVO.getCo_start();
// 	} catch (Exception e) {
// 		co_start = new java.sql.Timestamp(System.currentTimeMillis());
// 	}
// 	try {
// 		co_expire = couponVO.getCo_expire();
// 	} catch (Exception e) {
// 		co_expire = new java.sql.Timestamp(System.currentTimeMillis());
// 	}
<%-- %> --%>

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>
<script>
	$.datetimepicker.setLocale('zh'); // kr ko ja en
	$(function() {
		$('#start_date').datetimepicker({
			theme : '', //theme: 'dark',
			timepicker : true, //timepicker: false,
			step : 1, //step: 60 (這是timepicker的預設間隔60分鐘)
			format : 'Y-m-d H:i:s',
			value : new Date(),
			//disabledDates:    ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
			//startDate:	        '2017/07/10',  // 起始日
			minDate : '-1970-01-01', // 去除今日(不含)之前
		//maxDate:           '+1970-01-01'  // 去除今日(不含)之後
		});

		$('#end_date').datetimepicker({
			theme : '', //theme: 'dark',
			timepicker : true, //timepicker: false,
			step : 1, //step: 60 (這是timepicker的預設間隔60分鐘)
			format : 'Y-m-d H:i:s',
			value : new Date(),
			//disabledDates:    ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
			//startDate:	        '2017/07/10',  // 起始日
			minDate : '+1970-01-02', // 去除今日(不含)之前
		//maxDate:           '+1970-01-01'  // 去除今日(不含)之後
		});
	});
</script>
</body>
</html>