<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>蓝桥支付-修改登陆密码</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/lanqiaoPayModel/css/global.css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/lanqiaoPayModel/css/biz/login.css" />
<link rel="shortcut icon" type="image/x-icon"
	href="${basePath }resources/lanqiaoPayModel/images/logo.png" />
<script type="text/javascript"
	src="<%=basePath%>resources/lanqiaoPayModel/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/lanqiaoPayModel/js/global.js"></script>
</head>

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
	<div class="login-wrapper">
		<div class="login-pos layout">
			<div class="login-box logined-box">
				<div class="login-bd login-opa60">
					<div class="login-tip">
						<h4 class="acc-tip"><%=request.getAttribute("msg")%></h4>
						<a href="<%=basePath%>/userController/to_safe"
							class="glb-btn login-btn"><span>点击返回安全中心</span> </a>
					</div>

				</div>
			</div>
		</div>
	</div>
	<div class="bottom" id="bottom"></div>
</body>


</html>