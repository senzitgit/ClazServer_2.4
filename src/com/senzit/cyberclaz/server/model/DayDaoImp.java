package com.senzit.cyberclaz.server.model;

import java.util.List;

import org.hibernate.SessionFactory;

public class DayDaoImp implements DayDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Integer saveDayDetailsInDb(Day d) {
		return (Integer)sessionFactory.getCurrentSession().save(d);	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getDayDetails() {
		
 String hql="select dayName from Day";
		
		return sessionFactory.getCurrentSession()
				.createQuery(hql)
				.list();
	}

}


