<%@page import="org.lanqiao.pay.base.entity.Enterprise"%>
<%@page import="org.springframework.web.context.request.RequestScope"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'safe.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN">
  <head>
    <base href="<%=basePath%>">
    
    <title>蓝桥支付-安全中心</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/lanqiaoPayModel/css/global.css"">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/lanqiaoPayModel/css/biz/security.css">
	<script type="text/javascript" src="<%=basePath %>resources/lanqiaoPayModel/js/jquery.js"></script>
	<script type="text/javascript" src="<%=basePath %>resources/lanqiaoPayModel/js/global.js""></script>
	<script type="text/javascript" src="<%=basePath %>resources/lanqiaoPayModel/js/util.js"></script>
  </head>
  
  <body>
    <div id="top"></div>
		<div id="header">
			<div class="layout">
				<div id="logo">
					<a href="userController/to_uc">
						<img src="<%=basePath %>resources/lanqiaoPayModel/images/logo.png" alt="蓝桥"/>
					</a>
				</div>
				<div id="nav" class="glb-nav">
					<ul class="clearfix">
						<li class="glb-nav-uc">
							<a href="userController/to_uc">
								<span></span>
							</a>
						</li>
						<li class="glb-nav-setting current">
							<a href="secretController/to_safe">
								<span></span>
							</a>
						</li>
						<li class="glb-nav-trade">
							<a href="userController/userTradeHistory?id=${sessionScope.user.id}">
								<span></span>
							</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="main layout">
			<div class="col-menu">
				<ul class="menu-items">
					<li>
						<a class="menu-txt iconfont icon-menu-um" href="userController/goUserInformation">会员资料</a>
					</li>
					<li class="current">
						<a class="menu-txt iconfont icon-menu-sm" href="secretController/to_safe">安全中心</a>
					</li>
					<li class="last">
						<a class="menu-txt iconfont icon-menu-bm" href="BankCardController/my_card_list">银行卡管理</a>
					</li>
				</ul>
			</div>
			<div class="col-main">
				<div class="main-hd">
					<h3 class="iconfont icon-menu-sm">个人安全中心主页</h3>
				</div>
				<div class="main-bd">
					<ul class="security-items clearfix">
						<li class="item-login-pwd">
							<div class="col-item-main">
								<h4 class="item-ico"></h4>
								<h3 class="item-name">登录密码</h3>
								<p class="item-tips">登录蓝桥支付账户时需要输入的密码, 是您登录的唯一凭证.</p>
								<div class="item-action">
									<a href="userController/toModifyLoginPwd">修改</a>
								</div>
							</div>
							<div class="col-item-side safe-protected">
								<p class="safe-icon"></p>
								<p class="safe-text">保护中</p>
							</div>
						</li>
						<li class="item-pay-pwd item-even">
							<div class="col-item-main">
								<h4 class="item-ico"></h4>
								<h3 class="item-name">支付密码</h3>
								<p class="item-tips">在账户资金变动、修改账户信息时需要输入的密码.</p>
								<div class="item-action">
									<a href="secretController/skipUpdatePayPwd">设置</a>
								</div>
							</div>
							<div class="col-item-side safe-protected">
								<p class="safe-icon"></p>
								<p class="safe-text">保护中</p>
							</div>
						</li>
						<li class="item-mobile">
							<div class="col-item-main">
								<h4 class="item-ico"></h4>
								<h3 class="item-name">手机绑定</h3>
								<p class="item-tips">您当前未绑定手机. 绑定后可通过手机找回密码, 使用手机验证码进行账户操作</p>
								<div class="item-action">
									<a href="#">立即绑定</a>
								</div>
							</div>
							<div class="col-item-side safe-protected">
								<p class="safe-icon"></p>
								<p class="safe-text">保护中</p>
							</div>
						</li>
						<li class="item-email item-even">
							<div class="col-item-main">
								<h4 class="item-ico"></h4>
								<h3 class="item-name">邮箱</h3>
								<p class="item-tips">您已绑定邮箱: qx*****z@163.com</p>
								<div class="item-action">
									<a href="userController/toUpdate">修改</a>
								</div>
							</div>
							<div class="col-item-side safe-protected">
								<p class="safe-icon"></p>
								<p class="safe-text">保护中</p>
							</div>
						</li>
						<li class="item-secur-qa item-last">
							<div class="col-item-main">
								<h4 class="item-ico"></h4>
								<h3 class="item-name">安全保护问题</h3>
								<p class="item-tips">你当前已设置安全保护问题, 设置后可以通过回答已设置安全保护问题修改账户信息.</p>
								<div class="item-action">
									<a href="secretController/safe_secret">设置</a>
								</div>
							</div>
							<div class="col-item-side safe-protected">
								<p class="safe-icon"></p>
								<p class="safe-text">保护中</p>
							</div>
						</li>
					</ul>
				</div>
			</div>
		
	  </div>
	<div id="bottom" class="bottom"></div>
  </body>
</html>
  </body>
</html>
