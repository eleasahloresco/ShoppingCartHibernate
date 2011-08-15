<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Homepage</title>
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
	
	<h1>Homepage</h1>
	<c:url var="categoryURL" value="/admin/addCategory" />
	<a href="${categoryURL}">Add Category</a>
	<br />
	<c:url var="productURL" value="/admin/addProduct" />
	<a href="${productURL}">Add Product</a>
	

</body>
</html>