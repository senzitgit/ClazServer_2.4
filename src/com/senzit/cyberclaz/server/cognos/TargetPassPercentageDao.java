package com.senzit.cyberclaz.server.cognos;

public interface TargetPassPercentageDao {

	Integer savetargetPassPercentageDetailsInDb(TargetPassPercentage tp);

	Integer getTargetIdFromTeacherCourseBatch(String teacherId, Integer cBatchId);

	Integer updatePassPercentageUpdation(String teacherId, Integer cBatchId,
			String passPercentage);


}
