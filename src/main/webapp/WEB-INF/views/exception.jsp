<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE>
<html>
  <head>
    <title>出异常了!</title>
  </head>
  <body>
	<h1 style="color:red">${requestScope.exception }</h1>
	<br>
	<a href="${applicationScope.url }">返回首页</a>
  </body>
</html>
