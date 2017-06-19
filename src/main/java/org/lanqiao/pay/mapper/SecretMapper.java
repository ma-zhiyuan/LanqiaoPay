package org.lanqiao.pay.mapper;

import org.lanqiao.pay.base.entity.Secret;

/**
 * 
 * @author 王增
 *操作密保等高级信息的mapper接口
 */
public interface SecretMapper {
	
	public Secret getSecretZM(Integer id2);
	
	
	/**
	 * @author 西安工业大学蓝桥一期--毋泽航
	 * @Title: getSecret updatePayPwd
	 * @date 2017年4月24日 下午5:27:01 
	 * @throws
	 */
	public Secret getSecret(int id);
	public void updateSecret(Secret secret);
		/** 
	* @author 西安工业大学蓝桥一期--刘江
	* @Title: addByReq 
	* @Description: 注册讲密保插入数据库
	* @return void   
	* @date 2017年5月2日 下午7:20:35 
	* @throws 
	*/
	public void addByReq(Secret secret);
		
}
