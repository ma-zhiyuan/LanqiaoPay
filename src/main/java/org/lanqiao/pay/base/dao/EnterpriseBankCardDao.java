/**   
* @Title: EnterpriseBankCardDao.java 
* @Package org.lanqiao.pay.base.dao 
* @Description: TODO
* @author 西安工业大学蓝桥一期--王增   
* @date 2017年5月2日 上午11:37:33    
*/

package org.lanqiao.pay.base.dao;

import java.util.List;

import org.lanqiao.pay.base.entity.BankCard;
import org.lanqiao.pay.base.entity.Recharge_withdrawal;
import org.lanqiao.pay.mapper.BankCardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author 王增
 *
 */
@Repository
public class EnterpriseBankCardDao {
	@Autowired
	BankCardMapper bankCardMapper;
	/**   
	* @Title: EnterpriseBankCardDao.java 
	* @Package org.lanqiao.pay.base.dao 
	* @Description: 用银行卡号查询银行卡余额 
	* @author 西安工业大学蓝桥一期--刘宣   
	* @date 2017年5月9日 下午9:50:07    
	*/
	
	public BankCard getBankCardById(String cardNumber) {
		
		return bankCardMapper.getBankCardById(cardNumber);
	}

	/**   
	* @Title: EnterpriseBankCardDao.java 
	* @Package org.lanqiao.pay.base.dao 
	* @Description: 银行卡充值 
	* @author 西安工业大学蓝桥一期--刘宣   
	* @date 2017年5月12日 下午7:17:32    
	*/
	
	public int reduceBankCardBalance(BankCard bankcard) {
		return bankCardMapper.reduceBankCardBalance(bankcard);
	}

	/**   
	* @Title: EnterpriseBankCardDao.java 
	* @Package org.lanqiao.pay.base.dao 
	* @Description: TODO(用一句话描述该文件做什么) 
	* @author 西安工业大学蓝桥一期--刘宣   
	* @date 2017年5月14日 下午2:28:05    
	*/
	
	public List<BankCard> getBankCardsByEnterpriseId(int id) {
		return bankCardMapper.getBankCardsByEnterprise_Id(id);
	}

	/**   
	* @Title: EnterpriseBankCardDao.java 
	* @Package org.lanqiao.pay.base.dao 
	* @Description: TODO(用一句话描述该文件做什么) 
	* @author 西安工业大学蓝桥一期--刘宣   
	* @date 2017年5月14日 下午4:34:01    
	*/
	
	public int addRecharge(Recharge_withdrawal recharge) {
		return bankCardMapper.addRecharge(recharge);
	}
}
