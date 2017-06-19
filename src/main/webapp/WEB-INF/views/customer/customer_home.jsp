<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>客服首页</title>
<link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
<script src="resources/bootstrap/js/jquery-1.12.3.min.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
<!-- //custom-theme -->
<link href="resources/esteem/css/bootstrap.css" rel="stylesheet"
	type="text/css" media="all" />
<link href="resources/esteem/css/component.css" rel="stylesheet"
	type="text/css" media="all" />
<link href="resources/esteem/css/export.css" rel="stylesheet"
	type="text/css" media="all" />
<link href="resources/esteem/css/flipclock.css" rel="stylesheet"
	type="text/css" media="all" />
<link href="resources/esteem/css/circles.css" rel="stylesheet"
	type="text/css" media="all" />
<link href="resources/esteem/css/style_grid.css" rel="stylesheet"
	type="text/css" media="all" />
<link href="resources/esteem/css/style.css" rel="stylesheet"
	type="text/css" media="all" />

<!-- font-awesome-icons -->
<link href="resources/esteem/css/font-awesome.css" rel="stylesheet">
<script type="text/javascript" src="resources/echarts/echarts.min.js"></script>
<script type="text/javascript"
		src="<%=basePath%>resources/sccl-admin/common/lib/jquery-1.9.0.min.js"></script>
<script src="<%=basePath%>resources/echarts/echarts.js"></script>
</head>

<body style="background: #e1e9eb;">
	<jsp:include page="customer_header.jsp"></jsp:include>
	<div class="rightcon">
		<div class="right_con">
			<div class="jumbotron">
				<div class="container"style="margin-left: 185px">
					<h2>欢迎登录客服管理系统</h2>
					<p>这里是客服管理系统,请客服人员将所有客户所请求操作给予处理，详情请查看左侧导航栏</p>
				</div>
			</div>
			<div style="margin-left: 185px">
				<div id="homedata" class="">
					<div class="agile_top_w3_grids">
						<ul class="ca-menu">
							<li><a href="customerServiceController/toCustomerTransfer_personToPerson?state=0"> <i class="fa fa-user"
									aria-hidden="true"></i>
									<div class="ca-content">
										<h4 class="ca-main one">${requestScope.ptpCount }</h4>
										<h3 class="ca-sub one">个人向个人转账</h3>
									</div>
							</a></li>
							<li><a href="customerServiceController/toCustomerTransfer_personToEnterprise?state=0"> <i class="fa fa-building-o"
									aria-hidden="true"></i>
									<div class="ca-content">
										<h4 class="ca-main">${requestScope.pteCount }</h4>
										<h3 class="ca-sub">个人向企业转账</h3>
									</div>
							</a></li>
							<li><a href="customerServiceController/toCustomerTransfer_enterpriseToPerson?state=0"> <i class="fa fa-database"
									aria-hidden="true"></i>
									<div class="ca-content">
										<h4 class="ca-main two">${requestScope.etpCount }</h4>
										<h3 class="ca-sub two">企业向个人转账</h3>
									</div>
							</a></li>
							<li><a href="customerServiceController/toCustomerTransfer_enterpriseToEnterprise?state=0"> <i class="fa fa-tasks"
									aria-hidden="true"></i>
									<div class="ca-content">
										<h4 class="ca-main three">${requestScope.eteCount }</h4>
										<h3 class="ca-sub three">企业向企业转账</h3>
									</div>
							</a></li>
							<div class="clearfix"></div>
						</ul>
						<ul class="ca-menu">
							<li><a href="customerServiceController/to_customerCaer"> <i class="fa fa-user"
									aria-hidden="true"></i>
									<div class="ca-content">
										<h4 class="ca-main one">${requestScope.personCount }</h4>
										<h3 class="ca-sub one">个人提现</h3>
									</div>
							</a></li>
							<li><a href="customerServiceController/to_customerCaer_Enterprise"> <i class="fa fa-building-o"
									aria-hidden="true"></i>
									<div class="ca-content">
										<h4 class="ca-main">${requestScope.enterpriseCount }</h4>
										<h3 class="ca-sub">企业提现</h3>
									</div>
							</a></li>
							<div class="clearfix"></div>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
