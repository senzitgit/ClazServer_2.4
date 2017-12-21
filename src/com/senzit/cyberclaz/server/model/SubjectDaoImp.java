package com.senzit.cyberclaz.server.model;

import java.util.List;
import java.util.Properties;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

public class SubjectDaoImp implements SubjectDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) 
	{
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Integer saveSubjectDetailsDetailsInDb(Subject sub) {
		return (Integer)sessionFactory.getCurrentSession().save(sub);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String>  getFullSubjectList() {

		String hql="select subjectName from Subject";
		
		return sessionFactory.getCurrentSession()
				.createQuery(hql)
				.list();
//		String hql = "select courseName,courseDescription,courseCategory,courseDuration,currentScheme,semOrYear,department,distantOrRegular FROM Course";
//		Query query = sessionFactory.getCurrentSession().createQuery(hql);
//		List results = query.list();
//		return results;
//	
	}

	@Override
	public String getSubjectNameFromSubjectId(String subject) {
		
			String hql="select sub.subjectId from Subject sub where sub.subjectName=:subject";
			return (String) sessionFactory.getCurrentSession().createQuery(hql)
					.setParameter("subject", subject)
					.uniqueResult();
			
		
	}

	
	


}
