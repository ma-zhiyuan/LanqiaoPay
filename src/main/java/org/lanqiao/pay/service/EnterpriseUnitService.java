/**   
0* @Title: EnterpriseService.java 
* @Package org.lanqiao.pay.service 
* @Description: TODO
* @author 西安工业大学蓝桥一期--王增   
* @date 2017年4月23日 下午2:46:28    
*/

package org.lanqiao.pay.service;

import org.lanqiao.pay.base.entity.EnterpriseUnit;

/**
 * @author 王增
 *
 */
public interface EnterpriseUnitService {
	/**
	 * 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getEnterpriseUnitById 
	* @Description: TODO 通过id得到EnterpriseUnit
	* @param @param id
	* @param @return    设定文件 
	* @return EnterpriseUnit    返回类型 
	* @date 2017年5月5日 下午5:58:07 
	* @throws
	 */
	public EnterpriseUnit getEnterpriseUnitById(Integer id);
	
	public Integer addEnterpriseUnit(EnterpriseUnit enterpriseUnit);
}
