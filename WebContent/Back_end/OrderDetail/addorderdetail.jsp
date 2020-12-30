<%@page import="com.orderdetail.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
  OrderdetailVO orderdetailVO = (OrderdetailVO) request.getAttribute("orderdetailVO");
%>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>add</title>

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
		 <h4><a href="<%= request.getContextPath() %>/Back_end/OrderDetail/select_page.jsp"><img src="<%= request.getContextPath() %>/Back_end/images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
	
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






<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/OrderdetailServlet" name="form1">
<table>
	<tr>
		<td>流水號:</td>
		<td><input type="hidden" name="od_id" size="45" value="">

			 <p>會自動生成流水號</p>
	</tr>
	
	<tr>
		<td>訂單編號:</td>
		<td><input type="TEXT" name="o_id" size="45"
			 value="<%= (orderdetailVO==null)? "O00001" : orderdetailVO.getO_id()%>" /></td>
	</tr>
	<tr>
		<td>商品編號:</td>
		<td><input type="TEXT" name="p_id" size="45"
			 value="<%= (orderdetailVO==null)? "P00001" : orderdetailVO.getP_id()%>" /></td>
	</tr>
	<tr>
		<td>商品數量:</td>
		<td><input type="TEXT" name="od_count" size="45"
			 value="<%= (orderdetailVO==null)? "1" : orderdetailVO.getOd_count()%>" /></td>
	</tr>

	
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>


</html>