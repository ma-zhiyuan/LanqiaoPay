<%@ page language="java" pageEncoding="UTF-8"  import="java.util.*" %>
<%	Date date = new Date();
	String token = ""+date.getTime();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>蓝桥银行 - 会员注册</title>
		<meta name="keywords" content="" />
		<meta name="description" content="" />
		<link rel="stylesheet" type="text/css" href="${applicationScope.url}/resources/lanqiaoPayModel/css/global.css"/>
		<link rel="stylesheet" type="text/css" href="${applicationScope.url}/resources/lanqiaoPayModel/css/form.css"/>
		<link rel="stylesheet" type="text/css" href="${applicationScope.url}/resources/lanqiaoPayModel/css/biz/reg.css"/>
		<script type="text/javascript" src="${applicationScope.url}/resources/scripts/jquery.js"></script>
		<script type="text/javascript" src="${applicationScope.url}/resources/lanqiaoPayModel/js/global.js"></script>
		<script type="text/javascript" src="${applicationScope.url}/resources/lanqiaoPayModel/js/util.js"></script>

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
						<li class="step">
							<p class="arr arr-before"></p>
							<p class="txt">3. 密码设置</p>
							<p class="arr arr-after"></p>
						</li>
						<li class="step last current">
							<p class="arr arr-before"></p>
							<p class="txt">4. 注册成功,等待审核</p>
						</li>
					</ul>
				</div>
				<div class="glb-alert-box glb-alert-succ reg-alert-box">
					<p class="iconfont icon-glb-right"></p>
					<div class="glb-alert-msg">
						<p class="hd"> <em>您已成功注册蓝桥支付会员!!</em>
						</p>
						<p class="bd">
							系统将在3秒后自动跳转至蓝桥银行。立即进入<a href="login.html">蓝桥支付</a>
						</p>
						<p class="bd">
							温馨提示:<span class="em">用户名和密码</span>是您登录系统的唯一凭证,请您牢记!
						</p>
					</div>
				</div>
			</div>a
			<div id="bottom" class="bottom">
			</div>
		</div>
	</body>

</html>