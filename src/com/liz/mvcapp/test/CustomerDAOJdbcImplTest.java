package com.liz.mvcapp.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.liz.mvcapp.dao.CriteriaCustomer;
import com.liz.mvcapp.dao.CriteriaDrivingSchool;
import com.liz.mvcapp.dao.CustomerDAO;
import com.liz.mvcapp.dao.DrivingSchoolDAO;
import com.liz.mvcapp.dao.impl.CustomerDAOJdbcImpl;
import com.liz.mvcapp.dao.impl.DrivingSchoolDAOJdbcImpl;
import com.liz.mvcapp.domain.Customer;
import com.liz.mvcapp.domain.DrivingSchool;

public class CustomerDAOJdbcImplTest {

	private CustomerDAO customerDAO = 
			new CustomerDAOJdbcImpl();
	private DrivingSchoolDAO drivingSchoolDAO = new DrivingSchoolDAOJdbcImpl();
	@Test
	public void testgetfor() {
		CriteriaCustomer cc = new CriteriaCustomer("l", null, null);
		List<Customer> customers = customerDAO.getForListWithCriteriaCustomer(cc);
		System.out.println(customers);
		
	}
	
	
	@Test
	public void testSchoolC() {
		CriteriaDrivingSchool cds = new CriteriaDrivingSchool("大", null);
		List<DrivingSchool> schools = drivingSchoolDAO.getForListWithCriteriaDrivingSchool(cds);
		System.out.println(schools);
	}
	@Test
	public void testCheck() {
		customerDAO.makeCheck("asd","");
	}
	
	@Test
	public void testEdit() {
		Customer customer = new Customer("Lijie2","Zhejiang","1234567","fuck@qq.com","654321");
		Boolean aBoolean = customerDAO.edit(customer);
		System.out.println(aBoolean);
		//return null;
	}

	@Test
	public void testSave() {
		Customer customer = new Customer();
		customer.setSchool("xasdaaa");
		customer.setName("张磊");
		customer.setPhone("1870000adattt");
		customer.setPassword("asda");
		customer.setEmail("test");
		customerDAO.save(customer);

	}

	@Test
	public void testGetInteger() {
		Customer customer = customerDAO.get("12345");
		System.out.println(customer);
	}
	
	@Test
	public void testgetall () {
		
	}
	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCountWithName() {
		fail("Not yet implemented");
	}

}
