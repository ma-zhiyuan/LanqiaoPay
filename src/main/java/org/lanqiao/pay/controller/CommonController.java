/**   
* @Title: CommonCotroller.java 
* @Package org.lanqiao.pay.controller 
* @Description: TODO
* @author 西安工业大学蓝桥一期--王增   
* @date 2017年4月24日 下午8:34:36    
*/

package org.lanqiao.pay.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.pay.base.util.EmailUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 王增
 *在这个控制器里来进行发送邮件等普部分用户角色的普通操作
 */
@Controller
@RequestMapping(value="/commonController")
public class CommonController {
	/**
	 * 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: sendAEmail 
	* @Description: TODO  发送邮件,
	* @param @param request
	* @param @param response
	* @param @throws IOException    设定文件 
	* @return void    返回类型 成功true失败时false
	* @date 2017年4月25日 下午4:36:26 
	* @throws
	 */
	@RequestMapping(value="/sendAEmail")
	public void sendAEmail(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//获得邮箱地址
		String email = request.getParameter("email").trim();
		//获取前台的token
		String token = request.getParameter("aCode").trim();
		System.out.println("emailTo:"+email);
		System.out.println("token:"+token);
		//把这个前台的随机串一并发给用户
		boolean res = EmailUtil.sendHtmlEmail(email, "LanqiaoPay", "123.206.71.118", "80", token);
		System.out.println("sendEmail:"+res);
		PrintWriter out = response.getWriter();
		out.print(res);
	}
	/**
	 * 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: receive 
	* @Description: TODO 用户的邮箱中点击了链接之后,会来到这个控制器,在这里会将拿到的token放入application中
	* 当用户点击下一步的时候,我们需要一个ajax来到后台,然后从application中去名为token值的属性值,如果和前台token一样,那就OK
	* @param @param request
	* @param @param response
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @date 2017年4月25日 下午4:37:16 
	* @throws
	 */
	@RequestMapping(value="/receive")
	public void receive(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String emailCode = request.getParameter("emailCode").trim();
		ServletContext context = request.getSession().getServletContext();
		//之所以放一个名为emailCode的emailCode值,是因为现在如果有很多用户都注册,他们点击链接了就会覆盖别人的
		context.setAttribute(emailCode, emailCode);
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		if(!emailCode.equals("")){
			out.print("感谢您对蓝桥支付平台的支持,现在请点击注册页面的下一步,进行资料完善!");
		}
		else{
			out.print("失效了,请重新发送验证链接!");
		}
	}
	/**
	 * 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: validateEmailCode 
	* @Description: TODO  前台点击下一步,ajax来到这个控制器,将前台的aCode(即token),和application中的进行比较,如果
	* 相等则验证通过.
	* @param     设定文件 
	* @return void    返回类型 
	* @date 2017年4月25日 下午6:45:40 
	* @throws
	 */
	@RequestMapping(value="/validateEmailCode")
	public void validateEmailCode(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String aCode = request.getParameter("aCode").trim();
		System.out.println("后台aCode:"+aCode);
		ServletContext context = request.getSession().getServletContext();
		String contextACode = null;
		Object attribute = context.getAttribute(aCode);
		if(attribute != null){
			contextACode = attribute.toString();
		}
		System.out.println("application中的contextACode:"+contextACode);
		PrintWriter out = response.getWriter();
		if(aCode.equals(contextACode)){
			System.out.println("true");
			out.print(true);
		}else{
			out.print(false);
		}
	}
	
}
