/**   
* @Title: DirectoryNotFound.java 
* @Package org.lanqiao.pay.base.exception 
* @Description: TODO
* @author 西安工业大学蓝桥一期--王增   
* @date 2017年4月29日 上午11:37:22    
*/

package org.lanqiao.pay.base.exception;

/**
 * @author 王增
 *
 */
public class DirectoryNotFound extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public DirectoryNotFound(){
		super();
	}
	public DirectoryNotFound(String arg0){
		super(arg0);
	}
}
