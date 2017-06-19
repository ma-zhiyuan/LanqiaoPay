package org.lanqiao.pay.base.dao;

import java.util.List;

import org.lanqiao.pay.base.entity.TimeBean;
import org.lanqiao.pay.base.entity.User;
import org.lanqiao.pay.mapper.AdminMapper;
import org.lanqiao.pay.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author chuchen
 *
 */
@Repository
public class AdminDao {
	@Autowired
	AdminMapper adminMapper;
	@Autowired
	UserMapper userMapper;
		/** 
	* @author 西安工业大学蓝桥一期--刘江
	* @Title: getUsersByTime 
	* @Description:管理员通过时间查询用户注册数
	* @return List<User>   
	* @date 2017年5月16日 下午8:00:05 
	* @throws 
	*/
	public Integer getUsersByTime(TimeBean tb){
		Integer userNumber = userMapper.getUsersByTime(tb);
		return userNumber;
	};
}
