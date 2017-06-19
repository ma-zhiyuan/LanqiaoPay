package org.lanqiao.pay.base.bean;

import java.util.List;

import org.lanqiao.pay.base.entity.Bank;
import org.lanqiao.pay.base.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class BankPage {
	private Integer totalPage;
	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	private Integer pageNo;
		
	private List<Bank> banks;

	
	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	
	public BankPage() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public BankPage(Integer totalPage, Integer pageNo, List<Bank> banks) {
		super();
		this.totalPage = totalPage;
		this.pageNo = pageNo;
		this.banks = banks;
	}

	public List<Bank> getBanks() {
		return banks;
	}

	public void setBanks(List<Bank> banks) {
		this.banks = banks;
	}

	@Override
	public String toString() {
		return "BankPage [totalPage=" + totalPage + ", pageNo=" + pageNo + ", banks=" + banks + "]";
	}

	
	

}
