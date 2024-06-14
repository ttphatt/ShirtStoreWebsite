<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=yes">
	<title>Check out</title>
	<script type="text/javascript" src="js/jquery-3.7.1.min.js"></script>
	<script type="text/javascript" src="js/jquery.validate.min.js"></script>
	
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<br><br><br><br>

	<div class="container">	
		<c:if test="${message != null}">
			<div class="row justify-content-center" style="color: red;">
				<h4>${message}</h4>
			</div>
		</c:if>
		
		<c:set var="cart" value="${sessionScope['cart']}" />
		
		<c:if test="${cart.totalItems == 0}">
			<div class="row justify-content-center">
				<h2>There is nothing in your cart</h2>
			</div>
		</c:if>
		
		
		<c:if test="${cart.totalItems > 0}">
				<div class="row justify-content-center">
					<h2>Review your order details <a href="view_cart" class="btn btn-outline-primary btn-lg">Edit</a></h2>
				</div>
				
				<br>
				
				<div class="row justify-content-center">
					<table border="1" cellpadding="10" class="table">
						<thead class="thead-dark">
						<tr>
							<th class="align-middle justify-content-center text-center">No</th>
							<th colspan="2" class="align-middle justify-content-center text-center">Product</th>
							<th class="align-middle justify-content-center text-center">Brand</th>
							<th class="align-middle justify-content-center text-center">Price</th>
							<th class="align-middle justify-content-center text-center">Quantity</th>
							<th class="align-middle justify-content-center text-center">Subtotal</th>
						</tr>
						</thead>
						
						<tbody>
						<c:forEach var="item" items="${cart.items}" varStatus="status">
							<tr align="center">
								<td class="align-middle justify-content-center text-center">${status.index + 1}</td>
								<td class="align-middle justify-content-center text-center">
									<img src="data:image/jpg;base64,${item.key.base64Image}" width="250" height="240"/>
								</td>
								
								<td class="align-middle justify-content-center text-center">	
									&nbsp;&nbsp;
									${item.key.shoeName}
								</td>
								
								<td class="align-middle justify-content-center text-center">${item.key.brand}</td>
								
								<td class="align-middle justify-content-center text-center"><fmt:formatNumber value="${item.key.shoePrice}" type="currency"></fmt:formatNumber></td>
								
								<td class="align-middle justify-content-center text-center">
									<input type="text" name="quantity${status.index + 1}" value="${item.value}" size="5" readonly="readonly"/>
								</td>
								<td class="align-middle justify-content-center text-center"><fmt:formatNumber value="${item.value * item.key.shoePrice}" type="currency"></fmt:formatNumber></td>
							</tr>
						</c:forEach>
						
						<tr align="right">
							<td colspan="7">
								<p><b>Number pairs of shoes:</b> ${cart.totalQuantity}</p>
								<p><b>Subtotal: </b><fmt:formatNumber value="${cart.totalAmount}" type="currency" /></p>
								<p><b>Tax: </b><fmt:formatNumber value="${tax}" type="currency" /></p>
								<p><b>Shipping fee: </b><fmt:formatNumber value="${shippingFee}" type="currency" /></p>
								<p><b>Total price: </b><fmt:formatNumber value="${totalPrice}" type="currency" /></p>
							</td>
						</tr>
						</tbody>
					</table>
				</div>
				
				<br>
				<br>
				
				<div class="row justify-content-center">
					<h2>Recipient's information</h2>
				</div>
				
				<br>
		
					<form id="orderForm" action="place_order" method="post">
						<div class="row justify-content-center">	
							<table border="1" cellpadding="20" class="table table-striped" style="width: 550px">
								<tr>
									<td>Recipient's first name:</td>
									<td><input type="text" name="firstname" value="${loggedCustomer.firstname}"/></td>
								</tr>
								
								<tr>
									<td>Recipient's last name:</td>
									<td><input type="text" name="lastname" value="${loggedCustomer.lastname}" /></td>
								</tr>
								
								<tr>
									<td>Recipient phone:</td>
									<td><input type="text" name="phoneNumber" value="${loggedCustomer.phoneNumber}" /></td>
								</tr>
								
								<tr>
									<td>Address 1:</td>
									<td><input type="text" name="addressLine1" value="${loggedCustomer.addressLine1}" /></td>
								</tr>
								
								<tr>
									<td>Address 2:</td>
									<td><input type="text" name="addressLine2" value="${loggedCustomer.addressLine2}" /></td>
								</tr>
								
								<tr>
									<td>City:</td>
									<td><input type="text" name="city" value="${loggedCustomer.city}" /></td>
								</tr>
								
								<tr>
									<td>State:</td>
									<td><input type="text" name="state" value="${loggedCustomer.state}" /></td>
								</tr>
								
								<tr>
									<td>Zip Code:</td>
									<td><input type="text" name="zip" value="${loggedCustomer.zip}" /></td>
								</tr>
								
								<tr>
									<td>Country:</td>
									<td>
										<select name="country" id="country">
											<c:forEach items="${mapCountries}" var="country">
												<option value="${country.value}" <c:if test='${loggedCustomer.country eq country.value}'>selected='selected'</c:if> >${country.key}</option>
											</c:forEach>
										</select>
									</td>
								</tr>
							</table>
						</div>
						
						<br><br>
						<div class="row justify-content-center">
							<h2>Payment method</h2>
						</div>
						
						<br>
						
						<div class="row justify-content-center">
							Choose your payment method:
							&nbsp;&nbsp;&nbsp;&nbsp;
							<select name="payment">
								<option value="Cash On Delivery">Cash On Delivery</option>
								<option value="Paypal">Paypal or Credit card</option>
							</select>
						</div>
						
						<div class="row justify-content-center">
							<table cellpadding="20">
								<tr>
									<td></td>
									<td><button type="submit" class="btn btn-outline-success">Place Your Order</button></td>
									<td><a href="${pageContext.request.contextPath}/" class="btn btn-outline-info">Continue Shopping</a></td>
								</tr>
							</table>
						</div>
					</form>
		</c:if>
		
	</div>
	
	<br><br><br><br>
	<jsp:directive.include file="footer.jsp"/>
</body>
<script type="text/javascript">
	
	$(document).ready(function(){
		$("#orderForm").validate({
			rules:{
				firstname: "required",
				lastname: "required",
				phoneNumber: "required",
				addressLine1: "required",
				addressLine2: "required",
				city: "required",
				state: "required",
				zip: "required",
				country: "required",
			},
			
			messages:{
				firstname: "Please enter recipient's first name",
				lastname: "Please enter recipient's last name",
				phoneNumber: "Please enter recipient's phone number",
				addressLine1: "Please enter recipient's first address",
				addressLine2: "Please enter recipient's second address",
				city: "Please enter recipient's city",
				state: "Please enter recipient's state",
				zip: "Please enter recipient's zip code",
				country: "Please enter recipient's country",
			}
			
		});
	});
</script>
</html>