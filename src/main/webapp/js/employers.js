$(document).ready(function(){
$("#jobForm").submit(function(){
	 var data={};
		data.companyName=$('input[name=company_name]').val();
		data.position=$('input[name=position]').val();
		data.location=$('input[name=location]').val();
		data.experience=$('input[name=	experience]').val();
		data.skills=$('input[name=skills]').val();
    	$.ajax({
  			type: "POST",
  			url: "jobs",
  			data: data,
  			success: function(resp){
				if(resp=="true"){
					$("#res").html("Job added successfuly.");
				}
	
			},
  		});
		return false;

	});
});