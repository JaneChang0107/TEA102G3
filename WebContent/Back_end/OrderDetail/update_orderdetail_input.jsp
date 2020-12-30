<%@page import="com.orderdetail.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
  OrderdetailVO orderdetailVO = (OrderdetailVO) request.getAttribute("orderdetailVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<!DOCTYPE html>
<html>
<head>
<meta  http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>update orderlist input</title>
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
		 <h3>資料修改 - update_orderlist_input.jsp</h3>
		 <h4><a href="<%= request.getContextPath() %>/back_end/OrderDetail/select_page.jsp"><img src="<%= request.getContextPath() %>/back_end/images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

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
		<td>流水編號:<font color=red><b>*</b></font></td>
		<td><%=orderdetailVO.getOd_id()%></td>
	</tr>
	<tr>
		<td>訂單編號:</td>
		<td><input type="TEXT" name="o_id" size="45"	value="<%=orderdetailVO.getO_id()%>" /></td>
	</tr>
	<tr>
		<td>商品編號:</td>
		<td><input type="TEXT" name="p_id" size="45"	value="<%=orderdetailVO.getP_id()%>" /></td>
	</tr>
	<tr>
		<td>商品數量:</td>
		<td><input type="TEXT" name="od_count" size="45"	value="<%=orderdetailVO.getOd_count()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="od_id" value="<%=orderdetailVO.getOd_id()%>">
<input type="submit" value="送出修改"></FORM>




</body>
</html>