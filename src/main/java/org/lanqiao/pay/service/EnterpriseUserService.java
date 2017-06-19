/**   
* @Title: EnterpriseService.java 
* @Package org.lanqiao.pay.service 
* @Description: TODO
* @author 西安工业大学蓝桥一期--王增   
* @date 2017年4月23日 下午2:46:28    
*/

package org.lanqiao.pay.service;

import java.util.List;

import org.lanqiao.pay.base.entity.EnterpriseUser;
import org.lanqiao.pay.base.entity.EnterpriseUserPage;

/**
 * @author 王增
 *
 */
public interface EnterpriseUserService {
	/**
	 * 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: selectEnterpriseUserByEmail 
	* @Description: TODO 根据邮箱得到企业级用户
	* @param @param email
	* @param @return    设定文件 
	* @return EnterpriseUser    返回类型 
	* @date 2017年4月25日 下午4:58:39 
	* @throws
	 */
	public EnterpriseUser selectEnterpriseUserByEmail(String email);

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getCountByPage 
	* @Description: TODO 根据分页条件查询记录条数
	* @param @param enterpriseUserPage
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @date 2017年5月3日 上午11:47:01 
	* @throws 
	*/
	
	public Integer getCountByPage(EnterpriseUserPage enterpriseUserPage);

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getAllByPage 
	* @Description: TODO 查询符合条件的enterpriseUser,并连带他们的enterprise(在不级联下去了)
	* @param @param enterpriseUserPage
	* @param @return    设定文件 
	* @return List<EnterpriseUser>    返回类型 
	* @date 2017年5月4日 上午11:02:37 
	* @throws 
	*/
	
	public List<EnterpriseUser> getAllByPage(EnterpriseUserPage enterpriseUserPage);

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getEnterpriseUserDetail 
	* @Description: TODO 根据id查询详细信息(级联)
	* @param @param id
	* @param @return    设定文件 
	* @return EnterpriseUser    返回类型 
	* @date 2017年5月4日 下午8:55:11 
	* @throws 
	*/
	
	public EnterpriseUser getEnterpriseUserDetail(Integer id);

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: approve 
	* @Description: TODO 用户审批通过,即is_forbid设为1
	* @param @param enterpriseUserId
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @date 2017年5月6日 下午1:02:20 
	* @throws 
	*/
	
	public Integer approve(Integer enterpriseUserId);

	/**   
	* @Title: EnterpriseUserService.java 
	* @Package org.lanqiao.pay.service 
	* @Description: 企业用户充值余额 
	* @author 西安工业大学蓝桥一期--刘宣   
	* @date 2017年5月12日 下午6:56:37    
	*/
	
	public int addBalance(EnterpriseUser user);

	/**
	 * 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: addEnterpriseUser 
	* @Description: TODO 插入企业用户
	* @param @param enterpriseUser
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @date 2017年5月7日 下午3:46:36 
	* @throws
	 */
	Integer addEnterpriseUser(EnterpriseUser enterpriseUser);
	
	void finalRegistStep(EnterpriseUser enterpriseUser);
	/**
	 * 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getEnterpriseUserById 
	* @Description: TODO	通过Id得到企业级用户的基本信息
	* @param @param id
	* @param @return    设定文件 
	* @return EnterpriseUser    返回类型 
	* @date 2017年6月6日 下午2:42:47 
	* @throws
	 */
	public EnterpriseUser getEnterpriseUserById(Integer id);
}
