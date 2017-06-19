package org.lanqiao.pay.base.entity;

/**
 * @Title: Bank.java
 * @Package org.lanqiao.pay.base.entity
 * @Description: 银行类
 * @author 西安工业大学蓝桥一期--刘江
 * @date 2017年5月3日 下午8:45:33
 */
public class Bank {
	Integer id;
	String name;
	Double parities; // 银行的汇率 所有的汇率都是基于100元钱 eg:提现一百元收取一毛钱的金额
	Integer cardNum;
	
   
	public Integer getCardNum() {
		return cardNum;
	}

	public void setCardNum(Integer cardNum) {
		this.cardNum = cardNum;
	}


	public Bank(Integer id, String name, Double parities, Integer cardNum) {
		super();
		this.id = id;
		this.name = name;
		this.parities = parities;
		this.cardNum = cardNum;
	}

	@Override
	public String toString() {
		return "Bank [id=" + id + ", name=" + name + ", parities=" + parities + ", cardNum=" + cardNum + "]";
	}

	public Double getParities() {
		return parities;
	}

	public void setParities(Double parities) {
		this.parities = parities;
	}

	public Bank(String name) {
		super();
		this.name = name;
	}

	public Bank() {
		super();
	}

	public Bank(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
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
}
