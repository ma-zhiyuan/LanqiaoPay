/**   
* @Title: Page.java 
* @Package org.lanqiao.pay.base.entity 
* @Description: TODO
* @author 西安工业大学蓝桥一期--王增   
* @date 2017年5月2日 下午6:21:46    
*/

package org.lanqiao.pay.base.entity;

import java.text.ParseException;
import java.util.Date;

import org.lanqiao.pay.base.util.MyUtils;

/**
 * @author 王增
 * 企业用户分页信息的简单对应实体
 */
public class EnterpriseUserPage {
	// 第几页
	private Integer pageNo;
	// 所有记录数
	private Integer count;
	// 总共页数
	private Integer total;
	//记录起始处
	private Integer begin;
	//分页大小
	private Integer pageSize = 6;
	
	//企业用户邮箱关键字
	private String emailKeyWord;
	//注册时间对应的字符串
	private String registTimeStr;//方便用来进行日期转化
	//企业用户的注册时间
	private Date registTime;
	//是否已经审批,数据库表中对应isForbid字段,默认全选
	private Integer isApprove = 2;
	//
	public EnterpriseUserPage() {
		super();
	}

	@Override
	public String toString() {
		return "EnterpriseUserPage [pageNo=" + pageNo + ", count=" + count + ", total=" + total + ", begin=" + begin
				+ ", pageSize=" + pageSize  + ", registTime=" + registTime+", registTimeStr=" + registTimeStr
				+ ", isApprove=" + isApprove +", emailKeyWord="+ emailKeyWord+"]";
	}
	

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
		this.begin = (pageNo-1)*pageSize;
	}

	public Integer getCount() {
		return count;
	}
	
	public void setCount(Integer count) {
		this.count = count;
		if(count%pageSize == 0)
			this.total = count/pageSize;
		else
			this.total = count/pageSize+1;
	}

	public Integer getTotal() {
		return total;
	}

	private void setTotal(Integer totalPage) {
		this.total = totalPage;
	}

	public Integer getBegin() {
		return begin;
	}

	private void setBegin(Integer begin) {
		this.begin = begin;
	}

	public Integer getPageSize() {
		return pageSize;
	}


	public Date getRegistTime() {
		return registTime;
	}

	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}

	public Integer getIsApprove() {
		return isApprove;
	}
	//如果有人在前台恶意篡改了isApprove的值(或者绕过前台恶意赋值),那我就设其为2,即选择全部
	public void setIsApprove(Integer isApprove) {
		if(isApprove==1||isApprove==0){
			this.isApprove = isApprove;
		}else{
			this.isApprove = 2;
		}
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}



	public String getEmailKeyWord() {
		return emailKeyWord;
	}


	//我们这里对其进行处理
	public void setEmailKeyWord(String emailKeyWord) {
		if(emailKeyWord.trim().equals("")){
			this.emailKeyWord = null;
		}else{
			this.emailKeyWord = emailKeyWord.trim();
		}
	}



	public String getRegistTimeStr() {
		return registTimeStr;
	}


	//在进行为日期字符串赋值的同时进行日期转换,如果出现异常了(字符串格式不对),那registTime是没值的,是null
	public void setRegistTimeStr(String registTimeStr) {
		
		this.registTimeStr = registTimeStr.trim();
		System.out.println("registTimeStr:"+registTimeStr);
		try {
			if(!registTimeStr.equals("")){
				this.registTime = MyUtils.dateAndTimeStringToDate(registTimeStr);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

