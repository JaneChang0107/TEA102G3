<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.employee.model.*"%>

<%
  EmployeeVO employeeVO = (EmployeeVO) request.getAttribute("employeeVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="BIG5">
	<title>Insert title here</title>
<%-- 	<link rel="stylesheet" href="${pageContext.request.contextPath}/vendors/bootstrap/css/bootstrap.min.css"> --%>
<%--     <link rel="stylesheet type" href="${pageContext.request.contextPath}/Back_end/employee/css/index_backstage.css"> --%>
</head>
<body>
<FORM METHOD="post" ACTION="${pageContext.request.contextPath}/login" name="form1">
	 <div class="container update_without">
	 	<div class="row">
	 		<div class="col">
				<!--錯誤表列 -->
				<c:if test="${not empty errorMsgs_new}">
					<font style="color:red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs_new}">
							<li style="color:red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				<c:remove var="errorMsgs_new"/>
	 		</div>
	 	</div>
        <div class="row">
            <div class="col">
                <p>門市:</p>
            </div>
            <div class="col">
            	<jsp:useBean id="storeSvc" scope="page" class="com.store.model.StoreService" />
                <select size="1" name="st_id">
					<c:forEach var="storeVO" items="${storeSvc.all}">
					<option value="${storeVO.st_id}" ${(employeeVO.st_id==storeVO.st_id)? 'selected':'' }>${storeVO.st_id}
					</c:forEach>
				</select>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <p>職稱:</p>
            </div>
            <div class="col">											
                <input type="radio" name="e_title" size="45" value="EMPLOYEE" ${employeeVO.e_title eq 'EMPLOYEE' ? 'checked' : ''} >EMPLOYEE
				<input type="radio" name="e_title" size="45" value="BOSS" ${employeeVO.e_title eq 'BOSS' ? 'checked' : ''} >BOSS
            </div>
            <div class="col">
                <p>電話:</p>
            </div>
            <div class="col">
                <input type="TEXT" name="e_phone" value="<%=(employeeVO == null) ? "0987654321" : employeeVO.getE_phone()%>">
            </div>
        </div>
        <div class="row">
            <div class="col">
                <p>員工姓名:</p>
            </div>
            <div class="col">
                <input type="TEXT" name="e_name" value="<%=(employeeVO == null) ? "吳永志" : employeeVO.getE_name()%>">
            </div>
            <div class="col">
                <p>性別:</p>
            </div>
            <div class="col">
                <input type="radio" name="e_gender" value="MEN" ${employeeVO.e_gender eq 'MEN' ? 'checked' : '' }>MEN
				<input type="radio" name="e_gender" value="WOMEN" ${employeeVO.e_gender eq 'WOMEN' ? 'checked' : '' }>WOMEN
            </div>
        </div>
        <div class="row">
            <div class="col">
                <p>身分證字號:</p>
            </div>
            <div class="col">
                <input type="TEXT" name="e_identity" value="<%= (employeeVO == null) ? "A123456789" : employeeVO.getE_identity()%>">
            </div>
            <div class="col">
                <p>生日:</p>
            </div>
            <div class="col">
                <input name="e_birth" id="f_date1" type="text" placeholder="請選擇日期">
            </div>
        </div>
        <div class="row">
            <div class="col">
                <p>地址:</p>
            </div>
            <div class="col">
                <input type="TEXT" name="e_address" value="<%=(employeeVO == null) ? "新北市北投區大安路49號" : employeeVO.getE_address()%>">
            </div>
            <div class="col">
                <p>狀態:</p>
            </div>
            <div class="col">
                <input type="radio" name="e_status" value="0" ${employeeVO.e_status == 0 ? 'checked' : '' }>在職
				<input type="radio" name="e_status" value="1" ${employeeVO.e_status == 1 ? 'checked' : '' }>離職	
            </div>
        </div>
        <div class="row">
            <div class="col">
                <p>信箱:</p>
            </div>
            <div class="col">
                <input type="TEXT" name="e_email" value="${employeeVO.e_email}">
            </div>
            <div class="col">
                <p>密碼:</p>
            </div>
            <div class="col">
                <input type="TEXT" name="e_password" value="<%= (employeeVO == null) ? "" : employeeVO.getE_password()%>">
            </div>
        </div>
        <div class="row forget-row">
            <div class="col btn_col">
                <button type="button" id="btn_cancel" class="btn btn-primary forget-btn">取消</button>
                <button type="submit" class="btn btn-primary forget-btn">確認</button>
                <input type="hidden" name="action" value="insert">
            </div>
        </div>
    </div>
	</FORM>



	<script src="${pageContext.request.contextPath}/vendors/jquery/jquery-3.5.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/vendors/popper/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/vendors/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/Back_end/employee/js/index_backstage.js"></script>
    <script>
	    var btn_cancel = document.getElementById("btn_cancel");
	    btn_cancel.addEventListener('click',function(){
	    	location.href='<%=request.getContextPath()%>/Back_end/employee/index_backstage.jsp'
	    });
// 	    if(${errorMsgs_without != null}){
// 			alert("${errorMsgs_without}");			
// 		}
    </script>
    



</body>
</html>