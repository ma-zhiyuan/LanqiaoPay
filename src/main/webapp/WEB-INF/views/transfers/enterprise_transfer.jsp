<%@page import="org.lanqiao.pay.base.entity.BankCard"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>企业转账</title>
<meta http-equiv="keywords" content="">
<link rel="stylesheet" type="text/css"
	href="${applicationScope.url}/resources/lanqiaoPayModel/css/global.css">
<link rel="stylesheet" type="text/css"
	href="${applicationScope.url}/resources/lanqiaoPayModel/css/form.css">
<link rel="stylesheet" type="text/css"
	href="${applicationScope.url}/resources/lanqiaoPayModel/css/dialog.css">
<link rel="stylesheet" type="text/css"
	href="${applicationScope.url}/resources/lanqiaoPayModel/css/biz/uc.css">
<script type="text/javascript"
	src="${applicationScope.url}/resources/lanqiaoPayModel/js/jquery.js"></script>
<script type="text/javascript"
	src="${applicationScope.url}/resources/lanqiaoPayModel/js/group.js"></script>
<script type="text/javascript"
	src="${applicationScope.url}/resources/lanqiaoPayModel/js/global.js"></script>
<script type="text/javascript"
	src="${applicationScope.url}/resources/lanqiaoPayModel/js/util.js"></script>
<script type="text/javascript"
	src="${applicationScope.url}/resources/lanqiaoPayModel/js/animation.js"></script>
<script type="text/javascript"
	src="${applicationScope.url}/resources/lanqiaoPayModel/js/dialog.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/scripts/md5.js"></script>
<script type="text/javascript">
	function show(li) {
		var ul = li.getElementsByTagName("ul")[0];
		ul.style.display = "block";
	}
	function hide(li) {
		var ul = li.getElementsByTagName("ul")[0];
		ul.style.display = "none";
	}
	var urlChange = "TranMoneyIsTrueOrFalse_enterprise";
	$(function() {
		$("#userNameAboutCard").hide();
		$("#userBankCardID").hide();
		$("#businessName").hide();
		$("#businessUserName").hide();
		$("#businessUserCardID").hide();
		//向个人余额转账
		$("#userBalanceMenu").click(function(){
			$("#userAccount").show();
			$("#userNameAboutCard").hide();
			$("#userBankCardID").hide();
			$("#businessName").hide();
			$("#businessUserName").hide();
			$("#businessUserCardID").hide();
			$("#h3Text").text("转账到个人-余额");
			$('form[id=formType]').attr('action','transferController/transferToPersonFromEnterprise'); 
			urlChange = "TranMoneyIsTrueOrFalse_enterprise";
		});
		//向个人银行卡转账
		$("#userBankCardMenu").click(function(){
			$("#userAccount").hide();
			$("#userNameAboutCard").show();
			$("#userBankCardID").show();
			$("#businessName").hide();
			$("#businessUserName").hide();
			$("#businessUserCardID").hide();
			$("#h3Text").text("转账到个人-银行卡");
			$('form[id=formType]').attr('action','transferController/transferToPersonFromEnterpriseBank'); 
			urlChange = "TranMoneyIsTrueOrFalse_enterpriseBank";
		});
		//向企业余额转账
		$("#entBalanceMenu").click(function(){
			$("#userAccount").hide();
			$("#userNameAboutCard").hide();
			$("#userBankCardID").hide();
			$("#businessName").show();
			$("#businessUserName").hide();
			$("#businessUserCardID").hide();
			$("#h3Text").text("转账到企业-余额");
			$('form[id=formType]').attr('action','transferController/transferToEpFromEnterprise'); 
			urlChange = "TranMoneyIsTrueOrFalse_enterprise";
		});
		//向企业银行卡转账
		$("#entBankCardeMenu").click(function(){
			$("#userAccount").hide();
			$("#userNameAboutCard").hide();
			$("#userBankCardID").hide();
			$("#businessName").hide();
			$("#businessUserName").show();
			$("#businessUserCardID").show();
			$("#h3Text").text("转账到企业-银行卡");
			$('form[id=formType]').attr('action','transferController/transferToEpFromEnterpriseBank'); 
			urlChange = "TranMoneyIsTrueOrFalse_enterpriseBank";
		});
		
		var liy;
		var div1 = document.getElementById("yhk");
		liy = (div1.getElementsByTagName("p")[0]).textContent;
		var url = "<%=request.getContextPath()%>/BankController/select_bankcard_enterprise"
		$("#selectBankBtn2").click(function() {
			Dialogx.show({
				_url : url,
				_title : '请选择银行卡',
				_callback : function(dialog) {
					$(".bank-card-x").click(function() {
						// 选取之后覆盖银行卡
						liy = (this.getElementsByTagName("p")[0]).textContent;
						div1.innerHTML = this.innerHTML;
						dialog.closeDialog();
					});
				}
			});
		});
		<!--验证个人账号或手机号输入是否正确 -->
		$("#epName1").blur(function() {
			var userPhone = $("[name=epName]").val();
			var url = "<%=request.getContextPath()%>/transferController/IsTrueOrFalsePhone";
			var args = {
				"time" : new Date(),
				"userPhone" : userPhone,
			};
			var tage = 0;
			$.ajax({
				type : 'post',
				asyne : false,
				url : url,
				data : args,
				success : function(result) {
					if (result == "true") {
						tage = 1;
						$("#hint3").text("");
						$("#hint3").css("color", "red");
						return true;
					}
				}
			});
			if (tage == 0) {
				$("#hint3").text("输入账户不正确！");
				$("#hint3").css("color", "red");
				return false;
			}
		});
		<!--使用企业名称判断企业是否存在 -->
		$("#entName").blur(function() {
			var entName = $("[name=entName]").val();
			var url = "<%=request.getContextPath()%>/transferController/IsTrueOrFalseEpPhone";
			var args = {
				"time" : new Date(),
				"entName" : entName,
			};
			var tage = 0;
			$.ajax({
				type : 'post',
				asyne : false,
				url : url,
				data : args,
				success : function(result) {
					if (result == "true") {
						tage = 1;
						$("#entHint").text("");
						$("#entHint").css("color", "red");
						return true;
					}
				}
			});
			if (tage == 0) {
				$("#entHint").text("企业不存在");
				$("#entHint").css("color", "red");
				return false;
			}
		});
		
		//验证页面输入银行卡是否正确
		$("#userCareID1").blur(function() {
			// 获取银行卡号
			var bankNum = $("[name=userCareID1]").val();
			var url = "<%=request.getContextPath()%>/transferController/IsTrueOrFalseBankNum";
			var args = {
				"time" : new Date(),
				"bankNum" : bankNum,
			};
			var bankUserName = null;
			var aa = 0;
			$.ajax({
				type : 'post',
				asyne : false,
				url : url,
				data : args,
				success : function(result) {
					if (result != "false") {
						aa = 1;
						// 将通过银行卡获取到的个人用户的姓名自动显示至个人姓名
						bankUserName = result;
						$("#personNam").text(bankUserName);
						return true;
					}
					if (result == "false") {
						$("#hint4").text("未存在此银行卡号！");
						$("#hint4").css("color", "red");
						return false;
					}
				}
			});
			if (bankNum == "") {
				$("#hint4").text(" ");
				$("#hint4").css("color", "red");
				return false;
			}
		});
		//企业银行卡:判断银行卡是否是企业法定人的
		$("#entUserNameCareID").blur(function() {
			// 获取银行卡号
			var entUserName = $("[name=entUserName]").val();
			var bankNum = $("[name=entUserNameCareID]").val();
			var url = "<%=request.getContextPath()%>/transferController/IsTrueOrFalseEpBankNum";
			var args = {
				"time" : new Date(),
				"entUserName" : entUserName,
				"bankNum" : bankNum
			};
			$.ajax({
				type : 'post',
				asyne : false,
				url : url,
				data : args,
				success : function(result) {
				    if (result == "true") {
						$("#epCardHint").text(" ");
						$("#epCardHint").css("color", "red");
						return true;
					}
					if (result == "false") {
						$("#epCardHint").text("未存在此银行卡号！");
						$("#epCardHint").css("color", "red");
						return false;
					}
				}
			});
			if (bankNum == "") {
				$("#epCardHint").text(" ");
				$("#epCardHint").css("color", "red");
				return false;
			}
		});
		<!-- 判断企业向个人余额宝输入的金额与余额宝的金额和所选定的银行的金额是否符合规范要求  -->
		$("#transferMoney").blur(function() {
			// 获取页面输入的转账金额
			var tranMoney = $("[name=transferMoney]").val();
			// 获取转账的方式
			var inlineRadioOptions = $("input[name='inlineRadioOptions']:checked").val();
			// 获取用户的余额宝中的金额
			var allMoney = $("[name=nameBalance2]").val();
			if (tranMoney == "") {
				$("#hint2").text(" ");
				return false;
			}
			if (tranMoney < 0) {
				$("#hint2").text("请重新输入转账金额！");
				$("#hint2").css("color", "red");
				return false;
			}
			// 获取选定之后的银行卡
			var liyy = liy;
			var args = {
				"time" : new Date(),
				"tranMoney" : tranMoney,
				"inlineRadioOptions" : inlineRadioOptions,
				"allMoney" : allMoney,
				"liy" : liyy,
			};
			var tage = 0;
			var strMoney = null;
			var url = "<%=request.getContextPath()%>/transferController/"+urlChange;
			$.ajax({
				type : 'post',
				async : false,
				url : url,
				data : args,
				success : function(result) {
					if (result == "0.00") {
						tage = 1;
						strMoney = result;
						$("#sxf").text(strMoney);
						return true;
					} else if (result != "false") {
						tage = 1;
						// 获取生成的利息
						strMoney = result;
						$("#sxf").text(strMoney);
						return true;
					}
				}
			});
			if (tage == 0) {
				$("#hint2").text("余额不足");
				$("#hint2").css("color", "red");
				return false;
			}
		});
		$("#inlineRadio1").click(function() {
			$("#default").hide();
			$("#others").hide();
		});
		$("#inlineRadio2").click(function() {
			$("#default").show();
			$("#others").show();
		});
		$("#default").hide();
		// 对支付密码进行页面的加密
		$(":submit").click(function() {
			// 判断支付密码是否正确
			var id = $("[name=id2]").val();
			var payPassword = $("[name=payPassword]").val();
			var md5Pwd = md5(payPassword);
			var url = "<%=request.getContextPath()%>/BankController/pwdIsUserd";
			var args = {
				"time" : new Date(),
				"id" : id,
				"payPassword" : md5Pwd
			};
			var tag = 0;
			$.ajax({
				type : 'POST',
				async : false,
				url : url,
				data : args,
				success : function(result) {
					if (result == "true") {
						tag = 1;
						return true;
					}
				}
			});
			if (tag == 0) {
				$("#errorMsg").text("支付密码错误");
				$("#errorMsg").css("color", "red");
				return false;
			}
			var pwd = $("[name=payPassword]").val();
			var md5Pwd = md5(pwd);
			$("[name=payPassword]").val(md5Pwd);
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
					src="${applicationScope.url}/resources/lanqiaoPayModel/images/logo.png"
					alt="蓝桥" />
				</a>
			</div>
			<div id="nav" class="glb-nav">
				<ul class="clearfix">
					<li class="glb-nav-uc current"><a href="<%=basePath%>/transferController/to_enterprise_center">
							<span></span>
					</a></li>
					<li class="glb-nav-setting"><a href="${applicationScope.url}/enterprise/to_enterpriseSafeCenter">
							<span></span>
					</a></li>
					<li class="glb-nav-trade"><a href="<%=basePath%>/enterpriseUser/enterpriseUserTradeHistory?id=${sessionScope.enterpriseUser.enterprise.id}"> <span></span>
					</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="main layout">
		<!--  获取企业法定人登录之后的id -->
		<input type="hidden" id="id2" name="id2"
			value="${sessionScope.enterpriseUser.secret.id }" />
		<div class="col-menu">
			<ul class="menu-items">
				<li><span class="menu-txt iconfont icon-menu-sm">转账对象</span></li>
				<li onmouseover="show(this)" onmouseout="hide(this)"><a
					class="menu-txt iconfont icon-menu-um">向个人转账</a>
					<ul style="display: none">
						<li><a id="userBalanceMenu" >&emsp;&emsp;余额</a></li>
						<li><a id="userBankCardMenu" >&emsp;&emsp;银行卡</a></li>
					</ul></li>
				<li class="last" onmouseover="show(this)" onmouseout="hide(this)">
					<a class="menu-txt iconfont icon-menu-bm">向企业转账</a>
					<ul style=" display: none">
						<li><a id="entBalanceMenu" >&emsp;&emsp;余额</a></li>
						<li><a id="entBankCardeMenu" >&emsp;&emsp;银行卡</a></li>
					</ul>
				</li>
			</ul>
		</div>
		<div class="col-main">
			<div class="main-hd">
				<h3 class="iconfont icon-menu-sm" id="h3Text">转账到个人-余额</h3>
				<h3 class="trade-top-acc">账户余额：${sessionScope.enterpriseUser.balance }</h3>
				<form class="form-bd form-bd-trade"
					action="transferController/transferToPersonFromEnterprise"
					id="formType">
					<input type="hidden" id="id2" name="id2"
						value="${sessionScope.enterpriseUser.secret.id }" />
					<!-- start -->
					<!-- 个人-余额 -->
					<div class="form-item" id="userAccount" >
						<h4 class="form-label">个人账户:</h4>
						<div id="ep" name="ep" class="form-entity">
							<div class="form-field">
								<input class="ipt ipt-ename" type="text" name="epName"
									id="epName1" value="" placeholder="请输入个人账号或手机号码" /> <label
									id="hint3"></label><br />
							</div>
						</div>
					</div>
					<!-- 个人-银行卡 -->
					<div class="form-item" id = "userBankCardID">
						<h4 class="form-label">银行卡号:</h4>
						<div id="ep" name="ep" class="form-entity">
							<div class="form-field">
								<input class="ipt ipt-ename" type="text" name="userCareID1"
									id="userCareID1" value="" placeholder="请输入个人银行卡号" /> <label
									id="hint4"></label>
							</div>
						</div>
					</div>
					<div class="form-item form-item-money" id="userNameAboutCard">
						<h4 class="form-label">个人姓名:</h4>
						<div class="form-entity">
							<div class="form-field">
								<div class="form-twin-group">
									<p class="form-text strong" id="personNam" name="personName">
									</p>
								</div>
							</div>
						</div>
					</div>
					<!-- 企业-余额 -->
					<!-- 企业-银行卡 -->
					<div class="form-item" id="businessName">
						<h4 class="form-label">企业名称:</h4>
						<div id="ep" name="ep" class="form-entity">
							<div class="form-field">
								<input class="ipt ipt-ename" type="text" name="entName"
									id="entName" value="" placeholder="请输入企业名称" /><label
									id="entHint"></label>
							</div>
						</div>
					</div>
					<div class="form-item" id="businessUserName">
						<h4 class="form-label">法定人姓名:</h4>
						<div id="ep" name="ep" class="form-entity">
							<div class="form-field">
								<input class="ipt ipt-ename" type="text" name="entUserName"
									id="entUserName" value="" placeholder="请输入姓名" />
							</div>
						</div>
					</div>
					<div class="form-item" id="businessUserCardID">
						<h4 class="form-label" >银行卡号:</h4>
						<div id="ep" name="ep" class="form-entity">
							<div class="form-field">
								<input class="ipt ipt-ename" type="text" name="entUserNameCareID"
									id="entUserNameCareID" value="" placeholder="请输入其银行卡号" />
									<label id="epCardHint"></label>
							</div>
						</div>
					</div>
					<!-- end -->
					<div class="form-item form-item-money">
						<h4 class="form-label">交易名称:</h4>
						<div class="form-entity">
							<div class="form-field">
								&nbsp;<select name="transactionName" id="transactionName">
									<option value="">请进行选择:</option>
									<option value="账户余额-单次转入">账户余额-单次转入</option>
									<option value="账户余额-付款">账户余额-付款</option>
									<option value="账户余额-销售">账户余额-销售</option>
								</select>
							</div>
						</div>
					</div>
					<div class="form-item">
						<h4 class="form-label">转账方式:</h4>
						<div class="form-entity">
							<div class="form-field ">
								<input type="radio" checked="checked" value="yue"
									id="inlineRadio1" name="inlineRadioOptions"><span
									style="font-size: 16">余额</span>
							</div>
							<div class="form-field ">
								<input type="radio" value="yhk" id="inlineRadio2"
									name="inlineRadioOptions"><span style="font-size: 16">银行卡</span>
							</div>
							<br>
							<div class="form-field" id="default">
								<div class="form-twin-group">
									<%
										BankCard isdefaul = (BankCard) request.getSession().getAttribute("isdefaut");
										String name = isdefaul.getBankId().getName();
										String str = isdefaul.getCardNumber().substring(15, 19);
										if (name.equals("中国工商银行")) {
									%>
									<div class="bank-card-x" id="yhk">
										<div class="bank-ico bank-icbc"></div>
										<p class="card-prop card-no" id="yhkho" style="display: none"><%=isdefaul.getCardNumber()%></p>
										<p class="card-prop card-no">
											******
											<%=str%></p>
									</div>
									<%
										} else if (name.equals("华夏银行")) {
									%>
									<div class="bank-card-x" id="yhk">
										<div class="bank-ico bank-hxb"></div>
										<p class="card-prop card-no" id="yhkho" style="display: none"><%=isdefaul.getCardNumber()%></p>
										<p class="card-prop card-no">
											******
											<%=str%></p>
									</div>
									<%
										} else if (name.equals("中国农业银行")) {
									%>
									<div class="bank-card-x" id="yhk">
										<div class="bank-ico bank-abc"></div>
										<p class="card-prop card-no" id="yhkho" style="display: none"><%=isdefaul.getCardNumber()%></p>
										<p class="card-prop card-no">
											******
											<%=str%></p>
									</div>
									<%
										} else if (name.equals("平安银行")) {
									%>
									<div class="bank-card-x" id="yhk">
										<div class="bank-ico bank-pingan"></div>
										<p class="card-prop card-no" id="yhkho" style="display: none"><%=isdefaul.getCardNumber()%></p>
										<p class="card-prop card-no">
											******
											<%=str%></p>
									</div>
									<%
										} else if (name.equals("招商银行")) {
									%>
									<div class="bank-card-x" id="yhk">
										<div class="bank-ico bank-cmb"></div>
										<p class="card-prop card-no" id="yhkho" style="display: none"><%=isdefaul.getCardNumber()%></p>
										<p class="card-prop card-no">
											******
											<%=str%></p>
									</div>
									<%
										} else if (name.equals("中国银联")) {
									%>
									<div class="bank-card-x" id="yhk">
										<div class="bank-ico bank-unionpay"></div>
										<p class="card-prop card-no" id="yhkho" style="display: none"><%=isdefaul.getCardNumber()%></p>
										<p class="card-prop card-no">
											******
											<%=str%></p>
									</div>
									<%
										} else if (name.equals("中国民生银行")) {
									%>
									<div class="bank-card-x" id="yhk">
										<div class="bank-ico bank-cmbc"></div>
										<p class="card-prop card-no" id="yhkho" style="display: none"><%=isdefaul.getCardNumber()%></p>
										<p class="card-prop card-no">
											******
											<%=str%></p>
									</div>
									<%
										} else if (name.equals("中银富登村镇银行")) {
									%>
									<div class="bank-card-x" id="yhk">
										<div class="bank-ico bank-boc"></div>
										<p class="card-prop card-no" id="yhkho" style="display: none"><%=isdefaul.getCardNumber()%></p>
										<p class="card-prop card-no">
											******
											<%=str%></p>
									</div>
									<%
										} else if (name.equals("光大银行")) {
									%>
									<div class="bank-card-x" id="yhk">
										<div class="bank-ico bank-ceb"></div>
										<p class="card-prop card-no" id="yhkho" style="display: none"><%=isdefaul.getCardNumber()%></p>
										<p class="card-prop card-no">
											******
											<%=str%></p>
									</div>
									<%
										} else if (name.equals("中国邮政储蓄银行")) {
									%>
									<div class="bank-card-x" id="yhk">
										<div class="bank-ico bank-psbc"></div>
										<p class="card-prop card-no" id="yhkho" style="display: none"><%=isdefaul.getCardNumber()%></p>
										<p class="card-prop card-no">
											******
											<%=str%></p>
									</div>
									<%
										} else if (name.equals("哈尔滨银行")) {
									%>
									<div class="bank-card-x" id="yhk">
										<div class="bank-ico bank-hrbcb"></div>
										<p class="card-prop card-no" id="yhkho" style="display: none"><%=isdefaul.getCardNumber()%></p>
										<p class="card-prop card-no">
											******
											<%=str%></p>
									</div>
									<%
										} else if (name.equals("中国广发银行")) {
									%>
									<div class="bank-card-x" id="yhk">
										<div class="bank-ico bank-gdb"></div>
										<p class="card-prop card-no" id="yhkho" style="display: none"><%=isdefaul.getCardNumber()%></p>
										<p class="card-prop card-no">
											******
											<%=str%></p>
									</div>
									<%
										} else if (name.equals("中国建设银行")) {
									%>
									<div class="bank-card-x" id="yhk">
										<div class="bank-ico bank-ccb"></div>
										<p class="card-prop card-no" id="yhkho" style="display: none"><%=isdefaul.getCardNumber()%></p>
										<p class="card-prop card-no">
											******
											<%=str%></p>
									</div>
									<%
										} else {
									%>
									<div class="bank-card-x" id="yhk">
										<p class="card-prop card-no" id="yhkho" style="display: none"></p>
										<p class="card-prop card-no"></p>
									</div>
									<%
										}
									%>
								</div>
								<div class="form-twin-group" id="others">
									<a id="selectBankBtn2" class="rapid-link" href="javascript:;">使用其他银行卡</a>
								</div>
							</div>
						</div>
					</div>
					<div class="form-item form-item-money">
						<h4 class="form-label">转账金额:</h4>
						<div class="form-entity">
							<div class="form-field">
								<div class="form-twin-group">
									<input class="ipt ipt-money" type="text" name="transferMoney"
										id="transferMoney" value="" placeholder="请输入转账金额" /><label
										id="hint2"></label><br />
								</div>
								<!--  当前法定人余额宝的金额 -->
								<input type="hidden" name="nameBalance2" id="nameBalance2"
									value="${sessionScope.enterpriseUser.balance }" />
								<!--  获取当前的所选择的银行卡号 -->
								<input type="hidden" name="" id="yhkh1"
									value="<%=request.getSession().getAttribute("tranEnter")%>" />
								<!-- 当前法定人的ID -->
								<input type="hidden" id="userId2" name="userId2"
									value="${sessionScope.enterpriseUser.id }" />
								<div class="form-twin-group">
									<span class="unit">元</span>
								</div>
								<label id="hint5"></label>
							</div>
						</div>
					</div>
					<div class="form-item form-item-money">
						<h4 class="form-label">手续费:</h4>
						<div class="form-entity">
							<div class="form-field">
								<div class="form-twin-group">
									<p class="form-text strong" id="sxf" name="sxf">0.00</p>
								</div>
							</div>
						</div>
					</div>
					<div class="form-item form-item-money">
						<h4 class="form-label">到账时间:</h4>
						<div class="form-entity">
							<div class="form-field">
								<p class="form-text">2小时内到账</p>
							</div>
						</div>
					</div>
					<div class="form-item">
						<h4 class="form-label">支付密码:</h4>
						<div class="form-entity">
							<div class="form-field">
								<input class="ipt ipt-pwd" type="password" name="payPassword"
									id="payPwd" value="" placeholder="请输入支付密码" /> <label
									id="bankCardMsg"></label> <label id="errorMsg"></label>
							</div>
						</div>
					</div>
					<div class="form-action clearfix">
						<input type="submit" name="submit" class="glb-btn submit-btn"
							value="立即转账" />
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
