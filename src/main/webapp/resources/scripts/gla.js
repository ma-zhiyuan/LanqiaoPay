/**
 * 框架
 *
 * @Author zbq
 * @Date 2016-09-19
 */
! function() {

    window.Frame = window.Frame || {};
    var F = Frame = {};

    /**
     * 全局菜单
     */
    F.Frame_Menu = {};

}();

/**
 * 全局菜单
 * 使用方法: 页面加载完成后调用frame.Frame_Menu.init()即可. (注: 全局插件, 只需调用一次).
 */
! function(F, $) {

    var Frame_Menu = F.Frame_Menu;

    /**
     * 初始化全局菜单
     * 1. 绑定点击切换模式开关
     * 2. 绑定点击切换子菜单
     */
    Frame_Menu.init = function() {
        bindToggleMenu();
        bindToggleFold();
        bindToggleSub();
    };

    /**
     * 绑定点击切换模式开关
     */
    var bindToggleMenu = function() {

        $("#toggleMenuBtn").click(function() {
            $(this).toggleClass("icon-3line-x icon-3line-y");
            $("#frameBody").toggleClass("frame-body-full");
        });

    };

    /**
     * 绑定点击切换子菜单
     */
    var bindToggleFold = function() {

        $(".frame-menu-mod a").click(function() {
            $(this).parents(".frame-menu-item").toggleClass("frame-menu-fold");
        });

    };

    /**
     * 绑定点击切换二级菜单
     */
    var bindToggleSub = function() {

        $("#frameHeader").click(function() {
            $("#frameMsub").toggleClass("frame-msub-hide");
        });

    };

}(Frame, jQuery);

/**
 * 全局提示信息
 */
! function(F, $) {

    var TIP_ELE = {

        success: [
            '<h6 class="frame-tips glb-text-success">',
            '    <span class="iconfont icon-correct"></span>',
            '</h6>'
        ].join(""),

        failed: [
            '<h6 class="frame-tips glb-text-warning">',
            '    <span class="iconfont icon-error"></span>',
            '</h6>'
        ].join(""),

        waiting: [
            '<h6 class="frame-tips glb-text-info">',
            '    <span class="iconfont icon-loading"></span>',
            '</h6>'
        ].join("")
    };

    /**
     * 显示成功提示信息(无自定义信息时显示"操作成功")
     */
    F.showSuccess = function(msg) {
        var tip = $(TIP_ELE['success']).append(msg || '操作成功');
        showTip(tip);
    };

    /**
     * 显示失败提示信息(无自定义信息时显示"操作失败")
     */
    F.showError = function(msg) {
        var tip = $(TIP_ELE['failed']).append(msg || '操作失败');
        showTip(tip);
    };

    /**
     * 显示提示信息
     */
    F.showWaiting = function(msg) {
        var tip = $(TIP_ELE['waiting']).append(msg || '正在提交,请稍后');
        showTip(tip);
    };

    /**
     * 显示提示信息, 3秒后关闭
     */
    var showTip = function(tip) {

        var frameHd = $(".frame-main-hd");

        // 显示提示信息
        frameHd.find(".frame-tips").remove();
        tip.appendTo(frameHd).show().addClass("frame-tips-show");

        // 3秒后关闭
        window.setTimeout(function() {
            tip.remove();
        }, 3000);

    };

}(Frame, jQuery);

/**
 * 加载全局通用插件
 * 1. Menu
 */
$(function() {
    Frame.Frame_Menu.init();
});