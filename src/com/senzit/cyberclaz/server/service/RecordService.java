package com.senzit.cyberclaz.server.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import org.springframework.web.multipart.MultipartFile;

public interface RecordService {
	
	String getRebbonLink(String rebbonId);	

	String getRebbonIdFromUser(String userId);

	Properties checkForLiveSession(int courseBatchId);

	List<String> getTeacherInClass(String rebbonId);
	
	//List<String> getTeacherNameForBatch(int batchId);

	int createNewClassEvent(int scheduleId, String chapter, String topic,String tempSchedule, String subjectName, String courseName, String teacherName, String teacherId, String profilePic);

	Properties getAttendance(int scheduleId);
	
	Properties getAttendance(String rebbonId);

	List<String> getTeacherFromBatch(int courseBatchId);

	int updateAttachmentFlag(int classEventDetailId);

	boolean updateDB(int classEventDetailId, String generalLog,int totalAttendees);

	boolean insertClazNote(int classEventId, String userId, String clazNoteJson);

	void SaveAttachmentDetails(int classEventDetailId, String attachmentName,
			String attachmentLink, String attachmentType, Timestamp createdOn,byte att);

	void saveRaiseHandDetails(String from, String teacherId, String content,int classEventDetailId,Timestamp createdOn);

	void saveReminderNote(String teacherId, String tempRnote,
			int classEventDetailId, Timestamp reminderTime);

	int getCourseSubjectId(int scheduleId);

	void saveAttendanceDetails(String userId,int courseSubId,int classEventDetailId,
			int attendedSession, int totalNumberOfSession,String month);

	int getChangedSchedule(String teacherId, String subjectId,int CourseBatchId);

	boolean saveUploadedImage(MultipartFile file1, String fileName,
			 int classEventDetailId,byte imageType,String name,String time,String type);
	
	

//	boolean saveUploadedImage(String file1, String fileName,
//			 int classEventDetailId,byte imageType,String name,String time,String type);
	List<String> getStudentList(int scheduleId,int clazEventDetailId);

	int getClazEventDetailId(String question,String userId);

	void updateAnswerForRaiseHandandDoubtinDb(int notificationId,String answer,String answeredQuestion);

	

	int deleteQuestionFromDB(String raiseHandAskaDoubtQuestion,
			String fromUserId);


	void updateNotificationFlag(int notificationId, 
			String answerClick);

	List<Properties> getAttachmentDetailsUploadedViaPortal(String teacherId);
	
	//List<HashMap> getRaiseHandQuestionAndAnswer(int courseBatchId);

	List<HashMap> getRaiseHandQuestionAndAnswer(int classEventDetailId,String userId);

	void saveAttendanceForUser(String userId, int classEventDetailId);

	void saveRaiseHandQueuedDetailsInDb(
			String raiseHandText, String raiseHandAnswer,
			int classEventDetailId);

	int getClassStatus(int classEventDetailId);

	//void saveRaiseHandQueuedDetailsInDb(int raiseHandId, String raiseHandAnswer);

	void updateRaiseHandFlag(int notificationId);

	void saveWorkingHoursinDB(String teacherId, int time1,int batchId);

	List<HashMap> classDetailsAfterCrash(Integer batchId,String time);

	boolean saveUploadedImageFromPortal(String file, String name,
			int classEventDetailId, byte imageType, String time,
			String type);
	boolean saveUploadedImageFromAndroid(String file, String name,
			int classEventDetailId, byte imageType, String time,
			String type);

	Properties checkForLiveSessionManual();


	

}
