package com.senzit.cyberclaz.server.service;

import java.util.List;
import java.util.Properties;

import com.senzit.cyberclaz.server.model.User;

public interface CognosService {

	Integer saveAssignmentTasksInDb(String userId, String subjectId,
			String topic);
	
	void assignTaskToAll(String classRoomNo,String subject,String topic);
	void assignTaskToGroup(String subject,String assignJson);

	String getSubjectNameFromSubjectId(String subject);

	void saveprogressReportsInDb(String userId, String term, String subId,
			String mark);

	void saveteacherRecommendationInDb(String userId, String subId,String term,
			String rating);

	 List<Properties> getAttendanceDetails(String userId, String month, String subId);

	void saveFutureGoalsInDb(String userId,Integer cBatchId, String goal);

	//void saveUserRatingAboutTeacher(String teacherId);

	List<Properties> getStudentNameFromClass(String classRoomNo);

	List<Properties> getTeacherList();

	boolean updateAssignmentTaskStatus(String userId,String subId,String status);

	boolean saveSubjectPerformanceDetailsInDb(String userId, String subId,
			String rating);

	boolean subjectPerformanceDetailsUpdation(String userId,String subjectId,String rating);

	Integer getCourseBatchFromClassRoom(String classRoomNo);

	void savetargetPassPercentageDetailsInDb(String teacherId,
			Integer cBatchId, String passPercentage);

//	void saveTeacherPerformanceDetails(String teacherId);

	void saveAttendancePassPercentageDetailsInDb(String teacherId,
			Integer cBatchId, String attendancePercentage);

	List<Properties> getAssignedAssignmentTask(String subject);

	void saveViewersCount(int clazEventDetailId);

	Integer saveAssignAssignmentTasksInDb(String subId, String topic);

	Integer getAssignmentIdFromSubject(String subId, String topic);

	Integer getTaskIdFromSubjectTopic(String userId, String subId, String topic);

	

	

}
