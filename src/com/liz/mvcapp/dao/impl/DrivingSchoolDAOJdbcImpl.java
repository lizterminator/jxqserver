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
		//id, name ,password,area,mobile,email,location,price,discount,info,sold,life,pic2,pic3,pic4
		String sql = "SELECT  * FROM school WHERE id = ?";
		
		return get(sql,id);
	}
/*
	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM customers WHERE id = ?";
		update(sql, id);
	}*/
	
	public DrivingSchool get(String mobile){
		String sql = "SELECT id, name,password ,area,mobile,email,location,price,discount,info,sold,life FROM school WHERE mobile = ?";
		
		return get(sql,mobile);
	}
	
	@Override
	public long getCountWithMobile(String mobile) {
		// TODO Auto-generated method stub
		String sql = "SELECT count(id) FROM school WHERE mobile = ?";
		
		return getForValue(sql, mobile);
	}




	@Override
	public List<DrivingSchool> getForListWithCriteriaDrivingSchool(
			CriteriaDrivingSchool cds) {
		String sql = "SELECT id, name, email,telephone, area, location,price, sold,discount,info,pic1,bendi_price,waidi_price FROM school WHERE "+
				 "name LIKE ? OR area LIKE ?";
		//修改了CriteriaCustomer 的 getter 方法：使其返回的字符串中有'%%'
		return getForList(sql, cds.getName(),cds.getArea());
		//return null;
	}

	@Override
	public Boolean addSold(Integer id,Integer x) {
		String sql = "UPDATE school SET sold = sold+? WHERE id =?";
		
		return update(sql,x,id);
	}

	

   
	

}
