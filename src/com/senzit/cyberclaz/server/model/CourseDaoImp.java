package com.senzit.cyberclaz.server.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

public class CourseDaoImp implements CourseDao {


	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Integer saveCourseDetailsInDb(Course course) {
		return (Integer)sessionFactory.getCurrentSession().save(course);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getCourseSemSubIdz(String cName, String sName,
			String subName) {
		
		String hql="select c.courseId,sem.semId,sub.subjectId from Course c,Semester sem,Subject sub where c.courseName=:cName and sem.semName=:sName and sub.subjectName=:subName";
		return sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("cName", cName)
				.setParameter("sName", sName)
				.setParameter("subName", subName)
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getCourseSemBatchandClassRoomIdz(String cName,
			String sName, String bName, String cRoomNo) {
		
		String hql="select c.courseId,sem.semId,b.batchId,cr.classRoomId from Course c,Semester sem,Batch b,ClassRoom cr where c.courseName=:cName and sem.semName=:sName and b.batchName=:bName and cr.classRoomNo=:cRoomNo";
		return sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("cName", cName)
				.setParameter("sName", sName)
				.setParameter("bName", bName)
				.setParameter("cRoomNo", cRoomNo)
				.list();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getDetailsAboutCourse() {
		
		String hql = "select courseName,courseDescription,courseCategory,courseDuration,currentScheme,semOrYear,department,distantOrRegular FROM Course";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List results = query.list();
		return results;
		
		


	}


}
