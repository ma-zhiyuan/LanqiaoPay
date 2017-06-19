<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

<title>企业向个人转账明细未审核</title>

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
		var totalPage = "${requestScope.Creteria.maxPageNo}";
		var totalRecords = "${requestScope.Creteria.total}";
		var orderSelect = $("[name=orderSelect]").val();
		var pageNo = getParameter('pno');
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
			hrefFormer : 'customerServiceController',
			//链接尾部
			hrefLatter : '/toCustomerTransfer_enterpriseToPerson',
			getLink : function(n) {
				return this.hrefFormer + this.hrefLatter + "?pno=" + n+ "&&orderSelect=" + orderSelect;
			}
		});
		$("[name=orderSelect]").change(function(){
			var order = $("[name=orderSelect]").val();
			var $aa = $('<a>');    
		    $('[name=orderSelect]').parent().append($aa);    
		    $aa.attr('href','customerServiceController/toCustomerTransfer_enterpriseToPerson?orderSelect=' + order);    
		  	$aa.get(0).click();     
		});
	});
</script>
</head>

<body style="background: #e1e9eb;">
	<jsp:include page="customer_header.jsp"></jsp:include>
	<div class="container">
		<div class="right_con">
			<c:if test="${empty requestScope.transferList }" var="transfer">
				<div class="alert alert-success" role="alert">没有查到相关信息。</div>
			</c:if>
			<ul class="glb-search">
					<div class=" form-inline ">
						<select name="orderSelect" class="form-control">
							<option value="">请选择排序方式</option>
							<option value="time asc"
								${Creteria.order == 'time asc' ? 'selected' :'' }>按提现时间升序排列</option>
							<option value="time desc"
								${Creteria.order == 'time desc' ? 'selected' :'' }>按提现时间降序排列</option>
							<option value="money asc"
								${Creteria.order == 'money asc' ? 'selected' :'' }>按提现金额升序排列</option>
							<option value="money desc"
								${Creteria.order == 'money desc' ? 'selected' :'' }>按提现金额降序排列</option>
						</select>
					</div>
			</ul>
			<table class="glb-tables">
				<thead>
					<tr>
						<th>序号</th>
						<th>转账名称</th>
						<th>转账描述</th>
						<th>转出企业</th>
						<th>转入用户</th>
						<th>转账金额</th>
						<th>转账时间</th>
						<th>状态</th>
						<th>是否通过审核</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${requestScope.transferList }" var="transfer">
						<tr>
							<td>${transfer.id }</td>
							<td>${transfer.transferName }</td>
							<td>${transfer.transferDescription }</td>
							<td>${transfer.fromEnterprise.enterpriseName }</td>
							<td>${transfer.toUser.name }</td>
							<td>${transfer.money }</td>
							<td><fmt:formatDate value="${transfer.time }" type="both" /></td>
							<td>等待审核</td>
							<td><a class="btn btn-primary"
									href="transferController/reviewTransferETP?id=${transfer.id }">
									确定通过审核</a>
							</td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
			<div style="width:800px;margin:0 auto;">
				<div id="kkpager"></div>
			</div>

		</div>
	</div>
</body>
</html>
