package com.makwana.project.dao;

import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.makwana.project.entity.Appointment;
import com.makwana.project.entity.Doctor;
import com.makwana.project.entity.Login;
import com.makwana.project.entity.Patient;

@Component
public class DoctorDao 
{
	@Autowired
	SessionFactory sessionFactory;
	
	
	@Transactional
	public void addDoctor(Doctor doctor,Login login) 
	{
		
		System.out.println("Inserted1");
		int index=0;
		Session	session=sessionFactory.getCurrentSession();
		
		Query<Doctor> query=session.createQuery("from Doctor d ORDER BY d.D_Id",Doctor.class);
		System.out.println("Inserted1");
		 List<Doctor> ldoctor=query.list();			
		
		for(Doctor d: ldoctor)
		{
			index=d.getD_Id();
		}
		doctor.setD_Id(index+1);
		session.save(doctor);
		System.out.println(doctor);
		
		
		login.setType("Doctor");
		Session session2=sessionFactory.getCurrentSession();
		session2.save(login);
		
	}
	
	@Transactional
	public List<Doctor> getAllDoctor()
	{
		System.out.println("Hello1");
		Session session=sessionFactory.getCurrentSession();
		
		
		
		System.out.println("Hello2");
		Query<Doctor> query=session.createQuery("from Doctor d ORDER BY d.D_Id",Doctor.class);
		List<Doctor> ldor=query.list();
		System.out.println("Hello3");
		return ldor;
		
	}
	
	public void hello()
	{
		System.out.println("Hello");
		Session session=sessionFactory.getCurrentSession();
		
		System.out.println("Hello");
	}

	@Transactional
	public Doctor getDoctor(Login login) {
		Doctor d=null;
		
		Session session=sessionFactory.getCurrentSession();
		Query<Doctor> query=session.createQuery("from Doctor d where d.user_name='"+login.getUser_name()+"' AND d.password='"+login.getPassword()+"'");
		List<Doctor> lDoctors=query.list();
		for(Doctor dd:lDoctors)
		{
			d=dd;
		}
		return d;
	}
@Transactional
	public int getAppointCount(int d_Id) 
	{
		System.out.println("hello1");
		Session session=sessionFactory.getCurrentSession();
		System.out.println("hello2");

		Query<Appointment> query=session.createQuery("from Appointment a where a.D_id="+d_Id+"",Appointment.class);
		System.out.println("hello3");

		List<Appointment> lAppointments=query.list();
		System.out.println("hello4");
		
		return lAppointments.size();
	}
	
@Transactional
public List<Doctor> getDoctors(List<Appointment> lAppointments)
{
	
	Session session=sessionFactory.getCurrentSession();
	Query<Doctor> query=session.createQuery("from Doctor d ORDER BY d.D_Id",Doctor.class);
	return query.list();
}

@Transactional
public Doctor getDoctor(int d_id)
{
	Session session=sessionFactory.getCurrentSession();
	Query<Doctor> query=session.createQuery("from Doctor d where d.	D_Id="+d_id+"",Doctor.class);
	return query.list().get(0);
}


}
