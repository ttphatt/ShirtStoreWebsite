<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fnc" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=yes">
	<title>Search results for ${keyword} - PHK Shoe Store</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script type="text/javascript" src="js/jquery-3.7.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<br><br><br><br>

	<div class="container">
		<div class="row justify-content-center text-center">
			<c:if test="${fnc:length(result) == 0}">
				<h2>We are sorry but there is nothing for the keyword ${keyword}</h2>
			</c:if>
			
			<c:if test="${fnc:length(result) > 0}">
				<div>
					<h2>Results for "${keyword}"</h2>
					<br><br>
					<div class="row justify-content-center mb-5 pd-5">
					<c:forEach var="shoe" items="${result}">
						<div class="col justify-content-center text-center">
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
			</c:if>
		</div>
	</div>
	
	<br><br><br><br>
	<jsp:directive.include file="footer.jsp"/>
</body>
</html>