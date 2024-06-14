<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add shoes to order</title>
</head>
<body>
	<div align="center">
		<h2>
			The shoes with id: ${shoe.shoeId} of the order with id: ${order.orderId}
		</h2>
		<input type="button" value="Close" onclick="javascript: self.close();" />
	</div>
</body>
<script type="text/javascript">
	window.onunload = function(){
		window.opener.location.reload();
	}
</script>
</html>