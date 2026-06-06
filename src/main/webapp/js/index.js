$(document).ready(function(){
    $.ajax({
    	url: "jobs",
    	success: function(result){
			var jobs = JSON.parse(result);
			var htmlContent = "";
			for(var i=0;i<jobs.length; i++){
			htmlContent+="<div class=\"job-details\">" + "			<div class=\"company-name\"> "
						+ jobs[i].companyName + "</div>"
						+ "			<div><img src=\"img/Position.png\" class=\"icon\"> "
						+ jobs[i].position + "</div>"
						+ "			<div><img src=\"img/Location.png\" class=\"icon\"> "
						+ jobs[i].location + "</div>"
						+ "			<div><img src=\"img/Experience.png\" class=\"icon\"> "
						+ jobs[i].experience + "</div>"
						+ "			<div><img src=\"img/Skills.png\" class=\"icon\"> " + jobs[i].skills
						+ "</div>" + "		</div>";
			}
      		$(".jobs").html(htmlContent);
    	}
    });
});