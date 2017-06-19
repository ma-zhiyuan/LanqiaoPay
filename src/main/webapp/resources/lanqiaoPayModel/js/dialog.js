// -----------------------------------------------------
// | 版权所有: 蓝桥 - 教研部                                                                                 |
// -----------------------------------------------------

/**
 * 弹出对话框插件
 *
 * @Author zbq
 * @Date 2016-09-19
 */
! function($) {

	window.Dialogx = window.Dialogx || {};

	/**
	 * 对话框对外访问接口
	 */
	Dialogx.show = function(options) {
		return new Dialogor(options);
	}

	/**
	 * 对话框对象
	 */
	var Dialogor = function(options) {
		var dg = this;

		// 合并提交参数
		dg.opts = $.extend(defaults = {
			_url: '',
			_title: '',
			_rules: '',
			_callback: '',
			_success: '',
			_failed: ''
		}, options);

		// 如果有提示框存在不显示新提示框
		if ($(".dialog-box").length > 0) {
			return false;
		}

		// 初始化body, 遮罩层,加载图标,对话框
		dg._body = $("body"),
		dg._dialog = $(Dialogor.Elms['dialog']),
		dg._overlay = $(Dialogor.Elms['overlay']),
		dg._loading = $(Dialogor.Elms['loading']);

		// 显示遮罩层,等待图标
		dg.showDialogLoading();

		// 异步获取对话框页面内容
		// 成功后显示对话框
		$.ajax(dg.opts._url).done(function(data) {

			dg.showDialog(data);

		}).fail(function() {});

	};

	/**
	 * 对话框方法
	 */
	Dialogor.prototype = {

		/**
		 * 显示等待图标
		 */
		showDialogLoading: function() {
			this._loading.appendTo(this._body).showEleToCenter();
			this._overlay.appendTo(this._body).showEleToCenter(".1");
		},

		/**
		 * 显示对话框
		 */
		showDialog: function(data) {
			// 删除等待图标
			this._loading.removeEleWithFade();

			// 设置对话框基本信息
			this._dialog.find(".title").text(this.opts._title);
			this._dialog.find(".dialog-bd").append(data);

			// 显示对话框
			this._dialog.appendTo(this._body).showEleToMiddle();

			// 绑定用户自定义事件
			this.bindHandleEvent();

			// 绑定关闭对话框事件
			this.bindCloseEvent();

			// 绑定提交事件
			this.bindSubmitEvent();

			// 绑定表单校验
			this.bindFormValidate();

		},

		/**
		 * 关闭对话框
		 */
		closeDialog: function() {
			this._dialog.removeEleWithAnim();
			this._overlay.removeEleWithFade();
		},

		/**
		 * 绑定关闭对话框按钮点击,关闭对话框
		 */
		bindCloseEvent: function() {
			var dg = this;
			this._dialog.find(".close-dlg").off().on("click", function() {
				dg.closeDialog();
			});
		},

		/**
		 * 绑定对话框表单提交事件
		 */
		bindSubmitEvent: function() {
			var dg = this;
			// 绑定表单提交. 执行回调函数, 刷新页面
			this._dialog.off().on("submit", "form", function() {
				dg.submitDialogForm($(this), dg);
				return false;
			});
		},

		/**
		 * 对话框弹出前执行回调函数
		 */
		bindHandleEvent: function() {
			this.opts._callback && this.opts._callback(this);
		},

		/**
		 * 绑定对话框表单验证
		 */
		bindFormValidate: function() {
			this.opts._rules && this._dialog.find("form").validatex(this.opts._rules);
		},

		/**
		 * Ajax提交对话框表单. 提交地址为form的action属性, 提交后显示提示信息
		 */
		submitDialogForm: function(form, dg) {

			var dialogBd = dg._dialog.find(".dialog-bd"),
				formLoading = $(Dialogor.Elms['submitLoading']),
				formOverlay = $(Dialogor.Elms['submitOverlay']),
				doneTip = $(Dialogor.Elms['doneTip']);

			// 对话框表单提交地址及数据
			var postUrl = form.attr("action"),
				postData = form.serialize();

			// 添加对话框内遮罩,等待图标
			formOverlay.appendTo(dialogBd).showEleInCenter(dialogBd, ".1");
			formLoading.appendTo(dialogBd).showEleInCenter(dialogBd);

			// Ajax提交
			$.post(postUrl, postData).done(function() {
				// 删除等待图标,添加成功提示信息
				formLoading.removeEleWithFade(function() {
					doneTip.appendTo(dialogBd).showEleInCenter(dialogBd);
				});

				// 2秒后关闭对话框执行回调函数并刷新页面
				window.setTimeout(function() {
					dg.closeDialog();
					(dg.opts._success && dg.opts._success()) || location.reload();
				}, 2000);

			}).fail(function() {
				// 提交失败时
				var failTip = $(Dialogor.Elms['failTip']);

				// 删除等待图标,添加失败提示信息
				formLoading.removeEleWithFade(function() {
					failTip.appendTo(dialogBd).showEleInCenter(dialogBd);
				});

				// 3秒后关闭对话框刷新页面
				window.setTimeout(function() {
					failTip.removeEleWithFade();
					dg.opts._failed && dg.opts._failed();
					dg.closeDialog();
				}, 2800);

			});

		}

	};

	// 对话框异步DOM
	Dialogor.Elms = {

		// 屏幕遮罩层
		overlay: [
			'<div class="overlay win-overlay"></div>'
		].join(""),

		// 加载对话框时等待图标
		loading: [
			'<div class="loading win-loading"></div>'
		].join(""),

		// 对话框提交时等待图标
		submitLoading: [
			'<div class="loading dlg-loading"></div>'
		].join(""),

		// 对话框遮罩层
		submitOverlay: [
			'<div class="overlay dlg-overlay"></div>'
		].join(""),

		// 对话框
		dialog: [
			'<div class="dialog-box">',
			'    <div class="dialog-wrap">',
			'        <div class="dialog-hd">',
			'            <h3 class="title"></h3>',
			'            <a class="iconfont icon-close close-dlg" href="javascript:;"></a>',
			'        </div>',
			'        <div class="dialog-bd clearfix"></div>',
			'    </div>',
			'</div>'
		].join(""),

		// 提交成功提示信息
		doneTip: [
			'<div class="dlg-submit-tip dlg-succ-tip">',
			'    <h3>恭喜您,操作成功!</h3>',
			'</div>'
		].join(""),

		// 提交失败提示信息
		failTip: [
			'<div class="dlg-submit-tip dlg-fail-tip">',
			'    <h3>系统繁忙,请稍后再试!</h3>',
			'</div>'
		].join("")

	};


}(jQuery);