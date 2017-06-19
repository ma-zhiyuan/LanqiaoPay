<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">

	<title>显示所有客服</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel = "stylesheet" href="resources/lanqiaoPayModel/css/list.css" >
	<link rel = "stylesheet" href="resources/lanqiaoPayModel/css/global.css" >
	<link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
	<script type="text/javascript" src="resources/bootstrap/js/jquery-1.12.3.min.js"></script>
	<script type="text/javascript" src="resources/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="resources/bootstrap/js/kkpager.min.js"></script>
	<script type="text/javascript" src="resources/lanqiaoPayModel/js/list.js"></script>
	<script type="text/javascript" src="resources/scripts/md5.js"></script>
	<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/kkpager_blue.css" />

	<script type="text/javascript">
		function getParameter(name) {
			var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
			var r = window.location.search.substr(1).match(reg);
			if (r != null)
				return unescape(r[2]);
			return null;
		}
		
		function update(num){
			var id = $("#id"+num).val();
			$("#hiddenID").val(id);
		}
	
		$(function() {
			$("#updateButton").click(function(){
				var newPayPassword = $("[name=newPayPassword]").val();
				var newPayPassword2 = $("[name=newPayPassword2]").val();
				if(newPayPassword==""){
					alert("密码不能为空");
					return false;
				}else if(newPayPassword.length<6){
					alert("长度不能小于6");
					return false;
				}else if(newPayPassword2 != newPayPassword){
					alert("两次密码不一样");
					return false;
				}else{
					var newPwdMd5 = md5(newPayPassword);
					$("[name=newPayPassword]").val(newPwdMd5);
					var id = $("#hiddenID").val();
					var newPayPassword = $("[name=newPayPassword]").val();
					var url = "customerServiceController/updateCustomerServicePwd";
					var args = {"time":new Date(),"id":id,"newPayPassword":newPayPassword};
					$.ajax({
						type: 'POST',
						async: false,
						url: url,
						data: args,
						success: function(result){
									if(result=='true'){
										alert("修改成功");
									}
								 }
					});
				}
				
			});
			var totalPage = "${requestScope.Creteria.maxPageNo}";
			var totalRecords = "${requestScope.Creteria.total}";
			var pageNo = getParameter('pno');
			var state = $("[name=state]").val();
			var order = $("[name=order]").val();
			var minCount = $("[name=minCount]").val();
			var maxCount = $("[name=maxCount]").val();
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
				hrefLatter : '/showAllCustomerCares',
				getLink : function(n) {
					return this.hrefFormer + this.hrefLatter + "?pno=" + n + "&&state=" + state + "&&order=" + order 
								+ "&&minCount=" + minCount + "&&maxCount=" + maxCount;
				}
			});
		});
	</script>
	<style>
	.list-cond-tbs th{ width: 100px; text-align: right; padding-right: 8px; color:#666; font-weight: normal; }
	.texts {
		text-shadow: 5px 5px 5px #FF0000;
		font-size: 40px;
	}
	
	#customers {
		font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
		width: 100%;
		border-collapse: collapse;
	}
	
	#customers td, #customers th {
		font-size: 1em;
		text-align: center;
		border: 1px solid #98bf21;
		padding: 3px 7px 2px 7px;
	}
	
	#customers th {
		font-size: 1.1em;
		text-align: center;
		padding-top: 5px;
		padding-bottom: 4px;
		background-color: #A7C942;
		color: #ffffff;
	}
	</style>
</head>

<body style="background-color:#F0F0F0">
	<div class="container">
		<div style="size: 40px">
			<div class="texts">Customer Service List</div>
			<div class="form-group">
				<form action="customerServiceController/showAllCustomerCares" >
				<div id="listCondBd" class="list-cond-bd clearfix">
					<table class="list-cond-tbs">
						<tr>
							<th>客服状态:</th>
							<td>
								<select class="form-control" style="width: auto;" name="state" >
									<option value="2" ${Creteria.state == '2' ? 'selected' :'' }>所有服务</option>
									<option value="0" ${Creteria.state == '0' ? 'selected' :'' }>开放客服</option>
									<option value="1" ${Creteria.state == '1' ? 'selected' :'' }>禁用客服</option>
								</select>
							</td>
							<th>排列方式:</th>
							<td>
								<select class="form-control" style="width: auto;" name="order">
									<option value="id asc" ${Creteria.order == 'id asc' ? 'selected' :'' }>根据ID从小到大排列</option>
									<option  value="id desc" ${Creteria.order == 'id desc' ? 'selected' :'' }>根据ID从大到小排列</option>
									<option	 value="service_number asc" ${Creteria.order == 'service_number asc' ? 'selected' :'' }>根据业务量从小到大排序</option>
									<option  value="service_number desc" ${Creteria.order == 'iservice_number desc' ? 'selected' :'' }>根据业务量从大到小排序</option>
								</select>
							</td>
							<th >业务量区间:</th>
							<td>
								<div class="list-cond-between ">
									<input type="text" class="ipt twin-item " value="${requestScope.Creteria.minServiceCount }" name="minCount" /> 
									<span class="twin-ft">-</span> 
									<input type="text" class="ipt twin-item" value="${requestScope.Creteria.maxServiceCount }" name="maxCount"/>
								</div>
							</td>
							<input type="submit" class="cmn-btn cmn-btn-blue submit-cond-btn from-submit" value="确认">
						</tr>
					</table>
				</div>
				</form>
			</div>
		</div>
		<br>
		<div class="panel panel-default">
			<table class="table table-hover table-striped" align="center"
				id="customers">
				<thead>
					<tr>
						<th>客服ID</th>
						<th>客服名</th>
						<th>客服状态</th>
						<th>客服业务量</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${requestScope.CustomerCares }" var="cs" varStatus="num">
						<tr>
							<td>${cs.id }</td>
							<td>客服${cs.id }</td>
							<td><c:if test="${cs.state==0 }">开放中</c:if> <c:if
									test="${cs.state==1 }">禁用中</c:if></td>
							<td>${cs.serviceCount }</td>
							<td>
							<input type="hidden" id="id${num.index}" value="${cs.id }">
							<a data-toggle="modal" data-target="#myModal" id="updatePwd" onclick="update(${num.index})">修改密码</a> 
							<a href="customerServiceController/updateCustomerServiceState?id=${cs.id }&&state=${cs.state}"> <c:if test="${cs.state==1 }">开放</c:if> <c:if test="${cs.state==0 }">禁用</c:if>
							</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			    <div class="modal-dialog">
				        <div class="modal-content form-horizontal"  style="width:300px;margin:auto;margin-top: 150px;">
				            <div class="modal-header">
				                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				                <h4 class="modal-title" id="myModalLabel">修改密码</h4>
				            </div>
				            <div class="modal-body">
				            	<div>
				            		<input type="hidden" id="hiddenID" name="hiddenID">
				            	</div>
				            	<div class="form-group">
					            	<label class="col-sm-4 control-label">新密码:</label>
					          		<div class="col-sm-8"><input type="password" class="form-control" name="newPayPassword"></div>
				            	</div>
				            	<div  class="form-group">
				            		<label class="col-sm-4 control-label">确认密码:</label>
				            		<div class="col-sm-8"><input type="password" class="form-control"  name="newPayPassword2"></div>	
				            	</div>
				            </div>
				            <div class="modal-footer">
				                <button type="button" class="btn btn-default " data-dismiss="modal">关闭</button>
				             	<button type="button" class="btn btn-primary " data-dismiss="modal" id="updateButton" >提交更改</button>
				           	</div>
				        </div><!-- /.modal-content -->
			    </div><!-- /.modal -->
			</div>
		</div>
		<div style="width:800px;margin:0 auto;">
			<div id="kkpager"></div>
		</div>
	</div>
</body>
</html>
