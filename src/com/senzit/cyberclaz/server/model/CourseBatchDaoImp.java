package com.senzit.cyberclaz.server.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

public class CourseBatchDaoImp implements CourseBatchDao {

	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	@Override
	public Integer saveCourseBatchDetailsInDb(CourseBatch cb) {
		
		return (Integer)sessionFactory.getCurrentSession().save(cb);
	}


	@Override
	public int getCourseBatchId(String courseId, String semId, String batchId,
			String classRoomId) {

		String hql="SELECT cb.courseBatchId FROM CourseBatch cb,Course c,Semester sem,Batch b,ClassRoom cr WHERE" +
				" cb.course=c and cb.semester=sem and cb.batch=b and cb.classRoom=cr and c.courseId=:courseId and sem.semId=:semId and b.batchId=:batchId and cr.classRoomId=:classRoomId";

		return (int)sessionFactory.getCurrentSession()
				.createQuery(hql)
				.setParameter("courseId", courseId)
				.setParameter("semId", semId)
				.setParameter("batchId", batchId)
				.setParameter("classRoomId", classRoomId)
				.list().get(0);

		
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getCourseBatchDetails(int cBatchId) {
		String hql = "select c.courseName,s.semName,b.batchName,cr.classRoomNo FROM" +
				" Course c,Semester s,Batch b,ClassRoom cr,CourseBatch cb where cb.courseBatchId=:cBatchId";

		return sessionFactory.getCurrentSession()				
				.createQuery(hql)// LIMIT 1
				.setParameter("cBatchId", cBatchId)
				.list();
	}


	@Override
	public List<Object[]> getcourseBatchDetails() {
		
		String hql="select c.courseName,sem.semName,b.batchName,cr.classRoomNo from Course c,Semester sem,Batch b,ClassRoom cr,CourseBatch cb where cb.course=c and cb.semester=sem and cb.batch=b and cb.classRoom=cr";

		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List results = query.list();
		System.out.println("CourseBatchDetailsTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT"+results);
		return results;	
	}


	@Override
	public Integer getCourseBatchFromClassRoom(String classRoomNo) {
		String hql="SELECT cb.courseBatchId FROM CourseBatch cb,ClassRoom cr WHERE cb.classRoom=cr and cr.classRoomNo=:classRoomNo";

		return (int)sessionFactory.getCurrentSession()
				.createQuery(hql)
				.setParameter("classRoomNo", classRoomNo)
				.list().get(0);
	}

}
