<%@ page language="java" pageEncoding="utf-8"  import="java.util.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html  >

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>充值 - 蓝桥支付</title>
        <meta name="keywords" content="" />
        <meta name="description" content="" />
        <link rel="stylesheet" type="text/css" href="${applicationScope.url}/resources/lanqiaoPayModel/css/global.css">
        <link rel="stylesheet" type="text/css" href="${applicationScope.url}/resources/lanqiaoPayModel/css/dialog.css">
        <link rel="stylesheet" type="text/css" href="${applicationScope.url}/resources/lanqiaoPayModel/css/form.css">
        <link rel="stylesheet" type="text/css" href="${applicationScope.url}/resources/lanqiaoPayModel/css/biz/trade.css">
        <script type="text/javascript" src="${applicationScope.url}/resources/lanqiaoPayModel/js/jquery.js"></script>
        <script type="text/javascript" src="${applicationScope.url}/resources/lanqiaoPayModel/js/global.js"></script>
        <script type="text/javascript" src="${applicationScope.url}/resources/lanqiaoPayModel/js/util.js"></script>
        <script type="text/javascript" src="${applicationScope.url}/resources/lanqiaoPayModel/js/animation.js"></script>
        <script type="text/javascript" src="${applicationScope.url}/resources/lanqiaoPayModel/js/dialog.js"></script>
        <script type="text/javascript" src="<%=basePath %>resources/lanqiaoPayModel/js/md5.js"></script>
        <script type="text/javascript">
        	//防止页面重复提交的变量
        	 var checkSubmitFlg = false;
        	 		
            $(function() {
            	//声明变量存放用户支付的银行卡的id
            	var cardNumber;
            	//获取当前使用的银行卡的div
				var div1 = document.getElementById("usedCard");
				cardNumber = (div1.getElementsByTagName("input")[0]).value;
                $("#selectBankBtn").click(function(){
					var url="<%=basePath%>enterpriseUserAllBankcard.jsp"
					//用户选择银行卡
                    Dialogx.show({
                        _url:url,
                        _title: '请选择银行卡',
                        _callback: function(dialog) {
                           $(".bank-card-x").click(function(){
								//获取用户选取的银行卡的卡号
								cardNumber = (this.getElementsByTagName("input")[0]).value;
								div1.innerHTML=this.innerHTML;                         		
                                dialog.closeDialog();
							});	 
                        }
                    });
                });
                	
                	document.getElementById("submitBtn").onclick=function(){
                		//获取用户输入的充值金额
                		var addBalance = $("#addBalance").val();
	                	//获取用户输入的支付密码
	                	var pwd = $("#payPwd").val();
                		//判断充值金额是否为空
                		if(addBalance==""){
	                		$("#hint").text("请输入充值金额！");
	                		$("#hint").css("color","red");
	                		$("#addBalance").focus();
	                		return false;
	                	//判断支付密码是否为空
	                	}else if(pwd==""){
	                		$("#hint").text("");
	                		$("#hint2").text("请输入支付密码！");
	                		$("#hint2").css("color","red");
	                		$("#payPwd").focus();
	                		return false;
	                	}else{
	                		$("#hint2").text("");
	                		//md5加密	                	
		                	var pwd2=md5(pwd);
		                	//发ajax到后台，判断银行卡余额是否够，判断支付密码是否正确，然后充值
		                	var url ="<%=request.getContextPath() %>/enterpriseUser/addBalance";
		                	var args ={"time":new Date(),"cardNumber":cardNumber,"addBalance":addBalance,"payPwd":pwd2};
		                	$.post(url,args,function(data){
		                		if(data=="银行卡余额不足！"){
		                			$("#hint").text(data);
		                			$("#hint").css("color","red");
			                		$("#addBalance").focus();
		                			return false;
		                		}
		                		if(data=="支付密码错误！"){
		                			$("#hint").text("");
		                			$("#hint2").text(data);
		                			$("#hint2").css("color","red");
			                		$("#payPwd").focus();
			                		return false;
		                		}
		                		//判断是否是重复提交
							　　 	if (checkSubmitFlg == true) {
								 	return false;
						　　 		}
						　　 		checkSubmitFlg = true;
		                		if(data=="操作成功！"||data=="操作失败！"){
		                			window.location.href='<%=request.getContextPath() %>/enterpriseUser/addBalanceSuccess';
		                		}
		                	});
	                	}
	                }	
            });
        </script>
        
        
    </head>

    <body>
        <div id="top"></div>
        <div id="header">
            <div class="layout">
                <div id="logo">
                    <a href="${applicationScope.url}/enterpriseUser/login">
                        <img src="${applicationScope.url}/resources/lanqiaoPayModel/images/logo.png" alt="蓝桥" />
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
        <div id="page">
            <div class="main layout">
                <div class="trade-top-acc">
                    <dl class="balance">
                        <dt>账户余额:</dt>
                        <dd class="money">${sessionScope.enterpriseUser.balance}</dd>
                        <dd class="unit">元</dd>
                    </dl>
                </div>
                <div class="trade-main">
                    <div class="trade-tab">
                        <div class="trade-tab-hd">
                            <ul class="trade-tab-items">
                                <li class="current">
                                    <span class="tab">银行卡充值</span>
                                </li>
                            </ul>
                            <p class="trade-tab-tips">绑定快捷支付, 充值更方便</p>
                        </div>
                        <div class="trade-tab-bd">
                            <form class="form-bd form-bd-trade clearfix" action="" id="companyForm">
                                <div class="form-item form-item-bank">
                                    <h4 class="form-label">充值方式:</h4>
                                    <div class="form-entity">
                                        <div class="form-field">
                                            <div class="form-twin-group">
                                                <div class="bank-card-x" id="usedCard">
                                                	<c:if test="${!empty sessionScope.bankCards }">
	                                                    <c:forEach items="${sessionScope.bankCards }" var="bankCard">
															<c:if test="${bankCard.isDefault eq '1'}">
																<c:choose>
																	<c:when test="${bankCard.bankId.name eq '中国农业银行'}">
																				<div class="bank-ico bank-abc"></div>
																				<p class="card-prop card-no">**** **** ****${bankCard.jspCardNumber }</p>
																				<input type="hidden" value="${bankCard.cardNumber }">
																	</c:when>
																	<c:when test="${bankCard.bankId.name eq '招商银行'}">
																				<div class="bank-ico bank-cmb"></div>
																				<p class="card-prop card-no">**** **** ****${bankCard.jspCardNumber }</p>
																				<input type="hidden" value="${bankCard.cardNumber }">
																	</c:when>
																	<c:when test="${bankCard.bankId.name eq '中国建设银行'}">
																				<div class="bank-ico bank-ccb"></div>
																				<p class="card-prop card-no">**** **** ****${bankCard.jspCardNumber }</p>
																				<input type="hidden" value="${bankCard.cardNumber }">
																			
																	</c:when>
																	<c:when test="${bankCard.bankId.name eq '华夏银行'}">
																				<div class="bank-ico bank-hxb"></div>
																				<p class="card-prop card-no">**** **** ****${bankCard.jspCardNumber }</p>
																				<input type="hidden" value="${bankCard.cardNumber }">
																	</c:when>
																	<c:when test="${bankCard.bankId.name eq '平安银行'}">
																				<div class="bank-ico bank-pingan"></div>
																				<p class="card-prop card-no">**** **** ****${bankCard.jspCardNumber }</p>
																				<input type="hidden" value="${bankCard.cardNumber }">
																	</c:when>
																	<c:when test="${bankCard.bankId.name eq '中国民生银行'}">
																				<div class="bank-ico bank-cmbc"></div>
																				<p class="card-prop card-no">**** **** ****${bankCard.jspCardNumber }</p>
																				<input type="hidden" value="${bankCard.cardNumber }">
																	</c:when>
																	<c:when test="${bankCard.bankId.name eq '中国工商银行'}">
																				<div class="bank-ico bank-icbc"></div>
																				<p class="card-prop card-no">**** **** ****${bankCard.jspCardNumber }</p>
																				<input type="hidden" value="${bankCard.cardNumber }">
																	</c:when>
																	<c:otherwise>
																				<div class="bank-ico bank-icbc"></div>
																				<p class="card-prop card-no">**** **** ****${bankCard.jspCardNumber }</p>
																				<input type="hidden" value="${bankCard.cardNumber }">
																	</c:otherwise>
																</c:choose>			  			
															</c:if>
	                                                    </c:forEach>
                                                	</c:if>
                                                </div>
                                            </div>
                                            <div class="form-twin-group">
                                                <a id="selectBankBtn" class="rapid-link" href="javascript:;">使用其他银行卡</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-item form-item-money">
                                    <h4 class="form-label">充值金额:</h4>
                                    <div class="form-entity">
                                        <div class="form-field">
                                            <div class="form-twin-group">
                                                <input id="addBalance" class="ipt ipt-money" type="text" name="addBalance" value="" placeholder="请输入充值金额" />
                                            </div>
                                            <div class="form-twin-group">
                                                <span class="unit">元</span>&nbsp;&nbsp;<label id="hint"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-item">
                                    <h4 class="form-label">支付密码:</h4>
                                    <div class="form-entity">
                                        <div class="form-field">
                                            <input id="payPwd" class="ipt ipt-pwd" type="password" name="password" value="" placeholder="请输入支付密码" />
                                            <label id="hint2"></label>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-action clearfix">
                                    <a id="submitBtn" href="javascript:;" class="glb-btn submit-btn">
                                    	<span>立即充值</span>
                                    </a> 
                                </div>
       	                  </form>
                        </div>
                    </div>
                </div>
                <div class="trade-help">
                    <div class="trade-help-hd">
                        <h3>充值遇到问题?</h3>
                    </div>
                    <div class="trade-help-bd">
                        <dl>
                            <dt>1. 如果银行卡信息没填对，资金如何退还？</dt>
                            <dd>
                                答：款项会全额退还。 手续费：全部退回蓝桥支付余额。 本金：本金根据支付渠道退回蓝桥支付余额或者银行卡里，请关注。 1）银行退票一般会在7个工作日内退回（处理速度由银行决定）。 2）2小时到账的如果超过时间未到账，请用户在2个工作日内（不包括申请当天）关注。
                            </dd>
                        </dl>
                        <dl>
                            <dt>2. 银行已扣钱，为什么蓝桥支付账户没收到？</dt>
                            <dd>
                                答： 请您别担心，会有以下几种情况：
                                <ul>
                                    <li>
                                        1、可能由于网络繁忙原因到账延迟，请不用担心，系统会重新核实，一般在第二个工作日晚上18:00自动到账，如果交易已关闭或其他原因导致金额无法正常付款到交易中，金额会退回蓝桥支付账户余额或银行卡中，请您同时关注。
                                    </li>
                                    <li>2、如果是网银扣款，请您查询银行扣款明细，扣款商户如果显示不是蓝桥支付公司，请您联系银行客服咨询款项的去向。</li>
                                </ul>
                                <p>温馨提示：工作日包括周一至周五，不包括双休日、国家的法定节假日。</p>
                            </dd>
                        </dl>
                        <dl>
                            <dt>3. 如何取出充值的金额？</dt>
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
            <div id="bottom" class="bottom">
            </div>
        </div>
    </body>

</html>