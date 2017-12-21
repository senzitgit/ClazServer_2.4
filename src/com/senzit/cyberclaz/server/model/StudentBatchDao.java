package com.senzit.cyberclaz.server.model;

import java.util.List;

public interface StudentBatchDao {
	
	List<Object[]> getStudBatchId(String userId);
	
	List<String> getStudentList(int scheduleId,int clazEventDetailId);

	String saveCourseBatchDetailsinDb(String userId, int cbatchId);

	int saveCourseSubjectDetailsinDb(String userId, int i, String batchId);

	Integer saveStudentBatchDetailsInDb(StudentBatch sb);

	List<Object[]> getStudentBatchDetails();

	List<Object[]> getStudentNameFromClass(String classRoomNo);
	
	List<User> getStudentFromStudentBatch(String classRoomNo);



}
