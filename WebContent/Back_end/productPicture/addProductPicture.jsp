<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>
<c:if test="${!error.isEnpty}">
	<c:forEach var="error" items="${errors}">
		<p style="color:red">${error}</p>
	</c:forEach>
</c:if>

	<h1>�s�W�Ϥ�</h1>
	<form action="<%= request.getContextPath() %>/ProductPictureServlet" method="post" enctype="multipart/form-data">
		<div>
			<label>��a</label>
			<input type="text" name="pid" value="P00001">
		</div>
		<div>
			<label>�Ϥ�</label>
			<input type="file" name="picture" accept="image/*">
		</div>
		<div>
			<input type="hidden" name="action" value="insert">
			<input type="submit" value="�T�w">
		</div>
	</form>
	
	<a href="<%= request.getContextPath() %>/back_end/productPicture/allProductPicture.jsp">�����M��</a>
</body>
</html>