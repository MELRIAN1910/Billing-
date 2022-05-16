package com;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Billing;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Servlet implementation class BillingAPI
 */
@WebServlet("/BillingAPI")
public class BillingAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BillingAPI() {
        super();
    }
    
    Billing BillingObj = new Billing() ;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				 
				String output = BillingObj.insertBilling(request.getParameter("CID"),
				request.getParameter("BN"),
				request.getParameter("PR"),
				request.getParameter("CR"),
				request.getParameter("DA"),
				request.getParameter("BD"));
				response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 Map paras = getParasMap(request);
		 String output = BillingObj.updateBilling(paras.get("hidBillingIDSave").toString(),
		 paras.get("CID").toString(),
		 paras.get("BN").toString(),
		 paras.get("PR").toString(),
		 paras.get("CR").toString(),
		 paras.get("DA").toString());
		 response.getWriter().write(output); 

	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 Map paras = getParasMap(request);
		 String output = BillingObj.deleteBilling(paras.get("BID").toString());
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
	 { String[] p = param.split("=");
	 map.put(p[0], p[1]);
	 }
	 }
	catch (Exception e)
	 {
	 }
	return map;
	}

	
	
	
}
