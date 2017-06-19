<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>客服登陆</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link rel="stylesheet"
	href="resources/sccl-admin/common/layui/css/layui.css">
<link rel="stylesheet" href="resources/sccl-admin/common/css/sccl.css">
<style type="text/css">
body {
	background: url(resources/lanqiaoPayModel/images/4.png)
		no-repeat center center fixed;
	background-size: 100% 100%;
}
</style>
<script type="text/javascript"
	src="<%=basePath%>resources/lanqiaoPayModel/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/lanqiaoPayModel/js/md5.js"></script>
<script type="text/javascript">
	$(function(){
		$("#password").blur(function() {
			var md5Str = md5($("#password").val());
			($("#password")).val(md5Str);
		});
	});
</script>

</head>
<body>
	<div class="login-box">
		<header>
		<h1>LanqiaoPay客服系统</h1>
		</header>
		<div class="login-main">
			<form action="customerServiceController/loginCustomer" class="layui-form" method="post">
				<input name="__RequestVerificationToken" type="hidden" value="">
				<div class="layui-form-item">
					<label class="login-icon"> <i class="layui-icon">@</i>
					</label> <input type="text" name="id"  id="id" lay-verify="userName"
						autocomplete="off" placeholder="这里输入客服ID" class="layui-input">
				</div>
				<div class="layui-form-item">
					<label class="login-icon"> <i class="layui-icon">*</i>
					</label> <input type="password" name="password" id="password" lay-verify="password"
						autocomplete="off" placeholder="这里输入密码" class="layui-input">
				</div>
				<div class="layui-form-item">
					<div class="pull-left login-remember">
						<label>记住帐号？</label> <input type="checkbox" name="rememberMe"
							value="true" lay-skin="switch" title="记住帐号">
						<div class="layui-unselect layui-form-switch">
							<i></i>
						</div>
					</div>
					<div class="pull-right">
						<button class="layui-btn layui-btn-primary submit" 
							lay-filter="login">
							<i class="layui-icon">-></i> 登录
						</button>
					</div>
					<div class="clear"></div>
				</div>
			</form>
		</div>
		<footer>
		<p>www.lanqiao.org</p>
		</footer>
	</div>

	<script src="resources/sccl-admin/common/layui/layui.js"></script>
	<script>
		layui.use([ 'layer', 'form' ], function() {
			var layer = layui.layer,
				$ = layui.jquery,
				form = layui.form();
	
			form.verify({
				userName : function(value) {
					if (value === '')
						return '请输入用户名';
				},
				password : function(value) {
					if (value === '')
						return '请输入密码';
				}
			});
	
			var errorCount = 0;
	
			form.on('submit(login)', function(data) {
				window.location.href = "admin_login.jsp";
			/*if (errorCount > 5) {
			    layer.open({
			        title: '<img src="' + location.origin + '/Plugins/layui/images/face/7.gif" alt="[害羞]">输入验证码',
			        type: 1,
			        content: document.getElementById('code-temp').innerHTML,
			        btn: ['确定'],
			        yes: function (index, layero) {
			            var $code = $('#code');
			            if ($code.val() === '') {
			                layer.msg('输入验证码啦，让我知道你是人类。');
			                isCheck = false;
			            } else {
			                $('input[name=verifyCode]').val();
			                var params = data.field;
			                params.verifyCode = $code.val();
			                submit($,params);
			                layer.close(index);
			            }
			        },
			        area: ['250px', '150px']
			    });
			    $('#valiCode').off('click').on('click', function () {
			        this.src = '/manage/validatecode?v=' + new Date().getTime();
			    });
			}else{
			    submit($,data.field);
			}
			
			return false;*/
			});
	
		});
	
		/*function submit($,params){
		    $.post('/manage/login',params , function (res) {
		        if (!res.success) {
		            if (res.data !== undefined)
		                errorCount = res.data.errorCount
		            layer.msg(res.message,{icon:2});
		        }else
		        {
		            layer.msg(res.message,{icon:1},function(index){
		                layer.close(index);
		                location.href='/manage';
		            });
		        }
		    }, 'json');
		}*/
	</script>
</body>
<!-- body stop-->
</html>
