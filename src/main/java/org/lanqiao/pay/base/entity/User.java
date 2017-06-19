package org.lanqiao.pay.base.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @Title: User.java
 * @Package org.lanqiao.pay.base.entity
 * @Description:个人用户类
 * @author 西安工业大学蓝桥一期--刘江
 * @date 2017年4月23日 上午8:57:10
 */
public class User implements Serializable {
	public User(Integer id, Double balance) {
		super();
		this.id = id;
		this.balance = balance;
	}

	/**
	 * @Title: User.java
	 * @Package org.lanqiao.pay.base.entity
	 * @Description: TODO()
	 * @author 西安工业大学蓝桥一期--刘江
	 * @date 2017年4月23日 上午10:58:40
	 */
	private static final long serialVersionUID = 2124337193986122547L;
	private Integer id;
	@NotEmpty
	@NotNull
	private String name;
	@Email
	private String email;
	// 登录密码
	@NotEmpty
	@NotNull
	private String loginPassword;
	// 手机号
	private String call;
	// 身份证号
	private String cardId;
	// 注册日期
	private Date registration;
	// 密保
	private Secret secretId;
	// 状态信息 0表示正常状态，1表示被禁用
	private Integer state;
	// 余额
	private Double balance;
	// 银行卡
	private List<BankCard> listBankCard;
	// 头像路径
	private String sufface;
	// 注销状态 0为正常，1已注销
	private Integer deleteState;

	public Integer getDeleteState() {
		return deleteState;
	}

	public void setDeleteState(Integer deleteState) {
		this.deleteState = deleteState;
	}

	public String getSufface() {
		return sufface;
	}

	public void setSufface(String sufface) {
		this.sufface = sufface;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getCall() {
		return call;
	}

	public void setCall(String call) {
		this.call = call;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public Date getRegistration() {
		return registration;
	}

	public void setRegistration(Date registration) {
		this.registration = registration;
	}

	public Secret getSecretId() {
		return secretId;
	}

	public void setSecretId(Secret secretId) {
		this.secretId = secretId;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public User() {
		super();
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", loginPassword=" + loginPassword + ", call="
				+ call + ", cardId=" + cardId + ", registration=" + registration + ", secretId=" + secretId + ", state="
				+ state + ", balance=" + balance + ", listBankCard=" + listBankCard + ", sufface=" + sufface
				+ ", deleteState=" + deleteState + "]";
	}

	public User(Integer id, String name, String email, String loginPassword, String call, String cardId,
			Date registration, Secret secretId, Integer state, Double balance) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.loginPassword = loginPassword;
		this.call = call;
		this.cardId = cardId;
		this.registration = registration;
		this.secretId = secretId;
		this.state = state;
		this.balance = balance;
	}

	public User(Integer id, String name, String email, String loginPassword, String call, String cardId,
			Date registration, Secret secretId, Integer state, Double balance, List<BankCard> listBankCard,
			String sufface, Integer deleteState) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.loginPassword = loginPassword;
		this.call = call;
		this.cardId = cardId;
		this.registration = registration;
		this.secretId = secretId;
		this.state = state;
		this.balance = balance;
		this.listBankCard = listBankCard;
		this.sufface = sufface;
		this.deleteState = deleteState;
	}

	public List<BankCard> getListBankCard() {
		return listBankCard;
	}

	public void setListBankCard(List<BankCard> listBankCard) {
		this.listBankCard = listBankCard;
	}

}
