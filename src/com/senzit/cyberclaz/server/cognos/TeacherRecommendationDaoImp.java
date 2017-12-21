package com.senzit.cyberclaz.server.cognos;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

public class TeacherRecommendationDaoImp implements TeacherRecommendationDao 
{

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Integer saveteacherRecommendationInDb(TeacherRecommendation tr) {
		return (Integer)sessionFactory.getCurrentSession().save(tr);
		
		
	}
 
	@Override 
	public Integer getTeacherRecomentationId(String userId, String subId,
			String term) {
		
		String hql="select mh.teacherRId from TeacherRecommendation mh,User u,Subject sub where mh.user=u and mh.subject=sub and mh.term=:term and sub.subjectId=:subId and u.userId=:userId";

		  return (Integer) sessionFactory.getCurrentSession()    
		    .createQuery(hql)
		       .setParameter("term", term)	 
		      .setParameter("subId", subId)
		       .setParameter("userId", userId)	 
		    .list().size();
		
		
	}

	@Override
	public Integer updateTeacherRecommendation(String userId, String subId,
			String term, String rating) {
		
		Query query = sessionFactory.getCurrentSession().createQuery("update TeacherRecommendation mh set mh.rating=:rating where mh.term=:term and mh.subject=(select sub from Subject sub where sub.subjectId=:subId) and" +
				" mh.user=(select u from User u where u.userId=:userId)");
	  query.setParameter("userId", userId);
		query.setParameter("subId", subId);
		query.setParameter("term", term);
		query.setParameter("rating", rating);
		return query.executeUpdate();

		
	}


}
