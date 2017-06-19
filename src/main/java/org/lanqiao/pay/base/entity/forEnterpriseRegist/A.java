/**   
* @Title: A_email.java 
* @Package org.lanqiao.pay.base.entity.forEnterpriseRegist 
* @Description: TODO
* @author 西安工业大学蓝桥一期--王增   
* @date 2017年4月28日 下午7:00:03    
*/

package org.lanqiao.pay.base.entity.forEnterpriseRegist;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author 王增
 *A页面的表单对应的实体
 */
public class A {
	
	@NotEmpty
	@NotNull
	@Email
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "A [email=" + email + "]";
	}

	public A(String email) {
		super();
		this.email = email;
	}

	public A() {
		super();
	}
	
}
