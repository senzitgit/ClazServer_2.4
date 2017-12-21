package com.senzit.cyberclaz.server.model;

import java.util.List;

import org.hibernate.SessionFactory;

public class LogDaoImp implements LogDao
{
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void setLogStatus(Log logObj) 
	{
		sessionFactory.getCurrentSession().save(logObj);
		
	}

	@Override
	public Log getUserStatus(String userId) 
	{
		return (Log)sessionFactory.getCurrentSession().get(Log.class, userId);
	
	}

	@Override
	public void updateLoginStatus(Log logObj) {
		sessionFactory.getCurrentSession().update(logObj);
		
	}

	@Override
	public Log isRebbonExist(String rebbonId) {
		
		return (Log)sessionFactory.getCurrentSession().get(Log.class, rebbonId);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]>  getTeacherFromBatch(int courseBatchId) {
		
//		String hql="select l.userId,u.firstName from User u,Log l,Rebbon rb,UserRole ur,Role r," +
//				"CourseBatch cb,ClassRoom cr where l.user=u and l.rebbon=rb and ur.user=u and ur.role=r" +
//				" and r.roleName=:role and rb.classRoom=cb.classRoom and cb.courseBatchId=:courseBatchId ";
		
		String hql="select l.userId,u.firstName from User u,Log l,Rebbon rb,UserRole ur,Role r," +
				"CourseBatch cb,ClassRoom cr where l.userId=u.userId and l.rebbon=rb and ur.role=r" +
				" and r.roleName=:role and ur.user=u and rb.classRoom=cb.classRoom and cb.courseBatchId=:courseBatchId ";
		
		return sessionFactory.getCurrentSession()
				.createQuery(hql)
				.setParameter("role", "Teacher")
				.setParameter("courseBatchId", courseBatchId)
				.list();
				
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getRebbonUser(String rebbonId) {
		//select u.userId from User u,Log l,Rebbon r,UserRole UR,Role ro where UR.userid=u.userid and ur.roleid=ro.roleid and ro.rolename='Teacher' and l.userid=u.userid and l.rebbonid=r.rebbonid and r.rebbonid='r101';
		//select u.userId from User u,Rebbon r,Log l,SubjectTeacher st where st.userid=l.userid and l.userid=u.userid and r.rebbonid=l.rebbonid and r.rebbonId='r101';
		//select u.userId from user u,log l,rebbon r,Subjectteacher st where st.userid=u.userid and l.userid=u.userid and r.rebbonid='r101' ;
		String hql="select u.userId from User u,Rebbon r,Log l,UserRole ur,Role ro where ur.user=u and ur.role=ro and ro.roleName='Teacher' and" +
				" l.user=u and l.rebbon=r and l.user=u and r.rebbonId=:rebbonId";
		
		System.out.println("SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS"+sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("rebbonId", rebbonId)
				.list());
		return sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("rebbonId", rebbonId)
				.list();
	}

	@Override
	public Log getUserLog(String userId) {
		
		return (Log)sessionFactory.getCurrentSession().get(Log.class, userId);
	}

	@Override
	public String getSession(String userId) {
		String hql="select L.sessionId from Log L where L.userId=:userId";
		return (String) sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("userId", userId)
				.uniqueResult();
	}


	

	

}
