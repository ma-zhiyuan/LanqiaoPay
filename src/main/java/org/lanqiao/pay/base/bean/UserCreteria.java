package org.lanqiao.pay.base.bean;

import org.springframework.stereotype.Repository;

@Repository
public class UserCreteria {
	// 所在页码
	private Integer pageNo;
	// 选择排序方式
	private String orderby;
	// 模糊查询
	private String keyword;
	//总条数
	private Integer total;
	//每一页的条数
	private Integer pageSize = 10;

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	//起始的条数，通过total计算出需要查询的数据
	private Integer from;
	//结束的条数
	private Integer end;

	public Integer getFrom() {
		from=(this.pageNo-1)*this.pageSize;
		return from;
	}

	public void setFrom(Integer from) {
		this.from = from;
	}

	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public UserCreteria() {
		super();
	}

	public UserCreteria(Integer pageNo, String orderby, String keyword, Integer from, Integer end) {
		super();
		this.pageNo = pageNo;
		this.orderby = orderby;
		this.keyword = keyword;
		this.from = from;
		this.end = end;
	}

	

	public UserCreteria(Integer pageNo, String orderby, String keyword) {
		super();
		this.pageNo = pageNo;
		this.orderby = orderby;
		this.keyword = keyword;
	}
    
	


	@Override
	public String toString() {
		return "UserCreteria [pageNo=" + pageNo + ", orderby=" + orderby + ", keyword=" + keyword + ", from=" + from
				+ ", end=" + end + "]";
	}

}
