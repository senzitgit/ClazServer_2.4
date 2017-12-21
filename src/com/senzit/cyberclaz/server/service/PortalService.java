package com.senzit.cyberclaz.server.service;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.springframework.web.multipart.MultipartFile;

public interface PortalService {

	boolean saveCourseDetailsInDb(String courseName, String courseDesc,
			String courseCategory, String courseDuration, String currentScheme,
			String semOrYear, String department, String distantOrRegular);

	boolean saveSemDetailsInDb(String semName);

	boolean saveBatchDetailsInDb(String batchName);

	boolean saveClassRoomDetailsInDb(String classRoonNo);

	boolean saveSubjectDetailsInDb(String subjectName);

	boolean saveDayDetailsInDb(String dayName);

	boolean savePeriodDetailsInDb(String startTime, String endTime);

	boolean saveCourseSubjectDetailsInDb(String courseId, String semId,
			String subId);

	boolean saveCourseBatchDetailsInDb(String courseId, String semId,
			String batchId, String cRoomId);


	List<String> getCourseSemSubIdz(String cName, String sName, String subName);

	List<String> getCourseSemBatchandClassRoomIdz(String cName, String sName,
			String bName, String cRoomNo);

	int getCourseBatchId(String courseId, String semId, String batchId,
			String classRoomId);

	boolean saveStudentBatchDetailsInDb(String studentId, int courseBatchId);

	String getbatchIdFromBatchName(String bName);

	int getCourseSubjecIdForInputs(String courseId, String semId, String subId);

	boolean saveSubjectTeacherDetailsInDb(String teacherId,
			int courseSubjectId, String batchId);

	List<Properties> getSubjectDetails();

	List<Properties> getAttachmentDetailsUploadedViaPortalForDisplay(
			String userId);

	
	void saveAttachmentDetailsUploadFromPortal(String userId, String name,
			String description, MultipartFile file1, String type);

	boolean deleteAttachmentDetailsUploadFromPortal(int mediaId);

	List<Properties> getDetailsAboutCourse();

	List<Properties> getSemDetails();

	List<Properties> getBatchDetails();

	List<Properties> getClassRoomDetails();

	List<Properties> getDayDetails();

	List<Properties> getPeriodDetails();

	List<Properties> getStudentBatchDetails();

	List<Properties> getcourseBatchDetails();



	List<Properties> getSubjectTeacherDetails();

	List<Properties> getScheduleDetails();

	List<Properties> getcourseSubjectDetailsFromDb();



	

	

}
