package com.senzit.cyberclaz.server.cognos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

public class MaximumHoursDaoImp implements MaximumHoursDao 
{

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void saveWorkingHoursinDB(MaximumHours mh) {
		
		sessionFactory.getCurrentSession().save(mh);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getTeacherWorkingHoursTillNow(String teacherId) {
		
		String hql="select mh.minutes from MaximumHours mh,User u where mh.user=u and u.userId=:userId";

		  return (List<Integer>) sessionFactory.getCurrentSession()    
		    .createQuery(hql)
		    .setParameter("userId", teacherId)	 
		    .list();
	}
	public void updateTeacherAttendance(MaximumHours mh)
	{
		sessionFactory.getCurrentSession().update(mh);
	}
//////////////////////////need to edit///////////////////
	@Override
	public int updateTeacherAttendance(String teacherId, int workMinutes,int batchId) {
		System.out.println("TTTTTTTTTTTTTTTTTTTTTTTIIIIIIIIIIIIIIIIIIIIMMMMMMMMMMMMMMM"+workMinutes);
//		Query query = sessionFactory.getCurrentSession().createQuery("update MaximumHours mh,User u set mh.minutes=:workMinutes where mh.user=u and u.userId=:userId");
		Query query = sessionFactory.getCurrentSession().createQuery("update MaximumHours mh set mh.minutes=:workMinutes where mh.courseBatch=(select cb from CourseBatch cb where cb.courseBatchId=:batchId) and" +
				" mh.user=(select u from User u where u.userId=:userId)");
	  query.setParameter("workMinutes", workMinutes);
		query.setParameter("userId", teacherId);
		query.setParameter("batchId", batchId);
		return query.executeUpdate();

		
	}

	@Override
	public Integer getMaximumHoursStatus(String teacherId) {
		
		String hql="select mh.maxHourId from MaximumHours mh,User u where mh.user=u and u.userId=:userId";

		  return (Integer) sessionFactory.getCurrentSession()    
		    .createQuery(hql)
		    .setParameter("userId", teacherId)	 
		    .list().size();
		
		
	}



}
