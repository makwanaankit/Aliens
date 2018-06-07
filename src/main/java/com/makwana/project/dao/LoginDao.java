package com.makwana.project.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.makwana.project.entity.Doctor;
import com.makwana.project.entity.Login;
import com.makwana.project.entity.Patient;

@Component
public class LoginDao
{
	@Autowired
	SessionFactory sessionFactory;
	@Transactional
	public boolean getLogin(Login login)
	{
		Doctor d1=null;
		Patient p1=null;
		Session session=sessionFactory.getCurrentSession();
		Query<Login> query=session.createQuery("from Login l  where l.user_name='"+login.getUser_name()+"' AND l.password='"+login.getPassword()+"' AND  l.type='"+login.getType()+"'");
		List<Login> llogin=query.list();
		String cls="";
		
		if(llogin.size()==1)
		{
			System.out.println("Successful");
			Session session2=sessionFactory.getCurrentSession();
		/*	
			for(Login l:llogin)
			{
					cls=l.getType();
			}
			if(cls.equals("Doctor"))
			{
				
				Query<Doctor> query2=session2.createQuery("from doctor",Doctor.class);
				List<Doctor> lDoctors=query2.list();
				for(Doctor d:lDoctors)
				{
					
					
				}
				return d1;
			}
			if(cls.equals("Patient"))
			{
				
				Query<Patient> query2=session2.createQuery("from patient",Patient.class);
				List<Patient> lPatients=query2.list();
				for(Patient p:lPatients)
				{
					
					if((p.getUser_name().equals(login.getUser_name())) && (p.getPassword().equals(login.getPassword())))
					{
						p1=p;
					}
					
				}
				return p1;*/
	
			
			
			
			//Switch case for patient doctor and other
			//Query<cls> query2=session2.createQuery("from Patient p where p.user_name='"+login.getUser_name()+"' AND p.password='"+login.getPassword()+"'",cls+".class");
			
			return true;
		}
		else
		{
			String sr;
			System.out.println("unSucc");
			return false;
		}
		
		
		
	}
	
}
