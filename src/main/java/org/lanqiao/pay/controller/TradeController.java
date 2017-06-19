/**   
* @Title: TradeController.java 
* @Package org.lanqiao.pay.controller 
* @Description: TODO
* @author 西安工业大学蓝桥一期--王增   
* @date 2017年5月14日 下午2:17:53    
*/

package org.lanqiao.pay.controller;

import java.util.List;
import java.util.Map;

import org.lanqiao.pay.base.bean.TradePage;
import org.lanqiao.pay.base.entity.Recharge_withdrawal;
import org.lanqiao.pay.base.entity.Transfer;
import org.lanqiao.pay.base.util.MyUtils;
import org.lanqiao.pay.service.Recharge_withdrawalService;
import org.lanqiao.pay.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 王增
 *
 */
@Controller
@RequestMapping("/tradeController/")
public class TradeController {
	
	@Autowired
	Recharge_withdrawalService recharge_withdrawalService;//充值提现对应的Service
	@Autowired
	TransferService transferService;//转账对应的Service
	/*王增:对于trade下的trade_list的分页,现在由于提现和充值是在一张表中,而转账又是在另外一张表中.因此如果在同一个页面中显示的话
	 * 页面实现<c:if></c:if>判断,是哪一种类型的交易,而且由于交易类型不同,那么我们在分页向后台发送请求的时候,也会发不同的请求,
	 * 这就需要不同的映射方法
	 * */
	//充值和提现的分页
	@RequestMapping("/listTrade")
	public String listTrade(@RequestParam(value="pageNo",required=false)
							String pageNo,Map<String,Object> map,TradePage tradePage){
		map.put("listPageType", 0);//0表示trade_list页面为充值和提现
		//默认显示第一页(默认查询3个月内个人充值的发起方任意的交易状态任意的money任意的)
		if(pageNo==null||pageNo.trim().equals("")||!MyUtils.stringIsIntger(pageNo)){
			tradePage.setPageNo(1);
		}else{
			tradePage.setPageNo(Integer.parseInt(pageNo));
		}
		tradePage.dealParam();
		tradePage.dateSort();
		tradePage.moneySort();//对时间区间和金钱区间进行排序(小的在前,大的在后)
		//根据分页条件进行查询记录条数;
		Integer counts =  recharge_withdrawalService.getCountsByPage(tradePage);
		tradePage.setTotalRecords(counts);
		//根据分页条件查询记录
		List<Recharge_withdrawal> recharge_withdrawals =  recharge_withdrawalService.
				getRecharge_withdrawalsByPage(tradePage);
		System.out.println("counts:"+counts);
		for (Recharge_withdrawal recharge_withdrawal : recharge_withdrawals) {
			System.out.println(recharge_withdrawal);
		}
		map.put("tradePage", tradePage);
		map.put("recharge_withdrawals", recharge_withdrawals);
		System.out.println(tradePage);
		return "trade/trade_list";
	}

	//转账的分页
	@RequestMapping("/listTradeTransfer")
	public String listTradeTransfer(@RequestParam(value="pageNo",required=false)
					String pageNo,Map<String,Object> map,TradePage tradePage){
		//默认查询3个月内发起方任意交易状态任意金额任意的记录
		map.put("listPageType", 1);//1表示trade_list页面为转账页面
		if(pageNo==null||pageNo.trim().equals("")||!MyUtils.stringIsIntger(pageNo)){
			tradePage.setPageNo(1);
		}else{
			tradePage.setPageNo(Integer.parseInt(pageNo));
		}
		tradePage.dealParam();
		tradePage.dateSort();
		tradePage.moneySort();//对时间区间和金钱区间进行排序(小的在前,大的在后)
		System.out.println("listTradeTransfer的tradePage:"+tradePage);
		Integer counts = transferService.getCountsByPage(tradePage);
		tradePage.setTotalRecords(counts);
		List<Transfer> transfers = transferService.getTransfersByPage(tradePage);
		for (Transfer transfer : transfers) {
			System.out.println(transfer);
		}
		System.out.println("-------transfer:counts is "+counts);
		map.put("tradePage", tradePage);
		map.put("transfers", transfers);
		return "trade/trade_list";
	}
	
}
