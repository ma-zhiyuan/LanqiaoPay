package org.lanqiao.pay.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.lanqiao.pay.base.bean.CustomerCareCreteria;
import org.lanqiao.pay.base.dao.CustomerCareDao;
import org.lanqiao.pay.base.entity.CustomerCare;
import org.lanqiao.pay.base.util.MyUtils;
import org.lanqiao.pay.service.CustomerCareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**   
 * @Title: CustomerCareServiceImpl.java 
 * @Package org.lanqiao.pay.serviceImpl 
 * @Description: TODO 
 * @author 西安工业大学蓝桥一期--毋泽航  
 * @date 2017年5月3日 下午7:51:02    
 */
@Service
public class CustomerCareServiceImpl implements CustomerCareService{
	
	@Autowired
	CustomerCareDao customerCareDao;
	
	/**
	 * @author 西安工业大学蓝桥一期--毋泽航
	 * @Title: getCustomerCareList 
	 * @Description: 分页显示客服的业务方法 
	 * @return List<CustomerCare>    
	 * @date 2017年5月8日 下午9:09:47 
	 * @throws
	 */
	public List<CustomerCare> getCustomerCareList(CustomerCareCreteria customerCareCreteria){
		Integer total = customerCareDao.getAllCount(customerCareCreteria);
		customerCareCreteria.setTotal(total);
		customerCareCreteria.setMaxPageNo();
		customerCareCreteria.setPageNoFrom();
		List<CustomerCare> list = customerCareDao.getAll(customerCareCreteria);
		return list;
	}
	
	
	/**
	 * @author 西安工业大学蓝桥一期--毋泽航
	 * @Title: insertList 
	 * @Description: 批量增加客服 
	 * @param  number 增加的个数
	 * @return List<CustomerCare> 返回到Controller层    
	 * @date 2017年5月4日 下午5:18:06 
	 * @throws
	 */
	@Override
	public List<CustomerCare> insertList(int number) {
		//用来插入的List
		List<CustomerCare> inList = new ArrayList<CustomerCare>();
		//返回到页面的List
		List<CustomerCare> reList = new ArrayList<CustomerCare>();
		//for创建出所需的客服
		for(int i = 0; i<number; i++){
			//放入inList的对象
			CustomerCare c1 = new CustomerCare();
			//放入reList的对象
			CustomerCare c2 = new CustomerCare();
			//自动生成六位随机数
			Integer k = (int) ((Math.random()*9+1)*100000);
			String pwd = k.toString();
			//未加密的放入reList
			c2.setPassword(pwd);
			reList.add(c2);
			//加密的放入inList
			String md5Pwd = MyUtils.md5(pwd).toLowerCase();
			String md5 = MyUtils.md5(md5Pwd);
			c1.setPassword(md5);
			inList.add(c1);
		}
		//插入数据库
		customerCareDao.addList(inList);
		//数据库返回的id赋值到将要返回的list中
		for(int i = 0;i<inList.size();i++){
			int id = inList.get(i).getId();
			reList.get(i).setId(id);
		}
		return reList;
	}
	
	
	/**
	 * 
	* @author 西安工业大学蓝桥一期--王婷
	* @Title: selectcustomerCarebyID 
	* @Description: 查询客服
	* @param @param id
	* @param @return    设定文件 
	* @return CustomerCare    返回类型 
	* @date 2017年5月7日 下午6:08:21 
	* @throws
	 */
	public CustomerCare selectcustomerCarebyID(int id) {
		return customerCareDao.selectcustomerCarebyID(id);
	}
	
	/**
	 * @author 西安工业大学蓝桥一期--毋泽航
	 * @Title: updateCustomerCarePwd 
	 * @Description: 修改客服信息(密码或启用状态都行) 
	 * @date 2017年5月15日 下午5:56:12 
	 * @throws
	 */
	public void updateCustomerCare(CustomerCare customerCare){
		customerCareDao.updateCustomerCare(customerCare);
	}
	
}
