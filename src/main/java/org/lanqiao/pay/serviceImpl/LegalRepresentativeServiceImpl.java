/**   
* @Title: LegalRepresentativeServiceImpl.java 
* @Package org.lanqiao.pay.serviceImpl 
* @Description: TODO
* @author 西安工业大学蓝桥一期--王增   
* @date 2017年4月23日 下午2:52:27    
*/

package org.lanqiao.pay.serviceImpl;

import org.lanqiao.pay.base.dao.LegalRepresentativeDao;
import org.lanqiao.pay.base.entity.LegalRepresentative;
import org.lanqiao.pay.service.LegalRepresentativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 王增
 *
 */
@Service
public class LegalRepresentativeServiceImpl implements LegalRepresentativeService {
	@Autowired
	LegalRepresentativeDao legalRepresentativeDao;
	/* (non-Javadoc)
	 * @see org.lanqiao.pay.service.LegalRepresentativeService#getLegalRepresentativeById(java.lang.Integer)
	 *通过id得到企业法定人的信息*/
	@Override
	public LegalRepresentative getLegalRepresentativeById(Integer id) {
		// TODO Auto-generated method stub
		return legalRepresentativeDao.getLegalRepresentativeById(id);
	}
	/* (non-Javadoc)
	 * @see org.lanqiao.pay.service.LegalRepresentativeService#addLegalRepresentative(org.lanqiao.pay.base.entity.LegalRepresentative)
	 */
	@Override
	public Integer addLegalRepresentative(LegalRepresentative legalRepresentative) {
		// TODO Auto-generated method stub
		return legalRepresentativeDao.addLegalRepresentative(legalRepresentative);
	}

}
