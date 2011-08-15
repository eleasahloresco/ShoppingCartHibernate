<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="springform" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Category</title>
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

	
	<h1>All Categories</h1>
	<table width="300" style="text-align:center">
		<tr>
			<th>Number</th>
			<th>Name</th>
		</tr>
		
		<c:forEach var="category" items="${categories}">
			<tr>
				<td><c:out value="${category.id}" /></td>
				<td><c:out value="${category.name}" /></td>
			</tr>
		</c:forEach>
	</table>
	
	
	<h1>Add Category</h1>
	
	<c:url var="submitURL" value="/admin/addCategory" />
	
	<springform:form commandName="categoryModel" action="${submitURL}" method="POST">
		<springform:label path="name">Category Name</springform:label>
		<springform:input path="name"/>
		<springform:errors path="name" />
		
		<br />
		
		<input type="submit" value="Add Category"/>
	</springform:form>

</body>
</html>