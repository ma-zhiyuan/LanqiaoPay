package org.lanqiao.pay.base.bean;

import java.util.List;

import org.lanqiao.pay.base.entity.Recharge_withdrawal;
import org.lanqiao.pay.base.entity.Transfer;

/**
 * @Title: TransferCreteria.java
 * @Package org.lanqiao.pay.base.bean
 * @Description: 转账分页
 * @author 西安工业大学蓝桥一期--毋泽航
 * @date 2017年6月8日 下午7:45:54
 */
public class TransferCreteria {
	// 当前页码
	private Integer pageNo;
	// 最大的页数
	private Integer maxPageNo;
	// 查询的总数
	private Integer total;
	// 当前页的第一条数据对应的偏移量
	private Integer pageNoFrom;
	// 每一页显示数据的大小
	private Integer pageSize = 10;
	// 查询状态
	private Integer state;//0表示未审核;1表示已审核
	// 转账类型
	private String transferType;//etp表示企业向个人，其余效仿
	// 排序方式
	private String order;
	private List<Transfer> transfer;

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getMaxPageNo() {
		return maxPageNo;
	}

	public void setMaxPageNo() {
		this.maxPageNo = (total % pageSize != 0 ? (total / pageSize) + 1 : total / pageSize);
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getPageNoFrom() {
		return pageNoFrom;
	}

	public void setPageNoFrom() {
		this.pageNoFrom = (pageNo - 1) * pageSize;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getTransferType() {
		return transferType;
	}

	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}

	public List<Transfer> getTransfer() {
		return transfer;
	}

	public void setTransfer(List<Transfer> transfer) {
		this.transfer = transfer;
	}

	@Override
	public String toString() {
		return "TransferCreteria [pageNo=" + pageNo + ", maxPageNo=" + maxPageNo + ", total=" + total + ", pageNoFrom="
				+ pageNoFrom + ", pageSize=" + pageSize + ", state=" + state + ", transferType=" + transferType
				+ ", order=" + order + ", transfer=" + transfer + "]";
	}
	
}
