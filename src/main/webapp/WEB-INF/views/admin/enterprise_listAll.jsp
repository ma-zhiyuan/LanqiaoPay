<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>企业用户显示</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/lanqiaoPayUI/css/global.css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/lanqiaoPayUI/css/gla.css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/lanqiaoPayModel/css/kkpager_orange.css" />
<script type="text/javascript"
	src="<%=basePath%>resources/bootstrap/js/jquery-1.12.3.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/bootstrap/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/lanqiaoPayModel/js/kkpager.min.js"></script>
<script type="text/javascript">
	function getParameter(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null) return unescape(r[2]);
		return null;
	}

	//init
	$(function() {
		var totalPage = "${requestScope.enterpriceUserCreteria.end}";
		var totalRecords = "${requestScope.enterpriceUserCreteria.total}";
		var pageNo = getParameter('pno');
		var orderby = $("[name=orderby]").val();
		var keyword = $("[name=keyword]").val();
		if (!pageNo) {
			pageNo = 1;
		}
		//生成分页
		//有些参数是可选的，比如lang，若不传有默认值
		kkpager.generPageHtml({
			pno : pageNo,
			//总页码
			total : totalPage,
			//总数据条数
			totalRecords : totalRecords,
			//链接前部
			hrefFormer : 'adminController',
			//链接尾部
			hrefLatter : '/goEnterprise_listAll',
			getLink : function(n) {
				return this.hrefFormer + this.hrefLatter + "?pno=" + n + "&&orderby=" + orderby + "&&keyword=" + keyword;
			}
		});
	});
</script>


</head>

<body>
	<c:if test="${empty requestScope.enterPriseUser }">
		<div>没有查询到相关信息！</div>
	</c:if>
	<ul class="glb-search">
		<!-- 通过用户的需求查到相关信息 -->
		<form action="adminController/goEnterprise_listAll" method="post">
			<select name="orderby">
				<option value="id asc">请选择排序方式</option>
				<option value="id asc">按id升序排列</option>
				<option value="id desc">按id降序排列</option>
			</select> 请输入要查询的关键字：<input type="text" name="keyword"> <input
				type="submit" value="确定" />
		</form>
	</ul>
	<table class="glb-tables">
		<thead>
			<tr>
				<th class="tbs-col-cb"><input type="checkbox" name="" value="" /></th>
				<th>ID</th>
				<th>姓名</th>
				<th>邮箱</th>
				<th>电话号码</th>
				<th>住址</th>
				<th>身份类型</th>
				<th>注册时间</th>
				<th>企业id</th>
				<th>状态信息</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${requestScope.enterPriseUser }"
				var="enterPriseUser">
				<tr>
					<td class="tbs-col-cb"><input type="checkbox" name="" value="" /></td>
					<td>${enterPriseUser.id }</td>
					<td>${enterPriseUser.name }</td>
					<td>${enterPriseUser.email }</td>
					<td>${enterPriseUser.tell }</td>
					<td>${enterPriseUser.address }</td>
					<td>${enterPriseUser.identity }</td>
					<td>${enterPriseUser.registTime }</td>
					<td>${users.enterprise.id }</td>
					<!-- 0为禁用 -->
					<c:if test="${enterPriseUser.isForbid eq 0 }">
					<td>禁用|<a href="enterpriseUser/updateState?id=${enterPriseUser.id}&state=0">恢复</a></td>
					<td>当前状态下不能操作</td>
					</c:if>
					<!-- 1为正常 -->
						<c:if test="${enterPriseUser.isForbid eq 1 }">
						<td>正常|<a href="enterpriseUser/updateState?id=${enterPriseUser.id}&state=1">禁用</a></td>
							<!-- 默认正常 0 已注销为1 satate=3为需要去注销-->
							<c:if test="${enterPriseUser.isDelete eq 0 }">
							<td><a href="enterpriseUser/updateState?id=${enterPriseUser.id}&state=3">注销</a> <span class="tbs-split">|</span> <a href="enterpriseUser/toUpdatePassword?id=${enterPriseUser.id }">修改</a>
							</td>
						</c:if>
						<c:if test="${enterPriseUser.isDelete eq 1 }">
						<td>该用户已被注销</td>
						</c:if>
					</c:if>
				</tr>
			</c:forEach>

		</tbody>
	</table>
	<div style="width:800px;margin:0 auto;">
		<div id="kkpager"></div>
	</div>
</body>
</html>
