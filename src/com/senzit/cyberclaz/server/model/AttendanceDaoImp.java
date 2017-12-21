package com.senzit.cyberclaz.server.model;

import java.util.List;

import org.hibernate.SessionFactory;

public class AttendanceDaoImp implements AttendanceDao
{

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Attendance getAttendance(String userId) {
		
		return (Attendance)sessionFactory.getCurrentSession().get(Attendance.class, userId);
	}

	@Override
	public void saveAttendance(Attendance atObj) {
		
		sessionFactory.getCurrentSession().save(atObj);
		
	}

	@Override
	public void updateAttendance(Attendance attendance) {
		
		sessionFactory.getCurrentSession().update(attendance);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getAttendanceDetails(String userId, String month,
			String subId) {
		
		String hql="select att.attendedSession,att.totalNumberOfSession from User u,Subject sub,CourseSubject cs,Attendance att where att.courseSubject=cs and cs.subject=sub and sub.subjectId=:subId and att.user=u and u.userId=:userId and att.currentMonth=:month";
	
//	System.out.println(sessionFactory.getCurrentSession()
//			.createQuery(hql)
//			.setParameter("userId", userId)
//			.setParameter("month", month)
//			.setParameter("subId", subId)
//			.list());
	
	return sessionFactory.getCurrentSession()
			.createQuery(hql)
			.setParameter("userId", userId)
			.setParameter("month", month)
			.setParameter("subId", subId)
			.list();
		

	}
	
}
