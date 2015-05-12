(function(){
	var storage = sessionStorage;
	var school_id = storage.getItem("school");
	
	var school = JSON.parse(storage.getItem("school"+school_id));
	$('#jx_name').val(school.name);
	
	var user = JSON.parse(storage.getItem("user"));
	
	
	
	var namePattern = /^([\u4E00-\uFA29]|[\uE7C7-\uE7F3])+$/;
	var phonePattern = /^1[3|4|5|8][0-9]\d{8}$/;
	var numPattern = /\d/;
	$('#submitOrder').click(function(){
		if(!user){
			alert('请先登陆！');
			location.href = 'login.html';
			return;
		}
		
		
		var contact = $('#contact').val().trim();
		var contactPhone = $('#contactPhone').val().trim();
		
		var local = $('input:radio[name=local]:checked').val();
		//var not_local = $('#not_local').val().trim();
		console.log(local);
		
		if(!namePattern.test(contact)){
			alert('请填写正确的姓名！');
			return;
		}
		if(!phonePattern.test(contactPhone)){
			alert('请填写正确的手机号码');
			return;
		}
		/*if(!numPattern.test(local) || !numPattern.test(not_local)){
			
			alert('请填写正确的预约人数');
		}*/
		
		/*if(local==0&&not_local==0 || local>100 || not_local>100){
			alert('请正确填写预约人数！');
		}*/
		if(!local){
			alert("请选择本地或者异地!");
		}else{
			$(this).attr('disabled',"true");
			$.ajax({
				url: 'submitOrder.ds',
				method: 'POST',
				dataType: "json",
				data: {
					contact: contact,
					contactPhone: contactPhone,
					schoolId: school.id,
					schoolName: school.name,
					local: local,
					//notLocal: not_local,
					userPhone: user.phone,
					password: user.password
				},
				success:successcallback,
				error:errorcallback
			});
		}
	});
	function errorcallback(data){
		alert('提交订单出错,请联系管理员');
	}
	function successcallback(data){
		if(data.success){
			alert(data.msg);
			storage.setItem('orderNumber',data.orderNumber);
			//storage.setItem('school'+school_id,'');
			location.href = "orderList.html";
		}else{
			alert(data.msg);
			$('#submitOrder').attr('disabled',"false");
		}
	}
	
})();