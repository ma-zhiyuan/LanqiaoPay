package org.lanqiao.pay.base.entity;

/**
 * @Title: CustomerCare.java
 * @Package org.lanqiao.pay.base.entity
 * @Description: 客服的实体类
 * @author 西安工业大学蓝桥一期--毋泽航
 * @date 2017年5月2日 上午11:22:31
 */
public class CustomerCare {
	//客服id
	private int id;
	//客服密码
	private String password;
	//客服状态码，0表示已禁用客服，1表示客服正常使用
	private int state;
	//客服处理业务的总数
	private int serviceCount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getServiceCount() {
		return serviceCount;
	}

	public void setServiceCount(int serviceCount) {
		this.serviceCount = serviceCount;
	}

	public CustomerCare() {
		super();
	}

	public CustomerCare(String password, int state, int serviceCount) {
		super();
		this.password = password;
		this.state = state;
		this.serviceCount = serviceCount;
	}

	public CustomerCare(int id, String password, int state, int serviceCount) {
		super();
		this.id = id;
		this.password = password;
		this.state = state;
		this.serviceCount = serviceCount;
	}

	@Override
	public String toString() {
		return "CustomerService [id=" + id + ", password=" + password + ", state=" + state + ", serviceCount="
				+ serviceCount + "]";
	}

}
