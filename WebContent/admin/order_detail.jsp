<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=yes">
	<title>Edit Order Details</title>
	<script type="text/javascript" src="../js/jquery-3.7.1.min.js"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<br><br><br><br>
	
	<div align="center">
		<h1>Edit Order with ID: ${order.orderId}</h1>
		<hr width="70%">	
	</div>
	
	<c:if test="${message != null}">
		<div align="center" style="color: red;">
			<h4>${message}</h4>
		</div>
	</c:if>
		
	<jsp:directive.include file="../common/common_order_detail.jsp"/>
	
	<br><br>
	<div align="center">
		<a href="edit_order?orderId=${order.orderId}" class="btn btn-outline-primary">Edit order</a>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="javascript:void(0)" id="${order.orderId}" class="deleteLink btn btn-outline-primary">Delete order</a>
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