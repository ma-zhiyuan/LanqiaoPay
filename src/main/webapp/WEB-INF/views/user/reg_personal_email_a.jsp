<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>蓝桥银行 - 会员注册</title>
		<meta name="keywords" content="" />
		<meta name="description" content="" />
		<link rel="stylesheet" type="text/css" href="${applicationScope.url}/resources/lanqiaoPayModel/css/global.css">
		<link rel="stylesheet" type="text/css" href="${applicationScope.url}/resources/lanqiaoPayModel/css/form.css">
		<link rel="stylesheet" type="text/css" href="${applicationScope.url}/resources/lanqiaoPayModel/css/biz/reg.css">
		<script type="text/javascript" src="${applicationScope.url}/resources/lanqiaoPayModel/js/jquery.js"></script>
		<script type="text/javascript" src="${applicationScope.url}/resources//lanqiaoPayModel/global.js"></script>
		<script type="text/javascript" src="${applicationScope.url}/resources/lanqiaoPayModel/js/util.js"></script>
		<script type="text/javascript">
			//声明变量存放验证码
			var code;
			//生成验证码的函数
			function Create_Code(){
				code="";
				var code_length=6;
				var random=new Array(0,1,2,3,4,5,6,7,8,9);
				for(var i=0;i<code_length;i++){
					var index=Math.floor(Math.random()*10);
					code+=random[index];
				}
			}
			//声明int变量 
			var wait=60;
			//限制60秒后才可以重发验证码  
			function time(o) {  
		        if (wait == 0) {  
		            o.removeAttribute("disabled");            
		            o.value="发送验证邮件";  
		            wait = 60;  
		        } else {  
		            o.setAttribute("disabled", true); 
		            o.value= wait + "后重新发送";  
		            wait--;  
		            setTimeout(function() {  
		                time(o);  
		            },  
		            1000);  
		       }  
		    }  
			$(function(){
				//将焦点放到邮箱输入框
				$("#email").focus();
				//输入框失去焦点时触发
				$("#sendEmail").click(function(){
					//获取用户输入的邮箱
					var emailval= $("#email").val();
					//消除字符串前后空格
					emailval = $.trim(emailval);
					//邮箱的正则表达式
					var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
					//生成验证码
					Create_Code();
					//ajax请求发送的地址
					var url="<%=request.getContextPath() %>/userController/isUsed";
					//请求携带的参数
					var args={"time":new Date(),"userEmail":emailval,"emailCode":code};	
					if(emailval==""){
						$("#hint").text("邮箱不能为空！");
						$("#hint").css("color","red");
						$("#email").focus();
						return false;
					}else if(!reg.test(emailval)){
						$("#hint").text("邮箱格式不正确！");
						$("#hint").css("color","red");
						$("#email").focus();
            			return false;
        			}else{
						$("#hint").text("");
						//60秒后重新发送
						time(this);
        				$.post(url,args,function(data){
							if(data=="该邮箱已注册，请换一个！"){
								$("#hint").text(data);
								$("#hint").css("color","red");
								$("#email").focus();
								return false;
							}
							if(data=="邮箱发送失败，请重发！"){
								$("#hint").text(data);
								$("#hint").css("color","red");
								return false;
							}
							 if(data=="验证邮件已发送，请查看邮箱！"){
								$("#hint").text(data);
								$("#hint").css("color","green");
							}
								document.getElementById("personal_sendEmail").onclick=function(){
									//获取用户输入的验证码
									var codeVal = $("#code").val();
									if(emailval==""){
										$("#hint").text("邮箱不能为空！");
										$("#hint").css("color","red");
										$("#email").focus();
										return false;
									}else if(!reg.test(emailval)){
										$("#hint").text("邮箱格式不正确！");
										$("#hint").css("color","red");
										$("#email").focus();
				            			return false;
				        			}else if(codeVal==""||codeVal!=code){
										$("#hint").text("");
										alert("验证码输入错误！");
										return false;
									}
									document.getElementById("personal_sendEmail").href="<%=request.getContextPath() %>/userController/to_reg_personal_b";
								}
						});
        			}
				});
			});
		</script>
	</head>

	<body>
		<div id="top">
		</div>
		<div id="header">
			<div class="layout">
				<div id="logo">
					<a href="userController/to_uc">
						<img src="${applicationScope.url}/resources/styles/images/logo.png" alt="蓝桥" />
					</a>
				</div>
			</div>
		</div>
		<div id="page">
			<div class="layout reg-wrapper">
				<div class="reg-top">
					<ul class="reg-step clearfix" >
						<li class="step current">
							<p class="txt">1. 注册账号</p>
							<p class="arr arr-after"></p>
						</li>
						<li class="step">
							<p class="arr arr-before"></p>
							<p class="txt">2. 完善资料</p>
							<p class="arr arr-after"></p>
						</li>
						<li class="step">
							<p class="arr arr-before"></p>
							<p class="txt">3. 密码设置</p>
							<p class="arr arr-after"></p>
						</li>
						<li class="step last">
							<p class="arr arr-before"></p>
							<p class="txt">4. 注册成功,等待审核</p>
						</li>
					</ul>
				</div>
				<form class="form-bd" action="" id="demoForm">
					<div class="form-tab">
						<ul class="form-tab-hd clearfix">
							<li class="current">
								<a class="tab-item-a" href="reg_personal_mobile_a.html">
									<span class="iconfont icon-personal">个人注册</span> <em></em>
								</a>
							</li>
							<li>
								<a class="tab-item-b" href="reg_company_a.html">
									<span class="iconfont icon-company">企业注册</span>
								</a>
							</li>
						</ul>
						<div class="form-tab-bd">
							<div class="form-item">
								<h4 class="form-label"> <em>*</em>
									电子邮箱:
								</h4>
								<div class="form-entity">
									<div class="form-field">
										<input id="email" class="ipt" type="text" name="email" value="" placeholder="请输入邮箱" />&nbsp;&nbsp;<input id="sendEmail" type="button" value="发送验证邮件"><br/>
										<label id="hint"></label><br/>
										验证码：<input id="code" type="text" >
									</div>
								</div>
							</div>
							<div class="form-action clearfix">
								<a id="personal_sendEmail" href="javascript:;" class="glb-btn submit-btn">
									<span>下一步</span>
								</a>
							</div>
						</div>
					</div>
				</form>
			</div>
			<div id="bottom" class="bottom">
			</div>
		</div>
	</body>
</html>
