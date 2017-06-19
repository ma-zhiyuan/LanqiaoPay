package org.lanqiao.pay.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.lanqiao.pay.base.entity.Bank;
import org.lanqiao.pay.base.entity.BankCard;
import org.lanqiao.pay.base.entity.CurrencyAmountRecord;
import org.lanqiao.pay.base.entity.Enterprise;
import org.lanqiao.pay.base.entity.EnterpriseUser;
import org.lanqiao.pay.base.entity.Recharge_withdrawal;
import org.lanqiao.pay.base.entity.Secret;
import org.lanqiao.pay.base.entity.Transfer;
import org.lanqiao.pay.base.entity.User;
import org.lanqiao.pay.base.util.MyUtils;
import org.lanqiao.pay.service.UserService;
import org.lanqiao.pay.serviceImpl.BankCardServiceImpl;
import org.lanqiao.pay.serviceImpl.BankServiceImpl;
import org.lanqiao.pay.serviceImpl.EnterpriseUserServiceImpl;
import org.lanqiao.pay.serviceImpl.SecretServiceImpl;
import org.lanqiao.pay.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 关于银行卡的业务
 * 
 * @author 西安工业大学蓝桥一期--刘江
 * @date 2017年5月3日 下午8:58:33
 */
@Controller
@RequestMapping("/BankController")
public class BankController {
	@Autowired
	BankServiceImpl bankServiceImpl;
	@Autowired
	SecretServiceImpl secretServiceImpl;
	@Autowired
	BankCardServiceImpl bankCardServiceImpl;
	@Autowired
	UserServiceImpl userServiceImpl; 
	@Autowired
	EnterpriseUserServiceImpl enterpriseUserServiceImpl;

	/**
     * 
	* @author 西安工业大学蓝桥一期--何璐
	* @Title: toTransfer 
	* @Description: TODO 跳转至转账页面 
	* @param @param req
	* @param @param session
	* @param @return    设定文件 
	* @return String    返回类型 
	* @date 2017年5月17日 下午8:33:27 
	* @throws
	 */
	@RequestMapping("/toTransfer")
	public String toTransfer(HttpServletRequest req,HttpSession session){
		User u = (User)session.getAttribute("user");
		List<BankCard> bcs = new ArrayList<BankCard>();
		//获取用户所有的银行卡
	    List<BankCard> bankCards = bankCardServiceImpl.getAllBankCard(u.getId());
	    //遍历所有的银行卡
	    for (BankCard bankCard : bankCards) {
	    	//默认卡
			if(bankCard.getIsDefault()==1){
				req.getSession().setAttribute("isDefaultCard", bankCard);
			}else{
			    //非默认卡
				bcs.add(bankCard);
				req.getSession().setAttribute("notDefaultCards", bcs);
			}
		}
		return "user/transfer";
	}
	/**
	 * 
	* @author 西安工业大学蓝桥一期--何璐
	* @Title: isQuickPay 
	* @Description: TODO 判断是否开通快捷支付 
	* @param @param bankNumber
	* @param @param res
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @date 2017年5月17日 下午8:34:05 
	* @throws
	 */
	@RequestMapping("/isQuickPay")
	public void isQuickPay(@RequestParam("fromBankNumber")String fromBankNumber,HttpServletResponse res,HttpServletRequest req) throws IOException{
		res.setCharacterEncoding("utf-8");
		PrintWriter out = res.getWriter();
		BankCard bankCard = bankCardServiceImpl.getBankCardByCardNumber(fromBankNumber);
		Integer i = bankCard.getIsQuickPay();
		if(i==1){
			out.print("已开通快捷支付 ,请直接输入支付密码");
		}else{
			out.print("尚未开通快捷支付，请输入银行卡密码");
		}
	}
	/**
	 * 
	* @author 西安工业大学蓝桥一期--何璐
	* @Title: isOverBalance 
	* @Description: TODO 判断转账金额是否超出银行卡余额
	* @param @param bankNumber
	* @param @param money
	* @param @param res
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @date 2017年5月17日 下午8:34:36 
	* @throws
	 */
	@RequestMapping("/IsOverBankCardBalance")
	public void isOverBalance(@RequestParam("toBankCardNum")String toBankCardNum,@RequestParam("transferWay")String transferWay,@RequestParam("fromBankNumber")String fromBankNumber,@RequestParam("money")Double money,@RequestParam("toObj")String toObj,HttpServletResponse res,HttpServletRequest req) throws IOException{
		res.setCharacterEncoding("utf-8");
		PrintWriter out = res.getWriter();
		Double sxf = 0.00;
		//转账方式为银行卡	
		BankCard formBankCard = bankCardServiceImpl.getBankCardByCardNumber(fromBankNumber);
		// 使用的银行的利率
		Double parities = formBankCard.getBankId().getParities();
		// 转账的手续费
		sxf = money % 100 == 0 ? (int) (money / 100) * parities : (int) (money / 100) * parities + parities;
		// 银行卡的余额
		Double balance2 = formBankCard.getBalance();
		// 转账金额大于用户银行卡余额且转账金额和手续费的和大于用户银行卡余额
		if (money > balance2 && (money + sxf) > balance2) {
			out.print("false");
		} else {
			String str = sxf.toString();
			out.print(str);
		}
		req.getSession().setAttribute("sxf", sxf);
	}
	/**
	 * 
	* @author 西安工业大学蓝桥一期--何璐
	* @Title: isOverUserBalance 
	* @Description: TODO  判断转账金额是否超出用户余额
	* @param @param money
	* @param @param toObj
	* @param @param toBankCardNum
	* @param @param session
	* @param @param res
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @date 2017年6月13日 下午4:02:13 
	* @throws
	 */
	@RequestMapping("/isOverUserBalance")
	public void isOverUserBalance(@RequestParam("money")Double money,@RequestParam("toObj")String toObj,@RequestParam("toBankCardNum")String toBankCardNum,HttpSession session,HttpServletResponse res,HttpServletRequest req) throws IOException{
		res.setCharacterEncoding("utf-8");
		PrintWriter out = res.getWriter();
		User user = (User) session.getAttribute("user");
		//获取用户的余额
		Double balance = user.getBalance();
		Double sxf = 0.00;
		//转入到企业的余额
		if(toObj.equals("1")){
			if(money>balance){
				out.print("false");
			}else{
				out.print("暂不收取手续费");
			}
		//转入到企业银行卡
		}else{
			//转入的银行
		    BankCard toBankCard = bankCardServiceImpl.getBankCardByCardNumber(toBankCardNum);
			// 使用的银行的利率
			Double parities = toBankCard.getBankId().getParities();
			// 转账的手续费
			sxf = money % 100 == 0 ? (int) (money / 100) * parities : (int) (money / 100) * parities + parities;
			if(money>balance&&(money+sxf)>balance){
				out.print("false");
			}else{
				String str = sxf.toString();
				out.print(str);
			}
		}
		req.getSession().setAttribute("sxf", sxf);
	}
	/**
	 * @throws IOException 
	 * 
	* @author 西安工业大学蓝桥一期--何璐
	* @Title: isPayPwdOrCardPwd 
	* @Description: TODO 转账方式为银行卡时，判断支付密码或银行卡密码是否正确
	* @param @param md5PayPwd
	* @param @param res    设定文件 
	* @return void    返回类型 
	* @date 2017年5月19日 上午9:16:43 
	* @throws
	 */
	@RequestMapping("/isPayPwdOrCardPwd")
	public void isPayPwdOrCardPwd(@RequestParam("fromBankNumber")String fromBankNumber,@RequestParam("md5Pwd")String md5PayPwd,HttpServletResponse res,HttpSession session) throws IOException{
		res.setCharacterEncoding("utf-8");
		PrintWriter out = res.getWriter();
		User user = (User) session.getAttribute("user");
		BankCard bankCard = bankCardServiceImpl.getBankCardByCardNumber(fromBankNumber);
		//银行卡是否开通快捷支付
		Integer i = bankCard.getIsQuickPay();
		//开通快捷支付，则判断支付密码是否正确
		if(i==1){
			boolean boo = bankServiceImpl.isPayPassword(user.getId(), md5PayPwd);
			if(boo){
				 out.print("true");
			}else{
				 out.print("false");
			}
			//未开通快捷支付，则判断银行卡密码是否正确
		}else{
			boolean boo = bankServiceImpl.isBankCardPwd(fromBankNumber, md5PayPwd);
			if(boo){
			     out.print("true");
			}else{
				 out.print("false");
			}
		}
		
	}
	/**
	 * 
	* @author 西安工业大学蓝桥一期--何璐
	* @Title: isPayPwd 
	* @Description: TODO  转账方式是用户余额时，判断支付密码是否正确 
	* @param @param payPwd
	* @param @param session
	* @param @param res
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @date 2017年6月13日 下午5:37:05 
	* @throws
	 */
	@RequestMapping("/isPayPwd")
	public void isPayPwd(@RequestParam("md5Pwd")String payPwd,HttpSession session,HttpServletResponse res) throws IOException{
		res.setCharacterEncoding("utf-8");
		PrintWriter out = res.getWriter();
		User user = (User) session.getAttribute("user");
		//判断支付密码是否正确
		boolean boo = bankServiceImpl.isPayPassword(user.getId(), payPwd);
		if(boo){
			out.print("true");
		}else{
			out.print("false");
		}
	}
	
	/**
	 * 
	* @author 西安工业大学蓝桥一期--何璐
	* @Title: transfer 
	* @Description: TODO 转账方式是用户银行卡时，转账的具体实现 
	* @param @param transferWay
	* @param @param fromBankNumber
	* @param @param toObj
	* @param @param transferName
	* @param @param money
	* @param @param epUserPhone
	* @param @param toBankCardNum
	* @param @param session
	* @param @param req
	* @param @param res
	* @param @return
	* @param @throws IOException    设定文件 
	* @return String    返回类型 
	* @date 2017年6月14日 上午12:22:38 
	* @throws
	 */
	@RequestMapping("/transferByUserBankCard")
	public void transfer(@RequestParam("transferWay")String transferWay,@RequestParam("fromBankNumber")String fromBankNumber,@RequestParam("toObj")String toObj,@RequestParam("transferName")String transferName,@RequestParam("money")Double money,@RequestParam("epUserPhone")String epUserPhone,@RequestParam("toBankCardNum")String toBankCardNum,HttpSession session,HttpServletRequest req,HttpServletResponse res) throws IOException{
		res.setCharacterEncoding("utf-8");
		PrintWriter out = res.getWriter();
		User user = (User) session.getAttribute("user");
		// 用户的余额
		Double balance = user.getBalance();
		BankCard fromBankCard = bankCardServiceImpl.getBankCardByCardNumber(fromBankNumber);
		// 转入到企业银行卡
		if (toBankCardNum != "") {
			BankCard toBankCard = bankCardServiceImpl.getBankCardByCardNumber(toBankCardNum);
			Transfer transfer = new Transfer(transferName, "个人向企业转账", user, toBankCard.getEnterpriseId(), fromBankCard,
					toBankCard, money, new Date(), 0);
			// 获取利率
			Double parities_money = (Double) session.getAttribute("sxf");
			CurrencyAmountRecord car = new CurrencyAmountRecord(user, null, parities_money, new Date());
			// 将利率插入到数据库中
			bankServiceImpl.insertIntoPeritiesMon(car);
			// 更新用户银行卡的余额
			Double balance2 = fromBankCard.getBalance();
			balance2 = balance2 - money - parities_money;
			fromBankCard.setBalance(balance2);
			bankCardServiceImpl.updateBankCard(fromBankCard);
			// 将转账记录插入到数据库中
			bankServiceImpl.insertTransfer(transfer);
			System.out.println("银行卡转银行卡...");
		// 转入到企业余额
		} else {
			// 企业法定人
			EnterpriseUser enUser = enterpriseUserServiceImpl.getEnterpriseUserByPhone(epUserPhone);
			// 转入的企业
			Enterprise enterprise = enUser.getEnterprise();
			Transfer transfer = new Transfer(transferName, "个人向企业转账", user, enterprise, fromBankCard, null, money,
					new Date(), 0);
			// 获取利率
			Double parities_money = (Double) session.getAttribute("sxf");
			CurrencyAmountRecord car = new CurrencyAmountRecord(user, enUser, parities_money, new Date());
			// 将利率插入到数据库中
			bankServiceImpl.insertIntoPeritiesMon(car);
			// 更新用户银行卡的余额
			Double balance2 = fromBankCard.getBalance();
			balance2 = balance2 - money - parities_money;
			fromBankCard.setBalance(balance2);
			bankCardServiceImpl.updateBankCard(fromBankCard);
			// 将转账记录插入到数据库中
			bankServiceImpl.insertTransfer(transfer);
			System.out.println("银行卡转余额。。。");
		}
		out.print("操作成功"); 
	}
	/**
	 * 
	* @author 西安工业大学蓝桥一期--何璐
	* @Title: transferByUserBalance 
	* @Description: TODO  转账方式为用户的余额时转账的具体实现
	* @param @param toObj
	* @param @param transferName
	* @param @param money
	* @param @param epUserPhone
	* @param @param toBankCardNum
	* @param @param session
	* @param @param req
	* @param @param res
	* @param @return
	* @param @throws IOException    设定文件 
	* @return String    返回类型 
	* @date 2017年6月13日 下午7:56:42 
	* @throws
	 */
	@RequestMapping("/transferByUserBalance")
	public void transferByUserBalance(@RequestParam("toObj")String toObj,@RequestParam("transferName")String transferName,@RequestParam("money")Double money,@RequestParam("epUserPhone")String epUserPhone,@RequestParam("toBankCardNum")String toBankCardNum,HttpSession session,HttpServletRequest req,HttpServletResponse res) throws IOException{
		res.setCharacterEncoding("utf-8");
		PrintWriter out = res.getWriter();
		User user = (User) session.getAttribute("user");
		// 用户的余额
		Double balance = user.getBalance();
		// 转入到企业银行卡
		if (toBankCardNum != "") {
			BankCard toBankCard = bankCardServiceImpl.getBankCardByCardNumber(toBankCardNum);
			Transfer transfer = new Transfer(transferName, "个人向企业转账", user, toBankCard.getEnterpriseId(), null,
					toBankCard, money, new Date(), 0);
			// 获取利率
			Double parities_money = (Double) session.getAttribute("sxf");
			CurrencyAmountRecord car = new CurrencyAmountRecord(user, null, parities_money, new Date());
			// 将利率插入到数据库中
			bankServiceImpl.insertIntoPeritiesMon(car);
			// 更新用户的余额
			balance = balance - money - parities_money;
			user.setBalance(balance);
			User u = new User(user.getId(), balance);
			userServiceImpl.updateUserBalanceOne(u);
			// 将转账记录插入到数据库中
			bankServiceImpl.insertTransfer(transfer);
			System.out.println("余额转银行卡。。。");
			// 转入到企业余额
		} else {
			// 企业法定人
			EnterpriseUser enUser = enterpriseUserServiceImpl.getEnterpriseUserByPhone(epUserPhone);
			// 转入的企业
			Enterprise enterprise = enUser.getEnterprise();
			Transfer transfer = new Transfer(transferName, "个人向企业转账", user, enterprise, null, null, money, new Date(),
					0);
			// 更新用户的余额
			balance = balance - money;
			user.setBalance(balance);
			User u = new User(user.getId(), balance);
			userServiceImpl.updateUserBalanceOne(u);
			// 将转账记录插入到数据库中
			bankServiceImpl.insertTransfer(transfer);
			System.out.println("余额转余额。。。");
		}
		out.print("操作成功"); 
	}
	/**
	 * 
	* @author 西安工业大学蓝桥一期--何璐
	* @Title: toSuccess 
	* @Description: TODO  操作成功后跳转到成功页面 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @date 2017年6月18日 下午2:47:17 
	* @throws
	 */
	@RequestMapping("/to_success")
	public String toSuccess(){
		return "bank/success";
	}
	
	/**
	 * 跳转至提现页面
	 * @author 孙航建
	 * @time 2017年5月5日 下午11:40:15
	 * @return
	 */
	@RequestMapping("/to_withdraw")
	public String to_withdraw(HttpSession session, HttpServletRequest request) {
		User user = (User) session.getAttribute("user");
		// 通过userId获取所有的bankCard信息
		List<BankCard> bankCard = bankCardServiceImpl.getAllBankCard(user.getId());
		int i = 0;
		for (BankCard b : bankCard) {
			if (b.getIsDefault() != null) {
				if (b.getIsDefault() == 1) {
					request.getSession().setAttribute("isdefaul", b);
				}
			}
			// 确认快捷支付张数
			if (b.getIsQuickPay() != null) {
				if (b.getIsQuickPay() == 1) {
					i = i + 1;
				}
			}
		}
		// 将需要的数据放到session中
		request.getSession().setAttribute("kuaiJie", i);
		request.getSession().setAttribute("bankCards", bankCard);
		request.getSession().setAttribute("bankcarLengh", bankCard.size());
    	return "bank/withdraw";
	}

	/**
	 * 判断用户余额是否超过所进行提现的金额
	 * @author 孙航建
	 * @throws IOException
	 * @time 2017年5月14日 下午6:09:27
	 */
	@RequestMapping("/moneyIsTrueOrFalse")
	public void moneyIsTrueOrFalse(@RequestParam(value = "id") Integer id, @RequestParam(value = "mon") Double money,
			HttpSession session, HttpServletResponse response, HttpServletRequest request, Map<String, Object> map)
			throws IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		User user = (User) session.getAttribute("user");
		String liy = (String) request.getParameter("liy");
		// 通过银行卡号获取出银行卡的汇率
		BankCard bank = bankCardServiceImpl.getBankcard(liy);
		// 根据利率计算利率
		request.getSession().setAttribute("bank", bank);
		// 将获取到的指定的银行卡的汇率放入session域中
		request.getSession().setAttribute("yhkh", liy);
		if (user.getBalance() >= money) {
			Double paritiesMoney = money % 100 == 0 ? (int) (money / 100) * bank.getBankId().getParities()
					: (int) (money / 100) * bank.getBankId().getParities() + bank.getBankId().getParities();
			String str = Double.toString(paritiesMoney);
			out.write(str);
		} else {
			out.write("false");
		}
	}
	
	/**
     * 判断企业提现的钱数是否符合要求
	 * @author 孙航建
	 * @time 2017年5月16日 下午3:46:49
	 * @param id
	 * @param money
	 * @param session
	 * @param response
	 * @param request
	 * @throws IOException
	 */
	@RequestMapping("/moneyIsTrueOrFalse_enterprise")
	public void moneyIsTrueOrFalse_enterprise(@RequestParam(value = "id") Integer id,
			@RequestParam(value = "mon") Double money, HttpSession session, HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		EnterpriseUser enUser = (EnterpriseUser) request.getSession().getAttribute("enterpriseUser");
		String liy = (String) request.getParameter("liy");
		// 通过银行卡号获取出银行卡的汇率
		BankCard bank = bankCardServiceImpl.getBankcard(liy);
		// 根据利率计算利率
		request.getSession().setAttribute("bank", bank);
		request.getSession().setAttribute("yhkhEnterprise", liy);
		if (enUser.getBalance() >= money) {
			Double paritiesMoney = money % 100 == 0 ? (int) (money / 100) * bank.getBankId().getParities()
					: (int) (money / 100) * bank.getBankId().getParities() + bank.getBankId().getParities();
			String str = Double.toString(paritiesMoney);
			out.write(str);
		} else {
			out.write("false");
		}
	}
/**
	 * 个人提现时后台插入一条数据
	 * @author 孙航建
	 * @time 2017年5月7日 上午11:04:03
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/to_winthdrawMoney")
	public String to_winthdrawMoney(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		response.setCharacterEncoding("UTF-8");
		User user = (User) session.getAttribute("user");
		// 获取前台页面的提现金额
		String moneyStr = request.getParameter("winthdrawMoney");
		double money = Double.parseDouble(moneyStr);
		// 获取用户指定的银行卡号
		String yhkh = (String) request.getSession().getAttribute("yhkh");
		String tranNamae = request.getParameter("tranNamae");
		BankCard bankcard = bankCardServiceImpl.getBankcard(yhkh);
		// 获取页面对应的金额的利息
		Double paritiesMoney = money % 100 == 0 ? (int) (money / 100) * bankcard.getBankId().getParities()
				: (int) (money / 100) * bankcard.getBankId().getParities() + bankcard.getBankId().getParities();
		CurrencyAmountRecord car = new CurrencyAmountRecord(user, null, paritiesMoney, new Date());
		// 将生成的利息插入到数据库中
		bankServiceImpl.insertIntoPeritiesMon(car);
		// 通过银行卡号获取指定的银行卡
		Recharge_withdrawal rw = new Recharge_withdrawal(user, null, bankcard, money, new Date(), 1, 0, tranNamae,
				"个人提现");
		// 获取用户的余额
		double balance = user.getBalance();
		balance = balance - money;
		// 为用户设置新的余额
		user.setBalance(balance);
		User u = new User(user.getId(), balance);
		// 去更新user的余额
		userServiceImpl.updateUserBalanceOne(u);
		// 插入数据库表中
		bankServiceImpl.toWinthdrawMoney(rw);
		return "bank/success";
	}

	/**
	 * 企业提现时后台插入一条数据
	 * 
	 * @author 孙航建
	 * @time 2017年5月16日 下午4:23:30
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/enterprise_money")
	public String to_winthdrawMoney_enterprise(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		EnterpriseUser enUser = (EnterpriseUser) request.getSession().getAttribute("enterpriseUser");
		// 获取前台页面的提现金额
		String moneyStr = request.getParameter("winthdrawMoney2");
		double money = Double.parseDouble(moneyStr);
		// 获取用户指定的银行卡号
		String yhkh = (String) request.getSession().getAttribute("yhkhEnterprise");
		String tranNamae = request.getParameter("tranNamae2");
		// 通过银行卡号获取指定的银行卡
		BankCard bankCard = bankCardServiceImpl.getBankcard(yhkh);
		// 获取页面对应的金额的利息
		Double paritiesMoney = money % 100 == 0 ? (int) (money / 100) * bankCard.getBankId().getParities()
				: (int) (money / 100) * bankCard.getBankId().getParities() + bankCard.getBankId().getParities();
		CurrencyAmountRecord car = new CurrencyAmountRecord(null, enUser, paritiesMoney, new Date());
		// 将企业提现生成的利息插入到数据库中
		bankServiceImpl.insertIntoPeritiesEnterpriseMon(car);
		Recharge_withdrawal rw = new Recharge_withdrawal(null, enUser.getEnterprise(), bankCard, money, new Date(), 2,
				3, tranNamae, "企业提现");
		// 通过enterpriseId获取法定代表人的balance
		double balance = enUser.getBalance();
		// 设置新的金额
		balance = balance - money;
		enUser.setBalance(balance);
		// 去数据库中更新其法定代表人的余额
		EnterpriseUser en = new EnterpriseUser(enUser.getId(), balance);
		enterpriseUserServiceImpl.updateEnterUserBalance(en);
		// 去数据库提现的一条记录插入数据
		bankServiceImpl.insertIntoEnterpriseWithdraw(rw);
		return "bank/success_enterprise_wit";
	}

	/**
	 * 测试企业提现的跳转页面
	 * @author 孙航建
	 * @time 2017年5月15日 下午11:15:16
	 * @return
	 */
	@RequestMapping("/enterprise_withdraw")
	public String enterprise_withdraw(HttpServletRequest request, HttpSession session, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		EnterpriseUser enUser = (EnterpriseUser) request.getSession().getAttribute("enterpriseUser");
		// 通过enterpriseId获取所有的银行卡信息
		List<BankCard> bankCard = bankCardServiceImpl.getAllBankCardByEnterPriseId(enUser.getEnterprise().getId());
		int i = 0;
		for (BankCard b : bankCard) {
			if (b.getIsDefault() != null) {
				if (b.getIsDefault() == 1) {
					request.getSession().setAttribute("isdefaut", b);
				}
			}
			// 确认快捷支付张数
			if (b.getIsQuickPay() != null) {
				if (b.getIsQuickPay() == 1) {
					i = i + 1;
				}
			}
			// 将需要的数据放到session中
			request.getSession().setAttribute("kJie", i);
			request.getSession().setAttribute("bCards", bankCard);
			request.getSession().setAttribute("bLengh", bankCard.size());
		}
		return "bank/enterprise_withdraw";
	}
	
	/**
	 * 判断用户支付密码支付一致
	 * @author 孙航建
	 * @time 2017年5月23日 下午11:49:29
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/pwdIsUserd")
	public void pwdIsUserd(HttpServletRequest request,HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		String strId = request.getParameter("id");
		Integer id = Integer.valueOf(strId);
		// 根据SecretId获取支付密码
		Secret secret = secretServiceImpl.getSecretById(id);
		String pwd = request.getParameter("payPassword");
		pwd = MyUtils.md5(pwd);
		boolean boo = pwd.equals(secret.getPayPassword());
		if(boo){
			out.write("true");
		} else {
			out.write("false");
		}
	}
	

	/**
	 * 
	 * @author 孙航建
	 * @time 2017年5月7日 上午10:22:53
	 * @return
	 */
	@RequestMapping("/to_uc")
	public String to_uc() {
		return "user/uc";
	}

	/**
	 * 跳入转换银行卡的页面
	 * 
	 * @author 孙航建
	 * @time 2017年5月15日 下午10:45:46
	 * @return
	 */
	@RequestMapping("/select_bankcard")
	public String select_bankcard() {
		return "bank/select_bankcard";
	}

	/**
	 * 跳入企业的银行卡转换的页面
	 * 
	 * @author 孙航建
	 * @time 2017年5月16日 下午3:23:37
	 * @return
	 */
	@RequestMapping("/select_bankcard_enterprise")
	public String select_bankcard_enterprise() {
		return "bank/select_bankcard_enterprise";
	}
	/**
	 * 
	 * 
	* @author 西安工业大学蓝桥一期--王荣飞
	* @Title: addBank 
	* @Description: TODO
	* @param @param id
	* @param @param name
	* @param @param response
	* @param @throws IOException    设定文件 
	* @return void
	* @date 2017年5月26日 下午10:55:42 
	* @throws
	 */
	@RequestMapping("/addbank")
	public void addBank(@RequestParam(value="id",required=false) Integer id,@RequestParam(value="name",required=false) String name,HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		bankServiceImpl.addbank(id,name);
		String msg="添加成功！";
		out.print(msg);
	}
	/**
	 * 
	 * 
	* @author 西安工业大学蓝桥一期--王荣飞
	* @Title: deleteBank 
	* @Description: TODO
	* @param @param id
	* @param @param request
	* @param @param response    设定文件 
	* @return void
	* @date 2017年5月26日 下午10:56:03 
	* @throws
	 */
	@RequestMapping("/deleteBank")
	public void deleteBank(@RequestParam("id") Integer id,HttpServletRequest request,HttpServletResponse response){
		
		Integer i=bankServiceImpl.deleteBank(id);
		System.out.println(i);
		if(i>0){
			
			try {
				request.getRequestDispatcher("../adminController/goBankList").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * 
	* @author 西安工业大学蓝桥一期--王荣飞
	* @Title: updateBank 
	* @Description: TODO
	* @param @param id
	* @param @param name
	* @param @param response
	* @param @throws IOException    设定文件 
	* @return void
	* @date 2017年5月26日 下午10:56:18 
	* @throws
	 */
	@RequestMapping("/updateBank")
	public void updateBank(@RequestParam(value="id",required=false) Integer id,@RequestParam(value="name",required=false) String name,HttpServletResponse response) throws IOException{
		System.out.println(id);
		System.out.println(name);
		PrintWriter out=response.getWriter();
		int i=bankServiceImpl.vertifyBankName(name);
		System.out.println(i);
		String msg="";
		if(i>0){
			msg="该名称已存在！";
			out.print(msg);
		}else{
			int j=bankServiceImpl.updateBank(id,name);
			msg="修改成功！";
			out.print(msg);
		}
	}

	
	 /**
	 * @throws IOException 
     * 个人向个人银行卡转账
     * @author 西安工业大学蓝桥一期-代显峰
     * @title userTransfer
     * @description	TODO去数据库插入一条转账记录
     * @param @param request
     * @param @param response
     * @param @return
     * @return String
     * @date 2017年5月21日下午1:16:25
     * @throws
     */
    @RequestMapping("/userTransfertoBank")
    public void userTransfertoBank(HttpServletRequest request,HttpServletResponse response) throws IOException{
    	System.out.println("11111111111111************");
    	PrintWriter out = response.getWriter();
    	User u = (User) request.getSession().getAttribute("user");
		String money = request.getParameter("money");
		String toCardNumber = request.getParameter("toBankNumber");
		String transactionName = request.getParameter("transactionName");
		User fromUser = userServiceImpl.getUser(u.getId());
		BankCard toBankCard =	bankCardServiceImpl.getBankCardByCardNumber(toCardNumber);
		Transfer t = new Transfer(fromUser,toBankCard,Double.parseDouble(money),new Date(),transactionName,0,"个人银行卡转入个人银行卡");
		bankServiceImpl.userInsertTransferToUserBank(t);
		out.write("操作成功");
    }
    /**
     * @throws IOException 
     * 个人使用余额向个人支付宝转账
     * @author 西安工业大学蓝桥一期-代显峰
     * @title userTransfer
     * @description	TODO去数据库插入一条转账记录
     * @param @param request
     * @param @param response
     * @param @return
     * @return String
     * @date 2017年5月21日下午1:16:25
     * @throws
     */
    @RequestMapping("/userTransfertoUser")
    public void userTransfertoUser(HttpServletRequest request,HttpServletResponse response) throws IOException{
    	System.out.println("222222222222222***************");
    	PrintWriter out = response.getWriter();
    	User u = (User) request.getSession().getAttribute("user");
		String money = request.getParameter("money");
		String phone = request.getParameter("userName");
		String tranferName = request.getParameter("transactionName");
		User fromUser = userServiceImpl.getUser(u.getId());
		User toUser = userServiceImpl.getUser(phone);
		Transfer t = new Transfer(fromUser,toUser,Double.parseDouble(money),new Date(),tranferName,0,"个人余额转入个人支付宝");
		bankServiceImpl.userInsertTransferToUser(t);
		out.write("操作成功");
    }
    /**
     * @throws IOException 
     * 个人银行卡向个人支付宝转账
     * @author 西安工业大学蓝桥一期-代显峰
     * @title userTransfer
     * @description	TODO去数据库插入一条转账记录
     * @param @param request
     * @param @param response
     * @param @return
     * @return String
     * @date 2017年5月21日下午1:16:25
     * @throws
     */
    @RequestMapping("/bankTransfertoUser")
    public void bankTransfertoUser(HttpServletRequest request,HttpServletResponse response) throws IOException{
    	System.out.println("333333333333**************");
    	PrintWriter out = response.getWriter();
    	String fromCardNumber = request.getParameter("fromBankNumber");
		String money = request.getParameter("money");
		String phone = request.getParameter("userName");
		String transferName = request.getParameter("transferName");
		System.out.println(transferName);
		BankCard fromBankCard =	bankCardServiceImpl.getBankCardByCardNumber(fromCardNumber);
		User toUser = userServiceImpl.getUser(phone);
		Transfer t = new Transfer(fromBankCard,toUser,Double.parseDouble(money),new Date(), transferName,0,"个人银行卡转入个人支付宝");
		bankServiceImpl.userBankInsertTransferToUser(t);
		out.write("操作成功");
    }
    /**
     * @throws IOException 
     * 个人银行卡向个人银行卡转账
     * @author 西安工业大学蓝桥一期-代显峰
     * @title userTransfer
     * @description	TODO去数据库插入一条转账记录
     * @param @param request
     * @param @param response
     * @param @return
     * @return String
     * @date 2017年5月21日下午1:16:25
     * @throws
     */
    @RequestMapping("/bankTransfertoBank")
    public void bankTransfertoBank(HttpServletRequest request,HttpServletResponse response) throws IOException{
    	PrintWriter out = response.getWriter();
    	String fromCardNumber = request.getParameter("fromBankNumber");
		String money = request.getParameter("money");
		String toCardNumber = request.getParameter("toBankNumber");
		System.out.println(toCardNumber);
		String transactionName = request.getParameter("transferName");
		BankCard fromBankCard =	bankCardServiceImpl.getBankCardByCardNumber(fromCardNumber);
		System.out.println(fromBankCard);
		BankCard toBankCard =	bankCardServiceImpl.getBankCardByCardNumber(toCardNumber);
		System.out.println(toBankCard);
		Transfer t = new Transfer(fromBankCard,toBankCard,Double.parseDouble(money),new Date(),transactionName,0,"个人银行卡转入个人银行卡");
		bankServiceImpl.userBankInsertTransferToUserBank(t);
		out.write("操作成功");

    }
  
}
