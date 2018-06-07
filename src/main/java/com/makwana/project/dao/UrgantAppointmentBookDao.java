package com.makwana.project.dao;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.makwana.project.entity.UrgantAppointmentBook;

@Component
public class UrgantAppointmentBookDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	public int getUrgentAppointmentId()
	{
		Session session=sessionFactory.getCurrentSession();
		Query<UrgantAppointmentBook> query=session.createQuery("from UrgantAppointmentBook",UrgantAppointmentBook.class);
		return query.list().size();
	}

}
