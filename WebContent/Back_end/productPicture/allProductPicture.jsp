<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>allProductPicture</title>
<style>
	table, tr, th, td{
		border: 1px solid black;
	}
	img.showimg{
		width: 200px;
	}
</style>
</head>
<body>
	<h1>�����Ӥ�</h1>
<jsp:useBean id="ppService" scope="page" class="com.productPicture.model.ProductPictureService"></jsp:useBean>
<jsp:useBean id="pService" scope="page" class="com.product.model.ProductService"></jsp:useBean>
<table>
		<tr>
			<th>�s��</th>
			<th>�ӫ~</th>
			<th>�Ӥ�</th>
		</tr>
<c:forEach var="ppVO" items="${ppService.all}">
		<tr>
			<td>${ppVO.pp_id}</td>
			<td>${ppVO.p_id} ${pService.oneProduct(ppVO.p_id).getP_name()}</td>
			
			<td><img src=data:image/png;base64,${ppVO.pp_picture64} class="showimg"></td>
		</tr>
</c:forEach>
</table>

<a href="<%= request.getContextPath() %>/Back_end/productPicture/addProductPicture.jsp">�^�a</a>


</body>
</html>