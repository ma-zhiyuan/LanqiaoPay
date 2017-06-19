<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加客服</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">

  </head>
  
  <body>
  	<br>
  	<br>
  	<div class="col-md-6 col-md-offset-3">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">添加客服</h3>
			</div>
			<div class="panel-body">
				<br>
				<div class="alert alert-info" role="alert">
					<div align="center">
					<br>
						<form class="form-inline" action="customerServiceController/insertCustomerCareList" method="get">
							<div class="form-group">
								<label class="form-label">请输入添加的数量:</label>
						    	<input type="text" name="number" class="form-control" size=2>
					    		<input type="submit" value="提交" class="btn btn-primary">
							</div>
					    </form>
					</div>
				</div>
			</div>
		</div>
		<br>
	</div>
  	
  </body>
</html>
