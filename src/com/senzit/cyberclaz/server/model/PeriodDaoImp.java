package com.senzit.cyberclaz.server.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

public class PeriodDaoImp implements PeriodDao{

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) 
	{
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Integer savePeriodDetailsInDb(Period p) {
		return (Integer)sessionFactory.getCurrentSession().save(p);	
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getPeriodDetails() {
		String hql = "select startTime,endTime FROM Period";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List results = query.list();
		return results;
	}

}
