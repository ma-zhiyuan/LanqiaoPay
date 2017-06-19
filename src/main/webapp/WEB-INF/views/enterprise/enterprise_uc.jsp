<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>蓝桥支付-首页</title>
		<link rel="stylesheet" type="text/css" href="${applicationScope.url}/resources/lanqiaoPayModel/css/global.css">
		<link rel="stylesheet" type="text/css" href="${applicationScope.url}/resources/lanqiaoPayModel/css/form.css">
		<link rel="stylesheet" type="text/css" href="${applicationScope.url}/resources/lanqiaoPayModel/css/dialog.css">
		<link rel="stylesheet" type="text/css" href="${applicationScope.url}/resources/lanqiaoPayModel/css/biz/uc.css">
		<script type="text/javascript" src="${applicationScope.url}/resources/lanqiaoPayModel/js/jquery.js"></script>
		<script type="text/javascript" src="${applicationScope.url}/resources/lanqiaoPayModel/js/global.js"></script>
		<script type="text/javascript" src="${applicationScope.url}/resources/lanqiaoPayModel/js/util.js"></script>
		<script type="text/javascript" src="${applicationScope.url}/resources/lanqiaoPayModel/js/animation.js"></script>
		<script type="text/javascript" src="${applicationScope.url}/resources/lanqiaoPayModel/js/dialog.js"></script>
		<script type="text/javascript">
		 $.getScript("http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js", function () {
		     var country = remote_ip_info["country"];
	         var province = remote_ip_info["province"] + '省';
	         var city = remote_ip_info["city"] + '市';
	         var address=country+" "+province+" "+city;
	         var date=new Date();
	         var year=date.getFullYear();
	         var month=date.getMonth()+1;
	         var day=date.getDate();
	         var hour=date.getHours();
	         var minim=date.getMinutes();
	         var second=date.getSeconds();
	         var str="本次登录时间:"+year+"-"+month+"-"+day+"   "+hour+":"+minim+":"+second+"    地点:"+address;
	         $("#address").text(str);        
          });

          function getAll(){
       		$("#recharge").hide();
       		$("#withdrawal").hide();
       		$("#transfer1").hide();
       		$("#all").show();
          };
          function getRecharge(){
          	$("#all").hide();
          	$("#withdrawal").hide();
          	$("#transfer1").hide();
          	$("#recharge").show();
          };
          function getWithdrawal(){
       		$("#all").hide();
       		$("#recharge").hide();
       		$("#transfer1").hide();
       		$("#withdrawal").show();
          };
          function getTransfer(){
       		$("#all").hide();
       		$("#recharge").hide();
       		$("#withdrawal").hide();
       		$("#transfer1").show();
          };
		</script>
	</head>

	<body>
		<div id="top"></div>
		<div id="header">
			<div class="layout">
				<div id="logo">
					<a href="http://www.lanqiao.org">
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
							<a href="${applicationScope.url}/enterpriseUser/getEnterpriseUser">
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
						<div style="width:100px;height:100px;border-radius:100px">
							<img src="${sessionScope.enterpriseUser.photo}"/>
						</div>
				</span>
				</div>
				<div class="uc-banner-main">
					<ul class="uc-banner-top clearfix">
						<li class="uname">
							下午好:
							${sessionScope.enterpriseUser.enterprise.enterpriseName}
						</li>

					</ul>
					<ul class="uc-banner-btm clearfix">
						<li class="first">账号: ${sessionScope.enterpriseUser.email}</li>
						<li class="last" id="address"></li>
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
										<c:if test="${empty sessionScope.enterpriseUser.balance }">
											<span class="money">0</span> 元
										</c:if>
										<c:if test="${!empty sessionScope.enterpriseUser.balance }">
											<span class="money">${sessionScope.enterpriseUser.balance }</span> 元
										</c:if>
									</p>
								</div>
								<div class="uc-acc-action clearfix">
									<a id="deposit" href="<%=basePath %>enterpriseUser/to_enterpriseUser_deposit" class="uc-opt-btn uc-trade-btn">
										<span class="iconfont icon-btn-deposit"></span> 充值
									</a>
									<a id="withdraw" href="<%=request.getContextPath() %>/BankController/enterprise_withdraw" class="uc-opt-btn uc-trade-btn">
										<span class="iconfont icon-btn-withdraw"></span> 提现
									</a>
									<a id="transfer" href="<%=request.getContextPath() %>/transferController/to_Enterprise_transfer" class="uc-opt-btn uc-opt-last uc-trade-btn">
										<span class="iconfont icon-btn-payment"></span> 转账
									</a>
								</div>
							</div>
						</div>
					</div>
					<div class="uc-col-side uc-col-security">
						<div class="side-hd">
							<h3>账户安全</h3>
							<c:if test="${sessionScope.enterpriseUser.secret != null }">
								<div class="uc-safe-level">
									<p class="level-ico level-ico-3"></p>
								</div>
							</c:if>
							<c:if test="${sessionScope.enterpriseUser.secret == null }">
								<div class="uc-safe-level">
									<p class="level-ico level-ico-2"></p>
								</div>
							</c:if>
						</div>
						<div class="side-bd">
							<ul class="uc-col-items">
								<li>
									<span class="iconfont icon-pwd-unsetted"></span>
									<span class="item-txt">已设置支付密码</span>
									<a class="item-act" href="${applicationScope.url}/secretController/skipUpdatePayPwd_enterprise">修改</a>
								</li>
								<li>
									<span class="iconfont icon-mobile-setted"></span>
									<span class="item-txt">已绑定邮箱</span>
									<a class="item-act" href="${applicationScope.url}/secretController/updateEmail">修改</a>
								</li>
								<c:if test="${sessionScope.enterpriseUser.secret != null }">
									<li>
										<span class="iconfont icon-qa-setted"></span>
										<span class="item-txt">已设置密保问题</span>
										<a class="item-act" href="${applicationScope.url}/secretController/safe_secret_enterprise?id=1">设置</a>
									</li>
								</c:if>
								<c:if test="${sessionScope.enterpriseUser.secret == null }">
									<li>
										<span class="iconfont icon-qa-setted"></span>
										<span class="item-txt">未设置密保问题</span>
										<a class="item-act" href="${applicationScope.url}/secretController/safe_secret_enterprise?id=1">设置</a>
									</li>
								</c:if>
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
										<a class="count" href="${applicationScope.url }/BankCardController/EnterBankCard">${sessionScope.allCardNum }</a>张
									</em>
								</li>
								<li>
									<span class="item-txt">
										快捷支付:
									</span>
									<span class="item-act">
										<a class="count" href="${applicationScope.url }/BankCardController/EnterBankCard">${sessionScope.payQuickNum }</a>张
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
								<a href="javascript:getAll()">所有交易</a>
							</li>
							<li>
								<a href="javascript:getRecharge()">充值记录</a>
							</li>
							<li>
								<a href="javascript:getWithdrawal()">提现记录</a>
							</li>
							<li>
								<a href="javascript:getTransfer()">支付记录</a>
							</li>
						</ul>
					</div>
					<div class="uc-trade-bd clearfix" id="all">
						<table class="uc-trade-list">
							<tbody>
								<c:forEach items="${sessionScope.enterpriseRecharge}" var="recharges" begin="0" end="2">
									<tr>
										<td class="date"><fmt:formatDate type="date" value="${recharges.time}"/></td>
										<td class="time"><fmt:formatDate type="time" value="${recharges.time}"/></td>
										<td class="subject tal">
											<p class="ellps">${recharges.transaction_describe}</p>
										</td>
										<td class="money tar">${recharges.money}</td>
										<c:if test="${recharges.state==5}"><td class="status">充值成功</td></c:if>
									</tr>
								</c:forEach>
								<c:forEach items="${sessionScope.enterpriseWithdrawal}" var="withdrawals" begin="0" end="2">
									<tr>
										<td class="date"><fmt:formatDate type="date" value="${withdrawals.time}"/></td>
										<td class="time"><fmt:formatDate type="time" value="${withdrawals.time}"/></td>
										<td class="subject tal">
											<p class="ellps">${withdrawals.transaction_describe}</p>
										</td>
										<td class="money tar">${withdrawals.money}</td>
										<c:if test="${withdrawals.state==3}"><td class="status">等待审核</td></c:if>
										<c:if test="${withdrawals.state==4}"><td class="status">通过审核</td></c:if>
									</tr>
								</c:forEach>
								<c:forEach items="${sessionScope.enterprisePayItems}" var="transfers" begin="0" end="2">
									<tr>
										<td class="date"><fmt:formatDate type="date" value="${transfers.time}"/></td>
										<td class="time"><fmt:formatDate type="time" value="${transfers.time}"/></td>
										<td class="subject tal">
											<p class="ellps">${transfers.transferDescription}</p>
										</td>
										<td class="money tar">${transfers.money}</td>
										<c:if test="${transfers.state==0}"><td class="status">等待审核</td></c:if>
										<c:if test="${transfers.state==1}"><td class="status">通过审核</td></c:if>
										<c:if test="${transfers.state==2}"><td class="status">交易成功</td></c:if>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<!-- 企业充值列表 -->
					<div class="uc-trade-bd clearfix" id="recharge" style="display:none">
						<table class="uc-trade-list">
							<tbody>
								<c:forEach items="${sessionScope.enterpriseRecharge}" var="recharges2" begin="0" end="9">
									<tr>
										<td class="date"><fmt:formatDate type="date" value="${recharges2.time}"/></td>
										<td class="time"><fmt:formatDate type="time" value="${recharges2.time}"/></td>
										<td class="subject tal">
											<p class="ellps">${recharges2.transaction_describe}</p>
										</td>
										<td class="money tar">${recharges2.money}</td>
										<c:if test="${recharges2.state==5}"><td class="status">充值成功</td></c:if>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<!-- 企业提现列表 -->
					<div class="uc-trade-bd clearfix" id="withdrawal" style="display:none">
						<table class="uc-trade-list">
							<tbody>
								<c:forEach items="${sessionScope.enterpriseWithdrawal}" var="withdrawals2" begin="0" end="9">
									<tr>
										<td class="date"><fmt:formatDate type="date" value="${withdrawals2.time}"/></td>
										<td class="time"><fmt:formatDate type="time" value="${withdrawals2.time}"/></td>
										<td class="subject tal">
											<p class="ellps">${withdrawals2.transaction_describe}</p>
										</td>
										<td class="money tar">${withdrawals2.money}</td>
										<c:if test="${withdrawals2.state==3}"><td class="status">等待审核</td></c:if>
										<c:if test="${withdrawals2.state==4}"><td class="status">通过审核</td></c:if>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					
					<!-- 企业支付列表 -->
					<div class="uc-trade-bd clearfix" id="transfer1" style="display:none">
						<table class="uc-trade-list">
							<tbody>
								<c:forEach items="${sessionScope.enterprisePayItems}" var="transfers2" begin="0" end="9">
									<tr>
										<td class="date"><fmt:formatDate type="date" value="${transfers2.time}"/></td>
										<td class="time"><fmt:formatDate type="time" value="${transfers2.time}"/></td>
										<td class="subject tal">
											<p class="ellps">${transfers2.transferDescription}</p>
										</td>
										<td class="money tar" id="money">${transfers2.money}</td>
										<c:if test="${transfers2.state==0}"><td class="status">等待审核</td></c:if>
										<c:if test="${transfers2.state==1}"><td class="status">通过审核</td></c:if>
										<c:if test="${transfers2.state==2}"><td class="status">交易成功</td></c:if>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					
				</div>
			</div>
		</div>
		<div id="bottom" class="bottom"></div>
	</body>

</html>