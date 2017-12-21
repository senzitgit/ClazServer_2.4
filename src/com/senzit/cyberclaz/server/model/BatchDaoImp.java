package com.senzit.cyberclaz.server.model;

import java.util.List;

import org.hibernate.SessionFactory;

public class BatchDaoImp implements BatchDao{

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Integer saveBatchDetailsInDb(Batch batch) {
		return (Integer)sessionFactory.getCurrentSession().save(batch);
	}

	@Override
	public String getbatchIdFromBatchName(String bName) {
		
		String hql="SELECT b.batchId FROM Batch b WHERE b.batchName=:bName";

		return (String)sessionFactory.getCurrentSession()
				.createQuery(hql)
				.setParameter("bName", bName)
				.list().get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getBatchDetails() {
    String hql="select batchName from Batch";
		
		return sessionFactory.getCurrentSession()
				.createQuery(hql)
				.list();
	}

	
	
}
