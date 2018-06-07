package com.makwana.project.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.makwana.project.entity.Doctor;
import com.makwana.project.entity.DoctorLeave;


@Component
public class LeaveDao {
	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	public DoctorLeave createLeave(DoctorLeave leave) {
		int Leave_id = 0;
		Date Leave_Date=null;
		System.out.println("from LeaveDao");
		Session session = sessionFactory.getCurrentSession();
		
		
		
		Query<DoctorLeave> query = session.createQuery("from DoctorLeave l ORDER BY l.Leave_ID",DoctorLeave.class);
		List<DoctorLeave> lleaves = query.list();
		for (DoctorLeave l : lleaves)
		{
			Leave_id = l.getLeave_ID();
		}
		leave.setLeave_ID(Leave_id+1);
	/*	HttpSession httpSession = request.getSession();
		Doctor doctor = (Doctor)httpSession.getAttribute("DoctorProfile");
		int d_id = doctor.getD_Id();*/
		
		/*String sdate = request.getParameter("Leave_Date");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Leave_Date = dateFormat.parse(sdate);
			System.out.println(Leave_Date.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		String stime=request.getParameter("LeaveTime");
		int Leave_Time=0;
		if(stime.equals("10.00AM-12.PM"))
		{
			Leave_Time=1;
		}
		else
		{
			Leave_Time=2;
		}
		Leave leave=new Leave(1,1,Leave_Date,1);
		leave.setD_Id(1);
		leave.setLeave_Date(Leave_Date1);
		leave.setLeave_Time(Leave_Time);
		leave.setLeave_ID(1);
		System.out.println(leave.toString());*/
		System.out.println("before save");
		session.save(leave);
		
		return leave;
	}
	
	
	@Transactional
	public List<DoctorLeave> allLeave(int id)
	{
		Session session	=sessionFactory.getCurrentSession();
		Query<DoctorLeave> query=session.createQuery("from DoctorLeave d where d.D_Id="+id+"",DoctorLeave.class);
		//List<DoctorLeave> doctorLeaves=query.list();
		return query.list();
	}
	@Transactional
	public boolean deleteLeave(int leave_id) {
		Session session	=sessionFactory.getCurrentSession();
		//DoctorLeave doctorLeave=(DoctorLeave)session.get(DoctorLeave.class, leave_id);
		DoctorLeave doctorLeave=getLeave(leave_id);
		if(doctorLeave.getLeave_Date().compareTo(new Date())<0)
		{
			return false;
		}
		else
		{
		System.out.println(leave_id);
		
			session.delete(doctorLeave);
			return true;
		}
		}
	
	@Transactional
	public DoctorLeave getLeave(int id)
	{
		Session session=sessionFactory.getCurrentSession();
		DoctorLeave doctorLeave=session.get(DoctorLeave.class, id);
		return doctorLeave;
	}
	
	@Transactional
	public void updateLeave(DoctorLeave doctorLeave)
	{
		Session session=sessionFactory.getCurrentSession();
		session.update(doctorLeave);
	}
	
	@Transactional
	public boolean iseditable(int id)
	{
		Session session=sessionFactory.getCurrentSession();
		
		DoctorLeave doctorLeave=session.get(DoctorLeave.class, id);
		if(doctorLeave.getLeave_Date().compareTo(new Date())<0)
		{
		return  true;
		}
		else
		{
			return false;
		}
	}
	
	
	@Transactional
	public List<DoctorLeave> getAllLeave(String sdate,int time_id)
	{
		
		Session session=sessionFactory.getCurrentSession();
		Query<DoctorLeave> query=session.createQuery("from DoctorLeave dl where dl.Leave_Date='"+sdate+"' AND dl.Leave_Time="+time_id+"",DoctorLeave.class);
		return query.list();
	}
	
	
	
	
	
	
}
