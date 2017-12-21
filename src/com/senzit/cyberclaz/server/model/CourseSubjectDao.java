package com.senzit.cyberclaz.server.model;

import java.util.List;

public interface CourseSubjectDao {

	int getCourseSubjectId(int scheduleId);

	List<Object[]> getSubjectList(String rebbonId);
	List<Object[]> getSubjectListForRemoteUser(Integer batchId);
	List<Object[]> getSubjectDetailsofUser(String userId);

	Integer saveCourseSubjectDetailsInDb(CourseSubject cSubject);

	int getCourseSubjecIdForInputs(String courseId, String semId, String subId);

	List<Object[]> getcourseSubjectDetailsFromDb();
}
