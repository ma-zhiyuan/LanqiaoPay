<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${applicationScope.url}/resources/bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="${applicationScope.url}/resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${applicationScope.url}/resources/scripts/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
		function test(num){
			if($("#balance"+num).text()=="******"){
				$("#balance"+num).text($("#balancee"+num).val());
			}else{
				$("#balance"+num).text("******");
			}
		}
</script>
</head>

<body>
<div>
	<div>
		<!-- 查询表单 -->
		<form action="${applicationScope.url }/BankCardController/selectBankCard" class="form-inline">
			<select name="identity" class="form-control" style="width:100px;">
				<option>个人</option>
				<option>企业</option>
			</select>&nbsp;
			<select name="mode" class="form-control" style="width:100px;">
				<option>昵称</option>
				<option>邮箱</option>
				<option>卡号</option>
			</select>&nbsp;
			<input name="word" type="text" class="form-control" style="width:170px;" placeholder="请输入查询条件">&nbsp;
			<button class="btn btn-default" type="submit">查询</button>
		</form>
	</div>
	
	<div>
		<!-- 查询显示 -->
		<table class="table">
			<thead>
				<tr>
					<th>卡号</th>
					<th>所属银行</th>
					<th>所属者</th>
					<th>余额</th>
					<th>交易笔数</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.cards }" var="card" varStatus="num">
					<tr>
						<td>${card.cardNumber }</td>
						<td>${card.bankName }</td>
						<td>${card.person }</td>
						<td><span id="balance${num.index}">******</span>
							<button type="button" class="btn btn-default" aria-label="Left Align" onclick="test(${num.index})">
	  							<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
							</button>
							<input id="balancee${num.index}" type="text" style="display:none" value="${card.balance}">
						</td>
						<td>${card.dealNumber }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
</body>
</html>