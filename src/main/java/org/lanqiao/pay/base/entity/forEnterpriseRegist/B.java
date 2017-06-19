/**   
* @Title: B.java 
* @Package org.lanqiao.pay.base.entity.forEnterpriseRegist 
* @Description: TODO
* @author 西安工业大学蓝桥一期--王增   
* @date 2017年4月28日 下午7:00:41    
*/

package org.lanqiao.pay.base.entity.forEnterpriseRegist;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;
import org.lanqiao.pay.base.entity.EnterpriseUnit;
import org.lanqiao.pay.base.entity.LegalRepresentative;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author 王增
 *b页面的表单对应的实体
 */
public class B {
	@NotNull
	@NotEmpty
	private String enterpriseName; //企业名称
	private String licenseType; //证件类型
	@NotNull
	@NotEmpty
	private String buscode;  //营业执照注册号
	private String businessLicense;  //营业执照
	private String address;  //企业所在地
	private String userAddress;   //reg_company_b的住所
	private String businessScope;  //企业经营范围
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")   
	private Date businessTerm;   //企业营业期限
	@NotNull
	@NotEmpty
	private String organizationCodeNumber;  //组织机构代码号
	private String organizationCodeCertificate;  //组织机构代码证
	private String identity;	//reg_company_b身份类型
	@NotNull
	@NotEmpty
	private String name;		//对应reg_company_b.html常用联系人
	@NotNull
	@Pattern(regexp="^0?(12[0-9]|13[0-9]|14[57]|15[012356789]|16[0-9]|17[0-9]|18[0-9]|19[0-9])[0-9]{8}$")
	private String tell;			//对应reg_company_b.html常用联系人电话:
	@Valid
	private EnterpriseUnit enterpriseUnit; 		//企业单位类型
	@Valid
	private LegalRepresentative legalRepresentative;	//法定代表人
	
	
	
	
	public String getBusinessLicense() {
		return businessLicense;
	}
	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}
	public String getOrganizationCodeCertificate() {
		return organizationCodeCertificate;
	}
	public void setOrganizationCodeCertificate(String organizationCodeCertificate) {
		this.organizationCodeCertificate = organizationCodeCertificate;
	}
	public String getBusinessScope() {
		return businessScope;
	}
	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLicenseType() {
		return licenseType;
	}
	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}
	public Date getBusinessTerm() {
		return businessTerm;
	}
	public void setBusinessTerm(Date businessTerm) {
		this.businessTerm = businessTerm;
	}
	public String getOrganizationCodeNumber() {
		return organizationCodeNumber;
	}
	public void setOrganizationCodeNumber(String organizationCodeNumber) {
		this.organizationCodeNumber = organizationCodeNumber;
	}
	public String getBuscode() {
		return buscode;
	}
	public void setBuscode(String buscode) {
		this.buscode = buscode;
	}
	public String getEnterpriseName() {
		return enterpriseName;
	}
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTell() {
		return tell;
	}
	public void setTell(String tell) {
		this.tell = tell;
	}
	public EnterpriseUnit getEnterpriseUnit() {
		return enterpriseUnit;
	}
	public void setEnterpriseUnit(EnterpriseUnit enterpriseUnit) {
		this.enterpriseUnit = enterpriseUnit;
	}
	public LegalRepresentative getLegalRepresentative() {
		return legalRepresentative;
	}
	public void setLegalRepresentative(LegalRepresentative legalRepresentative) {
		this.legalRepresentative = legalRepresentative;
	}
	
	
	
	@Override
	public String toString() {
		return "B [enterpriseName=" + enterpriseName + ", licenseType=" + licenseType + ", buscode=" + buscode
				+ ", businessLicense=" + businessLicense + ", address=" + address + ", userAddress=" + userAddress
				+ ", businessScope=" + businessScope + ", businessTerm=" + businessTerm + ", organizationCodeNumber="
				+ organizationCodeNumber + ", organizationCodeCertificate=" + organizationCodeCertificate
				+ ", identity=" + identity + ", name=" + name + ", tell=" + tell + ", enterpriseUnit=" + enterpriseUnit
				+ ", legalRepresentative=" + legalRepresentative + "]";
	}
	public B(String enterpriseName, String licenseType, String buscode, String businessLicense, String address,
			String userAddress, String businessScope, Date businessTerm, String organizationCodeNumber,
			String organizationCodeCertificate, String identity, String name, String tell,
			EnterpriseUnit enterpriseUnit, LegalRepresentative legalRepresentative) {
		super();
		this.enterpriseName = enterpriseName;
		this.licenseType = licenseType;
		this.buscode = buscode;
		this.businessLicense = businessLicense;
		this.address = address;
		this.userAddress = userAddress;
		this.businessScope = businessScope;
		this.businessTerm = businessTerm;
		this.organizationCodeNumber = organizationCodeNumber;
		this.organizationCodeCertificate = organizationCodeCertificate;
		this.identity = identity;
		this.name = name;
		this.tell = tell;
		this.enterpriseUnit = enterpriseUnit;
		this.legalRepresentative = legalRepresentative;
	}
	public B(String identity, String name, String tell, EnterpriseUnit enterpriseUnit,
			LegalRepresentative legalRepresentative) {
		super();
		this.identity = identity;
		this.name = name;
		this.tell = tell;
		this.enterpriseUnit = enterpriseUnit;
		this.legalRepresentative = legalRepresentative;
	}
	public B() {
		super();
	}
	
}
