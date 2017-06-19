/**   
* @Title: BankService.java 
* @Package org.lanqiao.pay.service 
* @Description: TODO
* @author 西安工业大学蓝桥一期--王增   
* @date 2017年5月7日 下午4:11:27    
*/

package org.lanqiao.pay.service;

import org.lanqiao.pay.base.entity.Bank;

/**
 * @author 王增
 *
 */
public interface BankService {
	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getBankByName 
	* @Description: TODO 根据银行名得到银行
	* @param @param bankName
	* @param @return    设定文件 
	* @return Bank    返回类型 
	* @date 2017年5月7日 下午4:07:04 
	* @throws 
	*/
	
	Bank getBankByName(String bankName);
	public Integer addBank(Bank bank);
}
