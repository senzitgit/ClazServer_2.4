package com.senzit.cyberclaz.server.model;

import java.sql.Timestamp;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

public class NotificationDaoImp implements NotificationDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Integer saveReminderAsNotification(Notification notification) {

		return (Integer)sessionFactory.getCurrentSession().save(notification); 

	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getAllNotification(String userId) {

		String hql="select n.notificationId,n.fromUserId,n.notification,n.reminderOrRaiseHandFlag,n.notificationFlag from Notification n where n.toUserId=:userId";
		return sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("userId", userId)
				.list();
	}

	@Override
	public int getClazEventDetailId(String question,String fromUser) {
	
		String hql="select c.classEventDetailId from ClassEventDetail c ,Notification n where n.classEventDetail=c and n.notification=:question and n.fromUserId=:fromUser";
		int classEventId=(int) sessionFactory.getCurrentSession()
				.createQuery(hql)
				.setParameter("question", question)
				.setParameter("fromUser", fromUser)
				.list().get(0);

		return classEventId;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getAllNotificationForStudent(String userId) {
		
		System.out.println("dddddddddddddddddddddddddddddddd");
	
		String hql="select n.notificationId,n.toUserId,n.notification,n.notificationAnswer,n.reminderOrRaiseHandFlag,n.notificationFlag from Notification n where n.fromUserId=:userId";
		return sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("userId", userId)
				.list();
	}

//	@Override
//	public void updateRHQueuedQuestionAnswer(Notification notification) {
//		
//		 sessionFactory.getCurrentSession().update(notification);
//		
//	}
	
	@Override
	public int updateAnswerForRaiseHandandDoubtinDb(int notificationId,String answer,String flag) {
	

		
			
//		Query query = sessionFactory.getCurrentSession().createQuery("update Notification n set n.notificationFlag=:flag,n.notificationAnswer=:answer " +
//				"where n.classEventDetail in (select C from ClassEventDetail C where C.classEventDetailId=:clazEventDetailId)");
//		
		Query query = sessionFactory.getCurrentSession().createQuery("update Notification n set n.notificationFlag=:flag,n.notificationAnswer=:answer where n.notificationId=:notificationId");
		query.setParameter("flag", flag);
		query.setParameter("answer", answer);
		query.setParameter("notificationId", notificationId);
		return query.executeUpdate();
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getAnswerForRaiseHandDoubt(int clazEventDetailId) {


//    	ClassEventDetail eventObj = new ClassEventDetail();
//		eventObj.setClassEventDetailId(clazEventDetailId);
	
		String hql="select n.fromUserId,n.notification,n.notificationAnswer,n.reminderOrRaiseHandFlag from Notification n,ClassEventDetail c where n.classEventDetail=c " +
				"and c.classEventDetailId=:clazEventDetailId";
	    
		return sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("clazEventDetailId", clazEventDetailId)
				.list();
	}

	

	@Override
	public int deleteQuestionFromDB(Notification n) {
		
        sessionFactory.getCurrentSession().delete(n);
		return 1;
	}

	

	@Override
	public int updateNotificationFlag(int notificationId,
			 String answerClick) {
		
		System.out.println("DFXFGFHFFFFFFFFFFFFG"+answerClick);
		Query query = sessionFactory.getCurrentSession().createQuery("update Notification n set n.notificationFlag=:flag where n.notificationId=:notificationId");
		query.setParameter("flag", answerClick);
		
		query.setParameter("notificationId", notificationId);
		return query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getUserIdofStudent(int notificationId) {
		
		String hql="select n.fromUserId from Notification n where n.notificationId=:notificationId";
		
		return sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("notificationId", notificationId)
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getQuestionFromId(int notificationId) {
		
String hql="select n.notification from Notification n where n.notificationId=:notificationId";
		
		return (String) sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("notificationId", notificationId)
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getAllNotificationForCurrentClass(int clazEventId,String userId) {
		

		String hql="select n.notificationId,n.toUserId,n.notification,n.createdOn,n.notificationFlag from Notification n,StudentBatch sb,User u,ClassEventDetail ce" +
				" where n.classEventDetail=ce and sb.user=u and u.userId=n.fromUserId and n.fromUserId=:userId and ce.classEventDetailId=:classEventDetailId";
		return sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("classEventDetailId", clazEventId)
				.setParameter("userId", userId)
				.list();
	}
////////////////websocket issue///////////////
//	@Override
//	public int saveRaiseHandQueuedDetailsInDb(int raiseHandId,
//			String raiseHandAnswer) {
//
//
//		Query query = sessionFactory.getCurrentSession().createQuery("update Notification n set n.notificationAnswer=:raiseHandAnswer where n.notificationId=:raiseHandId");
////		query.setParameter("flag", "RHQueue");
//		query.setParameter("raiseHandId", "raiseHandId");
//		query.setParameter("raiseHandAnswer", raiseHandAnswer);
//		return query.executeUpdate();
//	}

	@Override
	public int updateRaiseHandFlag(int notificationId) {
		Query query = sessionFactory.getCurrentSession().createQuery("update Notification n set n.notificationFlag=:flag where n.notificationId=:notificationId");
		query.setParameter("flag", "RHQueue");
		query.setParameter("notificationId", "notificationId");
		return query.executeUpdate();	
	}

	@Override
	public int saveRaiseHandQueuedDetailsInDb(String raiseHandText,
			String raiseHandAnswer, int classEventDetailId) {
		
		ClassEventDetail eventObj = new ClassEventDetail();
    	eventObj.setClassEventDetailId(classEventDetailId);
		Query query = sessionFactory.getCurrentSession().createQuery("update Notification n set n.notificationFlag=:flag,n.notificationAnswer=:raiseHandAnswer where n.notification=:raiseHandText and classEventDetail=:eventObj");
		query.setParameter("flag", "RHQueue");
		query.setParameter("eventObj", eventObj);
		query.setParameter("raiseHandAnswer", raiseHandAnswer);
		query.setParameter("raiseHandText", raiseHandText);
		return query.executeUpdate();

	}

	



}
