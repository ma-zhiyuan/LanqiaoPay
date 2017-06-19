<%@ page language="java" import="java.util.*,org.lanqiao.pay.base.entity.*
,org.lanqiao.pay.base.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>企业用户显示</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="${applicationScope.url }/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<script type="text/javascript" src="${applicationScope.url }/resources/scripts/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="${applicationScope.url }/resources/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${applicationScope.url }/resources/dateComment/laydate.js"></script>
  </head>
  
  <body>
  <br>
	<h2 class="span7 text-center">企业用户审批</h2>
	<hr>
	<div class="container">
		
	<c:if test="${empty requestScope.enterpriseUsers }">
		<h2 class="span7 text-center">当前暂无符合条件的企业用户</h2>
	</c:if>
	<c:if test="${!empty requestScope.enterpriseUsers }">
   	<form:form class="form-inline text-center" role="form" name="creteriaForm"
		action="${applicationScope.url }/adminController/enterpriseUser_listForAdmin" modelAttribute="enterpriseUserPage">
			<div class="form-group">
				<label class="sr-only" for="emailKeyWord"></label> 邮箱关键字 <form:input
					 class="form-control" id="emailKeyWord" path="emailKeyWord"/>
			</div>
   			<div class="form-group">
				<label class="sr-only" for="registTime"></label> 注册起止时间 
				<%  EnterpriseUserPage enterpriseUserPage	
								= (EnterpriseUserPage)request.getAttribute("enterpriseUserPage");
					Date registTime = enterpriseUserPage.getRegistTime();
					String registTimeStr = MyUtils.dateAndTimeToString(registTime);
				 %>
				<input placeholder="请输入日期" class="laydate-icon form-control" id="registTime"
				onClick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"  name="registTimeStr"
					value="<%=registTimeStr%>"
				/>
			</div>
			<div class="form-group">
				<%Map<Integer,String> map = new HashMap<Integer,String>();
					map.put(1, "已审批");
					map.put(0, "未审批");
					map.put(2, "全选");
					request.setAttribute("approveMap", map);
				 %>
				<label class="sr-only" for="isApprove"></label>
				<form:radiobuttons path="isApprove" items="${requestScope.approveMap }"/> 
			</div>
			<div class="form-group">
				<label class="sr-only" for="index"></label>共${requestScope.enterpriseUserPage.total }页 当前页码:
				<input type="number" class="form-control" id="index" name="index"
	value="${requestScope.enterpriseUserPage.pageNo }" min="1" max="${requestScope.enterpriseUserPage.total }"/>
			</div>
			<input type="submit" class="btn btn-default btn-warning" value="查询">
   	</form:form>
   	
   	<table class="table table-hover">
   		<tr>
   			<th>#</th>
   			<th>邮箱</th>
   			<th>注册时间</th>
   			<th>联系电话</th>
   			<th>企业名称</th>
   			<th>状态</th>
   		</tr>
   		<c:forEach items="${requestScope.enterpriseUsers }" var="enterpriseUser" varStatus="vs">
   			<tr>
   				<th>${vs.index+1 }</th>
   				<th>${enterpriseUser.email }</th>
   				<c:set scope="page" var="registTime" value="${enterpriseUser.registTime }"></c:set>
   				<%
   					Date date = (Date)pageContext.getAttribute("registTime");
					String enterpriseUserRegistTimeStr = MyUtils.dateAndTimeToString(date);
   				 %>
   				<th><%=enterpriseUserRegistTimeStr %></th>
   				<th>${enterpriseUser.tell }</th>
   				<th>${enterpriseUser.enterprise.enterpriseName }</th>
   				<th>
   					<c:if test="${enterpriseUser.isForbid == 0 }">
   						<a id="approving" class="btn btn-success btn-sm" 
   						href="${applicationScope.url }/enterpriseUser/showDetailInfo?id=${enterpriseUser.id }"
   						 title="点击进行审批">未审批</a>
   					</c:if>
   					<c:if test="${enterpriseUser.isForbid == 1 }">
   						已审批
   					</c:if>
   				</th>
   			</tr>
   		</c:forEach>
   	</table>
   		<div class="pull-right">
			<c:if test="${requestScope.enterpriseUserPage.pageNo >1 }">
					<button type="button" class="btn btn-info" name="preNo">上一页</button>
			</c:if>
			<c:if test="${requestScope.enterpriseUserPage.pageNo < requestScope.enterpriseUserPage.total}">
					<button type="button" class="btn btn-info" name="nextNo">下一页</button>
			</c:if>
		</div>
   	</c:if>
   	</div>
  </body>
  <script type="text/javascript">
  	$(function(){
		$("[name=preNo]").click(function(){
			var index = $("[name=index]").val(${requestScope.enterpriseUserPage.pageNo }-1)
			$("[name=creteriaForm]").submit();
		});
		$("[name=nextNo]").click(function(){
			var index = $("[name=index]").val(${requestScope.enterpriseUserPage.pageNo }+1)
			$("[name=creteriaForm]").submit();
		});
	})
  </script>
</html>
