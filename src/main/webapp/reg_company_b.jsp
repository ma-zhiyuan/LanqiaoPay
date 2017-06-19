<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN">


<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>蓝桥支付 - 会员注册</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link rel="stylesheet" type="text/css"
	href="${applicationScope.url}/resources/lanqiaoPayModel/css/global.css"></link>
<link rel="stylesheet" type="text/css"
	href="${applicationScope.url}/resources/lanqiaoPayModel/css/form.css"></link>
<link rel="stylesheet" type="text/css"
	href="${applicationScope.url}/resources/lanqiaoPayModel/css/biz/reg.css"></link>
<link rel="stylesheet" type="text/css"
	href="${applicationScope.url}/resources/lanqiaoPayModel/css/dialog.css"></link>
<script type="text/javascript"
	src="${applicationScope.url}/resources/lanqiaoPayModel/js/jquery.js"></script>
<script type="text/javascript"
	src="${applicationScope.url}/resources/lanqiaoPayModel/js/global.js"></script>
<script type="text/javascript"
	src="${applicationScope.url}/resources/lanqiaoPayModel/js/animation.js"></script>
<script type="text/javascript"
	src="${applicationScope.url}/resources/lanqiaoPayModel/js/util.js"></script>
<script type="text/javascript"
	src="${applicationScope.url}/resources/lanqiaoPayModel/js/group.js"></script>
<script type="text/javascript"
	src="${applicationScope.url}/resources/lanqiaoPayModel/js/validate.js"></script>
<script type="text/javascript"
	src="${applicationScope.url}/resources/lanqiaoPayModel/js/dialog.js"></script>
<script type="text/javascript"
	src="${applicationScope.url}/resources/scripts/jquery-3.1.1.min.js"></script>
<!-- 省市县三级联动 -->
<script type="text/javascript"
	src="${applicationScope.url}/resources/lanqiaoPayModel/js/pdata.js"></script>
<script type="text/javascript">
	
	$(function() {
		var enterpriseNameOk=0;
		var userNameOk=0;
		var legalPersonOk=0;
		var legalPersonIDCardOk=0;
		var linkManOk=0;
		var linkManPhoneOk=0;
		var buscodeOk=0;
		var fffOk=0;
		var f2Ok=0;
	
		//企业名称验证（唯一，非空）
		$("#enterpriseName").blur(function() {
			var name = $(this).val();
			name = $.trim(name);
			var url = "${applicationScope.url}/enterprise/verifyEnterpriseNameOnly";
			var args = {
				"name" : name,
				"time" : new Date()
			};
			if (name == "") {
				$("#enterpriseName-msg").text("企业名称不能为空");
			} else {
				$("#enterpriseName-msg").text("");
				enterpriseNameOk=1;
				$.post(url, args, function(data) {
					//把返回的数据放到提示框中。	
					$("#enterpriseName-msg").text(data);
				});
			}
		});
		
		
		//省市县三级联动（wxy）
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
		
		
		//组织代码号非空、合法
		$("#userName").blur(function() {
			var orgCode = $(this).val();
			orgCode = $.trim(orgCode);
			var codeVal = [ "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" ];
			var intVal = [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35 ];
			var crcs = [ 3, 7, 9, 10, 5, 8, 4, 2 ];
			if (!("" == orgCode) && orgCode.length == 10) {
				var sum = 0;
				for (var i = 0; i < 8; i++) {
					var codeI = orgCode.substring(i, i + 1);
					var valI = -1;
					for (var j = 0; j < codeVal.length; j++) {
						if (codeI == codeVal[j]) {
							valI = intVal[j];
							break;
						}
					}
					sum += valI * crcs[i];
				}
				var crc = 11 - (sum % 11);
				switch (crc) {
				case 10: {
					crc = "X";
					break;
				}
				default: {
					break;
				}
				}
				//alert("crc="+crc+",inputCrc="+orgCode.substring(9));
				//最后位验证码正确
				if (crc == orgCode.substring(9)) {
					$("#agency_code").text("");
					userNameOk=1;
				} else {
					$("#agency_code").text("组织机构代码不正确");
				}
			} else if ("" == orgCode) {
				$("#agency_code").text("组织机构代码不能为空");
			} else {
				$("#agency_code").text("组织机构代码格式不正确");
			}
		});
		
		
		
		//验证法人姓名为非空(wxy)
		$("#legalPerson").blur(function() {
			var name = $(this).val();
			name = $.trim(name);
			if (name == "") {
				$("#legalPerson-msg").text("法人姓名不能为空");
			} else if (name.indexOf(" ") != -1) {

				$("#legalPerson-msg").text("法人姓名不能包含空格");
			} else {
				$("#legalPerson-msg").text("");
				legalPersonOk=1;
			}
		});
		
		
		//验证法人身份证非空且、合法、唯一(wxy)
		$("#legalPersonIDCard").blur(function() {
			var id = $(this).val();

			id = $.trim(id);
			var city = {
				11 : "北京",
				12 : "天津",
				13 : "河北",
				14 : "山西",
				15 : "内蒙古",
				21 : "辽宁",
				22 : "吉林",
				23 : "黑龙江 ",
				31 : "上海",
				32 : "江苏",
				33 : "浙江",
				34 : "安徽",
				35 : "福建",
				36 : "江西",
				37 : "山东",
				41 : "河南",
				42 : "湖北 ",
				43 : "湖南",
				44 : "广东",
				45 : "广西",
				46 : "海南",
				50 : "重庆",
				51 : "四川",
				52 : "贵州",
				53 : "云南",
				54 : "西藏 ",
				61 : "陕西",
				62 : "甘肃",
				63 : "青海",
				64 : "宁夏",
				65 : "新疆",
				71 : "台湾",
				81 : "香港",
				82 : "澳门",
				91 : "国外 "
			};
			if (id == "") {
				$("#legalPersonIDCard-msg").text("法人身份证不能为空");
			} else {
				if (!id || !/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i.test(id)) {
					$("#legalPersonIDCard-msg").text("身份证格式错误");
				} else if (!city[id.substr(0, 2)]) {
					$("#legalPersonIDCard-msg").text("身份证地址编码错误");
				} else if (id.length == 18) {
					$("#legalPersonIDCard-msg").text("");
					//id = id.split('');
					//∑(ai×Wi)(mod 11)
					//加权因子
					var factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
					//校验位
					var parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ];
					var sum = 0;
					var ai = 0;
					var wi = 0;
					for (var i = 0; i < 17; i++) {
						ai = id[i];
						wi = factor[i];
						sum += ai * wi;
					}
					var last = parity[sum % 11];
					if (parity[sum % 11] != id[17]) {
						$("#legalPersonIDCard-msg").text("身份证校验位错误");
					} else {
						$("#legalPersonIDCard-msg").text("");
						legalPersonIDCardOk=1;
						var url = "${applicationScope.url}/enterprise/verifyIDCardOnly";
						var args = {
							"time" : new Date(),
							"id" : id
						};
						$.post(url, args, function(data) {
							$("#legalPersonIDCard-msg").text(data);
						});
					}
				}
			}
		});
		
		
		//验证常用联系人姓名非空(wxy)
		$("#linkMan").blur(function() {
			var linkManName = $(this).val();
			linkManName = $.trim(linkManName);
			if (linkManName == "") {
				$("#linkManAndPhone-msg").text("常用联系人不能为空");
			} else {
				$("#linkManAndPhone-msg").text("");
				linkManOk=1;
			}
		});
		//验证常用联系人电话非空且合法(wxy)
		$("#linkManPhone").blur(function() {
			var phone = $(this).val();
			valid = /^0?(12[0-9]|13[0-9]|14[57]|15[012356789]|16[0-9]|17[0-9]|18[0-9]|19[0-9])[0-9]{8}$/;
			phone = $.trim(phone);
			if (phone == "") {
				$("#linkManAndPhone-msg").text("常用联系人电话不能为空");
			} else if (!valid.test(phone)) {
				$("#linkManAndPhone-msg").text("常用联系人电话格式错误");
			} else {
				$("#linkManAndPhone-msg").text("");
				var url = "${applicationScope.url}/enterprise/verifyPhone";
				var args = {
					"time" : new Date(),
					"phone" : phone
				};
				$.post(url, args, function(data) {
					$("#linkManAndPhone-msg").text(data);
					if(data=""){
						linkManPhoneOk=1;
					}
				});
			}
		});
		
		
		//验证营业执照注册号
		$("#buscode").blur(function() {
			var busCode = $(this).val();

			busCode = $.trim(busCode);

			if (busCode.length == 15) {
				var sum = 0;
				var s = [];
				var p = [];
				var a = [];
				var m = 10;
				p[0] = m;
				for (var i = 0; i < busCode.length; i++) {
					a[i] = parseInt(busCode.substring(i, i + 1), m);
					s[i] = (p[i] % (m + 1)) + a[i];
					if (0 == s[i] % m) {
						p[i + 1] = 10 * 2;
					} else {
						p[i + 1] = (s[i] % m) * 2;
					}
				}
				if (1 == (s[14] % m)) {
					//营业执照编号正确!
					$("#business_license").text("");
					buscodeOk=1;
				//alert("营业执照编号正确!");
				} else {
					//营业执照编号错误!
					$("#business_license").text("营业执照编号错误");
				//alert("营业执照编号错误!");
				}
			} //如果营业执照为空
			else if ("" == busCode) {
				$("#business_license").text("营业执照编号不能为空");
			} else {
				$("#business_license").text("营业执照格式不对，必须是15位数的");
			//alert("营业执照格式不对，必须是15位数的！");
			}

		});
		
		//营业期限时间格式验证
		$("#f2").blur(function() {
			var date = $(this).val();
			date = $.trim(date);
			var reg = /^(\d{4})-(\d{2})-(\d{2})$/;
			if (date == "") {
				$("#date-msg").text("时间不能为空");
			} else if (!reg.test(date)) {
				$("#date-msg").text("时间格式不正确");
			} else {
				$("#date-msg").text("");
				f2Ok=1;
			}
		});
		
		
		//证件有效期时间格式验证
		$("#fff").blur(function() {
			var date = $(this).val();

			date = $.trim(date);
			var reg = /^(\d{4})-(\d{2})-(\d{2})$/;
			if (date == "") {
				$("#date-msg2").text("有效期时间不能为空");
			} else if (!reg.test(date)) {
				$("#date-msg2").text("有效期时间格式不正确");
			} else {
				$("#date-msg2").text("");
				fffOk=1;
			}
		});
		
		
		//判断能否点击下一步进行跳转(wxy)
		$(":submit").click(function(){
			if(enterpriseNameOk==0||userNameOk==0||legalPersonOk==0||legalPersonIDCardOk==0||linkManOk==0||linkManPhoneOk==0||buscodeOk==0||fffOk==0||f2Ok==0){
				alert("信息填写不完整或不正确");
				return false;
			}else{
				return true;
			}
		});
		
	});
	
</script>
</head>

<body>
	<div id="top"></div>
	<div id="header">
		<div class="layout">
			<div id="logo">
				<a href="uc.html"> <img
					src="${applicationScope.url}/resources/styles/images/logo.png"
					alt="蓝桥" />
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
					<li class="step current">
						<p class="arr arr-before"></p>
						<p class="txt">2. 完善资料</p>
						<p class="arr arr-after"></p>
					</li>
					<li class="step">
						<p class="arr arr-before"></p>
						<p class="txt">3. 密码设置</p>
						<p class="arr arr-after"></p>
					</li>
					<li class="step last">
						<p class="arr arr-before"></p>
						<p class="txt">4. 注册成功,等待审核</p>
					</li>
				</ul>
			</div>
			<form:form class="form-bd items-group-box" action="${applicationScope.url }/enterprise/registB"
				id="companyForm" modelAttribute="b" enctype="multipart/form-data">
				<div class="item-group form-group">
					<div class="group-hd">
						<h3>单位类型</h3>
					</div>
					<div class="group-bd">
						<div class="form-item">
							<h4 class="form-label">选择单位类型:</h4>
							<div class="form-entity reg-entity">
								<ul class="reg-radio-groups">
									<li><input type="radio" name="companyType" checked />企业</li>
									<li><input type="radio" name="companyType" />事业单位</li>
									<li><input type="radio" name="companyType" />民办非企业单位</li>
									<li><input type="radio" name="companyType" />个体工商户</li>
									<li><input type="radio" name="companyType" />社会团体</li>
								</ul>
							</div>
						</div>
						<div class="form-item">
							<h4 class="form-label"></h4>
							<div class="form-entity">
								<ul class="form-upload-groups">
									<li>
										<div class="form-upload-img">
											<a href="javascript:;" class="btn-file"> <span
												class="trs0-1s">
													<input type="file" name="file1"/>
												</span>
											</a>
											<div class="preview">普通营业执照</div>
										</div>
									</li>
									<li>
										<div class="form-upload-img">
											<a href="javascript:;" class="btn-file"> <span
												class="trs0-1s">
													<input type="file" name="file2"/>
												</span>
											</a>
											<div class="preview">多证合一营业执照</div>
										</div>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
				<div class="item-group form-group">
					<div class="group-hd">
						<h3>企业信息</h3>
						<span>按照证书上的内容逐字填写</span>
					</div>
					<div class="group-bd">
						<div class="form-item">
							<h4 class="form-label">企业名称:</h4>
							<div class="form-entity">
								<div class="form-field">
									<input class="ipt" type="text" id="enterpriseName"
										name="enterpriseName" value="" placeholder="请输入企业名称" />
								</div>
								<label id="enterpriseName-msg" style="color: red"></label>
							</div>
						</div>
						<div class="form-item">
							<h4 class="form-label">证件类型:</h4>
							<div class="form-entity">
								<div class="form-reg-radio">
									<input type="radio" name="docType" checked="checked" /><span>普通营业执照(存在独立的组织机构代码证)</span><br>
										<input type="radio" name="docType" /><span>多证合一营业执照(不存在独立的组织机构代码证)</span>
								</div>
							</div>
						</div>
						<div class="form-item">
							<h4 class="form-label">上传企业证件:</h4>
							<div class="form-entity">
								<div class="form-upload-tips">
									<p>证件拍照或彩色扫描成图片后上传，图片格式为PNG,JPG,单个文件请小于5M，图片必须清晰</p>
								</div>
								<ul class="form-upload-groups">
									<li>
										<div class="form-upload-img">
											<a href="javascript:;" class="btn-file"> <span
												class="trs0-1s">
													<input type="file" name="file3"/>
												</span>
											</a>
											<div class="preview">上传组织机构代码证</div>
										</div>
									</li>
									<li>
										<div class="form-upload-img">
											<a href="javascript:;" class="btn-file"> <span
												class="trs0-1s">
													<input type="file" name="file4"/>
												</span>
											</a>
											<div class="preview">上传营业执照</div>
										</div>
									</li>
								</ul>
							</div>
						</div>
						<div class="form-item">
							<h4 class="form-label">营业执照注册号:</h4>
							<div class="form-entity">
								<div class="form-field">
									<input class="ipt" type="text" id="buscode" name="f1" value=""
										placeholder="请输入营业执照注册号" />
								</div>&nbsp;&nbsp;
								<label id="business_license" style="color: red"></label>
							</div>
						</div>
						<div class="form-item">
							<h4 class="form-label">单位所在地:</h4>
							<div class="form-entity">
								<div class="form-field form-multi-ipt">
									<select class="sel sel-reg" name="input_province"
										id="input_province">
									</select> <select class="sel sel-reg" name="input_city" id="input_city">
									</select>
								</div>
							</div>
						</div>
						<div class="form-item">
							<h4 class="form-label">住所:</h4>
							<div class="form-entity">
								<div class="form-field">
									<textarea rows="" cols="45" class="ipt reg-text"></textarea>
								</div>
							</div>
						</div>
						<div class="form-item">
							<h4 class="form-label">经营范围:</h4>
							<div class="form-entity">
								<div class="form-field">
									<textarea class="ipt reg-textarea"></textarea>
								</div>
							</div>
						</div>
						<div class="form-item">
							<h4 class="form-label">营业期限:</h4>
							<div class="form-entity">
								<div class="form-field reg-check">
									<input class="ipt ipt-date last" type="text" id="f2" value=""
										placeholder="有效期" /><span id="date-msg" style="color:red"></span>
									<div>例如：2015-03-12</div>
								</div>
							</div>
						</div>
						<div class="form-item">
							<h4 class="form-label">组织机构代码号:</h4>
							<div class="form-entity">
								<div class="form-field">
									<input class="ipt" type="text" id="userName" value=""
										placeholder="组织机构代码号" /> &nbsp;&nbsp; <label id="agency_code"
										style="color: red"></label>
								</div>
							</div>
						</div>
						-
					</div>
				</div>
				<div class="item-group form-group">
					<div class="group-hd">
						<h3>法定代表人信息</h3>
					</div>
					<div class="group-bd">
						<div class="form-item">
							<h4 class="form-label">法定代表人姓名:</h4>
							<div class="form-entity">
								<div class="form-field form-multi-ipt">
									<input class="ipt" type="text" id="legalPerson"
										name="legalPerson" value="" placeholder="请输入联系人姓名" />
									&nbsp;&nbsp; <label id="legalPerson-msg" style="color: red"></label>
								</div>
							</div>
						</div>
						<div class="form-item">
							<h4 class="form-label">法定代表人归属地:</h4>
							<div class="form-entity">
								<div class="form-field form-multi-ipt">
									<select class="sel sel-reg">
										<option>请选择</option>
										<option>中国大陆</option>
										<option>中国台湾</option>
									</select>
								</div>
							</div>
						</div>
						<div class="form-item">
							<h4 class="form-label">有效证件:</h4>
							<div class="form-entity">
								<div class="form-field form-multi-ipt">
									<select name="" id="" class="sel sel-card">
										<option value="">身份证</option>
									</select> <input class="ipt ipt-cardno last" type="text"
										id="legalPersonIDCard" name="legalPersonIDCard" value=""
										placeholder="请输入证件号码" /> &nbsp;&nbsp; <label
										id="legalPersonIDCard-msg" style="color: red"></label>
								</div>
							</div>
						</div>
						<div class="form-item">
							<h4 class="form-label">上传法人证件:</h4>
							<div class="form-entity">
								<div class="form-upload-tips">
									<p>证件拍照或彩色扫描成图片后上传，图片格式为PNG,JPG,单个文件请小于5M，图片必须清晰</p>
								</div>
								<ul class="form-upload-groups">
									<li>
										<div class="form-upload-img">
											<a href="javascript:;" class="btn-file"> <span>
												<input type="file" name="file5"/>
											</span>
											</a>
											<div class="preview">上传法人证件照(正面)</div>
										</div>
									</li>
									<li>
										<div class="form-upload-img">
											<a href="javascript:;" class="btn-file"> <span>
												<input type="file" name="file6"/>
											</span>
											</a>
											<div class="preview">上传法人证件照(背面)</div>
										</div>
									</li>
								</ul>
							</div>
						</div>
						<div class="form-item">
							<h4 class="form-label">证件有效期:</h4>
							<div class="form-entity">
								<div class="form-field reg-check">
									<input class="ipt ipt-date last" type="text" id="fff" name="f2"
										value="" placeholder="有效期" /> <label id="date-msg2"
										style="color: red"></label>
									<div>例如：2015-03-12</div>
								</div>
							</div>
						</div>
						<div class="form-item">
							<h4 class="form-label">填写人身份:</h4>
							<div class="form-entity">
								<div class="form-reg-radio">
									<input type="radio" name="representative" checked="checked" /><span>我是法定代表人</span>
									<input type="radio" name="representative" /><span>我是代理人</span>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="item-group form-group">
					<div class="group-hd">
						<h3>联系方式</h3>
					</div>
					<div class="group-bd">
						<div class="form-item">
							<h4 class="form-label">常用联系人/电话:</h4>
							<div class="form-entity">
								<div class="form-field form-multi-ipt">
									<input class="ipt ipt-name" type="text" id="linkMan"
										name="linkMan" value="" placeholder="请输入联系人姓名" /> <input
										class="ipt ipt-mobile last" type="text" id="linkManPhone"
										name="linkManPhone" value="" placeholder="请输入联系人手机号" />
									&nbsp;&nbsp; <label id="linkManAndPhone-msg" style="color: red"></label>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="form-action clearfix">
					<a class="reset-btn" href="reg_company_a.html">上一步</a>
					<input id="next-button" type="submit" class="glb-btn submit-btn" value="下一步"/>
				</div>
			</form:form>
		</div>
		<div id="bottom" class="bottom"></div>
	</div>
</body>

</html>
