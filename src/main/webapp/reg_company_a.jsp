<%@ page language="java" pageEncoding="UTF-8"  import="java.util.*" %>
<%	Date date = new Date();
	String token = ""+date.getTime();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>蓝桥支付 - 注册</title>
		<meta name="keywords" content="" />
		<meta name="description" content="" />
		<link rel="stylesheet" type="text/css" href="${applicationScope.url}/resources/lanqiaoPayModel/css/global.css"/>
		<link rel="stylesheet" type="text/css" href="${applicationScope.url}/resources/lanqiaoPayModel/css/form.css"/>
		<link rel="stylesheet" type="text/css" href="${applicationScope.url}/resources/lanqiaoPayModel/css/biz/reg.css"/>
		<link rel="stylesheet" type="text/css" href="${applicationScope.url}/resources/lanqiaoPayModel/css/slide-unlock.css"/>
		<link rel="stylesheet" type="text/css" href="${applicationScope.url}/resources/lanqiaoPayModel/css/dialog.css"/>
		<script type="text/javascript" src="${applicationScope.url}/resources/scripts/jquery-3.1.1.min.js"></script>
		<script type="text/javascript" src="${applicationScope.url}/resources/lanqiaoPayModel/js/global.js"></script>
		<script type="text/javascript" src="${applicationScope.url}/resources/lanqiaoPayModel/js/util.js"></script>
		<script type="text/javascript" src="${applicationScope.url}/resources/lanqiaoPayModel/js/group.js"></script>
		<script type="text/javascript" src="${applicationScope.url}/resources/lanqiaoPayModel/js/jquery-slide-unlock.js"></script>
		<script type="text/javascript" src="${applicationScope.url}/resources/lanqiaoPayModel/js/animation.js"></script>
		<script type="text/javascript" src="${applicationScope.url}/resources/lanqiaoPayModel/js/dialog.js"></script>
		<script type="text/javascript" src="${applicationScope.url}/resources/scripts/autoMail.1.0.min.js"></script>
		<script type="text/javascript">
			$(function () {
				//邮箱自动补全
				$('#email').autoMail({
					emails:['qq.com','163.com','126.com','sina.com','sohu.com','yahoo.cn']
				});
				
				var slider = new SliderUnlock("#slider", {
				}, function() {});
				
				slider.init();
				
				var wait=60;  
				function time(o) {  
		        if (wait == 0) {  
		            o.removeAttribute("disabled");            
		            o.value="发送验证链接";  
		            wait = 60;  
		        } else {  
		            o.setAttribute("disabled", true); 
		             
		            o.value="重新发送(" + wait + ")";  
		            wait--;  
		            setTimeout(function() {  
		                time(o);  
		            },  
		            1000);  
		        }  
			    }  
		/* 		// 发送邮件
				$("#sendEmail").click(function() {
		
					Dialogx.show({
						_url : 'dialog_email.html',
						_title : '验证用户名'
					});
				}); */
				//点击免费获取邮箱验证码:1.先判断是否存在一个合法的邮箱. 
			//定义一个发送Ajax的方法
			function ajaxReturnBoolean(data,url){
				var res = false;
				$.ajax({
					async : false,
					cache : false,
					url : url,
					success: function(data){
						res = data;
					},
					type:"POST",
					data : data
				});
				return res;
			}	
				
			$("[id=btn]").click(function(){
			var email = $("[id=email]").val();
			var aCode = $("[name=aCode]").val();
			if(email==""||email.indexOf(' ')!=-1){
			$("[name=emailValidate]").text("邮箱不能为空!"); 
			$("[name=emailValidate]").css("color","red");
			$("[name=email]").focus();
			/*alert(123);*/
			}else if(!(/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test(email))){
				$("[name=emailValidate]").text("邮箱不合法"); 
				$("[name=emailValidate]").css("color","red");
				$("[name=email]").focus();
			}else{//邮箱正确之后才发送邮件,还需要判断邮箱是否被别人使用过
				var data =  {"email":email}
				url = "${applicationScope.url}/enterprise/validateEmaliIsUseful";
				if(ajaxReturnBoolean(data,url)=='true'){//说明邮箱可以使用,未被注册过!
					$("[name=emailValidate]").text("说明邮箱可以使用,未被注册过!");
					var dataForSendEmail = {"email":email,"aCode":aCode};
					var urlForSendEmail = "${applicationScope.url}/commonController/sendAEmail";
					var sendOk = false;
					sendOk = ajaxReturnBoolean(dataForSendEmail, urlForSendEmail);
					if(sendOk == 'true'){
						$("[name=emailValidate]").html("邮件已发送成功,<a href='https://mail.qq.com/cgi-bin/loginpage' target='_blank'>请查看</a>"); 
						$("[name=emailValidate]").css("color","green");
						time(this);//下来才发送邮件
					}
					else{
						$("[name=emailValidate]").text("邮箱发送失败,请稍后重试"); 
						$("[name=emailValidate]").css("color","red");
					}
				}else{
					$("[name=emailValidate]").text("邮箱已被使用"); 
					$("[name=emailValidate]").css("color","red");
					$("[name=email]").focus();
				}
				
			}
			});
			//当下一部按钮点击后,验证是否用户已经点击了邮件的超链接
			$("#sendEmail").click(function(){
				//在这里直接写${applicationScope.emailCode}是会使整个js脚本都会失效
				var aCode = $("[name=aCode]").val();
				alert(aCode);
				var data = {"aCode":aCode};
				var url = "${applicationScope.url}/commonController/validateEmailCode"
				var emailCodeOk = ajaxReturnBoolean(data, url);
				alert(emailCodeOk);
				if(emailCodeOk == 'true'&&$("[id=labelTip]").text()=="验证成功！"){
				
				}else{
					alert("您的操作不完善,无法进行下一步!")
					return false;
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
					<a href="uc.html">
						<img src="${applicationScope.url}/resources/styles/images/logo.png" alt="蓝桥" />
					</a>
				</div>
			</div>
		</div>
		<div id="page">
			<div class="layout reg-wrapper">
				<div class="reg-top" >
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
				<form class="form-bd reg-pay-bd" action="" id="demoForm" >
					<div class="form-tab">
						<ul class="form-tab-hd clearfix">
							<li>
								<a class="tab-item-a" href="reg_personal_email_a.html">
									<span class="iconfont icon-personal">个人注册</span>
								</a>
							</li>
							<li class="current">
								<a class="tab-item-b" href="reg_company_a.html">
									<span class="iconfont icon-company">企业注册</span> <em></em>
								</a>
							</li>
						</ul>
						<div class="form-tab-bd">
							<!-- 邮箱验证消息显示域 -->
							<div class="form-item">
								<h4 class="form-label">
								</h4>
								<div class="form-entity">
									<input name="aCode" value="<%=token%>" type="hidden"/>
									<span name="emailValidate" style="color: red"></span>
								</div>
							</div>
							
							<div class="form-item">
								<h4 class="form-label"> <em>*</em>
									电子邮箱:
								</h4>
								<div class="form-entity">
									<div class="form-field">
										<input class="ipt" type="text" id="email" name="userName" value="" placeholder="请输入邮箱" />
										<input class="btn btn-success" id="btn" type="button" value="发送邮箱验链接"/>
									</div>
								</div>
							</div>
							<div class="form-item">
								<h4 class="form-label">验证码:</h4>
								<div class="form-entity">
									<div id="slider">
										<div id="slider_bg"></div>
										<span id="label">>></span> <span id="labelTip">请按住滑块，拖动到最右边</span>
									</div>
								</div>
							</div>
							<div class="form-item">
								<div class="form-label reg-checkbox">
									<input  type="checkbox" checked="checked" />
									我同意<a href="#">蓝桥支付协议</a>
								</div>
							</div>
							<div class="form-action clearfix">
								<a id="sendEmail" href="" class="glb-btn submit-btn">
									<span>下一步</span>
								</a>
							</div>
						</div>
					</div>
				</form>
				<div class="form-tab reg-pay-tab items-group-box">
					<div class="item-group form-group item-group-reg">
						<div class="group-hd group-hd-re1">
							<h3>注册前需要准备什么材料？</h3>
						</div>
						<div class="group-bd">
							<div class="group-hd-rea">
								<p>注册前需要准备:<span>影印件必须为彩色原件的扫描件或数码照</span></p>
								<ul>
									<li><a href="#">营业执照影印件</a></li>
									<li>对公银行账户，可以是基本户或一般户</li>
									<li>法定代表人的<a href="#">身份证影印件</a></li>
								</ul>
							</div>
							<div class="group-hd-rea">
								<p>如果你是代理人，除以上资料，还需准备:</p>
								<ul>
									<li>你的<a href="#">身份证影印件</a></li>
									<li>对公银行账户，可以使基本户或一般户</li>
									<li>企业委托书，必须该有公司公章或者财务专用章，不能是合同/企业专用章<a href="#">查看模板</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div id="bottom" class="bottom"></div>
		</div>
	</body>
</html>