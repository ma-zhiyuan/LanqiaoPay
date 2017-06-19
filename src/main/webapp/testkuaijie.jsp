<%@page import="org.lanqiao.pay.base.entity.Bank"%>
<%@page import="org.lanqiao.pay.base.entity.BankCard"%>
<%@page import="org.lanqiao.pay.base.dao.BankDao" %>
<%@page import="org.lanqiao.pay.base.dao.BankCardDao" %>
<%@page session="true" import="org.lanqiao.pay.serviceImpl.BankCardServiceImpl" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'testbank.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
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
		
		</script>
	</head>

	<body>
		<!-- <div id="top"> </div>
		<div id="header">
			<div class="layout">			
				<div id="nav" class="glb-nav">					
				</div>
			</div>
		</div> -->
		<div class="main layout">
			<div class="col-menu">
				<ul class="menu-items">										
					<li class="last current">				
					</li>
				</ul>
			</div>
			<div class="col-main">
				<div class="main-hd">
					<h3 class="iconfont icon-menu-bm">我的快捷支付银行卡</h3>
				</div>
				<div class="main-bd">					
					<div class="content-bd">
						<ul class="card-items">
						
						<%  
     List<BankCard> bankcards=(List<BankCard>)request.getSession().getAttribute("kuaijiek");
     for(int i=0;i<bankcards.size();i++){
     BankCard bankcard=bankcards.get(i);
     String str= bankcard.getCardNumber().substring(15, 19);
     String str2=bankcard.getCardNumber().substring(0, 4);
     Date time1=bankcard.getApplicationTime();
     int year1=time1.getYear()+1900;
	 int month1=time1.getMonth()+1;
	 int day1=time1.getDate();
	 int hours1=time1.getHours();
	 int minutes1=time1.getMinutes();
	 int second1=time1.getSeconds();
     String name= bankcard.getBankId().getName();
     if(name.equals("中国工商银行")){
     %> 
                 <li>
								<div class="card-layout card-top">
									<div class="card-col-l">
										<div class="bank-ico bank-icbc"></div>
									</div>
									<p class="card-col-r card-no"><%=str2%>*****<%=str%></p>
								</div>
								<div class="card-layout card-mid">
									<p class="card-col-l card-time">申请日期: <%=year1 %>-<%=month1 %>-<%=day1 %> <%=hours1 %>:<%=minutes1 %></p>
									<div class="card-col-r">									
									</div>
								</div>
								<div class="card-layout card-btm clearfix">
									<div class="card-col-l card-pay-l">										
									</div>
									<div class="card-pay-l">										
									</div>
									<div class="card-col-r">										
									</div>
								</div>
							</li>   
	<%
	}else if(name.equals("华夏银行")){
	%>
	        
	                       <li>
								<div class="card-layout card-top">
									<div class="card-col-l">
										<div class="bank-ico bank-hxb"></div>
									</div>
									<p class="card-col-r card-no"><%=str2%>*****<%=str%></p>
								</div>
								<div class="card-layout card-mid">
									<p class="card-col-l card-time">申请日期: <%=year1 %>-<%=month1 %>-<%=day1 %> <%=hours1 %>:<%=minutes1 %></p>
									<div class="card-col-r">									
									</div>
								</div>
								<div class="card-layout card-btm clearfix">
									<div class="card-col-l card-pay-l">										
									</div>
									<div class="card-pay-l">										
									</div>
									<div class="card-col-r">										
									</div>
								</div>
							</li>
	<%
	}else if(name.equals("中国农业银行")){
	%>             
	                      
	                         <li>
								<div class="card-layout card-top">
									<div class="card-col-l">
										<div class="bank-ico bank-abc"></div>
									</div>
									<p class="card-col-r card-no"><%=str2%>*****<%=str%></p>
								</div>
								<div class="card-layout card-mid">
									<p class="card-col-l card-time">申请日期: <%=year1 %>-<%=month1 %>-<%=day1 %> <%=hours1 %>:<%=minutes1 %></p>
									<div class="card-col-r">									
									</div>
								</div>
								<div class="card-layout card-btm clearfix">
									<div class="card-col-l card-pay-l">										
									</div>
									<div class="card-pay-l">										
									</div>
									<div class="card-col-r">										
									</div>
								</div>
							</li>
	<%
	}else if(name.equals("平安银行")){
	%>
	          <li>
								<div class="card-layout card-top">
									<div class="card-col-l">
										<div class="bank-ico bank-pingan"></div>
									</div>
									<p class="card-col-r card-no"><%=str2%>*****<%=str%></p>
								</div>
								<div class="card-layout card-mid">
									<p class="card-col-l card-time">申请日期: <%=year1 %>-<%=month1 %>-<%=day1 %> <%=hours1 %>:<%=minutes1 %></p>
									<div class="card-col-r">									
									</div>
								</div>
								<div class="card-layout card-btm clearfix">
									<div class="card-col-l card-pay-l">										
									</div>
									<div class="card-pay-l">										
									</div>
									<div class="card-col-r">										
									</div>
								</div>
							</li>
	<%
	}else if(name.equals("招商银行")){
	%>
	      <li>
								<div class="card-layout card-top">
									<div class="card-col-l">
										<div class="bank-ico bank-cmb"></div>
									</div>
									<p class="card-col-r card-no"><%=str2%>*****<%=str%></p>
								</div>
								<div class="card-layout card-mid">
									<p class="card-col-l card-time">申请日期: <%=year1 %>-<%=month1 %>-<%=day1 %> <%=hours1 %>:<%=minutes1 %></p>
									<div class="card-col-r">									
									</div>
								</div>
								<div class="card-layout card-btm clearfix">
									<div class="card-col-l card-pay-l">										
									</div>
									<div class="card-pay-l">										
									</div>
									<div class="card-col-r">										
									</div>
								</div>
							</li>
	<%
	}else if(name.equals("中国银联")){
	%>
	
	                       <li>
								<div class="card-layout card-top">
									<div class="card-col-l">
										<div class="bank-ico bank-unionpay"></div>
									</div>
									<p class="card-col-r card-no"><%=str2%>*****<%=str%></p>
								</div>
								<div class="card-layout card-mid">
									<p class="card-col-l card-time">申请日期: <%=year1 %>-<%=month1 %>-<%=day1 %> <%=hours1 %>:<%=minutes1 %></p>
									<div class="card-col-r">									
									</div>
								</div>
								<div class="card-layout card-btm clearfix">
									<div class="card-col-l card-pay-l">										
									</div>
									<div class="card-pay-l">										
									</div>
									<div class="card-col-r">										
									</div>
								</div>
					      	</li>
	<%
	}else if(name.equals("中国民生银行")){
	%>
	     <li>
								<div class="card-layout card-top">
									<div class="card-col-l">
										<div class="bank-ico bank-cmbc"></div>
									</div>
									<p class="card-col-r card-no"><%=str2%>*****<%=str%></p>
								</div>
								<div class="card-layout card-mid">
									<p class="card-col-l card-time">申请日期: <%=year1 %>-<%=month1 %>-<%=day1 %> <%=hours1 %>:<%=minutes1 %></p>
									<div class="card-col-r">									
									</div>
								</div>
								<div class="card-layout card-btm clearfix">
									<div class="card-col-l card-pay-l">										
									</div>
									<div class="card-pay-l">										
									</div>
									<div class="card-col-r">										
									</div>
								</div>
							</li>
	<%
	}else if(name.equals("中银富登村镇银行")){
	%>
	      <li>
								<div class="card-layout card-top">
									<div class="card-col-l">
										<div class="bank-ico bank-boc"></div>
									</div>
									<p class="card-col-r card-no"><%=str2%>*****<%=str%></p>
								</div>
								<div class="card-layout card-mid">
									<p class="card-col-l card-time">申请日期: <%=year1 %>-<%=month1 %>-<%=day1 %> <%=hours1 %>:<%=minutes1 %></p>
									<div class="card-col-r">									
									</div>
								</div>
								<div class="card-layout card-btm clearfix">
									<div class="card-col-l card-pay-l">										
									</div>
									<div class="card-pay-l">										
									</div>
									<div class="card-col-r">										
									</div>
								</div>
							</li> 
	<%
	}else if(name.equals("光大银行")){
	%>
	      <li>
								<div class="card-layout card-top">
									<div class="card-col-l">
										<div class="bank-ico bank-ceb"></div>
									</div>
									<p class="card-col-r card-no"><%=str2%>*****<%=str%></p>
								</div>
								<div class="card-layout card-mid">
									<p class="card-col-l card-time">申请日期: <%=year1 %>-<%=month1 %>-<%=day1 %> <%=hours1 %>:<%=minutes1 %></p>
									<div class="card-col-r">									
									</div>
								</div>
								<div class="card-layout card-btm clearfix">
									<div class="card-col-l card-pay-l">										
									</div>
									<div class="card-pay-l">										
									</div>
									<div class="card-col-r">										
									</div>
								</div>
							</li>
	<%
	}else if(name.equals("中国邮政储蓄银行")){
	%>
	      <li>
								<div class="card-layout card-top">
									<div class="card-col-l">
										<div class="bank-ico bank-psbc"></div>
									</div>
									<p class="card-col-r card-no"><%=str2%>*****<%=str%></p>
								</div>
								<div class="card-layout card-mid">
									<p class="card-col-l card-time">申请日期: <%=year1 %>-<%=month1 %>-<%=day1 %> <%=hours1 %>:<%=minutes1 %></p>
									<div class="card-col-r">									
									</div>
								</div>
								<div class="card-layout card-btm clearfix">
									<div class="card-col-l card-pay-l">										
									</div>
									<div class="card-pay-l">										
									</div>
									<div class="card-col-r">										
									</div>
								</div>
							</li>
	<%
	}else if(name.equals("哈尔滨银行")){
	%>
	      <li>
								<div class="card-layout card-top">
									<div class="card-col-l">
										<div class="bank-ico bank-hrbcb"></div>
									</div>
									<p class="card-col-r card-no"><%=str2%>*****<%=str%></p>
								</div>
								<div class="card-layout card-mid">
									<p class="card-col-l card-time">申请日期: <%=year1 %>-<%=month1 %>-<%=day1 %> <%=hours1 %>:<%=minutes1 %></p>
									<div class="card-col-r">									
									</div>
								</div>
								<div class="card-layout card-btm clearfix">
									<div class="card-col-l card-pay-l">										
									</div>
									<div class="card-pay-l">										
									</div>
									<div class="card-col-r">										
									</div>
								</div>
							</li>
	<%
	}else if(name.equals("中国广发银行")){
	%>
	      <li>
								<div class="card-layout card-top">
									<div class="card-col-l">
										<div class="bank-ico bank-gdb"></div>
									</div>
									<p class="card-col-r card-no"><%=str2%>*****<%=str%></p>
								</div>
								<div class="card-layout card-mid">
									<p class="card-col-l card-time">申请日期: <%=year1 %>-<%=month1 %>-<%=day1 %> <%=hours1 %>:<%=minutes1 %></p>
									<div class="card-col-r">									
									</div>
								</div>
								<div class="card-layout card-btm clearfix">
									<div class="card-col-l card-pay-l">										
									</div>
									<div class="card-pay-l">										
									</div>
									<div class="card-col-r">										
									</div>
								</div>
							</li>
	<%
	}
	else{
	%>
	    <li>
								<div class="card-layout card-top">
									<div class="card-col-l">
										<div class="bank-ico bank-ccb"></div>
									</div>
									<p class="card-col-r card-no"><%=str2%>*****<%=str%></p>
								</div>
								<div class="card-layout card-mid">
									<p class="card-col-l card-time">申请日期: <%=year1 %>-<%=month1 %>-<%=day1 %> <%=hours1 %>:<%=minutes1 %></p>
									<div class="card-col-r">									
									</div>
								</div>
								<div class="card-layout card-btm clearfix">
									<div class="card-col-l card-pay-l">										
									</div>
									<div class="card-pay-l">										
									</div>
									<div class="card-col-r">										
									</div>
								</div>
							</li> 
	<%
	}
     }
    %>
										
						</ul>
					</div>
				</div>
			</div>
		</div>
		
	</body>

</html>

