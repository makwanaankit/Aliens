package com.makwana.project.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Doctor {
int D_Id;
String D_Name;
String 	D_Gender;
int D_Age;
int D_Salary;
String D_Qualification;
String D_Address;

@Id
String D_MobileNumber;
String user_name;
String password;


public String getD_Address() {
	return D_Address;
}
public void setD_Address(String d_Address) {
	D_Address = d_Address;
}

public int getD_Id() {
	return D_Id;
}
public void setD_Id(int d_Id) {
	D_Id = d_Id;
}
public String getD_Name() {
	return D_Name;
}
public void setD_Name(String d_Name) {
	D_Name = d_Name;
}
public String getD_Gender() {
	return D_Gender;
}
public void setD_Gender(String d_Gender) {
	D_Gender = d_Gender;
}
public int getD_Age() {
	return D_Age;
}
public void setD_Age(int d_Age) {
	D_Age = d_Age;
}
public int getD_Salary() {
	return D_Salary;
}
public void setD_Salary(int d_Salary) {
	D_Salary = d_Salary;
}
public String getD_Qualification() {
	return D_Qualification;
}
public void setD_Qualification(String d_Qualification) {
	D_Qualification = d_Qualification;
}
public String getD_MobileNumber() {
	return D_MobileNumber;
}
public void setD_MobileNumber(String d_MobileNumber) {
	D_MobileNumber = d_MobileNumber;
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
public Doctor(int d_Id, String d_Name, String d_Gender, int d_Age, int d_Salary, String d_Qualification,
		String d_Address, String d_MobileNumber, String d_user_name, String d_password) {
	super();
	D_Id = d_Id;
	D_Name = d_Name;
	D_Gender = d_Gender;
	D_Age = d_Age;
	D_Salary = d_Salary;
	D_Qualification = d_Qualification;
	D_Address = d_Address;
	D_MobileNumber = d_MobileNumber;
	user_name = d_user_name;
	password = d_password;
}
public Doctor() {
	super();
	// TODO Auto-generated constructor stub
}
@Override
public String toString() {
	return "Doctor [D_Id=" + D_Id + ", D_Name=" + D_Name + ", D_Gender=" + D_Gender + ", D_Age=" + D_Age + ", D_Salary="
			+ D_Salary + ", D_Qualification=" + D_Qualification + ", D_Address=" + D_Address + ", D_MobileNumber="
			+ D_MobileNumber + ", user_name=" + user_name + ", password=" + password + "]";
}

	
	
}
