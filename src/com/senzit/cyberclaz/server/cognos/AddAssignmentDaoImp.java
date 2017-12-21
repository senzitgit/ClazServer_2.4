package com.senzit.cyberclaz.server.cognos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

public class AddAssignmentDaoImp implements AddAssignmentDao {
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Integer saveAssignAssignmentTasksInDb(AddAssignment at) {
		return (Integer) sessionFactory.getCurrentSession().save(at);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAssignedAssignmentTask(String subject) {
		String hql = "select a.topic FROM AddAssignment a,Subject sub where a.subject=sub and sub.subjectName=:subject";
		return sessionFactory.getCurrentSession()
				.createQuery(hql)
				.setParameter("subject", subject)
				.list();
	}

	@Override
	public Integer getAssignmentIdFromSubjectTopic(String subId, String topic) {
		
		String hql ="select a.assignmentId FROM AddAssignment a,Subject sub where a.subject=sub and sub.subjectId=:subject and a.topic=:topic";
		return (Integer) sessionFactory.getCurrentSession()
				.createQuery(hql)
				.setParameter("topic", topic)
				.setParameter("subject", subId)
				.list().size();
	}
}
