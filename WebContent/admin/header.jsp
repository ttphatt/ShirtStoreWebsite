<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div align="left">
	<table>
		<tr>
			<td>
				<a href="${pageContext.request.contextPath}/admin/">
					<img src="../images/Privacy_private_security-512.png" style="max-height: 100px; max-width: 100px;">
				</a>
			</td>
			
			<td>
				<h1><a href="${pageContext.request.contextPath}/admin/" class="text-dark" style="text-decoration: none">PHK Store Administration</a></h1>
			</td>			
		</tr>
	</table>
</div>

<div align="right">
	<table>
		<tr>
			<td>
				<h3 align="right">Hi there, ${sessionScope.userFullName}&nbsp;&nbsp;</h3>
			</td>
			
			<td>
				<a href="logout" class="btn btn-outline-dark" style="text-align: right;">Logout</a>
			</td>
		</tr>	
	
	</table>
</div>	

<div class="container">
	<br><br>
		
	<div class="row justify-content-center text-center">
		<div class="col">
			<a href = "list_users" class="btn btn-light">
				<img src="../images/profile.png" id = "logoSize" style="max-width: 70px; max-height: 70px"><br>Users
			</a>
		</div>
		
		<div class="col">
			<a href = "list_type" class="btn btn-light">
				<img src="../images/app.png" id = "logoSize" style="max-width: 70px; max-height: 70px"><br>Type
			</a>
		</div>
		
		<div class="col">
			<a href = "list_shoes" class="btn btn-light">
				<img src="../images/sneakers.png" id = "logoSize" style="max-width: 70px; max-height: 70px"><br>Shoes
			</a>
		</div>
		
		<div class="col">
			<a href = "list_customer" class="btn btn-light">
				<img src="../images/service.png" id = "logoSize" style="max-width: 70px; max-height: 70px"><br>Customer
			</a>
		</div>
		
		<div class="col">
			<a href = "list_rate" class="btn btn-light">
				<img src="../images/Rate.png" id = "logoSize" style="max-width: 70px; max-height: 70px"><br>Rate
			</a>
		</div>
		
		<div class="col">
			<a href = "list_order" class="btn btn-light">
				<img src="../images/shopping-cart.png" id = "logoSize" style="max-width: 70px; max-height: 70px"><br>Order
			</a>
		</div>
	
	</div>		
</div>