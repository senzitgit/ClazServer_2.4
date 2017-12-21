package com.senzit.cyberclaz.server.cognos;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

public class ProgressReportsDaoImp implements ProgressReportsDao 
{

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Integer saveprogressReportsInDb(ProgressReports pr) {
		return (Integer)sessionFactory.getCurrentSession().save(pr);
	}

	@Override
	public Integer getProgressIdFromDB(String userId, String term, String subId) {
		String hql="select mh.progressId from ProgressReports mh,User u,Subject sub where mh.user=u and mh.subject=sub and sub.subjectId=:subId and mh.term=:term and u.userId=:userId";

		  return (Integer) sessionFactory.getCurrentSession()    
		    .createQuery(hql)
		    .setParameter("userId", userId)	 
		     .setParameter("term", term)	 
		      .setParameter("subId", subId)	 
		    .list().size();
	}

	@Override
	public Integer updateMarksInDB(String userId, String term, String subId,
			String mark) {
		Query query = sessionFactory.getCurrentSession().createQuery("update ProgressReports mh set mh.marks=:mark where mh.term=:term and mh.subject=(select sub from Subject sub where sub.subjectId=:subId) and" +
				" mh.user=(select u from User u where u.userId=:userId)");
	  query.setParameter("subId", subId);
	  query.setParameter("mark", mark);
		query.setParameter("userId", userId);
		query.setParameter("term", term);
		return query.executeUpdate();
	}

}
