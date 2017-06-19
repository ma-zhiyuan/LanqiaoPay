/**   
* @Title: EnterpriseUserDao.java 
* @Package org.lanqiao.pay.base.dao 
* @Description: TODO
* @author 西安工业大学蓝桥一期--王增   
* @date 2017年4月23日 下午2:37:36    
*/

package org.lanqiao.pay.base.dao;

import org.lanqiao.pay.base.entity.EnterpriseUnit;
import org.lanqiao.pay.mapper.EnterpriseUnitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author 王增
 */
@Repository
public class EnterpriseUnitDao {
	@Autowired
	EnterpriseUnitMapper enterpriseUnitMapper;

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getEnterpriseUnitById 
	* @Description: TODO 通过id得到EnterpriseUnit
	* @param @param id
	* @param @return    设定文件 
	* @return EnterpriseUnit    返回类型 
	* @date 2017年5月5日 下午5:59:54 
	* @throws 
	*/
	
	public EnterpriseUnit getEnterpriseUnitById(Integer id) {
		// TODO Auto-generated method stub
		return enterpriseUnitMapper.getEnterpriseUnitById(id);
	}

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: addEnterpriseUnit 
	* @Description: TODO 添加企业类型
	* @param @param enterpriseUnit
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @date 2017年5月7日 下午3:15:19 
	* @throws 
	*/
	
	public Integer addEnterpriseUnit(EnterpriseUnit enterpriseUnit) {
		// TODO Auto-generated method stub
		return enterpriseUnitMapper.addEnterpriseUnit(enterpriseUnit);
	}
}
