package com.liz.mvcapp.dao;

public class CriteriaCustomer {
	private String name;
	
	private String school;
	
	private String phone;

	public String getName() {
		if(name == null)
			name = "%%";
		else name ="%" + name + "%";
		
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSchool() {
		
		if(school == null)
			school = "%%";
		else school ="%" + school + "%";
		
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getPhone() {
		if(phone == null)
			phone = "%%";
		else phone ="%" + phone + "%";
		
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public CriteriaCustomer(String name, String school, String phone) {
		super();
		this.name = name;
		this.school = school;
		this.phone = phone;
	}
	
}
