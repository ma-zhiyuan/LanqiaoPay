/**   
* @Title: EnterpriseUserDao.java 
* @Package org.lanqiao.pay.base.dao 
* @Description: TODO
* @author 西安工业大学蓝桥一期--王增   
* @date 2017年4月23日 下午2:37:36    
*/

package org.lanqiao.pay.base.dao;

import org.lanqiao.pay.base.entity.Enterprise;
import org.lanqiao.pay.mapper.EnterpriseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author 王增
 *	
 */
@Repository
public class EnterpriseDao {
	@Autowired
	EnterpriseMapper enterpriseMapper;

	public int verifyEnterpriseNameOnly(String name) {
		// TODO Auto-generated method stub
		return enterpriseMapper.verifyEnterpriseNameOnly(name);
	}

	public int verifyIDCardOnly(String legalPersonIDCard) {
		// TODO Auto-generated method stub
		return enterpriseMapper.verifyIDCardOnly(legalPersonIDCard);
	}

	public int verifyPhone(String phoneNum) {
		// TODO Auto-generated method stub
		return enterpriseMapper.verifyPhone(phoneNum);
	}
	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getEnterpriseById  
	* @Description: TODO通过id得到企业(包含级联)
	* @param @param id
	* @param @return    设定文件 
	* @return Enterprise    返回类型 
	* @date 2017年5月5日 下午2:28:44 
	* @throws 
	*/
	
	public Enterprise getEnterpriseById(Integer id) {
		// TODO Auto-generated method stub
		return enterpriseMapper.getEnterpriseById(id);
	}

	public int verifyName(String name) {
		// TODO Auto-generated method stub
		return enterpriseMapper.verifyName(name);
	}

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: addEnterprise 
	* @Description: TODO
	* @param @param enterprise
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @date 2017年5月7日 下午3:23:27 
	* @throws 
	*/
	
	public Integer addEnterprise(Enterprise enterprise) {
		// TODO Auto-generated method stub
		return enterpriseMapper.addEnterprise(enterprise);
	}
	
	/**
	 * 
	* @author 西安工业大学蓝桥一期--王向宇
	* @Title: getIdByName 
	* @Description: TODO  通过姓名查寻企业Id
	* @param @param name
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @date 2017年5月14日 上午9:20:30 
	* @throws
	 */
	public Integer getIdByName(String name){
		return enterpriseMapper.getIdByName(name);
	}
}
