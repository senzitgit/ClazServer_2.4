package com.senzit.cyberclaz.server.model;

import java.sql.Timestamp;
import java.util.List;

public interface NotificationDao {

	Integer saveReminderAsNotification(Notification notification);

	List<Object[]> getAllNotification(String userId);

	int getClazEventDetailId(String question,String fromUser);

	List<Object[]> getAllNotificationForStudent(String userId);



	List<Object[]> getAnswerForRaiseHandDoubt(int clazEventDetailId);

	int deleteQuestionFromDB(Notification n);

	int updateNotificationFlag(int notificationId, 
			String answerClick);
	
	int updateAnswerForRaiseHandandDoubtinDb(int notificationId,String answer, String answeredQuestion);

	List<String> getUserIdofStudent(int notificationId);

	String getQuestionFromId(int notificationId);

	List<Object[]> getAllNotificationForCurrentClass(int clazEventId,String userId);

	

	int saveRaiseHandQueuedDetailsInDb(String raiseHandText, String raiseHandAnswer,
			int classEventDetailId);

	//int saveRaiseHandQueuedDetailsInDb(int raiseHandId, String raiseHandAnswer);

	int updateRaiseHandFlag(int notificationId);

	

	//void updateRHQueuedQuestionAnswer(Notification notification);

	
	

}
