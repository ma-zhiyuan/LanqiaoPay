<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'my_card_list.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	        <link rel="stylesheet" type="text/css" href="${applicationScope.url}/resources/lanqiaoPayModel/css/global.css">
        <link rel="stylesheet" type="text/css" href="${applicationScope.url}/resources/lanqiaoPayModel/css/dialog.css">
        <link rel="stylesheet" type="text/css" href="${applicationScope.url}/resources/lanqiaoPayModel/css/form.css">
        <link rel="stylesheet" type="text/css" href="${applicationScope.url}/resources/lanqiaoPayModel/css/biz/trade.css">
        <script type="text/javascript" src="${applicationScope.url}/resources/lanqiaoPayModel/js/jquery.js"></script>
        <script type="text/javascript" src="${applicationScope.url}/resources/lanqiaoPayModel/js/global.js"></script>
        <script type="text/javascript" src="${applicationScope.url}/resources/lanqiaoPayModel/js/util.js"></script>
        <script type="text/javascript" src="${applicationScope.url}/resources/lanqiaoPayModel/js/animation.js"></script>
        <script type="text/javascript" src="${applicationScope.url}/resources/lanqiaoPayModel/js/dialog.js"></script>

  </head>
  
  <body>
  	<c:if test="${!empty sessionScope.user }">
	  <c:if test="${!empty sessionScope.bankCards }">
	  	<c:forEach items="${sessionScope.bankCards }" var="bankCard">
	  		<ul class="bank-items bankcard-items">
		  		<c:if test="${bankCard.bankId.name eq '中国农业银行'}">
		  			<li>
		  			<div class="bank-card-x">
						<div class="bank-ico bank-abc"></div><!-- 农业 -->
						<p class="card-prop card-no">2222</p>
					</div>
					</li>
		  		</c:if>
		  		<c:if test="${bankCard.bankId.name eq '招商银行'}">
		  			<li>
		  			<div class="bank-card-x">
						<div class="bank-ico bank-cmb"></div><!-- 招商 -->
						<p class="card-prop card-no">1111</p>
					</div>
					</li>
		  		</c:if>
		  		<c:if test="${bankCard.bankId.name eq '中国建设银行'}">
		  			<li>
		  			<div class="bank-card-x">
						<div class="bank-ico bank-ccb"></div><!-- 建设 -->
						<p class="card-prop card-no">3333</p>
					</div>
					</li>
		  		</c:if>
		  		<c:if test="${bankCard.bankId.name eq '华夏银行'}">
		  			<li>
		  			<div class="bank-card-x">
						<div class="bank-ico bank-hxb"></div><!-- 华夏 -->
						<p class="card-prop card-no">*44449</p>
					</div>
					</li>
		  		</c:if>
		  		<c:if test="${bankCard.bankId.name eq '平安银行'}">
		  			<li>
		  			<div class="bank-card-x">
						<div class="bank-ico bank-pingan"></div><!-- 平安 -->
						<p class="card-prop card-no">55559</p>
					</div>
					</li>
		  		</c:if>
		  		<c:if test="${bankCard.bankId.name eq '中国民生银行'}">
		  			<li>
		  			<div class="bank-card-x">
						<div class="bank-ico bank-cmbc"></div><!-- 民生 -->
						<p class="card-prop card-no">6666</p>
					</div>
					</li>
		  		</c:if>
		  		<c:if test="${bankCard.bankId.name eq '中国工商银行'}">
		  			<li>
			  			<div class="bank-card-x">
							<div class="bank-ico bank-cmbc"></div><!-- 民生 -->
							<p class="card-prop card-no">6666</p>
						</div>
					</li>
		  		</c:if>
	 		</ul> 		
	  	</c:forEach>
	  </c:if>
  	</c:if>	
  	<c:if test="${empty sessionScope.user }"><a href="<%=basePath %>userLogin.jsp">去登陆</a></c:if>
  </body>
</html>
