package org.lanqiao.pay.base.util;

import java.io.UnsupportedEncodingException;
import java.net.*;

/**
 * 二维码API
 * @author mazhiyuan
 *
 */
public class QRUtil {
	
	public static final String API = "http://qr.topscan.com/api.php?"; 
	
	//bg	背景颜色			bg=颜色代码			例如：bg=ffffff
	//fg	前景颜色			fg=颜色代码			例如：fg=cc0000
	//gc	渐变颜色			gc=颜色代码			例如：gc=cc00000
	//el	纠错等级			el可用值：h\q\m\l		例如：el=h
	//w		尺寸大小			w=数值（像素）			例如：w=300
	//m		静区（外边距）		m=数值（像素）			例如：m=30
	//pt	定位点颜色（外框）	pt=颜色代码			例如：pt=00ff00
	//inpt	定位点颜色（内点）	inpt=颜色代码			例如：inpt=000000
	//logo	logo图片			logo=图片地址			例如：logo=http://www.ifma.me/1.png
	
	/**
	* @author 西安工业大学蓝桥一期--马志远
	* @Title: getQRCodeURL 
	* @Description: 传入的参数最少的形式为  text=123  等号后可为字符串或网址（必须以http://开头）
	* 				也可参照上方参数表再加参数 例如：text=www.lanqiao.org&bg=ffffff&fg=cc0000
	* @date 2017年5月7日 下午5:58:04
	 */
	public static String getQRCodeURL(String param) {
		StringBuffer QRURL = new StringBuffer();
		QRURL.append(API);
		if("".equals(param)){
			QRURL.append("text=http://www.lanqiao.org&bg=ffffff&fg=cc0000");
		}
		QRURL.append(param);
		return QRURL.toString();
	} 
}
