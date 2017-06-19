// -----------------------------------------------------
// | 版权所有: 蓝桥 - 教研部                                                                                 |
// -----------------------------------------------------

/**
 * 表单分组插件
 *
 * 使用方法: 引用group.js即可, 默认展开所有组. 可以通过在组的外围容器中设置下列样式实现相应配置:
 * > group-fold-only: 展开某一组时关闭其他组
 * > group-fold-default: 默认折叠所有组
 * 如单独显示某一组, 使用group-fold-default样式,同时在URL后增加锚点, 例: a.html#1, 默认显示第一个
 * 如需让滚动条显示到某一组时,在URL后增加相应组ID的描点, 例a.html#here,滚动条默认到id为hereItem的组
 *
 * 插件需要的DOM结构: 插件会按以下结构进行组的设置, 其中***为组的任意内容
 * 折叠时: 隐藏group-bd内容,只显示group-hd内容, 配置Class需要在items-group-box中添加
 * ----------------------------------------------------------------
 * <div class="items-group-box group-fold-only group-fold-default">
 *     <div class="item-group">
 *         <div class="group-hd">*********</div>
 *         <div class="group-bd">*********</div>
 *     </div>
 * </div>
 * ----------------------------------------------------------------
 *
 * @Author zbq
 * @Date 2016-09-19
 */
! function($) {

	var GroupEle = {

		// 展开折叠按钮
		optGroupBtn: [
			'<a class="switch-btn iconfont icon-unfold" href="javascript:;"></a>'
		].join("")

	};

	var groupBox, foldOnly, initFold, lightKey;

	/**
	 * DOM加载完成后初始化组插件
	 */
	$(function() {
		initItemGroups();
		bindItemGroups();
		slideToOneItem();
	});

	/**
	 * 初始化组相关DOM及配置
	 */
	var initItemGroups = function() {

		// 组DOM容器
		groupBox = $(".items-group-box"),

		// 展开某一组时关闭其他组
		foldOnly = groupBox.hasClass("group-fold-only"),

		// 默认折叠所有组
		initFold = groupBox.hasClass("group-fold-default"),

		// 默认展开组或显示组的序号, URL中的锚点, 例: a.html#1
		lightKey = location.hash && location.hash.substring(1);

	};

	/**
	 * 绑定表单组,添加折叠展开按钮
	 */
	var bindItemGroups = function() {

		groupBox.find(".item-group").each(function() {
			var group = $(this),
				groupHd = group.find(".group-hd"),
				groupBd = group.find(".group-bd"),
				optGroupBtn = $(GroupEle['optGroupBtn']);

			// 添加展开折叠按钮
			groupHd.append(optGroupBtn);

			// 是否展开
			(lightKey == group.index() + 1 || !initFold) && displayGroupItem(group);

			// 绑定展开折叠事件
			groupHd.on("click", function() {
				if (optGroupBtn.hasClass("icon-unfold")) {
					showGroupItem(group);
				} else {
					hideGroupItem(group);
				}
			});

		});

	};

	/**
	 * 将屏幕滚动到特定的组中
	 * 根据链接后的描点中的字符,如果为某个对象的ID,则滚动至该对象所在位置
	 * 例: <div id="box">, 链接为:a.html#box则屏幕滚动至#box位置
	 */
	var slideToOneItem = function() {
		var lightItem = $('#' + lightKey + 'Item');
		if (lightItem.length > 0) {
			$("html, body").animate({
				scrollTop: lightItem.offset().top - 54
			});
		}
	};

	/**
	 * 展开某一组,无动画
	 *
	 * @param 组DOM对象
	 */
	var displayGroupItem = function(group) {
		group.find(".group-bd").show();
		group.find(".switch-btn").removeClass("icon-unfold").addClass("icon-fold");
	};

	/**
	 * 展开某一组
	 *
	 * @param 组DOM对象
	 */
	var showGroupItem = function(group) {
		group.find(".group-bd").stop().slideDown();
		group.find(".switch-btn").removeClass("icon-unfold").addClass("icon-fold");

		if (foldOnly) {
			hideGroupItem(group.siblings());
		}
	};

	/**
	 * 折叠某一组
	 *
	 * @param 组DOM对象
	 */
	var hideGroupItem = function(group) {
		group.find(".group-bd").stop().slideUp();
		group.find(".switch-btn").removeClass("icon-fold").addClass("icon-unfold");
	};

}(jQuery);