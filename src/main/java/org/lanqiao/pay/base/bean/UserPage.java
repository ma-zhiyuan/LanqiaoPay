package org.lanqiao.pay.base.bean;

import java.util.List;

import org.lanqiao.pay.base.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserPage<T> {
	// 当前页
	private Integer pageNo;
	// 分的页面
	private List<User> users;

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public UserPage(Integer pageNo, List<User> users) {
		super();
		this.pageNo = pageNo;
		this.users = users;
	}

	public UserPage() {
		super();
	}

}
