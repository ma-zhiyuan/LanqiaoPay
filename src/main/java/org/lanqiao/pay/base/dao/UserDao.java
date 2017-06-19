package org.lanqiao.pay.base.dao;

import java.util.List;

import org.lanqiao.pay.base.bean.UserCreteria;
import org.lanqiao.pay.base.entity.Recharge_withdrawal;
import org.lanqiao.pay.base.entity.Secret;
import org.lanqiao.pay.base.entity.Transfer;
import org.lanqiao.pay.base.entity.User;
import org.lanqiao.pay.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

/**
 * @Title: 用户DAO层
 * @Package org.lanqiao.pay.base.dao
 * @Description: TODO()
 * @author 西安工业大学蓝桥一期--刘江
 * @date 2017年4月23日 上午11:03:33
 */
@Repository
public class UserDao {
	@Autowired
	UserMapper userMapper;

	/**
	 * @author 西安工业大学蓝桥一期--李明 @Title: chongZhiin @Description: 用户充值
	 * @return void @date 2017年5月14日 下午4:31:13 @throws
	 */
	public int chongZhiin(User u) {
		return userMapper.chongZhiin(u);
	}

	public Integer getSecretId(Integer id) {
		return userMapper.getSecretId(id);

	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--何璐 @Title: getUser @Description: TODO
	 *         查询用户 @param @param id @param @return 设定文件 @return User 返回类型 @date
	 *         2017年4月24日 下午7:46:28 @throws
	 */
	public User getUser(int id) {
		return userMapper.getUser(id);
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--何璐 @Title: updateUser @Description: TODO
	 *         更新用户 @param @param user 设定文件 @return void 返回类型 @date 2017年4月24日
	 *         下午7:45:05 @throws
	 */
	public void updateUser(User user) {
		userMapper.update(user);
	}

	/**
	 * @Title: UserDao.java
	 * @Package org.lanqiao.pay.base.dao
	 * @Description: TODO(用一句话描述该文件做什么)
	 * @author 西安工业大学蓝桥一期--刘宣
	 * @date 2017年4月26日 下午8:10:38
	 */

	public User getUserByUserName(String userName) {
		return userMapper.getUserByUserName(userName);
	}

	/**
	 * @author 西安工业大学蓝桥一期--陈楚 @Title: getUser @Description: @param @param
	 *         name @param @return @return User @date 2017年4月24日
	 *         下午8:54:58 @throws
	 */
	public List<User> getUserByName(String name) {
		List<User> user = userMapper.getUserByName(name);
		return user;
	}

	/**
	 * @author 西安工业大学蓝桥一期--陈楚 @Title: getUserByNameAndName @Description:
	 *         通过姓名和密码判断用户信息 @param @param name @param @param
	 *         password @param @return @return User @date 2017年4月27日
	 *         下午7:35:42 @throws
	 */
	public User getUserByNameAndName(String name, String password) {
		User user = userMapper.getUserByNameAndPassword(name, password);
		return user;
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--董正健 @Title: getUserByEmail @Description:
	 * 通过邮箱查找密保表id（存在于user表中），需要级联查询 @param @param email @param @return
	 * 设定文件 @return User 返回类型 @date 2017年5月7日 下午6:14:58 @throws
	 */
	public User getUserByEmail(String email) {
		User user = userMapper.getUserByEmail(email);
		return user;
	}

	/**
	 * @author 西安工业大学蓝桥一期-代显峰 @title getSecretId @param @param
	 *         id @param @return @return User @date 2017年4月26日 下午4:57:01 @throws
	 */

	public void update(Integer id, String email) {
		userMapper.updateUser(id, email);
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期-代显峰 @title getUser @description TODO
	 *         用一句话描述方法的作用 @param @param email @param @return @return int @date
	 *         2017年4月26日 下午7:53:50 @throws
	 */
	public User getUser(String email) {
		return userMapper.getUserByEmail(email);
	}

	/**
	 * @author 西安工业大学蓝桥一期--刘江 @Title: regCardId @Description:
	 *         注册的时候检测身份证是否可用 @return User @date 2017年4月28日 下午7:59:46 @throws
	 */
	public User regCardId(User u) {
		return null;

	}

	/**
	 * @author 西安工业大学蓝桥一期--刘江 @Title:
	 *         updateUserByReq @Description:注册讲用户插入数据库 @return void @date
	 *         2017年5月2日 下午11:51:28 @throws
	 */
	public void updateUserByReq(User u) {
		userMapper.updateUserByReq(u);
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--陈楚 @Title: getAllUsers @Description:
	 * 查询所有用户信息 @param @return @return List<User> @date 2017年5月3日
	 * 下午1:32:35 @throws
	 */
	public List<User> getAllUsers() {
		List<User> users = userMapper.getAllUsers();
		return users;
	}

	/**
	 * 
	 * @Title: UserDao.java
	 * @Package org.lanqiao.pay.base.dao
	 * @Description: 通过邮箱找用户
	 * @author 西安工业大学蓝桥一期--董正健
	 * @date 2017年5月3日 下午12:50:32
	 */
	public User getUserByemail(String email) {
		return userMapper.getUserByEmail(email);
	}

	/**
	 * 
	 * @Title: UserDao.java
	 * @Package org.lanqiao.pay.base.dao
	 * @Description: 通过id获取secret对象
	 * @author 西安工业大学蓝桥一期--董正健
	 * @date 2017年5月3日 下午1:14:09
	 */
	public Secret getSecretById(Integer id) {
		return userMapper.getSecretById(id);
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--陈楚 @Title: getPage @Description:
	 * 分页处理 @param @return @return List<User> @date 2017年5月4日 下午3:55:44 @throws
	 */
	public List<User> getPage(UserCreteria userCreteria) {
		List<User> page = userMapper.getPage(userCreteria);
		return page;
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--陈楚 @Title: getuserByqualified @Description:
	 * 查找有条件的用户信息的数量 @param @param keyword @param @return @return int @date
	 * 2017年5月5日 下午8:19:13 @throws
	 */
	public int getuserByqualified(String keyword) {
		int i = userMapper.getuserByqualified(keyword);
		return i;
	}

	/**
	 * 去更新user的余额
	 * 
	 * @author 孙航建
	 * @time 2017年5月7日 下午9:43:23
	 * @param balance
	 * @param id
	 */
	public void updateUserBalanceOne(User u) {
		userMapper.updateUserBalanceOne(u);
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--王婷 @Title: updateUserName @Description:
	 * 更新用户name @param @param user 设定文件 @return void 返回类型 @date 2017年5月17日
	 * 下午9:16:11 @throws
	 */
	public void updateUserName(User user) {
		userMapper.updateUserName(user);

	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--王向宇 @Title: getUserIdByName @Description: TODO
	 * 通过name查询user的id @param @param name @param @return 设定文件 @return Integer
	 * 返回类型 @date 2017年5月14日 下午3:35:58 @throws
	 */
	public Integer getUserIdByName(String name) {
		return userMapper.getUserIdByName(name);
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--王向宇 @Title: getUserIdByEmail @Description: TODO
	 * 通过user的邮箱查询user的id @param @param email @param @return 设定文件 @return
	 * Integer 返回类型 @date 2017年5月14日 下午4:10:26 @throws
	 */
	public Integer getUserIdByEmail(String email) {
		return userMapper.getUserIdByEmail(email);
	}

	/**
	 * 
	* @author 西安工业大学蓝桥一期--王婷
	* @Title: updateUserbyEmail 
	* @Description: 通过邮箱更新用户密码
	* @param @param u    设定文件 
	* @return void    返回类型 
	* @date 2017年5月21日 上午11:40:21 
	* @throws
	 */
	public void updateUserbyEmail(User u) {
		 userMapper.updateUserbyEmail(u);
		
	}
	   /**
	    * 
	   * @author 西安工业大学蓝桥一期--王婷
	   * @Title: getUserByEmail1 
	   * @Description:通过邮箱查找用户
	   * @param @param email
	   * @param @return    设定文件 
	   * @return User    返回类型 
	   * @date 2017年5月21日 上午11:39:48 
	   * @throws
	    */
		public User getUserByEmail1(String email) {
			 return userMapper.getUserByEmail1(email);
		}

	/**
	 * 
	* @author 西安工业大学蓝桥一期—陈楚
	* @Title: updateState 
	* @Description:修改用户状态信息 改为禁用
	* @param     设定文件 
	* @return void    返回类型 
	* @date 2017年5月21日 下午2:14:15 
	* @throws
	 */
	public void updateState(int id) {
		userMapper.updateState(id);
	}
	/**
	 * 
	* @author 西安工业大学蓝桥一期—陈楚
	* @Title: backToState 
	* @Description: 修改用户状态信息 改为正常
	* @param @param parseInt    设定文件 
	* @return void    返回类型 
	* @date 2017年5月21日 下午2:42:46 
	* @throws
	 */
	public void backToState(int id) {
		userMapper.backToState(id);
		
	}

	/**
	 * 
	* @author 西安工业大学蓝桥一期—陈楚
	* @Title: deleteState 
	* @Description: 注销用户
	* @param @param id    设定文件 
	* @return void    返回类型 
	* @date 2017年5月22日 下午7:16:54 
	* @throws
	 */
	public void deleteState(int id) {
		userMapper.deleteState(id);
	}
	/**
	 * 更新用户支付宝余额
	 * @author 西安工业大学蓝桥一期-代显峰
	 * @title updateBalance
	 * @description	TODO
	 * @param @param money
	 * @param @return
	 * @return int
	 * @date 2017年5月24日下午8:36:46
	 * @throws
	 */
	public void updateBalance(User u) {
		userMapper.updateUserBalanceOne(u);
	}

	/**
	 * @param user 
	 * 
	* @author 西安工业大学蓝桥一期—陈楚
	* @Title: resetPassword 
	* @Description: 重置密码 
	* @param @param rePassword    设定文件 
	* @return void    返回类型 
	* @date 2017年5月24日 下午7:19:35 
	* @throws
	 */
	public void resetPassword(String rePassword, Integer id) {
		userMapper.resetPassword(rePassword,id);
	}

	/**
	 * 通过指定的账户获取转入用户的信息
	 * @author 孙航建
	 * @time 2017年6月4日 上午9:27:05
	 * @param personPhone
	 * @return
	 */
	public User getUserByPhone(String personPhone) {
		return userMapper.getUserByPhone(personPhone);
	}
    /**
     * 判断页面转入金额的个人账户在数据库中是否存在
     * @author 孙航建
     * @time 2017年6月4日 下午2:59:31
     * @param phone
     * @return
     */
	public User isTrueOrFalsePhone(String phone) {
		return userMapper.isTrueOrFalsePhone(phone);
	}

	
	public List<Recharge_withdrawal> getRecharge_withdrawal(Integer id) {
		return userMapper.getRecharge_withdrawal(id);
	}

	public List<Transfer> getTransfer(Integer id) {
		return userMapper.getTransfer(id);
	}

	public List<Transfer> getTransferAll(Integer id) {
		return userMapper.getTransferAll(id);
	}

	public List<Recharge_withdrawal> getRecharge_withdrawall(Integer id) {
		return userMapper.getRecharge_withdrawalAll(id);
	}

	public User getUserName(String userName) {
		return userMapper.getUser(userName);
	}
	public Integer getUsersNumber(){
		return userMapper.getUsersNumber();
	}
}
