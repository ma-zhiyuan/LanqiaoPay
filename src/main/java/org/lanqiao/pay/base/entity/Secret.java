package org.lanqiao.pay.base.entity;

import java.io.Serializable;

public class Secret  implements Serializable{
	/**   
	* @Title: 密保实体类
	* @Package org.lanqiao.pay.base.entity 
	* @Description: TODO() 
	* @author 西安工业大学蓝桥一期--刘江  
	* @date 2017年4月23日 上午10:58:57    
	*/
	private static final long serialVersionUID = 2928227843740951455L;
	public Secret(String payPassword, String securityQuestion, String classifiedAnswer) {
		super();
		this.payPassword = payPassword;
		this.securityQuestion = securityQuestion;
		this.classifiedAnswer = classifiedAnswer;
	}
	private Integer id;
	//支付密码
	private String payPassword;
	//密保问题
	private String securityQuestion;
	//密保答案
	private String classifiedAnswer;
	@Override
	public String toString() {
		return "Secret [id=" + id + ", payPassword=" + payPassword + ", securityQuestion=" + securityQuestion
				+ ", classifiedAnswer=" + classifiedAnswer + "]";
	}
	public Secret() {
		super();
	}
	public Secret(Integer id, String payPassword, String securityQuestion, String classifiedAnswer) {
		super();
		this.id = id;
		this.payPassword = payPassword;
		this.securityQuestion = securityQuestion;
		this.classifiedAnswer = classifiedAnswer;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPayPassword() {
		return payPassword;
	}
	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
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
	
}
