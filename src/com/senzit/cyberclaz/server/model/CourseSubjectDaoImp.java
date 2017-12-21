package com.senzit.cyberclaz.server.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

public class CourseSubjectDaoImp implements CourseSubjectDao
{
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public int getCourseSubjectId(int scheduleId) {


		String hql="SELECT cs.courseSubjectId FROM CourseSubject cs,SubjectTeacher st,Schedule s WHERE s.subjectTeacher=st and st.courseSubject=cs and s.scheduleId=:scheduleId";

		int courseSubjectId=(int) sessionFactory.getCurrentSession()
				.createQuery(hql)
				.setParameter("scheduleId", scheduleId)
				.list().get(0);

		return courseSubjectId;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getSubjectList(String rebbonId) {
		
		 String hql="select s.subjectId,s.subjectName from Subject s";

			  return sessionFactory.getCurrentSession()    
			    .createQuery(hql)
			    .list();
	}

	

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getSubjectListForRemoteUser(Integer batchId) {
		
		String hql="select s.subjectId,s.subjectName from Subject s,CourseSubject cs,CourseBatch cb where cs.subject=s " +
		  		"and cs.course=cb.course and cs.semester=cb.semester and  cb.courseBatchId=:courseBatchId";

		  return sessionFactory.getCurrentSession()    
		    .createQuery(hql)
		    .setParameter("courseBatchId", batchId)
		    .list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getSubjectDetailsofUser(String userId) {
	
		String hql="select sub.subjectName,sub.subjectId from Subject sub,CourseSubject cs,SubjectTeacher st,User u where st.teacher=u and st.courseSubject=cs and cs.subject=sub and u.userId=:userId";

		  return sessionFactory.getCurrentSession()    
		    .createQuery(hql)
		    .setParameter("userId", userId)
		    .list();
	}

	@Override
	public Integer saveCourseSubjectDetailsInDb(CourseSubject cSubject) {
		
		return (Integer)sessionFactory.getCurrentSession().save(cSubject);
	}

	@Override
	public int getCourseSubjecIdForInputs(String courseId, String semId, String subId) {
		
		String hql="SELECT cs.courseSubjectId FROM CourseSubject cs,Course c,Semester sem,Subject sub WHERE" +
				" cs.course=c and cs.semester=sem and cs.subject=sub and c.courseId=:courseId and sem.semId=:semId and sub.subjectId=:subId";
	
		int courseSubjectId=(int) sessionFactory.getCurrentSession()
				.createQuery(hql)
				.setParameter("courseId", courseId)
				.setParameter("semId", semId)
				.setParameter("subId", subId)
				.list().get(0);

		return courseSubjectId;
	}

	
	@Override
	public List<Object[]> getcourseSubjectDetailsFromDb() {
	
		String hql="select c.courseName,sem.semName,sub.subjectName from Subject sub,Course c,Semester sem,CourseSubject cs where cs.course=c and cs.subject=sub and cs.semester=sem";

		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List results = query.list();

		return results;
		
		
	}

}
