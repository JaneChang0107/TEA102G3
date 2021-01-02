<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>order home</title>
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
<body>
<table id="table-1">
   <tr><td><h3>order</h3><h4>home</h4></td></tr>
   
</table>

 <%-- ���~��C --%>
 <c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='<%= request.getContextPath() %>/Back_end/orderlist/listAllOrderlist.jsp'>List</a> all orderlist.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%= request.getContextPath() %>/orderlist" >
        <b>��J�q��N��:</b>
        <input type="text" name="o_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <!-- �d�|�� -->
    <li>
    <FORM METHOD="post" ACTION="<%= request.getContextPath() %>/orderlist" >
        <b>��J�|��id:</b>
        <input type="text" name="m_id">
        <input type="hidden" name="action" value="getMember_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  

  <jsp:useBean id="orderlistSvc" scope="page" class="com.orderlist.model.OrderlistService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%= request.getContextPath() %>/orderlist" >
       <b>��ܽs��:</b>
       <select size="1" name="o_id">
         <c:forEach var="orderlistVO" items="${orderlistSvc.all}" > 
          <option value="${orderlistVO.o_id}">${orderlistVO.o_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%= request.getContextPath() %>/orderlist" >
       <b>���:</b>
       <select size="1" name="o_id">
         <c:forEach var="orderlistVO" items="${orderlistSvc.all}" > 
          <option value="${orderlistVO.o_id}">${orderlistVO.o_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
</ul>

<h3>�޲z</h3>

<ul>
  <li><a href='<%= request.getContextPath() %>/Back_end/orderlist/addorderlist.jsp'>Add</a> a new orderlist.</li>
</ul>

</body>
</html>