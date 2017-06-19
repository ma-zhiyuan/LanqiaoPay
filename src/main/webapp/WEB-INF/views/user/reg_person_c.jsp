<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'reg_company_c.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    
        <link rel="stylesheet" type="text/css" href="${applicationScope.url}/resources/lanqiaoPayModel/css/global.css">
		<link rel="stylesheet" type="text/css" href="${applicationScope.url}/resources/lanqiaoPayModel/css/form.css">
		<link rel="stylesheet" type="text/css" href="${applicationScope.url}/resources/lanqiaoPayModel/css/biz/reg.css">
		<script type="text/javascript" src="${applicationScope.url}/resources/lanqiaoPayModel/js/jquery.js"></script>
		<script type="text/javascript" src="${applicationScope.url}/resources/lanqiaoPayModel/js/global.js"></script>
		<script type="text/javascript" src="${applicationScope.url}/resources/lanqiaoPayModel/js/animation.js"></script>
		<script type="text/javascript" src="${applicationScope.url}/resources/lanqiaoPayModel/js/util.js"></script>
		<script type="text/javascript" src="${applicationScope.url}/resources/lanqiaoPayModel/js/group.js"></script>
		<script type="text/javascript" src="${applicationScope.url}/resources/lanqiaoPayModel/js/md5.js"></script>
		<script type="text/javascript" src="${applicationScope.url}/resources/lanqiaoPayModel/limingrili/WdatePicker.js"></script>
    	
    	<script type="text/javascript">
		    $(function(){
		           var i=0;		         
		           var pwd1=$("#pwd1").val();
		           var pwd2=$("#pwd2").val();
		           var zpwd1=$("#zpwd1").val();
		           var zpwd2=$("#zpwd2").val();
		         
		    function isNumberOr_Letter(s) { //判断是否是数字或字母
			var regu = "^[0-9a-zA-Z\_]+$";
			var re = new RegExp(regu);
			if (re.test(s)) {
				return true;
			} else {
				return false;
			}
		}
		          //pwd1的验证		        
		         $("#pwd1").blur(function(){
		           var pwd1=$("#pwd1").val();
		           var pwd2=$("#pwd2").val();
		           if(!isNumberOr_Letter(pwd1)){
		              $("#pshint1").text("亲，密码只能是由数字或字母组成哦！");
		              $("#pshint1").css("color", "red");
		              return false;
		           }else if(pwd1.length==0){
		            $("#pshint1").text("亲，你的密码不能为空哦！");
		            $("#pshint1").css("color", "red");
		            return false;
		           }else if(pwd1.length<6||pwd1.length>16){
		            $("#pshint1").text("亲，密码长度只能在6-16位之间哦！");
		            $("#pshint1").css("color", "red");
		            return false;
		           }else{
		           $("#pshint1").text("");
		           $("#pshint1").css("color", "green");
		           return true;
		           }
		           });
		           //pwd2的验证,只需判断是否与pwd1相同即可
		           $("#pwd2").blur(function(){
		           var pwd1=$("#pwd1").val();
		           var pwd2=$("#pwd2").val();
		           
		           if(pwd1==pwd2){
		                $("#pshint").text("");
		                $("#pshint").css("color", "green");
		               return true;
		           }else{
		               $("#pshint").text("亲，两次密码不一致哦！");
		               $("#pshint").css("color", "red");
		               return false;
		           }
		           });
		          
		      
		           //zpwd1的验证
		            $("#zpwd1").blur(function(){
		           var zpwd1=$("#zpwd1").val();
		           var zpwd2=$("#zpwd2").val();
		           var pwd1=$("#pwd1").val();
		           var pwd2=$("#pwd2").val();
		           if(isNaN(Number(zpwd1))){
		                $("#zshint1").text("亲，支付密码只能是数字哦！");
		                $("#zshint1").css("color", "red");
		                 return false;
		               } else if(zpwd1==pwd1){
		              $("#zshint1").text("亲，支付密码和登录密码不能一致哦！");
		              $("#zshint1").css("color", "red");
		             return false;
		           } else if(zpwd1.length!=6){
		            $("#zshint1").text("亲，支付密码只能是6位数字哦！");
		            $("#zshint1").css("color", "red");
		            return false;
		           }else{
		           $("#zshint1").text("");
		           $("#zshint1").css("color", "green");		      
		           return true;
		           }
		           });
		           
		           //zpwd2的验证,同样只验证是否与前者相同即可
		           $("#zpwd2").blur(function(){
		           var zpwd1=$("#zpwd1").val();
		           var zpwd2=$("#zpwd2").val();
		           var pwd1=$("#pwd1").val();
		           var pwd2=$("#pwd2").val();
		           
		           if(zpwd1==zpwd2){
		             $("#zshint").text("");	
		             $("#zshint").css("color", "green");	            
		             return true;
		           }else{
		              $("#zshint").text("两次密码不一致");
		              $("#zshint").css("color", "red");
		              return false;
		           }
		           
		           });
		           
		     //密保问题     
		     $("#select").change(function(){            
             var se=document.getElementById("select");
             var index=se.selectedIndex;
             if(index!=0){
             $("#qq").hide();
             test1();
             }else{
             $("#qq").show();
             test2();            
             }                       
           });
		           
		         
		           
		         //提交并检查信息
		           $("#button").click(function(){		         
		           var pwd1=$("#pwd1").val();
		           var pwd2=$("#pwd2").val();
		           var zpwd1=$("#zpwd1").val();
		           var zpwd2=$("#zpwd2").val();
		           var zdyi=$("#zdyi").val();		           
		           var answer=$("#answer").val();
		           var se=document.getElementById("select");
                   var index=se.selectedIndex;
		          
		          if(!isNumberOr_Letter(pwd1)){
		              $("#pshint1").text("亲，密码只能是由数字或字母组成哦！");
		              $("#pshint1").css("color", "red");
		              return false;
		           }else if(pwd1.length==0){
		            $("#pshint1").text("亲，你的密码不能为空哦！");
		            $("#pshint1").css("color", "red");
		            return false;
		           }else if(pwd1.length<6||pwd1.length>16){
		            $("#pshint1").text("亲，密码长度只能在6-16位之间哦！");
		            $("#pshint1").css("color", "red");
		            return false;
		           }else if(pwd1!=pwd2){
		           $("#pshint").text("亲，两次密码不一致哦！");
		           $("#pshint").css("color", "red");
		           return false;
		           }else if(isNaN(Number(zpwd1))){
		            $("#zshint1").text("亲，支付密码只能是数字哦！");
		            $("#zshint1").css("color", "red");
		            return false;
		           } else if(zpwd1==pwd1){
		            $("#zshint1").text("亲，支付密码和登录密码不能一致哦！");
		            $("#zshint1").css("color", "red");
		            return false;
		           } else if(zpwd1.length!=6){
		            $("#zshint1").text("亲，支付密码只能是6位数字哦！");
		            $("#zshint1").css("color", "red");
		            return false;
		           }else if(zpwd1!=zpwd2){
		            $("#zshint").text("亲，两次密码不一致！");
		            $("#zshint").css("color", "red");
		            return false;
		           }else if(index!=0){
		           if($("#rili").val().length==0){
		             $("#rilil").text("亲，请设置密保答案！");
		             $("#rilil").css("color", "red");
		             return false;
		           }
		           }else if(index==0){
		           if($("#zdyi").val().length==0){
		           $("#zdyip").text("亲，请设置密保问题！");
		           $("#zdyip").css("color", "red");
		           return false;
		           }else if($("#zdyda").val().length==0){
		           $("#zdydl").text("亲，请设置密保答案！");
		           $("#zdydl").css("color", "red");
		           return false;
		           }
		           }else if(i=1){
		           return false;
		           }
		            else{ 	        	                            			           		        
		            } 	
		           var spwd1=md5(pwd1);
		           $("#pwd1").val(spwd1);
		           var spwd2=md5(pwd2);
		           $("#pwd2").val(spwd2);
		           
		           var szpwd1=md5(zpwd1);
		           $("#zpwd1").val(szpwd1);	
		           var szpwd2=md5(zpwd2);
		           $("#zpwd2").val(szpwd2);	
		           i=1;
		           $("#registedf").submit();
		           return false;				                     		        
		});
});   
        function test2(){
           var div1=document.getElementById("div1");
           var div2=document.getElementById("div2");
           div1.style.display="none";
           div2.style.display="inline";
        }
        
        function test1(){
          var div1=document.getElementById("div1");
          var div2=document.getElementById("div2");
          div1.style.display="inline";
          div2.style.display="none";         
        }
	</script>
		
  </head>
  
  <body>
  
    <div id="top">
		</div>
		<div id="header">
			<div class="layout">
				<div id="logo">
					<a href="uc.html">
						<img src="resources/lanqiaoPayModel/images/logo.png" alt="蓝桥" />
					</a>
				</div>
			</div>
		</div>
		<div id="page">
			<div class="layout reg-wrapper">
				<div class="reg-top">
					<ul class="reg-step clearfix">
						<li class="step">
							<p class="txt">1. 注册账号</p>
							<p class="arr arr-after"></p>
						</li>
						<li class="step">
							<p class="arr arr-before"></p>
							<p class="txt">2. 完善资料</p>
							<p class="arr arr-after"></p>
						</li>
						<li class="step current">
							<p class="arr arr-before"></p>
							<p class="txt">3. 密码设置</p>
							<p class="arr arr-after"></p>
						</li>
						<li class="step last">
							<p class="arr arr-before"></p>
							<p class="txt">4. 注册成功,等待审核</p>
						</li>
					</ul>
				</div>
				<form class="form-bd items-group-box" action="userController/registed" id="registedf" method="post">
					<div class="reg-accountName">
						<p>蓝桥支付用户名<span>11267@lanqiao.org</span></p>
					</div>
					<div class="item-group form-group">
						<div class="group-hd reg-setPassword">
							<h3>登录密码</h3><span>登录时需验证，保护账号信息</span>
						</div>
						<div class="group-bd">
							<div class="form-item">
								<h4 class="form-label">登录密码:</h4>
								<div class="form-entity">
									<div class="form-field">
										<input class="ipt" type="password" name="userName1" value="" placeholder="请设置登录密码" id="pwd1"/><label id="pshint1"></label>
									</div>
								</div>
							</div>
							<div class="form-item">
								<h4 class="form-label">再输入一次:</h4>
								<div class="form-entity">
									<div class="form-field">
										<input class="ipt" type="password" name="userName2" value="" placeholder="请再次输入密码" id="pwd2"/><label id="pshint"></label>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="item-group form-group">
						<div class="group-hd reg-setPassword">
							<h3>支付密码</h3><span>交易付款或账户信息变更时需输入，不可与登录密码一致！</span>
						</div>
						<div class="group-bd">
							<div class="form-item">
								<h4 class="form-label">支付密码:</h4>
								<div class="form-entity">
									<div class="form-field">
										<input class="ipt" type="password" name="userName3" value="" placeholder="请设置支付密码" id="zpwd1"/><label id="zshint1"></label>
									</div>
								</div>
							</div>
							<div class="form-item">
								<h4 class="form-label">再输入一次:</h4>
								<div class="form-entity">
									<div class="form-field">
										<input class="ipt" type="password" name="userName4" value="" placeholder="请再次输入密码" id="zpwd2"/><label id="zshint"></label>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="item-group form-group">
						<div class="group-hd reg-setPassword">
							<h3>安全保护问题</h3><span>忘记密码时，可通过回答问题找回密码</span>
						</div>
						<div class="group-bd">
							
							
						<div class="group-bd" id="selectdiv">
						<div class="form-item">
							<h4 class="form-label">安全保护问题:</h4>
							<div class="form-entity">
								<div class="form-field form-multi-ipt">
									<select class="sel sel-reg-setPassword" id="select" name="wentii">
										<option value="0">自定义</option>
										<option value="1">您的生日</option>
										<option value="2">您父亲的生日</option>
										<option value="3">您母亲的生日</option>
										<option value="4">您爱人的生日</option>
										<option value="5">您小学老师的生日</option>										
									</select>
									&nbsp;&nbsp;<span id="selectFalse"></span>
								</div>
							</div>
						</div>
							
							
							
							<div class="form-item" id="qq">
								<h4 class="form-label">自定义问题:</h4>
								<div class="form-entity">
									<div class="form-field">
										<input class="ipt" type="text" name="namezdy" value="" placeholder="请输入" id="zdyi" onclick="test2()"/><label id="zdyip"></label>
									</div>
								</div>
							</div>
							
							
							
							<div class="form-item" id="div1">
								<h4 class="form-label">您的答案:</h4>
								<div class="form-entity">
									<div class="form-field">
										<!-- <input class="ipt" type="text" name="namezd" value="" placeholder="请输入" id="answer"/><label id="answeri"></label> -->
										<input class="Wdate" type="text" onClick="WdatePicker()" id="rili" name="wentidai"><label id="rilil"></label>
									</div>
								</div>
							</div>
							
							<div class="form-item "id="div2" style="display:none">
								<h4 class="form-label">您的答案:</h4>
								<div class="form-entity">
									<div class="form-field">
										<input type="text" id="zdyda" name="wetidaz"><label id="zdydl"></label>
									</div>
								</div>
							</div>
							
							
						</div>
					</div>
					<div class="form-action clearfix">
						<a class="reset-btn" href="#">上一步</a>
						
						<!-- reg_common_e.html -->
						   <!-- <a href="userController/registed123" class="glb-btn submit-btn" id="button">
							<span>下一步</span>
						</a>  --> 
						
						<!--  <input type="submit" class="glb-btn submit-btn" id="button" value="下一步">   -->
						 
						 <button class="glb-btn submit-btn" type="submit" id="button">下一步</button>
					</div>
				</form>
			</div>
			<div id="bottom" class="bottom">
			</div>
		</div>
		
  </body>
</html>