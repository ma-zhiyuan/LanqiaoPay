/**   
* @Title: TransferMapper.java 
* @Package org.lanqiao.pay.mapper 
* @Description: TODO
* @author 西安工业大学蓝桥一期--王增   
* @date 2017年5月15日 下午7:33:10    
*/
package org.lanqiao.pay.mapper;

import java.util.List;

import org.lanqiao.pay.base.bean.TradePage;
import org.lanqiao.pay.base.bean.TransferCreteria;
import org.lanqiao.pay.base.bean.UserTradePage;
import org.lanqiao.pay.base.entity.TimeBean;
import org.lanqiao.pay.base.entity.Transfer;
import org.lanqiao.pay.base.entity.User;

/**
 * @author 王增
 *
 */
public interface TransferMapper {

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getCountsByPage 
	* @Description: TODO
	* @param @param tradePage
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @date 2017年5月21日 下午1:35:32 
	* @throws 
	*/
	
	Integer getCountsByPage(TradePage tradePage);

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getTransfersByPage 
	* @Description: TODO 根据分页信息得到记录
	* @param @param tradePage
	* @param @return    设定文件 
	* @return List<Transfer>    返回类型 
	* @date 2017年5月21日 下午3:16:31 
	* @throws 
	*/
	
	List<Transfer> getTransfersByPage(TradePage tradePage);
    // 生成企业余额宝向个人余额宝之间的转账
	void insertIntoTransferFromEnterToUserByYue(Transfer transfer);
    // 将生成的企业余额向用户银行卡转账记录插入到数据库中
	void insertIntoTransferFromEnterToUserByYhk(Transfer transfer);

	
	/**
	 * 
	* @author 西安工业大学蓝桥一期--王向宇
	* @Title: getEnterprisePayItems 
	* @Description: TODO	获取企业转出的记录
	* @param @param enterpriseId
	* @param @return    设定文件 
	* @return List<Transfer>    返回类型 
	* @date 2017年5月22日 下午8:23:31 
	* @throws
	 */
	List<Transfer> getEnterprisePayItems(Integer enterpriseId);
	
	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getUserTransferCount 
	* @Description: TODO	得到用户的转账记录数
	* @param @param userTradePage
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @date 2017年6月4日 下午5:01:39 
	* @throws 
	*/
	
	public Integer getUserTransferCount(UserTradePage userTradePage);

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getTransfersByUserTradePage 
	* @Description: TODO	根据用户交易分页bean得到用户的转账记录;
	* @param @param userTradePage
	* @param @return    设定文件 
	* @return List<Transfer>    返回类型 
	* @date 2017年6月4日 下午5:30:58 
	* @throws 
	*/
	
	List<Transfer> getTransfersByUserTradePage(UserTradePage userTradePage);

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getEnterpriseUserTransferCount 
	* @Description: TODO	得到企业用户的转账记录数
	* @param @param userTradePage
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @date 2017年6月6日 下午3:11:54 
	* @throws 
	*/
	
	Integer getEnterpriseUserTransferCount(UserTradePage userTradePage);

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getTransfersForEnterpriseUserByUserTradePage 
	* @Description: TODO	 得到企业用户的转账记录
	* @param @param userTradePage
	* @param @return    设定文件 
	* @return List<Transfer>    返回类型 
	* @date 2017年6月6日 下午3:21:15 
	* @throws 
	*/
	
	List<Transfer> getTransfersForEnterpriseUserByUserTradePage(UserTradePage userTradePage);


	/**
	 * 
	* @author 西安工业大学蓝桥一期--董正健
	* @Title: getTransferById 
	* @Description: 根据ID查询一条记录
	* @param @return    设定文件 
	*  @param id 
	* @return Transfer    返回类型 
	* @date 2017年6月8日 上午8:37:46 
	* @throws
	 */
	Transfer getTransferById(Integer id);

	/**
	 * 
	* @author 西安工业大学蓝桥一期--董正健
	* @Title: updateState 
	* @Description: 去数据库更新状态
	* @param @param id    设定文件 
	* @return void    返回类型 
	* @date 2017年6月8日 下午4:20:48 
	* @throws
	 */
	void updateState(Integer id);
	
	List<Transfer> getHavaFoundTransferList(TransferCreteria transferCreteria);
	
	Integer getHavaFoundTransferListCount(TransferCreteria transferCreteria);
	public Integer getTranNumber(TimeBean tb);
	public Integer getTranNumberAll();
}
