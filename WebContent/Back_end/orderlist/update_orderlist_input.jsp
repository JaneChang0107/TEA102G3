<%@page import="com.orderlist.model.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
  OrderlistVO orderlistVO = (OrderlistVO) request.getAttribute("orderlistVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
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
		 <h4><a href="<%= request.getContextPath() %>/Back_end/orderlist/select_page.jsp"><img src="<%= request.getContextPath() %>/Back_end/images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
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
<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/orderlist" name="form1" >
<table>
	<tr>
		<td>訂單編號:<font color=red><b>*</b></font></td>
		<td><%=orderlistVO.getO_id()%></td>
	</tr>
	<tr>
		<td>訂單日期:</td>
		<td><input name="o_date" id="o_date1" type="text" value="<%=orderlistVO.getO_date()%>"/></td>
	</tr>
	<tr>
		<td>訂單狀態:</td>
		<td><input type="TEXT" name="o_status" size="45"	value="<%=orderlistVO.getO_status()%>" /></td>
	</tr>
	<tr>
		<td>出貨日期:</td>
		<td><input name="o_shipdate" id="o_date2" type="text" value="<%=orderlistVO.getO_shipdate()%>"/></td>
	</tr>
	<tr>
		<td>收貨日期:</td>
		<td><input name="o_deceiptdate" id="o_date2" type="text"  value="<%=orderlistVO.getO_deceiptdate()%>"/ ></td>
	</tr>
	<tr>
		<td>完成日期:</td>
		<td><input name="o_finishdate" id="o_date3" type="text" value="<%=orderlistVO.getO_finishdate()%>"/ ></td>
	</tr>
	<tr>
		<td>運送方式:</td>
		<td><input type="TEXT" name="o_transport" size="45"	value="<%=orderlistVO.getO_transport()%>" /></td>
	</tr>
	<tr>
		<td>寄送地址:</td>
		<td><input type="TEXT" name="o_address" size="45"	value="<%=orderlistVO.getO_address()%>" /></td>
	</tr>
	<tr>
		<td>總金額:</td>
		<td><input type="TEXT" name="o_total" size="45"	value="<%=orderlistVO.getO_total()%>" /></td>
	</tr>
	<tr>
		<td>堃幣:</td>
		<td><input type="TEXT" name="o_pm" size="45" value="<%=orderlistVO.getO_pm()%>" /></td>
	</tr>
	<tr>
		<td>買家:</td>
		<td><input type="TEXT" name="m_id" size="45" value="<%=orderlistVO.getM_id()%>" /></td>
	</tr>


</table>
<br>

<input type="hidden" name="action2" value="update" >
<input type="hidden" name="o_id" value="<%=orderlistVO.getO_id()%>">
<input type="submit" value="送出修改" ></FORM>



</body>
</html>