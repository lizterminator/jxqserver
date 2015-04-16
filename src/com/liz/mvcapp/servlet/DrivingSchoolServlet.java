package com.liz.mvcapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.liz.mvcapp.dao.CriteriaDrivingSchool;
import com.liz.mvcapp.dao.DrivingSchoolDAO;
import com.liz.mvcapp.dao.impl.DrivingSchoolDAOJdbcImpl;
import com.liz.mvcapp.domain.DrivingSchool;

/**
 * 利用 *.ds收集相应的请求
 */
@WebServlet("*.ds")
public class DrivingSchoolServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	private CustomerDAO customerDAO = new CustomerDAOJdbcImpl();
	private DrivingSchoolDAO drivingSchoolDAO = new DrivingSchoolDAOJdbcImpl();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
/*	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		
		switch (method) {
		case "add": add(request,response);break;
		case "query": query(request,response);break;
		case "delete": delete(request,response);break;

		default:
			break;
		}
	}*/

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");  
		//1.获取ServletPath: /edit.do 或 /addCustomer.do
		String servletPath = request.getServletPath();
		
		//2.去除 / 和.do, 得到类似于edit 或addCustomer这样的字符串
		String methodName = servletPath.substring(1, servletPath.length()-3);
		
		Method method;
		try {
			
			//3.利用反射获取methodName 对应的方法
			method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class , HttpServletResponse.class);
			//4.利用反射调用对应的方法
			method.invoke(this, request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}
		
		
		//System.out.println(methodName);
		
	}
	
	
	
	private void search(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String keyword = request.getParameter("keyword");
		
		CriteriaDrivingSchool cds = new CriteriaDrivingSchool(keyword, keyword);
		
		List<DrivingSchool> list = drivingSchoolDAO.getForListWithCriteriaDrivingSchool(cds);
		
		PrintWriter out = response.getWriter();  
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		
		for (int i = 0; i < list.size(); i++) {
			array.put(new JSONObject(list.get(i)));
		}
		json.put("success", true).put("schools", array);
		out.print(json);
		out.flush();  
        out.close();  
        
	}
	
}
