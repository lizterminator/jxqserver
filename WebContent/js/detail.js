(function(){
	
	$('#buy').click(function(){
		location.href = "pay.html";
	});
	
	var storage = sessionStorage;
	var school = storage.getItem('school');
	
	
	var schoolDetile = storage.getItem('school'+school)
	if(schoolDetile){
		callback(schoolDetile);
		return;
	}
	
	$.ajax({
		url:'getDetile.ds',
		method:'post',
		data:{
			schoolId:school
		},
		success: function(data){
			storage.setItem('school'+school,data);
			callback(data);
		}
	})
	
	function callback(data){
		var school = JSON.parse(data);
		$('#bc_sname').text(school.name);
		$('#s_name').text(school.name);
		
		$('#sold').text(school.sold);
		$('#life').text(school.life);
	}
	
	
})();