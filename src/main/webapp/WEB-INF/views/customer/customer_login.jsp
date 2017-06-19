<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<title>客服登陆</title>
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
		$("#password").blur(function() {
			var md5Str = md5($("#password").val());
			($("#password")).val(md5Str);
		});
	});
</script>

</head>
<!-- body start-->
<body background="<%=basePath%>resources/styles/images/a.jpg">
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
	<div class="glb-page glb-wrapper" align="center" >
		<div class="glb-layout">
			<div class="glb-col-p60 glb-center">
				<div class="login-bd login-opa60">
					<h3 class="login-title">登录</h3>
					<form action="customerServiceController/loginCustomer"
						method="post">
						<dl class="login-item">
							<dt>
								<span class="iconfont icon-acc-user"></span>
							</dt>
							<dd>
								<input type="text" name="id" id="id" placeholder="请输入客服ID" />
							</dd>
						</dl>
						<dl class="login-item">
							<dt>
								<span class="iconfont icon-acc-pwd"></span>
							</dt>
							<dd>
								<input type="password" id="password" name="password" value=""
									placeholder="请输入密码" />
							</dd>
						</dl>

						<p class="login-help">
							<a href="reg_personal_email_a.html" class="register">立即注册</a> <a
								href="forget_password_a.html" class="forget-pwd" target="_blank">忘记密码?</a>
						</p>
						<div class="login-code clearfix"></div>
						<input type="submit" value=“登录”> </a>
						<div class="login-error">
							<span class="iconfont icon-wrong"></span>
							<p>请输入用户名</p>
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
