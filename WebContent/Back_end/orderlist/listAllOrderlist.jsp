<%@page import="java.util.*"%>
<%@page import="com.orderlist.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<%
	OrderlistService orderlistSvc =new OrderlistService();
	List<OrderlistVO> list = orderlistSvc.getAll();
	pageContext.setAttribute("list", list);

%>
<html>
<head>
<meta charset="UTF-8">
<title> listallOrderlist.jsp</title>

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
<table id="table-1">
	<tr><td>
		 <h3>所有訂單資料 - listAllorderlist.jsp</h3>
		 <h4><a href="<%= request.getContextPath() %>/back_end/orderlist/select_page.jsp"><img src="<%= request.getContextPath() %>/back_end/images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>訂單編號</th>
		<th>訂單日期</th>
		<th>訂單狀態</th>
		<th>出貨日期</th>
		<th>收貨日期</th>
		<th>完成日期</th>
		<th>運送方式</th>
		<th>寄送地址</th>
		<th>總金額</th>
		<th>幣</th>
		<th>買家</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	
	<%@ include file="page1.file" %> 
	<c:forEach var="orderlistVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>
			<td>${orderlistVO.o_id}</td>
			<td><fmt:formatDate value="${orderlistVO.o_date}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td>${orderlistVO.o_status}</td>
			<td><fmt:formatDate value="${orderlistVO.o_shipdate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td><fmt:formatDate value="${orderlistVO.o_deceiptdate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td><fmt:formatDate value="${orderlistVO.o_finishdate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td>${orderlistVO.o_transport}</td>
			<td>${orderlistVO.o_address}</td> 
			<td>${orderlistVO.o_total}</td>
			<td>${orderlistVO.o_pm}</td>
			<td>${orderlistVO.m_id}</td>
			<td>
				<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/orderlist" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="o_id"  value="${orderlistVO.o_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%= request.getContextPath() %>/orderlist" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="o_id"  value="${orderlistVO.o_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>	
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>