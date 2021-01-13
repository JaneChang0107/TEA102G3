<%@page import="java.util.*"%>
<%@page import="com.orderdetail.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
    
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	OrderdetailService orderdetailSvc =new OrderdetailService();
	List<OrderdetailVO> list = orderdetailSvc.getAll();
	pageContext.setAttribute("list", list);

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>orderdetail</title>

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
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body>

	<tr><td>
		 <h3>�Ҧ��q���� </h3>
<%-- 		 <h4><a href="<%= request.getContextPath() %>/Back_end/OrderDetail/select_page.jsp"><img src="<%= request.getContextPath() %>/Back_end/images/back1.gif" width="100" height="32" border="0">�^����</a></h4> --%>
	</td></tr>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<div id="odlist">
<table>
	<tr>
		<th>�y����</th>
		<th>�q��s��</th>
		<th>�ӫ~�s��</th>
		<th>���~�ƶq</th>
		<th>�ק�</th>
		<th>�R��</th>
	</tr>
	<c:forEach var="orderdetailVO" items="${list}" >
		<tr>
			<td>${orderdetailVO.od_id}</td>
			<td>${orderdetailVO.o_id}</td>
			<td>${orderdetailVO.p_id}</td>
			<td>${orderdetailVO.od_count}</td>
			
			<td>
				<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/OrderdetailServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="od_id"  value="${orderdetailVO.od_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%= request.getContextPath() %>/OrderdetailServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="od_id"  value="${orderdetailVO.od_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>	
	</c:forEach>
</table>
</div>

</body>
</html>