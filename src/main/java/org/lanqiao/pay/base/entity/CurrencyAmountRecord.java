package org.lanqiao.pay.base.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 利息金额生成记录表
 * @author 孙航建
 * @time 2017年5月21日 下午10:03:26
 */
public class CurrencyAmountRecord implements Serializable{

	private static final long serialVersionUID = 5374486358364041269L;
    
	private Integer id;
	private User user;
	private EnterpriseUser enterpriseUser;
	private Double parities_money; // 利率计算之后的利息
	private Date time;
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
	public EnterpriseUser getEnterpriseUser() {
		return enterpriseUser;
	}
	public void setEnterpriseUser(EnterpriseUser enterpriseUser) {
		this.enterpriseUser = enterpriseUser;
	}
	public Double getParities_money() {
		return parities_money;
	}
	public void setParities_money(Double parities_money) {
		this.parities_money = parities_money;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public CurrencyAmountRecord(Integer id, User user, EnterpriseUser enterpriseUser, Double parities_money,
			Date time) {
		super();
		this.id = id;
		this.user = user;
		this.enterpriseUser = enterpriseUser;
		this.parities_money = parities_money;
		this.time = time;
	}
	public CurrencyAmountRecord() {
		super();
	}
	@Override
	public String toString() {
		return "CurrencyAmountRecord [id=" + id + ", user=" + user + ", enterpriseUser=" + enterpriseUser
				+ ", parities_money=" + parities_money + ", time=" + time + "]";
	}
	public CurrencyAmountRecord(User user, EnterpriseUser enterpriseUser, Double parities_money, Date time) {
		super();
		this.user = user;
		this.enterpriseUser = enterpriseUser;
		this.parities_money = parities_money;
		this.time = time;
	}
	
}
