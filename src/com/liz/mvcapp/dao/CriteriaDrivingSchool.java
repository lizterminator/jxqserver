package com.liz.mvcapp.dao;

public class CriteriaDrivingSchool {
	
	private String name;
	
	private String area;

	public String getName() {
		if(name == null)
			name = "%%";
		else name ="%" + name + "%";
		
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArea() {
		
		if(area == null)
			area = "%%";
		else area ="%" + area + "%";
		
		return area;
	}

	

	

	public CriteriaDrivingSchool(String name, String area) {
		super();
		this.name = name;
		this.area = area;
		
	}
	
	
	
}
