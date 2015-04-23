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
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.liz.mvcapp.dao.CriteriaCustomer;
import com.liz.mvcapp.dao.CustomerDAO;
import com.liz.mvcapp.dao.impl.CustomerDAOJdbcImpl;
import com.liz.mvcapp.domain.Customer;

/**
 * 利用 *.cu收集相应的请求,代表收集customer的请求
 */
@WebServlet("*.cu")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CustomerDAO customerDAO = new CustomerDAOJdbcImpl();
	
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
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		// TODO Auto-generated method stub
		String idString =  request.getParameter("id");
		int id = 0;
		
		//try catch 的作用：防止 idStr 不能转为int,若不能转，则id=0,无法进行任何删除操作。
		try {
			id = Integer.parseInt(idString);
			customerDAO.delete("phone");
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		response.sendRedirect("query.do");
		//System.out.println("delete");
		
	}

	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//获取模糊查询的请求参数
		String name = request.getParameter("name");
		String school = request.getParameter("school");
		String phone = request.getParameter("phone");
		
		//封装请求参数
		CriteriaCustomer cc = new CriteriaCustomer(name, school, phone);
		
		//1、调用 CustomerDAO 的getAll方法得到Customer集合
		List<Customer> customers = customerDAO.getForListWithCriteriaCustomer(cc);
		//List<Customer> customers = null;
		//2.把 Customer 的集合放入 requset 中
		request.setAttribute("customers", customers);
		
		//3.转发页面到 index.jsp(不能使用重定向)
		request.getRequestDispatcher("/index.jsp").forward(request, response);
		
		//System.out.println("query");
	}

	private void addCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//1.获取请求参数
		String name = request.getParameter("name");
		String school = request.getParameter("school");
		String phone = request.getParameter("phone");
		
		//2.检验 name 是否已经被占用
		
		//2.1 调用 CustomerDAO 的 getCountWithName(String name)方法获取  name 在数据库中是否存在
		long count = customerDAO.getCountWithPhone(name);
		
		//2.2若返回值大于 0,则响应newcustomer.jsp页面 ：
		//通过转发的方式来响应 newcustomer.jsp
		if(count > 0){
			//2.2.1 要求在newcustomer.jsp 页面显示一个错误消息：用户名 xxx 已经被占用,请重新选择！
			//在 request 中放入一个属性 message:用户名 xxx 已经被占用,请重新选择！在页面上通过 request.getAttribute("message")
			request.setAttribute("message", "用户名"+ name +"已经被占用,请重新选择！");
			//2.2.2 newcustomer.jsp 的表单值可以回显
			//通过 value="<%=request.getParameter("name") == null ?"":request.getParameter("name") %>"
			//来进行回显
			
			//2.2.3 结束方法 ：return
			request.getRequestDispatcher("/newcustomer.jsp").forward(request, response);
			return ;
			
		}
		
		
		//3. 若验证通过，则把表单参数封装为一个 Customer 对象 customer
		Customer customer = new Customer(name, school, phone,"","");
		System.out.println(customer);
		//4.调用CustomerDAO 的  save(Customer customer) 执行保存操作
		customerDAO.save(customer);
		//5.重定向到success.jsp 页面:使用重定向可以避免出现表单的重复提交
		response.sendRedirect("success.jsp");
		
	//	System.out.println(request.getParameter("name"));
	}
	
	private void edit(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("edit");
	}
	
	private void userLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		
		long count = customerDAO.getCountWithPhone(phone);
		
		PrintWriter out = response.getWriter();  
        
        
        
        if(count > 0 ){
//        	HttpSession session = request.getSession();
        	Customer customer = customerDAO.get(phone);
        	System.out.println(customer);
        	if(customerDAO.getPassword(phone).equals(password)){
        		
            	JSONObject json = new JSONObject(customer); 
            	json.put("success", true);
            	out.print(json);
            	out.flush();  
    	        out.close();  
    			
        	}else{
        		JSONObject json = new JSONObject(); 
            	json.put("success", false).put("message", "用户名或密码错误！");
            	out.print(json);
            	out.flush();  
    	        out.close();  
    	      
        	}
        	
        }else{
        	JSONObject json = new JSONObject(); 
        	json.put("success", false).put("message", "用户名或密码错误！");
        	
        	out.print(json);
        	out.flush();  
	        out.close();  
	        
        }
        
	}
	private void userRegister(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		
		long count = customerDAO.getCountWithPhone(phone);
		
        PrintWriter out = response.getWriter();  
        JSONObject json = new JSONObject();
        
		if(count > 0){
			json.put("success", false).put("message", "该手机号已被注册！");
			out.print(json);
			out.flush();  
	        out.close();  
			return;
		}
		Customer customer = new Customer();
		customer.setPhone(phone);
		customer.setPassword(password);
		customerDAO.save(customer);

		
		JSONArray array = new JSONArray();
		JSONObject testJsonObject = new JSONObject("{'name':1}");
		for (int i = 0; i < 2; i++) {
			array.put(testJsonObject);
		}
        //将数据拼接成JSON格式  
		json.put("success", true).put("schools", array);

        out.print(json);
		out.flush();  
        out.close();  
		
	}
}
