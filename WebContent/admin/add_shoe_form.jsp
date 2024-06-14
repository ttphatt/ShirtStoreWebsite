<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add shoes to order</title>
</head>
<body>
	<div align="center">
		<h2>Add shoes to order with id: ${order.orderId}</h2>
		
		<form action="add_shoe_to_order" method="post">
			<table>
				<tr>
					<td>Select pair of shoes</td>
					<td>
						<select name="shoeId">
							<c:forEach items="${listShoe}" var="shoe" varStatus="status">
								<option value="${shoe.shoeId}">${shoe.shoeName} - ${shoe.brand}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				
				<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
				
				<tr>
					<td>Number pairs of shoes</td>
					<td>
						<select name="quantity">
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
						</select>
					</td>
				</tr>
				<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
				
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="Add">
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" value="Cancel" onclick="javascipt: self.close();">
					</td>
				</tr>
				
			</table>
		</form>
	</div>
</body>
</html>