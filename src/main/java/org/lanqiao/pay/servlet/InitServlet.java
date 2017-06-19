package org.lanqiao.pay.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.Init;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InitServlet
 */
/**
 * 
 * @author 王增
 *这个servlet不是用来访问的,而是方便大家在前台引用js,css之类文件的时候,如果需要使用绝对路径,那么只需要
 *${applicationScope.url}即可得到项目的根路径LanqiaoPay
 */
@WebServlet("/InitServlet")
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter writer = response.getWriter();
		writer.print("");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override//在这里servletConfig对象就是jsp中的config隐藏对象
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		ServletContext servletContext = config.getServletContext();//这个就是jsp的application
		String projectUrl = servletContext.getContextPath();
		servletContext.setAttribute("url", projectUrl);//以后就直接${applicationScope.url}
			//即可得到		/lanqiaoPay
		System.out.println(servletContext.getAttribute("url"));
	}
}
