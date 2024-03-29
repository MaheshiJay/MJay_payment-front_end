package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@WebServlet("/PaymentsAPI")
public class PaymentsAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Payment payObj = new Payment();
    
    public PaymentsAPI() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String output = payObj.insertPayment(request.getParameter("billID"),
				request.getParameter("paymentAmount"),
				request.getParameter("creditCardType"),
				request.getParameter("cardNo"),
		        request.getParameter("expirationalMonth"),
		        request.getParameter("expirationalYear"),
		        request.getParameter("cvn"));
				response.getWriter().write(output);
	}

	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request){
	
		Map<String, String> map = new HashMap<String, String>();
	
		try{
	     Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
	     String queryString = scanner.hasNext() ?
	     scanner.useDelimiter("\\A").next() : "";
	     scanner.close();
	     String[] params = queryString.split("&");
	    
	     for (String param : params){
		     String[] p = param.split("=");
	      	 map.put(p[0], p[1]);
		}
	  }catch (Exception e){
		
	  }
	  return map;
		
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map paras = getParasMap(request);
		
		String output = payObj.updatePayment(paras.get("hidPaymentIDSave").toString(),
		paras.get("billID").toString(),
		paras.get("paymentAmount").toString(),
		paras.get("creditCardType").toString(),
		paras.get("cardNo").toString(),
		paras.get("expirationalMonth").toString(),
		paras.get("expirationalYear").toString(),
		paras.get("cvn").toString());
		response.getWriter().write(output);
		
		
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map paras = getParasMap(request);
		
		String output = payObj.deletePaymentDetails(paras.get("paymentID").toString());
		
		response.getWriter().write(output);
	}

}
