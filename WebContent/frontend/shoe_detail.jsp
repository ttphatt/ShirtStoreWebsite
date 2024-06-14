<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fnc" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=yes">
	<title>${shoe.shoeName} - PHK shoes store</title>
	
	<script type="text/javascript" src="js/jquery-3.7.1.min.js"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<br><br><br><br>

	<div class="container">
		<div class="row justify-content-center">
		<table width="60%" border="0">
			<tr>
				<td>
					<img src="data:image/jpg;base64,${shoe.base64Image}" width="250" height="240"/>
				</td>
				
				<td>
					<h2>${shoe.shoeName}</h2><br>
					From: ${shoe.brand}
					<h2><jsp:directive.include file="shoe_rating.jsp"/></h2>
					<h2>Price: $${shoe.shoePrice}</h2>
					<button id="buttonAddToCart" class="btn btn-success">Add to your cart</button>
				</td>
			</tr>
		</table>
		</div>
		
		
		<br><br><br><br>
		<div class="row justify-content-center">
			<h2>Shoes' Description</h2>
		</div>
		
		<div class="row justify-content-center">
			${shoe.description}
		</div>
		
		<br><br><br><br>
		<div class="row justify-content-center">
			<h2><a id="rates">Customer's Rate</a></h2>
		</div>
		
		<br>
		
		<div class="row justify-content-center">
			<button id="buttonWriteRate" class="btn btn-danger">Rate our shoes</button>
		</div>
			
		<br>
		
		<div class="row justify-content-center">
			<table>
				<tr>
					<td colspan="3">
						<table cellpadding="10">
							<c:forEach items="${shoe.rates}" var="rate">
								<tr>
									<td>
										<c:forTokens items="${rate.stars}" delims="," var="star">
											<c:if test="${star eq 'on'}">
												<img src="images/OnStarV1.png">
											</c:if>
						
											<c:if test="${star eq 'off'}">
												<img src="images/OffStarV1.png">
											</c:if>
										</c:forTokens>
										<b>-${rate.headline}</b>
									</td>
								</tr>
								
								<tr>
									<td>
										by ${rate.customer.fullName} on ${rate.rateTime}
									</td>
								</tr>
								
								<tr>
									<td>
										${rate.ratingDetail}
									</td>
								</tr>
								
								<tr><td>&nbsp;</td></tr>
							</c:forEach>
						</table>
					</td>
				</tr>
			</table>
			
		</div>
		
	</div>
	
	<br><br><br><br>
	<jsp:directive.include file="footer.jsp"/>
</body>
<script type="text/javascript">
	$(document).ready(function(){
		$("#buttonWriteRate").click(function(){
			window.location = 'write_rate?shoeId=' + ${shoe.shoeId} ;
		});
		
		$("#buttonAddToCart").click(function(){
			window.location = 'add_to_cart?shoeId=' + ${shoe.shoeId} ;
		});
		
	});
</script>
</html>