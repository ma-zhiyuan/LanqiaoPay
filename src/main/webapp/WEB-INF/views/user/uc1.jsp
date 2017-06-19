<%@page import="org.lanqiao.pay.base.entity.Transfer"%>
<%@page import="org.lanqiao.pay.base.entity.Recharge_withdrawal"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Date date = new Date();
String token = ""+date.getTime();
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'uc.jsp' starting page</title>
    
	
		<title>蓝桥支付-首页</title>
		<meta name="keywords" content="" />
		<meta name="description" content="" />
	 	<link rel="stylesheet" type="text/css" href="${applicationScope.url}/resources/lanqiaoPayModel/css/global.css">
		<link rel="stylesheet" type="text/css" href="${applicationScope.url}/resources/lanqiaoPayModel/css/form.css">
		<link rel="stylesheet" type="text/css" href="${applicationScope.url}/resources/lanqiaoPayModel/css/dialog.css">
		<link rel="stylesheet" type="text/css" href="${applicationScope.url}/resources/lanqiaoPayModel/css/biz/uc.css">
		<link rel="stylesheet" type="text/css" href="${applicationScope.url}/resources/lanqiaoPayModel/css/kkpager_orange.css">
		<script type="text/javascript" src="${applicationScope.url}/resources/lanqiaoPayModel/js/jquery.js"></script>
		<script type="text/javascript" src="${applicationScope.url}/resources/lanqiaoPayModel/js/global.js"></script>
		<script type="text/javascript" src="${applicationScope.url}/resources/lanqiaoPayModel/js/util.js"></script>
		<script type="text/javascript" src="${applicationScope.url}/resources/lanqiaoPayModel/js/animation.js"></script>
		<script type="text/javascript" src="${applicationScope.url}/resources/lanqiaoPayModel/js/dialog.js"></script>
		<script type="text/javascript" src="${applicationScope.url}/resources/lanqiaoPayModel/js/jquery-1.10.2.min.js"></script>
		<script type="text/javascript" src="${applicationScope.url}/resources/lanqiaoPayModel/js/kkpager.min.js"></script> 
		<script type="text/javascript" src="${applicationScope.url}/resources/lanqiaoPayModel/js/limingpage.js"></script>
		<script type="text/javascript">
		 $(function(){
		 
		 $("#bankall").click(function() {
                    Dialogx.show({
                        _url: 'testbank.jsp',
                        _title: '我的银行卡',
                        _callback: function(dialog) {
                            $(".bank-card-x").click(function() {
                                dialog.closeDialog();
                            });
                        }
                    });

                });
                
                
                $("#kuaijieid").click(function() {
                    Dialogx.show({
                        _url: 'testkuaijie.jsp',
                        _title: '我的快捷支付银行卡',
                        _callback: function(dialog) {
                            $(".bank-card-x").click(function() {
                                dialog.closeDialog();
                            });
                        }
                    });

                });
		 
		
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
          
				// 充值
				$("#deposit").click(function() {
					window.open("userController/tiaoZhuan","_blank")
					
				});

				// 提现
				$("#withdraw").click(function() {

					window.open("BankController/to_withdraw","_blank")
				});

				// 转账
				$("#transfer").click(function() {

					window.open("BankCardController/toTransfer")
				});
				
			});
		</script>
	</head>
    <body onLoad="goPage(1,10);">	
		<div id="top"></div>
		<div id="header">
			<div class="layout">
				<div id="logo">
					<a href="uc.html">
						<img src="${applicationScope.url}/resources/lanqiaoPayModel/images/logo.png" alt="蓝桥" />
					</a>
				</div>
				<div id="nav" class="glb-nav">
					<ul class="clearfix">
						<li class="glb-nav-uc current">
							<a href="userController/to_uc">
								<span></span>
							</a>
						</li>
						<li class="glb-nav-setting">
							<a href="secretController/to_safe">
								<span></span>
							</a>
						</li>
						<li class="glb-nav-trade">
							<a href="trade_list.html">
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
					<img src="${applicationScope.url}<%=request.getSession().getAttribute("touxiang") %>" alt="头像" /> 
				</span>
				</div>
				<div class="uc-banner-main">
					<ul class="uc-banner-top clearfix">
						<li class="uname" id="timei">
							<div class="lk">欢迎你:${sessionScope.user.name }</div>
						</li>

					</ul>
					<ul class="uc-banner-btm clearfix">
						<li class="first">账号: ${sessionScope.user.email } </li>
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
										<span class="money"><%=request.getSession().getAttribute("balance") %></span> 元
									</p>
									<a class="item-link" href="trade_list.html">查看明细</a>
								</div>
								<div class="uc-acc-action clearfix">
									<a id="deposit" href="javascript:;" class="uc-opt-btn uc-trade-btn"> 
									<!-- <a href="userController/tiaoZhuan" class="uc-opt-btn uc-trade-btn"> -->
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
							<%
							String zf=(String)request.getSession().getAttribute("zf");
							String call=(String)request.getSession().getAttribute("call");
							if(zf!=null&&call==null){
							%><p class="level-ico level-ico-2"></p>
							<%
							} else if(zf!=null&&call!=null){
							%><p class="level-ico level-ico-3"></p>
							<%
							}else if(zf==null&&call!=null){
							%><p class="level-ico level-ico-1"></p>
							<%
							}
							else{
							%><p class="level-ico level-ico-0"></p>
							<%
							}%>	
								<!-- <p class="level-ico level-ico-1"></p> -->
							</div>
						</div>
						<div class="side-bd">
							<ul class="uc-col-items">
								<li>
									<span class="iconfont icon-pwd-unsetted"></span>
									<%
									if(zf!=null){
									%>
									<span class="item-txt">已设置支付密码</span>
									<a class="item-act" href="security_choose_type.html">修改</a>
									<%
									}else{
									%>
									<span class="item-txt">未设置支付密码</span>
									<a class="item-act" href="security_choose_type.html">设置</a>
									<%
									}
									 %>	
								</li>
								<li>
									<span class="iconfont icon-mobile-setted"></span>
									<%
									if(call!=null){
									%>
									<span class="item-txt">已绑定手机</span>
									<a class="item-act" href="security_bound_phone.html">修改</a>
									<%
									}else{
									%>
									<span class="item-txt">未绑定手机</span>
									<a class="item-act" href="security_bound_phone.html">绑定</a>
									<%
									}
									 %>	
								</li>
								<li>
									<span class="iconfont icon-qa-setted"></span>
									<%
									if(zf!=null){
									%>
									<span class="item-txt">已设置密保问题</span>
									<a class="item-act" href="security_protect_problem.html">修改</a>
									<%
									}else{
									%>
									<span class="item-txt">未设置密保问题</span>
									<a class="item-act" href="security_protect_problem.html">设置</a>
									<%
									}
									 %>	
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
										<a class="count" href="javascript:;" id="bankall"><%=request.getSession().getAttribute("bankcarlengh") %></a>张
									</em>
								</li>
								<li>
									<span class="item-txt">
										快捷支付:
									</span>
									<span class="item-act">
										<a class="count" href="javascript:;" id="kuaijieid"><%=request.getSession().getAttribute("kuaijie") %></a>张
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
							<a href="userController/zuiXing">最新交易</a>
						</h3>
						<ul class="col-opts col-opts-side">
							<li>
								<a href="userController/suoyou">所有交易</a>
							</li>
							<li>
								<a href="userController/chongTi">充值提现记录</a>
							</li>
							<!-- <li>
								<a href="#">提现记录</a>
							</li> -->
							<li>
								<a href="userController/zhiFu">支付记录</a>
							</li>
						</ul>
					</div>
					<div class="uc-trade-bd clearfix">
						<table class="uc-trade-list"  id="idData">							
							<%
							List<Transfer> transfers=(List<Transfer>)request.getSession().getAttribute("transfers");							
							for(int i=0;i<transfers.size();i++){
							
							Transfer transfer=transfers.get(i);
							Date time1=transfer.getTime();
							int year1=time1.getYear()+1900;
							int month1=time1.getMonth()+1;
							int day1=time1.getDate();
							int hours1=time1.getHours();
							int minutes1=time1.getMinutes();
							int second1=time1.getSeconds();
							
							String describe1= transfer.getTransferDescription();
							
							Double money1=transfer.getMoney();
							
							int state1= transfer.getState();
							String states1="";
							if(state1==0){
							  states1="等待审核！";
							}else{
							  states1="操作成功！";
							}
							%>
							<tr>
							
									<td class="status"><%=year1 %>-<%=month1 %>-<%=day1 %></td>
								   <td class="time"><%=hours1 %>:<%=minutes1 %>:<%=second1 %></td> 
									<td class="subject tal">
										<p class="ellps"><%=describe1 %></p>
									</td>
									<td class="money tar"><%=money1 %></td>
									<td class="status"><%=states1 %></td>
								</tr>
							<%
							}
							List<Recharge_withdrawal> recharge_withdrawals=(List<Recharge_withdrawal>)request.getSession().getAttribute("recharge_withdrawals");
							for(int i=0;i<recharge_withdrawals.size();i++){	
							
							Recharge_withdrawal recharge_withdrawal=recharge_withdrawals.get(i);
							
							Date time2=recharge_withdrawal.getTime();							
							int year2=time2.getYear()+1900;
							int month2=time2.getMonth()+1;
							int day2=time2.getDate();
							int hours2=time2.getHours();
							int minutes2=time2.getMinutes();
							int seconds2=time2.getSeconds();
							
							String describe2= recharge_withdrawal.getTransaction_describe();
							
							Double money2=recharge_withdrawal.getMoney();
							
							int state2=recharge_withdrawal.getState();
							String states2="";
							if(state2==0){
							  states2="等待审核！";
							}else if(state2==1){
							  states2="已审核！";
							}else{
							  states2="操作成功！";
							}
							%>
							<tr>
									<td class="status"><%=year2 %>-<%=month2 %>-<%=day2 %></td>
								    <td class="time"><%=hours2 %>:<%=minutes2 %>:<%=seconds2 %></td> 
									<td class="subject tal">
										<p class="ellps"><%=describe2 %></p>
									</td>
									<td class="money tar"><%=money2 %></td>
									<td class="status"><%=states2 %></td>
								</tr>
							<%
							}
							 %>
						<!--  <table align="right">
                                <tr><td><div id="barcon" name="barcon"></div></td></tr>
                         </table> -->
                         
    <table align="right">                      
     <div id="barcon" class="barcon" align="right">  
      <div id="barcon1" class="barcon1" align="right"></div>  
        <div id="barcon2" class="barcon2" align="right">  
            <ul>  
                <li><a href="javascript:goHistory(-1)" id="firstPage">首页</a>  <a href="javascript:goHistory()" id="prePage">上一页</a>  <a href="javascript:goNext()" id="nextPage">下一页</a> <a href="javascript:gogoNext(1)" id="lastPage">尾页</a>  
                    <select id="jumpWhere">  </select> <a href="javascript:goTo()" id="jumpPage" onclick="jumpPage()">跳转</a> </li>  
                <!-- <li><select id="jumpWhere">  
                </select></li>  
                <li><a href="javascript:goTo()" id="jumpPage" onclick="jumpPage()">跳转</a></li>   -->
            </ul>  
        </div>  
 </div>   
</table>  						 	
							 					
						</table>
						    
					</div>
				</div>
			</div>
		</div>
		<div id="bottom" class="bottom"></div>
	</body>
</html>
