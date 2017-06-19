// -----------------------------------------------------
// | 版权所有: 蓝桥 - 教研部                                                                                 |
// -----------------------------------------------------

/**
 * 弹出提示框插件
 * 使用方法:
 * 提示框, Hint.alert("提示信息", "提示内容", function(){"确认回调函数"})
 * 确认框, Hint.alert("确认信息", "确认内容", function(){"确认回调函数"}, function(){"取消回调函数"})
 *
 * @Author zbq
 * @Date 2016-09-19
 */
! function($) {

	window.Hint = window.Hint || {};

	/**
	 * 提示框对外访问接口
	 */
	Hint.alert = function(title, msg, onCancel) {
		return new Hinter({
			_title: title,
			_msg: msg,
			_onCancel: onCancel
		});
	};

	/**
	 * 确认框对外访问接口
	 */
	Hint.confirm = function(title, msg, onConfirm, onCancel) {
		return new Hinter({
			_mode: 'confirm',
			_title: title,
			_msg: msg,
			_onConfirm: onConfirm,
			_onCancel: onCancel
		});
	};

	/**
	 * 提示框对象
	 */
	var Hinter = function(options) {

		// 合并提交参数
		this.opts = $.extend(defaults = {
			_mode: 'alert',
			_title: '',
			_msg: '',
			_onConfirm: '',
			_onCancel: ''
		}, options);

		var extHint = $(".hint-box");
		// 如果有提示框存在不显示新提示框
		if (extHint.length && !extHint.hasClass("removing")) {
			return false;
		}

		// 初始化body, 遮罩层, 提示框
		this._body = $("body"),
		this._hinter = $(Hinter.Elms['hinter']),
		this._overlay = $(Hinter.Elms['overlay']);

		this.showHint();

	};

	/**
	 * 提示框方法
	 */
	Hinter.prototype = {

		/**
		 * 显示提示框
		 */
		showHint: function() {
			// 显示遮罩层
			this._overlay.appendTo(this._body).showEleToCenter(".1");

			// 显示提示框并设置基本信息
			this._hinter.find(".hint-msg").text(this.opts._msg);
			this._hinter.find(".hint-title").text(this.opts._title);
			this._hinter.find(".hint-action").append(Hinter.Elms[this.opts._mode + 'Btn']);
			this._hinter.appendTo(this._body).showEleToMiddle();

			// 绑定提示框事件
			this.bindHandleEvent();
		},

		/**
		 * 绑定处理事件
		 * > 遮罩层点击关闭
		 * > 点击关闭按钮关闭
		 * > 点击确认按钮执行自定义函数
		 */
		bindHandleEvent: function() {
			this.bindOverlayEvent();
			this.bindCloseEvent();
			this.opts._mode == 'alert' && this.bindAutoCloseEvent();
			this.opts._mode == 'confirm' && this.bindConfirmEvent();
		},

		/**
		 * 绑定点击遮罩层事件,点击关闭提示框
		 */
		bindOverlayEvent: function() {
			var hint = this;

			hint._overlay.off().on("click", function() {
				hint.cancelHint();
			});
		},

		/**
		 * 绑定关闭按钮点击事件,点击关闭提示框
		 */
		bindCloseEvent: function() {
			var hint = this;

			// 点击遮罩层
			hint._hinter.find("#cancelHintBtn").off().on("click", function() {
				hint.cancelHint();
			});
			// 点击关闭按钮
			hint._hinter.find(".hint-close").off().on("click", function() {
				hint.cancelHint();
			});
		},

		/**
		 * 绑定点击确认按钮事件, 点击执行自定义函数
		 */
		bindConfirmEvent: function() {
			var hint = this;

			hint._hinter.find("#confirmHintBtn").off().on("click", function() {
				hint.closeHint();
				window.setTimeout(function() {
					hint.opts._onConfirm && hint.opts._onConfirm(this);
				}, 30);

			});
		},

		/**
		 * 绑定自动关闭事件
		 */
		bindAutoCloseEvent: function() {
			var hint = this;
			window.setTimeout(function() {
				hint.cancelHint();
			}, 5000);
		},

		/**
		 * 取消提示框
		 */
		cancelHint: function() {
			var hint = this;

			hint.closeHint();
			window.setTimeout(function() {
				hint.opts._onCancel && hint.opts._onCancel(this);
			}, 30);
		},

		/**
		 * 关闭提示框
		 */
		closeHint: function() {
			this._hinter.addClass("removing").removeEleWithAnim();
			this._overlay.addClass("removing").removeEleWithFade();
		}

	};

	// 提示框DOM
	Hinter.Elms = {

		// 屏幕遮罩层
		overlay: [
			'<div class="overlay hint-overlay"></div>'
		].join(""),

		// 提示框
		hinter: [
			'<div class="hint-box">',
			'    <div class="hint-wrap">',
			'        <div class="hint-hd">',
			'            <h3 class="hint-title"></h3>',
			'            <a href="javascript:;" class="hint-close">×</a>',
			'        </div>',
			'        <div class="hint-bd clearfix">',
			'        	<div class="hint-msg"></div>',
			'        	<div class="hint-action"></div>',
			'        </div>',
			'    </div>',
			'</div>'
		].join(""),

		// 提示框按钮
		alertBtn: [
			'<a id="cancelHintBtn" href="javascript:;" class="hint-btn hint-btn-em">确定</a>'
		].join(""),

		// 确认框按钮
		confirmBtn: [
			'<a id="cancelHintBtn" href="javascript:;" class="hint-btn">取消</a>',
			'<a id="confirmHintBtn" href="javascript:;" class="hint-btn hint-btn-em">确定</a>'
		].join("")

	};


}(jQuery);