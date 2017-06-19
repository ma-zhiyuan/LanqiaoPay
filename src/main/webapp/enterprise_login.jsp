<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>企业用户登录</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
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
	function refresh(obj) {
		obj.src = "enterpriseUser/getcode?id=" + Math.random();
	}

	$(function() {
		$("#error").hide();

		$("#name").blur(function() {
			//获取用户输入的内容
			var name = $(this).val();
			name = $.trim(name);
			if (name == "") {
				$("#error").show();
				$("#hint").text("请输入用户名");
			} else {
				$("#error").hide();

				//ajax请求发送的地址
				var url = "enterpriseUser/verifyName";
				//发送请求所携带的参数
				var args = {
					"name" : name,
					"time" : new Date()
				};
				//发送请求，data是返回数据
				$.post(url, args, function(data) {
					if (data == "") {
						$("#error").hide();
					} else {
						//把返回的数据放到提示框中。
						$("#error").show();
						$("#hint").text(data);
					}
				});
			}
		});
		//页面通过MD5加密
		$("#submit").click(function() {
			var password = $("#password").val();
			password = $.trim(password);
			if (password == "") {
				$("#error").show();
				$("#hint").text("密码不能为空");
			} else {
				$("#error").hide();
				var md5Str = md5($("#password").val());
				($("#password")).val(md5Str);
			}
		});

		$("#submit").click(function() {
			//验证输入的密码是否正确
			var name = $("#name").val();
			var password = $("#password").val();
			//ajax发送url请求
			if (!name == "" && !password == "") {
				var url = "enterpriseUser/verifyUser";
				//发送请求参数
				var args = {
					"name" : name,
					"password" : password,
					"time" : new Date()
				};
				//发送请求到页面
				$.post(url, args, function(data) {
					//把返回数据放入提示框内
					if (data == "") {
						$("#error").hide();
					} else {
						$("#error").show();
						$("#hint").text(data);
					}
				});
			}
		});

		//验证用户输入的验证码是否正确
		$("#vcode").blur(function() {
			var vcode = $(("#vcode")).val();
			var url = "enterpriseUser/verifyCode";
			var args = {
				"vcode" : vcode,
				"time" : new Date()
			};
			$.post(url, args, function(data) {
				if (data == "") {
					$("#error").hide();
				} else {
					$("#error").show();
					$("#hint").text(data);
				}
			});
		});
		$("#changeCode").click(function() {
			var url = "enterpriseUser/getcode";
			var args = {
				"time" : new Date()
			};
			$.post(url, args, function(data) {
				$("#vcode").text(data);
			});
		});

	});
</script>
<style type="text/css">
.login-body {
	background: url(resources/lanqiaoPayModel/images/3.jpg);
	height: 100%;
	width: 100%;
}
</style>
</head>

<body class="login-body">
	<div id="top"></div>
	<div id="header">
		<div class="layout">
			<div id="logo">
				<a href="http://www.lanqiao.org"> <img
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
					<form action="enterpriseUser/login" method="post">
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
								<input type="password" name="password" id="password"
									placeholder="请输入密码" />
							</dd>
						</dl>
						<p class="login-help">
							<a href="${applicationScope.url }/enterprise/toRegistA"
								class="register">立即注册</a> <a
								href="${applicationScope.url }/enterprise/toForgetPwd"
								class="forget-pwd" target="_blank">忘记密码?</a>
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
							<a href="javascript:;" class="login-captcha"> <img
								src="enterpriseUser/getcode" alt="点击刷新" onclick="refresh(this)" />

							</a>
						</div>


						<button type="submit" class="glb-btn login-btn" id="submit">
							<span>登 录</span>
						</button>


						<div class="login-error" id="error">
							<span class="iconfont icon-wrong"></span>
							<p id="hint"></p>
						</div>


					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="bottom" id="bottom"></div>
</body>
</html>
