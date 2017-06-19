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
		<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/lanqiaoPayModel/css/global.css"></link>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/lanqiaoPayModel/css/form.css"></link>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/lanqiaoPayModel/css/biz/security.css"></link>
		<script type="text/javascript" src="<%=basePath %>resources/lanqiaoPayModel/js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath %>resources/lanqiaoPayModel/js/global.js"></script>
		<script type="text/javascript" src="<%=basePath %>resources/lanqiaoPayModel/js/util.js"></script>
		<script type="text/javascript" src="<%=basePath %>resources/scripts/jquery-3.1.1.min.js"></script>
		<script type="text/javascript" src="<%=basePath %>resources/scripts/md5.js"></script>
	    <script type="text/javascript">
	       //判断邮箱格式是否输入正确
	       $(function(){
	          $("#email").blur(function(){
	             var emailVal = $("#email").val();
		         emailVal = $.trim(emailVal);
		      //邮箱的正则表达式
		      var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
		      if(emailVal==""){
						$("#emailErrorMsg").text("邮箱不能为空！");
						$("#emailErrorMsg").css("color","red");
						$("#email").focus();
						return false;
		      } else if(!reg.test(emailVal)){
						$("#emailErrorMsg").text("邮箱格式不正确！");
						$("#emailErrorMsg").css("color","red");
						$("#email").focus();
            			return false;
              }else{
                        $("#emailErrorMsg").text("");
                        $("#emailErrorMsg").css("color","green");
                        return true;
              }
	          });
	       });
	       
	       //判断前后两次输入的密码是否一致
	       $(function(){
	          $("#newPassword2").blur(function(){
	             var newPwd2 = $(this).val();
	             if(newPwd2!==($("#newPassword").val())){
	                $("#pwdErrorMsg").text("两次输入密码不一致，请重新输入！");
	                return false;
	             }else{
	                $("pwdErrorMsg").text("");
	                $("#pwdErrorMsg").css("color","green");
	                return true;
	             }
	           });
	        });
	       //给登陆密码前台加密
	       $(function(){
	           $(":submit").click(function(){
	              var loginPassword = $("[name=loginPassword]").val();
	              //原登陆密码加密
				  $("[name=loginPassword]").val(md5(loginPassword));
				  var newLoginPwd = $("#newPassword").val();
				  //新登陆密码加密
				  $("#newPassword").val(md5(newLoginPwd));
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
					<li class="current">
						<a class="menu-txt iconfont icon-menu-sm" href="${applicationScope.url}/enterprise/to_enterpriseSafeCenter">安全中心</a>
					</li>
					<li class="last">
						<a class="menu-txt iconfont icon-menu-bm" href="${applicationScope.url }/BankCardController/EnterBankCard">银行卡管理</a>
					</li>
				</ul>
			</div>
			<div class="col-main">
				<div class="main-hd">
					<h3 class="iconfont icon-menu-sm">登录密码设置</h3>
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
								<p class="txt">修改密码</p>
							</li>
							<li class="direct">
								<p></p> <em></em>
							</li>
							<li class="step-c last">
								<span class="ico"></span>
								<p class="txt">修改成功</p>
							</li>
						</ul>
					</div>
					<form class="form-bd" action="modifyE_loginPwd" id="demoForm">
					   <div class="form-item">
							<h4 class="form-label">邮箱:</h4>
							<div class="form-entity">
								<div class="form-field">
									<input class="ipt" type="text" id="email" name="email" value="" placeholder="请输入您的邮箱" />
									<label id="emailErrorMsg"></label>
								</div>
							</div>
						</div>
						<div class="form-item">
							<h4 class="form-label">原登录密码:</h4>
							<div class="form-entity">
								<div class="form-field">
									<input class="ipt" type="password" name="loginPassword" value="" placeholder="请输入原登录密码" />
								</div>
							</div>
						</div>
						<div class="form-item">
							<h4 class="form-label">新登录密码:</h4>
							<div class="form-entity">
								<div class="form-field">
									<input class="ipt" type="password" id="newPassword" name="newPassword" value="" placeholder="请输入新登录密码" />
								</div>
							</div>
						</div>
						<div class="form-item">
							<h4 class="form-label">再次输入登录密码:</h4>
							<div class="form-entity">
								<div class="form-field">
									<input class="ipt" type="password" id="newPassword2" name="newPassword2" value="" placeholder="请新再次输入登录密码" />
									<label id="pwdErrorMsg" style="background-color: yellow"></label>
								</div>
							</div>
						</div>
						<div class="form-action clearfix">
							<a class="reset-btn" href="to_reset">重置</a>
							<div class="form-action clearfix">
							   <input type="submit" value="提交" class="glb-btn submit-btn"/>
						    </div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<div id="bottom" class="bottom"></div>
	</body>

</html>