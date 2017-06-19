/**   
* @Title: EnterpriseUserDao.java 
* @Package org.lanqiao.pay.base.dao 
* @Description: TODO
* @author 西安工业大学蓝桥一期--王增   
* @date 2017年4月23日 下午2:37:36    
*/

package org.lanqiao.pay.base.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lanqiao.pay.base.bean.EnterpriseUserCreteria;
import org.lanqiao.pay.base.entity.EnterpriseUser;
import org.lanqiao.pay.base.entity.EnterpriseUserPage;
import org.lanqiao.pay.base.entity.TimeBean;
import org.lanqiao.pay.mapper.EnterpriseUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author 王增
 *	
 */
@Repository
public class EnterpriseUserDao {
	@Autowired
	EnterpriseUserMapper enterpriseUserMapper;
	
	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: selectEnterpriseUserByEmail 
	* @Description: TODO 根据邮箱得到企业级用户
	* @param @param email
	* @param @return    设定文件 
	* @return EnterpriseUser    返回类型 
	* @date 2017年4月25日 下午4:57:09 
	* @throws 
	*/
	
	public EnterpriseUser selectEnterpriseUserByEmail(String email) {
		// TODO Auto-generated method stub
		return enterpriseUserMapper.selectEnterpriseUserByEmail(email);
	}

	/**
	 * 通过email获取企业法人的密保表的id(这里涉及到级联查询)
	 * @author 孙航建
	 * @time 2017年4月29日 下午7:10:43
	 * @param email
	 * @return
	 */
	public EnterpriseUser getEnterpriseUserByEmail(String email) {
		return enterpriseUserMapper.getEnterpriseUserByEmail(email);
	}

	/**
	 * 通过id获取EnterpriseUser对象判断email是否一致
	 * @author 孙航建
	 * @time 2017年4月30日 上午12:03:16
	 * @param id
	 */
	public EnterpriseUser getEnterpriseUserById(int id) {
		return enterpriseUserMapper.getEnterpriseUserById(id);
	}
		/**
	 * 
	 * @author 西安工业大学蓝桥一期-代显峰
	 * @title getEmail
	 * @description	用密保卡的Id查询用户的email
	 * @param @param id
	 * @param @return
	 * @return String
	 * @date 2017年5月3日 下午7:09:38
	 * @throws
	 */
	public String getEmail(Integer id) {
		return enterpriseUserMapper.getEmail(id);
	}
	/**
	 * 
	 * @author 西安工业大学蓝桥一期-代显峰
	 * @title update
	 * @description	根据密保Id修改企业法定人的邮箱
	 * @param @param id
	 * @param @param email
	 * @return void
	 * @date 2017年5月3日 下午7:24:16
	 * @throws
	 */
	public void update(Integer id, String email) {
		enterpriseUserMapper.updateEmail(id,email);
	}
	/**
	 * 
	* @author 西安工业大学蓝桥一期--何璐
	* @Title: update 
	* @Description: TODO 更新企业用户 
	* @param @param enterpriseUser    设定文件 
	* @return void    返回类型 
	* @date 2017年5月3日 下午2:43:46 
	* @throws
	 */
	public void update(EnterpriseUser enterpriseUser){
		enterpriseUserMapper.update(enterpriseUser);
	}

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getCountByPage 
	* @Description: TODO	根据分页条件查询记录条数
	* @param @param enterpriseUserPage
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @date 2017年5月3日 上午11:56:37 
	* @throws 
	*/
	
	public Integer getCountByPage(EnterpriseUserPage enterpriseUserPage) {
		// TODO Auto-generated method stub
		return enterpriseUserMapper.getCountByPage(enterpriseUserPage);
	}

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getAllByPage 
	* @Description: TODO查询符合条件的enterpriseUser,并连带他们的enterprise(在不级联下去了)
	* @param @param enterpriseUserPage
	* @param @return    设定文件 
	* @return List<EnterpriseUser>    返回类型 
	* @date 2017年5月4日 上午11:04:06 
	* @throws 
	*/
	
	public List<EnterpriseUser> getAllByPage(EnterpriseUserPage enterpriseUserPage) {
		// TODO Auto-generated method stub
		return enterpriseUserMapper.getAllByPage(enterpriseUserPage);
	}

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getEnterpriseUserDetail 
	* @Description: TODO 根据id查询详细信息(级联)
	* @param @param id
	* @param @return    设定文件 
	* @return EnterpriseUser    返回类型 
	* @date 2017年5月4日 下午9:01:24 
	* @throws 
	*/
	public EnterpriseUser getEnterpriseUserDetail(Integer id) {
		// TODO Auto-generated method stub
		System.out.println("ampp");
		return enterpriseUserMapper.getEnterpriseUserDetail(id);
	}

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: approve 
	* @Description: TODO用户审批通过,即is_forbid设为1
	* @param @param enterpriseUserId 
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @date 2017年5月6日 下午1:03:19 
	* @throws 
	*/
	
	public Integer approve(Integer enterpriseUserId) {
		// TODO Auto-generated method stub
		return enterpriseUserMapper.updateEnetrpriseUserToNotForbid(enterpriseUserId);
	}

	/**   
	* @Title: EnterpriseUserDao.java 
	* @Package org.lanqiao.pay.base.dao 
	* @Description: 企业用户余额充值
	* @author 西安工业大学蓝桥一期--刘宣   
	* @date 2017年5月12日 下午6:58:20    
	*/
	
	public int addBalance(EnterpriseUser user) {
		return enterpriseUserMapper.addEnterpriseUserBalance(user);
	}

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: addEnterpriseUser 
	* @Description: TODO 插入企业用户
	* @param @param enterpriseUser    设定文件 
	* @return void    返回类型 
	* @date 2017年5月7日 下午3:44:12 
	* @throws 
	*/
	
	public Integer addEnterpriseUser(EnterpriseUser enterpriseUser) {
		// TODO Auto-generated method stub
		return enterpriseUserMapper.addEnterpriseUser(enterpriseUser);
	}

	public int verifyName(String name) {
		// TODO Auto-generated method stub
		return enterpriseUserMapper.verifyName(name);
	}

	public String verifyUser(String name) {
		// TODO Auto-generated method stub
		return enterpriseUserMapper.verifyUser(name);
	}

	public EnterpriseUser getUserByNameAndPassword(String name, String password) {
		// TODO Auto-generated method stub
		return enterpriseUserMapper.getUserByNameAndPassword(name,password);
	}
	
	/**
	 * 
	* @author 西安工业大学蓝桥一期--王向宇
	* @Title: getEnterpriseIdByEmail 
	* @Description: TODO  	通过企业用户的邮箱获取一个企业的id
	* @param @param email
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @date 2017年5月14日 下午4:42:08 
	* @throws
	 */
	public Integer getEnterpriseIdByEmail(String email) {
		return enterpriseUserMapper.getEnterpriseIdByEmail(email);
	}

	public int getuserByqualified(String keyword) {
		int i = enterpriseUserMapper.getuserByqualified(keyword);
		return i;
	}

	/**
	 * 
	* @author 西安工业大学蓝桥一期—陈楚
	* @Title: getPage 
	* @Description: 获取分页的信息
	* @param @param userCreteria
	* @param @return    设定文件 
	* @return List<EnterpriseUser>    返回类型 
	* @date 2017年5月12日 下午8:58:33 
	* @throws
	 */
	public List<EnterpriseUser> getPage(EnterpriseUserCreteria enterpriceUserCreteria) {
		List<EnterpriseUser> page = enterpriseUserMapper.getPage(enterpriceUserCreteria);
		return page;
	}
    
	/**
	 * 通过enterpriseId获取法定代表人的balance
	 * @author 孙航建
	 * @time 2017年5月19日 下午6:18:40
	 * @param id
	 * @return
	 */
	public EnterpriseUser getEnterpriseByenterpriseId(Integer id) {
		return enterpriseUserMapper.getEnterpriseByenterpriseId(id);
	}
	
    /**
     * 企业提现更新法定人的余额
     * @author 孙航建
     * @time 2017年5月19日 下午6:59:46
     * @param en
     */
	public void updateEnterUserBalance(EnterpriseUser en) {
		enterpriseUserMapper.updateEnterUserBalance(en);
	}

	/**
	 * 
	* @author 西安工业大学蓝桥一期—陈楚
	* @Title: updateState 
	* @Description: 修改企业用户状态信息 禁用
	* @param @param id    设定文件 
	* @return void    返回类型 
	* @date 2017年5月21日 下午3:47:01 
	* @throws
	 */
	public void updateState(int i) {
		enterpriseUserMapper.updateState(i);
		
	}

	/**
	 * 
	* @author 西安工业大学蓝桥一期—陈楚
	* @Title: backToNormal 
	* @Description: 修改企业用户状态信息 恢复
	* @param @param id    设定文件 
	* @return void    返回类型 
	* @date 2017年5月21日 下午3:47:49 
	* @throws
	 */
	public void backToNormal(int i) {
		enterpriseUserMapper.backToNormal(i);
	}

	/**
	 * 
	* @author 西安工业大学蓝桥一期—陈楚
	* @Title: deleteState 
	* @Description: 注销用户 
	* @param @param parseInt    设定文件 
	* @return void    返回类型 
	* @date 2017年5月22日 下午8:04:03 
	* @throws
	 */
	public void deleteState(int id) {
		enterpriseUserMapper.deleteState(id);
	}

	/**
	 * 
	* @author 西安工业大学蓝桥一期—陈楚
	* @Title: rePassword 
	* @Description: 重置密码
	* @param @param id
	* @param @param rePassword    设定文件 
	* @return void    返回类型 
	* @date 2017年5月24日 下午8:46:54 
	* @throws
	 */
	public void rePassword(int id, String rePassword) {
		enterpriseUserMapper.rePassword(id,rePassword);
		
	}
	 /**
     * 
    * @author 西安工业大学蓝桥一期--何璐
    * @Title: getEnterpriseUser 
    * @Description: TODO 通过手机号获取企业法定人
    * @param @param epUserPhone    设定文件 
    * @return void    返回类型 
    * @date 2017年6月4日 下午2:28:51 
    * @throws
     */
	public EnterpriseUser getEnterpriseUserByPhone(String epUserPhone) {
		return enterpriseUserMapper.getEnterpriseUserByPhone(epUserPhone);
	}
		/** 
	* @author 西安工业大学蓝桥一期--刘江
	* @Title: getEntNumbersByReTime 
	* @Description: TODO
	* @return Integer   
	* @date 2017年6月18日 上午10:36:22 
	* @throws 
	*/
	public Integer getEntNumbersByReTime(TimeBean tb){
	return	enterpriseUserMapper.getEntNumbersByReTime(tb);
	}
		/** 
	* @author 西安工业大学蓝桥一期--刘江
	* @Title: getEntNumber 
	* @Description: TODO
	* @return Integer   
	* @date 2017年6月18日 上午10:37:41 
	* @throws 
	*/
	public Integer getEntNumber(){
		return enterpriseUserMapper.getEntNumber();
		}
	/**
	 * @return 
	 * @author 西安工业大学蓝桥一期--毋泽航
	 * @Title: getEnterpriseUserByEPID 
	 * @Description: 通过企业id去获取法定人 
	 * @throws
	 */
	public EnterpriseUser getEnterpriseUserByEPID(Integer id) {
		return enterpriseUserMapper.getEnterpriseUserByEPID(id);
	}


	/**
	 * 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getEnterpriseUserByEnterpriseId 
	* @Description: TODO
	* @param @param id
	* @param @return    设定文件 
	* @return EnterpriseUser    返回类型 
	* @date 2017年6月11日 上午11:58:43 
	* @throws
	 */
	public EnterpriseUser getEnterpriseUserByEnterpriseId(Integer id) {
		// TODO Auto-generated method stub
		return enterpriseUserMapper.getEnterpriseUserByEnterpriseId(id);
	}

	public void update() {
		// TODO Auto-generated method stub
		
	}

	public int updatePhone(Integer id, String linkManPhone) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("tell", linkManPhone);
		map.put("id", id);
		return enterpriseUserMapper.updateEnterPriseUserPhoneById(map);
		
	}



	
	/**
	 * 根据企业id修改企业用户头像--王向宇
	 * @param eu
	 */
	public void modifyPhotoById(EnterpriseUser eu) {
		enterpriseUserMapper.modifyPhotoById(eu);
		
	}
}
