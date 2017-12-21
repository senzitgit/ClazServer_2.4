package com.senzit.cyberclaz.server.model;

import org.hibernate.SessionFactory;

public class ManagerSettingsDaoImp implements ManagerSettingsDao {
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
