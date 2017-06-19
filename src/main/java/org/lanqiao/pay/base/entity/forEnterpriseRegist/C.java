/**   
* @Title: C.java 
* @Package org.lanqiao.pay.base.entity.forEnterpriseRegist 
* @Description: TODO
* @author 西安工业大学蓝桥一期--王增   
* @date 2017年4月28日 下午7:01:51    
*/

package org.lanqiao.pay.base.entity.forEnterpriseRegist;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author 王向宇
 *c页面的表单对应的实体
 */
public class C {
	@NotNull
	@NotEmpty
	private String password;   //enterpriseUser的密码
	@NotNull
	@NotEmpty
	private String password2;  //enterpriseUser的确认密码
	@NotNull
	@NotEmpty
	private String payPassword;  //enterpriseUser中secret的密码
	@NotNull
	@NotEmpty
	private String payPassword2;  //enterpriseUser中secret的确认密码
	@NotNull
	@NotEmpty
	private String securityQuestion;  //enterpriseUser中secret的密保问题
	@NotNull
	@NotEmpty
	private String classifiedAnswer;  //enterpriseUser中secret的密保答案

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getPayPassword() {
		return payPassword;
	}
	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}
	public String getPayPassword2() {
		return payPassword2;
	}
	public void setPayPassword2(String payPassword2) {
		this.payPassword2 = payPassword2;
	}
	public String getSecurityQuestion() {
		return securityQuestion;
	}
	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}
	public String getClassifiedAnswer() {
		return classifiedAnswer;
	}
	public void setClassifiedAnswer(String classifiedAnswer) {
		this.classifiedAnswer = classifiedAnswer;
	}
	public C(String password, String password2, String payPassword, String payPassword2, String securityQuestion,
			String classifiedAnswer) {
		super();
		this.password = password;
		this.password2 = password2;
		this.payPassword = payPassword;
		this.payPassword2 = payPassword2;
		this.securityQuestion = securityQuestion;
		this.classifiedAnswer = classifiedAnswer;
	}
	public C() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "C [password=" + password + ", password2=" + password2 + ", payPassword=" + payPassword
				+ ", payPassword2=" + payPassword2 + ", securityQuestion=" + securityQuestion + ", classifiedAnswer="
				+ classifiedAnswer + "]";
	}
	
}
