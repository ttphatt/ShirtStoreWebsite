<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=yes">
	<title>Shoe Store Administration</title>

	<script type="text/javascript" src="js/jquery-3.7.1.min.js"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
	
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<br><br><br><br>

<div class="container">
	<div class="row justify-content-center text-center">
		<h1>Quick Actions:</h1>
	</div>	
	
	<br>
	
	<div class="row justify-content-center text-center">
			<a href="user_form.jsp" class="btn btn-outline-info">Add a new user</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="new_customer" class="btn btn-outline-secondary">Add a new customer</a>&nbsp;&nbsp;&nbsp;&nbsp;

			<a href="new_shoe" class="btn btn-outline-info">Add new shoes</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="type_form.jsp" class="btn btn-outline-secondary">Add a new type</a>
		<hr width="70%">
	</div>
	
	<br>
	<br>
	<br>
	<br>
	
	<!-------------------------------------Charts Section------------------------------------------->
	<div class="row justify-content-center text-center">
		<h2>General Bar Chart</h2>
	</div>
			
	<div class="row justify-content-center text-center">
		<hr width="70%">
	</div>
	
	<div class="row justify-content-center">
		<canvas id="myChart" style="width:100%;max-width:700px"></canvas>
			
		<script>
			function getRandomColor(){
				var letters = "0123456789ABCDEF";
				var color = '#';
				for(var i = 0; i < 6; i++){
					color += letters[Math.floor(Math.random() * 16)];
				}
				return color;
			}
		
			const xValues = ["User", "Shoe", "Type", "Customer", "Rate", "Order"];
			const yValues = ["${totalUsers}", "${totalShoes}", "${totalTypes}", "${totalCustomers}", "${totalRates}", "${totalOrders}", 0];
			const barColors = [];
			
			for(var i = 0; i < 6; i++){
				barColors.push(getRandomColor());
			}
			
			new Chart("myChart", {
			  type: "bar",
			  data: {
			    labels: xValues,
			    datasets: [{
			      backgroundColor: barColors,
			      data: yValues
			    }]
			  },
			  options: {
			    legend: {display: false},
			    title: {
			      display: true,
			      text: "Overall Figures"
			    }
			  }
			});
		</script>
	</div>	
	
	<br>
	<br>
	<br>
	<br>
	
	<div class="row">
		<div class="col">
			<div class="row justify-content-center">
				<h2>Types of Shoes Pie Chart</h2>
			</div>
			
			<div class="row justify-content-center">
				<script>
					const xAxis = [];
					const yAxis = [];
					const pieColor = [];
				</script>
				
				<c:forEach items="${typeNames}" var="temp">
					<script>
						xAxis.push("${temp}");
					</script>
				</c:forEach>
				
				<c:forEach items="${shoesByTypes}" var="temp">
					<script>
						yAxis.push("${temp}");
						pieColor.push(getRandomColor());
					</script>
				</c:forEach>
				
				<canvas id="myChart1" style="width:100%;max-width:700px"></canvas>
				<script>					
					new Chart("myChart1", {
					  type: "pie",
					  data: {
					    labels: xAxis,
					    datasets: [{
					      backgroundColor: pieColor,
					      data: yAxis
					    }]
					  },
					  options: {
					    title: {
					      display: true,
					      text: "Number of Shoes per Type"
					    }
					  }
					});
				</script>		
			</div>
		</div>
		
		<div class="col">
			<div class="row justify-content-center">
				<h2>Average Rating Stars Pie Chart</h2>
			</div>
			
			<div class="row justify-content-center">
				<script>
					const horizontalAxis = [];
					const verticalAxis = [];
					const colors = [];
				</script>
				
				<c:forEach items="${listRatingStars}" var="temp">
					<script>
						horizontalAxis.push("${temp}" + " stars");
						colors.push(getRandomColor());
						</script>
				</c:forEach>
				
				<c:forEach items="${countRatingStars}" var="temp">
					<script>
						verticalAxis.push("${temp}");
					</script>
				</c:forEach>
				
				<canvas id="myChart2" style="width:100%;max-width:700px"></canvas>
				<script>					
					new Chart("myChart2", {
					  type: "pie",
					  data: {
					    labels: horizontalAxis,
					    datasets: [{
					      backgroundColor: colors,
					      data: verticalAxis
					    }]
					  },
					  options: {
					    title: {
					      display: true,
					      text: "Number of Shoes per Type"
					    }
					  }
					});
				</script>	
			</div>
		</div>
	</div>
	
	<br>
	<br>
	<br>
	<br>
	
	
		<div class="row justify-content-center">
			<h2>Selling Revenue Pie Chart</h2>
		</div>
		
		<div class="row justify-content-center">
				<script>
					const label = [];
					const percentages = [];
					const percentagesColor = [];
				</script>
				
				<c:forEach items="${soldShoeName}" var="temp">
					<script>
						label.push("${temp}");
						percentagesColor.push(getRandomColor());
					</script>
				</c:forEach>
				
				<c:forEach items="${eachShoeRevenue}" var="temp">
					<script>
						percentages.push("${temp}");
					</script>
				</c:forEach>
				
				<canvas id="myChart3" style="width:100%;max-width:700px"></canvas>
				<script>					
					new Chart("myChart3", {
					  type: "pie",
					  data: {
					    labels: label,
					    datasets: [{
					      backgroundColor: percentagesColor,
					      data: percentages,
					    }]
					  },
					  options: {
					    title: {
					      display: true,
					      text: "Revenue Of Each Pair Of Shoes"
					    }
					  }
					});
				</script>	
		</div>
	
	
	<!----------------------------------------------------------------------------------->
	<br>
	<br>
	<br>
	<br>
	
	<div class="row justify-content-center text-center">
		<h1>Administrative Dashboard</h1>
	</div>
	
	<div class="row justify-content-center text-center">
		<hr width="70%">
	</div>
	
	<br>
	<br>
	
	<div class="row justify-content-center text-center">
		<h2>Recent sales:</h2>
	</div>
		
	<div class="row justify-content-center text-center">
		<hr width="70%">
	</div>	
			
	<div class="row justify-content-center text-center">
		<table border="1" cellpadding="10" class="table" style="text-align: center">
			<thead class="thead-dark">
			<tr>
				<th>Order's ID</th>
				<th>Ordered By</th>
				<th>Number pairs of shoes</th>
				<th>Subtotal Price</th>
				<th>Payment method</th>
				<th>Status</th>
				<th>Order date</th>
			</tr>
			
			<c:forEach var="order" items="${listMostRecentSales}">
				<tr>
					<td><a href="view_order?orderId=${order.orderId}">${order.orderId}</a></td>
					<td>${order.customer.fullName}</td>
					<td>${order.pairOfShoes}</td>
					<td><fmt:formatNumber type="currency" value="${order.orderSum}"/></td>
					<td>${order.payment}</td>
					<td>${order.status}</td>
					<td>${order.orderDate}</td>
				</tr>
			</c:forEach>
			</thead>
		</table>
	</div>
	
	<br>
	<br>
	<br>
	<br>
	
	<div class="row justify-content-center text-center">
		<h2>Recent rates:</h2>
	</div>
	
	<div class="row justify-content-center text-center">
		<hr width="70%">
	</div>
		
	<div class="row justify-content-center text-center">
		<table border="1" cellpadding="15" class="table" style="text-align: center">
			<thead class="thead-dark">
			<tr>
				<th>Shoe</th>
				<th>Rating</th>
				<th>Headline</th>
				<th>Customer</th>
				<th>Rate on</th>
			</tr>
			
			<c:forEach items="${listMostRecentRates}" var="rate">
				<tr>
					<td>${rate.shoe.shoeName}</td>
					<td>${rate.ratingStars}</td>
					<td><a href="edit_rate?id=${rate.rateId}">${rate.headline}</a></td>
					<td>${rate.customer.fullName}</td>
					<td>${rate.rateTime}</td>
				</tr>
			</c:forEach>
			</thead>
		</table>
	</div>
</div>	
	<br><br><br><br>
	<jsp:directive.include file="footer.jsp"/>
</body>
</html>