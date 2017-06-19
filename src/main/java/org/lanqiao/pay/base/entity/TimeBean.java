package org.lanqiao.pay.base.entity;



public class TimeBean {
	public String beginTime;
	public String endTime;
	public int userNumber;
	public int entNumber;
	//充值
	public int rechargs;
	//提现
	public int withdrawal;
	//转账
	public int transfer;
	public int getTransfer() {
		return transfer;
	}
	public void setTransfer(int transfer) {
		this.transfer = transfer;
	}
	public String end;
	@Override
	public String toString() {
		return "TimeBean [beginTime=" + beginTime + ", endTime=" + endTime + ", userNumber=" + userNumber
				+ ", entNumber=" + entNumber + ", rechargs=" + rechargs + ", withdrawal=" + withdrawal + ", transfer="
				+ transfer + ", end=" + end + "]";
	}
	public int getRechargs() {
		return rechargs;
	}
	public void setRechargs(int rechargs) {
		this.rechargs = rechargs;
	}
	public int getWithdrawal() {
		return withdrawal;
	}
	public void setWithdrawal(int withdrawal) {
		this.withdrawal = withdrawal;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}
	public int getEntNumber() {
		return entNumber;
	}
	public void setEntNumber(int entNumber) {
		this.entNumber = entNumber;
	}

	

}
