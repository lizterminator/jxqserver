(function(){
	var user = JSON.parse(sessionStorage.getItem('user'));
	
	if(user){
		$('#nickname').val(user.nickname|| '');
		$('#realname').val(user.name|| '');
		
		if(user.sex ==="female"){
			$('#female').prop("checked", true);
		}else if(user.sex ==="male"){
			$('#male').prop("checked", true); 
		}
		
		$('#schoolname').val(user.school || '');
	}
	
	$('#savePersonalInfo').click(function(){
		
		if(!user){
			alert("请先登陆哦！");
			location.href = "login.html";
			return;
		}
		var nickname = $('#nickname').val();
		var realname = $('#realname').val();
		
		var namePattern = /^([\u4E00-\uFA29]|[\uE7C7-\uE7F3])+$/;
		if(!namePattern.test(realname)){
			alert('请填写正确的姓名！');
			return;
		}
		var sex = $('input[type="radio"][name="sexOptions"]:checked').val(); // 获取一组radio被选中项的值   
		//alert(sex);
		var school = $('#school').val();
		
		$.ajax({
			url: 'edit.cu',
			method: 'POST',
			dataType: "json",
			data: {
				nickname:nickname,
				name: realname,
				sex: sex,
				school: school,
				userPhone: user.phone,
				password: user.password
			},
			success:successcallback,
			error:errorcallback
		});
		
		function errorcallback(data){
			alert('出错,请联系管理员');
		}
		function successcallback(data){
			if(data.success){
				alert(data.msg);
				sessionStorage.setItem('user',JSON.stringify(data));
			}else{
				alert(data.msg);
				
			}
		}
		//alert(school);
	});
	
	
	
	
})();