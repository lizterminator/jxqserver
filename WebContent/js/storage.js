var Storage = (function(){
	
	/*if(sessionStorage){
		return sessionStorage;
	}else{*/
		return{
			getItem: function(name){
				var i, c, ca, nameEQ = name + "=";
				ca = document.cookie.split(';');
				for(i=0;i < ca.length;i++) {
					c = ca[i];
					while (c.charAt(0)==' ') {
						c = c.substring(1,c.length);
					}
					if (c.indexOf(nameEQ) == 0) {
						return c.substring(nameEQ.length,c.length);
					}
				}
				return '';
			},
			setItem: function(name,value,days){
				var date, expires;
				
				if (days) {
					date = new Date();
					date.setTime(date.getTime()+(days*24*60*60*1000));
					expires = "; expires=" + date.toGMTString();
				} else{
					expires = "";
				}
				document.cookie = name + "=" + value + expires + "; path=/";
			}
		}
	//}
})();