package com.tel.employeeservice.dto;

public class AddressDTO {

	private int id;
	
	private String street;
	
	private String city;
	
	private String pincode;

	public AddressDTO() {
	}

	public AddressDTO(int id, String street, String city, String pincode) {
		this.id = id;
		this.street = street;
		this.city = city;
		this.pincode = pincode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
}
