package com.liz.mvcapp.domain;

public class DrivingSchool {
	private Integer id;

	private String name;
	
	private String password;
	
	private String mobile;
	private String telephone;
	private String area;

	
	private String location;
	private double price;//todo 
	
	private double discount;
	private String email;
	
	private Integer sold;
	
	private String life;
	
	
	private String info;
	
	private String pic1;
	
	private String pic2;
	
	private String pic3;
	
	private String pic4;
	
	

	public DrivingSchool(Integer id, String name, String password,
			String mobile, String telephone, String area, String location,
			double price, double discount, String email, Integer sold,
			String life, String info, String pic1, String pic2, String pic3,
			String pic4) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.mobile = mobile;
		this.telephone = telephone;
		this.area = area;
		this.location = location;
		this.price = price;
		this.discount = discount;
		this.email = email;
		this.sold = sold;
		this.life = life;
		this.info = info;
		this.pic1 = pic1;
		this.pic2 = pic2;
		this.pic3 = pic3;
		this.pic4 = pic4;
	}
	
	
	public String getPic1() {
		return pic1;
	}


	public void setPic1(String pic1) {
		this.pic1 = pic1;
	}


	public String getPic2() {
		return pic2;
	}


	public void setPic2(String pic2) {
		this.pic2 = pic2;
	}


	public String getPic3() {
		return pic3;
	}


	public void setPic3(String pic3) {
		this.pic3 = pic3;
	}


	public String getPic4() {
		return pic4;
	}


	public void setPic4(String pic4) {
		this.pic4 = pic4;
	}


	public Integer getSold() {
		return sold;
	}

	public void setSold(Integer sold) {
		this.sold = sold;
	}

	public String getLife() {
		return life;
	}

	public void setLife(String life) {
		this.life = life;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	
	
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public DrivingSchool(Integer id, String name, String password, 
			String mobile,String email, String telephone, String area,String location, double price,
			double discount,String info) {
		//SELECT id, name,password, mobile, email,telephone, area, location,price, discount,info FROM school WHERE "+
		 //"name LIKE ? AND area LIKE ?";
		
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.area = area;
		this.email = email;
		this.mobile = mobile;
		this.telephone = telephone;
		this.location = location;
		this.price = price;
		this.discount = discount;
		this.info = info;
	}
	
	public DrivingSchool() {
		super();
	}

	public DrivingSchool(String name, String password, String area,
			String email, String mobile, String telephone, String location,
			double price, double discount, String info) {
		super();
		this.name = name;
		this.password = password;
		this.area = area;
		this.email = email;
		this.mobile = mobile;
		this.telephone = telephone;
		this.location = location;
		this.price = price;
		this.discount = discount;
		this.info = info;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}


	@Override
	public String toString() {
		return "DrivingSchool [id=" + id + ", name=" + name + ", password="
				+ password + ", mobile=" + mobile + ", telephone=" + telephone
				+ ", area=" + area + ", location=" + location + ", price="
				+ price + ", discount=" + discount + ", email=" + email
				+ ", sold=" + sold + ", life=" + life + ", info=" + info
				+ ", pic1=" + pic1 + ", pic2=" + pic2 + ", pic3=" + pic3
				+ ", pic4=" + pic4 + "]";
	}

	

	
}
