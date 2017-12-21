package com.senzit.cyberclaz.server.cognos;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

public class TargetAttendancePercentageDaoImp implements TargetAttendancePercentageDao{
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Integer getAttendanceIdFromTeacherCourseBatch(String teacherId,
			Integer cBatchId) {

		String hql="SELECT tp.attendanceId FROM TargetAttendancePercentage tp,CourseBatch cb,User u WHERE tp.user=u and tp.courseBatch=cb and u.userId=:teacherId and cb.courseBatchId=:cBatchId";

		return (int)sessionFactory.getCurrentSession()
				.createQuery(hql)
				.setParameter("teacherId", teacherId)
				.setParameter("cBatchId", cBatchId)
				.list().size();
	}

	@Override
	public Integer savetargetAttendancePercentageDetailsInDb(
			TargetAttendancePercentage tp) {
		
		return (Integer) sessionFactory.getCurrentSession().save(tp);
	}

	@Override
	public Integer updateAttendancePercentageUpdation(String teacherId,
			Integer cBatchId, String attendancePercentage) {
		
		Query query = sessionFactory.getCurrentSession().createQuery("update TargetAttendancePercentage tp set tp.targetAttendance=:passPercentage where tp.courseBatch=(select cb from CourseBatch cb where cb.courseBatchId=:cBatchId) and" +
				" tp.user=(select u from User u where u.userId=:teacherId)");
	  query.setParameter("passPercentage", attendancePercentage);
		query.setParameter("teacherId", teacherId);
		query.setParameter("cBatchId", cBatchId);
		return query.executeUpdate();
	}

}
