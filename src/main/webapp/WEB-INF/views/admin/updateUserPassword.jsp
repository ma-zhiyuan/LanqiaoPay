<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<head>
<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>蓝桥支付-登录</title>
	<meta name="keywords" content="" />
	<meta name="description" content="" />
	<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/bootstrap/css/bookstrap.min.css">
	<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/bootstrap/css/bootstrap-theme.min.css">
	<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/lanqiaoPayUI/css/global.css">
	<link rel="stylesheet" type="text/css"
		href="<%=basePath%>resources/lanqiaoPayUI/css/glw.css">
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>resources/lanqiaoPayUI/css/theme_blue.css">

</head>
<body>
	<div id="header" class="glb-header">
		<div class="glb-layout">
			<div id="logo" class="glb-logo">
				<a href="#"> <img
					src="<%=basePath%>resources/lanqiaoPayUI/images/logo_w.png"
					alt="LOGO" />
				</a>
			</div>
		</div>
	</div>
	<div class="glb-page glb-wrapper">
		<div class="glb-layout">
			<ul class="glb-steps glb-stepbar glb-steps-col4">
				<li class="step-current">
					<p class="step-text">1.重置密码</p>
				</li>
				<li>
					<p class="step-text">2.重置成功</p>
				</li>
			</ul>
			<div class="glb-col-p60 glb-center">
				<form class="form-body" action="#" id="form">
					<input type="hidden" id="id" name="id"
						value="${requestScope.user.id }" />
					<div class="form-item">
						<!-- 获取用户的邮箱地址 -->
						<h5 class="form-label">电子邮箱:</h5>
						<div class="form-entity">
							<div class="form-field">
								<input class="form-ipt" type="text" name="email" id="email"
									value="${requestScope.user.email }" />
							</div>
						</div>
					</div>
					<!-- <div class="form-item">
						给用户发验证码告诉他正在重置，若同意请说明验证码
						<h5 class="form-label">验证码:</h5>
						<div class="form-entity">
							<div class="form-field form-case-code">
								<input class="form-inline form-ipt" type="text" name="userName"
									value="" placeholder="请输入验证码" />
								<button id="sendEmail" type="button" class="btn btn-info">发送邮件</button>
							</div>
							<label id="hint"></label>
						</div>
					</div> -->
					<div class="form-action">
						<a class="glb-button glb-btn-primary" href="userController/resetPassword?id=${requestScope.user.id } ">重置密码</a>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript"
	src="<%=basePath%>resources/lanqiaoPayUI/js/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/lanqiaoPayUI/js/global.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/lanqiaoPayUI/js/validate.js"></script>
<script type="text/javascript">
	$(function() {
		/* $("#sendEmail").click(function() {
			var email = $("#email").val();
			var url = "userController/sendEmail";
			//请求参数
			var args = {
				"emailCode" :Math.floor(Math.random()*1000),
				"userEmail" : email,
				"date" : new Date()
			};
			//发送请求，data请求数据
			$.post(url,args,function(data){
				$("#hint").text(data);
			
			});
			

		}); */
	});
</script>

</html>