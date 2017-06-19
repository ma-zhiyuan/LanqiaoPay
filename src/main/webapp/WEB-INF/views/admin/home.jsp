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

<title>home</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="application/x-javascript">addEventListener("load", function() {
		setTimeout(hideURLbar, 0);
	}, false);
	function hideURLbar() {
		window.scrollTo(0, 1);
	}
</script>
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

<body>
	<div style="margin-left: 30px">
		<div id="homedata" class="">
			<div class="agile_top_w3_grids">
				<ul class="ca-menu">
					<li><a href="javascript:;"> <i class="fa fa-building-o"
							aria-hidden="true"></i>
							<div class="ca-content">
								<h4 class="ca-main">${sessionScope.al[0]}</h4>
								<h3 class="ca-sub">用户量</h3>
							</div>
					</a></li>
					<li><a href="javascript:;"> <i class="fa fa-user" aria-hidden="true"></i>
							<div class="ca-content">
								<h4 class="ca-main one">${sessionScope.al[1]}</h4>
								<h3 class="ca-sub one">企业量</h3>
							</div>
					</a></li>
					<li><a href="javascript:;"> <i class="fa fa-database"
							aria-hidden="true"></i>
							<div class="ca-content">
								<h4 class="ca-main two">${sessionScope.al[2]}</h4>
								<h3 class="ca-sub two">提现数</h3>
							</div>
					</a></li>
					<li><a href="javascript:;"> <i class="fa fa-tasks" aria-hidden="true"></i>
							<div class="ca-content">
								<h4 class="ca-main three">${sessionScope.al[4]}</h4>
								<h3 class="ca-sub three">充值数</h3>
							</div>
					</a></li>
					<li><a href="javascript:;"> <i class="fa fa-clone" aria-hidden="true"></i>
							<div class="ca-content">
								<h4 class="ca-main four">${sessionScope.al[3]}</h4>
								<h3 class="ca-sub four">转账数</h3>
							</div>
					</a></li>
					<div class="clearfix"></div>
				</ul>
			</div>
		</div>
	</div>

	<div id="main" style="width: 1100px;height:550px;"></div>
	<script type="text/javascript">
		// 基于准备好的dom，初始化echarts实例
		var myChart = echarts.init(document.getElementById('main'));
		var userNumbers = [];
		var EntNumbers = [];
		var time = [];
		var rechargs = [];
		var withdrawal = [];
		var transfer = [];
		var ez = [];
		$.ajax({
			type : "post",
			async : true, //异步请求
			url : "adminController/getall", //请求发送到TestServlet处
			data : {},
			dataType : "json", //返回数据形式为json
			success : function(result) {
				//请求成功时执行该函数内容，result即为服务器返回的json对象
				for (var i = 0; i < result.length; i++) {
					userNumbers[i] = result[i].userNumber;
					EntNumbers[i] = result[i].entNumber;
					rechargs[i] = result[i].rechargs;
					withdrawal[i] = result[i].withdrawal;
					transfer[i] = result[i].transfer;
					time[i] = result[i].end;
				}
	
				myChart.hideLoading();
				myChart.setOption(
					{
						//timeline基本配置都写在baseoption 中
						baseOption : {
							dataZoom : [
								{
									id : 'dataZoomY',
									type : 'slider',
									yAxisIndex : [ 0 ],
									filterMode : 'empty'
								},
	
							],
							tooltip : {},
							legend : {
	
							},
							timeline : {
								//loop: false,        
								axisType : 'category',
								show : true,
								inverse : true,
								autoPlay : true,
								playInterval : 1500,
							},
							grid : {
								botton : [ 60 ],
								containLabel : true
							},
							xAxis : [ {
								type : 'category',
								inverse : false,
								data : [ '用户数', '企业数', '转账数', '充值数', '转账数' ]
							}, ],
							yAxis : [ {
								type : 'value',
								name : '条',
								max : 100
							} ],
							series : [
								{
									type : 'bar',
									
								},
							]
						},
	
	
						//变量则写在options中
						options : [
							//1990
							{
								timeline : {
									//loop: false,        
									data : time
								},
								title : {
									text : ''
								},
								series : [
									{
										data : [ userNumbers[0], EntNumbers[0], transfer[0], rechargs[0], withdrawal[0] ]
									},
	
								]
							},
							//1995
							{
								title : {
									text : ''
								},
								series : [
									{
										data : [ userNumbers[1], EntNumbers[1], transfer[1], rechargs[1], withdrawal[1] ]
									},
	
								]
							},
							//97
							{
								title : {
									text : ''
								},
								series : [
									{
										data : [ userNumbers[2], EntNumbers[2], transfer[2], rechargs[2], withdrawal[2] ]
									},
	
								]
							},
							//
							{
								title : {
									text : ''
								},
								series : [
									{
										data : [ userNumbers[3], EntNumbers[3], transfer[3], rechargs[3], withdrawal[3] ]
									},
	
								]
							},
							//
							{
								title : {
									text : ''
								},
								series : [
									{
										data : [ userNumbers[4], EntNumbers[4], transfer[4], rechargs[4], withdrawal[4] ]
									},
	
								]
							},
							//
							{
								title : {
									text : ''
								},
								series : [
									{
										data : [ userNumbers[5], EntNumbers[5], transfer[5], rechargs[5], withdrawal[5] ]
									},
	
								]
							},
						]
					}
	
				);
			},
	
			error : function(errorMsg) {
				//请求失败时执行该函数
				myChart.hideLoading();
			}
		})
	
		myChart.showLoading();
	</script>

</body>
</html>
