<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>蓝桥支付-会员资料</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link rel="stylesheet" type="text/css"
	href="${applicationScope.url}/resources/lanqiaoPayModel/css/global.css">
<link rel="stylesheet" type="text/css"
	href="${applicationScope.url}/resources/lanqiaoPayModel/css/form.css">
<link rel="stylesheet" type="text/css"
	href="${applicationScope.url}/resources/lanqiaoPayModel/css/biz/security.css">
<script type="text/javascript"
	src="${applicationScope.url}/resources/lanqiaoPayModel/js/jquery.js"></script>
<script type="text/javascript"
	src="${applicationScope.url}/resources/lanqiaoPayModel/js/global.js"></script>
<script type="text/javascript"
	src="${applicationScope.url}/resources/lanqiaoPayModel/js/util.js"></script>


</head>

<body>
	<div id="top"></div>
	<div id="header">
		<div class="layout">
			<div id="logo">
				<a href="uc.html"> <img
					src="${applicationScope.url}/resources/lanqiaoPayModel/images/logo.png"
					alt="蓝桥" />
				</a>
			</div>
			<div id="nav" class="glb-nav">
				<ul class="clearfix">
					<li class="glb-nav-uc"><a href="uc.html"> <span></span>
					</a></li>
					<li class="glb-nav-setting current"><a
						href="member_information.html"> <span></span>
					</a></li>
					<li class="glb-nav-trade"><a href="trade_list.html"> <span></span>
					</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="main layout">
		<div class="col-menu">
			<ul class="menu-items">
				<li class="last current"><a
					class="menu-txt iconfont icon-menu-um"
					href="userController/goUserInformation">用户资料</a></li>
				<li><a class="menu-txt iconfont icon-menu-sm"
					href="security.html">安全中心</a></li>
				<li><a class="menu-txt iconfont icon-menu-bm"
					href="my_card_list.html">银行卡管理</a></li>
			</ul>
		</div>
		<div class="col-main">
			<div class="main-hd">
				<h3 class="iconfont icon-safe-service">用户资料</h3>
			</div>
			<div class="main-bd account-infor clearfix">
				<div class="account-img">
					<img
						src="${sessionScope.user.sufface}"
						alt="" /><br>
				</div>
			<form action="${applicationScope.url}/userController/updateMyPhoto" method="post"  enctype="multipart/form-data">
				<input type="file" id="file1" name="file1">
				<input type="submit" value="确认上传">
			</form>
				</div></div></div>
	<div id="bottom" class="bottom"></div>
</body>

</html>