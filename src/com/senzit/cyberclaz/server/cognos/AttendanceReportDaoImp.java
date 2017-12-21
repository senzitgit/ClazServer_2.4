package com.senzit.cyberclaz.server.cognos;

import org.hibernate.SessionFactory;

public class AttendanceReportDaoImp implements AttendanceReportDao 
{

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
