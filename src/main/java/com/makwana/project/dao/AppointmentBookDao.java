package com.makwana.project.dao;

import java.util.List;
import java.text.DateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.makwana.project.entity.Appointment;
import com.makwana.project.entity.Doctor;
import com.makwana.project.entity.DoctorLeave;
import com.makwana.project.entity.FamailyMember;
import com.makwana.project.entity.Patient;
import com.sun.org.apache.regexp.internal.recompile;

import sun.print.resources.serviceui;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class AppointmentBookDao {

	@Autowired
	SessionFactory sessioFactory;

	@Autowired
	LeaveDao ldao;
	
	@Transactional
	public void createAppointment(Appointment appointment) {
		int index = 0;
		Session session = sessioFactory.getCurrentSession();
		System.out.println(appointment.getDate());
		Appointment app=appointment;
		app.setDiscription("");
		app.setMedicine("");
		app.setPayment(0);
		app.setPayment_type("");
		/*
		 * Query<Appointment>
		 * query=session.createQuery("from Appointment A ORDER BY A.Appointment_ID"
		 * ,Appointment.class); List<Appointment> la=query.list(); for(Appointment a:la)
		 * { index=a.getAppointment_ID(); } appointment.setAppointment_ID(index+1);
		 */

		session.save(appointment);
	}

	@Transactional
	public int getCount(Appointment appointment) {
		Session session = sessioFactory.getCurrentSession();
		// Query<Appointment> query=session.createQuery("from Appointment A where
		// A.D_id='"+appointment.getD_id()+"',A.date='"+appointment.getDate()+"',A.time='"+appointment.getTime()+"'",Appointment.class);
		Query<Appointment> query = session.createQuery("from Appointment", Appointment.class);
		Date date = appointment.getDate();

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = dateFormat.format(date);
		System.out.println("Converted String: " + strDate);

		int count = 0;
		List<Appointment> la = query.list();
		for (Appointment a : la) {
			String dd = dateFormat.format(a.getDate());
			if (strDate.equals(dd) && a.getTime() == appointment.getTime() && a.getD_id() == appointment.getD_id()) {
				count = count + 1;
			}
			System.out.println(dd);
		}
		System.out.println("Count" + count);
		return count;
	}

	@Transactional
	public int getAppointmentID() {
		int index = 0;
		Session session = sessioFactory.getCurrentSession();
		Query<Appointment> query = session.createQuery("from Appointment A ORDER BY A.Appointment_ID",
				Appointment.class);
		List<Appointment> la = query.list();
		for (Appointment a : la) {
			index = a.getAppointment_ID();
		}
		return index + 1;
	}

	@Transactional
	public List<Appointment> getSpecificDoctorAppointment(HttpServletRequest request, HttpServletResponse response) {
		Session session;
		List<Appointment> aList = null;
		HttpSession httpSession = request.getSession();
		Doctor d = (Doctor) httpSession.getAttribute("DoctorProfile");
		Date date;
		int timeid = 0;
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yy");

		try {
			date = sdf1.parse(request.getParameter("date"));

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String strDate = dateFormat.format(date);
			System.out.println("Converted String: " + strDate);
			timeid = Integer.parseInt(request.getParameter("AppointmentTime"));
			System.out.println(date);
			session = sessioFactory.getCurrentSession();
			Query<Appointment> query = session.createQuery("from Appointment a where a.D_id=" + d.getD_Id()
					+ " AND a.date='" + strDate + "' AND a.time=" + timeid + "", Appointment.class);
			aList = query.list();
			System.out.println("Size of List" + aList.size());

			/*
			 * String sdate = sdf2.format(sdf1.parse(request.getParameter("date"))); date =
			 * df.parse(sdate);
			 */ System.out.println("heee" + date);

		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return aList;
	}

	@Transactional
	public List<Appointment> getSpecificPatientAppointment(HttpServletRequest request, HttpServletResponse response) {
		List<Appointment> lAppointments = null;
		HttpSession httpSession = request.getSession();
		FamailyMember famailyMember = (FamailyMember) httpSession.getAttribute("PatientProfile");
		Session session = sessioFactory.getCurrentSession();
		Query<Appointment> query = session.createQuery("from Appointment a where a.P_id=" + famailyMember.getP_id() + "",Appointment.class);
		lAppointments = query.list();
		
		return lAppointments;
	}

	@Transactional
	public boolean iseditable(int id) {
		Date date = new Date();
		Appointment appointment = null;
		Session session = sessioFactory.getCurrentSession();
		appointment=session.get(Appointment.class, id);
		
		
		
		
		/*Appointment appointment = null;
		Session session = sessioFactory.getCurrentSession();
		Query<Appointment> query = session.createQuery("from Appointment a where a.Appointment_ID=" + id + "",
				Appointment.class);
		List<Appointment> lAppointments = query.list();
		for (Appointment appointment1 : lAppointments) {
			appointment = appointment1;
		}*/
		System.out.println(date);
		System.out.println(date.compareTo(appointment.getDate()));
		if (date.compareTo(appointment.getDate()) > 0) {
			return false;
		} else {
			return true;
		}
	}

	@Transactional
	public Appointment getAppointmentInformation(int id) {
		Appointment appointment = null;
		Session session = sessioFactory.getCurrentSession();
		appointment=session.get(Appointment.class, id);
		
		
		
		/*Query<Appointment> query = session.createQuery("from Appointment a where a.Appointment_ID=" + id + "",
				Appointment.class);
		List<Appointment> lAppointments = query.list();
		for (Appointment appointment1 : lAppointments) {
			appointment = appointment1;
		}*/
		return appointment;
	}

	@Transactional
	public void updatePatientAppointment(int appointmentID, int d_id, Date date, int time_id) {
			Session session=sessioFactory.getCurrentSession();
			Appointment appointment=session.get(Appointment.class, appointmentID);
			appointment.setDate(date);
			appointment.setTime(time_id);
			appointment.setD_id(d_id);
			session.update(appointment);
			
	}
	@Transactional
	public List<Appointment> currentPatientAppointment(int d_id)
	{
		int time_id=0;
		List<Appointment> lAppointments=null;
		Session session=sessioFactory.getCurrentSession();
		Date date=new Date();
		Query<Appointment> query;
		if(date.getHours()>8 && date.getHours()<14)
		{
			time_id=1;
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String strDate = dateFormat.format(date);
			System.out.println(strDate);
			query=session.createQuery("from Appointment a where a.D_id='"+d_id+"' AND a.date='"+strDate+"' AND a.time='"+time_id+"'");
			lAppointments=query.list();
		}
		else
		{
			time_id=2;
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String strDate = dateFormat.format(date);

			query=session.createQuery("from Appointment a where a.D_id='"+d_id+"' AND a.date='"+strDate+"' AND a.time='"+time_id+"'",Appointment.class);
			lAppointments=query.list();
			
			System.out.println(strDate);
			System.out.println(lAppointments);
		}
		return lAppointments;
	}
	
	@Transactional
	public List<Appointment> patientAppointmentHistory(int p_id,int d_id)
	{
		Session session=sessioFactory.getCurrentSession();
		Query<Appointment>query=session.createQuery("from Appointment a where a.P_id='"+p_id+"' AND a.D_id='"+d_id+"'");
		List<Appointment> lAppointments=query.list();
		return lAppointments;
	}
	
	@Transactional
	public void updateTreatement(Appointment appointment)
	{
		Session session=sessioFactory.getCurrentSession();
		session.update(appointment);
		
	}
	
	@Transactional
	public List<Appointment> getAllAppointmentViewOfSelectedDate(String sdate,int time_id)
	{
		Session session=sessioFactory.getCurrentSession();
		List<Appointment> lAppointments=null;
		Query<Appointment>query=session.createQuery("from Appointment a where a.date='"+sdate+"' AND a.time="+time_id+"",Appointment.class);
		List<Appointment> appointments=query.list();
		System.out.println("Form App "+appointments);
		return appointments;
	}
	@Transactional
	public boolean updateReceptionistAppointment(Appointment appointment)
	{
		Session session=sessioFactory.getCurrentSession();
		int d_id=appointment.getD_id();
		Date date=appointment.getDate();
		int time_id=appointment.getTime();
		List<DoctorLeave> doctorLeaves=(List<DoctorLeave>)ldao.allLeave(d_id);
		for(DoctorLeave dl:doctorLeaves)
		{
			if(date.compareTo(dl.getLeave_Date())==0)
			{
				if(time_id==appointment.getTime())
				{
					return false;
				}
			}
		}
		session.update(appointment);
		return true;
	}
	
	@Transactional
	public boolean deleteAppointment(Appointment appointment)
	{
			Session session=sessioFactory.getCurrentSession();
			session.delete(appointment);
			return true;
	}
	
	@Transactional
	public List<Appointment> numberOfVisit(int p_id)
	{
			Session session=sessioFactory.getCurrentSession();
			Query<Appointment>query=session.createQuery("from Appointment a where a.P_id="+p_id+" ",Appointment.class);
			return query.list();
			
	}
	
	

/*	@Transactional
	public List<Appointment> getSelectedPatientAppointment(HttpServletRequest request, HttpServletResponse response) {
		List<Appointment> lAppointments = null;
		HttpSession httpSession = request.getSession();
		Patient p = (Patient) httpSession.getAttribute("PatientProfile");
		Session session = sessioFactory.getCurrentSession();
		Query<Appointment> query = session.createQuery("from Appointment a where a.P_id=" + p.getP_id() + "",Appointment.class);
		lAppointments = query.list();
		
		return lAppointments;
	}
*/
	
	
}
