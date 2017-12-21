package com.senzit.cyberclaz.server.model;

import java.util.List;

public interface CourseDao {

	Integer saveCourseDetailsInDb(Course course);

	List<String> getCourseSemSubIdz(String cName, String sName, String subName);

	List<String> getCourseSemBatchandClassRoomIdz(String cName, String sName,
			String bName, String cRoomNo);

	List<Object[]> getDetailsAboutCourse();

	

}
