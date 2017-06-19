/**   
* @Title: D.java 
* @Package org.lanqiao.pay.base.entity.forEnterpriseRegist 
* @Description: TODO
* @author 西安工业大学蓝桥一期--王增   
* @date 2017年4月28日 下午7:01:58    
*/

package org.lanqiao.pay.base.entity.forEnterpriseRegist;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author 王增
 *D页面的表单对应的实体
 */
public class D {
	@NotEmpty
	@NotNull
	String cardNumber;
	@NotEmpty
	@NotNull
	String bankName;
	@NotEmpty
	@NotNull
	String address;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
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
	public D(String cardNumber, String bankName, String address) {
		super();
		this.cardNumber = cardNumber;
		this.bankName = bankName;
		this.address = address;
	}
	public D() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "D [cardNumber=" + cardNumber + ", bankName=" + bankName + ", address=" + address + "]";
	}

	
}
