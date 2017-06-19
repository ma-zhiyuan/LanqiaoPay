package org.lanqiao.pay.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.pay.base.bean.SelectBankCard;
import org.lanqiao.pay.base.entity.Bank;
import org.lanqiao.pay.base.entity.BankCard;
import org.lanqiao.pay.base.entity.EnterpriseUser;
import org.lanqiao.pay.base.entity.User;
import org.lanqiao.pay.base.util.MyUtils;
import org.lanqiao.pay.serviceImpl.BankCardServiceImpl;
import org.lanqiao.pay.serviceImpl.BankServiceImpl;
import org.lanqiao.pay.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 关于银行卡的业务
 * 
 * @author 西安工业大学蓝桥一期--刘江
 * @date 2017年5月3日 下午8:58:33
 */
@Controller
@RequestMapping("/BankCardController")
public class BankCardController {
	@Autowired
	BankCardServiceImpl bankCardServiceImpl;
	/**
	 * 
	 * @author 西安工业大学蓝桥一期--王增 @Title: cardIsUnique @Description: TODO
	 *         验证银行卡账号是否唯一(根据银行卡的账号得到银行卡) @param @param cardNumber @param @param
	 *         response @param @throws IOException 设定文件 @return void 返回类型 @date
	 *         2017年5月7日 上午11:43:17 @throws
	 */
	@RequestMapping("/cardIsUnique")
	public void cardIsUnique(@RequestParam("cardNumber") String cardNumber, HttpServletResponse response)
			throws IOException {
		System.out.println("cardNumber:" + cardNumber);
		BankCard bankCard = bankCardServiceImpl.getBankCardByCardNumber(cardNumber);
		PrintWriter out = response.getWriter();
		if (bankCard == null) {// 说明没有找到,则没人用过
			out.print(true);
		} else {
			out.print(false);
		}
	}
	/**
	 * @author 西安工业大学蓝桥一期--刘江 @Title: my_card_list @Description:
	 *         跳转到显示用户银行卡的页面 @return String @date 2017年5月7日 上午8:24:16 @throws
	 */
	@RequestMapping("/my_card_list")
		public String my_card_list(HttpServletRequest request, HttpServletResponse response) {
	User u=(User)request.getSession().getAttribute("user");
		List<BankCard> listBankCard = u.getListBankCard();
		MyUtils mu = new MyUtils();
		u.setListBankCard(mu.cardNumberEncryption( listBankCard));
		return "common/my_card_list";
	}
		/** 
	* @author 西安工业大学蓝桥一期--刘江
	* @Title: EnterBankCard 
	* @Description: 跳转到企业的银行卡页面
	* @return String   
	* @date 2017年6月15日 上午12:45:51 
	* @throws 
	*/
	@RequestMapping("/EnterBankCard")
	public String EnterBankCard(HttpServletRequest request, HttpServletResponse response) {
		EnterpriseUser u=(EnterpriseUser)request.getSession().getAttribute("enterpriseUser");
	List<BankCard> listBankCard = u.getEnterprise().getBankcards();
	MyUtils mu = new MyUtils();
	u.getEnterprise().setBankcards(mu.cardNumberEncryption( listBankCard));
	return "enterpriseUser/my_card_list";
}
	
	/**
	 * @author 西安工业大学蓝桥一期--刘江 @Title: isDefault @Description:
	 *         先修改原默认卡的值，然后将此卡设为默认卡 @return String @date 2017年5月7日
	 *         下午2:07:45 @throws
	 */
	@RequestMapping("/isDefault")
	public String isDefault(@RequestParam(value = "id") int cardid,HttpServletRequest request, HttpServletResponse response) {
		User u=(User)request.getSession().getAttribute("user");
		 bankCardServiceImpl.modifyIsDefault(cardid, u);
		return "common/my_card_list";

	}
		/** 
	* @author 西安工业大学蓝桥一期--刘江
	* @Title: 企业设置一张卡为默认卡
	* @Description: TODO
	* @return String   
	* @date 2017年6月15日 下午12:29:24 
	* @throws 
	*/
	@RequestMapping("/EntisDefault")
	public String EntisDefault(@RequestParam(value = "id") int cardid,HttpServletRequest request, HttpServletResponse response) {
		EnterpriseUser u=(EnterpriseUser)request.getSession().getAttribute("enterpriseUser");
		 bankCardServiceImpl.EntmodifyIsDefault(cardid,u );
		return "enterpriseUser/my_card_list";

	}
	/**
	 * @author 西安工业大学蓝桥一期--刘江 @Title:
	 *         shutQuickPay @Description:用户关闭银行卡的快捷支付 @return String @date
	 *         2017年5月7日 下午5:06:35 @throws
	 */
	@RequestMapping("shutQuickPay")
	public String shutQuickPay(@RequestParam(value = "id") int cardid,HttpServletRequest request, HttpServletResponse response) {
		User u=(User)request.getSession().getAttribute("user");
		 bankCardServiceImpl.shutQuickPay(cardid, u);
		return "common/my_card_list";
	}
	
		/** 
	* @author 西安工业大学蓝桥一期--刘江
	* @Title: EntshutQuickPay 
	* @Description: 企业关闭快捷支付
	* @return String   
	* @date 2017年6月15日 下午4:31:01 
	* @throws 
	*/
	@RequestMapping("EntshutQuickPay")
	public String EntshutQuickPay(@RequestParam(value = "id") int cardid,HttpServletRequest request, HttpServletResponse response) {
		EnterpriseUser u=(EnterpriseUser)request.getSession().getAttribute("enterpriseUser");
		 bankCardServiceImpl.EntshutQuickPay(cardid, u);
		 return "enterpriseUser/my_card_list";
	}
	/**
	 * @author 西安工业大学蓝桥一期--刘江 @Title: openQuickPay @Description:开启快捷支付 @return
	 *         String @date 2017年5月7日 下午5:27:41 @throws
	 */
	@RequestMapping("openQuickPay")
	public String openQuickPay(@RequestParam(value = "id") int cardid,HttpServletRequest request, HttpServletResponse response) {
		User u=(User)request.getSession().getAttribute("user");
		bankCardServiceImpl.modifyIsQuickPay(cardid,u);
		return "common/my_card_list";
	}
	
		/** 
	* @author 西安工业大学蓝桥一期--刘江
	* @Title: EntopenQuickPay 
	* @Description: 企业开启快捷支付
	* @return String   
	* @date 2017年6月15日 下午4:31:52 
	* @throws 
	*/
	@RequestMapping("EntopenQuickPay")
	public String EntopenQuickPay(@RequestParam(value = "id") int cardid,HttpServletRequest request, HttpServletResponse response) {
		EnterpriseUser u=(EnterpriseUser)request.getSession().getAttribute("enterpriseUser");
		bankCardServiceImpl.EntmodifyIsQuickPay(cardid,u);
		return "enterpriseUser/my_card_list";
	}
	/**
	 * @author 西安工业大学蓝桥一期--刘江 @Title: removeBankCard @Description:删除这张卡 @return
	 *         String @date 2017年5月8日 下午5:43:19 @throws
	 */
	@RequestMapping("removeBankCard")
	public String removeBankCard(@RequestParam(value = "id") int cardid,HttpServletRequest request, HttpServletResponse response) {
		User u=(User)request.getSession().getAttribute("user");
	         bankCardServiceImpl.removeBankCard(cardid, u);
		return "common/my_card_list";
	}
		/** 
	* @author 西安工业大学蓝桥一期--刘江
	* @Title: EntremoveBankCard 
	* @Description: 企业删除一张卡
	* @return String   
	* @date 2017年6月15日 下午4:34:04 
	* @throws 
	*/
	@RequestMapping("EntremoveBankCard")
	public String EntremoveBankCard(@RequestParam(value = "id") int cardid,HttpServletRequest request, HttpServletResponse response) {
		EnterpriseUser u=(EnterpriseUser)request.getSession().getAttribute("enterpriseUser");
		bankCardServiceImpl.EntremoveBankCard(cardid, u);
		return "enterpriseUser/my_card_list";
	}

	@RequestMapping("to_userAddBankCard")
	public String userAddBankCard() {

		return "common/AddBankCard";
	}

	/**
	 * @author 西安工业大学蓝桥一期--刘江 @Title: AddBankCard @Description: 检测卡号是否可用 @return
	 *         void @date 2017年5月12日 下午7:31:24 @throws
	 */
	@RequestMapping("AddBankCard")
	public void AddBankCard(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String msg;
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String cardNumber = request.getParameter("cardnumber");
		BankCard bkd = bankCardServiceImpl.getBankCardByCardNumber(cardNumber);
		if (bkd == null) {
			msg = "t";
		} else {
			msg = "f";
		}
		// 返回数据
		out.print(msg);
	}

	/**
	 * @author 西安工业大学蓝桥一期--刘江 @Title: AddBankCard_ @Description: 用户添加银行卡 @return
	 *         void @date 2017年5月12日 下午7:17:34 @throws
	 */
	@RequestMapping("to_userAddBankCard_")
	public String AddBankCard_(BankCard bd, HttpServletRequest request, HttpServletResponse response) {
		User u=(User)request.getSession().getAttribute("user");
		//添加卡
		bankCardServiceImpl.userAddBankCard(bd, u);
		return "common/my_card_list";
	}
	
		/** 
	* @author 西安工业大学蓝桥一期--刘江
	* @Title: EntAddBankCard_ 
	* @Description: 企业添加卡
	* @return String   
	* @date 2017年6月15日 下午7:12:50 
	* @throws 
	*/
	@RequestMapping("EntAddBankCard")
	public String EntAddBankCard_(BankCard bd, HttpServletRequest request, HttpServletResponse response) {
		EnterpriseUser u=(EnterpriseUser)request.getSession().getAttribute("enterpriseUser");
		//添加卡
		bankCardServiceImpl.EntuserAddBankCard(bd, u);
		return "enterpriseUser/my_card_list";
	}
		
	@RequestMapping("EnttoAddBankCard")
	public String EnttoAddBankCard(HttpServletRequest request, HttpServletResponse response) {
		return"enterpriseUser/AddBankCard";
	}
	
		/**
		 * 
		* @author 西安工业大学蓝桥一期--王向宇
		* @Title: selectBankCard 
		* @Description: TODO  选择银行卡页面
		* @param @param identity
		* @param @param mode
		* @param @param word
		* @param @return    设定文件 
		* @return String    返回类型 
		* @date 2017年5月11日 下午8:05:39 
		* @throws
		 */
		@RequestMapping("/selectBankCard")
		public String selectBankCard(@RequestParam(value="identity",required=false) String identity,
				@RequestParam(value="mode",required=false) String mode,@RequestParam(value="word",required=false) String word,
				HttpServletRequest request,HttpServletResponse response){
			SelectBankCard sbc;
			List<SelectBankCard> cards=new ArrayList<SelectBankCard>();
			if(mode.equals("卡号")){
				BankCard bc=bankCardServiceImpl.getBankCardByCardNumber(word);
				if(bc==null){
					return "admin/select_bankcard";
				}
				int num=bankCardServiceImpl.getTradingRecord(bc.getCardNumber());
				if(bc.getUserId()!=null){
					sbc=new SelectBankCard(bc.getCardNumber(),bc.getBankId().getName(),bc.getUserId().getName(),bc.getBalance(),num);
				}else{
					sbc=new SelectBankCard(bc.getCardNumber(),bc.getBankId().getName(),bc.getEnterpriseId().getEnterpriseName(),bc.getBalance(),num);
				}
				cards.add(sbc);
				request.setAttribute("cards",cards);
				return "admin/select_bankcard";
			}else if(identity.equals("个人")){
				if(mode.equals("昵称")){
					List<BankCard> bankcards=bankCardServiceImpl.getBankCardsByUserName(word);
					if(bankcards==null||bankcards.size()==0){
						return "admin/select_bankcard";
					}
					for (BankCard bc: bankcards) {
						int num=bankCardServiceImpl.getTradingRecord(bc.getCardNumber());
						sbc=new SelectBankCard(bc.getCardNumber(),bc.getBankId().getName(),bc.getUserId().getName(),bc.getBalance(),num);
						cards.add(sbc);
					}
					request.setAttribute("cards",cards);
					return "admin/select_bankcard";
				}else if(mode.equals("邮箱")){
					List<BankCard> bankcards=bankCardServiceImpl.getBankCardsByUserEmail(word);
					if(bankcards==null||bankcards.size()==0){
						return "admin/select_bankcard";
					}
					for (BankCard bc: bankcards) {
						int num=bankCardServiceImpl.getTradingRecord(bc.getCardNumber());
						sbc=new SelectBankCard(bc.getCardNumber(),bc.getBankId().getName(),bc.getUserId().getName(),bc.getBalance(),num);
						cards.add(sbc);
					}
					request.setAttribute("cards",cards);
					return "admin/select_bankcard";
				}
			}else if(identity.equals("企业")){
				if(mode.equals("昵称")){
					List<BankCard> bankcards=bankCardServiceImpl.getBankCardsByEnterpriseName(word);
					if(bankcards==null||bankcards.size()==0){
						return "admin/select_bankcard";
					}
					for (BankCard bc: bankcards) {
						int num=bankCardServiceImpl.getTradingRecord(bc.getCardNumber());
						sbc=new SelectBankCard(bc.getCardNumber(),bc.getBankId().getName(),bc.getEnterpriseId().getEnterpriseName(),bc.getBalance(),num);
						cards.add(sbc);
					}
					request.setAttribute("cards",cards);
				}else if(mode.equals("邮箱")){
					List<BankCard> bankcards=bankCardServiceImpl.getBankCardsByEnterpriseEmail(word);
					if(bankcards==null||bankcards.size()==0){
						return "admin/select_bankcard";
					}
					for (BankCard bc: bankcards) {
						int num=bankCardServiceImpl.getTradingRecord(bc.getCardNumber());
						sbc=new SelectBankCard(bc.getCardNumber(),bc.getBankId().getName(),bc.getEnterpriseId().getEnterpriseName(),bc.getBalance(),num);
						cards.add(sbc);
					}
					request.setAttribute("cards",cards);
					return "admin/select_bankcard";
				}
			}
			return "admin/select_bankcard";
		}
}