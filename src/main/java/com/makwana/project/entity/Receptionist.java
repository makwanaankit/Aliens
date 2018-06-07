package com.makwana.project.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Receptionist {
int R_Id;
String R_FName;
String 	R_LName;
String R_Gender;
int R_Salary;
String 	R_Address;
@Id
String 	R_MObileNo;
String user_name;
String password;
Date R_DOB;
public int getR_Id() {
	return R_Id;
}
public void setR_Id(int r_Id) {
	R_Id = r_Id;
}
public String getR_FName() {
	return R_FName;
}
public void setR_FName(String r_FName) {
	R_FName = r_FName;
}
public String getR_LName() {
	return R_LName;
}
public void setR_LName(String r_LName) {
	R_LName = r_LName;
}
public String getR_Gender() {
	return R_Gender;
}
public void setR_Gender(String r_Gender) {
	R_Gender = r_Gender;
}
public int getR_Salary() {
	return R_Salary;
}
public void setR_Salary(int r_Salary) {
	R_Salary = r_Salary;
}
public String getR_Address() {
	return R_Address;
}
public void setR_Address(String r_Address) {
	R_Address = r_Address;
}
public String getR_MObileNo() {
	return R_MObileNo;
}
public void setR_MObileNo(String r_MObileNo) {
	R_MObileNo = r_MObileNo;
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
public Date getR_DOB() {
	return R_DOB;
}
public void setR_DOB(Date r_DOB) {
	R_DOB = r_DOB;
}
public Receptionist() {
	super();
	// TODO Auto-generated constructor stub
}
public Receptionist(int r_Id, String r_FName, String r_LName, String r_Gender, int r_Salary, String r_Address,
		String r_MObileNo, String user_name, String password, Date r_DOB) {
	super();
	R_Id = r_Id;
	R_FName = r_FName;
	R_LName = r_LName;
	R_Gender = r_Gender;
	R_Salary = r_Salary;
	R_Address = r_Address;
	R_MObileNo = r_MObileNo;
	this.user_name = user_name;
	this.password = password;
	R_DOB = r_DOB;
}
@Override
public String toString() {
	return "Receptionist [R_Id=" + R_Id + ", R_FName=" + R_FName + ", R_LName=" + R_LName + ", R_Gender=" + R_Gender
			+ ", R_Salary=" + R_Salary + ", R_Address=" + R_Address + ", R_MObileNo=" + R_MObileNo + ", user_name="
			+ user_name + ", password=" + password + ", R_DOB=" + R_DOB + "]";
}

}
