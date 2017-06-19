package org.lanqiao.pay.serviceImpl;

import java.util.List;

import org.lanqiao.pay.base.bean.UserCreteria;
import org.lanqiao.pay.base.dao.SecretDao;
import org.lanqiao.pay.base.dao.UserDao;
import org.lanqiao.pay.base.entity.EnterpriseUser;
import org.lanqiao.pay.base.entity.Recharge_withdrawal;
import org.lanqiao.pay.base.entity.Secret;
import org.lanqiao.pay.base.entity.Transfer;
import org.lanqiao.pay.base.entity.User;
import org.lanqiao.pay.base.util.MyUtils;
import org.lanqiao.pay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**   
* @Title: UserServiceImp.java 
* @Package org.lanqiao.pay.serviceImp 
* @Description: TODO() 
* @author 西安工业大学蓝桥一期--刘江  
* @date 2017年4月23日 上午11:05:32    
*/
/**   
* @Title: UserServiceImp.java 
* @Package org.lanqiao.pay.serviceImp 
* @Description: TODO() 
* @author 西安工业大学蓝桥一期--刘江  
* @date 2017年4月23日 上午11:07:06    
*/
/**   
* @Title: UserServiceImp.java 
* @Package org.lanqiao.pay.serviceImp 
* @Description: UserService接口的实现类，在这个类里调用dao层的代码
* @author 西安工业大学蓝桥一期--刘江  
* @date 2017年4月23日 上午11:07:21    
*/
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao userDao;
	@Autowired 
	SecretDao secretDao;
	 /**
     * 
    * @author 西安工业大学蓝桥一期--何璐
    * @Title: isCardId
    * @Description: TODO 判断身份证号是否正确
    * @param @param id cardId
    * @param @return    设定文件 
    * @return boolean    返回类型 
    * @date 2017年4月24日 下午9:46:28 
    * @throws
     */
	@Override
	public boolean isCardId(Integer id, String cardId) {
		User user = userDao.getUser(id);
		//从数据库获取身份证号码
		String cardId2 = user.getCardId();
		//客户端传过来的身份证号不为空且与数据库中的身份证号一样时，返回true;否则返回false
		if(cardId!=null&&cardId.equals(cardId2)){
			return true;
		}else{
		    return false;
		}
	}
	 /**
     * 
    * @author 西安工业大学蓝桥一期--何璐
    * @Title: isCardId
    * @Description: TODO 判断登陆密码是否正确
    * @param @param id loginPassword
    * @param @return    设定文件 
    * @return boolean    返回类型 
    * @date 2017年4月24日 下午10:02:20 
    * @throws
     */
	@Override
	public boolean isLoginPwd(Integer id, String loginPassword) {
		User user = userDao.getUser(id);
		//从数据库获取登陆密码
		String loginPassword1 = user.getLoginPassword();
		//对客户端传过来的登陆密码进行md5加密
		String loginPassword2 = MyUtils.md5(loginPassword);
		//客户端传过来的登陆密码不为空且与数据库中的登陆密码相等时，返回true;否则返回false
		if(loginPassword!=null&&loginPassword2.equals(loginPassword1)){
			return true;
		}else{
			return false;
		}
		
	}
	 /**
     * 
    * @author 西安工业大学蓝桥一期--何璐
    * @Title: getUser
    * @Description: TODO 通过ID获取用户
    * @param @param id 
    * @param @return    设定文件 
    * @return boolean    返回类型 
    * @date 2017年4月24日 下午10:05:23 
    * @throws
     */
	@Override
	public User getUser(Integer id) {
		User user = userDao.getUser(id);
		return user;
	}
	 /**
     * 
    * @author 西安工业大学蓝桥一期--何璐
    * @Title: isCardId
    * @Description: TODO 身份证号与登陆密码正确的情况下进行修改登陆密码操作
    * @param @param User newLoginPwd
    * @param @return    设定文件 
    * @return int    返回1:修改成功;返回0:修改失败 
    * @date 2017年4月24日 下午10:30:28 
    * @throws
     */
	@Override
	public int modify(User user,String newLoginPwd) {
		//判断身份证是否正确
		boolean boo1 = isCardId(user.getId(), user.getCardId());
		//判断登陆密码是否正确
		boolean boo2 = isLoginPwd(user.getId(), user.getLoginPassword());
		//身份证和登陆密码都正确则可以修改登陆密码，并返回1;否则返回0
		if(boo1&&boo2){
			user.setLoginPassword(MyUtils.md5(newLoginPwd));
			userDao.updateUser(user);
			return 1;
		}
		
		return 0;
	}
	/**   
	* @Title: UserServiceImpl.java 
	* @Package org.lanqiao.pay.serviceImp 
	* @Description: TODO(用一句话描述该文件做什么) 
	* @author 西安工业大学蓝桥一期--刘宣   
	* @date 2017年4月26日 下午8:10:01    
	*/
	
	public User getUserByUserName(String userName) {
		return userDao.getUserByUserName(userName);
	}
	
	/**
	* @author 西安工业大学蓝桥一期--陈楚
	* @Title: getUserByName 
	* @Description: 通过name查询是否有这个用户 
	* @param @param name
	* @param @return
	* @return List<User>
	* @date 2017年4月27日 下午6:33:33 
	* @throws
	 */
	public List<User> getUserByName(String name) {
		List<User> user = userDao.getUserByName(name);
		return user;
	}
	/**
	* @author 西安工业大学蓝桥一期--陈楚
	* @Title: getUserByNameAndPassword 
	* @Description:通过姓名和密码查询用户信息
	* @param @param name
	* @param @param password
	* @param @return
	* @return User
	* @date 2017年4月27日 下午7:34:17 
	* @throws
	 */
	public User getUserByNameAndPassword(String name, String password) {
		User user = userDao.getUserByNameAndName(name,password);
			return user;
		
	}
	/**
	* 
	* @author 西安工业大学蓝桥一期--董正健
	* @Title: getUserByEmail 
	* @Description: 通过邮箱查找密保表id（存在于user表中）
	* @param @param email
	* @param @return    设定文件 
	* @return User    返回类型 
	* @date 2017年4月26日 上午9:02:57 
	* @throws
	 */
	public User getUserByEmail(String email) {
		User user = userDao.getUserByEmail(email);
		return user;
	}
	/**
	 * 
	 * @author 西安工业大学蓝桥一期-代显峰
	 * @title update
	 * @description	TODO 用一句话描述方法的作用
	 * @param @param id
	 * @param @param email
	 * @return void
	 * @date 2017年4月26日 下午5:36:35
	 * @throws
	 */
	public void update(Integer id, String email) {
		 userDao.update(id,email);
	}
	/**
	 * 
	 * @author 西安工业大学蓝桥一期-代显峰
	 * @title getUser
	 * @description	TODO 用一句话描述方法的作用
	 * @param @param email
	 * @param @return
	 * @return int
	 * @date 2017年4月26日 下午7:51:03
	 * @throws
	 *//*
	public User getUser(String email) {
		System.out.println("ccccccc");
		return userDao.getUser(email);
	}*/
	
	public void updateUserByReq(User u) {
		
		 userDao.updateUserByReq(u);
		
	}
	
	/**
	 * 
	* @author 西安工业大学蓝桥一期--陈楚
	* @Title: getAllUsers 
	* @Description: 换取全部用户信息
	* @param @return
	* @return List<User>
	* @date 2017年5月3日 下午1:31:23 
	* @throws
	 */
	public List<User> getAllUsers() {
		List<User> users = userDao.getAllUsers();
		return users;
	}
	
	/**
	 * 
	* @author 西安工业大学蓝桥一期--董正健
	* @Title: isUserEmail 
	* @Description: 判断前台输入的email和用户的email是否一致 
	* @param @param id
	* @param @param email
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @date 2017年5月7日 下午6:04:13 
	* @throws
	 */
	public boolean isUserEmail(int id, String email) {
		User user = userDao.getUser(id);
		String userEmail = user.getEmail();
		if (userEmail.equals(email)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 
	* @author 西安工业大学蓝桥一期--陈楚
	* @Title: getPage 
	* @Description: 分页 
	* @param @param userCreteria
	* @param @return
	* @return List<User>
	* @date 2017年5月4日 下午3:54:56 
	* @throws
	 */
	public List<User> getPage(UserCreteria userCreteria) {
		List<User> page = userDao.getPage(userCreteria);
		return page;
	}
	
	/**
	 * @param orderby 
	 * @param pageNo 
	 * 
	* @author 西安工业大学蓝桥一期--陈楚
	* @Title: getuserByqualified 
	* @Description: 有条件的查询所有用户需要的信息
	* @param @param keyword
	* @param @return
	* @return int
	* @date 2017年5月5日 下午8:18:14 
	* @throws
	 */
	public UserCreteria getuserByqualified(String keyword, String pageNo, String orderby) {
		// 获取全部用户（带以上条件）的信息
				UserCreteria userCreteria = new UserCreteria(Integer.parseInt(pageNo), orderby,
						keyword);
				int total = userDao.getuserByqualified(keyword);
				//System.out.println("------total:"+total);
				userCreteria.setTotal(total);
				// 计算总页数
						if (total % 11 == 0) {
							userCreteria.setEnd(total / 11);
						} else {
							userCreteria.setEnd(total / 11 + 1);
						}
				return userCreteria;
	}
	/**
	 * 
	* @author 西安工业大学蓝桥一期--王婷
	* @Title: updateUserName 
	* @Description: 修改用户名
	* @param @param user    设定文件 
	* @return void    返回类型 
	* @date 2017年5月17日 上午1:16:09 
	* @throws
	 */
	public void updateUserName(User user) {
		 userDao.updateUserName(user);
		
	}

	
	/**
	 * 
	* @author 西安工业大学蓝桥一期--王向宇
	* @Title: getUserIdByName 
	* @Description: TODO  通过name获取user的id
	* @param @param name
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @date 2017年5月14日 下午3:34:54 
	* @throws
	 */
	public Integer getUserIdByName(String name){
		return userDao.getUserIdByName(name);
	}
	
	/**
	 * 
	* @author 西安工业大学蓝桥一期--王向宇
	* @Title: getUserIdByEmail 
	* @Description: TODO 通过user的邮箱获取user的id
	* @param @param email
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @date 2017年5月14日 下午4:09:54 
	* @throws
	 */
	public Integer getUserIdByEmail(String email){
		return userDao.getUserIdByEmail(email);
	}
	/**
	 * 
	* @author 西安工业大学蓝桥一期--王婷
	* @Title: changeLoginPwd 
	* @Description: 密码判断并更新 密码
	* @param @param email
	* @param @param pwd1
	* @param @param pwd2
	* @param @return    设定文件 
	* @return int    返回类型 
	* @date 2017年5月21日 上午11:38:52 
	* @throws
	 */
	public int changeLoginPwd(String email,String pwd1,String pwd2){
		//邮箱和密码都一致，则进行登陆密码的修改，并返回1，否则，返回0
		if(pwd1.equals(pwd2)){
			User u=userDao.getUserByEmail1(email);
			//给新的登陆密码后台加密
			String newPwd = MyUtils.md5(pwd1);
			//将加密后的密码放入用户对象
			u.setLoginPassword(newPwd);
			//去数据库更新
		     userDao.updateUserbyEmail(u);
		    return 1;
		  }else{
			return 0;
		}
	}

	
	/**
	 * 更新用户支付中的余额 
	 * @author 孙航建
	 * @time 2017年5月21日 下午1:49:42
	 * @param u
	 */
	public void updateUserBalanceOne(User u) {
        userDao.updateUserBalanceOne(u);	
	}
	
	/**
	 * 
	* @author 西安工业大学蓝桥一期—陈楚
	* @Title: updateState 
	* @Description:修改个人状态信息
	* @param @param id
	* @param @param state    设定文件 
	* @return void    返回类型 
	* @date 2017年5月21日 下午3:43:10 
	* @throws
	 */
	public void updateState(String id, String state) {
		if(("0").equals(state)){
			//修改为禁用
			userDao.updateState(Integer.parseInt(id));
		}else if(("1").equals(state)){
			//修改为正常
			userDao.backToState(Integer.parseInt(id));
		}else{
			userDao.deleteState(Integer.parseInt(id));
		}
		
	}
	
	/**
	 * @param user 
	 * 
	* @author 西安工业大学蓝桥一期—陈楚
	* @Title: resetPassword 
	* @Description: 重置密码
	* @param @param rePassword    设定文件 
	* @return void    返回类型 
	* @date 2017年5月24日 下午7:18:36 
	* @throws
	 */
	public void resetPassword(String rePassword, Integer id) {
		userDao.resetPassword(rePassword,id);
	}
	/**
	 * 通过指定的账户获取转入用户的信息
	 * @author 孙航建
	 * @time 2017年6月4日 上午9:26:06
	 * @param personPhone
	 * @return
	 */
	public User getUserByPhone(String personPhone) {
		return userDao.getUserByPhone(personPhone);
	}
	/**
	 * 判断页面转入金额的个人账户在数据库中是否存在
	 * @author 孙航建
	 * @time 2017年6月4日 下午2:59:38
	 * @param phone
	 * @return
	 */
	public User isTrueOrFalsePhone(String phone) {
		return userDao.isTrueOrFalsePhone(phone);
	}
	
	
	
	public List<Recharge_withdrawal> getRecharge_withdrawal(Integer id) {		
		return userDao.getRecharge_withdrawal(id);
	}
	public List<Transfer> getTransfer(Integer id) {
		return userDao.getTransfer(id);
	}
	public List<Transfer> getTransferAll(Integer id) {
		return userDao.getTransferAll(id);
	}
	public List<Recharge_withdrawal> getRecharge_withdrawall(Integer id) {
		return userDao.getRecharge_withdrawall(id);
	}
	/**
	 * 根据手机号获取该用户信息
	 * @author 西安工业大学蓝桥一期-代显峰
	 * @title getUser
	 * @description	TODO
	 * @param @param userName
	 * @param @return
	 * @return User
	 * @date 2017年6月6日下午10:45:27
	 * @throws
	 */
	public User getUser(String phone) {
		return userDao.getUserByPhone(phone);
	}
	

	
}
