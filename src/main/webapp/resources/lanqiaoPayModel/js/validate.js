// -----------------------------------------------------
// | 版权所有: 蓝桥 - 教研部                                                                                 |
// -----------------------------------------------------

/*
 * 表单验证
 *
 * 依赖animation.js
 *
 * @Author zbq
 * @Date 2016-09-19
 */
! function($) {

	/**
	 * 全局工具类
	 */
	var Util = window.Util;

	/**
	 * 验证函数入口
	 */
	$.extend($.fn, {
		validatex: function(rules, options) {
			return new Validator(this, rules, options);
		}
	});

	/**
	 * 验证对象
	 */
	var Validator = function(form, items, options) {
		this.curForm = form;
		this.props = items;
		this.configs = $.extend({
			debug: false,
			ajaxSubmit: ''
		}, options)

		var vd = this;

		// 初始化
		this.init();

		/**
		 * 绑定表单提交事件
		 */
		this.curForm.submit(function(event) {
			return vd.onsubmit(event);
		});

		/**
		 * 绑定输入框,下拉框,Textara的焦点事件
		 * 获取焦点时显示表单提示信息
		 */
		this.curForm.delegate(".ipt, .sel, .ta", "focusin", function(event) {
			vd.onfocusin(event.target);
		});

		/**
		 * 绑定输入框,下拉框,Textara的失去焦点事件
		 * 失去焦点时进行表单校验
		 */
		this.curForm.delegate(".ipt, .sel, .ta", "focusout", function(event) {
			vd.onfocusout(event.target);
		});

		/**
		 * 绑定checkbox,radio点击事件
		 * 点击时进行表单校验
		 */
		this.curForm.delegate(".cb", "click", function(event) {
			vd.onclick(event.target);
		});

	};

	/**
	 * 定义表单校验对象
	 */
	Validator.prototype = {

		/**
		 * 初始化验证对象
		 */
		init: function(props, configs) {
			this.cachedDOM = {};
			this.validedList = [];
			this.waitingList = [];
			this.errorMap = {};

			// Ajax验证完成后根据此状态判断是否提交表单
			// 当提交时有Ajax未完成的验证时为True
			this.goSubmit = false;

			// 初始化表单
			this.initFormTipAndcache();
		},

		/**
		 * 初始化表单提示信息并缓存表单相关节点
		 */
		initFormTipAndcache: function() {
			// 遍历所有设置了验证信息的表单项
			// 缓存表单, 添加表单信息节点, 设置必须输入标识
			for (prop in this.props) {
				var cachedForm = this.cacheAndGetFormEle(this.toJQ(prop)),
					extFormTip = cachedForm.formEnty.find(".form-tips");

				// 添加表单初始化标识(初次显示提示信息时使用)
				cachedForm.formItem.addClass("form-item-init");
				// 设置表单提示信息(如不存在自动添加)
				cachedForm.formTips = extFormTip.length ? extFormTip : this.loadEle('formTip');
				if (!extFormTip.length) {
					cachedForm.formTips.appendTo(cachedForm.formEnty);
					// 添加必须输入标识
					if (this.props[prop].rule.required) {
						cachedForm.formItem.find(".form-label").prepend(this.loadEle('requiredSign'));
					}
				}
			}
		},

		/**
		 * 缓存表单DOM节点
		 */
		cacheAndGetFormEle: function(ele) {
			var formColGroup = ele.parents(".form-col-group"),
				formItem = (formColGroup.length ? formColGroup : ele.parents(".form-item")),
				formEnty = ele.parents(".form-entity");

			return this.cachedDOM[ele.attr("name")] = {
				formItem: formItem,
				formEnty: formEnty,
				initValue: ele.val()
			};
		},

		/**
		 * 表单提交事件, 校验通过后提交表单
		 *
		 * @param event
		 */
		onsubmit: function(event) {
			// 进行验证
			this.checkAll();

			// 出现错误跳转至第一个错误表单
			if (Util.getSize(this.errorMap) > 0) {
				this.focusFirstError();
				return false;
			}

			// 正在验证的,不提交表单
			if (this.waitingList.length > 0) {
				this.goSubmit = true;
				return false;
			}


			// 调试模式不提交表单
			if (this.configs.debug) {
				return false;
			}

			// Ajax请求不提交表单
			// 调用回调函数执行
			if (this.configs.ajaxSubmit) {
				this.configs.ajaxSubmit();
				return false;
			}

			return true;
		},

		/**
		 * 表单获取焦点时, 隐藏验证信息并显示提示信息
		 *
		 * @param ele 当前获取焦点的DOM元素
		 */
		onfocusin: function(ele) {
			this.lastEleVal = ele.value;
			if (this.props[ele.name]) {
				this.showFormTip(ele, this.props[ele.name].tip, "focus");
			}
		},

		/**
		 * 表单失去焦点时, 隐藏表单提示信息并进行验证
		 * 输入框,下拉框,Textarea需要在失去焦点时验证
		 * 当表单未配置验证信息不进行校验
		 * 当表单元素为日期时,需要延时校验(日期插件对日期赋值需要时间)
		 *
		 * @param ele 当前失去焦点的DOM元素
		 */
		onfocusout: function(ele) {
			var vd = this,
				trimed = $.trim($(ele).val()),
				timeout = $(ele).hasClass("ipt-date") ? 200 : 0;

			// 去除左右空格
			$(ele).val(trimed);

			// 校验
			window.setTimeout(function() {
				vd.props[ele.name] && vd.check(ele);
			}, timeout);

		},

		/**
		 * 点击表单时, 隐藏表单提示信息并进行验证
		 * checkbox,radio需要在点击时验证.
		 * 当表单未配置验证信息不进行校验
		 *
		 * @param ele 当前被点击的DOM元素
		 */
		onclick: function(ele) {
			this.props[ele.name] && this.check(ele);
		},

		/**
		 * 验证表单元素,根据条件判断是否需要验证.
		 * 已忽略的表单清除验证信息
		 * 已验证过并且未修改内容时不重复验证, 只显示提示信息
		 *
		 * @param ele 需要被校验的DOM元素
		 * @param force 强制校验, 已校验过也重新校验
		 */
		check: function(ele, force) {

			// 忽略验证
			if (this.isIgnore(ele)) {
				this.clearValid(ele);
				return;
			}

			// 不需要再次验证
			if (this.needNotCheck(ele, force)) {
				this.showValidTip(ele);
				return;
			}

			this.cachedDOM[ele.name].lastVal = ele.value;
			this.isValided(ele) || this.validedList.push(ele.name);

			// 开始校验
			var isCorrect = this.checkEle(ele);

			// 验证成功, 清除错误缓存
			if (isCorrect) {
				this.removeErroredItem(ele);
			}

			// 显示验证提示信息
			this.showValidTip(ele);

			// 有Ajax校验,在Ajax校验完成后执行
			// 无Ajax时执行回调函数
			if (!this.isWaiting(ele)) {
				isCorrect ? this.doSuccess(ele) : this.doFailed(ele);
			}

		},

		/**
		 * 根据验证规则判断表单元素是否正确
		 *
		 * @param ele 需要被校验的DOM元素
		 * @return 验证通过返回true
		 */
		checkEle: function(ele) {
			var validRule = this.props[ele.name].rule;

			for (prop in validRule) {
				var rules = validRule[prop],
					param = (rules.length > 1) ? rules[1] : "",
					conds = (rules.length > 2) ? rules[2] : "";

				// 函数验证或正则表达式验证
				var isFunc = $.isFunction(Validator.methods[prop] || param),
					isValid = isFunc ? this.funcCheck(ele, prop, param, conds) : this.regexCheck(ele, prop, param);
				// 验证失败
				if (!isValid) {
					this.errorMap[ele.name] = rules[0];
					return false;
				}
			}

			return true;
		},

		/**
		 * 验证全部表单
		 */
		checkAll: function() {
			for (prop in this.props) {
				var ele = this.toDOM(prop);
				ele && this.check(ele);
			}
		},

		/**
		 * 调用验证函数判断元素是否合法
		 *
		 * @param ele 验证元素,DOM对象
		 * @param prop 验证方法
		 * @param param 验证规则参数
		 * @param conds 其余参数
		 * @return 验证通过返回true
		 */
		funcCheck: function(ele, prop, param, conds) {
			var method = Validator.methods[prop] || param,
				noneYet = ($.inArray(prop, Validator.noneYetCheck) == -1),
				funcYet = (noneYet && this.optional(ele));

			// 绑定联动校验
			// 关联表单变化时重新校验当前表单
			this.isLinkedCheck(prop) && this.bindLinkedCheck(ele, prop, param);

			// 无校验方法时使用自定义校验函数
			// 允许空值校验的方法或值不为空时进行函数校验
			return funcYet || (method && method.call(this, ele, param, conds));
		},

		/**
		 * 使用正则表达式验证函数是否合法
		 *
		 * @param ele 验证元素,DOM对象
		 * @param prop 正则表达式名称
		 * @param param 验证参数,无prop方法时使用param作为正则验证
		 * @return 验证通过返回true
		 */
		regexCheck: function(ele, prop, param) {
			var method = Validator.methods[prop] || param;
			return this.optional(ele) || method.test(ele.value);
		},

		/**
		 * 显示验证提示信息(正确,错误,等待)
		 *
		 * @param ele 显示信息的DOM对象
		 */
		showValidTip: function(ele) {
			var msg, css;

			if (this.isWaiting(ele)) {
				msg = "",
				css = "await";
			} else if (this.isError(ele)) {
				msg = this.errorMap[ele.name],
				css = "error";
			} else {
				msg = "",
				css = "valid";
			}

			this.showFormTip(ele, msg, css);
		},

		/**
		 * 显示表单提示信息(提示,正确,错误)
		 *
		 * @param ele 显示信息的DOM对象
		 * @param tip 提示信息文案
		 * @param style 表单样式
		 */
		showFormTip: function(ele, tip, style) {
			var cachedForm = this.cachedDOM[ele.name],
				formTips = cachedForm.formTips,
				formItem = cachedForm.formItem,
				formUiTips = this.loadEle('uiTip');

			// 清空原有提示信息文字和样式及表单样式
			this.clearFormTip(ele);

			// 设置表单样式
			$(ele).addClass("form-ele-" + style);

			// 清空原有提示信息及样式
			// 设置提示信息文案
			formTips.addClass("form-tips-" + style);
			formUiTips.appendTo(formTips).find(".tip").html(tip);

			// 初次使用动画显示提示信息
			if (formItem.hasClass("form-item-init")) {
				formItem.removeClass("form-item-init")
				formUiTips.showEleFlyToLeft();
			}

		},

		/**
		 * 清除表单提示信息
		 * >清空原有提示信息及样式
		 * >清空表单样式
		 *
		 * @param ele 元素DOM对象
		 */
		clearFormTip: function(ele) {
			$(ele).removeClass(Validator.eleClass);
			this.cachedDOM[ele.name].formTips.empty().removeClass(Validator.tipClass);
		},

		/**
		 * 绑定联动校验
		 * 关联表单变化时需要重新校验当前表单
		 *
		 * @param ele 验证元素,DOM对象
		 * @param prop 验证方法
		 * @param selector 关联表单选择器
		 */
		bindLinkedCheck: function(ele, prop, selector) {
			var vd = this,
				eventType = this.checkable(ele) ? "click" : "change";

			// 绑定依赖表单的变化事件
			$(selector).off().on(eventType, function() {
				vd.check(ele, true);
			});
		},

		/**
		 * 验证成功后操作
		 *
		 * @param ele 元素DOM对象
		 */
		doSuccess: function(ele) {
			this.doSuccessCallback(ele);
		},

		/**
		 * 验证成功后操作
		 *
		 * @param ele 元素DOM对象
		 */
		doFailed: function(ele) {
			this.doFailedCallback(ele);
		},

		/**
		 * 验证成功后执行回调函数
		 *
		 * @param ele 元素DOM对象
		 */
		doSuccessCallback: function(ele) {
			this.props[ele.name].success && this.props[ele.name].success();
		},

		/**
		 * 验证失败后执行回调函数
		 *
		 * @param ele 元素DOM对象
		 */
		doFailedCallback: function(ele) {
			this.props[ele.name].failed && this.props[ele.name].failed();
		},

		/**
		 * 设置焦点在第一个出错的元素
		 */
		focusFirstError: function() {},

		/**
		 * 远程(ajax)校验时,服务返回成功后处理
		 * 服务器返回成功说明连接成功,是否验证通过需要根据返回内容判断
		 * > 校验成功,删除错误缓存并系那是成功提示信息
		 * > 校验失败,将错误信息添加错误缓存并显示失败提示信息
		 *
		 * @param ele 显示信息的DOM对象
		 * @param result 服务器返回结果
		 */
		onAjaxSuccess: function(ele, result) {
			if (this.isIgnore(ele)) {
				return;
			}

			if (result != '0' && result !== 'true') {
				this.errorMap[ele.name] = this.props[ele.name].rule.remote[0];
				this.doFailed(ele)
			} else {
				this.removeErroredItem(ele);
				this.doSuccess(ele)
			}

			this.removeWaitingItem(ele);
			this.showValidTip(ele);

			// 如果在等待Ajax提交, Ajax处理完成后提交
			if (this.goSubmit && this.waitingList.length == 0) {
				var vd = this;
				window.setTimeout(function() {
					vd.curForm.submit();
				}, 500);
			}
		},

		/**
		 * 远程(ajax)校验时,服务返回失败处理
		 * 服务器返回失败说明服务器连接出现异常,设置异常提示信息并显示
		 *
		 * @param ele 显示信息的DOM对象
		 */
		onAjaxError: function(ele) {
			if (this.isIgnore(ele)) {
				return;
			}
			this.removeWaitingItem(ele);
			this.errorMap[ele.name] = "系统繁忙,请稍后再试";
			this.showValidTip(ele);
			this.doFailed(ele)
		},

		/**
		 * 判断元素是否已被验证,已验证的元素保存至validedList
		 *
		 * @param ele 被验证的DOM对象
		 * @return 已验证返回true
		 */
		isValided: function(ele) {
			return $.inArray(ele.name, this.validedList) > -1;
		},

		/**
		 * 判断元素是否不需要验证, 当元素存在某一样式时不需要验证
		 *
		 * @param ele 被验证的DOM对象
		 * @return 已忽略返回true
		 */
		isIgnore: function(ele) {
			return $(ele).hasClass(Validator.ignoreClass);
		},

		/**
		 * 判断元素是否验证是否在等待队列中
		 * Ajax校验在服务器未返回时将元素放入等待列表中
		 *
		 * @param ele DOM对象
		 * @return 在等待队列中返回true
		 */
		isWaiting: function(ele) {
			return $.inArray(ele.name, this.waitingList) > -1;
		},

		/**
		 * 判断元素验证是否出现错误
		 *
		 * @param ele DOM对象
		 * @return 验证错误返回true
		 */
		isError: function(ele) {
			return ele.name in this.errorMap;
		},

		/**
		 * 是否需要关联校验
		 * 关联校验: 该校验依赖于其他表单, 当依赖表单变化时, 需要重新校验本表单
		 *
		 * @param method 校验方法名称
		 */
		isLinkedCheck: function(method) {
			return $.inArray(method, Validator.linkedCheck) > -1;
		},

		/**
		 * 判断元素是否需要验证.
		 * >已验证过且值未改变时不需要验证
		 * >当前输入值和初始值相同时不需要验证
		 *
		 * @param ele 被验证的DOM对象
		 * @param force 强制校验, 已校验过也重新校验
		 * @return 不需要验证返回true
		 */
		needNotCheck: function(ele, force) {
			// 强制校验
			if (force) {
				return false;
			}

			var cachedForm = this.cachedDOM[ele.name],
				isInitVal = cachedForm.initValue && ele.value === cachedForm.initValue;

			// 页面初始值不进行校验
			if (isInitVal) {
				this.removeErroredItem(ele);
				return true;
			}

			// 已验证过且未改变值时不进行校验
			return this.isValided(ele) && (ele.value === cachedForm.lastVal);
		},

		/**
		 * 清空表单验证信息及缓存
		 *
		 * @param ele DOM对象
		 */
		clearValid: function(ele) {
			this.clearFormTip(ele);
			this.removeValidedItem(ele);
			this.removeWaitingItem(ele);
			this.removeErroredItem(ele);
		},

		/**
		 * 判断元素是否是checkbox或radio
		 *
		 * @param ele DOM对象
		 * @return 元素为checkbox或radio时返回true
		 */
		checkable: function(ele) {
			return ele.type === "checkbox" || ele.type === "radio";
		},

		/**
		 * 判断元素是否需要判断
		 *
		 * @param ele DOM对象
		 * @return 长度为0时返回true
		 */
		optional: function(ele) {
			return this.getEleLength(ele) == 0
		},

		/**
		 * 取得元素的长度
		 * 输入框,下拉框,Textarea: 取得内容的字数
		 * checkbox,raido: 取得同组(name相同)中被选中的数量
		 *
		 * @param ele DOM对象
		 */
		getEleLength: function(ele) {
			if (this.checkable(ele)) {
				return this.toJQ(ele.name, ":checked").length;
			}
			return ele.value.length;
		},

		/**
		 * 加载元素的HTML
		 *
		 * @param key
		 */
		loadEle: function(key) {
			return $(Validator.tipsEle[key]);
		},

		/**
		 * 将元素从已验证的列表中移除
		 *
		 * @param ele 元素
		 */
		removeValidedItem: function(ele) {
			var index = $.inArray(ele.name, this.validedList);
			if (index > -1) {
				this.validedList.splice(index, 1);
			}
		},

		/**
		 * 将元素从等待列表中移除
		 *
		 * @param eleName 元素
		 */
		removeWaitingItem: function(ele) {
			var index = $.inArray(ele.name, this.waitingList);
			if (index > -1) {
				this.waitingList.splice(index, 1);
			}
		},

		/**
		 * 将元素从错误列表中移除
		 *
		 * @param ele 元素
		 */
		removeErroredItem: function(ele) {
			delete this.errorMap[ele.name];
		},

		/**
		 * 取得元素的name属性
		 *
		 * @param eleName 元素名称
		 */
		getName: function(ele) {
			return ele.name.substring(ele.name.lastIndexOf(".") + 1);
		},

		/**
		 * 根据name及过滤条件查找DOM,并转换为jQuery对象
		 *
		 * @param name 属性
		 * @param filter 过滤器
		 * @return 元素的jQuery对象
		 */
		toJQ: function(name, filter) {
			return this.curForm.find("[name='" + name + "']" + (filter || ""));
		},

		/**
		 * 根据name查找对应的DOM元素
		 *
		 * @param name 属性
		 * @param filter 过滤器
		 * @return 元素的DOM对象
		 */
		toDOM: function(name) {
			return document.getElementsByName(name)[0];
		}

	};

	/**
	 * HTML
	 */
	Validator.tipsEle = {

		requiredSign: [
			'<em>*</em>'
		].join(""),

		formTip: [
			'<div class="form-tips"></div>'
		].join(""),

		uiTip: [
			'<div class="form-ui-tips">',
			'<i class="drt"></i><p class="tip"></p>',
			'</div>'
		].join("")

	};

	/**
	 * 表单提示信息样式
	 */
	Validator.tipClass = [
		'form-tips-focus',
		'form-tips-error',
		'form-tips-await',
		'form-tips-valid'
	].join(" ");

	/**
	 * 表单校验结果样式
	 */
	Validator.eleClass = [
		'form-ele-focus',
		'form-ele-error',
		'form-ele-await',
		'form-ele-valid'
	].join(" ");

	/**
	 * 忽略校验表单样式
	 */
	Validator.ignoreClass = [
		'form-ele-ignore',
	].join(" ");



	/**
	 * 字段值为空时也需要校验的方法名称
	 */
	Validator.noneYetCheck = [
		'required',
		'equalTo',
		'unequalTo'
	];

	/**
	 * 需要关联校验的方法名称
	 */
	Validator.linkedCheck = [
		'equalTo',
		'unequalTo',
		'maxTo',
		'minTo',
		'greater',
		'less'
	];

	/**
	 * 验证方法
	 */
	Validator.methods = {

		// 邮箱
		email: /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/,

		// 座机(0431-81880088)
		tel: /^(\d{3,4}-)\d{7,8}(-\d{1,6})?$/,

		// 手机(17012345678)
		mobile: /^1[34578]\d{9}$/,

		// 日期(2014年10月15日,2015-01-03)
		date: /^\d{4}\-[01]?\d\-[0-3]?\d$|^[01]\d\/[0-3]\d\/\d{4}$|^\d{4}年[01]?\d月[0-3]?\d[日号]$/,

		// 正整数(1+)
		integer: /^[1-9][0-9]*$/,

		// 整数(包括0)
		digits: /^[0-9]+$/,

		// 数字(任意数字包含小数点,不限小数点位数)
		number: /^[+-]?[1-9][0-9]*(\.[0-9]+)?([eE][+-][1-9][0-9]*)?$|^[+-]?0?\.[0-9]+([eE][+-][1-9][0-9]*)?$/,

		// 金钱(2位小数点)
		money: /^\d+(\.\d{0,2})?$/,

		// 大小写字母
		alpha: /^[a-zA-Z]+$/,

		// 大小写字母和数字
		alphaNum: /^[a-zA-Z0-9]+$/,

		// 大小写字母,数字,"-","_"
		betaNum: /^[a-zA-Z0-9-_]+$/,

		// 数字和"-"
		numStr: /^[0-9-]+$/,

		// 身份证(15和18位,默认可为X)
		IDcard: /^\d{15}$|^\d{17}[0-9xX]$/,

		// ip地址
		ip: /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/,

		// http和https地址
		http: /^(http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?$/,

		// 浏览器协议
		url: /^(https?|s?ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i,

		// 汉字
		chinese: /^[\u2E80-\uFE4F]+$/,

		// 邮编
		postal: /^[0-9]{6}$/,

		// 组织机构代码证(8位数字或大写字母 + "-" + 1位数字或大写字母, A1234567-6)
		orgNo: /^[a-zA-Z0-9]{8}[-]?[a-zA-Z0-9]{1}$/,

		// 营业执照(15位数字)
		licenseNo: /^\d{15}$/,

		// 必须输入
		required: function(ele) {
			return this.getEleLength(ele) > 0;
		},

		// 必须输入(关联其他表单)
		requiredTo: function(ele, selector) {
			// TODO
		},

		// ajax验证
		remote: function(ele, url, param) {
			var vd = this,
				data = {};

			this.waitingList.push(ele.name);
			this.validedList.push(ele.name);

			// Ajax参数
			// 当前DOM参数+其他关联参数
			data[this.getName(ele)] = ele.value;
			if (param) {
				for (i in param) {
					var pd = param[i];
					data[pd] = this.toJQ(pd).val()
				}
			}

			// 显示等待提示
			this.showValidTip(ele);

			// Ajax校验
			$.get(url, data).done(function(result) {
				vd.onAjaxSuccess(ele, result);
			}).fail(function() {
				vd.onAjaxError(ele);
			});

			return true;
		},

		// 长度验证
		length: function(ele, param) {
			var length = this.getEleLength(ele),
				larray = param.split("-");
			if (larray.length == 1) {
				return length == larray[0];
			}
			return (length >= larray[0] && length <= larray[1]);
		},

		// 与另一元素内容相等
		equalTo: function(ele, selector) {
			return ele.value === $(selector).val();
		},

		// 与另一元素内容不相等
		unequalTo: function(ele, selector) {
			return ele.value !== $(selector).val();
		},

		// 大于另一元素
		maxTo: function(ele, selector) {
			return ele.value * 1 > $(selector).val() * 1;
		},

		// 小于另一元素
		minTo: function(ele, selector) {
			return ele.value * 1 < $(selector).val() * 1;
		},

		// 大于等于另一元素
		greater: function(ele, selector) {
			return ele.value * 1 >= $(selector).val() * 1;
		},

		// 小于等于另一元素
		less: function(ele, selector) {
			return ele.value * 1 <= $(selector).val() * 1;
		}

	};


}(jQuery);