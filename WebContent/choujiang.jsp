<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="css/base_7e1382b.css" type="text/css" rel="stylesheet">  
		   <meta http-equiv="content-type" content="text/html; charset=UTF-8">
   			<!--声明文档兼容模式，表示使用IE浏览器的最新模式-->
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<!--设置视口的宽度(值为设备的理想宽度)，页面初始缩放值<理想宽度/可见宽度>-->
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
		<!-- 引入Bootstrap核心样式文件 -->
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<!-- 引入BootStrap核心js文件 -->
		
	<script src="js/jquery-1.8.3.js"  ></script>
		<script src="js/bootstrap.min.js"></script>
	<style>
.div1{text-align:center} 
	</style>
<meta charset="UTF-8">
	<title>酷音乐：祝君好运！！</title>
	<link href="css/choujiang.css" rel="stylesheet">
		<script >
		//定义一个数组变量存放几个数据，一个定时器，一个标识变量
		var data=['谢谢参与','买一赠一'],
			timer=null,
			flag=0,
			flag1=0;
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
			//alert("123");	 
			var flag2=false;
			$.post(
					"${pageContext.request.contextPath}/UserServlet?flag=trueORfalse",
					function(data){
						var isExist = data.isExist;
						flag2 = data.flag2;
						if(isExist){
							alert("亲，您今天已经抽奖了！");	
							flag1=1;
						}else{	
							document.getElementById("point out").innerHTML ="本次活动每人只有一次抽奖机会哦！祝您好运";
						}
					},
					"json"
			);
			

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
				var f = 1;
				
				$.post(
						"${pageContext.request.contextPath}/UserServlet?flag=trueORfalse",
						function(data){
							var isExist = data.isExist;
							flag2 = data.flag2;
							if(isExist){
								alert("亲，您今天已经抽奖了！");	
								
								flag1=1;
								return ;
							}else{	
								document.getElementById("point out").innerHTML ="本次活动每人只有一次抽奖机会哦！祝您好运";
							}
						},
						"json"
				);
				//这里阻止了抽奖
				if(flag1==1){
					return ;
				}
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
			
			function fnstop(){ //按停止
				///这里通过ajax返回到给servlet			
				if(flag1==0){  //第一次抽奖
					if(flag2){
						//alert("后台");
						random=1;
						oTitle.innerHTML=data[random];						
					}
					
					$.ajax({
						   type: "POST",
						   url: "${pageContext.request.contextPath}/FruitServlet",
						   data: "flag=chou&award="+random,
						   success: function(msg){
						   }
						});
					flag1=1;

				}else{
						random=Math.floor(Math.random()*1.4); //floor去取整
						oTitle.innerHTML=data[random];	
					$.ajax({
						   type: "POST",
						   url: "${pageContext.request.contextPath}/FruitServlet",
						   data: "flag=testchou&award="+random,
						   success: function(msg){
						   }
						});
					alert("亲，您今天已经抽奖了！");	
				}
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
	<script>
  function GetRTime(){
    var EndTime= new Date('2017/11/12 00:00:00');
    var NowTime = new Date();
    var t =EndTime.getTime() - NowTime.getTime();
    var d=0;
    var h=0;
    var m=0;
    var s=0;
    if(t>=0){
      d=Math.floor(t/1000/60/60/24);
      h=Math.floor(t/1000/60/60%24);
      m=Math.floor(t/1000/60%60);
      s=Math.floor(t/1000%60);
    }
    document.getElementById("t_d").innerHTML = d + "天";
    document.getElementById("t_h").innerHTML = h + "时";
    document.getElementById("t_m").innerHTML = m + "分";
    document.getElementById("t_s").innerHTML = s + "秒";
  }
  setInterval(GetRTime,0);
</script>
	
</head>
	<body>
	 <c:if test="${ sessionScope.user!=null }">
	 	<section class="user-profile">
		<div class="user-login clearfix" ><div class="fl"><font color = "red">欢迎</font><font color="blue"> ${sessionScope.user.qq}</font></div>
		</section>
  	</c:if>
	
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
 <div id="pageWrapper">
 <div  class="form-wrapper">

			<div class="f14 clearfix login-problem">
				<a  href="index.jsp">返回主页 </a>
				
				<a href="${pageContext.request.contextPath }/UserServlet?flag=fruit" id="login-other" class="type-other-login">查看抽奖记录</a>
			</div>
</div>
</div>
	<div class="div1"> 活动倒计时</div>
	  <div class="div1">
    <span id="t_d">00天</span>
    <span id="t_h">00时</span>
    <span id="t_m">00分</span>
    <span id="t_s">00秒</span>
  </div>
	</body>
</html>