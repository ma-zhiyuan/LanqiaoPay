// -----------------------------------------------------
// | 版权所有: 蓝桥 - 教研部                                                                                 |
// -----------------------------------------------------

/**
 * 通用动画效果
 *
 * @Author zbq
 * @Date 2016-09-19
 */
! function($) {

	// 全局工具类
	var Util = window.Util;


	$.extend($.fn, {

		/**
		 * 元素从顶部滑落,在另一元素中间位置显示
		 *
		 * @param relEle 相对元素
		 * @param opacity 透明度,默认1
		 * @param speed 动画速度,默认500ms
		 */
		showEleInMiddle: function(relEle, opacity, speed) {
			var _opacity = opacity || 1,
				_speed = speed || 500;

			$(this).css({
				opacity: 0,
				left: Util.getCenterX($(this), relEle),
				top: -1000
			}).animate({
				opacity: _opacity,
				top: Util.getCenterY($(this), relEle)
			}, _speed);
		},

		/**
		 * 在另一元素中间位置显示
		 *
		 * @param relEle 相对元素
		 * @param opacity 透明度,默认1
		 * @param speed 动画速度,默认500ms
		 */
		showEleInCenter: function(relEle, opacity, speed) {
			var _opacity = opacity || 1,
				_speed = speed || 500;

			$(this).css({
				opacity: 0,
				left: Util.getCenterX($(this), relEle),
				top: Util.getCenterY($(this), relEle)
			}).animate({
				opacity: _opacity
			}, _speed);
		},

		/**
		 * 元素从顶部滑落,在屏幕中间位置显示
		 *
		 * @param opacity 透明度,默认1
		 * @param speed 动画速度,默认500ms
		 */
		showEleToMiddle: function(opacity, speed) {
			$(this).showEleInMiddle($(window), opacity, speed);
		},

		/**
		 * 元素在屏幕中间位置显示
		 *
		 * @param opacity 透明度,默认1
		 * @param speed 动画速度,默认500ms
		 */
		showEleToCenter: function(opacity, speed) {
			$(this).showEleInCenter($(window), opacity, speed);
		},

		/**
		 * 飞入左侧显示元素
		 *
		 * @param offset 飞入偏移量,默认50
		 * @param speed 动画速度,默认200ms
		 */
		showEleFlyToLeft: function(offset, speed) {
			var _offset = offset || 50,
				_speed = speed || 200;

			$(this).css({
				opacity: 0,
				left: '+=' + _offset
			}).animate({
				opacity: 1,
				left: '-=' + _offset
			}, _speed);
		},

		/**
		 * 删除元素, 元素从顶部飞出后删除
		 *
		 * @param callback 删除后回调函数
		 */
		removeEleWithAnim: function(callback) {
			$(this).length > 0 && $(this).animate({
				opacity: 0,
				top: -1000
			}, 300, function() {
				$(this).remove();
				callback && callback();
			});
		},

		/**
		 * 删除元素, 先使元素透明后在删除元素
		 *
		 * @param callback 删除后回调函数
		 */
		removeEleWithFade: function(callback) {
			$(this).length > 0 && $(this).animate({
				opacity: 0
			}, 300, function() {
				$(this).remove();
				callback && callback();
			});
		}

	});

}(jQuery);