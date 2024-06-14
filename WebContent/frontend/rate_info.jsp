<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=yes">
	<title>Rate our shoes</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.css">
	
	<script type="text/javascript" src="js/jquery-3.7.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.js"></script>

	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<br><br><br><br>

	<div class="container">
		<div class="row justify-content-center text-center">
		<form id="rateForm">
			<table cellpadding="10px" width="100%">
				<tr>
					<td><h2 align="left">You already wrote the rate for this pair of shoes</h2></td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td colspan="3"><hr></td>	
				</tr>
				
				<tr>
					<td>
						<img src="data:image/jpg;base64,${shoe.base64Image}" width="250" height="240"/>
						<h2><b>${shoe.shoeName}</b></h2>
					</td>
				
					<td>
						<div id="rateYo"></div>
						<br>
						<br>
						<input type="text" name="headline" size="67" readonly="readonly" value="${rate.headline}">
						<br>
						<br>
						<br>
						<textarea name="ratingDetail" rows="10" cols="70" readonly="readonly">${rate.ratingDetail}</textarea>
					</td>		
				</tr>
			</table>
		</form>
		</div>
	</div>
	
	<br><br><br><br>
	<jsp:directive.include file="footer.jsp"/>
</body>
<script type="text/javascript">
	
	$(document).ready(function(){
		$("#rateYo").rateYo({
		    starWidth: "40px",
		    fullStar: true,
		    ratedFill: "#000000",
		    rating: ${rate.ratingStars},
		    readOnly: true,
		});
	});
</script>
</html>