package com.senzit.cyberclaz.server.cognos;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

public class TargetPassPercentageDaoImp implements TargetPassPercentageDao {
	

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Integer savetargetPassPercentageDetailsInDb(TargetPassPercentage tp) {
		return (Integer) sessionFactory.getCurrentSession().save(tp);
	
	}

	@Override
	public Integer getTargetIdFromTeacherCourseBatch(String teacherId,
			Integer cBatchId) {
		
		String hql="SELECT tp.targetId FROM TargetPassPercentage tp,CourseBatch cb,User u WHERE tp.user=u and tp.courseBatch=cb and u.userId=:teacherId and cb.courseBatchId=:cBatchId";

		return (int)sessionFactory.getCurrentSession()
				.createQuery(hql)
				.setParameter("teacherId", teacherId)
				.setParameter("cBatchId", cBatchId)
				.list().size();
	}

	@Override
	public Integer updatePassPercentageUpdation(String teacherId,
			Integer cBatchId, String passPercentage) {
	
		Query query = sessionFactory.getCurrentSession().createQuery("update TargetPassPercentage tp set tp.target=:passPercentage where tp.courseBatch=(select cb from CourseBatch cb where cb.courseBatchId=:cBatchId) and" +
				" tp.user=(select u from User u where u.userId=:teacherId)");
	  query.setParameter("passPercentage", passPercentage);
		query.setParameter("teacherId", teacherId);
		query.setParameter("cBatchId", cBatchId);
		return query.executeUpdate();
	}

}
