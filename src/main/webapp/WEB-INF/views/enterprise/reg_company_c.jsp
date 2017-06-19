<%@ page language="java" pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
	Date date = new Date();
	String token = "" + date.getTime();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>蓝桥支付 - 会员注册</title>
<meta name="keywords" content="" />
<meta name="description" content="" />

<link rel="stylesheet" type="text/css"
	href="${applicationScope.url}/resources/lanqiaoPayModel/css/global.css" />
<link rel="stylesheet" type="text/css"
	href="${applicationScope.url}/resources/lanqiaoPayModel/css/form.css" />
<link rel="stylesheet" type="text/css"
	href="${applicationScope.url}/resources/lanqiaoPayModel/css/biz/reg.css" />
<script type="text/javascript"
	src="${applicationScope.url}/resources/scripts/jquery.js"></script>
<script type="text/javascript"
	src="${applicationScope.url}/resources/lanqiaoPayModel/js/global.js"></script>
<script type="text/javascript"
	src="${applicationScope.url}/resources/lanqiaoPayModel/js/util.js"></script>
<script type="text/javascript"
	src="${applicationScope.url}/resources/lanqiaoPayModel/js/animation.js"></script>
<script type="text/javascript"
	src="${applicationScope.url}/resources/lanqiaoPayModel/js/group.js"></script>
<script type="text/javascript"
	src="${applicationScope.url}/resources/lanqiaoPayModel/js/md5.js"></script>
<script type="text/javascript">

	$(function() {
	
		var pass1=0;
		var pass2=0;
		var pass3=0;
		var pass4=0;
		var pass5=0;
		var pass6=0;
	
		$("#loginPassword").blur(function() {
			var lpwd = $(this).val();
			if (!$(this).val()) {
				$("#lpwd").html("密码不能为空");
				$("#lpwd").css("color", "red");
				return false;
			} else if (!isNumberOr_Letter(lpwd)) {
				$("#lpwd").html("密码只能由数字和字母组成");
				$("#lpwd").css("color", "red");
				return false;
			} else if (lpwd.length < 6 || lpwd.length > 12) {
				$("#lpwd").html("密码的长度必须在6-12位");
				$("#lpwd").css("color", "red");
				return false;
			} else {
				$("#lpwd").html("");
				pass1=1;
				$("#lpwd").css("color", "green");
				return true;
			}
		
		});

		function isNumberOr_Letter(s) { //判断是否是数字或字母
			var regu = "^[0-9a-zA-Z\_]+$";
			var re = new RegExp(regu);
			if (re.test(s)) {
				return true;
			} else {
				return false;
			}
		}
		
		/* 判断两次输入登录密码是否一致 */
		$("#secondLoginPassword").blur(function() {
			if (!$(this).val()) {
				$("#slpwd").html("密码不能为空");
				$("#slpwd").css("color", "red");
				return false;
			} else if ($("#secondLoginPassword").val() != $("#loginPassword").val()) {
				$("#slpwd").html("两次输入密码不一致");
				$("#slpwd").css("color", "red");
				return false;
			} else {
				$("#slpwd").html("");
				pass2=1;
				$("#slpwd").css("color", "green");
				return true;
			}
		});
		
		/*  设置支付密码约束  只允许6位数字*/
		$("#payPassword").blur(function() {
			var ppwd = $(this).val();
			if (!$(this).val()) {
				$("#ppwd").html("密码不能为空");
				$("#ppwd").css("color", "red");
				return false;
			} else if (!isNumber(ppwd)) {
				$("#ppwd").html("只能是数字密码");
				$("#ppwd").css("color", "red");
				return false;
			} else if (ppwd.length > 6 || ppwd.length < 6) {
				$("#ppwd").html("只能是6位密码");
				$("#ppwd").css("color", "red");
				return false;
			} else {
				$("#ppwd").html("");
				pass3=1;
				$("#ppwd").css("color", "green");
				return true;
			}
		});
		function isNumber(s) {
			var regu = "^[0-9]+$";
			var re = new RegExp(regu);
			if (s.search(re) != -1) {
				return true;
			} else {
				return false;
			}
		}
		
		/* 判断两次输入支付密码是否一致 */
		$("#secondPayPassword").blur(function() {
			if (!$(this).val()) {
				$("#sppwd").html("密码不能为空");
				$("#sppwd").css("color", "red");
				return false;
			} else if ($("#secondPayPassword").val() != $("#payPassword").val()) {
				$("#sppwd").html("两次输入密码不一致");
				$("#sppwd").css("color", "red");
				return false;
			} else {
				$("#sppwd").html("");
				pass4=1;
				$("#sppwd").css("color", "green");
				return true;
			}
		});


		//安全保护问题
		//自定义问题的显示
		$("#select").change(function(){
			var select=$(this).val();
			if(select==0){//自定义问题
				$("#byUser").show();
			}else if(select==1){
				pass5=1
				$("#byUser").hide();
				$("#byUser").val("您的生日");
				$("#userQuestion").text("");
			}else if(select==2){
				pass5=1
				$("#byUser").hide();
				$("#byUser").val("您父亲的生日");
				$("#userQuestion").text("");
			}else if(select==3){
				pass5=1
				$("#byUser").hide();
				$("#byUser").val("您母亲的生日");
				$("#userQuestion").text("");
			}
		});

		$("#byUser").blur(function() {
			var a = $(this).val();
			if (a == "") {
				$("#userQuestion").text("请输入自定义问题");
				$("#userQuestion").css("color", "red");
			} else {
				$("#userQuestion").text("");
				pass5=1;
			}
		});
		
		$("#answerByUser").blur(function(){
			var a = $(this).val();
			if (a == "") {
				$("#answer").text("请输入回答");
				$("#answer").css("color", "red");
			} else {
				$("#answer").text("");
				pass6=1;
			}
		});


		$(":submit").click(function(){
			if(pass1==1&&pass2==1&&pass3==1&&pass4==1&&pass5==1&&pass6==1){
				var md5lpwd = md5($("#loginPassword").val());
				$("#loginPassword").val(md5lpwd);
				var md5ppwd = md5($("#payPassword").val());
				$("#payPassword").val(md5ppwd);
				return true;
			}else{
				alert("信息填写不完整或不正确");
				return false;
			}
		});
		
		
	});


</script>
</head>

<body>
	<div id="top"></div>
	<div id="header">
		<div class="layout">
			<div id="logo">
				<a href="http://www.lanqiao.org"> <img
					src="${applicationScope.url}/resources/styles/images/logo.png" alt="蓝桥" />
				</a>
			</div>
		</div>
	</div>
	<div id="page">
		<div class="layout reg-wrapper">
			<div class="reg-top">
				<ul class="reg-step clearfix">
					<li class="step">
						<p class="txt">1. 注册账号</p>
						<p class="arr arr-after"></p>
					</li>
					<li class="step">
						<p class="arr arr-before"></p>
						<p class="txt">2. 完善资料</p>
						<p class="arr arr-after"></p>
					</li>
					<li class="step current">
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
			<form:form class="form-bd items-group-box" action="${applicationScope.url }/enterprise/registC"
				id="companyForm" modelAttribute="c" enctype="multipart/form-data">
				<div class="item-group form-group">
					<div class="group-hd reg-setPassword">
						<h3>登录密码</h3>
						<span>登录时需验证，保护账号信息</span>
					</div>
					<div class="group-bd">
						<div class="form-item">
							<h4 class="form-label">登录密码:</h4>
							<div class="form-entity">
								<div class="form-field">
									<form:input path="password" class="ipt" id="loginPassword" type="password" placeholder="密码需由6-12位的数字与字母组成"/>
									&nbsp;&nbsp;<span id="lpwd" style="color: red"><form:errors path="password"></form:errors></span>
								</div>
							</div>
						</div>
						<div class="form-item">
							<h4 class="form-label">再输入一次:</h4>
							<div class="form-entity">
								<div class="form-field">						
									<form:input path="password2" class="ipt" id="secondLoginPassword" type="password" placeholder="请再次输入密码" />
									&nbsp;&nbsp;<span id="slpwd" style="color: red"><form:errors path="password2"></form:errors></span>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="item-group form-group">
					<div class="group-hd reg-setPassword">
						<h3>支付密码</h3>
						<span>交易付款或账户信息变更时需输入，不可与登录密码一致！</span>
					</div>
					<div class="group-bd">
						<div class="form-item">
							<h4 class="form-label">支付密码:</h4>
							<div class="form-entity">
								<div class="form-field">									
									<form:input path="payPassword" class="ipt" id="payPassword" type="password" placeholder="密码只能是6位数字"/>
									&nbsp;&nbsp;<span id="ppwd" style="color: red"><form:errors path="payPassword"></form:errors></span>
								</div>
							</div>
						</div>
						<div class="form-item">
							<h4 class="form-label">再输入一次:</h4>
							<div class="form-entity">
								<div class="form-field">
									<form:input path="payPassword2" class="ipt" id="secondPayPassword" type="password" placeholder="请再次输入密码"/>
									&nbsp;&nbsp;<span id="sppwd" style="color: red"><form:errors path="payPassword2"></form:errors></span>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="item-group form-group">
					<div class="group-hd reg-setPassword">
						<h3>安全保护问题</h3>
						<span>忘记密码时，可通过回答问题找回密码</span>
					</div>
					<div class="group-bd">
						<div class="form-item">
							<h4 class="form-label">安全保护问题:</h4>
							<div class="form-entity">
								<div class="form-field form-multi-ipt">
									<select class="sel sel-reg-setPassword" id="select">
										<option value="0">自定义</option>
										<option value="1">您的生日</option>
										<option value="2">您父亲的生日</option>
										<option value="3">您母亲的生日</option>
									</select>
									&nbsp;&nbsp;<span id="selectFalse"></span>
								</div>
							</div>
						</div>

						<div class="form-item">
							<div class="form-entity">
								<div class="form-field">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<form:input path="securityQuestion" class="ipt" id="byUser"  value="" placeholder="请输入您的自定义问题" />
									&nbsp;&nbsp;<span id="userQuestion" style="color: red"><form:errors path="securityQuestion"></form:errors></span>
								</div>
							</div>
						</div>
						<div class="form-item">
							<h4 class="form-label">您的答案:</h4>
							<div class="form-entity">
								<div class="form-field">
									<form:input path="classifiedAnswer" class="ipt" id="answerByUser" type="text" value="" placeholder="请输入"/>
									&nbsp;&nbsp;<span id="answer" style="color: red"><form:errors path="classifiedAnswer"></form:errors></span>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="form-action clearfix">
					<input id="next-button" type="submit" class="glb-btn submit-btn" value="下一步"/>
				</div>
			</form:form>
		</div>
		<div id="bottom" class="bottom"></div>
	</div>

</body>

</html>