package com.senzit.cyberclaz.server.cognos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

public class TopPerformerDaoImp implements TopPerformerDao {
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	@Override
	public Integer getTopPerformerIdTeacher(int classEventId) {
		
		String hql="select tp.topPerformerId from TopPerformer tp,ClassEventDetail ce where tp.classEventDetail=ce and ce.classEventDetailId=:classEventId";
		
				  return (Integer) sessionFactory.getCurrentSession()    
				    .createQuery(hql)
				    .setParameter("classEventId", classEventId)	 
				    .list().size();
		
	}


	@Override
	public Integer saveUserRatingAboutTeacher(TopPerformer tp) {
		return (Integer) sessionFactory.getCurrentSession().save(tp);
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public Integer getTeacherRateCount(int classEventId) {
		
		String hql="select tp.likes from TopPerformer tp,ClassEventDetail ce where tp.classEventDetail=ce and ce.classEventDetailId=:classEventId";
		
		  return (Integer) sessionFactory.getCurrentSession()    
		    .createQuery(hql)
		    .setParameter("classEventId", classEventId)	 
		    .list().get(0);

	}


	@Override
	public int updateTheRateCount(int classEventId, Integer rateResult) {
	
		Query query = sessionFactory.getCurrentSession().createQuery("update TopPerformer tp set tp.likes=:rateResult where tp.classEventDetail=(select ce from ClassEventDetail ce where ce.classEventDetailId=:classEventId)");
		  query.setParameter("classEventId", classEventId);
			query.setParameter("rateResult", rateResult);
			return query.executeUpdate();
		
	}

}
