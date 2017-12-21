package com.senzit.cyberclaz.server.model;

import java.util.List;

import org.hibernate.SessionFactory;



public class VideoRatingDaoImp implements VideoRatingDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	
	@Override

	public int saveExcellentRatingDetailsInDb(String ratingvalue) {
		
		VideoRating vr=new VideoRating();
		vr.setVideoRate(ratingvalue);
		
		return (Integer)sessionFactory.getCurrentSession().save(vr);
	}



	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getCountValue(String ratingvalue) {

    String hql ="select ratingId from VideoRating where videoRate=:ratingvalue ";
             
 return (List<Integer>) sessionFactory.getCurrentSession()
				.createQuery(hql)
				.setParameter("ratingvalue", ratingvalue)
				.list();
	
	}





}
