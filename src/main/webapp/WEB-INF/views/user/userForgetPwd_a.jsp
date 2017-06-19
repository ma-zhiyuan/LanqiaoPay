<%@ page language="java" pageEncoding="UTF-8"  import="java.util.*" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%	Date date = new Date();
	String token = ""+date.getTime();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>用户密码找回</title>
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
			$(function(){
				
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
				url = "${applicationScope.url}/userController/validateEmaliIsUseful";
				if(ajaxReturnBoolean(data,url)=='false'){//说明邮箱已经注册过了
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
					$("[name=emailValidate]").text("邮箱未注册"); 
					$("[name=emailValidate]").css("color","red");
					$("[name=email]").focus();
				}
				
			}
			});
			//当下一部按钮点击后,验证是否用户已经点击了邮件的超链接
			$("#sendEmail").click(function(){
				//在这里直接写${applicationScope.emailCode}是会使整个js脚本都会失效
				var aCode = $("[name=aCode]").val();
				//alert(aCode);
				var data = {"aCode":aCode};
				var url = "${applicationScope.url}/commonController/validateEmailCode"
				var emailCodeOk = ajaxReturnBoolean(data, url);
				//alert(emailCodeOk);
				if(emailCodeOk == 'true'&&$("[id=labelTip]").text()=="验证成功！"){
				
				}else{
					alert("您的操作不完善,无法进行下一步!")
					return false;
				}
			});
			
				$(":checkbox").click(function(){
					//alert($(":checkbox").get(0).checked);
					if($(":checkbox").get(0).checked==true){
						$("#sendEmail").removeAttr("disabled");
					}else{
						$("#sendEmail").attr("disabled","disabled");
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
							<p class="txt">1. 邮箱验证</p>
							<p class="arr arr-after"></p>
						</li>
						<li class="step">
							<p class="arr arr-before"></p>
							<p class="txt">2. 修改密码</p>
							<p class="arr arr-after"></p>
						</li>
						<li class="step last">
							<p class="arr arr-before"></p>
							<p class="txt">3. 修改成功</p>
						</li>
					</ul>
				</div>
				<form class="form-bd reg-pay-bd" action="${applicationScope.url }/userController/forgetPwd" id="demoForm">
					<div class="form-tab">
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
										<input class="ipt" type="text" id="email" name="email" value="" placeholder="请输入邮箱" />
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
							<div class="form-action clearfix">
									<input id="sendEmail" type="submit" class="glb-btn submit-btn" value="下一步"/>
							</div>
						</div>
					</div>
				</form>
			</div>
			<div id="bottom" class="bottom"></div>
		</div>
	</body>
</html>