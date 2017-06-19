<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'deposit_bankcard.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
	  <c:if test="${!empty sessionScope.bankCards }">
	  	<c:forEach items="${sessionScope.bankCards }" var="bankCard">
	  		<ul class="bank-items bankcard-items">
				<c:choose>
					<c:when test="${bankCard.bankId.name eq '中国农业银行'}">
						<li>
				  			<div class="bank-card-x">
								<div class="bank-ico bank-abc"></div>
								<p class="card-prop card-no">**** **** ****${bankCard.jspCardNumber }</p>
								<input type="hidden" value="${bankCard.cardNumber }">
							</div>
						</li>
					</c:when>
					<c:when test="${bankCard.bankId.name eq '招商银行'}">
						<li>
				  			<div class="bank-card-x">
								<div class="bank-ico bank-cmb"></div>
								<p class="card-prop card-no">**** **** ****${bankCard.jspCardNumber }</p>
								<input type="hidden" value="${bankCard.cardNumber }">
							</div>
						</li>
					</c:when>
					<c:when test="${bankCard.bankId.name eq '中国建设银行'}">
						<li>
				  			<div class="bank-card-x">
								<div class="bank-ico bank-ccb"></div>
								<p class="card-prop card-no">**** **** ****${bankCard.jspCardNumber }</p>
								<input type="hidden" value="${bankCard.cardNumber }">
							</div>
						</li>
					</c:when>
					<c:when test="${bankCard.bankId.name eq '华夏银行'}">
						<li>
				  			<div class="bank-card-x">
								<div class="bank-ico bank-hxb"></div>
								<p class="card-prop card-no">**** **** ****${bankCard.jspCardNumber }</p>
								<input type="hidden" value="${bankCard.cardNumber }">
							</div>
						</li>
					</c:when>
					<c:when test="${bankCard.bankId.name eq '平安银行'}">
						<li>
				  			<div class="bank-card-x">
								<div class="bank-ico bank-pingan"></div>
								<p class="card-prop card-no">**** **** ****${bankCard.jspCardNumber }</p>
								<input type="hidden" value="${bankCard.cardNumber }">
							</div>
						</li>
					</c:when>
					<c:when test="${bankCard.bankId.name eq '中国民生银行'}">
						<li>
				  			<div class="bank-card-x">
								<div class="bank-ico bank-cmbc"></div>
								<p class="card-prop card-no">**** **** ****${bankCard.jspCardNumber }</p>
								<input type="hidden" value="${bankCard.cardNumber }">
						</li>
					</c:when>
					<c:when test="${bankCard.bankId.name eq '中国工商银行'}">
						<li>
				  			<div class="bank-card-x">
								<div class="bank-ico bank-icbc"></div>
								<p class="card-prop card-no">**** **** ****${bankCard.jspCardNumber }</p>
								<input type="hidden" value="${bankCard.cardNumber }">
							</div>
						</li>
					</c:when>
					<c:otherwise>
						<li>
				  			<div class="bank-card-x">
								<div class="bank-ico bank-icbc"></div>
								<p class="card-prop card-no">**** **** ****${bankCard.jspCardNumber }</p>
								<input type="hidden" value="${bankCard.cardNumber }">
							</div>
						</li>
					</c:otherwise>
				</c:choose>			  			
	 		</ul> 		
	  	</c:forEach>
	  </c:if>
  </body>
</html>

