package org.lanqiao.pay.base.bean;

import org.springframework.stereotype.Repository;

@Repository
public class BankCreteria {
		// 所在页码
		private Integer pageNo;
		// 选择排序方式
		private String orderby;
	
		private String keyword;
	
		
		
		
	

		private Integer pageSize = 5;
		private Integer begin;
		
		

		public Integer getBegin() {
			begin=(this.pageNo-1)*this.pageSize;
			return begin;
		}

		
		public void setBegin(Integer begin) {
			this.begin = begin;
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
		
		public Integer getPageSize() {
			return pageSize;
		}
		public void setPageSize(Integer pageSize) {
			this.pageSize = pageSize;
		}
		
		
		
		public BankCreteria(Integer pageNo, String orderby, String keyword, Integer pageSize, Integer begin) {
			super();
			this.pageNo = pageNo;
			this.orderby = orderby;
			this.keyword = keyword;
			this.pageSize = pageSize;
			this.begin = begin;
		
		}

		

		@Override
		public String toString() {
			return "BankCreteria [pageNo=" + pageNo + ", orderby=" + orderby + ", keyword=" + keyword + ", pageSize="
					+ pageSize + ", begin=" + begin + "]";
		}


		public BankCreteria() {
			super();
			// TODO Auto-generated constructor stub
		}

}
