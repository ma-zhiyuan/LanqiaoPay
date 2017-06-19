package org.lanqiao.pay.mapper;

import org.lanqiao.pay.base.entity.EnterpriseUnit;

/**
 * 
 * @author 王增
 * 操作企业单位信息的mapper接口
 */
public interface EnterpriseUnitMapper {

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getEnterpriseUnitById 
	* @Description: TODO 通过id得到EnterpriseUnit
	* @param @param id
	* @param @return    设定文件 
	* @return EnterpriseUnit    返回类型 
	* @date 2017年5月5日 下午6:00:25 
	* @throws 
	*/
	
	EnterpriseUnit getEnterpriseUnitById(Integer id);
	
	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: addEnterpriseUnit 
	* @Description: TODO 添加企业类型
	* @param @param enterpriseUnit
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @date 2017年5月7日 下午3:15:44 
	* @throws 
	*/
	
	Integer addEnterpriseUnit(EnterpriseUnit enterpriseUnit);
}
