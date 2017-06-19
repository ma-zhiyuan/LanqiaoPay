<%@ page import="org.lanqiao.pay.base.entity.BankCard"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>提现 - 蓝桥支付</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/lanqiaoPayModel/css/global.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/lanqiaoPayModel/css/dialog.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/lanqiaoPayModel/css/form.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/lanqiaoPayModel/css/biz/trade.css">
<script type="text/javascript"
	src="<%=basePath%>resources/lanqiaoPayModel/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/lanqiaoPayModel/js/global.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/lanqiaoPayModel/js/util.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/lanqiaoPayModel/js/animation.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/lanqiaoPayModel/js/dialog.js"></script>
<script type="text/javascript"
	<script type="text/javascript" src="<%=basePath%>resources/lanqiaoPayModel/js/group.js"></script>
	src="<%=basePath%>resources/scripts/jquery-3.1.1.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/scripts/md5.js"></script>
<script type="text/javascript">
	$(function() {
		var liy;
		var div1 = document.getElementById("yhk");
		liy = (div1.getElementsByTagName("p")[0]).textContent;
		var url = "<%=request.getContextPath()%>/BankController/select_bankcard"
		$("#selectBankBtn").click(function() {
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
		
		// 判断输入的金额是否超过用户支付宝本身所持有的钱数
		$("#Allmoney").blur(function() {
		    var liyy = liy;
			// 获取用户输入的提现金额
			var mon = $("[name= winthdrawMoney]").val();
			// 获取隐藏的userId
			var id = $("[name= userId]").val();
			var url = "<%=request.getContextPath()%>/BankController/moneyIsTrueOrFalse";
			var args = {
				"time" : new Date(),
				"id" : id,
				"mon" : mon,
				"liy" :liyy
			};
			var strMoney = null;
			var tage = 0;
			$.ajax({
				type : 'post',
				async : false,
				url : url,
				data : args,
				success : function(result) {
					if (result != "false") {
						tage = 1;
						// 获取生成的利息
						strMoney = result;
						$("#peritiesMoney").text(strMoney);
						return true;
					}
				}
			});
			if (tage == 0) {
				$("#hint2").text("余额不足");
				$("#hint2").css("color", "red");
				return false;
			}
			// 判断金额是否符合要求
			if(mon < 0){
			    $("#hint2").text("请重新输入金额！");
			    $("#hint2").css("color", "red");
			    return false;
			}
			
		});
		// 点击全部提现之后获取所有用户的账户金额
		$("[name = 'cbAllMoney']").click(function() {
		    // 获取输入框中的金额
		    var mm = $("[name= winthdrawMoney]").val();
			var m = $("[name='nameBalance']").val();
			if(mm == m){
			  $("[name= 'winthdrawMoney']").val("0.00");
			} else if(mm == 0){
			  $("[name= 'winthdrawMoney']").val(m);
			} else if( mm < m){
			  $("[name= 'winthdrawMoney']").val(m);
			}
		})
		// 对页面的密码进行加密 并且判断密码是否正确
		$(":submit").click(function() {
			var id = $("[name=id]").val();
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
				$("#hint1").text("支付密码错误");
				$("#hint1").css("color", "red");
				return false;
			}
			var newPayPassword = $("[name=newPayPassword]").val();
			var newPwdMd5 = md5(newPayPassword);
			$("[name=newPayPassword]").val(newPwdMd5);
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
					src="<%=basePath%>resources/styles/images/logo.png" alt="蓝桥" />
				</a>
			</div>
			<div id="nav" class="glb-nav">
				<ul class="clearfix">
					<li class="glb-nav-uc current"><a href="<%=basePath%>/userController/to_uc"> <span></span>
					</a></li>
					<li class="glb-nav-setting"><a href="<%=basePath%>/secretController/to_safe">
							<span></span>
					</a></li>
					<li class="glb-nav-trade"><a href="<%=basePath%>/userController/userTradeHistory?id=${sessionScope.user.id }"> <span></span>
					</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div id="page">
		<div class="main layout">
			<div class="trade-top-acc">
				<dl class="balance">
					<dt>当前账户余额:</dt>
					<dd class="money">${sessionScope.user.balance }</dd>
					<dd class="unit">元</dd>
				</dl>
			</div>
			<div class="trade-main">
				<div class="trade-tab">
					<div class="trade-tab-hd">
						<ul class="trade-tab-items">
							<li class="current"><a class="tab" href="#">提现至银行卡</a></li>
						</ul>
					</div>
					<div class="trade-tab-bd">
						<form class="form-bd form-bd-trade" action="to_winthdrawMoney"
							id="companyForm" method="post">
							<input type="hidden" id="id" name="id"
								value="${sessionScope.user.secretId.id }" />
							<div class="form-item form-item-bank">
								<h4 class="form-label">提现方式:</h4>
								<div class="form-entity">
									<div class="form-field">
										<div class="form-twin-group">
											<%
												BankCard isdefaul = (BankCard) request.getSession().getAttribute("isdefaul");
												String name = isdefaul.getBankId().getName();
												String str = isdefaul.getCardNumber().substring(15, 19);
												if (name.equals("中国工商银行")) {
											%>
											<div class="bank-card-x" id="yhk">
												<div class="bank-ico bank-icbc"></div>
												<p class="card-prop card-no" id="yhkho"
													style="display: none"><%=isdefaul.getCardNumber()%></p>
												<p class="card-prop card-no">
													******
													<%=str%></p>
											</div>
											<%
												} else if (name.equals("华夏银行")) {
											%>
											<div class="bank-card-x" id="yhk">
												<div class="bank-ico bank-hxb"></div>
												<p class="card-prop card-no" id="yhkho"
													style="display: none"><%=isdefaul.getCardNumber()%></p>
												<p class="card-prop card-no">
													******
													<%=str%></p>
											</div>
											<%
												} else if (name.equals("中国农业银行")) {
											%>
											<div class="bank-card-x" id="yhk">
												<div class="bank-ico bank-abc"></div>
												<p class="card-prop card-no" id="yhkho"
													style="display: none"><%=isdefaul.getCardNumber()%></p>
												<p class="card-prop card-no">
													******
													<%=str%></p>
											</div>
											<%
												} else if (name.equals("平安银行")) {
											%>
											<div class="bank-card-x" id="yhk">
												<div class="bank-ico bank-pingan"></div>
												<p class="card-prop card-no" id="yhkho"
													style="display: none"><%=isdefaul.getCardNumber()%></p>
												<p class="card-prop card-no">
													******
													<%=str%></p>
											</div>
											<%
												} else if (name.equals("招商银行")) {
											%>
											<div class="bank-card-x" id="yhk">
												<div class="bank-ico bank-cmb"></div>
												<p class="card-prop card-no" id="yhkho"
													style="display: none"><%=isdefaul.getCardNumber()%></p>
												<p class="card-prop card-no">
													******
													<%=str%></p>
											</div>
											<%
												} else if (name.equals("中国银联")) {
											%>
											<div class="bank-card-x" id="yhk">
												<div class="bank-ico bank-unionpay"></div>
												<p class="card-prop card-no" id="yhkho"
													style="display: none"><%=isdefaul.getCardNumber()%></p>
												<p class="card-prop card-no">
													******
													<%=str%></p>
											</div>
											<%
												} else if (name.equals("中国民生银行")) {
											%>
											<div class="bank-card-x" id="yhk">
												<div class="bank-ico bank-cmbc"></div>
												<p class="card-prop card-no" id="yhkho"
													style="display: none"><%=isdefaul.getCardNumber()%></p>
												<p class="card-prop card-no">
													******
													<%=str%></p>
											</div>
											<%
												} else if (name.equals("中银富登村镇银行")) {
											%>
											<div class="bank-card-x" id="yhk">
												<div class="bank-ico bank-boc"></div>
												<p class="card-prop card-no" id="yhkho"
													style="display: none"><%=isdefaul.getCardNumber()%></p>
												<p class="card-prop card-no">
													******
													<%=str%></p>
											</div>
											<%
												} else if (name.equals("光大银行")) {
											%>
											<div class="bank-card-x" id="yhk">
												<div class="bank-ico bank-ceb"></div>
												<p class="card-prop card-no" id="yhkho"
													style="display: none"><%=isdefaul.getCardNumber()%></p>
												<p class="card-prop card-no">
													******
													<%=str%></p>
											</div>
											<%
												} else if (name.equals("中国邮政储蓄银行")) {
											%>
											<div class="bank-card-x" id="yhk">
												<div class="bank-ico bank-psbc"></div>
												<p class="card-prop card-no" id="yhkho"
													style="display: none"><%=isdefaul.getCardNumber()%></p>
												<p class="card-prop card-no">
													******
													<%=str%></p>
											</div>
											<%
												} else if (name.equals("哈尔滨银行")) {
											%>
											<div class="bank-card-x" id="yhk">
												<div class="bank-ico bank-hrbcb"></div>
												<p class="card-prop card-no" id="yhkho"
													style="display: none"><%=isdefaul.getCardNumber()%></p>
												<p class="card-prop card-no">
													******
													<%=str%></p>
											</div>
											<%
												} else if (name.equals("中国广发银行")) {
											%>
											<div class="bank-card-x" id="yhk">
												<div class="bank-ico bank-gdb"></div>
												<p class="card-prop card-no" id="yhkho"
													style="display: none"><%=isdefaul.getCardNumber()%></p>
												<p class="card-prop card-no">
													******
													<%=str%></p>
											</div>
											<%
												} else if (name.equals("中国建设银行")) {
											%>
											<div class="bank-card-x" id="yhk">
												<div class="bank-ico bank-ccb"></div>
												<p class="card-prop card-no" id="yhkho"
													style="display: none"><%=isdefaul.getCardNumber()%></p>
												<p class="card-prop card-no">
													******
													<%=str%></p>
											</div>
											<%
												} else {
											%>
											<div class="bank-card-x" id="yhk">
												<p class="card-prop card-no" id="yhkho"
													style="display: none"></p>
												<p class="card-prop card-no"></p>
											</div>
											<%
												}
											%>
										</div>
										<div class="form-twin-group">
											<a id="selectBankBtn" class="rapid-link" href="javascript:;">使用其他银行卡</a>
										</div>
									</div>
								</div>
							</div>
							<div class="form-item form-item-money">
								<h4 class="form-label">提现金额:</h4>
								<div class="form-entity">
									<div class="form-field">
										<div class="form-twin-group">
											<input class="ipt ipt-money" type="text"
												name="winthdrawMoney" id="Allmoney" value=""
												placeholder="请输入提现金额" /> <label id="hint2"></label><br />
										</div>
										<input type="hidden" name="nameBalance" id="nameBalance"
											value="${sessionScope.user.balance }" />
									    <input type="hidden" name="" id="yhkh1" value="<%=request.getSession().getAttribute("yhkh") %>"/>		
										<div class="form-twin-group">
											<span class="unit">元</span>
										</div>
										<div class="form-cb-group form-twin-group form-twin-rapid">
											<input type="checkbox" name="cbAllMoney" class="cb" /> 全部提现
										</div>
										<input type="hidden" id="userId" name="userId"
											value="${sessionScope.user.id }" />
									</div>
								</div>
							</div>
							<div class="form-item form-item-money">
								<h4 class="form-label">请选择交易名称:</h4>
								<div class="form-field">
									&nbsp;&nbsp;&nbsp; <select name="tranNamae" id="getperitiesMoney">
										<option value="">请进行选择:</option>
										<option>账户余额-单次转入</option>
										<option>账户余额-付款</option>
										<option>账户余额-销售</option>
									</select>
								</div>
							</div>
							<div class="form-item form-item-money">
								<h4 class="form-label">手续费:</h4>
								<div class="form-entity">
									<div class="form-field">
										<div class="form-twin-group">
											<p class="form-text strong" id="peritiesMoney">0.00</p>
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
										<input id="pwd" class="ipt ipt-pwd" type="password"
											name="payPassword" value="" placeholder="请输入支付密码" /> <label
											id="hint1"></label><br />
									</div>
								</div>
							</div>
							<div class="form-action clearfix">
								<input type="submit" id="submit" class="glb-btn submit-btn"
									value="立即提现" />
							</div>
						</form>
					</div>
				</div>
			</div>
			<div class="trade-help">
				<div class="trade-help-hd">
					<h3>提现遇到问题?</h3>
				</div>
				<div class="trade-help-bd">
					<dl>
						<dt>1. 如果银行卡信息没填对，资金如何退还？</dt>
						<dd>答：款项会全额退还。 手续费：全部退回蓝桥支付余额。 本金：本金根据支付渠道退回蓝桥支付余额或者银行卡里，请关注。
							1）银行退票一般会在7个工作日内退回（处理速度由银行决定）。
							2）2小时到账的如果超过时间未到账，请用户在2个工作日内（不包括申请当天）关注。</dd>
					</dl>
					<dl>
						<dt>2. 银行已扣钱，为什么银行卡账户没收到？</dt>
						<dd>
							答： 请您别担心，会有以下几种情况：
							<ul>
								<li>
									1、可能由于网络繁忙原因到账延迟，请不用担心，系统会重新核实，一般在第二个工作日晚上18:00自动到账，如果交易已关闭或其他原因导致金额无法正常付款到交易中，金额会退回支付宝账户余额或银行卡中，请您同时关注。
								</li>
								<li>2、如果是网银扣款，请您查询银行扣款明细，扣款商户如果显示不是蓝桥支付公司，请您联系银行客服咨询款项的去向。</li>
							</ul>
							<p>温馨提示：工作日包括周一至周五，不包括双休日、国家的法定节假日。</p>
						</dd>
					</dl>
					<dl>
						<dt>3. 如何取出提现的金额？</dt>
						<dd>
							答：蓝桥支付不允许对充值资金进行提现，可放置在账户中作消费使用。如需要取出充值的金额，可在申请提现失败页面下方点击“立即申请充值退回”，并选择相应的订单号。Visa卡、中国银行，申请充退后款项会在5个工作日到账，其他银行2~5个工作日到账。
						</dd>
					</dl>
					<p class="more-help">
						<a href="#">查看更多帮助</a>
					</p>
				</div>
			</div>
		</div>
		<div id="bottom" class="bottom"></div>
	</div>
</body>
</html>