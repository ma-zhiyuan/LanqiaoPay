package org.lanqiao.pay.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.lanqiao.pay.base.entity.BankCard;
import org.lanqiao.pay.base.entity.CurrencyAmountRecord;
import org.lanqiao.pay.base.entity.CustomerCare;
import org.lanqiao.pay.base.entity.Enterprise;
import org.lanqiao.pay.base.entity.EnterpriseUser;
import org.lanqiao.pay.base.entity.Transfer;
import org.lanqiao.pay.base.entity.User;
import org.lanqiao.pay.service.TransferService;
import org.lanqiao.pay.serviceImpl.BankCardServiceImpl;
import org.lanqiao.pay.serviceImpl.BankServiceImpl;
import org.lanqiao.pay.serviceImpl.CustomerCareServiceImpl;
import org.lanqiao.pay.serviceImpl.EnterpriseServiceImpl;
import org.lanqiao.pay.serviceImpl.EnterpriseUserServiceImpl;
import org.lanqiao.pay.serviceImpl.TransferServiceImpl;
import org.lanqiao.pay.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Title: TransferConrtoller.java
 * @Package org.lanqiao.pay.controller
 * @Description: 转帐的操作
 * @author 西安工业大学蓝桥一期--毋泽航
 * @date 2017年5月24日 下午7:38:14
 */
@Controller
@RequestMapping("/transferController")
public class TransferConrtoller {
	@Autowired
	TransferServiceImpl transferServiceImpl;
	@Autowired
	BankCardServiceImpl bankCardServiceImpl;
	@Autowired
	EnterpriseUserServiceImpl enterpriseUserServiceImpl;
	@Autowired
	UserServiceImpl userServiceImpl;
	@Autowired
	BankServiceImpl bankServiceImpl;
	@Autowired
	EnterpriseServiceImpl enterpriseServiceImpl;
	@Autowired
	CustomerCareServiceImpl customerCareServiceImpl;

	/**
	 * @author 西安工业大学蓝桥一期--毋泽航 @Title: to_Enterprise_transfer @Description:
	 *         转到企业转账页面 @date 2017年5月26日 下午6:14:22 @throws
	 */
	@RequestMapping("/to_Enterprise_transfer")
	public String to_Enterprise_transfer(HttpServletRequest request) {
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
		// 目前测试阶段可没有对象
		return "transfers/enterprise_transfer";
	}

	/**
	 * 判断用户输入的账户是否正确
	 * @author 孙航建
	 * @throws IOException
	 * @time 2017年6月4日 下午2:53:03
	 */
	@RequestMapping("/IsTrueOrFalsePhone")
	public void IsTrueOrFalsePhone(HttpServletResponse response, HttpServletRequest request) throws IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		// 获取页面中输入的账号、
		String phone = request.getParameter("userPhone");
		// 判断页面转入金额的个人账户在数据库中是否存在
		User user = userServiceImpl.isTrueOrFalsePhone(phone);
		if (user != null) {
			out.write("true");
		} else {
			out.write("false");
		}
	}

	/**
	 * @author 西安工业大学蓝桥一期--毋泽航 @Title: IsTrueOrFalseEpPhone @Description:
	 * 判断企业账户是否存在 @date 2017年6月7日 下午11:38:51 @throws
	 */
	@RequestMapping("/IsTrueOrFalseEpPhone")
	public void IsTrueOrFalseEpPhone(HttpServletResponse response, HttpServletRequest request) throws IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		// 获取页面中输入的企业名称、
		String businessName = request.getParameter("entName");
		// 判断页面转入金额的企业账户在数据库中是否存在
		Enterprise ep = enterpriseServiceImpl.getEnterpriseByEpName(businessName);
		if (ep != null) {
			out.write("true");
		} else {
			out.write("false");
		}
	}

	/**
	 * 判断转账的金额是否符合要求
	 * @author 孙航建
	 * @time 2017年6月1日 下午3:57:18
	 * @param id
	 * @param money
	 * @param session
	 * @param response
	 * @param request
	 * @throws IOException
	 */
	@RequestMapping("/TranMoneyIsTrueOrFalse_enterprise")
	public void moneyIsTrueOrFalse_enterprise(HttpSession session, HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String cardNum = request.getParameter("userCareID1");
		// 获取页面输入的转账的余额
		String tranMoney = request.getParameter("tranMoney");
		double tranMoney2 = Double.parseDouble(tranMoney);
		// 获取页面进行转账的方式
		String inlineRadioOptions = request.getParameter("inlineRadioOptions");
		// 获取余额宝中的金额
		String allMoney = request.getParameter("allMoney");
		double allMoney2 = Double.parseDouble(allMoney);
		// 获取选定的银行卡号
		String liy = request.getParameter("liy");
		// 通过银行卡号获取相应的银行卡账户的余额及相应的汇率
		BankCard bank = bankCardServiceImpl.getBankcard(liy);
		request.getSession().setAttribute("tranEnter", liy);
		// 如果是选择支付账号进行转账时，则不收取任何费用
		if (inlineRadioOptions.equals("yue") & allMoney2 >= tranMoney2) {
			String str = "0.00";
			if (cardNum != null) {
				Double paritiesMoney = tranMoney2 % 100 == 0 ? (int) (tranMoney2 / 100) * bank.getBankId().getParities()
						: (int) (tranMoney2 / 100) * bank.getBankId().getParities() + bank.getBankId().getParities();
				str = Double.toString(paritiesMoney);
				out.write(str);
			}
			out.write(str);
		} else if (inlineRadioOptions.equals("yhk") & bank.getBalance() >= tranMoney2) {
			Double paritiesMoney = tranMoney2 % 100 == 0 ? (int) (tranMoney2 / 100) * bank.getBankId().getParities()
					: (int) (tranMoney2 / 100) * bank.getBankId().getParities() + bank.getBankId().getParities();
			String str = Double.toString(paritiesMoney);
			out.write(str);
		} else {
			out.write("false");
		}
	}

	/**
	 * 企业向个人的银行卡转账或者项个人的余额宝转账
	 * @author 孙航建
	 * @time 2017年6月1日 下午2:52:03
	 * @return
	 */
	@RequestMapping("/transferToPersonFromEnterprise")
	public String transferToPersonFromEnterprise(HttpServletRequest request) {
		// 获取进行转账的企业的法定人的信息
		EnterpriseUser enUser = (EnterpriseUser) request.getSession().getAttribute("enterpriseUser");
		String personPhone = request.getParameter("epName");
		// 通过指定的账户获取转入用户的信息
		User user = userServiceImpl.getUserByPhone(personPhone);
		String transactionName1 = request.getParameter("transactionName");
		String transferMoney1 = request.getParameter("transferMoney");
		double money = Double.parseDouble(transferMoney1);
		String inlineRadioOptions1 = request.getParameter("inlineRadioOptions");
		// 获取银行卡号
		String yhkh = (String) request.getSession().getAttribute("tranEnter");
		// 通过银行卡号获取银行卡的信息
		BankCard bankCard = bankCardServiceImpl.getBankcard(yhkh);
		// 获取页面对应的金额的利息
		Double paritiesMoney = money % 100 == 0 ? (int) (money / 100) * bankCard.getBankId().getParities()
				: (int) (money / 100) * bankCard.getBankId().getParities() + bankCard.getBankId().getParities();
		// 如果转账的方式是yue，则进行相关的操作
		if (inlineRadioOptions1.equals("yue")) {
			// 没有利息的生成，不进行相关的操作
			// 通过enterpriseId获取法定代表人的balance
			double balance = enUser.getBalance();
			// 设置新的金额
			balance = balance - money;
			enUser.setBalance(balance);
			// 去数据库中更新其法定代表人的余额
			EnterpriseUser en = new EnterpriseUser(enUser.getId(), balance);
			enterpriseUserServiceImpl.updateEnterUserBalance(en);
			// 生成转账记录
			Transfer transfer = new Transfer(transactionName1, "企业余额向个人余额转账", null, enUser.getEnterprise(), user, null,
					null, null, money, new Date(), 0);
			// 将生成的转账记录插入到数据库中
			transferServiceImpl.insertIntoTransferFromEnterToUserByYue(transfer);
		} else if (inlineRadioOptions1.equals("yhk")) {
			// 将企业向银行卡转账所生成的记录插入到数据库中
			CurrencyAmountRecord car = new CurrencyAmountRecord(null, enUser, paritiesMoney, new Date());
			// 将企业转账生成的利息插入到数据库中
			bankServiceImpl.insertIntoPeritiesEnterpriseMon(car);
			// 所有的利息都通过银行卡的余额进行扣除
			double monlx = bankCard.getBalance() - paritiesMoney - money;
			bankCard.setBalance(monlx);
			// 去数据库中更新银行卡的余额
			bankCardServiceImpl.updateBankCardByIdAndBalance(bankCard);
			// 生成转账记录
			Transfer transfer = new Transfer(transactionName1, "企业银行卡向个人余额转账", null, enUser.getEnterprise(), user, null,
					null, bankCard, money, new Date(), 0);
			// 将生成的企业余额向用户银行卡转账记录插入到数据库中
			transferServiceImpl.insertIntoTransferFromEnterToUserByYhk(transfer);
		}
		return "transfers/success_tranEnter";
	}

	/**
	 * @author 西安工业大学蓝桥一期--毋泽航 @Title: transferToEpFromEnterprise @Description:
	 * 向企业的法定人余额转账 @date 2017年6月8日 上午10:17:45 @throws
	 */
	@RequestMapping("/transferToEpFromEnterprise")
	public String transferToEpFromEnterprise(HttpServletRequest request) {
		String businessName = request.getParameter("entName");
		// 获取进行转账的企业的法定人的信息
		EnterpriseUser enUser = (EnterpriseUser) request.getSession().getAttribute("enterpriseUser");
		// 通过指定的账户获取转入企业的信息
		Enterprise enterprise = enterpriseServiceImpl.getEnterpriseByEpName(businessName);
		String transactionName1 = request.getParameter("transactionName");
		String transferMoney1 = request.getParameter("transferMoney");
		double money = Double.parseDouble(transferMoney1);
		String inlineRadioOptions1 = request.getParameter("inlineRadioOptions");
		// 获取银行卡号
		String yhkh = (String) request.getSession().getAttribute("tranEnter");
		// 通过银行卡号获取银行卡的信息
		BankCard bankCard = bankCardServiceImpl.getBankcard(yhkh);
		// 获取页面对应的金额的利息
		Double paritiesMoney = money % 100 == 0 ? (int) (money / 100) * bankCard.getBankId().getParities()
				: (int) (money / 100) * bankCard.getBankId().getParities() + bankCard.getBankId().getParities();
		// 如果转账的方式是yue，则进行相关的操作
		if (inlineRadioOptions1.equals("yue")) {
			// 没有利息的生成，不进行相关的操作
			// 通过enterpriseId获取法定代表人的balance
			double balance = enUser.getBalance();
			// 设置新的金额
			balance = balance - money;
			enUser.setBalance(balance);
			// 去数据库中更新其法定代表人的余额
			EnterpriseUser en = new EnterpriseUser(enUser.getId(), balance);
			enterpriseUserServiceImpl.updateEnterUserBalance(en);
			// 生成转账记录
			Transfer transfer = new Transfer(transactionName1, "企业余额向企业余额转账", null, enUser.getEnterprise(), null,
					enterprise, null, null, money, new Date(), 0);
			// 将生成的转账记录插入到数据库中
			transferServiceImpl.insertIntoTransferFromEnterToUserByYue(transfer);
		} else if (inlineRadioOptions1.equals("yhk")) {
			// 将企业向银行卡转账所生成的记录插入到数据库中
			CurrencyAmountRecord car = new CurrencyAmountRecord(null, enUser, paritiesMoney, new Date());
			// 将企业转账生成的利息插入到数据库中
			bankServiceImpl.insertIntoPeritiesEnterpriseMon(car);
			// 所有的利息都通过银行卡的余额进行扣除
			double monlx = bankCard.getBalance() - paritiesMoney - money;
			bankCard.setBalance(monlx);
			// 去数据库中更新银行卡的余额
			bankCardServiceImpl.updateBankCardByIdAndBalance(bankCard);
			// 生成转账记录
			Transfer transfer = new Transfer(transactionName1, "企业银行卡向企业余额转账", null, enUser.getEnterprise(), null,
					enterprise, bankCard, null, money, new Date(), 0);
			// 将生成的企业余额向用户银行卡转账记录插入到数据库中
			transferServiceImpl.insertIntoTransferFromEnterToUserByYhk(transfer);
		}
		return "transfers/success_tranEnter";
	}

	/**
	 * 企业通过银行卡向个人的银行卡转账或者项个人的余额宝转账
	 * @author 孙航建
	 * @time 2017年6月4日 下午9:06:51
	 * @return
	 */
	@RequestMapping("/transferToPersonFromEnterpriseBank")
	public String transferToPersonFromEnterpriseBank(HttpServletRequest request) {
		// 获取页面传入的银行卡号
		String userCardNum = request.getParameter("userCareID1");
		// 通过银行卡号获取user的信息
		BankCard userBankCard = bankCardServiceImpl.getBankByUserId(userCardNum);
		String transactionName = request.getParameter("transactionName");
		String transferMoney = request.getParameter("transferMoney");
		double money = Double.parseDouble(transferMoney);
		String inlineRadioOptions = request.getParameter("inlineRadioOptions");
		// 获取进行转账的企业的法定人的信息
		EnterpriseUser enUser = (EnterpriseUser) request.getSession().getAttribute("enterpriseUser");
		// 获取银行卡号
		String yhkh = (String) request.getSession().getAttribute("tranEnter");
		// 通过银行卡号获取银行卡的信息
		BankCard bankCard = bankCardServiceImpl.getBankcard(yhkh);
		// 获取页面对应的金额的利息
		Double paritiesMoney = money % 100 == 0 ? (int) (money / 100) * bankCard.getBankId().getParities()
				: (int) (money / 100) * bankCard.getBankId().getParities() + bankCard.getBankId().getParities();
		// 如果转账的方式是yue，则进行相关的操作
		if (inlineRadioOptions.equals("yue")) {
			// 将企业向银行卡转账所生成的记录插入到数据库中
			CurrencyAmountRecord car = new CurrencyAmountRecord(null, enUser, paritiesMoney, new Date());
			// 将企业转账生成的利息插入到数据库中
			bankServiceImpl.insertIntoPeritiesEnterpriseMon(car);
			// 设置新的法定企业人余额宝中的余额
			double mon = enUser.getBalance() - paritiesMoney - money;
			// 设置新的余额
			enUser.setBalance(mon);
			// 去数据库中更新企业法定人的余额
			enterpriseUserServiceImpl.updateEnterUserBalance(enUser);
			// 生成转账记录
			Transfer transfer = new Transfer(transactionName, "企业余额向个人银行卡转账", null, enUser.getEnterprise(),
					userBankCard.getUserId(), null, null, null, money, new Date(), 0);
			// 将生成的转账记录插入到数据库中
			transferServiceImpl.insertIntoTransferFromEnterToUserByYue(transfer);
		} else if (inlineRadioOptions.equals("yhk")) {
			// 将企业向银行卡转账所生成的记录插入到数据库中
			CurrencyAmountRecord car = new CurrencyAmountRecord(null, enUser, paritiesMoney, new Date());
			// 将企业转账生成的利息插入到数据库中
			bankServiceImpl.insertIntoPeritiesEnterpriseMon(car);
			// 所有的利息都通过银行卡的余额进行扣除
			double monlx = bankCard.getBalance() - paritiesMoney - money;
			bankCard.setBalance(monlx);
			// 去数据库中更新银行卡的余额
			bankCardServiceImpl.updateBankCardByIdAndBalance(bankCard);
			// 生成转账记录
			Transfer transfer = new Transfer(transactionName, "企业银行卡向个人银行卡转账", null, enUser.getEnterprise(),
					userBankCard.getUserId(), null, null, bankCard, money, new Date(), 0);
			// 将生成的企业余额向用户银行卡转账记录插入到数据库中
			transferServiceImpl.insertIntoTransferFromEnterToUserByYhk(transfer);
		}
		return "transfers/success_tranEnter";
	}

	/**
	 * @author 西安工业大学蓝桥一期--毋泽航 @Title:
	 * transferToEpFromEnterpriseBank @Description: 向企业的法定人银行卡转账 @date 2017年6月8日
	 * 上午10:18:10 @throws
	 */
	@RequestMapping("/transferToEpFromEnterpriseBank")
	public String transferToEpFromEnterpriseBank(HttpServletRequest request) {
		// 获取转入银行卡的卡号
		String businessUserCardNum = request.getParameter("entUserNameCareID");
		// 通过银行卡号获取法定人银行卡的信息
		BankCard userBankCard = bankCardServiceImpl.getBankCardByCardNumber(businessUserCardNum);
		String transactionName = request.getParameter("transactionName");
		String transferMoney = request.getParameter("transferMoney");
		double money = Double.parseDouble(transferMoney);
		String inlineRadioOptions = request.getParameter("inlineRadioOptions");
		// 获取进行转账的企业的法定人的信息
		EnterpriseUser enUser = (EnterpriseUser) request.getSession().getAttribute("enterpriseUser");
		// 获取银行卡号
		String yhkh = (String) request.getSession().getAttribute("tranEnter");
		// 通过银行卡号获取银行卡的信息
		BankCard bankCard = bankCardServiceImpl.getBankcard(yhkh);
		// 获取页面对应的金额的利息
		Double paritiesMoney = money % 100 == 0 ? (int) (money / 100) * bankCard.getBankId().getParities()
				: (int) (money / 100) * bankCard.getBankId().getParities() + bankCard.getBankId().getParities();
		// 如果转账的方式是yue，则进行相关的操作
		if (inlineRadioOptions.equals("yue")) {
			// 将企业向银行卡转账所生成的记录插入到数据库中
			CurrencyAmountRecord car = new CurrencyAmountRecord(null, enUser, paritiesMoney, new Date());
			// 将企业转账生成的利息插入到数据库中
			bankServiceImpl.insertIntoPeritiesEnterpriseMon(car);
			// 设置新的法定企业人余额宝中的余额
			double mon = enUser.getBalance() - paritiesMoney - money;
			// 设置新的余额
			enUser.setBalance(mon);
			// 去数据库中更新企业法定人的余额
			enterpriseUserServiceImpl.updateEnterUserBalance(enUser);
			// 生成转账记录
			Transfer transfer = new Transfer(transactionName, "企业余额向企业银行卡转账", null, enUser.getEnterprise(), null,
					userBankCard.getEnterpriseId(), null, userBankCard, money, new Date(), 0);
			// 将生成的转账记录插入到数据库中
			transferServiceImpl.insertIntoTransferFromEnterToUserByYue(transfer);
		} else if (inlineRadioOptions.equals("yhk")) {
			// 将企业向银行卡转账所生成的记录插入到数据库中
			CurrencyAmountRecord car = new CurrencyAmountRecord(null, enUser, paritiesMoney, new Date());
			// 将企业转账生成的利息插入到数据库中
			bankServiceImpl.insertIntoPeritiesEnterpriseMon(car);
			// 所有的利息都通过银行卡的余额进行扣除
			double monlx = bankCard.getBalance() - paritiesMoney - money;
			bankCard.setBalance(monlx);
			// 去数据库中更新银行卡的余额
			bankCardServiceImpl.updateBankCardByIdAndBalance(bankCard);
			// 生成转账记录
			Transfer transfer = new Transfer(transactionName, "企业银行卡向企业银行卡转账", null, enUser.getEnterprise(), null,
					userBankCard.getEnterpriseId(), bankCard, userBankCard, money, new Date(), 0);
			// 将生成的企业余额向用户银行卡转账记录插入到数据库中
			transferServiceImpl.insertIntoTransferFromEnterToUserByYhk(transfer);
		}
		return "transfers/success_tranEnter";
	}

	/**
	 * 验证银行卡号是否正确并且将获取到银行卡持有人的姓名回显至页面中
	 * @author 孙航建
	 * @throws IOException
	 * @time 2017年6月5日 下午5:53:08
	 */
	@SuppressWarnings("unused")
	@RequestMapping("/IsTrueOrFalseBankNum") // 1234467895511545121
	public void IsTrueOrFalseBankNum(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		// 获取页面传入的银行卡号
		String bankNum = request.getParameter("bankNum");
		// 通过银行卡号获取银行卡的信息
		BankCard bankCard = bankCardServiceImpl.getBankByUserId(bankNum);
		if (bankCard != null) {
			out.write(bankCard.getUserId().getName());
		} else if (bankCard == null) {
			out.write("false");
		}
	}

	/**
	 * @author 西安工业大学蓝桥一期--毋泽航 @Title: IsTrueOrFalseEpBankNum @Description:
	 * 验证企业法定人的银行卡是否正确 @date 2017年6月8日 上午12:29:13 @throws
	 */
	@SuppressWarnings("unused")
	@RequestMapping("/IsTrueOrFalseEpBankNum")
	public void IsTrueOrFalseEpBankNum(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		// 获取页面传入的银行卡号
		String bankNum = request.getParameter("bankNum");
		String businessUserName = request.getParameter("entUserName");
		// 通过银行卡号获取银行卡的信息
		BankCard bankCard = bankCardServiceImpl.getBankByUserId(bankNum);
		// 通过企业的id获取企业法定人的姓名
		EnterpriseUser enterpriseUser = enterpriseUserServiceImpl.getEnterpriseByenterpriseId(bankCard.getEnterpriseId().getId());
		if (bankCard != null) {
			if (bankCard.getUserId().getName().equals(businessUserName)) {
				out.write("true");
			}
		} else if (bankCard == null) {
			out.write("false");
		}
	}

	/**
	 * 判断企业向个人银行家输入的金额与余额宝的金额和所选定的银行的金额是否符合规范要求
	 * 
	 * @author 孙航建
	 * @throws IOException
	 * @time 2017年6月5日 下午7:33:44
	 */
	@RequestMapping("/TranMoneyIsTrueOrFalse_enterpriseBank")
	public void TranMoneyIsTrueOrFalse_enterpriseToPersonBanks(HttpSession session, HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		// 获取页面输入的转账的余额
		String tranMoney = request.getParameter("tranMoney");
		double tranMoney2 = Double.parseDouble(tranMoney);
		// 获取页面进行转账的方式
		String inlineRadioOptions = request.getParameter("inlineRadioOptions");
		// 获取余额宝中的金额
		String allMoney = request.getParameter("allMoney");
		double allMoney2 = Double.parseDouble(allMoney);
		// 获取选定的银行卡号
		String liy = request.getParameter("liy");
		// 通过银行卡号获取相应的银行卡账户的余额及相应的汇率
		BankCard bank = bankCardServiceImpl.getBankcard(liy);
		request.getSession().setAttribute("tranEnter", liy);
		// 如果是选择支付账号进行转账时，则不收取任何费用
		if (allMoney2 >= tranMoney2 || bank.getBalance() >= tranMoney2) {
			Double paritiesMoney = tranMoney2 % 100 == 0 ? (int) (tranMoney2 / 100) * bank.getBankId().getParities()
					: (int) (tranMoney2 / 100) * bank.getBankId().getParities() + bank.getBankId().getParities();
			String str = Double.toString(paritiesMoney);
			out.write(str);
		} else {
			out.write("false");
		}
	}

	/**
	 * 转账操作成功之后返回企业主页面
	 * 
	 * @author 孙航建
	 * @time 2017年6月6日 下午10:34:27
	 * @return
	 */
	@RequestMapping("/to_enterprise_center")
	public String to_enterprise_center() {
		return "enterprise/enterprise_uc";
	}
	

	/**
	 * 
	* @author 西安工业大学蓝桥一期--董正健
	* @Title: updateState 
	* @Description: 往转入用户账户添加金额，并修改状态
	* @param @param id
	* @param @return    设定文件 
	* @return String    返回类型 
	* @date 2017年6月8日 下午5:04:41 
	* @throws
	 */
	@RequestMapping("/updateState")
	public String updateState(Integer id){
		System.out.println(id);
		//根据ID查询一条记录
		Transfer t = transferServiceImpl.getTransferById(id);
		System.out.println(t);
		if (t != null) {
			System.out.println("shifougengxin");
			//是否更新成功
		 Boolean boo = transferServiceImpl.updateStateById(t);
		 System.out.println(boo);
			if (boo) {
				//跳转到已审核的页面
				return "redirect:/customerServiceController/toCustomerTransfer_personToPerson";
			}
		}
		//跳转到操作失败页面
		return "bank/fail";	
	}
	/**
	 * @author 西安工业大学蓝桥一期--毋泽航
	 * @Title: reviewTransferETP 
	 * @Description: 客服实现企业向个人转账的 后续功能
	 * @throws
	 */
	@RequestMapping("/reviewTransferETP")
	public String reviewTransferETP(Integer id,HttpServletRequest request){
		Transfer t = transferServiceImpl.getTransferById(id);
		if(t!=null){
			transferServiceImpl.achieveTransferState(t);
			CustomerCare customerCare = (CustomerCare) request.getSession().getAttribute("customer");
			customerCare.setServiceCount(customerCare.getServiceCount()+1);
			customerCareServiceImpl.updateCustomerCare(customerCare);
		}
		return "redirect:/customerServiceController/toCustomerTransfer_enterpriseToPerson";
	}
	/**
	 * @author 西安工业大学蓝桥一期--毋泽航
	 * @Title: reviewTransferETP 
	 * @Description: 客服实现企业向企业转账的 后续功能
	 * @throws
	 */
	@RequestMapping("/reviewTransferETE")
	public String reviewTransferETE(Integer id,HttpServletRequest request){
		Transfer t = transferServiceImpl.getTransferById(id);
		if(t!=null){
			transferServiceImpl.achieveTransferState(t);
			CustomerCare customerCare = (CustomerCare) request.getSession().getAttribute("customer");
			customerCare.setServiceCount(customerCare.getServiceCount()+1);
			customerCareServiceImpl.updateCustomerCare(customerCare);
		}
		return "redirect:/customerServiceController/toCustomerTransfer_enterpriseToEnterprise";
	}
	/**
	 * @author 西安工业大学蓝桥一期--毋泽航
	 * @Title: reviewTransferPTP 
	 * @Description: 客服实现个人向个人转账的 后续功能
	 * @throws
	 */
	@RequestMapping("/reviewTransferPTP")
	public String reviewTransferPTP(Integer id,HttpServletRequest request){
		Transfer t = transferServiceImpl.getTransferById(id);
		if(t!=null){
			transferServiceImpl.achieveTransferState(t);
			CustomerCare customerCare = (CustomerCare) request.getSession().getAttribute("customer");
			customerCare.setServiceCount(customerCare.getServiceCount()+1);
			customerCareServiceImpl.updateCustomerCare(customerCare);
		}
		return "redirect:/customerServiceController/toCustomerTransfer_personToPerson";
	}
	/**
	 * @author 西安工业大学蓝桥一期--毋泽航
	 * @Title: reviewTransferPTE 
	 * @Description: 客服实现个人向企业转账的 后续功能
	 * @throws
	 */
	@RequestMapping("/reviewTransferPTE")
	public String reviewTransferPTE(Integer id,HttpServletRequest request){
		Transfer t = transferServiceImpl.getTransferById(id);
		if(t!=null){
			transferServiceImpl.achieveTransferState(t);
			CustomerCare customerCare = (CustomerCare) request.getSession().getAttribute("customer");
			customerCare.setServiceCount(customerCare.getServiceCount()+1);
			customerCareServiceImpl.updateCustomerCare(customerCare);
		}
		return "redirect:/customerServiceController/toCustomerTransfer_personToEnterprise";
	}
	
}
