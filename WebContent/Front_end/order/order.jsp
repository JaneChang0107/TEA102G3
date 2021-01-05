<%@page import="java.util.List"%>
<%@page import="com.orderlist.model.OrderlistVO"%>
<%@page import="com.orderlist.model.OrderlistService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String m_id = session.getAttribute("loginId").toString();
// System.out.println(m_id);
OrderlistService orderlistSvc =new OrderlistService();
List<OrderlistVO> list = orderlistSvc.findByMember(m_id);
pageContext.setAttribute("list", list);

%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>order</title>

<jsp:include page="../header.jsp"></jsp:include>
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

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>�q��s��</th>
		<th>�q����</th>
		<th>�q�檬�A</th>
		<th>�X�f���</th>
		<th>���f���</th>
		<th>�������</th>
		<th>�B�e�覡</th>
		<th>�H�e�a�}</th>
		<th>�`���B</th>
		<th>��</th>
		<th>�R�a</th>
		
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
<!-- 			<td> -->
<%-- 				<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/orderlist" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="�ק�"> -->
<%-- 			     <input type="hidden" name="o_id"  value="${orderlistVO.o_id}"> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 			</td> -->
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%= request.getContextPath() %>/orderlist" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="�R��"> -->
<%-- 			     <input type="hidden" name="o_id"  value="${orderlistVO.o_id}"> --%>
<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
<!-- 			</td> -->
		</tr>	
	</c:forEach>
</table>
<%@ include file="page2.file" %>


</body>
	<div class="footer">
		<jsp:include page="../footer.jsp"></jsp:include>
	</div>
</html>