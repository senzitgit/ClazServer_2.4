package com.senzit.cyberclaz.server.cognos;

import org.hibernate.SessionFactory;

public class TargetProgressDaoImp implements TargetProgressDao 
{

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


}
