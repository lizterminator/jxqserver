package com.liz.mvcapp.domain;

public class Customer {
	
	private Integer id;
	
	private String name;
	
	private String password;
	
	private String email;
	
	private String school;
	
	private String phone;
	
	private String sex;
	
	private String nickname;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", password="
				+ password + ", email=" + email + ", school=" + school
				+ ", phone=" + phone + ", sex=" + sex + ", nickname="
				+ nickname + "]";
	}

	public Customer(Integer id, String name, String password, String email,
			String school, String phone, String sex, String nickname) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.school = school;
		this.phone = phone;
		this.sex = sex;
		this.nickname = nickname;
	}

	public Customer() {
	}

	public Customer(String name, String school, String phone, String email, String password) {
		super();
		this.name = name;
		this.school = school;
		this.phone = phone;
		this.email = email;
		this.password = password;
	}

	public Customer(String password, String phone) {
		super();
		this.password = password;
		this.phone = phone;
	}
	
	

}
