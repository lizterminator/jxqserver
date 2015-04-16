package com.liz.mvcapp.dao.impl;

import java.util.List;

import com.liz.mvcapp.dao.CriteriaDrivingSchool;
import com.liz.mvcapp.dao.DAO;
import com.liz.mvcapp.dao.DrivingSchoolDAO;
import com.liz.mvcapp.domain.DrivingSchool;

public class DrivingSchoolDAOJdbcImpl extends DAO<DrivingSchool> implements DrivingSchoolDAO{

	/*@Override
	public List<Customer> getAll() {
		String sql = "SELECT id, name, school, phone FROM customers";
		
		return getForList(sql);
	}*/

	@Override
	public Boolean save(DrivingSchool dSchool) {
		//String sql = "INSERT INTO drivingSchool(name,school,phone)VALUES(?,?,?)";
		//update(sql, dSchool.getName(),dSchool.getSchool(),dSchool.getPhone());
		return false;
	}

	@Override
	public DrivingSchool get(Integer id) {
		// TODO Auto-generated method stub
		String sql = "SELECT id, name ,area,phone,email,location,price,discount,info FROM customers WHERE id = ?";
		
		return get(sql,id);
	}
/*
	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM customers WHERE id = ?";
		update(sql, id);
	}*/

	@Override
	public long getCountWithName(String name) {
		// TODO Auto-generated method stub
		String sql = "SELECT count(id) FROM customers WHERE name = ?";
		
		return getForValue(sql, name);
	}




	@Override
	public List<DrivingSchool> getForListWithCriteriaDrivingSchool(
			CriteriaDrivingSchool cds) {
		String sql = "SELECT id, name,password, mobile, email,telephone, area, location,price, discount,info FROM school WHERE "+
				 "name LIKE ? AND area LIKE ?";
		//修改了CriteriaCustomer 的 getter 方法：使其返回的字符串中有'%%'
		return getForList(sql, cds.getName(),cds.getArea());
		//return null;
	}

	

   
	

}
