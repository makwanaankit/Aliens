package com.makwana.project.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.makwana.project.entity.Admin;
import com.makwana.project.entity.Doctor;
import com.makwana.project.entity.Login;

@Component
public class AdminDao {
	@Autowired
SessionFactory sessionFactory;

	@Transactional
	public Admin getAdmin(Login login) {

	Admin admin=null;
		
		Session session=sessionFactory.getCurrentSession();
		Query<Admin> query=session.createQuery("from Admin a where a.user_name='"+login.getUser_name()+"' AND a.password='"+login.getPassword()+"'");
		List<Admin> lAdmin=query.list();
		for(Admin ad1:lAdmin)
		{
			admin=ad1;
		}
		return admin;
		
	}


}
