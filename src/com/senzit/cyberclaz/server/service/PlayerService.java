package com.senzit.cyberclaz.server.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

public interface PlayerService {

	List<Properties> getDateFromSubject(String subjectId);

	List<Properties> getDateFromSubject(String subjectId, Integer batchId);

	List<HashMap> getClassDetail(int clazEventDetailId);

	List<Properties> getAttachmentLink(int clazEventDetailId);

	List<String> getClazNotes(int clazEventDetailId, String userId);
	/////////////////////////////
	 List<Properties> getClassEventDetail(String date,int batchId);
//	 List<Properties> getClassEventDetailFromTopic(String topic,int batchId);
     List<Properties> getClassEventDetailFromTopic(String topicName,String topicName1,int batchId);
     List<Properties> getClassEventDetailForTopic(String topic,int batchId);
	 List<Properties> getClassEventDetailFromDateSubject(String date, String subjectId, int batchId);
	 List<Properties> getClassEventDetailfromSubjectTopic(String subjectId, String topic,int batchId);
	 List<Properties> getClassEventDetailFromDateTopic(String date, String topic,int batchId);
	 List<Properties> getClassEventDetailFromSDT(String date, String subjectId,
			   String topic,int batchId);
	 
	 //Teacher
	 List<Properties> getClassEventDetail(String date);
	 List<Properties> getClassEventDetailFromTopic(String topicName);
	 List<Properties> getClassEventDetailFromDateSubject(String date, String subjectId);
	 List<Properties> getClassEventDetailfromSubjectTopic(String subjectId, String topic);
	 List<Properties> getClassEventDetailFromDateTopic(String date, String topic);
	 List<Properties> getClassEventDetailFromSDT(String date, String subjectId,
			   String topic);

	List<String> teacherIdForClazEventDetailId(
			int clazEventDetailId);

	int saveAskDoubtsInDb(String userId, String teacherName,
			String doubtText, int clazEventDetailId, Timestamp reminderTime,
			String doubt);

	String getProfilePicFromEventId(int clazEventDetailId);

	List<Properties> getAnswerForRaiseHandDoubt(int clazEventDetailId);



	boolean saveViewersDetailsInDb(String userId,int clazEventDetailId, String teacherId);

	List<String> getViewersList(int clazEventDetailId);

	void saveRatingDetailsInDb(int clazEventDetailId);

	int getUserCountValue();

	String getTeacherIdFromClazEventId(int clazEventDetailId);

	void saveDislikeRatingDetailsInDb(int clazEventDetailId);

	Properties getViewerRatingCount(int clazEventDetailId);

	void updateInViewerTable(int clazEventDetailId);

//	Integer saveViewersCount(int clazEventDetailId);

	//String getUserIdFromEventId(int clazEventDetailId);

	
	

}
