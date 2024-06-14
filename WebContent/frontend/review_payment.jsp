<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=yes">
	<title>Review Payment</title>
	<script type="text/javascript" src="js/jquery-3.7.1.min.js"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<br><br><br><br>
	
	<div class="container">
		<div class="row justify-content-center">
			<h4>Please check your information carefully before making payment</h4>
		</div>
		
		<br>
		<br>
		
		<div class="row justify-content-center">
			<h2>Payer Information</h2>
		</div>
		
		<br>
		
		<div class="row justify-content-center">
			<table border="1" cellpadding="10" class="table table-striped" style="width: 300px">
				<tr>
					<td><b>First Name:</b></td>
					<td>${payer.firstName}</td>
				</tr>
				
				<tr>
					<td><b>Last Name:</b></td>
					<td>${payer.lastName}</td>
				</tr>
				
				<tr>
					<td><b>Email:</b></td>
					<td>${payer.email}</td>
				</tr>
			</table>
		</div>
		
		<br>
		<br>
		
		<div class="row justify-content-center">
			<h2>Recipient Information</h2>
		</div>
		
		<br>
		
		<div class="row justify-content-center">
			<table border="1" cellpadding="10" class="table table-striped" style="width: 450px">
				<tr>
					<td><b>Recipient's Name:</b></td>
					<td>${recipient.recipientName}</td>
				</tr>
				
				<tr>
					<td><b>Address Line 1:</b></td>
					<td>${recipient.line1}</td>
				</tr>
				
				<tr>
					<td><b>Address Line 2:</b></td>
					<td>${recipient.line2}</td>
				</tr>
				
				<tr>
					<td><b>City:</b></td>
					<td>${recipient.city}</td>
				</tr>
				
				<tr>
					<td><b>State:</b></td>
					<td>${recipient.state}</td>
				</tr>
				
				<tr>
					<td><b>Country Code:</b></td>
					<td>${recipient.countryCode}</td>
				</tr>
				
				<tr>
					<td><b>Postal Code:</b></td>
					<td>${recipient.postalCode}</td>
				</tr>
			</table>
		</div>
		
		<br>
		<br>
		
		<div class="row justify-content-center">
			<h2>Transaction Details:</h2>
		</div>
		
		<br>
		
		<div class="row justify-content-center">	
			<table border="1" cellpadding="10" class="table table-striped">
				<tr>
					<td><b>Description:</b></td>
					<td class="align-middle justify-content-center text-center">${transaction.description}</td>
				</tr>
				
				<tr>
					<td colspan="2"><b>Item List:</b></td>
				</tr>
				
				<tr>
					<td colspan="2">
					<table border="1" cellpadding="10" style="text-align: center" class="table">
						<thead class="thead-dark">
						<tr>
							<th class="align-middle justify-content-center text-center">No.</th>
							<th class="align-middle justify-content-center text-center">Name</th>
							<th class="align-middle justify-content-center text-center">Quantity</th>
							<th class="align-middle justify-content-center text-center">Price</th>
							<th class="align-middle justify-content-center text-center">Subtotal</th>
						</tr>
						</thead>
						
						<tbody>
						<c:forEach items="${transaction.itemList.items}" var="item" varStatus="status">
							<tr>
								<td class="align-middle justify-content-center text-center">${status.index + 1}</td>
								<td class="align-middle justify-content-center text-center">${item.name}</td>
								<td class="align-middle justify-content-center text-center">${item.quantity}</td>
								<td class="align-middle justify-content-center text-center"><fmt:formatNumber value="${item.price}" type="currency"/></td>
								<td class="align-middle justify-content-center text-center"><fmt:formatNumber value="${item.price * item.quantity}" type="currency"/></td>
							</tr>
						</c:forEach>
						
						<tr>
							<td colspan="5" align="right">
								<p><b>Subtotal: </b><fmt:formatNumber value="${transaction.amount.details.subtotal}" type="currency" /></p>
								<p><b>Tax: </b><fmt:formatNumber value="${transaction.amount.details.tax}" type="currency" /></p>
								<p><b>Shipping fee: </b><fmt:formatNumber value="${transaction.amount.details.shipping}" type="currency" /></p>
								<p><b>Total price: </b><fmt:formatNumber value="${transaction.amount.total}" type="currency" /></p>
							</td>
						</tr>
						</tbody>
						
					</table>
					</td>
				</tr>
			</table>
		</div>
		
		<br>
		<br>
		
		<div class="row justify-content-center">
			<form action="execute_payment" method="post">
				<input type="hidden" name="paymentId" value="${param.paymentId}" />
				<input type="hidden" name="PayerID" value="${param.PayerID}" />
				<button type="submit" class="btn btn-outline-success">Pay now</button>
			</form>
		</div>
	</div>
	
	<br><br><br><br>
	<jsp:directive.include file="footer.jsp"/>
</body>
</html>