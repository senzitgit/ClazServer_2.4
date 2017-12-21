package com.senzit.cyberclaz.server.model;

import java.util.List;

import org.hibernate.SessionFactory;

public class NoteDaoImp implements NoteDao
{
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) 
	{
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Integer insertStudentClazNote(Note noteObj) {
		
		return (Integer)sessionFactory.getCurrentSession().save(noteObj);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getClazNotes(String userId, int clazEventDetailId) {
		
		  String hql="select n.notes from Note n,ClassEventDetail c,User u where n.classEventDetail=c " +
		  		"and c.classEventDetailId=:classEventDetailId and n.user=u and u.userId=:userId";

		  return sessionFactory.getCurrentSession()    
		    .createQuery(hql)
		    .setParameter("classEventDetailId", clazEventDetailId)
		    .setParameter("userId", userId)
		    .list();
	}
	
	

}
