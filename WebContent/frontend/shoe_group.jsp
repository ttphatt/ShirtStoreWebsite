<div class="col text-center">
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