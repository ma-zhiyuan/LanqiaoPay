/**   
* @Title: TransferService.java 
* @Package org.lanqiao.pay.service 
* @Description: TODO
* @authonr 西安工业大学蓝桥一期--王增   
* @date 2017年5月15日 下午7:27:47    
*/

package org.lanqiao.pay.service;

import java.util.List;

import org.lanqiao.pay.base.bean.TradePage;
import org.lanqiao.pay.base.bean.UserTradePage;
import org.lanqiao.pay.base.entity.Recharge_withdrawal;
import org.lanqiao.pay.base.entity.Transfer;

/**
 * @author 王增
 *
 */
public interface TransferService {

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getCountsByPage 
	* @Description: TODO 根据tradePage得到记录数
	* @param @param tradePage
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @date 2017年5月21日 下午1:32:21 
	* @throws 
	*/
	
	Integer getCountsByPage(TradePage tradePage);

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getTransfersByPage 
	* @Description: TODO	根据分页信息得到记录
	* @param @param tradePage
	* @param @return    设定文件 
	* @return List<Transfer>    返回类型 
	* @date 2017年5月21日 下午3:14:56 
	* @throws 
	*/
	List<Transfer> getTransfersByPage(TradePage tradePage);
	
    
	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getUserTransferCount 
	* @Description: TODO	根据用户的交易分页条件得到其转账记录
	* @param @param userTradePage
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @date 2017年6月4日 下午4:59:46 
	* @throws 
	*/
	
	Integer getUserTransferCount(UserTradePage userTradePage);

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getTransfersByUserTradePage 
	* @Description: TODO	根据用户交易分页bean得到用户的转账记录;
	* @param @param userTradePage
	* @param @return    设定文件 
	* @return List<Transfer>    返回类型 
	* @date 2017年6月4日 下午5:28:49 
	* @throws 
	*/
	
	List<Transfer> getTransfersByUserTradePage(UserTradePage userTradePage);

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getEnterpriseUserTransferCount 
	* @Description: TODO	得到企业的转账记录数
	* @param @param userTradePage
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @date 2017年6月6日 下午3:08:11 
	* @throws 
	*/
	
	Integer getEnterpriseUserTransferCount(UserTradePage userTradePage);

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getTransfersForEnterpriseUserByUserTradePage 
	* @Description: TODO	得到企业用户的转账记录
	* @param @param userTradePage
	* @param @return    设定文件 
	* @return List<Transfer>    返回类型 
	* @date 2017年6月6日 下午3:19:59 
	* @throws 
	*/
	
	List<Transfer> getTransfersForEnterpriseUserByUserTradePage(UserTradePage userTradePage);

}
