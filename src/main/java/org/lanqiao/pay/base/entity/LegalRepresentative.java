package org.lanqiao.pay.base.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

public class LegalRepresentative {
	private Integer id;
	@NotEmpty
	@NotNull
	private String name;
	private String homeLocation;		//归属地
	private String certificateType;		//证件类型
	@NotNull
	@Pattern(regexp="^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$")
	private String certificateNumber;		//证件号码
	private String certificatePhotoPositive;	//证件照正面
	private String certificatePhotoReserse;		//证件照反面
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")  
	private Date certificateTerm;			//证件有效期
	@Override
	public String toString() {
		return "LegalRepresentative [id=" + id + ", name=" + name + ", homeLocation=" + homeLocation
				+ ", certificateType=" + certificateType + ", certificateNumber=" + certificateNumber
				+ ", certificatePhotoPositive=" + certificatePhotoPositive + ", certificatePhotoReserse="
				+ certificatePhotoReserse + ", certificateTerm=" + certificateTerm + "]";
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
	public String getHomeLocation() {
		return homeLocation;
	}
	public void setHomeLocation(String homeLocation) {
		this.homeLocation = homeLocation;
	}
	public String getCertificateType() {
		return certificateType;
	}
	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}
	public String getCertificateNumber() {
		return certificateNumber;
	}
	public void setCertificateNumber(String certificateNumber) {
		this.certificateNumber = certificateNumber;
	}
	public String getCertificatePhotoPositive() {
		return certificatePhotoPositive;
	}
	public void setCertificatePhotoPositive(String certificatePhotoPositive) {
		this.certificatePhotoPositive = certificatePhotoPositive;
	}
	public String getCertificatePhotoReserse() {
		return certificatePhotoReserse;
	}
	public void setCertificatePhotoReserse(String certificatePhotoReserse) {
		this.certificatePhotoReserse = certificatePhotoReserse;
	}
	public Date getCertificateTerm() {
		return certificateTerm;
	}
	public void setCertificateTerm(Date certificateTerm) {
		this.certificateTerm = certificateTerm;
	}
	public LegalRepresentative(String name, String homeLocation, String certificateType, String certificateNumber,
			String certificatePhotoPositive, String certificatePhotoReserse, Date certificateTerm) {
		super();
		this.name = name;
		this.homeLocation = homeLocation;
		this.certificateType = certificateType;
		this.certificateNumber = certificateNumber;
		this.certificatePhotoPositive = certificatePhotoPositive;
		this.certificatePhotoReserse = certificatePhotoReserse;
		this.certificateTerm = certificateTerm;
	}
	public LegalRepresentative() {
		super();
	}
	
}
