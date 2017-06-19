<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>客服首页</title>
	<link rel="stylesheet" type="text/css" href="resources/customers/css/style.css" />
	<script type="text/javascript" src="resources/customers/js/jquery-1.6.min.js"></script>
	<script type="text/javascript" src="resources/customers/js/index.js"></script>
  </head>
  
  <body>
   	<div class="nav-top">
	<span><a href="customerServiceController/goCustomerHome">客服管理系统</a></span>
    <div class="nav-topright">
        <span>您好：客服${sessionScope.customer.id}</span><span><a href="customerServiceController/goCustomer">注销</a></span>
    </div>
	</div>
	<div class="nav-down">
		
	    <div class="leftmenu2">
	        <ul>
	            <li>
	            	<a class="j_a_list j_a_list3"></a>
	                <div class="j_menu_list">
	                	<span class="sp2"><i></i>提现审核</span>
	                	<a href="customerServiceController/to_customerCaer">个人提现待审核</a>
	                    <a href="customerServiceController/to_customerCaerYes">个人提现已审核</a>
	                    <a href="customerServiceController/to_customerCaer_Enterprise">企业提现待审核</a>
	                    <a href="customerServiceController/to_withdrawal_enterprise_true">企业提现已审核</a>
	                </div>
	            </li>
	            <li>
	            	<a class="j_a_list j_a_list3"></a>
	                <div class="j_menu_list">
	                	<span class="sp3"><i></i>转账审核</span>
	                	<a href="customerServiceController/toCustomerTransfer_personToPerson?state=0">个人向个人转账未审核</a>
	                    <a href="customerServiceController/toCustomerTransfer_personToPerson?state=1">个人向个人转账已审核</a>
	                	<a href="customerServiceController/toCustomerTransfer_personToEnterprise?state=0">个人向企业转账未审核</a>
	                    <a href="customerServiceController/toCustomerTransfer_personToEnterprise?state=1">个人向企业转账已审核</a>
	                    <a href="customerServiceController/toCustomerTransfer_enterpriseToPerson?state=0">企业向个人转账未审核</a>
	                    <a href="customerServiceController/toCustomerTransfer_enterpriseToPerson?state=1">企业向个人转账已审核</a>
	                    <a href="customerServiceController/toCustomerTransfer_enterpriseToEnterprise?state=0">企业向企业转账未审核</a>
	                    <a href="customerServiceController/toCustomerTransfer_enterpriseToEnterprise?state=1">企业向企业转账已审核</a>
	                </div>
	            </li>
	        </ul>
	    </div>
	    <div class="rightcon">
	    	<div class="right_con">
	        	
	        </div>
	    </div>
	</div>
  </body>
</html>
