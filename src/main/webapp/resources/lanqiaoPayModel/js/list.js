// -----------------------------------------------------
// | 版权所有: 蓝桥 - 教研部                                                                                 |
// -----------------------------------------------------

var Listx = {};
/*
 * 列表页脚本
 *
 * @Author zbq
 * @Date 2016-09-19
 */
! function($) {

	var listCondBd, listPage;

	/**
	 * 初始化列表
	 */
	Listx.init = function(){
		listCondBd = $("#listCondBd"),
		listPage = $("#listPage");

		bindToggleCond();
		bindClickPage();

	};

	/**
	 * 绑定显示查询条件事件
	 */
	var bindToggleCond = function(){
		$("#moreCondBtn").click(function(){
			$(this).toggleClass("cond-tbs-down");
			$(this).find(".iconfont").toggleClass("icon-arrow-up icon-arrow-down");
			listCondBd.slideToggle(100);
		});
	};

	/**
	 * 绑定点击分页事件
	 */
	var bindClickPage = function(){
		if (!listPage.length){
			return;
		}
		listPage.find("li a").click(function(){
			$("#goPageNo").val($(this).html());
			listPage.parents("form").submit();
		});
	};

}(jQuery);

/**
 * 列表
 */
$(function() {
	Listx.init();
});