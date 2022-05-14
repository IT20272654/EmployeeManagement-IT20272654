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

/**
 * Servlet implementation class employeeAPI
 */
@WebServlet("/employeeAPI")
public class employeeAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	employee employeeObj = new employee(); 
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			 throws ServletException, IOException 
			{ 
			 String output = employeeObj.insertEmployee(request.getParameter("employeeCode"), 
			 request.getParameter("employeeName"), 
			request.getParameter("employeePhone"), 
			request.getParameter("employeeDesc")); 
			response.getWriter().write(output); 
			}
	
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
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) 
			 throws ServletException, IOException 
			{ 
			 Map paras = getParasMap(request); 
			 String output = employeeObj.updateEmployee(paras.get("hidEmployeeIDSave").toString(), 
			 paras.get("employeeCode").toString(), 
			paras.get("employeeName").toString(), 
			paras.get("employeePhone").toString(), 
			paras.get("employeeDesc").toString()); 
			response.getWriter().write(output); 
			} 
			protected void doDelete(HttpServletRequest request, HttpServletResponse response) 
			 throws ServletException, IOException 
			{ 
			 Map paras = getParasMap(request); 
			 String output = employeeObj.deleteEmployee(paras.get("employeeID").toString()); 
			response.getWriter().write(output); 
			}
	
}
