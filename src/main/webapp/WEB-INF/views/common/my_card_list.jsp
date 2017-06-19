<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/lanqiaoPayModel/css/global.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/lanqiaoPayModel/css/form.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/lanqiaoPayModel/css/dialog.css">

<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/lanqiaoPayModel/css/biz/uc.css">
<script type="text/javascript"
	src="<%=basePath%>resources/lanqiaoPayModel/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/lanqiaoPayModel/js/global.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/lanqiaoPayModel/js/util.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/lanqiaoPayModel/js/animation.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/lanqiaoPayModel/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/lanqiaoPayModel/js/dialog.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/liuj/loginDialog.css">
<script type="text/javascript"
	src="<%=basePath%>resources/liuj/jquery-1.8.3.min.js"></script>

<script type="text/javascript">
	
</script>
<script type="text/javascript">
	
	</script>
<script type="text/javascript"> 
</script>
</head>

<body>
	<div id="top"></div>
	<div id="header">
		<div class="layout">
			<div id="logo">
				<a href="uc.html"> <img
					src="<%=basePath%>resources/lanqiaoPayModel/images/logo.png"
					alt="蓝桥" />
				</a>
			</div>
			<div id="nav" class="glb-nav">
				<ul class="clearfix">
					<li class="glb-nav-uc"><a href="userController/to_uc"> <span></span>
					</a></li>
					<li class="glb-nav-setting current"><a
						href="secretController/to_safe"> <span></span>
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
				<li></li>
				<li><a class="menu-txt iconfont icon-menu-um"
					href="userController/goUserInformation">会员资料</a></li>
				<li><a class="menu-txt iconfont icon-menu-sm"
					href="userController/to_safe">安全中心</a></li>
				<li class="last current"><a
					class="menu-txt iconfont icon-menu-bm" href="BankCardController/my_card_list">银行卡管理</a>
				</li>
			</ul>
		</div>
		<div class="col-main">
			<div class="main-hd">
				<h3 class="iconfont icon-menu-bm">我的银行卡</h3>
			</div>
			<div class="main-bd">
			<c:choose>
			<c:when test="${fn:length(sessionScope.user.listBankCard)}==0">
			
				<div class="content-hd">
					<p class="tips">
					<span id="isValid-show"></span>
						您当前未绑定银行卡 <a href="BankCardController/to_userAddBankCard"
							id="applyNewCard">添加新的银行卡</a>
					<div id="LoginBox">
						
					</div>
					</p>
				</div>
			
			
			</c:when>
			<c:otherwise> 
			
				<div class="content-hd">
					<p class="tips">
					<span id="isValid-show"></span>
						您当前已绑定<span class="em">${fn:length(sessionScope.user.listBankCard)}</span>张银行卡 <a href="BankCardController/to_userAddBankCard"
							id="applyNewCard">添加新的银行卡</a>
					<div id="LoginBox">
						
					</div>
					</p>
				</div>
				<div class="content-bd">
					<ul class="card-items">
						<c:forEach items="${sessionScope.user.listBankCard}" var="ca">
							<li class="df-show">
								<div class="card-layout card-top">
									<div class="card-col-l">
										<div class="bank-ico bank-ccb"></div>
									</div>
									<p class="card-col-r card-no">${ca.cardNumber }</p>
								</div>
								<div class="card-layout card-mid">
									<p class="card-col-l card-time">申请日期:${ca.applicationTime }</p>
									<div class="card-col-r">
										<a class="card-opt card-opt-hide"
											href="BankCardController/removeBankCard?id=${ca.id}">删除</a>
									</div>
								</div>
								<div class="card-layout card-btm clearfix">
									<div class="card-col-l card-pay-l">
										<p>快捷支付&nbsp;&nbsp;${ca.isQuickPay eq 0?"未开通":"已开通"}</p>
									</div>
									<div class="card-pay-l">
										<c:if test="${ca.isQuickPay==1 }">
											<a href="BankCardController/shutQuickPay?id=${ca.id}">关闭</a>
										</c:if>
										<c:if test="${ca.isQuickPay==0}">
											<a href="BankCardController/openQuickPay?id=${ca.id}">开启</a>
										</c:if>

									</div>
									<div class="card-col-r">
										<c:if test="${ca.isDefault==1 }">
											<p class="card-txt">默认银行卡</p>
										</c:if>
										<c:if test="${ca.isDefault==0}">
											<a class="card-opt"
												href="BankCardController/isDefault?id=${ca.id}">设为默认银行卡</a>
										</c:if>
									</div>
								</div>
							</li>
						</c:forEach>

					</ul>
				</div>
				</c:otherwise>
			</c:choose>
			</div>
		</div>
	</div>
	<div id="bottom" class="bottom"></div>
</body>

</html>