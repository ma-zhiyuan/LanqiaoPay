package org.lanqiao.pay.base.entity;

public class EnterpriseUnit {
	private Integer id;
	private String type;				//前台的企业单位类型
	private String businessLicense;		//普通营业执照
	private String allInOneLicense;		//多证合一营业执照
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBusinessLicense() {
		return businessLicense;
	}
	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}
	public String getAllInOneLicense() {
		return allInOneLicense;
	}
	public void setAllInOneLicense(String allInOneLicense) {
		this.allInOneLicense = allInOneLicense;
	}
	public EnterpriseUnit(String type, String businessLicense, String allInOneLicense) {
		super();
		this.type = type;
		this.businessLicense = businessLicense;
		this.allInOneLicense = allInOneLicense;
	}
	public EnterpriseUnit() {
		super();
	}
	@Override
	public String toString() {
		return "EnterpriseUnit [id=" + id + ", type=" + type + ", businessLicense=" + businessLicense
				+ ", allInOneLicense=" + allInOneLicense + "]";
	}
	
}
