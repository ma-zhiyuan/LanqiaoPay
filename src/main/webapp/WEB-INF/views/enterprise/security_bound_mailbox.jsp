<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN">
<head>
<base href="<%=basePath%>">

<title>蓝桥支付-安全中心</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="">
<meta http-equiv="description" content="">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="resources/lanqiaoPayModel/css/global.css">
<link rel="stylesheet" type="text/css" href="resources/lanqiaoPayModel/css/form.css">
<link rel="stylesheet" type="text/css" href="resources/lanqiaoPayModel/css/biz/security.css">
<script type="text/javascript" src="resources/lanqiaoPayModel/js/jquery.js"></script>
<script type="text/javascript" src="resources/lanqiaoPayModel/js/global.js"></script>
<script type="text/javascript" src="resources/lanqiaoPayModel/js/util.js"></script>
<script type="text/javascript" src="resources/lanqiaoPayModel/js/group.js"></script>
<script type="text/javascript" src="resources/scripts/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="resources/scripts/md5.js"></script>
<script type="text/javascript">
	$(function(){
		//判断密码是否符合规则
		$("#payPassword").blur(function(){
			var payPassword = $("[name=payPassword]").val();
			if(payPassword==""){
				$("#hint1").text("支付密码不能为空");
				$("#hint1").css("color","red");
				return false;
			}else if(payPassword.length<6){
				$("#hint1").text("长度不能小于6");
				$("#hint1").css("color","red");
				return false;
			}else{
				$("#hint1").text("");
				$("#hint1").css("color","green");
				return true;
			}
		});
		
		//判断原密码是否正确
		$(":submit").click(function(){
			var id = $("[name=secretId]").val();
			var payPassword = $("[name=payPassword]").val();
			var md5Pwd = md5(payPassword);
			var url = "secretController/validatePayPwd";
			var args = {"time":new Date(),"id":id,"payPassword":md5Pwd};
			var tag = 0;
			$.ajax({
				type: 'POST',
				async: false,
				url: url,
				data: args,
				success: function(result){
							if(result=='true'){
								tag = 1;
							}
						 }
			});
			if(tag==0){
				$("#hint1").text("支付密码错误");
				$("#hint1").css("color","red");
				return false;
			}
			return true;
		});
	});
   
</script>
<script type="text/javascript">
	$(function() {
		//输入框失去焦点时触发
		$("#email").blur(function() {
			var id = $("#id").val();
			//获取用户输入的邮箱
			var email = $("#email").val();
			//消除字符串前后空格
			email = $.trim(email);
			var url = "secretController/registEmail";
			var args = {
				"id" : id,
				"email" : email,
				"time" : new Date() 
			};
			//发送请求，data是返回数据
			$.post(url, args, function(data) {
				//把返回的数据放到提示框中。
				$("#hint").text(data);
			});
		});
	});
</script>
</head>
<body>
	<div id="top"></div>
	<div id="header">
		<div class="layout">
			<div id="logo">
				<a href="${applicationScope.url}/enterpriseUser/login"> <img src="images/logo.png" alt="蓝桥" />
				</a>
			</div>
			<div id="nav" class="glb-nav">
				<ul class="clearfix">
					<li class="glb-nav-uc"><a href="${applicationScope.url}/enterpriseUser/login"> <span></span>
					</a></li>
					<li class="glb-nav-setting current"><a
						href="${applicationScope.url}/enterprise/to_enterpriseSafeCenter"> <span></span>
					</a></li>
					<li class="glb-nav-trade"><a href="${applicationScope.url }/enterpriseUser/enterpriseUserTradeHistory?id=${sessionScope.enterpriseUser.enterprise.id}"> <span></span>
					</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="main layout">
		<div class="col-menu">
			<ul class="menu-items">
				<li><a class="menu-txt iconfont icon-menu-um"
					href="${applicationScope.url}/enterpriseUser/getEnterpriseUser">会员资料</a></li>
				
				<li class="last"><a class="menu-txt iconfont icon-menu-bm"
					href="${applicationScope.url }/BankCardController/EnterBankCard">银行卡管理</a></li>
				<li class="current"><a class="menu-txt iconfont icon-menu-sm"
					href="${applicationScope.url}/enterprise/to_EnterpriseSafeCenter">安全中心</a></li>
			</ul>
		</div>
		<div class="col-main">
			<div class="main-hd">
				<h3 class="iconfont icon-safe-email">邮箱绑定设置</h3>
			</div>
			<div class="main-bd">
				<div class="glb-step-top">
					<ul class="glb-step-nav safe-reset-nav">
						<li class="step-a"><span class="ico"></span>
							<p class="txt">验证身份</p></li>
						<li class="direct">
							<p></p> <em></em>
						</li>
						<li class="step-b"><span class="ico"></span>
							<p class="txt">邮箱设置</p></li>
						<li class="direct">
							<p></p> <em></em>
						</li>
						<li class="step-c last"><span class="ico"></span>
							<p class="txt">设置成功</p></li>
					</ul>
				</div>
				<form class="form-bd" action="secretController/toUpdateEmail"
					id="demoForm">
					<input type="hidden" name="secretId" id="id" value="${sessionScope.enterpriseUser.secret.id}" />
					<div class="form-item">
						<h4 class="form-label">支付密码:</h4>
						<div class="form-entity">
							<div class="form-field">
								<input id="payPassword" class="ipt" type="password"
									name="payPassword" value="" placeholder="请输入您的支付密码" /> <label
									id="hint1" value=""></label>
							</div>
						</div>
					</div>
					<div class="form-item">
						<h4 class="form-label">邮箱:</h4>
						<div class="form-entity">
							<div class="form-field">
								<input id="email" class="ipt" type="text" name="email" value=""
									placeholder="请输入您的邮箱" /> <label id="hint" value=""></label>
							</div>
						</div>
					</div>
					<div class="form-action clearfix">
						<a class="reset-btn" href="secretController/to_safe">取消</a>
						<input class="glb-btn submit-btn" type="submit" value="提交" />
					</div>
				</form>
			</div>
		</div>
	</div>
	<div id="bottom" class="bottom"></div>
</body>

</html>