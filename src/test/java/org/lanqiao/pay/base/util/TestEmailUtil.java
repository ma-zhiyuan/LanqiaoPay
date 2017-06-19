package org.lanqiao.pay.base.util;

import javax.mail.MessagingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class TestEmailUtil {
	@Autowired
	JavaMailSender sender;
	@Test
	public void testSendEmail(){
		EmailUtil.sendEmail("maazhiyuan@163.com", "测试", "验证码");
	}
	
	@Test
	public void testSendHtmlEmail(){
		EmailUtil.sendHtmlEmail("maazhiyuan@163.com", "测试", "213213123","80", "23");
	}
	

	@Test
	public void sendEmailTest() throws MessagingException {
		String toEmail = "maazhiyuan@163.com";
		String title = "ceshi";
		String content = "213123";
		EmailUtil.sendEmail(toEmail, title, content, sender);
	}
}
