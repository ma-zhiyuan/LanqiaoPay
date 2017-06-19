/**   
* @Title: TransferServiceDao.java 
* @Package org.lanqiao.pay.base.dao 
* @Description: TODO
* @author 西安工业大学蓝桥一期--王增   
* @date 2017年5月15日 下午7:32:09    
*/

package org.lanqiao.pay.base.dao;

import java.util.List;import javax.sound.midi.Transmitter;

import org.lanqiao.pay.base.bean.TradePage;
import org.lanqiao.pay.base.bean.TransferCreteria;
import org.lanqiao.pay.base.bean.UserTradePage;
import org.lanqiao.pay.base.entity.TimeBean;
import org.lanqiao.pay.base.entity.Transfer;
import org.lanqiao.pay.base.entity.User;
import org.lanqiao.pay.mapper.TransferMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author 王增
 *
 */
@Repository
public class TransferDao {
	@Autowired
	TransferMapper transferMapper;

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getCountsByPage 
	* @Description: TODO 根据tradePage得到记录数
	* @param @param tradePage
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @date 2017年5月21日 下午1:33:59 
	* @throws 
	*/
	
	public Integer getCountsByPage(TradePage tradePage) {
		// TODO Auto-generated method stub
		return transferMapper.getCountsByPage(tradePage);
	}

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getTransfersByPage 
	* @Description: TODO	根据分页信息得到记录
	* @param @param tradePage
	* @param @return    设定文件 
	* @return List<Transfer>    返回类型 
	* @date 2017年5月21日 下午3:16:01 
	* @throws 
	*/
	
	public List<Transfer> getTransfersByPage(TradePage tradePage) {
		// TODO Auto-generated method stub
		return transferMapper.getTransfersByPage(tradePage);
	}
	
    /**
     * 生成企业余额宝向个人余额宝之间的转账
     * @author 孙航建
     * @time 2017年6月4日 上午10:02:13
     * @param transfer
     */
	public void insertIntoTransferFromEnterToUserByYue(Transfer transfer) {
		transferMapper.insertIntoTransferFromEnterToUserByYue(transfer);
	}
    /**
     * 将生成的企业余额向用户银行卡转账记录插入到数据库中
     * @author 孙航建
     * @time 2017年6月4日 下午12:53:48
     * @param transfer
     */
	public void insertIntoTransferFromEnterToUserByYhk(Transfer transfer) {
		transferMapper.insertIntoTransferFromEnterToUserByYhk(transfer);		
	}

	
	/**
	 * 
	* @author 西安工业大学蓝桥一期--王向宇
	* @Title: getEnterprisePayItems 
	* @Description: TODO	获取企业转出的记录
	* @param @param enterpriseId
	* @param @return    设定文件 
	* @return List<Transfer>    返回类型 
	* @date 2017年5月22日 下午8:21:57 
	* @throws
	 */
	public List<Transfer> getEnterprisePayItems(Integer enterpriseId) {
		return transferMapper.getEnterprisePayItems(enterpriseId);
	}
	
	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getUserTransferCount 
	* @Description: TODO	得到用户的转账记录数
	* @param @param userTradePage
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @date 2017年6月4日 下午5:01:00 
	* @throws 
	*/
	
	public Integer getUserTransferCount(UserTradePage userTradePage) {
		// TODO Auto-generated method stub
		return transferMapper.getUserTransferCount(userTradePage);
	}

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getTransfersByUserTradePage 
	* @Description: TODO	根据用户交易分页bean得到用户的转账记录;
	* @param @param userTradePage
	* @param @return    设定文件 
	* @return List<Transfer>    返回类型 
	* @date 2017年6月4日 下午5:30:00 
	* @throws 
	*/
	
	public List<Transfer> getTransfersByUserTradePage(UserTradePage userTradePage) {
		// TODO Auto-generated method stub
		return transferMapper.getTransfersByUserTradePage(userTradePage);
	}

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getEnterpriseUserTransferCount 
	* @Description: TODO	得到企业用户的转账记录数
	* @param @param userTradePage
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @date 2017年6月6日 下午3:10:58 
	* @throws 
	*/
	
	public Integer getEnterpriseUserTransferCount(UserTradePage userTradePage) {
		// TODO Auto-generated method stub
		return transferMapper.getEnterpriseUserTransferCount(userTradePage);
	}

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getTransfersForEnterpriseUserByUserTradePage 
	* @Description: TODO	得到企业用户的转账记录
	* @param @param userTradePage
	* @param @return    设定文件 
	* @return List<Transfer>    返回类型 
	* @date 2017年6月6日 下午3:20:38 
	* @throws 
	*/
	
	public List<Transfer> getTransfersForEnterpriseUserByUserTradePage(UserTradePage userTradePage) {
		// TODO Auto-generated method stub
		return transferMapper.getTransfersForEnterpriseUserByUserTradePage(userTradePage);
	}


	/**
	 * @param id 
	 * 
	* @author 西安工业大学蓝桥一期--董正健
	* @Title: getTransferById 
	* @Description: 根据ID查询一条记录 
	* @param @return    设定文件 
	* @return Transfer    返回类型 
	* @date 2017年6月8日 上午8:37:08 
	* @throws
	 */
	public Transfer getTransferById(Integer id) {
		return transferMapper.getTransferById(id);
	}

	/**
	 * 
	* @author 西安工业大学蓝桥一期--董正健
	* @Title: updateState 
	* @Description: 去数据库更新状态
	* @param @param id    设定文件 
	* @return void    返回类型 
	* @date 2017年6月8日 下午4:16:56 
	* @throws
	 */
	public void updateState(Integer id) {
		transferMapper.updateState(id);
	}
	
	public List<Transfer> getHavaFoundTransferList(TransferCreteria transferCreteria){
		return transferMapper.getHavaFoundTransferList(transferCreteria);
	}
	public Integer getHavaFoundTransferListCount(TransferCreteria transferCreteria){
		return transferMapper.getHavaFoundTransferListCount(transferCreteria);
	}


	public Integer getTranNumber(TimeBean tb){
		return transferMapper.getTranNumber(tb);
		
	};
	public Integer getTranNumberAll(){
		return transferMapper.getTranNumberAll();
	};
}
