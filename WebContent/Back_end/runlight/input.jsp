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
<!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous"> -->
<style>

/* .input_body_class { */
/*   padding: 40px 100px; */
/* } */
.dic_css{
  width: 800px;
  margin: 40px auto;
  font-family: "trebuchet MS","Lucida sans","Arial";
  font-size: 14px;
  color: #444;
}
/*表格的默认设置*/
.bordered {
  *border-collapse: collapse;
  border-spacing: 0;
  width: 100%;
}
/*==========制作圆角表格===========*/
.bordered {
  border: solid #ccc 1px;
  border-radius: 6px;
  box-shadow: 0 1px 1px #ccc;
}
.borderd tr {
  -o-transition: all 0.1s ease-in-out;
  -webkit-transition: all 0.1s ease-in-out;
  -moz-transition: all 0.1s ease-in-out;
  -ms-transition: all 0.1s ease-in-out;
  transition: all 0.1s ease-in-out;
}
.bordered .highlight,
.bordered tr:hover {
  background: #fbf8e9;/*表格行的悬浮状态效果*/ 
}
.bordered td, 
.bordered th {
  border-left: 1px solid #ccc;
  border-top: 1px solid #ccc;
  padding: 10px;
  text-align: left;
}
.bordered th {
	/*表格表头添加渐变背景色*/
  background-color: #dce9f9;
  background-image: -webkit-gradient(linear, left top, left bottom, from(#ebf3fc), to(#dce9f9));
  background-image: -webkit-linear-gradient(top, #ebf3fc, #dce9f9);
  background-image: -moz-linear-gradient(top, #ebf3fc, #dce9f9);
  background-image: -ms-linear-gradient(top, #ebf3fc, #dce9f9);
  background-image: -o-linear-gradient(top, #ebf3fc, #dce9f9);
  background-image: linear-gradient(top, #ebf3fc, #dce9f9);
  filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0, startColorstr=#ebf3fc, endColorstr=#dce9f9);
  -ms-filter: "progid:DXImageTransform.Microsoft.gradient (GradientType=0, startColorstr=#ebf3fc, endColorstr=#dce9f9)";	
  box-shadow: 0 1px 0 rgba(255,255,255,.8) inset;/*表格表头设置内阴影*/
  border-top: none;	
  text-shadow: 0 1px 0 rgba(255,255,255,.5);/*表格表头设置文字阴影*/
}
/*使用:first-child去除表格每行的第一个单元格的左边框*/
.bordered td:first-child, 
.bordered th:first-child {
  border-left: none;
}



</style>

</head>


<body class="input_body_class">

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


   
<div id="div_input" class="dic_css">

<!-- zebra        table table-dark table-hover         bordered -->
<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/light.do" name="form1">
<table class="bordered"  >

	<tr>
		<td>輸入:</td>
		<td>
		<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />   
<select class="form-select" aria-label="Default select example">
  <option selected >請輸入系統公告或是會員編號</option>
  <option id="key" value="all">all系統公告</option>
  <c:forEach var="mVO" items="${memSvc.all}">
  <option id="key" value="${mVO.m_id}">${mVO.m_id},${mVO.m_name}</option>
  </c:forEach>
  
</select>
		</td>
	</tr>
	
	<tr>
		<td>內容:</td>
<!-- 		<td><input name="value" id="value" type="text"></td> -->
		<td><textarea id="value" name="value" class="form-control" rows="10"></textarea></td>
	</tr>
</table>
<input type="hidden" name="action" value="show">
<!-- <input type="submit" value="送出新增"> -->
<!-- <button type="button" >送出新增</button> -->
<button type="button" id="btn_send" class="btn btn-primary btn-sm">送出新增</button>
</FORM>
</div>
<!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script> -->
<!-- <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js" integrity="sha384-q2kxQ16AaE6UbzuKqyBE9/u/KzioAlnx2maXQHiDX9d4/zp8Ok3f+M7DPm+Ib6IU" crossorigin="anonymous"></script> -->
<!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-pQQkAEnwaBkjpqZ8RU1fF1AKtTcHJwFl3pblpTlHXybJjHpMYo79HY3hIi4NKxyj" crossorigin="anonymous"></script> -->

</body>
</html>


