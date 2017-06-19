package org.lanqiao.pay.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.pay.base.bean.BankCreteria;
import org.lanqiao.pay.base.bean.BankPage;
import org.lanqiao.pay.base.bean.EnterpriseUserCreteria;
import org.lanqiao.pay.base.bean.UserCreteria;
import org.lanqiao.pay.base.entity.Bank;
import org.lanqiao.pay.base.entity.EnterpriseUser;
import org.lanqiao.pay.base.entity.EnterpriseUserPage;
import org.lanqiao.pay.base.entity.TimeBean;
import org.lanqiao.pay.base.entity.User;
import org.lanqiao.pay.base.util.MyUtils;
import org.lanqiao.pay.service.EnterpriseUserService;
import org.lanqiao.pay.serviceImpl.AdminServiceImpI;
import org.lanqiao.pay.serviceImpl.BankServiceImpl;
import org.lanqiao.pay.serviceImpl.EnterpriseUserServiceImpl;
import org.lanqiao.pay.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

@Controller
@RequestMapping("/adminController")
public class AdminController {
	@Autowired
	BankPage bankPage;
	@Autowired
	BankServiceImpl bankServiceImpl;
	@Autowired
	AdminServiceImpI adminServiceImpI;
	@Autowired
	EnterpriseUserServiceImpl enterpriseUserServiceImpl;
	@Autowired
	EnterpriseUserService enterpriseUserService;

	@Autowired
	UserServiceImpl userServiceImpI;

	/*
	 * @Autowired UserCreteria userCreteria;
	 * 
	 * @Autowired EnterpriseUserCreteria enterpriceUserCreteria;
	 */
	/**
	 * @author 西安工业大学蓝桥一期--马志远
	 * @Title: loginAdmin
	 * @Description: 管理员登录
	 * @param @param
	 *            userName 管理员用户名
	 * @param @param
	 *            password 密码
	 * @param @param
	 *            request 请求域对象
	 * @param @param
	 *            response 响应域对像
	 * @param @throws
	 *            IOException
	 * @param @throws
	 *            ServletException
	 * @return String
	 * @date 2017年4月29日 下午1:28:33
	 */
	@RequestMapping("/loginAdmin")
	public String loginAdmin(String userName, String password, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		if ("root".equals(userName) && "123".equals(password)) {
			Integer[] al = adminServiceImpI.getAllNumber();
			request.getSession().setAttribute("al", al);
			return "admin/admin_index";
			
		} else {
			
			return "forward:tologin";
		}
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期--马志远
	 * @Title: tologin
	 * @Description: 去登录跳转页面
	 * @param @param
	 *            request
	 * @param @param
	 *            response
	 * @param @throws
	 *            IOException
	 * @param @throws
	 *            ServletException
	 * @return void
	 * @date 2017年4月29日 下午1:29:55
	 */
	@RequestMapping("/tologin")
	public void tologin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.getRequestDispatcher("/admin_login.jsp").forward(request, response);
	}

	@RequestMapping("/goIndex")
	public String goIndex(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		return "admin/admin_index";
	}

	@RequestMapping("/goHome")
	public String goHome(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		return "admin/home";
	}

	@RequestMapping("/goSelect_bankcard")
	public String goSelect_bankcard(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		return "admin/select_bankcard";
	}

	/**
	 * @author 西安工业大学蓝桥一期--陈楚 @Title: user_listAll @Description:
	 * 个人用户信息展示(无条件的) @param @param request @param @param response @param @param
	 * map @param @return @param @throws IOException @param @throws
	 * ServletException @return String @date 2017年5月3日 下午1:29:01 @throws
	 */
	@RequestMapping("/goUser_listAll")
	public String user_listAll(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map)
			throws IOException, ServletException {
		String pageNo = request.getParameter("pno") == null ? "1" : request.getParameter("pno");
		String orderby = request.getParameter("orderby") == null ? "id asc" : request.getParameter("orderby");
		String keyword = request.getParameter("keyword") == null ? "" : request.getParameter("keyword");
		// 获取全部用户（有条件的）信息
		UserCreteria userCreteria = userServiceImpI.getuserByqualified(keyword, pageNo, orderby);
		// 获取符合查找条件的所有信息
		List<User> page = userServiceImpI.getPage(userCreteria);
		map.put("users", page);
		map.put("userCreteria", userCreteria);
		return "admin/user_listAll";
	}

	/**
	 * 
	 * @author 西安工业大学蓝桥一期—陈楚 @Title: enterprise_listAll @Description:
	 * 企业用户信息展示 @param @param request @param @param
	 * response @param @return @param @throws IOException @param @throws
	 * ServletException 设定文件 @return String 返回类型 @date 2017年5月12日
	 * 下午8:33:21 @throws
	 */
	@RequestMapping("/goEnterprise_listAll")
	public String enterprise_listAll(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map)
			throws IOException, ServletException {
		String orderby = request.getParameter("orderby") == null ? "id asc" : request.getParameter("orderby");
		String keyword = request.getParameter("keyword") == null ? "" : request.getParameter("keyword");
		String pageNo = request.getParameter("pno") == null ? "1" : request.getParameter("pno");
		// 获取有此关键字的所有记录的条数
		EnterpriseUserCreteria enterpriceUserCreteria = enterpriseUserServiceImpl.getuserByqualified(keyword, pageNo,
				orderby);
		// 所有符合条件的信息
		List<EnterpriseUser> page = enterpriseUserServiceImpl.getPage(enterpriceUserCreteria);

		map.put("enterPriseUser", page);
		map.put("enterpriceUserCreteria", enterpriceUserCreteria);
		return "admin/enterprise_listAll";
	}

	@RequestMapping("/goCustomer_service_listAll")
	public String customer_service_listAll(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		return "admin/customer_service_listAll";
	}

	@RequestMapping("/goCustomer_service_add")
	public String customer_service_add(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		return "admin/customer_service_add";
	}

	/** 
	 	 *  
	 	 *  
		* @author 西安工业大学蓝桥一期--王荣飞 
	 	* @Title: listAllBank 	 
	 	* @Description: TODO 
	 	* @param @param request 
	 * @param @param response 
	 	* @param @param bankCreteria 
	 * @param @param map 
	 	* @param @return    设定文件  
	 	* @return String 
	 	* @date 2017年5月14日 下午12:01:22  
	 	* @throws 
	 	 */ 
	 	@RequestMapping("/goBankList") 
		public String listAllBank(HttpServletRequest request,HttpServletResponse response,BankCreteria bankCreteria,Map<String, Object> map){ 
	 		 
	 	System.out.println(111); 
	 	 
	 		if(bankCreteria.getPageNo()==null){ 
	 			bankCreteria.setPageNo(1); 
			} 
	 		if(bankCreteria.getOrderby()==null||bankCreteria.getOrderby()==""){ 
	 			bankCreteria.setOrderby("id asc"); 
	 		} 
	 		if(bankCreteria.getKeyword()==null||bankCreteria.getKeyword()==""){ 
	 			bankCreteria.setKeyword(""); 
				 
	 		} 
	 		 
	 			BankCreteria bc=new BankCreteria(bankCreteria.getPageNo(), bankCreteria.getOrderby(), bankCreteria.getKeyword(), bankCreteria.getPageSize(),bankCreteria.getBegin()); 
			System.out.println(bc); 
	 		System.out.println(bankCreteria.getKeyword()); 
	 		int total=bankServiceImpl.gettotal(bankCreteria.getKeyword()); 
	 		System.out.println(total); 
	 	int totalPage=0; 
	 	if(total%bankCreteria.getPageSize()==0){ 
	 			totalPage=total/bankCreteria.getPageSize(); 
		}else{ 
	 			totalPage=total/bankCreteria.getPageSize()+1; 
			} 
	 		System.out.println(totalPage); 
			List<Bank> banks=bankServiceImpl.getPage(bc); 
			for(Bank bank:banks){
				int count=bankServiceImpl.getCardNumById(bank.getId());
				bank.setCardNum(count);
			}
			
	 		bankPage.setBanks(banks); 
			bankPage.setTotalPage(totalPage); 
	 		System.out.println(bankPage); 
	 		map.put("bankCreteria", bc); 
			map.put("bankPage",bankPage);
			map.put("total", total);
	 		return "admin/banklist"; 
	 		 
	 	} 


	/**
	 * 
	 * @author 王增 @Title: listEnterpriseUserByPage @Description: TODO
	 * 对企业用户进行分页〉 @param @param index @param @param
	 * enterpriseUserPage @param @param map @param @return 璁惧畾鏂囦欢 @return String
	 * 杩斿洖绫诲瀷 @date 2017骞�5鏈�6鏃� 涓嬪崍1:42:03 @throws
	 */
	@RequestMapping("/enterpriseUser_listForAdmin") // 涔嬫墍浠ヤ娇鐢⊿tring绫诲瀷鐨刬ndex,鍥犱负鍓嶅彴鍙兘浼犳潵鐨勬槸绌轰覆
	public String listEnterpriseUserByPage(@RequestParam(value = "index", required = false) String index,
			EnterpriseUserPage enterpriseUserPage, Map<String, Object> map) {
		// 瀵逛簬涓嶅悎娉曠殑index,缁熺粺璧嬩负绗竴椤�
		if (index == null || index.trim().equals("") || !MyUtils.stringIsIntger(index)) {
			enterpriseUserPage.setPageNo(1);
		} else {
			enterpriseUserPage.setPageNo(Integer.parseInt(index));
		}
		/*
		 * enterpriseUserPage.setIsApprove(1);
		 * enterpriseUserPage.setRegistTimeStr("2017-05-01 13:26:36");
		 * enterpriseUserPage.setEmailKeyWord("25");
		 */
		// 鏌ヨ绗﹀悎鏉′欢鐨勮褰曟潯鏁�
		System.out.println("enterpriseUserService:"+enterpriseUserService);
		Integer count = enterpriseUserServiceImpl.getCountByPage(enterpriseUserPage);
		System.out.println("count:" + count);
		enterpriseUserPage.setCount(count);
		System.out.println("enterpriseUserPage:" + enterpriseUserPage);
		// 鏌ヨ绗﹀悎鏉′欢鐨別nterpriseUser,骞惰繛甯︿粬浠殑enterprise鐨刵ame鍜宨d(鍦ㄤ笉绾ц仈涓嬪幓浜�)
		List<EnterpriseUser> enterpriseUsers = enterpriseUserService.getAllByPage(enterpriseUserPage);
		// System.out.println(enterpriseUsers);
		for (EnterpriseUser enterpriseUser : enterpriseUsers) {
			System.out.println(enterpriseUser);
		}

		// enterpriseUser
		map.put("enterpriseUserPage", enterpriseUserPage);
		map.put("enterpriseUsers", enterpriseUsers);
		return "admin/enterpriseUser_listForAdmin";
	}
	/** 
	* @author 西安工业大学蓝桥一期--刘江
	* @Title: getUsersByTime 
	* @Description: 获取管理界面首页的数据
	* @return void   
	* @date 2017年5月31日 下午8:05:28 
	* @throws 
	*/
	@RequestMapping("getall")
	public void getUsersByTime(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("utf-8");
		Gson gson = new Gson();
		List<TimeBean> list=null;
		list= adminServiceImpI.getUsersByTime();
		JsonElement jsonTree = gson.toJsonTree(list);
		response.getWriter().print(jsonTree);
	}
}
