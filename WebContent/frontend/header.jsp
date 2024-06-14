<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
	<div align="left">
		<table>
			<tr>
				<td>
					<a href = "${pageContext.request.contextPath}">
						<img src="images/sport-shoes-icon-vector-id121242.png" style="max-height: 250px; max-width: 250px;" class="img-fluid">
					</a>
				</td>
				
				<td>
					<h1><a href = "${pageContext.request.contextPath}" class="text-dark" style="text-decoration: none">PHK Shoe Store</a></h1>
	
				</td>
								
				<td>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
				
				<td>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
							
				<td align="right">
					<div style="margin: 10px">
						<form action="search" method="get" style="margin: 10px">
							<c:if test="${loggedCustomer == null}">
								<a href="login" class="text-white badge badge-dark">Login</a> |
								<a href="register" class="text-white badge badge-dark">Sign up</a> |
							</c:if>
							
							<c:if test="${loggedCustomer != null}">
								<a href="view_profile" class="text-white badge badge-dark">Welcome, ${loggedCustomer.fullName}</a> |
								<a href="view_orders" class="text-white badge badge-dark">My orders</a> |
								<a href="logout" class="text-white badge badge-dark">Logout</a> |
							</c:if>
							
							<a href="view_cart" class="text-white badge badge-dark">Cart</a>
							<br><br>
							
							<div class="input-group input-group-sm mb-3 justify-content-center">
								<input type="text" name="keyword" size=50 class="form-control" placeholder="Search for keywords"/>
								<div class="input-group-append">
									<button type="submit" class="btn btn-dark">Search</button> 
								</div>
							</div>
						</form>
					</div>	
				</td>
			</tr>
		</table>
		
	</div>
</div>
<div>&nbsp;</div>

<div align="center">
	<table>
		<tr>
		<c:forEach var="type" items="${listType}">
			<th>
				<a href="view_type?id=${type.typeId}" class="text-white badge badge-danger bm-10">
					<font size="+2"><b><c:out value="${type.typeName}"/></b></font>
				</a>
				&nbsp;
				&nbsp;
				&nbsp;
			</th>
		</c:forEach>
		</tr>
	</table>
</div>
<br><br>
