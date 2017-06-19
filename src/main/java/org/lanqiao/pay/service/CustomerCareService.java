package org.lanqiao.pay.service;

import java.util.List;

import org.lanqiao.pay.base.entity.CustomerCare;
import org.springframework.stereotype.Service;

/**   
 * @Title: CustomerCareService.java 
 * @Package org.lanqiao.pay.service 
 * @Description: 客服的业务接口
 * @author 西安工业大学蓝桥一期--毋泽航  
 * @date 2017年5月2日 上午11:38:16    
 */
@Service
public interface CustomerCareService {
	/**
	 * @author 西安工业大学蓝桥一期--毋泽航
	 * @Title: insertList 
	 * @Description: 批量增加客服 
	 * @param  number 增加的个数
	 * @return List<CustomerCare> 返回到Controller层    
	 * @date 2017年5月4日 下午5:15:01 
	 * @throws
	 */
	public List<CustomerCare> insertList(int number);
}
