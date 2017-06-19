<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN">
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP '2.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
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
			$(":submit").click(function(){
			    var payPassword = $("[name=payPassword]").val();
				var md5Pwd = $("[name=payPassword]").val(md5(payPassword));
		  });
		});
	</script>
  </head>
  <body>
    <div id="top"></div>
		<div id="header">
			<div class="layout">
				<div id="logo">
					<a href="userController/to_uc">
						<img src="<%=basePath %>resources/styles/images/logo.png" alt="蓝桥" />
					</a>
				</div>
				<div id="nav" class="glb-nav">
					<ul class="clearfix">
						<li class="glb-nav-uc">
							<a href="userController/to_uc">
								<span></span>
							</a>
						</li>
						<li class="glb-nav-setting current">
							<a href="secretController/to_safe">
								<span></span>
							</a>
						</li>
						<li class="glb-nav-trade">
							<a href="userController/userTradeHistory?id=${sessionScope.user.id}">
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
						<a class="menu-txt iconfont icon-menu-um" href="<%=basePath %>/userController/goUserInformation">会员资料</a>
					</li>
					<li class="current">
						<a class="menu-txt iconfont icon-menu-sm" href="<%=basePath %>/secretController/to_safe">安全中心</a>
					</li>
					<li class="last">
						<a class="menu-txt iconfont icon-menu-bm" href="<%=basePath %>/BankCardController/my_card_list">银行卡管理</a>
					</li>
				</ul>
			</div>
			<div class="col-main">
				<div class="main-hd">
					<h3 class="iconfont icon-safe-pwd">安全问题设置</h3>
				</div>
				<div class="main-bd">
					<div class="glb-step-top">
						<ul class="glb-step-nav safe-reset-nav">
							<li class="step-a">
								<span class="ico"></span>
								<p class="txt">验证身份</p>
							</li>
							<li class="direct">
								<p></p> <em></em>
							</li>
							<li class="step-b">
								<span class="ico"></span>
								<p class="txt">问题设置</p>
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
					<form class="form-bd" action="secretController/updateSecret" id="demoForm" method="post">
					   <input type="hidden" id= "id" name="id" value="${sessionScope.user.secretId.id }" />
						<div class="form-item">
							<h4 class="form-label">支付密码:</h4>
							<div class="form-entity">
								<div class="form-field">
									<input id ="payPassword" class="ipt" type="password" id="payPassword" name="payPassword" value=""  placeholder="请输入您的支付密码"/>
								</div>
							</div>
						</div>
						<div class="form-item">
							<h4 class="form-label">安全保护问题:</h4>
							<div class="form-entity">
								<div class="form-field">
									<input class="ipt" type="text" name="securityQuestion" value="" placeholder="请输入" />
								</div>
							</div>
						</div>
						<div class="form-item">
							<h4 class="form-label">您的答案:</h4>
							<div class="form-entity">
								<div class="form-field">
									<input class="ipt" type="text" name="classifiedAnswer" value=""  placeholder="请输入" />
								</div>
							</div>
						</div>
						<div class="form-action clearfix">
						    <a class="reset-btn" class="glb-btn submit-btn" href="secretController/to_safe">取消</a>
							<input type="submit" id= "submit" value="提交" class="glb-btn submit-btn"/>
						</div>
					</form>
				</div>
			</div>
		</div>
		<div id="bottom" class="bottom"></div>
  </body>
</html>
