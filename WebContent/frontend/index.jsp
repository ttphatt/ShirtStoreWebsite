<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	 <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=yes">
	<title>PHK shoes store</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script type="text/javascript" src="js/jquery-3.7.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<br><br><br><br>
	
	<!-- With Bootstrap -->	
	<div class="container">
	
		<div class="row">
			<div class="col">	
				<h2>New shoes</h2>
			</div>
		</div>
	
		<div class="row justify-content-center mb-5">
			<c:forEach var="shoe" items="${listNewShoes}">
				<jsp:directive.include file="shoe_group.jsp"/>
			</c:forEach>
		</div>
		
		<div class="row">
			<div class="col">
				<h2>Top-Selling shoes</h2>
			</div>
		</div>
	
		<div class="row justify-content-center mb-5">
			<c:forEach var="shoe" items="${listBestSellingShoes}">
				<jsp:directive.include file="shoe_group.jsp"/>
			</c:forEach>
		</div>
		
		<div class="row">
			<div class="col">
				<h2>Most-favorite Shoes</h2>
			</div>
		</div>
	
		<div class="row justify-content-center mb-5">
			<c:forEach var="shoe" items="${listMostFavoredShoes}">
				<jsp:directive.include file="shoe_group.jsp"/>
			</c:forEach>
		</div>
		
	<!-- Without Bootstrap -->	
	<%-- <div align="center">
		<h2 align="left" style="margin-left: 190px">New shoes</h2>
		<div align="center" style="width: 80%; margin: 0 auto;">
			<c:forEach var="shoe" items="${listNewShoes}">
				<jsp:directive.include file="shoe_group.jsp"/>
			</c:forEach>
		</div>
		
		<h2 align="left" style="margin-left: 190px">Top-Selling shoes</h2>
		<div align="center" style="width: 80%; margin: 0 auto;">
			<c:forEach var="shoe" items="${listBestSellingShoes}">
				<jsp:directive.include file="shoe_group.jsp"/>
			</c:forEach>
		</div>
		
		<h2 align="left" style="margin-left: 190px">Most-favorite Shoes</h2>
		<div align="center" style="width: 80%; margin: 0 auto;">
			<c:forEach var="shoe" items="${listMostFavoredShoes}">
				<jsp:directive.include file="shoe_group.jsp"/>
			</c:forEach>
		</div>
	</div> --%>
	</div>
	<br><br><br><br>
	<jsp:directive.include file="footer.jsp"/>
</body>
</html>