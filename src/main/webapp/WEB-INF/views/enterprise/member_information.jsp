<%@ page language="java" pageEncoding="UTF-8"  import="java.util.*,org.lanqiao.pay.base.util.*,
	org.lanqiao.pay.base.entity.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>蓝桥支付-会员资料</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link rel="stylesheet" type="text/css" href="${applicationScope.url }/resources/lanqiaoPayModel/css/global.css"/>
<link rel="stylesheet" type="text/css"
	href="${applicationScope.url }/resources/lanqiaoPayModel/css/form.css" />
<link rel="stylesheet" type="text/css"
	href="${applicationScope.url }/resources/lanqiaoPayModel/css/biz/security.css" />
<script type="text/javascript"
	src="${applicationScope.url }/resources/scripts/jquery.js"></script>
<script type="text/javascript"
	src="${applicationScope.url }/resources/lanqiaoPayModel/js/global.js"></script>
<script type="text/javascript"
	src="${applicationScope.url }/resources/lanqiaoPayModel/js/util.js"></script>
<script type="text/javascript"
	src="${applicationScope.url }/resources/lanqiaoPayModel/js/global.js"></script>
<script type="text/javascript"
	src="${applicationScope.url }/resources/lanqiaoPayModel/js/util.js"></script>
<link rel="stylesheet" type="text/css" href="${applicationScope.url}/resources/bootstrap/css/bootstrap.min.css"/>
<!--JQuery一定要在bootstrap前面加--><!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script type="text/javascript" src="${applicationScope.url }/resources/scripts/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="${applicationScope.url }/resources/bootstrap/js/bootstrap.min.js"></script>

<script type="text/javascript">
	$(function() {
		document.getElementById("apply").onclick = function() {
			document.getElementById("applyDiv").style.display = "block";
		}
		document.getElementById("alreadyMer").onclick = function() {
			document.getElementById("applyDiv").style.display = "none";
		}
		
		$("#upload").click(function(){
			if($("#photo").val()==""){
				alert("请选择文件");
				return false;
			}
		});
		
	});
	
	function modifyPhoto(){
		$('#photoModal').modal("show");
	};
	
	
</script>
</head>

<body>
	<div id="top"></div>
	<div id="header">
		<div class="layout">
			<div id="logo">
				<a href="${applicationScope.url}/enterpriseUser/login"> <img src="${applicationScope.url}/resources/styles/images/logo.png" alt="蓝桥" />
				</a>
			</div>
			<div id="nav" class="glb-nav">
				<ul class="clearfix">
					<li class="glb-nav-uc"><a href="${applicationScope.url}/enterpriseUser/login"> <span></span>
					</a></li>
					<li class="glb-nav-setting current"><a
						href="${applicationScope.url}/enterpriseUser/getEnterpriseUser"> <span></span>
					</a></li>
					<li class="glb-nav-trade"><a href="${applicationScope.url }/enterpriseUser/enterpriseUserTradeHistory?id=${sessionScope.enterpriseUser.enterprise.id}"> <span></span>
					</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="main layout">
		<div class="col-menu">
			<ul class="menu-items">
				<li class="last current"><a
					class="menu-txt iconfont icon-menu-um"
					href="${applicationScope.url}/enterpriseUser/getEnterpriseUser">会员资料</a></li>
				<li><a class="menu-txt iconfont icon-menu-sm"
					href="${applicationScope.url}/enterprise/to_enterpriseSafeCenter">安全中心</a></li>
				<li><a class="menu-txt iconfont icon-menu-bm"
					href="${applicationScope.url }/BankCardController/EnterBankCard">银行卡管理</a></li>
			</ul>
		</div>
		<div class="col-main">
			<div class="main-hd">
				<h3 class="iconfont icon-safe-service">会员资料</h3>
			</div>
			<div class="main-bd account-infor clearfix">
				<div class="account-img">
					<c:if test="${empty sessionScope.enterpriseUser.photo }">
						<img src="${applicationScope.url }/resources/lanqiaoPayModel/images/df_portrait.jpg" alt="" /><br>
					</c:if>
					<c:if test="${!empty sessionScope.enterpriseUser.photo }">
						<img src="${sessionScope.enterpriseUser.photo}" alt="" height="100" width="100"/><br>
					</c:if>
					<a href="javascript:modifyPhoto()">修改头像</a>
				</div>
				<div class="account-table">
					<table>
						<tr>
							<th>企业名称:</th>
							<td colspan="2">${sessionScope.enterpriseUser.enterprise.enterpriseName}</td>
						</tr>
						<tr>
							<th>账户名:</th>
							<td colspan="2">${sessionScope.enterpriseUser.email}</td>
						</tr>
						<tr>
							<th>账户状态:</th>
							<td colspan="2"><a href="#">实名认证会员</a></td>
						</tr>
						<tr>
							<th>绑定手机号:</th>
							<td><% EnterpriseUser eUser = (EnterpriseUser)session.getAttribute("enterpriseUser");
														String phone  = eUser.getTell();%>
														<%=MyUtils.phoneNumberForHidden(phone) %>
														</td>
							<td class="col"><a href="${applicationScope.url }/enterpriseUser/update">修改</a></td>
						</tr>
						<tr>
							<th>绑定邮箱:</th>
							<td>
							<%String email  = eUser.getEmail();%>
														<%=MyUtils.emailToHidden(email) %>
							</td>
							<td class="col"><a href="${applicationScope.url }/secretController/updateEmail">修改</a></td>
						</tr>
						<tr>
							<th>银行卡:</th>
							<td><span class="bind">已绑定${sessionScope.allCardNum }张银行卡</span></td>
							<td class="col"><a href="${applicationScope.url }/BankCardController/EnterBankCard">查看</a></td>
						</tr>
						<tr>
							<th>注册时间:</th>
							<td colspan="2"><%
														Date date  = eUser.getRegistTime();%>
														<%=MyUtils.dateToString(date) %>
							</td>
						</tr>
						<tr>
							<th>是否是商户:</th>
							<td colspan="2" class="merchant"><input type="radio"
								name="radio1" id="alreadyMer" checked="checked" /><span>是</span>
								<input type="radio" name="radio1" id="apply" /><span>否</span></td>
						</tr>
					</table>
				</div>
			</div>
			<div id="applyDiv" class="applyDiv">
				<a href="javascript:;" class="glb-btn submit-btn"> <span>申请成为商户</span>
				</a>
			</div>
		</div>
	</div>
	<div id="bottom" class="bottom"></div>
	
	
	<div class="modal fade" id="photoModal" tabindex="-1" role="dialog" aria-labelledby="photoModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="exampleModalLabel">上传头像</h4>
				</div>
				<div class="modal-body">
					<form:form action="${applicationScope.url }/enterpriseUser/modifyPhoto" method="post" enctype="multipart/form-data">
						<div class="form-group">
							<label for="recipient-name" class="control-label">选择图片：</label>
							<input type="file" class="form-control" id="photo" name="photo">
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
							<button id=upload type="submit" class="btn btn-primary" >上传</button>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
	
	
</body>

</html>