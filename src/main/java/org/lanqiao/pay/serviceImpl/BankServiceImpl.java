package org.lanqiao.pay.serviceImpl;

import java.util.List;

import org.lanqiao.pay.base.bean.BankCreteria;
import org.lanqiao.pay.base.bean.Recharge_withdrawalCreteria;
import org.lanqiao.pay.base.dao.BankCardDao;
import org.lanqiao.pay.base.dao.BankDao;
import org.lanqiao.pay.base.dao.EnterpriseDao;
import org.lanqiao.pay.base.dao.EnterpriseUserDao;
import org.lanqiao.pay.base.dao.UserDao;
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
import org.lanqiao.pay.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BankServiceImpl implements BankService {
	@Autowired
	BankDao bankDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.lanqiao.pay.service.BankService#getBankByName(java.lang.String)
	 */
	@Override
	public Bank getBankByName(String bankName) {
		// TODO Auto-generated method stub
		return bankDao.getBankByName(bankName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.lanqiao.pay.service.BankService#addBank(org.lanqiao.pay.base.entity.
	 * Bank)
	 */
	@Override
	public Integer addBank(Bank bank) {
		// TODO Auto-generated method stub
		return bankDao.addBank(bank);
	}

	@Autowired
	UserDao userDao;
	@Autowired
	BankCardDao bankCardDao;
	@Autowired
	EnterpriseDao enterpriseDao;

	/**
	 * 向数据库中插入一条Recharge_withdrawal记录
	 * 
	 * @author 孙航建
	 * @time 2017年5月7日 下午8:41:12
	 * @param rw
	 */
	public void toWinthdrawMoney(Recharge_withdrawal rw) {
		bankDao.toWinthdrawMoney(rw);
	}

	/**
	 * @author 西安工业大学蓝桥一期--毋泽航 @Description: 获取
	 *         Recharge_withdrawal分页的记录list @date 2017年5月19日 下午7:41:17 @throws
	 */
	public List<Recharge_withdrawal> getRecharge_withdrawalList(Recharge_withdrawalCreteria rwc) {
		return bankDao.getRecharge_withdrawalList(rwc);
	}

	/**
	 * @author 西安工业大学蓝桥一期--毋泽航 @Description: 获取 Recharge_withdrawal分页的记录总数 @date
	 *         2017年5月19日 下午7:25:40 @throws
	 */
	public Integer getRecharge_withdrawalCount(Recharge_withdrawalCreteria rwc) {
		return bankDao.getRecharge_withdrawalCount(rwc);
	}

	/**
	 * 获取所有的Recharge_withdrawal对象
	 * 
	 * @author 孙航建
	 * @time 2017年5月7日 下午8:41:49
	 * @return
	 */
	public List<Recharge_withdrawal> getAllWithdrawal() {
		return bankDao.getAllWithdrawal();
	}

	/**
	 * 通过指定的id获取一条记录
	 * 
	 * @author 孙航建
	 * @time 2017年5月7日 下午8:42:42
	 * @param id
	 * @return
	 */
	public Recharge_withdrawal getOneWithdrawal(Integer id) {
		return bankDao.getOneWithdrawal(id);
	}

	/**
	 * 用获取的 Recharge_withdrawal去数据路中更新money
	 * 
	 * @author 孙航建
	 * @time 2017年5月7日 下午9:04:30
	 * @param re
	 * @return
	 */
	public String updateUserAndBankCardMoney(Recharge_withdrawal re) {
		// 更新Recharge_withdrawal的数据
		bankDao.updateWithdrawalOne(re.getId());
		// 通过bankCardId获取当前的利率
		BankCard bankCard = bankCardDao.getBanKParities(re.getBankCard().getId());
		// 通过reId获取转账的金额，再次计算其利息，计算转入银行卡的金额
		Double paritiesMoney = re.getMoney() % 100 == 0
				? (int) (re.getMoney() / 100) * bankCard.getBankId().getParities()
				: (int) (re.getMoney() / 100) * bankCard.getBankId().getParities() + bankCard.getBankId().getParities();
		// 获取银行卡的钱数
		double bankBalance = re.getBankCard().getBalance();
		bankBalance = bankBalance + re.getMoney() - paritiesMoney;
		// 为银行卡设置新的钱数
		re.getBankCard().setBalance(bankBalance);
		BankCard bc = new BankCard(re.getBankCard().getId(), bankBalance);
		// 去数据库更新银行卡的余额
		bankCardDao.updateBankCardByIdAndBalance(bc);
		return "成功";
	}

	public List<Bank> getPage(BankCreteria bc) {
		// TODO Auto-generated method stub
		return bankDao.getPage(bc);
	}

	public int gettotal(String keyword) {
		// TODO Auto-generated method stub
		return bankDao.gettotal(keyword);
	}

	@Autowired
	EnterpriseUserDao enterpriseUserDao;

	/**
	 * 企业提现金额更新并且实现事物管理
	 * 
	 * @author 孙航建
	 * @time 2017年5月19日 下午5:38:59
	 * @param re
	 * @return
	 */
	public String updateEnterpriseUserAndBankCardMoney(Recharge_withdrawal re) {
		// 更新Recharge_withdrawal中的状态
		bankDao.updateEnterpriseWithdrawalOne(re.getId());
		// 通过bankCardId获取当前的利率
		BankCard bankCard = bankCardDao.getBanKParities(re.getBankCard().getId());
		// 通过reId获取转账的金额，再次计算其利息，计算转入银行卡的金额
		Double paritiesMoney = re.getMoney() % 100 == 0
				? (int) (re.getMoney() / 100) * bankCard.getBankId().getParities()
				: (int) (re.getMoney() / 100) * bankCard.getBankId().getParities() + bankCard.getBankId().getParities();
		// 获取银行卡的钱数
		double bBalance = re.getBankCard().getBalance();
		bBalance = bBalance + re.getMoney() - paritiesMoney;
		// 为银行卡设置新的设置新的金额
		re.getBankCard().setBalance(bBalance);
		BankCard bc = new BankCard(re.getBankCard().getId(), bBalance);
		// 去数据库更新银行卡的余额
		bankCardDao.updateBankCardByIdAndBalance(bc);
		return "成功";
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--何璐 @Title: isPayPassword @Description: TODO
	 *         判断支付密码是否正确 @param @param id @param @param
	 *         payPassword @param @return 设定文件 @return boolean 返回类型 @date
	 *         2017年5月7日 下午5:04:39 @throws
	 */
	public boolean isPayPassword(Integer id, String payPassword) {
		// 通过id获取用户
		User user = userDao.getUser(id);
		// 通过用户获取密保
		Secret secret = user.getSecretId();
		// 通过密保获取支付密码
		String payPassword1 = secret.getPayPassword();
		// 对客户端传过来的支付密码加密
		String payPassword2 = MyUtils.md5(payPassword);
		// 判断支付密码是否一致
		if (payPassword2.equals(payPassword1)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--何璐 @Title: isBankCardPwd @Description: TODO
	 *         判断银行卡密码是否正确 @param @param bankNumber @param @param
	 *         payPassword @param @return 设定文件 @return boolean 返回类型 @date
	 *         2017年5月19日 下午5:35:06 @throws
	 */
	public boolean isBankCardPwd(String bankNumber, String payPassword) {
		// 通过银行卡号获取银行卡
		BankCard bankCard = bankCardDao.getBankCardByCardNumber(bankNumber);
		// 通过银行卡获取银行卡密码
		String bankCardPassWord = bankCard.getBankCardPassWord();
		// 对客户端传过来的支付密码加密
		String payPassword2 = MyUtils.md5(payPassword);
		if (payPassword2.equals(bankCardPassWord)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--何璐 @Title: insertTransfer @Description: TODO
	 *         向数据库中插入一条个人向企业转账的记录 @param @param transfer @param @return
	 *         设定文件 @return void 返回类型 @date 2017年5月19日 下午5:58:57 @throws
	 */
	public void insertTransfer(Transfer transfer) {
		bankDao.insert(transfer);
	}
	/**
	 * 
	* @author 西安工业大学蓝桥一期--何璐
	* @Title: updateTransfer 
	* @Description: TODO  客服审核转账操作将转账状态改为已审核 
	* @param @param id    设定文件 
	* @return void    返回类型 
	* @date 2017年6月6日 下午4:51:41 
	* @throws
	 */
	public void updateTransfer(Integer id){
		bankDao.update(id);
	}
	/**
	 * 
	* @author 西安工业大学蓝桥一期--何璐
	* @Title: updateEnterPriseUserBalance 
	* @Description: TODO  客服端实现转账操作，更改转入方的余额 
	* @param     设定文件 
	* @return void    返回类型 
	* @date 2017年6月6日 下午4:54:28 
	* @throws
	 */
	public void updateEnterPriseUserBalance(Transfer transfer){
		//转入方收到的钱
		Double money = transfer.getMoney();
		//转入方
		EnterpriseUser eUser = enterpriseUserDao.getEnterpriseByenterpriseId(transfer.getToEnterprise().getId());
		//转入方的余额
		Double balance = eUser.getBalance();
		//更改转入方的余额
		eUser.setBalance(money+balance);
		//去数据库更新企业用户的余额
		enterpriseUserDao.update(eUser);
	}
	/**
	 * 
	* @author 西安工业大学蓝桥一期--何璐
	* @Title: updateEnterPriseBankCardBalance 
	* @Description: TODO  客服端实现转账操作，更改转入方银行卡的余额
	* @param     设定文件 
	* @return void    返回类型 
	* @date 2017年6月6日 下午4:59:42 
	* @throws
	 */
	public void updateEnterPriseBankCardBalance(Transfer transfer){
		//转入方收到的钱数
		Double money = transfer.getMoney();
		//转入方的银行卡余额
		Double balance = transfer.getToBankCard().getBalance();
		BankCard toBankCard = transfer.getToBankCard();
		//更改转入方银行卡的余额
		toBankCard.setBalance(money+balance);
		//去数据库更新转入方银行卡的余额
		bankCardDao.updateBankCardByIdAndBalance(toBankCard);
		
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--王向宇 @Title: getRechangeByCardNumberId @Description:
	 *         TODO 通过银行卡id查询交易记录 @param @param id 设定文件 @return void 返回类型 @date
	 *         2017年5月14日 下午8:16:00 @throws
	 */
	public int getRechangeByCardNumberId(int id) {
		return bankDao.getRechangeByCardNumberId(id);
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--王向宇 @Title: getTransferByCardNumberId @Description:
	 *         TODO 通过银行卡id查询转账记录 @param @param id @param @return 设定文件 @return
	 *         int 返回类型 @date 2017年5月14日 下午8:25:51 @throws
	 */
	public int getTransferByCardNumberId(Integer id) {
		return bankDao.getTransferByCardNumberId(id);
	}

	/**
	 * 企业提现去数据库中插入一条数据
	 * 
	 * @author 孙航建
	 * @time 2017年5月16日 下午4:44:05
	 * @param rw
	 */
	public void insertIntoEnterpriseWithdraw(Recharge_withdrawal rw) {
		bankDao.insertIntoEnterpriseWithdraw(rw);
	}

	public List<Recharge_withdrawal> getAllEnterpriseWithdrawalTrue() {
		return bankDao.getAllEnterpriseWithdrawalTrue();
	}

	public List<Recharge_withdrawal> getAllWithdrawalTrue() {
		return bankDao.getAllWithdrawalTrue();
	}

	public List<Recharge_withdrawal> getAllEnterpriseWithdrawallist(Recharge_withdrawalCreteria rwc) {
		return bankDao.getAllEnterpriseWithdrawallist(rwc);
	}

	public Integer getEnterpriseRecharge_withdrawalCount(Recharge_withdrawalCreteria rwc) {
		return bankDao.getEnterpriseRecharge_withdrawalCount(rwc);
	}

	public void addbank(Integer id, String name) {
		// TODO Auto-generated method stub
		bankDao.addbank(id,name);
	}

	public int getCardNumById(Integer id) {
		// TODO Auto-generated method stub
		return bankDao.getCardNumById(id);
	}

	public Integer deleteBank(Integer id) {
		// TODO Auto-generated method stub
		return bankDao.deleteBank(id);
	}

	public int vertifyBankName(String name) {
		// TODO Auto-generated method stub
		return bankDao.vertifyBankName(name);
	}

	public int updateBank(Integer id, String name) {
		// TODO Auto-generated method stub
		return bankDao.updateBank(id,name);
	}

	/**
	 * 将个人提现生成的利息插入到数据库中
	 * 
	 * @author 孙航建
	 * @time 2017年5月22日 下午8:44:06
	 * @param car
	 */
	public void insertIntoPeritiesMon(CurrencyAmountRecord car) {
		bankDao.insertIntoPeritiesMon(car);
	}

	/**
	 * 将企业提现生成的利息插入到数据库中
	 * 
	 * @author 孙航建
	 * @time 2017年5月23日 下午10:07:38
	 * @param car
	 */
	public void insertIntoPeritiesEnterpriseMon(CurrencyAmountRecord car) {
		bankDao.insertIntoPeritiesEnterpriseMon(car);
	}
	/**
	 * @return 
	 * 插入一条个人向个人转账的记录
	 * @author 西安工业大学蓝桥一期-代显峰
	 * @title userInsertTransferToUser
	 * @description	TODO
	 * @param @param t
	 * @return void
	 * @date 2017年5月24日下午7:34:46
	 * @throws
	 */
	//个人使用余额向个人支付宝转账
	@Transactional
	public void userInsertTransferToUser(Transfer t) {
		bankDao.userInsertToUser(t);
		User u = userDao.getUser(t.getFromUser().getId());
		Double  balance = u.getBalance()-t.getMoney()*(1+0.1);
		u.setBalance(balance);
		userDao.updateBalance(u);
	}
	//个人使用银行卡向个人支付宝转账
	@Transactional
	public void userBankInsertTransferToUser(Transfer t) {
		bankDao.userBankInsertToUser(t);
		Bank b = bankDao.getBank(t.getFromBankCard().getBankId().getId());
		Double parities = b.getParities();
		Double  balance = t.getFromBankCard().getBalance()-t.getMoney()*(1+parities);
		BankCard bc = t.getFromBankCard();
		bankCardDao.updateBalance(bc);
	}
	//个人使用银行卡向个人银行卡转账
	@Transactional
	public void userBankInsertTransferToUserBank(Transfer t) {
		bankDao.userBankInsertToUserBank(t);
		Bank b = bankDao.getBank(t.getToBankCard().getBankId().getId());
		Double parities = b.getParities();
		System.out.println(parities);
		Double  balance = t.getFromBankCard().getBalance()-t.getMoney()*(1+parities);
		System.out.println("oooo");
		System.out.println(balance);
		BankCard bc = t.getFromBankCard();
		bc.setBalance(balance);
		bankCardDao.updateBalance(bc);
	}
	//个人使用余额向个人银行卡转账
	@Transactional
	public void userInsertTransferToUserBank(Transfer t) {
		bankDao.userInsertToUserBank(t);
		User u = userDao.getUser(t.getFromUser().getId());
		Bank b = bankDao.getBank(t.getToBankCard().getBankId().getId());
		Double parities = b.getParities();
		Double  balance = u.getBalance()-t.getMoney()*(1+parities);
		u.setBalance(balance);
		userDao.updateBalance(u);
		BankServiceImpl bsi = new BankServiceImpl();
	}
}
