<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=yes">
	<title>Managing Users</title>

	<script type="text/javascript" src="../js/jquery-3.7.1.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<br><br><br><br>
	
	<div align="center">
		<h1>Users Management</h1>
		<hr width="70%">
		<h3><a href="user_form.jsp" class="btn btn-outline-primary btn-lg">Create new User</a></h3>
	</div>
	
	<c:if test="${message != null}">
		<div align="center" style="color: red;">
			<h4>${message}</h4>
		</div>
	</c:if>
		
	<div align="center">
		<table border="1" cellpadding="5" class="table" style="width: 1000px">
			<thead class="thead-dark">
			<tr>
				<th class="align-middle justify-content-center text-center">Index</th>
				<th class="align-middle justify-content-center text-center">ID</th>
				<th class="align-middle justify-content-center text-center">Email</th>
				<th class="align-middle justify-content-center text-center">Full Name</th>
				<th class="align-middle justify-content-center text-center">Actions</th>
			</tr>
			</thead>
			
			<tbody></tbody>
			<c:forEach var="user" items="${listUsers}" varStatus="status">
			<tr>
				<td class="align-middle justify-content-center text-center">${status.index + 1}</td>
				<td class="align-middle justify-content-center text-center">${user.userId}</td>
				<td class="align-middle justify-content-center text-center">${user.email}</td>
				<td class="align-middle justify-content-center text-center">${user.fullName}</td>
				<td class="align-middle justify-content-center text-center">
					<c:if test="${sessionScope.userEmail eq user.email}">
						<a href="edit_user?id=${user.userId}" class="btn btn-outline-primary">Edit</a>	&nbsp;
					</c:if>
					
					<c:if test="${sessionScope.userEmail ne user.email}">
						<a href="edit_user?id=${user.userId}" class="btn btn-outline-primary">Edit</a>	&nbsp;
						<a href="javascript:void(0)" class="deleteLink btn btn-outline-primary" id="${user.userId}">Delete</a> &nbsp;
					</c:if>
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
				userId = $(this).attr("id");
				if(confirm("Are you sure you want to delete the user with user's id: " + userId + " ?")){
					window.location = "delete_user?id=" + userId;
				}
			})
		});
	});
</script>

</html>