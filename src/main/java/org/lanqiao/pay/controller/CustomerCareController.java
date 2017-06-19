package org.lanqiao.pay.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import org.apache.poi.util.Removal;
import org.lanqiao.pay.base.bean.CustomerCareCreteria;
import org.lanqiao.pay.base.bean.Recharge_withdrawalCreteria;
import org.lanqiao.pay.base.bean.TransferCreteria;
import org.lanqiao.pay.base.bean.UserCreteria;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.lanqiao.pay.base.entity.BankCard;
import org.lanqiao.pay.base.entity.CustomerCare;
import org.lanqiao.pay.base.entity.Recharge_withdrawal;
import org.lanqiao.pay.base.entity.Transfer;
import org.lanqiao.pay.base.entity.User;
import org.lanqiao.pay.base.util.MyUtils;
import org.lanqiao.pay.serviceImpl.BankCardServiceImpl;
import org.lanqiao.pay.serviceImpl.BankServiceImpl;
import org.lanqiao.pay.serviceImpl.CustomerCareServiceImpl;
import org.lanqiao.pay.serviceImpl.TransferServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Title: CustomerCareController.java
 * @Package org.lanqiao.pay.controller
 * @Description: 与前端页面的控制跳转
 * @author 西安工业大学蓝桥一期--毋泽航
 * @date 2017年5月2日 上午11:16:53
 */
@RequestMapping("/customerServiceController")
@Controller
public class CustomerCareController {

	@Autowired
	CustomerCareServiceImpl customerCareServiceImpl;
	@Autowired
	BankServiceImpl bankServiceImpl;
	@Autowired
	TransferServiceImpl transferServiceImpl;

	/**
	 * @author 西安工业大学蓝桥一期--毋泽航 @Title: insertCustomerCareList @Description:
	 *         增加客服 @param map @return String 跳转的是制作报表的工具类 @date 2017年5月7日
	 *         上午11:13:17 @throws
	 */
	@RequestMapping("/insertCustomerCareList")
	public String insertCustomerCareList(@RequestParam(value = "number") Integer num, Map<String, Object> map) {
		List<CustomerCare> list = customerCareServiceImpl.insertList(num);
		map.put("beans", list);
		return "excelView";
	}

	/**
	 * @author 西安工业大学蓝桥一期--毋泽航 @Title: showAllCustomerCares @Description:
	 *         分页显示客服 @date 2017年5月8日 下午9:15:25 @throws
	 */
	@RequestMapping("/showAllCustomerCares")
	public String showAllCustomerCares(Map<String, Object> map, HttpServletRequest request) {
		String pageNo = request.getParameter("pno");
		String state = request.getParameter("state");
		String order = request.getParameter("order");
		String minCount = request.getParameter("minCount");
		String maxCount = request.getParameter("maxCount");
		if (pageNo == null || pageNo == "") {
			pageNo = "1";
		}
		if (state == null || state == "") {
			state = "2";
		}
		if (order == null || order == "") {
			order = "id asc";
		}
		if (minCount == null || minCount == "") {
			minCount = "0";
		}
		if (maxCount == null || maxCount == "") {
			maxCount = "10000";
		}
		CustomerCareCreteria customerCareCreteria = new CustomerCareCreteria();
		customerCareCreteria.setPageNo(Integer.valueOf(pageNo));
		customerCareCreteria.setState(Integer.valueOf(state));
		customerCareCreteria.setOrder(order);
		customerCareCreteria.setMinServiceCount(Integer.valueOf(minCount));
		customerCareCreteria.setMaxServiceCount(Integer.valueOf(maxCount));
		List<CustomerCare> careList = customerCareServiceImpl.getCustomerCareList(customerCareCreteria);
		map.put("Creteria", customerCareCreteria);
		map.put("CustomerCares", careList);
		return "admin/customer_service_listAll";
	}

	/**
	 * 去数据库查看所有的待处理的提现业务(分页)
	 * @author 孙航建
	 * @time 2017年5月7日 下午2:06:10
	 * @param map
	 * @return
	 */
	@RequestMapping("/to_customerCaer")
	public String to_customerCaer(Map<String, Object> map, HttpServletRequest request) {
		// 从页面获取属性
		String pageNo = request.getParameter("pno");
		String orderSelect = request.getParameter("orderSelect");
		String fuzzy = request.getParameter("fuzzy");
		if (pageNo == null || pageNo == "" || pageNo.equals("NaN")) {
			pageNo = "1";
		}
		if (orderSelect == null || orderSelect == "") {
			orderSelect = "time asc";
		}
		// 实例化一个bean用来进行分页查询
		Recharge_withdrawalCreteria rwc = new Recharge_withdrawalCreteria();
		rwc.setPageNo(Integer.valueOf(pageNo));
		rwc.setOrder(orderSelect);
		rwc.setFuzzy(fuzzy);
		rwc.setState(0);
		rwc.setPageNoFrom();
		// 查询到符合条件的信息总数
		Integer count = bankServiceImpl.getRecharge_withdrawalCount(rwc);
		rwc.setTotal(count);
		rwc.setMaxPageNo();
		// 查询到的分页的list
		List<Recharge_withdrawal> rw = bankServiceImpl.getRecharge_withdrawalList(rwc);
		// 传到页面
		map.put("customerList", rw);
		map.put("Creteria", rwc);
		return "bank/customerCaer";
	}
	/**
	 * 获取所有的
	 * @author 孙航建
	 * @time 2017年5月8日 下午7:27:23
	 * @param map
	 * @return
	 */
	@RequestMapping("/to_customerCaerYes")
	public String to_customerCaerYes(Map<String, Object> map, HttpServletRequest request) {
		// 从页面获取属性
		String pageNo = request.getParameter("pno");
		String orderSelect = request.getParameter("orderSelect");
		String fuzzy = request.getParameter("fuzzy");
		if (pageNo == null || pageNo == "" || pageNo.equals("NaN")) {
			pageNo = "1";
		}
		if (orderSelect == null || orderSelect == "") {
			orderSelect = "time asc";
		}
		// 实例化一个bean用来进行分页查询
		Recharge_withdrawalCreteria rwc = new Recharge_withdrawalCreteria();
		rwc.setPageNo(Integer.valueOf(pageNo));
		rwc.setOrder(orderSelect);
		rwc.setFuzzy(fuzzy);
		rwc.setState(1);
		rwc.setPageNoFrom();
		// 查询到符合条件的信息总数
		Integer count = bankServiceImpl.getRecharge_withdrawalCount(rwc);
		rwc.setTotal(count);
		rwc.setMaxPageNo();
		// 查询到的分页的list
		List<Recharge_withdrawal> rw = bankServiceImpl.getRecharge_withdrawalList(rwc);
		// 传到页面
		map.put("customerList", rw);
		map.put("Creteria", rwc);
		return "bank/customerCaerYes";
	}
	
	/**
	 * 去数据库查看所有的企业提现记录
	 * @author 孙航建
	 * @time 2017年5月17日 下午6:08:05
	 * @param map
	 * @return
	 */
	@RequestMapping("/to_customerCaer_Enterprise")
	public String to_customerCaer_Enterprise(Map<String, Object> map, HttpServletRequest request) {
		// 从页面获取属性
		String pageNo = request.getParameter("pno");
		String orderSelect = request.getParameter("orderSelect");
		String fuzzy = request.getParameter("fuzzy");
		if (pageNo == null || pageNo == "" || pageNo.equals("NaN")) {
			pageNo = "1";
		}
		if (orderSelect == null || orderSelect == "") {
			orderSelect = "time asc";
		}
		// 实例化一个bean用来进行分页查询
		Recharge_withdrawalCreteria rwc = new Recharge_withdrawalCreteria();
		rwc.setPageNo(Integer.valueOf(pageNo));
		rwc.setOrder(orderSelect);
		rwc.setFuzzy(fuzzy);
		rwc.setState(3);
		rwc.setPageNoFrom();
		// 查询到符合条件的信息总数
		Integer count = bankServiceImpl.getEnterpriseRecharge_withdrawalCount(rwc);
		rwc.setTotal(count);
		rwc.setMaxPageNo();
		// 获取所有的记录
		List<Recharge_withdrawal> rww = bankServiceImpl.getAllEnterpriseWithdrawallist(rwc);
		map.put("Creteria2", rwc);
		map.put("enterpriseCustlist", rww);
		return "bank/customer_enterprise";
	}

	/**
	 * 客服查看所有企业已经审核的提现
	 * @author 孙航建
	 * @time 2017年5月20日 下午5:41:58
	 * @param map
	 * @return
	 */
	@RequestMapping("/to_withdrawal_enterprise_true")
	public String to_withdrawal_enterprise_true(Map<String, Object> map,HttpServletRequest request) {
		// 从页面获取属性
		String pageNo = request.getParameter("pno");
		String orderSelect = request.getParameter("orderSelect");
		String fuzzy = request.getParameter("fuzzy");
		if (pageNo == null || pageNo == "" || pageNo.equals("NaN")) {
			pageNo = "1";
		}
		if (orderSelect == null || orderSelect == "") {
			orderSelect = "time asc";
		}
		// 实例化一个bean用来进行分页查询
		Recharge_withdrawalCreteria rwc = new Recharge_withdrawalCreteria();
		rwc.setPageNo(Integer.valueOf(pageNo));
		rwc.setOrder(orderSelect);
		rwc.setFuzzy(fuzzy);
		rwc.setState(4);
		rwc.setPageNoFrom();
		// 查询到符合条件的信息总数
		Integer count = bankServiceImpl.getEnterpriseRecharge_withdrawalCount(rwc);
		rwc.setTotal(count);
		rwc.setMaxPageNo();
		// 获取所有的记录
		List<Recharge_withdrawal> rwww = bankServiceImpl.getAllEnterpriseWithdrawallist(rwc);
		map.put("Creteria2", rwc);
		map.put("enterpriseCustw", rwww);
		return "bank/customer_Enyes";
	}

	/**
	 * 客服端实现个人提现的具体操作
	 * @author 孙航建
	 * @time 2017年5月7日 下午6:20:46
	 * @param id
	 * 页面传入的提现表的id
	 * @return
	 */
	@RequestMapping("/to_withdrawalTrue")
	public String to_withdrawalTrue(Integer id) {
		// 通过id获取一个对象
		Recharge_withdrawal re = bankServiceImpl.getOneWithdrawal(id);
		if (re != null) {
			// 去数据库更新
			String str = bankServiceImpl.updateUserAndBankCardMoney(re);
			if (str.equals("成功")) {
				return "redirect:/customerServiceController/to_customerCaer";
			}
		}
		return "bank/fail";
	}

	/**
	 * 客服端实现企业提现的具体操作
	 * @author 孙航建
	 * @time 2017年5月19日 下午5:22:04
	 * @return
	 */
	@RequestMapping("/to_withdrawal_enterprise_false")
	public String to_withdrawal_enterprise_true(Integer id) {
		// 通过id获取一个对象
		Recharge_withdrawal re = bankServiceImpl.getOneWithdrawal(id);
		if (re != null) {
			// 去数据库更新
			String str = bankServiceImpl.updateEnterpriseUserAndBankCardMoney(re);
			if (str.equals("成功")) {
				return "redirect:/customerServiceController/to_customerCaer_Enterprise";
			}
		}
		return "bank/fail";
	}

	@RequestMapping("/loginCustomer")
	public String loginCustomer(CustomerCare customerCare, HttpServletRequest request, Model model,
			HttpSession session) {
		CustomerCare c = customerCareServiceImpl.selectcustomerCarebyID(customerCare.getId());
		if ((c.getPassword()).equals(MyUtils.md5(customerCare.getPassword()))) {
			session.setAttribute("customer", c);
			// 成功跳至目标页面
			return "redirect:/customerServiceController/goCustomerHome";
		} else {
			// 失败返回到登陆界面
			return "redirect:/customerServiceController/goCustomer";
		}
	}
	//客服主页面
	@RequestMapping("/goCustomerHome")
	public String goCustomerHome(HttpServletRequest request){
		//提现未审核数目
		Recharge_withdrawalCreteria rwc = new Recharge_withdrawalCreteria();
		rwc.setState(0);
		Integer personCount = bankServiceImpl.getRecharge_withdrawalCount(rwc);
		rwc.setState(3);
		Integer enterpriseCount = bankServiceImpl.getEnterpriseRecharge_withdrawalCount(rwc);
		//转账未审核数目
		TransferCreteria tc = new TransferCreteria();
		tc.setState(0);
		tc.setTransferType("ptp");
		Integer ptpCount = transferServiceImpl.getTransferListCount(tc);
		tc.setTransferType("etp");
		Integer etpCount = transferServiceImpl.getTransferListCount(tc);
		tc.setTransferType("pte");
		Integer pteCount = transferServiceImpl.getTransferListCount(tc);
		tc.setTransferType("ete");
		Integer eteCount = transferServiceImpl.getTransferListCount(tc);
		request.setAttribute("personCount", personCount);
		request.setAttribute("enterpriseCount", enterpriseCount);
		request.setAttribute("ptpCount", ptpCount);
		request.setAttribute("etpCount", etpCount);
		request.setAttribute("pteCount", pteCount);
		request.setAttribute("eteCount", eteCount);
		return "customer/customer_home";
	}
	//去登录页面
	@RequestMapping("/goCustomer")
	public void goCustomer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("customer")!=null){
			session.removeAttribute("customer");
		}
		request.getRequestDispatcher("/customer_login.jsp").forward(request, response);
	}

	/**
	 * @throws IOException
	 * @author 西安工业大学蓝桥一期--毋泽航 @Title: updateCustomerServicePwd @Description:
	 *         修改客服密码(只能用于管理员修改) @date 2017年5月15日 下午6:27:54 @throws
	 */
	@RequestMapping("/updateCustomerServicePwd")
	public void updateCustomerServicePwd(HttpServletResponse response, HttpServletRequest request) throws IOException {
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		String password = request.getParameter("newPayPassword");
		CustomerCare customerCare = new CustomerCare();
		String md5 = MyUtils.md5(password);
		customerCare.setPassword(md5);
		customerCare.setId(Integer.valueOf(id));
		customerCareServiceImpl.updateCustomerCare(customerCare);
		out.write("true");
	}

	/**
	 * @author 西安工业大学蓝桥一期--毋泽航 @Title: updateCustomerServiceState @Description:
	 *         修改客服状态(要不要启用) @param @param map @param @param
	 *         request @param @param id @param @param state @return void @date
	 *         2017年5月15日 下午6:31:15 @throws
	 */
	@RequestMapping("updateCustomerServiceState")
	public String updateCustomerServiceState(@RequestParam(value = "id") Integer id,
			@RequestParam(value = "state") Integer state) {
		CustomerCare customerCare = new CustomerCare();
		customerCare.setId(id);
		if (state == 1) {
			customerCare.setState(0);
		} else {
			customerCare.setState(1);
		}
		customerCareServiceImpl.updateCustomerCare(customerCare);
		return "redirect:/customerServiceController/showAllCustomerCares";
	}
	/**
	 * @author 西安工业大学蓝桥一期--毋泽航
	 * @Title: toCustomerTransfer_personToPerson 
	 * @Description: 客服分页查看个人向个人的转账 
	 * @date 2017年6月8日 下午5:04:59 
	 * @throws
	 */
	@RequestMapping("toCustomerTransfer_personToPerson")
	public String toCustomerTransfer_personToPerson(Map<String, Object> map,HttpServletRequest request){
		String pageNo = request.getParameter("pno");
		String orderSelect = request.getParameter("orderSelect");
		String state = request.getParameter("state");
		if(state == null || state == ""){
			state = "0";
		}
		if (pageNo == null || pageNo == "" || pageNo.equals("NaN")) {
			pageNo = "1";
		}
		if (orderSelect == null || orderSelect == "") {
			orderSelect = "time asc";
		}
		TransferCreteria tc = new TransferCreteria();
		tc.setPageNo(Integer.valueOf(pageNo));
		tc.setOrder(orderSelect);
		tc.setTransferType("ptp");
		tc.setState(Integer.valueOf(state));
		tc.setPageNoFrom();
		//模拟
		Integer count = transferServiceImpl.getTransferListCount(tc);
		tc.setTotal(count);
		tc.setMaxPageNo();
		//模拟 查询到的分页的list
		List<Transfer> ctl = transferServiceImpl.getTransferList(tc);
		// 传到页面
		map.put("transferList", ctl);
		map.put("Creteria", tc);
		if(state.equals("1")){
			return "customer/customer_personToPersonYes";
		}
		return "customer/customer_personToPersonNo";
	}
	/**
	 * @author 西安工业大学蓝桥一期--毋泽航
	 * @Title: toCustomerTransfer_personToEnterprise 
	 * @Description: 客服分页查看个人向企业的转账 
	 * @date 2017年6月8日 下午5:05:39 
	 * @throws
	 */
	@RequestMapping("toCustomerTransfer_personToEnterprise")
	public String toCustomerTransfer_personToEnterprise(Map<String, Object> map,HttpServletRequest request){
		String pageNo = request.getParameter("pno");
		String state = request.getParameter("state");
		String orderSelect = request.getParameter("orderSelect");
		if(state == null || state == ""){
			state = "0";
		}
		if (pageNo == null || pageNo == "" || pageNo.equals("NaN")) {
			pageNo = "1";
		}
		if (orderSelect == null || orderSelect == "") {
			orderSelect = "time asc";
		}
		TransferCreteria tc = new TransferCreteria();
		tc.setPageNo(Integer.valueOf(pageNo));
		tc.setOrder(orderSelect);
		tc.setTransferType("pte");
		tc.setState(Integer.valueOf(state));
		tc.setPageNoFrom();
		//模拟
		Integer count = transferServiceImpl.getTransferListCount(tc);
		tc.setTotal(count);
		tc.setMaxPageNo();
		//模拟 查询到的分页的list
		List<Transfer> ctl = transferServiceImpl.getTransferList(tc);
		// 传到页面
		map.put("transferList", ctl);
		map.put("Creteria", tc);
		if(state.equals("1")){
			return "customer/customer_personToEnterpriseYes";
		}
		return "customer/customer_personToEnterpriseNo";
	}
	/**
	 * @author 西安工业大学蓝桥一期--毋泽航
	 * @Title: toCustomerTransfer_enterpriseToPerson 
	 * @Description: 客服分页查看企业向个人的转账 
	 * @date 2017年6月8日 下午5:06:07 
	 * @throws
	 */
	@RequestMapping("toCustomerTransfer_enterpriseToPerson")
	public String toCustomerTransfer_enterpriseToPerson(Map<String, Object> map,HttpServletRequest request){
		String pageNo = request.getParameter("pno");
		String state = request.getParameter("state");
		String orderSelect = request.getParameter("orderSelect");
		if(state == null || state == ""){
			state = "0";
		}
		if (pageNo == null || pageNo == "" || pageNo.equals("NaN")) {
			pageNo = "1";
		}
		if (orderSelect == null || orderSelect == "") {
			orderSelect = "time asc";
		}
		TransferCreteria tc = new TransferCreteria();
		tc.setPageNo(Integer.valueOf(pageNo));
		tc.setOrder(orderSelect);
		tc.setTransferType("etp");
		tc.setState(Integer.valueOf(state));
		tc.setPageNoFrom();
		//模拟
		Integer count = transferServiceImpl.getTransferListCount(tc);
		tc.setTotal(count);
		tc.setMaxPageNo();
		//模拟 查询到的分页的list
		List<Transfer> ctl = transferServiceImpl.getTransferList(tc);
		// 传到页面
		map.put("transferList", ctl);
		map.put("Creteria", tc);
		if(state.equals("1")){
			return "customer/customer_enterpriseToPersonYes";
		}
		return "customer/customer_enterpriseToPersonNo";
	}
	/**
	 * @author 西安工业大学蓝桥一期--毋泽航
	 * @Title: toCustomerTransfer_enterpriseToEnterprise 
	 * @Description: 客服分页查看企业向企业的转账 
	 * @date 2017年6月8日 下午5:06:33 
	 * @throws
	 */
	@RequestMapping("toCustomerTransfer_enterpriseToEnterprise")
	public String toCustomerTransfer_enterpriseToEnterprise(Map<String, Object> map,HttpServletRequest request){
		String pageNo = request.getParameter("pno");
		String state = request.getParameter("state");
		String orderSelect = request.getParameter("orderSelect");
		if(state == null || state == ""){
			state = "0";
		}
		if (pageNo == null || pageNo == "" || pageNo.equals("NaN")) {
			pageNo = "1";
		}
		if (orderSelect == null || orderSelect == "") {
			orderSelect = "time asc";
		}
		TransferCreteria tc = new TransferCreteria();
		tc.setPageNo(Integer.valueOf(pageNo));
		tc.setOrder(orderSelect);
		tc.setTransferType("ete");
		tc.setState(Integer.valueOf(state));
		tc.setPageNoFrom();
		//模拟
		Integer count = transferServiceImpl.getTransferListCount(tc);
		tc.setTotal(count);
		tc.setMaxPageNo();
		//模拟 查询到的分页的list
		List<Transfer> ctl = transferServiceImpl.getTransferList(tc);
		// 传到页面
		map.put("transferList", ctl);
		map.put("Creteria", tc);		
		if(state.equals("1")){
			return "customer/customer_enterpriseToEnterpriseYes";
		}
		return "customer/customer_enterpriseToEnterpriseNo";
	}
		/**
	 * 
	* @author 西安工业大学蓝桥一期--何璐
	* @Title: toTransferTrue 
	* @Description: TODO  客服操作转账的具体实现 
	* @param @param id
	* @param @return    设定文件 
	* @return String    返回类型 
	* @date 2017年6月6日 下午8:52:28 
	* @throws
	 */
	@RequestMapping("/toTransferTrue")
	public String toTransferTrue(Integer id){
		Transfer transfer = transferServiceImpl.getTransferByID(id);
		if (transfer != null) {
			// 去数据库更新转账的状态
			bankServiceImpl.updateTransfer(id);
			// 获取转入方的银行卡
			BankCard toBankCard = transfer.getToBankCard();
			// 判断转入方的银行卡是否为空
			//为空,更改转入方即企业用户的余额
			if (toBankCard == null) {
				bankServiceImpl.updateEnterPriseUserBalance(transfer);
			//不为空,更改转入方的银行卡余额
			} else {
				bankServiceImpl.updateEnterPriseBankCardBalance(transfer);
			}
		}
		return "redirect:/customerServiceController/toCustomerTransfer_personToEnterprise";
	}
	
	
	
}
