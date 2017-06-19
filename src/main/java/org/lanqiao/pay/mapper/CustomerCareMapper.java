package org.lanqiao.pay.mapper;

import java.util.List;

import org.lanqiao.pay.base.bean.CustomerCareCreteria;
import org.apache.ibatis.annotations.Param;
import org.lanqiao.pay.base.entity.CustomerCare;

/**   
 * @Title: CustomerCareMapper.java 
 * @Package org.lanqiao.pay.mapper 
 * @Description: 关于客服操作的mapper接口 
 * @author 西安工业大学蓝桥一期--毋泽航  
 * @date 2017年5月3日 下午7:48:16    
 */
public interface CustomerCareMapper {
	
	/**
	 * @author 西安工业大学蓝桥一期--毋泽航
	 * @Title: addCustomerCareList 
	 * @Description: 批量增加客服
	 * @param list    
	 * @return void    
	 * @date 2017年5月3日 下午9:10:40 
	 * @throws
	 */
	public void addList(List<CustomerCare> list);
	/**
	 * @author 西安工业大学蓝桥一期--毋泽航
	 * @Title: getCustomerCareList 
	 * @Description: 获取分页的List 
	 * @return List<CustomerCare>    
	 * @date 2017年5月8日 下午9:12:57 
	 * @throws
	 */
	public List<CustomerCare> getCustomerCareList(CustomerCareCreteria customerCareCreteria);
	/**
	 * @author 西安工业大学蓝桥一期--毋泽航
	 * @Title: getCustomerCareCount 
	 * @Description: 获取分页的信息总数 
	 * @return Integer    
	 * @date 2017年5月8日 下午9:13:23 
	 * @throws
	 */
	public Integer getCustomerCareCount(CustomerCareCreteria customerCareCreteria);
	/**
	 * 
	* @author 西安工业大学蓝桥一期--王婷
	* @Title: selectcustomerCarebyID 
	* @Description: 查询客服 
	* @param @param id
	* @param @return    设定文件 
	* @return CustomerCare    返回类型 
	* @date 2017年5月7日 下午6:07:19 
	* @throws
	 */
	CustomerCare selectcustomerCarebyID(int id);
	/**
	 * @author 西安工业大学蓝桥一期--毋泽航
	 * @Title: updateCustomerCare 
	 * @Description: 修改客服的个人信息 
	 * @date 2017年5月15日 下午5:49:48 
	 * @throws
	 */
	public void updateCustomerCare(CustomerCare customerCare);
	
}
