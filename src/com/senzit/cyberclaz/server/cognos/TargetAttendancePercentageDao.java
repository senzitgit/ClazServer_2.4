package com.senzit.cyberclaz.server.cognos;

public interface TargetAttendancePercentageDao {

	Integer getAttendanceIdFromTeacherCourseBatch(String teacherId,
			Integer cBatchId);

	Integer savetargetAttendancePercentageDetailsInDb(TargetAttendancePercentage tp);

	Integer updateAttendancePercentageUpdation(String teacherId,
			Integer cBatchId, String attendancePercentage);
	

}
