package com.liz.mvcapp.dao.impl;

import java.util.List;

import com.liz.mvcapp.dao.CriteriaCustomer;
import com.liz.mvcapp.dao.CustomerDAO;
import com.liz.mvcapp.dao.DAO;
import com.liz.mvcapp.domain.Customer;

public class CustomerDAOJdbcImpl extends DAO<Customer> implements CustomerDAO{
	
	
	
	@Override
	public List<Customer> getAll(Integer schoolId) {
		String sql = "SELECT id, name,school,phone,email,password,islocal,ischecked FROM customers";
		
		return getForList(sql);
	}

	@Override
	public Boolean save(Customer customer) {
		String sql = "INSERT INTO customers(name,school,phone,email,password)VALUES(?,?,?,?,?)";
		System.out.println(customer.getName());
		return update(sql, customer.getName(),customer.getSchool(),customer.getPhone(),customer.getEmail(),customer.getPassword());
		
	}

	@Override
	public Customer get(String phone) {
		// TODO Auto-generated method stub
		String sql = "SELECT id,name,school,phone,email FROM customers WHERE phone = ?";
		
		return get(sql,phone);
	}

	@Override
	public Boolean delete(String phone) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM customers WHERE phone = ?";
		return update(sql, phone);
	}

	@Override
	public long getCountWithPhone(String phone) {
		// TODO Auto-generated method stub
		String sql = "SELECT count(id) FROM customers WHERE phone = ?";
		
		return getForValue(sql, phone);
	}
	

	public List<Customer> getForListWithCriteriaCustomer(CriteriaCustomer cc) {
		String sql = "SELECT id, name, school, phone FROM customers WHERE "+
					 "name LIKE ? AND school LIKE ? AND phone LIKE ?";
		//修改了CriteriaCustomer 的 getter 方法：使其返回的字符串中有'%%'
		return getForList(sql, cc.getName(),cc.getSchool(),cc.getPhone());
	}
	
	
	/**
	 * check with check_number
	 */
	@Override
	public Boolean makeCheck(String phone, String check) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Boolean edit(Customer customer) {
		String sql = "UPDATE customers SET name = ?,school=?,email=?,password=? WHERE phone = ?";
		return update(sql, customer.getName(),customer.getSchool(),customer.getEmail(),customer.getPassword(),customer.getPhone());
	}

	@Override
	public String getPassword(String phone) {
		String sql = "SELECT password FROM customers WHERE "+
				 "phone = ?";
	
		return getForValue(sql, phone);
	}

   
	

}
