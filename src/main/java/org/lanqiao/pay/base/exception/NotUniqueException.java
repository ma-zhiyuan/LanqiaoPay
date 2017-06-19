/**   
* @Title: NotUniqueException.java 
* @Package org.lanqiao.pay.base.exception 
* @Description: TODO
* @author 西安工业大学蓝桥一期--王增   
* @date 2017年5月7日 下午2:17:15    
*/

package org.lanqiao.pay.base.exception;

/**
 * @author 王增
 *
 */
public class NotUniqueException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotUniqueException(){
		super();
	}
	public NotUniqueException(String arg0){
		super(arg0);
	}
}
