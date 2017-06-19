<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'wangzengTest.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <a href="${applicationScope.url }/enterprise/toRegistA">去企业注册页面</a><br>
    <a href="${applicationScope.url }/enterprise/finalRegistStep">企业注册插入各类信息</a><br>
    <a href="${applicationScope.url }/enterprise/toRegistD">去企业注册的D</a><br>
    <a href="${applicationScope.url }/userController/userTradeHistory?id=8">某个用户的交易历史记录页面</a><br>
    <a href="${applicationScope.url }/enterpriseUser/enterpriseUserTradeHistory?id=${sessionScope.enterpriseUser.enterprise.id}">某个企业用户的交易历史记录页面</a>
  </body>
</html>
