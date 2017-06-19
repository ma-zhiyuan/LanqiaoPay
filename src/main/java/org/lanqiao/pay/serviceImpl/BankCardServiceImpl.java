package org.lanqiao.pay.serviceImpl;

import java.util.Date;
import java.util.List;

import org.lanqiao.pay.base.dao.BankCardDao;
import org.lanqiao.pay.base.dao.UserDao;
import org.lanqiao.pay.base.entity.BankCard;
import org.lanqiao.pay.base.entity.EnterpriseUser;
import org.lanqiao.pay.base.entity.Recharge_withdrawal;
import org.lanqiao.pay.base.entity.User;
import org.lanqiao.pay.base.util.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BankCardServiceImpl {
	@Autowired
	BankCardDao bankCardDao;
	@Autowired
	UserDao userDao;
	@Autowired
	EnterpriseServiceImpl enterpriseServiceImpl;
	@Autowired
	UserServiceImpl userServiceImple;
	@Autowired
	EnterpriseUserServiceImpl enterpriseUserServiceImpl;
	@Autowired
	BankServiceImpl bankServiceImpl;
	
		
		/**
			 * 通过userId获取所有的bankCard信息
			 * @author 孙航建
			 * @time 2017年5月12日 下午3:04:05
			 * @param id
			 * @return
			 */
			public List<BankCard> getAllBankCard(Integer id) {
				return bankCardDao.getAllBankCard(id);
			}
			
			/**
			 * 
			* @author 西安工业大学蓝桥一期--王向宇
			* @Title: getBankCardsByUserName 
			* @Description: TODO  通过企业昵称来查询一系列银行卡
			* @param @param name
			* @param @return    设定文件 
			* @return List<Bank>    返回类型 
			* @date 2017年5月14日 上午9:08:00 
		* @throws
			 */
			public List<BankCard> getBankCardsByEnterpriseName(String name){
				Integer enterpriseId=enterpriseServiceImpl.getIdByName(name);
				if(enterpriseId!=null){
					List<BankCard> cards=bankCardDao.getBankCardsByEnterpriseId(enterpriseId);
					return cards;
				}
				return null;
			}
			
			/**
			 * 
			* @author 西安工业大学蓝桥一期--王向宇
			* @Title: getBankCardsByEnterpriseEmail 
			* @Description: TODO	通过企业邮箱来获取企业的银行卡
			* @param @param email
			* @param @return    设定文件 
			* @return List<BankCard>    返回类型 
			* @date 2017年5月14日 下午4:36:32 
			* @throws
			 */
			public List<BankCard> getBankCardsByEnterpriseEmail(String email){
				Integer enterpriseId=enterpriseUserServiceImpl.getEnterpriseIdByEmail(email);
				if(enterpriseId!=null){
					List<BankCard> cards=bankCardDao.getBankCardsByEnterpriseId(enterpriseId);
					return cards;
				}
				return null;
			}
			
			/**
			 * 
			* @author 西安工业大学蓝桥一期--王向宇
			* @Title: getBankCardsByUserName 
			* @Description: TODO	通过用户昵称来查询一系列银行卡
			* @param @param name
			* @param @return    设定文件 
			* @return List<BankCard>    返回类型 
			* @date 2017年5月14日 下午2:19:51 
			* @throws
			 */
			public List<BankCard> getBankCardsByUserName(String name){
				Integer userId=userServiceImple.getUserIdByName(name);
				if(userId!=null){
					List<BankCard> cards=bankCardDao.getBankCardsByUserId(userId);
					return cards;
				}
				return null;
			}
			
			/**
			 * 
			* @author 西安工业大学蓝桥一期--王向宇
			* @Title: getBankCardsByUserEmail 
			* @Description: TODO  通过邮箱获取用户的银行卡
			* @param @param email
			* @param @return    设定文件 
			* @return List<BankCard>    返回类型 
			* @date 2017年5月14日 下午4:08:24 
			* @throws
			 */
			public List<BankCard> getBankCardsByUserEmail(String email){
				Integer userId=userServiceImple.getUserIdByEmail(email);
				if(userId!=null){
					List<BankCard> cards=bankCardDao.getBankCardsByUserId(userId);
					return cards;
				}
				return null;
			}
		 
			/**
			 * 
			* @author 西安工业大学蓝桥一期--王向宇
			* @Title: getTradingRecord 
			* @Description: TODO	通过银行卡号获取总交易记录数量
			* @param @param cardNumber
			* @param @return    设定文件 
			* @return Integer    返回类型 
			* @date 2017年5月14日 下午8:08:21 
			* @throws
			 */
			public int getTradingRecord(String cardNumber) {
				BankCard bc=bankCardDao.getBankCardByCardNumber(cardNumber);
				if(bc!=null){
					int rechangeNum=bankServiceImpl.getRechangeByCardNumberId(bc.getId());
					int transferNum=bankServiceImpl.getTransferByCardNumberId(bc.getId());
					int num=rechangeNum+transferNum;
					return num;
				}
				return 0;
			}
		
			/**
			 * 通过enterpriseId获取所有的企业的银行卡信息
			 * @author 孙航建
			 * @time 2017年5月15日 下午11:51:22
			 * @param id
			 * @return
			 */
			public List<BankCard> getAllBankCardByEnterPriseId(Integer id) {
				return bankCardDao.getAllBankCardByEnterPriseId(id);
			}
			
		
		/**
		* @author 西安工业大学蓝桥一期--李明@Title: getBankname @Description: 利用级联查询获取bankcard的所有信息 
		* @return List<BankCard>   @date 2017年5月14日 下午4:46:38  @throws
		 */
		public List<BankCard> getBankname(Integer id){
			return bankCardDao.getBankName(id);
		}
		/**
		* @author 西安工业大学蓝桥一期--李明@Title: chongZhiJiLu  @Description: 插入一条充值记录
		* @return int   @date 2017年5月14日 下午4:34:33  @throws
		 */
		public int chongZhiJiLu(Recharge_withdrawal czjl){
			return bankCardDao.chongZhiJiLu(czjl);
		}
		
		/**
		* @author 西安工业大学蓝桥一期--李明@Title: chongZhi  @Description:实现事务 
		* @return void   @date 2017年5月14日 下午4:29:53  @throws
		 */
		@Transactional
		public int chongZhi(User user,BankCard bankCard,Double dmoney){
			User u=new User(user.getId(), user.getBalance()+dmoney);
			BankCard b=new BankCard(bankCard.getId(), bankCard.getUserId(), bankCard.getBalance()-dmoney, bankCard.getCardNumber());
			int in=userDao.chongZhiin(u);
			int out=bankCardDao.chongZhiout(b);
			//充值成功后往数据库中插入一条记录
			Recharge_withdrawal czjl = new Recharge_withdrawal(user, null, bankCard, dmoney, new Date(), 0, 2, "充值","个人充值");			
			int jiLu = bankCardDao.chongZhiJiLu(czjl);			
			//3者的返回值皆为1时才算充值成功
			if(in==out&&in==jiLu){				
				return 1;
			}else{
				return 0;
			}
		}
		/**
		 * 
		* @author 西安工业大学蓝桥一期--李明 @Title: getCardByNumber  @Description: 通过银行卡号获取银行卡 
		* @return BankCard   @date 2017年5月14日 下午4:27:01  @throws
		 */
		public BankCard getCardByNumber(String number){
			return bankCardDao.getCardByNumber(number);
		}
	/**
	 * @author 西安工业大学蓝桥一期--刘江 @Title: addBankCard @Description: 用户添加银行卡 @return
	 *         void @date 2017年5月5日 下午7:24:28 @throws
	 */
	public void userAddBankCard(BankCard bkd,User u) {
		bkd.setUserId(u);
		bkd.setIsDefault(0);
		bkd.setIsQuickPay(0);
		bkd.setApplicationTime(new Date());
		bankCardDao.userAddBankCard(bkd);
		u.getListBankCard().add(bkd);
		u.setListBankCard(new MyUtils().cardNumberEncryption(u.getListBankCard()));
	};

	/**
	 * @author 西安工业大学蓝桥一期--刘江 @Title: removeBankCard @Description: 企业移除一张卡 @return
	 *         void @date 2017年5月5日 下午7:25:54 @throws
	 */
	public void EntremoveBankCard(int bkdid, EnterpriseUser  u) {
		// 移除卡
		bankCardDao.removeBankCard(bkdid);
		// 获取企业的卡集合
		List<BankCard> list = u.getEnterprise().getBankcards();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getId()==bkdid){
				list.remove(i);
			}
		}

	};
	public void removeBankCard(int bkdid, User u) {
		// 移除卡
		bankCardDao.removeBankCard(bkdid);
		// 获取用户的卡集合
		List<BankCard> list = u.getListBankCard();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getId()==bkdid){
				list.remove(i);
			}
		}

	};
	/**
	 * @author 西安工业大学蓝桥一期--刘江 @Title: updateBankCard @Description: 修改卡 @return
	 *         void @date 2017年5月5日 下午7:42:48 @throws
	 */
	public void updateBankCard(BankCard bkd) {
		bankCardDao.updateBankCard(bkd);

	};

	/**
	 * @author 西安工业大学蓝桥一期--刘江 @Title: getBankCards @Description: 用户查询卡 @return
	 *         List<BankCard> @date 2017年5月5日 下午7:43:02 @throws
	 */
	public List<BankCard> userGetBankCards(int userId) {
		return bankCardDao.userGetBankCards(userId);

	};

	/**
	 * @author 西安工业大学蓝桥一期--刘江 @Title: getBankCards @Description:
	 *         用户查询一张默认卡 @return List<BankCard> @date 2017年5月5日
	 *         下午7:43:02 @throws
	 */
	public BankCard userGetBankCard(int userId) {
		return bankCardDao.userGetBankCard(userId);

	};



	/**
	 * @author 西安工业大学蓝桥一期--刘江 @Title: enterAddBankCard @Description:
	 *         企业添加银行卡 @return void @date 2017年5月5日 下午8:01:26 @throws
	 */
	public void EntuserAddBankCard(BankCard bkd,EnterpriseUser u) {
		bkd.setEnterpriseId(u.getEnterprise());
		bkd.setApplicationTime(new Date());
		bkd.setIsDefault(0);
		bkd.setIsQuickPay(0);
		bankCardDao.enterAddBankCard(bkd);
		u.getEnterprise().getBankcards().add(bkd);
		u.getEnterprise().setBankcards(new MyUtils().cardNumberEncryption(u.getEnterprise().getBankcards()));
	}

	/**
	 * @author 西安工业大学蓝桥一期--刘江 @Title: modifyIsDefault @Description:
	 *         修改是默认的卡 @return void @date 2017年5月6日 下午4:51:51 @throws
	 */
	public void modifyIsDefault(int cardid,User u) {
			// 把默认卡移除
			bankCardDao.removeIsDefault(u.getId());
			// 设置此卡为默认卡
			bankCardDao.modifyIsDefault(cardid);
			List<BankCard> bks = u.getListBankCard();
			for (int i = 0; i < bks.size(); i++) {
				if(bks.get(i).getId()==cardid){
					bks.get(i).setIsDefault(1);
				}else{
					bks.get(i).setIsDefault(0);
				}
			}	
		
	};
	/**
	 * @author 西安工业大学蓝桥一期--刘江 @Title: modifyIsDefault @Description:
	 *        企业 修改是默认的卡 @return void @date 2017年5月6日 下午4:51:51 @throws
	 */
	public void EntmodifyIsDefault(int cardid,EnterpriseUser u) {
			// 把默认卡移除
			bankCardDao.EntremoveIsDefault(u.getEnterprise().getId());
			// 设置此卡为默认卡
			List<BankCard> bks = u.getEnterprise().getBankcards();
			bankCardDao.modifyIsDefault(cardid);
			for (int i = 0; i < bks.size(); i++) {
				if(bks.get(i).getId()==cardid){
					bks.get(i).setIsDefault(1);
				}else{
					bks.get(i).setIsDefault(0);
				}
			}	
		
	};
	/**
	 * @author 西安工业大学蓝桥一期--刘江 @Title: modifyIsQuickPay @Description:
	 *         开启快捷支付 @return void @date 2017年5月6日 下午4:51:38 @throws
	 */
	public void modifyIsQuickPay(int cardid, User u) {
		// 用户开启快捷支付
		bankCardDao.modifyIsQuickPay(cardid);
		List<BankCard> list = u.getListBankCard();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getId()==cardid){
				list.get(i).setIsQuickPay(1);
			}
		}
	};
	public void EntmodifyIsQuickPay(int cardid, EnterpriseUser u) {
		// 企业开启快捷支付
		bankCardDao.modifyIsQuickPay(cardid);
		List<BankCard> list = u.getEnterprise().getBankcards();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getId()==cardid){
				list.get(i).setIsQuickPay(1);
			}
		}
	};

	/**
	 * @author 西安工业大学蓝桥一期--刘江 @Title: shutQuickPay @Description: 用户关闭快捷支付 @return
	 * void @date 2017年5月7日 下午5:19:19 @throws
	 */
	public void shutQuickPay(int cardid, User u) {
		bankCardDao.shutQuickPay(cardid);
		List<BankCard> list = u.getListBankCard();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getId()==cardid){
				list.get(i).setIsQuickPay(0);
			}
		}
		
	};
	/**
	 * @author 西安工业大学蓝桥一期--刘江 @Title: shutQuickPay @Description: 企业关闭快捷支付 @return
	 * void @date 2017年5月7日 下午5:19:19 @throws
	 */
	public void EntshutQuickPay(int cardid, EnterpriseUser u) {
		bankCardDao.shutQuickPay(cardid);
		List<BankCard> list = u.getEnterprise().getBankcards();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getId()==cardid){
				list.get(i).setIsQuickPay(0);
			}
		}
		
	};
	/**
	 * 
	 * @author 孙航建
	 * @time 2017年5月6日 下午3:54:40
	 * @param id
	 * @return
	 */
	public BankCard getBankcard(String id) {
		return bankCardDao.getBankByUserId(id);
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--王增 @Title: getBankCardByCardNumber @Description: TODO
	 * 根据银行卡卡号得到银行卡 @param @param cardNumber @param @return 设定文件 @return
	 * BankCard 返回类型 @date 2017年5月7日 上午11:21:59 @throws
	 */
	public BankCard getBankCardByCardNumber(String cardNumber) {
		return bankCardDao.getBankCardByCardNumber(cardNumber);
	}
	
	public void updateBankCardByIdAndBalance(BankCard bankCard) {
		bankCardDao.updateBankCardByIdAndBalance(bankCard);		
	}

	public BankCard getBankByUserId(String userCardNum) {
		return bankCardDao.getBankByUserId(userCardNum);
	}

}
