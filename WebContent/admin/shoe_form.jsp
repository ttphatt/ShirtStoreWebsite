<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=yes">
	<title>
		<c:if test="${shoe != null}">
			Edit Shoes
		</c:if>
			
		<c:if test="${shoe == null}">
			Create New Shoes
		</c:if>
	
	</title>
	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
	<link rel="stylesheet" href="..//css/richtext.min.css">
	
	
	
	<script type="text/javascript" src="../js/jquery-3.7.1.min.js"></script>
	<script type="text/javascript" src="..//js/jquery.richtext.min.js"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<br><br><br><br>
	
	<div align="center">
		<h1>
			<c:if test="${shoe != null}">
				Edit Shoes
			</c:if>
			
			<c:if test="${shoe == null}">
				Create New Shoes
			</c:if>
		</h1>
	</div>
	
	<br>
	<br>	
		
	<div align="center">
		<c:if test="${shoe != null}">
			<form action="update_shoe" method="post" id="shoeForm" enctype="multipart/form-data">
			<input type="hidden" name="shoeId" value="${shoe.shoeId}"/>
			
		</c:if>
		
		<c:if test="${shoe == null}">
			<form action="create_shoe" method="post" id="shoeForm" enctype="multipart/form-data">
			
		</c:if>
		
		<table cellpadding="10px">
			<tr>
				<td align="left">Type</td>
				<td align="left">
					<select name="type" class="btn btn-outline-secondary dropdown-toggle dropdown-toggle-split">
						<c:forEach var="type" items="${listType}">
							<c:if test="${type.typeId eq shoe.type.typeId}">
								<option value="${type.typeId}" selected>
							</c:if>
							
							<c:if test="${type.typeId ne shoe.type.typeId}">
								<option value="${type.typeId}">
							</c:if>
								${type.typeName}
						</c:forEach>
					</select>
				</td>
			</tr>
		
		
			<tr>
				<td align="left">Name of the shoes</td>
				<td align="left"><input type="text" id="shoeName" name="shoeName" size="20" value="${shoe.shoeName}" required="required" class="form-control"/></td>
			</tr>
			
			<tr>
				<td align="left">Brand</td>
				<td align="left"><input type="text" id="brand" name="brand" size="20" value="${shoe.brand}" required="required" class="form-control"/></td>
			</tr>
			
			<tr>
				<td align="left">Description</td>
				<td align="left">
					<textarea rows="5" cols="50" name="description" id="description" required="required">${shoe.description}</textarea>
				</td>
			</tr>
			
			<tr>
				<td align="left">Shoes' image</td>
				<td align="left">
					<c:if test="${shoe == null}">
						<input type="file" id="shoeImage" name="shoeImage" size="20" required="required" class="form-control"/><br>
					</c:if>
					
					<c:if test="${shoe != null}">
						<input type="file" id="shoeImage" name="shoeImage" size="20" class="form-control"/><br>
					</c:if>
					
					<img alt="Image Preview" id="thumbnail" style="margin-top: 10px; max-height: 300px; max-width: 300px" src="data:image/jpg;base64,${shoe.base64Image}"/>	
				</td>
			</tr>
			
			<tr>
				<td align="left">Price</td>
				<td align="left"><input type="number" step="0.01" min="0.01" id="shoePrice" name="shoePrice" size="20" value="${shoe.shoePrice}" required="required" class="form-control"/></td>
			</tr>
			
			<tr>
				<td align="left">Released Date</td>
				<td align="left"><input type="date" id="releasedDate" name="releasedDate" size="20" class="form-control" required="required" 
				value="<f:formatDate pattern='yyyy-MM-dd' value='${shoe.releasedDate}'/>"/></td>
			</tr>
					
			
			<tr><td>&nbsp;</td></tr>
				
			<tr>
				<td colspan="2" align="center">
					<button type = "submit" class="btn btn-outline-success">Save</button>
					<button type="button" class="btn btn-outline-info" onclick="history.go(-1);">Cancel</button>
				</td>
			</tr>
		</table>
	</div>
	
	
	<br><br><br><br>
	<jsp:directive.include file="footer.jsp"/>
</body>
<script type="text/javascript">
	$(document).ready(function(){	
		$("#description").richText();
		
		$("#buttonCancel").click(function(){
			history.go(-1);
		});
		
 		$("#shoeImage").change(function(){
			showImageThumbnail(this);	
		}); 
	});
	
	function showImageThumbnail(fileInput){
		var file = fileInput.files[0];
		
		var reader = new FileReader();
		
		reader.onload = function(e){
			$("#thumbnail").attr("src", e.target.result);
		};
		
		reader.readAsDataURL(file);
	}; 
	
</script>
</html>