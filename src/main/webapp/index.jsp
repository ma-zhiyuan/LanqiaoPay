<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="baidu-site-verification" content="OyUb4RVdSe" />
<meta name="renderer" content="webkit" />

<title>LanqiaoPay</title>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/bootstrap/css/bootstrap.min.css">
<style>
* {
	margin: 0;
	padding: 0;
}

table {
	border-collapse: collapse;
	border-spacing: 0;
}

h1, h2, h3, h4, h5, h6 {
	font-size: 100%;
}

ul, ol, li {
	list-style: none;
}

em, i {
	font-style: normal;
}

img {
	border: none;
}

input, img {
	vertical-align: middle;
}

body {
	background: #fff;
	color: #666;
	font-size: 14px;
	font-family: arial;
}

a {
	color: #666666;
	text-decoration: none;
}

html, body {
	background: #f9f9f9;
	width: 100%;
	height: 100%;
	font-family: Helvetica, sans-serif;
	-webkit-text-size-adjust: none;
}

* {
	-webkit-tap-highlight-color: rgba(0, 0, 0, 0);
}

textarea.fixAndroidKeyboard:focus, input.fixAKeyboard:focus {
	-webkit-tap-highlight-color: rgba(255, 255, 255, 0);
	-webkit-user-modify: read-write-plaintext-only;
}

.noscroll {
	position: absolute;
	width: 100%;
	height: 100%;
	overflow: hidden;
}

.app-dom {
	width: 100%;
}

.clearfix:after {
	display: block;
	content: '';
	clear: both;
	visibility: hidden;
}
</style>



<style>
body {
	font: 12px/1.5 "Microsoft YaHei", tahoma, arial, Hiragino Sans GB,
		\5b8b\4f53;
	overflow: hidden;
	position: relative;
	height: 100%;
	width: 100%;
}

a {
	margin-left: 15px;
	margin-right: 15px;
	text-decoration: none;
}

.header {
	position: fixed;
	width: 100%;
	top: 20px;
	left: 0px;
	z-index: 999;
}

.nav {
	width: 80%;
	height: 30px;
	line-height: 30px;
	margin: 0 auto;
}

.logo {
	float: left;
	background-image:
		url(${applicationScope.url}/resources/lanqiaoPayModel/images/logo.png);
	display: block;
	width: 70px;
	height: 25px;
	background-position: 0 0;
	background-repeat: no-repeat;
	background-size: 70px;
}

.entry {
	float: right;
	color: #fff;
}

.entry .state {
	color: #bfbfbf;
}

.entry a {
	font-size: 12px;
	color: #fff;
	margin: 0 5px;
}

.entry a:hover {
	color: #00bbee;
}

.container {
	width: 100%;
	height: 100%;
	background-color: #fff;
}

.content {
	width: 1200px;
	height: 100%;
	margin: 0 auto;;
}

.wrap {
	position: absolute;
	left: 0;
	top: 20%;
	width: 100%;
	text-align: center;
	z-index: 2;
}

.slogan {
	width: 600px;
	height: 200px;
	background:
		url(https://img.alicdn.com/tps/TB1POhqIFXXXXXbXFXXXXXXXXXX.png)
		no-repeat 0 0;
	display: inline-block;
	background-size: 600px;
}

.mid {
	width: 100%;
}

.main-entry {
	width: 550px;
	height: 50px;
	margin: 15px auto 0;
}

.main-entry a {
	display: block;
	text-decoration: none;
	float: left;
	text-align: center;
	cursor: pointer;
	border-radius: 8px;
	font-size: 14px;
	letter-spacing: 1px;
	height: 50px;
	width: 170px;
	color: #ffffff;
	line-height: 50px;
	position: relative;
	overflow: hidden;
}

.main-entry a .title {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	z-index: 2;
	padding-left: 18px;
}

.main-entry a .title i {
	position: absolute;
	left: 20px;
	top: 14px;
	background:
		url(https://img.alicdn.com/tps/TB1uh30IpXXXXXKXVXXXXXXXXXX.png)
		no-repeat 0 0;
	display: block;
	width: 24px;
	height: 24px;
	background-size: 24px;
}

.main-entry a .title .seller {
	background-image:
		url(https://img.alicdn.com/tps/TB12JNkIFXXXXXBXXXXXXXXXXXX.png);
}

.main-entry a .title .developer {
	background-image:
		url(https://zos.alipayobjects.com/rmsportal/neqhNGwxBXBmhVY.png);
}

.main-entry s {
	background-color: #00a3ee;
	opacity: .9;
	display: block;
	border-radius: 8px;
	height: 100%;
	width: 100%;
	position: absolute;
	top: 0;
}

.main-entry a:hover s {
	background-color: #00aaee;
	opacity: 1;
}

a.personal-login, a.seller-login {
	margin-left: 20px;
	transition: all .3s ease-in-out;
	-webkit-transition: all .3s ease-in-out;
	-moz-transition: all .3s ease-in-out;
	-o-transition: all .3s ease-in-out;
}

a.seller-login .seller-entry {
	display: none;
	z-index: 2;
	position: relative;
	height: 50px;
}

a.seller-login .inerval-line {
	display: none;
	width: 150px;
	margin: 0 auto;
	border-bottom: 1px solid rgba(255, 255, 255, 0.2);
	position: relative;
	height: 0;
	z-index: 2;
}

a.seller-login:hover {
	height: 100px;
	margin-top: -25px;
}

a.seller-login:hover .title {
	display: none;
}

a.seller-login:hover .seller-entry, a.seller-login:hover .inerval-line {
	display: block;
}

.alipay-app {
	text-align: center;
	position: absolute;
	bottom: 70px;
	left: 0;
	z-index: 3;
	width: 100%;
}

.alipay-app .ma {
	width: 600px;
	margin: 0 auto;
}

.alipay-app img {
	width: 60px;
	height: 60px;
}

.alipay-app p {
	line-height: 30px;
	height: 30px;
	color: #ffffff;
	margin: 5px 0 10px;
}

.footer {
	position: absolute;
	bottom: 0px;
	left: 0px;
	width: 100%;
	height: 65px;
	background-color: white;
	z-index: 99;
}

.nav-links {
	width: 99%;
	height: 20px;
	margin: 0 auto;
	text-align: center;
	overflow: hidden;
}

.footer ul {
	padding-left: 5px;
}

.footer li {
	display: inline-block;
	margin: 2px;
}

.footer li a {
	color: #666;
}

.ownership {
	text-align: center;
	height: 20px;
	line-height: 25px;
}

.nav-icons {
	width: 250px;
	height: 30px;
	margin: 0 auto;
	text-align: center;
}

.nav-icons a {
	width: 20px;
	display: block;
	float: left;
	margin-right: 5px;
	height: 28px;
	background:
		url(https://img.alicdn.com/tps/TB1.cMTIpXXXXbLXVXXXXXXXXXX.png)
		no-repeat 0 0;
}

a.pic1 {
	background-position: 0px -5px;
	width: 18px;
}

a.pic1:hover {
	background-position: 1px -28px;
	width: 18px;
}

a.pic2 {
	background-position: -33px -5px;
	width: 40px;
}

a.pic2:hover {
	background-position: -32px -28px;
	width: 40px;
}

a.pic3 {
	background-position: -74px -5px;
	width: 33px;
}

a.pic3:hover {
	background-position: -73px -28px;
	width: 33px;
}

a.pic4 {
	background-position: -115px -5px;
	width: 18px;
}

a.pic4:hover {
	background-position: -114px -28px;
	width: 18px;
}

a.pic5 {
	background-position: -135px -5px;
	width: 31px;
}

a.pic5:hover {
	background-position: -134px -28px;
	width: 31px;
}

a.pic7 {
	background-position: -200px -5px;
	width: 20px;
}

a.pic7:hover {
	background-position: -200px -26px;
	width: 20px;
}

#ServerNum, #ServerNum p {
	line-height: 10px;
	text-align: center;
	color: white;
	height: 10px;
}

/*slide*/
.front, .items, .item {
	position: absolute;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	overflow: hidden;
}

.back {
	bottom: 70px;
	position: absolute;
	left: 0;
	top: 0;
	width: 100%;
	overflow: hidden;
}

.items {
	overflow: visible;
}

.item {
	background: #fff none no-repeat center center;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
	display: none;
}
</style>

</head>

<body>
	<div class="main">
		<div class="header">
			<div class="nav">
				<div class="logo"></div>
				<div class="entry"></div>
			</div>
		</div>
		<div class="container">
			<div class="content">
				<div class="wrap">
					<div class="slogan"></div>
					<div class="mid">
						<a class="btn btn-primary btn-lg" href="userLogin.jsp">个人用户</a> <a
							class="btn btn-success btn-lg" href="enterprise_login.jsp">企业用户</a>

						<a class="btn btn-warning btn-lg" href="login.jsp">开发者中心</a>

						<a class="btn btn-info btn-lg" href="customer_login.jsp">客服登录</a>

						<a class="btn btn-danger btn-lg" href="admin_login.jsp">管理员登录</a>
					</div>
				</div>
			</div>
			<div class="back">

				<div class="items">
					<div class="item item1"
						style="background-image:url(https://img.alicdn.com/tps/TB1h9xxIFXXXXbKXXXXXXXXXXXX.jpg)"></div>
					<div class="item item2"
						style="background-image:url(https://img.alicdn.com/tps/TB1pfG4IFXXXXc6XXXXXXXXXXXX.jpg)"></div>
					<div class="item item3"
						style="background-image:url(https://img.alicdn.com/tps/TB1sXGYIFXXXXc5XpXXXXXXXXXX.jpg)"></div>
				</div>

			</div>
		</div>
		<div class="footer">
 		
        <div class="nav-links">
            <ul class="clearfix">
				<li><a href="#" target="_blank">关于我们</a></li>
				<li><a href="#" target="_blank">安全保障</a></li>
				<li><a href="#" target="_blank">诚聘英才</a></li>
				<li><a href="#" target="_blank">商户合作</a></li>
				<li><a href="#" target="_blank">银行合作</a></li>
				<li><a href="#" target="_blank">新手指南</a></li>
				<li><a href="#" target="_blank">在线客服</a></li>
				<li><a href="#" target="_blank">帮助中心</a></li>
				<li><a href="#" target="_blank">站点地图</a></li>
				<li class="last"><a href="#" target="_blank">联系我们</a></li>
			</ul>
        </div>
        <div class="ownership">
            <span><p>国信世纪人才服务（北京）有限公司</p></span>
        </div>
        <div class="nav-icons">
            <p>2017©蓝桥|京ICP备 11017700号
			</p>
        </div>
    </div>
	</div>
	<script
		src="https://t.alipayobjects.com/images/rmsweb/T19ctgXcRlXXXXXXXX.js"></script>
	<script>
	
		var slideEle = slider($('.items'));
	
		function slider(elem) {
			var items = elem.children(),
				max = items.length - 1,
				animating = false,
				currentElem,
				nextElem,
				pos = 0;
	
			sync();
	
			return {
				next : function() {
					move(1);
				},
				prev : function() {
					move(-1);
				},
				itemsNum : items && items.length
			};
	
			function move(dir) {
				if (animating) {
					return;
				}
				if (dir > 0 && pos == max || dir < 0 && pos == 0) {
					if (dir > 0) {
						nextElem = elem.children('div').first().remove();
						nextElem.hide();
						elem.append(nextElem);
					} else {
						nextElem = elem.children('div').last().remove();
						nextElem.hide();
						elem.prepend(nextElem);
					}
					pos -= dir;
					sync();
				}
				animating = true;
				items = elem.children();
				currentElem = items[pos + dir];
				$(currentElem).fadeIn(400, function() {
					pos += dir;
					animating = false;
				});
			}
	
			function sync() {
				items = elem.children();
				for (var i = 0; i < items.length; ++i) {
					items[i].style.display = i == pos ? 'block' : '';
				}
			}
	
		}
	
		if (slideEle.itemsNum && slideEle.itemsNum > 1) {
			setInterval(function() {
				slideEle.next();
			}, 4000)
		}
	</script>
	<script>
		function setCookie(cname, cvalue, exdays) {
			var d = new Date();
			d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
			var expires = "expires=" + d.toUTCString();
			document.cookie = cname + "=" + cvalue + "; " + expires;
		}
	
	
		//cookie记录个人登录标记为1,商家登录标记为2
		$(".personal-login").click(function() {
			setCookie("_n_h_n_option", "1", 365);
			location.href = "https://www.alipay.com";
		});
	
		$(".seller-login").click(function(e) {
			var target = $(e.target);
			if (target.hasClass('alipay')) {
				location.href = "https://b.alipay.com/?ynsrc=zhuzhanA";
			} else if (target.hasClass('koubei')) {
				location.href = "https://e.alipay.com/index.htm?from=zhuzhan20160927";
			}
		});
		$(".developer-login").click(function() {
			location.href = "https://open.alipay.com/platform/home.htm?from=zhuzhan20160818";
		});
	</script>

</body>
</body>
</html>
