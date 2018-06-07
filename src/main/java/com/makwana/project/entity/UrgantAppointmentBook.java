package com.makwana.project.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UrgantAppointmentBook 
{@Id
int Appointment_ID;
	
	String P_id;
	int D_id;
	Date date;
	int time;
	String Discription;
	String Medicine;
	int Payment;
	String Payment_type;
	String mobileNumber;
	public int getAppointment_ID() {
		return Appointment_ID;
	}
	public void setAppointment_ID(int appointment_ID) {
		Appointment_ID = appointment_ID;
	}
	public String getP_id() {
		return P_id;
	}
	public void setP_id(String p_id) {
		P_id = p_id;
	}
	public int getD_id() {
		return D_id;
	}
	public void setD_id(int d_id) {
		D_id = d_id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public String getDiscription() {
		return Discription;
	}
	public void setDiscription(String discription) {
		Discription = discription;
	}
	public String getMedicine() {
		return Medicine;
	}
	public void setMedicine(String medicine) {
		Medicine = medicine;
	}
	public int getPayment() {
		return Payment;
	}
	public void setPayment(int payment) {
		Payment = payment;
	}
	public String getPayment_type() {
		return Payment_type;
	}
	public void setPayment_type(String payment_type) {
		Payment_type = payment_type;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public UrgantAppointmentBook(int appointment_ID, String p_id, int d_id, Date date, int time, String discription,
			String medicine, int payment, String payment_type, String mobileNumber) {
		super();
		Appointment_ID = appointment_ID;
		P_id = p_id;
		D_id = d_id;
		this.date = date;
		this.time = time;
		Discription = discription;
		Medicine = medicine;
		Payment = payment;
		Payment_type = payment_type;
		this.mobileNumber = mobileNumber;
	}
	@Override
	public String toString() {
		return "UrgantAppointmentBook [Appointment_ID=" + Appointment_ID + ", P_id=" + P_id + ", D_id=" + D_id
				+ ", date=" + date + ", time=" + time + ", Discription=" + Discription + ", Medicine=" + Medicine
				+ ", Payment=" + Payment + ", Payment_type=" + Payment_type + ", mobileNumber=" + mobileNumber + "]";
	}
	public UrgantAppointmentBook() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
