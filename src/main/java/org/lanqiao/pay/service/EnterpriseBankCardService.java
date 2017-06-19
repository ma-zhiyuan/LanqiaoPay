/**   
* @Title: EnterpriseBankCardService.java 
* @Package org.lanqiao.pay.service 
* @Description: TODO
* @authonr 西安工业大学蓝桥一期--王增   
* @date 2017年5月2日 上午11:35:26    
*/

package org.lanqiao.pay.service;

import java.util.List;

import org.lanqiao.pay.base.entity.BankCard;
import org.lanqiao.pay.base.entity.Recharge_withdrawal;

/**
 * @author 王增
 *
 */
public interface EnterpriseBankCardService {
	/**
	* @Title: EnterpriseBankCardService.java 
	* @Package org.lanqiao.pay.service 
	* @Description: 用银行卡号查询银行卡余额 
	* @author 西安工业大学蓝桥一期--刘宣   
	* @date 2017年5月9日 下午9:46:51
	 */
	BankCard getBankCardById(String cardNumber);

	/**   
	* @Title: EnterpriseBankCardService.java 
	* @Package org.lanqiao.pay.service 
	* @Description: 银行卡充值 
	* @author 西安工业大学蓝桥一期--刘宣   
	* @date 2017年5月12日 下午7:13:47    
	*/
	
	int reduceBankCardBalance(BankCard bankCard);

	/**   
	* @Title: EnterpriseBankCardService.java 
	* @Package org.lanqiao.pay.service 
	* @Description: TODO(用一句话描述该文件做什么) 
	* @author 西安工业大学蓝桥一期--刘宣   
	* @date 2017年5月14日 下午2:19:59    
	*/
	
	List<BankCard> getBankCardByEnterpriseId(int id);

	/**   
	* @Title: EnterpriseBankCardService.java 
	* @Package org.lanqiao.pay.service 
	* @Description: TODO(用一句话描述该文件做什么) 
	* @author 西安工业大学蓝桥一期--刘宣   
	* @date 2017年5月14日 下午4:32:41    
	*/
	
	int addRecharge(Recharge_withdrawal recharge);
}
