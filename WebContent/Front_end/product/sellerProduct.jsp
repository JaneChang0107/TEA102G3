<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% session.setAttribute("m_id", "M00001"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>sellerProduct</title>
</head>
<body>
<jsp:useBean id="ptService" scope="page" class="com.productType.model.ProductTypeService" />
<jsp:useBean id="ppService" scope="page" class="com.productPicture.model.ProductPictureService" />
	<table>
	  <tr>
	    <th>商品名稱</th>
	    <th>商品種類</th>
	    <th>商品數量</th>
	    <th>商品價格</th>
	    <th>商品新增日期</th>
	    <th>商品狀態</th>
	    <th>商品圖片</th>
	    <th>商品介紹</th>
	  </tr>
	  <tr>
	    <td>${pVO.p_name}</td>
	    <td>${pVO.p_name}</td>
	    <td>${pVO.p_name}</td>
	    <td>${pVO.p_name}</td>
	    <td>${pVO.p_name}</td>
	    <td>${pVO.p_name}</td>
	    <td>${pVO.p_name}</td>
	  </tr>
	</table>


</body>
</html>