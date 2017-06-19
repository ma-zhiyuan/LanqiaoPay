/**   
* @Title: EnterpriseUserDao.java 
* @Package org.lanqiao.pay.base.dao 
* @Description: TODO
* @author 西安工业大学蓝桥一期--王增   
* @date 2017年4月23日 下午2:37:36    
*/

package org.lanqiao.pay.base.dao;

import org.lanqiao.pay.base.entity.LegalRepresentative;
import org.lanqiao.pay.mapper.LegalRepresentativeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author 王增
 *	法定代表人的Dao
 */
@Repository
public class LegalRepresentativeDao {
	@Autowired
	LegalRepresentativeMapper legalRepresentativeMapper;

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getLegalRepresentativeById 
	* @Description: TODO通过id得到企业法定人的信息
	* @param @param id
	* @param @return    设定文件 
	* @return LegalRepresentative    返回类型 
	* @date 2017年5月5日 下午6:04:09 
	* @throws 
	*/
	
	public LegalRepresentative getLegalRepresentativeById(Integer id) {
		// TODO Auto-generated method stub
		return legalRepresentativeMapper.getLegalRepresentativeById(id);
	}

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: addLegalRepresentative 
	* @Description: TODO 插入企业法人
	* @param @param legalRepresentative
	* @param @return    设定文件 
	* @return LegalRepresentative    返回类型 
	* @date 2017年5月7日 下午2:56:24 
	* @throws 
	*/
	
	public Integer addLegalRepresentative(LegalRepresentative legalRepresentative) {
		// TODO Auto-generated method stub
		return legalRepresentativeMapper.addLegalRepresentative(legalRepresentative);
	}
}
