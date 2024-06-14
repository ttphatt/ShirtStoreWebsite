<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fnc" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=yes">
	<title>My Orders History</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script type="text/javascript" src="js/jquery-3.7.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<br><br><br><br>
	
	
	<div class="container">
		<div class="row justify-content-center text-center">
			<h1>My Orders History</h1>	
		</div>
		
		<div>
			<hr width="70%"/>
		</div>
	
		<br>
	
		<c:if test="${fnc:length(listOrders) == 0}">
			<div class="row justify-content-center">
				<h2>You don't have any orders yet</h2>
			</div>
		</c:if>
		
		<c:if test="${fnc:length(listOrders) > 0}">
		<div class="row justify-content-center">
			<table border="1" cellpadding="5" style="text-align: center" class="table">
				<thead class="thead-dark">
				<tr>
					<th>Index</th>
					<th>Order's ID</th>
					<th>Quantity</th>
					<th>Total Price</th>
					<th>Order Date</th>
					<th>Status</th>
					<th>Actions</th>
				</tr>
				</thead>
				
				<tbody class="align-middle justify-content-center text-center">
				<c:forEach var="order" items="${listOrders}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${order.orderId}</td>
					<td>${order.pairOfShoes}</td>
					<td><fmt:formatNumber type="currency" value="${order.orderSum}"/></td>
					<td>${order.orderDate}</td>
					<td>${order.status}</td>
					
					<td>
						<a href="show_order_detail?orderId=${order.orderId}" class="btn btn-outline-dark">View Details</a>
					</td>
				</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
		</c:if>
	</div>
	
	<br><br><br><br>
	<jsp:directive.include file="footer.jsp"/>
</body>
</html>