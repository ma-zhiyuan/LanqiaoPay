package org.lanqiao.pay.base.dao;

import org.lanqiao.pay.base.bean.UserCreteria;
import org.lanqiao.pay.base.entity.Bank;
import org.lanqiao.pay.base.entity.BankCard;
import org.lanqiao.pay.base.entity.CurrencyAmountRecord;

import java.util.List;

import org.lanqiao.pay.base.bean.BankCreteria;
import org.lanqiao.pay.base.bean.BankPage;
import org.lanqiao.pay.base.bean.Recharge_withdrawalCreteria;
import org.lanqiao.pay.base.entity.Bank;
import org.lanqiao.pay.base.entity.Recharge_withdrawal;
import org.lanqiao.pay.base.entity.Transfer;
import org.lanqiao.pay.mapper.BankMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BankDao {
	@Autowired
	BankMapper bankMapper;
	 /**
     * 
    * @author 西安工业大学蓝桥一期--何璐
    * @Title: insert 
    * @Description: TODO  插入一条个人向企业转账记录 
    * @param @param transfer    设定文件 
    * @return void    返回类型 
    * @date 2017年5月7日 下午3:13:25 
    * @throws
     */
	public void insert(Transfer transfer){
		bankMapper.insertTransferByUserToEp(transfer);
	}
	/**
	 * 
	* @author 西安工业大学蓝桥一期--何璐
	* @Title: update 
	* @Description: TODO  更新一条个人向企业转账记录 
	* @param @param transfer    设定文件 
	* @return void    返回类型 
	* @date 2017年5月7日 下午3:13:58 
	* @throws
	 */
	public void update(Integer id){
		bankMapper.updateTransferByUserToEp(id);
	}


	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getBankByName 
	* @Description: TODO 根据银行名得到银行
	* @param @param bankName
	* @param @return    设定文件 
	* @return Bank    返回类型 
	* @date 2017年5月7日 下午4:06:32 
	* @throws 
	*/
	
	public Bank getBankByName(String bankName) {
		// TODO Auto-generated method stub
		return bankMapper.getBankByName(bankName);
	}

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: addBank 
	* @Description: TODO 插入银行
	* @param @param bank    设定文件 
	* @return void    返回类型 
	* @date 2017年5月7日 下午4:14:27 
	* @throws 
	*/
	
	public Integer addBank(Bank bank) {
		// TODO Auto-generated method stub
		return bankMapper.addBank(bank);
	}

	/**
	 * 向数据库中插入一条Recharge_withdrawal记录
	 * @author 孙航建
	 * @time 2017年5月7日 下午8:43:50
	 * @param rw
	 */
	public void toWinthdrawMoney(Recharge_withdrawal rw) {
		bankMapper.toWinthdrawMoney(rw);
	}
	
	/**
	 * @author 西安工业大学蓝桥一期--毋泽航
	 * @Description: 获取 Recharge_withdrawal分页记录列表
	 * @date 2017年5月19日 下午7:11:48 
	 * @throws
	 */
	public List<Recharge_withdrawal> getRecharge_withdrawalList(Recharge_withdrawalCreteria rwc){
		return bankMapper.getRecharge_withdrawalList(rwc);
	}
	/**
	 * @author 西安工业大学蓝桥一期--毋泽航
	 * @Description: 获取 Recharge_withdrawal分页记录总数 
	 * @date 2017年5月19日 下午7:13:23 
	 * @throws
	 */
	public Integer getRecharge_withdrawalCount(Recharge_withdrawalCreteria rwc){
		return bankMapper.getRecharge_withdrawalCount(rwc);
	}
	
	/**
	 * 获取所有的Recharge_withdrawal对象
	 * @author 孙航建
	 * @time 2017年5月7日 下午8:43:43
	 * @return
	 */
	public List<Recharge_withdrawal> getAllWithdrawal() {
		return bankMapper.getAllWithdrawal();
	}
    /**
     * 通过指定的id获取一条记录
     * @author 孙航建
     * @time 2017年5月7日 下午8:43:32
     * @param id
     * @return
     */
	public Recharge_withdrawal getOneWithdrawal(Integer id) {
		return bankMapper.getOneWithdrawal(id);
	}
	
	/**
	 * 更新Recharge_withdrawal的数据
	 * @author 孙航建
	 * @time 2017年5月7日 下午9:10:08
	 * @param id
	 */
	public void updateWithdrawalOne(Integer id) {
		bankMapper.updateWithdrawalOne(id);
	}
	public List<Bank> getPage(BankCreteria bc) {
		// TODO Auto-generated method stub
		return bankMapper.getPage(bc);
	}
	public int gettotal(String keyword) {
		// TODO Auto-generated method stub
		return bankMapper.gettotal(keyword);
	}
	

	/**
	 * 
	* @author 西安工业大学蓝桥一期--王向宇
	* @Title: getRechangeByCardNumberId 
	* @Description: TODO	通过银行卡id查询交易记录
	* @param @param id
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @date 2017年5月14日 下午8:18:18 
	* @throws
	 */
	public int getRechangeByCardNumberId(int id) {
		return bankMapper.getRechangeByCardNumberId(id);
	}

	/**
	 * 
	* @author 西安工业大学蓝桥一期--王向宇
	* @Title: getTransferByCardNumberId 
	* @Description: TODO	通过银行卡id查询转账记录
	* @param @param id
	* @param @return    设定文件 
	* @return int    返回类型 
	* @date 2017年5月14日 下午8:26:45 
	* @throws
	 */
	public int getTransferByCardNumberId(Integer id) {
		return bankMapper.getTransferByCardNumberId(id);
	}

	/**
	 * 企业提现去数据库中插入一条数据
	 * @author 孙航建
	 * @time 2017年5月16日 下午4:46:19
	 * @param rw
	 */
	public void insertIntoEnterpriseWithdraw(Recharge_withdrawal rw) {
       bankMapper.insertIntoEnterpriseWithdraw(rw);		
	}


	/**
	 * 更新企业表进行实际的提现操作之后的状态
	 * @author 孙航建
	 * @time 2017年5月19日 下午5:52:17
	 * @param id
	 */
	public void updateEnterpriseWithdrawalOne(Integer id) {
		bankMapper.updateEnterpriseWithdrawalState(id);
	}

	public List<Recharge_withdrawal> getAllEnterpriseWithdrawalTrue() {
		return bankMapper.getAllEnterpriseWithdrawalTrue();
	}

	public List<Recharge_withdrawal> getAllWithdrawalTrue() {
		return bankMapper.getAllWithdrawalTrue();
	}

	public List<Recharge_withdrawal> getAllEnterpriseWithdrawallist(Recharge_withdrawalCreteria rwc) {
		return bankMapper.getAllEnterpriseWithdrawallist(rwc);
	}

	public Integer getEnterpriseRecharge_withdrawalCount(Recharge_withdrawalCreteria rwc) {
		return bankMapper.getEnterpriseRecharge_withdrawalCount(rwc);
	}

	public void addbank(Integer id, String name) {
		// TODO Auto-generated method stub
		bankMapper.addbank(id,name);
	}
	public int getCardNumById(Integer id) {
		// TODO Auto-generated method stub
		return bankMapper.getCardNumById(id);
	}
	public Integer deleteBank(Integer id) {
		// TODO Auto-generated method stub
		return bankMapper.deleteBank(id);
	}
	public int vertifyBankName(String name) {
		// TODO Auto-generated method stub
		return bankMapper.vertifyBankName(name);
	}
	public int updateBank(Integer id, String name) {
		// TODO Auto-generated method stub
		return bankMapper.updateBank(id,name);
	}
    /**
     * 将生成的利息插入到数据库中
     * @author 孙航建
     * @time 2017年5月23日 下午12:56:20
     * @param car
     */
	public void insertIntoPeritiesMon(CurrencyAmountRecord car) {
		bankMapper.insertIntoPeritiesMon(car);
	}
    /**
     * 将企业提现生成的利息插入到数据库中
     * @author 孙航建
     * @time 2017年5月23日 下午10:08:04
     * @param car
     */
	public void insertIntoPeritiesEnterpriseMon(CurrencyAmountRecord car) {
		bankMapper.insertIntoPeritiesEnterpriseMon(car);
	}
	/**
	 * 插入一条个人向个人转账的记录
	 * @author 西安工业大学蓝桥一期-代显峰
	 * @title usernInsertToUser
	 * @description	TODO
	 * @param @param transfer
	 * @return void
	 * @date 2017年5月24日下午7:29:38
	 * @throws
	 */
	////个人使用向个人支付宝用户转账
	public void userInsertToUser(Transfer t){
		bankMapper.userInsertToUser(t);
	}
	//个人使用银行卡向个人支付宝用户转账
	public void userBankInsertToUser(Transfer t) {
		 bankMapper.userBankInsertToUser(t);
	}
	//个人使用银行卡向个人银行卡转账
	public void userBankInsertToUserBank(Transfer t) {
		bankMapper.userBankInsertToUserBank(t);
	}
	//个人使用支付宝余额向个人银行卡转账
	public void userInsertToUserBank(Transfer t) {
		bankMapper.userInsertToUserBank(t);
		
	}
	public Bank getBank(Integer id) {
		System.out.println("123654789");
		return bankMapper.getOneBank(id);
	}
	public void insertIntoPeritiesUserMon(CurrencyAmountRecord car) {
		 bankMapper.insertIntoPeritiesMon(car);
	}
	
}
