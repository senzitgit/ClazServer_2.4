package com.senzit.cyberclaz.server.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

public class ViewersDaoImp implements ViewersDao{
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Integer saveViewersDetailsInDb(Viewers v) {
		
		return (Integer)sessionFactory.getCurrentSession().save(v);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getViewersList(int clazEventDetailId) {
		
		ClassEventDetail ce=new ClassEventDetail();
		ce.setClassEventDetailId(clazEventDetailId);
	//	String hql="select u.userId from User u,Log l,Viewers v where l.user=u and v.user=u and v.classEventDetail=:classEventDetail ";
		String hql="select u.userId from User u,Viewers v where v.user=u and v.classEventDetail=:classEventDetail ";
		return sessionFactory.getCurrentSession()
				.createQuery(hql)
				.setParameter("classEventDetail",ce)
				.list();
	}

	@Override
	public Integer saveRatingDetailsInDb(Viewers v) {
		
		return (Integer)sessionFactory.getCurrentSession().save(v);
		}

	@SuppressWarnings("unchecked")
	@Override
    public List<Integer> getUserCountValue() {
		
		String hql="select c.classEventDetailId from Viewers v,ClassEventDetail c where v.classEventDetail=c ORDER BY v.userratecount DESC";
		
		return sessionFactory.getCurrentSession()
				.createQuery(hql)
				.list();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getViewersStatus(int clazEventDetailId) {
		
		
		String hql="select v.viewerId from Viewers v,ClassEventDetail c where v.classEventDetail=c and c.classEventDetailId=:clazEventDetailId";

		  return (Integer) sessionFactory.getCurrentSession()    
		    .createQuery(hql)
		    .setParameter("clazEventDetailId", clazEventDetailId)	 
		    .list().size();
		
	
	}

	@Override
	public int updateViewRateofVideo(int clazEventDetailId, int rateCount) {
		
		Query query = sessionFactory.getCurrentSession().createQuery("update Viewers v set v.userRateCount=:rateCount where v.classEventDetail=(select c from ClassEventDetail c where c.classEventDetailId=:clazEventDetailId)");
		  query.setParameter("rateCount", rateCount);
			query.setParameter("clazEventDetailId", clazEventDetailId);
			return query.executeUpdate();

		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getViewerRateCountTillNow(int clazEventDetailId)
	{
		String hql="select v.userRateCount from Viewers v,ClassEventDetail c where v.classEventDetail=c and c.classEventDetailId=:clazEventDetailId";

		  return (List<Integer>) sessionFactory.getCurrentSession()    
		    .createQuery(hql)
		    .setParameter("clazEventDetailId", clazEventDetailId)	 
		    .list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public Integer getViewersIdofThisClassEvent(String userId, int clazEventDetailId)
	{
		String hql="select v.viewerId from Viewers v,ClassEventDetail c,User u where v.user=u and v.classEventDetail=c and u.userId=:userId and c.classEventDetailId=:clazEventDetailId";

		  return (Integer) sessionFactory.getCurrentSession()    
		    .createQuery(hql)
		    .setParameter("clazEventDetailId", clazEventDetailId)	 
		    .setParameter("userId", userId)	
		    .list().size();
	}


//	@Override
//	public Integer updateRatingDetailsInDb(int clazEventDetailId,int rateCount ) {
//		
//		Query query = sessionFactory.getCurrentSession().createQuery("update Viewers v set v.userRateCount=:rateCount where v.classEventDetail=(select c from ClassEventDetail c where c.classEventDetailId=:clazEventDetailId)");
//		   query.setParameter("rateCount", rateCount);
//			query.setParameter("clazEventDetailId", clazEventDetailId);
//			return query.executeUpdate();
//	}

	@Override
	public Integer updateInViewerTable(int clazEventDetailId,int rateValue) {
		Query query = sessionFactory.getCurrentSession().createQuery("update Viewers v set v.userRateCount=:rateCount where v.classEventDetail=(select c from ClassEventDetail c where c.classEventDetailId=:clazEventDetailId)");
		   query.setParameter("rateCount", rateValue);
			query.setParameter("clazEventDetailId", clazEventDetailId);
			return query.executeUpdate();
	
	}





}


	
	

