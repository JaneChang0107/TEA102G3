<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>黑名單</title>

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
   <tr><td><h4>黑名單</h4></td></tr>
</table>

<!-- <p>新增/查詢黑名單畫面</p>  -->

<h3>黑名單查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='<%=request.getContextPath()%>/blacklist/listAllBlacklist.jsp'>List</a> 黑名單列表  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/blacklist/BlacklistServlet" >
        <b>輸入黑名單編號 (如BL00001):</b>
        <input type="text" name="bl_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>
<!--  <li> -->
<%--     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/blacklist/BlacklistServlet" > --%>
<!--         <b>輸入封鎖方編號 (如M00001):</b> -->
<!--         <input type="text" name="bl_id"> -->
<!--         <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--         <input type="submit" value="送出"> -->
<!--     </FORM> -->
<!--   </li> -->
<!--     <li> -->
<%--     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/blacklist/BlacklistServlet" > --%>
<!--         <b>輸入被封鎖方編號 (如M00002):</b> -->
<!--         <input type="text" name="m_blackId"> -->
<!--         <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--         <input type="submit" value="送出"> -->
<!--     </FORM> -->
<!--   </li> -->
  <jsp:useBean id="blacklistSvc" scope="page" class="com.blacklist.model.BlacklistService" />
<!--      <li> -->
<%--      <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/blacklist/BlacklistServlet" > --%>
<!--        <b>選擇封鎖方會員編號:</b> -->
<!--        <select size="1" name="bl_id"> -->
<%--          <c:forEach var="blacklistVO" items="${blacklistSvc.all}" >  --%>
<%--           <option value="${blacklistVO.bl_id}">${blacklistVO.m_id} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--        <input type="submit" value="送出"> -->
<!--     </FORM> -->
<!--   </li> -->
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/blacklist/BlacklistServlet" >
       <b>選擇黑名單編號:</b>
       <select size="1" name="bl_id">
         <c:forEach var="blacklistVO" items="${blacklistSvc.all}" > 
          <option value="${blacklistVO.bl_id}">${blacklistVO.bl_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
<!--   <li> -->
<!--      <FORM METHOD="post" ACTION="emp.do" > -->
<!--        <b>選擇員工姓名:</b> -->
<!--        <select size="1" name="empno"> -->
<%--          <c:forEach var="empVO" items="${empSvc.all}" >  --%>
<%--           <option value="${empVO.empno}">${empVO.ename} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--        <input type="submit" value="送出"> -->
<!--      </FORM> -->
<!--   </li> -->
</ul>


<h3>新增黑名單</h3>

<ul>
  <li><a href='addBlacklist.jsp'>新增</a>黑名單</li>
</ul>

</body>
</html>