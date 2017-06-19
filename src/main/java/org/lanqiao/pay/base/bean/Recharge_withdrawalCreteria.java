package org.lanqiao.pay.base.bean;

import java.util.List;

import org.lanqiao.pay.base.entity.Recharge_withdrawal;

/**
 * @Title: Recharge_withdrawalCreteria.java
 * @Package org.lanqiao.pay.base.bean
 * @Description: Recharge_withdrawal的分页查询
 * @author 西安工业大学蓝桥一期--毋泽航
 * @date 2017年5月19日 下午5:13:12
 */
public class Recharge_withdrawalCreteria {
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
	// 排序方式
	private String order;
	//模糊查询
	private String fuzzy;
	private List<Recharge_withdrawal> recharges;
	
	public String getFuzzy() {
		return fuzzy;
	}

	public void setFuzzy(String fuzzy) {
		this.fuzzy = fuzzy;
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

	public Integer getPageNoFrom() {
		return pageNoFrom;
	}

	public void setPageNoFrom() {
		this.pageNoFrom = (pageNo-1)*pageSize;
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

	public List<Recharge_withdrawal> getRecharges() {
		return recharges;
	}

	public void setRecharges(List<Recharge_withdrawal> recharges) {
		this.recharges = recharges;
	}

}
