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
		$("#newPayPassword").blur(function(){
			var newPayPassword = $("[name=newPayPassword]").val();
			if(newPayPassword==""){
				$("#hint1").text("支付密码不能为空");
				$("#hint1").css("color","red");
				return false;
			}else if(newPayPassword.length<6){
				$("#hint1").text("长度不能小于6");
				$("#hint1").css("color","red");
				return false;
			}else{
				$("#hint1").text("");
				$("#hint1").css("color","green");
				return true;
			}
		});
		//判断第二次输入密码是否一致
		$("#newPayPassword2").blur(function(){
			var newPayPassword = $("[name=newPayPassword]").val();
			var newPayPassword2 = $("[name=newPayPassword2]").val();
			if(newPayPassword2!=newPayPassword){
				$("#hint2").text("两次密码不一致");
				$("#hint2").css("color","red");
				return false;
			}else{
				$("#hint2").text("");
				$("#hint2").css("color","green");
				return true;
			}
		});
		//判断原密码是否正确
		$(":submit").click(function(){
			var id = $("[name=id]").val();
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
				$("#hint").text("支付密码错误");
				$("#hint").css("color","red");
				return false;
			}
			var newPayPassword = $("[name=newPayPassword]").val();
			var newPwdMd5 = md5(newPayPassword);
			$("[name=newPayPassword]").val(newPwdMd5);
		});
	});
</script>
</head>

<body>
	<div id="top"></div>
	<div id="header">
		<div class="layout">
			<div id="logo">
				<a href="userController/to_uc"> <img src="resources/styles/images/logo.png" alt="蓝桥" />
				</a>
			</div>
			<div id="nav" class="glb-nav">
				<ul class="clearfix">
					<li class="glb-nav-uc"><a href="userController/to_uc"> <span></span>
					</a></li>
					<li class="glb-nav-setting current"><a
						href="secretController/to_safe"> <span></span>
					</a></li>
					<li class="glb-nav-trade"><a href="userController/userTradeHistory?id=${sessionScope.user.id}"> <span></span>
					</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="main layout">
		<div class="col-menu">
			<ul class="menu-items">
				<li><a class="menu-txt iconfont icon-menu-um"
					href="<%=basePath %>/userController/goUserInformation">会员资料</a></li>
				<li class="current"><a class="menu-txt iconfont icon-menu-sm"
					href="<%=basePath %>/secretController/to_safe">安全中心</a></li>
				<li class="last"><a class="menu-txt iconfont icon-menu-bm"
					href="<%=basePath %>/BankCardController/my_card_list">银行卡管理</a></li>
			</ul>
		</div>
		<div class="col-main">
			<div class="main-hd">
				<h3 class="iconfont icon-menu-sm">支付密码设置</h3>
			</div>
			<div class="main-bd">
				<div class="main-top-tip">
					<h3 class="title">
						当前绑定手机为: <span class="em">${sessionScope.call}</span>
					</h3>
				</div>
				<div class="items-group-box group-fold-only group-fold-default">
					<div class="item-group form-group">
						<div class="group-hd clearfix">
							<div class="group-hd-txt">
								<h3>修改支付密码</h3>
								<p>修改支付密码选择此方式</p>
							</div>
						</div>
						<div class="group-bd type-items">
							<form class="form-bd form-pay" method="post" action="secretController/updatePayPwd" id="updatePayPwd" >
								<div class="form-item">
									<input type="hidden" name="id" id="id" value="${sessionScope.user.secretId.id }">
								</div>
								<div class="form-item">
									<h4 class="form-label form-pay-label">原支付密码:</h4>
									<div class="form-entity">
										<div class="form-field">
											<input class="ipt" type="password" id="payPassword" name="payPassword" value=""
												placeholder="请输入原支付密码" />
											<label class="" id="hint"></label>
										</div>
									</div>
								</div>
								<div class="form-item">
									<h4 class="form-label form-pay-label">新密码:</h4>
									<div class="form-entity">
										<div class="form-field">
											<input class="ipt" type="password" id="newPayPassword" name="newPayPassword" value=""
												placeholder="请输入新的支付密码" />
												<label class="" id="hint1"></label>
										</div>
									</div>
								</div>
								<div class="form-item">
									<h4 class="form-label form-pay-label">再次输入密码:</h4>
									<div class="form-entity">
										<div class="form-field">
											<input class="ipt" type="password" id="newPayPassword2" name="newPayPassword2" value=""
												placeholder="请再次输入支付密码" />
											<label class="" id="hint2"></label>
										</div>
									</div>
								</div>
								<div class="form-item" style="display:none" >
									<h4 class="form-label form-pay-label">校验码:</h4>
									<div class="form-entity">
										<div class="form-field">
											<div class="form-twin-group">
												<input class="ipt ipt-code" type="text" name=""
													value="" placeholder="邮箱校验码" />
											</div>
											<div class="form-twin-group">
												<a href="javascript:;" class="cmn-btn cmn-btn-disabled">发送验证码</a>
											</div>
										</div>
									</div>
								</div>
								<div class="form-action form-action-pay clearfix">
									<a class="reset-btn" href="secretController/to_safe">取消</a> 
									<input type="submit" class="glb-btn submit-btn" id="submit" value="提交"> 
								</div>
							</form>
						</div>
					</div>
					<div class="item-group form-group">
						<div class="group-hd clearfix">
							<div class="group-hd-txt">
								<h3>我忘记支付密码了</h3>
								<p>忘记了密码或是密码被锁定了</p>
							</div>
						</div>
						<ul class="group-bd type-items">
							<li class="form-item clearfix"><span
								class="item-ico iconfont icon-safe-mobile"></span>
								<div class="item-desc">
									<h3>手机号找回密码</h3>
									<p>通过绑定的手机号找回密码,请选择此方式.</p>
								</div>
								<div class="item-action">
									<a href="security_mobileVerity_step_a"
										class="glb-btn item-btn"> <span>立即重置</span>
									</a>
								</div>
							</li>
							<li class="clearfix"><span
								class="item-ico iconfont icon-safe-email"></span>
								<div class="item-desc">
									<h3>邮箱找回密码</h3>
									<p>通过绑定的邮箱找回密码,请选择此方式.</p>
								</div>
								<div class="item-action">
									<a href="userController/toReSettingPasswordByEmail"
										class="glb-btn item-btn"> <span>立即重置</span>
									</a>
								</div>
							</li>
							<li class="clearfix"><span
								class="item-ico iconfont icon-safe-pwd"></span>
								<div class="item-desc">
									<h3>密保找回密码</h3>
									<p>通过设置的安全保护问题找回密码,请选择此方式.</p>
								</div>
								<div class="item-action">
									<a href="secretController/toReSettingPasswordByProtect?id=${sessionScope.user.secretId.id }"
										class="glb-btn item-btn"> <span>立即重置</span>
									</a>
								</div>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="bottom" class="bottom"></div>
</body>
</html>
