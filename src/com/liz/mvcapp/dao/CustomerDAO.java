package com.liz.mvcapp.dao;

import java.util.List;

import com.liz.mvcapp.domain.Customer;

public interface CustomerDAO {
	
	/**
	 * 返回满足查询条件的List,支持模糊查询
	 * @param cc ：封装了查询条件
	 * @return
	 */
	public List<Customer> getForListWithCriteriaCustomer(CriteriaCustomer cc);
	
	/**
	 * 根据xxID 获取报名该xx的List
	 */
	public List<Customer> getAll(Integer schoolId);
	
	public Boolean save(Customer customer);

	public Boolean edit(Customer customer);
		
	public Customer get(String phone);
	
	public Boolean delete(String phone);
	
	public Boolean makeCheck(String phone, String checkNumber);
	/**
	 * 返回和 name 相等的记录数。
	 * @param name
	 * @return
	 */
	public long getCountWithPhone(String phone);
}
