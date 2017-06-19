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
		//判断密保问题是否正确
		$(":submit").click(function(){
			var id = $("[name=id]").val();
			var classifiedAnswer = $("[name=classifiedAnswer]").val();
			var url = "secretController/validateSecurity";
			var args = {"time":new Date(),"id":id,"classifiedAnswer":classifiedAnswer};
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
				$("#hint").text("密保答案错误");
				$("#hint").css("color","red");
				return false;
			}else{
				$("#hint").text("密保答案正确");
				$("#hint").css("color","green");
				var newPayPassword = $("[name=newPayPassword]").val();
				var newPwdMd5 = md5(newPayPassword);
				$("[name=newPayPassword]").val(newPwdMd5);
				return true;
			}
		});	
	});
</script>
</head>

<body>
	<div id="top"></div>
	<div id="header">
		<div class="layout">
			<div id="logo">
				<a href="${applicationScope.url}/enterpriseUser/login"> <img src="resources/styles/images/logo.png" alt="蓝桥" />
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
					<span></span>
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
					href="${applicationScope.url }/enterprise/to_EnterpriseSafeCenter">安全中心</a></li>
			</ul>
		</div>
		<div class="col-main">
			<div class="main-hd">
				<h3 class="iconfont icon-menu-sm">密保找回密码</h3>
			</div>
			<div class="main-bd">
				<form class="form-bd form-pay" method="post" action="secretController/updatePayPwd" id="resetPayPwd" >
					<div class="form-item">
						<input type="hidden" name="id" id="id" value="2">
					</div>
					<div class="form-item">
						<h4 class="form-label form-pay-label">密保问题:</h4>
						<div class="form-entity">
							<div class="form-field">
								<h4 class="form-label form-pay-label">${requestScope.securityQuestion }</h4>
							</div>
						</div>
					</div>
					<div class="form-item">
						<h4 class="form-label form-pay-label">密保答案:</h4>
						<div class="form-entity">
							<div class="form-field">
								<input class="ipt" type="text" id="classifiedAnswer" name="classifiedAnswer" value=""
									placeholder="请输入问题答案" />
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
									<h4 class="form-label form-pay-label">确认密码:</h4>
									<div class="form-entity">
										<div class="form-field">
											<input class="ipt" type="password" id="newPayPassword2" name="newPayPassword2" value=""
												placeholder="请再次输入支付密码" />
											<label class="" id="hint2"></label>
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
	</div>
	<div id="bottom" class="bottom"></div>
</body>
</html>
