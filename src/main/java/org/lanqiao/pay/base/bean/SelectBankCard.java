package org.lanqiao.pay.base.bean;
/**
 * 
* @Title: SelectBankCard.java 
* @Package org.lanqiao.pay.base.bean 
* @Description: TODO 选择银行卡页面返回的表单对象
* @author 西安工业大学蓝桥一期--王向宇 
* @date 2017年5月11日 下午7:45:56
 */
public class SelectBankCard {
	private String cardNumber; //银行卡号
	private String bankName; //所属银行名
	private String person; //所属人
	private Double balance; //余额
	private Integer dealNumber; //交易笔数
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Integer getDealNumber() {
		return dealNumber;
	}
	public void setDealNumber(Integer dealNumber) {
		this.dealNumber = dealNumber;
	}
	public SelectBankCard(String cardNumber, String bankName, String person, Double balance, Integer dealNumber) {
		super();
		this.cardNumber = cardNumber;
		this.bankName = bankName;
		this.person = person;
		this.balance = balance;
		this.dealNumber = dealNumber;
	}
	public SelectBankCard() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "SelectBankCard [cardNumber=" + cardNumber + ", bankName=" + bankName + ", person=" + person
				+ ", balance=" + balance + ", dealNumber=" + dealNumber + "]";
	}
	
	
}
