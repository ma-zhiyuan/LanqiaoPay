<%@ page language="java" import="java.util.*,org.lanqiao.pay.base.entity.*
,org.lanqiao.pay.base.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<title>企业用户详情</title>
  	<link href="${applicationScope.url }/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<script type="text/javascript" src="${applicationScope.url }/resources/scripts/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="${applicationScope.url }/resources/bootstrap/js/bootstrap.min.js"></script>
  </head>
  
  <body>
  <div class="container">
		<div class="panel panel-primary">
			<c:if test="${!empty requestScope.enterpriseUser }">
			  <div class="panel-heading">企业用户基本信息</div>
			  <div class="panel-body">
			  </div>
			
			 <table class="table table-bordered">
			 	<tr>
				 	<th>id</th>
				 	<th>照片</th>
				 	<th>姓名</th>
				 	<th>邮箱</th>
				 	<th>电话</th>
				 	<th>身份</th>
				 	<th>地址</th>
				 	<th>注册时间</th>
			 	</tr>
			 	<tr>
			 		<td>${requestScope.enterpriseUser.id }</td>
			 		<td><img src="${requestScope.enterpriseUser.photo}"  class="img-rounded" height="50px" 
			 				alt="暂无图片"	width="60px"/></td>
			 		<td>${requestScope.enterpriseUser.name }</td>
			 		<td>${requestScope.enterpriseUser.email }</td>
			 		<td>${requestScope.enterpriseUser.tell }</td>
			 		<td>${requestScope.enterpriseUser.identity }</td>
			 		<td>${requestScope.enterpriseUser.address }</td>
			 		<c:set scope="page" var="registTime" value="${requestScope.enterpriseUser.registTime }"></c:set>
	   				<%
	   					Date date = (Date)pageContext.getAttribute("registTime");
						String enterpriseUserRegistTimeStr = MyUtils.dateAndTimeToString(date);
	   				 %>
   					<td><%=enterpriseUserRegistTimeStr %></td>
			 	</tr>
			 </table>
			 </c:if>
		</div>
		
		 <hr>
			<div class="panel panel-success">
			  <div class="panel-heading">企业基本信息</div>
			  <div class="panel-body">
			  	<c:if test="${empty requestScope.enterpriseUser.enterprise.id }">
			  		暂无企业信息
			  	</c:if>
			  </div>
			<c:if test="${!empty requestScope.enterpriseUser.enterprise.id }">
			
			 <table class="table table-bordered">
			 	<tr>
				 	<th>id</th>
				 	<th>企业名称</th>
				 	<th>证件类型</th>
				 	<th>营业执照注册号</th>
				 	<th>单位所在地</th>
				 	<th>企业类型</th>
				 	<th>营业范围</th>
				 	<th>营业期限</th>
				 	<th>组织机构代码号</th>
			 	</tr>
			 	<tr>
			 		<td>${requestScope.enterpriseUser.enterprise.id }</td>
			 		<td>${requestScope.enterpriseUser.enterprise.enterpriseName }</td>
			 		<td>${requestScope.enterpriseUser.enterprise.licenseType }</td>
			 		<td>${requestScope.enterpriseUser.enterprise.licenseRegistrationNumber }</td>
			 		<td>${requestScope.enterpriseUser.enterprise.address }</td>
			 		<td>${requestScope.enterpriseUser.enterprise.enterpriseUnit.type }</td>
			 		<td>${requestScope.enterpriseUser.enterprise.businessScope}</td>
			 		<c:set scope="page" var="businessTerm" value="${requestScope.enterpriseUser.enterprise.businessTerm  }"></c:set>
	   				<%
	   					Date date = (Date)pageContext.getAttribute("businessTerm");
						String businessTerm = MyUtils.dateAndTimeToString(date);
	   				 %>
   					<td><%=businessTerm %></td>
   					<td>${requestScope.enterpriseUser.enterprise.organizationCodeNumber}</td>
			 	</tr>
			 </table>
			 <br>
			 </c:if>
		</div>
		 
		 
		  <hr>
			 <div class="panel panel-info">
			  <div class="panel-heading">法定代表人基本信息</div>
			  <div class="panel-body">
			  	<c:if test="${empty requestScope.enterpriseUser.enterprise.legalRepresentative }">
			  		暂无
			  	</c:if>
			  </div>
			<c:if test="${!empty requestScope.enterpriseUser.enterprise.legalRepresentative }">
			
			 <table class="table table-bordered">
			 	<tr>
				 	<th>id</th>
				 	<th>姓名</th>
				 	<th>证件类型</th>
				 	<th>证件号</th>
				 	<th>证件有效期</th>
			 	</tr>
			 	<tr>
			 		<td>${requestScope.enterpriseUser.enterprise.legalRepresentative.id }</td>
			 		<td>${requestScope.enterpriseUser.enterprise.legalRepresentative.name }</td>
			 		<td>${requestScope.enterpriseUser.enterprise.legalRepresentative.certificateType }</td>
			 		<td>${requestScope.enterpriseUser.enterprise.legalRepresentative.certificateNumber }</td>
			 		<c:set scope="page" var="certificateTerm"
			 		 value="${requestScope.enterpriseUser.enterprise.legalRepresentative.certificateTerm  }"></c:set>
	   				<%
	   					Date date = (Date)pageContext.getAttribute("certificateTerm");
						String certificateTerm = MyUtils.dateAndTimeToString(date);
	   				 %>
   					<td><%=certificateTerm %></td>
			 	</tr>
			 </table>
			 <br>
			 </c:if>
			</div>
		 	<table>
		 		<tr>
		 			<th style="text-align:center; vertical-align:middle">企业组织机构代码证</th>
		 			<th style="text-align:center; vertical-align:middle">营业执照</th>
		 			<th style="text-align:center; vertical-align:middle">普通营业执照</th>
		 			<th style="text-align:center; vertical-align:middle">多证合一营业执照</th>
		 			<th style="text-align:center; vertical-align:middle">法定人证件照正面</th>
		 			<th style="text-align:center; vertical-align:middle">法定人证件照反面</th>
		 		</tr>
		 		<tr>
		 			<td>
		 			 <img src="${requestScope.enterpriseUser.enterprise.organizationCodeCertificate}"
			 	   	width="150" height="100"/>
		 			</td>
		 			<td>
		 			<img src="${requestScope.enterpriseUser.enterprise.businessLicense}"
				   width="150" height="100"/>
		 			</td>
		 			<td>
		 			<img src="${requestScope.enterpriseUser.enterprise.enterpriseUnit.businessLicense}"
				   width="150" height="100"/>
				   </td>
		 			<td>
		 			<img src="${requestScope.enterpriseUser.enterprise.enterpriseUnit.allInOneLicense}"
				   width="150" height="100"/>
		 			</td>
		 			<td>
		 			<img src="${requestScope.enterpriseUser.enterprise.legalRepresentative.certificatePhotoPositive}"
				   width="150" height="100"/>
				   </td>
		 			<td>
		 			<img src="${requestScope.enterpriseUser.enterprise.legalRepresentative.certificatePhotoReserse}"
				   width="150" height="100"/>
		 			</td>
		 		</tr>
		 	</table>
		 <hr>
		 
		<div class="pull-right">
			<a class="btn btn-warning"
			 href="${applicationScope.url }/enterpriseUser/approve?id=${requestScope.enterpriseUser.id }" 
			 id="approve">通过审批</a>
		</div>
	</div>
  </body>
  <script type="text/javascript">
  	$(function(){
  		$("#approve").click(function(){
  				var res = confirm("你确定对该企业用户通过审批吗?");
				if(res==false)
					return false  				
  		});
  	});
  </script>
</html>
