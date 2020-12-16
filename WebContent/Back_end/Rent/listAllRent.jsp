<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.rent.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    RentService rSvc = new RentService();
    List<RentVO> list = rSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<%
	java.sql.Timestamp r_revisedate = null;
	try {
		r_revisedate = new java.sql.Timestamp(System.currentTimeMillis());
	} catch (Exception e) {
		r_revisedate = new java.sql.Timestamp(System.currentTimeMillis());
	}
%>

<html>
<head>
<title>所有員工資料 - xxx.jsp</title>

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
<body bgcolor='white'>

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有出租品資料 - listAllRentEmp.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/Back_end/Rent/index.jsp"><img src="<%=request.getContextPath() %>/images/back1.png" width="40" height="40" border="0">回首頁</a></h4>
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
		<th>出租品編號</th>
		<th>出租品種類</th>
		<th>出租品名稱</th>
		<th>類別編號</th>
		<th>商品描述</th>
		<th>貨況</th>
		<th>貨物狀態</th>
		<th>出租品價格</th>
		<th>新增日期</th>
		<th>上次修改</th>
		<th>新增者</th>
		<th>修改者</th>
		<th>所在門市</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="RentVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"> 
														
		<tr>
			<td>${RentVO.r_id}</td>
			<td>${RentVO.r_type}</td>
			<td>${RentVO.r_name}</td>
			<td>${RentVO.pt_id}</td>
			<td>${RentVO.r_describe}</td>
			<td>${RentVO.r_situation}</td> 
			<td>${RentVO.r_status}</td>
			<td>${RentVO.r_price}</td>
			<td>${RentVO.r_adddate}</td>
			<td>${RentVO.r_revisedate}</td>
			<td>${RentVO.e_addid}</td>
			<td>${RentVO.e_editorid}</td>
			<td>${RentVO.st_id}</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/RentServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="r_id"  value="${RentVO.r_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post"  ACTION="<%=request.getContextPath() %>/RentServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="r_id"  value="${RentVO.r_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>