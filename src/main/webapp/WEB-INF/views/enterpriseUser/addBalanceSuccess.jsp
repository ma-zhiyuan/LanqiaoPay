<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addBalanceSuccess.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${applicationScope.url}/resources/lanqiaoPayModel/js/jquery.js"></script>
	<script type="text/javascript">
		//设置多少秒后自动跳转
		function countDown(time,url){
		    $("#second").text(time);//<span>中显示的内容值
		     if(url==''){
		             url="/";
		     }
		     if(--time>0){
		            setTimeout("countDown("+time+",'"+url+"')",1000);//设定超时时间
		     }
		     else{
		         location.href=url;//跳转页面
		     }
		}
	</script>

  </head>
  
  <body>
  		<ul>  
             <li><span >${sessionScope.msg}</span></li>  
             <li>再过<span id="second">5</span>秒后自动跳转回首页<script language='javascript'>countDown(5,'<%=basePath %>enterpriseUser/to_enterprise_uc');</script></li>  
        </ul> 
  </body>
</html>
