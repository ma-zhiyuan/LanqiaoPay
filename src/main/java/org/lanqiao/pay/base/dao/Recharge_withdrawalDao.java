/**   
* @Title: Recharge_withdrawalDao.java 
* @Package org.lanqiao.pay.base.dao 
* @Description: TODO
* @author 西安工业大学蓝桥一期--王增   
* @date 2017年5月15日 下午7:34:22    
*/

package org.lanqiao.pay.base.dao;

import java.util.List;

import org.lanqiao.pay.base.bean.TradePage;
import org.lanqiao.pay.base.bean.UserTradePage;
import org.lanqiao.pay.base.entity.Recharge_withdrawal;
import org.lanqiao.pay.base.entity.TimeBean;
import org.lanqiao.pay.mapper.Recharge_withdrawalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author 王增
 *
 */
@Repository
public class Recharge_withdrawalDao {
	@Autowired
	Recharge_withdrawalMapper recharge_withdrawalMapper;

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getCountsByPage 
	* @Description: TODO 根据分页条件进行查询提现或充值的记录条数;
	* @param @param tradePage
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @date 2017年5月15日 下午7:49:35 
	* @throws 
	*/
	
	public Integer getCountsByPage(TradePage tradePage) {
		// TODO Auto-generated method stub
		return recharge_withdrawalMapper.getCountsByPage(tradePage);
	}

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getRecharge_withdrawalsByPage 
	* @Description: TODO	根据分页条件查询结果
	* @param @param tradePage
	* @param @return    设定文件 
	* @return List<Recharge_withdrawal>    返回类型 
	* @date 2017年5月16日 下午7:07:27 
	* @throws 
	*/
	
	public List<Recharge_withdrawal> getRecharge_withdrawalsByPage(TradePage tradePage) {
		// TODO Auto-generated method stub
		return recharge_withdrawalMapper.getRecharge_withdrawalsByPage(tradePage);
	}

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getUserTradeCount 
	* @Description: TODO 根据分页对象得到用户的充值或提现记录数
	* @param @param userTradePage
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @date 2017年5月24日 下午8:35:49 
	* @throws 
	*/
	
	public Integer getUserTradeCount(UserTradePage userTradePage) {
		// TODO Auto-generated method stub
		return recharge_withdrawalMapper.getUserTradeCount(userTradePage);
	}

	
	/**
	 * 
	* @author 西安工业大学蓝桥一期--王向宇
	* @Title: getEnterpriseRechargeAndWithdrawal 
	* @Description: TODO	通过企业id获取企业的充值和提现记录
	* @param @param enterpriseId
	* @param @return    设定文件 
	* @return List<Recharge_withdrawal>    返回类型 
	* @date 2017年5月25日 下午8:38:04 
	* @throws
	 */
	public List<Recharge_withdrawal> getEnterpriseRechargeAndWithdrawal(Integer enterpriseId) {
		return recharge_withdrawalMapper.getEnterpriseRechargeAndWithdrawal(enterpriseId);
	}

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getRecharge_withdrawalsByUserTradePage 
	* @Description: TODO	根据用户的交易的分页对象得到该用户交易记录
	* @param @param userTradePage
	* @param @return    设定文件 
	* @return List<Recharge_withdrawal>    返回类型 
	* @date 2017年6月4日 下午2:55:03 
	* @throws 
	*/
	
	public List<Recharge_withdrawal> getRecharge_withdrawalsByUserTradePage(UserTradePage userTradePage) {
		// TODO Auto-generated method stub
		return recharge_withdrawalMapper.getRecharge_withdrawalsByUserTradePage(userTradePage);
	}

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getEnterpriseUserTradeCount 
	* @Description: TODO	根据企业的交易条件得到记录数
	* @param @param userTradePage
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @date 2017年6月6日 下午2:06:47 
	* @throws 
	*/
	
	public Integer getEnterpriseUserTradeCount(UserTradePage userTradePage) {
		// TODO Auto-generated method stub
		return recharge_withdrawalMapper.getEnterpriseUserTradeCount(userTradePage);
	}

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getRecharge_withdrawalsForEnterpriseUserByUserTradePage 
	* @Description: TODO	得到企业用户的交易记录
	* @param @param userTradePage
	* @param @return    设定文件 
	* @return List<Recharge_withdrawal>    返回类型 
	* @date 2017年6月6日 下午2:49:08 
	* @throws 
	*/
	
	public List<Recharge_withdrawal> getRecharge_withdrawalsForEnterpriseUserByUserTradePage(
			UserTradePage userTradePage) {
		// TODO Auto-generated method stub
		return recharge_withdrawalMapper.getRecharge_withdrawalsForEnterpriseUserByUserTradePage(userTradePage);
	}
	/** 
	* @author 西安工业大学蓝桥一期--刘江
	* @Title: GetNumber 
	* @Description:获取企业交易量
	* @return Integer   
	* @date 2017年6月17日 下午11:33:07 
	* @throws 
	*/
	public Integer GetEntNumber(TimeBean tb){
		return recharge_withdrawalMapper.GetEntNumber(tb);
	};
		/** 
	* @author 西安工业大学蓝桥一期--刘江
	* @Title: GetUserNumber 
	* @Description: 获取用户交易量
	* @return Integer   
	* @date 2017年6月17日 下午11:34:35 
	* @throws 
	*/
	public Integer GetUserNumber(TimeBean tb){
		return recharge_withdrawalMapper.GetUserNumber(tb);
	};
	public Integer getReNumber(){
		return recharge_withdrawalMapper.getReNumber();
		
	};
	//总提现
	public Integer getDrNuber(){
		return recharge_withdrawalMapper.getDrNuber();
		
	};
}
