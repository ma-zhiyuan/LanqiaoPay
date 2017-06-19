package org.lanqiao.pay.base.entity;

import java.util.Date;

import javax.validation.Valid;

import org.lanqiao.pay.base.entity.forEnterpriseRegist.D;
/**
 * 
 * @author 王增
 *这个类对企业级用户的基本信息进行定义
 */
public class EnterpriseUser {
	private Integer id;
	private String name;		//对应reg_company_b.html常用联系人
	private String email;		//对应reg_company_a.html邮箱:
	private String password;	////对应reg_company_c.登录密码:
	private String tell;			//对应reg_company_b.html常用联系人电话:
	private String photo;		//存到是路径
	private String address;		//reg_company_b的住所
	private String identity;	//reg_company_b身份类型
	private Date registTime;	//注册时间
	private Integer isDelete = 0;	//是否已删除 默认是0 未删除
	private Integer isForbid = 0;	//是否已禁止 默认是0 为禁止
	private Double balance;	//账户余额
	private D 		d;			//注册d实体
	@Valid
	private Enterprise enterprise;	//企业
	private Secret secret;		//密保等高级信息
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTell() {
		return tell;
	}
	public void setTel(String tell) {
		this.tell = tell;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String indentity) {
		this.identity = indentity;
	}
	public Date getRegistTime() {
		return registTime;
	}
	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public Integer getIsForbid() {
		return isForbid;
	}
	public void setIsForbid(Integer isForbid) {
		this.isForbid = isForbid;
	}
	public Enterprise getEnterprise() {
		return enterprise;
	}
	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}
	public Secret getSecret() {
		return secret;
	}
	public void setSecret(Secret secret) {
		this.secret = secret;
	}
	public EnterpriseUser(String name, String email, String password, String tel, String photo, String address,
			String indentity, Date registTime, Integer isDelete, Integer isForbid, Enterprise enterprise,
			Secret secret,Double balance) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.tell = tel;
		this.photo = photo;
		this.address = address;
		this.identity = indentity;
		this.registTime = registTime;
		this.isDelete = isDelete;
		this.isForbid = isForbid;
		this.enterprise = enterprise;
		this.secret = secret;
		this.balance = balance;
	}
	
	public EnterpriseUser() {
		super();
		this.enterprise = new Enterprise();
		this.secret = new Secret();
	}
	
	public EnterpriseUser(Integer id, double balance) {
		this.id = id;
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "EnterpriseUser [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", tel="
				+ tell + ", photo=" + photo + ", address=" + address + ", indentity=" + identity + ", registTime="
				+ registTime + ", isDelete=" + isDelete + ", isForbid=" + isForbid +", balance " +balance+", enterprise=" + enterprise
				+ ", secret=" + secret + ",D"+d+"]";
	}
	public D getD() {
		return d;
	}
	public void setD(D d) {
		this.d = d;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public EnterpriseUser(Integer id, Double balance) {
		super();
		this.id = id;
		this.balance = balance;
	}
}
