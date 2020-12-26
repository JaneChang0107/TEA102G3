<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>租賃門市</title>

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
   <tr><td><h4>租賃門市</h4></td></tr>
</table>

<!-- <p>新增/查詢門市畫面</p>  -->

<h3>門市查詢:</h3>
	
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
  <li><a href='<%=request.getContextPath()%>/store/listAllStore.jsp'>List</a> 門市列表  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/StoreServlet" >
        <b>輸入門市編號 (如ST00001):</b>
        <input type="text" name="st_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="storeSvc" scope="page" class="com.store.model.StoreService" />
    
     <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/StoreServlet" >
       <b>選擇門市名稱:</b>
       <select size="1" name="st_id">
         <c:forEach var="storeVO" items="${storeSvc.all}" > 
          <option value="${storeVO.st_id}">${storeVO.st_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/StoreServlet" >
       <b>選擇門市編號:</b>
       <select size="1" name="st_id">
         <c:forEach var="storeVO" items="${storeSvc.all}" > 
          <option value="${storeVO.st_id}">${storeVO.st_id}
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


<h3>新增租賃門市</h3>

<ul>
  <li><a href='addStore.jsp'>新增</a>租賃門市</li>
</ul>

</body>
</html>