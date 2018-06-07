package com.makwana.project.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Patient {
	int P_id;
	String name;
	@Id
	String mobileNumber;
	String gender;
	String address;
	int age;
	String user_name;
	String password;
	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getP_id() {
		return P_id;
	}

	public void setP_id(int p_id) {
		P_id = p_id;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
*/
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Patient(String name, String mobileNumber, String gender, String address) {
		super();
		this.name = name;
		this.mobileNumber = mobileNumber;
		this.gender = gender;
		this.address = address;
	}

	@Override
	public String toString() {
		return "Patient [P_id=" + P_id + ", name=" + name + ", mobileNumber=" + mobileNumber + ", gender=" + gender
				+ ", address=" + address + ", age=" + age + ", user_name=" + user_name + ", password=" + password + "]";
	}


}
