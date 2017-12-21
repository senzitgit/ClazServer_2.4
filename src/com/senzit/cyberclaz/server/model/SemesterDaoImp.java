package com.senzit.cyberclaz.server.model;

import java.util.List;

import org.hibernate.SessionFactory;

public class SemesterDaoImp implements SemesterDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public Integer saveSemDetailsInDb(String semName) {
		return (Integer)sessionFactory.getCurrentSession().save(semName);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getFullSemesterList() {
	String hql="select semName from Semester";
		
		return sessionFactory.getCurrentSession()
				.createQuery(hql)
				.list();
	}
	
	


}
