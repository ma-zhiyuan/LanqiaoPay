/**
 * 弹出对话框插件
 *
 * @Author zbq
 * @Date 2016-09-19
 */
! function($) {

    window.Dialogx = window.Dialogx || {};

    /**
     * 系统提示框,等同于alert('提示信息');
     * 调用方式: Dialogx.alert('提示信息');
     *
     * @param 提示信息
     */
    Dialogx.alert = function(msg) {
        return this.confirm(msg);
    };

    /**
     * 系统确认框,等同于confirm('确认信息');
     * 区别在于confirm函数点击确定按钮时会返回true, 该函数不会返回结果, 需要传入点击确定按钮时执行的函数
     * 调用方式: Dialogx.confirm('提示信息', function());
     *
     * @param 确认内容
     * @param 点击确认按钮执行的操作
     */
    Dialogx.confirm = function(msg, callback) {
        var btn = callback ? 'confirmBtn' : 'alertBtn',
            title = callback ? '确认信息' : '系统提示',
            html = $(Dialogor.Elms['msgDialog']);

        html.find(".dialog-foot").append($(Dialogor.Elms[btn]));
        html.find(".dialog-title").html(title);
        html.find(".dialog-message").html(msg);

        return this.show({
            _html: html,
            _callback: function(dialog) {
                html.find(".dialog-confirm-btn").off().on("click", function() {
                    dialog.closeDialog();
                    callback && callback();
                });
            }
        });
    }

    /**
     * 对话框对外访问接口, 弹出对话框
     * 调用方式: Dialogx.show();
     */
    Dialogx.show = function(options) {
        return new Dialogor(options);
    }

    /**
     * 对话框对象
     */
    var Dialogor = function(options) {
        var t = this;

        // 合并提交参数
        t.opts = $.extend(defaults = {

            _url: '', // 通过该参数异步加载内容显示到对话框中(url和当前页为同域,不支持跨越)
            _html: '', // 对话框中HTML内容, 请勿同时传入_url, _eleId, _html参数
            _selector: '', // 元素选择器, 将选定元素内容作为对话框中的内容.例:#id, .class
            _rules: '', // 表单验证规则, 传入后在提交对话框表单时自动校验,验证通过后提交
            _callback: '', // 对话框弹出后执行该函数(会将对话框对象作为参数传入至该函数中)
            _success: '', // 提交成功时回调函数(可在该函数中自定义提交成功后操作)
            _failed: '', // 提交失败时回调函数(可在该函数中自定义提交失败后操作)
            _autoClose: true, // 提交成功时是否自动关闭对话框(默认关闭,提交失败不关闭)
            _refresh: !this._success // 提交成功时是否自动刷新页面(有成功回调函数时不刷新,提交失败不刷新)

        }, options);

        // 如果有提示框存在不显示新提示框
        if($(".dialog-panel").length > 0) {
            return false;
        }

        // 初始化body, 遮罩层,加载图标,对话框
        t._body = $("body");
        t._dialog = $(Dialogor.Elms['dialog']);
        t._overlay = $(Dialogor.Elms['overlay']);
        t._loading = $(Dialogor.Elms['loading']);
        t._formLoading = $(Dialogor.Elms['submitLoading']);
        t._formOverlay = $(Dialogor.Elms['submitOverlay']);

        // 显示遮罩层,加载图标
        t.showElement(t._overlay, t._body);
        t.showElement(t._loading, t._body);

        // 根据参数确定显示的对话框内容
        // 优先级: html > selector > url
        if(t.opts._html) {

            t.showDialog(t.opts._html);

        } else if(t.opts._selector) {

            t.showDialog($(t.opts._selector).html());

        } else if(t.opts._url) {

            // 通过URL获取对话框内容
            $.ajax(t.opts._url).done(function(data) {
                t.showDialog(data);
            }).fail(function() {
                console.log("error. url: " + t.opts._url);
            });

        } else {
            console.error('parameter (_html, _selector, _url) is empty');
        }

    };

    /**
     * 对话框方法
     */
    Dialogor.prototype = {

        /**
         * 显示对话框
         */
        showDialog: function(html) {
            var t = this;

            // 删除加载图标
            t.removeElement(t._loading);

            // 设置对话框内容并显示对话框
            t.showElement(t._dialog.append(html), t._body);

            // 绑定关闭对话框事件
            t.bindClickCloseBtn();

            // 绑定用户自定义事件
            t.bindHandleEvent();

            // 绑定提交事件
            t.bindSubmitEvent();

            // 绑定表单校验
            t.bindFormValidate();

        },

        /**
         * 绑定关闭对话框按钮点击,关闭对话框
         */
        bindClickCloseBtn: function() {
            var t = this;

            t._dialog.find(".dialog-close-btn").off().on("click", function() {
                t.closeDialog();
            });

        },

        /**
         * 关闭对话框(删除对话框和遮罩层)
         */
        closeDialog: function() {

            this.removeElement(this._dialog);
            this.removeElement(this._overlay);

        },

        /**
         * 对话框弹出时执行自定义回调函数
         */
        bindHandleEvent: function() {
            this.opts._callback && this.opts._callback(this);
        },

        /**
         * 绑定对话框表单提交事件
         * 当有对话框内的form提交时拦截, 通过异步提交后根据结果处理
         */
        bindSubmitEvent: function() {
            var t = this;

            // 绑定表单提交. 执行回调函数, 刷新页面
            this._dialog.off().on("submit", "form", function() {
                t.submitDialogForm($(this), t);
                return false;
            });

        },

        /**
         * 绑定对话框表单验证, 需要引入validate.js
         */
        bindFormValidate: function() {
            this.opts._rules && this._dialog.find("form").validate(this.opts._rules);
        },

        /**
         * Ajax提交对话框表单. 提交地址为form的action属性, 提交后显示提示信息
         *
         * @param form 被提交的表单
         * @param t 对话框对象
         */
        submitDialogForm: function(form, t) {

            var url = form.attr("action"),
                data = form.serialize();

            // 添加对话框内遮罩和加载图标
            t.addSubmitLoading();

            // Ajax提交
            // 地址为form的action属性, 内容为form的表单项
            $.post(url, data).done(function(data) {

                // 删除对话框内的加载图标和遮罩层
                t.removeSumbitLoading();
                // 显示成功提示信息
                t.showSubmitResultTip(data);
                // 执行用户自定义操作
                t.opts._success && t.opts._success(data);

                // 关闭对话框
                window.setTimeout(function() {
                    t.opts._autoClose && t.closeDialog();
                }, 1700);

                // 刷新页面
                window.setTimeout(function() {
                    t.opts._refresh && location.reload();
                }, 2000);

            }).fail(function() {

                // 删除加载图标和遮罩层
                t.removeSumbitLoading();
                // 显示失败提示信息
                t.showSubmitResultTip()
                    // 执行自定义回调函数
                t.opts._failed && t.opts._failed();

            });

        },

        /**
         * 对话框异步提交时显示对话框内遮罩层和加载图标
         */
        addSubmitLoading: function() {

            var dialogBox = this._dialog.find(".dialog-box");

            this.showElement(this._formOverlay, dialogBox);
            this.showElement(this._formLoading, dialogBox);

        },

        /**
         * 删除对话框内的加载图标和遮罩层
         */
        removeSumbitLoading: function() {

            this.removeElement(this._formLoading);
            this.removeElement(this._formOverlay);

        },

        /**
         * 在对话框内添加提示信息
         *
         * @param data ajax提交成功返回值(未传入代表失败的情况)
         */
        showSubmitResultTip: function(data) {

            var msg, style;
            var t = this,
                dialogHead = t._dialog.find(".dialog-head"),
                tip = $(Dialogor.Elms['resultTip']);

            // 成功
            if(data) {

                // 如果需要显示将提交返回结果(以message:开始)
                if(data.startsWith("message:")) {
                    msg = data.substring(8), style = "info";
                } else {
                    msg = "操作成功", style = "correct";
                }

            } else {
                msg = "系统繁忙,请稍后再试", style = "error";
            }

            tip.find(".dialog-result-msg").text(msg);
            tip.find(".dialog-result-ico").addClass("icon-" + style);
            tip.addClass("dialog-result-" + style);

            // 如果有提示信息, 先删除
            t._dialog.find(".dialog-result").remove();

            // 显示提示信息
            t.showElement(tip, dialogHead);

            // 延时删除对话框提示信息
            window.setTimeout(function() {
                t.removeElement(tip)
            }, 1500);

        },

        /**
         * 显示元素(使用动画效果)
         */
        showElement: function(ele, parent) {

            ele.appendTo(parent).show().addClass("dialog-show-on");

        },

        /**
         * 删除元素(使用动画效果)
         */
        removeElement: function(ele) {

            // 使用动画效果隐藏元素
            ele.removeClass("dialog-show-on");

            // 延时(动画结束后)删除元素
            window.setTimeout(function() {
                ele.remove();
            }, 200);

        }

    };

    // 对话框异步DOM
    Dialogor.Elms = {

        // 屏幕遮罩层
        overlay: [
            '<div class="dialog-overlay"></div>'
        ].join(""),

        // 加载对话框时等待图标
        loading: [
            '<div class="dialog-loading"></div>'
        ].join(""),

        // 对话框
        dialog: [
            '<div class="dialog-panel">',
            '</div>'
        ].join(""),

        // 对话框提交时等待图标
        submitLoading: [
            '<div class="dialog-loading dialog-loading-form"></div>'
        ].join(""),

        // 对话框遮罩层
        submitOverlay: [
            '<div class="dialog-overlay dialog-overlay-form"></div>'
        ].join(""),

        // 对话框
        msgDialog: [
            '<div class="dialog-box dialog-box-alert">',
            '	<div class="dialog-head">',
            '		<a class="dialog-close-btn" href="javascript:;">×</a>',
            '		<h3 class="dialog-title"></h3>',
            '	</div>',
            '	<div class="dialog-body">',
            '		<p class="dialog-message"></p>',
            '	</div>',
            '	<div class="dialog-foot">',
            '	</div>',
            '</div>'
        ].join(""),

        // 对话框按钮
        alertBtn: [
            '<a href="javascript:;" class="glb-button glb-btn-primary dialog-close-btn">确认</a>'
        ].join(""),

        // 对话框按钮
        confirmBtn: [
            '<a href="javascript:;" class="glb-button glb-btn-primary dialog-confirm-btn">确认</a>',
            '<a href="javascript:;" class="glb-button glb-btn-default dialog-close-btn">取消</a>',
        ].join(""),

        // 提交提示信息
        resultTip: [
            '<div class="dialog-result">',
            '	<span class="dialog-result-ico iconfont"></span>',
            '   <h6 class="dialog-result-msg"></h6>',
            '</div>'
        ].join("")

    };

}(jQuery);