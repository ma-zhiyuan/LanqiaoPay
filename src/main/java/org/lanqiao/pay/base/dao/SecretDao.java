/**   
* @Title: EnterpriseUserDao.java 
* @Package org.lanqiao.pay.base.dao 
* @Description: TODO
* @author 西安工业大学蓝桥一期--王增   
* @date 2017年4月23日 下午2:37:36    
*/

package org.lanqiao.pay.base.dao;
import org.lanqiao.pay.base.entity.Secret;
import org.lanqiao.pay.mapper.SecretMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author 王增
 */
@Repository
public class SecretDao {
	@Autowired
	SecretMapper secretMapper;
	
	public Secret getSecretZM(Integer id2) {
		return secretMapper.getSecretZM(id2);
	}
	
	/**
	 * @author 西安工业大学蓝桥一期--毋泽航
	 * @Title: getSecret 
	 * @Description: 根据id去查对象 
	 * @param  id
	 * @return Secret    
	 * @date 2017年4月24日 下午5:51:56 
	 * @throws
	 */
	public Secret getSecret(int id){
		Secret secret = secretMapper.getSecret(id);
		return secret;
	}
	/**
	 * @author 西安工业大学蓝桥一期--毋泽航
	 * @Title: updateSecret 
	 * @Description: 修改对象 
	 * @date 2017年4月24日 下午5:53:06 
	 * @throws
	 */
	public void updateSecret(Secret secret){
		secretMapper.updateSecret(secret);
	}
	/** 
	* @author 西安工业大学蓝桥一期--刘江
	* @Title: addByReq 
	* @Description: 注册讲密保插入数据库
	* @return void   
	* @date 2017年5月2日 下午7:20:35 
	* @throws 
	*/
	public void addByReq(Secret secret) {
		secretMapper.addByReq(secret);
	}
	
	
}
