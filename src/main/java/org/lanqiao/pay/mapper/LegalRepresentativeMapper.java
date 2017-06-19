package org.lanqiao.pay.mapper;

import org.lanqiao.pay.base.entity.LegalRepresentative;

/**
 * 
 * @author 王增
 *	操作企业级法定代表人的mapper接口
 */
public interface LegalRepresentativeMapper {

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getLegalRepresentativeById 
	* @Description: TODO 通过id得到企业法定人的信息
	* @param @param id
	* @param @return    设定文件 
	* @return LegalRepresentative    返回类型 
	* @date 2017年5月5日 下午6:04:31 
	* @throws 
	*/
	
	LegalRepresentative getLegalRepresentativeById(Integer id);

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: addLegalRepresentative 
	* @Description: TODO 插入企业法人
	* @param @param legalRepresentative
	* @param @return    设定文件 
	* @return LegalRepresentative    返回类型 
	* @date 2017年5月7日 下午2:56:50 
	* @throws 
	*/
	
	Integer addLegalRepresentative(LegalRepresentative legalRepresentative);

}
