package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PaymentAPI
 */
@WebServlet("/CustomerAPI")
public class CustomerAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Customer customerObj = new Customer();
		String output = customerObj.insertCus(request.getParameter("wiringMethod"), 
				 request.getParameter("fullName"), 
				request.getParameter("phoneNo"), 
				request.getParameter("electricID")); 
				response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	
	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request) 
	{ 
	 Map<String, String> map = new HashMap<String, String>(); 
	try
	 { 
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
	 String queryString = scanner.hasNext() ? 
	 scanner.useDelimiter("\\A").next() : ""; 
	 scanner.close(); 
	 String[] params = queryString.split("&"); 
	 for (String param : params) 
	 {
		 String[] p = param.split("=");
		 map.put(p[0], p[1]); 
		 } 
		 } 
		catch (Exception e) 
		 { 
		 } 
		return map; 
		}

		 
		 
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Customer customerObj = new Customer();
		Map paras = getParasMap(request); 
		 String output = customerObj.updateCus(paras.get("hidPIDSave").toString(), 
		 paras.get("wiringMethod").toString(), 
		 paras.get("fullName").toString(), 
		paras.get("phoneNo").toString(), 
		paras.get("electricID").toString()); 
		response.getWriter().write(output);
		
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Customer customerObj = new Customer();
		Map paras = getParasMap(request); 
		 String output = customerObj.deleteCus(paras.get("PID").toString()); 
		response.getWriter().write(output);
	}

}
