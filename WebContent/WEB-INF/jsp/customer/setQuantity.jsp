<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="springform" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Set Quantity</title>
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
	<h3>Set Quantity</h3>

	<c:url var="submitURL" value="/customer/setQuantity" />

	<springform:form commandName="orderDetailModel" action="${submitURL}" method="POST">
		Product Name: 
		<springform:hidden path="product.name" value="${product.name}"/>
		<c:out value="${product.name}" />
	
		<br />
		Price: 
		<springform:hidden path="product.unitPrice" value="${product.unitPrice}"/>
		<c:out value="${product.unitPrice}" />
		<br />
	
		<springform:label path="quantity">Quantity</springform:label>
		<springform:input path="quantity" size="5"/>
		<springform:errors path="quantity" />
		
		<br / >
		<springform:hidden path="product.id" value="${product.id}"/>
		<springform:hidden path="product.inventoryQuantity" value="${product.inventoryQuantity}"/>
		<input type="submit" value="Add To Cart" />
	</springform:form>

</body>
</html>