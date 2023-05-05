package com.libr.pojos;

public class RegisterUser {
	
	private String userid;
	private String password;
	private String name;
	private String contact;
	private String address;
	private String role;
	public RegisterUser(String userid, String password, String name, String contact, String address, String role) {
		super();
		this.userid = userid;
		this.password = password;
		this.name = name;
		this.contact = contact;
		this.address = address;
		this.role = role;
	}
	@Override
	public String toString() {
		return "AddUser [userid=" + userid + ", password=" + password + ", name=" + name + ", contact=" + contact
				+ ", address=" + address + ",  role = "+role+"]";
	}
	public String getRole() {
		return role;
	}
	public void setRole() {
		this.role = role;
		
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
