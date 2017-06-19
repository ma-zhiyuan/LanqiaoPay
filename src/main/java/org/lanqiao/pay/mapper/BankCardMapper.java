package org.lanqiao.pay.mapper;

import java.util.List;

import org.lanqiao.pay.base.entity.Bank;
import org.lanqiao.pay.base.entity.BankCard;
import org.lanqiao.pay.base.entity.Recharge_withdrawal;
import org.lanqiao.pay.base.entity.Recharge_withdrawal;

/**
 * @Title: BankCardMapper.java
 * @Package org.lanqiao.pay.mapper
 * @Description:
 * @author 西安工业大学蓝桥一期--刘江
 * @date 2017年5月5日 下午7:02:30
 */
public interface BankCardMapper {

	// 级联查询
	public List<BankCard> getBankName(Integer id);

	// 插入一条充值记录
	public int chongZhiJiLu(Recharge_withdrawal czjl);

	// 银行卡减钱
	public int chongZhiout(BankCard b);

	// 通过卡号获取银行卡
	public BankCard getCardByNumber(String number);

	// 用户添加银行卡
	public void userAddBankCard(BankCard bkd);

	// 企业添加银行卡
	public void enterAddBankCard(BankCard bkd);

	// 删除银行卡
	public void removeBankCard(int bkdid);

	// 修改银行卡
	public void updateBankCard(BankCard bkd);

	// 用户查询银行卡
	public List<BankCard> userGetBankCards(int userId);

	// 用户查询一张默认卡
	public BankCard userGetBankCard(int userId);

	// 企业查询银行卡
	public List<BankCard> enterGetBankCards(int enterId);

	// 修改是否为默认卡
	public void modifyIsDefault(int cardid);

	// 移除默认卡
	public void removeIsDefault(int userid);
	public void EntremoveIsDefault(int userid);

	// 开启快捷支付
	public void modifyIsQuickPay(int cardid);

	// 关闭快捷支付
	public void shutQuickPay(int cardid);

	/**
	 * @author 西安工业大学蓝桥一期--王增 @Title: getBankCardByCardNumber @Description: TODO
	 *         根据银行卡卡号得到银行卡 @param @param cardNumber @param @return 设定文件 @return
	 *         BankCard 返回类型 @date 2017年5月7日 上午11:23:47 @throws
	 */

	public BankCard getBankCardByCardNumber(String cardNumber);

	/**
	 * 通过id获取对应的BankCard
	 * 
	 * @author 孙航建
	 * @time 2017年5月7日 下午8:58:32
	 * @param bkd
	 * @return
	 */
	public BankCard getBankcard(BankCard bkd);

	/**
	 * 去数据库更新银行卡的余额
	 * 
	 * @author 孙航建
	 * @time 2017年5月7日 下午10:32:06
	 * @param bankBalance
	 * @param id
	 */
	public void updateBankCardByIdAndBalance(BankCard bc);

	// 通过userId获取银行卡信息
	public BankCard getBankByUserId(String kh);

	// 通过userId获取所有的bankCard信息
	public List<BankCard> getAllBankCard(Integer id);

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--王向宇 @Title: getBankCardsByEnterpriseId @Description:
	 * TODO 通过企业id获取一系列银行卡 @param @param enterpriseId @param @return
	 * 设定文件 @return List<BankCard> 返回类型 @date 2017年5月14日 上午9:41:52 @throws
	 */
	public List<BankCard> getBankCardsByEnterpriseId(Integer enterpriseId);

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--王向宇 @Title: getBankCardsByUserId @Description: TODO
	 * 通过UserId获取一系列银行卡 @param @param userId @param @return 设定文件 @return
	 * List<BankCard> 返回类型 @date 2017年5月14日 下午2:27:11 @throws
	 */
	public List<BankCard> getBankCardsByUserId(Integer userId);

	/**
	 * 通过enterpriseId获取所有的企业的银行卡信息
	 * 
	 * @author 孙航建
	 * @time 2017年5月15日 下午11:57:24
	 * @param id
	 * @return
	 */
	public List<BankCard> getAllBankCardByEnterPriseId(Integer id);

	// 通过bankCardId获取当前的利率
	public BankCard getBanKParities(Integer id);
	


	/**   
	* @Title: EnterpriseBankCardMapper.java 
	* @Package org.lanqiao.pay.mapper 
	* @Description: 通过银行卡ID查询银行卡 
	* @author 西安工业大学蓝桥一期--刘宣   
	* @date 2017年5月9日 下午9:53:17    
	*/
	
	BankCard getBankCardById(String cardNumber);

	/**   
	* @Title: EnterpriseBankCardMapper.java 
	* @Package org.lanqiao.pay.mapper 
	* @Description: 银行卡充值 
	* @author 西安工业大学蓝桥一期--刘宣   
	* @date 2017年5月12日 下午7:18:20    
	*/
	
	int reduceBankCardBalance(BankCard bankcard);

	/**   
	* @Title: EnterpriseBankCardMapper.java 
	* @Package org.lanqiao.pay.mapper 
	* @Description: 通过企业ID查询企业所有银行卡 
	* @author 西安工业大学蓝桥一期--刘宣   
	* @date 2017年5月14日 下午2:28:53    
	*/
	
	List<BankCard> getBankCardsByEnterprise_Id(int id);

	/**   
	* @Title: BankCardMapper.java 
	* @Package org.lanqiao.pay.mapper 
	* @Description: TODO(用一句话描述该文件做什么) 
	* @author 西安工业大学蓝桥一期--刘宣   
	* @date 2017年5月14日 下午4:34:49    
	*/
	
	public int addRecharge(Recharge_withdrawal recharge);
	
	/**
	 * @author 西安工业大学蓝桥一期--毋泽航
	 * @Title: updateBankCardBalance 
	 * @Description: 修改银行卡的金额 
	 * @throws
	 */
	public void updateBankCardBalance(BankCard toBankCard);

}
