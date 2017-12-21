package com.senzit.cyberclaz.server.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

public class TopSessionDaoImp implements TopSessionDao {
	

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Integer getTopSessionIdFromEventId(int clazEventDetailId) {
		
		String hql="select v.topSessionId from TopSession v,ClassEventDetail c where v.classEventDetail=c and c.classEventDetailId=:clazEventDetailId";
		//String hql="select * from TopSession";
		System.out.println("VAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"+sessionFactory.getCurrentSession()    
		    .createQuery(hql)
		    .setParameter("clazEventDetailId", clazEventDetailId)	
		    .list().size());
		
		  return (Integer) sessionFactory.getCurrentSession()    
		    .createQuery(hql)
		    .setParameter("clazEventDetailId", clazEventDetailId)	
		    .list().size();
	}

	@Override
	public Integer saveRatingDetailsInDb(TopSession ts) {
		return (Integer)sessionFactory.getCurrentSession().save(ts); 
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getLikeCountTillNow(int clazEventDetailId) {
		
		String hql="select v.like from TopSession v,ClassEventDetail c where v.classEventDetail=c and c.classEventDetailId=:clazEventDetailId";

		  return (List<Integer>) sessionFactory.getCurrentSession()    
		    .createQuery(hql)
		    .setParameter("clazEventDetailId", clazEventDetailId)	 
		    .list();
	}

	@Override
	public Integer updateViewerLikeCount(int clazEventDetailId, int rateCount) {
		
		Query query = sessionFactory.getCurrentSession().createQuery("update TopSession ts set ts.like=:rateCount where ts.classEventDetail=(select ce from ClassEventDetail ce where ce.classEventDetailId=:clazEventDetailId)");
		query.setParameter("rateCount", rateCount);
		query.setParameter("clazEventDetailId", clazEventDetailId);
	
		return query.executeUpdate();
	}
	@Override
	public Integer updateViewerDisLikeCount(int clazEventDetailId, int rateCount)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("update TopSession ts set ts.disLike=:rateCount where ts.classEventDetail=(select ce from ClassEventDetail ce where ce.classEventDetailId=:clazEventDetailId)");
		query.setParameter("rateCount", rateCount);
		query.setParameter("clazEventDetailId", clazEventDetailId);
	
		return query.executeUpdate();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getLikeCountNow(int clazEventDetailId) {
		
		String hql="select v.like,v.disLike from TopSession v,ClassEventDetail c where v.classEventDetail=c and c.classEventDetailId=:clazEventDetailId";

		  return (List<Object[]>) sessionFactory.getCurrentSession()    
		    .createQuery(hql)
		    .setParameter("clazEventDetailId", clazEventDetailId)	 
		    .list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getDisLikeCountTillNow(int clazEventDetailId) {
		
		String hql="select v.disLike from TopSession v,ClassEventDetail c where v.classEventDetail=c and c.classEventDetailId=:clazEventDetailId";

		  return (List<Integer>) sessionFactory.getCurrentSession()    
		    .createQuery(hql)
		    .setParameter("clazEventDetailId", clazEventDetailId)	 
		    .list();
	}

}
