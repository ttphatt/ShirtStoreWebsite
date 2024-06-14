<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=yes">
	<title>
		<c:if test="${type != null}">
			Type Editing
		</c:if>
				
		<c:if test="${type == null}">
			Create a new type
		</c:if>
	</title>

	<script type="text/javascript" src="../js/jquery-3.7.1.min.js"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<br><br><br><br>
	
	<div align="center">
		<h1>
			<c:if test="${type != null}">
				Edit Type
			</c:if>
			
			<c:if test="${type == null}">
				Create a New Type
			</c:if>
		</h1>
	</div>
	
	<br>
	
	<div align="center">
		<c:if test="${type != null}">
			<form action="update_type" method="post" id="listForm">
			<input type="hidden" name="typeId" value="${type.typeId}">
			
		</c:if>
		
		<c:if test="${type == null}">
			<form action="create_type" method="post" id="listForm">
			
		</c:if>
		
		<table>
			<tr>
				<td align="left">Type's name:&nbsp;</td>
				<td align="right"><input type="text" id="typeName" name="typeName" size="20" value="${type.typeName}" required="required" class="form-control"/></td>
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
</html>