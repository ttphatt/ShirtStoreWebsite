<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=yes">
	<title>Customer Login</title>
	<script type="text/javascript" src="js/jquery-3.7.1.min.js"></script>
	<script type="text/javascript" src="js/jquery.validate.min.js"></script>
	
	
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script type="text/javascript" src="js/jquery-3.7.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<br><br><br><br>

	<div class="container">
		<div class="row justify-content-center text-center">
			<h2>Customer Login Page</h2>
		</div>
		
		<br>
		
		<c:if test="${message != null}">
			<div class="row justify-content-center" style="color: red;">
				<h4>${message}</h4>
			</div>
		</c:if>
		
		<div class="row justify-content-center text-center">
		<form id="formLogin" action="login" method="post">
			<table cellpadding="10">
				<tr>
					<td>Email:</td>
					<td><input type="text" name="email" id="email" size="20"></td>
				</tr>
				
				<tr>
					<td>Password:</td>
					<td><input type="password" name="password" id="password" size="20"></td>
				</tr>
							
				<tr>
					<td colspan="2" align="center">
						<button type="submit" class="btn btn-dark">Login</button>
					</td>
				</tr>
			</table>
		</form>
		</div>
	</div>
	
	<br><br><br><br>
	<jsp:directive.include file="footer.jsp"/>
</body>
<script type="text/javascript">
	
	$(document).ready(function(){
		$("#formLogin").validate({
			rules: {
				email: {
					required: true,
					email: true,
				},
				password: "required",
			},
			
			messages: {
				email: {
					required: "Please enter the email",
					email: "Please enter a valid email",
				},
				password: "Please enter password",
			}
		});
	});
</script>
</html>