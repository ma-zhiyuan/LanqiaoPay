<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'testbanklist.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/lanqiaoPayUI/css/global.css" />
	<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/lanqiaoPayUI/css/gla.css" />
	<script type="text/javascript"
	src="<%=basePath%>resources/bootstrap/js/jquery-1.12.3.min.js"></script>
	<script type="text/javascript"
	src="<%=basePath%>resources/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript"
	src="<%=basePath%>resources/bootstrap/js/modal.js"></script>
	<script type="text/javascript">
	
	 function getid(id){
			
			var id=id;
			
			document.getElementById("recipient-name1").value=id;
			
		
		
		}
		
	 $(function(){ 

		$("#submit").click(function() {
		var id=$("#recipient-name").val();
		var name=$("#message-text").val();
		name=$.trim(name);
		if(name==""){
			alert("请输入银行名称！")
		}else{
			//ajax请求发送的地址
			var url = "BankController/addbank";
			//发送请求所携带的参数
			var args = {
				"id":id,
				"name" : name,
				"time" : new Date()
			};
			//发送请求，data是返回数据
			$.post(url, args, function(data) {
				//把返回的数据放到提示框中。
				alert(data);
				 
				
			});
		
			}
		});
		
		$("#submit1").click(function() {
		var id=$("#recipient-name1").val();
		
		var name=$("#message-text1").val();
		
		name=$.trim(name);
		if(name==""){
			alert("请输入银行名称！")
		}else{
			//ajax请求发送的地址
			var url = "BankController/updateBank";
			//发送请求所携带的参数
			var args = {
				"id":id,
				"name" : name,
				"time" : new Date()
			};
			//发送请求，data是返回数据
			$.post(url, args, function(data) {
				//把返回的数据放到提示框中。
				alert(data);
				window.location.reload();
			});
		
			}
		});
		
		
	
		
		
});
		$('#exampleModal').on('show.bs.modal', function (event) {
			  var button = $(event.relatedTarget) 
			  var recipient = button.data('whatever') 
  
			  var modal = $(this)
			  modal.find('.modal-title').text('New message to ' + recipient)
			  modal.find('.modal-body input').val(recipient)
		})
		$('#exampleModal2').modal(options)
	</script>
  </head>
  		<c:if test="${empty requestScope.bankPage.banks}" var="bank">
			<div class="alert alert-success" role="alert">没有查到相关信息。</div>
		</c:if>
  		<div class="container" style="margin-left:250px;text-align: center;">
			<form class="form-inline" role="form" action="adminController/goBankList" method="get" style="font-size:15px;" >
			  <select name="orderby" style="font-size:15px;">
				<option value="">请选择排序方式</option>
				<option value="id asc" >按id升序排列</option>
				<option value="id desc">按id降序排列</option>
			</select> 请输入要查询的关键字：<input type="text" name="keyword"> <input type="submit" value="确定"  style="font-size:15px;"/>
			 
			</form>
		</div>
		<table class="glb-tables">
		<thead>
			<tr>
				<th class="tbs-col-cb"><input type="checkbox" name="" value="" /></th>
				<th>ID</th>
				<th>姓名</th>
				<th></th>
				<th>该行银行卡数量</th>
				<th></th>
				<th></th>
				<th></th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${requestScope.bankPage.banks }" var="bank">
				<tr>
					<td class="tbs-col-cb"><input type="checkbox" name="" value="" /></td>
					<td>${bank.id }</td>
					<td>${bank.name }</td>
					<td></td>
					<td>${bank.cardNum}  张</td>
					<td></td>
					<td></td>
					
					<td><a href="BankController/deleteBank?id=${bank.id }" onclick="return confirm('确定删除此银行吗?')">删除</a><span class="tbs-split">|</span> 
					<a  data-toggle="modal" href="BankController/updateBankid=${bank.id }" data-target="#exampleModal2" onclick="getid(${bank.id})" value="${bank.id }" id="id">修改</a>
					</td>
				</tr>
			</c:forEach>

		</tbody>
	</table>
		
		
		<nav aria-label="Page navigation">
			<ul class="pagination">
				<!-- 上一页 -->
				
					<c:forEach var="s" begin="1" end="${requestScope.bankPage.totalPage}">
					<li><a href="adminController/goBankList?pageNo=${s}&&keyword=${requestScope.bankCreteria.keyword}&&ordeby=${requestScope.bankCreteria.orderby}&&begin=${requestScope.bankCreteria.begin}&&pageSize=${requestScope.bankCreteria.pageSize}">${s}</a></li>
					</c:forEach>
					
			</ul>
			
			</nav>
			<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo">点次添加银行</button>
			
 <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">填写银行相关信息</h4>
      </div>
      <div class="modal-body">
        <form action="BankController/addbank" method="post">
          <div class="form-group">
            <label for="recipient-name" class="control-label">银行id:</label>
            <input type="text" class="form-control" id="recipient-name" value="${requestScope.total+1}" >
          </div>
          <div class="form-group">
            <label for="message-text" class="control-label">银行名称:</label>
            <textarea class="form-control" id="message-text"></textarea>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" data-dismiss="modal" id="submit">确定</button>
      </div>
    </div>
  </div>
</div>


<div class="modal fade" id="exampleModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">修改银行相关信息</h4>
      </div>
      	<form action="BankController/update" method="post" id="from1">
        	<div class="form-group">
           
            <input type="hidden" class="form-control" id="recipient-name1"  >
          </div>
          <div class="form-group">
            <label for="message-text" class="control-label">银行名称:</label>
            <textarea class="form-control" id="message-text1"></textarea>
          </div>
          
        </form> 
      	
     	
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="submit1" data-dismiss="modal" >保存</button>
      </div>
    </div>
  </div>
</div>
  <body>
 
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				

  </body>
</html>
