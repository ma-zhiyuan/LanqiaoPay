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
<script type="text/javascript">
		  
		//在页面加载时，调用查询用户银行卡数量的函数
		window.onload=function tt(){
			bankCard();
		}
		//查询用户银行卡数目的函数
		function bankCard(){
			//获取用户id
			var userId=${sessionScope.user.id};
			//ajax的url和args
			var url="<%=request.getContextPath()%>/userController/getBankCard";
			var args={"userId":userId,"time":new Date()};
			//ajax请求查询用户的银行卡
			$.post(url,args,function(data){
				$("#bankCard").text(data)				
			});
		}
	</script>
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
					<li class="glb-nav-uc"><a href="${applicationScope.url}/userController/to_uc"> <span></span>
					</a></li>
					<li class="glb-nav-setting current"><a
						href="${applicationScope.url}/secretController/to_safe"> <span></span>
					</a></li>
					<li class="glb-nav-trade"><a href="${applicationScope.url}/userController/userTradeHistory?id=${sessionScope.user.id}"> <span></span>
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
					href="${applicationScope.url}/userController/goUserInformation">用户资料</a></li>
				<li><a class="menu-txt iconfont icon-menu-sm"
					href="${applicationScope.url}/secretController/to_safe">安全中心</a></li>
				<li><a class="menu-txt iconfont icon-menu-bm"
					href="${applicationScope.url}/BankCardController/my_card_list">银行卡管理</a></li>
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
						alt=""  height="80" width="80"/><br>
				
					<a
						href="${applicationScope.url}/userController/goUserModifyPicture">修改头像</a>
				</div>
				<div class="account-table">
					<table>
					

						<tr>
							<th>用戶名:</th>
							<td>${sessionScope.user.name }</td>
							<td class="col" ><a href="${applicationScope.url}/userController/goUpdateName" >修改</a></td>
						</tr>
						<tr>
							<th>账户状态:</th>
							<td colspan="2"><c:if
									test="${empty sessionScope.user.cardId }">还未<a href="#">实名认证</a>
								</c:if> <c:if test="${!empty sessionScope.user.cardId }">已实名认证</c:if></td>
						</tr>
						<tr>
							<th>绑定手机号:</th>
							<td>${sessionScope.user.call }</td>
							<td class="col"><a href="#">修改</a></td>
						</tr>
						<tr>
							<th>绑定邮箱:</th>
							<td>${sessionScope.user.email }</td>
							<td class="col"><a href="#">修改</a></td>
						</tr>
						<tr>
							<th>银行卡:</th>
							<td><span id="bankCard" class="bind"></span></td>

							<td class="col"><a href="<%=request.getContextPath() %>/BankCardController/my_card_list">查看</a></td>
						</tr>
						<tr>
							<th>注册时间:</th>
							<td colspan="2">${sessionScope.user.registration }</td>
						</tr>
					</table>
				</div>
				<a href="#" class="glb-btn submit-btn"> <span>申请成为商户</span>
				</a>
			</div>
		</div>
	</div>
	<div id="bottom" class="bottom"></div>
</body>

</html>