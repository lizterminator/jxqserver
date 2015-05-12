(function(){
	var storage = sessionStorage;
	var user = JSON.parse(storage.getItem('user'));
	
	
	if(!user){
		location.href="login.html";
		return;
	}
	
	$.ajax({
		url:'getOrdersByUser.cu',
		method:"POST",
		dataType: 'json',
		data:{
			phone:user.phone,
			password:user.password
		},
		success:successCallback,
		error: errorCallback
	});
	
	function errorCallback(data){
		console.log(data);
	}
	function successCallback(data){
		if(data.success){
			var orders = data.orders;
			$("#orderList").empty();
			for(var i in orders){
				$("#orderList").append(itemTemplate(orders[i]));
			}
			
			$(".order_delete").click(function(e){
				var a=confirm("确定要删除订单吗？");
				 if(a==true){
					e.preventDefault();
					deleteOrder(this.id.split('_')[1]);
				 }
			
			});
			
		}else{
			
		}
	}
	
	function deleteOrder(orderNumber){
		$.ajax({
			url:'deleteOrder.cu',
			method:"POST",
			dataType: 'json',
			data:{
				phone:user.phone,
				password:user.password,
				orderNumber:orderNumber
			},
			success:function(data){
				alert(data.msg);
				location.reload();
//				console.log(data);
			},
			error: errorCallback
		});
	}
	
	function itemTemplate(order){
		
		var msg = order.checked?"已完成预约":"24小时内会有专人联系您哦";
		return  "<div class='row' >" +
					"<div class='col-md-12' style='border:0px red solid;'>" +
						"<div class='panel panel-default'>" +
							"<div class='panel-heading' style='text-align:center;'>" +
								"<div class='row' >" +
									"<div class='col-lg-2 col-md-2 col-sm-2 col-xs-2' >" +
										"<h4 >预约号："+order.orderNumber +"</h4>" +
									"</div>" +
									"<div class='col-lg-2 col-md-2 col-sm-2 col-xs-2' >" +
									"<h4 >下单时间</h4>" +
									"</div>" +
									"<div class='col-lg-1 col-md-1 col-sm-1 col-xs-1' >" +
										"<h4 >本地/异地</h4>" +
									"</div>" +
									"<div class='col-lg-1 col-md-1 col-sm-1 col-xs-1' >" +
										"<h4 >合计(元)</h4>" +
									"</div>" +
									"<div class='col-lg-1 col-md-1 col-sm-1 col-xs-1' >" +
									"<h4 >预约人</h4>" +
									"</div>" +
									"<div class='col-lg-4 col-md-4 col-sm-4 col-xs-4' >" +
									"<h4 >预约状态</h4>" +
									"</div>" +
								"</div>" +
							"</div>" +
							"<div class='panel-body' class='dingdancontent' style='text-align:center;>" +
								"<div class='row' >" +
									
									"<div class='col-lg-2 col-md-2 col-sm-2 col-xs-2' style='height:120px;padding-top:50px;'>" +
										"<h3 >"+order.schoolName+"</h3>" +
									"</div>" +
									"<div class='col-lg-2 col-md-2 col-sm-2 col-xs-2' style='height:120px;padding-top:50px;'>" +
									"<h4>"+order.orderDate+"</h4>" +
									"</div>" +
									"<div class='col-lg-1 col-md-1 col-sm-1 col-xs-1' style='height:120px;padding-top:50px;'>" +
										"<h3 >"+(order.local=='1'?"本地":"异地")+"</h3>" +
									"</div>" +
									"<div class='col-lg-1 col-md-1 col-sm-1 col-xs-1' style='height:120px;padding-top:50px;'>" +
										"<h3 >"+order.sum+"</h3>" +
									"</div>" +
									"<div class='col-lg-1 col-md-1 col-sm-1 col-xs-1' style='height:120px;padding-top:50px;'>" +
									"<h3 >"+order.contact+"</h3>" +
									"</div>" +
									"<div class='col-lg-3 col-md-3 col-sm-3 col-xs-3' style='height:120px;padding-top:50px;'>" +
										"<h3 >"+msg+"</h3>" +
									"</div>" +
									/*"<div class='col-md-2' style='border:0px green solid;height:120px;padding-top:50px;'>" +
										"<h3 id='dingdan_jiaoyi'>交易未完成</h3>" +
									"</div>" +*/
									"<div class='col-lg-2 col-md-2 col-sm-2 col-xs-2' style='border:0px green solid;height:120px;padding-top:50px;'>" +
										"<span class='dingdan_delete'><a href='#' class='order_delete' id="+"delete_"+order.orderNumber+">删除</a></span>" +
									"</div>" +
								"</div>" +
								"<hr>" +
							"</div>" +
						"</div>" +
					"</div>" +
				"</div>";
	}
})();