var tuarr = new Array("xl.png", "px.png","xx.png");
var index = 0;
var ydarr = new Array("xlyd", "pxyd", "xxyd");
function next() 
{
	index++;
	if(index < tuarr.length)
	{
		document.jiaxiaojihe.src = "images/"+tuarr[index];
		if(index ==0)
		{
			document.xlyd.style.opacity = 0.4;
			document.pxyd.style.opacity = 1;
			document.xxyd.style.opacity = 1;
		}else if (index ==1 )
		{
			document.pxyd.style.opacity = 0.4;
			document.xlyd.style.opacity = 1;
			document.xxyd.style.opacity = 1;
		}
		else
		{
			document.xxyd.style.opacity = 0.4;	
			document.xlyd.style.opacity = 1;
			document.pxyd.style.opacity = 1;
		}
	}else
	{
		index = 0;
		document.jiaxiaojihe.src = "images/"+tuarr[index];
		document.xlyd.style.opacity = 0.4;
			document.pxyd.style.opacity = 1;
			document.xxyd.style.opacity = 1;
	}
}
window.onload = setInterval(next,3000);	

function xl()
{
	document.jiaxiaojihe.src = "images/"+tuarr[0];	
	document.xlyd.style.opacity = 0.4;
			document.pxyd.style.opacity = 1;
			document.xxyd.style.opacity = 1;
			index =0;
}
function px()
{
	document.jiaxiaojihe.src = "images/"+tuarr[1];	
	document.pxyd.style.opacity = 0.4;
			document.xlyd.style.opacity = 1;
			document.xxyd.style.opacity = 1;
			index = 1;
}
function xx()
{
	document.jiaxiaojihe.src = "images/"+tuarr[2];
	document.xxyd.style.opacity = 0.4;	
			document.xlyd.style.opacity = 1;
			document.pxyd.style.opacity = 1;
			index =2;
}
function xzurl()
{
	if (index == 0)
	{
		//alert("翔龙");
		window.open("http://www.baidu.com/","xianglong");
	}else if(index ==1)
	{
		window.open(pengxiang.html,"pengxiang");
		
	}else
	{
		window.open(xx.html,"xx");	
	}
}

function change(val) {
	var o = document.getElementById("newstag");
	var zn = document.getElementById("zhinan");
	
	if(val == 'newstag')
	{
		o.style.display="block";
		zn.style.display="none";
		
		
	}else
	{
		
		o.style.display = "none";
		zn.style.display = "block";
		
	}
}

function showdiv() {
	
	var d = document.getElementById("headkuang");
	d.style.display="block";	
	clearTimeout(st);
	
}
var st;
function zs() {
	
	var d = document.getElementById("headkuang");
	d.style.display="none";	
}
function hidediv() {
	var d = document.getElementById("headkuang");
	st = setTimeout("zs()",200);
	
}