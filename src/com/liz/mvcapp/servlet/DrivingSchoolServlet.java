package com.liz.mvcapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import org.json.JSONArray;
import org.json.JSONObject;

import com.liz.mvcapp.dao.CriteriaDrivingSchool;
import com.liz.mvcapp.dao.CustomerDAO;
import com.liz.mvcapp.dao.DrivingSchoolDAO;
import com.liz.mvcapp.dao.OrderDao;
import com.liz.mvcapp.dao.impl.CustomerDAOJdbcImpl;
import com.liz.mvcapp.dao.impl.DrivingSchoolDAOJdbcImpl;
import com.liz.mvcapp.dao.impl.OrderDAOJdbcImpl;
import com.liz.mvcapp.domain.Customer;
import com.liz.mvcapp.domain.DrivingSchool;
import com.liz.mvcapp.domain.Order;
import com.liz.mvcapp.validate.Validate;

/**
 * 利用 *.ds收集相应的请求
 */
@WebServlet("*.ds")
public class DrivingSchoolServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	private CustomerDAO customerDAO = new CustomerDAOJdbcImpl();
	private DrivingSchoolDAO drivingSchoolDAO = new DrivingSchoolDAOJdbcImpl();
	private CustomerDAO customerDAO = new CustomerDAOJdbcImpl();
	private OrderDao orderDAO = new OrderDAOJdbcImpl(); 
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
	
	private void submitOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String idString = request.getParameter("schoolId");
		String contact = request.getParameter("contact");
		String contactPhone = request.getParameter("contactPhone");
		String localString = request.getParameter("local");
		String notLocalString = request.getParameter("notLocal");
		String userPhone = request.getParameter("userPhone");
		String password = request.getParameter("password");
		String schoolName = request.getParameter("schoolName");
		
		if(!Validate.checkPhone(contactPhone)){
			return;
		}
		if(!Validate.checkPhone(userPhone)){
			return;
		}
		
		Integer jxId = 0;
		Integer local = 0;
		Integer notLocal = 0;
		try {
			jxId = Integer.parseInt(idString);
		} catch (Exception e) {
			System.out.println("解析出错！");
			e.printStackTrace();
			return;
			// TODO: handle exception
		}
		try {
			local = Integer.parseInt(localString);
		} catch (Exception e) {
			System.out.println("解析出错！");
			e.printStackTrace();
			return;
			// TODO: handle exception
		}
		try {
			notLocal = Integer.parseInt(notLocalString);
		} catch (Exception e) {
			System.out.println("解析出错！");
			e.printStackTrace();
			return;
			// TODO: handle exception
		}
		
		long count = customerDAO.getCountWithPhone(userPhone);
		
		PrintWriter out = response.getWriter();  
		JSONObject json = new JSONObject();
        if(count > 0 ){
//        	HttpSession session = request.getSession();
        	Customer customer = customerDAO.get(userPhone);
        	System.out.println(customer);
        	if(customerDAO.getPassword(userPhone).equals(password)){
        		Order order = new Order();
        		order.setUserId(customer.getId());
        		order.setContact(contact);
        		order.setContactPhone(contactPhone);
        		order.setJxId(jxId);
        		order.setLocal(local);
        		order.setNotLocal(notLocal);
        		order.setSchoolName(schoolName);
        		Date date = new Date();
        		
        		
        		order.setSum(0);
        		String orderNumber = ""+ date.getTime();
        		order.setOrderNumber(orderNumber);
        		
        		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        		order.setOrderDate(ft.format(date));
        	    System.out.println("Current Date: " + ft.format(date));  
        		
        		if(orderDAO.save(order)){
        			drivingSchoolDAO.addSold(jxId,local+notLocal);
        			json.put("success", true);
                	json.put("msg", "下单成功");
                	json.put("orderNumber",orderNumber);
        		}else{
        			json.put("success", false);
                	json.put("msg", "下单失败");
        		}
        		
        	}
        }else{
        	json.put("success", false);
        	json.put("msg", "参数出错");
        }
        out.print(json);
		out.flush();  
        out.close();  
		
	}
	private void getDetile(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String idString = request.getParameter("schoolId");
		
		Integer id = 0;
		//try catch 的作用：防止 idStr 不能转为int,若不能转，则id=0,无法进行任何操作。
		PrintWriter out = response.getWriter();  
		try {
			id = Integer.parseInt(idString);
			DrivingSchool ds = drivingSchoolDAO.get(id);
			
	        JSONObject json = new JSONObject(ds);
			
	        json.put("success", true);
	        out.print(json);
		} catch (Exception e) {
			// TODO: handle exception
			JSONObject json = new JSONObject();
			
	        json.put("success", false);
	        out.print(json);
			
		} finally{
			out.flush();  
	        out.close();
		}
	}
	
	private void search(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String keyword = request.getParameter("keyword");
		
		CriteriaDrivingSchool cds = new CriteriaDrivingSchool(keyword, keyword);
		
		List<DrivingSchool> list = drivingSchoolDAO.getForListWithCriteriaDrivingSchool(cds);
		System.out.println(list);
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
	private void getOrdersInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String mobile = request.getParameter("phone"); 
		String password = request.getParameter("jxpwd");
		if(!Validate.checkPhone(mobile)){
			return;
		}
		
		
		long count = drivingSchoolDAO.getCountWithMobile(mobile);
		
		PrintWriter out = response.getWriter();  
		
		
		if(count > 0){
			DrivingSchool ds = drivingSchoolDAO.get(mobile);
			
			if(ds.getPassword().equals(password)){
				
				List<Order> orders = orderDAO.getAllByJxId(ds.getId());
				System.out.println(orders);
				JSONArray array = new JSONArray();
				for(int i=0;i<orders.size();i++){
					Order order = orders.get(i);
					System.out.println(order);
					JSONObject json = new JSONObject();
					json.put("name", order.getContact());
					json.put("phone", order.getContactPhone());
					System.out.println(order.getOrderDate());
					json.put("order_time", order.getOrderDate());
					json.put("local", order.getLocal());
					json.put("notLocal", order.getNotLocal());
					String checked = (order.getChecked()==1)?"是":"否";
					json.put("checked", checked);
					array.put(json);
					
				}
				
        		out.print(array);
        		
				
				
			}else{
				//json.put("success", false);
			}
		}else{
			//json.put("success", false);
		}
		out.flush();  
        out.close();
	}
	
	private void userLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String mobile = request.getParameter("mobile");
		String password = request.getParameter("password");
		System.out.println(mobile);
		long count = drivingSchoolDAO.getCountWithMobile(mobile);
		
		PrintWriter out = response.getWriter();  
        
        if(count > 0 ){
//        	HttpSession session = request.getSession();
//        	Customer customer = customerDAO.get(phone);
        	DrivingSchool ds = drivingSchoolDAO.get(mobile);
        	System.out.println(ds);
        	if(ds.getPassword().equals(password)){
            	JSONObject json = new JSONObject(ds); 
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
	
	private void check(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String contact = request.getParameter("name");
		String contactPhone = request.getParameter("phone");
		String checknumber = request.getParameter("checknumber");
		
		String mobile = request.getParameter("jxmobile");
		String password = request.getParameter("jxpassword");
		
		JSONObject json = new JSONObject(); 
		
		long count = drivingSchoolDAO.getCountWithMobile(mobile);
		
		PrintWriter out = response.getWriter();  
        
        if(count > 0 ){
        	DrivingSchool ds = drivingSchoolDAO.get(mobile);
        	if(ds.getPassword().equals(password)){
        		long c_order = orderDAO.getCountWithOrderId(checknumber);
        		if(c_order > 0){
        			Order order = orderDAO.get(checknumber);
        			
        			if(order.getContact().equals(contact) && order.getContactPhone().equals(contactPhone)){
        				orderDAO.check(checknumber);
        				json.put("success",true).put("msg","完成预约！享受减免！");
        			}else{
        				json.put("success", false).put("msg", "请检查输入是否有误");
        			}
        		}else{
        			json.put("success", false).put("msg", "请检查输入是否有误");
        		}
        	}else{
        		json.put("success", false).put("msg", "请检查输入是否有误");
        	}
        }
        out.print(json);
    	out.flush();  
        out.close();  
        
	}
}
