
$(function(){
	
	var user = JSON.parse(sessionStorage.getItem("user"));
	if(user){
		var p = $("#moveAble");
//		var children = p.children();
//		children.empty();
		p.empty();
		var sname = user.name||user.phone;
		p.append("<a href='personal.html'>[欢迎你 "+ sname + "]</a>");
		p.append("<a href='index.html' id='logout'>[登出]</a>");
	}else{
	}
	
	
	var jx = JSON.parse(sessionStorage.getItem("jx"));
	if(jx){
		var p = $("#moveAble");
//		var children = p.children();
//		children.empty();
		p.empty();
		var sname = jx.name||jx.mobile;
		p.append("<a href='personal.html'>[欢迎你 "+ sname + "]</a>&nbsp&nbsp");
		p.append("<a href='studentManagement.html'>[学员管理]</a>&nbsp&nbsp");
		p.append("<a href='index.html' id='logout'>[登出]</a>");
	}else{
	}
	
	$("#logout").click(function(){
		User.logout();
	});
	
	$(".search_btn").click(function(){
		User.search();
	});
	
});


/**
 * 定义了对于普通用户的一些操作
 * 
 */
var User = (function () {
	
	var storage = sessionStorage || "请换更先进的浏览器哦！推荐Chrome";
	//存储在Storage中的User的Key
	var storageKeyName = "user";

	/******Selectors,用户登录时候获取用户名密码等信息 login***/
	var userLoginSlcts = [
		"#phone",
		"#password"
	];
	
	var jxLoginSlcts = [
  		"#mobile",
  		"#password"
  	];
	/*************************************************/

	/******Selectors,用户注册时候获取用户名密码等信息 register***/
	var userRegisterSlcts = [
		"#phone",
		"#password",
		'#passwordAgain'
		//"#email",
		//"#school"
	];
	/*************************************************/

	/******Selectors,用户用户购买时的一些信息 order***/
	var orderSlcts = [
		"#school",
		"#name",
		"#phone",
		"#paymethod",
		"#jx_name",
		"#local",
		"#not_local"
	];
	
	/*************************************************/

	var  searchSlcts = ["#keyword"];


	/***************urls********************/
	var loginUrl =  "userLogin.cu";
	var registerUrl = "userRegister.cu";
	var buyUrl = ""; 
	var shoppingCarUrl = "";
	var orderListUrl = "";
	var searchUrl = "search.ds";
	var jx_loginUrl = "userLogin.ds";
	return{
		
		getUser:function(){
			return JSON.parse(storage.getItem(storageKeyName));
		},
		/**
		 * 检查用户是否登录过
		 * @param  {[type]} storage [description]
		 * @return {[type]}         [description]
		 */
		checkLogin: function(){
			var user;
			if(typeof storage === "String"){
				alert(storage);
				return false;
			}
			var userItem = storage.getItem(storageKeyName);
			if(!userItem){
				//TODO:用户没登录过,跳转到登录页面
				this.goLogin();
				return false;
			}else{
				user = JSON.parse(userItem);
				return user;
			}

		},
		
		/**
		 * 用户登录
		 * @return {[type]} [description]
		 */
		login: function(){
			
			var params = this.getParams(userLoginSlcts);
			
			var phone = params.phone;
			var phonePattern = /^1[3|4|5|8][0-9]\d{8}$/;
			if(!phonePattern.test(phone)){
				alert("请输入正确的手机号码");
				return;
			}
			
			$.post(
				loginUrl,
				params,
				loginCallback,
				"json"
			);
			var that = this;
			function loginCallback(data, textStatus, xhr){
				//返回登录判断逻辑
				if(textStatus === "success"){
					
					if(data.success){//登陆成功
						//TODO: 
						//将数据放入storage,跳转到主页,主页中检验storage中的值是否存在
						//如果存在,则显示已经登录，若不存在,重新登录
						storage.setItem(storageKeyName,JSON.stringify(data));
						//window.history.back();
						that.goIndex();
					}else{//登陆失败
						alert("登陆失败,"+data.message);
						//that.goRegister();
					}
				}else{
					alert("服务器连接失败！请联系管理员");
				}
				
			}
		},
		jx_login: function(){
			var params = this.getParams(jxLoginSlcts);
			
			var phone = params.mobile;
			
			
			var phonePattern = /^1[3|4|5|8][0-9]\d{8}$/;
			if(!phonePattern.test(phone)){
				alert("请输入正确的手机号码");
				return;
			}
			
			$.post(
				jx_loginUrl,
				params,
				loginCallback,
				"json"
			);
			var that = this;
			function loginCallback(data, textStatus, xhr){
				//返回登录判断逻辑
				if(textStatus === "success"){
					
					if(data.success){//登陆成功
						//TODO: 
						//将数据放入storage,跳转到主页,主页中检验storage中的值是否存在
						//如果存在,则显示已经登录，若不存在,重新登
						storage.setItem('jx',JSON.stringify(data));
						/*if(localtion.pathname.indexOf("pay"))
							window.history.back();
						else*/
						 location.href = 'studentManagement.html'
					}else{//登陆失败
						alert("登陆失败,"+data.message);
						//that.goRegister();
					}
				}else{
					alert("服务器连接失败！请联系管理员");
				}
				
			}
		},
		logout: function(){
			storage.clear();
		},

		register: function(){
			var params = this.getParams(userRegisterSlcts);
			
			var phone = params.phone;
			var password = params.password;
			var passwordAgain = params.passwordAgain;
			var phonePattern = /^1[3|4|5|8][0-9]\d{8}$/;
			if(!phonePattern.test(phone)){
				alert("请输入正确的手机号码");
				return;
			}
			if(password.length<6 || password.length>12){
				alert("请使用长度在6和12之间的密码哦！");
				return;
			}
			if(!(password === passwordAgain)){
				alert("两次密码输入不一致！");
				return;
			}
			$.post(
				registerUrl,
				params,
				registerCallback,
				"json"
			);
			var that = this;
			function registerCallback(data, textStatus, xhr){
				//注册请求返回逻辑
				
				
				if(textStatus === "success"){
					if(data.success){
						
						alert(data.msg);
						that.goLogin();
					}else{
						console.log(data);
						alert(data.msg);
						//that.goRegister();
					}
				}else{
					alert("服务器连接失败！请联系管理员");
				}
				
				
			}

		},
		
			
	
		goLogin: function(){
			location.href = "login.html";
		},
		goRegister: function(){
			location.href = "register.html";
		},
		
		goIndex: function(){
			location.href = "index.html";
		},
		edit: function(){
			var params = this.getParams(userEditSlcts);
			$.post(
				editUrl,
				params,
				editCallback,
				"json"
			);

			function editCallback(data, textStatus, xhr){
				//修改用户信息请求返回逻辑
				if(data.success){

				}else{

				}
			}
		},

		buy: function(){

			var user = this.checkLogin();

			var params = this.getParams(orderSlcts);

			params["userId"] = user.id;
			$.post(buyUrl, params, function(data, textStatus, xhr) {
				/*optional stuff to do after success */
			},"json");
		},

		getParams: function(selectors){
			var params = {};
			for(var i = 0, l = selectors.length; i < l; ++i){
				
				params[selectors[i].split("#")[1]] = $.trim($(selectors[i]).val());
			}

			return params;
		},
		/**
		 * 获取用户订单
		 * @return {[type]} [description]
		 */
		getOrderList: function(){
			var user = this.checkLogin();
			console.log(user);
			$.post(orderListUrl, {
				userId: user.id
			}, function(data, textStatus, xhr) {
				/*optional stuff to do after success */
			});
		},
		/**
		 * 获取用户购物车
		 * @return {[type]} [description]
		 */
		getShoppingCar: function(){
			var user = this.checkLogin();

			$.post(shoppingCarUrl, {
				userId: user.id
			}, function(data, textStatus, xhr) {
				/*optional stuff to do after success */
			});

		},

		/**
		 * 搜索,主页初始化时也调用
		 * @return {[type]} [description]
		 */
		search: function(){
			if(window.location.pathname.indexOf('index')==-1){
				var params = this.getParams(searchSlcts);
				storage.setItem('keyword',JSON.stringify(params));
				location.href='index.html';
				return;
			}
			var keyword = storage.getItem('keyword');
			var params;
			if(keyword){
				
				params = JSON.parse(keyword);
				
			}else{
				params = this.getParams(searchSlcts);
				//storage.setItem('keyword',params[0]);
			}
			$.post(
				searchUrl,
				params,
				searchCallback,
				"json"
			);
			
			function searchCallback(data, textStatus, xhr){
				//修改用户信息请求返回逻辑
				
				storage.setItem('keyword','');
				
				function itemTemplate(config){
					return "<div class='row'>" +
								"<div class='col-md-7'>" +
									"<a href='portfolio-item.html'>" +
										"<img class='img-responsive img-hover' src='images/700x300.gif' alt=''>" +
									"</a>" +
								"</div>" +
								"<div class='col-md-5 shortcuts'>" +
									"<h3>"+config.name +"<small>减免<span class='text-danger' style='font-size:23px'>" +
									config.discount+"</span>元/人  多报多免哦</small></h3>" +
	//								"<span>免费！</span>"+
	//								"<img src='images/rmb_21.png'/>" +
	//								"<span class='originPrice'>￥" +config.price+
									//"<span class='originPrice'> 减免 "+"<span class='text-primary'>" +config.discount+"</span>元/人  多报多免哦"+
									"</span><hr/>" +
									
									"<p>" + config.info+
									"</p>" +
									"<button id='jx_"+ config.id+"' class='jy_btn'>免费预约</button>" +
									"<span class='orderDetial'><span class='countOfBuyers'>"+config.sold+"</span>人已成交</span>" +
								"</div>" +
							"</div>" +
						"<hr><br>";
				}
				$("#main").empty();
				for(var i=0;i<data.schools.length;i++){
					$("#main").append(itemTemplate(data.schools[i]));
				}
				
				$('.jy_btn').click(function(){
					storage.setItem("school",this.id.split('_')[1]);
					location.href="item-detail.html";
				});
				
			}
		}
	}

})();



