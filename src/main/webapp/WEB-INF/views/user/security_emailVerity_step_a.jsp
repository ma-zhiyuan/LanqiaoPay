<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>蓝桥支付-安全中心</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/lanqiaoPayModel/css/global.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/lanqiaoPayModel/css/form.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/lanqiaoPayModel/css/biz/security.css">
<script type="text/javascript"
	src="<%=basePath%>resources/lanqiaoPayModel/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/lanqiaoPayModel/js/global.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/lanqiaoPayModel/js/util.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/scripts/jquery-3.1.1.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/scripts/md5.js"></script>
<script type="text/javascript">
	$(function() {
		// 输入框失去焦点时触发
		   $("#email").blur(function(){
		      var emailVal = $("#email").val();
		      emailVal = $.trim(emailVal);
		      //邮箱的正则表达式
		      var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
		      // ajax发送时请求的地址
		      var url = "<%=request.getContextPath() %>/userController/isUserEmail";
		      // 请求携带的参数
		      var args = {"time":new Date(),"email":emailVal};
		      if(emailVal==""){
						$("#hint").text("邮箱不能为空！");
						$("#hint").css("color","red");
						$("#email").focus();
						return false;
		      } else if(!reg.test(emailVal)){
						$("#hint").text("邮箱格式不正确！");
						$("#hint").css("color","red");
						$("#email").focus();
            			return false;
        	  }else{
        	            $.post(url,args,function(data){
        	            if(data== "邮箱输入不正确"){
        	                $("#hint").text("邮箱输入不正确!");
							$("#hint").css("color","red");
							$("#email").focus();
            				return false;
        	           }
        	     });
        	  }
       });
       });
</script>
<script type="text/javascript">
	$(function() {
		
		// 判断前台传入的新密码是否符合规则,并进行MD5的加密
			$(":submit").click(function(){
				var payPassword = $("[name=newPayPassword]").val();
				var pwd1 = $("[name=newPayPassword]").val(md5(payPassword));
				var payPassword2 = $("[name= newPayPassword2]").val();
				var pwd2 = $("[name=newPayPassword2]").val(md5(payPassword2));
				  if(payPassword.length < 6){
				   $("#hint1").text("密码长度不能小于6");
				   $("#hint1").css("color","red");
				   return false;
				} else if(payPassword != payPassword2){
				   return false;
				}
		  });
		//判断两次输入密码是否一致
		$("#newPayPassword2").blur(function() {
			var newPayPassword = $("[name=newPayPassword]").val();
			var newPayPassword2 = $("[name=newPayPassword2]").val();
			if (newPayPassword2 != newPayPassword) {
				$("#hint2").text("两次密码不一致");
				$("#hint2").css("color", "red");
				return false;
			} else {
				$("#hint2").text("");
				$("#hint2").css("color", "green");
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
				<a href="to_uc"> <img
					src="<%=basePath%>resources/styles/images/logo.png" alt="蓝桥" />
				</a>
			</div>
			<div id="nav" class="glb-nav">
				<ul class="clearfix">
					<li class="glb-nav-uc"><a href="to_uc"> <span></span>
					</a></li>
					<li class="glb-nav-setting current"><a
						href="to_safe"> <span></span>
					</a></li>
					<li class="glb-nav-trade"><a href="userTradeHistory?id=${sessionScope.user.id}"> <span></span>
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
				<h3 class="iconfont icon-safe-email">邮箱重置密码</h3>
			</div>
			<div class="main-bd">
				<div class="glb-step-top">
					<ul class="glb-step-nav safe-reset-nav">
						<li class="step-a"><span class="ico"></span>
							<p class="txt">邮箱验证</p></li>
						<li class="direct">
							<p></p> <em></em>
						</li>
						<li class="step-b"><span class="ico"></span>
							<p class="txt">密码重置</p></li>
						<li class="direct">
							<p></p> <em></em>
						</li>
						<li class="step-c last"><span class="ico"></span>
							<p class="txt">重置成功</p></li>
					</ul>
				</div>
				<form class="form-bd" action="reSettingPayPassword" id="demoForm" method="post">
				  <input type="hidden" name="id" id="id" value="${sessionScope.user.id }" />
					<div class="form-item">
						<h4 class="form-label">邮箱：</h4>
						<div class="form-entity">
							<div class="form-field">
								<input id="email" class="ipt" type="text"
									name="email" value="" placeholder="请输入您的邮箱地址" />
									<label id="hint" value=""></label>
							</div>
						</div>
					</div>
					<div class="form-item">
						<h4 class="form-label">新密码：</h4>
						<div class="form-entity">
							<div class="form-field">
								<input class="ipt" type="password" id="newPayPassword"
									name="newPayPassword" value="" placeholder="请输入新密码" /> 
									<label id="hint1"></label>
							</div>
						</div>
					</div>
					<div class="form-item">
						<h4 class="form-label">确认密码：</h4>
						<div class="form-entity">
							<div class="form-field">
								<input class="ipt" type="password" id="newPayPassword2"
									name="newPayPassword2" value="" placeholder="请再次输入新密码" />
									<label id="hint2"></label>
							</div>
						</div>
					</div>
					<div class="form-action form-action-pay clearfix">
									<a class="reset-btn" href="to_safe">取消</a> 
									<input type="submit" class="glb-btn submit-btn" id="submit" value="提交"> 
								</div>
				</form>
			</div>
		</div>
	</div>
	<div id="bottom" class="bottom"></div>
</body>

</html>