package com.senzit.cyberclaz.server.cognos;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

public class SubjectPerformanceDaoImp implements SubjectPerformanceDao {
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Integer saveSubjectPerformanceDetailsInDb(SubjectPerformance sp) {
		return (Integer) sessionFactory.getCurrentSession().save(sp);
	}

	@Override
	public int subjectPerformanceDetailsUpdation(String userId,String subjectId,String rating) {
		
		//String hql ="update SubjectPerformance sp set sp.rating=:rating where sp.subject=(select sub from Subject sub where sub.subjectId=:subjectId) and sp.user=(select u from User u where u.userId=:userId)";
		
//		System.out.println("WWWWWWWWWWWWWWWWWWWWWWWWWW"+sessionFactory.getCurrentSession().createQuery(hql)
//				.setParameter("rating", rating)
//				.setParameter("subjectId", subjectId)
//				.setParameter("userId", userId)
//				.executeUpdate());
//		
//		return sessionFactory.getCurrentSession().createQuery(hql)
//				.setParameter("rating", rating)
//				.setParameter("subjectId", subjectId)
//				.setParameter("userId", userId)
//				.executeUpdate();
	
		
		Query query = sessionFactory.getCurrentSession().createQuery("update SubjectPerformance sp set sp.rating=:rating where" +
				" sp.subject=(select sub from Subject sub where sub.subjectName=:subjectId) and" +
				" sp.user=(select u from User u where u.userId=:userId)");
		
	    query.setParameter("rating", rating);
		query.setParameter("subjectId", subjectId);
		query.setParameter("userId", userId);
		System.out.println("WWWWWWWWWWWWWWWWW"+query.executeUpdate());
		return query.executeUpdate();
	}
}
