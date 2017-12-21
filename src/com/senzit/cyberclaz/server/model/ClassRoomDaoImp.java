package com.senzit.cyberclaz.server.model;

import java.util.List;

import org.hibernate.SessionFactory;

public class ClassRoomDaoImp implements ClassRoomDao{

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Integer saveClassRoomDetailsInDb(ClassRoom cr) {
		
		return (Integer)sessionFactory.getCurrentSession().save(cr);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getClassRoomDetails() {
String hql="select classRoomNo from ClassRoom";
		
		return sessionFactory.getCurrentSession()
				.createQuery(hql)
				.list();
	}

	

}
