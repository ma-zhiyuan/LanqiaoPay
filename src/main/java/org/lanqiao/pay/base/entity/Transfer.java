package org.lanqiao.pay.base.entity;

import java.util.Date;

/**
 * 
 * @Title: Transfer.java
 * @Package org.lanqiao.pay.base.entity
 * @Description: TODO 转账的实体类
 * @author 西安工业大学蓝桥一期--何璐
 * @date 2017年5月7日 上午10:25:55
 */
public class Transfer {
	private Integer id;
	private String transferName;// 转账名称
	private String transferDescription;// 转账描述
	private User fromUser;// 转出用户
	private Enterprise fromEnterprise;// 转出企业
	private User toUser;// 转入用户
	private Enterprise toEnterprise;// 转入企业
	private BankCard fromBankCard;// 转出银行卡
	private BankCard toBankCard;// 转入银行卡
	private Double money;// 转账金额
	private Date time;// 转账时间
	private int state;// 0：等待审核；1：通过审核，2：交易成功

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTransferName() {
		return transferName;
	}

	public void setTransferName(String transferName) {
		this.transferName = transferName;
	}

	public String getTransferDescription() {
		return transferDescription;
	}

	public void setTransferDescription(String transferDescription) {
		this.transferDescription = transferDescription;
	}

	public User getFromUser() {
		return fromUser;
	}

	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}

	public Enterprise getFromEnterprise() {
		return fromEnterprise;
	}

	public void setFromEnterprise(Enterprise fromEnterprise) {
		this.fromEnterprise = fromEnterprise;
	}

	public User getToUser() {
		return toUser;
	}

	public void setToUser(User toUser) {
		this.toUser = toUser;
	}

	public Enterprise getToEnterprise() {
		return toEnterprise;
	}

	public void setToEnterprise(Enterprise toEnterprise) {
		this.toEnterprise = toEnterprise;
	}

	public BankCard getFromBankCard() {
		return fromBankCard;
	}

	public void setFromBankCard(BankCard fromBankCard) {
		this.fromBankCard = fromBankCard;
	}

	public BankCard getToBankCard() {
		return toBankCard;
	}

	public void setToBankCard(BankCard toBankCard) {
		this.toBankCard = toBankCard;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Transfer() {
		super();
	}

	public Transfer(Integer id, String transferName, String transferDescription, User fromUser,
			Enterprise fromEnterprise, User toUser, Enterprise toEnterprise, BankCard fromBankCard, BankCard toBankCard,
			Double money, Date time, int state) {
		super();
		this.id = id;
		this.transferName = transferName;
		this.transferDescription = transferDescription;
		this.fromUser = fromUser;
		this.fromEnterprise = fromEnterprise;
		this.toUser = toUser;
		this.toEnterprise = toEnterprise;
		this.fromBankCard = fromBankCard;
		this.toBankCard = toBankCard;
		this.money = money;
		this.time = time;
		this.state = state;
	}

	public Transfer(String transferName, String transferDescription, User fromUser, Enterprise toEnterprise,
			BankCard fromBankCard, BankCard toBankCard, Double money, Date time, int state) {
		super();
		this.transferName = transferName;
		this.transferDescription = transferDescription;
		this.fromUser = fromUser;
		this.toEnterprise = toEnterprise;
		this.fromBankCard = fromBankCard;
		this.toBankCard = toBankCard;
		this.money = money;
		this.time = time;
		this.state = state;
	}
	//个人使用银行卡向个银行卡宝转账的
	public Transfer(
			BankCard fromBankCard, BankCard toBankCard, Double money, Date time, String transferName,int state, String transferDescription) {
		super();
		this.transferName = transferName;
		this.transferDescription = transferDescription;
		this.fromBankCard = fromBankCard;
		this.toBankCard = toBankCard;
		this.money = money;
		this.time = time;
		this.state = state;
	}
	
	//个人使用支付宝向个人银行卡转账的
	public Transfer(User fromUser, BankCard toBankCard, double money, Date time, String transferName, int state,
			String transferDescription) {
		this.transferName = transferName;
		this.transferDescription = transferDescription;
		this.toBankCard = toBankCard;
		this.money = money;
		this.time = time;
		this.state = state;
		this.fromUser = fromUser;
	}
	//个人使用支付宝向个人支付宝转账的
	public Transfer(User fromUser, User toUser, double money, Date time, String transferName, int state,
			String transferDescription) {
		this.transferName = transferName;
		this.transferDescription = transferDescription;
		this.toUser = toUser;
		this.money = money;
		this.time = time;
		this.state = state;
		this.fromUser = fromUser;
	}
	//个人使用银行卡向个人支付宝转账的
	public Transfer(BankCard fromBankCard, User toUser, double money, Date time, String transferName, int state,
			String transferDescription) {
		this.transferName = transferName;
		this.transferDescription = transferDescription;
		this.toUser = toUser;
		this.money = money;
		this.time = time;
		this.state = state;
		this.fromBankCard = fromBankCard;
	}
	
    
	public Transfer(String transferName, String transferDescription, User fromUser, Enterprise fromEnterprise,
			User toUser, Enterprise toEnterprise, BankCard fromBankCard, BankCard toBankCard, Double money, Date time,
			int state) {
		super();
		this.transferName = transferName;
		this.transferDescription = transferDescription;
		this.fromUser = fromUser;
		this.fromEnterprise = fromEnterprise;
		this.toUser = toUser;
		this.toEnterprise = toEnterprise;
		this.fromBankCard = fromBankCard;
		this.toBankCard = toBankCard;
		this.money = money;
		this.time = time;
		this.state = state;
	}

	@Override
	public String toString() {
		return "Transfer [id=" + id + ", transferName=" + transferName + ", transferDescription=" + transferDescription
				+ ", fromUser=" + fromUser + ", fromEnterprise=" + fromEnterprise + ", toUser=" + toUser
				+ ", toEnterprise=" + toEnterprise + ", fromBankCard=" + fromBankCard + ", toBankCard=" + toBankCard
				+ ", money=" + money + ", time=" + time + ", state=" + state + "]";
	}

}
