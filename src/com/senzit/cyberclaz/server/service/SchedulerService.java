package com.senzit.cyberclaz.server.service;

import java.util.List;
import java.util.Properties;

public interface SchedulerService {

	List<Properties> getFullSchedule(String userId);

	Integer getCourseBatchId(int scheduleId);

	List<Properties> getFullTimeTable(Integer batchId);

	List<Integer> getScheduleIdOnly(String userId);

	List<Integer> getStudentScheduleIdOnly(int batchId);

	List<Object[]> getTodaysScheduleDetails(int scheduleId);

//	Integer saveTempScheduleDetails(String dayId, String periodId,
//			Integer subTeacherId, Integer courseBatchId, int scheduleId);
	
	Integer saveTempScheduleDetails(String dayId, String periodId,
					Integer subTeacherId, Integer courseBatchId);
	Integer getCourseBatchIdForTeacher(String rebbonId);

	String getDayandPeriod(String dayName);

	Integer getSubTeacherId(String subjectId);

	String getDayandPeriodSchedule(String currentMinute);

}
