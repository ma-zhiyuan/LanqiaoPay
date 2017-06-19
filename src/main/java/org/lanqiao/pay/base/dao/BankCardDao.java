 package org.lanqiao.pay.base.dao;

import java.util.List;

import org.lanqiao.pay.base.entity.Bank;
import org.lanqiao.pay.base.entity.BankCard;
import org.lanqiao.pay.base.entity.Recharge_withdrawal;
import org.lanqiao.pay.mapper.BankCardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BankCardDao {
	@Autowired
	BankCardMapper bankCardMapper;
	
	/**
	* @author 西安工业大学蓝桥一期--李明@Title: getBankname @Description: 利用级联查询获取bankcard的所有信息 
	* @return List<BankCard>   @date 2017年5月14日 下午4:46:38  @throws
	 */
	public List<BankCard> getBankName(Integer id){
		return bankCardMapper.getBankName(id);
	}
	
	/**
	* @author 西安工业大学蓝桥一期--李明 @Title: chongZhiJiLu @Description: 
	*   @return int   @date 2017年5月14日 下午4:35:19  @throws
	 */
	public int chongZhiJiLu(Recharge_withdrawal czjl){
		return bankCardMapper.chongZhiJiLu(czjl);
	}
	/**
	* @author 西安工业大学蓝桥一期--李明 @Title: chongZhiout 银行卡减钱
	* @Description: TODO  @return void   @date 2017年5月14日 下午4:33:04 @throws
	 */
	public int chongZhiout(BankCard b){
		return bankCardMapper.chongZhiout(b);
	}
	
	/**
	 * 
	* @author 西安工业大学蓝桥一期--李明 @Title: getCardByNumber  @Description: TODO 
	* @return BankCard   @date 2017年5月14日 下午4:27:46  @throws
	 */
	public BankCard getCardByNumber(String number){
		return bankCardMapper.getCardByNumber(number);
	}
	

	/**
	 * @author 西安工业大学蓝桥一期--刘江 @Title: addBankCard @Description: 用户添加银行卡 @return
	 *         void @date 2017年5月5日 下午7:24:28 @throws
	 */
	public void userAddBankCard(BankCard bkd) {
		bankCardMapper.userAddBankCard(bkd);
	};

	/**
	 * @author 西安工业大学蓝桥一期--刘江 @Title: removeBankCard @Description: 移除一张卡 @return
	 *         void @date 2017年5月5日 下午7:25:54 @throws
	 */
	public void removeBankCard(int bkdid) {
		bankCardMapper.removeBankCard(bkdid);

	};

	/**
	 * @author 西安工业大学蓝桥一期--刘江 @Title: updateBankCard @Description: 修改卡 @return
	 *         void @date 2017年5月5日 下午7:42:48 @throws
	 */
	public void updateBankCard(BankCard bkd) {
		bankCardMapper.updateBankCard(bkd);

	};

	/**
	 * @author 西安工业大学蓝桥一期--刘江 @Title: getBankCards @Description: 用户查询卡 @return
	 *         List<BankCard> @date 2017年5月5日 下午7:43:02 @throws
	 */
	public List<BankCard> userGetBankCards(int userId) {
		return bankCardMapper.userGetBankCards(userId);

	};

	/**
	 * @author 西安工业大学蓝桥一期--刘江 @Title: getBankCards @Description:
	 *         用户查询一张默认卡 @return List<BankCard> @date 2017年5月5日
	 *         下午7:43:02 @throws
	 */
	public BankCard userGetBankCard(int userId) {
		return bankCardMapper.userGetBankCard(userId);

	};

	/**
	 * @author 西安工业大学蓝桥一期--刘江 @Title: enterGetBankCards @Description:
	 *         企业查询卡 @return List<BankCard> @date 2017年5月5日 下午7:59:49 @throws
	 */
	public List<BankCard> enterGetBankCards(int enterId) {
		return bankCardMapper.enterGetBankCards(enterId);
	}

	/**
	 * @author 西安工业大学蓝桥一期--刘江 @Title: enterAddBankCard @Description:
	 *         企业添加银行卡 @return void @date 2017年5月5日 下午8:01:26 @throws
	 */
	public void enterAddBankCard(BankCard bkd) {
		bankCardMapper.enterAddBankCard(bkd);

	}

	/**
	 * @author 西安工业大学蓝桥一期--刘江 @Title: modifyIsDefault @Description:
	 *         修改是否为默认的卡 @return void @date 2017年5月6日 下午4:51:51 @throws
	 */
	public void modifyIsDefault(int cardid) {
		bankCardMapper.modifyIsDefault(cardid);
	};

	/**
	 * @author 西安工业大学蓝桥一期--刘江 @Title: removeIsDefault @Description:
	 * 用户把默认卡设置为非默认 @return void @date 2017年5月7日 下午3:10:33 @throws
	 */
	public void removeIsDefault(int userid) {
		bankCardMapper.removeIsDefault(userid);
	};
	/**
	 * @author 西安工业大学蓝桥一期--刘江 @Title: removeIsDefault @Description:
	 * 企业把默认卡设置为非默认 @return void @date 2017年5月7日 下午3:10:33 @throws
	 */
	public void EntremoveIsDefault(int userid) {
		bankCardMapper.EntremoveIsDefault(userid);
	};

	/**
	 * @author 西安工业大学蓝桥一期--刘江 @Title: modifyIsQuickPay @Description:
	 *         用户开启快捷支付 @return void @date 2017年5月6日 下午4:51:38 @throws
	 */
	public void modifyIsQuickPay(int cardid) {
		bankCardMapper.modifyIsQuickPay(cardid);
	};

	/**
	 * @author 西安工业大学蓝桥一期--刘江 @Title: shutQuickPay @Description: 关闭快捷支付 @return
	 * void @date 2017年5月7日 下午5:19:19 @throws
	 */
	public void shutQuickPay(int cardid) {
		bankCardMapper.shutQuickPay(cardid);
	};
	

	/**
	 * 通过userid获取bankCart信息
	 * 
	 * @author 孙航建
	 * @time 2017年5月6日 下午4:00:53
	 * @return
	 */
	public BankCard getBankByUserId(String kh) {
		return bankCardMapper.getBankByUserId(kh);
	}

	/**
	 * @author 西安工业大学蓝桥一期--王增 @Title: getBankCardByCardNumber @Description: TODO
	 * 根据银行卡卡号得到银行卡 @param @param cardNumber @param @return 设定文件 @return
	 * BankCard 返回类型 @date 2017年5月7日 上午11:23:06 @throws
	 */

	public BankCard getBankCardByCardNumber(String cardNumber) {
		// TODO Auto-generated method stub
		return bankCardMapper.getBankCardByCardNumber(cardNumber);
	}

	/**
	 * 去数据库更新银行卡的余额
	 * @author 孙航建
	 * @param integer
	 * @param bankBalance
	 * @time 2017年5月7日 下午10:26:32
	 */
	public void updateBankCardByIdAndBalance(BankCard bc) {
		bankCardMapper.updateBankCardByIdAndBalance(bc);
	}
	
	/**
	 * 
	* @author 西安工业大学蓝桥一期--王向宇
	* @Title: getBankCardsByEnterpriseId 
	* @Description: TODO  通过企业id查询一系列银行卡
	* @param @param enterpriseId
	* @param @return    设定文件 
	* @return List<BankCard>    返回类型 
	* @date 2017年5月14日 上午9:45:28 
	* @throws
	 */
	public List<BankCard> getBankCardsByEnterpriseId(Integer enterpriseId){
		return bankCardMapper.getBankCardsByEnterpriseId(enterpriseId);
	}
	
	/**
	 * 
	* @author 西安工业大学蓝桥一期--王向宇
	* @Title: getBankCardByUserId 
	* @Description: TODO  通过UserId来获取一系列银行卡
	* @param @param userId
	* @param @return    设定文件 
	* @return List<BankCard>    返回类型 
	* @date 2017年5月14日 下午2:25:56 
	* @throws
	 */
	public List<BankCard> getBankCardsByUserId(Integer userId){
		return bankCardMapper.getBankCardsByUserId(userId);
	}

	/**
	 * 通过userId获取所有的bankCard信息
	 * @author 孙航建
	 * @time 2017年5月12日 下午3:05:00
	 * @param id
	 * @return
	 */
	public List<BankCard> getAllBankCard(Integer id) {
		return bankCardMapper.getAllBankCard(id);
	}

	/**
	 * 通过enterpriseId获取所有的企业的银行卡信息
	 * @author 孙航建
	 * @time 2017年5月15日 下午11:56:09
	 * @param id
	 * @return
	 */
	public List<BankCard> getAllBankCardByEnterPriseId(Integer id) {
		return bankCardMapper.getAllBankCardByEnterPriseId(id);
	}
	/**
	 * 通过bankCardId获取当前的利率
	 * @author 孙航建
	 * @time 2017年5月25日 上午10:38:15
	 * @param id
	 * @return
	 */
	public BankCard getBanKParities(Integer id) {
		return bankCardMapper.getBanKParities(id);
	}
	/**
	 * 更新用户使用的银行卡余额
	 * @author 西安工业大学蓝桥一期-代显峰
	 * @title updateBalance
	 * @description	TODO
	 * @param @param money
	 * @param @return
	 * @return int
	 * @date 2017年5月24日下午8:41:38
	 * @throws
	 */
		

	public void updateBalance(BankCard bc) {
		
	}
	/**
	 * @author 西安工业大学蓝桥一期--毋泽航
	 * @Title: updateBankCardBalance 
	 * @Description: 更新银行卡的金额 
	 * @throws
	 */
	public void updateBankCardBalance(BankCard toBankCard) {
		bankCardMapper.updateBankCardBalance(toBankCard);
	}

}
