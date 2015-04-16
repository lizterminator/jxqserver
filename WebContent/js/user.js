/**
 * 定义了对于普通用户的一些操作
 * 
 */
var User = (function () {

	//存储在Storage中的User的Key
	var storageKeyName = "user";

	/******Selectors,用户登录时候获取用户名密码等信息 login***/
	var userLoginSlcts = [
		"#userName",
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

	var storage = localStorage || "请换更先进的浏览器哦！推荐Chrome";


	/***************urls********************/
	var loginUrl =  "login.do";
	var registerUrl = "userRegister.cu";
	var buyUrl = ""; 
	var shoppingCarUrl = "";
	var orderListUrl = "";
	var searchUrl = "";
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
				//
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

			function loginCallback(data){
				//返回登录判断逻辑
				if(data.sucess){//成功
					//TODO: 
					//将数据放入storage,跳转到主页,主页中检验storage中的值是否存在
					//如果存在,则显示已经登录，若不存在,重新登录
				}else{//失败

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

			function registerCallback(data, textStatus, xhr){
				//注册请求返回逻辑
				console.log(data);
				console.log(textStatus);
				if(data.success){
					
				}else{

				}
				
			}

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
		search: function(keyword){

		}

	}

})();