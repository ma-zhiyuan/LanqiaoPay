package org.lanqiao.pay.mapper;

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

/**
 * @Title: BankMapper.java
 * @Package org.lanqiao.pay.mapper
 * @Description:银行类的mapper接口
 * @author 西安工业大学蓝桥一期--刘江
 * @date 2017年5月4日 下午8:05:36
 */
public interface BankMapper {

	public String getBank(Integer id);

	/**
	 * @author 西安工业大学蓝桥一期--毋泽航 @Description: Recharge_withdrawal分页list @date
	 * 2017年5月19日 下午7:07:55 @throws
	 */
	public List<Recharge_withdrawal> getRecharge_withdrawalList(Recharge_withdrawalCreteria rwc);

	/**
	 * @author 西安工业大学蓝桥一期--毋泽航 @Description: Recharge_withdrawal分页记录总数 @date
	 * 2017年5月19日 下午7:08:38 @throws
	 */
	public Integer getRecharge_withdrawalCount(Recharge_withdrawalCreteria rwc);

	/**
	 * 向数据库中插入一条Recharge_withdrawal记录
	 * 
	 * @author 孙航建
	 * @time 2017年5月7日 下午2:20:59
	 * @param rw
	 */
	void toWinthdrawMoney(Recharge_withdrawal rw);

	/**
	 * 获取所有的Recharge_withdrawal对象
	 * 
	 * @author 孙航建
	 * @time 2017年5月7日 下午2:21:10
	 * @return
	 */
	List<Recharge_withdrawal> getAllWithdrawal();

	/**
	 * 通过指定的id获取一条记录
	 * 
	 * @author 孙航建
	 * @time 2017年5月7日 下午8:44:47
	 * @param id
	 * @return
	 */
	Recharge_withdrawal getOneWithdrawal(Integer id);

	/**
	 * 更新Recharge_withdrawal的数据
	 * 
	 * @author 孙航建
	 * @time 2017年5月7日 下午9:11:04
	 * @param id
	 */
	void updateWithdrawalOne(Integer id);

	List<Bank> getPage(BankCreteria bankCreteria);

	int gettotal(String keyword);

	/**
	 * @author 西安工业大学蓝桥一期--王增 @Title: getBankByName @Description: TODO
	 * 根据银行名得到银行 @param @param bankName @param @return 设定文件 @return Bank
	 * 返回类型 @date 2017年5月7日 下午4:07:04 @throws
	 */

	Bank getBankByName(String bankName);

	/**
	 * @return @author 西安工业大学蓝桥一期--王增 @Title: addBank @Description:
	 * TODO @param @param bank 设定文件 @return void 返回类型 @date 2017年5月7日
	 * 下午4:15:37 @throws
	 */

	Integer addBank(Bank bank);

	Bank getOneBank(Integer id);

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--王向宇 @Title: getRechangeByCardNumberId @Description:
	 * TODO 通过银行卡id查询交易记录 @param @param id @param @return 设定文件 @return Integer
	 * 返回类型 @date 2017年5月14日 下午8:19:00 @throws
	 */
	int getRechangeByCardNumberId(int id);

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--王向宇 @Title: getTransferByCardNumberId @Description:
	 * TODO 通过银行卡id查询转账记录 @param @param id @param @return 设定文件 @return int
	 * 返回类型 @date 2017年5月14日 下午8:27:31 @throws
	 */
	int getTransferByCardNumberId(Integer id);

	// 企业提现时向数据库中插一条数据
	public void insertIntoEnterpriseWithdraw(Recharge_withdrawal rw);

	// 更新企业表进行实际的提现操作之后的状态
	public void updateEnterpriseWithdrawalState(Integer id);

	public List<Recharge_withdrawal> getAllEnterpriseWithdrawalTrue();

	public List<Recharge_withdrawal> getAllWithdrawalTrue();

	/**
	 * 企业分页
	 * 
	 * @author 孙航建
	 * @time 2017年5月20日 下午5:19:13
	 * @param rwc
	 * @return
	 */
	public List<Recharge_withdrawal> getAllEnterpriseWithdrawallist(Recharge_withdrawalCreteria rwc);

	/**
	 * @author 西安工业大学蓝桥一期--毋泽航 @Title:
	 * getEnterpriseRecharge_withdrawalCount @Description: 企业分页获取总数 @date
	 * 2017年5月21日 下午4:40:25 @throws
	 */
	public Integer getEnterpriseRecharge_withdrawalCount(Recharge_withdrawalCreteria rwc);

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--何璐 @Title: insert @Description: TODO
	 * 插入一条个人向企业转账记录 @param @param transfer 设定文件 @return void 返回类型 @date
	 * 2017年5月7日 下午3:08:46 @throws
	 */
	void insertTransferByUserToEp(Transfer transfer);

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--何璐 @Title: update @Description: TODO
	 * 更新一条个人向企业转账记录 @param @param transfer 设定文件 @return void 返回类型 @date
	 * 2017年5月7日 下午3:09:20 @throws
	 */
	void updateTransferByUserToEp(Integer id);

	// 将生成的利息插入到数据库中
	public void insertIntoPeritiesMon(CurrencyAmountRecord car);

	// 将企业提现生成的利息插入到数据库中
	public void insertIntoPeritiesEnterpriseMon(CurrencyAmountRecord car);

	public void addbank(Integer id, String name);

	public int getCardNumById(Integer id);

	public Integer deleteBank(Integer id);

	public int vertifyBankName(String name);

	public int updateBank(Integer id, String name);
	/**
	 * @return 
	 * 插入一条个人向个人转账的记录
	 * @author 西安工业大学蓝桥一期-代显峰
	 * @title userInsertToUser
	 * @description	TODO
	 * @param @param transfer
	 * @return void
	 * @date 2017年5月24日下午7:57:36
	 * @throws
	 */
	//个人使用支付宝余额向个人支付宝用户转账
	public void userInsertToUser(Transfer transfer);
	//个人使用银行卡向个人支付宝用户转账
	public void userBankInsertToUser(Transfer transfer);
	//个人使用支付宝余额向个人银行卡转账
	public void userInsertToUserBank(Transfer transfer);
	//个人使用银行卡余额向个人支付宝用户转账
	public void userBankInsertToUserBank(Transfer transfer);
	
   

}
