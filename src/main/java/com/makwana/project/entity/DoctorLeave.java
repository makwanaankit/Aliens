package com.makwana.project.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DoctorLeave
{
		@Id
		int Leave_ID;
		int 	D_Id;
		Date 	Leave_Date;
		int Leave_Time;
		public DoctorLeave() {
			super();
			// TODO Auto-generated constructor stub
		}
		public DoctorLeave(int leave_ID, int d_Id, Date leave_Date, int leave_Time) {
			super();
			Leave_ID = leave_ID;
			D_Id = d_Id;
			Leave_Date = leave_Date;
			Leave_Time = leave_Time;
		}
		@Override
		public String toString() {
			return "Leave [Leave_ID=" + Leave_ID + ", D_Id=" + D_Id + ", Leave_Date=" + Leave_Date + ", Leave_Time="
					+ Leave_Time + "]";
		}
		public int getLeave_ID() {
			return Leave_ID;
		}
		public void setLeave_ID(int leave_ID) {
			Leave_ID = leave_ID;
		}
		public int getD_Id() {
			return D_Id;
		}
		public void setD_Id(int d_Id) {
			D_Id = d_Id;
		}
		public Date getLeave_Date() {
			return Leave_Date;
		}
		public void setLeave_Date(Date leave_Date) {
			Leave_Date = leave_Date;
		}
		public int getLeave_Time() {
			return Leave_Time;
		}
		public void setLeave_Time(int leave_Time) {
			Leave_Time = leave_Time;
		}
		
		
		
		
}
