/**   
* @Title: EnterpriseService.java 
* @Package org.lanqiao.pay.service 
* @Description: TODO
* @author 西安工业大学蓝桥一期--王增   
* @date 2017年4月23日 下午2:46:28    
*/

package org.lanqiao.pay.service;

import org.lanqiao.pay.base.entity.LegalRepresentative;

/**
 * @author 王增
 *
 */
public interface LegalRepresentativeService {
	/**
	 * 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getLegalRepresentativeById 
	* @Description: TODO 通过id得到企业法定人的信息
	* @param @param id
	* @param @return    设定文件 
	* @return LegalRepresentative    返回类型 
	* @date 2017年5月5日 下午6:01:53 
	* @throws
	 */
	public LegalRepresentative getLegalRepresentativeById(Integer id);
	
	public Integer addLegalRepresentative(LegalRepresentative legalRepresentative);
}
