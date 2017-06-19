<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>蓝桥支付-安全中心</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link rel="stylesheet" type="text/css"
	href="${applicationScope.url}/resources/lanqiaoPayModel/css/global.css"></link>
<link rel="stylesheet" type="text/css"
	href="${applicationScope.url}/resources/lanqiaoPayModel/css/form.css"></link>
<link rel="stylesheet" type="text/css"
	href="${applicationScope.url}/resources/lanqiaoPayModel/css/biz/security.css"></link>
<script type="text/javascript"
	src="${applicationScope.url}/resources/lanqiaoPayModel/js/jquery.js"></script>
<script type="text/javascript"
	src="${applicationScope.url}/resources/lanqiaoPayModel/js/global.js"></script>
<script type="text/javascript"
	src="${applicationScope.url}/resources/lanqiaoPayModel/js/util.js"></script>
<script type="text/javascript"
	src="${applicationScope.url}/resources/lanqiaoPayModel/js/md5.js"></script>
<script type="text/javascript">
	var linkManPhoneOk = 0;
	var payPasswordOK = 0;
	/*  设置支付密码约束  只允许6位数字*/
	function CheckPayPassword(payPassword) {
		var pwd = $.trim(payPassword.val());
		if (pwd == "") {
			$("#ppwd").html("密码不能为空");
			$("#ppwd").css("color", "red");
			return false;
		}
		if (isNaN(pwd)) {
			$("#ppwd").html("只能是数字密码");
			$("#ppwd").css("color", "red");
			return false;
		}
		if (pwd.length > 6 || pwd.length < 6) {
			$("#ppwd").html("只能是6位密码");
			$("#ppwd").css("color", "red");
			return false;
		} else {
			$("#ppwd").html("");
			payPasswordOK = 1;
			$("#ppwd").css("color", "green");
			return true;
		}
	};
	$(function(){
	//判断原密码是否正确
		$(":submit").click(function(){
			var id = $("[name=secretId]").val();
			var payPassword = $("[name=payPassword]").val();
			var md5Pwd = md5(payPassword);
			var url = "${applicationScope.url}/secretController/validatePayPwd";
			var args = {"id":id,"payPassword":md5Pwd};
			$.ajax({
					async : false,
					cache : false,
					url : url,
					success: function(data){
						if(data == "true"){
							payPasswordOK = 1;
						}
					},
					type:"POST",
					data : args
				});
				
			if(payPasswordOK==0){
				$("#hint1").text("支付密码错误");
				$("#hint1").css("color","red");
				return false;
			}
			if(linkManPhoneOk == 0)
			{
				alert("手机号不合法！")
				return false;
			}
			return true;
		});
	
	
	});
	 	

	//验证常用联系人电话非空且合法
	function CheckLinkManPhone(linkManPhone) {
		var phone = $.trim(linkManPhone.val());
		valid = /^0?(12[0-9]|13[0-9]|14[57]|15[012356789]|16[0-9]|17[0-9]|18[0-9]|19[0-9])[0-9]{8}$/;
		phone = $.trim(phone);
		if (phone == "") {
			$("#linkManAndPhone-msg").text("常用联系人电话不能为空");
		} else if (!valid.test(phone)) {
			$("#linkManAndPhone-msg").text("常用联系人电话格式错误");
		} else {
			$("#linkManAndPhone-msg").text("");
			var url = "${applicationScope.url}/enterprise/verifyPhone";
			var args = {
				"time" : new Date(),
				"phone" : phone
			};
			$.post(url, args, function(data) {
				if(data == ""){
					linkManPhoneOk = 1;
				}
				$("#linkManAndPhone-msg").text(data);
			});
		}
	};
	
	
				
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
					</a></li><span></span>
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
				<li class="current"><a class="menu-txt iconfont icon-menu-sm"
					href="${applicationScope.url}/enterprise/to_enterpriseSafeCenter">安全中心</a></li>
				<li class="last"><a class="menu-txt iconfont icon-menu-bm"
					href="${applicationScope.url }/BankCardController/EnterBankCard">银行卡管理</a></li>
			</ul>
		</div>
		<div class="col-main">
			<div class="main-hd">
				<h3 class="iconfont icon-safe-mobile">手机号绑定设置</h3>
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
							<p class="txt">手机号设置</p></li>
						<li class="direct">
							<p></p> <em></em>
						</li>
						<li class="step-c last"><span class="ico"></span>
							<p class="txt">设置成功</p></li>
					</ul>
				</div>
				<input type="hidden" id="secretId" value="${ sessionScope.enterpriseUser.secret.id}"/>
				<form class="form-bd" action="toUpdatePhone" id="demoForm">
				<input type="hidden" name="id" id="id" value="${ sessionScope.enterpriseUser.id} " />
					<div class="form-item">
						<h4 class="form-label">支付密码:</h4>
						<div class="form-entity">
							<div class="form-field">
								<input id="payPassword" class="ipt" type="password"
									name="payPassword" value="" placeholder="密码是六位数字"
									onblur="CheckPayPassword($('#payPassword'));" /> &nbsp;&nbsp;<span
									id="ppwd"></span><label id="hint1" value=""></label>
							</div>
						</div>
					</div>
					<div class="form-item">
						<h4 class="form-label">手机号码:</h4>
						<div class="form-entity">
							<div class="form-field">
								<input class="ipt " type="text" id="linkManPhone"
									name="linkManPhone" value="" placeholder="请输入联系人手机号"
									onblur="CheckLinkManPhone($('#linkManPhone'));" /> <span
									id="linkManAndPhone-msg" style="color: red"></span>
									<label id="hint2" value=""></label>
							</div>
						</div>
					</div>
					<!--
					<div class="form-item">
						<h4 class="form-label">校验码:</h4>
						<div class="form-entity">
							<div class="form-field">
								<div class="form-twin-group">
									<input class="ipt ipt-code" type="text" id="number"
										name="userName" value="" placeholder="校验码" />
								</div>
								<div class="form-twin-group">
								<input id="email" name="email" type="hidden" value="${ sessionScope.enterpriseUser.email} "/>
								
									<a href=";" class="cmn-btn cmn-btn-disabled">发送验证码</a>
								</div>
							</div>
						</div>
					</div>
					  -->
					<div class="form-action clearfix">
						<a class="reset-btn" href="${applicationScope.url}/secretController/to_safe">取消</a>
						<input class="glb-btn submit-btn" type="submit" value="提交" />
					</div>
				</form>
			</div>
		</div>
	</div>
	<div id="bottom" class="bottom"></div>
</body>
</html>