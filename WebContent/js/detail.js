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
		console.log(school);
		$('#sold').text(school.sold);
		$('#life').text(school.life);
		$('#pos').prop("src",school.location);
		$('#pic2').prop("src",school.pic2);
		$('#pic3').prop("src",school.pic3);
		$('#pic4').prop("src",school.pic4);
	}
	
	
})();