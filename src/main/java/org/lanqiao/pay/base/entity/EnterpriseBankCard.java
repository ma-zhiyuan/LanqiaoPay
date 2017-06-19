/**   
* @Title: EnterpriseBankCard.java 
* @Package org.lanqiao.pay.base.entity 
* @Description: TODO
* @author 西安工业大学蓝桥一期--王增   
* @date 2017年5月2日 上午10:59:56    
*/

package org.lanqiao.pay.base.entity;

/**
 * @author 王增
 *企业注册的时候需要用到的银行卡,对于普通用户而言,如果需要绑定银行卡,在验证唯一性的时候还需要在企业的银行卡中查看并验证.
 *同样企业用户在绑定银行卡的时候也需要同时去用户的银行卡表中查看并验证唯一性
 */
public class EnterpriseBankCard {
	private Integer id;
	private String bankName;		//开户行名称
	private String accountNumber;	//银行卡账号
	private String openAccountAddress;	//开户行地址
	private Double account;			//账户余额
	private EnterpriseUser enterpriseUser;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getOpenAccountAddress() {
		return openAccountAddress;
	}
	public void setOpenAccountAddress(String openAccountAddress) {
		this.openAccountAddress = openAccountAddress;
	}
	public Double getAccount() {
		return account;
	}
	public void setAccount(Double account) {
		this.account = account;
	}
	public EnterpriseUser getEnterpriseUser() {
		return enterpriseUser;
	}
	public void setEnterpriseUser(EnterpriseUser enterpriseUser) {
		this.enterpriseUser = enterpriseUser;
	}
	@Override
	public String toString() {
		return "EnterpriseBankCard [id=" + id + ", bankName=" + bankName + ", accountNumber=" + accountNumber
				+ ", openAccountAddress=" + openAccountAddress + ", account=" + account + ", enterpriseUser="
				+ enterpriseUser + "]";
	}
	public EnterpriseBankCard(String bankName, String accountNumber, String openAccountAddress, Double account) {
		super();
		this.bankName = bankName;
		this.accountNumber = accountNumber;
		this.openAccountAddress = openAccountAddress;
		this.account = account;
	}
	public EnterpriseBankCard() {
		super();
	}
	
}
