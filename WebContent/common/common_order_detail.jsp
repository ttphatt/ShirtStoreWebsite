<div class="container">
	<div class="row justify-content-center">
		<h2>Order Overview</h2>
	</div>
	
	<br>
		<div class="row justify-content-center">
			<table border="1" cellpadding="10" style="text-align: left; width: 400px" class="table table-striped">
				<tr>
					<td><b>Ordered by</b></td>
					<td>${order.customer.fullName}</td>
				</tr>
				
				<tr>
					<td><b>Order status</b></td>
					<td>${order.status}</td>
				</tr>
				
				<tr>
					<td><b>Order date</b></td>
					<td>${order.orderDate}</td>
				</tr>
				
				<tr>
					<td><b>Payment method</b></td>
					<td>${order.payment}</td>
				</tr>
				
				<tr>
					<td><b>Pair(s) of shoes</b></td>
					<td>${order.pairOfShoes}</td>
				</tr>
				
				<tr>
					<td><b>Total price</b></td>
					<td><fmt:formatNumber type="currency" value="${order.orderSum}"/></td>
				</tr>
			</table>
		</div>

	<br>
	<div class="row justify-content-center">	
		<h2>Recipient Information</h2>
	</div>	
	<br>
		<div class="row justify-content-center">
		<table border="1" cellpadding="10" class="table table-striped" style="width: 450px">
			<tr>
				<td><b>Recipient's first name</b></td>
				<td>${order.firstname}</td>
			</tr>
			
			<tr>
				<td><b>Recipient's last name</b></td>
				<td>${order.lastname}</td>
			</tr>
			
			<tr>
				<td><b>Recipient's phone number</b></td>
				<td>${order.phone}</td>
			</tr>
			
			<tr>
				<td><b>Address line 1</b></td>
				<td>${order.addressLine1}</td>
			</tr>
			
			<tr>
				<td><b>Address line 2</b></td>
				<td>${order.addressLine2}</td>
			</tr>
			
			<tr>
				<td><b>City</b></td>
				<td>${order.city}</td>
			</tr>
			
			<tr>
				<td><b>State</b></td>
				<td>${order.state}</td>
			</tr>
			
			<tr>
				<td><b>Zip code</b></td>
				<td>${order.zipcode}</td>
			</tr>
			
			<tr>
				<td><b>Country</b></td>
				<td>${order.countryName}</td>
			</tr>
		</table>
		</div>
	
	<br>
	<div class="row justify-content-center">
		<h2>Ordered Shoes</h2>
	</div>
		<br>
		<div class="row justify-content-center">
		<table border="1" cellpadding="10" style="text-align: center" class="table">
			<thead class="thead-dark">
			<tr>
				<th class="align-middle justify-content-center text-center">Index</th>
				<th colspan="2" class="align-middle justify-content-center text-center">Shoes' name</th>
				<th class="align-middle justify-content-center text-center">Brand</th>
				<th class="align-middle justify-content-center text-center">Price</th>
				<th class="align-middle justify-content-center text-center">Quantity</th>
				<th class="align-middle justify-content-center text-center">Sub total</th>
			</tr>
			</thead>
			
			<tbody class="align-middle justify-content-center text-center">
			<c:forEach items="${order.orderDetails}" var="orderDetail" varStatus="status">
			<tr>
				<td class="align-middle justify-content-center text-center">${status.index + 1}</td>
				<td class="align-middle justify-content-center text-center">
					<img src="data:image/jpg;base64,${orderDetail.shoe.base64Image}" width="150" height="140"/>
				</td>
				
				<td class="align-middle justify-content-center text-center">
					${orderDetail.shoe.shoeName}
				</td>
				<td class="align-middle justify-content-center text-center">${orderDetail.shoe.brand}</td>
				<td class="align-middle justify-content-center text-center"><fmt:formatNumber type="currency" value="${orderDetail.shoe.shoePrice}"/></td>
				<td class="align-middle justify-content-center text-center">${orderDetail.quantity}</td>
				<td class="align-middle justify-content-center text-center"><fmt:formatNumber type="currency" value="${orderDetail.subTotal}"/></td>	
			</tr>
			</c:forEach>
			
			<tr>
				<td colspan="7" align="right">
					<p>Subtotal: <fmt:formatNumber type="currency" value="${order.subtotal}"/></p>
					<p>Tax: <fmt:formatNumber type="currency" value="${order.tax}"/></p>
					<p>Shipping fee: <fmt:formatNumber type="currency" value="${order.shippingFee}"/></p>
					<p>Total: <fmt:formatNumber type="currency" value="${order.orderSum}"/></p>
				</td>
			</tr>
			</tbody>
		</table>
		</div>

</div>