<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="com.runlight.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
RunlightVO runlightVO = new RunlightVO();
String value=runlightVO.getValue();
pageContext.setAttribute("value",value);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>輸入公告</title>
</head>


<body>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


   
<div id="div_input">
<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/light.do" name="form1">
<table>

	<tr>
		<td>輸入:</td>
		<td><input name="key" id="key" type="text" value="all">(all為系統公告或是輸入會員編號)</td>
	</tr>
	
	<tr>
		<td>內容:</td>
		<td><input name="value" id="value" type="text"></td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="show">
<!-- <input type="submit" value="送出新增"> -->
<button type="button" id="btn_send">送出新增</button>
</FORM>
</div>



</body>
</html>


