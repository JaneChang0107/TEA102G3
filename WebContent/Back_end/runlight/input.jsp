<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="com.runlight.model.*"%>
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
<input type="submit" value="送出新增"></FORM>




</body>
</html>


