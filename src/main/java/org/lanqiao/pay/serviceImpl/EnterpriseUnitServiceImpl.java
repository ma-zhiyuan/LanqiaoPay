/**   
* @Title: EnterpriseUnitServiceImpl.java 
* @Package org.lanqiao.pay.serviceImpl 
* @Description: TODO
* @author 西安工业大学蓝桥一期--王增   
* @date 2017年4月23日 下午2:51:02    
*/

package org.lanqiao.pay.serviceImpl;

import org.lanqiao.pay.base.dao.EnterpriseUnitDao;
import org.lanqiao.pay.base.entity.EnterpriseUnit;
import org.lanqiao.pay.service.EnterpriseUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 王增
 *
 */
@Service
public class EnterpriseUnitServiceImpl implements EnterpriseUnitService {
	@Autowired
	EnterpriseUnitDao enterpriseUnitDao;
	/* (non-Javadoc)
	 * @see org.lanqiao.pay.service.EnterpriseUnitService#getEnterpriseUnitById(java.lang.Integer)
	 */
	@Override
	public EnterpriseUnit getEnterpriseUnitById(Integer id) {
		// TODO Auto-generated method stub
		return enterpriseUnitDao.getEnterpriseUnitById(id);
	}
	/* (non-Javadoc)
	 * @see org.lanqiao.pay.service.EnterpriseUnitService#addEnterpriseUnit(org.lanqiao.pay.base.entity.EnterpriseUnit)
	 */
	@Override
	public Integer addEnterpriseUnit(EnterpriseUnit enterpriseUnit) {
		// TODO Auto-generated method stub
		return enterpriseUnitDao.addEnterpriseUnit(enterpriseUnit);
	}

}
