/**   
* @Title: EnterpriseService.java 
* @Package org.lanqiao.pay.service 
* @Description: TODO
* @author 西安工业大学蓝桥一期--王增   
* @date 2017年4月23日 下午2:46:28    
*/

package org.lanqiao.pay.service;

import org.lanqiao.pay.base.entity.Secret;
import org.springframework.stereotype.Service;

/**
 * @author 王增
 *
 */
@Service
public interface SecretService {
	
	/**
	 * @author 西安工业大学蓝桥一期--毋泽航
	 * @Title: isPayPwd 
	 * @Description: 判断支付密码是否正确 
	 * @param  id
	 * @param  payPassword
	 * @return boolean    
	 * @date 2017年4月24日 下午6:39:37 
	 * @throws
	 */
	public boolean isPayPwd(int id, String payPassword);
	/**
	 * 
	 * @author 西安工业大学蓝桥一期--毋泽航
	 * @Title: updatePayPwd 
	 * @Description: 修改支付密码
	 * @param  id
	 * @param  payPassword
	 * @date 2017年4月24日 下午7:13:15 
	 * @throws
	 */
	public void updatePayPwd(int id, String payPassword);
	
	/**
	 * 
	 * @author 西安工业大学蓝桥一期--孙航建
	 * @Title: updatePaySecret 
	 * @Description: 修改密保问题及答案
	 * @param  secret
	 * @param @return    
	 * @return int   1表示修改成功,0表示修改失败 
	 * @date 2017年4月24日 下午7:13:15 
	 * @throws
	 */
	public int updatePaySecret(Secret secret);
}
