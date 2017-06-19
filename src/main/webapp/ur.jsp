<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN">
  <head>
  <base href=<%=basePath%>> 
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
   	<title>蓝桥支付-首页</title>
	<meta name="keywords" content="" />
		<meta name="description" content="" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/lanqiaoPayModel/css/global.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/lanqiaoPayModel/css/form.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/lanqiaoPayModel/css/dialog.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/lanqiaoPayModel/css/biz/uc..css"/>
		<script type="text/javascript" src="<%=basePath%>resources/lanqiaoPayModel/js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/lanqiaoPayModel/js/global.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/lanqiaoPayModel/js/util.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/lanqiaoPayModel/js/animation.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/lanqiaoPayModel/js/dialog.js"></script>
		<script type="text/javascript">
			$(function() {

				// 充值
				$("#deposit").click(function() {
					window.open("deposit.html","_blank")
				});

				// 提现
				$("#withdraw").click(function() {

					window.open("withdraw.html","_blank")
				});

				// 转账
				$("#transfer").click(function() {

					window.open("transfer_account.html","_blank")
				});


			});
		</script>
	</head>

	<body>
		<div id="top"></div>
		<div id="header">
			<div class="layout">
				<div id="logo">
					<a href="uc.html">
						<img src="${applicationScope.url}/resources/styles/images/logo.png" alt="蓝桥" />
					</a>
				</div>
				<div id="nav" class="glb-nav">
					<ul class="clearfix">
						<li class="glb-nav-uc current">
							<a href="${applicationScope.url}/enterpriseUser/login">
								<span></span>
							</a>
						</li>
						<li class="glb-nav-setting">
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
		<div class="uc-banner">
			<div class="layout clearfix">
				<div class="uc-banner-left">
					<span class="portrait">
					<img src="${applicationScope.url}/resources/styles/images/df_portrait.jpg" alt="XXX" />
				</span>
				</div>
				<div class="uc-banner-main">
					<ul class="uc-banner-top clearfix">
						<li class="uname">
							下午好:
							<a class="lk" href="#">蓝桥科技有限公司</a>
						</li>

					</ul>
					<ul class="uc-banner-btm clearfix">
						<li class="first">账号: taeststest@lanqiao.com</li>
						<li class="last">最后一次登录时间: 2015-09-16 12:35 长春市</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="main uc-main">
			<div class="layout">
				<div class="uc-main-box clearfix">
					<div class="uc-col-main">
						<div class="uc-acc-box">
							<div class="uc-acc-bd">
								<h3>账户余额</h3>
								<div class="uc-acc-main">
									<p class="item-amount">
										<span class="money">135698.02</span> 元
									</p>
									<a class="item-link" href="trade_list.html">查看明细</a>
								</div>
								<div class="uc-acc-action clearfix">
									<a id="deposit" href="javascript:;" class="uc-opt-btn uc-trade-btn">
										<span class="iconfont icon-btn-deposit"></span> 充值
									</a>
									<a id="withdraw" href="javascript:;" class="uc-opt-btn uc-trade-btn">
										<span class="iconfont icon-btn-withdraw"></span> 提现
									</a>
									<a id="transfer" href="javascript:;" class="uc-opt-btn uc-opt-last uc-trade-btn">
										<span class="iconfont icon-btn-payment"></span> 转账
									</a>
								</div>
							</div>
						</div>
					</div>
					<div class="uc-col-side uc-col-security">
						<div class="side-hd">
							<h3>账户安全</h3>
							<div class="uc-safe-level">
								<p class="level-ico level-ico-1"></p>
							</div>
						</div>
						<div class="side-bd">
							<ul class="uc-col-items">
								<li>
									<span class="iconfont icon-pwd-unsetted"></span>
									<span class="item-txt">已设置支付密码</span>
									<a class="item-act" href="security_choose_type.html">修改</a>
								</li>
								<li>
									<span class="iconfont icon-mobile-setted"></span>
									<span class="item-txt">未绑定手机</span>
									<a class="item-act" href="security_bound_phone.html">绑定</a>
								</li>
								<li>
									<span class="iconfont icon-qa-setted"></span>
									<span class="item-txt">已设置密保问题</span>
									<a class="item-act" href="security_protect_problem.html">修改</a>
								</li>
							</ul>
						</div>
					</div>
					<div class="uc-col-side uc-col-personal">
						<div class="side-hd">
							<h3>个人设置</h3>
						</div>
						<div class="side-bd">
							<ul class="uc-col-items">
								<li>
									<span class="item-txt">
										银行卡:
									</span>
									<em class="item-act">
										<a class="count" href="#">9</a>张
									</em>
								</li>
								<li>
									<span class="item-txt">
										快捷支付:
									</span>
									<span class="item-act">
										<a class="count" href="#">2</a>张
									</span>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="uc-trade-box">
					<div class="uc-trade-hd">
						<h3 class="title">
							<span class="iconfont icon-trade-list"></span>
							最新交易
						</h3>
						<ul class="col-opts col-opts-side">
							<li>
								<a href="#">所有交易</a>
							</li>
							<li>
								<a href="#">充值记录</a>
							</li>
							<li>
								<a href="#">提现记录</a>
							</li>
							<li>
								<a href="#">支付记录</a>
							</li>
						</ul>
					</div>
					<div class="uc-trade-bd clearfix">
						<table class="uc-trade-list">
							<tbody>
								<tr>
									<td class="date">2015-04-21</td>
									<td class="time">18:21</td>
									<td class="subject tal">
										<p class="ellps">淘宝购物-腾讯5Q币5QB/5qb5QQ币5</p>
									</td>
									<td class="money tar">16320.26</td>
									<td class="status">交易成功</td>
								</tr>
								<tr>
									<td class="date">2015-04-21</td>
									<td class="time">18:21</td>
									<td class="subject tal">
										<p class="ellps">财付通-跨行充值</p>
									</td>
									<td class="money tar">5000.00</td>
									<td class="status">交易成功</td>
								</tr>
								<tr>
									<td class="date">2015-04-21</td>
									<td class="time">18:21</td>
									<td class="subject tal">
										<p class="ellps">工商银行-跨行提现</p>
									</td>
									<td class="money tar">136.00</td>
									<td class="status">交易成功</td>
								</tr>
								<tr>
									<td class="date">2015-04-21</td>
									<td class="time">18:21</td>
									<td class="subject tal">
										<p class="ellps">淘宝购物-腾讯5Q币5QB/5qb5QQ币5</p>
									</td>
									<td class="money tar">10020.00</td>
									<td class="status">交易成功</td>
								</tr>
								<tr>
									<td class="date">2015-04-21</td>
									<td class="time">18:21</td>
									<td class="subject tal">
										<p class="ellps">普通支付</p>
									</td>
									<td class="money tar">695.26</td>
									<td class="status">交易成功</td>
								</tr>
								<tr>
									<td class="date">2015-04-21</td>
									<td class="time">18:21</td>
									<td class="subject tal">
										<p class="ellps">淘宝购物-5qb5QQ币5</p>
									</td>
									<td class="money tar">16320.26</td>
									<td class="status">交易成功</td>
								</tr>
								<tr>
									<td class="date">2015-04-21</td>
									<td class="time">18:21</td>
									<td class="subject tal">
										<p class="ellps">淘5qb5QQ币5</p>
									</td>
									<td class="money tar">999.00</td>
									<td class="status">交易成功</td>
								</tr>
								<tr>
									<td class="date">2015-04-21</td>
									<td class="time">18:21</td>
									<td class="subject tal">
										<p class="ellps">淘宝购物-腾讯5Q币5QB/5qb5QQ币5</p>
									</td>
									<td class="money tar">16.00</td>
									<td class="status">交易成功</td>
								</tr>
								<tr>
									<td class="date">2015-04-21</td>
									<td class="time">18:21</td>
									<td class="subject tal">
										<p class="ellps">普通支付</p>
									</td>
									<td class="money tar">73569.01</td>
									<td class="status">交易成功</td>
								</tr>
								<tr class="last">
									<td class="date">2015-04-20</td>
									<td class="time">18:21</td>
									<td class="subject tal">
										<p class="ellps">普通支付</p>
									</td>
									<td class="money tar">3369.00</td>
									<td class="status">交易成功</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div id="bottom" class="bottom"></div>
	</body>

</html>