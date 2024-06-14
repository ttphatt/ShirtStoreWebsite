<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=yes">
	<title>
		<c:if test="${customer != null}">
			Edit Customer
		</c:if>
			
		<c:if test="${customer == null}">
			Create New Customer
		</c:if>
	
	</title>
	<script type="text/javascript" src="../js/jquery-3.7.1.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
	
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<br><br><br><br>
	
	<div align="center">
		<h1>
			<c:if test="${customer != null}">
				Edit Customer
			</c:if>
			
			<c:if test="${customer == null}">
				Create New Customer
			</c:if>
		</h1>
	</div>
		
	<br>
	<br>
		
	<div align="center">
		<c:if test="${customer != null}">
			<form action="update_customer" method="post" id="customerForm">
			<input type="hidden" name="customerId" value="${customer.customerId}"/>
			
		</c:if>
		
		<c:if test="${customer == null}">
			<form action="create_customer" method="post" id="customerForm">
			
		</c:if>
		
			<jsp:directive.include file="../common/customer_form.jsp"/>
		
	</div>
	
	
	<br><br><br><br>
	<jsp:directive.include file="footer.jsp"/>
</body>
<script type="text/javascript" src="../js/customer-form.js">
</script>
</html>