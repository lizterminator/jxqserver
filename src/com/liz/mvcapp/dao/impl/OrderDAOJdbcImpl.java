package com.liz.mvcapp.dao.impl;



import java.util.List;

import com.liz.mvcapp.dao.DAO;
import com.liz.mvcapp.dao.OrderDao;
import com.liz.mvcapp.domain.Customer;
import com.liz.mvcapp.domain.Order;

public class OrderDAOJdbcImpl extends DAO<Order> implements OrderDao{

	@Override
	public Boolean save(Order order) {
		String sql = "INSERT INTO orders (userId,jxId,contact,contactPhone,local,notLocal,sum,orderNumber,checked,schoolName,orderDate)VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		return update(sql, order.getUserId(),order.getJxId(),order.getContact(),order.getContactPhone(),order.getLocal(),order.getNotLocal(),order.getSum(),order.getOrderNumber(),0,order.getSchoolName(),order.getOrderDate());
	}

	@Override
	public Boolean check(String orderId) {
		// TODO Auto-generated method stub
		String sql="UPDATE orders SET checked = 1 where orderNumber=?";
		return update(sql, orderId);
	}
	
	
	public long getCountWithOrderId(String orderId) {
		// TODO Auto-generated method stub
		String sql = "SELECT count(jxId) FROM orders WHERE orderNumber = ?";
		
		return getForValue(sql, orderId);
	}
	
	@Override
	public Order get(String orderId) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM orders WHERE orderNumber = ?";
		return get(sql,orderId);
	}
	
	public List<Order> getAllByJxId(Integer jxId) {
		String sql = "SELECT * FROM orders WHERE jxId =?";
		
		return getForList(sql,jxId);
	}

	@Override
	public List<Order> getAllByUserId(Integer userId) {
		String sql = "SELECT * FROM orders WHERE userId =?";
		
		return getForList(sql,userId);
	
	}
}
