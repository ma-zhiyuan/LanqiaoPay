<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
	href="<%=basePath%>resources/lanqiaoPayModel/css/biz/reg.css">
<script type="text/javascript"
	src="<%=basePath%>resources/bootstrap/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/bootstrap/js/global.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/bootstrap/js/animation.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/bootstrap/js/util.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/bootstrap/js/group.js"></script>
<script src="<%=basePath%>resources/bootstrap/js/jquery-1.12.3.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/bootstrap/js/IDValidator.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/bootstrap/js/GB2260.js" charset="utf-8"></script>
<style type="text/css">
</style>
<script type="text/javascript">
	//新建普通实例
	var Validator = new IDValidator();
	//或使用带地址码实例,需要引入GB2260
	var Validator = IDValidator(GB2260);
	$(function() {
		$("#isValid").blur(function() {
			//验证号码是否合法，合法返回true，不合法返回false
			var code = $("#isValid").val();
			var i = Validator.isValid(code);
			$("#isValid-show").html(i == false ? "身份证号不合法" :  "合法");
		});
	});
	$(function() {
		$("#name1").blur(function() {
			//验证号码是否合法，合法返回true，不合法返回false
			var d = $("#name1").val();
			var i =d.length;
		$("#isValid-show1").html(i<3 ? "昵称长度不小于3" : "可以用");
		});
	});
</script>
</head>

<body>

	<div id="top"></div>
	<div id="header">
		<div class="layout">
			<div id="logo">
				<a href="uc.html"> <img
					src="<%=basePath%>resources/bootstrap/images/logo.png" alt="蓝桥" />
				</a>
			</div>
		</div>
	</div>
	<div id="page">
		<div class="layout reg-wrapper">
			<div class="reg-top">
				<ul class="reg-step clearfix">
					<li class="step">
						<p class="txt">1. 注册账号</p>
						<p class="arr arr-after"></p>
					</li>
					<li class="step current">
						<p class="arr arr-before"></p>
						<p class="txt">2. 完善资料</p>
						<p class="arr arr-after"></p>
					</li>
					<li class="step">
						<p class="arr arr-before"></p>
						<p class="txt">3. 密码设置</p>
						<p class="arr arr-after"></p>
					</li>
					<li class="step last">
						<p class="arr arr-before"></p>
						<p class="txt">4. 注册成功,等待审核</p>
					</li>
				</ul>
			</div>
			<form class="form-bd items-group-box" 
				action="<%=basePath%>userController/regE" id="companyForm">
				<div class="item-group form-group">
					<div class="group-hd">
						<h3>完善信息</h3>
					</div>

					<div class="group-bb">

						<div class="form-item">
							<h4 class="form-label">身份证号:</h4>
							<div class="form-entity">
								<div class="form-field">
										<input type="text" class="ipt" name="cardId" value="" id="isValid" />
									<span id="isValid-show"></span>
								</div>
							</div>
						</div>
						<div class="form-item">
							<h4 class="form-label">昵称:</h4>
							<div class="form-entity">
								<div class="form-field">
								<input class="ipt" type="text" name="name"   id="name1">
									
									<span id="isValid-show1"></span>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="form-action clearfix">
						<a class="reset-btn" href="reg_personal_email_a.html" >上一步</a>
						
						<button class="glb-btn submit-btn" id="button1" >下一步</button>
					</div>
			</form>
			<div>
			</div>
		</div>
	</div>
</body>

</html>