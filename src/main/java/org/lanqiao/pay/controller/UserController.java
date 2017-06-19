package org.lanqiao.pay.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.lanqiao.pay.base.bean.UserCreteria;
import org.lanqiao.pay.base.bean.UserTradePage;
import org.lanqiao.pay.base.entity.BankCard;
import org.lanqiao.pay.base.entity.EnterpriseUser;
import org.lanqiao.pay.base.entity.Recharge_withdrawal;
import org.lanqiao.pay.base.entity.Secret;
import org.lanqiao.pay.base.entity.Transfer;
import org.lanqiao.pay.base.entity.User;
import org.lanqiao.pay.base.util.EmailUtil;
import org.lanqiao.pay.base.util.FileUploadUtil;
import org.lanqiao.pay.base.util.MyUtils;
import org.lanqiao.pay.base.util.ValidateCode;
import org.lanqiao.pay.service.Recharge_withdrawalService;
import org.lanqiao.pay.service.TransferService;
import org.lanqiao.pay.serviceImpl.BankCardServiceImpl;
import org.lanqiao.pay.serviceImpl.SecretServiceImpl;
import org.lanqiao.pay.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Title: UserController.java
 * @Package org.lanqiao.pay.controller
 * @Description: 和前端交互
 * @author 西安工业大学蓝桥一期--刘江
 * @date 2017年4月23日 上午11:04:19
 */
@Controller
@SessionAttributes("reqUser")
@RequestMapping("/userController")
public class UserController {
	@Autowired
	UserServiceImpl userServiceImpl;
	@Autowired
	SecretServiceImpl secretServiceImpl;
	@Autowired
	BankCardServiceImpl bankCardServiceImpl;
	@Autowired
	Recharge_withdrawalService recharge_withdrawalService;
	@Autowired
	TransferService transferService;

	@RequestMapping("/resetPassword")
	public String resetPassword(HttpServletRequest request, Map<String, Object> map) {
		String id = request.getParameter("id");
		// 通过id找到该用户的密码并重置
		User user = userServiceImpl.getUser(Integer.parseInt(id));
		String cardId = user.getCardId();
		// 获取身份证后六位
		String password = cardId.substring(cardId.length() - 6, cardId.length());
		String rePassword = MyUtils.md5(password);
		// 去重置密码
		userServiceImpl.resetPassword(rePassword, user.getId());

		String pageNo = request.getParameter("pno") == null ? "1" : request.getParameter("pno");
		String orderby = request.getParameter("orderby") == null ? "id asc" : request.getParameter("orderby");
		String keyword = request.getParameter("keyword") == null ? "" : request.getParameter("keyword");
		// 获取封装好的userCreteria
		UserCreteria userCreteria = userServiceImpl.getuserByqualified(keyword, pageNo, orderby);
		// 获取符合所有条件的用户信息
		List<User> page = userServiceImpl.getPage(userCreteria);
		map.put("userCreteria", userCreteria);
		map.put("users", page);
		return "admin/user_listAll";
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期—陈楚 @Title: toUpdateUserPassword @Description:
	 * 去修改密码 @param @return 设定文件 @return String 返回类型 @date 2017年5月22日
	 * 下午8:55:21 @throws
	 */
	@RequestMapping("/toUpdateUserPassword")
	public String toUpdateUserPassword(HttpServletRequest request, Map<String, Object> map) {
		String id = request.getParameter("id");
		// 通过id找到该用户的身份证号
		User user = userServiceImpl.getUser(Integer.parseInt(id));
		map.put("user", user);
		return "admin/updateUserPassword";
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--陈楚
	 * @Title: getPage
	 * @Description: 分页的方法
	 * @param @param
	 *            page
	 * @param @return
	 * @return String
	 * @date 2017年5月4日 下午3:13:35
	 * @throws @author
	 *             西安工业大学蓝桥一期—陈楚 @Title:
	 *             updatState @Description:修改禁用状态 @param @param request
	 *             设定文件 @return void 返回类型 @date 2017年5月21日 下午12:01:14 @throws
	 */
	@RequestMapping("/updateState")
	public String updateState(HttpServletRequest request, Map<String, Object> map) {
		// 从页面获取管理员给的信息
		String id = request.getParameter("id");
		// 获取当前状态信息 修改为禁用或正常
		String state = request.getParameter("state");
		// 通过id去修改用户状态信息
		userServiceImpl.updateState(id, state);
		String pageNo = request.getParameter("pno") == null ? "1" : request.getParameter("pno");
		String orderby = request.getParameter("orderby") == null ? "id asc" : request.getParameter("orderby");
		String keyword = request.getParameter("keyword") == null ? "" : request.getParameter("keyword");
		// 获取封装好的userCreteria
		UserCreteria userCreteria = userServiceImpl.getuserByqualified(keyword, pageNo, orderby);
		// 获取符合所有条件的用户信息
		List<User> page = userServiceImpl.getPage(userCreteria);
		map.put("userCreteria", userCreteria);
		map.put("users", page);
		return "admin/user_listAll";
	}

	/**
	 * @author 西安工业大学蓝桥一期--陈楚 @Title:verifyName @Description:通过判断用户是否存在 @param
	 *         request @param response @return void @date
	 *         2017年4月24日上午10:41:32 @throws
	 */
	@RequestMapping("/verifyName")
	public void verifyName(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 设置字符集
		response.setCharacterEncoding("utf-8");
		// 获取PrintWriter对象以返回数据
		PrintWriter out = response.getWriter();
		// 准备返回数据
		String msg = "";
		// 获取用户提交的用户名
		String name = request.getParameter("name");
		// 通过name去数据库获取用户 是否存在这个用户
		List<User> users = userServiceImpl.getUserByName(name);
		// System.out.println(users);
		if (users.size() == 0) {
			// System.out.println("用户名不存在");
			msg = "用户名不存在，请注册";
		} else {
			for (User user : users) {
				if (user.getState().equals(1)) {
					msg = "该用户名已被禁用！";
				} else if (user.getDeleteState().equals(1)) {
					msg = "该用户已被注销！";
				} else {
					msg = "";
				}
			}
		}
		// 返回数据
		out.print(msg);
	}

	@RequestMapping("/nameTrueOrFalse")
	public void nameTrueOrFalse(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		System.out.println("name:" + name);
		List<User> users = userServiceImpl.getUserByName(name);
		System.out.println(users.size());
		if (users.size() == 0) {
			out.write("true");
		} else {
			out.write("false");
		}
	}

	/**
	 * @author 西安工业大学蓝桥一期--陈楚 @Title:
	 *         validateCodeServlet @Description:验证码 @param reqeust @param
	 *         response @throws IOException @return String @date 2017年4月23日
	 *         下午5:37:44 @throws
	 */
	@RequestMapping("/getcode")
	public void validateCodeServlet(HttpServletRequest reqeust, HttpServletResponse response) throws IOException {
		// 设置响应类型
		response.setContentType("image/jpeg");
		// 禁用缓存
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		HttpSession session = reqeust.getSession();

		// 创建验证码对象
		ValidateCode vCode = new ValidateCode(120, 40, 4, 100);
		// 将验证码放到session中
		session.setAttribute("code", vCode.getCode());
		vCode.write(response.getOutputStream());
	}

	@RequestMapping("/to_uc")
	public String to_uc() {
		return "user/uc";
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--马志远
	 * @Title: verifyPassword
	 * @Description: TODO检查登录
	 * @param @param
	 *            request
	 * @param @param
	 *            response
	 * @param @throws
	 *            IOException
	 * @return void
	 * @date 2017年5月14日 上午8:49:56
	 */
	@RequestMapping("/isLogin")
	public void isLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String msg = "";
		User u = (User) request.getSession().getAttribute("user");
		EnterpriseUser eu = (EnterpriseUser) request.getSession().getAttribute("enterpriseUser");
		if (u != null) {
			msg = u.getName();
		} else if (eu != null) {
			msg = eu.getName();
		} else {
			msg = "";
		}
		// 返回数据
		out.write(msg);
	}

	/**
	 * @throws IOException
	 * @author 西安工业大学蓝桥一期--陈楚 @Title: verifyPassword @Description:
	 *         利用ajax验证密码是否正确 @param @return void @date 2017年4月25日
	 *         下午6:25:21 @throws
	 */
	@RequestMapping("/verifyPassword")
	public void verifyPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 设置字符集
		response.setCharacterEncoding("utf-8");
		// 获取PrintWriter对象以返回数据
		PrintWriter out = response.getWriter();
		// 准备返回数据
		String msg = "";
		// 获取用户名
		String name = request.getParameter("name");
		// 获取用户输入的密码
		String userPassword = request.getParameter("password");
		String password = MyUtils.md5(userPassword);
		// 去数据库查询是否有这样一个用户
		// 测试前端页面
		// User user = new User("aa", null, null, null, null, null, null);
		User user = userServiceImpl.getUserByNameAndPassword(name, password);
		if (user != null) {
			msg = "";
		} else {
			msg = "密码错误，请重试！";
		}
		// 返回数据
		out.write(msg);
	}

	/**
	 * @throws IOException
	 * @author 西安工业大学蓝桥一期--陈楚 @Title: verifyCode @Description:
	 *         判断用户输入的验证码是否正确 @param @param request @param @param
	 *         response @return void @date 2017年4月25日 下午7:29:17 @throws
	 */
	@RequestMapping("/verifyCode")
	public void verifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 设置字符集
		response.setCharacterEncoding("utf-8");
		// 获取PrintWriter对象以返回数据
		PrintWriter out = response.getWriter();
		// 准备返回数据
		String msg = "";
		// 获取系统验证码
		String code = ((String) request.getSession().getAttribute("code")).toLowerCase();
		// 获取用户输入的验证码
		String vcode = (request.getParameter("vcode")).toLowerCase();
		if (code.equals(vcode)) {
			msg = "";
		} else {
			msg = "验证码错误，请重试！";
		}
		out.write(msg);
	}

	/**
	 * @throws IOException
	 * @author 西安工业大学蓝桥一期--陈楚 @Title: login @Description: 登录信息验证 @param @param
	 *         user @param @param request @param @return @return String @date
	 *         2017年4月23日 下午5:38:13 @throws
	 * @throws ServletException
	 */
	@RequestMapping("/login")
	public String login(User user, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// 从页面获取用户填写的信息
		String password = MyUtils.md5(user.getLoginPassword());
		User u = userServiceImpl.getUserByNameAndPassword(user.getName(), password);
		// System.out.println("secretId:" + u.getSecretId().getId());//6
		// 获取系统验证码
		String code = ((String) request.getSession().getAttribute("code")).toLowerCase();
		// 获取用户输入的验证码
		String vcode = (request.getParameter("vcode")).toLowerCase();
		boolean boo = code.equals(vcode);
		if (u != null & boo != false && u.getState() == 0 && u.getDeleteState() == 0) {
			// 将登陆信息放入Session域中
			
			// 设置Session的最大空闲时的存活时间
			// request.getSession().setMaxInactiveInterval(20);
			// 创建一个带session id的cookie
			Cookie cookie = new Cookie("JSESSIONID", request.getSession().getId());
			// 设置Cookie在浏览器关闭之后还能存活多长时间
			// cookie.setMaxAge(30);
			// 将Cookie返回给客户端
			response.addCookie(cookie);
			// 将email放到session中.....liming
			request.getSession().setAttribute("email", u.getEmail());
			request.getSession().setAttribute("name", u.getName());
			// 得到图头像片路径
			request.getSession().setAttribute("touxiang", u.getSufface());
			// 将balance放到session中.....liming
			if (u.getBalance() == null) {
				request.getSession().setAttribute("balance", 0.0);
			} else {
				request.getSession().setAttribute("balance", u.getBalance());
			}
			// 将bankcard放到session中.....liming
			List<BankCard> userGetBankCards = bankCardServiceImpl.getBankname(u.getId());
			// 遍历银行卡
			/* int i = 0; */
			List<BankCard> isquick = new ArrayList<BankCard>();
			for (BankCard bankCard : userGetBankCards) {
				// 找到该用户的默认银行卡,并且用户默认银行卡应该为一张
				if (bankCard.getIsDefault() != null) {
					if (bankCard.getIsDefault() == 1) {
						request.getSession().setAttribute("isdefaul", bankCard);
					}
				}
				// 确定快捷支付卡的张数
				if (bankCard.getIsQuickPay() != null) {
					if (bankCard.getIsQuickPay() == 1) {
						isquick.add(bankCard);
						/* i = i + 1; */
					} else {
					}
				}
			}
			// 将需要的数据放到session中..........liming
			request.getSession().setAttribute("kuaijie", isquick.size());
			request.getSession().setAttribute("kuaijiek", isquick);
			/* request.getSession().setAttribute("kuaijie", i); */
			request.getSession().setAttribute("bankcards", userGetBankCards);
			request.getSession().setAttribute("bankcarlengh", userGetBankCards.size());
			// 将PayPassword(支付密碼)放到session中.....liming
			Secret secret = secretServiceImpl.getSecretZM(u.getId());
			request.getSession().setAttribute("zf", secret.getPayPassword());
			request.getSession().setAttribute("id", u.getId());
			request.getSession().setAttribute("email", u.getEmail());
			// 得到手机号
			if (u.getCall() != null) {
				request.getSession().setAttribute("call", u.getCall());
			} else {
				request.getSession().setAttribute("call", null);
			}
			List<Recharge_withdrawal> recharge_withdrawals = userServiceImpl.getRecharge_withdrawal(u.getId());
			List<Transfer> transfers = userServiceImpl.getTransfer(u.getId());
			request.getSession().setAttribute("transfers", transfers);
			request.getSession().setAttribute("recharge_withdrawals", recharge_withdrawals);
			u.setListBankCard(userGetBankCards);
			request.getSession().setAttribute("user", u);
			return "user/uc";
		} else {
			// 登录失败，请重新登录
			request.getRequestDispatcher("/userLogin.jsp").forward(request, response);
		}
		return null;
	}

	/**
	 * 
	 * @Title: getUserNameByEmail
	 * @Package org.lanqiao.pay.controller
	 * @Description: 验证该邮箱是否已经注册过
	 * @param @param
	 *            reqeust
	 * @param @return
	 * @param @throws
	 *            IOException
	 * @return boolean
	 * @author 西安工业大学蓝桥一期--刘宣
	 * @date 2017年4月23日 下午8:37:58
	 */
	@RequestMapping("/isUsed")
	public void getUserNameByEmail(HttpServletRequest reqeust, HttpServletResponse response,
			@ModelAttribute("reqUser") User reqUser, ModelMap m) throws IOException {
		// 设置字符集
		response.setCharacterEncoding("utf-8");
		// 获取PrintWriter对象以返回数据
		PrintWriter out = response.getWriter();
		// 获取用户填入的邮箱
		String userEmail = reqeust.getParameter("userEmail");
		//获取页面生成的验证码
		String code = reqeust.getParameter("emailCode");
		// 通过用户名查询用户
		User user = userServiceImpl.getUserByUserName(userEmail);
		// 页面ajax返回的内容
		String msg = "";
		if (user != null) {
			// 该邮箱已注册
			msg = "该邮箱已注册，请换一个！";
		} else {
			msg = sendEmail(userEmail, code);
			// 封装一个user对象
			reqUser.setEmail(userEmail);
		}
		out.print(msg);
	}

	/**
	 * 
	 * @Title: UserController.java
	 * @Package org.lanqiao.pay.controller
	 * @Description: 发送验证邮件
	 * @author 西安工业大学蓝桥一期--刘宣
	 * @date 2017年5月7日 下午3:55:47
	 */
	public String sendEmail(String userEmail,String code) {
		// 发送验证邮件
		boolean boo = EmailUtil.sendHtmlEmail(userEmail, "蓝桥支付", code);
		String str = "";
		if (boo) {
			str = "验证邮件已发送，请查看邮箱！";
		} else {
			str = "邮件发送失败，请重新发送！";
		}
		return str;
	}

	/**
	 * @Title: UserController.java
	 * @Package org.lanqiao.pay.controller
	 * @Description: 转发到reg_personal_email_b.jsp页面
	 * @author 西安工业大学蓝桥一期--刘宣
	 * @date 2017年5月3日 下午7:09:03
	 */
	@RequestMapping("/to_reg_personal_b")
	public String toRegPersonalB() {
		return "user/reg_personal_b";
	}

	/**
	 * @Title: UserController.java
	 * @Package org.lanqiao.pay.controller
	 * @Description: 查询用户绑定的银行卡数
	 * @author 西安工业大学蓝桥一期--刘宣
	 * @date 2017年5月7日 上午11:35:27
	 */
	@RequestMapping("/getBankCard")
	public void getBankCard(HttpServletRequest reqeust, HttpServletResponse response) throws IOException {
		// 设置字符集
		response.setCharacterEncoding("utf-8");
		// 获取PrintWriter对象以返回数据
		PrintWriter out = response.getWriter();
		// 获取用户id
		String userId = reqeust.getParameter("userId");
		// 通过用户id查询银行卡
		List<BankCard> cards = bankCardServiceImpl.userGetBankCards(Integer.parseInt(userId));
		// 返回页面数据
		String str = "";
		if (cards != null) {
			str = "已绑定" + cards.size() + "张银行卡";
		} else {
			str = "还未绑定银行卡";
		}
		out.print(str);
	}

	/**
	 * @Title: UserController.java
	 * @Package org.lanqiao.pay.controller
	 * @Description: 跳转到页面user_information.jsp
	 * @author 西安工业大学蓝桥一期--刘宣
	 * @date 2017年5月7日 上午11:39:55
	 */
	@RequestMapping("/to_user_information")
	public String toUserInformation() {
		return "user/user_information";
	}

	@ModelAttribute
	public void getModel(@RequestParam(value = "id", required = false) Integer id, Map<String, Object> map) {
		if (!"".equals(id) && id != null) {
			User u = userServiceImpl.getUser(id);
			map.put("user", u);
		} else {
			User u = new User();
			map.put("user", u);
		}
	}

	@RequestMapping("/getUser")
	public String getUser(Integer id, Map<String, Object> map) {
		User user = userServiceImpl.getUser(id);
		map.put("user", user);
		return "user/ur";
	}

	/**
	 * @author 西安工业大学蓝桥一期--刘江 @Title: regPersonalB @Description: 注册添加银行卡 @return
	 * String @date 2017年6月4日 上午11:43:40 @throws
	 */
	@RequestMapping("/regE")
	public String regPersonalB(@ModelAttribute("reqUser") User reqUser, ModelMap m) {
		return "user/reg_person_c";
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--李明 @Title: registed @Description: TODO @密保问题
	 *         String @date 2017年4月24日 下午6:00:32 @throws
	 */
	@RequestMapping("/registed")
	public String registed(HttpServletRequest request, @ModelAttribute("reqUser") User reqUser, ModelMap m) {
		// 密保问题
		String zdy1 = "";
		// 密保问题答案
		String zd1 = "";
		// 登录密码
		String pwd1 = request.getParameter("userName1");
		// 支付密码
		String pwd3 = request.getParameter("userName3");
		if (Integer.parseInt(request.getParameter("wentii")) == 0) {
			zdy1 = request.getParameter("namezdy");
			zd1 = request.getParameter("wetidaz");
		} else {
			if (Integer.parseInt(request.getParameter("wentii")) == 1) {
				zdy1 = "您的生日";
			} else if (Integer.parseInt(request.getParameter("wentii")) == 2) {
				zdy1 = "您父亲的生日";
			} else if (Integer.parseInt(request.getParameter("wentii")) == 3) {
				zdy1 = "您母亲的生日";
			} else if (Integer.parseInt(request.getParameter("wentii")) == 4) {
				zdy1 = "您爱人的生日";
			} else if (Integer.parseInt(request.getParameter("wentii")) == 5) {
				zdy1 = "您小学老师的生日";
			}
			zd1 = request.getParameter("wentidai");
		}
		Secret secret = new Secret(MyUtils.md5(pwd3), zdy1, zd1);
		secretServiceImpl.addByReq(secret);
		reqUser.setLoginPassword(MyUtils.md5(pwd1));
		reqUser.setSecretId(secret);
		reqUser.setBalance(0.0);
		reqUser.setRegistration(new Date());
		reqUser.setState(0);
		reqUser.setDeleteState(0);
		userServiceImpl.updateUserByReq(reqUser);

		return "user/reg_common_e";
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--何璐 @Title: toModifyUser @Description: TODO
	 *         通过从客户端传来的ID获取用户 @param @param id @param @param map @param @return
	 *         设定文件 @return String 返回类型 @date 2017年4月25日 下午1:10:01 @throws
	 */
	@RequestMapping("/toModifyLoginPwd")
	public String toModifyUser() {
		return "user/loginPwd_reset";
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--何璐 @Title: modifyLoginPwd @Description: TODO
	 *         修改登陆密码 @param @param user @param @param req @param @param
	 *         res @param @return 设定文件 @return String 返回类型 @date 2017年4月24日
	 *         下午11:45:43 @throws
	 */
	@RequestMapping("/modifyLoginPwd")
	public String modifyLoginPwd(User user, HttpServletRequest req, HttpServletResponse res) {
		// 设置字符集
		res.setCharacterEncoding("utf-8");
		// 获取新的登陆密码
		String newLoginPwd = req.getParameter("newPassword");
		// 修改登陆密码返回i
		int i = userServiceImpl.modify(user, newLoginPwd);
		// 返回的消息
		String msg = "";
		// i为1,修改成功
		if (i == 1) {
			msg = "登陆密码修改成功";
			req.setAttribute("msg", msg);
			// 跳转到成功页面
			return "user/message";
			// i为0即修改失败的情况下判断是身份证号码不正确的原因还是登陆密码不正确的原因
			// 身份证号码不正确
		} else {
			boolean boo = false;
			boolean b = userServiceImpl.isCardId(user.getId(), user.getCardId());
			if (boo==b) {
				msg = "身份证号不正确，请重新操作";
				// 登陆密码不正确
			} else {
				msg = "登陆密码不正确，请重新操作";
			}
		}
		req.setAttribute("msg", msg);
		// 跳转到失败页面
		return "user/message";
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--何璐 @Title: toSafe @Description: TODO
	 *         操作完成后跳转到安全中心首页 @param @return 设定文件 @return String 返回类型 @date
	 *         2017年4月28日 下午10:47:48 @throws
	 */
	@RequestMapping("/to_safe")
	public String toSafe() {
		return "user/safe";
	}
	/**
	 * 
	* @author 西安工业大学蓝桥一期--何璐
	* @Title: to_reset 
	* @Description: TODO  点重置时返回到安全中心 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @date 2017年6月17日 上午12:48:33 
	* @throws
	 */
	@RequestMapping("/to_reset")
	public String to_reset(){
		return "user/safe";
	}

	/**
	 * @throws IOException
	 * @author 西安工业大学蓝桥一期-代显峰
	 * @title updateEmail
	 * @description 修改用户绑定的邮箱
	 * @param @param
	 *            id
	 * @param @param
	 *            payPassword
	 * @param @param
	 *            email
	 * @param @return
	 * @return String
	 * @date 2017年4月26日 下午5:54:17
	 */
	@RequestMapping(value = "/updateEmail", params = { "id", "email" })
	public String updateEmail(Integer id, String email) {
		userServiceImpl.update(id, email);
		// 去成功页面
		return "secret_success";
	}

	/**
	 * @author 西安工业大学蓝桥一期-代显峰 @title updateEmail @description TODO
	 *         用一句话描述方法的作用 @param @return @return String @date 2017年4月26日
	 *         下午10:18:58 @throws
	 */
	@RequestMapping("/toUpdate")
	public String updateEmail() {
		return "user/security_bound_mailbox";

	}

	/**
	 * @author 西安工业大学蓝桥一期-代显峰 @title registEmail @description 邮箱验证 @param @param
	 *         reqeust @param @param response @param @throws IOException @return
	 *         void @date 2017年4月28日 下午8:30:01 @throws
	 */
	@RequestMapping("/registEmail")
	public void registEmail(HttpServletRequest reqeust, HttpServletResponse response) throws IOException {
		// 设置字符集
		response.setCharacterEncoding("utf-8");
		// 获取PrintWriter对象以返回数据
		PrintWriter out = response.getWriter();
		String email = reqeust.getParameter("email");
		// 获取用户填入的邮箱
		User user = userServiceImpl.getUser(3);
		String email2 = user.getEmail();
		// 页面ajax返回的内容
		String msg = "";
		if (email2 == email) {
			// 该用户不存在
			msg = "您输入的邮箱不正确！";
		} else {
			// 邮箱验证规则
			Pattern pattern = Pattern.compile(
					"^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
			// 编译正则表达式
			Matcher matcher = pattern.matcher(email);
			System.out.println(matcher.matches());
			// 字符串是否与正则表达式相匹配
			boolean rs = matcher.matches();
			if (rs == true) {
				msg = "邮箱格式正确";
			} else {
				msg = "邮箱格式不正确";
			}
		}
		out.print(msg);

	}

	/**
	 * @author 西安工业大学蓝桥一期-代显峰 @title registPayPwd @description
	 *         验证支付密码 @param @param reqeust @param @param
	 *         response @param @throws IOException @return void @date 2017年4月29日
	 *         上午12:18:51 @throws
	 */
	@RequestMapping(value = "/registPayPwd", params = { "id" })
	public void registPayPwd(HttpServletRequest reqeust, HttpServletResponse response, Integer id) throws IOException {
		// 设置字符集
		response.setCharacterEncoding("utf-8");
		// 获取PrintWriter对象以返回数据
		PrintWriter out = response.getWriter();
		String payPassword = reqeust.getParameter("payPassword");
		// 页面ajax返回的内容
		boolean b = secretServiceImpl.isPayPwd(id, payPassword);
		if (b) {
			out.write("true");
		} else {
			out.write("false");
		}
	}

	/**
	 * @author 西安工业大学蓝桥一期--刘江 @Title: register @Description 点击立即注册按钮，跳到此方法，
	 *         创建一个新的User对象 放到session里，跳到邮箱注册的页面 @return String @date 2017年4月30日
	 *         下午9:50:34 @throws
	 */
	@RequestMapping("/register")
	public String register(ModelMap m) {
		User reqUser = new User();
		m.addAttribute("reqUser", reqUser);
		return "user/reg_personal_email_a";
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--李明 @Title: tiaoZhuan @Description: 跳转至充值页面
	 * @return String @date 2017年5月14日 下午4:19:05 @throws
	 */
	@RequestMapping("/tiaoZhuan")
	public String tiaoZhuan() {
		return "bank/deposit";
	}

	/**
	 * 
	* @author 西安工业大学蓝桥一期--董正健
	* @Title: toReSettingPasswordByEmail 
	* @Description: 跳转到通过邮箱修改密码页面
	* @param @return    设定文件 
	* @return String    返回类型 
	* @date 2017年6月17日 下午1:32:05 
	* @throws
	 */
	@RequestMapping("/toReSettingPasswordByEmail")
	public String toReSettingPasswordByEmail(){
		return "user/security_emailVerity_step_a";
	}
	
	/**
	 * 
	* @author 西安工业大学蓝桥一期--董正健
	* @Title: reSettingPayPassword 
	* @Description: 通过邮箱修改密码
	* @param @param request
	* @param @return    设定文件 
	* @return String    返回类型 
	* @date 2017年6月17日 下午1:29:45 
	* @throws
	 */
	@RequestMapping(value = "/reSettingPayPassword", method = RequestMethod.POST)
	public String reSettingPayPassword(HttpServletRequest request) {
		String email = request.getParameter("email");
		String payPassword = request.getParameter("newPayPassword");
		String strId = request.getParameter("id");
		Integer id = Integer.valueOf(strId);
		// 判断前台输入的email和用户的是否一致
		boolean boo = userServiceImpl.isUserEmail(id, email);
		if (boo) {
			// 通过email获取密保表id(密保表id存在于user表中)
			User user = userServiceImpl.getUserByEmail(email);
			// 调用毋泽航的方法修改密码
			secretServiceImpl.updatePayPwd(user.getSecretId().getId(),payPassword);
			return "secret_success";
		} else {
			return "user/security_emailVerity_step_a";
		}
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--李明 @Title: companyForm @Description: 利用ajax判断银行卡余额
	 * @return void @date 2017年5月14日 下午4:19:35 @throws
	 */
	@RequestMapping("/deposit1")
	public void companyForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 该银行卡的余额
		Double balance = 0.0;
		// 用户的充值
		Double dmoney = 0.0;
		// 银行卡号
		String sliy = null;
		String msg = "";
		PrintWriter out = response.getWriter();
		try {
			sliy = (String) request.getParameter("liy");
			if (sliy == null) {
			} else {
				// 银行卡号
				/* ssliy = sliy.substring(11, 30); */
			}
			String money = request.getParameter("moneyn");
			if (money == null) {
			} else {
				dmoney = Double.parseDouble(money);
			}
			BankCard bankcard = bankCardServiceImpl.getCardByNumber(sliy);
			balance = bankcard.getBalance();
		} catch (Exception e) {
		}
		request.getSession().setAttribute("yhkh", sliy);
		request.getSession().setAttribute("dmoney", dmoney);
		request.getSession().setAttribute("balance1", balance);
		if (balance < dmoney) {
			msg = "此卡,余额不足！";
		} else {
			msg = "";
		}
		out.print(msg);
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--李明 @Title: @Description: 判断输入的密码是否正确
	 * @return String @date 2017年6月4日 下午4:50:25 @throws
	 */
	@RequestMapping("/deposit2")
	public void deposit2(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out;
		String msg;
		try {
			out = response.getWriter();
			String str1 = request.getParameter("str1");
			String zf = (String) request.getSession().getAttribute("zf");
			String smd5 = MyUtils.md5(str1);
			if (smd5.equals(zf)) {
				msg = "";
				out.print(msg);
			} else {
				msg = "密码错误，请重新输入!";
				out.print(msg);
			}
			/* out.print(msg); */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--李明 @Title: deposit @Description: 充值后的提交
	 * @return String @date 2017年5月14日 下午4:20:37 @throws
	 */
	@RequestMapping("/deposit")
	public String deposit(HttpServletRequest request, HttpServletResponse response) {
		int i;
		// 得到银行卡号
		String yhkh = (String) request.getSession().getAttribute("yhkh");
		// 得到充值金额
		Double dmoney = (Double) request.getSession().getAttribute("dmoney");
		// 得到充值的用户id
		Integer id = (Integer) request.getSession().getAttribute("id");
		User user = userServiceImpl.getUser(id);
		// 通过卡号的到银行卡
		BankCard bankCard = bankCardServiceImpl.getCardByNumber(yhkh);
		// 充值并实现事务
		if (dmoney < bankCard.getBalance()) {
			i = bankCardServiceImpl.chongZhi(user, bankCard, dmoney);
		} else {
			return "bank/deposit";
		}
		// 将新的相应的值放到session中
		User user1 = userServiceImpl.getUser(id);
		request.getSession().setAttribute("name", user1.getName());
		request.getSession().setAttribute("email", user1.getEmail());
		request.getSession().setAttribute("balance", user1.getBalance());
		// 判断是否充值成功
		if (i == 1) {
			return "bank/successChongZhi";
		} else {
			return "bank/failChongZhi";
		}
	}

	@RequestMapping("/isUserEmail")
	public void isUserEmail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String email = request.getParameter("email");
		User user = userServiceImpl.getUserByEmail(email);
		String msg = "";
		if (user == null) {
			msg = "邮箱正确";
		} else if (user != null) {
			msg = "邮箱不正确";
		}
		out.print(msg);
	}

	/**
	 * @author 西安工业大学蓝桥一期--李明@Title: deposit3
	 * @Description: TODO @return String @date 2017年5月14日 下午4:45:19 @throws
	 */
	// 充值成功后要到的相应页面
	@RequestMapping("/deposit3")
	public String deposit3() {
		return "user/uc";
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--王婷 @Title:
	 *         show @Description:(跳转至user_information页面) @param @return
	 *         设定文件 @return String 返回类型 @date 2017年5月12日 下午6:13:02 @throws
	 */

	@RequestMapping("/goUserInformation")
	public String show() {
		return "user/user_information";
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--王婷 @Title: updateMyInfo @Description:
	 *         TODO(跳转至修改头像的user_modifyPicture.jsp页面) @param @param
	 *         file @param @param session @param @param map @param @param
	 *         request @param @return 设定文件 @return String 返回类型 @date 2017年5月12日
	 *         下午7:02:28 @throws
	 */
	@RequestMapping("/goUserModifyPicture")
	public String userModifyPicture() {
		return "user/user_modifyPicture";
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--王婷 @Title: addImage @Description:
	 *         修改用户头像 @param @param file1 @param @param session @param @param
	 *         map @param @param request @param @param
	 *         bindingResult @param @return 设定文件 @return String 返回类型 @date
	 *         2017年5月17日 上午1:25:04 @throws
	 */
	@RequestMapping("/updateMyPhoto")
	public String addImage(@RequestParam("file1") MultipartFile file1, HttpSession session, Map<String, Object> map,
			HttpServletRequest request) {
		User user = (User) session.getAttribute("user");
		// 使用工具类上传文件
		String upload = FileUploadUtil.FileUpload(file1, session, "/upload/user");
		// 将生成文件相对路径赋给user的sufface属性
		user.setSufface(upload);
		// 更新user的sufface属性
		userServiceImpl.updateUserName(user);
		return "user/user_information";
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--王婷 @Title: updateName @Description:
	 *         修改用户姓名 @param @param name @param @param request @param @return
	 *         设定文件 @return String 返回类型 @date 2017年5月17日 上午12:36:00 @throws
	 */
	@RequestMapping("/updateUserName")
	public String updateName(String name1, HttpServletRequest request, HttpSession session) {
		System.out.println(name1);
		User user = (User) session.getAttribute("user");
		System.out.println(user);
		user.setName(name1);
		System.out.println(user);
		userServiceImpl.updateUserName(user);
		return "user/user_information";
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--王婷 @Title: goUpdateName @Description:
	 *         去更新页面 @param @return 设定文件 @return String 返回类型 @date 2017年5月17日
	 *         下午9:18:26 @throws
	 */
	@RequestMapping("/goUpdateName")
	public String goUpdateName() {
		return "user/user_updateName";
	}

	/**
	 * @author 西安工业大学蓝桥一期--李明 @Title: deposit4 @Description: TODO
	 * @return String @date 2017年5月14日 下午4:45:37 @throws
	 */
	// 充值失败后要到的相应页面
	@RequestMapping("/deposit4")
	public String deposit4() {
		return "bank/deposit";
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--王婷 @Title: toforgetPwd @Description:
	 *         去忘记密码修改密码页面 @param @return 设定文件 @return String 返回类型 @date
	 *         2017年5月21日 上午11:44:08 @throws
	 */
	@RequestMapping("/toforgetPwd")
	public String toforgetPwd() {
		return "user/userForgetPwd_a";
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--王婷 @Title: forgetPwd @Description:
	 *         去修改密码的第二个页面 @param @param email @param @param map @param @return
	 *         设定文件 @return String 返回类型 @date 2017年5月21日 上午11:44:56 @throws
	 */
	@RequestMapping("/forgetPwd")
	public String forgetPwd(@RequestParam("email") String email, Map<String, Object> map) {
		map.put("email", email);
		return "user/userForgetPwd_b";
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--王婷 @Title: validateEmaliIsUseful @Description:
	 *         邮箱验证 @param @param response @param @param email @param @throws
	 *         IOException 设定文件 @return void 返回类型 @date 2017年5月21日
	 *         上午11:46:06 @throws
	 */
	@RequestMapping("/validateEmaliIsUseful")
	public void validateEmaliIsUseful(HttpServletResponse response, @RequestParam("email") String email)
			throws IOException {
		User user = userServiceImpl.getUserByUserName(email);
		PrintWriter out = response.getWriter();
		if (user == null) {// 说明没有找到,则邮箱没人用过
			out.print(true);
		} else {
			out.print(false);
		}

	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--王婷 @Title: changePwd @Description:
	 *         用户忘记密码，通过邮箱验证修改密码 @param @param email @param @param
	 *         password @param @param password2 @param @param map @param @return
	 *         设定文件 @return String 返回类型 @date 2017年5月21日 上午11:36:53 @throws
	 */
	@RequestMapping("/changePwd")
	public String changePwd(@RequestParam("email") String email, @RequestParam("password") String password,
			@RequestParam("password2") String password2, Map<String, Object> map) {
		// 修改登陆密码
		int i = userServiceImpl.changeLoginPwd(email, password, password2);
		String msg = "";
		if (i == 1) {
			msg = "修改密码成功";
			map.put("msg", msg);
			return "user/userForgetPwd_c";
		} else {
			msg = "修改密码失败";
			map.put("msg", msg);
			return "user/userForgetPwd_c";
		}
	}

	/**
	 * @Title: UserController.java
	 * @Package org.lanqiao.pay.controller
	 * @Description: 转发到my_card_list.jsp
	 * @author 西安工业大学蓝桥一期--刘宣
	 * @date 2017年5月15日 下午8:49:54
	 */
	@RequestMapping("/to_my_card_list")
	public String toMyCardList() {
		return "user/my_card_list";

	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--王增 @Title: getTradeHistoty @Description: TODO
	 * 用户交易(充值和提现)历史记录 @param @param id @param @param pageNo @param @param
	 * userTradePage @param @return 设定文件 @return String 返回类型 @date 2017年5月24日
	 * 下午8:26:54 @throws
	 */
	@RequestMapping("/userTradeHistory")
	public String getTradeHistoty(@RequestParam("id") Integer id, String pageNo, UserTradePage userTradePage,
			Map<String, Object> map) {
		map.put("listPageType", 0);
		System.out.println("id:" + id);
		// 默认显示第一页(默认查询3个月内个人充值的交易状态任意的money任意的)
		if (pageNo == null || pageNo.trim().equals("") || !MyUtils.stringIsIntger(pageNo)) {
			userTradePage.setPageNo(1);
		} else {
			userTradePage.setPageNo(Integer.parseInt(pageNo));
		}
		// 整理参数
		userTradePage.dealParam();
		userTradePage.dateSort();
		userTradePage.moneySort();

		// 根据分页对象得到记录数
		Integer count = recharge_withdrawalService.getUserTradeCount(userTradePage);
		System.out.println("count:" + count);
		userTradePage.setTotalRecords(count);
		// 根据分页对象取得记录
		List<Recharge_withdrawal> recharge_withdrawals = recharge_withdrawalService
				.getRecharge_withdrawalsByUserTradePage(userTradePage);
		for (Recharge_withdrawal recharge_withdrawal : recharge_withdrawals) {
			System.out.println(recharge_withdrawal);
		}
		map.put("userTradePage", userTradePage);
		map.put("recharge_withdrawals", recharge_withdrawals);
		Double balance = userServiceImpl.getUser(id).getBalance();
		System.out.println("balance:" + balance);
		map.put("userBalance", balance);
		return "user/tradeHistory";
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--王增 @Title: userTransferHistory @Description: TODO
	 * 前台的个人的转账记录将会映射到下面的这个方法 @param @param id @param @param
	 * pageNo @param @param userTradePage @param @param map @param @return
	 * 设定文件 @return String 返回类型 @date 2017年6月4日 下午4:23:31 @throws
	 */
	@RequestMapping("/userTransferHistory")
	public String userTransferHistory(@RequestParam("id") Integer id, String pageNo, UserTradePage userTradePage,
			Map<String, Object> map) {
		map.put("listPageType", 1);
		System.out.println("id:" + id);

		if (pageNo == null || pageNo.trim().equals("") || !MyUtils.stringIsIntger(pageNo)) {
			userTradePage.setPageNo(1);
		} else {
			userTradePage.setPageNo(Integer.parseInt(pageNo));
		}
		// 整理参数
		userTradePage.dealParam();
		userTradePage.dateSort();
		userTradePage.moneySort();

		// 根据分页对象得到记录数
		Integer count = transferService.getUserTransferCount(userTradePage);
		System.out.println("count:" + count);
		userTradePage.setTotalRecords(count);
		// 根据分页对象取得记录
		List<Transfer> transfers = transferService.getTransfersByUserTradePage(userTradePage);
		for (Transfer transfer : transfers) {
			System.out.println(transfer);
		}
		map.put("userTradePage", userTradePage);
		map.put("transfers", transfers);
		Double balance = userServiceImpl.getUser(id).getBalance();
		System.out.println("balance:" + balance);
		map.put("userBalance", balance);
		return "user/tradeHistory";
	}

	/**
	 * @author 西安工业大学蓝桥一期--李明 @Title: @Description:查询所有记录
	 * @return String @date 2017年6月4日 上午11:47:04 @throws
	 */
	@RequestMapping("/suoyou")
	public String suoyou(HttpServletRequest request) {
		User u = (User) request.getSession().getAttribute("user");
		List<Recharge_withdrawal> recharge_withdrawals = userServiceImpl.getRecharge_withdrawall(u.getId());
		List<Transfer> transfers = userServiceImpl.getTransferAll(u.getId());
		request.getSession().setAttribute("transfers", transfers);
		request.getSession().setAttribute("recharge_withdrawals", recharge_withdrawals);
		return "user/uc1";
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--李明 @Title: @Description:充值提现记录
	 * @return String @date 2017年6月4日 上午11:48:23 @throws
	 */
	@RequestMapping("/chongTi")
	public String chongTi(HttpServletRequest request) {
		User u = (User) request.getSession().getAttribute("user");
		List<Recharge_withdrawal> recharge_withdrawals = userServiceImpl.getRecharge_withdrawall(u.getId());
		request.getSession().setAttribute("recharge_withdrawals", recharge_withdrawals);
		return "user/uc2";
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--李明 @Title: @Description: 支付记录
	 * @return String @date 2017年6月4日 上午11:48:59 @throws
	 */
	@RequestMapping("/zhiFu")
	public String zhiFu(HttpServletRequest request) {
		User u = (User) request.getSession().getAttribute("user");
		List<Transfer> transfers = userServiceImpl.getTransferAll(u.getId());
		request.getSession().setAttribute("transfers", transfers);
		return "user/uc3";
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--李明 @Title: @Description: 最新记录
	 * @return String @date 2017年6月4日 上午11:49:22 @throws
	 */
	@RequestMapping("/zuiXing")
	public String zuiXing(HttpServletRequest request) {
		User u = (User) request.getSession().getAttribute("user");
		List<Recharge_withdrawal> recharge_withdrawals = userServiceImpl.getRecharge_withdrawal(u.getId());
		List<Transfer> transfers = userServiceImpl.getTransfer(u.getId());
		request.getSession().setAttribute("transfers", transfers);
		request.getSession().setAttribute("recharge_withdrawals", recharge_withdrawals);
		return "user/uc";
	}

	@RequestMapping("/to_zhuce")
	public String zhuce() {
		return "user/reg_person_c";
	}

}