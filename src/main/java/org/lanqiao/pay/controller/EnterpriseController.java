/**   
* @Title: EnterpriseController.java 
* @Package org.lanqiao.pay.controller 
* @Description: TODO
* @author 西安工业大学蓝桥一期--王增   
* @date 2017年4月25日 下午4:47:44    
*/

package org.lanqiao.pay.controller;



import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.lanqiao.pay.base.entity.EnterpriseBankCard;
import org.lanqiao.pay.base.entity.EnterpriseUser;
import org.lanqiao.pay.base.entity.forEnterpriseRegist.A;
import org.lanqiao.pay.base.entity.forEnterpriseRegist.B;
import org.lanqiao.pay.base.entity.forEnterpriseRegist.C;
import org.lanqiao.pay.base.entity.forEnterpriseRegist.D;
import org.lanqiao.pay.base.util.FileUploadUtil;
import org.lanqiao.pay.base.util.MyUtils;
import org.lanqiao.pay.serviceImpl.EnterpriseServiceImpl;
import org.lanqiao.pay.serviceImpl.EnterpriseUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 王增
 */
@SessionAttributes(value={"enterpriseUser","enterpriseBankCard"})
@Controller
@RequestMapping("/enterprise")
public class EnterpriseController {

	//指定了enterprise/下的jsp
    private  final String  DIR = "enterprise/";
	@Autowired
	EnterpriseServiceImpl enterpriseServiceImpl;
	
	@Autowired
	EnterpriseUserServiceImpl enterpriseUserServiceImpl;
	
	/**
	 * 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: validateEmaliIsUseful 
	* @Description: TODO 验证邮箱是否重复
	* @param @param response
	* @param @param email 从前台的ajax发过来的email的值
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @date 2017年4月25日 下午5:15:23 
	* @throws
	 */
	@RequestMapping("/validateEmaliIsUseful")
	public  void validateEmaliIsUseful(HttpServletResponse response,
						@RequestParam("email")String email) throws IOException{
			EnterpriseUser enterpriseUser = enterpriseUserServiceImpl.selectEnterpriseUserByEmail(email);
			System.out.println("email:"+email);
			System.out.println(enterpriseUser);
			PrintWriter out = response.getWriter();
			if(enterpriseUser == null){//说明没有找到,则邮箱没人用过
				out.print(true);
			}else{
				out.print(false);
			}
			
	}
	/**
	 * @throws IOException 
	 * 
	 * 
	* @author 西安工业大学蓝桥一期--王荣飞
	* @Title: verifyEnterpriseNameOnly 
	* @Description: TODO
	* @param @param name    设定文件 
	* @return void
	* @date 2017年4月27日 下午4:44:40 
	* @throws
	 */
	@RequestMapping("/verifyEnterpriseNameOnly")
	public void verifyEnterpriseNameOnly(@RequestParam("name") String name,HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		int i=enterpriseServiceImpl.verifyEnterpriseNameOnly(name);
		String msg="";
		if(i>0){
			msg="该名称已被占用";
			out.print(msg);
		}else{
			msg="";
			out.print(msg);
		}
		 
}

	
	
	

	/**
	 * 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: toRegist 
	* @Description: TODO 去注册页面,先放一个空对象,方便前台回显modelAttribute
	* @param @param map
	* @param @return    设定文件 
	* @return String    返回类型 
	* @date 2017年4月27日 上午9:51:38 
	* @throws
	 */
	@RequestMapping("/toRegistA")
	public String toRegist(Map<String,Object> map){
		EnterpriseUser enterpriseUser = new EnterpriseUser();
		A a = new A();
		map.put("a", a);
		map.put("enterpriseUser", enterpriseUser);
		return DIR+"reg_company_a";
	}
	/**
	 * 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: registA 
	* @Description: TODO 从A注册页面过来的信息进行验证,信息正确的话就会给session中的enterpriseUser附上
	* @param @param a
	* @param @param bindingResult
	* @param @param session
	* @param @param map
	* @param @return    设定文件 
	* @return String    返回类型 
	* @date 2017年4月28日 下午7:19:41 
	* @throws
	 */
	@RequestMapping(value="/registA",method=RequestMethod.POST)
	public String registA(@Valid A a,BindingResult bindingResult,
							HttpSession session,Map<String,Object> map){
		System.out.println(a);
		EnterpriseUser enterpriseUser = (EnterpriseUser) session.getAttribute("enterpriseUser");
		System.out.println(enterpriseUser);
		if(bindingResult.getErrorCount() > 0)//出错了!
		{
			System.out.println("//出错了!");
			List<FieldError> fieldErrors = bindingResult.getFieldErrors();
			for (FieldError fieldError : fieldErrors) {
				System.out.println(fieldError.getObjectName()+":"+fieldError.getField()+":"+fieldError.getDefaultMessage());
			}
			//出错则转向原页面
			return DIR+"reg_company_a";
		}
		//没出错的话.那么就将正确的email放入enterpriseUser中
		enterpriseUser.setEmail(a.getEmail());
		//EnterpriseUser enterpriseUser2 = (EnterpriseUser) session.getAttribute("enterpriseUser");
		//System.out.println("session中的正确邮件的:"+enterpriseUser2);
		B b = new B();
		map.put("b", b);
		return DIR+"reg_company_b";	
		
	}
	
	/**
	 * 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: registB 
	* @Description: TODO  从B注册页面过来的信息进行验证,信息正确的话就会给session中的enterpriseUser附上
	* @param @param b
	* @param @param bindingResult
	* @param @param session
	* @param @param map
	* @param @param file
	* @param @return    设定文件 
	* @return String    返回类型 
	* @date 2017年4月29日 下午3:01:44 
	* @throws
	 */
	@RequestMapping(value="/registB",method=RequestMethod.POST)
	public String registB(@Valid B b,BindingResult bindingResult,HttpSession session
						,Map<String,Object> map,@RequestParam("file1") MultipartFile file1,
						@RequestParam("file2") MultipartFile file2,@RequestParam("file3") MultipartFile file3,
						@RequestParam("file4") MultipartFile file4,@RequestParam("file5") MultipartFile file5,
						@RequestParam("file6") MultipartFile file6){
		EnterpriseUser enterpriseUser = (EnterpriseUser) session.getAttribute("enterpriseUser");
		System.out.println(enterpriseUser);
		if(bindingResult.getErrorCount() > 0)//出错了!
		{
			System.out.println("//出错了!");
			List<FieldError> fieldErrors = bindingResult.getFieldErrors();
			for (FieldError fieldError : fieldErrors) {
				System.out.println(fieldError.getObjectName()+":"+fieldError.getField()+":"+fieldError.getDefaultMessage());
			}
			//出错则转向原页面
			return DIR+"reg_company_b";
		}
		//测试文件上传功能/enterpriseFile
		String businessLicense = FileUploadUtil.FileUpload(file1, session, "/enterpriseFile");
		String allInOneLicense = FileUploadUtil.FileUpload(file2, session, "/enterpriseFile");
		String organizationCodeCertificate = FileUploadUtil.FileUpload(file3, session, "/enterpriseFile");
		String businessLicense2 = FileUploadUtil.FileUpload(file4, session, "/enterpriseFile");
		String certificatePhotoPositive = FileUploadUtil.FileUpload(file5, session, "/enterpriseFile");
		String certificatePhotoReserse = FileUploadUtil.FileUpload(file6, session, "/enterpriseFile");
		//下面就可以将这个filePath给set到某个对应的文件属性上了
		b.getEnterpriseUnit().setBusinessLicense(businessLicense);
		b.getEnterpriseUnit().setAllInOneLicense(allInOneLicense);
		b.setOrganizationCodeCertificate(organizationCodeCertificate);
		b.setBusinessLicense(businessLicense2);
		b.getLegalRepresentative().setCertificatePhotoPositive(certificatePhotoPositive);
		b.getLegalRepresentative().setCertificatePhotoReserse(certificatePhotoReserse);
		System.out.println(b);
		//将正确的表单信息放入enterpriseUser
		enterpriseUser.setName(b.getName());
		enterpriseUser.setTel(b.getTell());
		enterpriseUser.setAddress(b.getUserAddress());
		enterpriseUser.setIdentity(b.getIdentity());
		enterpriseUser.getEnterprise().setEnterpriseName(b.getEnterpriseName());
		enterpriseUser.getEnterprise().setLicenseType(b.getLicenseType());
		enterpriseUser.getEnterprise().setOrganizationCodeCertificate(b.getOrganizationCodeCertificate());
		enterpriseUser.getEnterprise().setBusinessLicense(b.getBusinessLicense());
		enterpriseUser.getEnterprise().setLicenseRegistrationNumber(b.getBuscode());
		enterpriseUser.getEnterprise().setAddress(b.getAddress());
		enterpriseUser.getEnterprise().setBusinessScope(b.getBusinessScope());
		enterpriseUser.getEnterprise().setBusinessTerm(b.getBusinessTerm());
		enterpriseUser.getEnterprise().setOrganizationCodeNumber(b.getOrganizationCodeNumber());
		enterpriseUser.getEnterprise().setEnterpriseUnit(b.getEnterpriseUnit());
		enterpriseUser.getEnterprise().setLegalRepresentative(b.getLegalRepresentative());
		System.out.println(enterpriseUser);
		C c=new C();
		map.put("c", c);
		return DIR+"reg_company_c";				//由于在Request中没有enterpriseUser,就会取session中的
	}
	
	@RequestMapping("/toRegistD")
	public String toRegistD(Map<String,Object> map){
		D d = new D();
		map.put("d", d);
		return DIR+"reg_company_d";
	}
	
	@RequestMapping("/registD")
	public String registD(@Valid D d,BindingResult bindingResult,Map<String,Object> map,
							HttpSession session){
		EnterpriseUser enterpriseUser = (EnterpriseUser) session.getAttribute("enterpriseUser");
		System.out.println("d:"+d);
		if(bindingResult.getErrorCount() > 0)//出错了!
		{
			System.out.println("//出错了!");
			List<FieldError> fieldErrors = bindingResult.getFieldErrors();
			for (FieldError fieldError : fieldErrors) {
				System.out.println(fieldError.getObjectName()+":"+fieldError.getField()+":"+fieldError.getDefaultMessage());
			}
			//出错则转向原页面
			return DIR+"reg_company_d";
		}
		enterpriseUser.setD(d);
		System.out.println("++++enterpriseUser:"+enterpriseUser);
		enterpriseUserServiceImpl.finalRegistStep(enterpriseUser);
		return DIR+"reg_company_e";
	}
	
	/**
	 * 从个人安全中心到企业安全中心
	 * @author 孙航建
	 * @time 2017年4月29日 下午12:09:10
	 * @return
	 */
	@RequestMapping("to_EnterpriseSafeCenter")
	public String to_EnterpriseSafeCenter(){
		return "enterprise/enterpriseSafeCenter";
	}
	
	/**
	 * 验证邮箱是否正确
	 * @author 孙航建
	 * @time 2017年5月1日 上午11:49:48
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/isUsedEmail")
	public void isUsedEmail(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String userName = request.getParameter("userName");
		EnterpriseUser enterpriseUser = enterpriseUserServiceImpl.getEnterpriseUserByEmail(userName);
		String msg = "";
		if(enterpriseUser == null){
			msg = "邮箱输入不正确";
		}else if(enterpriseUser != null){
			msg = "邮箱输入正确";
		}
		out.print(msg);
	}
	
	
	/**
	 * 通过企业法定人的email更新支付密码
	 * @author 孙航建
	 * @time 2017年4月29日 下午6:33:50
	 * @param enuser
	 * @return
	 */
	@RequestMapping(value = "/UpdatePwdByEmail",method= RequestMethod.POST)
	public String UpdatePwdByEmail(HttpServletRequest request){
		EnterpriseUser enUser2 = (EnterpriseUser) request.getSession().getAttribute("enterpriseUser");
		int id = enUser2.getId();
		String email = request.getParameter("email");
		String pwd = request.getParameter("newPayPassword");
		// 判断email是否与数据库中的保持一致
		boolean boo = enterpriseUserServiceImpl.isEmailTrueOrFalse(email,id);
		System.out.println("boo:" + boo);
		if(boo){
			// 通过email获取密保表
			EnterpriseUser enUser = enterpriseUserServiceImpl.getEnterpriseUserByEmail(email);
			// 通过secret的id更新支付密码pwd
			enterpriseUserServiceImpl.updateEnterpriseUserByEnUserId(pwd,enUser.getSecret().getId());
			return "enterprise/success_safe";
		} else{
			return "enterprise/getPwdByEmail";
		}
	}
	
	/**
	 * 登录时点击取消按钮回归企业安全中心
	 * @author 孙航建
	 * @time 2017年5月2日 下午7:54:15
	 * @return
	 */
	@RequestMapping("/to_safe")
	public String to_safe(){
		return "enterprise/enterpriseSafeCenter";
	}
	
    /**
     * 通过邮箱找回密码跳转页面
     * @author 孙航建
     * @time 2017年4月29日 下午5:55:27
     * @return
     */
	@RequestMapping("/getPwdByEmail")
	public String getPwdByEmail(){
		return "enterprise/getPwdByEmail";
	}
	
	/**
	 * 操作成功后返回企业安全中心首页
	 * @author 孙航建
	 * @time 2017年4月29日 下午9:02:22
	 * @return
	 */
	@RequestMapping("/to_enterpriseSafeCenter")
	public String to_enterpriseSafeCenter(){
		return "enterprise/enterpriseSafeCenter";
	}
	/**
	 * 
	* @author 西安工业大学蓝桥一期--何璐
	* @Title: toModifyE_loginPwd 
	* @Description: TODO  跳转到修改登陆密码的页面 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @date 2017年5月3日 下午3:47:51 
	* @throws
	 */
	@RequestMapping("/toModifyE_loginPwd")
	public String toModifyE_loginPwd(){
		return "enterprise/enterprise_loginPwd_reset";
	}
	/**
	 * 
	* @author 西安工业大学蓝桥一期--何璐
	* @Title: modifyE_loginPwd 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param id
	* @param @param req
	* @param @param res
	* @param @return    设定文件 
	* @return String    返回类型 
	* @date 2017年5月3日 下午3:48:02 
	* @throws
	 */
	@RequestMapping("/modifyE_loginPwd")
	public String modifyE_loginPwd(HttpServletRequest req,HttpServletResponse res){
		res.setCharacterEncoding("utf-8");
		String email = req.getParameter("email");
		String password = req.getParameter("loginPassword");
		String newPassword = req.getParameter("newPassword");
		EnterpriseUser enterpriseUser = (EnterpriseUser) req.getSession().getAttribute("enterpriseUser");
		//修改登陆密码
		int i = enterpriseUserServiceImpl.modifyLoginPwd(enterpriseUser.getId(), email, password, newPassword);
		//操作后状态消息
		String msg = "";
		//修改成功返回成功的状态消息
		if(i==1){
			msg = "登陆密码修改成功";
			req.setAttribute("msg", msg);
			return "enterprise/message";
			//修改失败的话判断是邮箱还是登陆密码输入有误并返回错误的状态消息
		}else {
			boolean boo = false;
			boolean b = enterpriseUserServiceImpl.isEmailTrueOrFalse(email, enterpriseUser.getId());
			if(boo==b){
				msg = "邮箱输入有误，操作失败";
			}else{
				msg = "登陆密码输入有误，操作失败";
			}
		}
		req.setAttribute("msg", msg);
		return "enterprise/message";
	}
	/**
	 * 
	* @author 西安工业大学蓝桥一期--何璐
	* @Title: toSafe 
	* @Description: TODO 操作完成后跳转回企业安全中心的页面
	* @param @return    设定文件 
	* @return String    返回类型 
	* @date 2017年5月3日 下午3:48:16 
	* @throws
	 */
	@RequestMapping("/returnToSafe")
	public String toSafe(){
		return "enterprise/enterpriseSafeCenter";
	}
	/**
	 * 
	* @author 西安工业大学蓝桥一期--何璐
	* @Title: reset 
	* @Description: TODO  点重置时跳转到新的修改页面
	* @param @return    设定文件 
	* @return String    返回类型 
	* @date 2017年5月3日 下午8:58:27 
	* @throws
	 */
	@RequestMapping("/to_reset")
	public String reset(){
		return "enterprise/enterprise_loginPwd_reset";
	}
	
	
	/**
	 * 
	 * 
	* @author 西安工业大学蓝桥一期--王荣飞
	* @Title: verifyIDCardOnly 
	* @Description: TODO
	* @param @param id
	* @param @param response
	* @param @throws IOException    设定文件 
	* @return void
	* @date 2017年5月7日 上午10:44:21 
	* @throws
	 */
	@RequestMapping("/verifyIDCardOnly")
	public void verifyIDCardOnly(@RequestParam("id") String id,HttpServletResponse response) throws IOException{
				response.setCharacterEncoding("utf-8");
				PrintWriter out=response.getWriter();
				int i=enterpriseServiceImpl.verifyIDCardOnly(id);
				String msg="";
				if(i>0){
					msg="该身份证已被注册";
					out.print(msg);
					
				}else{
					msg="";
					out.print(msg);
				}
		
	}
	/**
	 * 
	 * 
	* @author 西安工业大学蓝桥一期--王荣飞
	* @Title: verifyPhone 
	* @Description: TODO
	* @param @param phone
	* @param @param response
	* @param @throws IOException    设定文件 
	* @return void
	* @date 2017年5月7日 上午10:44:32 
	* @throws
	 */
	@RequestMapping("/verifyPhone")
	public void verifyPhone(@RequestParam("phone")String phone,HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		int i=enterpriseServiceImpl.verifyPhone(phone);
		String msg="";
		if(i>0){
			msg="该电话号码已被占用";
			out.print(msg);
		}else{
			msg="";
			out.print(msg);
		}
	}
	
	/**
	 * 
	* @author 西安工业大学蓝桥一期--王向宇
	* @Title: registC 
	* @Description: TODO  企业注册C页面
	* @param @param c
	* @param @param bindingResult
	* @param @param session
	* @param @param map
	* @param @return    设定文件 
	* @return String    返回类型 
	* @date 2017年5月7日 上午11:50:53 
	* @throws
	 */
	@RequestMapping(value="/registC",method=RequestMethod.POST)
	public String registC(@Valid C c,BindingResult bindingResult,
							HttpSession session,Map<String,Object> map){
		EnterpriseUser enterpriseUser = (EnterpriseUser) session.getAttribute("enterpriseUser");
		System.out.println(enterpriseUser);
		System.out.println(c);
		if(bindingResult.getErrorCount() > 0)//出错了!
		{
			System.out.println("//出错了!");
			List<FieldError> fieldErrors = bindingResult.getFieldErrors();
			for (FieldError fieldError : fieldErrors) {
				System.out.println(fieldError.getObjectName()+":"+fieldError.getField()+":"+fieldError.getDefaultMessage());
			}
			//出错则转向原页面
			return DIR+"reg_company_c";
		}
		System.out.println(c);
		//将获取到的密码进行二次加密
		String password=MyUtils.md5(c.getPassword());
		String payPassword=MyUtils.md5(c.getPayPassword());
		//将正确的表单信息填入enterpriseUser
		enterpriseUser.setPassword(password);
		enterpriseUser.getSecret().setPayPassword(payPassword);
		enterpriseUser.getSecret().setSecurityQuestion(c.getSecurityQuestion());
		enterpriseUser.getSecret().setClassifiedAnswer(c.getClassifiedAnswer());
		System.out.println(enterpriseUser);
		D d=new D();
		map.put("d", d);
		return DIR+"reg_company_d";	
	}
	

	/**
	 * 
	* @author 西安工业大学蓝桥一期--王向宇
	* @Title: toForgetPwd 
	* @Description: TODO  从企业注册页面转发到邮箱验证页面
	* @param @return    设定文件 
	* @return String    返回类型 
	* @date 2017年5月17日 下午6:10:21 
	* @throws
	 */
	@RequestMapping("toForgetPwd")
	public String toForgetPwd(){
		return DIR+"enterprise_forgetPwd_a";
	}
	
	/**
	 * 
	* @author 西安工业大学蓝桥一期--王向宇
	* @Title: forgetPwd 
	* @Description: TODO	邮箱验证通过后去修改密码页面
	* @param @param email
	* @param @param map
	* @param @return    设定文件 
	* @return String    返回类型 
	* @date 2017年5月17日 下午7:03:02 
	* @throws
	 */
	@RequestMapping("forgetPwd")
	public String forgetPwd(@RequestParam("email")String email,Map<String,Object> map){
		map.put("email", email);
		System.out.println("email1="+email);
		return DIR+"enterprise_forgetPwd_b";
	}
	
	/**
	 * 
	* @author 西安工业大学蓝桥一期--王向宇
	* @Title: changePwd 
	* @Description: TODO  修改密码页面
	* @param @param email
	* @param @param password
	* @param @return    设定文件 
	* @return String    返回类型 
	* @date 2017年5月17日 下午7:05:08 
	* @throws
	 */
	@RequestMapping("changePwd")
	public String changePwd(@RequestParam("email")String email,@RequestParam("password")String password,
			@RequestParam("password2")String password2,Map<String,Object> map){
		//修改登陆密码
		int i = enterpriseUserServiceImpl.changeLoginPwd(email, password, password2);
		String msg="";
		if(i==1){
			msg = "修改密码成功";
			map.put("msg",msg);
			return DIR+"enterprise_forgetPwd_c";
		}else{
			msg = "修改密码失败";
			map.put("msg", msg);
			return DIR+"enterprise_forgetPwd_c";
		}
	}
}
