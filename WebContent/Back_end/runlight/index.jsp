<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>
	
<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/light.do" name="form1">
<table>

	<tr>
		<td>key:</td>
		<td><input name="key" id="key" type="text" value="1"></td>
	</tr>
	<tr>
		<td>value:</td>
		<td><input name="value" id="value" type="text" ></td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>


</body>
</html>


