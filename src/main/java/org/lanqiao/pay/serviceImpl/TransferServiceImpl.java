/**   
* @Title: TransferServiceImpl.java 
* @Package org.lanqiao.pay.serviceImpl 
* @Description: TODO
* @author 西安工业大学蓝桥一期--王增   
* @date 2017年5月15日 下午7:30:29    
*/

package org.lanqiao.pay.serviceImpl;

import java.util.List;

import org.lanqiao.pay.base.bean.TradePage;
import org.lanqiao.pay.base.bean.TransferCreteria;
import org.lanqiao.pay.base.bean.UserTradePage;
import org.lanqiao.pay.base.dao.BankCardDao;
import org.lanqiao.pay.base.dao.EnterpriseUserDao;
import org.lanqiao.pay.base.dao.TransferDao;
import org.lanqiao.pay.base.dao.UserDao;
import org.lanqiao.pay.base.entity.BankCard;
import org.lanqiao.pay.base.entity.Enterprise;
import org.lanqiao.pay.base.entity.EnterpriseUser;
import org.lanqiao.pay.base.entity.Recharge_withdrawal;
import org.lanqiao.pay.base.entity.Transfer;
import org.lanqiao.pay.base.entity.User;
import org.lanqiao.pay.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 王增
 *
 */
@Service
public class TransferServiceImpl implements TransferService{
	@Autowired
	private TransferDao TransferDao;
	@Autowired
	BankCardDao bankCardDao;
	@Autowired UserDao userDao;
	@Autowired 
	EnterpriseUserDao enterpriseUserDao;
	
	/* (non-Javadoc)
	 * @see org.lanqiao.pay.service.TransferService#getCountsByPage(org.lanqiao.pay.base.bean.TradePage)
	 */
	@Override
	public Integer getCountsByPage(TradePage tradePage) {
		// TODO Auto-generated method stub
		return TransferDao.getCountsByPage(tradePage);
	}

	/* (non-Javadoc)
	 * @see org.lanqiao.pay.service.TransferService#getTransfersByPage(org.lanqiao.pay.base.bean.TradePage)
	 */
	@Override
	public List<Transfer> getTransfersByPage(TradePage tradePage) {
		// TODO Auto-generated method stub
		return TransferDao.getTransfersByPage(tradePage);
	}

	/**
	 * 生成企业余额宝向个人余额宝之间的转账
	 * @author 孙航建
	 * @time 2017年6月4日 上午9:52:26
	 * @param transfer
	 */
	public void insertIntoTransferFromEnterToUserByYue(Transfer transfer) {
		TransferDao.insertIntoTransferFromEnterToUserByYue(transfer);
	}
    /**
     * 将生成的企业余额向用户银行卡转账记录插入到数据库中
     * @author 孙航建
     * @time 2017年6月4日 下午12:53:11
     * @param transfer
     */
	public void insertIntoTransferFromEnterToUserByYhk(Transfer transfer) {
		TransferDao.insertIntoTransferFromEnterToUserByYhk(transfer);		
	}

	
	/**
	 * 
	* @author 西安工业大学蓝桥一期--王向宇
	* @Title: getEnterprisePay 
	* @Description: TODO	查询企业转出的记录
	* @param @param enterprise_id
	* @param @return    设定文件 
	* @return List<Transfer>    返回类型 
	* @date 2017年5月22日 下午8:17:24 
	* @throws
	 */
	public List<Transfer> getEnterprisePayItems(Integer enterpriseId){
		return TransferDao.getEnterprisePayItems(enterpriseId);
	}
	/**
	 * 
	* @author 西安工业大学蓝桥一期--何璐
	* @Title: getTransferByID 
	* @Description: TODO  通过id获取一条转账记录 
	* @param @param id
	* @param @return    设定文件 
	* @return Transfer    返回类型 
	* @date 2017年6月6日 下午8:36:57 
	* @throws
	 */
	public Transfer getTransferByID(Integer id){
		return TransferDao.getTransferById(id);
	}
	
	/* (non-Javadoc)
	 * @see org.lanqiao.pay.service.Recharge_withdrawalService#getUserTransferCount(org.lanqiao.pay.base.bean.UserTradePage)
	 */
	@Override
	public Integer getUserTransferCount(UserTradePage userTradePage) {
		// TODO Auto-generated method stub
		return TransferDao.getUserTransferCount(userTradePage);
	}

	/* (non-Javadoc)
	 * @see org.lanqiao.pay.service.TransferService#getTransfersByUserTradePage(org.lanqiao.pay.base.bean.UserTradePage)
	 */
	@Override
	public List<Transfer> getTransfersByUserTradePage(UserTradePage userTradePage) {
		// TODO Auto-generated method stub
		return TransferDao.getTransfersByUserTradePage(userTradePage);
	}

	/* (non-Javadoc)
	 * @see org.lanqiao.pay.service.TransferService#getEnterpriseUserTransferCount(org.lanqiao.pay.base.bean.UserTradePage)
	 */
	@Override
	public Integer getEnterpriseUserTransferCount(UserTradePage userTradePage) {
		// TODO Auto-generated method stub
		return TransferDao.getEnterpriseUserTransferCount(userTradePage);
	}

	/* (non-Javadoc)
	 * @see org.lanqiao.pay.service.TransferService#getTransfersForEnterpriseUserByUserTradePage(org.lanqiao.pay.base.bean.UserTradePage)
	 */
	@Override
	public List<Transfer> getTransfersForEnterpriseUserByUserTradePage(UserTradePage userTradePage) {
		// TODO Auto-generated method stub
		return TransferDao.getTransfersForEnterpriseUserByUserTradePage(userTradePage);
	}


	/**
	 * 
	* @author 西安工业大学蓝桥一期--董正健
	* @Title: getTransferById 
	* @Description: 根据ID查询一条记录
	* @param @param id
	* @param @return    设定文件 
	* @return Transfer    返回类型 
	* @date 2017年6月8日 上午8:48:04 
	* @throws
	 */
	public Transfer getTransferById(Integer id) {
		return TransferDao.getTransferById(id);
	}
	
	/**
	 * 
	* @author 西安工业大学蓝桥一期--董正健
	* @Title: updateStateById 
	* @Description: 把金额添加到转入账户，并修改转账状态
	* @param @param t
	* @param @return    设定文件 
	* @return String    返回类型 
	* @date 2017年6月8日 下午12:59:26 
	* @throws
	 */
	public Boolean updateStateById(Transfer t) {
		
		if (t.getToBankCard().equals(null)) {     //转入用户的余额宝
			//从数据库中获取用户余额
			Double userbalance = t.getToUser().getBalance();
			Double money = t.getMoney();
			//将用户ID和新的余额封装成一个对象
			User user = new User(t.getToUser().getId(), userbalance+money);
			//根据封装对象去数据库更新用户余额宝----调用代显峰的方法
			userDao.updateBalance(user);
		}else {    //转入用户的银行卡
			//从数据库中获取用户余额,将用户ID和新的余额封装成一个对象
			User user = new User(t.getToUser().getId(), t.getToBankCard().getBalance()+t.getMoney());
			userDao.updateBalance(user);
		}
		return true;
	}
	/**
	 * @author 西安工业大学蓝桥一期--毋泽航
	 * @Title: getTransferList 
	 * @Description: 分页显示转账记录 
	 * @throws
	 */
	public List<Transfer> getTransferList(TransferCreteria transferCreteria){
		return TransferDao.getHavaFoundTransferList(transferCreteria);
	}
	/**
	 * @author 西安工业大学蓝桥一期--毋泽航
	 * @Title: getTransferListCount 
	 * @Description: 分页的总数
	 * @throws
	 */
	public Integer getTransferListCount(TransferCreteria transferCreteria){
		return TransferDao.getHavaFoundTransferListCount(transferCreteria);
	}
	/**
	 * @author 西安工业大学蓝桥一期--毋泽航
	 * @Title: achieveTransferState 
	 * @Description: 客服实现转账的后续 
	 * @throws
	 */
	public void achieveTransferState(Transfer t) {
		BankCard toBankCard = t.getToBankCard();
		if(toBankCard!=null){
			//转到银行卡
			Double balance = toBankCard.getBalance()+t.getMoney();
			toBankCard.setBalance(balance);
			//修改卡上余额
			bankCardDao.updateBankCardBalance(toBankCard);
		}else{
			//转到余额
			User toUser = t.getToUser();
			if(toUser==null){
				//转给企业
				System.out.println(1);
				 EnterpriseUser	enterpriseUser =  enterpriseUserDao.getEnterpriseUserByEPID(t.getToEnterprise().getId());
				 enterpriseUser.setBalance(enterpriseUser.getBalance()+t.getMoney());
				 enterpriseUserDao.addBalance(enterpriseUser);
			}else{
				//转给个人
				toUser.setBalance(toUser.getBalance()+t.getMoney());
				userDao.updateBalance(toUser);
			}
		}
		//修改转账状态
		TransferDao.updateState(t.getId());
	}
	
}
