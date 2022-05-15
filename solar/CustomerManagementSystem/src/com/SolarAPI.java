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


	@WebServlet("/SolarAPI")
	public class SolarAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Solar solarObj = new Solar();

	public SolarAPI() {
	super();
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

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String output = solarObj.insertSolar(request.getParameter("customername"),
					request.getParameter("paneltype"),
					request.getParameter("generatepower"),
			        request.getParameter("noOfPanels"));
					response.getWriter().write(output); 

		}

		protected void doPut(HttpServletRequest request, HttpServletResponse response)
				 throws ServletException, IOException
				{
				 Map paras = getParasMap(request);
				 String output = solarObj.updateSolar(paras.get("hidSolarIDSave").toString(),
				 paras.get("customername").toString(),
				 paras.get("paneltype").toString(),
				 paras.get("generatepower").toString(),
				 paras.get("noOfPanels").toString());
				 response.getWriter().write(output);
				} 

		protected void doDelete(HttpServletRequest request, HttpServletResponse response)
				 throws ServletException, IOException
				{
				 Map paras = getParasMap(request);
				 String output = solarObj.deleteSolar(paras.get("ID").toString());
				response.getWriter().write(output);
				}

	}
