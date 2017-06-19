package org.lanqiao.pay.base.dao;

import java.util.List;

import org.lanqiao.pay.base.bean.CustomerCareCreteria;
import org.lanqiao.pay.base.entity.CustomerCare;
import org.lanqiao.pay.mapper.CustomerCareMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**   
 * @Title: CustomerCareDao.java 
 * @Package org.lanqiao.pay.base.dao 
 * @Description: 客服的Dao 
 * @author 西安工业大学蓝桥一期--毋泽航  
 * @date 2017年5月3日 下午8:11:53    
 */
@Repository
public class CustomerCareDao {
	
	@Autowired
	CustomerCareMapper customerCareMapper;
	
	/**
	 * @author 西安工业大学蓝桥一期--毋泽航
	 * @Title: addList 
	 * @Description: 批量增加客服的DAO方法 
	 * @param  list   批量的客服 
	 * @date 2017年5月4日 下午5:08:37 
	 * @throws
	 */
	public void addList(List<CustomerCare> list){
		customerCareMapper.addList(list);
	}

	
	/**
	 * 
	* @author 西安工业大学蓝桥一期--王婷
	* @Title: selectcustomerCarebyID 
	* @Description: 客服登陆
	* @param @param id
	* @param @return    设定文件 
	* @return CustomerCare    返回类型 
	* @date 2017年5月7日 下午6:05:58 
	* @throws
	 */
	public CustomerCare selectcustomerCarebyID(int id) {
		return customerCareMapper.selectcustomerCarebyID(id);
	}
	/**
	 * @author 西安工业大学蓝桥一期--毋泽航
	 * @Title: getAllCount 
	 * @Description: 获取分页的信息总数 
	 * @return Integer    
	 * @date 2017年5月8日 下午9:14:19 
	 * @throws
	 */
	public Integer getAllCount(CustomerCareCreteria customerCareCreteria){
		Integer count = customerCareMapper.getCustomerCareCount(customerCareCreteria);
		return count;
	}
	/**
	 * @author 西安工业大学蓝桥一期--毋泽航
	 * @Title: getAll 
	 * @Description: 获取分页的List 
	 * @return List<CustomerCare>    
	 * @date 2017年5月8日 下午9:14:42 
	 * @throws
	 */
	public List<CustomerCare> getAll(CustomerCareCreteria customerCareCreteria){
		List<CustomerCare> careList = customerCareMapper.getCustomerCareList(customerCareCreteria);
		return careList;
	}
	
	/**
	 * @author 西安工业大学蓝桥一期--毋泽航
	 * @Title: updateCustomerCare 
	 * @Description: 修改客服信息
	 * @date 2017年5月15日 下午5:53:29 
	 * @throws
	 */
	public void updateCustomerCare(CustomerCare customerCare){
		customerCareMapper.updateCustomerCare(customerCare);
	}
	
}
