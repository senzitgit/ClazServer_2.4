package com.senzit.cyberclaz.server.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

public class SubjectTeacherDaoImp implements SubjectTeacherDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> teacherIdForClazEventDetailId(int clazEventDetailId) {
		

	
			 String hql="select u.userId from User u,Schedule s,ClassEventDetail e,SubjectTeacher st" +
						" where e.schedule=s and s.subjectTeacher=st and st.teacher=u and e.classEventDetailId=:classEventDetailId ";
				return sessionFactory.getCurrentSession()
						.createQuery(hql)
						.setParameter("classEventDetailId", clazEventDetailId)
						.list();
		
	}

	@Override
	public int getSubTeacherId(String teacherId) {
	
		String hql="select st.subjectTeacherId from SubjectTeacher st,Schedule s,User u where s.subjectTeacher=st and st.teacher=u" +
				" and u.userId=:teacherId";
		int subTeacherId=(int) sessionFactory.getCurrentSession()
				.createQuery(hql)
				.setParameter("teacherId", teacherId)
		
				.list().get(0);

		return subTeacherId;
	}

	@Override
	public Integer saveSubjectTeacherDetailsInDb(SubjectTeacher st) {
	
		return (Integer)sessionFactory.getCurrentSession().save(st);
	}

	@Override
	public List<Object[]> getSubjectTeacherDetails() {
//		select u.userId,c.courseName,sem.semName,sub.subjectName,b.batchName from User u,Course c,Semester sem,Subject sub,Batch b,SubjectTeacher st,CourseSubject cs where 
//		st.userId=u.userId and st.courseSubjectId=cs.courseSubjectId
//				 and cs.courseId=c.courseId and cs.semesterId=sem.semId and cs.subjectId=sub.subjectId and st.batchId=b.batchId;
				         
		String hql="select u.userId,c.courseName,sem.semName,sub.subjectName,b.batchName from User u,Course c,Semester sem,Subject sub," +
				"Batch b,SubjectTeacher st,CourseSubject cs where st.teacher=u and st.courseSubject=cs and cs.course=c and cs.semester=sem and cs.subject=sub and st.batch=b";

		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List results = query.list();
		System.out.println("CourseBatchDetailsTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT"+results);
		return results;	
		
	}
	

}
