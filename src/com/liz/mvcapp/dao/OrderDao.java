package com.liz.mvcapp.dao;


import java.util.List;

import com.liz.mvcapp.domain.Order;

public interface OrderDao {
	public Boolean save(Order order);
	
	public Boolean check(String orderId);
	
	public Boolean delete(String orderId);
	
	public Order get(String orderId);
	
	public List<Order> getAllByJxId(Integer jxId);
	
	public List<Order> getAllByUserId(Integer userId);
	
	public long getCountWithOrderId(String orderId);
}
