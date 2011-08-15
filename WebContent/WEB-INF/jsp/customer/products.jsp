<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Products</title>
</head>
<body>
	<h1>Role: Customer</h1>
	<c:url var="categoryURL" value="/customer/customer" />
	<a href="${categoryURL}">All Categories </a>
	|
	<c:url var="shopURL" value="/customer/shoppingCart" />
	<a href="${shopURL}">View Shopping Cart</a>
	|
	<c:url var="logoutURL" value="/j_spring_security_logout" />
	<a href="${logoutURL}">Logout</a>
	
	<br />
	<h3>Products</h3>
				
		<table width="400" style="text-align:center">
		<tr>
			<th>Product Name</th>
			<th>Price</th>
			<th>Action</th>
		</tr>
		<c:forEach var="product" items="${products}">
			<tr>
				<td>
					<c:out value="${product.name}" />
				</td>
				<td>
					<c:out value="${product.unitPrice}" />
				</td>

				<td>
					<c:url var="setQuantity" value="/customer/setQuantity">
						<c:param name="productNumber" value="${product.id}" />
					</c:url>
					<a href="${setQuantity}">Add To Cart</a>
				</td>
			</tr>
		 </c:forEach>
		 </table>
	

</body>
</html>