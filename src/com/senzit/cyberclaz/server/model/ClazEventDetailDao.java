package com.senzit.cyberclaz.server.model;

import java.util.Date;
import java.util.List;

public interface ClazEventDetailDao {
	


	List<Object[]> checkForLive(int courseBatchId);

	int saveNewClassEvent(ClassEventDetail classEvent);

	int setAttachmentFlag(boolean flag,int classEventDetailId);

	ClassEventDetail getEventDetials(int classEventDetailId);

	int updateDB(int classEventDetailId, String generalLog,int totalAttendees);

	List<Object[]> getDateOfEvent(String subjectId);

	List<Object[]> checkForEndTime(Integer scheduleId);

	List<Object[]> getDateOfEvent(String subjectId, Integer batchId);

	List<Object[]> getClassDetails(int clazEventDetailId);
	////////////////////////////////////////////////////
	 List<Object[]> getClassEventDetail(String date,int batchId);
//	 List<Object[]> getClassEventDetailFromTopic(String topic,int batchId);
////	 List<Object[]> getClassEventDetailFromTopic(String topicName,int batchId);
	 List<Object[]> getClassEventDetailFromTopic(String topicName,String topicName1,int batchId);
	 List<Object[]> getClassEventDetailForTopic(String topic,int batchId);
	 List<Object[]> getClassEventDetailFromDateSubject(String date, String subjectId,int batchId);
	 List<Object[]> getClassEventDetailFromSubjectTopic(String subjectId, String topic,int batchId);
	 List<Object[]> getClassEventDetailFromDateTopic(String date, String topic,int batchId);
	 List<Object[]> getClassEventDetailFromSDT(String date, String subjectId,
			   String topic,int batchId);
	 
	 //Teacher
	 List<Object[]> getClassEventDetail(String date);
	 List<Object[]> getClassEventDetailFromTopic(String topicName);
	 List<Object[]> getClassEventDetailFromDateSubject(String date, String subjectId);
	 List<Object[]> getClassEventDetailFromSubjectTopic(String subjectId, String topic);
	 List<Object[]> getClassEventDetailFromDateTopic(String date, String topic);
	 List<Object[]> getClassEventDetailFromSDT(String date, String subjectId,
			   String topic);

	List<Object[]> getClassStatus(int classEventDetailId);

	List<Object[]> getDateOfEventForTempSchedule(String subjectId);

	String getScheduleFlagForEventId(int classEventDetailId);

	List<String> getTeacherNameForTempSchedule(int eventId);

	List<Object[]> checkForLiveManual();

	//Integer checkForLiveAfterCrash(Integer batchId);








 
}
