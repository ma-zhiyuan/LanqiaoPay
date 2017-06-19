package org.lanqiao.pay.service;

import org.lanqiao.pay.base.entity.User;
import org.springframework.stereotype.Service;

/**   
* @Title: UserService.java 
* @Package org.lanqiao.pay.service.user 
* @Description: 用户的Service接口 规定用户的方法，具体实现方法体在他的实现类UserServiceImp里
* @author 西安工业大学蓝桥一期--刘江  
* @date 2017年4月23日 上午11:09:24    
*/
@Service
public interface UserService  {
	/**
	 * 
	* @author 西安工业大学蓝桥一期--何璐
	* @Title: isCardId 
	* @Description: TODO 判断身份证号 
	* @param @param cardId
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @date 2017年4月24日 下午8:55:11 
	* @throws
	 */
	public boolean isCardId(Integer id,String cardId);
	/**
	 * 
	* @author 西安工业大学蓝桥一期--何璐
	* @Title: isLoginPwd 
	* @Description: TODO 判断登录密码
	* @param @param loginPassword
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @date 2017年4月24日 下午8:55:43 
	* @throws
	 */
	public boolean isLoginPwd(Integer id,String loginPassword);
	/**
	 * 
	* @author 西安工业大学蓝桥一期--何璐
	* @Title: getUser 
	* @Description: TODO  通过ID获取用户
	* @param @param id
	* @param @return    设定文件 
	* @return User    返回类型 
	* @date 2017年4月25日 下午1:04:59 
	* @throws
	 */
	public User getUser(Integer id);
	
	/**
	 * 	
	* @author 西安工业大学蓝桥一期--何璐
	* @Title: modify 
	* @Description: TODO 修改登陆密码
	* @param @param id
	* @param @param cardId
	* @param @param loginPwd
	* @param @param newLoginPwd
	* @param @return    设定文件 
	* @return int    返回类型 
	* @date 2017年4月24日 下午8:56:25 
	* @throws
	 */
	public int modify(User user,String newLoginPwd);
		/** 
	* @author 西安工业大学蓝桥一期--刘江
	* @Title: updateUser 
	* @Description:添加一个用户
	* @return int   
	* @date 2017年5月2日 下午6:38:04 
	* @throws 
	*/
}
