/*
 * 表单验证
 *
 * @Author zbq
 * @Date 2015-02-26
 */
! function($) {

    // 绑定对外接口
    // 外部对象可直接调用
    $.extend($.fn, {

        /**
         * 外部调用的验证主方法
         *
         * @param rules
         *            验证规则
         * @param options
         *            配置参数
         */
        validate: function(rules, options) {
            this.each(function() {
                new Validator($(this), rules, options);
            });
            // return new Validator(this, rules, options);
        }

    });

    /**
     * 表单验证对象
     *
     * @param form 被验证的的表单
     * @param props 验证规则
     * @param options 配置参数
     */
    var Validator = function(form, props, options) {

        /**
         * 当前表单
         */
        this.curForm = form;

        /**
         * 验证规则
         * a. tip
         * b. rule
         * c. success
         * d. failed
         * e. force
         * f. initial,
         * g. show: 隐藏/显示表单条件
         */
        this.props = props;

        /**
         * 合并内外部参数
         */
        this.configs = $.extend({

            // 调试模式
            // 不提交表单
            debug: false,

            // Ajax提交表单时
            // 调用此函数提交
            ajaxSubmit: '',

            // 提交时校验出错
            // 调用此函数处理
            onError: '',

            // 提交时校验成功
            // 调用此函数处理
            onSuccess: ''

        }, options);

        /**
         * 当前对象 区分事件内this对象
         */
        var t = this;

        /**
         * 初始化
         */
        this.init();

        /**
         * 绑定表单提交事件
         */
        this.curForm.submit(function(e) {
            return t.onsubmit(e);
        });

        /**
         * 绑定输入框,下拉框,Textara的焦点事件.获取焦点时显示表单提示信息
         */
        this.curForm.on("focusin", ".form-ipt, .form-sel, .form-ta", function(e) {
            window.setTimeout(function() {
                t.onfocusin(e.target);
            }, 20);
        });

        /**
         * 绑定输入框,下拉框,Textara的失去焦点事件.失去焦点时进行表单校验
         */
        this.curForm.on("focusout", ".form-ipt, .form-sel, .form-ta", function(e) {
            window.setTimeout(function() {
                t.onfocusout(e.target);
            }, 20);
        });

        /**
         * 下拉框选中项改变时进行表单校验
         */
        this.curForm.on("change", ".form-sel, .form-hidden", function(e) {
            window.setTimeout(function() {
                t.onchange(e.target);
            }, 20);
        });

        /**
         * 绑定checkbox,radio点击事件.点击时进行表单校验
         */
        this.curForm.on("click", ".form-cb", function(e) {
            window.setTimeout(function() {
                t.onclick(e.target);
            }, 20);
        });

        /**
         * 手动触发校验事件,表单无法触发click,change等自动校验事件时可调用.trigger("check")触发校验
         */
        this.curForm.on("check", ".form-ipt, .form-sel, .form-ta, .form-cb, .form-sel, .form-hidden", function(e, interval) {
            window.setTimeout(function() {
                t.oncheck(e.target);
            }, (interval || 20));

        });

        /**
         * 富文本编辑器(ueditor)焦点获取与离开事件
         */
        this.curForm.find(".form-editor,.edui-default").each(function() {

            var id = $(this).attr("id"),
                ele = $("#" + id)[0],
                editor = UE.getEditor(id);

            editor && editor.on("focus", function() {

                window.setTimeout(function() {
                    t.onfocusin(ele);
                }, 20);

            });

            editor && editor.on("blur", function() {

                window.setTimeout(function() {
                    t.onfocusout(ele);
                }, 20);

            });
        });

    };

    /**
     * 定义表单校验内部方法
     */
    Validator.prototype = {

        /**
         * 初始化验证对象
         */
        init: function(props, configs) {

            this.cachedDOM = {}; // 待验证的表单DOM缓存
            this.errorMap = {}; // 验证失败的表单列表
            this.validedList = []; // 验证通过的表单列表
            this.waitingList = []; // 等待验证结果的表单列表

            // Ajax验证完成后根据此状态判断是否提交表单
            // 当提交时有Ajax未完成的验证时为True
            this.goSubmit = false;

            // 初始化表单
            this.initAndCacheFormTips();

            // 绑定关联表单显示状态
            this.bindLinkedDisplay();

            // 绑定关联校验表单
            this.bindLinkedCheck();

        },

        /**
         * 初始化表单提示信息并缓存表单相关节点.将需要校验的表单初始值和表单相应节点缓存
         */
        initAndCacheFormTips: function() {

            // 遍历需要验证的表单项
            for(prop in this.props) {

                var rules = this.props[prop].rule,
                    cachedForm = this.cacheAndGetFormDOM(prop),
                    existTips = cachedForm.formEnty.find(".form-tips");

                // 同一组内多个表单项只添加一次表单提示信息
                // 组内所有表单项验证结果都在该提示信息内显示
                if(existTips.length) {

                    cachedForm.formTips = existTips;

                } else {

                    // 添加表单提示信息
                    cachedForm.formTips = this.loadEle('formTips');
                    cachedForm.formTips.appendTo(cachedForm.formEnty);

                    // 添加必须输入标识
                    if(rules && rules.required) {
                        var sign = this.loadEle('requiredSign');
                        this.cachedDOM[prop].formEnty.prev().prepend(sign);
                    }

                }

            }

        },

        /**
         * 缓存需要被校验的表单项相关DOM节点信息
         *
         * @param name 元素名称
         * @return 该元素的缓存信息
         */
        cacheAndGetFormDOM: function(name) {
            var ele = this.toJQ(name);

            return this.cachedDOM[name] = {

                initValue: this.getValue(name), // 初始化值
                formEnty: ele.parents(".form-entity") // 表单父节点

            };
        },

        /**
         * 绑定关联校验表单.关联表单变化时重新校验当前表单
         */
        bindLinkedCheck: function() {

            // 遍历需要验证的表单项
            for(prop in this.props) {
                // 表单验证规则
                var ele = this.toDOM(prop),
                    rules = this.props[prop].rule;

                // 遍历表单所有验证规则
                for(field in rules) {

                    var param = (rules[field].length > 1) ? rules[field][1] : "";
                    this.isLinkedCheck(field) && this.bindEleLinkedCheck(ele, field, param);

                }

            }

        },

        /**
         * 绑定表单关联显示操作
         *
         * 有show参数则进行绑定操作, show: ["elename", 'value', "3"]
         * 参数1: 关联的表单名
         * 参数2: 关联类型: 'size':长度, 'value':内容
         * 参数3: 表单值或长度数值
         * 某一表单项设置中含有show时进行绑定, 当elename的表单值为3时, 显示表单项, 否则隐藏
         * 注: 'size'一般适用于chekbox的选中项
         */
        bindLinkedDisplay: function() {

            // 遍历需要关联显示的的表单项
            for(prop in this.props) {

                var ele = this.toDOM(prop),
                    show = this.props[prop].show;

                if(show) {

                    // 初始化设置是否显示
                    this.decideShow(ele, show);

                    // 绑定关联表单变化事
                    this.bindEleLinkedDisplay(ele, show);

                }

            }

        },

        /**
         * 表单提交事件, 校验通过后提交表单
         *
         * 拦截页面表单提交事件, 进行表单校验. 校验失败不提交表单显示错误信息, 校验成功进行如下处理: > 使用AJAX提交时,
         * 不提交表单调用传入的AJAX函数 > 非AJAX提交时, 直接表单(提交至Form的Action属性)
         *
         * @param event 表单提交事件
         * @return 验证通过提交表单时返回true, 不提交表单返回false
         */
        onsubmit: function(event) {

            var t = this;

            // 设置表单验证标记
            // 防止重复验证或提交
            if(t.isSubmiting()) {
                return false;
            }

            // 设置表单正在提交中
            t.setSubmiting();

            // 验证所有表单
            t.checkAll();

            // 有验证失败的表单项时不提交表单
            if(t.hasErrorEle()) {
                t.onFormValidError();
                t.removeSubmiting();
                return false;
            }

            // 有等待验证结果的表单项时不提交表单
            if(t.hasWaitingEle()) {
                t.goSubmit = true;
                return false;
            }

            // 执行用户自定义函数
            t.configs.onSuccess && t.configs.onSuccess();

            // 调试模式不提交表单
            if(t.configs.debug) {
                t.removeSubmiting();
                return false;
            }

            // Ajax请求不提交表单
            // 调用回调函数执行
            if(t.configs.ajaxData) {
                t.clearAllFormTip();
                $.ajax(t.configs.ajaxData(t.curForm)).always(function() {
                    t.removeSubmiting();
                });
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

            // 保存当前元素的值
            this.lastEleVal = this.getValue(ele);

            // 显示表单提示信息
            if(this.props[ele.name]) {
                this.createFormTipsAndShow(ele, this.props[ele.name].tip, "focus");
            }

        },

        /**
         * 输入框,Textarea失去焦点时, 隐藏表单提示信息并进行验证 当表单未配置验证信息不进行校验
         *
         * @param ele 当前失去焦点的DOM元素
         */
        onfocusout: function(ele) {

            var t = this,
                trimed = $.trim($(ele).val());

            // 去除左右空格
            $(ele).val(trimed);

            // 校验
            this.doCheck(ele);

        },

        /**
         * 下拉框选项改变时, 隐藏表单提示信息并进行验证
         *
         * @param ele 当前被改变的DOM元素
         */
        onchange: function(ele) {
            this.doCheck(ele);
        },

        /**
         * 点击checkbox,radio时, 隐藏表单提示信息并进行验证
         *
         * @param ele 当前被点击的DOM元素
         */
        onclick: function(ele) {
            this.doCheck(ele);
        },

        /**
         * 自定义校验事件,表单无触发校验事件时手动触发check事件即可校验
         */
        oncheck: function(ele) {
            this.doCheck(ele);
        },

        /**
         * 验证全部表单
         */
        checkAll: function() {

            for(prop in this.props) {
                var ele = this.toDOM(prop),
                    force = this.props[ele.name].force;
                ele && this.check(ele, force);
            }

        },

        /**
         * 进行表单验证 表单未配置验证信息不进行验证
         */
        doCheck: function(ele) {
            var prop = this.props[ele.name];
            prop && this.check(ele, prop.force);
        },

        /**
         * 验证表单元素,根据条件判断是否需要验证.
         *
         * 已忽略的表单清除验证信息 已验证过并且未修改内容时不重复验证, 只显示提示信息
         *
         * @param ele 需要被校验的DOM元素
         * @param force 强制校验, 已校验过也重新校验
         */
        check: function(ele, force) {

            // 忽略验证
            if(this.isIgnore(ele)) {
                this.clearValid(ele);
                return;
            }

            // 不需要再次验证
            if(this.needNotCheck(ele, force)) {
                this.showFormTips(ele);
                return;
            }

            // 缓存当前值, 并设置元素为已验证元素
            this.cachedDOM[ele.name].lastVal = this.getValue(ele);
            this.isValided(ele) || this.validedList.push(ele.name);

            // 开始校验
            var isCorrect = this.checkEle(ele);

            // 验证成功, 清除错误缓存
            if(isCorrect) {
                this.removeErroredItem(ele);
            }

            // 显示验证提示信息
            this.showFormTips(ele);

            // 有Ajax校验,在Ajax校验完成后执行
            // 无Ajax时执行回调函数
            if(!this.isWaiting(ele)) {
                isCorrect ? this.doAjaxSuccess(ele) : this.doAjaxFailed(ele);
            }

        },

        /**
         * 根据验证规则对表单项进行校验
         *
         * @param ele 需要被校验的DOM元素
         * @return 校验通过返回true
         */
        checkEle: function(ele) {

            var rules = this.props[ele.name].rule;

            for(field in rules) {

                var rule = rules[field],
                    param = (rule.length > 1) ? rule[1] : "",
                    conds = (rule.length > 2) ? rule[2] : "";

                // 函数验证或正则表达式验证
                var isFunc = $.isFunction(Validator.methods[field] || param),
                    isValid = isFunc ? this.funcCheck(ele, field, param, conds) : this.regexCheck(ele, field, param);

                // 验证失败
                if(!isValid) {
                    this.errorMap[ele.name] = rule[0];
                    return false;
                }

            }

            return true;
        },

        /**
         * 调用验证函数判断元素是否合法
         *
         * 当校验规则使用的是函数验证时调用本方法执行函数. 例: required, equalTo等逻辑校验.
         * 当传入参数是插件提供的函数时调用函数验证. 如传入自定义函数时使用自定义函数校验.
         *
         * @param ele 验证元素,DOM对象
         * @param prop 验证方法
         * @param param 验证规则参数, 如果插件内无此函数将该参数作为校验方法
         * @param conds 其余参数
         * @return 验证通过返回true
         */
        funcCheck: function(ele, prop, param, conds) {

            var method = Validator.methods[prop] || param,
                noneYet = ($.inArray(prop, Validator.noneYetCheck) == -1),
                funcYet = (noneYet && this.optional(ele));

            // 无校验方法时使用自定义校验函数
            // 允许空值校验的方法或值不为空时进行函数校验
            return funcYet || (method && method.call(this, ele, param, conds));

        },

        /**
         * 使用正则表达式验证函数是否合法
         *
         * 当校验规则使用的是正则验证时调用本方法执行正则表达式验证. 例: email, url等格式校验.
         * 当传入参数是插件提供的正则时调用正则验证. 如非插件提供时使用该参数作为正则表达式.
         * 当传入参数为多个自定义或内置正则表达式(半角逗号分隔)时, 通过一个验证即视为验证通过.
         *
         * @param ele 验证元素,DOM对象
         * @param prop 正则表达式名称
         * @param param 验证参数
         * @return 验证通过返回true
         */
        regexCheck: function(ele, prop, param) {

            // 未输入时不验证
            if(this.optional(ele)) {
                return true;
            }

            var method = Validator.methods[prop],
                value = this.getValue(ele);

            // 验证规则为插件内置方法
            // 使用方法的正则表达式验证
            if(method) {
                return method.test(value);
            }

            // 非内置方法时使用传入参数作为验证规则
            // 参数中可以传入多个验证规则(使用逗号分隔)
            // 支持使用插件内置方法和用户自定义正则表达式
            var regs = param.split(",");

            // 依次验证每个规则
            for(var i in regs) {

                // 确定验证规则使用内置方法或自定义表达式
                var trimReg = $.trim(regs[i]),
                    inlay = Validator.methods[trimReg],
                    regex = inlay ? inlay : new RegExp(trimReg);

                // 通过一个即视为验证通过
                if(regex.test(value)) {
                    return true;
                }

            }

            // 所有规则都未通过
            // 视为本次校验失败
            return false;

        },

        /**
         * 显示验证提示信息
         *
         * 根据元素的校验结果显示相应的提示信息. 验证结果分以下三种: > 正确: 表单项验证成功后显示 > 错误: 表单项验证失败后显示 >
         * 等待: 连接服务器验证未完成时显示
         *
         * @param ele 显示信息的DOM对象
         */
        showFormTips: function(ele) {

            var msg, css;

            // 设置提示信息的类型
            if(this.isWaiting(ele)) {
                msg = "", css = "await";
            } else if(this.isError(ele)) {
                msg = this.errorMap[ele.name], css = "error";
            } else {
                msg = "", css = "valid";
            }

            // 创建并显示表单提示信息
            this.createFormTipsAndShow(ele, msg, css);

        },

        /**
         * 创建并显示表单提示信息
         *
         * 根据提示信息类型显示对应的表单, 提示信息分以下四种: > 正确: 表单项验证成功后显示 > 错误: 表单项验证失败后显示 > 等待:
         * 连接服务器验证未完成时显示 > 提示: 当表单项获取焦点时显示
         *
         * @param ele 显示信息的DOM对象
         * @param tip 提示信息文案
         * @param style 表单样式(提示信息类型)
         */
        createFormTipsAndShow: function(ele, tip, style) {

            var cachedForm = this.cachedDOM[ele.name],
                formTips = cachedForm.formTips,
                formTipBox = this.loadEle('formTipBox');

            // 清空原有提示信息文字和样式及表单样式
            formTips.find(".form-tip-to-" + ele.name).remove();

            // 清除原有表单项样式,设置验证后的样式
            $(ele).removeClass(Validator.eleClass).addClass("form-ele-" + style);

            // 设置提示信息内容,样式
            formTipBox.removeClass(Validator.tipClass);
            formTipBox.addClass("form-tip-" + style).addClass("form-tip-to-" + ele.name);
            formTipBox.find(".form-tip-icon").removeClass(Validator.tipIcon).addClass("icon-form-" + style);
            formTipBox.find(".form-tip-msg").html(tip);

            // 添加当前表单信息(默认显示)
            formTipBox.appendTo(formTips);

            // 如果是组内表单(同组内还有其他表单)
            // 需要根据提示信息类型设置显示哪个表单的提示信息

            // 隐藏组内所有提示信息
            formTipBox.hide();
            formTips.find(".form-tip-box").hide();

            // 多个菜单时根据优先级选择显示菜单
            // 提示信息 > 错误信息 > 等待信息 > 正确信息
            this.showWitchTip(formTips);

        },

        /**
         * 清除表单所有提示信息
         */
        clearAllFormTip: function() {
            for(prop in this.props) {
                var ele = this.toDOM(prop);
                this.clearFormTip(ele);
            }
        },

        /**
         * 清除表单提示信息 当某一表单项不需要进行校验时, 需要清空该表单的校验结果
         * > 清空原有提示信息及样式
         * > 清空表单样式
         *
         * @param ele 元素DOM对象
         */
        clearFormTip: function(ele) {

            var formTips = this.cachedDOM[ele.name].formTips;

            // 删除现有提示信息
            $(ele).removeClass(Validator.eleClass);
            formTips.find(".form-tip-to-" + ele.name).remove();

            // 如果还有其他信息, 根据规则显示其他信息
            this.showWitchTip(formTips);

        },

        /**
         * 同组表单多个提示信息时, 显示哪一个
         */
        showWitchTip: function(formTips) {

            // 多个菜单时根据优先级选择显示菜单
            // 提示信息 > 错误信息 > 等待信息 > 正确信息
            if(formTips.children(".form-tip-focus").length) {
                formTips.children(".form-tip-focus:last").show();
                return;
            }

            if(formTips.children(".form-tip-error").length) {
                formTips.children(".form-tip-error:last").show();
                return;
            }

            if(formTips.children(".form-tip-await").length) {
                formTips.children(".form-tip-await:last").show();
                return;
            }

            if(formTips.children(".form-tip-valid").length) {
                formTips.children(".form-tip-valid:last").show();
                return;
            }

        },

        /**
         * 当表单校验出错错误时
         */
        onFormValidError: function() {
            var errPosTop = $(".form-ele-error:first").offset().top - 50;

            // 滑动到错误位置
            $("html, body").scrollTop(errPosTop);
            // 执行用户自定义函数
            this.configs.onError && this.configs.onError();
        },

        /**
         * 远程(ajax)校验时,服务返回成功后处理
         *
         * 服务器返回成功说明连接成功,是否验证通过需要根据返回内容判断 > 校验成功,删除错误缓存并系那是成功提示信息 >
         * 校验失败,将错误信息添加错误缓存并显示失败提示信息
         *
         * @param ele 显示信息的DOM对象
         * @param result 服务器返回结果
         */
        onAjaxSuccess: function(ele, result) {

            // 忽略验证
            if(this.isIgnore(ele)) {
                return;
            }

            // 根据返回结果判断是否校验成功
            // 服务器返回0或者true时代表验证通过
            // 执行验证成功或失败的业务处理
            if(result != '0' && result !== 'true') {
                this.errorMap[ele.name] = this.props[ele.name].rule.remote[0];
                this.doAjaxFailed(ele)
            } else {
                this.removeErroredItem(ele);
                this.doAjaxSuccess(ele)
            }

            // 删除等待标识并显示提示信息
            this.removeWaitingItem(ele);
            this.showFormTips(ele);

            // 当用户点击提交时, Ajax校验还未完成
            // 当校验完成且其余验证完成时后自动提交表单
            if(this.goSubmit && this.waitingList.length == 0) {
                var vd = this;
                window.setTimeout(function() {
                    vd.removeSubmiting();
                    vd.curForm.submit();
                }, 500);
            }

        },

        /**
         * 远程(ajax)校验时,服务返回失败处理 服务器返回失败说明服务器连接出现异常,设置异常提示信息并显示
         *
         * @param ele 显示信息的DOM对象
         */
        onAjaxError: function(ele) {

            // 表单项被忽略时不处理
            if(this.isIgnore(ele)) {
                return;
            }

            // 删除等待标识
            // 设置错误信息并显示
            // 执行验证失败处理
            this.removeWaitingItem(ele);
            this.errorMap[ele.name] = "系统繁忙,请稍后再试";
            this.showFormTips(ele);
            this.doAjaxFailed(ele)

        },

        /**
         * Ajax验证成功后操作 , 执行用户自定义验证成功函数
         *
         * @param ele 元素DOM对象
         */
        doAjaxSuccess: function(ele) {
            this.doSuccessCallback(ele);
        },

        /**
         * Ajax验证失败后操作, 执行用户自定义验证失败函数
         *
         * @param ele 元素DOM对象
         */
        doAjaxFailed: function(ele) {
            this.doFailedCallback(ele);
        },

        /**
         * 验证成功后执行用户自定义回调函数
         *
         * @param ele 元素DOM对象
         */
        doSuccessCallback: function(ele) {
            this.props[ele.name].success && this.props[ele.name].success();
        },

        /**
         * 验证失败后执行用户自定义回调函数
         *
         * @param ele 元素DOM对象
         */
        doFailedCallback: function(ele) {
            this.props[ele.name].failed && this.props[ele.name].failed();
        },

        /**
         * 绑定联动校验
         *
         * 关联某一表单变化时需要重新校验被关联的表单 例: 两个输入框内容必须一致, 当一个变了需要对另一个进行校验
         *
         * @param ele 验证元素,DOM对象
         * @param prop 验证方法
         * @param selector 关联表单选择器
         */
        bindEleLinkedCheck: function(ele, prop, selector) {
            var vd = this,
                evt = vd.getTriggerEvent(selector);

            // 绑定依赖表单的变化事件
            vd.toJQ(selector).on(evt, function() {
                vd.check(ele, true);
            });
        },

        /**
         * 绑定表单关联显示
         *
         * @param  ele 被操作的元素
         * @param show 显示参数,数组格式,包含3个参数: ["elename", 'value', "3"]
         *             参数1: 关联的表单名
         *             参数2: 关联类型: 'size':长度, 'value':内容
         *             参数3: 表单值或长度数值
         */
        bindEleLinkedDisplay: function(ele, show) {

            var v = this,
                selector = show[0],
                evt = v.getTriggerEvent(selector);

            // 绑定依赖表单的变化事件
            v.toJQ(selector).on(evt, function() {

                v.decideShow(ele, show);

            });

        },

        /**
         * 显示或隐藏表单项
         *
         * @param  ele 被操作的元素
         * @param show 显示参数,数组格式,包含3个参数: ["elename", 'value', "3"]
         *             参数1: 关联的表单名
         *             参数2: 关联类型: 'size':长度, 'value':内容
         *             参数3: 表单值或长度数值
         */
        decideShow: function(ele, show) {
            var selector = show[0],
                type = show[1],
                value = show[2],
                isShow;

            // 根据类型判断长度相等或内容相等时变化表单的显示状态
            if(type === 'size') {
                isShow = this.getEleLength(selector) === value;
            } else {
                isShow = this.getValue(selector) === value;
            }

            // 满足条件时(关联表单的内容或长度符合条件), 显示表单, 否则隐藏表单
            isShow ? this.showEle(ele) : this.hideEle(ele);

        },
        /**
         * 清空表单验证信息及缓存
         *
         * @param ele DOM元素
         */
        clearValid: function(ele) {
            this.clearFormTip(ele);
            this.removeValidedItem(ele);
            this.removeWaitingItem(ele);
            this.removeErroredItem(ele);
        },

        /**
         * 将元素从已验证的列表中移除
         *
         * @param ele DOM元素
         */
        removeValidedItem: function(ele) {
            var index = $.inArray(ele.name, this.validedList);
            if(index > -1) {
                this.validedList.splice(index, 1);
            }
        },

        /**
         * 将元素从等待列表中移除
         *
         * @param ele DOM元素
         */
        removeWaitingItem: function(ele) {
            var index = $.inArray(ele.name, this.waitingList);
            if(index > -1) {
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
         * 判断元素是否需要验证.
         * > 已验证过且值未改变时不需要验证
         * > 当前输入值和初始值相同时不需要验证
         *
         * @param ele 被验证的DOM对象
         * @param force 强制校验, 已校验过也重新校验
         * @return 不需要验证返回true
         */
        needNotCheck: function(ele, force) {
            // 强制校验
            if(force) {
                return false;
            }

            var cachedForm = this.cachedDOM[ele.name],
                value = this.getValue(ele),
                initial = this.props[ele.name].initial,
                isInitVal = cachedForm.initValue && cachedForm.initValue === value;

            // 页面初始值不进行校验
            if(initial && isInitVal) {
                this.removeErroredItem(ele);
                return true;
            }

            // 已验证过且未改变值时不进行校验
            return this.isValided(ele) && (cachedForm.lastVal === value);
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
         * 判断元素是否验证是否在等待队列中 Ajax校验在服务器未返回时将元素放入等待列表中
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
         * 是否需要关联校验 关联校验: 该校验依赖于其他表单. 当依赖表单变化时需要重新校验本表单. 例: 新密码和确认密码两个字段内容必须一致.
         * 当新密码变化时必须校验旧密码内容
         *
         * @param method 校验方法名称
         */
        isLinkedCheck: function(method) {
            return $.inArray(method, Validator.linkedCheck) > -1;
        },

        /**
         * 是否有验证失败的表单项
         *
         * @return 有验证失败的表单项返回true
         */
        hasErrorEle: function() {
            for(err in this.errorMap) {
                return true;
            }
            return false;
        },

        /**
         * 是否有验证中的表单项
         *
         * @return 有验证中的表单项返回true
         */
        hasWaitingEle: function() {
            return this.waitingList.length > 0;
        },

        /**
         * 加载指定HTML
         *
         * @param key 名称
         */
        loadEle: function(key) {
            return $(Validator.tipsEle[key]);
        },

        /**
         * 判断元素是否需要进行验证
         *
         * @param ele DOM元素
         * @return 长度为0时返回true
         */
        optional: function(ele) {
            return this.getEleLength(ele) == 0
        },

        /**
         * 判断元素是否是checkbox或radio
         *
         * @param param 元素名称或DOM节点
         * @return 元素为checkbox或radio时返回true
         */
        checkable: function(param) {
            var type = this.getEleType(param);
            return type === "checkbox" || type === "radio";
        },

        /**
         * 判断元素是否是select
         *
         * @param param 元素名称或DOM节点
         * @return 元素为select时返回true
         */
        selectable: function(param) {
            var type = this.getEleType(param);
            return type === "select-one" || type === "select-multiple";
        },

        /**
         * 是否是隐藏输入表单(<input type="hidden" />)
         *
         * @param param 元素名称或DOM节点
         * @return 元素为隐藏输入表单时返回true
         */
        isInputHidden: function(param) {
            var type = this.getEleType(param);
            return type === "hidden";
        },

        /**
         * 判断表单是否正在提交中 防止重复提交, 正在提交的表单不应该再次进行处理
         *
         * @return 提交中返回true
         */
        isSubmiting: function() {
            return this.curForm.hasClass(Validator.submitClass);
        },

        /**
         * 设置表单正在提交中 防止重复提交, 正在提交的表单不应该再次进行处理
         */
        setSubmiting: function() {
            this.curForm.addClass(Validator.submitClass);
            this.curForm.find("." + Validator.btnClass).addClass(Validator.btnPause).attr("disabled", true);
        },

        /**
         * 删除表单正在提交中 防止重复提交, 正在提交的表单不应该再次进行处理
         */
        removeSubmiting: function() {
            this.curForm.removeClass(Validator.submitClass);
            this.curForm.find("." + Validator.btnClass).removeClass(Validator.btnPause).removeAttr("disabled");
        },

        /**
         * 显示元素, 并设置需要校验
         */
        showEle: function(ele) {

            var eleJQ = this.toJQ(ele.name),
                parent = this.getJoinParent(ele);

            // 显示元素
            eleJQ.removeClass(Validator.ignoreClass).show();

            // 如果父元素含有样式form-inline(布局样式), 同时显示父元素
            parent && parent.show();

        },

        /**
         * 隐藏元素,被隐藏的元素不需要校验
         */
        hideEle: function(ele) {

            var eleJQ = this.toJQ(ele.name),
                parent = this.getJoinParent(ele);

            //隐藏元素
            eleJQ.addClass(Validator.ignoreClass).hide();

            // 如果父元素含有样式form-inline(布局样式), 同时隐藏父元素
            parent && parent.hide();

        },

        /**
         * 显示关联的父元素
         * 显示元素或隐藏元素时:
         * a. 如果被隐藏/显示的表单项组内还有其他的表单项,只隐藏当前表单项, 不隐藏其他表单项
         * b. 如果没有其他表单项, 需要将表单项及其父元素整体隐藏(包含label等)
         * 由于页面局部不确定性较大,因此需要将和表单项一起的父元素单独设定为"form-joined"样式
         * 系统隐藏表单时,会寻找表单含有"form-joined"样式父元素,没有则只隐藏表单本身
         */
        getJoinParent: function(ele) {

            var eleJQ = this.toJQ(ele.name);
            return eleJQ.parents("." + Validator.joinedClass) || eleJQ;

        },

        /**
         * 取得元素类型
         *
         * @param param 元素名称或DOM节点
         * @return DOM节点返回该节点类型, 元素名称返回对应节点的类型
         */
        getEleType: function(param) {
            return(typeof param == 'string') ? this.toDOM(param).type :
                param.type;
        },

        /**
         * 取得元素名称
         *
         * @param param 元素名称或DOM节点
         * @return DOM节点返回该节点的名称
         */
        getEleName: function(param) {
            return(typeof param == 'string') ? param : param.name;
        },

        /**
         * 获取表单触发事件名称
         * > radio, checkbox: click事件
         * > select,input:hidden: change事件
         * > input, textarea: focusout
         *
         * @param ele DOM节点
         */
        getTriggerEvent: function(selector) {
            var e = "focusout";

            if(this.checkable(selector)) {
                e = "click";
            } else if(this.selectable(selector)) {
                e = "change";
            } else if(this.isInputHidden(selector)) {
                e = "change";
            }

            return e + " " + "check";
        },

        /**
         * 取得元素的长度
         * > 输入框,下拉框,Textarea: 取得内容的字数
         * > checkbox,raido: 取得同组(name相同)中被选中的数量
         *
         * @param ele DOM元素
         */
        getEleLength: function(ele) {
            return this.checkable(ele) ? this.getChecked(ele).length : this.getValue(ele).length;
        },

        /**
         * 取得checkbox, radio被选中的项
         *
         * @param param 元素名称或DOM对象
         * @return 被选中项的jQuery对象
         */
        getChecked: function(param) {
            var name = this.getEleName(param);
            return this.toJQ(name, ":checked");
        },

        /**
         * 取得元素的值
         * > 输入框,下拉框,Textarea: 取得内容
         * > checkbox,raido:
         * 取得同组(name相同)中被选中项内容(选中多组通过","拼接)
         *
         * @param param 元素名称或DOM节点
         */
        getValue: function(param) {

            var name = this.getEleName(param);

            // input, select, textarea
            if(!this.checkable(name)) {
                return this.toJQ(name).val();
            }

            // checkbox, radio
            var temp = new Array();

            this.getChecked(name).each(function() {
                temp.push($(this).val());
            });

            return temp.join(',');

        },

        /**
         * 取得元素的name属性
         *
         * 例: 某元素的属性名为company.deptName
         * > 表单提交时参数名使用company.deptName
         * > Ajax校验时参数名使用deptName
         *
         * @param ele DOM元素
         */
        getPropName: function(ele) {
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
         * @return 元素的DOM对象, 多个DOM元素时返回第一个
         */
        toDOM: function(name) {
            return this.curForm.find('[name=' + name + ']')[0];
        }

    };

    /**
     * HTML
     */
    Validator.tipsEle = {

        /**
         * 必须输入项标识
         */
        requiredSign: ['<span class="iconfont icon-input-pen form-required"></span>'].join(""),

        /**
         * 表单提示信息容器
         */
        formTips: ['<div class="form-tips"></div>'].join(""),

        /**
         * 表单提示信息组件
         */
        formTipBox: [
            '<div class="form-tip-box">',
            '<i class="form-tip-icon iconfont"></i><p class="form-tip-msg"></p>',
            '</div>'
        ].join("")

    };

    /**
     * 表单提示信息样式
     */
    Validator.tipClass = ['form-tip-focus', 'form-tip-error',
        'form-tip-await', 'form-tip-valid'
    ].join(" ");

    /**
     * 表单校验结果样式
     */
    Validator.eleClass = ['form-ele-focus', 'form-ele-error',
        'form-ele-await', 'form-ele-valid'
    ].join(" ");

    /**
     * 忽略校验表单样式
     */
    Validator.ignoreClass = ['form-ele-ignore'].join(" ");

    /**
     * 表单提交中样式
     */
    Validator.submitClass = ['form-submiting'].join(" ");

    /**
     * 表单提示图标样式
     */
    Validator.tipIcon = ['icon-form-focus', 'icon-form-error',
        'icon-form-await', 'icon-form-valid'
    ].join(" ");

    /**
     * 字段值为空时也需要校验的方法名称
     */
    Validator.noneYetCheck = ['required', 'requiredTo', 'equalTo',
        'unequalTo', 'either', 'least'
    ];

    /**
     * 需要关联校验的方法名称
     */
    Validator.linkedCheck = ['show', 'requiredTo', 'equalTo', 'unequalTo', 'either',
        'least', 'maxTo', 'minTo', 'greater', 'less'
    ];

    /**
     * 表单组样式
     */
    Validator.joinedClass = 'form-joined';

    /**
     * 表单内按钮样式
     */
    Validator.btnClass = 'glb-button';

    /**
     * 表单内按钮不可用样式
     */
    Validator.btnPause = 'glb-btn-pause';

    /**
     * 验证方法
     */
    Validator.methods = {

        // 邮箱
        email: /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/,

        // 座机(400-818-0088)
        tel400: /^(400|800)-\d{3}-\d{4}$/,

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

        // 整数(包括0或负数)
        digital: /^-?[0-9]+$/,

        // 数字(任意数字包含小数点,不限小数点位数)
        number: /^[+-]?[1-9][0-9]*(\.[0-9]+)?([eE][+-][1-9][0-9]*)?$|^[+-]?0?\.[0-9]+([eE][+-][1-9][0-9]*)?$/,

        // 金钱(2位小数点)
        money: /^\d+(\.\d{0,2})?$/,

        // 年
        year: /^(19|20)\d{2}$/,

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
        // orgNo: /^[a-zA-Z0-9]{8}[-]?[a-zA-Z0-9]{1}$/,
        orgNo: /^[a-zA-Z0-9]{9}$/,

        // 营业执照(15位数字)
        licenseNo: /^\d{15}$/,

        // 必须输入
        required: function(ele) {
            return this.getEleLength(ele) > 0;
        },

        // 必须输入(关联其他表单)
        requiredTo: function(ele, selector, compare) {

            if(compare) {
                return this.getValue(selector) !== compare ||
                    this.getEleLength(ele) > 0;
            } else {
                return !this.getEleLength(ele) === !this.getEleLength(selector);
            }

        },

        // 不能同时存在或同时为空
        either: function(ele, selector) {
            return !this.getEleLength(ele) !== !this.getEleLength(selector);
        },

        // 至少一个不能为空
        least: function(ele, selector) {
            return !this.getEleLength(ele) || !this.getEleLength(selector);
        },

        // ajax验证
        remote: function(ele, url, param) {
            var vd = this,
                data = {};

            this.waitingList.push(ele.name);
            this.validedList.push(ele.name);

            // Ajax参数
            // 当前DOM参数+其他关联参数
            data[this.getPropName(ele)] = this.getValue(ele);
            if(param) {
                for(i in param) {
                    var pd = param[i];
                    data[pd] = this.getValue(pd)
                }
            }

            // 显示等待提示
            this.showFormTips(ele);

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

            // 固定长度
            if(larray.length == 1) {
                return length == larray[0];
            }

            // 区间
            // -9: 最大9位, 3-: 最少3位
            // 3-9: 位数在3-9位(包含3和9)
            if(!larray[0]) {
                return length <= larray[1];
            }
            if(!larray[1]) {
                return length >= larray[0];
            }

            return(length >= larray[0] && length <= larray[1]);
        },

        // 等于某一值
        equals: function(ele, val) {
            return this.getValue(ele) === val;
        },

        // 与另一元素内容相等
        equalTo: function(ele, selector) {
            return this.getValue(ele) === this.getValue(selector);
        },

        // 与另一元素内容不相等
        unequalTo: function(ele, selector) {
            return this.getValue(ele) !== this.getValue(selector);
        },

        // 不能大于某值
        max: function(ele, val) {
            return this.getValue(ele) * 1 < val * 1;
        },

        // 不能小于某值
        min: function(ele, val) {
            return this.getValue(ele) * 1 > val * 1;
        },

        // 不能大于等于某值
        maxEq: function(ele, val) {
            return this.getValue(ele) * 1 <= val * 1;
        },

        // 不能小于等于某值
        minEq: function(ele, val) {
            return this.getValue(ele) * 1 >= val * 1;
        },

        // 大于另一元素
        maxTo: function(ele, selector) {
            return this.getValue(ele) * 1 > this.getValue(selector) * 1;
        },

        // 小于另一元素
        minTo: function(ele, selector) {
            return this.getValue(ele) * 1 < this.getValue(selector) * 1;
        },

        // 大于等于另一元素
        maxEqTo: function(ele, selector) {
            return this.getValue(ele) * 1 >= this.getValue(selector) * 1;
        },

        // 小于等于另一元素
        minEqTo: function(ele, selector) {
            return this.getValue(ele) * 1 <= this.getValue(selector) * 1;
        }

    };

}(jQuery);