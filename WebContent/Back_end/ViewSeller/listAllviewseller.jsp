<%@page import="java.util.*"%>
<%@page import="com.viewseller.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	ViewsellerService viewsellerlSvc =new ViewsellerService();
	List<ViewsellerVO> list = viewsellerlSvc.getAll();
	pageContext.setAttribute("list", list);

%>


<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
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
		 <h3>所有訂單資料 - listAllViewSeller.jsp</h3>
		 <h4><a href="<%= request.getContextPath() %>/back_end/ViewSeller/select_page.jsp"><img src="<%= request.getContextPath() %>/back_end/images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
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
		<th>評價編號</th>
		<th>訂單編號</th>
		<th>評價的人</th>
		<th>會員</th>
		<th>評價好壞</th>
		<th>評論</th>
		<th>時間</th>
		<th>修改</th>
		<th>刪除</th>
		
	</tr>
	
	<%@ include file="page1.file" %> 
	<c:forEach var="viewsellerVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>
			<td>${viewsellerVO.v_id}</td>
			<td>${viewsellerVO.o_id}</td>
			<td>${viewsellerVO.m_buyid}</td>
			<td>${viewsellerVO.m_sellid}</td>
			<td>${viewsellerVO.v_gb}</td>
			<td>${viewsellerVO.v_comment}</td>
<%-- 			<td>${viewsellerVO.v_date}</td> --%>
			<td><fmt:formatDate value="${viewsellerVO.v_date}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			<td>
				<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/ViewSellerServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="v_id"  value="${viewsellerVO.v_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%= request.getContextPath() %>/ViewSellerServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="v_id"  value="${viewsellerVO.v_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>	
	</c:forEach>
</table>
<%@ include file="page2.file" %>





</body>
</html>