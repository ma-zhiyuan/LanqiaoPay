/**   
* @Title: EnterpriseBankCardServiceImpl.java 
* @Package org.lanqiao.pay.serviceImpl 
* @Description: TODO
* @author 西安工业大学蓝桥一期--王增   
* @date 2017年5月2日 上午11:35:56    
*/

package org.lanqiao.pay.serviceImpl;

import java.util.Date;
import java.util.List;

import org.lanqiao.pay.base.dao.EnterpriseBankCardDao;
import org.lanqiao.pay.base.entity.BankCard;
import org.lanqiao.pay.base.entity.EnterpriseUser;
import org.lanqiao.pay.base.entity.Recharge_withdrawal;
import org.lanqiao.pay.service.EnterpriseBankCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 王增
 *
 */
@Service
public class EnterpriseBankCardServiceImpl implements EnterpriseBankCardService{
	
	@Autowired
	EnterpriseBankCardDao enterpriseBankCardDao;
	@Autowired
	EnterpriseUserServiceImpl enterpriseUserServiceImpl;
	//用银行卡号查询银行卡
	public BankCard getBankCardById(String cardNumber) {
		return enterpriseBankCardDao.getBankCardById(cardNumber);
	}
	/**
	* @Title: EnterpriseBankCardServiceImpl.java 
	* @Package org.lanqiao.pay.serviceImpl 
	* @Description: 银行卡充值 
	* @author 西安工业大学蓝桥一期--刘宣   
	* @date 2017年5月12日 下午7:15:10
	 */
	public int reduceBankCardBalance(BankCard bankcard){
		return enterpriseBankCardDao.reduceBankCardBalance(bankcard);
	}
	//通过企业ID查询企业所有的银行卡
	@Override
	public List<BankCard> getBankCardByEnterpriseId(int id) {
		return enterpriseBankCardDao.getBankCardsByEnterpriseId(id);
	}
	//添加充值记录
	@Override
	public int addRecharge(Recharge_withdrawal recharge) {
		return enterpriseBankCardDao.addRecharge(recharge);
	}
	/**
	* @Title: EnterpriseUserController.java 
	* @Package org.lanqiao.pay.controller 
	* @Description: 企业充值 
	* @author 西安工业大学蓝桥一期--刘宣   
	* @date 2017年5月14日 下午2:14:50
	 */
	@Transactional
	public boolean addEnterpriseUserBalance(EnterpriseUser user,Double balance,BankCard bankCard){
		//增加用户余额
		EnterpriseUser u = new EnterpriseUser(user.getId(),(user.getBalance()+balance));
		int a = enterpriseUserServiceImpl.addBalance(u);
		//减少银行卡余额
		BankCard card = new BankCard(bankCard.getCardNumber(),(bankCard.getBalance()-balance));
		card.setBalance(bankCard.getBalance()-balance);
		int b = this.reduceBankCardBalance(card);
		//插入一条充值记录
		Recharge_withdrawal recharge = new Recharge_withdrawal(user.getEnterprise(), bankCard,balance, new Date(), 3, 5,"帐户余额-充值","企业充值");
		int c =this.addRecharge(recharge);
		boolean boo;
		if(a==1&&b==1&&c==1){
			boo=true;
		}else {
			boo=false;
		}
		return boo;
	}
}
