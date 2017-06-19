/**   
* @Title: Recharge_withdrawalMapper.java 
* @Package org.lanqiao.pay.mapper 
* @Description: TODO
* @author 西安工业大学蓝桥一期--王增   
* @date 2017年5月15日 下午7:33:51    
*/

package org.lanqiao.pay.mapper;

import java.util.List;

import org.lanqiao.pay.base.bean.TradePage;
import org.lanqiao.pay.base.bean.UserTradePage;
import org.lanqiao.pay.base.entity.Recharge_withdrawal;
import org.lanqiao.pay.base.entity.TimeBean;

/**
 * @author 王增
 *
 */
public interface Recharge_withdrawalMapper {

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getCountsByPage 
	* @Description: TODO
	* @param @param tradePage
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @date 2017年5月15日 下午7:50:02 
	* @throws 
	*/
	
	public Integer getCountsByPage(TradePage tradePage) ;

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getRecharge_withdrawalsByPage 
	* @Description: TODO	根据分页条件查询结果
	* @param @param tradePage
	* @param @return    设定文件 
	* @return List<Recharge_withdrawal>    返回类型 
	* @date 2017年5月16日 下午7:08:03 
	* @throws 
	*/
	
	public List<Recharge_withdrawal> getRecharge_withdrawalsByPage(TradePage tradePage);

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getUserTradeCount 
	* @Description: TODO  根据分页对象得到用户的充值或提现记录数
	* @param @param userTradePage
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @date 2017年5月24日 下午8:36:17 
	* @throws 
	*/
	
	public Integer getUserTradeCount(UserTradePage userTradePage);

	
	/**
	 * 
	* @author 西安工业大学蓝桥一期--王向宇
	* @Title: getEnterpriseRechargeAndWithdrawal 
	* @Description: TODO	通过企业id获取企业的充值和提现记录
	* @param @param enterpriseId
	* @param @return    设定文件 
	* @return List<Recharge_withdrawal>    返回类型 
	* @date 2017年5月25日 下午8:38:44 
	* @throws
	 */
	public List<Recharge_withdrawal> getEnterpriseRechargeAndWithdrawal(Integer enterpriseId);

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getRecharge_withdrawalsByUserTradePage 
	* @Description: TODO	根据用户的交易的分页对象得到该用户交易记录
	* @param @param userTradePage
	* @param @return    设定文件 
	* @return List<Recharge_withdrawal>    返回类型 
	* @date 2017年6月4日 下午2:55:27 
	* @throws 
	*/
	
	public List<Recharge_withdrawal> getRecharge_withdrawalsByUserTradePage(UserTradePage userTradePage);

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getEnterpriseUserTradeCount 
	* @Description: TODO	根据企业的交易条件得到记录数
	* @param @param userTradePage
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @date 2017年6月6日 下午2:07:11 
	* @throws 
	*/
	
	public Integer getEnterpriseUserTradeCount(UserTradePage userTradePage);

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getRecharge_withdrawalsForEnterpriseUserByUserTradePage 
	* @Description: TODO	得到企业用户的交易记录
	* @param @param userTradePage
	* @param @return    设定文件 
	* @return List<Recharge_withdrawal>    返回类型 
	* @date 2017年6月6日 下午2:49:36 
	* @throws 
	*/
	
	public List<Recharge_withdrawal> getRecharge_withdrawalsForEnterpriseUserByUserTradePage(
			UserTradePage userTradePage);
		/** 
	* @author 西安工业大学蓝桥一期--刘江
	* @Title: GetNumber 
	* @Description:获取企业交易量
	* @return Integer   
	* @date 2017年6月17日 下午11:33:07 
	* @throws 
	*/
	public Integer GetEntNumber(TimeBean tb);
		/** 
	* @author 西安工业大学蓝桥一期--刘江
	* @Title: GetUserNumber 
	* @Description: 获取用户交易量
	* @return Integer   
	* @date 2017年6月17日 下午11:34:35 
	* @throws 
	*/
	public Integer GetUserNumber(TimeBean tb);
	//总转账
	public Integer getReNumber();
	//总提现
	public Integer getDrNuber();
}
