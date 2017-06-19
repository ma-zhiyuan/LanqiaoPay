<%@ page language="java" pageEncoding="UTF-8" 
	import="java.util.*,org.lanqiao.pay.base.util.*,org.lanqiao.pay.base.entity.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>蓝桥支付-会员资料</title>
<!-- 设备适应性 移动设备优先-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="keywords" content="" />
<meta name="description" content="" />

<link rel="stylesheet" type="text/css" href="${applicationScope.url }/resources/lanqiaoPayModel/css/global.css"/>
<link rel="stylesheet" type="text/css"
	href="${applicationScope.url }/resources/lanqiaoPayModel/css/form.css" />
<link rel="stylesheet" type="text/css"
	href="${applicationScope.url }/resources/lanqiaoPayModel/css/biz/security.css" />

<link rel="stylesheet" href="${applicationScope.url }/resources/bootstrap/css/bootstrap.min.css" />
<!--JQuery一定要在bootstrap前面加--><!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script type="text/javascript" src="${applicationScope.url }/resources/scripts/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="${applicationScope.url }/resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${applicationScope.url }/resources/lanqiaoPayModel/js/global.js"></script>
<script type="text/javascript"
	src="${applicationScope.url }/resources/lanqiaoPayModel/js/util.js"></script>
<script>
	$(function() {
		document.getElementById("apply").onclick = function() {
			document.getElementById("applyDiv").style.display = "block";
		}
		document.getElementById("alreadyMer").onclick = function() {
			document.getElementById("applyDiv").style.display = "none";
		}
	});
</script>
</head>

<body>
	<div id="top"></div>
	<div id="header">
		<div class="layout">
			<div id="logo">
				<a href="http://www.lanqiao.org"> <img src="${applicationScope.url}/resources/styles/images/logo.png" alt="蓝桥" />
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
					<img src="${applicationScope.url }/resources/lanqiaoPayModel/images/df_portrait.jpg" alt="" /><br>
					 <a href="#foo" data-toggle="modal">修改头像</a>
				</div>
				<div class="account-table">
					<table>
						
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
	
	<!-- ---------------点击注册时触发模态框-------------------------- -->
		<!-- Modal -->
		<div class="modal fade" id="foo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		      	 <label name="valiName" style="color: red"></label>
		      	 <label name="valiPassword" style="color: red"></label>
		      	 <label name="valiPassword2" style="color: red"></label>
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		        <h4 class="modal-title" id="myModalLabel">修改图片</h4>
		      </div>
		      	<form class="form-horizontal" role="form" action="${applicationScope.url }/adminEvent/regist" method="post">
			      <div class="modal-body">
			      	<div class="form-group">
					    <label for="pic" class="col-sm-2 control-label">选择文件:</label>
					    <div class="col-sm-10">
					      <input type="file" value="" id="pic"  name="newPic">
					    </div>
					</div>
					
			    <div class="modal-footer">
			      <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			      <input type="submit" class="btn btn-primary" value="提交">
			      
			    </div>
			</form>
		    </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -- -->
</body>

</html>