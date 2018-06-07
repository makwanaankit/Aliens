package com.makwana.project.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.makwana.project.entity.Login;
import com.makwana.project.entity.Receptionist;

@Component
public class ReceptionistDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	public Receptionist getReceptionstOBJ(Login login)
	{
		Session session=sessionFactory.getCurrentSession();
		Receptionist receptionist=null;
		Query<Receptionist>query=session.createQuery("from Receptionist r where r.user_name='"+login.getUser_name()+"' AND r.password='"+login.getPassword()+"'",Receptionist.class);
		List<Receptionist> lReceptionists=query.list();
		for(Receptionist r:lReceptionists)
		{
			receptionist=r;
		}
		return receptionist;
	}

}
