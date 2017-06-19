// -----------------------------------------------------
// | 版权所有: 蓝桥 - 教研部                                                                                 |
// -----------------------------------------------------

/**
 * 工具类
 *
 * @Author zbq
 * @Date 2016-09-19
 */
! function($) {

	window.Util = window.Util || {};

	var win = window,
		doc = win.document;

	/**
	 * 获取浏览器UA
	 */
	Util.getUA = function() {
		return win.navigator && win.navigator.userAgent || '';
	},

	/**
	 * 判断浏览器为IE6
	 */
	Util.isIE6 = function() {
		var m = Util.getUA().match(/MSIE ([\w.]+)/);
		return !!(m && m[1] === '6.0');
	},

	/**
	 * 判断浏览器为IE8及以下版本
	 */
	Util.isltIE8 = function() {
		return !$.support.leadingWhitespace;
	},

	/**
	 * 判断字符
	 */
	Util.isString = function(val) {
		return typeof val == 'string';
	},

	/**
	 * 判断数字
	 */
	Util.isNumber = function(val) {
		return typeof val === 'number';
	},

	/**
	 * 判断非空字符
	 */
	Util.isNotEmptyString = function(val) {
		return this.isString(val) && val !== '';
	},

	/**
	 * 判断对象
	 */
	Util.isObj = function(ele) {
		return ele != undefined && ele != null;
	},

	/**
	 * 判断对象是否存在
	 */
	Util.isExist = function(ele) {
		return Util.isObj(ele) && ele.length > 0;
	},

	/**
	 * 判断对象是否不存在
	 */
	Util.isNotExist = function(ele) {
		return !Util.isObj(ele) || !Util.isExist(ele);
	},

	/**
	 * 判断是否是同一DOM节点,判断ID是否相同
	 */
	Util.isSame = function(one, two) {
		return Util.isExist(one) && Util.isExist(two) && one.attr("id") == two.attr("id");
	},

	/**
	 * 判断字符串或数字是否相等
	 */
	Util.isEqual = function(a, b) {
		return Util.isObj(a) && Util.isObj(b) && a == b
	},

	/**
	 * 判断一个对象是否是另一个对象的子节点, 包含返回true
	 * 例: <p><a></a></p>
	 *     Util.isContain($("p"), $("a")) = true
	 *     Util.isContain($("a"), $("p")) = false
	 *
	 * @param parent 父节点
	 * @param child 子节点
	 */
	Util.isContain = function(parent, child) {
		var p = parent instanceof $ ? parent.get(0) : parent,
			c = child instanceof $ ? child.get(0) : child;
		return $.contains(p, c);
	},

	/**
	 * 是否是函数
	 */
	Util.isFunction = function(t) {
		return "function" == typeof t || Object.prototype.toString.apply(t) === "[object Function]";
	},

	/**
	 * 获取JS对象属性数量
	 */
	Util.getSize = function(ele) {
		var i = 0;
		for (o in ele) {
			i++;
		}
		return i;
	},

	/**
	 * 获取Cookie
	 */
	Util.getCookie = function(name) {
		var m = document.cookie.match('(?:^|;)\\s*' + name + '=([^;]*)');
		return (m && m[1]) ? decodeURIComponent(m[1]) : '';
	},

	/**
	 * 设置Cookie
	 */
	Util.setCookie = function(name, val, expires, domain, path, secure) {
		var text = String(encodeURIComponent(val));
		//有效期
		if (this.isNumber(expires)) {
			var date = new Date();
			date.setTime(date.getTime() + expires * 24 * 60 * 60 * 1000);
			text += '; expires=' + date.toUTCString();
		}
		// 访问域,访问路径,加密
		text += this.isString(domain) ? '; domain=' + domain : '';
		text += this.isString(path) ? '; path=' + path : '';
		text += secure ? '; secure' : '';

		doc.cookie = name + '=' + text;
	},

	/**
	 * 清除Cookie
	 */
	Util.deleteCookie = function(name) {
		var date = new Date();
		date.setTime(date.getTime() - 10000);
		doc.cookie = name + "=a; expires=" + date.toGMTString();
	},

	/**
	 * 设置元素相对于另一元素X轴居中
	 *
	 * @param ele 当前对象, 为空时取屏幕中央
	 * @param rel 相对对象, 为空时相对于屏幕
	 * @param over 如果为true,当相对位置为负数时,设置为0.
	 */
	Util.setCenterX = function(ele, rel, over) {
		ele.css({
			left: this.getCenterX(ele, rel, over)
		});
	},

	/**
	 * 设置元素相对于另一元素Y轴居中
	 *
	 * @param ele 当前对象, 为空时取屏幕中央
	 * @param rel 相对对象, 为空时相对于屏幕
	 * @param over 如果为true,当相对位置为负数时,设置为0.
	 */
	Util.setCenterY = function(ele, rel, over) {
		ele.css({
			top: this.getCenterY(ele, rel, over)
		});
	},

	/**
	 * 设置元素相对于另一元素居中
	 *
	 * @param ele 当前对象, 为空时取屏幕中央
	 * @param rel 相对对象, 为空时相对于屏幕
	 * @param over 如果为true,当相对位置为负数时,设置为0.
	 */
	Util.setCenter = function(ele, rel, over) {
		ele.css({
			left: this.getCenterX(ele, rel, over),
			top: this.getCenterY(ele, rel, over)
		});
	},

	/**
	 * 取得对象相对于另一对象居中时X轴坐标
	 *
	 * @param ele 当前对象, 为空时取屏幕中央
	 * @param rel 相对对象, 为空时相对于屏幕
	 * @param over 如果为true,当相对位置为负数时,设置为0.
	 */
	Util.getCenterX = function(ele, rel, over) {
		var minuend = this.isObj(ele) ? ele.innerWidth() : 0,
			relWidth = this.isObj(rel) ? rel.innerWidth() : $(win).width(),
			difference = (relWidth - minuend) / 2;

		return difference < 0 ? (over ? 0 : difference) : difference;
	},

	/**
	 * 取得对象相对于另一对象居中时Y轴坐标
	 *
	 * @param ele 当前对象, 为空时取屏幕中央
	 * @param rel 相对对象, 为空时相对于屏幕
	 * @param over 如果为true,当相对位置为负数时,设置为0.
	 */
	Util.getCenterY = function(ele, rel, over) {
		var minuend = this.isObj(ele) ? ele.innerHeight() : 0,
			relHeight = this.isObj(rel) ? rel.innerHeight() : $(win).height(),
			difference = (relHeight - minuend) / 2;

		return difference < 0 ? (over ? 0 : difference) : difference;
	},

	/**
	 * 取得对象相对位置
	 *
	 * @param ele 当前对象
	 * @param rel 相对对象, 为空时返回当前对象相对屏幕位置
	 */
	Util.getRelativePos = function(ele, rel) {
		var x = ele.offset().left,
			y = ele.offset().top;
		var isRel = Util.isObj(rel);

		return {
			x: isRel ? x - rel.offset().left : x,
			y: isRel ? y - rel.offset().top : y
		};
	},

	/**
	 * 移除对象的行内样式
	 */
	Util.removeStyle = function(ele) {
		Util.isExist(ele) && ele.removeAttr("style");
	},

	/**
	 * 移除对象的类和行内样式
	 */
	Util.removeStyles = function(ele, css) {
		Util.isExist(ele) && ele.removeClass(css) && Util.removeStyle(ele);
	},

	/**
	 * 添加唯一样式,删除兄弟节点样式
	 */
	Util.addUqClass = function(ele, css) {
		Util.isExist(ele) && ele.addClass(css).siblings().removeClass(css);
	}

}(jQuery);