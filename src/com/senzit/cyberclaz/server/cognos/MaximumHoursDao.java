package com.senzit.cyberclaz.server.cognos;

import java.util.List;

public interface MaximumHoursDao {

	void saveWorkingHoursinDB(MaximumHours mh);

	List<Integer> getTeacherWorkingHoursTillNow(String teacherId);

	//void updateTeacherAttendance(MaximumHours mh);

    int updateTeacherAttendance(String teacherId, int workMinutes,int batchId);

	//void updateTeacherAttendance(MaximumHours mh);

	Integer getMaximumHoursStatus(String teacherId);

	

}
