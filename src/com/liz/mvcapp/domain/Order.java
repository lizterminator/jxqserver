package com.liz.mvcapp.domain;

import com.sun.corba.se.pept.transport.ContactInfo;

public class Order {
	private Integer userId;
	private Integer jxId;
	
	private String contact;
	private String contactPhone;
	
	private Integer local;
	private Integer notLocal;
	private String schoolName;
	private String orderNumber;
	private double sum;
	
	private Integer checked;
	
	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getJxId() {
		return jxId;
	}

	public void setJxId(Integer jxId) {
		this.jxId = jxId;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public Integer getLocal() {
		return local;
	}

	public void setLocal(Integer local) {
		this.local = local;
	}

	public Integer getNotLocal() {
		return notLocal;
	}

	public void setNotLocal(Integer notLocal) {
		this.notLocal = notLocal;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}
	
	

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	
	public Integer getChecked() {
		return checked;
	}

	public void setChecked(Integer checked) {
		this.checked = checked;
	}

	public Order() {
		super();
	}

	public Order(Integer userId, Integer jxId, String contact,
			String contactPhone, Integer local, Integer notLocal,
			String schoolName, String orderNumber, double sum, Integer checked) {
		super();
		this.userId = userId;
		this.jxId = jxId;
		this.contact = contact;
		this.contactPhone = contactPhone;
		this.local = local;
		this.notLocal = notLocal;
		this.schoolName = schoolName;
		this.orderNumber = orderNumber;
		this.sum = sum;
		this.checked = checked;
	}

	@Override
	public String toString() {
		return "Order [userId=" + userId + ", jxId=" + jxId + ", contact="
				+ contact + ", contactPhone=" + contactPhone + ", local="
				+ local + ", notLocal=" + notLocal + ", schoolName="
				+ schoolName + ", orderNumber=" + orderNumber + ", sum=" + sum
				+ ", checked=" + checked + "]";
	}

	
	

	
	
}
