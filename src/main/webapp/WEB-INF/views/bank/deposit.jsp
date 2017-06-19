<%@page import="org.lanqiao.pay.base.entity.BankCard"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'deposit.jsp' starting page</title>
    
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
        <script type="text/javascript" src="${applicationScope.url}/resources/lanqiaoPayModel/js/md5.js"></script>
        <script type="text/javascript">
        var b;
            $(function() {   
            var a;
            var chongfu=0;        
             var liy;  
              var div1=document.getElementById("yhk");
              liy=(div1.getElementsByTagName("p")[0]).textContent; 
              var url="<%=basePath%>dialog_bankcard.jsp"
                  $("#selectBankBtn").click(function() {                
                     Dialogx.show({
                        _url: url,
                        _title: '请选择银行卡',
                        _callback: function(dialog) {
                            $(".bank-card-x").click(function() {                                                          
                                       liy=(this.getElementsByTagName("p")[0]).textContent;
                                       div1.innerHTML=this.innerHTML;                                                                                                                                 
                                dialog.closeDialog(); 
                            });
                        }
                    });                   
                    });                           
   //判断输入金额是否为数字        
        var oInp = document.getElementById('moneyi');
        oInp.onblur=function(){
        if(isNaN(Number(oInp.value))){  //当输入不是数字的时候，Number后返回的值是NaN;然后用isNaN判断。
            $("#moneyl").text("      请输入正确的金额！");
            $("#moneyl").css("color","red");
            return false;
        }else{       
            $("#moneyl").text("");
            return true;
        }  
    } 
   
     //判断输入金额和银行卡余额的大小      
     $("#moneyi").blur(function(){
    var mony=$("#moneyi").val();
    var liyy=liy;
    
    var url="userController/deposit1";
      var args={
              "liy" : liyy,
              "moneyn" :mony,
              "time" : new Date()
  }
        $.post(url,args,function(data){
	 	if(data=="此卡,余额不足！"){
	 	b=1;
	 	}else{ 	
	 	b=0;
	 	}   	     
		 });  
   }); 
       
       //判断密码是否一致
       $("#zfmm").blur(function(){
       var pwd1=$("#zfmm").val();
       var str1=md5(pwd1);
          var url="userController/deposit2";
          var args={
              "str1" : str1,           
              "time" : new Date()
                 };
        $.post(url,args,function(data){
	    if(data=="密码错误，请重新输入!"){
	    a=1;	           
	    }else{	   
	    a=0;
	    }  
      });  
       });
       
          
      //判断输入的密码是否与数据库中一致，并在提交前判断相应属性是否合乎要求  
      $("#biaodani").click(function(){
      var moneyii=$("#moneyi").val();
      var dmoney=$("#dmoney").val();
      var balance1=$("#balance1").val();
      var yhkh1=$("#yhkh1").val();
      var oInp = document.getElementById('moneyi');
      var ym=$("#ym").val();
      var yue1=$("#yue").val();
      var mony=$("#moneyi").val();
      var liyy=liy;
       
       if(chongfu==1){
       return false;
       }else if(liy.length==0){
       $("#yue").text("亲，你还没有有选择银行卡哦！");
       $("#yue").css("color","red");
       return false;
       }else if(b==1){
       $("#yue").text("此卡余额不足！");
	   $("#yue").css("color","red");	   
       return false;
       }else if(b==0){
       $("#yue").text("");
	   $("#yue").css("color","green");	   
       } else if(isNaN(Number(oInp.value))){
        $("#moneyl").text("      请输入正确的金额！");
       $("#moneyl").css("color","red");
       return false;
       }else if(a==1){   
        $("#mm").text("密码错误，请重新输入!");
        $("#mm").css("color","red");
        return false;
       }else if(a==0){     
        $("#mm").text("");
        $("#mm").css("color","green");
       }else if(liy.length!=19){
       $("#yue").text("非法银行卡！");
       $("#yue").css("color","red");
       return false;
       }else if(moneyii==null){
        $("#moneyl").text("请输入要充值的金额！");
        $("#moneyl").css("color","red");
        return false;
       }else{     
        chongfu=1;
        $("#companyForm").submit();
        return false;   	    
	    }      	    
    });  
          });
          
        <%--  function changebank(){
              var liy;  
              var div1=document.getElementById("yhk");
              liy=(div1.getElementsByTagName("p")[0]).textContent; 
              var url="<%=basePath%>dialog_bankcard.jsp"
                   $("#selectBankBtn").click(function() {           
                    Dialogx.show({
                        _url: url,
                        _title: '此卡余额不足，请选择其他银行卡',
                        _callback: function(dialog) {
                            $(".bank-card-x").click(function() {                                                                            
                                       liy=(this.getElementsByTagName("p")[0]).textContent;
                                       div1.innerHTML=this.innerHTML;                                                                                                                                    
                                       dialog.closeDialog(); 
                            });
                        }
                     });               
                     });            
          }
          
      function panduan(){
    var mony=$("#moneyi").val();
    var liyy=liy;
    
    var url="userController/deposit1";
      var args={
              "liy" : liyy,
              "moneyn" :mony,
              "time" : new Date()
  }
        $.post(url,args,function(data){
	 	if(data=="此卡,余额不足！"){
	 	b=1;
	 	}else{ 	
	 	b=0;
	 	}  
	 	     
		 }); 
           } 
     --%>
        </script>

    </head>

    <body>
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
                            <a href="${applicationScope.url}/userController/to_uc">
                                <span></span>
                            </a>
                        </li>
                        <li class="glb-nav-setting">
                            <a href="${applicationScope.url}/secretController/to_safe">
                                <span></span>
                            </a>
                        </li>
                        <li class="glb-nav-trade">
                            <a href="${applicationScope.url}/userController/userTradeHistory?id=${sessionScope.user.id}">
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
                        <dd class="money"><%=request.getSession().getAttribute("balance") %></dd>
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
                            <form class="form-bd form-bd-trade clearfix" action="userController/deposit" id="companyForm" method="post">
                                <div class="form-item form-item-bank">
                                    <h4 class="form-label">充值方式:</h4>
                                    <div class="form-entity">
                                        <div class="form-field">
                                            <div class="form-twin-group">
                                            <%
                                            BankCard isdefaul= (BankCard)request.getSession().getAttribute("isdefaul");
                                            String name=isdefaul.getBankId().getName();
                                            String str=isdefaul.getCardNumber().substring(15, 19);
                                            if(name.equals("中国工商银行")){                                  
     %>        
		      <div class="bank-card-x" id="yhk">
			     <div class="bank-ico bank-icbc"></div>
			     <p class="card-prop card-no" id="yhkho" style="display: none"><%=isdefaul.getCardNumber()%></p>
			     <p class="card-prop card-no">****** <%=str%></p>
		     </div>	      
	<%
	}else if(name.equals("华夏银行")){
	%>	    
		      <div class="bank-card-x" id="yhk">
			     <div class="bank-ico bank-hxb"></div>
			     <p class="card-prop card-no" id="yhkho" style="display: none"><%=isdefaul.getCardNumber()%></p>
			     <p class="card-prop card-no">****** <%=str%></p>
		     </div>	     
	<%
	}else if(name.equals("中国农业银行")){
	%>	    
		      <div class="bank-card-x" id="yhk">
			     <div class="bank-ico bank-abc"></div>
			     <p class="card-prop card-no" id="yhkho" style="display: none"><%=isdefaul.getCardNumber()%></p>
			     <p class="card-prop card-no">****** <%=str%></p>
		     </div>	     
	<%
	}else if(name.equals("平安银行")){
	%>	     
		      <div class="bank-card-x" id="yhk">
			     <div class="bank-ico bank-pingan"></div>
			     <p class="card-prop card-no" id="yhkho" style="display: none"><%=isdefaul.getCardNumber()%></p>
			     <p class="card-prop card-no">****** <%=str%></p>
		     </div>	     
	<%
	}else if(name.equals("招商银行")){
	%>	   
		      <div class="bank-card-x" id="yhk">
			     <div class="bank-ico bank-cmb"></div>
			     <p class="card-prop card-no" id="yhkho" style="display: none"><%=isdefaul.getCardNumber()%></p>
			     <p class="card-prop card-no">****** <%=str%></p>
		     </div>	      
	<%
	}else if(name.equals("中国银联")){
	%>	   
		      <div class="bank-card-x" id="yhk">
			     <div class="bank-ico bank-unionpay"></div>
			     <p class="card-prop card-no" id="yhkho" style="display: none"><%=isdefaul.getCardNumber()%></p>
			     <p class="card-prop card-no">****** <%=str%></p>
		     </div>	     
	<%
	}else if(name.equals("中国民生银行")){
	%>	   
		      <div class="bank-card-x" id="yhk">
			     <div class="bank-ico bank-cmbc"></div>
			     <p class="card-prop card-no" id="yhkho" style="display: none"><%=isdefaul.getCardNumber()%></p>
			     <p class="card-prop card-no">****** <%=str%></p>
		     </div>	 
	<%
	}else if(name.equals("中银富登村镇银行")){
	%>	     
		      <div class="bank-card-x" id="yhk">
			     <div class="bank-ico bank-boc"></div>
			     <p class="card-prop card-no" id="yhkho" style="display: none"><%=isdefaul.getCardNumber()%></p>
			     <p class="card-prop card-no">****** <%=str%></p>
		     </div>
	<%
	}else if(name.equals("光大银行")){
	%>
		      <div class="bank-card-x" id="yhk">
			     <div class="bank-ico bank-ceb"></div>
			     <p class="card-prop card-no" id="yhkho" style="display: none"><%=isdefaul.getCardNumber()%></p>
			     <p class="card-prop card-no">****** <%=str%></p>
		     </div>
	<%
	}else if(name.equals("中国邮政储蓄银行")){
	%>	     
		      <div class="bank-card-x" id="yhk">
			     <div class="bank-ico bank-psbc"></div>
			     <p class="card-prop card-no" id="yhkho" style="display: none"><%=isdefaul.getCardNumber()%></p>
			     <p class="card-prop card-no">****** <%=str%></p>
		     </div>	    
	<%
	}else if(name.equals("哈尔滨银行")){
	%>	    
		      <div class="bank-card-x" id="yhk">
			     <div class="bank-ico bank-hrbcb"></div>
			     <p class="card-prop card-no" id="yhkho" style="display: none"><%=isdefaul.getCardNumber()%></p>
			     <p class="card-prop card-no">****** <%=str%></p>
		     </div>	    
	<%
	}else if(name.equals("中国广发银行")){
	%>	     
		      <div class="bank-card-x" id="yhk">
			     <div class="bank-ico bank-gdb"></div>
			     <p class="card-prop card-no" id="yhkho" style="display: none"><%=isdefaul.getCardNumber()%></p>
			     <p class="card-prop card-no">****** <%=str%></p>
		     </div>	     
	<%
	}
	else if(name.equals("中国建设银行")){
	%>     
		      <div class="bank-card-x" id="yhk">
			     <div class="bank-ico bank-ccb"></div>
			     <p class="card-prop card-no" id="yhkho" style="display: none"><%=isdefaul.getCardNumber()%></p>
			     <p class="card-prop card-no">****** <%=str%></p>
		     </div>	    
	<%
	}else{
	%>     
		      <div class="bank-card-x" id="yhk">
			     <!-- <div class="bank-ico bank-ccb"></div> -->
			     <p class="card-prop card-no" id="yhkho" style="display: none"></p>
			     <p class="card-prop card-no"></p>
		     </div>	    
	<%
	}
                                             %>
                                                <!-- <div class="bank-card-x" id="yhk">
                                                    <div class="bank-ico bank-ccb"></div>
                                                    <p class="card-prop card-no"></p>                                                                                             
                                                </div>  -->
                                            </div>
                                            <div class="form-twin-group">
                                                <a id="selectBankBtn" class="rapid-link" href="javascript:;">请选择银行卡</a>
                                            </div><label id="yue"></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-item form-item-money">
                                    <h4 class="form-label">充值金额:</h4>
                                    <div class="form-entity">
                                        <div class="form-field">
                                            <div class="form-twin-group">
                                                <input class="ipt ipt-money" type="text" name="money" value="" placeholder="请输入充值金额" id="moneyi"/>
                                            </div>
                                            <div class="form-twin-group">
                                                <span class="unit">元</span><label id="moneyl"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-item">
                                    <h4 class="form-label">支付密码:</h4>
                                    <div class="form-entity">
                                        <div class="form-field">
                                            <input class="ipt ipt-pwd" type="password" id="zfmm" name="userName1" value="" placeholder="请输入支付密码"/><label id="mm"></label>  
                                            <input type="hidden" name="yuanmima" id="ym" value="<%=request.getSession().getAttribute("zf")%>"> 
                                            <input type="hidden" name="" id="dmoney" value="<%=request.getSession().getAttribute("dmoney")%>"> 
                                            <input type="hidden" name="" id="balance1" value="<%=request.getSession().getAttribute("balance1")%>">
                                            <input type="hidden" name="" id="yhkh1" value="<%=request.getSession().getAttribute("yhkh")%>">                                      
                                         </div>
                                    </div>
                                </div>   
                              
                                <div class="form-action clearfix">
                                    <!-- <a id="biaodani" href="userController/deposit" class="glb-btn submit-btn">
                                        <span>立即充值</span>
                                    </a> -->
                                    
                                 <input type="submit" id="biaodani" value="立即充值" class="glb-btn submit-btn"> 
                                   
                                  <!--  <a href="javascript:;" id="biaodani" class="glb-btn submit-btn"><span>立即充值</span></a> -->
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