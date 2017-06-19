// -----------------------------------------------------
// | 版权所有: 蓝桥 - 教研部                                                                                 |
// -----------------------------------------------------

/**
 * 全局通用函数(定义网站通用的函数,工具类)
 *
 * @Author zbq
 * @Date 2016-09-19
 */
! function() {

	window.Global = window.Global || {};
	var G = Global = {};

	/**
	 * 全局域名配置
	 */
	G.Domain = {};

	/**
	 * 全局常量
	 */
	G.Config = {};

	/**
	 * 全局导航
	 */
	G.Nav = {};

	/**
	 * 顶部信息
	 */
	G.Header = {};

	/*
	 * 底部信息
	 */
	G.Bottom = {};

	/**
	 * 页面布局
	 */
	G.Layout = {};

}();

/**
 * 蓝桥银行全局域
 */
! function(G, $) {

	//var http = 'http://',
	//	domain = '.lanqiao.org';
	var http = '',
	    domain = ''

	// 主页
	// G.Domain.home = http + 'www' + domain;
	G.Domain.home = '';

	// 统一身份认证
	G.Domain.passport = G.Domain.home;

	// 登录
	G.Domain.login = G.Domain.home;

	// 注册
	G.Domain.reg = G.Domain.home + 'reg_personal_b.html';

	// 个人中心
	G.Domain.uc = G.Domain.home + 'uc.html';

	// 后台管理系统
	G.Domain.admin = G.Domain.home;

	// 帮助中心
	G.Domain.help = G.Domain.home;

	// 反馈中心
	G.Domain.feedback = G.Domain.home;

	// 蓝桥官网
	G.Domain.lanqiaoHome = 'http://www.lanqiao.org';

	// 蓝桥微博
	G.Domain.lanqiaoWeibo = 'http://www.lanqiao.org';

	// 蓝桥微信
	G.Domain.lanqiaoWeixin = 'http://www.lanqiao.org';

}(Global, jQuery);

/**
 * 蓝桥银行全局配置信息
 */
! function(G, $) {

	// 跨域参数
	G.Config.callback = 'callback=?';

}(Global, jQuery);

/**
 * 蓝桥银行顶部全局导航
 * 使用方法: 页面加载完成后调用Global.Nav.init()即可. (注: 全局插件, 只需调用一次).
 * 插件说明: 调用本插件后会自动绑定导航的鼠标移入移出事件, 当鼠标移入移出导航时设置相应的样式.
 * 默认导航: 在默认导航li标签中添加current样式后会自动将选中样式移入该导航中. 例: <li class="current">
 */
! function(G, $) {

	var Nav = G.Nav;

	// 导航相关元素节点
	var NavEle = {

		// 导航移入效果HTML
		navHover: [
			'<div id="navHover" class="glb-nav-hover"></div>'
		].join("")

	};

	var navObj, navHover, curNav;

	/**
	 * 初始化全局导航
	 * 1. 添加导航选中样式动画效果节点(选中样式通过DIV动画实现)
	 * 2. 设置页面默认导航为已选中样式(将选中样式移入默认导航中)
	 * 3. 绑定全部导航鼠标事件(鼠标移入移出)
	 */
	Nav.init = function() {
		navObj = $("#nav");
		if(!navObj.length) {
			return false;
		}

		navHover = $(NavEle["navHover"]), curNav = navObj.find(".current a");
		navObj.append(navHover);
		curNav.length && animNavHover(curNav);

		$("#nav a").hover(function() {
			animNavHover($(this));
		}, function() {
			animNavHover(curNav);
		});
	};

	/**
	 * 使用动画设置导航选中样式.
	 * @param cur 导航对象
	 */
	var animNavHover = function(cur) {
		var parent = cur.parent();

		// 设置已选中导航样式
		// 动画移入选中导航
		if(parent.hasClass("hover") == false) {
			window.Util.addUqClass(parent, "hover");
			navHover.stop().animate({
				width: cur.outerWidth(),
				left: window.Util.getRelativePos(cur, navObj).x
			}, 240);
		}

	};

}(Global, jQuery);

/**
 * 蓝桥银行头部管理组件
 *
 * 使用方法: header.js即可
 *
 * @Author zbq
 * @Date 2015-02-26
 */
! function(G, $) {

	/**
	 * 蓝桥银行头部节点
	 */
	var topEle = {

		// 全局吊顶节点
		top: [
			'<div class="layout">',
			'	<ul class="col-l">',
			'		<li class="glb-pat">',
			'			<span class="iconfont icon-glb-home"></span>',
			'			<a href="' + Global.Domain.lanqiaoHome + '" target="_blank">蓝桥主页</a>',
			'		</li>',
			'		<li class="glb-pat">',
			'			<span class="iconfont icon-glb-mb"></span>',
			'			<a href="' + Global.Domain.lanqiaoWeibo + '" target="_blank">官方微博</a>',
			'		</li>',
			'	</ul>',
			'	<ul class="col-r" id="topItem">',
			'		<li class="bdl">',
			'			<a href="' + Global.Domain.help + '" target="_blank">帮助中心</a>',
			'		</li>',
			'		<li class="bdl">',
			'			<a href="' + Global.Domain.feedback + '" target="_blank">意见反馈</a>',
			'		</li>',
			'	</ul>',
			'</div>'
		].join(""),

		// 吊顶登录后显示节点
		logined: [
			'<li class="self">',
			'    你好,<span id="topUser" class="username"></span>',
			'    <a class="logout" href="userLogin.jsp">退出</a>',
			'</li>',
			'<li>',
			'    <a href="' + Global.Domain.uc + '">我的蓝桥支付</a>',
			'</li>'
		].join(""),

		// 吊顶未登录显示节点
		unlogin: [
			'<li class="self">欢迎使用蓝桥支付服务平台</li>',
			'<li>',
			'    <a href=" userLogin.jsp " target="_blank">登录</a>',
			'    /',
			'    <a href="userController/register" target="_blank">注册</a>',
			'</li>'
		].join("")

	};

	/**
	 * 初始化网站吊顶,并根据当前用户是否登录在吊顶中显示相应信息
	 * 未登录: 显示欢迎信息及登录/注册链接
	 * 已登录: 显示登录信息及退出链接
	 */
	G.Header.init = function() {
		// 添加网站头部吊顶信息
		// 所有蓝桥银行页面统一添加
		$("#top").empty().append(topEle['top']);

		// 注销异步加载获取登录信息
			// 加载当前登录用户的服务器地址
				var url = "userController/isLogin";
				//发送请求参数
				var args = {
					"time" : new Date()
				};
				//发送请求到页面
				$.post(url, args, function(data) {
					if (data == '') {
						// 未登录或登录超时的用户显示欢迎词和登录/注册链接
						//$("#topItem").prepend(topEle['unlogin']);
					} else {
						// 已登录的用户显示用户名称+[我的蓝桥银行]链接
						$("#topItem").prepend(topEle['logined']);
						$("#topUser").text(data);
					}
				});
	};

}(Global, jQuery);

/**
 * 蓝桥银行底部管理组件
 *
 * @Author zbq
 * @Date 2015-02-26
 */
! function(G, $) {

	/**
	 * 蓝桥银行底部节点
	 */
	var bottomEle = [
		'<div class="layout">',
		'	<ul class="clearfix">',
		'		<li><a href="#" target="_blank">关于我们</a></li>',
		'		<li><a href="#" target="_blank">安全保障</a></li>',
		'		<li><a href="#" target="_blank">诚聘英才</a></li>',
		'		<li><a href="#" target="_blank">商户合作</a></li>',
		'		<li><a href="#" target="_blank">银行合作</a></li>',
		'		<li><a href="#" target="_blank">新手指南</a></li>',
		'		<li><a href="#" target="_blank">在线客服</a></li>',
		'		<li><a href="#" target="_blank">帮助中心</a></li>',
		'		<li><a href="#" target="_blank">站点地图</a></li>',
		'		<li class="last"><a href="#" target="_blank">联系我们</a></li>',
		'	</ul>',
		'	<p>工业和信息化部人才交流中心</p>',
		'	<p>蓝桥杯全国软件与信息技术专业人才大赛组委会</p>',
		'	<p>国信世纪人才服务（北京）有限公司</p>',
		'	<p>Copyright Reserved 2014©蓝桥',
		'		<a class="beian" href="http://www.miibeian.gov.cn" target="_blank">京ICP备 11017700号</a>',
		'	</p>',
		'</div>'
	].join("");

	/**
	 * 初始化网站底部信息
	 */
	G.Bottom.init = function() {
		$("#bottom").empty().append(bottomEle);
	};

}(Global, jQuery);

/**
 * 蓝桥银行页面布局插件
 *
 * @Author zbq
 * @Date 2015-02-26
 */
! function(G, $) {

	var winHeight,
		bodyHeight;

	/**
	 * 初始化网站布局
	 */
	G.Layout.init = function() {
		winHeight = $(window).height();
		bodyHeight = $("body").height();

		fillMainToScreen();
	};

	/**
	 * 填充屏幕
	 */
	var fillMainToScreen = function() {
		var diff = winHeight - bodyHeight,
			target = $("#page .col-main");
		if(diff > 0) {
			target = target.length ? target : $("#page .main");
			target.css({
				"min-height": diff + target.height()
			});
		}
	};

}(Global, jQuery);

/**
 * 加载全局通用插件
 * 1. Logo
 * 2. 导航
 */
$(function() {
	Global.Nav.init();
	Global.Header.init();
	Global.Bottom.init();
	Global.Layout.init();
});