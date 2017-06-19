/**   
* @Title: EnterpriseUserServiceImpl.java 
* @Package org.lanqiao.pay.serviceImpl 
* @Description: TODO
* @author 西安工业大学蓝桥一期--王增   
* @date 2017年4月23日 下午2:51:53    
*/

package org.lanqiao.pay.serviceImpl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.lanqiao.pay.base.bean.EnterpriseUserCreteria;
import org.lanqiao.pay.base.dao.BankCardDao;
import org.lanqiao.pay.base.dao.BankDao;
import org.lanqiao.pay.base.dao.EnterpriseUnitDao;
import org.lanqiao.pay.base.dao.EnterpriseUserDao;
import org.lanqiao.pay.base.dao.LegalRepresentativeDao;
import org.lanqiao.pay.base.dao.SecretDao;
import org.lanqiao.pay.base.entity.Bank;
import org.lanqiao.pay.base.entity.BankCard;
import org.lanqiao.pay.base.entity.Enterprise;
import org.lanqiao.pay.base.entity.EnterpriseUnit;
import org.lanqiao.pay.base.entity.EnterpriseUser;
import org.lanqiao.pay.base.entity.EnterpriseUserPage;
import org.lanqiao.pay.base.entity.LegalRepresentative;
import org.lanqiao.pay.base.entity.Secret;
import org.lanqiao.pay.base.entity.forEnterpriseRegist.D;
import org.lanqiao.pay.base.exception.NotUniqueException;
import org.lanqiao.pay.base.util.MyUtils;
import org.lanqiao.pay.service.EnterpriseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 王增
 *
 */
@Service
public class EnterpriseUserServiceImpl implements EnterpriseUserService {

	@Autowired
	EnterpriseUserDao enterpriseUserDao;
	@Autowired
	EnterpriseServiceImpl enterpriseDao;
	@Autowired
	BankCardDao bankCardDao;
	@Autowired
	SecretDao secretDao;
	@Autowired
	LegalRepresentativeDao legalRepresentativeDao;
	@Autowired
	EnterpriseUnitDao enterpriseUnitDao;
	@Autowired
	BankDao bankDao;

	/**
	 * @author 西安工业大学蓝桥一期--王增 @Title: selectEnterpriseUserByEmail @Description:
	 * TODO 根据邮箱得到企业级用户 @param @param email @param @return 设定文件 @return
	 * EnterpriseUser 返回类型 @date 2017年4月25日 下午4:55:27 @throws
	 */
	public EnterpriseUser selectEnterpriseUserByEmail(String email) {
		// TODO Auto-generated method stub
		return enterpriseUserDao.selectEnterpriseUserByEmail(email);
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--王增 @Title: finalRegistStep @Description:
	 * TODO @param @param enterpriseUser @param @return 设定文件 @return String
	 * 返回类型 @date 2017年5月7日 下午4:37:41 @throws
	 */
	/*
	 * 1:查询邮箱、企业的组织机构代码号、法定代表人的身份证号、常用联系人的电话、银行账号的唯一性.
	 * 2:进行插入。(密保->企业法人->企业单位类型->企业->企业用户->银行卡)
	 */
	@Transactional(isolation = Isolation.SERIALIZABLE, timeout = 10)
	public void finalRegistStep(EnterpriseUser enterpriseUser) {
		String email = enterpriseUser.getEmail();// 用户的邮箱
		Enterprise enterprise = enterpriseUser.getEnterprise();
		LegalRepresentative legalRepresentative = enterprise.getLegalRepresentative();
		EnterpriseUnit enterpriseUnit = enterprise.getEnterpriseUnit();
		// 法定代表人的身份证 号码
		String certificateNumber = legalRepresentative.getCertificateNumber();
		// 常用联系人的电话
		String tell = enterpriseUser.getTell();
		D d = enterpriseUser.getD();
		// 银行账号
		String accountNumber = d.getCardNumber();

		EnterpriseUser enterpriseUser2 = enterpriseUserDao.getEnterpriseUserByEmail(email);
		Integer line = enterpriseDao.verifyIDCardOnly(certificateNumber);
		Integer line2 = enterpriseDao.verifyPhone(tell);
		BankCard bankCard = bankCardDao.getBankCardByCardNumber(accountNumber);
		Secret secret = enterpriseUser.getSecret();

		String msg = "";
		if (enterpriseUser2 != null) {
			msg = "邮箱已被注册";
		}
		if (bankCard != null) {
			msg = "银行卡已被注册";
		}
		if (line == 1) {
			msg = "法定人身份证号码已被注册";
		}
		if (line2 == 1) {
			msg = "常用联系人电话已被注册";
		}
		// 如果出现了上面的任意一种情况那么就会抛出不唯一的异常!!
		if (!msg.equals("")) {
			throw new NotUniqueException(msg);
		} else {
			secretDao.addByReq(secret);// 插secret完后就有id了
			// 插入法定人
			legalRepresentativeDao.addLegalRepresentative(legalRepresentative);
			// 插入企业类型
			enterpriseUnitDao.addEnterpriseUnit(enterpriseUnit);
			// 插入企业
			enterpriseDao.addEnterprise(enterprise);
			// 插入企业用户
			enterpriseUserDao.addEnterpriseUser(enterpriseUser);
			// 插入银行卡,先根据银行名称查询数据表bank,如果该银行不存在则插入银行后,再插入银行卡
			String bankName = d.getBankName();
			Bank bank = bankDao.getBankByName(bankName);
			if (bank == null) {
				bank = new Bank();
				bank.setName(bankName);
				bankDao.addBank(bank);
			}
			BankCard bankCard2 = new BankCard();
			bankCard2.setAddress(d.getAddress());
			bankCard2.setBankId(bank);
			bankCard2.setCardNumber(accountNumber);
			bankCard2.setEnterpriseId(enterprise);
			bankCardDao.enterAddBankCard(bankCard2);
		}
	}

	/**
	 * 通过email获取企业法人的密保表的id(这里涉及到级联查询)
	 * 
	 * @author 孙航建
	 * @time 2017年4月29日 下午7:07:21
	 * @param email
	 * @return
	 */
	public EnterpriseUser getEnterpriseUserByEmail(String email) {
		return enterpriseUserDao.getEnterpriseUserByEmail(email);
	}

	/**
	 * 通过id更新支付密码
	 * 
	 * @author 孙航建
	 * @time 2017年4月29日 下午8:57:13
	 * @param pwd
	 * @param id
	 */
	public void updateEnterpriseUserByEnUserId(String pwd, Integer id) {
		// 通过id获取secret对象
		Secret secret = secretDao.getSecret(id);
		String md5Pwd = MyUtils.md5(pwd);
		secret.setPayPassword(md5Pwd);
		secretDao.updateSecret(secret);
	}

	/**
	 * 判断email是否一致
	 * 
	 * @author 孙航建
	 * @time 2017年4月30日 上午12:16:02
	 * @param email
	 * @param id
	 * @return
	 */
	public boolean isEmailTrueOrFalse(String email, int id) {
		EnterpriseUser enUser = enterpriseUserDao.getEnterpriseUserById(id);
		System.out.println("email" + enUser.getEmail());
		if (enUser.getEmail().equals(email)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @author 西安工业大学蓝桥一期-代显峰 @title getEmail @description TODO
	 * 通过密保Id查询用户的email @param @param id @param @return @return String @date
	 * 2017年5月3日 下午7:07:51 @throws
	 */
	public String getEmail(Integer id) {
		return enterpriseUserDao.getEmail(id);
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期-代显峰 @title update @description
	 * 根据密保Id修改企业法定人的邮箱 @param @param id @param @param email @return void @date
	 * 2017年5月3日 下午7:22:47 @throws
	 */
	public void update(Integer id, String email) {
		enterpriseUserDao.update(id, email);
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--何璐 @Title: isLoginPwd @Description: TODO
	 * 判断登陆密码是否一样 @param @param id @param @param loginPassword @param @return
	 * 设定文件 @return boolean 返回类型 @date 2017年5月2日 下午4:30:10 @throws
	 */
	public boolean isLoginPwd(Integer id, String loginPassword) {
		// 通过id获取企业用户
		EnterpriseUser enterpriseUser = enterpriseUserDao.getEnterpriseUserById(id);
		// 从数据库获取原登陆密码
		String password = enterpriseUser.getPassword();
		// 对客户端传过来的原登陆密码进行加密
		String loginPassword2 = MyUtils.md5(loginPassword);
		// 判断客户端传过来的密码是否为空并且是否与原登陆密码相同
		if (loginPassword != null && loginPassword2.equalsIgnoreCase(password)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--何璐 @Title: modifyLoginPwd @Description: TODO
	 * 修改企业用户的登陆密码 @param @param id @param @param email @param @param
	 * loginPassword @param @param newPassword @param @return 设定文件 @return int
	 * 返回类型 ：返回1：修改成功;返回0：修改失败 @date 2017年5月3日 下午2:44:35 @throws
	 */
	public int modifyLoginPwd(Integer id, String email, String loginPassword, String newPassword) {
		boolean b1 = isEmailTrueOrFalse(email, id);
		boolean b2 = isLoginPwd(id, loginPassword);
		// 邮箱和密码都一致，则进行登陆密码的修改，并返回1，否则，返回0
		if (b1 && b2 == true) {
			EnterpriseUser eu = enterpriseUserDao.getEnterpriseUserById(id);
			// 给新的登陆密码后台加密
			String newPwd = MyUtils.md5(newPassword);
			// 设置新的登陆密码
			eu.setPassword(newPwd);
			// 去数据库更新
			enterpriseUserDao.update(eu);
			return 1;
		} else {
			return 0;
		}
	}

	// 根据分页条件查询记录条数
	@Override
	public Integer getCountByPage(EnterpriseUserPage enterpriseUserPage) {
		// TODO Auto-generated method stub
		return enterpriseUserDao.getCountByPage(enterpriseUserPage);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.lanqiao.pay.service.EnterpriseUserService#getAllByPage(org.lanqiao.
	 * pay.base.entity.EnterpriseUserPage)
	 * 查询符合条件的enterpriseUser,并连带他们的enterprise(在不级联下去了)
	 */
	@Override
	public List<EnterpriseUser> getAllByPage(EnterpriseUserPage enterpriseUserPage) {
		// TODO Auto-generated method stub
		return enterpriseUserDao.getAllByPage(enterpriseUserPage);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.lanqiao.pay.service.EnterpriseUserService#getEnterpriseUserDetail(
	 * java.lang.Integer) 根据id查询详细信息(级联)
	 */
	@Override
	public EnterpriseUser getEnterpriseUserDetail(Integer id) {
		// TODO Auto-generated method stub
		return enterpriseUserDao.getEnterpriseUserDetail(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.lanqiao.pay.service.EnterpriseUserService#approve(java.lang.Integer)
	 */
	@Override
	public Integer approve(Integer enterpriseUserId) {
		// TODO Auto-generated method stub
		return enterpriseUserDao.approve(enterpriseUserId);
	}
	/**   
	* @Title: EnterpriseUserDao.java 
	* @Package org.lanqiao.pay.base.dao 
	* @Description: 企业用户余额充值
	* @author 西安工业大学蓝桥一期--刘宣   
	* @date 2017年5月12日 下午6:58:20    
	*/
	public int addBalance(EnterpriseUser user) {
		return enterpriseUserDao.addBalance(user);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.lanqiao.pay.service.EnterpriseUserService#addEnterpriseUser(org.
	 * lanqiao.pay.base.entity.EnterpriseUser)
	 */
	@Override
	public Integer addEnterpriseUser(EnterpriseUser enterpriseUser) {
		// TODO Auto-generated method stub
		return enterpriseUserDao.addEnterpriseUser(enterpriseUser);
	}

	public int verifyName(String name) {
		// TODO Auto-generated method stub
		return enterpriseUserDao.verifyName(name);
	}

	public String verifyUser(String name) {
		// TODO Auto-generated method stub
		return enterpriseUserDao.verifyUser(name);
	}

	public EnterpriseUser getUserByNameAndPassword(String name, String password) {
		// TODO Auto-generated method stub
		return enterpriseUserDao.getUserByNameAndPassword(name, password);
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--王向宇 @Title: getEnterpriseIdByEmail @Description: TODO
	 * 通过邮箱获取一个enterprise的id @param @param email @param @return 设定文件 @return
	 * Integer 返回类型 @date 2017年5月14日 下午4:41:09 @throws
	 */
	public Integer getEnterpriseIdByEmail(String email) {
		return enterpriseUserDao.getEnterpriseIdByEmail(email);
	}

	/**
	 * @param orderby @param pageNo
	 * 
	 * @author 西安工业大学蓝桥一期—陈楚 @Title:
	 * getuserByqualified @Description:获取带有关键字的企业用户的数量 @param @param
	 * keyword @param @return 设定文件 @return int 返回类型 @date 2017年5月12日
	 * 下午8:53:48 @throws
	 */
	public EnterpriseUserCreteria getuserByqualified(String keyword, String pageNo, String orderby) {
		// 获取全部用户（带以上条件）的信息
		EnterpriseUserCreteria enterpriceUserCreteria = new EnterpriseUserCreteria(Integer.parseInt(pageNo), orderby,
				keyword);
		int total = enterpriseUserDao.getuserByqualified(keyword);
		enterpriceUserCreteria.setTotal(total);
		// 计算总页数
		if (total % 11 == 0) {
			enterpriceUserCreteria.setEnd(total / 11);
		} else {
			enterpriceUserCreteria.setEnd(total / 11 + 1);
		}
		return enterpriceUserCreteria;
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期—陈楚 @Title: getPage @Description: 获取分页的数据 @param @param
	 * userCreteria @param @return 设定文件 @return List<User> 返回类型 @date 2017年5月12日
	 * 下午8:54:25 @throws
	 */
	public List<EnterpriseUser> getPage(EnterpriseUserCreteria enterpriceUserCreteria) {
		List<EnterpriseUser> enterpriseUser = enterpriseUserDao.getPage(enterpriceUserCreteria);
		return enterpriseUser;
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--王向宇 @Title: modifyLoginPwd @Description: TODO
	 * 修改企业用户密码 @param @param id @param @param email @param @param
	 * loginPassword @param @param newPassword @param @return 设定文件 @return int
	 * 返回类型 @date 2017年5月17日 下午8:58:43 @throws
	 */
	public int changeLoginPwd(String email, String pwd1, String pwd2) {
		// 邮箱和密码都一致，则进行登陆密码的修改，并返回1，否则，返回0
		if (pwd1.equals(pwd2)) {
			EnterpriseUser eu = selectEnterpriseUserByEmail(email);
			// 给新的登陆密码后台加密
			String newPwd = MyUtils.md5(pwd1);
			// 将加密后的密码放入企业用户对象
			eu.setPassword(newPwd);
			// 去数据库更新
			enterpriseUserDao.update(eu);
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * 更新用户的金额
	 * 
	 * @author 孙航建
	 * @time 2017年5月21日 下午2:15:12
	 * @param en
	 */
	public void updateEnterUserBalance(EnterpriseUser en) {
		enterpriseUserDao.updateEnterUserBalance(en);
	}

	/**
	 * 
	* @author 西安工业大学蓝桥一期—陈楚
	* @Title: updateState 
	* @Description:修改企业用户个人状态信息
	* @param @param id
	* @param @param state    设定文件 
	* @return void    返回类型 
	* @date 2017年5月21日 下午3:43:10 
	* @throws
	 */
	public void updateState(String id, String state) {
		if(("1").equals(state)){
			//修改为禁用
			enterpriseUserDao.updateState(Integer.parseInt(id));
		}else if(("0").equals(state)){
			//修改为正常
			enterpriseUserDao.backToNormal(Integer.parseInt(id));
		}else{
			//注销用户
			enterpriseUserDao.deleteState(Integer.parseInt(id));
		}
		
	}

	/**
	 * 
	* @author 西安工业大学蓝桥一期—陈楚
	* @Title: rePassword 
	* @Description: 重置密码
	* @param @param id
	* @param @param rePassword    设定文件 
	* @return void    返回类型 
	* @date 2017年5月24日 下午8:46:13 
	* @throws
	 */
	public void rePassword(int id, String rePassword) {
		enterpriseUserDao.rePassword(id,rePassword);
	}
    /**
     * 
    * @author 西安工业大学蓝桥一期--何璐
    * @Title: getEnterpriseUser 
    * @Description: TODO  通过手机号获取企业法定人
    * @param @param epUserPhone    设定文件 
    * @return void    返回类型 
    * @date 2017年6月4日 下午2:29:29 
    * @throws
     */
	public EnterpriseUser getEnterpriseUserByPhone(String epUserPhone) {
		return enterpriseUserDao.getEnterpriseUserByPhone(epUserPhone);
	}

	/* (non-Javadoc)
	 * @see org.lanqiao.pay.service.EnterpriseUserService#getEnterpriseUserById(java.lang.Integer)
	 */
	@Override
	public EnterpriseUser getEnterpriseUserById(Integer id) {
		// TODO Auto-generated method stub
		return enterpriseUserDao.getEnterpriseUserById(id);
	}
    
	/**
	 * 通过enterpriseId获取EnterpriseUser
	 * @author 孙航建
	 * @time 2017年6月8日 下午5:16:52
	 * @param id
	 * @return
	 */
	public EnterpriseUser getEnterpriseByenterpriseId(Integer id) {
		return enterpriseUserDao.getEnterpriseByenterpriseId(id);
	}
	
	/**
	 * 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getEnterpriseUserByEnterpriseId 
	* @Description: TODO
	* @param @param id
	* @param @return    设定文件 
	* @return EnterpriseUser    返回类型 
	* @date 2017年6月11日 上午11:58:57 
	* @throws
	 */
	public EnterpriseUser getEnterpriseUserByEnterpriseId(Integer id) {
		// TODO Auto-generated method stub
		return enterpriseUserDao.getEnterpriseUserByEnterpriseId(id);
	}
	/**
	 * 
	* @author 西安工业大学蓝桥一期--邱新虎
	* @Title: isPay_Pwd 
	* @Description: TODO
	* @param @param id pay_Password
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @date 2017年6月11日 上午12:00;02 
	* @throws
	 */
	public boolean isPay_Pwd(int id, String pay_Password) {
		//获取Secret对象
		Secret sec = secretDao.getSecret(id);
		// 获取数据库登录密码
		String pay_Password1 = sec.getPayPassword();
		//  进行加密
		String pay_Password2 = MyUtils.md5(pay_Password);
		// 密码一致且不为空则返回true
		if (pay_Password2 != null && pay_Password2.equalsIgnoreCase(pay_Password1)) {
			return true;
		} else {
			return false;
		}
	}
	
	
	/**
	 * 
	* @author 西安工业大学蓝桥一期--邱新虎
	* @Title: modifyPhone 
	* @Description: TODO
	* @param @param id pay_Password
	* @param @return    设定文件 
	* @return int    返回类型 
	* @date 2017年6月11日 上午12:00;02 
	* @throws
	 */
	public int  updatePhone( Integer id,String tell){
			return enterpriseUserDao.updatePhone(id,tell);
	}
	/**
	 * 
	* @author 西安工业大学蓝桥一期--邱新虎
	* @Title: modifyPhoto 
	* @Description: TODO
	* @param @param id Photo
	* @param @return    设定文件 
	* @return int    返回类型 
	* @date 2017年6月11日 上午12:00;02 
	* @throws
	 */
	 public void modifyPhoto(HttpServletRequest request){
		 String strId = request.getParameter("id");
			Integer id = Integer.valueOf(strId);
			String newPhoto =request.getParameter("newPic");
		 EnterpriseUser eu = enterpriseUserDao.getEnterpriseUserById(id);
		 eu.setPhoto(newPhoto);
		 enterpriseUserDao.update(eu);
	 }


	/**
	 * 根据企业id修改企业用户头像--王向宇
	 * @param eu
	 */
	public void modifyPhotoById(EnterpriseUser eu) {
		enterpriseUserDao.modifyPhotoById(eu);
	}
}
