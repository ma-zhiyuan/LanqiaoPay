/**   
* @Title: Recharge_withdrawalServiceImpl.java 
* @Package org.lanqiao.pay.serviceImpl 
* @Description: TODO
* @author 西安工业大学蓝桥一期--王增   
* @date 2017年5月15日 下午7:28:37    
*/

package org.lanqiao.pay.serviceImpl;

import java.util.List;

import org.lanqiao.pay.base.bean.TradePage;
import org.lanqiao.pay.base.bean.UserTradePage;
import org.lanqiao.pay.base.dao.Recharge_withdrawalDao;
import org.lanqiao.pay.base.entity.Recharge_withdrawal;
import org.lanqiao.pay.service.Recharge_withdrawalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 王增
 *
 */
@Service
public class Recharge_withdrawalServiceImpl implements Recharge_withdrawalService{
	@Autowired
	Recharge_withdrawalDao recharge_withdrawalDao;

	/* (non-Javadoc)
	 * @see org.lanqiao.pay.service.Recharge_withdrawalService#getCountsByPage(org.lanqiao.pay.base.bean.TradePage)
	 */
	@Override
	public Integer getCountsByPage(TradePage tradePage) {
		// TODO Auto-generated method stub
		return recharge_withdrawalDao.getCountsByPage(tradePage);
	}

	/* (non-Javadoc)
	 * @see org.lanqiao.pay.service.Recharge_withdrawalService#getRecharge_withdrawalsByPage(org.lanqiao.pay.base.bean.TradePage)
	 */
	@Override
	public List<Recharge_withdrawal> getRecharge_withdrawalsByPage(TradePage tradePage) {
		// TODO Auto-generated method stub
		return recharge_withdrawalDao.getRecharge_withdrawalsByPage(tradePage);
	}

	/**
	 * 
	* @author 西安工业大学蓝桥一期--王向宇
	* @Title: getEnterpriseRechargeAndWithdrawal 
	* @Description: TODO	通过企业id获取企业的充值和提现
	* @param @param id
	* @param @return    设定文件 
	* @return List<Recharge_withdrawal>    返回类型 
	* @date 2017年5月25日 下午8:33:03 
	* @throws
	 */
	public List<Recharge_withdrawal> getEnterpriseRechargeAndWithdrawal(Integer enterpriseId) {
		return recharge_withdrawalDao.getEnterpriseRechargeAndWithdrawal(enterpriseId);
	}

	/* (non-Javadoc)
	 * @see org.lanqiao.pay.service.Recharge_withdrawalService#getUserTradeCount(org.lanqiao.pay.base.bean.UserTradePage)
	 */
	@Override
	public Integer getUserTradeCount(UserTradePage userTradePage) {
		// TODO Auto-generated method stub
		return recharge_withdrawalDao.getUserTradeCount(userTradePage);
	}

	/* (non-Javadoc)
	 * @see org.lanqiao.pay.service.Recharge_withdrawalService#getRecharge_withdrawalsByUserTradePage(org.lanqiao.pay.base.bean.UserTradePage)
	 */
	@Override
	public List<Recharge_withdrawal> getRecharge_withdrawalsByUserTradePage(UserTradePage userTradePage) {
		// TODO Auto-generated method stub
		return recharge_withdrawalDao.getRecharge_withdrawalsByUserTradePage(userTradePage);
	}

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getEnterpriseUserTradeCount 
	* @Description: TODO 	根据企业的交易条件得到记录数
	* @param @param userTradePage
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @date 2017年6月6日 下午1:59:54 
	* @throws 
	*/
	
	public Integer getEnterpriseUserTradeCount(UserTradePage userTradePage) {
		// TODO Auto-generated method stub
		return recharge_withdrawalDao.getEnterpriseUserTradeCount(userTradePage);
	}

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getRecharge_withdrawalsForEnterpriseUserByUserTradePage 
	* @Description: TODO	得到企业用户的交易记录
	* @param @param userTradePage
	* @param @return    设定文件 
	* @return List<Recharge_withdrawal>    返回类型 
	* @date 2017年6月6日 下午2:47:48 
	* @throws 
	*/
	
	public List<Recharge_withdrawal> getRecharge_withdrawalsForEnterpriseUserByUserTradePage(
			UserTradePage userTradePage) {
		// TODO Auto-generated method stub
		return recharge_withdrawalDao.getRecharge_withdrawalsForEnterpriseUserByUserTradePage(userTradePage);
	}

}
