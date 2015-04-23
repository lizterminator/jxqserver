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
	/*************************************************/

	/******Selectors,用户注册时候获取用户名密码等信息 register***/
	var userRegisterSlcts = [
		"#phone",
		"#password"
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
	return{
		
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

		logout: function(){
			storage.clear();
		},

		register: function(){
			var params = this.getParams(userRegisterSlcts);

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
						
						that.goLogin();
					}else{
						console.log(data);
						//alert(data.message);
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
			var params = this.getParams(searchSlcts);
			$.post(
				searchUrl,
				params,
				searchCallback,
				"json"
			);

			function searchCallback(data, textStatus, xhr){
				//修改用户信息请求返回逻辑
				
				console.log(data);
				
				function itemTemplate(config){
					return "<div class='row'>" +
								"<div class='col-md-7'>" +
								"<a href='portfolio-item.html'>" +
									"<img class='img-responsive img-hover' src='images/700x300.gif' alt=''>" +
								"</a>" +
							"</div>" +
							"<div class='col-md-5 shortcuts'>" +
								"<h3>"+config.name +"</h3>" +
								"<img src='images/rmb_21.png'/>" +
								"<span class='originPrice'>￥" +config.price+
								"</span>" +
								"<p>" + config.info+
								"</p>" +
								"<a class='jy_btn' href='item-detail.html?name=xx'>去抢购</a>" +
								"<span class='orderDetial'><span class='countOfBuyers'>9</span>人已成交</span>" +
							"</div>" +
						"</div>" +
						"<hr>";
				}
				$("#main").empty();
				for(var i=0;i<data.schools.length;i++){
					$("#main").append(itemTemplate(data.schools[i]));
				}
				
				
			}
		}

	}

})();



$(function(){
	
	console.log(location);
	var user;
	var noCheckLoginPath = ['index','login','detail','pay']; 
	var checkPath;
	if(location.pathname.indexOf("login") == -1 || location.pathname.indexOf("index") == -1){
		user = User.checkLogin();
	}
	console.log(user);
	if(user){
		var p = $("#headerLeft");
		var children = p.children();
		for(var i = 1,l=children.length;i<l;i++){
			children[i].remove();
		}
		var sname = user.name||user.phone;
		p.append("<a href='personal.html'>[欢迎你 "+ sname + "]</a>");
		p.append("<a href='index.html' id='logout'>[登出]</a>");
	}else{
		//alert("请先登陆");
	}
	
	$("#logout").click(function(){
		User.logout();
	});
	
	$(".search_btn").click(function(){
		User.search();
	});
	
});
