<%@ page language="java" pageEncoding="UTF-8"  import="java.util.*" %>
<%	Date date = new Date();
	String token = ""+date.getTime();
 %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>蓝桥银行 - 密码找回</title>
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
							<p class="txt">1. 邮箱验证</p>
							<p class="arr arr-after"></p>
						</li>
						<li class="step">
							<p class="arr arr-before"></p>
							<p class="txt">2. 修改密码 </p>
							<p class="arr arr-after"></p>
						</li>
						<li class="step last current">
							<p class="arr arr-before"></p>
							<p class="txt">4. 修改成功</p>
						</li>
					</ul>
				</div>
				<div class="glb-alert-box glb-alert-succ reg-alert-box">
					<p class="iconfont icon-glb-right"></p>
					<div class="glb-alert-msg">
						<c:if test="${requestScope.msg eq '修改密码失败'}">
							<p class="hd"> <em>修改密码失败，请返回重新修改!!</em>
							</p>
						</c:if>
						<c:if test="${requestScope.msg eq '修改密码成功'}">
							<p class="hd"> <em>您已成功修改密码!!</em>
							</p>
						</c:if>
						<p class="bd">
							<a href="${applicationScope.url}/userLogin.jsp">返回用户登录页面</a>
						</p>
						<p class="bd">
							温馨提示:<span class="em">用户名和密码</span>是您登录系统的唯一凭证,请您牢记!
						</p>
					</div>
				</div>
			</div>
			<div id="bottom" class="bottom">
			</div>
		</div>
	</body>

</html>