package com.senzit.cyberclaz.server.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

public class StudentBatchDaoImp implements StudentBatchDao {
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getStudBatchId(String userId) {
		
		String hql="select c.courseName,b.batchName,s.semName,cr.classRoomNo,cb.courseBatchId from Course c," +
				"Batch b,Semester s,ClassRoom cr,CourseBatch cb,StudentBatch sb where cb.course=c and " +
				"cb.semester=s and cb.batch=b and cb.classRoom=cr and sb.courseBatch=cb and sb.userId=:userId ";
		return sessionFactory.getCurrentSession()
				.createQuery(hql)
				.setParameter("userId", userId)
				.list();
				
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getStudentList(int scheduleId,int classEventDetailId) {

		String hql="select u.userId from User u,StudentBatch sb,Schedule s,ClassEventDetail e" +
				" where e.schedule=s and sb.user=u and s.courseBatch=sb.courseBatch and s.scheduleId=:scheduleId and e.classEventDetailId=:classEventDetailId ";
		return sessionFactory.getCurrentSession()
				.createQuery(hql)
				.setParameter("scheduleId", scheduleId)
				.setParameter("classEventDetailId", classEventDetailId)
				.list();
		
	}

	@Override
	public String saveCourseBatchDetailsinDb(String userId, int cbatchId) {
	
		User u=new User();
		u.setUserId(userId);
		CourseBatch cb=new CourseBatch();
		cb.setCourseBatchId(cbatchId);
	    StudentBatch sb=new StudentBatch();
	    sb.setUser(u);
	    sb.setCourseBatch(cb);
	    return (String) sessionFactory.getCurrentSession().save(sb); 
	    
	
	}

	@Override
	public int saveCourseSubjectDetailsinDb(String userId, int i,String batchId) {
		
		User u=new User();
		u.setUserId(userId);
		CourseSubject cs=new CourseSubject();
		cs.setCourseSubjectId(i);
		Batch b=new Batch();
		b.setBatchId(batchId);
		SubjectTeacher st=new SubjectTeacher();
		st.setTeacher(u);
		st.setBatch(b);
		st.setCourseSubject(cs);
		
	  return (Integer) sessionFactory.getCurrentSession().save(st);
	}

	@Override
	public Integer saveStudentBatchDetailsInDb(StudentBatch sb) {
		return (Integer)sessionFactory.getCurrentSession().save(sb);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getStudentBatchDetails() {
		String hql = "select u.userId,cb.courseBatchId FROM User u,StudentBatch sb,CourseBatch cb where sb.user=u and sb.courseBatch=cb";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List results = query.list();
		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getStudentNameFromClass(String classRoomNo) {
		String hql = "select u.userId,u.firstName,u.middleName,u.lastName, u.profilePic FROM"
				+ " User u,StudentBatch sb,CourseBatch cb,ClassRoom cr where sb.user=u and sb.courseBatch=cb and cb.classRoom=cr and cr.classRoomNo=:classRoomNo";
//		Query query = sessionFactory.getCurrentSession().createQuery(hql);
//		List results = query.list();
//		return results;
		return sessionFactory.getCurrentSession()
				.createQuery(hql)
				.setParameter("classRoomNo", classRoomNo)
				.list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getStudentFromStudentBatch(String classRoomNo){
		
		String hql = "select u FROM User u,StudentBatch sb,CourseBatch cb,ClassRoom cr"
				+ " where sb.user=u and sb.courseBatch=cb and cb.classRoom=cr and"
				+ " cr.classRoomNo=:classRoomNo";
		return sessionFactory.getCurrentSession()
				.createQuery(hql)
				.setParameter("classRoomNo", classRoomNo)
				.list();
	}

}
