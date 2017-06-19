package org.lanqiao.pay.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.pay.base.util.QRUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/operationController")
public class OperationController {
	
	@RequestMapping("/go_QR")
	public void go_QR(Map<String,String> map,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		 String qrCodeURL = QRUtil.getQRCodeURL("text=http://localhost:8080/LanqiaoPay/userLogin.jsp?t=2&bg=ffffff&fg=cc0000");
		 System.out.println(qrCodeURL);
		 request.setAttribute("qrCodeURL", qrCodeURL);
		 request.getRequestDispatcher("/qr.jsp").forward(request, response);
	}
	
	
}
