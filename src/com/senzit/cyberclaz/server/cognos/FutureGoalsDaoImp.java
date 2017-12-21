package com.senzit.cyberclaz.server.cognos;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

public class FutureGoalsDaoImp implements FutureGoalsDao 
{

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Integer saveFutureGoalsInDb(FutureGoals pr)
	{
		return (Integer) sessionFactory.getCurrentSession().save(pr);
		
	}

	@Override
	public Integer getGoalIdForUser(String userId, Integer cBatchId) {
		String hql="select mh.goalId from FutureGoals mh,User u,CourseBatch cb where mh.user=u and mh.courseBatch=cb and cb.courseBatchId=:cBatchId and u.userId=:userId";

		  return (Integer) sessionFactory.getCurrentSession()    
		    .createQuery(hql)
		    .setParameter("userId", userId)	 	     	 
		    .setParameter("cBatchId", cBatchId)	 
		    .list().size();
	}

	@Override
	public Integer updateGoalInDB(String userId, Integer cBatchId, String goal) {
		
		Query query = sessionFactory.getCurrentSession().createQuery("update FutureGoals mh set mh.goal=:goal where mh.courseBatch=(select cb from CourseBatch cb where cb.courseBatchId=:cBatchId) and" +
				" mh.user=(select u from User u where u.userId=:userId)");
	  
	  query.setParameter("cBatchId", cBatchId);
		query.setParameter("userId", userId);
		query.setParameter("goal", goal);
		return query.executeUpdate();
	}

}
