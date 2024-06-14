<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=yes">
	<title>Shopping Cart</title>
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
			<h2>Your cart</h2>
		</div>
		
		<c:if test="${message != null}">
			<div class="row justify-content-center text-danger">
				<h4>${message}</h4>
			</div>
		</c:if>
		
		<c:set var="cart" value="${sessionScope['cart']}" />
		
		<c:if test="${cart.totalItems == 0}">
			<div class="row justify-content-center">
				<h2>There is nothing in your cart</h2>
			</div>
		</c:if>
		
		<br>
		
		<div class="row justify-content-center text-center">
		<c:if test="${cart.totalItems > 0}">
				<form action="update_cart" method="post" id="cartForm">
				<div>
					<table border="1" cellpadding="10" style="height: 100px;" class="table">
						<thead class="thead-dark">
							<tr class="align-middle">
								<th>No</th>
								<th colspan="2">Product</th>
								<th>Quantity</th>
								<th>Price</th>
								<th>Subtotal</th>
								<th>Action</th>
								</tr>
						</thead>
						
						<tbody>
						<c:forEach var="item" items="${cart.items}" varStatus="status">
								<tr class="align-middle justify-content-center text-center">
									<td class="align-middle justify-content-center text-center">${status.index + 1}</td>
									<td class="align-middle justify-content-center text-center">
										<img src="data:image/jpg;base64,${item.key.base64Image}" width="250" height="240"/>
									</td>
									
									<td class="align-middle justify-content-center text-center">	
										&nbsp;&nbsp;
										${item.key.shoeName}
									</td>
									<td class="align-middle justify-content-center text-center">
										<input type="hidden" name="shoeId" value="${item.key.shoeId}"/>
										<input type="text" name="quantity${status.index + 1}" value="${item.value}" size="5"/>
									</td>
									<td class="align-middle justify-content-center text-center"><fmt:formatNumber value="${item.key.shoePrice}" type="currency"></fmt:formatNumber></td>
									<td class="align-middle justify-content-center text-center"><fmt:formatNumber value="${item.value * item.key.shoePrice}" type="currency"></fmt:formatNumber></td>
									<td class="align-middle justify-content-center text-center">
										<a href="remove_from_cart?shoeId=${item.key.shoeId}" class="btn btn-outline-secondary">Remove</a>
									</td>
								</tr>
						</c:forEach>
						
						<tr align="center">
							<td></td>
							<td></td>
							<td></td>
							<td class="align-middle justify-content-center text-center"><b>${cart.totalQuantity} pair(s) of shoes</b></td>
							<td class="align-middle justify-content-center text-center"><b>Total</b></td>
							<td colspan="2" class="align-middle justify-content-center text-center"><b><fmt:formatNumber value="${cart.totalAmount}" type="currency"></fmt:formatNumber></b></td>
						</tr>
						
					</tbody>
					</table>
				</div>
				
				<div class="row justify-content-center">
					<table cellpadding="20">
						<tr>
							<td></td>
							<td><a href="${pageContext.request.contextPath}/" class="btn btn-outline-info">Continue Shopping</a></td>
							<td><button type="submit" class="btn btn-danger">Update</button></td>
							<td><button id="clearCart" type="button" class="btn btn-danger">Clear Cart</button></td>
							<td><a href="checkout" class="btn btn-outline-success">Check Out</a></td>
						</tr>
					</table>
				</div>
				</form>
		</c:if>
		</div>
	</div>
	
	<br><br><br><br>
	<jsp:directive.include file="footer.jsp"/>
</body>
<script type="text/javascript">
	
	$(document).ready(function(){
		$("#cartForm").validate({
			rules: {
				
				<c:forEach var="item" items="${cart.items}" varStatus="status">
					quantity${status.index + 1}: {
						required: true,
						number: true,
						min: 1
					},
				</c:forEach>
			},
			
			messages: {
				<c:forEach var="item" items="${cart.items}" varStatus="status">
					quantity${status.index + 1}: {
						required: "Please enter the quantity",
						number: "Quantity must be a number",
						min: "Quantity must be greater than 0"
					},
				</c:forEach>
			}
		});
		
		$("#clearCart").click(function(){
			window.location = 'clear_cart';
		});
	});
</script>
</html>