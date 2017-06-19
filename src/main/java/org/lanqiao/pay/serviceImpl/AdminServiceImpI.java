package org.lanqiao.pay.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.lanqiao.pay.base.dao.AdminDao;
import org.lanqiao.pay.base.dao.EnterpriseUserDao;
import org.lanqiao.pay.base.dao.Recharge_withdrawalDao;
import org.lanqiao.pay.base.dao.TransferDao;
import org.lanqiao.pay.base.dao.UserDao;
import org.lanqiao.pay.base.entity.TimeBean;
import org.lanqiao.pay.base.entity.User;
import org.lanqiao.pay.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**   
* @Title: AdminServiceImp.java 
* @Package org.lanqiao.pay.serviceImp 
* @Description: TODO() 
* @author 西安工业大学蓝桥一期--陈楚  
* @date 2017年4月23日 下午11:20:32    
*/
@Service
public class AdminServiceImpI implements AdminService{
	@Autowired
	AdminDao adminDao;
	@Autowired
	UserDao userDao;
	@Autowired
	EnterpriseUserDao enterpriseUserDao;
	@Autowired
	Recharge_withdrawalDao recharge_withdrawalDao;
	@Autowired
	TransferDao transferDao;
	/**
	 * 
	* @author 西安工业大学蓝桥一期--陈楚
	* @Title: getAllUsers 
	* @Description: 获取所有用户信息 
	* @param @return
	* @return List<User>
	* @date 2017年4月30日 下午11:36:27 
	* @throws
	 */
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * @author 西安工业大学蓝桥一期--刘江 @Title:
	 *         getUsersByTime @Description:管理员通过时间查询用户注册数 @return List
	 *         <User> @date 2017年5月16日 下午8:00:05 @throws
	 */
	public List<TimeBean> getUsersByTime() {
		// 获取当前时间 ,格式 年-月-日
		ArrayList<TimeBean> arrayList = new ArrayList<TimeBean>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date); // 设置为当前时间
		for (int i = 0; i < 6; i++) {
			TimeBean tb = new TimeBean();
			tb.setEndTime(dateFormat.format(date));
			calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1); // 设置为上一个月
			date = calendar.getTime();
			String format = dateFormat.format(date);
			tb.setBeginTime(dateFormat.format(date));
			 adminDao.getUsersByTime(tb);
			tb.setUserNumber(adminDao.getUsersByTime(tb));
			tb.setEntNumber(enterpriseUserDao.getEntNumbersByReTime(tb));
			tb.setWithdrawal(recharge_withdrawalDao.GetEntNumber(tb));
			tb.setRechargs(recharge_withdrawalDao.GetUserNumber(tb));
			tb.setTransfer(transferDao.getTranNumber(tb));
			tb.setEnd(tb.endTime.substring(0,4)+"/"+tb.endTime.substring(5,7));
			arrayList.add(tb);
		}
		
		return arrayList;
	};
	
	public Integer[] getAllNumber(){
		Integer [] al=new Integer[]{userDao.getUsersNumber(),enterpriseUserDao.getEntNumber(),recharge_withdrawalDao.getDrNuber(),recharge_withdrawalDao.getReNumber(),transferDao.getTranNumberAll()};
		return al;
	}
}
