/**   
* @Title: EmailUtil.java 
* @Package org.lanqiao.pay.base.util 
* @Description: TODO
* @author 西安工业大学蓝桥一期--王增   
* @date 2017年4月24日 上午10:03:35    
*/

package org.lanqiao.pay.base.util;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * @author 王增
 *
 */
public class EmailUtil {
	/**
	 * 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: sendEmail 
	* @Description: TODO
	* @param @param reciver
	* @param @param subject
	* @param @param content
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @date 2017年4月24日 上午10:11:19 
	* @throws
	 */
	public static boolean sendEmail(String reciver,String subject,String content){
		Email e=new SimpleEmail();
    	try {
    		// 设置使用发送邮件的邮件服务器若使用qq是smtp.qq.com前提是qq已经开通smtp服务
    		e.setHostName("smtp.qq.com");
            //防止中文乱码
    		e.setCharset("utf-8");
    		e.setSmtpPort(465);
    		//收件人的信箱
			e.addTo(reciver);
			//发件人信箱和密码
			e.setAuthentication("2502101454@qq.com", "kgmaccdmsyaudjge");
			//发信人的信箱和别名
			e.setFrom("2502101454@qq.com","meeting服务中心","utf-8");
			e.setSSLOnConnect(true);
			//主题
			e.setSubject(subject);
			//消息内容
			e.setMsg("验证码(不要告诉他人):"+content);
			e.send();
		} catch (EmailException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
    	return true;
	}
	/**
	 * 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: sendHtmlEmail 
	* @Description: TODO
	* 前台的邮箱注册时候,会在前台页面随机生成一个数字串(new Date().getTime()),然后点击发送邮箱建,这个是Ajax请求
	* 到带着这个随机串后台,调用这个方法,发邮件带随机串,发送成功的话,那就给前台显示发送成功,且做一个60s后重新发送的效果,用户
	* 去邮箱验证,点击超链接,去controller层的某个方法中,将这个随机串取出来放入application中,然后前台点击下一步的时候
	* 应该去比较页面的随机串和application中的是否一致,相同才能够去下一个页面
	* @param @param reciver 接受者的邮箱地址
	* @param @param subject  主题,比如LanqiaoPay注册
	* @param @param ip  访问当前项目的ip地址
	* @param @param port 访问当前项目的服务器端口号
	* @param @param code 链接中的验证码
	* @param @return    设定文件 
	* @return boolean    成功返回true,失败返回false
	* @date 2017年4月24日 上午10:47:26 
	* @throws
	 */
	public static boolean sendHtmlEmail(String reciver,String subject,String ip,String port,String code){
		// 不要使用SimpleEmail,会出现乱码问题
		HtmlEmail e = new HtmlEmail();
		try {
			// 设置使用发送邮件的邮件服务器若使用qq是smtp.qq.com前提是qq已经开通smtp服务
    		e.setHostName("smtp.163.com");
            //防止中文乱码
    		e.setCharset("utf-8");
    		e.setSmtpPort(465);
    		//收件人的信箱
			e.addTo(reciver);
			//发件人信箱和密码
			e.setAuthentication("lanqiaozhifu@163.com", "lanqiaopay");
			//发信人的信箱和别名
			e.setFrom("lanqiaozhifu@163.com","LanqiaoPay","utf-8");
			e.setSSLOnConnect(true);
			//主题
			e.setSubject(subject);
			
			String content = "<a href='http://"+ip+":"+port+"/LanqiaoPay/commonController/receive?emailCode="+code+"'>点击进行下一步</a>";
			//消息内容
			e.setMsg("验证链接(确保本人操作):"+content);
			e.send();
		} catch (Exception e1) {
			e1.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		System.out.println(sendHtmlEmail("2324701794@qq.com", "注册链接","127.0.0.1","8080","465"));
		//System.out.println(sendEmail("2324701794@qq.com", "注册验证", "456456"));
		
		
	}
	/**
	 * 
	* @Title: EmailUtil.java 
	* @Package org.lanqiao.pay.base.util 
	* @Description: 重载SendEmail方法 
	* @author 西安工业大学蓝桥一期--刘宣 
	* @param @param reciver 接受者的邮箱地址
	* @param @param subject  定义邮件的主题
	* @param @param ip  访问当前项目的服务器ip地址
	* @param @param port 访问当前项目的服务器端口号
	* @param @param projectName 当前项目的名字
	* @param @param url   邮件里的链接需要跳到的url
	* @param @param code 链接中的验证码
	* @date 2017年5月2日 下午12:36:00
	 */
	public static boolean sendHtmlEmail(String reciver,String subject,String code){
		// 不要使用SimpleEmail,会出现乱码问题
		HtmlEmail e = new HtmlEmail();
		try {
			// 设置使用发送邮件的邮件服务器
    		e.setHostName("smtp.163.com");
            //防止中文乱码
    		e.setCharset("utf-8");
    		e.setSmtpPort(465);
    		//收件人的信箱
			e.addTo(reciver);
			//发件人信箱和密码
			e.setAuthentication("lanqiaozhifu@163.com", "lanqiaopay");
			//发信人的信箱和别名
			e.setFrom("lanqiaozhifu@163.com","LanqiaoPay","utf-8");
			e.setSSLOnConnect(true);
			//主题
			e.setSubject(subject);
			//消息内容
			String content ="蓝桥支付验证码是："+code;
			e.setMsg(content);
			e.send();
		} catch (Exception e1) {
			e1.printStackTrace();
			return false;
		}
		return true;
	}
	
	public final static void sendEmail(String toEmail, String title, String content, JavaMailSender sender)
			throws MessagingException {
		MimeMessage messages = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(messages, true);
		helper.setFrom("ma@ifma.me");
		helper.setTo(toEmail);
		helper.setSubject(title);
		helper.setText(content);
		sender.send(messages);
	}
}
