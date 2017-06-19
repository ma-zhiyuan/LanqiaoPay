<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>蓝桥银行 - 会员注册</title>
		<meta name="keywords" content="" />
		<meta name="description" content="" />
		<link rel="stylesheet" type="text/css" href="${applicationScope.url}/resources/lanqiaoPayModel/css/global.css"/>
		<link rel="stylesheet" type="text/css" href="${applicationScope.url}/resources/lanqiaoPayModel/css/form.css"/>
		<link rel="stylesheet" type="text/css" href="${applicationScope.url}/resources/lanqiaoPayModel/css/biz/reg.css"/>
		<script type="text/javascript" src="${applicationScope.url}/resources/scripts/jquery-3.1.1.min.js"></script>
		<script type="text/javascript" src="${applicationScope.url}/resources/lanqiaoPayModel/js/global.js"></script>
		<script type="text/javascript" src="${applicationScope.url}/resources/lanqiaoPayModel/js/util.js"></script>
		<!-- 省市县三级联动 -->
		<script type="text/javascript" src="${applicationScope.url}/resources/lanqiaoPayModel/js/pdata.js"></script>
		<!-- 验证身份证 -->
		<script type="text/javascript" src="${applicationScope.url}/resources/lanqiaoPayModel/js/luhmCheck.js"></script>
		<script type="text/javascript">
			$(function() {
				//省市县三级联动
				var html = "<option value=''>请选择</option>";
				$("#input_city").append(html); $("#input_area").append(html);
				$.each(pdata, function(idx, item) {
					if (parseInt(item.level) == 0) {
						html += "<option value='" + item.names + "' exid='" + item.code + "'>" + item.names + "</option>";
					}
				});
				$("#input_province").append(html);
				$("#input_province").change(function() {
					if ($(this).val() == "") return;
					$("#input_city option").remove(); $("#input_area option").remove();
					var code = $(this).find("option:selected").attr("exid");
					code = code.substring(0, 2);
					var html = "<option value=''>请选择</option>";
					$("#input_area").append(html);
					$.each(pdata, function(idx, item) {
						if (parseInt(item.level) == 1 && code == item.code.substring(0, 2)) {
							html += "<option value='" + item.names + "' exid='" + item.code + "'>" + item.names + "</option>";
						}
					});
					$("#input_city").append(html);
				});
				$("#input_city").change(function() {
					if ($(this).val() == "") return;
					$("#input_area option").remove();
					var code = $(this).find("option:selected").attr("exid");
					code = code.substring(0, 4);
					var html = "<option value=''>=请选择=</option>";
					$.each(pdata, function(idx, item) {
						if (parseInt(item.level) == 2 && code == item.code.substring(0, 4)) {
							html += "<option value='" + item.names + "' exid='" + item.code + "'>" + item.names + "</option>";
						}
					});
					$("#input_area").append(html);
				});
		
				//后台验证银行卡唯一性
				var cardNumberIsOk = 0;
				function ajaxReturnBoolean(data, url) {
					var res = false;
					$.ajax({
						async : false,
						cache : false,
						url : url,
						success : function(data) {
							res = data;
						},
						type : "POST",
						data : data
					});
					return res;
				}
		
				$("[name=cardNumber]").blur(
					function() {
						var cardNumber = $("[name=cardNumber]").val();
						if (cardNumber.trim() == '') {
		
						} else { //发送ajax验证唯一性
							var res = ajaxReturnBoolean({
								"cardNumber" : cardNumber
							}, "${applicationScope.url}/BankCardController/cardIsUnique");
							if (res == 'true') {
								cardNumberIsOk = 1;
								$("[name=cardNumberValidateInfo]").text("")
							} else {
								cardNumberIsOk = 0;
								$("[name=cardNumberValidateInfo]").text("该卡已被注册!")
							}
						}
					});
		
		
				$(":submit").click(function(){
					
					//将单位所在地传入address
		 			var province=$("#input_province").val();
		 			var city=$("#input_city").val();
		 			if($.trim(province)!=""||$.trim(city)!=""){
		 				var address=province+","+city;
		 			}else if($.trim(province)!=""||$.trim(city)==""){
		 				var address=province;
		 			}else if($.trim(province)==""||$.trim(city)==""){
		 				var address="";
		 			}
		 			$("#address").val(address);
					if(cardNumberIsOk ==0){
						alert("信息填写不完整或不正确");
						return false;
					}else{
						return true;
					}
				});
				
				
			});
			/* bankno验证 */
			function CheckBankNo(t_bankno) {
				var bankno = $.trim(t_bankno.val());
				if (bankno == "") {
					$("#banknoInfo").html("请填写银行卡号");
					return false;
				}
				if (bankno.length < 16 || bankno.length > 19) {
					$("#banknoInfo").html("银行卡号长度必须在16到19之间");
					return false;
				}
				var num = /^\d*$/; //全数字
				if (!num.exec(bankno)) {
					$("#banknoInfo").html("银行卡号必须全为数字");
					return false;
				}
				//开头6位
				var strBin = "10,18,30,35,37,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,58,60,62,65,68,69,84,87,88,94,95,98,99";
				if (strBin.indexOf(bankno.substring(0, 2)) == -1) {
					$("#banknoInfo").html("银行卡号开头6位不符合规范");
					return false;
				}
				//Luhm校验（新）
				if (!luhmCheck(bankno))
					return false;
				$("#banknoInfo").html("uyt");
				cardNumberIsOk = 1;
				return true;
			}
			
		</script>
	</head>

	<body>
		<div id="top">
		</div>
		<div id="header">
			<div class="layout">
				<div id="logo">
					<a href="http://www.lanqiao.org">
						<img  src="${applicationScope.url}/resources/styles/images/logo.png" alt="蓝桥" />
					</a>
				</div>
			</div>
		</div>
		<div id="page">
			<div class="layout reg-wrapper">
				<div class="reg-top">
					<ul class="reg-step clearfix">
						<li class="step">
							<p class="txt">1. 注册账号</p>
							<p class="arr arr-after"></p>
						</li>
						<li class="step">
							<p class="arr arr-before"></p>
							<p class="txt">2. 完善资料</p>
							<p class="arr arr-after"></p>
						</li>
						<li class="step">
							<p class="arr arr-before"></p>
							<p class="txt">3. 密码设置</p>
							<p class="arr arr-after"></p>
						</li>
						<li class="step current">
							<p class="arr arr-before"></p>
							<p class="txt">4. 填写银行卡信息</p>
							<p class="arr arr-after"></p>
						</li>
						<li class="step last">
							<p class="arr arr-before"></p>
							<p class="txt">5. 注册成功,等待审核</p>
						</li>
					</ul>
				</div>
				<form:form class="form-bd" action="${applicationScope.url }/enterprise/registD" id="demoForm" modelAttribute="d">
					<div class="form-item">
						<h4 class="form-label">银行账号:</h4>
						<div class="form-entity">
							<div class="form-field">
								<input class="ipt" id="t_bankno" onblur="CheckBankNo($('#t_bankno'));" type="text" name="cardNumber" value="" placeholder="请输入银行账号" />
								<span id="banknoInfo" name="cardNumberValidateInfo" style="color: red"><form:errors path="cardNumber"/></span>
							</div>
						</div>
					</div>
					<div class="form-item">
						<h4 class="form-label">开户行:</h4>
						<div class="form-entity">
							<div class="form-field">
								<select name="bankName" class="sel sel-reg">
									<option value="中国工商银行">中国工商银行</option>
									<option value="中国光大银行">中国光大银行</option>
									<option value="招商银行">招商银行</option>
									<option value="兴业银行">兴业银行</option>
									<option value="中国建设银行">中国建设银行</option>
									<option value="中国邮政储蓄银行">中国邮政储蓄银行</option>
									<option value="中信银行">中信银行</option>
									<option value="中国银行">中国银行</option>
									<option value="中国民生银行">中国民生银行</option>
									<option value="中国农业银行">中国农业银行</option>
									<option value="河北银行">河北银行</option>
									<option value="华夏银行">华夏银行</option>
									<option value="哈尔滨银行">哈尔滨银行</option>
									<option value="北京银行银行">北京银行银行</option>
									<option value="东亚银行">东亚银行</option>
									<option value="浦发银行">浦发银行</option>
									<option value="上海银行">上海银行</option>
									<option value="平安银行">平安银行</option>
									<option value="微商银行">微商银行</option>
									<option value="交通银行">交通银行</option>
									<option value="广发银行">广发银行</option>
								</select>
								<span style="color: red"><form:errors path="bankName"/></span>
							</div>
						</div>
					</div>
					<div class="form-item">
						<h4 class="form-label">开户地址:</h4>
						<div class="form-entity">
							<div class="form-field" id="location">
								<select class="sel sel-reg" name="input_province" id="input_province"></select>
								<select class="sel sel-reg" name="input_city" id="input_city"></select>
								<span style="display:none"><input class="ipt" type="text" id="address" name="address" value="" placeholder="请输入开户地址" /></span>
								<span style="color: red"><form:errors path="address"/></span>
							</div>
						</div>
					</div>
					<div class="form-action clearfix">
						<input id="areyouok" type="submit" class="glb-btn submit-btn" value="下一步"/>
					</div>
				</form:form>
			</div>
			<div id="bottom" class="bottom">
			</div>
		</div>
	</body>
</html>
