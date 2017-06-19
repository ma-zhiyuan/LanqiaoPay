/**   
* @Title: EnterpriseUserController.java 
* @Package org.lanqiao.pay.controller 
* @Description: TODO
* @author 西安工业大学蓝桥一期--王增   
* @date 2017年5月4日 下午8:42:55    
*/

package org.lanqiao.pay.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.lanqiao.pay.base.bean.EnterpriseUserCreteria;
import org.lanqiao.pay.base.bean.UserTradePage;
import org.lanqiao.pay.base.entity.BankCard;
import org.lanqiao.pay.base.entity.EnterpriseUser;
import org.lanqiao.pay.base.entity.Recharge_withdrawal;
import org.lanqiao.pay.base.entity.Secret;
import org.lanqiao.pay.base.entity.Transfer;
import org.lanqiao.pay.base.util.FileUploadUtil;
import org.lanqiao.pay.base.util.MyUtils;
import org.lanqiao.pay.base.util.ValidateCode;
import org.lanqiao.pay.service.EnterpriseUserService;
import org.lanqiao.pay.service.TransferService;
import org.lanqiao.pay.serviceImpl.BankCardServiceImpl;
import org.lanqiao.pay.serviceImpl.EnterpriseBankCardServiceImpl;
import org.lanqiao.pay.serviceImpl.EnterpriseUserServiceImpl;
import org.lanqiao.pay.serviceImpl.Recharge_withdrawalServiceImpl;
import org.lanqiao.pay.serviceImpl.SecretServiceImpl;
import org.lanqiao.pay.serviceImpl.TransferServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 王增
 *
 */
@Controller
@RequestMapping("/enterpriseUser")
public class EnterpriseUserController {
	private final static String DIR = "enterpriseUser";
	@Autowired
	EnterpriseUserService enterpriseUserService;
	@Autowired
	EnterpriseBankCardServiceImpl enterpriseBankCardServiceImpl;
	@Autowired
	EnterpriseUserServiceImpl enterpriseUserServiceImpl;
	@Autowired
	BankCardServiceImpl bankCardServiceImpl;
	@Autowired
	TransferServiceImpl transferServiceImpl;
	@Autowired
	Recharge_withdrawalServiceImpl recharge_withdrawalServiceImpl;
	@Autowired
	TransferService transferService;
	@Autowired
	SecretServiceImpl secretServiceImpl;

	/**
	 * @author 西安工业大学蓝桥一期—陈楚 @Title: resetPassword @Description:
	 * 重置密码 @param @return 设定文件 @return String 返回类型 @date 2017年5月24日
	 * 下午8:40:47 @throws
	 */
	@RequestMapping("/resetPassword")
	public String resetPassword(HttpServletRequest request, Map<String, Object> map) {
		String idStr = request.getParameter("id");
		int id = Integer.parseInt(idStr);
		// 查找该用户信息
		EnterpriseUser eu = enterpriseUserServiceImpl.getEnterpriseUserDetail(id);
		// 重置初始密码为123456
		String password = "123456";
		// 使用MD5加密
		String rePassword = MyUtils.md5(password);
		enterpriseUserServiceImpl.rePassword(id, rePassword);

		String orderby = request.getParameter("orderby") == null ? "id asc" : request.getParameter("orderby");
		String keyword = request.getParameter("keyword") == null ? "" : request.getParameter("keyword");
		String pageNo = request.getParameter("pno") == null ? "1" : request.getParameter("pno");
		// 获取有此关键字的所有记录的条数
		EnterpriseUserCreteria enterpriceUserCreteria = enterpriseUserServiceImpl.getuserByqualified(keyword, pageNo,
				orderby);
		// 所有符合条件的信息
		List<EnterpriseUser> page = enterpriseUserServiceImpl.getPage(enterpriceUserCreteria);

		map.put("enterPriseUser", page);
		map.put("enterpriceUserCreteria", enterpriceUserCreteria);
		return "admin/enterprise_listAll";
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期—陈楚 @Title: toUpdatePassword @Description:
	 * 去重置密码 @param @param request @param @return 设定文件 @return String 返回类型 @date
	 * 2017年5月24日 下午8:37:38 @throws
	 */
	@RequestMapping("/toUpdatePassword")
	public String toUpdatePassword(HttpServletRequest request, Map<String, Object> map) {
		// 从页面获取用户id
		String idStr = request.getParameter("id");
		// 查找该用户的信息
		EnterpriseUser eu = enterpriseUserServiceImpl.getEnterpriseUserDetail(Integer.parseInt(idStr));
		map.put("user", eu);
		return "admin/resetPassword";
	}

	@RequestMapping("/updateState")
	public String updateState(HttpServletRequest request, Map<String, Object> map) {
		String id = request.getParameter("id");
		String state = request.getParameter("state");
		// 修改状态信息
		enterpriseUserServiceImpl.updateState(id, state);
		String orderby = request.getParameter("orderby") == null ? "id asc" : request.getParameter("orderby");
		String keyword = request.getParameter("keyword") == null ? "" : request.getParameter("keyword");
		String pageNo = request.getParameter("pno") == null ? "1" : request.getParameter("pno");
		// 获取有此关键字的所有记录的条数
		EnterpriseUserCreteria enterpriceUserCreteria = enterpriseUserServiceImpl.getuserByqualified(keyword, pageNo,
				orderby);
		// 所有符合条件的信息
		List<EnterpriseUser> page = enterpriseUserServiceImpl.getPage(enterpriceUserCreteria);

		map.put("enterPriseUser", page);
		map.put("enterpriceUserCreteria", enterpriceUserCreteria);
		return "admin/enterprise_listAll";
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--王增 @Title: toApprove @Description: TODO
	 *         更新企业级用户的fobid字段为0;即就是审批通过 @param @param
	 *         enterpriseUserId @param @return 设定文件 @return String 返回类型 @date
	 *         2017年5月4日 下午8:36:08 @throws
	 */
	@RequestMapping("/approve")
	public String approve(@RequestParam("id") Integer enterpriseUserId) {
		Integer line = enterpriseUserService.approve(enterpriseUserId);
		return "redirect:/adminController/enterpriseUser_listForAdmin";
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--王增 @Title: showDetailInfo @Description: TODO
	 *         得到企业用户的详细信息(包含级联) @param @param id @param @param
	 *         map @param @return 设定文件 @return String 返回类型 @date 2017年5月5日
	 *         下午2:48:29 @throws
	 */
	@RequestMapping("/showDetailInfo")
	public String showDetailInfo(@RequestParam("id") Integer id, Map<String, Object> map) {
		EnterpriseUser enterpriseUser = enterpriseUserService.getEnterpriseUserDetail(id);
		System.out.println(enterpriseUser);
		map.put("enterpriseUser", enterpriseUser);
		return DIR + "/showDetail";
	}

	/**
	 * @Title: EnterpriseUserController.java
	 * @Package org.lanqiao.pay.controller
	 * @Description: 跳转到enterpriseUser_deposit.jsp
	 * @author 西安工业大学蓝桥一期--刘宣
	 * @date 2017年5月9日 下午6:53:11
	 */
	@RequestMapping("/to_addBalance")
	public String toAddBalance(HttpServletRequest request) {
		return "enterpriseUser/enterpriseUser_deposit";
	}

	/**
	 * @Title: EnterpriseUserController.java
	 * @Package org.lanqiao.pay.controller
	 * @Description: 跳转到addBlanceSuccess页面
	 * @author 西安工业大学蓝桥一期--刘宣
	 * @date 2017年5月21日 下午3:44:16
	 */
	@RequestMapping("addBalanceSuccess")
	public String toSuccess() {
		return "enterpriseUser/addBalanceSuccess";
	}

	/**
	 * @Title: EnterpriseUserController.java
	 * @Package org.lanqiao.pay.controller
	 * @Description:企业用户余额充值
	 * @author 西安工业大学蓝桥一期--刘宣
	 * @throws ServletException
	 * @date 2017年5月9日 下午6:55:43
	 */
	@RequestMapping("/addBalance")
	public void selectBalance(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// 获取PrintWriter对象以返回数据
		PrintWriter out = response.getWriter();
		// 获取当前用户
		EnterpriseUser u = (EnterpriseUser) request.getSession().getAttribute("enterpriseUser");
		// 获取用户输入的充值金额
		String addBalance = request.getParameter("addBalance");
		// 获取用户选择的银行卡的卡号
		String cardNumber = request.getParameter("cardNumber");
		// 用户输入的支付密码
		String payPwd = request.getParameter("payPwd");
		// 后台加密支付密码
		String payPwd2 = MyUtils.md5(payPwd);
		// 获取银行卡
		BankCard bankCard = enterpriseBankCardServiceImpl.getBankCardById(cardNumber);
		// 准备返回数据
		String msg = "";
		// 如果银行卡余额小于充值金额，返回余额不足
		if (bankCard.getBalance() < Integer.parseInt(addBalance)) {
			msg = "银行卡余额不足！";
			// 判断用户的支付密码是否正确
		} else if (!payPwd.equals(bankCard.getBankCardPassWord())) {
			msg = "支付密码错误！";
		} else {
			// 执行充值操作（需要实现事务）返回boolean类型
			boolean boo = enterpriseBankCardServiceImpl.addEnterpriseUserBalance(u, Double.parseDouble(addBalance),
					bankCard);
			// 查询充值操作后的用户信息，放入session
			EnterpriseUser user = enterpriseUserServiceImpl.getUserByNameAndPassword(u.getName(), u.getPassword());
			request.getSession().setAttribute("enterpriseUser", user);
			if (boo) {
				request.getSession().setAttribute("msg", "操作成功！");
				msg = "操作成功！";
			} else {
				request.getSession().setAttribute("msg", "操作失败！");
				msg = "操作失败！";
			}
		}
		out.print(msg);
	}

	/**
	 * @Title: EnterpriseUserController.java
	 * @Package org.lanqiao.pay.controller
	 * @Description: 转发到页面 enterpriseUser_deposit.jsp
	 * @author 西安工业大学蓝桥一期--刘宣
	 * @date 2017年5月15日 下午5:53:16
	 */
	@RequestMapping("/to_enterpriseUser_deposit")
	public String toEnterpriseUserDeposit(HttpServletRequest request) {
		// 手动获取一个用户，模拟用户登录。
		// EnterpriseUser user =
		// enterpriseUserServiceImpl.getUserByNameAndPassword("Lx",
		// "e10adc3949ba59abbe56e057f20f883e");
		// request.getSession().setAttribute("enterpriseUser", user);
		// 获取登录的用户
		EnterpriseUser user = (EnterpriseUser) request.getSession().getAttribute("enterpriseUser");
		// 查询用户所有银行卡
		List<BankCard> bankcards = enterpriseBankCardServiceImpl
				.getBankCardByEnterpriseId(user.getEnterprise().getId());
		// 遍历所有银行卡，并截取卡号后4位set到银行卡的属性里，页面显示时只显示卡号后4位，直接get。
		for (BankCard bankCard : bankcards) {
			String cardNumber = bankCard.getCardNumber();
			String jspCardNumber = cardNumber.substring(cardNumber.length() - 4, cardNumber.length());
			bankCard.setJspCardNumber(jspCardNumber);
		}
		request.getSession().setAttribute("bankCards", bankcards);
		return "enterpriseUser/enterpriseUser_deposit";
	}

	@RequestMapping("/to_enterprise_uc")
	public String toEnterpriseUc() {
		return "enterprise/enterprise_uc";
	}

	/**
	 * 
	 * 
	 * @author 西安工业大学蓝桥一期--王荣飞 @Title: verifyName @Description:
	 *         TODO @param @param name @param @param response @param @throws
	 *         IOException 设定文件 @return void @date 2017年5月7日 下午1:39:47 @throws
	 */
	@RequestMapping("/verifyName")
	public void verifyName(@RequestParam(value = "name", required = false) String name, HttpServletResponse response)
			throws IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		int i = enterpriseUserServiceImpl.verifyName(name);
		String msg = "";
		if (i > 0) {
			msg = "";
			out.print(msg);
		} else {
			msg = "用户名不存在！";
			out.print(msg);
		}

	}

	/**
	 * 
	 * 
	 * @author 西安工业大学蓝桥一期--王荣飞 @Title: verifyUser @Description:
	 *         TODO @param @param name @param @param password @param @param
	 *         response @param @throws IOException 设定文件 @return void @date
	 *         2017年5月7日 下午1:51:58 @throws
	 */
	@RequestMapping("/verifyUser")

	public void verifyUser(@RequestParam(value = "name", required = false) String name,
			@RequestParam("password") String password, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		System.out.println("第一次加密后的密码=" + password);
		PrintWriter out = response.getWriter();
		String pwd = enterpriseUserServiceImpl.verifyUser(name);
		String password2 = MyUtils.md5(password);
		String msg = "";
		System.out.println("数据库的密码=" + pwd);
		System.out.println("前台输入的密码=" + password2);
		if (pwd.equalsIgnoreCase(password2)) {
			msg = "";
			out.print(msg);
		} else {
			msg = "用户名或密码不正确";
			out.print(msg);
		}

	}

	/**
	 * 
	 * 
	 * @author 西安工业大学蓝桥一期--王荣飞 @Title: validateCodeServlet @Description:
	 *         TODO @param @param reqeust @param @param response @param @throws
	 *         IOException 设定文件 @return void @date 2017年5月7日 下午2:06:14 @throws
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

	/**
	 * 
	 * 
	 * @author 西安工业大学蓝桥一期--王荣飞 @Title: verifyCode @Description:
	 *         TODO @param @param request @param @param response @param @throws
	 *         IOException 设定文件 @return void @date 2017年5月7日 下午2:08:44 @throws
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
		if (code.equalsIgnoreCase(vcode)) {
			msg = "";
		} else {
			msg = "验证码错误，请重试！";
		}
		out.write(msg);
	}

	/**
	 * 
	 * 
	 * @author 西安工业大学蓝桥一期--王荣飞，王向宇 @Title: login @Description: TODO 从登录跳转至企业首页
	 *         enterpriseUser @param @param request @param @param
	 *         response @param @return @param @throws
	 *         ServletException @param @throws IOException 设定文件 @return
	 *         String @date 2017年5月7日 下午2:38:23 @throws
	 */
	@RequestMapping("/login")
	public String login(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "password", required = false) String password, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		EnterpriseUser u = (EnterpriseUser) request.getSession().getAttribute("enterpriseUser");
		boolean boo = true;
		if (u == null) {
			String password2 = MyUtils.md5(password);
			u = enterpriseUserServiceImpl.getUserByNameAndPassword(name, password2);
			// 获取系统验证码
			String code = ((String) request.getSession().getAttribute("code")).toLowerCase();
			// 获取用户输入的验证码
			String vcode = (request.getParameter("vcode")).toLowerCase();
			boo = code.equals(vcode);
		}
		if (u != null & boo != false) {
			System.out.println("mu=" + u);
			// 通过u获取一些列银行卡
			int allCardNum = 0;
			int payQuickNum = 0;
			List<BankCard> bankcards = u.getEnterprise().getBankcards();
			if(bankcards!=null){
				allCardNum = bankcards.size();
				for (BankCard bc : bankcards) {
					if (bc.getIsQuickPay() == 1) {
						payQuickNum++;
					}
				}
			}
			// 获取企业充值和提现记录
			List<Recharge_withdrawal> enterpriseRechargeAndWithdrawal = recharge_withdrawalServiceImpl
					.getEnterpriseRechargeAndWithdrawal(u.getEnterprise().getId());
			System.out.println("menterpriseRechargeAndWithdrawalSize=" + enterpriseRechargeAndWithdrawal.size());
			List<Recharge_withdrawal> enterpriseRecharge = new ArrayList<Recharge_withdrawal>();
			List<Recharge_withdrawal> enterpriseWithdrawal = new ArrayList<Recharge_withdrawal>();
			if (!enterpriseRechargeAndWithdrawal.isEmpty()) {
				for (Recharge_withdrawal rw : enterpriseRechargeAndWithdrawal) {
					if (rw.getOperation() == 3) {
						// 获取企业的充值记录
						enterpriseRecharge.add(rw);
					} else if (rw.getOperation() == 2) {
						// 获取企业提现记录
						enterpriseWithdrawal.add(rw);
					}
				}
			}
			System.out.println("enterpriseRechargeSize=" + enterpriseRecharge.size());
			System.out.println("enterpriseWithdrawalSize=" + enterpriseWithdrawal.size());
			// 获取企业支付记录
			List<Transfer> enterprisePayItems = transferServiceImpl.getEnterprisePayItems(u.getEnterprise().getId());
			System.out.println("enterprisePayItemSize=" + enterprisePayItems.size());
			request.getSession().setAttribute("enterpriseRecharge", enterpriseRecharge);
			request.getSession().setAttribute("enterpriseWithdrawal", enterpriseWithdrawal);
			request.getSession().setAttribute("enterprisePayItems", enterprisePayItems);
			request.getSession().setAttribute("allCardNum", allCardNum);
			request.getSession().setAttribute("payQuickNum", payQuickNum);
			request.getSession().setAttribute("enterpriseUser", u);
			System.out.println(u);
			request.getRequestDispatcher("/WEB-INF/views/enterprise/enterprise_uc.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/enterprise_login.jsp").forward(request, response);
		}
		return null;
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--王增 @Title:
	 * getEnterpriseUserTradeHistory @Description: TODO
	 * 根据企业的交易条件进行分页显示充值或提现的记录. @param @param id @param @param
	 * pageNo @param @param userTradePage
	 * 由于企业的交易分页实体类内容和用户的一样,因此就没有特意新建一个 @param @param map @param @return
	 * 设定文件 @return String 返回类型 @date 2017年6月6日 下午1:21:11 @throws
	 */
	@RequestMapping("/enterpriseUserTradeHistory")
	public String getEnterpriseUserTradeHistory(@RequestParam("id") Integer id, String pageNo,
			UserTradePage userTradePage, Map<String, Object> map) {
		map.put("listPageType", 0);
		System.out.println("id:" + id);
		// 默认显示第一页(默认查询3个月内企业个人充值的交易状态任意的money任意的)
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
		Integer count = recharge_withdrawalServiceImpl.getEnterpriseUserTradeCount(userTradePage);
		System.out.println("count:" + count);
		userTradePage.setTotalRecords(count);
		// 根据分页对象取得记录
		List<Recharge_withdrawal> recharge_withdrawals = recharge_withdrawalServiceImpl
				.getRecharge_withdrawalsForEnterpriseUserByUserTradePage(userTradePage);
		for (Recharge_withdrawal recharge_withdrawal : recharge_withdrawals) {
			System.out.println(recharge_withdrawal);
		}
		map.put("userTradePage", userTradePage);
		map.put("recharge_withdrawals", recharge_withdrawals);
		Double balance = enterpriseUserServiceImpl.getEnterpriseUserByEnterpriseId(id).getBalance();
		System.out.println("balance:" + balance);
		map.put("enterpriseUserBalance", balance);
		return "enterpriseUser/tradeHistory";
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--王增 @Title:
	 * enterpriseUserTransferHistory @Description: TODO
	 * 企业用户的转账记录,这里同样参数userTradePage可用 @param @param id @param @param
	 * pageNo @param @param userTradePage @param @param map @param @return
	 * 设定文件 @return String 返回类型 @date 2017年6月6日 下午3:05:34 @throws
	 */
	@RequestMapping("/enterpriseUserTransferHistory")
	public String enterpriseUserTransferHistory(@RequestParam("id") Integer id, String pageNo,
			UserTradePage userTradePage, Map<String, Object> map) {
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
		Integer count = transferService.getEnterpriseUserTransferCount(userTradePage);
		System.out.println("count:" + count);
		userTradePage.setTotalRecords(count);
		// 根据分页对象取得记录
		List<Transfer> transfers = transferService.getTransfersForEnterpriseUserByUserTradePage(userTradePage);
		for (Transfer transfer : transfers) {
			System.out.println(transfer);
		}
		map.put("userTradePage", userTradePage);
		map.put("transfers", transfers);
		Double balance = enterpriseUserServiceImpl.getEnterpriseUserByEnterpriseId(id).getBalance();
		System.out.println("balance:" + balance);
		map.put("enterpriseUserBalance", balance);
		return "enterpriseUser/tradeHistory";
	}

	/**
	 * 获取会员信息 @author 西安工业大学蓝桥一期--韩冰 @Title: getEnterpriseUserInfo @Description:
	 * TODO（） @return String @date 2017年6月7日 下午10:55:49 @throws
	 */
	@RequestMapping("/getEnterpriseUser")
	public String getEnterpriseUserInfo() {
		return "enterprise/member_information";
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--邱新虎 @Title: toUpdatePhone @Description: TODO
	 * 点击提交时跳转页面@param @param @param @return 设定文件 @return int 返回类型 @date
	 * 2017年6月11日 上午12:00;02 @throws
	 * @param request 
	 */

	@RequestMapping("/toUpdatePhone")
	public String toUpdatePhone(HttpServletRequest request,PrintWriter out) {
		String tell=request.getParameter("linkManPhone");
		String strId = request.getParameter("id");
		System.out.println("&*^&*^&%%^$%$%^");
		System.out.println(strId);
		int id = Integer.parseInt(strId.replaceAll(" ", ""));
		System.out.println("id"+id);
		System.out.println("tell:"+tell);
		int line = enterpriseUserServiceImpl.updatePhone(id,tell);
		System.out.println(line+":::line");
		if(line == 1){
			return "secret_success";
		}
		else{
			return "enterprise/error_safe";
		}
			
		
	}
	
	
	/**
	 * 
	 * @author 西安工业大学蓝桥一期--邱新虎 @Title: modifyPhone @Description: TODO
	 * 跳转修改电话页面 @param @param @param @return 设定文件 @return int 返回类型 @date
	 * 2017年6月11日 上午12:00;20 @throws
	 * @param request 
	 */
	@RequestMapping("/update")
	public String update(){
		return "enterprise/security_bound_phone";
	}
	
	@RequestMapping("/updatePhoto")
	public String updatePhoto(){
		return  "enterprise/member_information";
	}
	
	/**
	 * 修改企业用户头像--王向宇
	 */
	@RequestMapping("/modifyPhoto")
	public String modifyPhoto(@RequestParam(value="photo",required=false) MultipartFile  photo,HttpServletRequest request,HttpServletResponse response){
		EnterpriseUser eu=(EnterpriseUser)request.getSession().getAttribute("enterpriseUser");
		File oldPhoto=new File(eu.getPhoto());
		if(oldPhoto!=null){
			oldPhoto.delete();
		}
		String photoPath = FileUploadUtil.FileUpload(photo, request.getSession(), "/enterpriseFile");
		eu.setPhoto(photoPath);
		enterpriseUserServiceImpl.modifyPhotoById(eu);
		request.getSession().setAttribute("enterprsieUser", eu);
		return "enterprise/member_information";
	}
}
