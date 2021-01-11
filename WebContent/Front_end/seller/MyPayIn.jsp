<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	margin:80px auto; 
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


</style>

</head>
<body>
<body style="background: #F5D2CD; height: 100%;">

<div class="header">
	<jsp:include page="/Front_end/header.jsp"></jsp:include>
	
	<H4 style="margin: 40px">我的進帳</H4>
	<br>
</div>
	
	<div id="showMoney" border="2px black solid">
	<br>
	
	<div id="showMoneyContent">
	<table>
	<tr>
	<td colspan=2><h3 >即將撥款</h3></td>
	</tr>
	<tr>
	<td><h3>$</td><td><h3 style="color:red">XXXXX</h3></td>
	</tr>
	</table>
	</div>
	
	<div id="showMoneyContent">
	<table>
	<tr>
	<td colspan=2><h3>已撥款</h3></td>
	</tr>
	<tr>
	<td><h3>$</td><td><h3 style="color:red">XXXXX</h3></td>
	</tr>
	</table>
	</div>
	
	<div id="showMoneyContent">
	<table>
	<tr>
	<td colspan=2><h3 >全部</h3></td>
	</tr>
	<tr>
	<td><h3>$</td><td><h3 style="color:red">XXXXX</h3></td>
	</tr>
	</table>
	</div>
	</div>
	
	<H4 style="margin: 40px">入帳詳情</H4>
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
		  <td>撥款日</td>
		  <td>金額</td>
		 </tr>
		 <tr>
		  <td>O00001</td>
		  <td>M00001</td>
		  <td>2021/1/11</td>
		  <td>130</td>
		 </tr>
		  </table>  </div>
		  
	  <div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
	 	<table id="tablecontent"  class="table table-hover">
	 	<tr>
		  <td>訂單</td>
		  <td>買家</td>
		  <td>撥款日</td>
		  <td>金額</td>
		 </tr>
		 <tr>
		  <td>O00001</td>
		  <td>M00001</td>
		  <td>2021/1/11</td>
		  <td>130</td>
		 </tr>
		  </table>  </div>
		
	  
	  <div class="tab-pane fade" id="nav-contact" role="tabpanel" aria-labelledby="nav-contact-tab">
	  	<table id="tablecontent" class="table table-hover">
	  	<tr>
		  <td>訂單</td>
		  <td>買家</td>
		  <td>撥款日</td>
		  <td>金額</td>
		 <tr>
		 <tr>
		  <td>O00001</td>
		  <td>M00001</td>
		  <td>2021/1/11</td>
		  <td>130</td>
		 </tr>
		  </table></div>
		</div>
		</div>
		
		
		
	<div class="footer">
		<jsp:include page="/Front_end/footer.jsp"></jsp:include>
	</div>

</body>
</html>