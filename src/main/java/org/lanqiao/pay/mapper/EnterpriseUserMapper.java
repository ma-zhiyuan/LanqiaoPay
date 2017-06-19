package org.lanqiao.pay.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.lanqiao.pay.base.bean.EnterpriseUserCreteria;
import org.lanqiao.pay.base.entity.EnterpriseUser;
import org.lanqiao.pay.base.entity.EnterpriseUserPage;
import org.lanqiao.pay.base.entity.TimeBean;

/**
 * 
 * @author 王增
 *	操作企业级用户信息的mapper接口
 */
public interface EnterpriseUserMapper {

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: selectEnterpriseUserByEmail 
	* @Description: TODO 根据邮箱得到企业级用户
	* @param @param email
	* @param @return    设定文件 
	* @return EnterpriseUser    
	* @date 2017年4月25日 下午4:57:34 
	* @throws 
	*/
	EnterpriseUser selectEnterpriseUserByEmail(String email);
	
	/**
	 * 通过email获取企业法人的密保表的id(这里涉及到级联查询)
	 * @author 孙航建
	 * @time 2017年4月29日 下午7:08:57
	 * @param email
	 * @return
	 */
	EnterpriseUser getEnterpriseUserByEmail(String email);

	/**
	 * 通过id获取EnterpriseUser对象值
	 * @author 孙航建
	 * @time 2017年4月30日 上午12:04:56
	 * @param id
	 * @return
	 */
	EnterpriseUser getEnterpriseUserById(int id);
	/**
	 * 
	 * @author 西安工业大学蓝桥一期-代显峰
	 * @title getEmail
	 * @description	用密保卡的Id获取用户的邮箱
	 * @param @param id
	 * @param @return
	 * @return String
	 * @date 2017年5月3日 下午7:19:00
	 * @throws
	 */
	String getEmail(Integer id);
	/**
	 * 	根据密保Id修改企业法定人的邮箱
	 * @author 西安工业大学蓝桥一期-代显峰
	 * @title updateEmail
	 * @description	TODO 用一句话描述方法的作用
	 * @param @param id
	 * @param @param email
	 * @return void
	 * @date 2017年5月3日 下午7:25:12
	 * @throws
	 */
	void updateEmail(@Param("id")Integer id,@Param("email")String email);

    /**
     * 
    * @author 西安工业大学蓝桥一期--何璐
    * @Title: update 
    * @Description: TODO 更新企业用户 
    * @param @param enterpriseUser    设定文件 
    * @return void    返回类型 
    * @date 2017年5月3日 下午2:42:16 
    * @throws
     */
	void update(EnterpriseUser enterpriseUser);

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getCountByPage 
	* @Description: TODO 根据分页条件查询分页条数
	* @param @param enterpriseUserPage
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @date 2017年5月3日 上午11:57:21 
	* @throws 
	*/
	
	Integer getCountByPage(EnterpriseUserPage enterpriseUserPage);

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getAllByPage 
	* @Description: TODO查询符合条件的enterpriseUser,并连带他们的enterprise(在不级联下去了)
	* @param @param enterpriseUserPage
	* @param @return    设定文件 
	* @return List<EnterpriseUser>    返回类型 
	* @date 2017年5月4日 上午11:04:40 
	* @throws 
	*/
	
	List<EnterpriseUser> getAllByPage(EnterpriseUserPage enterpriseUserPage);

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getEnterpriseUserDetail 
	* @Description: TODO
	* @param @param id
	* @param @return    设定文件 
	* @return EnterpriseUser    返回类型 
	* @date 2017年5月4日 下午9:05:02 
	* @throws 
	*/
	
	EnterpriseUser getEnterpriseUserDetail(Integer id);

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: updateEnetrpriseUserToNotForbid 
	* @Description: TODO 更新企业级用户的Is_forbid为1
	* @param @param enterpriseUserId
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @date 2017年5月6日 下午1:04:37 
	* @throws 
	*/
	
	Integer updateEnetrpriseUserToNotForbid(Integer enterpriseUserId);

	/**   
	* @Title: EnterpriseUserMapper.java 
	* @Package org.lanqiao.pay.mapper 
	* @Description: 企业用户充值 
	* @author 西安工业大学蓝桥一期--刘宣   
	* @date 2017年5月12日 下午7:03:05    
	*/
	
	int addEnterpriseUserBalance(EnterpriseUser user);

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: addEnterpriseUser 
	* @Description: TODO
	* @param @param enterpriseUser
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @date 2017年5月7日 下午3:45:46 
	* @throws 
	*/
	
	Integer addEnterpriseUser(EnterpriseUser enterpriseUser);

	int verifyName(String name);

	String verifyUser(String name);

	EnterpriseUser getUserByNameAndPassword(String name, String password);
	/**
	 * 
	* @author 西安工业大学蓝桥一期—陈楚
	* @Title: getuserByqualified 
	* @Description: 查找带有关键字的企业用户数量
	* @param @param keyword
	* @param @return    设定文件 
	* @return int    返回类型 
	* @date 2017年5月12日 下午8:52:52 
	* @throws
	 */
	int getuserByqualified(String keyword);
	
	/**
	 * 
	* @author 西安工业大学蓝桥一期—陈楚
	* @Title: getPage 
	* @Description:获取分页的信息
	* @param @param userCreteria
	* @param @return    设定文件 
	* @return List<EnterpriseUser>    返回类型 
	* @date 2017年5月12日 下午8:58:53 
	* @throws
	 */
	List<EnterpriseUser> getPage(EnterpriseUserCreteria enterpriceUserCreteria);

	
	/**
	 * 
	* @author 西安工业大学蓝桥一期--王向宇
	* @Title: getEnterpriseIdByEmail 
	* @Description: TODO	通过企业用户的邮箱获取一个企业的id
	* @param @param email
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @date 2017年5月14日 下午4:44:21 
	* @throws
	 */
	Integer getEnterpriseIdByEmail(String email);
    // 通过enterpriseId获取法定代表人的balance
	EnterpriseUser getEnterpriseByenterpriseId(Integer id);
    // 企业提现更新法定人的余额
	void updateEnterUserBalance(EnterpriseUser en);

	/**
	 * 
	* @author 西安工业大学蓝桥一期—陈楚
	* @Title: updateState 
	* @Description: 修改企业用户信息 禁用 
	* @param @param id    设定文件 
	* @return void    返回类型 
	* @date 2017年5月21日 下午3:49:11 
	* @throws
	 */
	void updateState(int i);

	/**
	 * 
	* @author 西安工业大学蓝桥一期—陈楚
	* @Title: backToNormal 
	* @Description: 修改企业用户信息 恢复 
	* @param @param id    设定文件 
	* @return void    返回类型 
	* @date 2017年5月21日 下午3:49:45 
	* @throws
	 */
	void backToNormal(int i);

	/**
	 * 
	* @author 西安工业大学蓝桥一期—陈楚
	* @Title: deleteState 
	* @Description: 注销用户 
	* @param @param id    设定文件 
	* @return void    返回类型 
	* @date 2017年5月22日 下午8:04:47 
	* @throws
	 */
	void deleteState(int id);

	/**
	 * 
	* @author 西安工业大学蓝桥一期—陈楚
	* @Title: rePassword 
	* @Description: 重置密码
	* @param @param id
	* @param @param rePassword    设定文件 
	* @return void    返回类型 
	* @date 2017年5月24日 下午8:47:48 
	* @throws
	 */
	void rePassword(@Param("id")int id, @Param("rePassword")String rePassword);
    /**
     * 
    * @author 西安工业大学蓝桥一期--何璐
    * @Title: getEnterpriseUser 
    * @Description: TODO  通过手机号获取企业法定人
    * @param @param epUserPhone    设定文件 
    * @return void    返回类型 
    * @date 2017年6月4日 下午2:27:45 
    * @throws
     */
	EnterpriseUser getEnterpriseUserByPhone(String epUserPhone);
	/** 
* @author 西安工业大学蓝桥一期--刘江
* @Title: getEntNumbersByReTime 
* @Description:通过注册时间获取相对应的注册企业数
* @return Integer   
* @date 2017年5月26日 下午5:19:36 
* @throws 
*/
Integer getEntNumbersByReTime(TimeBean tb);
	/**
	 * @author 西安工业大学蓝桥一期--毋泽航
	 * @Title: getEnterpriseUserByEPID 
	 * @Description: 通过企业id去获取法定人 
	 */
	EnterpriseUser getEnterpriseUserByEPID(Integer id);

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getEnterpriseUserByEnterpriseId 
	* @Description: TODO
	* @param @param id
	* @param @return    设定文件 
	* @return EnterpriseUser    返回类型 
	* @date 2017年6月11日 下午12:00:07 
	* @throws 
	*/
	
	EnterpriseUser getEnterpriseUserByEnterpriseId(Integer id);

	int updateEnterPriseUserPhoneById(Map<String,Object> map);


	/**
	 * 根据企业id修改企业用户头像--王向宇
	 * @param eu
	 * @return
	 */
	void modifyPhotoById(EnterpriseUser eu);

	//获取所有企业数据条数
			public Integer getEntNumber();
}
