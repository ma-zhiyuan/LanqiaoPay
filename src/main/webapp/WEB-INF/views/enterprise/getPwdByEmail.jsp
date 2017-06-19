<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>蓝桥支付-安全中心</title>
		<meta name="keywords" content="" />
		<meta name="description" content="" />
		<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/lanqiaoPayModel/css/global.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/lanqiaoPayModel/css/form.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/lanqiaoPayModel/css/biz/security.css">
		<script type="text/javascript" src="<%=basePath %>resources/lanqiaoPayModel/js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath %>resources/lanqiaoPayModel/js/global.js"></script>
		<script type="text/javascript" src="<%=basePath %>resources/lanqiaoPayModel/js/util.js"></script>
		<script type="text/javascript" src="<%=basePath %>resources/scripts/jquery-3.1.1.min.js"></script>
		<script type="text/javascript" src="<%=basePath %>resources/scripts/md5.js"></script>
		<script type="text/javascript">
		 $(function(){
		    // 输入框失去焦点时触发
		   $("#email").blur(function(){
		      var emailVal = $("#email").val();
		      emailVal = $.trim(emailVal);
		      //邮箱的正则表达式
		      var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
		      // ajax发送时请求的地址
		      var url = "<%=request.getContextPath() %>/enterprise/isUsedEmail";
		      // 请求携带的参数
		      var args = {"time":new Date(),"userName":emailVal};
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
		    // 对前台传入的新密码进行MD5的加密
			$(":submit").click(function(){
				var payPassword = $("[name=newPayPassword]").val();
				var pwd1 = $("[name=newPayPassword]").val(md5(payPassword));
				var payPassword2 = $("[name= newPayPassword2]").val();
				var pwd2 = $("[name=newPayPassword2]").val(md5(payPassword2));
				if(payPassword != payPassword2){
				   return false;
				} else if(payPassword.length < 6){
				   $("#hint1").text("密码长度不能小于6");
				   $("#hint1").css("color","red");
				   return false;
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
					<a href="${applicationScope.url}/enterpriseUser/login">
						<img src="<%=basePath %>resources/styles/images/logo.png" alt="蓝桥" />
					</a>
				</div>
				<div id="nav" class="glb-nav">
					<ul class="clearfix">
						<li class="glb-nav-uc">
							<a href="${applicationScope.url}/enterpriseUser/login">
								<span></span>
							</a>
						</li>
						<li class="glb-nav-setting current">
							<a href="${applicationScope.url}/enterprise/to_enterpriseSafeCenter">
								<span></span>
							</a>
						</li>
						<li class="glb-nav-trade">
							<a href="${applicationScope.url }/enterpriseUser/enterpriseUserTradeHistory?id=${sessionScope.enterpriseUser.enterprise.id}">
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
						<a class="menu-txt iconfont icon-menu-um" href="${applicationScope.url}/enterpriseUser/getEnterpriseUser">会员资料</a>
					</li>
					
					<li class="last">
						<a class="menu-txt iconfont icon-menu-bm" href="${applicationScope.url }/BankCardController/EnterBankCard">银行卡管理</a>
					</li>
					<li class="current">
						<a class="menu-txt iconfont icon-menu-sm" href="${applicationScope.url }/enterprise/to_enterpriseSafeCenter">安全中心</a>
					</li>
				</ul>
			</div>
			<div class="col-main">
				<div class="main-hd">
					<h3 class="iconfont icon-safe-email">邮箱更改密码</h3>
				</div>
				<div class="main-bd">
					<div class="glb-step-top">
						<ul class="glb-step-nav safe-reset-nav">
							<li class="step-a">
								<span class="ico"></span>
								<p class="txt">验证邮箱</p>
							</li>
							<li class="direct">
								<p></p> <em></em>
							</li>
							<li class="step-b">
								<span class="ico"></span>
								<p class="txt">密码设置</p>
							</li>
							<li class="direct">
								<p></p> <em></em>
							</li>
							<li class="step-c last">
								<span class="ico"></span>
								<p class="txt">设置成功</p>
							</li>
						</ul>
					</div>
					<form class="form-bd" action="<%=basePath %>/enterprise/UpdatePwdByEmail" id="demoForm" method="post">
						<div class="form-item">
							<h4 class="form-label">请输入邮箱:</h4>
							<div class="form-entity">
								<div class="form-field">
									<input id="email" class="ipt"  type="text" name="email" value=""  placeholder="请输入您的邮箱"/>
									<label id="hint"></label><br/>
								</div>
							</div>	
						</div>
						<div class="form-item">
							<h4 class="form-label">新密码:</h4>
							<div class="form-entity">
								<div class="form-field">
									<input id="newPayPassword" class="ipt" type="password" name="newPayPassword" value="" placeholder="请输入您设置的新密码"  />
									<label id="hint1"></label><br/>
								</div>
							</div>
						</div>
						<div class="form-item">
							<h4 class="form-label">确认密码：</h4>
							<div class="form-entity">
								<div class="form-field">
									<input id="newPayPassword2" class="ipt" type="password" name="newPayPassword2" value="" placeholder="请再次输入您设置的密码" />
									<label id="hint2"></label><br/>
								</div>
							</div>
						</div>
						<div class="form-action form-action-pay clearfix">
								<a class="reset-btn" class="glb-btn submit-btn" href="to_safe">取消</a> 
								<input type="submit" class="glb-btn submit-btn" id="submit" value="提交"> 
						</div>
					</form>
				</div>
			</div>
		</div>
		<div id="bottom" class="bottom"></div>
	</body>

</html>