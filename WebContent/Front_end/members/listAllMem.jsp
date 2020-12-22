<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>

<%
   MemberService memSvc =new MemberService();
   List<MemberVO> list =memSvc.getAll();
   pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>會員列表</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
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

<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>

<body bgcolor='E3F8F6'>
<table id="table-1">
	<tr><td>
		 <h3>所有會員資料</h3>
		 <h4><a href="<%=request.getContextPath()%>/Front_end/members/select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
<tr>
   <th>會員id</th>
   <th>電子郵件</th>
   <th>密碼</th>
   <th>姓名</th>
   <th>性別</th>
   <th>電話</th>
   <th>地址</th>
   <th>生日</th>
   <th>大頭照</th>
   <th>會員狀態</th>
   <th>身分證字號</th>
   <th>身分證照片</th>
   <th>銀行帳戶</th>
   <th>銀行戶名</th>
   <th>銀行代號</th>
   <th>存摺照片</th>
   <th>匯款最後日期</th>
   <th>賣場名稱</th>
   <th>賣場簡介</th>
   <th>賣場封面</th>
   <th>聊天問候語</th>
   <th>離線問候語</th>
   <th>堃幣</th>

</tr>
<%@ include file="page1.file" %>   <!-- 要做的分頁重點 -->
<c:forEach var="memberVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
   <tr>
     <td>${memberVO.m_id}</td>
     <td>${memberVO.m_email}</td>
     <td>${memberVO.m_password}</td>
     <td>${memberVO.m_name}</td>
     <td>${memberVO.m_gender}</td>
     <td>${memberVO.m_phone}</td>
     <td>${memberVO.m_address}</td>
     <td>${memberVO.m_birth}</td>
     <td><img src="${memberVO.m_headpic2}" width="100" height="100"></td>
     <td>${memberVO.m_status}</td>
     <td>${memberVO.m_identity}</td>
     <td><img src="${memberVO.m_id_pic2}"  width="100" height="100"></td>
     <td>${memberVO.m_account}</td>
     <td>${memberVO.m_accountName}</td>
     <td>${memberVO.b_code}</td>
     <td><img src="${memberVO.m_bank_pic2}" width="100" height="100"></td>
     <td>${memberVO.m_moneyTranDate}</td>
     <td>${memberVO.m_storename}</td>
     <td>${memberVO.m_info}</td>
     <td><img src="${memberVO.m_cover2}" width="100" height="100"></td>
     <td>${memberVO.m_hi}</td>
     <td>${memberVO.m_offlineHi}</td>
     <td>${memberVO.m_coin}</td>
       <td>
	     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/controller/MemberServlet" style="margin-bottom: 0px;">
		     <input type="submit" value="修改">
		     <input type="hidden" name="m_id"  value="${memberVO.m_id}">
		     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
       </td>
	   
	    <td>
		  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/controller/MemberServlet" style="margin-bottom: 0px;">
			  <input type="submit" value="刪除">
	          <input type="hidden" name="m_id"  value="${memberVO.m_id}">
		      <input type="hidden" name="action" value="delete"></FORM>
	    </td>
   
   </tr>
     
</c:forEach>
</table>
<%@ include file="page2.file" %>



</body>
</html>