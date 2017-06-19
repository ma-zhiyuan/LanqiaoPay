/**   
* @Title: TradePage.java 
* @Package org.lanqiao.pay.base.bean 
* @Description: TODO
* @author 西安工业大学蓝桥一期--王增   
* @date 2017年5月14日 上午10:36:54    
*/

package org.lanqiao.pay.base.bean;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author 王增
 *用于显示交易记录的分页条件
 */
public class TradePage {
	private Integer pageNo;
	private Integer totalPage;//总页数
	private Integer totalRecords;//总记录条数
	private Integer pageSize = 5;
	private Integer begin;
	//如果前台需要选择的是提现的,那么这里需要是设置一个tradeType=4.在数据库中判断是4那么底层需要判断了(因为发起方可能是任意的)
	private Integer tradeType;//0个人充值或企业充值,4个人提现或企业提现(不要0,1) 对应Recharge_withdrawal的operation	3对应transfer
	private Integer tradeDateRangeType;//交易时间的范围,0当天,1一周内,2一个月内,3三个月内,4一年内
	private Integer tradeNumber;//交易号 对应数据表中的id;
	private String  tradeName;//交易名称
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date 	begainTradeRange;//交易起始日期区间
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date	endeTradeRange;//交易结束日期区间
	private Integer initiator;//发起方,0全部,1用户,2企业
	private Integer tradeState;//-1全部.0表示等待审核,1表示已审核,2表示成功(这里需要在底层判断一下,因为是分user和enteprise发起方的)
	private Double 	begainMoneyRange;//最小金额
	private Double 	endMoneyRange;//最大金额
	
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
		this.begin = (pageNo-1)*pageSize;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
		if(totalRecords%pageSize == 0)
			this.totalPage = totalRecords/pageSize;
		else
			this.totalPage = totalRecords/pageSize+1;
	}
	public Integer getTradeType() {
		return tradeType;
	}
	//只有合法的输入才能正确赋值
	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}
	public Integer getTradeDateRangeType() {
		return tradeDateRangeType;
	}
	public void setTradeDateRangeType(Integer tradeDateRangeType) {
			this.tradeDateRangeType = tradeDateRangeType;
	}

	public Date getBegainTradeRange() {
		return begainTradeRange;
	}
	public void setBegainTradeRange(Date begainTradeRange) {
		this.begainTradeRange = begainTradeRange;
	}
	public Date getEndeTradeRange() {
		return endeTradeRange;
	}
	public void setEndeTradeRange(Date endeTradeRange) {
		this.endeTradeRange = endeTradeRange;
	}
	public Integer getInitiator() {
		return initiator;
	}
	public void setInitiator(Integer initiator) {
			this.initiator = initiator;
	}
	public Integer getTradeState() {
		return tradeState;
	}
	public void setTradeState(Integer tradeState) {
			this.tradeState = tradeState;
	}
	public Double getBegainMoneyRange() {
		return begainMoneyRange;
	}
	public void setBegainMoneyRange(Double begainMoneyRange) {
			this.begainMoneyRange = begainMoneyRange;
	}
	public Double getEndMoneyRange() {
		return endMoneyRange;
	}
	public void setEndMoneyRange(Double endMoneyRange) {
			this.endMoneyRange = endMoneyRange;
	}
	public String getTradeName() {
		return tradeName;
	}
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName.trim();
	}
	
	public TradePage() {
		super();
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTradeNumber() {
		return tradeNumber;
	}
	public void setTradeNumber(Integer tradeNumber) {
		this.tradeNumber = tradeNumber;
	}
	//合法处理来自前台的一些参数
	public void dealParam(){
		if(this.tradeType!=null){
			if(this.tradeType==0||this.tradeType==4||this.tradeType==3){
				
			}else{
				this.tradeType = 0;
			}
		}else{
			this.tradeType = 0;
		}
		if(this.tradeDateRangeType!=null){
			if(this.tradeDateRangeType==0||this.tradeDateRangeType==1||this.tradeDateRangeType==2||
					this.tradeDateRangeType==3||this.tradeDateRangeType==4){
			}else{
				this.tradeDateRangeType  = 3;
			}
		}else{
			this.tradeDateRangeType  = 3;
		}
		if(this.initiator!=null){
			if(this.initiator==0|this.initiator==1||this.initiator==2){
				
			}else{
				this.initiator = 0;
			}
		}else{
			this.initiator = 0;
		}
		if(this.tradeState!=null){
			if(this.tradeState==-1||this.tradeState==0||this.tradeState==1||this.tradeState==2){
				
			}else{
				this.tradeState = -1;
			}
		}else{
			this.tradeState = -1;
		}
		if(this.begainMoneyRange!=null){
			if(this.begainMoneyRange<0||this.begainMoneyRange>99999999.9){
				this.begainMoneyRange = 0.0;
			}
		}
		if(this.endMoneyRange!=null){
			if(this.endMoneyRange<0||this.endMoneyRange>99999999.9){
				this.endMoneyRange = 0.0;
			}
		}
	}
	
	
	//进行日期区间条件的排序
	public void dateSort(){
		if(this.begainTradeRange!=null&&this.endeTradeRange!=null){
			Date temp = null;
			long time = this.begainTradeRange.getTime();
			long time2 = this.endeTradeRange.getTime();
			if(time > time2){
				temp = this.begainTradeRange;
				this.begainTradeRange = this.endeTradeRange;
				this.endeTradeRange = temp;
			}
		}
	}

	//进行价格区间的排序
	public void moneySort(){
		if(this.begainMoneyRange!=null&&this.endMoneyRange!=null){
			if(this.begainMoneyRange>this.endMoneyRange){
				Double temp = null;
				temp = this.begainMoneyRange;
				this.begainMoneyRange = this.endMoneyRange;
				this.endMoneyRange = temp;
			}
		}
	}
	public Integer getBegin() {
		return begin;
	}
	public void setBegin(Integer begin) {
		this.begin = begin;
	}
	@Override
	public String toString() {
		return "TradePage [pageNo=" + pageNo + ", totalPage=" + totalPage + ", totalRecords=" + totalRecords
				+ ", pageSize=" + pageSize + ", begin=" + begin + ", tradeType=" + tradeType + ", tradeDateRangeType="
				+ tradeDateRangeType + ", tradeNumber=" + tradeNumber + ", tradeName=" + tradeName
				+ ", begainTradeRange=" + begainTradeRange + ", endeTradeRange=" + endeTradeRange + ", initiator="
				+ initiator + ", tradeState=" + tradeState + ", begainMoneyRange=" + begainMoneyRange
				+ ", endMoneyRange=" + endMoneyRange + "]";
	}
	
}
