<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=yes">
	<title>Managing Orders</title>
	<script type="text/javascript" src="../js/jquery-3.7.1.min.js"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<br><br><br><br>
	
	<div align="center">
		<h1>Orders Management</h1>
		<hr width="70%">	
	</div>
	
	<br>
	
	<c:if test="${message != null}">
		<div align="center" style="color: red;">
			<h4>${message}</h4>
		</div>
	</c:if>
	
	<br>	
		
	<div align="center">
		<table border="1" cellpadding="5" style="text-align: center; width: 1300px" class="table">
			<thead class="thead-dark">
			<tr>
				<th class="align-middle justify-content-center text-center">Index</th>
				<th class="align-middle justify-content-center text-center">Order's ID</th>
				<th class="align-middle justify-content-center text-center">Ordered by</th>
				<th class="align-middle justify-content-center text-center">Pair(s) of shoes</th>
				<th class="align-middle justify-content-center text-center">Total</th>
				<th class="align-middle justify-content-center text-center">Payment Method</th>
				<th class="align-middle justify-content-center text-center">Status</th>
				<th class="align-middle justify-content-center text-center">Order Date</th>
				<th class="align-middle justify-content-center text-center">Actions</th>
			</tr>
			</thead>
			
			<tbody>
			<c:forEach var="order" items="${listOrder}" varStatus="status">
			<tr>
				<td class="align-middle justify-content-center text-center">${status.index + 1}</td>
				<td class="align-middle justify-content-center text-center">${order.orderId}</td>
				<td class="align-middle justify-content-center text-center">${order.customer.fullName}</td>
				<td class="align-middle justify-content-center text-center">${order.pairOfShoes}</td>
				<td class="align-middle justify-content-center text-center"><fmt:formatNumber type="currency" value="${order.orderSum}"/></td>
				<td class="align-middle justify-content-center text-center">${order.payment}</td>
				<td class="align-middle justify-content-center text-center">${order.status}</td>
				<td class="align-middle justify-content-center text-center">${order.orderDate}</td>
				
				<td class="align-middle justify-content-center text-center">
					<a href="view_order?orderId=${order.orderId}" class="btn btn-outline-primary">Details</a>	&nbsp;
					<a href="edit_order?orderId=${order.orderId}" class="btn btn-outline-primary">Edit</a>	&nbsp;
					<a href="javascript:void(0)" class="deleteLink btn btn-outline-primary" id="${order.orderId}">Delete</a> &nbsp;
				</td>
			</tr>
			</c:forEach>
			</tbody>
			
		</table>
	</div>
	
	
	<br><br><br><br>
	<jsp:directive.include file="footer.jsp"/>
</body>
<script>
	$(document).ready(function(){
		$(".deleteLink").each(function(){
			$(this).on("click", function(){
				orderId = $(this).attr("id");
				if(confirm("Are you sure you want to delete the order with order's id: " + orderId + " ?")){
					window.location = "delete_order?orderId=" + orderId;
				}
			})
		});
	});
</script>

</html>