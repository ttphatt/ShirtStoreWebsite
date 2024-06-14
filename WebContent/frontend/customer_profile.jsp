<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=yes">
	<title>Customer's Profile</title>
	
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script type="text/javascript" src="js/jquery-3.7.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<br><br><br><br>
	
	<div class="container">
		<div class="row justify-content-center">
			<h3>Welcome, ${loggedCustomer.fullName}</h3>
		</div>
			
			<hr width="70%">
		
		<div class="row justify-content-center">
			<table border="1" cellpadding="20" class="table table-striped" style="width: 400px">
				<tr>
					<td><b>Email address: </b></td>
					<td>${loggedCustomer.email}</td>
				</tr>
				
				<tr>
					<td><b>First name: </b></td>
					<td>${loggedCustomer.firstname}</td>
				</tr>
				
				<tr>
					<td><b>Last name: </b></td>
					<td>${loggedCustomer.lastname}</td>
				</tr>
				
				<tr>
					<td><b>Phone number: </b></td>
					<td>${loggedCustomer.phoneNumber}</td>	
				</tr>
				
				<tr>
					<td><b>Address 1: </b></td>
					<td>${loggedCustomer.addressLine1}</td>
				</tr>
				
				<tr>
					<td><b>Address 2: </b></td>
					<td>${loggedCustomer.addressLine2}</td>
				</tr>
				
				<tr>
					<td><b>City: </b></td>
					<td>${loggedCustomer.city}</td>
				</tr>
				
				<tr>
					<td><b>State: </b></td>
					<td>${loggedCustomer.state}</td>
				</tr>
				
				<tr>
					<td><b>Zip code: </b></td>
					<td>${loggedCustomer.zip}</td>
				</tr>
					
				<tr>
					<td><b>Country: </b></td>
					<td>${loggedCustomer.countryName}</td>
				</tr>
						
				<!-- <tr><td>&nbsp;</td></tr> -->
				
				<tr>
					<td colspan="2" align="center"><b><a href="edit_profile" class="btn btn-outline-primary">Edit your profile</a></b></td>
				</tr>
			</table>
		</div>
	
	</div>
	
	
	
	<br><br><br><br>
	<jsp:directive.include file="footer.jsp"/>
</body>
</html>