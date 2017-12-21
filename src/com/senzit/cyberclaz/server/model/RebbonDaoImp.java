package com.senzit.cyberclaz.server.model;

import java.util.List;

import org.hibernate.SessionFactory;

public class RebbonDaoImp implements RebbonDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Rebbon getRebbonLink(String rebbonId) {

		return (Rebbon)sessionFactory.getCurrentSession().get(Rebbon.class, rebbonId);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getTeacherInClaz(String rebbonId) {
		
		String hql="select u.firstName,l.userId from User u,Log l,Rebbon rb,UserRole ur,Role r where l.user=u " +
				"and l.rebbon=rb and ur.user=u and ur.role=r and r.roleName=:role and rb.rebbonId=:rebbonId ";
		return sessionFactory.getCurrentSession()
				.createQuery(hql)
				.setParameter("role", "Teacher")
				.setParameter("rebbonId", rebbonId)
				.list();
		
	}

	@Override
	public String getRebbonIdFromUser(String userId) {
		
		String  hql="select r.rebbonId from Rebbon r,User u,StudentBatch sb,CourseBatch cb where u.userId=:userId and sb.user=u " +
						" and sb.courseBatch=cb and cb.classRoom=r.classRoom";
		return (String) sessionFactory.getCurrentSession()
				.createQuery(hql)
				.setParameter("userId", userId)
				.uniqueResult();
	}

}
