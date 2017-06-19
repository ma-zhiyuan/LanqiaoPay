/**   
* @Title: EnterpriseServiceImp.java 
* @Package org.lanqiao.pay.serviceImp 
* @Description: TODO
* @author 西安工业大学蓝桥一期--王增   
* @date 2017年4月23日 下午2:49:43    
*/

package org.lanqiao.pay.serviceImpl;

import org.lanqiao.pay.base.dao.EnterpriseDao;
import org.lanqiao.pay.base.entity.Enterprise;
import org.lanqiao.pay.base.dao.EnterpriseDao;
import org.lanqiao.pay.base.entity.LegalRepresentative;
import org.lanqiao.pay.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 王增
 *
 */
@Service
public class EnterpriseServiceImpl implements EnterpriseService {
	@Autowired
	EnterpriseDao enterpriseDao;
	public int verifyEnterpriseNameOnly(String name) {
		// TODO Auto-generated method stub
		return enterpriseDao.verifyEnterpriseNameOnly(name) ;
	}

	public int verifyIDCardOnly(String legalPersonIDCard) {
		// TODO Auto-generated method stub
		return enterpriseDao.verifyIDCardOnly(legalPersonIDCard);
	}

	public int verifyPhone(String phoneNum) {
		// TODO Auto-generated method stub
		return enterpriseDao.verifyPhone(phoneNum);
	}
	

	/* (non-Javadoc)
	 * @see org.lanqiao.pay.service.EnterpriseService#getEnterpriseById(java.lang.Integer)
	 */
	@Override
	public Enterprise getEnterpriseById(Integer id) {
		// TODO Auto-generated method stub
		return enterpriseDao.getEnterpriseById(id);
	}

	public int verifyName(String name) {
		// TODO Auto-generated method stub
		return enterpriseDao.verifyName(name);
	}

	/** 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: add 
	* @Description: TODO 插入企业
	* @param @param enterprise    设定文件 
	* @return void    返回类型 
	* @date 2017年5月7日 下午3:22:25 
	* @throws 
	*/
	
	public Integer addEnterprise(Enterprise enterprise) {
		// TODO Auto-generated method stub
		return enterpriseDao.addEnterprise(enterprise);
	}
	
	/**
	 * 
	* @author 西安工业大学蓝桥一期--王向宇
	* @Title: getIdByName 
	* @Description: TODO  通过姓名查enterpriseId
	* @param @param name
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @date 2017年5月14日 上午9:19:31 
	* @throws
	 */
	public Integer getIdByName(String name){
		return enterpriseDao.getIdByName(name);
	}
	
	/**
	 * @author 西安工业大学蓝桥一期--毋泽航
	 * @Title: getEnterpriseByEpName
	 * @Description: 通过企业名字获取企业 
	 * @param @param businessName
	 * @return Enterprise    
	 * @date 2017年6月8日 上午10:49:01 
	 * @throws
	 */
	public Enterprise getEnterpriseByEpName(String businessName) {
		Integer enterpriseID = enterpriseDao.getIdByName(businessName);
		Enterprise enterprise = enterpriseDao.getEnterpriseById(enterpriseID);
		return enterprise;
	}

	
}
