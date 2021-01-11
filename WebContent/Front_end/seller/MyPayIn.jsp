<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.orderlist.model.*"%>	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
	<%
    OrderlistService OrderlistSvc = new OrderlistService();
    List<OrderlistVO> list = OrderlistSvc.getAll();
    pageContext.setAttribute("list",list);
    List<OrderlistVO> notFinishList = new ArrayList<OrderlistVO>();
    List<OrderlistVO> finishList = new ArrayList<OrderlistVO>();
    int sum=0;
	int paidNotYet=0;
	int paidAlready=0;
	
    for(int i=0;i<list.size();i++){
    	
    	sum+=list.get(i).getO_total();
    	
    	if(!(list.get(i).getO_status().equals("訂單完成"))){
    		
    		paidNotYet+=list.get(i).getO_total();
    	
    		notFinishList.add(list.get(i));
    		System.out.println(notFinishList);
    		
    		
    	}
    	if(list.get(i).getO_status().equals("訂單完成")){
    		
    		paidAlready+=list.get(i).getO_total();
    		
    		
    		finishList.add(list.get(i));
    		//System.out.println(finishList);
    	}
    
    	
    }
    pageContext.setAttribute("notFinishList",notFinishList);
    pageContext.setAttribute("finishList",finishList);
    
 %> 


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的進帳</title>
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

#showMoney{
	margin:20px auto; 
 	background:white;
 	width:1000px;
  	height:150px;
  	

}
#showMoneyContent{
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



</style>

</head>

<body style="background: #F5D2CD; height: 100%;">

<div class="header">
	
	<div class="header">
		<jsp:include page="/Front_end/header.jsp"></jsp:include>
	</div>
	<div>
		<jsp:include page="../index_Seller_Buttongroup.jsp"></jsp:include>
	</div>
	

	<table>
	<tr>
	<td><h4>我的進帳</h4></td>
	</tr>
	<tr>
	<td><div id="showMoney">
	<br>
	<div id="showMoneyContent">
	<table>
	<tr>
	<td colspan=2><h3 >即將撥款</h3></td>
	</tr>
	<tr>
  

	<td><h3>$</h3></td><td><h3 style="color:red"><%=paidNotYet%></h3></td>
	</tr>
	</table>
	</div>
	
	<div id="showMoneyContent">
	<table>
	<tr>
	<td colspan=2><h3>已撥款</h3></td>
	</tr>
	<tr>
	<td><h3>$</h3></td><td><h3 style="color:red"><%=paidAlready%></h3></td>
	</tr>
	</table>
	</div>
	
	<div id="showMoneyContent">
	<table>
	<tr>
	<td colspan=2><h3 >全部</h3></td>
	</tr>
	<tr>
	<td><h3>$</h3></td><td><h3 style="color:red"><%=sum%></h3></td>
	</tr>
	</table>
	</div>
	</div></td>
	</tr>
	</table>
	
	%>
	
	<table>
	<tr>
	<td><h4>入帳詳情</h4></td>
	</tr>
	<tr>
	<td>
	<div id="showAll">
	<nav>
	  <div class="nav nav-tabs" id="nav-tab" role="tablist">
	    <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab" aria-controls="nav-home" aria-selected="true">全部</a>
	    <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" role="tab" aria-controls="nav-profile" aria-selected="false">即將撥款</a>
	    <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" href="#nav-contact" role="tab" aria-controls="nav-contact" aria-selected="false">已撥款</a>
	  </div>
	</nav>
	<div class="tab-content" id="nav-tabContent">
	  <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
	  	<table id="tablecontent"  class="table table-hover">
	  	<tr>
		  <td>訂單</td>
		  <td>買家</td>
		  <td>日期</td>
		  <td>金額</td>
		 </tr>
		
	<c:forEach var="orderlistVO" items="${list}">
		 <tr>
		  <td>${orderlistVO.o_id}</td>
		  <td>${orderlistVO.m_id}</td>
		  <td>${orderlistVO.o_finishdate}</td>
		  <td>${orderlistVO.o_total}</td>
		 </tr>
		</c:forEach>
	</table>

  </div>
		  
	  <div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
	 	<table id="tablecontent"  class="table table-hover">
	 	<tr>
		  <td>訂單</td>
		  <td>買家</td>
		  <td>訂單完成日</td>
		  <td>金額</td>
		 </tr>
		 <c:forEach var="orderlistVO" items="${notFinishList}">
		 <tr>
		 <td>${orderlistVO.o_id}</td>
		  <td>${orderlistVO.m_id}</td>
		  <td>${orderlistVO.o_finishdate}</td>
		  <td>${orderlistVO.o_total}</td>
		 </tr>
		 </c:forEach>
		  </table>  </div>
		
	  
	  <div class="tab-pane fade" id="nav-contact" role="tabpanel" aria-labelledby="nav-contact-tab">
	  	<table id="tablecontent" class="table table-hover">
	  
	<c:forEach var="orderlistVO" items="${finishList}">
	  	<tr>
		  <td>訂單</td>
		  <td>買家</td>
		  <td>撥款日</td>
		  <td>金額</td>
		 <tr>
		 <tr>
		  <td>${orderlistVO.o_id}</td>
		  <td>${orderlistVO.m_id}</td>
		  <td>${orderlistVO.o_finishdate}</td>
		  <td>${orderlistVO.o_total}</td>
		 </tr>
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
</body>
</html>