package org.lanqiao.pay.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.pay.base.entity.EnterpriseUser;
import org.lanqiao.pay.base.entity.Secret;
import org.lanqiao.pay.base.entity.User;
import org.lanqiao.pay.serviceImpl.EnterpriseUserServiceImpl;
import org.lanqiao.pay.serviceImpl.SecretServiceImpl;
import org.lanqiao.pay.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**   
 * @Title: SecretController.java 
 * @Package org.lanqiao.pay.controller 
 * @Description: TODO 
 * @author 西安工业大学蓝桥一期--毋泽航  
 * @date 2017年4月25日 下午4:12:41    
 */
@RequestMapping("/secretController")
@Controller
public class SecretController {
	@Autowired
	UserServiceImpl userServiceImpl;
	@Autowired
	SecretServiceImpl secretServiceImp;
	@Autowired
	EnterpriseUserServiceImpl enterpriseUserServiceImpl;
	
	/**
	 * 
	 * @author 西安工业大学蓝桥一期--毋泽航
	 * @Title: skipUpdatePayPwd 
	 * @Description: 跳转到修改支付密码页面
	 * @date 2017年4月25日 下午6:11:37 
	 * @throws
	 */
	@RequestMapping("/skipUpdatePayPwd")
	public String skipUpdatePayPwd(){
		return "user/security_choose_type";
	}
	
	/**
	 * @author 西安工业大学蓝桥一期--毋泽航
	 * @Title: resetPayPwdByProtect 
	 * @Description: 跳转到密保问题重置密码页面
	 * @date 2017年4月30日 下午7:03:23 
	 * @throws
	 */
	@RequestMapping("/skipResetPayPwdByProtect")
	public String skipResetPayPwdByProtect(@RequestParam(value="id")Integer id, Map<String, Object> map){
		Secret secret = secretServiceImp.getSecretById(id);
		String question = secret.getSecurityQuestion();
		map.put("securityQuestion",question);
		return "enterprise/security_protectVerity_enterprise";
	}
	
	/**
	 * 
	 * @author 西安工业大学蓝桥一期--孙航建
	 * @Title: skipUpdatePayPwd 
	 * @Description: 跳转到修改支付密码页面
	 * @date 2017年4月29日 下午4:11:37 
	 * @throws
	 */
	@RequestMapping("/safe_secret_enterprise")
	public String safe_secret_enterprise(){
		return "enterprise/safe_Enterprise_Secret";
	}
	
	/**
	 * 
	 * @author 西安工业大学蓝桥一期--孙航建
	 * @Title: skipUpdatePayPwd 
	 * @Description: 跳转到修改支付密码页面
	 * @date 2017年4月29日 下午4:11:37 
	 * @throws
	 */
	@RequestMapping("/skipUpdatePayPwd_enterprise")
	public String skipUpdatePayPwd_enterprise(){
		return "enterprise/security_choose_type_enterprise";
	}
	/**
	 * @throws IOException 
	 * @author 西安工业大学蓝桥一期--毋泽航
	 * @Title: validatePayPwd 
	 * @Description: 支付密码的验证
	 * @date 2017年4月25日 下午6:12:04 
	 */
	@RequestMapping("/validatePayPwd")
	public void validatePayPwd(HttpServletResponse response, 
					@RequestParam(value="id")Integer id,
					@RequestParam(value="payPassword")String pwd) throws IOException{
		PrintWriter out = response.getWriter();
		boolean b = secretServiceImp.isPayPwd(id, pwd);
		if(b){
			out.write("true");
		}else{
			out.write("false");
		}
	}
	
	/**
	 * @throws IOException 
	 * @author 西安工业大学蓝桥一期--毋泽航
	 * @Title: validateSecurity 
	 * @Description: 密保的验证 
	 * @date 2017年4月30日 下午7:31:15 
	 * @throws
	 */
	@RequestMapping("/validateSecurity")
	public void validateSecurity(HttpServletResponse response,
						@RequestParam(value="id")Integer id,
						@RequestParam(value="classifiedAnswer")String classifiedAnswer) throws IOException{
		System.out.println(id+classifiedAnswer);
		PrintWriter out = response.getWriter();
		boolean b = secretServiceImp.isSecurity(id, classifiedAnswer);
		if(b){
			out.write("true");
		}else{
			out.write("false");
		}
	}
	
	/**
	 * @author 西安工业大学蓝桥一期--毋泽航
	 * @Title: updatePayPwd 
	 * @Description: 修改支付密码 
	 * @param  id :Secret 的 id
	 * @param  pwd
	 * @date 2017年4月25日 下午6:12:39 
	 * @throws
	 */
	@RequestMapping("/updatePayPwd")
	public String updatePayPwd(@RequestParam(value="id")Integer id, @RequestParam(value="newPayPassword")String pwd){
		secretServiceImp.updatePayPwd(id, pwd);
		return "secret_success";
	}
	
	
	/**
	* @author 西安工业大学蓝桥一期--孙航建
	* @Title: safe_secret 
	* @Description:验证码 
	* @param @param  id
	* @param @param map
	* @param @return
	* @param @throws 
	* @return String
	* @date 2017年4月24日 下午23:57:44 
	* @throws
	 */
	@RequestMapping("/safe_secret")
	public String safe_secret(){
		return "user/safeSecret";
	}
	
	/**
	* @author 西安工业大学蓝桥一期--孙航建
	* @Title: updateSecretBySecret 
	* @Description:设置数据表密保及问题
	* @param @param  secret
	* @param @return
	* @param @throws 
	* @return String
	* @date 2017年4月24日 下午23:57:44 
	* @throws
	 */
	@RequestMapping("/updateSecret")
	public String updateSecretBySecret(HttpServletRequest request){
		EnterpriseUser enUser = (EnterpriseUser) request.getSession().getAttribute("enterpriseUser");
		User u = (User)request.getSession().getAttribute("user");
		String payPassword = request.getParameter("payPassword");
		String securityQuestion = request.getParameter("securityQuestion");
		String classifiedAnswer = request.getParameter("classifiedAnswer");
		if(enUser != null){
			Secret secret1 = new Secret(enUser.getSecret().getId(), payPassword, securityQuestion, classifiedAnswer);
			int i = secretServiceImp.updatePaySecret(secret1);
			if(i==1){
				return "secret_success";
			} else {
				return "user/safeSecret";
			}
		}
		if(u != null){
			Secret secret2 = new Secret(u.getSecretId().getId(), payPassword, securityQuestion, classifiedAnswer);
			int j = secretServiceImp.updatePaySecret(secret2);
			if(j == 1){
				return "secret_success";
			}else {
				return "user/safeSecret";
			}
		}
		return "user/safeSecret";
	}
	/**
	* @author 西安工业大学蓝桥一期--孙航建
	* @Title: to_safe 
	* @Description:数据库设置成功之后返回安全中心的首页
	* @param @return
	* @param @throws 
	* @return String
	* @date 2017年4月26日 下午14:18:44 
	* @throws
	 */
	@RequestMapping("/to_safe")
	public String to_safe(){
		return "user/safe";
	}
	/**
	 * @author 西安工业大学蓝桥一期-代显峰
	 * @title updateEmail
	 * @description	跳转到修改邮箱的页面
	 * @param @return
	 * @return String
	 * @date 2017年5月2日 下午8:11:28
	 * @throws
	 */
	@RequestMapping("/updateEmail")
	public String updateEmail(){
		return "enterprise/security_bound_mailbox";
		
	}
	/**
	 * @throws IOException
	 * @author 西安工业大学蓝桥一期-代显峰
	 * @title updateEmail
	 * @description 修改绑定的邮箱
	 * @param @param id
	 * @param @param payPassword
	 * @param @param email
	 * @param @return
	 * @return String
	 * @date 2017年5月2日 下午5:54:17
	 */
	@RequestMapping(value = "/toUpdateEmail", params = { "secretId", "email" })
	public String update(Integer secretId,  String email) {
		enterpriseUserServiceImpl.update(secretId,email); 
		// 去成功页面
		return "secret_success";
	}

	
	/**
	 * @author 西安工业大学蓝桥一期-代显峰
	 * @title registEmail
	 * @description	邮箱验证
	 * @param @param reqeust
	 * @param @param response
	 * @param @throws IOException
	 * @return void
	 * @date 2017年4月28日 下午8:30:01
	 * @throws
	 */
	@RequestMapping("/registEmail")
	public void registEmail(HttpServletRequest reqeust, HttpServletResponse response) throws IOException {
		// 设置字符集 
		response.setCharacterEncoding("utf-8");
		// 获取PrintWriter对象以返回数据
		PrintWriter out = response.getWriter();
		/*String secretId = reqeust.getParameter("secretId");
		Integer id = Integer.parseInt(secretId);*/
		String email2 = enterpriseUserServiceImpl.getEmail(4);
		// 获取用户填入的邮箱
		String email = reqeust.getParameter("email");
		// 页面ajax返回的内容
		String msg = "";
		if (email2 == email) {
			// 该用户不存在
			msg = "您输入的邮箱不正确！";
		}else{
			// 邮箱验证规则
	        Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
	        // 编译正则表达式
	        Matcher matcher = pattern.matcher(email);
	        // 字符串是否与正则表达式相匹配 
	        boolean rs=matcher.matches();
	        if(rs==true){
	        	msg="邮箱格式正确";
	        }else{
	        	msg="邮箱格式不正确";
	        }
		}
		out.print(msg);

	}
	

	
	
	
	/**
	 * 
	* @Title: SecretController.java 
	* @Package org.lanqiao.pay.controller 
	* @Description: 跳转到通过密保重置密码页面，获取密保问题并放入到域中
	* @author 西安工业大学蓝桥一期--董正健   
	* @date 2017年5月3日 下午8:35:08
	 */
	@RequestMapping("/toReSettingPasswordByProtect")
	public String toResettingPwd(Map<String, Object> map,HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("user");
		Secret secret = secretServiceImp.getSecretById(user.getSecretId().getId());
		String question = secret.getSecurityQuestion();
		map.put("secretQuestion", question);
		return "user/security_protectVerity_step_a";
	}
	
	/**
	 * 
	* @Title: SecretController.java 
	* @Package org.lanqiao.pay.controller 
	* @Description: 通过密保重置用户支付密码
	* @author 西安工业大学蓝桥一期--董正健   
	* @date 2017年5月3日 下午8:51:24
	 */
	@RequestMapping("/resetingPwdByProtect")
	public String resetingPasswordByProtect(@RequestParam(value="id")Integer id,@RequestParam(value="newPayPassword")String payPassword){
		secretServiceImp.updatePayPwd(id, payPassword);
		return "secret_success";
	}
}
