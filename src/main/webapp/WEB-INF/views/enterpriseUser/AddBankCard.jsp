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
	src="<%=basePath%>resources/lanqiaoPayModel/js/md5.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/bootstrap/js/GB2260.js" charset="utf-8"></script>
<style type="text/css">
</style>
<script type="text/javascript">
	$(function() {
		//当银行卡输入框失去焦点
		$("#cardnumber").blur(function() {
			var cardnumber = $("#cardnumber").val();
			if (cardnumber.length != 19) {
				$("#isValid-show").html("格式错误")
				return false;
			} else {
				$("#isValid-show").html("")
				var url1 = "<%=basePath%>BankCardController/AddBankCard";
				var args = {
					"cardnumber" : cardnumber,
				}
				$.post(url1, args, function(data) {
					if (data == "f") {
						$("#isValid-show").html("已经占用");
						return false;
					} else {
						$("#isValid-show").html("");
						$("#password").blur(function() {
							var password = $("#password").val();
							if (password.length < 6) {
								$("#isValid-show1").html("密码太短")
								return false;
							} else {
								$("#isValid-show1").html("")
								$("#password").val(md5(password));
								document.getElementById("bt2").action = "<%=basePath%>BankCardController/EntAddBankCard";
							}
						})
					}
				})
			}

		})




	})
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
						<p class="txt">添加银行卡</p>
						<p class="arr arr-after"></p>
					</li>

				</ul>
			</div>
			<form class="form-bd items-group-box" action="javascript:;" id="bt2"
				method="post">
				<div class="item-group form-group">
					<div class="group-hd">
						<h3>请输入银行卡信息</h3>
					</div>

					<div class="group-bb">

						<div class="form-item">
							<h4 class="form-label">银行卡号:</h4>
							<div class="form-entity">
								<div class="form-field">
									<input type="text" class="ipt" name="cardNumber"
										id="cardnumber" /> <span id="isValid-show"></span>
								</div>
							</div>
						</div>
						<div class="form-item">
							<h4 class="form-label">密码:</h4>
							<div class="form-entity">
								<div class="form-field">
									<input class="ipt" type="password" name="bankCardPassWord"
										id="password"> <span id="isValid-show1"></span>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="form-action clearfix">
					<button class="glb-btn submit-btn" id="bt1" href="javascript:;">
						<span>下一步</span>
					</button>
				</div>
			</form>
		</div>
	</div>
</body>

</html>