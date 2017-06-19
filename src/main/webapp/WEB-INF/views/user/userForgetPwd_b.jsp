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
<title>蓝桥支付 - 密码找回</title>
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
		
		function isNumber(s) {
			var regu = "^[0-9]+$";
			var re = new RegExp(regu);
			if (s.search(re) != -1) {
				return true;
			} else {
				return false;
			}
		}

		$(":submit").click(function(){
			if(pass1==1&&pass2==1){
				var md5lpwd = md5($("#loginPassword").val());
				$("#loginPassword").val(md5lpwd);
				var md5ppwd = md5($("#secondLoginPassword").val());
				$("#secondLoginPassword").val(md5ppwd);
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
				<a href="uc.html"> <img
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
						<p class="txt">1. 邮箱验证</p>
						<p class="arr arr-after"></p>
					</li>
					<li class="step current">
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
			<form class="form-bd items-group-box" action="${applicationScope.url }/userController/changePwd"
				id="companyForm" enctype="multipart/form-data" method="post">
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
									<input name="password" class="ipt" id="loginPassword" type="password" placeholder="密码需由6-12位的数字或字母组成"/>
									&nbsp;&nbsp;<span id="lpwd" style="color: red"></span>
								</div>
							</div>
						</div>
						<div class="form-item">
							<h4 class="form-label">再输入一次:</h4>
							<div class="form-entity">
								<div class="form-field">						
									<input name="password2" class="ipt" id="secondLoginPassword" type="password" placeholder="请再次输入密码" />
									&nbsp;&nbsp;<span id="slpwd" style="color: red"></span>
								</div>
							</div>
						</div>
						<input id="email" name="email" type="hidden" value="${requestScope.email} "/>
					</div>
				</div>
				<div class="form-action clearfix">
					<input id="next-button" type="submit" class="glb-btn submit-btn" value="下一步"/>
				</div>
			</form>
		</div>
		<div id="bottom" class="bottom"></div>
	</div>

</body>

</html>