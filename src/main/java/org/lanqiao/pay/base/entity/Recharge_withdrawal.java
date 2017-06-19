package org.lanqiao.pay.base.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 转账和提现客服所进行查看的实体类
 * @author 孙航建
 * @time 2017年5月6日 下午12:00:20
 */
public class Recharge_withdrawal implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private Integer id;
	private User user;
	private Enterprise enterprise;
	private BankCard bankCard;
	private Double money;
	private Date time;  // 转账或提现时的时间
    private int operation;// 操作 个人1表示提现，0个人表示充值，2表示企业提现,3表示企业充值
    private int state; // 状态 个人： 0表示提现待客服审核，1表示提现已经审核，2表示冲值成功 表示操作成功； 企业： 3表示提现待客服审核，4表示 客服已经审核,5表示企业充值成功
    private String transaction_name;// 交易名称
    private String transaction_describe; // 交易描述
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Enterprise getEnterprise() {
		return enterprise;
	}
	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}
	public BankCard getBankCard() {
		return bankCard;
	}
	public void setBankCard(BankCard bankCard) {
		this.bankCard = bankCard;
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
	public int getOperation() {
		return operation;
	}
	public void setOperation(int operation) {
		this.operation = operation;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getTransaction_name() {
		return transaction_name;
	}
	public void setTransaction_name(String transaction_name) {
		this.transaction_name = transaction_name;
	}
	public String getTransaction_describe() {
		return transaction_describe;
	}
	public void setTransaction_describe(String transaction_describe) {
		this.transaction_describe = transaction_describe;
	}
	public Recharge_withdrawal() {
		super();
	}
	
	public Recharge_withdrawal(Integer id, User user, Enterprise enterprise, BankCard bankCard, Double money, Date time,
			int operation, int state) {
		super();
		this.id = id;
		this.user = user;
		this.enterprise = enterprise;
		this.bankCard = bankCard;
		this.money = money;
		this.time = time;
		this.operation = operation;
		this.state = state;
	}
	public Recharge_withdrawal(User user, Enterprise enterprise, BankCard bankCard, Double money, Date time,
			int operation, int state) {
		super();
		this.user = user;
		this.enterprise = enterprise;
		this.bankCard = bankCard;
		this.money = money;
		this.time = time;
		this.operation = operation;
		this.state = state;
	}

	
	
	public Recharge_withdrawal(User user, BankCard bankCard, Double money, Date time, int operation, int state) {
		super();
		this.user = user;
		this.bankCard = bankCard;
		this.money = money;
		this.time = time;
		this.operation = operation;
		this.state = state;
	}

	
	public Recharge_withdrawal(User user, Enterprise enterprise, BankCard bankCard, Double money, Date time,
			int operation, int state, String transaction_name) {
		super();
		this.user = user;
		this.enterprise = enterprise;
		this.bankCard = bankCard;
		this.money = money;
		this.time = time;
		this.operation = operation;
		this.state = state;
		this.transaction_name = transaction_name;
	}

	@Override
	public String toString() {
		return "Recharge_withdrawal [id=" + id + ", user=" + user + ", enterprise=" + enterprise + ", bankCard="
				+ bankCard + ", money=" + money + ", time=" + time + ", operation=" + operation + ", state=" + state
				+ ", transaction_name=" + transaction_name + ", transaction_describe=" + transaction_describe + "]";
	}
	/**
	 * @param user
	 * @param enterprise
	 * @param bankCard
	 * @param money
	 * @param time
	 * @param operation
	 * @param state
	 * @param transaction_name
	 * @param transaction_describe
	 */
	public Recharge_withdrawal(User user, Enterprise enterprise, BankCard bankCard, Double money, Date time,
			int operation, int state, String transaction_name, String transaction_describe) {
		super();
		this.user = user;
		this.enterprise = enterprise;
		this.bankCard = bankCard;
		this.money = money;
		this.time = time;
		this.operation = operation;
		this.state = state;
		this.transaction_name = transaction_name;
		this.transaction_describe = transaction_describe;
	}
	public Recharge_withdrawal(Enterprise enterprise, BankCard bankCard, Double money, Date time, int operation,
			int state, String transaction_name, String transaction_describe) {
		super();
		this.enterprise = enterprise;
		this.bankCard = bankCard;
		this.money = money;
		this.time = time;
		this.operation = operation;
		this.state = state;
		this.transaction_name = transaction_name;
		this.transaction_describe = transaction_describe;
	}
	
}
