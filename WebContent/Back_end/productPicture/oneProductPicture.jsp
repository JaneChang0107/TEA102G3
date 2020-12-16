<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
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
<jsp:useBean id="pService" scope="page" class="com.product.model.ProductService" />
<jsp:useBean id="ptService" scope="page" class="com.productType.model.ProductTypeService" />
	<form action="<%= request.getContextPath() %>/ProductPictureServlet" method="post">
	<h1>單一搜尋</h1>
	<select name="pid">
		<option>請選擇</option>
		<c:forEach var="pVO" items="${pService.all}">
			<option value="${pVO.p_id}">${pVO.p_name} ${ptService.getOneProductType(pVO.pt_id).getPt_platform()}</option>
		</c:forEach>
	</select>
	<input type="hidden" name="action" value="getOne">
	<input type="submit" value="搜尋">
	</form>
	
		<a href="<%= request.getContextPath() %>/Back_end/productPicture/allProductPicture.jsp">全部清單</a>
		<a href="<%= request.getContextPath() %>/Back_end/productPicture/addProductPicture.jsp">新增</a>
	<c:if test="${ppVOs != null}">
		<table>	
		<c:forEach var="ppVO" items="${ppVOs}">
			<tr>
				<td>${ppVO.pp_id}</td>
				<td>${ppVO.p_id} ${pService.oneProduct(ppVO.p_id).getP_name()}</td>
				<td><img src="data:image/png;base64,${ppVO.pp_picture64}" class="showimg"></td>
				<td>
					<form action="<%= request.getContextPath() %>/ProductPictureServlet" method="post">
						<input type="hidden" name="action" value="updateOne">
						<input type="hidden" name="ppid" value="${ppVO.pp_id}">
						<input type="hidden" name="pid" value="${ppVO.p_id}">
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
	</c:if>
</body>
</html>