package org.lanqiao.pay.base.bean;

import java.util.List;

import org.lanqiao.pay.base.entity.CustomerCare;
import org.springframework.stereotype.Repository;

/**
 * @Title: CustomerCareCreteria.java
 * @Package org.lanqiao.pay.base.bean
 * @Description: 分页的条件
 * @author 西安工业大学蓝桥一期--毋泽航
 * @date 2017年5月7日 上午11:48:42
 */
@Repository
public class CustomerCareCreteria {
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
	private Integer state;
	// 最大业务量
	private Integer maxServiceCount;
	// 最小业务量
	private Integer minServiceCount;
	// 排序方式
	private String  order;
	
	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	private List<CustomerCare> customerCares;
	
	public List<CustomerCare> getCustomerCares() {
		return customerCares;
	}

	public void setCustomerCares(List<CustomerCare> customerCares) {
		this.customerCares = customerCares;
	}

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
		this.maxPageNo = (total%pageSize!=0?(total/pageSize)+1:total/pageSize);
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNoFrom() {
		return pageNoFrom;
	}

	public void setPageNoFrom() {
		this.pageNoFrom = (pageNo - 1) * pageSize;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getMaxServiceCount() {
		return maxServiceCount;
	}

	public void setMaxServiceCount(Integer maxServiceCount) {
		this.maxServiceCount = maxServiceCount;
	}

	public Integer getMinServiceCount() {
		return minServiceCount;
	}

	public void setMinServiceCount(Integer minServiceCount) {
		this.minServiceCount = minServiceCount;
	}

	public CustomerCareCreteria(Integer pageNo, Integer total, Integer pageNoFrom, Integer pageSize,
			Integer state, Integer maxServiceCount, Integer minServiceCount) {
		super();
		this.pageNo = pageNo;
		this.maxPageNo = (total%pageSize!=0?(total/pageSize)+1:total/pageSize);
		this.total = total;
		this.pageSize = pageSize;
		this.pageNoFrom = (pageNo - 1) * pageSize;
		this.state = state;
		this.maxServiceCount = maxServiceCount;
		this.minServiceCount = minServiceCount;
	}

	public CustomerCareCreteria() {
		super();
	}

}
