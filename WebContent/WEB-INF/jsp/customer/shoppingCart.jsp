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
	<h3>Shopping Cart</h3>
	
	<c:url var="checkOut" value="/customer/checkOut" />
	<springform:form action="${checkOut}">
	
	<table width="400" style="text-align:center">
		<tr>
			<th>Product Name</th>
			<th>Price</th>
			<th>Quantity</th>
			<th>Total</th>
			<th>Action</th>
		</tr>
		<c:forEach var="orderDetail" items="${orderDetails}">
			<tr>
				<td>
					<c:out value="${orderDetail.product.name}" />
				</td>
				<td>
					<c:out value="${orderDetail.product.unitPrice}" />
				</td>

				<td>
					<c:out value="${orderDetail.quantity}" />
				</td>
				
				<td>
					<c:out value="${orderDetail.unitPrice*orderDetail.quantity}" />
				</td>
				
				<td>
					<c:url var="removeItem" value="/customer/remove">
						<c:param name="orderDetail.product.name" value="${orderDetail.product.name}" />
						<c:param name="orderDetail.quantity" value="${orderDetail.quantity}" />
					</c:url>
					<a href="${removeItem}">Remove Item</a>
				</td>
				
			</tr>
		 </c:forEach>
		 	
		 <tr>
		 		<td>
		 		Total Price
				</td>
				<td></td>
				<td></td>
				
				<td>
					<c:out value="${totalPrice}" />
				</td>
				<td></td>
				
		</tr>
		
	</table>
	
	<br /><input type = "submit" value = "CHECKOUT" />
	
	</springform:form>
</body>
</html>