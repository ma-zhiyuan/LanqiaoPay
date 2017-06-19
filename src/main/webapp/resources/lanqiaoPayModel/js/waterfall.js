// -----------------------------------------------------
// | 版权所有: 蓝桥 - 教研部                                                                                 |
// -----------------------------------------------------

/**
 * 瀑布流插件
 *
 * @Author zbq
 * @Date 2016-09-19
 */
(function($) {

	// 瀑布流
	$.fn.waterfall = function(config) {
		var p = $.extend({
			dsX: 30, // 元素X轴间隔
			dsY: 30 // 元素Y轴间隔
		}, config, {
			result: {
				_width: 0,
				_height: 0,
				_left: 0,
				_speed: 0
			}
		}, {

			// 初始化瀑布流
			init: function() {
				if(!waterfall.hasClass("waterfalled")) {
					// 设置元素瀑布流基本样式
					waterfall.css({
						position: "relative"
					}).addClass("waterfalled");
					waterfall.children().css({
						position: "absolute"
					});
				} else {
					p.setAnimateSpeed(600);
				}
				// 设置瀑布流位置
				p.setPosition();
				p.setAnimateSpeed(600);
			},

			// 设置动画速度
			setAnimateSpeed: function(speed) {
				p.result._speed = speed;
			},

			// 计算每行显示的元素数量
			handleLayoutParam: function() {
				var totalWidth = parent.width();

				p.result._size = (totalWidth + p.dsX) / itemWidth | 0; // 每行显示元素个数
				if(p.result._size > children.length) {
					p.result._size = children.length;
				}

				p.result._width = p.result._size * itemWidth - p.dsX, // 瀑布流整体宽度
					p.result._left = (totalWidth - p.result._width) / 2; // 瀑布流整体离窗体左侧间距
			},

			// 设置瀑布流各元素位置
			setPosition: function() {
				// 初始化瀑布流布局参数
				this.handleLayoutParam();

				var hgArray = [];
				// 设置瀑布流中每个元素的位置
				$.each(children, function(i) {
					var top, left, height = $(this).innerHeight();
					if(i < p.result._size) {
						// 第一行, 正常排列
						hgArray[i] = height,
							top = 0, left = i * itemWidth;
					} else {
						// 第二行及以后,元素放置在行高度最小的元素下面
						var minHeight = Math.min.apply(Math, hgArray),
							minQ = $.inArray(minHeight, hgArray);
						hgArray[minQ] += height + p.dsY,
							top = minHeight + p.dsY, left = minQ * itemWidth;
					}

					// 元素位置设置动画效果
					$(this).animate({
						top: top,
						left: left
					}, p.result._speed);
				});

				// 设置瀑布流容器的宽度和高度
				waterfall.width(p.result._width).height(Math.max.apply(Math, hgArray));
			}
		});

		var waterfall = $(this),
			parent = waterfall.parent(),
			children = waterfall.children(),
			itemWidth = children.first().innerWidth() + p.dsX;

		return $(this).each(function() {
			p.init();
		});
	}

})(jQuery);