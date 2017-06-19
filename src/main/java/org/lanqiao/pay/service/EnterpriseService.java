/**   
* @Title: EnterpriseService.java 
* @Package org.lanqiao.pay.service 
* @Description: TODO
* @author 西安工业大学蓝桥一期--王增   
* @date 2017年4月23日 下午2:46:28    
*/

package org.lanqiao.pay.service;

import org.lanqiao.pay.base.entity.Enterprise;

/**
 * @author 王增
 *
 */
public interface EnterpriseService {
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
	public Enterprise getEnterpriseById(Integer id);
	Integer addEnterprise(Enterprise enterprise);
}
