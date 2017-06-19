<%@ page import="org.lanqiao.pay.base.entity.BankCard"%>
<%@ page import="org.lanqiao.pay.base.entity.Bank"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>转账- 蓝桥支付</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/lanqiaoPayModel/css/global.css"></link>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/lanqiaoPayModel/css/dialog.css"></link>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/lanqiaoPayModel/css/form.css"></link>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/lanqiaoPayModel/css/biz/trade.css"></link>
<script type="text/javascript"
	src="<%=basePath%>resources/lanqiaoPayModel/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/lanqiaoPayModel/js/global.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/lanqiaoPayModel/js/util.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/lanqiaoPayModel/js/group.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/lanqiaoPayModel/js/animation.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/lanqiaoPayModel/js/dialog.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/bootstrap/css/bootstrap.min.css"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/scripts/jquery-3.1.1.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/scripts/md5.js"></script>
<script type="text/javascript">

	$(function() {
		//将转入方的需要输入信息的div隐藏起来
		$("#toBalance").hide();
		$("#toBankCard").hide();
		//将使用默认银行卡和其他银行卡的div隐藏起来
		$("#default").hide();
		$("#others").hide();
		//转入方式是余额的话将隐藏起来的默认和其他银行卡的div继续隐藏
		$("#inlineRadio3").click(function() {
			$("#default").hide();
			$("#others").hide();
		});
		//转入方式是银行卡的话将隐藏起来的默认和其他银行卡的div显示出来
		$("#inlineRadio4").click(function() {
			$("#default").show();
			$("#others").show();
		});
		//判断转入对象是企业且转到对方的余额时，将余额的div显示出来，银行卡的div隐藏起来，将隐藏起来的企业名称显示出来，个人姓名隐藏起来
		$("#inlineRadio2").click(function() {
			$("#toBalanceOrBankCard").click(function() {
				var way = $("#toBalanceOrBankCard").val();
				if (way == "1") {
					$("#toBalance").show();
					$("#user").hide();
					$("#toBankCard").hide();
				} else if (way == "2") {
					$("#toBankCard").show();
					$("#toBalance").hide();
					$("#uName").hide();
				} else {
					$("#toBalance").hide();
					$("#toBankCard").hide();
				}
			});
		});
		//判断转入对象是企业且转到对方的余额时，将余额的div隐藏起来，银行卡的div显示出来，将企业名称隐藏起来，个人姓名显示出来
		$("#inlineRadio1").click(function() {
			$("#toBalanceOrBankCard").click(function() {
				var way = $("#toBalanceOrBankCard").val();
				if (way == "1") {
					$("#toBalance").show();
					$("#ep").hide();
					$("#toBankCard").hide();
				} else if (way == "2") {
					$("#toBankCard").show();
					$("#eName").hide();
					$("#toBalance").hide();
				} else {
					$("#toBalance").hide();
					$("#toBankCard").hide();
				}
			});
		});
		// 对前台支付密码进行加密
		$("#payPwd").blur(function() {
			var payPassword = $("[name=payPassword]").val();
			$("[name=payPassword]").val(md5(payPassword));
		});
	});
	function check() {
	    //获取转账方式 
		var transferWay = $("input[name='inlineRadioOptions1']:checked").val();
		//转账方式为用户的余额时
		if (transferWay == "option3") {
			//判断转账金额是否超出用户余额并且算出手续费
			$("#transferMoney").blur(function() {
			    var money = $("#transferMoney").val();
				//获取转入方
				var toObj = $("#toBalanceOrBankCard").val();
				//获取转入方的银行卡号
				var toBankCardNum = $("#toBankCardNum").val();
				var url0 = "<%=request.getContextPath()%>/BankController/isOverUserBalance";
				var args0 = {
					"money" : money,
					"toObj" : toObj,
					"toBankCardNum" : toBankCardNum,
					"time" : new Date()
				};
				//手续费
				var sxf = null;
				$.ajax({
					type : 'POST',
					async : false,
					url : url0,
					data : args0,
					success : function(result) {
						if (result != "false") {
							sxf = result;
							$("#sxf").text(sxf);
							return true;
						} else {
							$("#moneyMsg").text("余额不足,请重新操作");
							$("#moneyMsg").css("color", "red");
							return false;
						}
					}
				});
				if (money < 0) {
					$("#moneyMsg").text("输入错误,请重新操作");
					$("#moneyMsg").css("color", "red");
					return false;
				}
			});
			//判断支付密码是否正确
			$("#payPwd").blur(function() {
				var md5PayPwd = $("#payPwd").val();
				var url4 = "<%=request.getContextPath()%>/BankController/isPayPwd";
				if (md5PayPwd == null) {
					$("#errorMsg").text("支付密码不能为空");
					$("#errorMsg").css("color", "red");
					return false;
				} else {
					var args4 = {
						"md5Pwd" : md5PayPwd,
						"time" : new Date()
					};
					$.ajax({
						type : 'POST',
						async : false,
						url : url4,
						data : args4,
						success : function(result) {
							if (result == "true") {
								$("#errorMsg").text("");
								$("#errorMsg").css("color", "green");
								return true;
							} else {
								$("#errorMsg").text("支付密码错误");
								$("#errorMsg").css("color", "red");
								return false;
							}
						}
					});
				}
			});
			//提交表单
			$("#submit").click(function() {
				//获取转入对象
				var obj = $("input[name='inlineRadioOptions']:checked").val();
				//获取转入方
				var toObj = $("#toBalanceOrBankCard").val();
				//获取交易名称
				var transferName = $("#transactionName").val();
				//获取转账金额
				var money = $("#transferMoney").val();
				//获取企业账户
				var epUserPhone = $("#epName").val();
				//获取转入的银行卡号
				var toBankCardNum = $("#toBankCardNum").val();
				if (obj == "option1") {
						//向个人转账
						//判断使用余额还是使用银行卡转账
						if (transferWay == "option3") {
							//使用余额转账                  	
							//判断向个人的余额还是银行卡转账
							if (toObj == "1") {
								//向个人的支付宝转账
								alert("userTransferToUser");
								var userName = $("#userName").val();
								var url5 = "<%=request.getContextPath()%>/BankController/userTransfertoUser";
								var args5 = {
									"userName" : userName,
									"transferName" : transferName,
									"money" : money,
									"Date" : new Date()
								};
							} else {
							alert("userTransferToUserBank");
								//向个人的银行卡转账
								var url5 = "<%=request.getContextPath()%>/BankController/userTransfertoBank";
								var args5 = {
									"toBankNumber" : toBankCardNum,
									"transferName" : transferName,
									"money" : money,
									"Date" : new Date(),
								};
							}
						} 
				} else {
					var url5 = "<%=request.getContextPath()%>/BankController/transferByUserBalance";
				}
				var args5 = {
					"toObj" : toObj,
					"transferName" : transferName,
					"money" : money,
					"epUserPhone" : epUserPhone,
					"toBankCardNum" : toBankCardNum,
					"time" : new Date()
				};
				$.ajax({
					type : 'POST',
					async : false,
					url : url5,
					data : args5,
					success : function(result) {
						alert(result);
						if (result == "操作成功") {
							window.location.href = "<%=request.getContextPath()%>/BankController/to_success";
						}
					}
				});
			});
		} else {
			//转账方式为用户的银行卡时
			//选中银行卡
			$(".bank-card-x").click(function() {
				var div1 = document.getElementById("yhk");
				var fromBankNumber = (this.getElementsByTagName("p")[0]).textContent;
				var url = "<%=request.getContextPath()%>/BankController/isQuickPay";
				var args = {
					"fromBankNumber" : fromBankNumber,
					"time" : new Date()
				};
				//判断是否开通快捷支付
				$.ajax({
					type : 'POST',
					async : false,
					url : url,
					data : args,
					success : function(data) {
						$("#bankCardMsg").text(data);
						$("#bankCardMsg").css("color", "blue");
					}
				});
                //判断转账金额是否超出银行卡余额并且算出手续费
				$("#transferMoney").blur(function() {
					var money = $("#transferMoney").val();
					//获取转账方式 
					var transferWay = $("input[name='inlineRadioOptions1']:checked").val();
					//获取转入方
					var toObj = $("#toBalanceOrBankCard").val();
					//获取转入方的银行卡号
					var toBankCardNum = $("#toBankCardNum").val();
					var url1 = "<%=request.getContextPath()%>/BankController/IsOverBankCardBalance";
					var args1 = {
						"transferWay" : transferWay,
						"fromBankNumber" : fromBankNumber,
						"money" : money,
						"toObj" : toObj,
						"toBankCardNum" : toBankCardNum,
						"time" : new Date()
					};
					var tag = 0;
					//手续费
					var sxf = null;
					$.ajax({
						type : 'POST',
						async : false,
						url : url1,
						data : args1,
						success : function(data) {
							if (data != "false") {
								sxf = data;
								$("#sxf").text(sxf);
								return true;
							} else {
								$("#moneyMsg").text("余额不足,请重新操作");
								$("#moneyMsg").css("color", "red");
								return false;
							}
						}
					});
					if (money < 0) {
						$("#moneyMsg").text("输入错误,请重新操作");
						$("#moneyMsg").css("color", "red");
						return false;
					}
				});
                //判断支付密码或银行卡密码是否为空或者是否正确
				$("#payPwd").blur(function() {
					var md5PayPwd = $("#payPwd").val();
					var url2 = "<%=request.getContextPath()%>/BankController/isPayPwdOrCardPwd";
					if (md5PayPwd == null) {
						$("#errorMsg").text("支付密码不能为空");
						$("#errorMsg").css("color", "red");
						return false;
					} else {
						var args2 = {
							"fromBankNumber" : fromBankNumber,
							"md5Pwd" : md5PayPwd,
							"time" : new Date()
						};
						$.ajax({
							type : 'POST',
							async : false,
							url : url2,
							data : args2,
							success : function(result) {
								if (result == "true") {
									$("#errorMsg").text("");
									$("#errorMsg").css("color", "green");
									return true;
								} else {
									$("#errorMsg").text("支付密码错误");
									$("#errorMsg").css("color", "red");
									return false;
								}
							}
						});
					}
				});
				//提交表单
				$("#submit").click(function() {
					//获取转入对象
					var obj = $("input[name='inlineRadioOptions']:checked").val();
					//获取转账方式 
					var transferWay = $("input[name='inlineRadioOptions1']:checked").val();
					//获取转入方
					var toObj = $("#toBalanceOrBankCard").val();
					//获取交易名称
					var transferName = $("#transactionName").val();
					//获取转账金额
					var money = $("#transferMoney").val();
					//获取企业账户
					var epUserPhone = $("#epName").val();
					//获取转入的银行卡号
					var toBankCardNum = $("#toBankCardNum").val();
					if (obj == "option1") {
						//向个人转账
						//判断使用余额还是使用银行卡转账
						if (transferWay == "option4") {
							//判断向个人的余额还是银行卡转账
							if (toObj == "1") {
								//向个人的支付宝转账
								var userName = $("#userName").val();
								alert("userBankTransferToUser");
								var url3 = "<%=request.getContextPath()%>/BankController/bankTransfertoUser";
								alert(url3);
								var args3 = {
									"fromBankNumber" : fromBankNumber,
									"userName" : userName,
									"transferName" : transferName,
									"money" : money,
									"Date" : new Date(),
								};
							} else {
								//向个人的银行卡转账
								alert("userBankTransferToUserBank");
								var url3 = "<%=request.getContextPath()%>/BankController/bankTransfertoBank";
								var args3 = {
									"toBankNumber" : toBankCardNum,
									"fromBankNumber" : fromBankNumber,
									"transferName" : transferName,
									"money" : money,
									"Date" : new Date(),
								};
							}
						}
					} else {
						var url3 = "<%=request.getContextPath()%>/BankController/transferByUserBankCard";
						var args3 = {
							"fromBankNumber" : fromBankNumber,
							"transferWay" : transferWay,
							"toObj" : toObj,
							"transferName" : transferName,
							"money" : money,
							"epUserPhone" : epUserPhone,
							"toBankCardNum" : toBankCardNum,
							"time" : new Date()
						};
						
					}
					$.ajax({
							type : 'POST',
							async : false,
							url : url3,
							data : args3,
							success : function(result) {
								alert(result);
								if (result == "操作成功") {
									window.location.href = "<%=request.getContextPath()%>/BankController/to_success";
								}
							}
						});
				});
			});
		}
	}
</script>
</head>
<body>
	<div id="top"></div>
	<div id="header">
		<div class="layout">
			<div id="logo">
				<a href="userController/to_uc"> <img
					src="<%=basePath%>resources/styles/images/logo.png" alt="蓝桥" />
				</a>
			</div>
			<div id="nav" class="glb-nav">
				<ul class="clearfix">
					<li class="glb-nav-uc current"><a href="<%=basePath%>/userController/to_uc"> <span></span>
					</a></li>
					<li class="glb-nav-setting"><a href="<%=basePath%>/secretController/to_safe"> <span></span>
					</a></li>
					<li class="glb-nav-trade"><a href="<%=basePath%>/userController/userTradeHistory?id=${sessionScope.user.id}"> <span></span>
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
					<dd class="money"><%=request.getSession().getAttribute("balance")%></dd>
					<dd class="unit">元</dd>
				</dl>
			</div>
			<div class="trade-main">
				<div class="trade-tab">
					<div class="trade-tab-hd">
						<ul class="trade-tab-items">
							<li class="current"><a class="tab" href="#">转账</a></li>
						</ul>
					</div>
					<div class="trade-tab-bd">
						<form class="form-bd form-bd-trade" action="" id="companyForm">
							<input type="hidden" name="id" value="${sessionScope.user.id }" />
							<div class="form-item form-item-aspect">
								<h4 class="form-label">转入对象:</h4>
								&nbsp;&nbsp; <label class="radio-inline"> <input
									type="radio" name="inlineRadioOptions" id="inlineRadio1"
									value="option1" /> 个人
								</label> &nbsp;&nbsp; <label class="radio-inline"> <input
									type="radio" name="inlineRadioOptions" id="inlineRadio2"
									value="option2" /> 企业
								</label>
							</div>
							<div class="items-group-box group-fold-only group-fold-default">
								<h4 class="form-label">转账方式:</h4>
								<br /> &nbsp;&nbsp;<label class="radio-inline"> <input
									type="radio" name="inlineRadioOptions1" id="inlineRadio3"
									value="option3" onclick="check()"/>余额
								</label> &nbsp;&nbsp; <label class="radio-inline"> <input
									type="radio" name="inlineRadioOptions1" id="inlineRadio4"
									value="option4" onclick="check()"/>银行卡
								</label><br></br>
								<div class="item-group form-group" id="default">
									<div class="group-hd clearfix">
										<div class="group-hd-txt">
											<h3>使用默认银行卡</h3>
											<p>有默认银行卡选择此方式</p>
										</div>
									</div>
									<div class="group-bd type-items">
										<div class="modal-content">
											<ul class="bank-items bankcard-items">
												<%
													BankCard defaultCard = (BankCard) request.getSession().getAttribute("isDefaultCard");
													String bankName = defaultCard.getBankId().getName();
													String bankNum = defaultCard.getCardNumber();
													String str = bankNum.substring(15, 19);
													if (bankName.equals("中国工商银行")) {
												%>
												<li>
													<div class="bank-card-x" id="yhk">
														<div class="bank-ico bank-icbc"></div>
														<p class="card-prop card-no" id="yhkho"
															style="display: none"><%=bankNum%></p>
														<p class="card-prop card-no">
															******
															<%=str%></p>
													</div>
												</li>
												<%
													} else if (bankName.equals("华夏银行")) {
												%>
												<li>
													<div class="bank-card-x" id="yhk">
														<div class="bank-ico bank-hxb"></div>
														<p class="card-prop card-no" id="yhkho"
															style="display: none"><%=bankNum%></p>
														<p class="card-prop card-no">
															******
															<%=str%></p>
													</div>
												</li>
												<%
													} else if (bankName.equals("中国农业银行")) {
												%>
												<li>
													<div class="bank-card-x" id="yhk">
														<div class="bank-ico bank-abc"></div>
														<p class="card-prop card-no" id="yhkho"
															style="display: none"><%=bankNum%></p>
														<p class="card-prop card-no">
															******
															<%=str%></p>
													</div>
												</li>
												<%
													} else if (bankName.equals("平安银行")) {
												%>
												<li>
													<div class="bank-card-x" id="yhk">
														<div class="bank-ico bank-pingan"></div>
														<p class="card-prop card-no" id="yhkho"
															style="display: none"><%=bankNum%></p>
														<p class="card-prop card-no">
															******
															<%=str%></p>
													</div>
												</li>
												<%
													} else if (bankName.equals("招商银行")) {
												%>
												<li>
													<div class="bank-card-x" id="yhk">
														<div class="bank-ico bank-cmb"></div>
														<p class="card-prop card-no" id="yhkho"
															style="display: none"><%=bankNum%></p>
														<p class="card-prop card-no">
															******
															<%=str%></p>
													</div>
												</li>
												<%
													} else if (bankName.equals("中国银联")) {
												%>
												<li>
													<div class="bank-card-x" id="yhk">
														<div class="bank-ico bank-unionpay"></div>
														<p class="card-prop card-no" id="yhkho"
															style="display: none"><%=bankNum%></p>
														<p class="card-prop card-no">
															******
															<%=str%></p>
													</div>
												</li>
												<%
													} else if (bankName.equals("中国民生银行")) {
												%>
												<li>
													<div class="bank-card-x" id="yhk">
														<div class="bank-ico bank-cmbc"></div>
														<p class="card-prop card-no" id="yhkho"
															style="display: none"><%=bankNum%></p>
														<p class="card-prop card-no">
															******
															<%=str%></p>
													</div>
												</li>
												<%
													} else if (bankName.equals("中银富登村镇银行")) {
												%>
												<li>
													<div class="bank-card-x" id="yhk">
														<div class="bank-ico bank-boc"></div>
														<p class="card-prop card-no" id="yhkho"
															style="display: none"><%=bankNum%></p>
														<p class="card-prop card-no">
															******
															<%=str%></p>
													</div>
												</li>
												<%
													} else if (bankName.equals("光大银行")) {
												%>
												<li>
													<div class="bank-card-x" id="yhk">
														<div class="bank-ico bank-ceb"></div>
														<p class="card-prop card-no" id="yhkho"
															style="display: none"><%=bankNum%></p>
														<p class="card-prop card-no">
															******
															<%=str%></p>
													</div>
												</li>
												<%
													} else if (bankName.equals("中国邮政储蓄银行")) {
												%>
												<li>
													<div class="bank-card-x" id="yhk">
														<div class="bank-ico bank-psbc"></div>
														<p class="card-prop card-no" id="yhkho"
															style="display: none"><%=bankNum%></p>
														<p class="card-prop card-no">
															******
															<%=str%></p>
													</div>
												</li>
												<%
													} else if (bankName.equals("哈尔滨银行")) {
												%>
												<li>
													<div class="bank-card-x" id="yhk">
														<div class="bank-ico bank-hrbcb"></div>
														<p class="card-prop card-no" id="yhkho"
															style="display: none"><%=bankNum%></p>
														<p class="card-prop card-no">
															******
															<%=str%></p>
													</div>
												</li>
												<%
													} else if (bankName.equals("中国广发银行")) {
												%>
												<li>
													<div class="bank-card-x" id="yhk">
														<div class="bank-ico bank-gdb"></div>
														<p class="card-prop card-no" id="yhkho"
															style="display: none"><%=bankNum%></p>
														<p class="card-prop card-no">
															******
															<%=str%></p>
													</div>
												</li>
												<%
													} else if (bankName.equals("中国建设银行")) {
												%>
												<li>
													<div class="bank-card-x" id="yhk">
														<div class="bank-ico bank-ccb"></div>
														<p class="card-prop card-no" id="yhkho"
															style="display: none"><%=bankNum%></p>
														<p class="card-prop card-no">
															******
															<%=str%></p>
													</div>
												</li>
												<%
													} else {
												%>
												<li>
													<div class="bank-card-x" id="yhk">
														<div class="bank-ico bank-ccb"></div>
														<p class="card-prop card-no" id="yhkho"
															style="display: none"></p>
														<p class="card-prop card-no"></p>
													</div>
												</li>
												<%
													}
												%>
											</ul>
										</div>
									</div>
								</div>
								<br />
								<div class="item-group form-group" id="others">
									<div class="group-hd clearfix">
										<div class="group-hd-txt">
											<h3>使用其他银行卡</h3>
											<p>无默认银行卡选择此方式</p>
										</div>
									</div>
									<ul class="group-bd type-items">
										<div class="modal-content">
											<ul class="bank-items bankcard-items">
												<%
													List<BankCard> bankCards = (List<BankCard>) request.getSession().getAttribute("notDefaultCards");
													for (BankCard bankCard : bankCards) {
														String name = bankCard.getBankId().getName();
														String substr = bankCard.getCardNumber().substring(15, 19);
														if (name.equals("中国工商银行")) {
												%>
												<li>
													<div class="bank-card-x" id="yhk">
														<div class="bank-ico bank-icbc"></div>
														<p class="card-prop card-no" id="yhkho"
															style="display: none"><%=bankCard.getCardNumber()%></p>
														<p class="card-prop card-no">
															******
															<%=substr%></p>
													</div>
												</li>
												<%
													} else if (name.equals("华夏银行")) {
												%>
												<li>
													<div class="bank-card-x" id="yhk">
														<div class="bank-ico bank-hxb"></div>
														<p class="card-prop card-no" id="yhkho"
															style="display: none"><%=bankCard.getCardNumber()%></p>
														<p class="card-prop card-no">
															******
															<%=substr%></p>
													</div>
												</li>
												<%
													} else if (name.equals("中国农业银行")) {
												%>
												<li>
													<div class="bank-card-x" id="yhk">
														<div class="bank-ico bank-abc"></div>
														<p class="card-prop card-no" id="yhkho"
															style="display: none"><%=bankCard.getCardNumber()%></p>
														<p class="card-prop card-no">
															******
															<%=substr%></p>
													</div>
												</li>
												<%
													} else if (name.equals("平安银行")) {
												%>
												<li>
													<div class="bank-card-x" id="yhk">
														<div class="bank-ico bank-pingan"></div>
														<p class="card-prop card-no" id="yhkho"
															style="display: none"><%=bankCard.getCardNumber()%></p>
														<p class="card-prop card-no">
															******
															<%=substr%></p>
													</div>
												</li>
												<%
													} else if (name.equals("招商银行")) {
												%>
												<li>
													<div class="bank-card-x" id="yhk">
														<div class="bank-ico bank-cmb"></div>
														<p class="card-prop card-no" id="yhkho"
															style="display: none"><%=bankCard.getCardNumber()%></p>
														<p class="card-prop card-no">
															******
															<%=substr%></p>
													</div>
												</li>
												<%
													} else if (name.equals("中国银联")) {
												%>
												<li>
													<div class="bank-card-x" id="yhk">
														<div class="bank-ico bank-unionpay"></div>
														<p class="card-prop card-no" id="yhkho"
															style="display: none"><%=bankCard.getCardNumber()%></p>
														<p class="card-prop card-no">
															******
															<%=substr%></p>
													</div>
												</li>
												<%
													} else if (name.equals("中国民生银行")) {
												%>
												<li>
													<div class="bank-card-x" id="yhk">
														<div class="bank-ico bank-cmbc"></div>
														<p class="card-prop card-no" id="yhkho"
															style="display: none"><%=bankCard.getCardNumber()%></p>
														<p class="card-prop card-no">
															******
															<%=substr%></p>
													</div>
												</li>
												<%
													} else if (name.equals("中银富登村镇银行")) {
												%>
												<li>
													<div class="bank-card-x" id="yhk">
														<div class="bank-ico bank-boc"></div>
														<p class="card-prop card-no" id="yhkho"
															style="display: none"><%=bankCard.getCardNumber()%></p>
														<p class="card-prop card-no">
															******
															<%=substr%></p>
													</div>
												</li>
												<%
													} else if (name.equals("光大银行")) {
												%>
												<li>
													<div class="bank-card-x" id="yhk">
														<div class="bank-ico bank-ceb"></div>
														<p class="card-prop card-no" id="yhkho"
															style="display: none"><%=bankCard.getCardNumber()%></p>
														<p class="card-prop card-no">
															******
															<%=substr%></p>
													</div>
												</li>
												<%
													} else if (name.equals("中国邮政储蓄银行")) {
												%>
												<li>
													<div class="bank-card-x" id="yhk">
														<div class="bank-ico bank-psbc"></div>
														<p class="card-prop card-no" id="yhkho"
															style="display: none"><%=bankCard.getCardNumber()%></p>
														<p class="card-prop card-no">
															******
															<%=substr%></p>
													</div>
												</li>
												<%
													} else if (name.equals("哈尔滨银行")) {
												%>
												<li>
													<div class="bank-card-x" id="yhk">
														<div class="bank-ico bank-hrbcb"></div>
														<p class="card-prop card-no" id="yhkho"
															style="display: none"><%=bankCard.getCardNumber()%></p>
														<p class="card-prop card-no">
															******
															<%=substr%></p>
													</div>
												</li>
												<%
													} else if (name.equals("中国广发银行")) {
												%>
												<li>
													<div class="bank-card-x" id="yhk">
														<div class="bank-ico bank-gdb"></div>
														<p class="card-prop card-no" id="yhkho"
															style="display: none"><%=bankCard.getCardNumber()%></p>
														<p class="card-prop card-no">
															******
															<%=substr%></p>
													</div>
												</li>
												<%
													} else if (name.equals("中国建设银行")) {
												%>
												<li>
													<div class="bank-card-x" id="yhk">
														<div class="bank-ico bank-ccb"></div>
														<p class="card-prop card-no" id="yhkho"
															style="display: none"><%=bankCard.getCardNumber()%></p>
														<p class="card-prop card-no">
															******
															<%=substr%></p>
													</div>
												</li>
												<%
													} else {
												%>
												<li>
													<div class="bank-card-x" id="yhk">
														<div class="bank-ico bank-ccb"></div>
														<p class="card-prop card-no" id="yhkho"
															style="display: none"></p>
														<p class="card-prop card-no"></p>
													</div>
												</li>
												<%
													}
													}
												%>
											</ul>
										</div>
									</ul>
								</div>
							</div>
							<br />
							<div class="form-item form-item-money">
								<h4 class="form-label">转入方:</h4>
								&nbsp;&nbsp; <select name="toBalanceOrBankCard"
									id="toBalanceOrBankCard">
									<option value="">请进行选择</option>
									<option value="1">转到对方余额</option>
									<option value="2">转到对方银行卡</option>
								</select> <br></br> &nbsp;&nbsp;&nbsp;&nbsp;
								<div class="form-entity" id="toBalance">
									<div class="form-field-group">
										<div id="ep" name="ep">
											企业账户:&nbsp;<input class="ipt ipt-ename" type="text"
												name="epName" id="epName" value="" placeholder="请输入企业账户" />
										</div>
										<div id="user" name="user">
											个人账户:&nbsp;<input class="ipt ipt-uname" type="text"
												name="userName" id="userName" value="" placeholder="请输入对方账户" />
										</div>
									</div>
								</div>
								<div class="form-entity" id="toBankCard">
									<div class="form-field-group">
										<div id="eName" name="eName">
											企业名称:<input class="ipt ipt-ename" type="text" name="ename"
												id="ename" value="" placeholder="请输入企业的名称" />
										</div>
										<div id="uName" name="uName">
											姓&nbsp;&nbsp;&nbsp;&nbsp;名：<input class="ipt ipt-uname"
												type="text" name="uname" id="uname" value=""
												placeholder="请输入对方的姓名" />
										</div>
										银行卡号:<input class="ipt ipt-cardNum" type="text"
											name="toBankCardNum" id="toBankCardNum" value=""
											placeholder="请输入对方的银行卡号" />
									</div>
								</div>
								<br></br>
							</div>
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
							<div class="form-item form-item-money">
								<h4 class="form-label">转账金额:</h4>
								<div class="form-entity">
									<div class="form-field">
										<div class="form-twin-group">
											<input class="ipt ipt-money" type="text" name="transferMoney"
												id="transferMoney" value="转账金额" placeholder="请输入转账金额" />
										</div>
										<div class="form-twin-group">
											<span class="unit">元</span>
										</div>
										<label id="moneyMsg"></label>
									</div>
								</div>
							</div>
							<div class="form-item form-item-money">
								<h4 class="form-label">手续费:</h4>
								<div class="form-entity">
									<div class="form-field">
										<div class="form-twin-group">
											<p class="form-text strong" id="sxf">0.00</p>
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
											id="bankCardMsg"></label><br /> <label id="errorMsg"></label>
									</div>
								</div>
							</div>
							<div class="form-action clearfix">
								<input type="submit" id="submit" class="glb-btn submit-btn"
									value="立即转账" />
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>


	<div id="bottom" class="bottom"></div>
</body>
</html>


