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
	       //判断身份证的格式输入是否有误
	       $(function(){
				$("#cardId").blur(function(){
					var IDcard=$(this).val();
					IDcard=$.trim(IDcard);
					var city={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江 ",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北 ",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏 ",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外 "};
					if(IDcard==""){
						$("#cardErrorMsg").text("身份证不能为空");
						$("#cardErrorMsg").css("color","red");
						return false;
					} else {
						if (!IDcard || !/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i.test(id)) {
							$("#cardErrorMsg").text("身份证格式错误");
							$("#cardErrorMsg").css("color","red");
						} else if (!city[IDcard.substr(0, 2)]) {
							$("#cardErrorMsg").text("身份证地址编码错误");
							$("#cardErrorMsg").css("color","red");
						} else if(IDcard.length==18){
							$("#cardErrorMsg").text("");
							IDcard = IDcard.split('');
							//∑(ai×Wi)(mod 11)
							//加权因子
							var factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
							//校验位
							var parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ];
							var sum = 0;
							var ai = 0;
							var wi = 0;
							for (var i = 0; i < 17; i++) {
								ai = id[i];
								wi = factor[i];
								sum += ai * wi;
							}
							var last = parity[sum % 11];
							if (parity[sum % 11] != IDcard[17]) {
								$("#cardErrorMsg").text("身份证校验位错误");
								$("#cardErrorMsg").css("color","red");
								return false;
							}else{
								$("#cardErrorMsg").text("");
							    return true;
						    }
					    }
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
	                $("#pwdErrorMsg").text("");
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
					<a href="to_uc">
						<img src="<%=basePath %>resources/styles/images/logo.png" alt="蓝桥" />
					</a>
				</div>
				<div id="nav" class="glb-nav">
					<ul class="clearfix">
						<li class="glb-nav-uc">
							<a href="to_uc">
								<span></span>
							</a>
						</li>
						<li class="glb-nav-setting current">
							<a href="to_safe">
								<span></span>
							</a>
						</li>
						<li class="glb-nav-trade">
							<a href="userTradeHistory?id=${sessionScope.user.id}">
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
						<a class="menu-txt iconfont icon-menu-sm" href="<%=basePath %>/userController/to_safe">安全中心</a>
					</li>
					<li class="last">
						<a class="menu-txt iconfont icon-menu-bm" href="<%=basePath %>/BankCardController/my_card_list">银行卡管理</a>
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
					<form class="form-bd" action="modifyLoginPwd" id="demoForm" modelAttribute="user" method="post">
					   <input  type="hidden" name="id" value="${sessionScope.user.id }"/>
						<div class="form-item">
							<h4 class="form-label">身份证:</h4>
							<div class="form-entity">
								<div class="form-field">
									<input class="ipt" type="text" id="cardId" name="cardId" value="" placeholder="请输入您的身份证号" />
								    <label id="cardErrorMsg" style="background-color: yellow"></label>
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