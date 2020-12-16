<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>會員管理畫面</title>

<style>
table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
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

</head>
<body bgcolor='#E3F8F6'>

	<table id="table-1">
		<tr>
			<td><h4>會員查詢中心</h4></td>
		</tr>
	</table>


	<h3>資料查詢:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
		
<ul>
	   <li><a href='listAllMem.jsp'>查詢全部會員</a><br><br></li>

       <li>
	    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/controller/MemberServlet" >
	        <b>輸入會員ID(如M00001):</b>
	        <input type="text" name="m_id">
	        <input type="hidden" name="action" value="getOne_For_Display">
	        <input type="submit" value="送出查詢">
	    </FORM>
       </li>
   
<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />   

   <li>
       <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/controller/MemberServlet" >
            <b>選擇會員ID:</b>       
              <select size="1" name="m_id">
               <c:forEach var="memberVO" items="${memSvc.all}">
                 <option value="${memberVO.m_id}">${memberVO.m_id}
               </c:forEach>
              </select> 
            <input type="hidden" name="action" value="getOne_For_Display">
            <input type="submit" value="送出查詢">      
       </FORM>
    </li>     
   </ul>
   

	<h3>新增會員</h3>

	<ul>
		<li><a href='addMem.jsp'>新增一般會員</a></li>
	</ul>

	<ul>
		<li><a href='addMemSeller.jsp'>新增賣家會員</a></li>
	</ul>

</body>
</html>