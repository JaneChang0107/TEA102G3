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
	<h1>全部照片</h1>
<jsp:useBean id="ppService" scope="page" class="com.productPicture.model.ProductPictureService"></jsp:useBean>
<jsp:useBean id="pService" scope="page" class="com.product.model.ProductService"></jsp:useBean>
<table>
		<tr>
			<th>編號</th>
			<th>商品</th>
			<th>照片</th>
			<th>修改</th>
			<th>刪除</th>
		</tr>
<c:forEach var="ppVO" items="${ppService.all}">
		<tr>
			<td>${ppVO.pp_id}</td>
			<td>${ppVO.p_id} ${pService.oneProduct(ppVO.p_id).getP_name()}</td>
<%-- 			<td><img src="data:image/png;base64,${ppVO.pp_picture64}" class="showimg"></td> --%>
			<td><img src="<%= request.getContextPath() %>/ShowPicture?type=pp&id=${ppVO.pp_id}" class="showimg"></td>
			<td>
				<form action="<%= request.getContextPath() %>/ProductPictureServlet" method="post">
					<input type="hidden" name="action" value="updateOne">
					<input type="hidden" name="pid" value="${ppVO.p_id}">
					<input type="hidden" name="ppid" value="${ppVO.pp_id}">
					<input type="submit" value="修改">	
				</form>
			</td>
			<td>
				<form action="<%= request.getContextPath() %>/ProductPictureServlet" method="post">
					<input type="hidden" name="action" value="delete">
					<input type="hidden" name="ppid" value="${ppVO.pp_id}">
					<input type="submit" value="刪除">
				</form>
			</td>
		</tr>
</c:forEach>
</table>

<a href="<%= request.getContextPath() %>/Back_end/productPicture/oneProductPicture.jsp">回找單一</a>

</body>
</html>