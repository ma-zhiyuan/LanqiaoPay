/**   
* @Title: MyExceptionHandler.java 
* @Package org.lanqiao.pay.controller 
* @Description: TODO
* @author 西安工业大学蓝桥一期--王增   
* @date 2017年4月29日 上午11:38:46    
*/

package org.lanqiao.pay.controller;

import org.lanqiao.pay.base.exception.DirectoryNotFound;
import org.lanqiao.pay.base.exception.NotUniqueException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 王增
 *
 */
@ControllerAdvice
public class MyExceptionHandler {
	@ExceptionHandler(value={DirectoryNotFound.class})
	public ModelAndView handleDirectoryNotFound(DirectoryNotFound ex){
			System.out.println(ex);
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("exception");
			modelAndView.addObject("exception", ex);
			return modelAndView;
	}
	
	@ExceptionHandler(value={NotUniqueException.class})
	public ModelAndView handleNotUniqueException(NotUniqueException ex){
			System.out.println(ex);
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("exception");
			modelAndView.addObject("exception", ex);
			return modelAndView;
	}
}
