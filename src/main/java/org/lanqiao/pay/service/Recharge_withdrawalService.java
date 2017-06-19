/**   
* @Title: Recharge_withdrawalService.java 
* @Package org.lanqiao.pay.service 
* @Description: TODO
* @author 西安工业大学蓝桥一期--王增   
* @date 2017年5月15日 下午7:28:13    
*/

package org.lanqiao.pay.service;

import java.util.List;

import org.lanqiao.pay.base.bean.TradePage;
import org.lanqiao.pay.base.bean.UserTradePage;
import org.lanqiao.pay.base.entity.Recharge_withdrawal;

/**
 * @author 王增
 *
 */
public interface Recharge_withdrawalService {

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getCountsByPage 
	* @Description: TODO 根据分页条件进行查询提现或充值的记录条数;
	* @param @param tradePage
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @date 2017年5月15日 下午7:48:25 
	* @throws 
	*/
	
	Integer getCountsByPage(TradePage tradePage);

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getRecharge_withdrawalsByPage 
	* @Description: TODO
	* @param @param tradePage
	* @param @return    设定文件 
	* @return List<Recharge_withdrawal>    返回类型 
	* @date 2017年5月16日 下午7:06:52 
	* @throws 
	*/
	
	List<Recharge_withdrawal> getRecharge_withdrawalsByPage(TradePage tradePage);

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getUserTradeHist 
	* @Description: TODO 根据分页对象得到用户的充值或提现记录数
	* @param @param userTradePage
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @date 2017年5月24日 下午8:31:13 
	* @throws 
	*/
	
	Integer getUserTradeCount(UserTradePage userTradePage);

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getRecharge_withdrawalsByUserTradePage 
	* @Description: TODO	根据用户的交易的分页对象得到该用户交易记录
	* @param @param userTradePage
	* @param @return    设定文件 
	* @return List<Recharge_withdrawal>    返回类型 
	* @date 2017年6月4日 下午2:53:30 
	* @throws 
	*/
	
	List<Recharge_withdrawal> getRecharge_withdrawalsByUserTradePage(UserTradePage userTradePage);

}
