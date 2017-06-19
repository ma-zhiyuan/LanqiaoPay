<%@page import="org.lanqiao.pay.base.entity.Bank"%>
<%@page import="org.lanqiao.pay.base.dao.BankDao" %>
<%@page import="org.lanqiao.pay.base.dao.BankCardDao" %>
<%@page session="true" import="org.lanqiao.pay.serviceImpl.BankCardServiceImpl" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@page import="org.lanqiao.pay.base.entity.BankCard" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'dialog_bankcard.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${applicationScope.url}/resources/lanqiaoPayModel/js/jquery.js"></script>
	<script type="text/javascript">
	/* $(function(){
	    window.onload=function(){
	      var url="userController/deposit2";
	      var args={
	          "time":new Date(),
	      };
	      $.post(url,args,function(data){    
		 });
	   } 
	   }); */
	</script>
  </head>
  <body>
      <ul class="bank-items bankcard-items" id="yh">  
      <%  
     List<BankCard> bankcards=(List<BankCard>)request.getSession().getAttribute("bankCards");
     for(int i=0;i<bankcards.size();i++){
     BankCard bankcard=bankcards.get(i);
     String str= bankcard.getCardNumber().substring(15, 19);
     String name= bankcard.getBankId().getName();
     if(name.equals("中国工商银行")){
     %> 
          <li>
		      <div class="bank-card-x" id="yhk2">
			     <div class="bank-ico bank-icbc"></div>
			     <p class="card-prop card-no" id="yhkho" style="display: none"><%=bankcard.getCardNumber()%></p>
			     <p class="card-prop card-no">*** *** <%=str%></p>
		     </div>
	      </li>     
	<%
	}else if(name.equals("华夏银行")){
	%>
	      <li>
		      <div class="bank-card-x" id="yhk2">
			     <div class="bank-ico bank-hxb"></div>
			     <p class="card-prop card-no" id="yhkho" style="display: none"><%=bankcard.getCardNumber()%></p>
			     <p class="card-prop card-no">*** *** <%=str%></p>
		     </div>
	      </li>
	<%
	}else if(name.equals("中国农业银行")){
	%>
	      <li>
		      <div class="bank-card-x" id="yhk2">
			     <div class="bank-ico bank-abc"></div>
			     <p class="card-prop card-no" id="yhkho" style="display: none"><%=bankcard.getCardNumber()%></p>
			     <p class="card-prop card-no">****** <%=str%></p>
		     </div>
	      </li>
	<%
	}else if(name.equals("平安银行")){
	%>
	      <li>
		      <div class="bank-card-x" id="yhk2">
			     <div class="bank-ico bank-pingan"></div>
			     <p class="card-prop card-no" id="yhkho" style="display: none"><%=bankcard.getCardNumber()%></p>
			     <p class="card-prop card-no">****** <%=str%></p>
		     </div>
	      </li>
	<%
	}else if(name.equals("招商银行")){
	%>
	      <li>
		      <div class="bank-card-x" id="yhk2">
			     <div class="bank-ico bank-cmb"></div>
			     <p class="card-prop card-no" id="yhkho" style="display: none"><%=bankcard.getCardNumber()%></p>
			     <p class="card-prop card-no">****** <%=str%></p>
		     </div>
	      </li>
	<%
	}else if(name.equals("中国银联")){
	%>
	      <li>
		      <div class="bank-card-x" id="yhk2">
			     <div class="bank-ico bank-unionpay"></div>
			     <p class="card-prop card-no" id="yhkho" style="display: none"><%=bankcard.getCardNumber()%></p>
			     <p class="card-prop card-no">****** <%=str%></p>
		     </div>
	      </li>
	<%
	}else if(name.equals("中国民生银行")){
	%>
	      <li>
		      <div class="bank-card-x" id="yhk2">
			     <div class="bank-ico bank-cmbc"></div>
			     <p class="card-prop card-no" id="yhkho" style="display: none"><%=bankcard.getCardNumber()%></p>
			     <p class="card-prop card-no">****** <%=str%></p>
		     </div>
	      </li>
	<%
	}else if(name.equals("中银富登村镇银行")){
	%>
	      <li>
		      <div class="bank-card-x" id="yhk2">
			     <div class="bank-ico bank-boc"></div>
			     <p class="card-prop card-no" id="yhkho" style="display: none"><%=bankcard.getCardNumber()%></p>
			     <p class="card-prop card-no">****** <%=str%></p>
		     </div>
	      </li>
	<%
	}else if(name.equals("光大银行")){
	%>
	      <li>
		      <div class="bank-card-x" id="yhk2">
			     <div class="bank-ico bank-ceb"></div>
			     <p class="card-prop card-no" id="yhkho" style="display: none"><%=bankcard.getCardNumber()%></p>
			     <p class="card-prop card-no">****** <%=str%></p>
		     </div>
	      </li>
	<%
	}else if(name.equals("中国邮政储蓄银行")){
	%>
	      <li>
		      <div class="bank-card-x" id="yhk2">
			     <div class="bank-ico bank-psbc"></div>
			     <p class="card-prop card-no" id="yhkho" style="display: none"><%=bankcard.getCardNumber()%></p>
			     <p class="card-prop card-no">****** <%=str%></p>
		     </div>
	      </li>
	<%
	}else if(name.equals("哈尔滨银行")){
	%>
	      <li>
		      <div class="bank-card-x" id="yhk2">
			     <div class="bank-ico bank-hrbcb"></div>
			     <p class="card-prop card-no" id="yhkho" style="display: none"><%=bankcard.getCardNumber()%></p>
			     <p class="card-prop card-no">****** <%=str%></p>
		     </div>
	      </li>
	<%
	}else if(name.equals("中国广发银行")){
	%>
	      <li>
		      <div class="bank-card-x" id="yhk2">
			     <div class="bank-ico bank-gdb"></div>
			     <p class="card-prop card-no" id="yhkho" style="display: none"><%=bankcard.getCardNumber()%></p>
			     <p class="card-prop card-no">****** <%=str%></p>
		     </div>
	      </li>
	<%
	}
	else{
	%>
	      <li>
		      <div class="bank-card-x" id="yhk2">
			     <div class="bank-ico bank-ccb"></div>
			     <p class="card-prop card-no" id="yhkho" style="display: none"><%=bankcard.getCardNumber()%></p>
			     <p class="card-prop card-no">****** <%=str%></p>
		     </div>
	      </li>
	<%
	}
     } 
    %>
	<li>
</ul>
  </body>
</html>

