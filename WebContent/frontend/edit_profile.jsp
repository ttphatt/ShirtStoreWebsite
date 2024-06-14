<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=yes">
	<title>Sign up as a customer</title>
	<script type="text/javascript" src="js/jquery-3.7.1.min.js"></script>
	<script type="text/javascript" src="js/jquery.validate.min.js"></script>
	

	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<br><br><br><br>
	
	<div class="container">
		<div class="row justify-content-center">
			<h1>
				Profile Editing 
			</h1>
		</div>
	
		<div class="row justify-content-center">
		<form action="update_profile" method="post" id="customerForm">
			<table cellpadding="10px">
				<tr>
					<td align="left">Email</td>
					<td align="left"><b>${loggedCustomer.email}</b> (Unable to change)</td>
				</tr>
				
				<tr>
					<td align="left">First Name</td>
					<td align="left"><input type="text" id="firstname" name="firstname" size="40" value="${loggedCustomer.firstname}"/></td>
				</tr>
				
				<tr>
					<td align="left">Last Name</td>
					<td align="left"><input type="text" id="lastname" name="lastname" size="40" value="${loggedCustomer.lastname}"/></td>
				</tr>
				
				<tr>
					<td align="left">Phone number</td>
					<td align="left"><input type="text" id="phone" name="phone" size="20" value="${loggedCustomer.phoneNumber}"/></td>
				</tr>
				
				<tr>
					<td align="left">Address Line 1</td>
					<td align="left"><input type="text" id="address1" name="address1" size="70" value="${loggedCustomer.addressLine1}"/></td>
				</tr>
				
				<tr>
					<td align="left">Address Line 2</td>
					<td align="left"><input type="text" id="address2" name="address2" size="70" value="${loggedCustomer.addressLine2}"/></td>
				</tr>
				
				<tr>
					<td align="left">City</td>
					<td align="left"><input type="text" id="city" name="city" size="20" value="${loggedCustomer.city}"/></td>
				</tr>
				
				<tr>
					<td align="left">State</td>
					<td align="left"><input type="text" id="state" name="state" size="20" value="${loggedCustomer.state}"/></td>
				</tr>
				
				<tr>
					<td align="left">Zip Code</td>
					<td align="left"><input type="text" id="zip" name="zip" size="20" value="${loggedCustomer.zip}"/></td>
				</tr>
				
				<tr>
					<td align="left">Country</td>
					<td align="left">
						<select name="country" id="country">
							<c:forEach items="${mapCountries}" var="country">
								<option value="${country.value}" <c:if test='${loggedCustomer.country eq country.value}'>selected='selected'</c:if> >${country.key}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				
				<tr>
					<td colspan="2" align="center" style="color: red">
						<i><b>(Please leave the password field blank if you do not want to change your current password)</b></i>
					</td>
				</tr>
				
				<tr>
					<td align="left">Password</td>
					<td align="left"><input type="password" id="password" name="password" size="40"/></td>
				</tr>
				
				<tr>
					<td align="left">Confirm password</td>
					<td align="left"><input type="password" id="confirmPassword" name="confirmPassword" size="40"/></td>
				</tr>
				
				<tr><td>&nbsp;</td></tr>
					
				<tr>
					<td colspan="2" align="center">
						<button type ="submit" class="btn btn-outline-success">Save</button>
						<button id="buttonCancel" class="btn btn-outline-info">Cancel</button>
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
		$("#customerForm").validate({
			rules: {
				email:{
					required: true,
					email: true
				},
				firstname: "required",
				lastname: "required",
				confirmPassword:{
					equalTo: "#password"
				},
				phone: "required",
				address1: "required",
				address1: "required",
				city: "required",
				state: "required",
				zip: "required",
				country: "required",
			},
			
			messages: {
				email:{
					required: "Please enter an email address",
					email: "Please enter a valid email address"
				},
				
				firstname: "Please enter full name",
				lastname: "Please enter full name",
				confirmPassword:{
					equalTo: "The password does not match with each other"
				},
				phone: "Please enter a phone number",
				address1: "Please enter address 1",
				address2: "Please enter address 2",
				city: "Please enter city",
				state: "Please enter state",
				zip: "Please enter the zip code",
				country: "Please enter the country's name",
			}
		});
		
		
		$("#buttonCancel").click(function(){
			history.go(-1);
		});
	});
</script>
</html>