package org.lanqiao.pay.mapper;

import org.lanqiao.pay.base.entity.Enterprise;


import org.lanqiao.pay.base.entity.LegalRepresentative;

/**
 * 
 * @author 王增
 *	操作企业信息的mapper接口
 */
public interface EnterpriseMapper {

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: getEnterpriseById 
	* @Description: TODO 根据id得到企业信息(级联也包含)
	* @param @param id
	* @param @return    设定文件 
	* @return Enterprise    返回类型 
	* @date 2017年5月5日 下午2:29:52 
	* @throws 
	*/
	
	Enterprise getEnterpriseById(Integer id);

	int verifyEnterpriseNameOnly(String name);

	

	int verifyIDCardOnly(String legalPersonIDCard);



	int verifyPhone(String phoneNum);

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: addEnterprise 
	* @Description: TODO
	* @param @param enterprise
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @date 2017年5月7日 下午3:23:46 
	* @throws 
	*/
	
	Integer addEnterprise(Enterprise enterprise);
	/**
	 * 
	* @author 西安工业大学蓝桥一期--王向宇
	* @Title: getIdByName 
	* @Description: TODO  通过姓名查询企业Id
	* @param @param name
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @date 2017年5月14日 上午9:21:21 
	* @throws
	 */
	Integer getIdByName(String name);

	int verifyName(String name);
	
}
