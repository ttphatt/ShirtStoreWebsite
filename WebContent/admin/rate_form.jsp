<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=yes">
	<title>Edit Rate</title>
	<script type="text/javascript" src="../js/jquery-3.7.1.min.js"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<br><br><br><br>
	
	<div align="center">
		<h1>Edit Rate</h1>
	</div>
		
	<br>
		
	<div align="center">
		<form action="update_rate" method="post" id="rateForm">
		<input type="hidden" name="rateId" value="${rate.rateId}">
		<table cellpadding="10px">
			<tr>
				<td align="right">Shoes' name:</td>
				<td align="left"><b>${rate.shoe.shoeName}</b></td>
			</tr>
			
			<tr>
				<td align="right">Rating Stars:</td>
				<td align="left"><b>${rate.ratingStars}</b></td>
			</tr>
			
			<tr>
				<td align="right">Customer's full name:</td>
				<td align="left"><b>${rate.customer.fullName}</b></td>
			</tr>
			
			<tr>
				<td align="right">Rate's headline:</td>
				<td align="left">
					<input type="text" size="60" name="headline" value="${rate.headline}" required="required" minlength="5" maxlength="50" class="form-control">
				</td>
			</tr>
			
			<tr>
				<td align="right">Rate detail:</td>
				<td align="left">
					<textarea rows="5" cols="70" name="ratingDetail" required="required" minlength="1" maxlength="50">${rate.ratingDetail}</textarea>
				
				</td>
			</tr>
			
			
			
			
			<tr><td>&nbsp;</td></tr>
				
			<tr>
				<td colspan="2" align="center">
					<button type = "submit" class="btn btn-outline-success">Save</button>
					<button type="button" class="btn btn-outline-info" onclick="history.go(-1);">Cancel</button>
				</td>
			</tr>
		</table>
		</form>
	</div>
	
	
	<br><br><br><br>
	<jsp:directive.include file="footer.jsp"/>
</body>
<script type="text/javascript">
	$(document).ready(function(){
		$("#buttonCancel").click(function(){
			history.go(-1);
		});
	});
</script>
</html>