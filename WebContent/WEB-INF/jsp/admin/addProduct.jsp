<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="springform" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>Role: Admin</h1>
	<c:url var="categoryURL" value="/admin/addCategory" />
	<a href="${categoryURL}">Add Category </a>
	|
	<c:url var="productURL" value="/admin/addProduct" />
	<a href="${productURL}">Add Product</a>
	|
	<c:url var="logoutURL" value="/j_spring_security_logout" />
	<a href="${logoutURL}">Logout</a>
	
	<br />
	
	<h1>All Products</h1>
	<table width="300" style="text-align:center">
		<tr>
			<th>Number</th>
			<th>Name</th>
			<th>Inventory Quantity</th>
			<th>Price</th>
		</tr>
		
		<c:forEach var="product" items="${products}">
			<tr>
				<td><c:out value="${product.id}" /></td>
				<td><c:out value="${product.name}" /></td>
				<td><c:out value="${product.inventoryQuantity}" /></td>
				<td><c:out value="${product.unitPrice}" /></td>
			</tr>
		</c:forEach>
	</table>
	

	<h1>Add Product</h1>
	
	<c:url var="submitURL" value="/admin/addProduct" />
	
	<table>
	
	
	<springform:form commandName="productModel" action="${submitURL}" method="POST">
		
		<tr>
		<td><springform:label path="category.id">Category Name</springform:label></td>
		<td>
		<springform:select path="category.id">
			<c:forEach var="category" items="${categories}">
				<springform:option value="${category.id}" ><c:out value="${category.name}" />
				</springform:option>
			</c:forEach>	
		</springform:select>
		</td>
		</tr>
		
		<br />
		
		<tr>
		<td><springform:label path="name">Product Name</springform:label></td>
		<td>
		<springform:input path="name"/>
		<springform:errors path="name" />
		</td>
		</tr>
		
		<br />

		<tr>
		<td><springform:label path="inventoryQuantity">Quantity</springform:label></td>
		<td><springform:input path="inventoryQuantity"/>
		<springform:errors path="inventoryQuantity" />		
		</td>
		</tr>
		
		<br />
		
		<tr>
		<td><springform:label path="unitPrice">Price</springform:label></td>
		<td><springform:input path="unitPrice"/>
		<springform:errors path="unitPrice" />
		</td>
		</tr>
		
		<br />
		<tr><td><input type="submit" value="Add Product"/></td></tr>
	</springform:form>

	</table>
</body>
</html>