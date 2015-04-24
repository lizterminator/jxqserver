package com.liz.mvcapp.dao;

import java.util.List;

import com.liz.mvcapp.domain.DrivingSchool;

public interface DrivingSchoolDAO {
	/**
	 * 返回满足查询条件的List,支持模糊查询
	 * @param cc ：封装了查询条件
	 * @return
	 */
	
	public List<DrivingSchool> getForListWithCriteriaDrivingSchool(CriteriaDrivingSchool cds);
	
	//public List<DrivingSchool> getAll();
	
	public Boolean save(DrivingSchool dSchool);
	
	
	
	public DrivingSchool get(Integer id);
	
	public DrivingSchool get(String mobile);
	
	public Boolean addSold(Integer id,Integer x);
	//public void delete(Integer id);
	
	/**
	 * 返回和 name 相等的记录数。
	 * @param name
	 * @return
	 */
	public long getCountWithMobile(String mobile);
}
