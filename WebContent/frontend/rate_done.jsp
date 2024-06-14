<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=yes">
	<title>Done rating</title>
	
	<script type="text/javascript" src="js/jquery-3.7.1.min.js"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<br><br><br><br>

	<div class="container">
			<div class="row justify-content-center">
			<table cellpadding="10px" width="70%">
				<tr>
					<td><h2>Share us your thoughts, ${loggedCustomer.fullName}</h2></td>
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
						<h3>Your rate has been posted. Thank you</h3>
					</td>		
				</tr>
			</table>
			</div>
	</div>
	
	<br><br><br><br>
	<jsp:directive.include file="footer.jsp"/>
</body>
</html>