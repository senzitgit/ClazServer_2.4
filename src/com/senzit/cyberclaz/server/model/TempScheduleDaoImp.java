package com.senzit.cyberclaz.server.model;

import org.hibernate.SessionFactory;

public class TempScheduleDaoImp implements TempScheduleDao{
	
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Integer saveTempScheduleDetails(TempSchedule ts) {
		
		return (Integer)sessionFactory.getCurrentSession().save(ts);
		
	}



}
