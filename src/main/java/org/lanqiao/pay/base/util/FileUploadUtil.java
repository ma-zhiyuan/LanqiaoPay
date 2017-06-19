/**   
* @Title: FileUploadUtil.java 
* @Package org.lanqiao.pay.base.util 
* @Description: TODO
* @author 西安工业大学蓝桥一期--王增   
* @date 2017年4月29日 上午11:35:08    
*/

package org.lanqiao.pay.base.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.lanqiao.pay.base.exception.DirectoryNotFound;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author 王增
 *实现文件上传的一个工具类
 */
public class FileUploadUtil {
	/***
	 * 
	* @author 西安工业大学蓝桥一期--王增
	* @Title: FileUpload 
	* @Description: TODO
	* @param @param file
	* @param @param session
	* @param @param directory 服务器上指定的文件夹名称,如果有多层那么就XXX/XX这种或者开头或末尾多一个/也可以
	* 该目录是基于项目根路径的!请不要把目录指定在web-inf下,否则在页面上引用img的src时,可能无法访问到
	* @param @return
	* @param @throws IOException    设定文件 
	* @return String    返回文件在web服务器上的绝对路径,最后传文件而且 指定文件存在写成功的会返回一个web虚拟路径,否则是空串
	* @date 2017年4月29日 上午9:54:21 
	* @throws
	 */
	public static String FileUpload(MultipartFile file,HttpSession session,String directory){
			String fileOnWebPath = "";
			String fileName = file.getOriginalFilename();//
			if(fileName.equals("")){//前台没有传文件
				
			}else{//前台传来了文件
				//文件夹在服务器上的路径,这里还需要看看如果没有那个文件夹,或者写错了咋办!
				String serverDirectoryPath = session.getServletContext().getRealPath(directory);
				File directoryFile = new File(serverDirectoryPath);
				if(directoryFile.exists()==false){
					throw new DirectoryNotFound("^_^!出异常了!"+serverDirectoryPath+"	不存在!");
				}
				//能执行到下面的是没有出过异常的,然后进行写文件操作
				System.out.println(serverDirectoryPath);
				//对文件名进行处理,由于directory末尾有没有'/',上一步都不会保留末尾/的,所以这里需要手动加上
				fileName = "/"+System.currentTimeMillis()+new Random().nextInt(10000)+fileName;
				//得到项目名
				String projectName = session.getServletContext().getContextPath();
				//最后这个路径使我们在服务器上的相对路径,也是我们需要保存到数据库中的.
				fileOnWebPath = projectName + handleDirectory(directory)+fileName;
				System.out.println("fileOnWebPath:"+fileOnWebPath);
				//获取输入流
				InputStream inputStream = null;
				FileOutputStream fileOutputStream = null;
				try {
					inputStream = file.getInputStream();
					byte bytes[] = new byte[1024*1024];
					int len = -1;
					//写入服务器上的物理路径
					fileOutputStream = new FileOutputStream(serverDirectoryPath+fileName);
					while((len = inputStream.read(bytes))!=-1){
						fileOutputStream.write(bytes, 0, len);
					}
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				finally{
					if(inputStream != null){
						try {
							inputStream.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if(fileOutputStream!=null){
						try {
							fileOutputStream.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				
					}
				}
				//
				//
			}
			return fileOnWebPath;
	}
	//处理directory,开头或结尾带或不带/的问题,最后返回同一带/的
	//算了还是不用处理结尾了,因为我们上面的那个函数,fileName中的开头已经加了一个/了
	private static String handleDirectory(String directory){
		char[] charArray = directory.toCharArray();
		if(charArray[0]!='/'){
			directory = "/" + directory;
		}
		/*if(charArray[charArray.length-1]!='/')
		{
			directory = directory+"/";
		}*/
		return directory;
	}
}
