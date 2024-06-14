$(document).ready(function(){
		$("#customerForm").validate({
			rules: {
				phone:{
					number: true,	
				},
				
				confirmPassword:{
					equalTo: "#password",
				},
			},
			
			messages: {
				phone:{
					number: "Phone must contain only number",	
				},
				
				confirmPassword:{
					equalTo: "The password does not match with each other",
				},
			}
		});
		
		
		$("#buttonCancel").click(function(){
			history.go(-1);
		});
	});