<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Shoes of ${type.typeName} - PSK Shoe Store</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script type="text/javascript" src="js/jquery-3.7.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<br>

	<div class="container">
		<div class="row justify-content-center">
			<h2>${type.typeName}</h2>
		</div>
		
		<br><br>	
		
		<div class="row justify-content-center text-center mb-5">
			<c:forEach var="shoe" items="${listShoes}">
				<div class="col">
					<div>
						<a href="view_shoe?id=${shoe.shoeId}">
							<img src="data:image/jpg;base64,${shoe.base64Image}" width="250" height="240"/>
						</a>
					</div>
					<div style="font-size: 25px">
						<a href="view_shoe?id=${shoe.shoeId}" class="text-dark">
							<b>${shoe.shoeName}</b>
						</a>
					</div>
					<div>
						<jsp:directive.include file="shoe_rating.jsp"/>
					</div>
					<div>From: ${shoe.brand}</div>
					<div><b>Price: $${shoe.shoePrice}</b></div>
				</div>
			</c:forEach>
		</div>

	</div>
	
	<br><br><br><br>
	<jsp:directive.include file="footer.jsp"/>
</body>
</html>