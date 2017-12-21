package com.senzit.cyberclaz.server.cognos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.senzit.cyberclaz.server.model.Subject;
import com.senzit.cyberclaz.server.model.User;
import com.senzit.cyberclaz.server.model.VideoRating;


public class AssignedTaskDaoImp implements AssignedTaskDao 
{

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Integer saveAssignmentTasksInDb(AssignedTask at) {
		return (Integer)sessionFactory.getCurrentSession().save(at);
		
	}

	@Override
	public Integer updateAssignmentTaskStatus(String userId,String subId,String status) {
		
		String hql = "update AssignedTask at set at.status=:status where at.user=(select u from User u where u.userId=:userId) and at.subject=(select sub from Subject sub where sub.subjectId=:subId)";
		return sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("status", status)
				.setParameter("userId", userId)
				.setParameter("subId", subId)
				.executeUpdate();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getAssignedAssignmentTask() {
		String hql = "select taskId,assignedTopic FROM AssignedTask";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List results = query.list();
		return results;
	}

	@Override
	public Integer getTaskIdFromSubjectTopic(String userId, String subId,
			String topic) {
		String hql ="select a.taskId FROM AssignedTask a,Subject sub,User u where a.subject=sub and a.user=u and u.userId=:userId and sub.subjectId=:subject and a.assignedTopic=:topic";
		return (Integer) sessionFactory.getCurrentSession()
				.createQuery(hql)
				.setParameter("topic", topic)
				.setParameter("subject", subId)
				.setParameter("userId", userId)
				.list().size();
	}

}
