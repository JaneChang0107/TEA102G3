<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Emp: Home</title>

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
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>IBM Emp: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Emp: Home</p>

<h3>��Ƭd��:</h3>
	
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
  <li><a href='listAllRent.jsp'>List</a> all Emps.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/RentServlet" >
        <b>��J�X���~�s�� (�pR00001):</b>
        <input type="text" name="r_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="rentSvc" scope="page" class="com.rent.model.RentService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/RentServlet" >
       <b>��ܥX���~�s��:</b>
       <select size="1" name="r_id">
         <c:forEach var="rentVO" items="${rentSvc.all}" > 
          <option value="${rentVO.r_id}">${rentVO.r_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
<!--   <li> -->
<%--      <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/RentServlet" > --%>
<!--        <b>��ܭ��u�m�W:</b> -->
<!--        <select size="1" name="r_name"> -->
<%--          <c:forEach var="rentVO" items="${rentSvc.all}" >  --%>
<%--           <option value="${rentVO.r_name}">${rentVO.r_name} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden"  name="action" value="getOne_For_Display"> -->
<!--        <input type="submit" value="�e�X"> -->
<!--      </FORM> -->
<!--   </li> -->
</ul>


<h3>���u�޲z</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/Back_end/Rent/addRent.jsp'>Add</a> a new Emp.</li>
</ul>

</body>
</html>