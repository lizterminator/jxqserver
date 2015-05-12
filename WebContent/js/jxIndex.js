
$('.shopCar').click(function(e){
	location.href = "pay.html";
});
$(function(){
	var storage = sessionStorage;
	var school_id = storage.getItem("school");
	
	var school = storage.getItem("school"+school_id);
	
	if(school){
		callback(school);
		return;
	}
	$.ajax({
		url:'getDetile.ds',
		method:'post',
		data:{
			schoolId:school_id
		},
		success: function(data){
			storage.setItem('school'+school_id,data);
			callback(data);
		}
	})
	
	function callback(data){
		var school = JSON.parse(data);
		$('#pic_2').prop("src",school.pic2);
		$('#pic_3').prop("src",school.pic3);
		$('#pic_4').prop("src",school.pic4);
		
		$('#brief_pic').prop("src",school.pic2);
		$('#shizililiang_pic').prop("src",school.pic3);
		$('#location').prop("src",school.location);
		
		$('#brief').text(school.jianjie);
		$('#shizililiang').text(school.shizililiang);
		$('#fuwuxuexiao').text(school.fuwuxuexiao);
	}
	
});