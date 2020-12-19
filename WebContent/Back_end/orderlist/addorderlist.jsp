<%@page import="java.sql.Timestamp"%>
<%@page import="com.orderlist.model.OrderlistVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
  OrderlistVO orderlistVO = (OrderlistVO) request.getAttribute("orderlistVO");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>新增</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body>

<table id="table-1">
	<tr><td>
		 <h3>新增 - add.jsp</h3></td><td>
		 <h4><a href="<%= request.getContextPath() %>/back_end/orderlist/select_page.jsp"><img src="<%= request.getContextPath() %>/back_end/images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
	
	</td></tr>
</table>
<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/orderlist" name="form1">
<table>
	<tr>
		<td>訂單編號:</td>
		<td><input type="hidden" name="o_id" size="45" value="">
			 value="<%= (orderlistVO==null)? "會自動生成訂單編號" : orderlistVO.getO_id()%>" /></td>
			 <p>會自動生成訂單編號</p>
	</tr>
	<tr>
		<td>訂單日期:</td>
		<td><input name="o_date" id="o_date" type="text"></td>
	</tr>
	<tr>
		<td>訂單狀態:</td>
		<td><input type="TEXT" name="o_status" size="45"
			 value="<%= (orderlistVO==null)? "已送出" : orderlistVO.getO_status()%>" /></td>
	</tr>
	<tr>
		<td>出貨日期:</td>
		<td><input name="o_shipdate" id="o_shipdate" type="text"></td>
	</tr>
	<tr>
		<td>收貨日期:</td>
		<td><input name="o_deceiptdate" id="o_deceiptdate" type="text"></td>
	</tr>
	<tr>
		<td>完成日期:</td>
		<td><input name="o_finishdate" id="o_finishdate" type="text"></td>
		
	</tr>
	<tr>
		<td>運送方式:</td>
		<td><input type="TEXT" name="o_transport" size="45"
			 value="<%= (orderlistVO==null)? "7-11" : orderlistVO.getO_transport()%>" /></td>
	</tr>
	<tr>
		<td>寄送地址:</td>
		<td><input type="TEXT" name="o_address" size="45"
			 value="<%= (orderlistVO==null)? "基隆市" : orderlistVO.getO_address()%>" /></td>
	</tr>
	<tr>
		<td>總金額:</td>
		<td><input type="TEXT" name="o_total" size="45"
			 value="<%= (orderlistVO==null)? "1000" : orderlistVO.getO_total()%>" /></td>
	</tr>
	<tr>
		<td>堃幣:</td>
		<td><input type="TEXT" name="o_pm" size="45"
			 value="<%= (orderlistVO==null)? "1" : orderlistVO.getO_pm()%>" /></td>
	</tr>
	<tr>
		<td>買家:</td>
		<td><input type="TEXT" name="m_id" size="45"
			 value="<%= (orderlistVO==null)? "M00001" : orderlistVO.getM_id()%>" /></td>
	</tr>
	
	
	
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>




</html>