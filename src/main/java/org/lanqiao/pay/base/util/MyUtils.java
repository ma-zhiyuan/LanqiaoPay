package org.lanqiao.pay.base.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.lanqiao.pay.base.entity.BankCard;

public class MyUtils {

	/**
	 * @author 西安工业大学蓝桥一期--马志远
	 * @Title: getGenericClass
	 * @Description: 获取父类范型
	 * @param @param
	 *            c
	 * @param @return
	 * @return Class
	 * @date 2017年4月23日 上午10:34:53
	 */
	public static Class getGenericClass(Class c) {
		ParameterizedType pt = (ParameterizedType) c.getGenericSuperclass();
		Type t = (Type) pt.getActualTypeArguments()[0];
		Class clazz = (Class) t;
		return clazz;
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--马志远
	 * @Title: sha1
	 * @Description: sha1加密
	 * @param @param
	 *            pwd
	 * @param @return
	 * @return String
	 * @date 2017年4月23日 上午10:35:20
	 */
	public static String sha1(String pwd) {
		try {
			MessageDigest sha1 = MessageDigest.getInstance("sha1");
			byte[] digest = sha1.digest(pwd.getBytes());
			String str = byteToHexStr(digest);
			return str;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--马志远
	 * @Title: md5
	 * @Description: MD5加密
	 * @param @param
	 *            pwd
	 * @param @return
	 * @return String
	 * @date 2017年4月23日 上午10:35:34
	 */
	public static String md5(String pwd) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("md5");
			byte[] digest = md5.digest(pwd.getBytes());
			String str = byteToHexStr(digest);
			return str;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--马志远
	 * @Title: byteToHexStr
	 * @Description: byteToHexStr用于各种加密算法原数据
	 * @param @param
	 *            digest
	 * @param @return
	 * @return String
	 * @date 2017年4月23日 上午10:35:48
	 */
	public static String byteToHexStr(byte[] digest) {
		char[] c = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		StringBuffer sb = new StringBuffer();
		for (byte b : digest) {
			sb.append(c[(b >> 4) & 15]);
			sb.append(c[b & 15]);
		}
		return sb.toString();
	}

	public static int getRandom() {
		return (int) (Math.random() * 1000000);
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--王增 @Title: strToUtilDate @Description: TODO
	 * 字符串转为utilDATE @param @param dateStr @param @return @param @throws
	 * ParseException 设定文件 @return Date 返回类型 @date 2017年5月3日 下午7:10:31 @throws
	 */
	public static Date strToUtilDate(String dateStr) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.parse(dateStr);
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--王增 @Title: dateToString @Description: TODO
	 * utilDATE转为字符串 @param @param date @param @return 设定文件 @return String
	 * 返回类型 @date 2017年5月3日 下午7:10:45 @throws
	 */
	public static String dateToString(Date date) {
		String res = "";
		if(date!=null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			res = sdf.format(date);
		}
		return res;
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--王增 @Title: dateAndTimeToString @Description: TODO
	 * utilDATE转为字符串,同时带有时间 @param @param date @param @return 设定文件 @return
	 * String 返回类型 @date 2017年5月3日 下午7:10:53 @throws
	 */
	public static String dateAndTimeToString(Date date) {
		String dateAndTimeStr = "";
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			dateAndTimeStr = sdf.format(date);
		}
		return dateAndTimeStr;
	}

	public static String dateAndTimeDateToStringNoSeconds(Date date) {
		String dateAndTimeStr = "";
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			dateAndTimeStr = sdf.format(date);
		}
		return dateAndTimeStr;
	}
	/**
	 * 
	 * @author 西安工业大学蓝桥一期--王增 @Title: dateAndTimestringToDate @Description: TODO
	 * 日期时间字符串转为date @param @param dateAndTimeStr @param @return @param @throws
	 * ParseException 设定文件 @return Date 返回类型 @date 2017年5月3日 下午7:17:56 @throws
	 */
	public static Date dateAndTimeStringToDate(String dateAndTimeStr) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.parse(dateAndTimeStr);
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--王增 @Title: stringIsIntger @Description: TODO
	 * 判断字符串是否是一个正整数 @param @param numberStr @param @return 设定文件 @return boolean
	 * 返回类型 @date 2017年5月4日 下午5:50:37 @throws
	 */
	public static boolean stringIsIntger(String numberStr) {
		boolean res = false;
		Pattern p = Pattern.compile("^[0-9]*$");
		Matcher m = p.matcher(numberStr);
		if (m.matches()) {// 此时还存在一种是00的这种可能
			Integer i = Integer.parseInt(numberStr);
			if (i != 0) {
				res = true;
			}
		}
		return res;
	}

	/**
	 * @author 西安工业大学蓝桥一期--刘江 @Title: cardNumber @Description:
	 * 对银行卡进行中部加密 @return String @date 2017年5月8日 下午6:15:24 @throws
	 */
	public List<BankCard> cardNumberEncryption(List<BankCard> listBankCard) {
		for (int i = 0; i < listBankCard.size(); i++) {
			String cardN = listBankCard.get(i).getCardNumber();
			StringBuffer sb = new StringBuffer(cardN);
			for (int j = 4; j < sb.length() - 4;j++) {
				sb.replace(j, j + 1, "*");
			}
		listBankCard.get(i).setCardNumber(	sb.substring(0, sb.length()));
		}
		
		
	
		return listBankCard;
	}

	public static String phoneNumberForHidden(String phone){
		if(phone.length()==11){
			String head = phone.substring(0, 3);
			String trail = phone.substring(7, 11);
			return head+"****"+trail;
		}
		return phone;
	}
	
	public static String emailToHidden(String email){
		int i = email.indexOf("@");
		String head = email.substring(0, i);
		String trail = email.substring(i);
		System.out.println("head"+head);
		String dealedHead = "";
		String headXing = "";
		int key = head.length();
		if(key>=10){
			dealedHead = head.substring(0, 4);//提取前四位非*的;
			headXing = getStartString(key-4);
		}else if(key>=6){
			dealedHead = head.substring(0, 3);//提取前三位非*的;
			headXing = getStartString(key-3);
		}else if(key>=3){
			dealedHead = head.substring(0,2 );//提取前二位非*的;
			headXing = getStartString(key-2);
		}
		return dealedHead+headXing+trail;
	}
	
	public static String getStartString(int n){
		String startStr = "";
		for(int i = 0 ;i<n;i++)
			startStr  += "*";
			return startStr;
	}
	public static void main(String[] args) {
		//System.out.println(phoneNumberForHidden("18829897334"));
	System.out.println(emailToHidden("15812345@qq.com"));	
	}
}
