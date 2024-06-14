<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=yes">
	<title>Admin Login</title>
	<script type="text/javascript" src="js/jquery-3.7.1.min.js"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		
		<div class="row justify-content-center">
			<h1>Admin Login</h1>
		</div>
		
		<c:if test="${message != null}">
			<br>
			<div class="row justify-content-center" style="color: red;">
				<h4>${message}</h4>
			</div>
		</c:if>
		
		<br>
		
		<div class="row justify-content-center">
		<form id="formLogin" action="login" method="post">
			<table class="table">
				<tr>
					<td>Email:</td>
					<td><input type="email" name="email" id="email" size="30" required minlength="5" maxlength="50" class="form-control"></td>
				</tr>
				
				<tr>
					<td>Password:</td>
					<td><input type="password" name="password" id="password" size="30" required minlength="5" maxlength="50" class="form-control"></td>
				</tr>
			
				<tr>
					<td colspan="2" align="center">
						<button type="submit" class="btn btn-dark btn-lg">Login</button>
					</td>
				</tr>
			</table>
		</form>
		</div>
	</div>
</body>
</html>