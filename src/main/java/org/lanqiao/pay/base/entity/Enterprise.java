package org.lanqiao.pay.base.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * @author 王增
 *这个类中对企业信息进行了定义
 */
public class Enterprise {
	private Integer id;
	@NotEmpty
	@NotNull
	private String enterpriseName;		//企业名称
	private String licenseType;			//证件类型
	@NotEmpty
	@NotNull
	private String organizationCodeCertificate;	//组织机构代码证
	private String businessLicense;//营业执照
	@NotEmpty
	@NotNull
	private String licenseRegistrationNumber;	//营业执照注册号
	private String address;			//单位所在地
	private String businessScope;	//营业范围
	@NotEmpty
	@NotNull
	@DateTimeFormat
	private Date businessTerm;		//营业期限,有效期
	private String organizationCodeNumber;		//组织机构代码号
	private EnterpriseUnit enterpriseUnit; 		//企业单位类型
	private LegalRepresentative legalRepresentative;	//法定代表人
	private List<BankCard> bankcards;  //企业的一系列银行卡
	
	
	@Override
	public String toString() {
		return "Enterprise [id=" + id + ", enterpriseName=" + enterpriseName + ", licenseType=" + licenseType
				+ ", organizationCodeCertificate=" + organizationCodeCertificate + ", businessLicense="
				+ businessLicense + ", licenseRegistrationNumber=" + licenseRegistrationNumber + ", address=" + address
				+ ", businessScope=" + businessScope + ", businessTerm=" + businessTerm + ", organizationCodeNumber="
				+ organizationCodeNumber + ", enterpriseUnit=" + enterpriseUnit + ", legalRepresentative="
				+ legalRepresentative + ", bankcards=" + bankcards + "]";
	}

	public List<BankCard> getBankcards() {
		return bankcards;
	}

	public void setBankcards(List<BankCard> bankcards) {
		this.bankcards = bankcards;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	public String getLicenseType() {
		return licenseType;
	}
	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}
	public String getOrganizationCodeCertificate() {
		return organizationCodeCertificate;
	}
	public void setOrganizationCodeCertificate(String organizationCodeCertificate) {
		this.organizationCodeCertificate = organizationCodeCertificate;
	}
	public String getBusinessLicense() {
		return businessLicense;
	}
	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}
	public String getLicenseRegistrationNumber() {
		return licenseRegistrationNumber;
	}
	public void setLicenseRegistrationNumber(String licenseRegistrationNumber) {
		this.licenseRegistrationNumber = licenseRegistrationNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBusinessScope() {
		return businessScope;
	}
	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
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
	public Enterprise() {
		super();
	}
	public Enterprise(String enterpriseName, String licenseType, String organizationCodeCertificate,
			String businessLicense, String licenseRegistrationNumber, String address, String businessScope,
			Date businessTerm, String organizationCodeNumber, EnterpriseUnit enterpriseUnit,
			LegalRepresentative legalRepresentative) {
		super();
		this.enterpriseName = enterpriseName;
		this.licenseType = licenseType;
		this.organizationCodeCertificate = organizationCodeCertificate;
		this.businessLicense = businessLicense;
		this.licenseRegistrationNumber = licenseRegistrationNumber;
		this.address = address;
		this.businessScope = businessScope;
		this.businessTerm = businessTerm;
		this.organizationCodeNumber = organizationCodeNumber;
		this.enterpriseUnit = enterpriseUnit;
		this.legalRepresentative = legalRepresentative;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
}
