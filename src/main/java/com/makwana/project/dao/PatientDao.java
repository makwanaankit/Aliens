package com.makwana.project.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.makwana.project.entity.Appointment;
import com.makwana.project.entity.Login;
import com.makwana.project.entity.Patient;

@Component
public class PatientDao {

	@Autowired
	SessionFactory sessionFactory;
	

	
	@Transactional
	public void addParentPatient(Patient patient,Login login) {
		System.out.println("Inserted1");

		Session  session= sessionFactory.getCurrentSession();
		Query<Patient> query = session.createQuery("from Patient p ORDER BY p.P_id ", Patient.class);
		int index=0;
		List<Patient> pts = query.list();
		for(Patient p:pts)
		{
			index=p.getP_id();
		}
		patient.setP_id(index+1);
		
		Session session2=sessionFactory.getCurrentSession();
		session.save(patient);
		System.out.println("Inserted2");
		login.setType("Patient");
		session2.save(login);
		}

	@Transactional
	public List<Patient> getAllPatient() {

		Session session = sessionFactory.getCurrentSession();
		Query<Patient> query = session.createQuery("from Patient", Patient.class);

		List<Patient> pts = query.list();

		return pts;

	}
@Transactional
	public Patient getPatient(Login login) {
		int id=0;
		Patient p=null;
		Session session=sessionFactory.getCurrentSession();
		
		Query<Patient> query=session.createQuery("from Patient p where p.user_name='"+login.getUser_name()+"' AND p.password='"+login.getPassword()+"'");
		List<Patient> lPatients=query.list();
		for(Patient pp:lPatients)
		{
			p=pp;
		}
		return p;
	}

		@Transactional
			public List<Patient> getTodayPatientID(List<Appointment> lAppointments)
			{
			Session session=sessionFactory.getCurrentSession();
			List<Patient> patients=new ArrayList<Patient>();
			Query<Patient>query=session.createQuery("from Patient",Patient.class);
			List<Patient> allpatien=query.list();
			System.out.println(allpatien);
			for(Appointment a:lAppointments)
			{
				for(Patient p:allpatien)
				{
					if(a.getP_id()==p.getP_id())
					{
						patients.add(p);
						break;
					}
				}
			}
			return patients;
			}
		
		@Transactional
		public Patient getPatient(int id)
		{
			Patient patient=null;
			Session session=sessionFactory.getCurrentSession();
			Query<Patient> query=session.createQuery("from Patient p where p.P_id='"+id+"'");
			List<Patient>lPatients=query.list();
			for(Patient p:lPatients)
			{
				if(p.getP_id()==id)
				{
					patient=p;
				}
			}
			return patient;
		}
		
		@Transactional 
		public List<Patient> getAppointmentPatient(List<Appointment> lAppointment)
		{
			System.out.println("before");
			return getTodayPatientID(lAppointment);
			
			
		}
		
}
