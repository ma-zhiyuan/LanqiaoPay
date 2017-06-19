/**   
* @Title: SecretServiceImpl.java 
* @Package org.lanqiao.pay.serviceImpl 
* @Description: TODO
* @author 西安工业大学蓝桥一期--王增   
* @date 2017年4月23日 下午2:52:49    
*/

package org.lanqiao.pay.serviceImpl;

import org.lanqiao.pay.base.dao.SecretDao;
import org.lanqiao.pay.base.dao.UserDao;
import org.lanqiao.pay.base.entity.Secret;
import org.lanqiao.pay.service.SecretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.lanqiao.pay.base.util.MyUtils;
/**
 * @author 王增
 *
 */
@Service
public class SecretServiceImpl implements SecretService{
	
	@Autowired
	SecretDao secretDao;
	@Autowired
	UserDao userDao;
	
	
	public Secret getSecretZM(Integer id){
		Integer id2 = userDao.getSecretId(id);
		return secretDao.getSecretZM(id2);
	}
	
	
	/**
	 * @author 西安工业大学蓝桥一期--毋泽航
	 * @Title: isPayPwd 
	 * @Description: 判断支付密码是否正确 
	 * @param  id
	 * @param  payPassword
	 * @return boolean    
	 * @date 2017年4月24日 下午7:02:10 
	 * @throws
	 */
	@Override
	public boolean isPayPwd(int id,String payPassword) {
		Secret secret = secretDao.getSecret(id);
		String payPassword2 = secret.getPayPassword();
		String md5Pwd = MyUtils.md5(payPassword);
		if(md5Pwd.equals(payPassword2)){
			return true;
		}else{
			return false;
		}
	}
	public boolean isSecurity(Integer id, String classifiedAnswer) {
		Secret secret = secretDao.getSecret(id);
		String classifiedAnswer2 = secret.getClassifiedAnswer();
		if(classifiedAnswer.equals(classifiedAnswer2)){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * @author 西安工业大学蓝桥一期--毋泽航
	 * @Title: updatePayPwd 
	 * @Description: 修改支付密码
	 * @param  id
	 * @param  payPassword
	 * @date 2017年4月24日 下午7:18:25 
	 * @throws
	 */
	@Override
	public void updatePayPwd(int id, String payPassword) {
		Secret secret = secretDao.getSecret(id);
		String md5Pwd = MyUtils.md5(payPassword);
		secret.setPayPassword(md5Pwd);
		secretDao.updateSecret(secret);
	}
	
	/**
	 * @author 西安工业大学蓝桥一期--孙航建
	 * @Title: updatePayPwd 
	 * @Description: 判断支付密码是否正确并且去数据库中更新 
	 * @param  secret 页面传进来的数据
	 * @return int  1表示修改成功,0表示修改失败 
	 * @date 2017年4月24日 下午7:25:25 
	 * @throws
	 */
	@Override
	public int updatePaySecret(Secret secret) {
		boolean boo = isPayPwd(secret.getId(), secret.getPayPassword());
		if(boo){
			String md5Pwd = MyUtils.md5(secret.getPayPassword());
			secret.setPayPassword(md5Pwd);
			secretDao.updateSecret(secret);
			return 1;
		}
		return 0;
	}
	
	/**
	 * @author 西安工业大学蓝桥一期--孙航建
	 * @Title: getSecretById 
	 * @Description: 查询 
	 * @param  id 页面传进来的数据id
	 * @date 2017年4月24日 下午23:55:25 
	 * @throws
	 */
	public  Secret getSecretById(Integer id) {
		return secretDao.getSecret(id);
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
		secretDao.addByReq(secret);
	}

   


	
	
}
