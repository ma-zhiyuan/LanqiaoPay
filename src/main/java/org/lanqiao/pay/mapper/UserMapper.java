package org.lanqiao.pay.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.lanqiao.pay.base.bean.UserCreteria;
import org.lanqiao.pay.base.entity.Recharge_withdrawal;
import org.lanqiao.pay.base.entity.Secret;
import org.lanqiao.pay.base.entity.TimeBean;
import org.lanqiao.pay.base.entity.Transfer;
import org.lanqiao.pay.base.entity.User;

/**   
* @Title: UserMapper.java 
* @Package org.lanqiao.pay.mapper 
* @Description: 个体用户的mapper接口
* @author 西安工业大学蓝桥一期--刘江  
* @date 2017年4月23日 上午11:05:05    
*/
public interface UserMapper {
	
	
	public Recharge_withdrawal getRecharge_withdrawalid(Integer id);
	//所有提现充值记录
	List<Recharge_withdrawal> getRecharge_withdrawalAll(Integer id);
	//所有转账记录
	List<Transfer> getTransferAll(Integer id);
	//提现充值记录
	 List<Recharge_withdrawal> getRecharge_withdrawal(Integer id);
	//得到转账记录
	 List<Transfer> getTransfer(Integer id);
	//用户充值
	int chongZhiin(User u);
	
	Integer getSecretId(Integer id);
	
	
	/**
	* @author 西安工业大学蓝桥一期--陈楚
	* @Title: getUserByName 
	* @Description:通过姓名查询用户信息 
	* @param @param name
	* @param @return
	* @return List<User>
	* @date 2017年4月27日 下午7:30:45 
	* @throws
	 */
	List<User> getUserByName(String name);
	
		User regCardId(User user);
		
		/**
		* @author 西安工业大学蓝桥一期--陈楚
		* @Title: getUserByNameAndPassword 
		* @Description: 通过姓名和密码查询用户信息
		* @param @param name
		* @param @param password
		* @param @return
		* @return User
		* @date 2017年4月27日 下午7:31:03 
		* @throws
		 */
		User getUserByNameAndPassword(@Param("name")String name,@Param("password")String password);
	/**
	 * 
	* @author 西安工业大学蓝桥一期--何璐
	* @Title: getUser  updateUser
	* @Description: TODO  查询用户，更新登陆密码
	* @param @param id
	* @param @return    设定文件 
	* @return User    返回类型 
	* @date 2017年4月24日 下午7:40:44 
	* @throws
	 */
	public void update(User user);
	public User getUser(int id);
	/**   
	* @Title: UserMapper.java 
	* @Package org.lanqiao.pay.mapper 
	* @Description: TODO(用一句话描述该文件做什么) 
	* @author 西安工业大学蓝桥一期--刘宣   
	* @date 2017年4月26日 下午8:21:18    
	*/
	
	public User getUserByUserName(String userName);
	/**
	 * @author 西安工业大学蓝桥一期-代显峰
	 * @title updateUser
	 * @description	TODO 用一句话描述方法的作用
	 * @param @param id
	 * @param @param email
	 * @return void
	 * @date 2017年4月26日 下午5:53:46
	 * @throws
	 */

	 void updateUser(@Param("id")Integer id,@Param("email")String email);

		/** 
	* @author 西安工业大学蓝桥一期--刘江
	* @Title: updateUserByReq 
	* @Description: 注册一个用户
	* @return int   
	* @date 2017年5月2日 下午6:44:51 
	* @throws 
	*/
	public void updateUserByReq(User u);
	/**
	* @author 西安工业大学蓝桥一期--陈楚
	* @Title: getAllUsers 
	* @Description: 查询所有用户信息
	* @param @return
	* @return List<User>
	* @date 2017年5月3日 下午1:34:06 
	* @throws
	 */
		List<User> getAllUsers();
	/**
	 * 
	* @Title: UserMapper.java 
	* @Package org.lanqiao.pay.mapper 
	* @Description: 通过email找查询user表中的密保表id，级联查询 
	* @author 西安工业大学蓝桥一期--董正健   
	* @date 2017年5月3日 下午12:37:14
	 */
	User getUserByEmail(String email);
	/**
	 * 
	* @Title: UserMapper.java 
	* @Package org.lanqiao.pay.mapper 
	* @Description: 通过id获取secret对象  
	* @author 西安工业大学蓝桥一期--董正健   
	* @date 2017年5月3日 下午1:16:45
	 */
	Secret getSecretById(Integer id);
	

	/**
	 * 
	* @author 西安工业大学蓝桥一期--陈楚
	* @Title: getPage 
	* @Description: 分页
	* @param @param userCreteria
	* @param @return
	* @return List<User>
	* @date 2017年5月4日 下午3:57:57 
	* @throws
	 */
	List<User> getPage(UserCreteria userCreteria);

	/**
	 * 
	* @author 西安工业大学蓝桥一期--陈楚
	* @Title: getuserByqualified 
	* @Description:获取有条件的用户数量
	* @param @param keyword
	* @param @return
	* @return int
	* @date 2017年5月5日 下午8:20:10 
	* @throws
	 */
	int getuserByqualified(String keyword);

	/**
	 * 去更新user的余额
	 * @author 孙航建
	 * @time 2017年5月7日 下午9:44:16
	 * @param balance
	 * @param id
	 */
	void updateUserBalanceOne(User u);

	
	/**
	 * 
	* @author 西安工业大学蓝桥一期--王婷
	* @Title: updateUserName 
	* @Description: 更新用户的name
	* @param @param user    设定文件 
	* @return void    返回类型 
	* @date 2017年5月17日 下午9:17:27 
	* @throws
	 */
	void updateUserName(User user);


	/**
	-	 * @author 西安工业大学蓝桥一期--刘江 @Title: getUsersByTime @Description:
	-	 * 通过注册时间查找用户 @return List<User> @date 2017年5月14日 下午4:24:49 @throws
	 	 */
		public Integer getUsersByTime(TimeBean tb);

	/**
	 * 
	* @author 西安工业大学蓝桥一期--王向宇
	* @Title: getUserIdByName 
	* @Description: TODO  通过name查询user的id
	* @param @param name
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @date 2017年5月14日 下午3:36:41 
	* @throws
	 */
	Integer getUserIdByName(String name);
	
	/**
	 * 
	* @author 西安工业大学蓝桥一期--王向宇
	* @Title: getUserIdByEmail 
	* @Description: TODO	通过email查询用户的id
	* @param @param email
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @date 2017年5月14日 下午4:16:13 
	* @throws
	 */
	Integer getUserIdByEmail(String email);
	/**
	 * 
	* @author 西安工业大学蓝桥一期--王婷
	* @Title: updateUserbyEmail 
	* @Description: 通过邮箱更新用户密码
	* @param @param u    设定文件 
	* @return void    返回类型 
	* @date 2017年5月21日 上午11:41:09 
	* @throws
	 */
	void updateUserbyEmail(User u);
	/**
	 * 
	* @author 西安工业大学蓝桥一期--王婷
	* @Title: getUserByEmail1 
	* @Description: 通过邮箱获取用户
	* @param @param email
	* @param @return    设定文件 
	* @return User    返回类型 
	* @date 2017年5月21日 上午11:41:48 
	* @throws
	 */
	 User getUserByEmail1 (String email);

	 /**
		 * 
		* @author 西安工业大学蓝桥一期—陈楚
		* @Title: updateState 
		* @Description: 修改个人状态信息  修改为禁用
		* @param     设定文件 
		* @return void    返回类型 
		* @date 2017年5月21日 下午2:15:07 
		* @throws
		 */
		void updateState(int id);
		
		/**
		 * 
		* @author 西安工业大学蓝桥一期—陈楚
		* @Title: backToState 
		* @Description: 修改个人状态信息  修改为正常
		* @param @param id    设定文件 
		* @return void    返回类型 
		* @date 2017年5月21日 下午2:43:35 
		* @throws
		 */
		void backToState(int id);

		/**
		 * 
		* @author 西安工业大学蓝桥一期—陈楚
		* @Title: deleteState 
		* @Description: 注销用户
		* @param @param id    设定文件 
		* @return void    返回类型 
		* @date 2017年5月22日 下午7:19:03 
		* @throws
		 */
		void deleteState(int id);

		/**
		 * @param id 
		 * @param user 
		 * 
		* @author 西安工业大学蓝桥一期—陈楚
		* @Title: resetPassword 
		* @Description: 重置密码 
		* @param @param rePassword    设定文件 
		* @return void    返回类型 
		* @date 2017年5月24日 下午7:20:33 
		* @throws
		 */
		void resetPassword(@Param("repassword")String rePassword, @Param("id")Integer id );
        // 通过指定的账户获取转入用户的信息
		User getUserByPhone(String personPhone);
        //判断页面转入金额的个人账户在数据库中是否存在
		User isTrueOrFalsePhone(String phone);
		public User getUser(String userName);
		//获取所有用户数据条数
		public Integer getUsersNumber();
}



