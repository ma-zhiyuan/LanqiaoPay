<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>蓝桥支付-登录</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/bootstrap/css/bookstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/lanqiaoPayModel/css/global.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/lanqiaoPayModel/css/biz/login.css">
<script type="text/javascript"
	src="<%=basePath%>resources/lanqiaoPayModel/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/lanqiaoPayModel/js/global.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/lanqiaoPayModel/js/md5.js"></script>
<script type="text/javascript">
	$(function() {
		$("#error").hide(); //隐藏div
		$("#name").blur(function() {
			//获取用户输入的内容
			var name = $(this).val();
			if (!name == '' || !name == undefined) {

				//ajax请求发送的地址
				var url = "userController/verifyName";
				//发送请求所携带的参数
				var args = {
					"name" : name,
					"time" : new Date()
				};
				//发送请求，data是返回数据
				$.post(url, args, function(data) {
					//把返回的数据放到提示框中。
					if (data == "") {
						$("#error").hide(); //隐藏div
					} else {
						$("#error").show(); //显示div
						$("#hint").text(data);
					}
				});
			} else {
				$("#error").show(); //显示div
				$("#hint").text("用户名不能为空！");
			}
		});
		//页面通过MD5加密
		$("#password").blur(function() {
			var pwd = $("#password").val();
			if (!pwd == '' || !pwd == undefined) {
				var md5Str = md5($("#password").val());
				($("#password")).val(md5Str);
			} else {
				$("#error").show(); //显示div
				$("#hint").text("密码不能为空！");
			}

		});
		//当鼠标点击验证码时开始验证输入的密码是否正确
		$("#vcode").click(function() {
			//验证输入的密码是否正确
			var name = $("#name").val();
			var pwd = $("#password").val();
			if (!pwd == '' || !pwd == undefined) {
				//ajax发送url请求
				var url = "userController/verifyPassword";
				//发送请求参数
				var args = {
					"name" : name,
					"password" : pwd,
					"time" : new Date()
				};
				//发送请求到页面
				$.post(url, args, function(data) {
					//把返回数据放入提示框内
					if (data == "") {
						$("#error").hide(); //隐藏div
					} else {
						$("#error").show(); //显示div
						$("#hint").text(data);
					}
				});
			}
		});

		//验证用户输入的验证码是否正确
		$("#vcode").blur(function() {
			var vcode = $(("#vcode")).val();
			if (!vcode == '' || !vcode == undefined) {
				var url = "userController/verifyCode";
				var args = {
					"vcode" : vcode,
					"time" : new Date()
				};
				$.post(url, args, function(data) {
					if (data == "") {
						$("#error").hide(); //隐藏div
					} else {
						$("#error").show(); //显示div
						$("#hint").text(data);
					}
				});
			} else {
				$("#error").show(); //显示div
				$("#hint").text("验证码不能为空！");
			}
		});


	});
</script>


</head>
<!-- body start-->
<body class="login-body">
	<div id="top"></div>
	<div id="header">
		<div class="layout">
			<div id="logo">
				<a href="uc.html"> <img
					src="<%=basePath%>resources/styles/images/logo.png" alt="蓝桥" />
				</a>
			</div>
		</div>
	</div>
	<!-- 表单 -->
	<div class="login-wrapper">
		<div class="login-pos layout">
			<div class="login-box login-wth-code">
				<div class="login-bd login-opa60">
					<h3 class="login-title">登录</h3>
					<form action="userController/login" method="post">
						<dl class="login-item">
							<dt>
								<span class="iconfont icon-acc-user"></span>
							</dt>
							<dd>
								<input name="name" id="name" placeholder="请输入用户名" />
							</dd>
						</dl>
						<dl class="login-item">
							<dt>
								<span class="iconfont icon-acc-pwd"></span>
							</dt>
							<dd>
								<input type="password" name="loginPassword" id="password"
									placeholder="请输入密码" />
							</dd>
						</dl>
						<p class="login-help">
							<a href="userController/register" class="register">立即注册</a> <a
								href="userController/toforgetPwd" class="forget-pwd" target="_blank">忘记密码?</a>
						</p>
						<div class="login-code clearfix">
							<dl class="login-item ">
								<dt>
									<span class="iconfont icon-acc-kaptcha"></span>
								</dt>
								<dd>
									<!-- 验证码 -->
									<input type="text" name="vcode" id="vcode" placeholder="验证码" />
								</dd>
							</dl>
							<a class="login-captcha"> <img id="verification"
								src="userController/getcode"
								onclick="this.src='userController/getcode?t='+Math.random()" />
							</a>
						</div>
						<button type="submit" class="glb-btn login-btn">
							<span>登 录</span>
						</button>

						<div class="login-error" id="error">
							<span class="iconfont icon-wrong"></span>
							<p id="hint">请输入用户名</p>
						</div>

					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="bottom" id="bottom"></div>
</body>
<!-- body stop-->
</html>
