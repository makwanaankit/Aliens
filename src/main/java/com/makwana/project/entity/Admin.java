package com.makwana.project.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Admin {
	int A_Id;
	String A_Name;
	String A_Gender;
	@Id
	String A_MobileNumber;
	String user_name;
	String password;
	public int getA_Id() {
		return A_Id;
	}
	public void setA_Id(int a_Id) {
		A_Id = a_Id;
	}
	public String getA_Name() {
		return A_Name;
	}
	public void setA_Name(String a_Name) {
		A_Name = a_Name;
	}
	public String getA_Gender() {
		return A_Gender;
	}
	public void setA_Gender(String a_Gender) {
		A_Gender = a_Gender;
	}
	public String getA_MobileNumber() {
		return A_MobileNumber;
	}
	public void setA_MobileNumber(String a_MobileNumber) {
		A_MobileNumber = a_MobileNumber;
	}
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
	@Override
	public String toString() {
		return "Admin [A_Id=" + A_Id + ", A_Name=" + A_Name + ", A_Gender=" + A_Gender + ", A_MobileNumber="
				+ A_MobileNumber + ", user_name=" + user_name + ", password=" + password + "]";
	}
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Admin(int a_Id, String a_Name, String a_Gender, String a_MobileNumber, String user_name, String password) {
		super();
		A_Id = a_Id;
		A_Name = a_Name;
		A_Gender = a_Gender;
		A_MobileNumber = a_MobileNumber;
		this.user_name = user_name;
		this.password = password;
	}	
	
	
}
