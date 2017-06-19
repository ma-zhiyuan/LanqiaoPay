package org.lanqiao.pay.base.entity;

import java.util.Date;

/**
 * @Title: BankCard.java
 * @Package org.lanqiao.pay.base.entity
 * @Description: 银行卡类
 * @author 西安工业大学蓝桥一期--刘江
 * @date 2017年5月3日 下午8:47:59
 */
public class BankCard {
	Integer id;
	// 用户id
	User userId;
	// 企业id
	Enterprise enterpriseId;
	public BankCard(Integer id, Double balance) {
		super();
		this.id = id;
		this.balance = balance;
	}

	// 余额
	Double balance;

	// 银行卡对应的银行
	Bank bankId;
	// 卡号
	String cardNumber;
	// 是否是默认银行卡
	Integer isDefault;
	// 是否开通快捷支付
	Integer isQuickPay;
	// 注册时间
	Date applicationTime;
	// 银行卡密码
	String bankCardPassWord;
	//开户地址
	String address;
	//jap显示卡号后四位
	String jspCardNumber;
	public String getJspCardNumber() {
		return jspCardNumber;
	}
	public void setJspCardNumber(String jspCardNumber) {
		this.jspCardNumber = jspCardNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBankCardPassWord() {
		return bankCardPassWord;
	}

	public void setBankCardPassWord(String bankCardPassWord) {
		this.bankCardPassWord = bankCardPassWord;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	public Integer getIsQuickPay() {
		return isQuickPay;
	}

	public Date getApplicationTime() {
		return applicationTime;
	}

	public void setApplicationTime(Date applicationTime) {
		this.applicationTime = applicationTime;
	}

	public void setIsQuickPay(Integer isQuickPay) {
		this.isQuickPay = isQuickPay;
	}

	public BankCard(User userId, Enterprise enterpriseId, Double balance, Bank bankId, String cardNumber,
			Integer isDefault, Integer isQuickPay) {
		super();
		this.userId = userId;
		this.enterpriseId = enterpriseId;
		this.balance = balance;
		this.bankId = bankId;
		this.cardNumber = cardNumber;
		this.isDefault = 0;
		this.isQuickPay = 0;
	}

	public BankCard(Enterprise enterpriseId, Double balance, Bank bankId, String cardNumber) {
		super();
		this.enterpriseId = enterpriseId;
		this.balance = balance;
		this.bankId = bankId;
		this.cardNumber = cardNumber;
	}

	public BankCard(User userId, Enterprise enterpriseId, Double balance, Bank bankId, String cardNumber) {
		super();
		this.userId = userId;
		this.enterpriseId = enterpriseId;
		this.balance = balance;
		this.bankId = bankId;
		this.cardNumber = cardNumber;
	}

	public BankCard(Integer id, User userId, Enterprise enterpriseId, Double balance, Bank bankId, String cardNumber) {
		super();
		this.id = id;
		this.userId = userId;
		this.enterpriseId = enterpriseId;
		this.balance = balance;
		this.bankId = bankId;
		this.cardNumber = cardNumber;
	}

	public Enterprise getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Enterprise enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	@Override
	public String toString() {
		return "BankCard [id=" + id + ", userId=" + userId + ", enterpriseId=" + enterpriseId + ", balance=" + balance
				+ ", bankId=" + bankId + ", cardNumber=" + cardNumber + ", isDefault=" + isDefault + ", isQuickPay="
				+ isQuickPay + ", applicationTime=" + applicationTime + ", bankCardPassWord=" + bankCardPassWord
				+ ", address=" + address + "]";
	}

	public BankCard() {
		super();
	}

	public BankCard(Integer id, User userId, Bank bankId, String cardNumber, Integer isDefault, Integer isQuickPay,
			Date applicationTime) {
		super();
		this.id = id;
		this.userId = userId;
		this.bankId = bankId;
		this.cardNumber = cardNumber;
		this.isDefault = isDefault;
		this.isQuickPay = isQuickPay;
		this.applicationTime = applicationTime;
	}

	public BankCard(User userId, Double balance, Bank bankId) {
		super();
		this.userId = userId;
		this.balance = balance;
		this.bankId = bankId;
	}
	
	
	
	

	public BankCard(Integer id, User userId, Double balance, String cardNumber) {
		super();
		this.id = id;
		this.userId = userId;
		this.balance = balance;
		this.cardNumber = cardNumber;
	}

	public BankCard(Integer id, User userId, Double balance, Bank bankId) {
		super();
		this.id = id;
		this.userId = userId;
		this.balance = balance;
		this.bankId = bankId;
	}

	
	public BankCard(String cardNumber, double balance) {
		this.cardNumber = cardNumber;
		this.balance = this.balance;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Bank getBankId() {
		return bankId;
	}

	public void setBankId(Bank bankId) {
		this.bankId = bankId;
	}

}
