<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'reg_common_e.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	    <link rel="stylesheet" type="text/css" href="<%=basePath %>resources/lanqiaoPayModel/css/global.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/lanqiaoPayModel/css/form.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/lanqiaoPayModel/css/biz/reg.css">
		<script type="text/javascript" src="<%=basePath %>resources/lanqiaoPayModel/js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath %>resources/lanqiaoPayModel/js/global.js"></script>
		<script type="text/javascript" src="<%=basePath %>resources/lanqiaoPayModel/js/util.js"></script>
	</head>

	<body>
		<div id="top">
		</div>
		<div id="header">
			<div class="layout">
				<div id="logo">
					<a href="uc.html">
						<img src="<%=basePath %>resources/lanqiaoPayModel/images/logo.png" alt="蓝桥" />
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
							系统将在3秒后自动跳转至蓝桥支付。立即进入<a href="userLogin.jsp">蓝桥支付</a>
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