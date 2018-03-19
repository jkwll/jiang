<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
		   <meta http-equiv="content-type" content="text/html; charset=UTF-8">
   			<!--声明文档兼容模式，表示使用IE浏览器的最新模式-->
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<!--设置视口的宽度(值为设备的理想宽度)，页面初始缩放值<理想宽度/可见宽度>-->
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
		<!-- 引入Bootstrap核心样式文件 -->
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<!-- 引入BootStrap核心js文件 -->
			<link href="css/choujiang.css" rel="stylesheet">
	<script src="js/jquery-1.8.3.js"  ></script>
		<script src="js/bootstrap.min.js"></script>
<meta charset="UTF-8">
	<title>酷音乐：祝君好运！！</title>
	<link href="css/choujiang.css" rel="stylesheet">
	

		<script >
		//定义一个数组变量存放几个数据，一个定时器，一个标识变量
		var data=['谢谢参与','买一送一'],
			timer=null,
			flag=0;
		//封装一个方法拿到 通过class拿到的标签 注意拿到的时候是个数组对象，记得最后[0]，取一下第一个对象
		function getByClass(clsName,parent){
			var oParent=parent?document.getElementById(parent):document,
				eles=[],
				elements=oParent.getElementsByTagName('*');

			for(var i=0;i<elements.length;i++){
				if(elements[i].className == clsName){
					eles.push(elements[i]);
				}
			}
			return eles;
		}
		//函数开始
		window.onload=function(){

			var oTitle=getByClass('title')[0],
				begin=getByClass('begin')[0],
				stop=getByClass('stop')[0];
			var random;//随机数
			//开始抽奖
			begin.onclick=fnplay;
			stop.onclick=fnstop;

			//键盘事件 针对的是整个document
			document.onkeyup=fnkey;
			function fnplay(){
			//var that=this;//这里指的是begin这个按钮 这里暂时不考虑这个。
			//每个开始之前关闭一下定时器，不然每次按click的时候容易加快速度，以至于整个浏览器容易奔溃  
				clearInterval(timer);
		setTimeout("check()",1000);
				//定义一个定时器
				timer=setInterval(function(){
					//var v = Math.random();
					//Math.random()拿到的是0-1之前的数字，去乘数组的长度 再取整数可以拿到想要的数组下标
					random=Math.floor(Math.random()*2); //floor去取整
					//把拿到的数组的值写进去
					oTitle.innerHTML=data[random];
					setTimeout("check()",1000);
				},50);
				//按开始之后，让颜色改变一下
				begin.style.background="#999";
			}
			
			function fnstop(){
				random=Math.floor(Math.random()*1.7); //floor去取整
				oTitle.innerHTML=data[random];	
				///这里通过ajax返回到给servlet
				$.get("${pageContext.request.contextPath}/FruitServlet",{award:random});
				
				$.ajax({
					   type: "POST",
					   url: "${pageContext.request.contextPath}/FruitServlet",
					   data: "flag=testchou&award="+random,
					   success: function(msg){
					   }
					});

				if(random==1){
					document.getElementById("point out").innerHTML ="咦，抽中了";
				}else{
					var r = Math.floor(Math.random()*3);
					if(r==0){
						document.getElementById("point out").innerHTML ="哎呦，要不换个姿势在试一试";
						
					}else if(r==1){
						document.getElementById("point out").innerHTML ="手速，要快才行";
						
					}else if(r==2){
						document.getElementById("point out").innerHTML ="很遗憾，换个手指再试试吧？";						
					}
				}
				
				clearInterval(timer);
				//恢复为原来的颜色
				begin.style.background="#708098";
			}

			function fnkey(event){
				console.log(event.keyCode);
				event=event ||window.event;
				if(event.keyCode == 13){  //当按下回车键的时候
					//一开始是0的状态，我们改变。
					if(flag == 0){
						fnplay();
						//设置为1的状态，停止改变
						flag =1;
					}else{
						fnstop();
						flag=0;
					}
				}
			}
		}
	</script>
		<style type="text/css">
	
	

	.stop{
	}
	</style>
	
</head>
	<body>
<div class="row">
  <div class="col-md-12">
  <table   Style=" margin:10px auto; border-collapse:   separate;   border-spacing:  0px 30px; " >	
  	<tr>
				<td colspan="2" ><span id="point out">&nbsp</span></td>
				</tr>	
				<tr>
				<td align="center" colspan="2" ><div class="title" >开始抽奖</div></td>
				</tr>
				
				<tr>
					<td>	
						<div class="bs" >
								<span class="begin">开  始</span>
						</div>
					</td>
					
					<td>
						<div class="bs">
								<span class="stop">停  止</span>
						</div>
					</td>
			
				</tr>
			
 </table>
</div>
</div>

		<div id="pageWrapper">
 <div  class="form-wrapper">

			<div class="f14 clearfix login-problem" style=" margin-top: 10px;">
				<a  href="index.jsp">返回主页 </a>
			</div>
</div>
</div>
<div style="  margin-top: -60px;text-align: right;  font-family: '微软雅黑';  color:#e0e0e0; font-size:50px;">测试</div>
	</body>
</html>
