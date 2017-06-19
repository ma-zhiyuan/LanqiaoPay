<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>客服已审核</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/lanqiaoPayUI/css/global.css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/lanqiaoPayUI/css/gla.css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/lanqiaoPayUI/css/bootstrap.min.css">
<script type="text/javascript"
	src="<%=basePath%>resources/scripts/jquery-3.1.1.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/lanqiaoPayUI/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="resources/bootstrap/js/kkpager.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="resources/bootstrap/css/kkpager_blue.css" />
<script type="text/javascript">
	function getParameter(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return unescape(r[2]);
		return null;
	}

	$(function() {
		var totalPage = "${requestScope.Creteria.maxPageNo}";
		var totalRecords = "${requestScope.Creteria.total}";
		var pageNo = getParameter('pno');
		var orderSelect = $("[name=orderSelect]").val();
		var fuzzy = $("[name=fuzzy]").val();
		if (!pageNo) {
			pageNo = 1;
		}
		kkpager.generPageHtml({
			pno : pageNo,
			//总页码
			total : totalPage,
			//总数据条数
			totalRecords : totalRecords,
			//链接前部
			hrefFormer : 'customerServiceController',
			//链接尾部
			hrefLatter : '/to_customerCaerYes',
			getLink : function(n) {
				return this.hrefFormer + this.hrefLatter + "?pno=" + n + "&&orderSelect=" + orderSelect + "&&fuzzy=" + fuzzy;
			}
		});
	});
</script>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

html body {
	height: 100%
}

body {
	background: url("<%=basePath%>resources/styles/images/");
	background-size: 100% 100%;
}

.top {
	width: 400px;
	height: 40px;
	margin: 20px auto 0;
	font-size: 30px;
	font-family: "华文行楷";
	color: #ffffff;
	text-shadow: 0px 0px 4px #000;
}
</style>
</head>
<body style="background: #e1e9eb;">
	<jsp:include page="../customer/customer_header.jsp"></jsp:include>
	<div class="rightcon">
		<div class="right_con">
			<div class="top">
				<marquee behavior=alternate>个人提现管理中心</marquee>
			</div>
			<ul class="glb-search">
				<!-- 通过用户的需求查到相关信息 -->
				<form action="customerServiceController/to_customerCaerYes"
					method="post" class="form-inline">
					<div class="col-md-12 col-md-offset-5">
						<select name="orderSelect" class="form-control">
							<option value="">请选择排序方式</option>
							<option value="time asc"
								${Creteria.order == 'time asc' ? 'selected' :'' }>按提现时间升序排列</option>
							<option value="time desc"
								${Creteria.order == 'time desc' ? 'selected' :'' }>按提现时间降序排列</option>
							<option value="money asc"
								${Creteria.order == 'money asc' ? 'selected' :'' }>按提现金额升序排列</option>
							<option value="money desc"
								${Creteria.order == 'money asc' ? 'selected' :'' }>按提现金额降序排列</option>
						</select> <input type="text" name="fuzzy" class="form-control"
							placeholder="请输入要查询的名字:" value="${Creteria.fuzzy }"> <input
							type="submit" class="btn btn_success" value="确定" />
					</div>
				</form>
			</ul>
			<!-- 显示未处理的提现的业务 -->
			<div class="container">
				<div class="container">
					<table class="glb-tables">
						<thead>
							<tr>
								<th>ID</th>
								<th>用户名</th>
								<th>金额</th>
								<th>时间</th>
								<th>操作</th>
								<th>状态</th>
								<th>是否通过审核</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${requestScope.customerList }" var="ct">
								<c:if test="${ct.state==1 }">
									<tr>
										<td>${ct.id }</td>
										<td>${ct.user.name }</td>
										<td>${ct.money }</td>
										<td><fmt:formatDate value="${ct.time }" type="both" /></td>
										<td><c:if test="${ct.operation==1 }">提现</c:if></td>
										<td>已审核</td>
										<td>已通过审核</td>
									</tr>
								</c:if>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<!-- 分页 -->

			<div style="width:800px;margin:20px auto;">
				<div id="kkpager"></div>
			</div>
		</div>
	</div>
</body>
</html>
