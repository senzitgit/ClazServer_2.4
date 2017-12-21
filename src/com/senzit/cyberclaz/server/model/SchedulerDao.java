package com.senzit.cyberclaz.server.model;

import java.util.List;

public interface SchedulerDao {

	List<Object[]> getFullScheduleTeacher(String userId);

	List<Integer> getCourseBatch(int scheduleId);

	List<Object[]> getFullScheduleStudent(Integer batchId);

	List<Object[]> getTodaysScheduleTeacher(String userId);

	List<Object[]> getTodaysScheduleStudent(int batchId);

	//int getNewSchedule(String teacherId, Integer subjectId,int CourseBatchId);
	List<Integer> getNewSchedule(String teacherId, String subjectId,Integer CourseBatchId);

	Integer saveScheduleDetails(String i, String j, String userId, int k);

	List<Object[]> getTodaysScheduleDetails(int scheduleId);

	List<Integer> getCourseBatchIdForTeacher(String rebbonId);

	List<String> getDayandPeriod(String dayName);

	List<Integer> getSubTeacherId(String subjectId);

	List<String> getDayandPeriodSchedule(String currentMinute);

	List<Object[]> getScheduleDetails();

}
