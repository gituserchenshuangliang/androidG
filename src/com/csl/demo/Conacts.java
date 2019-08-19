package com.csl.demo;

public class Conacts {
	private String Imag;
	private String name;
	private String phone;
	public String getImag() {
		return Imag;
	}
	public void setImag(String imag) {
		Imag = imag;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Conacts(String phone) {
		super();
		this.phone = phone;
	}
	public Conacts(String name, String phone) {
		super();
		this.name = name;
		this.phone = phone;
	}
}
