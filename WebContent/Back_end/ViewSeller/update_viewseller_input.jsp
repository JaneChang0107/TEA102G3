<%@page import="com.viewseller.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
ViewsellerVO viewsellerVO = (ViewsellerVO) request.getAttribute("viewsellerVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
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
	
		 <h3>資料修改 - update_viewseller_input.jsp</h3>
		 <h4><a href="<%= request.getContextPath() %>/back_end/ViewSeller/select_page.jsp"><img src="<%= request.getContextPath() %>/back_end/images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
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
<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/ViewSellerServlet" name="form1">
<table>
	<tr>
		<td>評論編號:<font color=red><b>*</b></font></td>
		<td><%=viewsellerVO.getV_id()%></td>
	</tr>
	<tr>
		<td>訂單編號:</td>
		<td><input type="TEXT" name="o_id" size="45"
			 value="<%= (viewsellerVO==null)? "O00001" : viewsellerVO.getO_id()%>" /></td>
	</tr>
	<tr>
		<td>買家:</td>
		<td><input type="TEXT" name="m_buyid" size="45"
			 value="<%= (viewsellerVO==null)? "M00001" : viewsellerVO.getM_buyid()%>" /></td>
	</tr>
	<tr>
		<td>賣家:</td>
		<td><input type="TEXT" name="m_sellid" size="45"
			 value="<%= (viewsellerVO==null)? "M00002" : viewsellerVO.getM_sellid()%>" /></td>
	</tr>
	<tr>
		<td>好壞:</td>
		<td><input type="TEXT" name="v_gb" size="45"
			 value="<%= (viewsellerVO==null)? "好" : viewsellerVO.getV_gb()%>" /></td>
	</tr>
	
	<tr>
		<td>評論:</td>
		<td><input type="TEXT" name="v_comment" size="45"
		 value="<%= (viewsellerVO==null)? "出貨速度快" : viewsellerVO.getV_comment()%>" /></td>
	</tr>
	<tr>
		<td>評價日期:</td>
		<td><input name="v_date" id="v_date" type="text" ></td>
	</tr>
	

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="v_id" value="<%=viewsellerVO.getV_id()%>">
<input type="submit" value="送出修改"></FORM>


</body>
</html>