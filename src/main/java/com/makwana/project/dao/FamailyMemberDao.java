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
import com.makwana.project.entity.FamailyMember;
import com.makwana.project.entity.Login;
import com.makwana.project.entity.Patient;

@Component
public class FamailyMemberDao 
{
	@Autowired
	SessionFactory sessionFactory;
	@Transactional
	public void addParentPatient(FamailyMember famailyMember,Login login)
	{
		Session  session= sessionFactory.getCurrentSession();
		Query<FamailyMember> query = session.createQuery("from FamailyMember f ORDER BY f.familyId", FamailyMember.class);
		int indexF=0;
		int indexP=0;
		List<FamailyMember> famailyMembers = query.list();
		for(FamailyMember fm:famailyMembers)
		{
			indexF=fm.getFamilyId();
			indexP=fm.getP_id();
		}
		indexF =indexF+1;
		indexP=indexP+1;
		famailyMember.setFamilyId(indexF);
		famailyMember.setP_id(indexP);
		session.save(famailyMember);
		
		}
		

@Transactional
public void addFamilyMember(FamailyMember famailyMember)
{
	Session  session= sessionFactory.getCurrentSession();
	Query<FamailyMember> query = session.createQuery("from FamailyMember f ORDER BY f.P_id", FamailyMember.class);
	int indexF=0;
	int indexP=0;
	List<FamailyMember> famailyMembers = query.list();
	for(FamailyMember fm:famailyMembers)
	{
		indexF=fm.getFamilyId();
		indexP=fm.getP_id();
	}
	indexF =indexF+1;
	indexP=indexP+1;
	famailyMember.setP_id(indexP);
	session.save(famailyMember);
	
	}

@Transactional
public List<FamailyMember> getAllFamilyMember(int fid)
{
	Session session=sessionFactory.getCurrentSession();
	Query<FamailyMember> query=session.createQuery("from FamailyMember f where f.familyId="+fid+"",FamailyMember.class);
	return query.list();
}

@Transactional
public FamailyMember getMember(int pid)
{
	Session session=sessionFactory.getCurrentSession();
	Query<FamailyMember> query=session.createQuery("from FamailyMember f where f.P_id="+pid+"",FamailyMember.class);
	return query.list().get(0);
}

@Transactional
public List<FamailyMember> getAllFamilyMember()
{
	Session session=sessionFactory.getCurrentSession();
	Query<FamailyMember> query=session.createQuery("from FamailyMember f ",FamailyMember.class);
	return query.list();
}

@Transactional
public List<FamailyMember> getTodayPatientID(List<Appointment> lAppointments)
{
Session session=sessionFactory.getCurrentSession();
List<FamailyMember> lFamailyMembers=new ArrayList<FamailyMember>();
Query<FamailyMember>query=session.createQuery("from FamailyMember",FamailyMember.class);
List<FamailyMember> allpatien=query.list();
System.out.println(allpatien);
for(Appointment a:lAppointments)
{
	for(FamailyMember p:allpatien)
	{
		if(a.getP_id()==p.getP_id())
		{
			lFamailyMembers.add(p);
			break;
		}
	}
}
return lFamailyMembers;
}

@Transactional
public FamailyMember geFamailyMember(int id)
{
	FamailyMember famailyMember=null;
	Session session=sessionFactory.getCurrentSession();
	Query<FamailyMember> query=session.createQuery("from FamailyMember f where f.P_id='"+id+"'",FamailyMember.class);
	List<FamailyMember>lPatients=query.list();
	for(FamailyMember p:lPatients)
	{
		if(p.getP_id()==id)
		{
			famailyMember=p;
		}
	}
	return famailyMember;
}

/*@Transactional
public List<FamailyMember> getTodayPatientID(List<Appointment> lAppointments)
{
Session session=sessionFactory.getCurrentSession();
List<FamailyMember> patients=new ArrayList<FamailyMember>();
Query<FamailyMember>query=session.createQuery("from FamailyMember",FamailyMember.class);
List<FamailyMember> allpatien=query.list();
System.out.println(allpatien);
for(Appointment a:lAppointments)
{
	for(FamailyMember p:allpatien)
	{
		if(a.getP_id()==p.getP_id())
		{
			patients.add(p);
			break;
		}
	}
}
return patients;
}*/


@Transactional 
public List<FamailyMember> getAppointmentPatient(List<Appointment> lAppointment)
{
	System.out.println("before");
	return getTodayPatientID(lAppointment);
	
	
}





}
	

